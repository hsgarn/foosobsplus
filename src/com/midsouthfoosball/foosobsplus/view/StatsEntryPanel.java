/**
Copyright 2020 Hugh Garner
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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class StatsEntryPanel extends JPanel {
	private JLabel lblCode;
	private JTextField txtCode;
	private JLabel lblCodeHistory;
	private JList<String> lstCodeHistory;
	private DefaultListModel<String> mdlCodeHistory;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnClear;
	private JScrollPane scrCodeHistory;

	public StatsEntryPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		lblCode = new JLabel("Code:");
		lblCodeHistory = new JLabel("History:");
		txtCode = new JTextField();
		mdlCodeHistory = new DefaultListModel<String>();
		lstCodeHistory = new JList<String>(mdlCodeHistory);
		btnUndo = new JButton("Undo");
		btnRedo = new JButton("Redo");
		btnClear = new JButton("Clear");
		scrCodeHistory = new JScrollPane();
		scrCodeHistory.setViewportView(lstCodeHistory);
		lstCodeHistory.setLayoutOrientation(JList.VERTICAL);

		Border innerBorder = BorderFactory.createTitledBorder("Statistics Entry Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();

	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblCode, gc);
		
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 1;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblCodeHistory, gc);
		
		//////// Statistics Window ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 5);
		add(txtCode, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.gridheight=4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(scrCodeHistory, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnUndo, gc);

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnClear, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnRedo, gc);

	}

	////// Listeners  //////
	
	public void addCodeListener(ActionListener listenForTxtCode) {
		txtCode.addActionListener(listenForTxtCode);
	}
	public void addStatsClearListener(ActionListener listenForBtnClear) {
		btnClear.addActionListener(listenForBtnClear);
	}
	public void addUndoListener(ActionListener listenForBtnUndo) {
		btnUndo.addActionListener(listenForBtnUndo);
	}
	public void addRedoListener(ActionListener listenForBtnRedo) {
		btnRedo.addActionListener(listenForBtnRedo);
	}

	////// Utility Methods //////
	
	public void updateCode(String code) {
		txtCode.setText(code);
	}
	public void updateCodeHistory(String code) {
		mdlCodeHistory.addElement(code);
	}
	public void removeCodeHistory() {
		mdlCodeHistory.removeElement(mdlCodeHistory.lastElement());
	}
	public void selectCode() {
		txtCode.selectAll();
	}
	public void clearAll() {
		updateCode("");
		mdlCodeHistory.clear();
	}
	public String getCode() {
		return txtCode.getText();
	}

}
