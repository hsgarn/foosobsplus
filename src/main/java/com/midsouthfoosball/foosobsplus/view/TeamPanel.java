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

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JLabel lblMatchCount;
	private JLabel lblTimeOutCount;
	private JLabel lblLastScoredTime;
	private JTextField txtTeamName;
	private JTextField txtForwardName;
	private JTextField txtGoalieName;
	private JTextField txtScore;
	private JTextField txtGameCount;
	private JTextField txtMatchCount;
	private JTextField txtTimeOutCount;
	private JButton btnScoreIncrease;
	private JButton btnScoreDecrease;
	private JButton btnGameCountIncrease;
	private JButton btnGameCountDecrease;
	private JButton btnMatchCountIncrease;
	private JButton btnMatchCountDecrease;
	private JButton btnTimeOutCountIncrease;
	private JButton btnTimeOutCountDecrease;
	private JToggleButton btnReset;
	private JToggleButton btnWarn;
	private JCheckBox ckbxKingSeat;
	private int teamNbr;
	private Border innerBorder;
	private static CustomFocusTraversalPolicy customTraversalPolicy;
	public TeamPanel(int teamNbr) {
		this.teamNbr = teamNbr;
		initializeTeamPanel(teamNbr);
		layoutComponents();
	}
	private void initializeTeamPanel(int teamNbr) {
		this.teamNbr = teamNbr;
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 300;
		setPreferredSize(dim);
		setName(buildTitle());
		lblTeamName = new JLabel(Messages.getString("TeamPanel.TeamName")); //$NON-NLS-1$
		txtTeamName = new JTextField(50);
		txtTeamName.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblForwardName = new JLabel(Messages.getString("TeamPanel.ForwardName")); //$NON-NLS-1$
		txtForwardName = new JTextField(50);
		txtForwardName.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblGoalieName = new JLabel(Messages.getString("TeamPanel.GoalieName")); //$NON-NLS-1$
		txtGoalieName = new JTextField(50);
		txtGoalieName.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnSwitchPositions = new JButton(Messages.getString("TeamPanel.SwitchPositions")); //$NON-NLS-1$
		btnSwitchPositions.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblScore = new JLabel(Messages.getString("TeamPanel.Score")); //$NON-NLS-1$
		btnScoreDecrease = new JButton(Messages.getString("TeamPanel.Decrement")); //$NON-NLS-1$
		btnScoreDecrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		txtScore = new JTextField(3);
		txtScore.setHorizontalAlignment(JTextField.CENTER);
		txtScore.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnScoreIncrease = new JButton(Messages.getString("TeamPanel.Increment")); //$NON-NLS-1$
		btnScoreIncrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblGameCount = new JLabel(Messages.getString("TeamPanel.GameCount")); //$NON-NLS-1$
		btnGameCountDecrease = new JButton(Messages.getString("TeamPanel.Decrement")); //$NON-NLS-1$
		btnGameCountDecrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		txtGameCount = new JTextField(3);
		txtGameCount.setHorizontalAlignment(JTextField.CENTER);
		txtGameCount.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnGameCountIncrease = new JButton(Messages.getString("TeamPanel.Increment")); //$NON-NLS-1$
		btnGameCountIncrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblMatchCount = new JLabel(Messages.getString("TeamPanel.MatchCount")); //$NON-NLS-1$
		btnMatchCountDecrease = new JButton(Messages.getString("TeamPanel.Decrement")); //$NON-NLS-1$
		btnMatchCountDecrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		txtMatchCount = new JTextField(3);
		txtMatchCount.setHorizontalAlignment(JTextField.CENTER);
		txtMatchCount.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnMatchCountIncrease = new JButton(Messages.getString("TeamPanel.Increment")); //$NON-NLS-1$
		btnMatchCountIncrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblTimeOutCount = new JLabel(Messages.getString("TeamPanel.TimeOutCount")); //$NON-NLS-1$
		btnTimeOutCountDecrease = new JButton(Messages.getString("TeamPanel.ReturnTimeOut")); //$NON-NLS-1$
		btnTimeOutCountDecrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		txtTimeOutCount = new JTextField(3);
		txtTimeOutCount.setHorizontalAlignment(JTextField.CENTER);
		txtTimeOutCount.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnTimeOutCountIncrease = new JButton(Messages.getString("TeamPanel.UseTimeOut")); //$NON-NLS-1$
		btnTimeOutCountIncrease.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		btnReset = new JToggleButton(Messages.getString("TeamPanel.Reset")); //$NON-NLS-1$
		btnReset.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblLastScoredTime = new JLabel("0"); //$NON-NLS-1$
		lblLastScoredTime.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		lblLastScoredTime.setOpaque(true);
		lblLastScoredTime.setBackground(Color.CYAN);
		btnWarn = new JToggleButton(Messages.getString("TeamPanel.Warn")); //$NON-NLS-1$
		btnWarn.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		ckbxKingSeat = new JCheckBox(Messages.getString("TeamPanel.KingSeat")); //$NON-NLS-1$
		ckbxKingSeat.setName("Team " + Integer.toString(teamNbr)); //$NON-NLS-1$
		setMnemonics(teamNbr);
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
                Vector<Component> order = new Vector<>(16);
                order.add(txtTeamName);
                order.add(txtForwardName);
                order.add(txtGoalieName);
                order.add(txtScore);
                order.add(txtGameCount);
                order.add(txtMatchCount);
                order.add(txtTimeOutCount);
                order.add(btnSwitchPositions);
                order.add(btnScoreDecrease);
                order.add(btnScoreIncrease);
                order.add(btnGameCountDecrease);
                order.add(btnGameCountIncrease);
                order.add(btnMatchCountDecrease);
                order.add(btnMatchCountIncrease);
                order.add(btnTimeOutCountDecrease);
                order.add(btnTimeOutCountIncrease);
                order.add(btnReset);
                order.add(btnWarn);
                order.add(ckbxKingSeat);
                customTraversalPolicy = new CustomFocusTraversalPolicy(order);
                this.setFocusTraversalPolicy(customTraversalPolicy);
                this.setFocusCycleRoot(true);
	}
	private void setMnemonics(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		if(Settings.getTeamHotKeyParameter(teamNumber,"SwitchPositions").isEmpty()) { //$NON-NLS-1$
			btnSwitchPositions.setMnemonic(-1);
		} else {
			btnSwitchPositions.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"SwitchPositions").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"ScorePlus").isEmpty()) { //$NON-NLS-1$
			btnScoreIncrease.setMnemonic(-1);
		} else {
			btnScoreIncrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"ScorePlus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"ScoreMinus").isEmpty()) { //$NON-NLS-1$
			btnScoreDecrease.setMnemonic(-1);
		} else {
			btnScoreDecrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"ScoreMinus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"GameCountPlus").isEmpty()) { //$NON-NLS-1$
			btnGameCountIncrease.setMnemonic(-1);
		} else {
			btnGameCountIncrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"GameCountPlus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"GameCountMinus").isEmpty()) { //$NON-NLS-1$
			btnGameCountDecrease.setMnemonic(-1);
		} else {
			btnGameCountDecrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"GameCountMinus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"MatchCountPlus").isEmpty()) { //$NON-NLS-1$
			btnMatchCountIncrease.setMnemonic(-1);
		} else {
			btnMatchCountIncrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"MatchCountPlus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"MatchCountMinus").isEmpty()) { //$NON-NLS-1$
			btnMatchCountDecrease.setMnemonic(-1);
		} else {
			btnMatchCountDecrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"MatchCountMinus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"TimeOutPlus").isEmpty()) { //$NON-NLS-1$
			btnTimeOutCountIncrease.setMnemonic(-1);
		} else {
			btnTimeOutCountIncrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"TimeOutPlus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"TimeOutMinus").isEmpty()) { //$NON-NLS-1$
			btnTimeOutCountDecrease.setMnemonic(-1);
		} else {
			btnTimeOutCountDecrease.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"TimeOutMinus").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"Reset").isEmpty()) { //$NON-NLS-1$
			btnReset.setMnemonic(-1);
		} else {
			btnReset.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"Reset").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"Warn").isEmpty()) { //$NON-NLS-1$
			btnWarn.setMnemonic(-1);
		} else {
			btnWarn.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"Warn").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getTeamHotKeyParameter(teamNumber,"KingSeat").isEmpty()) { //$NON-NLS-1$
			ckbxKingSeat.setMnemonic(-1);
		} else {
			ckbxKingSeat.setMnemonic(Settings.getTeamHotKeyParameter(teamNumber,"KingSeat").charAt(0)); //$NON-NLS-1$
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
		//////// Match Count ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblMatchCount, gc);
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(btnMatchCountDecrease, gc);
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(txtMatchCount, gc);
		gc.gridx = 2;
		gc.insets = new Insets(0, 10, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnMatchCountIncrease, gc);
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
		gc.gridx = 1;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		add(lblLastScoredTime, gc);
		gc.gridx = 2;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(btnWarn, gc);
		////////KING SEAT ////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1.0;
		gc.gridx = 1;
		gc.insets = new Insets(10, 0, 10, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		add(ckbxKingSeat, gc);
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
	public void addMatchCountDecreaseListener(ActionListener listenForBtnMatchCountDecrease) {
		btnMatchCountDecrease.addActionListener(listenForBtnMatchCountDecrease);
	}
	public void addGameCountListener(ActionListener listenForTxtGameCount) {
		txtGameCount.addActionListener(listenForTxtGameCount);
	}
	public void addMatchCountListener(ActionListener listenForTxtMatchCount) {
		txtMatchCount.addActionListener(listenForTxtMatchCount);
	}
	public void addGameCountFocusListener(FocusListener focusListenerForTxtGameCount) {
		txtGameCount.addFocusListener(focusListenerForTxtGameCount);
	}
	public void addMatchCountFocusListener(FocusListener focusListenerForTxtMatchCount) {
		txtMatchCount.addFocusListener(focusListenerForTxtMatchCount);
	}
	public void addGameCountIncreaseListener(ActionListener listenForBtnGameCountIncrease) {
		btnGameCountIncrease.addActionListener(listenForBtnGameCountIncrease);
	}
	public void addMatchCountIncreaseListener(ActionListener listenForBtnMatchCountIncrease) {
		btnMatchCountIncrease.addActionListener(listenForBtnMatchCountIncrease);
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
	public void addKingSeatListener(ActionListener listenForCkbxKingSeat) {
		ckbxKingSeat.addActionListener(listenForCkbxKingSeat);
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
	public void updateMatchCount(int matchCount) {
		txtMatchCount.setText(Integer.toString(matchCount));
	}
	public void updateGameCount(String gameCount) {
		txtGameCount.setText(gameCount);
	}
	public void updateMatchCount(String matchCount) {
		txtMatchCount.setText(matchCount);
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
	public void updateKingSeat(boolean state) {
		ckbxKingSeat.setSelected(state);
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
	public void displayAllFields(String teamName, String forwardName, String goalieName, int score, int gameCount, int matchCount, int timeOutCount, boolean isReset, boolean isWarn, boolean isKingSeat) {
		updateTeamName(teamName);
		updateNames(forwardName, goalieName);
		updateScore(score);
		updateGameCount(gameCount);
		updateMatchCount(matchCount);
		updateTimeOutCount(timeOutCount);
		btnReset.setSelected(isReset);
		btnWarn.setSelected(isWarn);
		ckbxKingSeat.setSelected(isKingSeat);
	}
	public void setTitle(String title) {
		title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	public void updateLastScoredTime(int timeElapsed) {
		lblLastScoredTime.setText(Integer.toString(timeElapsed));
	}
	public void updateLastScoredTime(String timeElapsed) {
		lblLastScoredTime.setText(timeElapsed);
	}
	public String getLastScoredTimeText() {
		return lblLastScoredTime.getText();
	}
	public Color getLastScoredTimeColor() {
		return lblLastScoredTime.getBackground();
	}
	public void setLastScoredTimeColor(Color color) {
		lblLastScoredTime.setBackground(color);
	}
	public void updateMnemonics() {
		setMnemonics(teamNbr);
	}
	public void setTitle() {
		setTitle(buildTitle());
	}
	private String buildTitle() {
		String theColor;
            switch (teamNbr) {
                case 1:
                    theColor = Settings.getControlParameter("Side1Color"); //$NON-NLS-1$
                    break;
                case 2:
                    theColor = Settings.getControlParameter("Side2Color"); //$NON-NLS-1$
                    break;
                default:
                    theColor = Settings.getControlParameter("Side2Color"); //$NON-NLS-1$
                    break;
            }
		return Messages.getString("TeamPanel.Team") + teamNbr + Messages.getString("TeamPanel.Information") + theColor + Messages.getString("TeamPanel.Side"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
    public static class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
    	Vector<Component> order;
    	public CustomFocusTraversalPolicy(Vector<Component> order) {
			this.order = new Vector<Component>(order.size());
			this.order.addAll(order);
		}
            @Override
		public Component getComponentAfter(Container focusCycleRoot, Component aComponent)	{
			int idx = (order.indexOf(aComponent) + 1) % order.size();
			return order.get(idx);
		}
            @Override
		public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
			int idx = order.indexOf(aComponent) - 1;
			if (idx < 0) {
			  idx = order.size() - 1;
			}
			return order.get(idx);
		}
            @Override
		public Component getDefaultComponent(Container focusCycleRoot) {
			return order.get(0);
		}
            @Override
		public Component getLastComponent(Container focusCycleRoot) {
			return order.lastElement();
		}
            @Override
		public Component getFirstComponent(Container focusCycleRoot) {
			return order.get(0);
		}
	}
}
