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
package com.midsouthfoosball.foosobsplus.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for network-related operations
 */
public class NetworkUtil {
	private static final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

	/**
	 * Get all local machine IPv4 addresses across all active non-loopback interfaces.
	 * @return list of IP address strings; empty if none found
	 */
	public static List<String> getAllLocalIPAddresses() {
		List<String> addresses = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()) {
				NetworkInterface netInterface = networks.nextElement();
				if (netInterface.isLoopback() || !netInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> inetAddresses = netInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().contains(".")) {
						addresses.add(inetAddress.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			logger.error("Error detecting local IP addresses", e);
		}
		return addresses;
	}

	/**
	 * Get the local machine's IP address on the network
	 * Prefers IPv4 addresses and skips localhost/loopback addresses
	 * @return IP address string, or "Unknown" if unable to determine
	 */
	public static String getLocalIPAddress() {
		try {
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()) {
				NetworkInterface netInterface = networks.nextElement();

				// Skip loopback and inactive interfaces
				if (netInterface.isLoopback() || !netInterface.isUp()) {
					continue;
				}

				Enumeration<InetAddress> inetAddresses = netInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();

					// Prefer IPv4 addresses (not IPv6)
					if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().contains(".")) {
						logger.info("Local IP Address detected: {}", inetAddress.getHostAddress());
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			logger.error("Error detecting local IP address", e);
		}

		// Fallback: try localhost
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			logger.error("Error getting localhost address", e);
		}

		return "Unknown";
	}
}
