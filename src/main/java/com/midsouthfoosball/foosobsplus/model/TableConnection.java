/**
Copyright © 2020-2026 Hugh Garner
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
package com.midsouthfoosball.foosobsplus.model;

import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * AutoScore connection details for a single foosball table.
 *
 * <p>Each table with an AutoScore (Pico) system has one {@code TableConnection}:
 * a display label plus the host/port to connect to and per-table preferences
 * (auto-connect on startup, detailed logging). The set of connections is
 * persisted by {@link Settings} and, at runtime, each will drive its own
 * {@link TableSession}.
 */
public class TableConnection {
	private static final Pattern MAC_PATTERN = Pattern.compile("(?:[0-9A-F]{2}-){5}[0-9A-F]{2}"); //$NON-NLS-1$

	private final String id;
	private String label;
	private String serverAddress;
	private String serverPort;
	private boolean autoConnect;
	private boolean detailLog;
	// OBS source (optionally "scene,source") shown for this table when the auto
	// camera swap toggle is on and this table becomes the displayed one. Empty
	// = no camera swap for this table.
	private String cameraSource;
	// The Pico's own MAC address, learned from a discovery response (Search /
	// Assign Selected / Assign All) and persisted so Flash / Report Table
	// Number can target this device directly without re-discovering it every
	// time. Empty = not yet discovered.
	private String macAddress;

	public TableConnection(String label, String serverAddress, String serverPort, boolean autoConnect, boolean detailLog) {
		this(label, serverAddress, serverPort, autoConnect, detailLog, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public TableConnection(String label, String serverAddress, String serverPort, boolean autoConnect, boolean detailLog, String cameraSource) {
		this(label, serverAddress, serverPort, autoConnect, detailLog, cameraSource, ""); //$NON-NLS-1$
	}

	public TableConnection(String label, String serverAddress, String serverPort, boolean autoConnect, boolean detailLog, String cameraSource, String macAddress) {
		this(UUID.randomUUID().toString(), label, serverAddress, serverPort, autoConnect, detailLog, cameraSource, macAddress);
	}

	public TableConnection(String id, String label, String serverAddress, String serverPort, boolean autoConnect, boolean detailLog, String cameraSource, String macAddress) {
		this.id = id == null || id.isBlank() ? UUID.randomUUID().toString() : id.trim();
		this.label = label;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.autoConnect = autoConnect;
		this.detailLog = detailLog;
		this.cameraSource = cameraSource == null ? "" : cameraSource; //$NON-NLS-1$
		this.macAddress = normalizeMac(macAddress);
	}

	public String getId() { return id; }
	public TableConnection copy() {
		return new TableConnection(id, label, serverAddress, serverPort, autoConnect, detailLog, cameraSource, macAddress);
	}

	public static String normalizeMac(String macAddress) {
		if (macAddress == null || macAddress.isBlank()) return ""; //$NON-NLS-1$
		String compact = macAddress.trim().toUpperCase(Locale.ROOT).replace(":", "").replace("-", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (compact.length() != 12) return macAddress.trim().toUpperCase(Locale.ROOT);
		return compact.replaceAll("(..)(?!$)", "$1-"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static boolean isValidMac(String macAddress) {
		return macAddress == null || macAddress.isBlank() || MAC_PATTERN.matcher(normalizeMac(macAddress)).matches();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public boolean isAutoConnect() {
		return autoConnect;
	}

	public void setAutoConnect(boolean autoConnect) {
		this.autoConnect = autoConnect;
	}

	public boolean isDetailLog() {
		return detailLog;
	}

	public void setDetailLog(boolean detailLog) {
		this.detailLog = detailLog;
	}

	public String getCameraSource() {
		return cameraSource;
	}

	public void setCameraSource(String cameraSource) {
		this.cameraSource = cameraSource == null ? "" : cameraSource; //$NON-NLS-1$
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = normalizeMac(macAddress);
	}

	@Override
	public String toString() {
		return label;
	}
}
