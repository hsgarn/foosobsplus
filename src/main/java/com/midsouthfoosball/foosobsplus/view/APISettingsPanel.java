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
package com.midsouthfoosball.foosobsplus.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.util.NetworkUtil;

import net.miginfocom.swing.MigLayout;

public class APISettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(APISettingsPanel.class);
	private static final String ON = "1"; //$NON-NLS-1$
	private static final String OFF = "0"; //$NON-NLS-1$

	private final JLabel lblMachineIP;
	private final JCheckBox chckbxAPIEnabled;
	private final JTextField txtAPIPort;
	private final JTextField txtAPIKey;
	private final JButton btnApply;
	private final JButton btnApplyClose;
	private final JButton btnCancel;
	private final JButton btnRestoreDefaults;

	// Track original settings to detect changes
	private boolean originalAPIEnabled;
	private String originalAPIPort;
	private String originalAPIKey;

	public APISettingsPanel() throws IOException {
		setLayout(new MigLayout("", "[150.00][grow,left]", "[][][][] []")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		// Title
		JLabel lblTitle = new JLabel("REST API Settings"); //$NON-NLS-1$
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTitle, "cell 0 0 2 1"); //$NON-NLS-1$

		// Machine IP Address (read-only)
		JLabel lblMachineIPLabel = new JLabel("Machine IP Address:"); //$NON-NLS-1$
		add(lblMachineIPLabel, "cell 0 1,alignx right"); //$NON-NLS-1$
		lblMachineIP = new JLabel(NetworkUtil.getLocalIPAddress());
		lblMachineIP.setFont(new Font("Courier New", Font.PLAIN, 11)); //$NON-NLS-1$
		add(lblMachineIP, "cell 1 1,growx"); //$NON-NLS-1$

		// API Enabled checkbox
		JLabel lblAPIEnabled = new JLabel("API Enabled:"); //$NON-NLS-1$
		add(lblAPIEnabled, "cell 0 2,alignx right"); //$NON-NLS-1$
		chckbxAPIEnabled = new JCheckBox();
		String apiEnabled = Settings.getAPIParameter("APIEnabled"); //$NON-NLS-1$
		originalAPIEnabled = apiEnabled != null && apiEnabled.equals("1"); //$NON-NLS-1$
		chckbxAPIEnabled.setSelected(originalAPIEnabled);
		add(chckbxAPIEnabled, "cell 1 2"); //$NON-NLS-1$

		// API Port
		JLabel lblAPIPort = new JLabel("API Port:"); //$NON-NLS-1$
		add(lblAPIPort, "cell 0 3,alignx right"); //$NON-NLS-1$
		txtAPIPort = new JTextField();
		originalAPIPort = Settings.getAPIParameter("APIPort"); //$NON-NLS-1$
		txtAPIPort.setText(originalAPIPort != null ? originalAPIPort : "9051"); //$NON-NLS-1$
		txtAPIPort.setColumns(10);
		add(txtAPIPort, "cell 1 3,growx"); //$NON-NLS-1$

		// API Key
		JLabel lblAPIKey = new JLabel("API Key:"); //$NON-NLS-1$
		add(lblAPIKey, "cell 0 4,alignx right"); //$NON-NLS-1$
		txtAPIKey = new JTextField();
		originalAPIKey = Settings.getAPIParameter("APIKey"); //$NON-NLS-1$
		txtAPIKey.setText(originalAPIKey != null ? originalAPIKey : ""); //$NON-NLS-1$
		txtAPIKey.setColumns(10);
		add(txtAPIKey, "cell 1 4,growx"); //$NON-NLS-1$

		// Buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new MigLayout("", "[][][][]", "[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		btnApply = new JButton("Apply"); //$NON-NLS-1$
		buttonPanel.add(btnApply, "cell 0 0"); //$NON-NLS-1$

		btnApplyClose = new JButton("Apply & Close"); //$NON-NLS-1$
		buttonPanel.add(btnApplyClose, "cell 1 0"); //$NON-NLS-1$

		btnCancel = new JButton("Cancel"); //$NON-NLS-1$
		btnCancel.addActionListener(e -> reloadSettings());
		buttonPanel.add(btnCancel, "cell 2 0"); //$NON-NLS-1$

		btnRestoreDefaults = new JButton("Restore Defaults"); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(e -> restoreDefaults());
		buttonPanel.add(btnRestoreDefaults, "cell 3 0"); //$NON-NLS-1$

		add(buttonPanel, "cell 0 5 2 1"); //$NON-NLS-1$
	}

	/**
	 * Apply API settings (in-memory only, not saved to file)
	 */
	public void applySettings() {
		try {
			// Validate port number
			if (!isValidInteger(txtAPIPort.getText())) {
				JOptionPane.showMessageDialog(null,
					"API Port must be a valid integer (typically 1024-65535).",
					"Invalid Port Number", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int portNumber = Integer.parseInt(txtAPIPort.getText());
			if (portNumber < 1024 || portNumber > 65535) {
				JOptionPane.showMessageDialog(null,
					"API Port must be between 1024 and 65535.",
					"Invalid Port Number", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validate API key is not empty
			if (txtAPIKey.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null,
					"API Key cannot be empty.",
					"Invalid API Key", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Check if settings have changed BEFORE applying
			boolean settingsChanged = hasSettingsChanged();

			// Apply settings
			Settings.setAPIParameter("APIEnabled", chckbxAPIEnabled.isSelected() ? ON : OFF); //$NON-NLS-1$
			Settings.setAPIParameter("APIPort", txtAPIPort.getText()); //$NON-NLS-1$
			Settings.setAPIParameter("APIKey", txtAPIKey.getText()); //$NON-NLS-1$

			// Update original values to current values for next comparison
			updateOriginalValues();

			if (settingsChanged) {
				logger.info("API Settings changed, restarting REST API server");
				com.midsouthfoosball.foosobsplus.main.Main.restartAPIServer();
			}

			logger.info("API Settings applied successfully");
		} catch (Exception e) {
			logger.error("Error applying API settings", e);
			JOptionPane.showMessageDialog(null,
				"Error applying settings: " + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Save API settings to properties file
	 */
	public void saveSettings() {
		try {
			// Validate settings first
			if (!isValidInteger(txtAPIPort.getText())) {
				JOptionPane.showMessageDialog(null,
					"API Port must be a valid integer (typically 1024-65535).",
					"Invalid Port Number", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int portNumber = Integer.parseInt(txtAPIPort.getText());
			if (portNumber < 1024 || portNumber > 65535) {
				JOptionPane.showMessageDialog(null,
					"API Port must be between 1024 and 65535.",
					"Invalid Port Number", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (txtAPIKey.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null,
					"API Key cannot be empty.",
					"Invalid API Key", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Check if settings have changed BEFORE saving
			boolean settingsChanged = hasSettingsChanged();

			// Save settings
			Settings.setAPIParameter("APIEnabled", chckbxAPIEnabled.isSelected() ? ON : OFF); //$NON-NLS-1$
			Settings.setAPIParameter("APIPort", txtAPIPort.getText()); //$NON-NLS-1$
			Settings.setAPIParameter("APIKey", txtAPIKey.getText()); //$NON-NLS-1$

			Settings.saveAPIConfig();

			// Update original values to current values for next comparison
			updateOriginalValues();

			if (settingsChanged) {
				logger.info("API Settings changed, restarting REST API server");
				com.midsouthfoosball.foosobsplus.main.Main.restartAPIServer();
			}

			logger.info("API Settings saved to api.properties");
		} catch (IOException e) {
			logger.error("Error saving API settings", e);
			JOptionPane.showMessageDialog(null,
				"Error saving settings: " + e.getMessage(),
				"Error Saving Settings", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Reload settings from properties file (cancel operation)
	 */
	public void reloadSettings() {
		try {
			String apiEnabled = Settings.getAPIParameter("APIEnabled"); //$NON-NLS-1$
			chckbxAPIEnabled.setSelected(apiEnabled != null && apiEnabled.equals("1")); //$NON-NLS-1$

			String port = Settings.getAPIParameter("APIPort"); //$NON-NLS-1$
			txtAPIPort.setText(port != null ? port : "9051"); //$NON-NLS-1$

			String apiKey = Settings.getAPIParameter("APIKey"); //$NON-NLS-1$
			txtAPIKey.setText(apiKey != null ? apiKey : ""); //$NON-NLS-1$

			updateOriginalValues();
			logger.info("API Settings reloaded from configuration");
		} catch (Exception e) {
			logger.error("Error reloading API settings", e);
			JOptionPane.showMessageDialog(null,
				"Error reloading settings: " + e.getMessage(),
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Check if a string is a valid integer
	 */
	private boolean isValidInteger(String checkString) {
		try {
			Integer.valueOf(checkString);
			return true;
		} catch (NumberFormatException e) {
			logger.error("Invalid integer: {}", checkString);
			return false;
		}
	}

	/**
	 * Check if any settings have changed from their original values
	 */
	private boolean hasSettingsChanged() {
		boolean apiEnabledChanged = chckbxAPIEnabled.isSelected() != originalAPIEnabled;
		String currentPort = txtAPIPort.getText();
		String defaultPort = originalAPIPort != null ? originalAPIPort : "9051"; //$NON-NLS-1$
		boolean portChanged = !currentPort.equals(defaultPort);
		String currentKey = txtAPIKey.getText();
		String defaultKey = originalAPIKey != null ? originalAPIKey : ""; //$NON-NLS-1$
		boolean keyChanged = !currentKey.equals(defaultKey);
		return apiEnabledChanged || portChanged || keyChanged;
	}

	/**
	 * Update the original values to match current values for next comparison
	 */
	private void updateOriginalValues() {
		originalAPIEnabled = chckbxAPIEnabled.isSelected();
		originalAPIPort = txtAPIPort.getText();
		originalAPIKey = txtAPIKey.getText();
	}

	/**
	 * Restore API settings to default values
	 */
	private void restoreDefaults() {
		chckbxAPIEnabled.setSelected(true);
		txtAPIPort.setText("9051"); //$NON-NLS-1$
		txtAPIKey.setText(""); //$NON-NLS-1$
		logger.info("API Settings restored to defaults");
	}

	// Listener methods
	public void addApplyListener(ActionListener listener) {
		btnApply.addActionListener(listener);
	}

	public void addApplyCloseListener(ActionListener listener) {
		btnApplyClose.addActionListener(listener);
	}

	public void addCancelListener(ActionListener listener) {
		btnCancel.addActionListener(listener);
	}
}
