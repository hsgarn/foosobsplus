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
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.IntPredicate;

import javax.swing.Icon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.InetAddresses;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

import net.miginfocom.swing.MigLayout;

public class AutoScoreSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JComboBox<TableConnection> cmbTables;
	private final DefaultComboBoxModel<TableConnection> mdlTables;
	private final JButton btnAddTable;
	private final JButton btnDeleteTable;
	private final JTextField txtLabel;
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
	private final Map<Component, Object> snapshot = new HashMap<>();
	private BooleanSupplier saveCallback = () -> { saveSettings(); return true; };
	private Runnable afterSaveCallback = () -> {};
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreSettingsPanel.class);
	// In-memory list of table connections being edited and the one currently
	// shown in the editor fields. Edits are committed to currentConnection when
	// the selection changes or on save, so navigating between tables never loses
	// in-progress edits.
	private final List<TableConnection> connections;
	private TableConnection currentConnection;
	// Guards against the combo/editor listeners reacting while we are loading
	// field values programmatically.
	private boolean loadingFields = false;
	// Supplies the live connection state of the table at a given index (set by
	// Main), so the Table dropdown can show a green/red dot per table.
	private IntPredicate tableConnected = i -> false;
	private static final Icon DOT_CONNECTED = makeDot(new Color(0, 170, 0));
	private static final Icon DOT_DISCONNECTED = makeDot(new Color(200, 0, 0));
	public AutoScoreSettingsPanel() throws IOException {
		connections = Settings.getTableConnections();
		currentConnection = connections.get(0);
		mdlMessageHistory = new DefaultListModel<>();
		lstMessageHistory = new JList<>(mdlMessageHistory);
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// --- Table selector row ---
		JLabel lblTable = new JLabel("Table:"); //$NON-NLS-1$
		add(lblTable, "cell 0 0,alignx trailing"); //$NON-NLS-1$
		mdlTables = new DefaultComboBoxModel<>();
		for (TableConnection c : connections) mdlTables.addElement(c);
		cmbTables = new JComboBox<>(mdlTables);
		cmbTables.setSelectedItem(currentConnection);
		cmbTables.addActionListener((ActionEvent e) -> onTableSelected());
		cmbTables.setRenderer(new ConnectionStatusRenderer());
		add(cmbTables, "cell 1 0,growx"); //$NON-NLS-1$
		btnAddTable = new JButton("Add"); //$NON-NLS-1$
		btnAddTable.addActionListener((ActionEvent e) -> addTable());
		add(btnAddTable, "flowx,cell 2 0,alignx left"); //$NON-NLS-1$
		btnDeleteTable = new JButton("Delete"); //$NON-NLS-1$
		btnDeleteTable.addActionListener((ActionEvent e) -> deleteTable());
		add(btnDeleteTable, "cell 2 0,alignx left"); //$NON-NLS-1$
		// --- Name row ---
		JLabel lblLabel = new JLabel("Name:"); //$NON-NLS-1$
		add(lblLabel, "cell 0 1,alignx trailing"); //$NON-NLS-1$
		txtLabel = new JTextField();
		txtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		txtLabel.setColumns(10);
		add(txtLabel, "cell 1 1,alignx left"); //$NON-NLS-1$
		// --- Connection detail rows ---
		JLabel lblServerAddress = new JLabel(Messages.getString("AutoScoreSettingsPanel.ServerAddress")); //$NON-NLS-1$
		add(lblServerAddress, "cell 0 2,alignx trailing"); //$NON-NLS-1$
		txtServerAddress = new JTextField();
		txtServerAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerAddress.setInputVerifier(new IPAddrInputVerifier());
		txtServerAddress.setColumns(10);
		add(txtServerAddress, "cell 1 2,alignx left"); //$NON-NLS-1$
		chckbxAutoConnect = new JCheckBox(Messages.getString("AutoScoreSettingsPanel.AutoConnectOnStartUp")); //$NON-NLS-1$
		add(chckbxAutoConnect, "cell 2 2, alignx left"); //$NON-NLS-1$
		JLabel lblServerPort = new JLabel(Messages.getString("AutoScoreSettingsPanel.ServerPort")); //$NON-NLS-1$
		add(lblServerPort, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		txtServerPort = new JTextField();
		txtServerPort.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerPort.setColumns(10);
		add(txtServerPort, "cell 1 3,alignx left,aligny top"); //$NON-NLS-1$
		chckbxDetailLog = new JCheckBox(Messages.getString("AutoScoreSettingsPanel.DetailLog")); //$NON-NLS-1$
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
		btnCancel.addActionListener((ActionEvent e) -> {
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    confirmClose(win);
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
		loadConnectionIntoFields(currentConnection);
		takeSnapshot();
	}
	// --- Table connection list management ---
	private void onTableSelected() {
		if (loadingFields) return;
		TableConnection selected = (TableConnection) cmbTables.getSelectedItem();
		if (selected == null || selected == currentConnection) return;
		commitFieldsTo(currentConnection);
		currentConnection = selected;
		loadConnectionIntoFields(currentConnection);
	}
	private void addTable() {
		commitFieldsTo(currentConnection);
		TableConnection added = new TableConnection(
			"Table " + (connections.size() + 1), //$NON-NLS-1$
			Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_ADDRESS), //$NON-NLS-1$
			Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_PORT), //$NON-NLS-1$
			false, false);
		connections.add(added);
		mdlTables.addElement(added);
		currentConnection = added;
		loadingFields = true;
		cmbTables.setSelectedItem(added);
		loadingFields = false;
		loadConnectionIntoFields(added);
	}
	private void deleteTable() {
		if (connections.size() <= 1) {
			JOptionPane.showMessageDialog(this, "At least one table connection is required.", "Cannot Delete", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
		connections.remove(currentConnection);
		mdlTables.removeElement(currentConnection);
		currentConnection = connections.get(0);
		loadingFields = true;
		cmbTables.setSelectedItem(currentConnection);
		loadingFields = false;
		loadConnectionIntoFields(currentConnection);
	}
	private void loadConnectionIntoFields(TableConnection c) {
		loadingFields = true;
		txtLabel.setText(c.getLabel());
		txtServerAddress.setText(c.getServerAddress());
		txtServerPort.setText(c.getServerPort());
		chckbxAutoConnect.setSelected(c.isAutoConnect());
		chckbxDetailLog.setSelected(c.isDetailLog());
		loadingFields = false;
	}
	private void commitFieldsTo(TableConnection c) {
		c.setLabel(txtLabel.getText());
		c.setServerAddress(txtServerAddress.getText());
		c.setServerPort(txtServerPort.getText());
		c.setAutoConnect(chckbxAutoConnect.isSelected());
		c.setDetailLog(chckbxDetailLog.isSelected());
		cmbTables.repaint();
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
	public void setAfterSaveCallback(Runnable callback) {
		this.afterSaveCallback = callback == null ? () -> {} : callback;
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
		txtServerAddress.setText(Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_ADDRESS)); //$NON-NLS-1$
		txtServerPort.setText(Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_PORT)); //$NON-NLS-1$
	}
	private void revertChanges() {
		// Discard in-memory edits by reloading the saved connection list.
		connections.clear();
		connections.addAll(Settings.getTableConnections());
		mdlTables.removeAllElements();
		for (TableConnection c : connections) mdlTables.addElement(c);
		currentConnection = connections.get(0);
		loadingFields = true;
		cmbTables.setSelectedItem(currentConnection);
		loadingFields = false;
		loadConnectionIntoFields(currentConnection);
		takeSnapshot();
	}
	public void setSaveCallback(BooleanSupplier callback) { this.saveCallback = callback; }
	private void takeSnapshot() { snapshot.clear(); snapshotOf(this); }
	private void snapshotOf(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JCheckBox cb) {
				snapshot.put(cb, cb.isSelected());
			} else if (c instanceof JTextField tf) {
				snapshot.put(tf, tf.getText());
			} else if (c instanceof Container sub) {
				snapshotOf(sub);
			}
		}
	}
	public boolean hasChanges() { return checkChangesIn(this); }
	private boolean checkChangesIn(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JCheckBox cb) {
				Object saved = snapshot.get(cb);
				if (saved != null && !saved.equals(cb.isSelected())) return true;
			} else if (c instanceof JTextField tf) {
				Object saved = snapshot.get(tf);
				if (saved != null && !tf.getText().equals(saved)) return true;
			} else if (c instanceof Container sub) {
				if (checkChangesIn(sub)) return true;
			}
		}
		return false;
	}
	void confirmClose(Window win) {
		if (!hasChanges()) {
			revertChanges();
			win.dispose();
			return;
		}
		int result = JOptionPane.showConfirmDialog(
			win,
			Messages.getString("Global.UnsavedChangesMessage"), //$NON-NLS-1$
			Messages.getString("Global.UnsavedChangesTitle"), //$NON-NLS-1$
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			if (saveCallback.getAsBoolean()) {
				win.dispose();
			}
		} else if (result == JOptionPane.NO_OPTION) {
			revertChanges();
			win.dispose();
		}
	}
	// Index of the table currently selected in the dropdown. Aligned with the
	// order of Settings.getTableConnections() and the live AutoScoreManager list.
	public int getSelectedTableIndex() {
		return cmbTables.getSelectedIndex();
	}
	public int getTableCount() {
		return connections.size();
	}
	// Applies a discovered device's address/port to the table connection at the
	// given index (dropdown order). When that connection is the one being edited,
	// the editor fields are updated too so a later commit does not overwrite it.
	public void setTableAddress(int index, String host, String port) {
		if (index < 0 || index >= connections.size()) return;
		TableConnection c = connections.get(index);
		c.setServerAddress(host);
		c.setServerPort(port);
		if (c == currentConnection) {
			loadingFields = true;
			txtServerAddress.setText(host);
			txtServerPort.setText(port);
			loadingFields = false;
		}
		cmbTables.repaint();
	}
	public void saveSettings() {
		commitFieldsTo(currentConnection);
		try {
			Settings.saveTableConnections(connections);
			// Mirror the currently selected connection into the legacy
			// single-connection keys so Connect/Search operate on the table the
			// user is viewing, until per-session wiring routes each connection to
			// its own session.
			Settings.setAutoScore(SettingsKeys.AS_SERVER_ADDRESS, currentConnection.getServerAddress()); //$NON-NLS-1$
			Settings.setAutoScore(SettingsKeys.AS_SERVER_PORT, currentConnection.getServerPort()); //$NON-NLS-1$
			Settings.setAutoScore(SettingsKeys.AS_AUTO_CONNECT, currentConnection.isAutoConnect() ? ON : OFF); //$NON-NLS-1$
			Settings.setAutoScore(SettingsKeys.AS_DETAIL_LOG, currentConnection.isDetailLog() ? ON : OFF); //$NON-NLS-1$
			Settings.saveAutoScoreSettingsConfig();
			takeSnapshot();
			afterSaveCallback.run();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());	//$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	// Registers the provider that reports each table's connection state by index,
	// and triggers an initial repaint of the dropdown dots.
	public void setTableConnectedProvider(IntPredicate provider) {
		this.tableConnected = provider;
		refreshTableStatus();
	}
	// Repaints the Table dropdown so its connection dots reflect current state.
	public void refreshTableStatus() {
		cmbTables.repaint();
	}
	private static Icon makeDot(Color color) {
		return new Icon() {
			@Override public int getIconWidth() { return 12; }
			@Override public int getIconHeight() { return 12; }
			@Override public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(color);
				g2.fillOval(x + 1, y + 1, 10, 10);
				g2.setColor(color.darker());
				g2.drawOval(x + 1, y + 1, 10, 10);
				g2.dispose();
			}
		};
	}
	// Renders each table in the dropdown with a green (connected) or red
	// (disconnected) dot. For the collapsed display (index -1) it uses the
	// currently selected table's state.
	private class ConnectionStatusRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			int statusIdx = (index >= 0) ? index : cmbTables.getSelectedIndex();
			boolean connected = statusIdx >= 0 && tableConnected.test(statusIdx);
			if (comp instanceof JLabel) {
				((JLabel) comp).setIcon(connected ? DOT_CONNECTED : DOT_DISCONNECTED);
			}
			return comp;
		}
	}
	public class IPAddrInputVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			try {
				if (text != null && InetAddresses.isInetAddress(text)) {
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
