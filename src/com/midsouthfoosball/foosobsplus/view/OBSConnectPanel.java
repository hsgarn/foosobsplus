/**
Copyright © 2020-2024 Hugh Garner
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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class OBSConnectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtHost;
	private JTextField txtPort;
	private JButton btnConnect;
	private JTextField txtPassword;
	private JTextField txtScene;
	private JButton btnSetScene;
	private JCheckBox chckbxSavePassword;
	private JCheckBox chckbxAutoLogin;
	private JCheckBox chckbxCloseOnConnect;
	private JCheckBox chckbxUpdateOnConnect;
	private JButton btnDisconnect;
	private JButton btnSave;
	private JList<String> lstMessageHistory;
	private DefaultListModel<String> mdlMessageHistory;
	private JScrollPane scrMessageHistory;
	private static final String ONE = "1";
	private static final String ZERO = "0";
	private static Logger logger = LoggerFactory.getLogger(OBSConnectPanel.class);
	public OBSConnectPanel() throws IOException {
		mdlMessageHistory = new DefaultListModel<String>();
		lstMessageHistory = new JList<String>(mdlMessageHistory);
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][grow]"));
		JLabel lblPanelTitle = new JLabel("OBS Connection Details:");
		add(lblPanelTitle, "cell 1 0");
		JLabel lblHost = new JLabel("Host");
		add(lblHost, "cell 0 1,alignx right");
		JLabel lblPort = new JLabel("Port");
		add(lblPort, "cell 0 2,alignx right");
		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "cell 0 3,alignx right");
		JLabel lblScene = new JLabel("Scene");
		add(lblScene, "cell 0 4, alignx right");
		txtHost = new JTextField();
		txtHost.setText(Settings.getOBSParameter("OBSHost"));
		txtHost.setColumns(10);
		add(txtHost, "cell 1 1,alignx left");
		txtPort = new JTextField();
		txtPort.setText(Settings.getOBSParameter("OBSPort"));
		txtPort.setColumns(10);
		add(txtPort, "cell 1 2,alignx left");
		txtPassword = new JTextField();
		txtPassword.setText(Settings.getOBSParameter("OBSPassword"));
		txtPassword.setColumns(10);
		add(txtPassword, "cell 1 3,alignx left");
		txtScene = new JTextField();
		txtScene.setText(Settings.getOBSParameter("OBSScene"));
		txtScene.setColumns(10);
		add(txtScene, "cell 1 4,alignx left");
		chckbxSavePassword = new JCheckBox("Save Password");
		if (Settings.getOBSParameter("OBSSavePassword").equals(ONE)) { //$NON-NLS-1$
			chckbxSavePassword.setSelected(true);
		} else {
			chckbxSavePassword.setSelected(false);
		}
		add(chckbxSavePassword, "cell 1 5,alignx left");
		chckbxAutoLogin = new JCheckBox("Auto Login on Start");
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ONE)) { //$NON-NLS-1$
			chckbxAutoLogin.setSelected(true);
		} else {
			chckbxAutoLogin.setSelected(false);
		}
		add(chckbxAutoLogin, "cell 1 6,alignx left");
		btnConnect = new JButton("Connect");
		add(btnConnect, "cell 1 7,alignx left");
		JLabel lblMessage = new JLabel("Message:");
		add(lblMessage, "cell 1 8, alignx left");
		scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		lstMessageHistory.setCellRenderer(new AttributiveCellRenderer());
		add(scrMessageHistory, "cell 1 9,span 3");
		btnSetScene = new JButton("Set Scene");
		add(btnSetScene, "cell 1 4,alignx right");
		btnDisconnect = new JButton("Disconnect");
		add(btnDisconnect, "cell 1 7,alignx right");
		chckbxCloseOnConnect = new JCheckBox("Close on Connect");
		if (Settings.getOBSParameter("OBSCloseOnConnect").equals(ONE)) { //$NON-NLS-1$
			chckbxCloseOnConnect.setSelected(true);
		} else {
			chckbxCloseOnConnect.setSelected(false);
		}
		add(chckbxCloseOnConnect, "cell 3 5,alignx left");
		chckbxUpdateOnConnect = new JCheckBox("Update on Connect");
		if (Settings.getOBSParameter("OBSUpdateOnConnect").equals(ONE)) {
			chckbxUpdateOnConnect.setSelected(true);
		} else {
			chckbxUpdateOnConnect.setSelected(false);
		}
		add(chckbxUpdateOnConnect, "cell 3 6,alignx left");
		btnSave = new JButton("Save");
		add(btnSave, "cell 3 7,alignx left");
	}
	public void disableConnect() {
		btnConnect.setEnabled(false);
		btnDisconnect.setEnabled(true);
	}
	public void enableConnect() {
		btnConnect.setEnabled(true);
		btnDisconnect.setEnabled(false);
	}
	public void addMessage(String message) {
		mdlMessageHistory.insertElementAt(message, 0);
//		scrMessageHistory.append(message + "\n");
	}
	public boolean isConnectionChanged() {
		boolean changed = false;
		if (!txtHost.getText().equals(Settings.getOBSParameter("OBSHost")) ||
			!txtPort.getText().equals(Settings.getOBSParameter("OBSPort"))  ||
			!txtPassword.getText().equals(Settings.getOBSParameter("OBSPassword"))) {
			changed = true;
		}
		return changed;
	}
	public void updateOBS() {
		OBS.setHost(txtHost.getText());
		OBS.setPort(txtPort.getText());
		OBS.setPassword(txtPassword.getText());
	}
	public void saveSettings() {
		updateOBS();
		Settings.setOBS("OBSHost",txtHost.getText());
		Settings.setOBS("OBSPort",txtPort.getText());
		Settings.setOBS("OBSScene",txtScene.getText());
		Settings.setOBS("OBSAutoLogin", chckbxAutoLogin.isSelected() ? ONE : ZERO);
		Settings.setOBS("OBSUpdateOnConnect", chckbxUpdateOnConnect.isSelected() ? ONE : ZERO);
		Settings.setOBS("OBSCloseOnConnect", chckbxCloseOnConnect.isSelected() ? ONE : ZERO);
		Settings.setOBS("OBSSavePassword",chckbxSavePassword.isSelected() ? ONE : ZERO);
		Settings.setOBS("OBSPassword",chckbxSavePassword.isSelected() ? txtPassword.getText() : "");
		OBS.setScene(txtScene.getText());
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ONE)) {
			if (Settings.getOBSParameter("OBSHost").isEmpty() || Settings.getOBSParameter("OBSPassword").isEmpty() || Settings.getOBSParameter("OBSPort").isEmpty() || Settings.getOBSParameter("OBSSavePassword").equals(ZERO)) {
				Settings.setOBS("OBSAutoLogin",ZERO);
				chckbxAutoLogin.setSelected(false);
				String msg = Messages.getString("Errors.OBSConnectPanel.AutoLogin");
				String ttl = Messages.getString("Errors.OBSConnectPanel.AutoLogin.Title");
				logger.error(msg);
				JOptionPane.showMessageDialog(null, msg, ttl,1);
			}
		}
		try {
			Settings.saveOBSConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile"));
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, ex.getMessage(), Messages.getString("Errors.ErrorSavingPropertiesFile"),1);
		}
	}
    public class AttributiveCellRenderer extends DefaultListCellRenderer {
	  private static final long serialVersionUID = 1L;
	  public AttributiveCellRenderer() {
	    setOpaque(true);
	  }

	  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	  {
		  String tmp = ""; //$NON-NLS-1$
		  tmp = (String) value;
		  setBackground(UIManager.getColor("List.background")); //$NON-NLS-1$
		  setForeground(UIManager.getColor("List.foreground")); //$NON-NLS-1$
		  if (tmp.indexOf("Disconnect") != -1 || tmp.indexOf("Unable") != -1 || tmp.indexOf("ERROR!") != -1) { //$NON-NLS-1$
			  setForeground(Color.RED);
		  } 
		  if (tmp.indexOf("Connected!") != -1) {
			  setForeground(Color.BLUE);
		  }
          setText(tmp);
          return this;
	  }
    }
	////// Listeners \\\\\\
	public void addSceneFocusListener(FocusListener focusListenerForTxtScene) {
		txtScene.addFocusListener(focusListenerForTxtScene);
	}
	public void addSetSceneListener(ActionListener listenForBtnSetScene) {
    	btnSetScene.addActionListener(listenForBtnSetScene);
    }
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
	public void addSceneListener(ActionListener listenForTxtScene) {
		txtScene.addActionListener(listenForTxtScene);
	}
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
	public void addHostListener(ActionListener listenForTxtHost) {
		txtHost.addActionListener(listenForTxtHost);
	}
	public void addPortListener(ActionListener listenForTxtPort) {
		txtPort.addActionListener(listenForTxtPort);
	}
	public void addPasswordListener(ActionListener listenForTxtPassword) {
		txtPassword.addActionListener(listenForTxtPassword);
	}
}
