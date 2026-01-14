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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.PicoDiscovery;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.view.Messages;
import com.midsouthfoosball.foosobsplus.view.AutoScoreConfigPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreMainPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsPanel;

/**
 * Manages AutoScore device connection, communication, and configuration.
 * Handles socket communication with AutoScore hardware for automatic score detection.
 */
public class AutoScoreManager {

	private static final Logger logger = LoggerFactory.getLogger(AutoScoreManager.class);
	private static final String ON = "On"; //$NON-NLS-1$
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Messages.getString("Main.DateTimePattern")); //$NON-NLS-1$

	/**
	 * Callback interface for connection state changes.
	 */
	public interface ConnectionStateListener {
		void onConnectionStateChanged(boolean connected);
	}

	/**
	 * Callback interface for score/timeout events from AutoScore device.
	 */
	public interface ScoreEventListener {
		void onScoreEvent(String code);
	}

	// Connection state
	private volatile boolean connected = false;
	private volatile boolean blockReconnect = false;
	private Socket socket;
	private volatile PrintWriter socketWriter;
	private SwingWorker<Boolean, Integer> worker;

	// UI Panels
	private final AutoScoreSettingsPanel settingsPanel;
	private final AutoScoreConfigPanel configPanel;
	private final AutoScoreMainPanel mainPanel;

	// Callbacks
	private ConnectionStateListener connectionListener;
	private ScoreEventListener scoreListener;

	/**
	 * Creates a new AutoScoreManager.
	 *
	 * @param settingsPanel the settings panel for displaying connection status
	 * @param configPanel the config panel for device configuration
	 * @param mainPanel the main panel with ignore sensors checkbox
	 */
	public AutoScoreManager(
			AutoScoreSettingsPanel settingsPanel,
			AutoScoreConfigPanel configPanel,
			AutoScoreMainPanel mainPanel) {
		this.settingsPanel = settingsPanel;
		this.configPanel = configPanel;
		this.mainPanel = mainPanel;
	}

	/**
	 * Sets the listener for connection state changes.
	 */
	public void setConnectionStateListener(ConnectionStateListener listener) {
		this.connectionListener = listener;
	}

	/**
	 * Sets the listener for score events.
	 */
	public void setScoreEventListener(ScoreEventListener listener) {
		this.scoreListener = listener;
	}

	/**
	 * Returns whether currently connected to AutoScore device.
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Sets whether to block automatic reconnection attempts.
	 */
	public void setBlockReconnect(boolean block) {
		this.blockReconnect = block;
	}

	/**
	 * Connects to the AutoScore device.
	 */
	public void connect() {
		settingsPanel.addMessage(Messages.getString("Main.TryingToConnectToAutoScore")); //$NON-NLS-1$
		logger.info("Trying to connect to AutoScore..."); //$NON-NLS-1$
		createWorker();
		worker.execute();
	}

	/**
	 * Disconnects from the AutoScore device.
	 */
	public void disconnect() {
		settingsPanel.addMessage(Messages.getString("Main.Disconnecting")); //$NON-NLS-1$
		logger.info("Trying to disconnect from AutoScore..."); //$NON-NLS-1$
		if (worker != null) {
			worker.cancel(true);
		}
		notifyConnectionStateChanged(false);
		connected = false;
	}

	/**
	 * Searches for AutoScore device on the network.
	 */
	public void search() {
		try {
			String picoIP = PicoDiscovery.listenForPico(5051, 300);
			if (picoIP != null) {
				System.out.println("Discovered Pico at: " + picoIP); //$NON-NLS-1$
				settingsPanel.addMessage("Found: " + picoIP); //$NON-NLS-1$
				// Response format: "Table N:ipaddress:port" (e.g., "Table 1:192.168.68.74:5051")
				String[] parts = picoIP.split(":"); //$NON-NLS-1$
				if (parts.length == 3) {
					String label = parts[0];
					String ipAddress = parts[1];
					String port = parts[2];
					String message = String.format(
						"Discovered Device:\n\n%s\nIP Address: %s\nPort: %s\n\nWould you like to update the IP and Port?", //$NON-NLS-1$
						label, ipAddress, port
					);
					int result = JOptionPane.showConfirmDialog(
						settingsPanel,
						message,
						"Update Auto Score Settings", //$NON-NLS-1$
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if (result == JOptionPane.YES_OPTION) {
						updateHostPort(ipAddress, port);
					}
				} else {
					logger.warn("Invalid picoIP format: " + picoIP); //$NON-NLS-1$
				}
			} else {
				System.out.println("No Pico found."); //$NON-NLS-1$
				settingsPanel.addMessage("No Pico found."); //$NON-NLS-1$
			}
		} catch (Exception e) {
			logger.error("searchAutoScore call to PicoDiscovery Exception: " + e); //$NON-NLS-1$
		}
	}

	/**
	 * Reads configuration from the AutoScore device.
	 */
	public void readConfig() {
		PrintWriter writer = socketWriter;
		if (connected && writer != null) {
			writer.println("read:"); //$NON-NLS-1$
			if (writer.checkError()) {
				logger.error("readAutoScoreConfig println error sending read:"); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Writes configuration to the AutoScore device.
	 */
	public void writeConfig() {
		if (validateConfig()) {
			PrintWriter writer = socketWriter;
			if (connected && writer != null) {
				String config = configPanel.getConfigTextArea();
				String dateStamp = dtf.format(LocalDateTime.now()) + "\r\n"; //$NON-NLS-1$
				dateStamp = dateStamp.replace(":", ""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = dateStamp.replace("/", ""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = dateStamp.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = "date = " + dateStamp; //$NON-NLS-1$
				writer.println("save:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				if (writer.checkError()) {
					logger.error("saveAutoScoreConfig println error sending save:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					logger.info("write autoscore config:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
	}

	/**
	 * Validates the AutoScore configuration.
	 * @return true if valid, false otherwise
	 */
	public boolean validateConfig() {
		boolean validated = true;
		String configErrors = ""; //$NON-NLS-1$
		String[] paramNames = {"PORT", "SENSOR1", "SENSOR2", "SENSOR3", "LED1", "LED2", "DELAY_SENSOR", "DELAY_PB", "PB1", "PB2"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
		String[] paramTests = {"PORT", "PIN", "PIN", "PIN", "PIN", "PIN", "TIME", "TIME", "PIN", "PIN"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
		List<String> copyNames = new ArrayList<>(Arrays.asList(paramNames));
		List<String> copyTests = new ArrayList<>(Arrays.asList(paramTests));
		String config = configPanel.getConfigTextArea();
		String[] lines = config.split("\n"); //$NON-NLS-1$
		for (String line : lines) {
			if (line.contains("=")) { //$NON-NLS-1$
				String[] pair = line.split("="); //$NON-NLS-1$
				String name = pair[0].trim();
				String testValue = pair[1].trim();
				if (copyNames.contains(name)) {
					int pos = -1;
					boolean found = false;
					for (String param : copyNames) {
						pos += 1;
						if (param.equals(name)) {
							found = true;
							break;
						}
					}
					if (found) {
						String test = copyTests.get(pos);
						copyNames.remove(pos);
						copyTests.remove(pos);
						if (test.equals("PORT")) { //$NON-NLS-1$
							try {
								int value = Integer.parseInt(testValue);
								if (!((value > 0) && (value < 65535))) {
									String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "].  Must be between 0 and 65535."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
									configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
									logger.info(msg);
									validated = false;
								}
							} catch (NumberFormatException e) {
								String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "]. Must be between 0 and 65535."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
								logger.error(msg);
								logger.error(e.toString());
								validated = false;
							}
						} else if (test.equals("PIN")) { //$NON-NLS-1$
							try {
								int value = Integer.parseInt(testValue);
								if (!(((value > -1) && (value < 23)) || ((value > 25) && (value < 29)))) {
									String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
									configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
									logger.info(msg);
									validated = false;
								}
							} catch (NumberFormatException e) {
								String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
								logger.error(msg);
								logger.error(e.toString());
								validated = false;
							}
						} else if (test.equals("TIME")) { //$NON-NLS-1$
							try {
								int value = Integer.parseInt(testValue);
								if (!((value > 0) && (value < 60000))) {
									String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "].  Must be between 0 and 60000."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
									configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
									logger.info(msg);
									validated = false;
								}
							} catch (NumberFormatException e) {
								String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "]. Must be between 0 and 60000."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
								logger.error(msg);
								logger.error(e.toString());
								validated = false;
							}
						}
					}
				}
			}
		}
		if (!copyNames.isEmpty()) {
			String msg = "Validation Failed. Missing parameters:\r\n"; //$NON-NLS-1$
			for (String missing : copyNames) {
				msg = msg + missing + "\r\n"; //$NON-NLS-1$
			}
			configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
			logger.info(msg);
			validated = false;
		}
		if (validated) {
			logger.info("Validation passed."); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null, Messages.getString("Main.InvalidConfiguration") + configErrors, Messages.getString("Main.ValidationResults"), 1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return validated;
	}

	/**
	 * Resets the AutoScore device configuration.
	 */
	public void resetConfig() {
		PrintWriter writer = socketWriter;
		if (connected && writer != null) {
			writer.println("reset:"); //$NON-NLS-1$
			if (writer.checkError()) {
				logger.error("resetAutoScoreConfig println error sending reset:"); //$NON-NLS-1$
			}
			disconnect();
		}
	}

	/**
	 * Clears the configuration text area.
	 */
	public void clearConfig() {
		configPanel.clearConfigTextArea();
	}

	// --- ActionListener factory methods ---

	public ActionListener createSettingsApplyListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingsPanel.saveSettings();
			}
		};
	}

	public ActionListener createSettingsSaveListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingsPanel.saveSettings();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		};
	}

	public ActionListener createSettingsConnectListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockReconnect = false;
				connect();
			}
		};
	}

	public ActionListener createSettingsDisconnectListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("AutoScore Settings Window Disconnect Button Pressed."); //$NON-NLS-1$
				blockReconnect = true;
				disconnect();
			}
		};
	}

	public ActionListener createSettingsSearchListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("AutoScore Settings Window Search Button Pressed."); //$NON-NLS-1$
				search();
			}
		};
	}

	public ActionListener createConfigReadListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readConfig();
			}
		};
	}

	public ActionListener createConfigWriteListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		};
	}

	public ActionListener createConfigValidateListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateConfig()) {
					logger.info("Validation passed."); //$NON-NLS-1$
					JOptionPane.showMessageDialog(null, Messages.getString("Main.ValidationPassed"), Messages.getString("Main.ValidationResults"), 1); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		};
	}

	public ActionListener createConfigResetListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("AutoScore Configuration Reset Pico Button Pressed."); //$NON-NLS-1$
				resetConfig();
			}
		};
	}

	public ActionListener createConfigClearListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearConfig();
			}
		};
	}

	public ActionListener createMainPanelConnectListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockReconnect = false;
				logger.info("AutoScore Main Panel Connect Button Pressed."); //$NON-NLS-1$
				connect();
			}
		};
	}

	public ActionListener createMainPanelDisconnectListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockReconnect = true;
				logger.info("AutoScore Main Panel Disconnect Button Pressed."); //$NON-NLS-1$
				disconnect();
			}
		};
	}

	// --- Private helper methods ---

	private void updateHostPort(String host, String port) {
		settingsPanel.setServerAddress(host);
		settingsPanel.setServerPort(port);
		settingsPanel.saveSettings();
	}

	private void notifyConnectionStateChanged(boolean isConnected) {
		if (connectionListener != null) {
			connectionListener.onConnectionStateChanged(isConnected);
		}
	}

	private void notifyScoreEvent(String code) {
		if (scoreListener != null) {
			scoreListener.onScoreEvent(code);
		}
	}

	private void createWorker() {
		worker = new SwingWorker<Boolean, Integer>() {
			BufferedReader dataIn;

			@Override
			protected Boolean doInBackground() throws Exception {
				boolean isConnected = false;
				String address = Settings.getAutoScoreParameter("AutoScoreSettingsServerAddress"); //$NON-NLS-1$
				int port = Settings.getAutoScoreParameter("AutoScoreSettingsServerPort", Integer::parseInt); //$NON-NLS-1$
				try {
					socket = new Socket(address, port);
					socket.setSoLinger(true, 0);
					SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ConnectedTo") + address + ": " + port)); //$NON-NLS-1$ //$NON-NLS-2$
					logger.info("Auto Score connected to " + address + ": " + port); //$NON-NLS-1$ //$NON-NLS-2$
					dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					try {
						socketWriter = new PrintWriter(socket.getOutputStream());
						if (socketWriter.checkError()) {
							logger.error("createAutoScoreWork doInBackground new PrintWriter error:"); //$NON-NLS-1$
						}
					} catch (IOException ex) {
						logger.error("createAutoScoreWork doInBackground PrintWriter exception:"); //$NON-NLS-1$
						logger.error(ex.toString());
					}
					isConnected = true;
					SwingUtilities.invokeLater(() -> notifyConnectionStateChanged(true));
					connected = true;
				} catch (UnknownHostException uh) {
					SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.AutoScoreUnknownHostException"))); //$NON-NLS-1$
					logger.error("Auto Score new Socket UnknownHostException"); //$NON-NLS-1$
					logger.error(uh.toString());
				} catch (IOException io) {
					SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.AutoScoreIOException"))); //$NON-NLS-1$
					logger.error("Auto Score new Socket IOException"); //$NON-NLS-1$
					logger.error(io.toString());
				}
				String raw;
				String str[];
				String cmd[];
				while (!isCancelled() && isConnected) {
					raw = ""; //$NON-NLS-1$
					try {
						if (dataIn.ready()) {
							raw = dataIn.readLine();
							if (raw == null) {
								break;
							}
						}
					} catch (IOException io) {
						final String errorMsg = io.toString();
						SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + errorMsg)); //$NON-NLS-1$
						logger.error(errorMsg);
						isConnected = false;
					}
					if (!raw.isEmpty()) {
						logger.info("Received raw data: [" + raw + "]"); //$NON-NLS-1$ //$NON-NLS-2$
						cmd = raw.split(":"); //$NON-NLS-1$
						logger.info("Parse command: " + cmd[0]); //$NON-NLS-1$
						if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals(ON)) { //$NON-NLS-1$
							final String receivedMsg = raw;
							SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Received") + receivedMsg)); //$NON-NLS-1$
						}
						if (cmd[0].equals("Team")) { //$NON-NLS-1$
							str = cmd[1].split("[,]", 0); //$NON-NLS-1$
							if (str[0].equals("1")) { //$NON-NLS-1$
								publish(1);
							} else if (str[0].equals("2")) { //$NON-NLS-1$
								publish(2);
							}
						} else if (cmd[0].equals("TO")) { //$NON-NLS-1$
							str = cmd[1].split("[,]", 0); //$NON-NLS-1$
							if (str[0].equals("1")) { //$NON-NLS-1$
								publish(3);
							} else if (str[0].equals("2")) { //$NON-NLS-1$
								publish(4);
							}
						}
						if (cmd[0].equals("Read")) { //$NON-NLS-1$
							SwingUtilities.invokeLater(() -> configPanel.clearConfigTextArea());
						}
						if (cmd[0].equals("Line")) { //$NON-NLS-1$
							final String line = cmd[1] + "\n"; //$NON-NLS-1$
							SwingUtilities.invokeLater(() -> configPanel.appendConfigTextArea(line));
						}
					}
				}
				SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ConnectionTerminated"))); //$NON-NLS-1$
				logger.info("Auto Score Connection Terminated!!"); //$NON-NLS-1$
				try {
					if (dataIn != null) {
						dataIn.close();
					}
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
					isConnected = false;
				} catch (IOException io) {
					final String errorMsg = io.toString();
					SwingUtilities.invokeLater(() -> settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + errorMsg)); //$NON-NLS-1$
					logger.error(errorMsg);
				}
				return isConnected;
			}

			@Override
			protected void done() {
				boolean status;
				if (!isCancelled()) {
					try {
						status = get();
						settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.WorkerCompletedWithIsConnected") + status); //$NON-NLS-1$
					} catch (InterruptedException | ExecutionException e) {
						settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString()); //$NON-NLS-1$
						logger.error(e.toString());
					}
				}
				notifyConnectionStateChanged(false);
				connected = false;
			}

			@Override
			protected void process(List<Integer> chunks) {
				if (isCancelled()) return;
				int mostRecentValue = chunks.get(chunks.size() - 1);
				boolean ignoreSensors = mainPanel.isIgnored();
				if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals(ON)) { //$NON-NLS-1$
					if (ignoreSensors) {
						if (mostRecentValue == 1 || mostRecentValue == 2) {
							settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team") + mostRecentValue + Messages.getString("Main.ScoredButIgnored")); //$NON-NLS-1$ //$NON-NLS-2$
						} else {
							settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team") + (mostRecentValue - 2) + Messages.getString("Main.CalledTimeOut")); //$NON-NLS-1$ //$NON-NLS-2$
						}
					} else {
						if (mostRecentValue == 1 || mostRecentValue == 2) {
							settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team") + mostRecentValue + Messages.getString("Main.Scored")); //$NON-NLS-1$ //$NON-NLS-2$
						} else {
							settingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team") + (mostRecentValue - 2) + Messages.getString("Main.CalledTimeOut")); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
				if (!ignoreSensors && (mostRecentValue == 1)) {
					notifyScoreEvent("XIST1"); //$NON-NLS-1$
				} else if (!ignoreSensors && (mostRecentValue == 2)) {
					notifyScoreEvent("XIST2"); //$NON-NLS-1$
				}
				if (mostRecentValue == 3) {
					notifyScoreEvent("XUTT1"); //$NON-NLS-1$
				} else if (mostRecentValue == 4) {
					notifyScoreEvent("XUTT2"); //$NON-NLS-1$
				}
			}
		};
		worker.addPropertyChangeListener(new WorkerStateChangeListener());
	}

	private class WorkerStateChangeListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Object source = evt.getSource();
			SwingWorker.StateValue state;
			if (source == worker) {
				state = worker.getState();
			} else {
				state = null;
			}
			if (state == null) {
				logger.info("AutoScoreWorker state is null so probably no AutoScore instance to connect to."); //$NON-NLS-1$
			} else {
				logger.info("AutoScoreWorker state changed to: " + state.toString()); //$NON-NLS-1$
				if (state == SwingWorker.StateValue.DONE) {
					if (!blockReconnect) {
						logger.info("Attempt reconnect to AutoScore..."); //$NON-NLS-1$
						connect();
					}
				}
			}
		}
	}
}
