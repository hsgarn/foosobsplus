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
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class TeamPanel extends JPanel {
	
	private JLabel lblTeamName;
	private JLabel lblForwardName;
	private JLabel lblGoalieName;
	private JButton btnSwitchPositions;
	private JLabel lblScore;
	private JLabel lblGameCount;
	private JLabel lblTimeOutCount;
	private JTextField txtTeamName;
	private JTextField txtForwardName;
	private JTextField txtGoalieName;
	private JTextField txtScore;
	private JTextField txtGameCount;
	private JTextField txtTimeOutCount;
	private JButton btnScoreIncrease;
	private JButton btnScoreDecrease;
	private JButton btnGameCountIncrease;
	private JButton btnGameCountDecrease;
	private JButton btnTimeOutCountIncrease;
	private JButton btnTimeOutCountDecrease;
	private JToggleButton btnReset;
	private JToggleButton btnWarn;
	private JButton btnClear;
	private Settings settings;
	
	public TeamPanel(int teamNbr, String teamColor, Settings settings) {
		
		this.settings = settings;

		initializeTeamPanel(teamNbr, teamColor);
		
		layoutComponents();
		
//		setTitle("check it out");
	}

	private void initializeTeamPanel(int teamNbr, String teamColor) {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		lblTeamName = new JLabel("Team Name: ");
		txtTeamName = new JTextField(50);
		txtTeamName.setName("Team " + Integer.toString(teamNbr));
		lblForwardName = new JLabel("Forward Name: ");
		txtForwardName = new JTextField(50);
		txtForwardName.setName("Team " + Integer.toString(teamNbr));
		lblGoalieName = new JLabel("Goalie Name: ");
		txtGoalieName = new JTextField(50);
		txtGoalieName.setName("Team " + Integer.toString(teamNbr));
		btnSwitchPositions = new JButton("Switch Positions");
		btnSwitchPositions.setName("Team " + Integer.toString(teamNbr));
		lblScore = new JLabel("Score: ");
		btnScoreDecrease = new JButton("-");
		btnScoreDecrease.setName("Team " + Integer.toString(teamNbr));
		txtScore = new JTextField(3);
		txtScore.setHorizontalAlignment(JTextField.CENTER);
		txtScore.setName("Team " + Integer.toString(teamNbr));
		btnScoreIncrease = new JButton("+");
		btnScoreIncrease.setName("Team " + Integer.toString(teamNbr));
		lblGameCount = new JLabel("Game Count: ");
		btnGameCountDecrease = new JButton("-");
		btnGameCountDecrease.setName("Team " + Integer.toString(teamNbr));
		txtGameCount = new JTextField(3);
		txtGameCount.setHorizontalAlignment(JTextField.CENTER);
		txtGameCount.setName("Team " + Integer.toString(teamNbr));
		btnGameCountIncrease = new JButton("+");
		btnGameCountIncrease.setName("Team " + Integer.toString(teamNbr));
		lblTimeOutCount = new JLabel("Time Out Count: ");
		btnTimeOutCountDecrease = new JButton("Return TO");
		btnTimeOutCountDecrease.setName("Team " + Integer.toString(teamNbr));
		txtTimeOutCount = new JTextField(3);
		txtTimeOutCount.setHorizontalAlignment(JTextField.CENTER);
		txtTimeOutCount.setName("Team " + Integer.toString(teamNbr));
		btnTimeOutCountIncrease = new JButton("Use TO");
		btnTimeOutCountIncrease.setName("Team " + Integer.toString(teamNbr));
		btnReset = new JToggleButton("Reset");
		btnReset.setName("Team " + Integer.toString(teamNbr));
		btnWarn = new JToggleButton(" Warn ");
		btnWarn.setName("Team " + Integer.toString(teamNbr));
		btnClear = new JButton("Clear");
		btnClear.setName("Team " + Integer.toString(teamNbr));
		
		setMnemonics(teamNbr);

		Border innerBorder = BorderFactory.createTitledBorder("Team " + teamNbr + " Information (" + teamColor + " side)");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

	}
	private void setMnemonics(int teamNbr) {
		if (teamNbr == 1) {
			if(settings.getTeam1ClearHotKey().isEmpty()) {
				btnClear.setMnemonic(-1);
			} else {
				btnClear.setMnemonic(settings.getTeam1ClearHotKey().charAt(0));
			};
			if(settings.getTeam1SwitchPositionsHotKey().isEmpty()) {
				btnSwitchPositions.setMnemonic(-1);
			} else {
				btnSwitchPositions.setMnemonic(settings.getTeam1SwitchPositionsHotKey().charAt(0));
			};
			if(settings.getScore1PlusHotKey().isEmpty()) {
				btnScoreIncrease.setMnemonic(-1);
			} else {
				btnScoreIncrease.setMnemonic(settings.getScore1PlusHotKey().charAt(0));
			};
		} else {
			if(settings.getTeam2ClearHotKey().isEmpty()) {
				btnClear.setMnemonic(-1);
			} else {
				btnClear.setMnemonic(settings.getTeam2ClearHotKey().charAt(0));
			};
			if(settings.getTeam2SwitchPositionsHotKey().isEmpty()) {
				btnSwitchPositions.setMnemonic(-1);
			} else {
				btnSwitchPositions.setMnemonic(settings.getTeam2SwitchPositionsHotKey().charAt(0));
			};
			if(settings.getScore2PlusHotKey().isEmpty()) {
				btnScoreIncrease.setMnemonic(-1);
			} else {
				btnScoreIncrease.setMnemonic(settings.getScore2PlusHotKey().charAt(0));
			};
		}
	}
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Team Name ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeamName, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtTeamName, gc);
		gc.gridwidth = 1;
		
		//////// Forward Name ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblForwardName, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtForwardName, gc);
		
		///////// Switch Positions //////
		gc.gridx = 2;
		gc.gridheight = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.VERTICAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnSwitchPositions, gc);
		gc.gridheight = 1;
		
		
		//////// Goalie Name ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblGoalieName, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtGoalieName, gc);
		
		//////// Score ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblScore, gc);
		
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(btnScoreDecrease, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		add(txtScore, gc);

		gc.gridx = 2;
		gc.insets = new Insets(0, 10, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnScoreIncrease, gc);

		//////// Game Count ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblGameCount, gc);
		
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(btnGameCountDecrease, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtGameCount, gc);

		gc.gridx = 2;
		gc.insets = new Insets(0, 10, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnGameCountIncrease, gc);

		//////// Time Out Count ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTimeOutCount, gc);
		
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(btnTimeOutCountDecrease, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtTimeOutCount, gc);

		gc.gridx = 2;
		gc.insets = new Insets(0, 10, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnTimeOutCountIncrease, gc);

		//////// Reset & Warn ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 0;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(btnReset, gc);
		
		gc.gridx = 2;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnWarn, gc);
		
		////////// Clear Team ////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		add(btnClear, gc);
	}
	
	////// Listener Methods //////
	public void addTeamNameListener(ActionListener listenForTxtTeamName) {
		txtTeamName.addActionListener(listenForTxtTeamName);
	}
	public void addTeamNameFocusListener(FocusListener focusListenerForTxtTeamName) {
		txtTeamName.addFocusListener(focusListenerForTxtTeamName);
	}
	public void addTeamNameMouseListener(MouseListener mouseListenerForTxtTeamName) {
		txtTeamName.addMouseListener(mouseListenerForTxtTeamName);
	}	
	public void addForwardNameListener(ActionListener listenForTxtForwardName) {
		txtForwardName.addActionListener(listenForTxtForwardName);
	}
	public void addForwardNameFocusListener(FocusListener focusListenerForTxtForwardName) {
		txtForwardName.addFocusListener(focusListenerForTxtForwardName);
	}
	public void addForwardNameMouseListener(MouseListener mouseListenerForTxtForwardName) {
		txtForwardName.addMouseListener(mouseListenerForTxtForwardName);
	}	
	public void addGoalieNameListener(ActionListener listenForTxtGoalieName) {
		txtGoalieName.addActionListener(listenForTxtGoalieName);
	}
	public void addGoalieNameFocusListener(FocusListener focusListenerForTxtGoalieName) {
		txtGoalieName.addFocusListener(focusListenerForTxtGoalieName);
	}
	public void addGoalieNameMouseListener(MouseListener mouseListenerForTxtGoalieName) {
		txtGoalieName.addMouseListener(mouseListenerForTxtGoalieName);
	}	
	public void addSwitchPositionsListener(ActionListener listenForBtnSwitchPositions) {
		btnSwitchPositions.addActionListener(listenForBtnSwitchPositions);
	}
	public void addScoreDecreaseListener(ActionListener listenForBtnScoreDecrease) {
		btnScoreDecrease.addActionListener(listenForBtnScoreDecrease);
	}
	public void addScoreListener(ActionListener listenForTxtScore) {
		txtScore.addActionListener(listenForTxtScore);
	}
	public void addScoreFocusListener(FocusListener focusListenerForTxtScore) {
		txtScore.addFocusListener(focusListenerForTxtScore);
	}
	public void addScoreIncreaseListener(ActionListener listenForBtnScoreIncrease) {
		btnScoreIncrease.addActionListener(listenForBtnScoreIncrease);
	}
	public void addGameCountDecreaseListener(ActionListener listenForBtnGameCountDecrease) {
		btnGameCountDecrease.addActionListener(listenForBtnGameCountDecrease);
	}
	public void addGameCountListener(ActionListener listenForTxtGameCount) {
		txtGameCount.addActionListener(listenForTxtGameCount);
	}
	public void addGameCountFocusListener(FocusListener focusListenerForTxtGameCount) {
		txtGameCount.addFocusListener(focusListenerForTxtGameCount);
	}
	public void addGameCountIncreaseListener(ActionListener listenForBtnGameCountIncrease) {
		btnGameCountIncrease.addActionListener(listenForBtnGameCountIncrease);
	}
	public void addTimeOutCountDecreaseListener(ActionListener listenForBtnTimeOutCountDecrease) {
		btnTimeOutCountDecrease.addActionListener(listenForBtnTimeOutCountDecrease);
	}
	public void addTimeOutCountListener(ActionListener listenForTxtTimeOutCount) {
		txtTimeOutCount.addActionListener(listenForTxtTimeOutCount);
	}
	public void addTimeOutCountFocusListener(FocusListener focusListenerForTxtTimeOutCount) {
		txtTimeOutCount.addFocusListener(focusListenerForTxtTimeOutCount);
	}
	public void addTimeOutCountIncreaseListener(ActionListener listenForBtnTimeOutCountIncrease) {
		btnTimeOutCountIncrease.addActionListener(listenForBtnTimeOutCountIncrease);
	}
	public void addResetListener(ActionListener listenForBtnReset) {
		btnReset.addActionListener(listenForBtnReset);
	}
	public void addWarnListener(ActionListener listenForBtnWarn) {
		btnWarn.addActionListener(listenForBtnWarn);
	}
	public void addClearAllListener(ActionListener listenForBtnClearAll) {
		btnClear.addActionListener(listenForBtnClearAll);
	}
	
	////// Utility Methods //////
	
	public void updateScore(int score) {
		updateScore(Integer.toString(score));
	}
	public void updateScore(String score) {
		txtScore.setText(score);
	}
	public void updateGameCount(int gameCount) {
		txtGameCount.setText(Integer.toString(gameCount));
	}
	public void updateGameCount(String gameCount) {
		txtGameCount.setText(gameCount);
	}
	public void updateTimeOutCount(int timeOutCount) {
		txtTimeOutCount.setText(Integer.toString(timeOutCount));
	}
	public void updateTimeOutCount(String timeOutCount) {
		txtTimeOutCount.setText(timeOutCount);
	}
	public void updateReset(boolean state) {
		btnReset.setSelected(state);
	}
	public void updateWarn(boolean state) {
		btnWarn.setSelected(state);
	}
	public void updateNames(String forwardName, String goalieName) {
		txtForwardName.setText(forwardName);
		txtGoalieName.setText(goalieName);
	}
	public void updateTeamName(String teamName) {
		txtTeamName.setText(teamName);
	}
	public void updateForwardName(String forwardName) {
		txtForwardName.setText(forwardName);
	}
	public void updateGoalieName(String goalieName) {
		txtGoalieName.setText(goalieName);
	}
	public void selectTeamName() {
		txtTeamName.selectAll();
	}
	public void selectForwardName() {
		txtForwardName.selectAll();
	}
	public void selectGoalieName() {
		txtGoalieName.selectAll();
	}
	public void displayAllFields(String teamName, String forwardName, String goalieName, int score, int gameCount, int timeOutCount, boolean isReset, boolean isWarn) {
		updateTeamName(teamName);
		updateNames(forwardName, goalieName);
		updateScore(score);
		updateGameCount(gameCount);
		updateTimeOutCount(timeOutCount);
		btnReset.setSelected(isReset);
		btnWarn.setSelected(isWarn);
	}
	
	////// Utility Methods \\\\\\
	
	public void setTitle(String title) {
			TitledBorder border = BorderFactory.createTitledBorder(title);
			border.setTitleJustification(TitledBorder.CENTER);
			this.setBorder(border);
	}
}
