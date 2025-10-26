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
	private final JTextField txtHost;
	private final JTextField txtPort;
	private final JButton btnConnect;
	private final JTextField txtPassword;
	private final JTextField txtMainScene;
	private final JButton btnSetMainScene;
	private final JCheckBox chckbxSavePassword;
	private final JCheckBox chckbxAutoLogin;
	private final JCheckBox chckbxCloseOnConnect;
	private final JCheckBox chckbxUpdateOnConnect;
	private final JButton btnDisconnect;
	private final JButton btnFetchMonitors;
	private final JButton btnFetchScenes;
	private final JButton btnProject;
	private final JButton btnActivateScene;
	private final JButton btnApply;
	private final JButton btnSave;
	private final JButton btnCancel;
	private final JButton btnRestoreDefaults;
	private final JList<String> lstMessageHistory;
	private final DefaultListModel<String> mdlMessageHistory;
	private final JScrollPane scrMessageHistory;
	private final JComboBox<String> monitorComboBox;
	private final JComboBox<String> sceneComboBox;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final String OFF = "0"; //$NON-NLS-1$
	private static final Logger logger = LoggerFactory.getLogger(OBSConnectPanel.class);
	private static final int COLUMN0 = 0;
	private static final int COLUMN1 = 1;
	private static final int COLUMN2 = 2;
	private static final int COLUMN3 = 3;
	private static final int COLUMN4 = 4;
	
	public OBSConnectPanel() throws IOException {
		int row = 0;
		monitorComboBox = new JComboBox<>();
		sceneComboBox = new JComboBox<>();
		mdlMessageHistory = new DefaultListModel<>();
		lstMessageHistory = new JList<>(mdlMessageHistory);
		setLayout(new MigLayout("", "[grow][grow][][]", "[][][][][][][][][][][][grow]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblPanelTitle = new JLabel(Messages.getString("OBSConnectPanel.Title")); //$NON-NLS-1$
		add(lblPanelTitle, "cell " + COLUMN1 + " " + row); //$NON-NLS-1$ //$NON-NLS-2$
		row += 1;
		JLabel lblHost = new JLabel(Messages.getString("OBSConnectPanel.Host")); //$NON-NLS-1$
		add(lblHost, "cell " + COLUMN0 + " " + row + ",alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		txtHost = new JTextField();
		txtHost.setText(Settings.getOBSParameter("OBSHost")); //$NON-NLS-1$
		txtHost.setColumns(10);
		add(txtHost, "cell " + COLUMN1 + " " + row + " 2,growx");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblPort = new JLabel(Messages.getString("OBSConnectPanel.Port")); //$NON-NLS-1$
		add(lblPort, "cell " + COLUMN0 + " " + row + ",alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		txtPort = new JTextField();
		txtPort.setText(Settings.getOBSParameter("OBSPort")); //$NON-NLS-1$
		txtPort.setColumns(10);
		add(txtPort, "cell " + COLUMN1 + " " + row + " 2,growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblPassword = new JLabel(Messages.getString("OBSConnectPanel.Password")); //$NON-NLS-1$
		add(lblPassword, "cell " + COLUMN0 + " " + row + ",alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		txtPassword = new JTextField();
		txtPassword.setText(Settings.getOBSParameter("OBSPassword")); //$NON-NLS-1$
		txtPassword.setColumns(10);
		add(txtPassword, "cell " + COLUMN1 + " " + row + " 2,growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblMainScene = new JLabel(Messages.getString("OBSConnectPanel.MainScene")); //$NON-NLS-1$
		add(lblMainScene, "cell " + COLUMN0 + " " + row + ", alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		txtMainScene = new JTextField();
		txtMainScene.setText(Settings.getOBSParameter("OBSMainScene")); //$NON-NLS-1$
		txtMainScene.setColumns(10);
		add(txtMainScene, "cell " + COLUMN1 + " " + row + " 2,growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnSetMainScene = new JButton(Messages.getString("OBSConnectPanel.SetMainScene")); //$NON-NLS-1$
		add(btnSetMainScene, "cell " + COLUMN3 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblMonitor = new JLabel(Messages.getString("OBSConnectPanel.Monitor")); //$NON-NLS-1$
		add(lblMonitor, "cell " + COLUMN0 + " " + row + ", alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		add(monitorComboBox, "cell " + COLUMN1 + " " + row + " 2,growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnFetchMonitors = new JButton(Messages.getString("OBSConnectPanel.FetchMonitors")); //$NON-NLS-1$
		add(btnFetchMonitors, "cell " + COLUMN3 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnProject = new JButton(Messages.getString("OBSConnectPanel.Project")); //$NON-NLS-1$
		add(btnProject, "cell " + COLUMN4 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblScene = new JLabel(Messages.getString("OBSConnectPanel.Scene")); //$NON-NLS-1$
		add(lblScene, "cell " + COLUMN0 + " " + row + ", alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		add(sceneComboBox, "cell " + COLUMN1 + " " + row + " 2,growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnFetchScenes = new JButton(Messages.getString("OBSConnectPanel.FetchScenes")); //$NON-NLS-1$
		add(btnFetchScenes, "cell " + COLUMN3 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnActivateScene = new JButton(Messages.getString("OBSConnectPanel.ActivateScene")); //$NON-NLS-1$
		add(btnActivateScene, "cell " + COLUMN4 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		chckbxSavePassword = new JCheckBox(Messages.getString("OBSConnectPanel.SavePassword")); //$NON-NLS-1$
		if (Settings.getOBSParameter("OBSSavePassword").equals(ON)) { //$NON-NLS-1$
			chckbxSavePassword.setSelected(true);
		} else {
			chckbxSavePassword.setSelected(false);
		}
		add(chckbxSavePassword, "cell " + COLUMN1 + " " + row); //$NON-NLS-1$ //$NON-NLS-2$
		chckbxCloseOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.CloseOnConnect")); //$NON-NLS-1$
		if (Settings.getOBSParameter("OBSCloseOnConnect").equals(ON)) { //$NON-NLS-1$
			chckbxCloseOnConnect.setSelected(true);
		} else {
			chckbxCloseOnConnect.setSelected(false);
		}
		add(chckbxCloseOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		chckbxAutoLogin = new JCheckBox(Messages.getString("OBSConnectPanel.AutoLoginOnStart")); //$NON-NLS-1$
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ON)) { //$NON-NLS-1$
			chckbxAutoLogin.setSelected(true);
		} else {
			chckbxAutoLogin.setSelected(false);
		}
		add(chckbxAutoLogin, "cell " + COLUMN1 + " " + row); //$NON-NLS-1$ //$NON-NLS-2$
		chckbxUpdateOnConnect = new JCheckBox(Messages.getString("OBSConnectPanel.UpdateOnConnect")); //$NON-NLS-1$
		if (Settings.getOBSParameter("OBSUpdateOnConnect").equals(ON)) { //$NON-NLS-1$
			chckbxUpdateOnConnect.setSelected(true);
		} else {
			chckbxUpdateOnConnect.setSelected(false);
		}
		add(chckbxUpdateOnConnect, "cell " + COLUMN2 + " " + row + ",alignx left"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		btnConnect = new JButton(Messages.getString("OBSConnectPanel.Connect")); //$NON-NLS-1$
		add(btnConnect, "flowx,cell " + COLUMN1 + " " + row); //$NON-NLS-1$ //$NON-NLS-2$
		btnDisconnect = new JButton(Messages.getString("OBSConnectPanel.Disconnect")); //$NON-NLS-1$
		add(btnDisconnect, "cell " + COLUMN2 + " " + row + ",alignx left"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		JLabel lblMessage = new JLabel(Messages.getString("OBSConnectPanel.Message")); //$NON-NLS-1$
		add(lblMessage, "cell " + COLUMN0 + " " + row + ", alignx trailing"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		lstMessageHistory.setCellRenderer(new AttributiveCellRenderer());
		add(scrMessageHistory, "cell " + COLUMN1 + " " + row + " 4 1,grow"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		row += 1;
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell " + COLUMN1 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell " + COLUMN1 + " " + row + ",alignx left"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancel, "cell " + COLUMN3 + " " + row + ",growx"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent arg0) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell " + COLUMN4+ " " + row + ",alignx left"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		if (!txtHost.getText().equals(Settings.getOBSParameter("OBSHost")) || //$NON-NLS-1$
			!txtPort.getText().equals(Settings.getOBSParameter("OBSPort"))  || //$NON-NLS-1$
			!txtPassword.getText().equals(Settings.getOBSParameter("OBSPassword"))) { //$NON-NLS-1$
			changed = true;
		}
		return changed;
	}
	public void restoreDefaults() {
		txtHost.setText(Settings.getDefaultOBSParameter("OBSHost")); //$NON-NLS-1$
		txtPort.setText(Settings.getDefaultOBSParameter("OBSPort")); //$NON-NLS-1$
		txtPassword.setText(Settings.getDefaultOBSParameter("OBSPassword")); //$NON-NLS-1$
		txtMainScene.setText(Settings.getDefaultOBSParameter("OBSMainScene")); //$NON-NLS-1$
		chckbxSavePassword.setSelected(Settings.getDefaultOBSParameter("OBSSavePassword").equals(ON)); //$NON-NLS-1$
		chckbxAutoLogin.setSelected(Settings.getDefaultOBSParameter("OBSAutoLogin").equals(ON)); //$NON-NLS-1$
		chckbxCloseOnConnect.setSelected(Settings.getDefaultOBSParameter("OBSCloseOnConnect").equals(ON)); //$NON-NLS-1$
		chckbxUpdateOnConnect.setSelected(Settings.getDefaultOBSParameter("OBSUpdateOnConnect").equals(ON)); //$NON-NLS-1$
	}
	public void revertChanges() {
		txtHost.setText(Settings.getOBSParameter("OBSHost")); //$NON-NLS-1$
		txtPort.setText(Settings.getOBSParameter("OBSPort")); //$NON-NLS-1$
		txtPassword.setText(Settings.getOBSParameter("OBSPassword")); //$NON-NLS-1$
		txtMainScene.setText(Settings.getOBSParameter("OBSMainScene")); //$NON-NLS-1$
		chckbxSavePassword.setSelected(Settings.getOBSParameter("OBSSavePassword").equals(ON)); //$NON-NLS-1$
		chckbxAutoLogin.setSelected(Settings.getOBSParameter("OBSAutoLogin").equals(ON)); //$NON-NLS-1$
		chckbxCloseOnConnect.setSelected(Settings.getOBSParameter("OBSCloseOnConnect").equals(ON)); //$NON-NLS-1$
		chckbxUpdateOnConnect.setSelected(Settings.getOBSParameter("OBSUpdateOnConnect").equals(ON)); //$NON-NLS-1$
	}
	public void saveSettings() {
		updateOBS();
		Settings.setOBS("OBSHost",txtHost.getText()); //$NON-NLS-1$
		Settings.setOBS("OBSPort",txtPort.getText()); //$NON-NLS-1$
		Settings.setOBS("OBSMainScene",txtMainScene.getText()); //$NON-NLS-1$
		Settings.setOBS("OBSAutoLogin", chckbxAutoLogin.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setOBS("OBSUpdateOnConnect", chckbxUpdateOnConnect.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setOBS("OBSCloseOnConnect", chckbxCloseOnConnect.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setOBS("OBSSavePassword",chckbxSavePassword.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setOBS("OBSPassword",chckbxSavePassword.isSelected() ? txtPassword.getText() : ""); //$NON-NLS-1$ //$NON-NLS-2$
		OBS.setMainScene(txtMainScene.getText());
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ON)) { //$NON-NLS-1$
			if (Settings.getOBSParameter("OBSHost").isEmpty() || Settings.getOBSParameter("OBSPassword").isEmpty() || Settings.getOBSParameter("OBSPort").isEmpty() || Settings.getOBSParameter("OBSSavePassword").equals(OFF)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				Settings.setOBS("OBSAutoLogin",OFF); //$NON-NLS-1$
				chckbxAutoLogin.setSelected(false);
				String msg = Messages.getString("Errors.OBSConnectPanel.AutoLogin"); //$NON-NLS-1$
				String ttl = Messages.getString("Errors.OBSConnectPanel.AutoLogin.Title"); //$NON-NLS-1$
				logger.error(msg);
				JOptionPane.showMessageDialog(null, msg, ttl,1);
			}
		}
		try {
			Settings.saveOBSConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, ex.getMessage(), Messages.getString("Errors.ErrorSavingPropertiesFile"),1); //$NON-NLS-1$
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
                @Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			String tmp;
			tmp = (String) value;
			setBackground(UIManager.getColor("List.background")); //$NON-NLS-1$
			setForeground(UIManager.getColor("List.foreground")); //$NON-NLS-1$
			if (tmp.contains("Disconnect") || tmp.contains("Unable") || tmp.contains("ERROR!")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				setForeground(Color.RED);
			} 
			if (tmp.contains("Connected!")) { //$NON-NLS-1$
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
    		monitorComboBox.addItem(index + ": " + monitor); //$NON-NLS-1$
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
    		scene = ""; //$NON-NLS-1$
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
