/**
Copyright © 2026 Hugh Garner
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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sends the AutoScore (Pico) FLASH and REPORT_TABLE commands directly to a
 * single device by IP, matching the protocol used by FoosTableManager.py's
 * flashRetry()/reportRetry(): a unicast UDP packet to the Pico's discovery
 * port ("FLASH:&lt;mac&gt;" or "REPORT_TABLE:&lt;mac&gt;"), resent a couple of
 * times if no matching reply arrives since a single UDP packet is sometimes
 * dropped. The Pico replies "FLASHING:&lt;mac&gt;" / "REPORTING:&lt;mac&gt;:&lt;table&gt;",
 * or "BUSY:&lt;mac&gt;" if it refuses because a game connection is active.
 */
public final class PicoCommand {
	private static final Logger logger = LoggerFactory.getLogger(PicoCommand.class);
	public static final int PICO_PORT = 5051;
	private static final int RETRIES = 3;
	private static final int RETRY_TIMEOUT_MS = 200;

	private PicoCommand() {}

	/** Outcome of a Flash / Report Table Number request. */
	public record Result(boolean acked, String reply) {}

	/** Asks the Pico at ip (identified by mac) to flash its LEDs. Blocks; call off the EDT. */
	public static Result flash(String ip, String mac) {
		return send(ip, "FLASH:" + mac, mac); //$NON-NLS-1$
	}

	/** Asks the Pico at ip (identified by mac) to report its configured table number. Blocks; call off the EDT. */
	public static Result reportTable(String ip, String mac) {
		return send(ip, "REPORT_TABLE:" + mac, mac); //$NON-NLS-1$
	}

	private static Result send(String ip, String message, String mac) {
		try (DatagramSocket socket = new DatagramSocket()) {
			socket.setSoTimeout(RETRY_TIMEOUT_MS);
			byte[] data = message.getBytes(StandardCharsets.UTF_8);
			InetAddress address = InetAddress.getByName(ip);
			DatagramPacket packet = new DatagramPacket(data, data.length, address, PICO_PORT);
			byte[] buffer = new byte[256];
			for (int attempt = 0; attempt < RETRIES; attempt++) {
				socket.send(packet);
				try {
					DatagramPacket response = new DatagramPacket(buffer, buffer.length);
					socket.receive(response);
					String reply = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8).trim();
					String[] parts = reply.split(":"); //$NON-NLS-1$
					// The reply's 2nd colon-field is always the mac (FLASHING:<mac>,
					// REPORTING:<mac>:<table>, BUSY:<mac>) - only trust a reply that
					// names the device we actually addressed.
					if (parts.length >= 2 && parts[1].trim().equalsIgnoreCase(mac)) {
						return new Result(true, reply);
					}
					logger.info("PicoCommand ignoring reply not matching mac " + mac + ": " + reply); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (SocketTimeoutException e) {
					// No reply within the window; resend on the next attempt.
				}
			}
			return new Result(false, null);
		} catch (IOException e) {
			logger.error("PicoCommand send to " + ip + " failed: " + e); //$NON-NLS-1$ //$NON-NLS-2$
			return new Result(false, null);
		}
	}
}
