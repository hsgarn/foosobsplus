package com.midsouthfoosball.foosobsplus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.common.net.InetAddresses;

/** Normalizes and validates the shared AutoScore table-connection model. */
public final class AutoScoreConnectionValidator {
	private AutoScoreConnectionValidator() {}

	public static List<String> validate(List<TableConnection> connections) {
		List<String> errors = new ArrayList<>();
		Set<String> labels = new HashSet<>();
		Map<String, Integer> macRows = new HashMap<>();
		Map<String, Integer> endpointRows = new HashMap<>();
		for (int i = 0; i < connections.size(); i++) {
			TableConnection c = connections.get(i);
			String label = trim(c.getLabel());
			String address = trim(c.getServerAddress());
			String port = trim(c.getServerPort());
			c.setLabel(label);
			c.setServerAddress(address);
			c.setServerPort(port);

			String rowName = label.isEmpty() ? "Table " + (i + 1) : label; //$NON-NLS-1$
			if (label.isEmpty()) errors.add(rowName + ": label is required."); //$NON-NLS-1$
			else if (!labels.add(label.toLowerCase(Locale.ROOT))) errors.add(label + ": duplicate label."); //$NON-NLS-1$
			if (!InetAddresses.isInetAddress(address)) errors.add(rowName + ": invalid IP address \"" + address + "\"."); //$NON-NLS-1$ //$NON-NLS-2$

			boolean validPort = false;
			try { int parsed = Integer.parseInt(port); validPort = parsed >= 1 && parsed <= 65535; }
			catch (NumberFormatException ignored) { /* reported below */ }
			if (!validPort) errors.add(rowName + ": invalid port \"" + port + "\"."); //$NON-NLS-1$ //$NON-NLS-2$

			String mac = c.getMacAddress();
			if (!TableConnection.isValidMac(mac)) errors.add(rowName + ": invalid MAC address \"" + mac + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
			else if (!mac.isEmpty()) {
				c.setMacAddress(mac);
				Integer prior = macRows.putIfAbsent(c.getMacAddress(), i);
				if (prior != null) errors.add(rowName + ": MAC address is already assigned to " + displayName(connections, prior) + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (InetAddresses.isInetAddress(address) && validPort) {
				Integer prior = endpointRows.putIfAbsent(address + ":" + port, i); //$NON-NLS-1$
				if (prior != null) errors.add(rowName + ": address and port are already used by " + displayName(connections, prior) + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return errors;
	}

	private static String trim(String value) { return value == null ? "" : value.trim(); } //$NON-NLS-1$
	private static String displayName(List<TableConnection> connections, int index) {
		String label = trim(connections.get(index).getLabel());
		return label.isEmpty() ? "table " + (index + 1) : label; //$NON-NLS-1$
	}
}
