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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class SwitchPanel extends JPanel {
	
	private JButton btnSwitchSides;
	private JButton btnSwitchTeams;
	private JButton btnSwitchForwards;
	private JButton btnSwitchGoalies;
	private JButton btnSwitchScores;
	private JButton btnSwitchGameCounts;
	private JButton btnSwitchMatchCounts;
	private JButton btnSwitchTimeOuts;
	private JButton btnSwitchResetWarns;
	private JButton btnClearAll;
	private JLabel lblLastScored;
	private Border innerBorder;
	public SwitchPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 300;
		setPreferredSize(dim);
		setName(buildTitle());
		btnSwitchSides = new JButton(Messages.getString("SwitchPanel.SwitchSides", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchTeams = new JButton(Messages.getString("SwitchPanel.SwitchTeams", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchForwards = new JButton(Messages.getString("SwitchPanel.SwitchPlayer1", Settings.getGameType()));
		btnSwitchGoalies = new JButton(Messages.getString("SwitchPanel.SwitchPlayer2", Settings.getGameType()));
		btnSwitchScores = new JButton(Messages.getString("SwitchPanel.SwitchScores", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchGameCounts = new JButton(Messages.getString("SwitchPanel.SwitchGameCounts", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchMatchCounts = new JButton(Messages.getString("SwitchPanel.SwitchMatchCounts", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchTimeOuts = new JButton(Messages.getString("SwitchPanel.SwitchTimeOuts", Settings.getGameType())); //$NON-NLS-1$
		btnSwitchResetWarns = new JButton(Messages.getString("SwitchPanel.SwitchResetWarns", Settings.getGameType())); //$NON-NLS-1$
		btnClearAll = new JButton(Messages.getString("SwitchPanel.ClearAll", Settings.getGameType())); //$NON-NLS-1$
		lblLastScored = new JLabel(Messages.getString("SwitchPanel.LastScored", Settings.getGameType())); //$NON-NLS-1$
		lblLastScored.setBorder(BorderFactory.createLoweredBevelBorder());
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Switch Sides ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchSides, gc);
		
		//////// Switch Teams ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchTeams, gc);
		
		//////// Switch Player1 ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchForwards, gc);
		
		//////// Switch Player2 ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchGoalies, gc);
		
		//////// Switch Scores ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchScores, gc);
		
		//////// Switch Game Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchGameCounts, gc);
		
		//////// Switch Match Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchMatchCounts, gc);
		
		//////// Switch Time Out Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchTimeOuts, gc);
		
		//////// Switch Reset Warns ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnSwitchResetWarns, gc);

		//////// Clear All ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .5;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnClearAll, gc);

		//////// Last Scored ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblLastScored, gc);
	}

	private void setMnemonics() {
		if(Settings.getHotKeyParameter("SwitchSidesHotKey").isEmpty()) {
			btnSwitchSides.setMnemonic(-1);
		} else {
			btnSwitchSides.setMnemonic(Settings.getHotKeyParameter("SwitchSidesHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchTeamsHotKey").isEmpty()) {
			btnSwitchTeams.setMnemonic(-1);
		} else {
			btnSwitchTeams.setMnemonic(Settings.getHotKeyParameter("SwitchTeamsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchForwardsHotKey").isEmpty()) {
			btnSwitchForwards.setMnemonic(-1);
		} else {
			btnSwitchForwards.setMnemonic(Settings.getHotKeyParameter("SwitchForwardsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchGoaliesHotKey").isEmpty()) {
			btnSwitchGoalies.setMnemonic(-1);
		} else {
			btnSwitchGoalies.setMnemonic(Settings.getHotKeyParameter("SwitchGoaliesHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchScoresHotKey").isEmpty()) {
			btnSwitchScores.setMnemonic(-1);
		} else {
			btnSwitchScores.setMnemonic(Settings.getHotKeyParameter("SwitchScoresHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchGameCountsHotKey").isEmpty()) {
			btnSwitchGameCounts.setMnemonic(-1);
		} else {
			btnSwitchGameCounts.setMnemonic(Settings.getHotKeyParameter("SwitchGameCountsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchMatchCountsHotKey").isEmpty()) {
			btnSwitchMatchCounts.setMnemonic(-1);
		} else {
			btnSwitchMatchCounts.setMnemonic(Settings.getHotKeyParameter("SwitchMatchCountsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchTimeOutsHotKey").isEmpty()) {
			btnSwitchTimeOuts.setMnemonic(-1);
		} else {
			btnSwitchTimeOuts.setMnemonic(Settings.getHotKeyParameter("SwitchTimeOutsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("SwitchResetWarnsHotKey").isEmpty()) {
			btnSwitchResetWarns.setMnemonic(-1);
		} else {
			btnSwitchResetWarns.setMnemonic(Settings.getHotKeyParameter("SwitchResetWarnsHotKey").charAt(0));
		};
		if(Settings.getHotKeyParameter("ClearAllHotKey").isEmpty()) {
			btnClearAll.setMnemonic(-1);
		} else {
			btnClearAll.setMnemonic(Settings.getHotKeyParameter("ClearAllHotKey").charAt(0));
		};
	}
	////// Listeners \\\\\\
	public void addSwitchSidesListener(ActionListener listenForBtnSwitchSides) {
		btnSwitchSides.addActionListener(listenForBtnSwitchSides);
	}
	public void addSwitchTeamsListener(ActionListener listenForBtnSwitchTeams) {
		btnSwitchTeams.addActionListener(listenForBtnSwitchTeams);
	}
	public void addSwitchPlayer1Listener(ActionListener listenForBtnSwitchPlayer1) {
		btnSwitchForwards.addActionListener(listenForBtnSwitchPlayer1);
	}
	public void addSwitchPlayer2Listener(ActionListener listenForBtnSwitchPlayer2) {
		btnSwitchGoalies.addActionListener(listenForBtnSwitchPlayer2);
	}
	public void addSwitchScoresListener(ActionListener listenForBtnSwitchScores) {
		btnSwitchScores.addActionListener(listenForBtnSwitchScores);
	}
	public void addSwitchGameCountsListener(ActionListener listenForBtnSwitchGameCounts) {
		btnSwitchGameCounts.addActionListener(listenForBtnSwitchGameCounts);
	}
	public void addSwitchMatchCountsListener(ActionListener listenForBtnSwitchMatchCounts) {
		btnSwitchMatchCounts.addActionListener(listenForBtnSwitchMatchCounts);
	}
	public void addSwitchTimeOutsListener(ActionListener listenForBtnSwitchTimeOuts) {
		btnSwitchTimeOuts.addActionListener(listenForBtnSwitchTimeOuts);
	}
	public void addSwitchResetWarnsListener(ActionListener listenForBtnSwitchResetWarns) {
		btnSwitchResetWarns.addActionListener(listenForBtnSwitchResetWarns);
	}
	public void addClearAllListener(ActionListener listenForBtnClearAll) {
		btnClearAll.addActionListener(listenForBtnClearAll);
	}
	////// Utility Methods \\\\\\
	public void setLastScored(String txt) {
		lblLastScored.setText(txt);
	}
	public void updateMnemonics() {
		setMnemonics();
	}
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("SwitchPanel.SwitchPanel", Settings.getGameType()); //$NON-NLS-1$
	}
}
