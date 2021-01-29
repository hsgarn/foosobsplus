package com.midsouthfoosball.foosobsplus.view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class OBSConnectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtHost;
	private JTextField txtPort;
	private JButton btnConnect;
	private JTextField txtPassword;
	private JCheckBox chckbxSavePassword;
	private JCheckBox chckbxAutoLogin;
	private JButton btnDisconnect;
	private JTextArea txtAreaMessage;
	
	public OBSConnectPanel(Settings settings) throws IOException {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][grow]"));
		
		JLabel lblPanelTitle = new JLabel("OBS Connection Details:");
		add(lblPanelTitle, "cell 1 0");
		
		JLabel lblHost = new JLabel("Host");
		add(lblHost, "cell 0 1,alignx right");
		
		JLabel lblPort = new JLabel("Port");
		add(lblPort, "cell 0 2,alignx right");
		
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "flowx,cell 0 3,alignx trailing");
		
		txtPassword = new JTextField();
		txtPassword.setText(settings.getOBSPassword());
		add(txtPassword, "cell 1 3,growx");
		txtPassword.setColumns(10);
		
		chckbxSavePassword = new JCheckBox("Save Password");
		if (Integer.toString(settings.getOBSSavePassword()).equals("1")) { //$NON-NLS-1$
			chckbxSavePassword.setSelected(true);
		} else {
			chckbxSavePassword.setSelected(false);
		}
		add(chckbxSavePassword, "cell 1 5");
		
		chckbxAutoLogin = new JCheckBox("Auto Login on Start");
		if (Integer.toString(settings.getOBSAutoLogin()).equals("1")) { //$NON-NLS-1$
			chckbxAutoLogin.setSelected(true);
		} else {
			chckbxAutoLogin.setSelected(false);
		}
		add(chckbxAutoLogin, "cell 1 6");
		
		btnConnect = new JButton("Connect");
		add(btnConnect, "flowx,cell 1 7,growx");
		
		JLabel lblMessage = new JLabel("Message:");
		add(lblMessage, "cell 1 8");
		
		txtAreaMessage = new JTextArea();
		txtAreaMessage.setEditable(false);
		add(txtAreaMessage, "cell 1 9,grow");
		
		txtHost = new JTextField();
		txtHost.setText(settings.getOBSHost());
		add(txtHost, "cell 1 1,growx");
		txtHost.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText(settings.getOBSPort());
		add(txtPort, "cell 1 2,growx");
		txtPort.setColumns(10);
		
		btnDisconnect = new JButton("Disconnect");
		add(btnDisconnect, "cell 1 7,growx");
		
	}
	public void disableConnect() {
		btnConnect.setEnabled(false);
		btnDisconnect.setEnabled(true);
	}
	public void enableConnect() {
		btnConnect.setEnabled(true);
		btnDisconnect.setEnabled(false);
	}
	public void setMessage(String message) {
		txtAreaMessage.setText(message);
//		return;
	}
	public void saveSettings(Settings settings) {
		settings.setOBSHost(txtHost.getText());
		settings.setOBSPort(txtPort.getText());
		settings.setOBSPassword(txtPassword.getText());
		if (chckbxSavePassword.isSelected()) {
			settings.setOBSSavePassword(1);
		} else {
			settings.setOBSSavePassword(0);
		}
		if (chckbxAutoLogin.isSelected()) {
			settings.setOBSAutoLogin(1);
		} else {
			settings.setOBSAutoLogin(0);
		}
		try {
			settings.saveToConfig();
		} catch (IOException ex) {
			System.out.println(Messages.getString("Errors.ErrorSavingPropertiesFile", settings.getGameType()) + ex.getMessage());		 //$NON-NLS-1$
		}
	}
	////// Listeners \\\\\\
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
}
