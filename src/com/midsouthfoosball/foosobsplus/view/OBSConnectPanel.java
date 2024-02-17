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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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
	private JTextField txtMainScene;
	private JButton btnSetMainScene;
	private JCheckBox chckbxSavePassword;
	private JCheckBox chckbxAutoLogin;
	private JCheckBox chckbxCloseOnConnect;
	private JCheckBox chckbxUpdateOnConnect;
	private JButton btnDisconnect;
	private JButton btnFetchMonitors;
	private JButton btnFetchScenes;
	private JButton btnProject;
	private JButton btnActivateScene;
	private JButton btnApply;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnRestoreDefaults;
	private JList<String> lstMessageHistory;
	private DefaultListModel<String> mdlMessageHistory;
	private JScrollPane scrMessageHistory;
	private JComboBox<String> monitorComboBox;
	private JComboBox<String> sceneComboBox;
	private static final String ON = "1";
	private static final String OFF = "0";
	private static Logger logger = LoggerFactory.getLogger(OBSConnectPanel.class);
	private static final int COLUMN0 = 0;
	private static final int COLUMN1 = 1;
	private static final int COLUMN2 = 2;
	private static final int COLUMN3 = 3;
	private static final int COLUMN4 = 4;
	
	public OBSConnectPanel() throws IOException {
		int row = 0;
		monitorComboBox = new JComboBox<>();
		sceneComboBox = new JComboBox<>();
		mdlMessageHistory = new DefaultListModel<String>();
		lstMessageHistory = new JList<String>(mdlMessageHistory);
		setLayout(new MigLayout("", "[grow][grow][][]", "[][][][][][][][][][][][grow]"));
		JLabel lblPanelTitle = new JLabel(Messages.getString("OBSConnectPanel.Title"));
		add(lblPanelTitle, "cell " + COLUMN1 + " " + row);
		row += 1;
		JLabel lblHost = new JLabel(Messages.getString("OBSConnectPanel.Host"));
		add(lblHost, "cell " + COLUMN0 + " " + row + ",alignx trailing");
		txtHost = new JTextField();
		txtHost.setText(Settings.getOBSParameter("OBSHost"));
		txtHost.setColumns(10);
		add(txtHost, "cell " + COLUMN1 + " " + row + " 2,growx");
		row += 1;
		JLabel lblPort = new JLabel(Messages.getString("OBSConnectPanel.Port"));
		add(lblPort, "cell " + COLUMN0 + " " + row + ",alignx trailing");
		txtPort = new JTextField();
		txtPort.setText(Settings.getOBSParameter("OBSPort"));
		txtPort.setColumns(10);
		add(txtPort, "cell " + COLUMN1 + " " + row + " 2,growx");
		row += 1;
		JLabel lblPassword = new JLabel(Messages.getString("OBSConnectPanel.Password"));
		add(lblPassword, "cell " + COLUMN0 + " " + row + ",alignx trailing");
		txtPassword = new JTextField();
		txtPassword.setText(Settings.getOBSParameter("OBSPassword"));
		txtPassword.setColumns(10);
		add(txtPassword, "cell " + COLUMN1 + " " + row + " 2,growx");
		row += 1;
		JLabel lblMainScene = new JLabel(Messages.getString("OBSConnectPanel.MainScene"));
		add(lblMainScene, "cell " + COLUMN0 + " " + row + ", alignx trailing");
		txtMainScene = new JTextField();
		txtMainScene.setText(Settings.getOBSParameter("OBSMainScene"));
		txtMainScene.setColumns(10);
		add(txtMainScene, "cell " + COLUMN1 + " " + row + " 2,growx");
		btnSetMainScene = new JButton(Messages.getString("OBSConnectPanel.SetMainScene"));
		add(btnSetMainScene, "cell " + COLUMN3 + " " + row + ",growx");
		row += 1;
		JLabel lblMonitor = new JLabel(Messages.getString("OBSConnectPanel.Monitor"));
		add(lblMonitor, "cell " + COLUMN0 + " " + row + ", alignx trailing");
		add(monitorComboBox, "cell " + COLUMN1 + " " + row + " 2,growx");
		btnFetchMonitors = new JButton(Messages.getString("OBSConnectPanel.FetchMonitors"));
		add(btnFetchMonitors, "cell " + COLUMN3 + " " + row + ",growx");
		btnProject = new JButton(Messages.getString("OBSConnectPanel.Project"));
		add(btnProject, "cell " + COLUMN4 + " " + row + ",growx");
		row += 1;
		JLabel lblScene = new JLabel(Messages.getString("OBSConnectPanel.Scene"));
		add(lblScene, "cell " + COLUMN0 + " " + row + ", alignx trailing");
		add(sceneComboBox, "cell " + COLUMN1 + " " + row + " 2,growx");
		btnFetchScenes = new JButton(Messages.getString("OBSConnectPanel.FetchScenes"));
		add(btnFetchScenes, "cell " + COLUMN3 + " " + row + ",growx");
		btnActivateScene = new JButton(Messages.getString("OBSConnectPanel.ActivateScene"));
		add(btnActivateScene, "cell " + COLUMN4 + " " + row + ",growx");
		row += 1;
		chckbxSavePassword = new JCheckBox(Messages.getString("OBSConnectPanel.SavePassword"));
		if (Settings.getOBSParameter("OBSSavePassword").equals(ON)) { //$NON-NLS-1$
			chckbxSavePassword.setSelected(true);
		} else {
			chckbxSavePassword.setSelected(false);
		}
		add(chckbxSavePassword, "cell " + COLUMN1 + " " + row);
		chckbxCloseOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.CloseOnConnect"));
		if (Settings.getOBSParameter("OBSCloseOnConnect").equals(ON)) { //$NON-NLS-1$
			chckbxCloseOnConnect.setSelected(true);
		} else {
			chckbxCloseOnConnect.setSelected(false);
		}
		add(chckbxCloseOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left");
		row += 1;
		chckbxAutoLogin = new JCheckBox(Messages.getString("OBSConnectPanel.AutoLoginOnStart"));
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ON)) { //$NON-NLS-1$
			chckbxAutoLogin.setSelected(true);
		} else {
			chckbxAutoLogin.setSelected(false);
		}
		add(chckbxAutoLogin, "cell " + COLUMN1 + " " + row);
		chckbxUpdateOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.UpdateOnConnect"));
		if (Settings.getOBSParameter("OBSUpdateOnConnect").equals(ON)) {
			chckbxUpdateOnConnect.setSelected(true);
		} else {
			chckbxUpdateOnConnect.setSelected(false);
		}
		add(chckbxUpdateOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left");
		row += 1;
		btnConnect = new JButton(Messages.getString("OBSConnectPanel.Connect"));
		add(btnConnect, "flowx,cell " + COLUMN1 + " " + row);
		btnDisconnect = new JButton(Messages.getString("OBSConnectPanel.Disconnect"));
		add(btnDisconnect, "cell " + COLUMN2 + " " + row + ",alignx left");
		row += 1;
		JLabel lblMessage = new JLabel(Messages.getString("OBSConnectPanel.Message"));
		add(lblMessage, "cell " + COLUMN0 + " " + row + ", alignx trailing");
		row += 1;
		scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		lstMessageHistory.setCellRenderer(new AttributiveCellRenderer());
		add(scrMessageHistory, "cell " + COLUMN1 + " " + row + " 4 1,grow");
		row += 1;
		btnApply = new JButton(Messages.getString("Global.Apply"));
		add(btnApply, "cell " + COLUMN1 + " " + row + ",growx");
		btnSave = new JButton(Messages.getString("Global.Save"));
		add(btnSave, "cell " + COLUMN1 + " " + row + ",alignx left");
		btnCancel = new JButton(Messages.getString("Global.Cancel"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revertChanges();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell " + COLUMN3 + " " + row + ",growx");
		btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults"));
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restoreDefaults();
			}
		});
		add(btnRestoreDefaults, "cell " + COLUMN4+ " " + row + ",alignx left");


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
	public void restoreDefaults() {
		txtHost.setText(Settings.getDefaultOBSParameter("OBSHost"));
		txtPort.setText(Settings.getDefaultOBSParameter("OBSPort"));
		txtPassword.setText(Settings.getDefaultOBSParameter("OBSPassword"));
		txtMainScene.setText(Settings.getDefaultOBSParameter("OBSMainScene"));
		chckbxSavePassword.setSelected(Settings.getDefaultOBSParameter("OBSSavePassword")==ON);
		chckbxAutoLogin.setSelected(Settings.getDefaultOBSParameter("OBSAutoLogin")==ON);
		chckbxCloseOnConnect.setSelected(Settings.getDefaultOBSParameter("OBSCloseOnConnect")==ON);
		chckbxUpdateOnConnect.setSelected(Settings.getDefaultOBSParameter("OBSUpdateOnConnect")==ON);
	}
	public void revertChanges() {
		txtHost.setText(Settings.getOBSParameter("OBSHost"));
		txtPort.setText(Settings.getOBSParameter("OBSPort"));
		txtPassword.setText(Settings.getOBSParameter("OBSPassword"));
		txtMainScene.setText(Settings.getOBSParameter("OBSMainScene"));
		chckbxSavePassword.setSelected(Settings.getOBSParameter("OBSSavePassword")==ON);
		chckbxAutoLogin.setSelected(Settings.getOBSParameter("OBSAutoLogin")==ON);
		chckbxCloseOnConnect.setSelected(Settings.getOBSParameter("OBSCloseOnConnect")==ON);
		chckbxUpdateOnConnect.setSelected(Settings.getOBSParameter("OBSUpdateOnConnect")==ON);
	}
	public void saveSettings() {
		updateOBS();
		Settings.setOBS("OBSHost",txtHost.getText());
		Settings.setOBS("OBSPort",txtPort.getText());
		Settings.setOBS("OBSMainScene",txtMainScene.getText());
		Settings.setOBS("OBSAutoLogin", chckbxAutoLogin.isSelected() ? ON : OFF);
		Settings.setOBS("OBSUpdateOnConnect", chckbxUpdateOnConnect.isSelected() ? ON : OFF);
		Settings.setOBS("OBSCloseOnConnect", chckbxCloseOnConnect.isSelected() ? ON : OFF);
		Settings.setOBS("OBSSavePassword",chckbxSavePassword.isSelected() ? ON : OFF);
		Settings.setOBS("OBSPassword",chckbxSavePassword.isSelected() ? txtPassword.getText() : "");
		OBS.setMainScene(txtMainScene.getText());
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ON)) {
			if (Settings.getOBSParameter("OBSHost").isEmpty() || Settings.getOBSParameter("OBSPassword").isEmpty() || Settings.getOBSParameter("OBSPort").isEmpty() || Settings.getOBSParameter("OBSSavePassword").equals(OFF)) {
				Settings.setOBS("OBSAutoLogin",OFF);
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
	public void updateOBS() {
		OBS.setHost(txtHost.getText());
		OBS.setPort(txtPort.getText());
		OBS.setPassword(txtPassword.getText());
		OBS.setMainScene(txtMainScene.getText());
		OBS.setAutoLogin(chckbxAutoLogin.isSelected());
		OBS.setUpdateOnConnect(chckbxUpdateOnConnect.isSelected());
		OBS.setCloseOnConnect(chckbxCloseOnConnect.isSelected());
		OBS.setSavePassword(chckbxSavePassword.isSelected());
	}
   public class AttributiveCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		public AttributiveCellRenderer() {
			setOpaque(true);
		}
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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
//    public void updateSceneList(HashMap<Integer, String> sceneMap) {
//    	sceneComboBox.removeAllItems();
//    	for (HashMap.Entry<Integer, String> entry : sceneMap.entrySet()) {
//    		int index = entry.getKey();
//    		String scene = entry.getValue();
//    		sceneComboBox.addItem(index + ": " + scene);
//    	}
//    	sceneComboBox.repaint();
//    }
    public void updateSceneList(HashMap<Integer, String> sceneMap) {
        sceneComboBox.removeAllItems();
        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(sceneMap.entrySet());
        for (int i = entryList.size() - 1; i >= 0; i--) {
            Map.Entry<Integer, String> entry = entryList.get(i);
            String scene = entry.getValue();
            sceneComboBox.addItem(scene);
        }
        sceneComboBox.repaint();
    }
    public void updateMainScene(String scene) {
    	txtMainScene.setText(scene);
    	txtMainScene.repaint();
    }
    public int getSelectedSceneIndex() {
    	int sceneIndex = sceneComboBox.getSelectedIndex();
    	return sceneIndex;
    }
    public String getSelectedSceneName() {
    	String scene;
    	if (getSelectedSceneIndex() > -1) {
//    		scene = ((String) sceneComboBox.getSelectedItem()).split(": ")[1];
    		scene = ((String) sceneComboBox.getSelectedItem());
    	} else {
    		scene = "";
    	}
    	return scene;
    }
	////// Listeners \\\\\\
	public void addMainSceneFocusListener(FocusListener focusListenerForTxtMainScene) {
		txtMainScene.addFocusListener(focusListenerForTxtMainScene);
	}
	public void addSetMainSceneListener(ActionListener listenForBtnSetMainScene) {
    	btnSetMainScene.addActionListener(listenForBtnSetMainScene);
    }
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
	public void addMainSceneListener(ActionListener listenForTxtMainScene) {
		txtMainScene.addActionListener(listenForTxtMainScene);
	}
	public void addApplyListener(ActionListener listenForBtnApply) {
		btnApply.addActionListener(listenForBtnApply);
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
	public void addFetchScenesListener(ActionListener listenForBtnFetchScenes) {
		btnFetchScenes.addActionListener(listenForBtnFetchScenes);
	}
	public void addProjectListener(ActionListener listenForBtnProject) {
		btnProject.addActionListener(listenForBtnProject);
	}
	public void addActivateSceneListener(ActionListener listenForBtnActivateScene) {
		btnActivateScene.addActionListener(listenForBtnActivateScene);
	}
}
