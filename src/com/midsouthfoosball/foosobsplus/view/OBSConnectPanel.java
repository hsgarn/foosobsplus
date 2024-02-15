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
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	private JButton btnFetchMonitors;
	private JButton btnProject;
	private JButton btnSave;
	private JList<String> lstMessageHistory;
	private DefaultListModel<String> mdlMessageHistory;
	private JScrollPane scrMessageHistory;
	private JComboBox<String> monitorComboBox;
	private static final String ONE = "1";
	private static final String ZERO = "0";
	private static Logger logger = LoggerFactory.getLogger(OBSConnectPanel.class);
	private static final int COLUMN0 = 0;
	private static final int COLUMN1 = 1;
	private static final int COLUMN2 = 2;
	
	public OBSConnectPanel() throws IOException {
		int row = 0;
		monitorComboBox = new JComboBox<>();
		mdlMessageHistory = new DefaultListModel<String>();
		lstMessageHistory = new JList<String>(mdlMessageHistory);
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][grow]"));
		JLabel lblPanelTitle = new JLabel(Messages.getString("OBSConnectPanel.Title"));
		add(lblPanelTitle, "cell " + COLUMN1 + " " + row);
		row += 1;
		JLabel lblHost = new JLabel(Messages.getString("OBSConnectPanel.Host"));
		add(lblHost, "cell " + COLUMN0 + " " + row + ",alignx right");
		row += 1;
		JLabel lblPort = new JLabel(Messages.getString("OBSConnectPanel.Port"));
		add(lblPort, "cell " + COLUMN0 + " " + row + ",alignx right");
		row += 1;
		JLabel lblPassword = new JLabel(Messages.getString("OBSConnectPanel.Password"));
		add(lblPassword, "cell " + COLUMN0 + " " + row + ",alignx right");
		row += 1;
		JLabel lblScene = new JLabel(Messages.getString("OBSConnectPanel.Scene"));
		add(lblScene, "cell " + COLUMN0 + " " + row + ", alignx right");
		row += 1;
		JLabel lblMonitor = new JLabel(Messages.getString("OBSConnectPanel.Monitor"));
		add(lblMonitor, "cell " + COLUMN0 + " " + row + ", alignx right");
		row += 4;
		JLabel lblMessage = new JLabel(Messages.getString("OBSConnectPanel.Message"));
		add(lblMessage, "cell " + COLUMN0 + " " + row + ", alignx left");
		row = 1;
		txtHost = new JTextField();
		txtHost.setText(Settings.getOBSParameter("OBSHost"));
		txtHost.setColumns(10);
		add(txtHost, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		txtPort = new JTextField();
		txtPort.setText(Settings.getOBSParameter("OBSPort"));
		txtPort.setColumns(10);
		add(txtPort, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		txtPassword = new JTextField();
		txtPassword.setText(Settings.getOBSParameter("OBSPassword"));
		txtPassword.setColumns(10);
		add(txtPassword, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		txtScene = new JTextField();
		txtScene.setText(Settings.getOBSParameter("OBSScene"));
		txtScene.setColumns(10);
		add(txtScene, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		add(monitorComboBox, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		chckbxSavePassword = new JCheckBox(Messages.getString("OBSConnectPanel.SavePassword"));
		if (Settings.getOBSParameter("OBSSavePassword").equals(ONE)) { //$NON-NLS-1$
			chckbxSavePassword.setSelected(true);
		} else {
			chckbxSavePassword.setSelected(false);
		}
		add(chckbxSavePassword, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		chckbxAutoLogin = new JCheckBox(Messages.getString("OBSConnectPanel.AutoLoginOnStart"));
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ONE)) { //$NON-NLS-1$
			chckbxAutoLogin.setSelected(true);
		} else {
			chckbxAutoLogin.setSelected(false);
		}
		add(chckbxAutoLogin, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		btnConnect = new JButton(Messages.getString("OBSConnectPanel.Connect"));
		add(btnConnect, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		lstMessageHistory.setCellRenderer(new AttributiveCellRenderer());
		add(scrMessageHistory, "cell " + COLUMN1 + " " + row + ",span 3");
		row = 4;
		btnSetScene = new JButton(Messages.getString("OBSConnectPanel.SetScene"));
		add(btnSetScene, "cell " + COLUMN1 + " " + row + ",alignx left");
		row += 1;
		btnFetchMonitors = new JButton(Messages.getString("OBSConnectPanel.FetchMonitors"));
		add(btnFetchMonitors, "cell " + COLUMN1 + " " + row + ",alignx left");
		btnProject = new JButton(Messages.getString("OBSConnectPanel.Project"));
		add(btnProject, "cell " + COLUMN1 + " " + row + ",alignx left");
		row = 8;
		btnDisconnect = new JButton(Messages.getString("OBSConnectPanel.Disconnect"));
		add(btnDisconnect, "cell " + COLUMN1 + " " + row + ",alignx left");
		chckbxCloseOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.CloseOnConnect"));
		if (Settings.getOBSParameter("OBSCloseOnConnect").equals(ONE)) { //$NON-NLS-1$
			chckbxCloseOnConnect.setSelected(true);
		} else {
			chckbxCloseOnConnect.setSelected(false);
		}
		row = 6;
		add(chckbxCloseOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left");
		chckbxUpdateOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.UpdateOnConnect"));
		if (Settings.getOBSParameter("OBSUpdateOnConnect").equals(ONE)) {
			chckbxUpdateOnConnect.setSelected(true);
		} else {
			chckbxUpdateOnConnect.setSelected(false);
		}
		row += 1;
		add(chckbxUpdateOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left");
		btnSave = new JButton(Messages.getString("Global.Apply"));
		add(btnSave, "cell " + COLUMN2 + " 8,alignx left");
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
    public void updateMonitorList(HashMap<Integer, String> monitorMap) {
    	monitorComboBox.removeAllItems();
    	for (HashMap.Entry<Integer, String> entry : monitorMap.entrySet()) {
    		int index = entry.getKey();
    		String monitor = entry.getValue();
    		monitorComboBox.addItem(index + ": " + monitor);
    	}
    	monitorComboBox.repaint();
    }
    public Number getSelectedMonitor() {
		Number monitorIndex = monitorComboBox.getSelectedIndex();
		return monitorIndex;
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
	public void addFetchMonitorsListener(ActionListener listenForBtnFetchMonitors) {
		btnFetchMonitors.addActionListener(listenForBtnFetchMonitors);
	}
	public void addProjectListener(ActionListener listenForBtnProject) {
		btnProject.addActionListener(listenForBtnProject);
	}
}
