/**
Copyright 2020-2024 Hugh Garner
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
	private JButton btnSwitchTimeOuts;
	private JButton btnSwitchResetWarns;
	private JButton btnClearAll;
	private JLabel lblLastScored;
	private Settings settings;
	private Border innerBorder;

	public SwitchPanel(Settings settings) {
		
		this.settings = settings;
		
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 300;
		setPreferredSize(dim);
		setName(buildTitle());
		
		btnSwitchSides = new JButton(Messages.getString("SwitchPanel.SwitchSides", settings.getGameType())); //$NON-NLS-1$
		btnSwitchTeams = new JButton(Messages.getString("SwitchPanel.SwitchTeams", settings.getGameType())); //$NON-NLS-1$
		btnSwitchForwards = new JButton(Messages.getString("SwitchPanel.SwitchPlayer1", settings.getGameType()));
		btnSwitchGoalies = new JButton(Messages.getString("SwitchPanel.SwitchPlayer2", settings.getGameType()));
		btnSwitchScores = new JButton(Messages.getString("SwitchPanel.SwitchScores", settings.getGameType())); //$NON-NLS-1$
		btnSwitchGameCounts = new JButton(Messages.getString("SwitchPanel.SwitchGameCounts", settings.getGameType())); //$NON-NLS-1$
		btnSwitchTimeOuts = new JButton(Messages.getString("SwitchPanel.SwitchTimeOuts", settings.getGameType())); //$NON-NLS-1$
		btnSwitchResetWarns = new JButton(Messages.getString("SwitchPanel.SwitchResetWarns", settings.getGameType())); //$NON-NLS-1$
		btnClearAll = new JButton(Messages.getString("SwitchPanel.ClearAll", settings.getGameType())); //$NON-NLS-1$
		lblLastScored = new JLabel(Messages.getString("SwitchPanel.LastScored", settings.getGameType())); //$NON-NLS-1$
		lblLastScored.setBorder(BorderFactory.createLoweredBevelBorder());
		
		setMnemonics();

		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(settings.getBorderTop(),settings.getBorderLeft(),settings.getBorderBottom(),settings.getBorderRight());
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
		if(settings.getSwitchSidesHotKey().isEmpty()) {
			btnSwitchSides.setMnemonic(-1);
		} else {
			btnSwitchSides.setMnemonic(settings.getSwitchSidesHotKey().charAt(0));
		};
		if(settings.getSwitchTeamsHotKey().isEmpty()) {
			btnSwitchTeams.setMnemonic(-1);
		} else {
			btnSwitchTeams.setMnemonic(settings.getSwitchTeamsHotKey().charAt(0));
		};
		if(settings.getSwitchForwardsHotKey().isEmpty()) {
			btnSwitchForwards.setMnemonic(-1);
		} else {
			btnSwitchForwards.setMnemonic(settings.getSwitchForwardsHotKey().charAt(0));
		};
		if(settings.getSwitchGoaliesHotKey().isEmpty()) {
			btnSwitchGoalies.setMnemonic(-1);
		} else {
			btnSwitchGoalies.setMnemonic(settings.getSwitchGoaliesHotKey().charAt(0));
		};
		if(settings.getSwitchScoresHotKey().isEmpty()) {
			btnSwitchScores.setMnemonic(-1);
		} else {
			btnSwitchScores.setMnemonic(settings.getSwitchScoresHotKey().charAt(0));
		};
		if(settings.getSwitchGameCountsHotKey().isEmpty()) {
			btnSwitchGameCounts.setMnemonic(-1);
		} else {
			btnSwitchGameCounts.setMnemonic(settings.getSwitchGameCountsHotKey().charAt(0));
		};
		if(settings.getSwitchTimeOutsHotKey().isEmpty()) {
			btnSwitchTimeOuts.setMnemonic(-1);
		} else {
			btnSwitchTimeOuts.setMnemonic(settings.getSwitchTimeOutsHotKey().charAt(0));
		};
		if(settings.getSwitchResetWarnsHotKey().isEmpty()) {
			btnSwitchResetWarns.setMnemonic(-1);
		} else {
			btnSwitchResetWarns.setMnemonic(settings.getSwitchResetWarnsHotKey().charAt(0));
		};
		if(settings.getClearAllHotKey().isEmpty()) {
			btnClearAll.setMnemonic(-1);
		} else {
			btnClearAll.setMnemonic(settings.getClearAllHotKey().charAt(0));
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
		return Messages.getString("SwitchPanel.SwitchPanel", settings.getGameType()); //$NON-NLS-1$
	}

}
