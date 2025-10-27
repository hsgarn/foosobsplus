/**
Copyright © 2022-2026 Hugh Garner
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
import java.io.IOException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.InetAddresses;
import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class AutoScoreSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTextField txtServerAddress;
	private final JTextField txtServerPort;
	private final JCheckBox chckbxAutoConnect;
	private final JCheckBox chckbxDetailLog;
	private final JButton btnApply;
	private final JButton btnSave;
	private final JButton btnConnect;
	private final JButton btnDisconnect;
    private final JButton btnSearch;
	private final JList<String> lstMessageHistory;
	private final DefaultListModel<String> mdlMessageHistory;
	private final JScrollPane scrMessageHistory;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final String OFF = "0"; //$NON-NLS-1$
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreSettingsPanel.class);
	public AutoScoreSettingsPanel() throws IOException {
		mdlMessageHistory = new DefaultListModel<>();
		lstMessageHistory = new JList<>(mdlMessageHistory);
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblServerAddress = new JLabel(Messages.getString("AutoScoreSettingsPanel.ServerAddress")); //$NON-NLS-1$
		add(lblServerAddress, "cell 0 2,alignx trailing"); //$NON-NLS-1$
		txtServerAddress = new JTextField();
		txtServerAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerAddress.setInputVerifier(new IPAddrInputVerifier());
		txtServerAddress.setText(Settings.getAutoScoreParameter("AutoScoreSettingsServerAddress")); //$NON-NLS-1$
		txtServerAddress.setColumns(10);
		add(txtServerAddress, "cell 1 2,alignx left"); //$NON-NLS-1$
		chckbxAutoConnect = new JCheckBox(Messages.getString("AutoScoreSettingsPanel.AutoConnectOnStartUp")); //$NON-NLS-1$
		if (Settings.getAutoScoreParameter("AutoScoreSettingsAutoConnect").equals(ON)) { //$NON-NLS-1$
			chckbxAutoConnect.setSelected(true);
		} else {
			chckbxAutoConnect.setSelected(false);
		}
		add(chckbxAutoConnect, "cell 2 2, alignx left"); //$NON-NLS-1$
		JLabel lblServerPort = new JLabel(Messages.getString("AutoScoreSettingsPanel.ServerPort")); //$NON-NLS-1$
		add(lblServerPort, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		txtServerPort = new JTextField();
		txtServerPort.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerPort.setText(Settings.getAutoScoreParameter("AutoScoreSettingsServerPort")); //$NON-NLS-1$
		txtServerPort.setColumns(10);
		add(txtServerPort, "cell 1 3,alignx left,aligny top"); //$NON-NLS-1$
		chckbxDetailLog = new JCheckBox(Messages.getString("AutoScoreSettingsPanel.DetailLog")); //$NON-NLS-1$
		if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals(ON)) { //$NON-NLS-1$
			chckbxDetailLog.setSelected(true);
		} else {
			chckbxDetailLog.setSelected(false);
		}
		add(chckbxDetailLog, "cell 2 3, alignx left"); //$NON-NLS-1$
        btnSearch = new JButton(Messages.getString("AutoScoreSettingsPanel.Search")); //$NON-NLS-1$
        add(btnSearch, "flowx,cell 0 4,alignx left"); //$NON-NLS-1$
		btnConnect = new JButton(Messages.getString("AutoScoreSettingsPanel.Connect")); //$NON-NLS-1$
		add(btnConnect, "flowx,cell 1 4,alignx left"); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("AutoScoreSettingsPanel.Disconnect")); //$NON-NLS-1$
		add(btnDisconnect, "cell 2 4,growx"); //$NON-NLS-1$
		JLabel lblMessage = new JLabel(Messages.getString("AutoScoreSettingsPanel.Message")); //$NON-NLS-1$
		add(lblMessage, "cell 0 8"); //$NON-NLS-1$
		scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		lstMessageHistory.setCellRenderer(new AttributiveCellRenderer());
		add(scrMessageHistory, "cell 0 9 3,grow"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "flowx,cell 0 18, alignx center"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "flowx,cell 1 18,alignx center"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComponent comp = (JComponent) e.getSource();
                        Window win = SwingUtilities.getWindowAncestor(comp);
                        win.dispose();
                    }
                });
		add(btnCancel, "cell 1 18,alignx center"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            restoreDefaults();
                    }
		});
		add(btnRestoreDefaults, "cell 2 18,alignx center"); //$NON-NLS-1$
	}
    public class AttributiveCellRenderer extends DefaultListCellRenderer {
	  private static final long serialVersionUID = 1L;
	  public AttributiveCellRenderer() {
	    setOpaque(true);
	  }
          @Override
	  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	  {
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
    public void setServerAddress(String serverAddress) {
    	txtServerAddress.setText(serverAddress);
    }
    public void setServerPort(String serverPort) {
        txtServerPort.setText(serverPort);
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
	}
	private void restoreDefaults() {
		txtServerAddress.setText(Settings.getDefaultAutoScoreSettings("AutoScoreSettingsServerAddress")); //$NON-NLS-1$
		txtServerPort.setText(Settings.getDefaultAutoScoreSettings("AutoScoreSettingsServerPort")); //$NON-NLS-1$
	}
	public void saveSettings() {
		Settings.setAutoScore("AutoScoreSettingsServerAddress",txtServerAddress.getText()); //$NON-NLS-1$
		Settings.setAutoScore("AutoScoreSettingsServerPort",txtServerPort.getText()); //$NON-NLS-1$
		Settings.setAutoScore("AutoScoreSettingsAutoConnect",chckbxAutoConnect.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setAutoScore("AutoScoreSettingsDetailLog",chckbxDetailLog.isSelected() ? ON : OFF); //$NON-NLS-1$
		try {
			Settings.saveAutoScoreSettingsConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());	//$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public class IPAddrInputVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			try {
				if (InetAddresses.isInetAddress(text)) {
					return true;
				} else {
					logger.warn("Invalid input: [" + text + "]. Must be valid IP Address: ###.###.###.###"); //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.showMessageDialog(null, Messages.getString("AutoScoreSettingsPanel.MustBeValidIPAddress") + ": ###.###.###.###", Messages.getString("AutoScoreSettingsPanel.InvalidInput"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, Messages.getString("AutoScoreSettingsPanel.MustBeValidIPAddress") + ": ###.###.###.###", Messages.getString("AutoScoreSettingsPanel.InvalidInput"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				logger.error("Invalid input: [" + text + "]. Must be valid IP Address: ###.###.###.###"); //$NON-NLS-1$ //$NON-NLS-2$
				logger.error(e.toString());
				return false;
			}
		}
	}
	////// Listeners \\\\\\
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
	public void addApplyListener(ActionListener listenForBtnApply) {
		btnApply.addActionListener(listenForBtnApply);
	}
    public void addSearchListener(ActionListener listenForBtnSearch) {
        btnSearch.addActionListener(listenForBtnSearch);
    }
}
