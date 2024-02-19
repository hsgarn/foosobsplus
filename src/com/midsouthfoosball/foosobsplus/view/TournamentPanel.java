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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public final class TournamentPanel extends JPanel {
	private JLabel lblTournamentName;
	private JLabel lblEventName;
	private JLabel lblTableName;
	private JTextField txtTournamentName;
	private JTextField txtEventName;
	private JTextField txtTableName;
	private JButton btnClear;
	private Border innerBorder;
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
		txtTableName = new JTextField(50);
		btnClear = new JButton(Messages.getString("TournamentPanel.Clear")); //$NON-NLS-1$
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	private final void layoutComponents() {
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
		////////  Tournament Name ////////
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
		add(txtTableName, gc);
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
	public void addTableNameListener(ActionListener listenForTxtTableName) {
		txtTableName.addActionListener(listenForTxtTableName);
	}
	public void addTableNameFocusListener(FocusListener focusListenerForTxtTableName) {
		txtTableName.addFocusListener(focusListenerForTxtTableName);
	}
	public void addTableNameMouseListener(MouseListener mouseListenerForTxtTableName) {
		txtTableName.addMouseListener(mouseListenerForTxtTableName);
	}	
	public void addClearListener(ActionListener listenForBtnClear) {
		btnClear.addActionListener(listenForBtnClear);
	}
	////// Utility Methods //////
	public void updateTournamentName(String tournamentName) {
		txtTournamentName.setText(tournamentName);
	}
	public void updateEventName(String eventName) {
		txtEventName.setText(eventName);
	}
	public void updateTableName(String tableName) {
		txtTableName.setText(tableName);
	}
	public void selectTournamentName() {
		txtTournamentName.selectAll();
	}
	public void selectEventName() {
		txtEventName.selectAll();
	}
	public void selectTableName() {
		txtTableName.selectAll();
	}
	public void clearAllFields() {
		updateTournamentName(""); //$NON-NLS-1$
		updateEventName(""); //$NON-NLS-1$
		updateTableName(""); //$NON-NLS-1$
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
