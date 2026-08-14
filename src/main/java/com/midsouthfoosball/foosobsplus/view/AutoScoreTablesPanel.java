/**
Copyright © 2026 Hugh Garner
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.InetAddresses;
import com.midsouthfoosball.foosobsplus.controller.PicoSearchHelper;
import com.midsouthfoosball.foosobsplus.main.PicoDiscovery;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

import net.miginfocom.swing.MigLayout;

/**
 * Multi-table AutoScore configuration as a single grid: every configured
 * table's Label/Address/Port/Auto Connect/Detail Log/Camera Source/Status is
 * visible and editable at once, instead of one table at a time (see
 * {@link AutoScoreSettingsPanel}). Introduced alongside the existing
 * one-at-a-time settings screen (kept in place) so Search + Assign All's
 * edge cases - discovering more or fewer devices than configured tables -
 * are visible directly in the grid rather than inferred from a log.
 */
public class AutoScoreTablesPanel extends JPanel implements PicoSearchHelper.AssignTarget {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreTablesPanel.class);
	private static final int COL_LABEL = 0;
	private static final int COL_ADDRESS = 1;
	private static final int COL_PORT = 2;
	private static final int COL_AUTO_CONNECT = 3;
	private static final int COL_DETAIL_LOG = 4;
	private static final int COL_CAMERA_SOURCE = 5;
	private static final int COL_STATUS = 6;
	private static final int COL_ACTION = 7;
	private static final String[] COLUMN_NAMES = {
		"Label", "Address", "Port", "Auto Connect", "Detail Log", "Camera Source", "Status", "" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	};

	private final List<TableConnection> connections;
	private final ConnectionsTableModel tableModel;
	private final JTable table;
	private final DefaultListModel<String> mdlMessageHistory;
	private final JList<String> lstMessageHistory;
	private volatile boolean searching = false;
	private String savedSignature;
	private Runnable afterSaveCallback = () -> {};
	// Supplies the live connection state of the table at a given row (set by
	// Main), so the Status column reflects the real socket state.
	private IntPredicate tableConnected = i -> false;
	private static final Icon DOT_CONNECTED = makeDot(new Color(0, 170, 0));
	private static final Icon DOT_DISCONNECTED = makeDot(new Color(200, 0, 0));
	// Connect/Disconnect callbacks (set by Main); the per-row Action button
	// picks connect vs. disconnect itself based on tableConnected, and the
	// Connect All/Disconnect All buttons act on every table.
	private IntConsumer connectListener = i -> {};
	private IntConsumer disconnectListener = i -> {};
	private Runnable connectAllListener = () -> {};
	private Runnable disconnectAllListener = () -> {};
	// Camera Source column editor: a single editable, filterable combo shared
	// across all rows/edits (standard JTable cell-editor pattern), populated
	// with OBS source names when available - same combo + filtering behavior
	// as AutoScoreSettingsPanel's cmbCameraSource.
	private final JComboBox<String> cameraSourceCombo = new JComboBox<>();
	private List<String> obsSourcesList = new ArrayList<>();
	private boolean filterUpdating = false;

	public AutoScoreTablesPanel() {
		connections = new ArrayList<>(Settings.getTableConnections());
		mdlMessageHistory = new DefaultListModel<>();
		lstMessageHistory = new JList<>(mdlMessageHistory);
		tableModel = new ConnectionsTableModel();
		table = new JTable(tableModel);
		table.setRowHeight(22);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(COL_STATUS).setCellRenderer(new StatusCellRenderer());
		table.getColumnModel().getColumn(COL_ACTION).setCellRenderer(new ActionButtonRenderer());
		table.getColumnModel().getColumn(COL_ACTION).setCellEditor(new ActionButtonEditor());
		cameraSourceCombo.setEditable(true);
		cameraSourceCombo.setPrototypeDisplayValue("                    "); //$NON-NLS-1$
		cameraSourceCombo.setToolTipText(Messages.getString("AutoScoreSettingsPanel.CameraSourceToolTip")); //$NON-NLS-1$
		setupComboFiltering(cameraSourceCombo);
		table.getColumnModel().getColumn(COL_CAMERA_SOURCE).setCellEditor(new CameraSourceCellEditor(cameraSourceCombo));
		setColumnWidth(COL_LABEL, 90);
		setColumnWidth(COL_ADDRESS, 110);
		setColumnWidth(COL_PORT, 60);
		setColumnWidth(COL_AUTO_CONNECT, 90);
		setColumnWidth(COL_DETAIL_LOG, 80);
		setColumnWidth(COL_CAMERA_SOURCE, 150);
		setColumnWidth(COL_STATUS, 90);
		setColumnWidth(COL_ACTION, 90);

		setLayout(new MigLayout("", "[grow]", "[grow][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		add(new JScrollPane(table), "cell 0 0,grow,height 160:200:"); //$NON-NLS-1$

		JButton btnAddRow = new JButton("Add Table"); //$NON-NLS-1$
		btnAddRow.addActionListener((ActionEvent e) -> addRow());
		JButton btnDeleteRow = new JButton("Delete Table"); //$NON-NLS-1$
		btnDeleteRow.addActionListener((ActionEvent e) -> deleteSelectedRow());
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> restoreSelectedRowDefaults());
		JButton btnSearch = new JButton(Messages.getString("AutoScoreSettingsPanel.Search")); //$NON-NLS-1$
		btnSearch.addActionListener((ActionEvent e) -> search());
		JButton btnConnectAll = new JButton("Connect All"); //$NON-NLS-1$
		btnConnectAll.addActionListener((ActionEvent e) -> connectAllListener.run());
		JButton btnDisconnectAll = new JButton("Disconnect All"); //$NON-NLS-1$
		btnDisconnectAll.addActionListener((ActionEvent e) -> disconnectAllListener.run());
		JPanel rowButtons = new JPanel(new MigLayout("insets 0")); //$NON-NLS-1$ //$NON-NLS-2$
		rowButtons.add(btnAddRow);
		rowButtons.add(btnDeleteRow);
		rowButtons.add(btnRestoreDefaults);
		rowButtons.add(btnSearch);
		rowButtons.add(btnConnectAll);
		rowButtons.add(btnDisconnectAll);
		add(rowButtons, "cell 0 1,alignx left"); //$NON-NLS-1$

		JLabel lblMessage = new JLabel(Messages.getString("AutoScoreSettingsPanel.Message")); //$NON-NLS-1$
		add(lblMessage, "cell 0 2"); //$NON-NLS-1$
		JScrollPane scrMessageHistory = new JScrollPane();
		scrMessageHistory.setViewportView(lstMessageHistory);
		lstMessageHistory.setLayoutOrientation(JList.VERTICAL);
		add(scrMessageHistory, "cell 0 2,growx,height 80::,gaptop 20"); //$NON-NLS-1$

		JButton btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		btnApply.addActionListener((ActionEvent e) -> save());
		JButton btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSave.addActionListener((ActionEvent e) -> {
			if (save()) {
				Window win = SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				if (win != null) win.dispose();
			}
		});
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
			JComponent comp = (JComponent) e.getSource();
			confirmClose(SwingUtilities.getWindowAncestor(comp));
		});
		JPanel commitButtons = new JPanel(new MigLayout("insets 0")); //$NON-NLS-1$ //$NON-NLS-2$
		commitButtons.add(btnApply);
		commitButtons.add(btnSave);
		commitButtons.add(btnCancel);
		add(commitButtons, "cell 0 2,alignx center,aligny bottom"); //$NON-NLS-1$

		takeSnapshot();
	}

	private void setColumnWidth(int col, int width) {
		TableColumn column = table.getColumnModel().getColumn(col);
		column.setPreferredWidth(width);
	}

	// --- Row management ---
	private void addRow() {
		TableConnection added = new TableConnection(
			"Table " + (connections.size() + 1), //$NON-NLS-1$
			Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_ADDRESS), //$NON-NLS-1$
			Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_PORT), //$NON-NLS-1$
			false, false);
		connections.add(added);
		int newRow = connections.size() - 1;
		tableModel.fireTableRowsInserted(newRow, newRow);
		table.setRowSelectionInterval(newRow, newRow);
	}
	private void deleteSelectedRow() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Select a table to delete.", "No Selection", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
		if (connections.size() <= 1) {
			JOptionPane.showMessageDialog(this, "At least one table connection is required.", "Cannot Delete", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
		if (table.isEditing()) table.getCellEditor().cancelCellEditing();
		connections.remove(row);
		tableModel.fireTableDataChanged();
	}
	private void restoreSelectedRowDefaults() {
		int row = table.getSelectedRow();
		if (row < 0) return;
		TableConnection c = connections.get(row);
		c.setServerAddress(Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_ADDRESS)); //$NON-NLS-1$
		c.setServerPort(Settings.getDefaultAutoScoreSettings(SettingsKeys.AS_SERVER_PORT)); //$NON-NLS-1$
		tableModel.fireTableRowsUpdated(row, row);
	}

	// --- PicoSearchHelper.AssignTarget ---
	@Override
	public int getTableCount() {
		return connections.size();
	}
	@Override
	public void setTableAddress(int index, String host, String port) {
		if (index < 0 || index >= connections.size()) return;
		TableConnection c = connections.get(index);
		c.setServerAddress(host);
		c.setServerPort(port);
		tableModel.fireTableRowsUpdated(index, index);
	}
	@Override
	public void ensureTableCount(int minCount) {
		while (connections.size() < minCount) {
			addRow();
		}
	}
	@Override
	public void addMessage(String message) {
		mdlMessageHistory.addElement(message);
		lstMessageHistory.ensureIndexIsVisible(mdlMessageHistory.getSize() - 1);
	}
	@Override
	public void saveAssignments() {
		save();
	}

	// --- Search ---
	private void search() {
		if (searching) {
			addMessage("Search already in progress."); //$NON-NLS-1$
			return;
		}
		searching = true;
		new SwingWorker<List<PicoDiscovery.PicoInfo>, Void>() {
			@Override
			protected List<PicoDiscovery.PicoInfo> doInBackground() throws Exception {
				return PicoDiscovery.discoverPicos(5051, 300, msg -> SwingUtilities.invokeLater(() -> addMessage(msg)));
			}
			@Override
			protected void done() {
				searching = false;
				List<PicoDiscovery.PicoInfo> picos;
				try {
					picos = get();
				} catch (InterruptedException | ExecutionException e) {
					logger.error("searchAutoScore call to PicoDiscovery Exception: " + e); //$NON-NLS-1$
					addMessage("Search failed: " + e); //$NON-NLS-1$
					return;
				}
				PicoSearchHelper.handleDiscoveryResult(AutoScoreTablesPanel.this, picos, AutoScoreTablesPanel.this, table::getSelectedRow);
			}
		}.execute();
	}

	// --- Apply / Save / Cancel ---
	/** Validates and persists all rows. Returns false (and shows an error dialog) if any row is invalid. */
	public boolean save() {
		if (table.isEditing()) table.getCellEditor().stopCellEditing();
		List<String> errors = validateConnections();
		if (!errors.isEmpty()) {
			JOptionPane.showMessageDialog(this, String.join("\n", errors), "Invalid Table Configuration", JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
		try {
			Settings.saveTableConnections(connections);
			takeSnapshot();
			afterSaveCallback.run();
			return true;
		} catch (IOException ex) {
			logger.error("Error saving AutoScore table connections: " + ex.getMessage()); //$NON-NLS-1$
			logger.error(ex.toString());
			return false;
		}
	}
	private List<String> validateConnections() {
		List<String> errors = new ArrayList<>();
		for (int i = 0; i < connections.size(); i++) {
			TableConnection c = connections.get(i);
			String addr = c.getServerAddress();
			if (addr == null || !InetAddresses.isInetAddress(addr)) {
				errors.add(c.getLabel() + ": invalid IP address \"" + addr + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			boolean validPort = false;
			try {
				int port = Integer.parseInt(c.getServerPort().trim());
				validPort = port > 0 && port < 65535;
			} catch (NumberFormatException | NullPointerException e) {
				validPort = false;
			}
			if (!validPort) {
				errors.add(c.getLabel() + ": invalid port \"" + c.getServerPort() + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return errors;
	}
	/** Discards in-memory edits, reloading rows from the last-saved settings. */
	public void reload() {
		if (table.isEditing()) table.getCellEditor().cancelCellEditing();
		connections.clear();
		connections.addAll(Settings.getTableConnections());
		tableModel.fireTableDataChanged();
		takeSnapshot();
	}
	private void takeSnapshot() {
		savedSignature = signature();
	}
	private boolean hasChanges() {
		return !signature().equals(savedSignature);
	}
	private String signature() {
		List<String> fields = new ArrayList<>();
		for (TableConnection c : connections) {
			fields.add(c.getLabel());
			fields.add(c.getServerAddress());
			fields.add(c.getServerPort());
			fields.add(Boolean.toString(c.isAutoConnect()));
			fields.add(Boolean.toString(c.isDetailLog()));
			fields.add(c.getCameraSource());
		}
		return String.join("|", fields); //$NON-NLS-1$
	}
	void confirmClose(Window win) {
		if (table.isEditing()) table.getCellEditor().stopCellEditing();
		if (!hasChanges()) {
			reload();
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
			if (save()) win.dispose();
		} else if (result == JOptionPane.NO_OPTION) {
			reload();
			win.dispose();
		}
	}

	// --- Status dots ---
	public void setTableConnectedProvider(IntPredicate provider) {
		this.tableConnected = provider;
		table.repaint();
	}
	public void refreshTableStatus() {
		table.repaint();
	}
	public void setAfterSaveCallback(Runnable callback) {
		this.afterSaveCallback = callback;
	}

	// --- Connect / Disconnect ---
	public void setTableConnectListener(IntConsumer listener) {
		this.connectListener = listener;
	}
	public void setTableDisconnectListener(IntConsumer listener) {
		this.disconnectListener = listener;
	}
	public void setConnectAllListener(Runnable listener) {
		this.connectAllListener = listener;
	}
	public void setDisconnectAllListener(Runnable listener) {
		this.disconnectAllListener = listener;
	}

	// --- Camera Source combo (OBS sources) ---
	// Populates the Camera Source combo with the fetched OBS source names,
	// preserving whatever is currently being typed/edited. Called by Main when
	// OBS delivers its input list (window open / Fetch Sources), same as
	// AutoScoreSettingsPanel.populateObsSources.
	public void populateObsSources(List<String> inputNames) {
		SwingUtilities.invokeLater(() -> {
			obsSourcesList = new ArrayList<>(inputNames);
			filterUpdating = true;
			try {
				String current = getCameraComboText();
				cameraSourceCombo.removeAllItems();
				inputNames.forEach(cameraSourceCombo::addItem);
				cameraSourceCombo.setSelectedItem(current);
			} finally {
				filterUpdating = false;
			}
		});
	}
	private String getCameraComboText() {
		Object item = cameraSourceCombo.getEditor().getItem();
		return item != null ? item.toString() : ""; //$NON-NLS-1$
	}
	// Filters the Camera Source dropdown to OBS sources matching the typed
	// text, mirroring AutoScoreSettingsPanel.setupComboFiltering.
	private void setupComboFiltering(JComboBox<String> combo) {
		JTextComponent editor = (JTextComponent) combo.getEditor().getEditorComponent();
		editor.getDocument().addDocumentListener(new DocumentListener() {
			private void filter() {
				if (filterUpdating) return;
				SwingUtilities.invokeLater(() -> {
					if (filterUpdating) return;
					filterUpdating = true;
					try {
						String text = editor.getText();
						int caret = Math.min(editor.getCaretPosition(), text.length());
						String lower = text.toLowerCase();
						DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
						obsSourcesList.stream()
							.filter(s -> s.toLowerCase().contains(lower))
							.forEach(model::addElement);
						combo.setModel(model);
						editor.setText(text);
						editor.setCaretPosition(caret);
						if (model.getSize() > 0 && !text.isEmpty()) {
							combo.showPopup();
						} else {
							combo.hidePopup();
						}
					} finally {
						filterUpdating = false;
					}
				});
			}
			@Override public void insertUpdate(DocumentEvent e) { filter(); }
			@Override public void removeUpdate(DocumentEvent e) { filter(); }
			@Override public void changedUpdate(DocumentEvent e) { filter(); }
		});
	}
	// Reads the typed/edited text directly from the combo's editor rather than
	// relying on JComboBox.getSelectedItem() (DefaultCellEditor's default),
	// which for an editable combo does not reliably reflect free-typed text
	// that hasn't triggered a selection event - same workaround
	// AutoScoreSettingsPanel.getCameraComboText() uses.
	private class CameraSourceCellEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;
		CameraSourceCellEditor(JComboBox<String> combo) {
			super(combo);
		}
		@Override
		public Object getCellEditorValue() {
			return getCameraComboText();
		}
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
	private class StatusCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, col);
			boolean connected = row >= 0 && row < connections.size() && tableConnected.test(row);
			label.setIcon(connected ? DOT_CONNECTED : DOT_DISCONNECTED);
			label.setText(connected ? "Connected" : "Disconnected"); //$NON-NLS-1$ //$NON-NLS-2$
			label.setHorizontalAlignment(SwingConstants.LEFT);
			return label;
		}
	}
	// A single button that toggles Connect/Disconnect based on the row's live
	// connection state (mirrors the AutoScore > Tables submenu's toggle click).
	private class ActionButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
		private static final long serialVersionUID = 1L;
		ActionButtonRenderer() { setOpaque(true); }
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			boolean connected = row >= 0 && row < connections.size() && tableConnected.test(row);
			setText(connected ? "Disconnect" : "Connect"); //$NON-NLS-1$ //$NON-NLS-2$
			return this;
		}
	}
	private class ActionButtonEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor {
		private static final long serialVersionUID = 1L;
		private final JButton button = new JButton();
		private int editingRow = -1;
		ActionButtonEditor() {
			button.addActionListener((ActionEvent e) -> {
				fireEditingStopped();
				if (editingRow < 0 || editingRow >= connections.size()) return;
				if (tableConnected.test(editingRow)) {
					disconnectListener.accept(editingRow);
				} else {
					connectListener.accept(editingRow);
				}
			});
		}
		@Override
		public Component getTableCellEditorComponent(JTable jTable, Object value, boolean isSelected, int row, int col) {
			editingRow = row;
			boolean connected = row >= 0 && row < connections.size() && tableConnected.test(row);
			button.setText(connected ? "Disconnect" : "Connect"); //$NON-NLS-1$ //$NON-NLS-2$
			return button;
		}
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}

	// --- Table model ---
	private class ConnectionsTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		@Override public int getRowCount() { return connections.size(); }
		@Override public int getColumnCount() { return COLUMN_NAMES.length; }
		@Override public String getColumnName(int col) { return COLUMN_NAMES[col]; }
		@Override
		public Class<?> getColumnClass(int col) {
			if (col == COL_AUTO_CONNECT || col == COL_DETAIL_LOG) return Boolean.class;
			return String.class;
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			return col != COL_STATUS;
		}
		@Override
		public Object getValueAt(int row, int col) {
			TableConnection c = connections.get(row);
			return switch (col) {
				case COL_LABEL -> c.getLabel();
				case COL_ADDRESS -> c.getServerAddress();
				case COL_PORT -> c.getServerPort();
				case COL_AUTO_CONNECT -> c.isAutoConnect();
				case COL_DETAIL_LOG -> c.isDetailLog();
				case COL_CAMERA_SOURCE -> c.getCameraSource();
				case COL_STATUS -> ""; //$NON-NLS-1$
				case COL_ACTION -> ""; //$NON-NLS-1$
				default -> null;
			};
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
			TableConnection c = connections.get(row);
			switch (col) {
				case COL_LABEL -> c.setLabel((String) value);
				case COL_ADDRESS -> c.setServerAddress((String) value);
				case COL_PORT -> c.setServerPort((String) value);
				case COL_AUTO_CONNECT -> c.setAutoConnect((Boolean) value);
				case COL_DETAIL_LOG -> c.setDetailLog((Boolean) value);
				case COL_CAMERA_SOURCE -> c.setCameraSource((String) value);
				default -> { /* Status column is read-only */ }
			}
			fireTableCellUpdated(row, col);
		}
	}

}
