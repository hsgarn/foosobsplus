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

	private String label;
	private String serverAddress;
	private String serverPort;
	private boolean autoConnect;
	private boolean detailLog;

	public TableConnection(String label, String serverAddress, String serverPort, boolean autoConnect, boolean detailLog) {
		this.label = label;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.autoConnect = autoConnect;
		this.detailLog = detailLog;
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

	@Override
	public String toString() {
		return label;
	}
}
