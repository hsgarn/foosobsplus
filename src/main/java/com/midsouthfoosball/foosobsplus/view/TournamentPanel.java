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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public final class TournamentPanel extends JPanel {
	private final JLabel lblTournamentName;
	private final JLabel lblEventName;
	private final JLabel lblTableName;
	private final JTextField txtTournamentName;
	private final JTextField txtEventName;
	// Table Name is a combo box: its dropdown lists the configured tables (so the
	// displayed table can be changed here), and it is editable so the active
	// table can be renamed. Tournament/event names remain plain text fields.
	private final JComboBox<String> cmbTableName;
	private final DefaultComboBoxModel<String> mdlTableName;
	private final JButton btnClear;
	private final Border innerBorder;
	// Index of the active (displayed) table, used to tell a table switch (picking a
	// different existing entry) apart from a rename (editing the current entry).
	private int activeTableIndex = 0;
	// Guards programmatic combo updates so they don't fire select/rename callbacks.
	private boolean updatingTables = false;
	private IntConsumer tableSelectListener;
	private Consumer<String> tableRenameListener;
	public TournamentPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 225;
		setPreferredSize(dim);
		setName(buildTitle());
		lblTournamentName = new JLabel(Messages.getString("TournamentPanel.TournamentName")); //$NON-NLS-1$
		lblEventName = new JLabel(Messages.getString("TournamentPanel.EventName")); //$NON-NLS-1$
		lblTableName = new JLabel(Messages.getString("TournamentPanel.TableName")); //$NON-NLS-1$
		txtTournamentName = new JTextField(50);
		txtEventName = new JTextField(50);
		mdlTableName = new DefaultComboBoxModel<>();
		cmbTableName = new JComboBox<>(mdlTableName);
		cmbTableName.setEditable(true);
		cmbTableName.addActionListener(this::onTableComboAction);
		cmbTableName.getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
			@Override public void focusLost(FocusEvent e) { commitTableRename(); }
		});
		btnClear = new JButton(Messages.getString("TournamentPanel.Clear")); //$NON-NLS-1$
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		////////  Tournament Name ////////
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.LINE_START;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 5, 5, 5);
		add(lblTournamentName, gc);
		gc.weightx = .5;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(txtTournamentName, gc);
		////////  Event Name ////////
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.LINE_START;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 5, 5, 5);
		add(lblEventName, gc);
		gc.weightx = .5;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(txtEventName, gc);
		////////  Table Name ////////
		gc.gridy++;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.LINE_START;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 5, 5, 5);
		add(lblTableName, gc);
		gc.weightx = .5;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(cmbTableName, gc);
		/////// Clear button ////////
		gc.gridy++;
		gc.weightx = .5;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(btnClear, gc);
	}
	////// Listeners  //////
	public void addTournamentNameListener(ActionListener listenForTxtTournamentName) {
		txtTournamentName.addActionListener(listenForTxtTournamentName);
	}
	public void addTournamentNameFocusListener(FocusListener focusListenerForTxtTournamentName) {
		txtTournamentName.addFocusListener(focusListenerForTxtTournamentName);
	}
	public void addTournamentNameMouseListener(MouseListener mouseListenerForTxtTournamentName) {
		txtTournamentName.addMouseListener(mouseListenerForTxtTournamentName);
	}
	public void addEventNameListener(ActionListener listenForTxtEventName) {
		txtEventName.addActionListener(listenForTxtEventName);
	}
	public void addEventNameFocusListener(FocusListener focusListenerForTxtEventName) {
		txtEventName.addFocusListener(focusListenerForTxtEventName);
	}
	public void addEventNameMouseListener(MouseListener mouseListenerForTxtEventName) {
		txtEventName.addMouseListener(mouseListenerForTxtEventName);
	}
	// Fired with the table index when the user picks a different table from the
	// Table Name dropdown (i.e. switches the displayed table).
	public void addTableSelectListener(IntConsumer listener) {
		this.tableSelectListener = listener;
	}
	// Fired with the new name when the user edits the active table's name.
	public void addTableRenameListener(Consumer<String> listener) {
		this.tableRenameListener = listener;
	}
	public void addClearListener(ActionListener listenForBtnClear) {
		btnClear.addActionListener(listenForBtnClear);
	}
	////// Combo handling //////
	private void onTableComboAction(ActionEvent e) {
		if (updatingTables) return;
		int idx = cmbTableName.getSelectedIndex();
		String text = getTableEditorText();
		if (idx >= 0 && idx != activeTableIndex && text.equals(mdlTableName.getElementAt(idx))) {
			if (tableSelectListener != null) tableSelectListener.accept(idx);
		} else {
			commitTableRename();
		}
	}
	private void commitTableRename() {
		if (updatingTables) return;
		String text = getTableEditorText();
		if (!text.isEmpty() && tableRenameListener != null) {
			tableRenameListener.accept(text);
		}
	}
	private String getTableEditorText() {
		Object item = cmbTableName.getEditor().getItem();
		return item == null ? "" : item.toString().trim(); //$NON-NLS-1$
	}
	////// Utility Methods //////
	public void updateTournamentName(String tournamentName) {
		txtTournamentName.setText(tournamentName);
	}
	public void updateEventName(String eventName) {
		txtEventName.setText(eventName);
	}
	// Rebuilds the Table Name dropdown from the given per-table names and selects
	// the active table. Guarded so it does not fire the select/rename callbacks.
	public void setTableNames(List<String> names, int activeIndex) {
		updatingTables = true;
		mdlTableName.removeAllElements();
		for (String name : names) {
			mdlTableName.addElement(name);
		}
		activeTableIndex = activeIndex;
		if (activeIndex >= 0 && activeIndex < names.size()) {
			cmbTableName.setSelectedIndex(activeIndex);
		}
		updatingTables = false;
	}
	public void selectTournamentName() {
		txtTournamentName.selectAll();
	}
	public void selectEventName() {
		txtEventName.selectAll();
	}
	public void clearAllFields() {
		updateTournamentName(""); //$NON-NLS-1$
		updateEventName(""); //$NON-NLS-1$
	}
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("TournamentPanel.TournamentInformation"); //$NON-NLS-1$
	}

}
