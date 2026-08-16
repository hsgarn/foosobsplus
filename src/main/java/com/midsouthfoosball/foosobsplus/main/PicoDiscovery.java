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
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.net.InetAddresses;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

public class PicoDiscovery {
	private static final Logger logger = LoggerFactory.getLogger(PicoDiscovery.class);
	private static final int BROADCAST_ATTEMPTS = 5;

	/**
	 * One discovered AutoScore device, parsed from a discovery response.
	 * Response formats:
	 *   Legacy: "Table N:ipaddress:port"                        (e.g., "Table 1:192.168.68.74:5051")
	 *   Free:   "Table N:ipaddress:port:MAC:FREE"               (e.g., "Table 2:192.168.68.75:5051:28-cd-c1-0f-12-34:FREE")
	 *   Busy:   "Table N:ipaddress:port:MAC:BUSY:clientIP"      (a FoosOBSPlus TCP client at clientIP is connected)
	 * The MAC address is lowercase, using dashes (or no separator), never colons;
	 * the only colon past the MAC is the one inside "BUSY:clientIP", which stays
	 * in the status field.
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
			if (parts.length != 3 && parts.length != 5) return null;
			if (!InetAddresses.isInetAddress(parts[1])) return null;
			try { int p = Integer.parseInt(parts[2]); if (p < 1 || p > 65535) return null; }
			catch (NumberFormatException e) { return null; }
			String mac = parts.length == 5 ? TableConnection.normalizeMac(parts[3]) : ""; //$NON-NLS-1$
			if (!TableConnection.isValidMac(mac)) return null;
			PicoInfo info = new PicoInfo(parts[0].trim(), parts[1], parts[2], mac, parts.length == 5 ? parts[4] : "", trimmed); //$NON-NLS-1$
			return info.tableNumber() > 0 ? info : null;
		}

		/** The table number parsed from the label ("Table N"), or -1 if it cannot be parsed. */
		public int tableNumber() {
			java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+)\\s*$").matcher(label.trim()); //$NON-NLS-1$
			if (!m.find()) return -1;
			try { return Integer.parseInt(m.group(1)); } catch (NumberFormatException e) { return -1; }
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
	 * Local IPv4 addresses to bind a discovery socket to, one per broadcast-capable
	 * interface. A single unbound socket lets the OS pick whichever adapter it
	 * defaults to for broadcast traffic, which on a PC with multiple active
	 * adapters (Ethernet + WiFi, or a virtual adapter from a VPN, Hyper-V, Docker,
	 * WSL, VMware, etc.) may not be the network the Picos are actually on. Binding
	 * a socket per interface and broadcasting from each guarantees the request
	 * goes out every segment, mirroring FoosTableManager.py's broadcast().
	 */
	private static List<InetAddress> localBroadcastAddresses() {
		List<InetAddress> addresses = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				if (iface.isLoopback() || !iface.isUp() || iface.isPointToPoint()) {
					continue;
				}
				for (InterfaceAddress ifaceAddr : iface.getInterfaceAddresses()) {
					if (ifaceAddr.getAddress() instanceof Inet4Address && ifaceAddr.getBroadcast() != null) {
						addresses.add(ifaceAddr.getAddress());
					}
				}
			}
		} catch (SocketException e) {
			logger.warn("Failed to enumerate network interfaces", e); //$NON-NLS-1$
		}
		return addresses;
	}

	/**
	 * Broadcasts discovery requests and collects responses from ALL picos on the
	 * network (there may be one per table). A socket is bound to each local
	 * broadcast-capable interface so the request goes out on every adapter, not
	 * just whichever one the OS would otherwise default to. Every broadcast
	 * attempt drains responses until all sockets go quiet, and duplicate
	 * responses from the same pico across attempts are ignored.
	 *
	 * @param port the UDP port to broadcast on
	 * @param timeoutMs how long to wait, after the last response, before giving up on an attempt
	 * @param statusCallback optional progress messages for the settings log
	 * @return all unique devices discovered, in arrival order (possibly empty)
	 */
	public static List<PicoInfo> discoverPicos(int port, int timeoutMs, Consumer<String> statusCallback) throws Exception {
		Set<String> rawResponses = new LinkedHashSet<>();
		List<DatagramSocket> sockets = new ArrayList<>();
		int perSocketTimeoutMs = Math.max(20, Math.min(50, timeoutMs));

		List<InetAddress> localAddresses = localBroadcastAddresses();
		if (localAddresses.isEmpty()) {
			// No usable interface list; fall back to letting the OS pick.
			localAddresses.add(null);
		}
		for (InetAddress localAddr : localAddresses) {
			try {
				DatagramSocket socket = (localAddr == null) ? new DatagramSocket() : new DatagramSocket(0, localAddr);
				socket.setBroadcast(true);
				socket.setSoTimeout(perSocketTimeoutMs);
				sockets.add(socket);
			} catch (Exception e) {
				logger.warn("Failed to open discovery socket on " + localAddr, e); //$NON-NLS-1$
			}
		}
		if (sockets.isEmpty()) {
			throw new Exception("Unable to open any discovery socket"); //$NON-NLS-1$
		}

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
				for (DatagramSocket socket : sockets) {
					try {
						socket.send(sendPacket);
					} catch (Exception e) {
						logger.warn("Broadcast send failed on " + socket.getLocalAddress(), e); //$NON-NLS-1$
					}
				}

				// Drain every response to this broadcast round-robin across all
				// sockets until none of them have produced anything for timeoutMs.
				long lastActivity = System.currentTimeMillis();
				while (System.currentTimeMillis() - lastActivity < timeoutMs) {
					for (DatagramSocket socket : sockets) {
						try {
							byte[] receiveData = new byte[1024];
							DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
							socket.receive(receivePacket);
							lastActivity = System.currentTimeMillis();

							String msg = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8).trim();
							logger.info("Received: " + msg); //$NON-NLS-1$
							if (msg.startsWith("Table") && rawResponses.add(msg)) { //$NON-NLS-1$
								if (statusCallback != null) statusCallback.accept("Received: " + msg); //$NON-NLS-1$
							}
						} catch (SocketTimeoutException _) {
							// this socket had nothing within its poll window; try the others
						}
					}
				}
				if (rawResponses.isEmpty()) {
					String retryMsg = "No response, retrying (attempt " + (i+1) + ")..."; //$NON-NLS-1$ //$NON-NLS-2$
					logger.info(retryMsg);
					if (statusCallback != null) statusCallback.accept(retryMsg);
				}
			}
		} finally {
			for (DatagramSocket socket : sockets) {
				socket.close();
			}
		}

		return parseUniqueResponses(rawResponses, statusCallback);
	}

	/** Parses and deduplicates responses by MAC, or by endpoint for legacy devices. */
	public static List<PicoInfo> parseUniqueResponses(Iterable<String> rawResponses, Consumer<String> statusCallback) {
		List<PicoInfo> picos = new ArrayList<>();
		Set<String> deviceKeys = new LinkedHashSet<>();
		for (String raw : rawResponses) {
			PicoInfo info = PicoInfo.parse(raw);
			if (info == null) {
				logger.warn("Invalid discovery response format: " + raw); //$NON-NLS-1$
				if (statusCallback != null) statusCallback.accept("Ignoring invalid response: " + raw); //$NON-NLS-1$
			} else {
				String key = info.macAddress().isEmpty() ? info.ipAddress() + ":" + info.port() : info.macAddress(); //$NON-NLS-1$
				if (!deviceKeys.add(key)) continue;
				picos.add(info);
			}
		}
		return picos;
	}
}
