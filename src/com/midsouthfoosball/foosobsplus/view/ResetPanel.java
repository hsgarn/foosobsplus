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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class ResetPanel extends JPanel {
	private JButton btnResetNames;
	private JButton btnResetScores;
	private JButton btnResetGameCounts;
	private JButton btnResetMatchCounts;
	private JButton btnResetTimeOuts;
	private JButton btnResetResetWarns;
	private JButton btnResetAll;
	private Border innerBorder;
	public ResetPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 300;
		setPreferredSize(dim);
		setName(buildTitle());
		btnResetNames = new JButton(Messages.getString("ResetPanel.ResetNames", Settings.getGameType())); //$NON-NLS-1$
		btnResetScores = new JButton(Messages.getString("ResetPanel.ResetScores", Settings.getGameType())); //$NON-NLS-1$
		btnResetGameCounts = new JButton(Messages.getString("ResetPanel.ResetGameCounts", Settings.getGameType())); //$NON-NLS-1$
		btnResetMatchCounts = new JButton(Messages.getString("ResetPanel.ResetMatchCounts", Settings.getGameType())); //$NON-NLS-1$
		btnResetTimeOuts = new JButton(Messages.getString("ResetPanel.ResetTimeOuts", Settings.getGameType())); //$NON-NLS-1$
		btnResetResetWarns = new JButton(Messages.getString("ResetPanel.ResetResetWarns", Settings.getGameType())); //$NON-NLS-1$
		btnResetAll = new JButton(Messages.getString("ResetPanel.ResetAll", Settings.getGameType())); //$NON-NLS-1$
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle()); //$NON-NLS-1$
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Reset Names ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetNames, gc);
		
		//////// Reset Scores ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetScores, gc);
		
		//////// Reset Game Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetGameCounts, gc);
		
		//////// Reset Match Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetMatchCounts, gc);
		
		//////// Reset Time Out Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetTimeOuts, gc);
		
		//////// Reset Reset Warns ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetResetWarns, gc);

		//////// Reset All ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnResetAll, gc);
	}
	private void setMnemonics() {
		if(Settings.getHotKeyParameter("ResetNamesHotKey").isEmpty()) {
			btnResetNames.setMnemonic(-1);
		} else {
			btnResetNames.setMnemonic(Settings.getHotKeyParameter("ResetNamesHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetScoresHotKey").isEmpty()) {
			btnResetScores.setMnemonic(-1);
		} else {
			btnResetScores.setMnemonic(Settings.getHotKeyParameter("ResetScoresHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetGameCountsHotKey").isEmpty()) {
			btnResetGameCounts.setMnemonic(-1);
		} else {
			btnResetGameCounts.setMnemonic(Settings.getHotKeyParameter("ResetGameCountsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetMatchCountsHotKey").isEmpty()) {
			btnResetMatchCounts.setMnemonic(-1);
		} else {
			btnResetMatchCounts.setMnemonic(Settings.getHotKeyParameter("ResetMatchCountsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetTimeOutsHotKey").isEmpty()) {
			btnResetTimeOuts.setMnemonic(-1);
		} else {
			btnResetTimeOuts.setMnemonic(Settings.getHotKeyParameter("ResetTimeOutsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetResetWarnHotKey").isEmpty()) {
			btnResetResetWarns.setMnemonic(-1);
		} else {
			btnResetResetWarns.setMnemonic(Settings.getHotKeyParameter("ResetResetWarnHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetAllHotKey").isEmpty()) {
			btnResetAll.setMnemonic(-1);
		} else {
			btnResetAll.setMnemonic(Settings.getHotKeyParameter("ResetAllHotKey").charAt(0));
		};
	}
	public void updateMnemonics() {
		setMnemonics();
	}
	////// Listeners //////
	public void addResetNamesListener(ActionListener listenForBtnResetNames) {
		btnResetNames.addActionListener(listenForBtnResetNames);
	}
	public void addResetScoresListener(ActionListener listenForBtnResetScores) {
		btnResetScores.addActionListener(listenForBtnResetScores);
	}
	public void addResetGameCountsListener(ActionListener listenForBtnResetGameCounts) {
		btnResetGameCounts.addActionListener(listenForBtnResetGameCounts);
	}
	public void addResetMatchCountsListener(ActionListener listenForBtnResetMatchCounts) {
		btnResetMatchCounts.addActionListener(listenForBtnResetMatchCounts);
	}
	public void addResetTimeOutsListener(ActionListener listenForBtnResetTimeOuts) {
		btnResetTimeOuts.addActionListener(listenForBtnResetTimeOuts);
	}
	public void addResetResetWarnsListener(ActionListener listenForBtnResetResetWarns) {
		btnResetResetWarns.addActionListener(listenForBtnResetResetWarns);
	}
	public void addResetAllListener(ActionListener listenForBtnResetAll) {
		btnResetAll.addActionListener(listenForBtnResetAll);
	}
	
	////// Utility Methods \\\\\\
	
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}

	private String buildTitle() {
		return Messages.getString("ResetPanel.ResetPanel", Settings.getGameType()); //$NON-NLS-1$
	}

}
