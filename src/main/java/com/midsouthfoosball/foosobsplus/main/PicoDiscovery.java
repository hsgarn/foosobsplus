/**
Copyright © 2025-2026 Hugh Garner
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
**/
package com.midsouthfoosball.foosobsplus.main;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PicoDiscovery {
	private static final Logger logger = LoggerFactory.getLogger(PicoDiscovery.class);
	private static final int BROADCAST_ATTEMPTS = 5;

	/**
	 * One discovered AutoScore device, parsed from a discovery response.
	 * Response formats:
	 *   Legacy: "Table N:ipaddress:port"                        (e.g., "Table 1:192.168.68.74:5051")
	 *   Free:   "Table N:ipaddress:port:MAC:FREE"               (e.g., "Table 2:192.168.68.75:5051:28-CD-C1-0F-12-34:FREE")
	 *   Busy:   "Table N:ipaddress:port:MAC:BUSY:clientIP"      (a FoosOBSPlus TCP client at clientIP is connected)
	 * The MAC address uses dashes (or no separator), never colons; the only colon
	 * past the MAC is the one inside "BUSY:clientIP", which stays in the status field.
	 */
	public record PicoInfo(String label, String ipAddress, String port, String macAddress, String status, String raw) {
		private static final String BUSY_PREFIX = "BUSY:"; //$NON-NLS-1$

		/** Parses a discovery response; returns null if the message is not a valid response. */
		public static PicoInfo parse(String msg) {
			if (msg == null) {
				return null;
			}
			String trimmed = msg.trim();
			if (!trimmed.startsWith("Table")) { //$NON-NLS-1$
				return null;
			}
			// Limit 5 keeps "BUSY:clientIP" together as the status field.
			String[] parts = trimmed.split(":", 5); //$NON-NLS-1$
			if (parts.length == 3) {
				return new PicoInfo(parts[0], parts[1], parts[2], "", "", trimmed); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (parts.length == 5) {
				return new PicoInfo(parts[0], parts[1], parts[2], parts[3], parts[4], trimmed);
			}
			return null;
		}

		/** The table number parsed from the label ("Table N"), or -1 if it cannot be parsed. */
		public int tableNumber() {
			java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+)\\s*$").matcher(label.trim()); //$NON-NLS-1$
			return m.find() ? Integer.parseInt(m.group(1)) : -1;
		}

		/** True when the device reports anything other than FREE (a client is connected, or unknown status). */
		public boolean isBusy() {
			return !status.isEmpty() && !status.equalsIgnoreCase("FREE"); //$NON-NLS-1$
		}

		/** The connected client's IP when the status is BUSY:clientIP, otherwise empty. */
		public String busyClientIp() {
			if (status.regionMatches(true, 0, BUSY_PREFIX, 0, BUSY_PREFIX.length())) {
				return status.substring(BUSY_PREFIX.length());
			}
			return ""; //$NON-NLS-1$
		}

		/** One-line summary for lists and log messages. */
		public String display() {
			StringBuilder sb = new StringBuilder();
			sb.append(label).append("  ").append(ipAddress).append(":").append(port); //$NON-NLS-1$ //$NON-NLS-2$
			if (!macAddress.isEmpty()) {
				sb.append("  MAC ").append(macAddress); //$NON-NLS-1$
			}
			String clientIp = busyClientIp();
			if (!clientIp.isEmpty()) {
				sb.append("  [IN GAME - ").append(clientIp).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (!status.isEmpty()) {
				sb.append("  [").append(status).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return sb.toString();
		}
	}

	/**
	 * Broadcasts discovery requests and collects responses from ALL picos on the
	 * network (there may be one per table). Every broadcast attempt drains
	 * responses until the socket goes quiet, and duplicate responses from the
	 * same pico across attempts are ignored.
	 *
	 * @param port the UDP port to broadcast on
	 * @param timeoutMs how long to wait for each response before giving up on an attempt
	 * @param statusCallback optional progress messages for the settings log
	 * @return all unique devices discovered, in arrival order (possibly empty)
	 */
	public static List<PicoInfo> discoverPicos(int port, int timeoutMs, Consumer<String> statusCallback) throws Exception {
		Set<String> rawResponses = new LinkedHashSet<>();
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);
		socket.setSoTimeout(timeoutMs);

		try {
			byte[] sendData = "DISCOVER_PICO".getBytes(StandardCharsets.UTF_8); //$NON-NLS-1$
			DatagramPacket sendPacket = new DatagramPacket(
				sendData,
				sendData.length,
				InetAddress.getByName("255.255.255.255"), //$NON-NLS-1$
				port
			);

			for (int i = 0; i < BROADCAST_ATTEMPTS; i++) {
				String attemptMsg = "Broadcasting discovery request (attempt " + (i+1) + ")..."; //$NON-NLS-1$ //$NON-NLS-2$
				logger.info(attemptMsg);
				if (statusCallback != null) statusCallback.accept(attemptMsg);
				socket.send(sendPacket);

				// Drain every response to this broadcast until the socket goes quiet.
				while (true) {
					try {
						byte[] receiveData = new byte[1024];
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
						socket.receive(receivePacket);

						String msg = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8).trim();
						logger.info("Received: " + msg); //$NON-NLS-1$
						if (msg.startsWith("Table") && rawResponses.add(msg)) { //$NON-NLS-1$
							if (statusCallback != null) statusCallback.accept("Received: " + msg); //$NON-NLS-1$
						}
					} catch (SocketTimeoutException _) {
						break;
					}
				}
				if (rawResponses.isEmpty()) {
					String retryMsg = "No response, retrying (attempt " + (i+1) + ")..."; //$NON-NLS-1$ //$NON-NLS-2$
					logger.info(retryMsg);
					if (statusCallback != null) statusCallback.accept(retryMsg);
				}
			}
		} finally {
			socket.close();
		}

		List<PicoInfo> picos = new ArrayList<>();
		for (String raw : rawResponses) {
			PicoInfo info = PicoInfo.parse(raw);
			if (info == null) {
				logger.warn("Invalid discovery response format: " + raw); //$NON-NLS-1$
				if (statusCallback != null) statusCallback.accept("Ignoring invalid response: " + raw); //$NON-NLS-1$
			} else {
				picos.add(info);
			}
		}
		return picos;
	}
}
