/**
Copyright 2020, 2021, 2022 Hugh Garner
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

import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class HotKeysPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtStartMatchHotKey;
	private JTextField txtPauseMatchHotKey;
	private JTextField txtStartGameHotKey;
	private JTextField txtTeam1ClearHotKey;
	private JTextField txtTeam1SwitchPositionsHotKey;
	private JTextField txtTeam2ClearHotKey;
	private JTextField txtTeam2SwitchPositionsHotKey;
	private JTextField txtSwitchTeamsHotKey;
	private JTextField txtGameCount1MinusHotKey;
	private JTextField txtGameCount1PlusHotKey;
	private JTextField txtGameCount2MinusHotKey;
	private JTextField txtGameCount2PlusHotKey;
	private JTextField txtSwitchGameCountsHotKey;
	private JTextField txtScore1MinusHotKey;
	private JTextField txtScore1PlusHotKey;
	private JTextField txtScore2MinusHotKey;
	private JTextField txtScore2PlusHotKey;
	private JTextField txtSwitchScoresHotKey;
	private JTextField txtTimeOut1MinusHotKey;
	private JTextField txtTimeOut1PlusHotKey;
	private JTextField txtTimeOut2MinusHotKey;
	private JTextField txtTimeOut2PlusHotKey;
	private JTextField txtSwitchTimeOutsHotKey;
	private JTextField txtReset1HotKey;
	private JTextField txtReset2HotKey;
	private JTextField txtWarn1HotKey;
	private JTextField txtWarn2HotKey;
	private JTextField txtSwitchResetWarnsHotKey;
	private JTextField txtSwitchSidesHotKey;
	private JTextField txtResetGameCountsHotKey;
	private JTextField txtResetScoresHotKey;
	private JTextField txtResetTimeOutsHotKey;
	private JTextField txtResetResetWarnHotKey;
	private JTextField txtResetAllHotKey;
	private JTextField txtClearAllHotKey;
	private JTextField txtShotTimerHotKey;
	private JTextField txtPassTimerHotKey;
	private JTextField txtTimeOutTimerHotKey;
	private JTextField txtGameTimerHotKey;
	private JTextField txtRecallTimerHotKey;
	private JTextField txtResetTimersHotKey;
	private JTextField txtResetNamesHotKey;
	private JTextField txtUndoHotKey;
	private JTextField txtRedoHotKey;
	private JTextField txtSwitchPlayer1HotKey;
	private JTextField txtSwitchPlayer2HotKey;
	private JTextField txtShowSkunkHotKey;
	private JTextField txtStartStreamHotKey;
	private JButton btnSave;
	private JButton btnGenerateHotKeyScripts;
	private String hotKeyBaseScriptText;

	public HotKeysPanel(Settings settings) throws IOException {
		hotKeyBaseScriptText = settings.getHotKeyBaseScript();
		if (hotKeyBaseScriptText.isEmpty()) hotKeyBaseScriptText = settings.getDefaultHotKeyBaseScript();
		setLayout(new MigLayout("", "[][grow][10.00][][grow][10.00][][grow][10.00][][grow]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JLabel lblButton = new JLabel(Messages.getString("HotKeysPanel.Button")); //$NON-NLS-1$
		lblButton.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblButton, "cell 0 0,alignx right"); //$NON-NLS-1$
		
		JLabel lblHotKey = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKey.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKey, "cell 1 0,alignx center"); //$NON-NLS-1$
		
		JLabel lblButton1 = new JLabel(Messages.getString("HotKeysPanel.Button")); //$NON-NLS-1$
		lblButton1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblButton1, "cell 3 0,alignx right"); //$NON-NLS-1$
		
		JLabel lblHotKey1 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKey1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKey1, "cell 4 0,alignx center"); //$NON-NLS-1$
		
		JLabel lblButton2 = new JLabel(Messages.getString("HotKeysPanel.Button")); //$NON-NLS-1$
		lblButton2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblButton2, "cell 6 0,alignx right"); //$NON-NLS-1$
		
		JLabel lblHotKey2 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKey2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKey2, "cell 7 0,alignx center"); //$NON-NLS-1$
		
		JLabel lblButton3 = new JLabel(Messages.getString("HotKeysPanel.Button")); //$NON-NLS-1$
		lblButton3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblButton3, "cell 9 0,alignx right"); //$NON-NLS-1$
		
		JLabel lblHotKey3 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKey3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKey3, "cell 10 0,alignx center"); //$NON-NLS-1$
		
		JLabel lblStartMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.StartMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblStartMatchHotKey, "cell 0 1,alignx trailing"); //$NON-NLS-1$
		
		txtStartMatchHotKey = new JTextField();
		txtStartMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartMatchHotKey.setText(settings.getStartMatchHotKey());
		add(txtStartMatchHotKey, "cell 1 1,alignx left"); //$NON-NLS-1$
		txtStartMatchHotKey.setColumns(10);
		
		JLabel lblReset1HotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblReset1HotKey, "cell 3 1,alignx trailing"); //$NON-NLS-1$
		
		txtReset1HotKey = new JTextField();
		txtReset1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset1HotKey.setText(settings.getReset1HotKey());
		add(txtReset1HotKey, "cell 4 1,alignx left"); //$NON-NLS-1$
		txtReset1HotKey.setColumns(10);
		
		JLabel lblWarn1HotKey = new JLabel(Messages.getString("HotKeysPanel.WarnTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn1HotKey, "cell 6 1,alignx trailing"); //$NON-NLS-1$
		
		txtWarn1HotKey = new JTextField();
		txtWarn1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn1HotKey.setText(settings.getWarn1HotKey());
		add(txtWarn1HotKey, "cell 7 1,alignx left"); //$NON-NLS-1$
		txtWarn1HotKey.setColumns(10);
		
		JLabel lblTimers = new JLabel(Messages.getString("HotKeysPanel.Timers", settings.getGameType())); //$NON-NLS-1$
		add(lblTimers, "cell 9 1,alignx right"); //$NON-NLS-1$
		
		JLabel lblPauseMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.PauseMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblPauseMatchHotKey, "cell 0 2,alignx trailing"); //$NON-NLS-1$
		
		txtPauseMatchHotKey = new JTextField();
		txtPauseMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPauseMatchHotKey.setText(settings.getPauseMatchHotKey());
		add(txtPauseMatchHotKey, "cell 1 2,alignx left"); //$NON-NLS-1$
		txtPauseMatchHotKey.setColumns(10);
		
		JLabel lblReset2HotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblReset2HotKey, "cell 3 2,alignx trailing"); //$NON-NLS-1$
		
		txtReset2HotKey = new JTextField();
		txtReset2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset2HotKey.setText(settings.getReset2HotKey());
		add(txtReset2HotKey, "cell 4 2,growx"); //$NON-NLS-1$
		txtReset2HotKey.setColumns(10);
		
		JLabel lblWarn2HotKey = new JLabel(Messages.getString("HotKeysPanel.WarnTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn2HotKey, "cell 6 2,alignx trailing"); //$NON-NLS-1$
		
		txtWarn2HotKey = new JTextField();
		txtWarn2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn2HotKey.setText(settings.getWarn2HotKey());
		add(txtWarn2HotKey, "cell 7 2,alignx left"); //$NON-NLS-1$
		txtWarn2HotKey.setColumns(10);
		
		JLabel lblShotTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartShotTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblShotTimerHotKey, "cell 9 2,alignx trailing"); //$NON-NLS-1$
		
		txtShotTimerHotKey = new JTextField();
		txtShotTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShotTimerHotKey.setText(settings.getShotTimerHotKey());
		add(txtShotTimerHotKey, "cell 10 2,alignx left"); //$NON-NLS-1$
		txtShotTimerHotKey.setColumns(10);
		
		JLabel lblStartGameHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGame", settings.getGameType())); //$NON-NLS-1$
		add(lblStartGameHotKey, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		
		txtStartGameHotKey = new JTextField();
		txtStartGameHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartGameHotKey.setText(settings.getStartGameHotKey());
		add(txtStartGameHotKey, "cell 1 4,alignx left,aligny top"); //$NON-NLS-1$
		txtStartGameHotKey.setColumns(10);
		
		JLabel lblPassTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartPassTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblPassTimerHotKey, "cell 9 4,alignx trailing"); //$NON-NLS-1$
		
		txtPassTimerHotKey = new JTextField();
		txtPassTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassTimerHotKey.setText(settings.getPassTimerHotKey());
		add(txtPassTimerHotKey, "cell 10 4,alignx left"); //$NON-NLS-1$
		txtPassTimerHotKey.setColumns(10);
		
		JLabel lblTeamNames = new JLabel(Messages.getString("HotKeysPanel.TeamNames", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamNames, "cell 0 5,alignx right"); //$NON-NLS-1$
		
		JLabel lblScores = new JLabel(Messages.getString("HotKeysPanel.Scores", settings.getGameType())); //$NON-NLS-1$
		add(lblScores, "cell 3 5,alignx right"); //$NON-NLS-1$
		
		JLabel lblSwitchResetWarnsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchResetWarns", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchResetWarnsHotKey, "cell 6 5,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchResetWarnsHotKey = new JTextField();
		txtSwitchResetWarnsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchResetWarnsHotKey.setText(settings.getSwitchResetWarnsHotKey());
		add(txtSwitchResetWarnsHotKey, "cell 7 5,alignx left"); //$NON-NLS-1$
		txtSwitchResetWarnsHotKey.setColumns(10);
		
		JLabel lblTimeOutTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartTimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOutTimerHotKey, "cell 9 5,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOutTimerHotKey = new JTextField();
		txtTimeOutTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOutTimerHotKey.setText(settings.getTimeOutTimerHotKey());
		add(txtTimeOutTimerHotKey, "cell 10 5,alignx left"); //$NON-NLS-1$
		txtTimeOutTimerHotKey.setColumns(10);
		
		JLabel lblTeam1ClearHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearHotKey, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1ClearHotKey = new JTextField();
		txtTeam1ClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1ClearHotKey.setText(settings.getTeam1ClearHotKey());
		add(txtTeam1ClearHotKey, "cell 1 6,alignx left"); //$NON-NLS-1$
		txtTeam1ClearHotKey.setColumns(10);
		
		JLabel lblScore1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementScoreTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1MinusHotKey, "cell 3 6,alignx trailing"); //$NON-NLS-1$
		
		txtScore1MinusHotKey = new JTextField();
		txtScore1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1MinusHotKey.setText(settings.getScore1MinusHotKey());
		add(txtScore1MinusHotKey, "cell 4 6,alignx left"); //$NON-NLS-1$
		txtScore1MinusHotKey.setColumns(10);
		
		JLabel lblGameTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGameTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimerHotKey, "cell 9 6,alignx trailing"); //$NON-NLS-1$
		
		txtGameTimerHotKey = new JTextField();
		txtGameTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameTimerHotKey.setText(settings.getGameTimerHotKey());
		add(txtGameTimerHotKey, "cell 10 6,alignx left"); //$NON-NLS-1$
		txtGameTimerHotKey.setColumns(10);
		
		JLabel lblTeam1SwitchPositionsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPositionsTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsHotKey, "cell 0 7,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1SwitchPositionsHotKey = new JTextField();
		txtTeam1SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1SwitchPositionsHotKey.setText(settings.getTeam1SwitchPositionsHotKey());
		add(txtTeam1SwitchPositionsHotKey, "cell 1 7,alignx left"); //$NON-NLS-1$
		txtTeam1SwitchPositionsHotKey.setColumns(10);
		
		JLabel lblScore1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementScoreTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1PlusHotKey, "cell 3 7,alignx trailing"); //$NON-NLS-1$
		
		txtScore1PlusHotKey = new JTextField();
		txtScore1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1PlusHotKey.setText(settings.getScore1PlusHotKey());
		add(txtScore1PlusHotKey, "cell 4 7,alignx left"); //$NON-NLS-1$
		txtScore1PlusHotKey.setColumns(10);
		
		JLabel lblSwitchSidesHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchSides", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchSidesHotKey, "cell 6 7,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchSidesHotKey = new JTextField();
		txtSwitchSidesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchSidesHotKey.setText(settings.getSwitchSidesHotKey());
		add(txtSwitchSidesHotKey, "cell 7 7,alignx left"); //$NON-NLS-1$
		txtSwitchSidesHotKey.setColumns(10);
		
		JLabel lblRecallTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartRecallTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblRecallTimerHotKey, "cell 9 7,alignx trailing"); //$NON-NLS-1$
		
		txtRecallTimerHotKey = new JTextField();
		txtRecallTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecallTimerHotKey.setText(settings.getRecallTimerHotKey());
		add(txtRecallTimerHotKey, "cell 10 7,alignx left"); //$NON-NLS-1$
		txtRecallTimerHotKey.setColumns(10);
		
		JLabel lblTeam2ClearHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearHotKey, "cell 0 8,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2ClearHotKey = new JTextField();
		txtTeam2ClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2ClearHotKey.setText(settings.getTeam2ClearHotKey());
		add(txtTeam2ClearHotKey, "cell 1 8,alignx left"); //$NON-NLS-1$
		txtTeam2ClearHotKey.setColumns(10);
		
		JLabel lblScore2MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementScoreTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblScore2MinusHotKey, "cell 3 8,alignx trailing"); //$NON-NLS-1$
		
		txtScore2MinusHotKey = new JTextField();
		txtScore2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2MinusHotKey.setText(settings.getScore2MinusHotKey());
		add(txtScore2MinusHotKey, "cell 4 8,alignx left"); //$NON-NLS-1$
		txtScore2MinusHotKey.setColumns(10);
		
		JLabel lblClearAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearAll", settings.getGameType())); //$NON-NLS-1$
		add(lblClearAllHotKey, "cell 6 8,alignx trailing"); //$NON-NLS-1$
		
		txtClearAllHotKey = new JTextField();
		txtClearAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtClearAllHotKey.setText(settings.getClearAllHotKey());
		add(txtClearAllHotKey, "cell 7 8,alignx left"); //$NON-NLS-1$
		txtClearAllHotKey.setColumns(10);
		
		JLabel lblResetTimersHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblResetTimersHotKey, "cell 9 8,alignx trailing"); //$NON-NLS-1$
		
		txtResetTimersHotKey = new JTextField();
		txtResetTimersHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimersHotKey.setText(settings.getResetTimersHotKey());
		add(txtResetTimersHotKey, "cell 10 8,alignx left"); //$NON-NLS-1$
		txtResetTimersHotKey.setColumns(10);
		
		JLabel lblTeam2SwitchPositionsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPositionsTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SwitchPositionsHotKey, "cell 0 9,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2SwitchPositionsHotKey = new JTextField();
		txtTeam2SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2SwitchPositionsHotKey.setText(settings.getTeam2SwitchPositionsHotKey());
		add(txtTeam2SwitchPositionsHotKey, "cell 1 9,alignx left"); //$NON-NLS-1$
		txtTeam2SwitchPositionsHotKey.setColumns(10);
		
		JLabel lblScore2PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementScoreTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblScore2PlusHotKey, "cell 3 9,alignx trailing"); //$NON-NLS-1$
		
		txtScore2PlusHotKey = new JTextField();
		txtScore2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2PlusHotKey.setText(settings.getScore2PlusHotKey());
		add(txtScore2PlusHotKey, "cell 4 9,alignx left,aligny top"); //$NON-NLS-1$
		txtScore2PlusHotKey.setColumns(10);
		
		JLabel lblSwitchTeamsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTeams", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchTeamsHotKey, "cell 0 10,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchTeamsHotKey = new JTextField();
		txtSwitchTeamsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTeamsHotKey.setText(settings.getSwitchTeamsHotKey());
		add(txtSwitchTeamsHotKey, "cell 1 10,alignx left"); //$NON-NLS-1$
		txtSwitchTeamsHotKey.setColumns(10);
		
		JLabel lblSwitchScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchScores", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchScoresHotKey, "cell 3 10,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchScoresHotKey = new JTextField();
		txtSwitchScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchScoresHotKey.setText(settings.getSwitchScoresHotKey());
		add(txtSwitchScoresHotKey, "cell 4 10,alignx left"); //$NON-NLS-1$
		txtSwitchScoresHotKey.setColumns(10);
		
		JLabel lblResetNamesHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetNames", settings.getGameType())); //$NON-NLS-1$
		add(lblResetNamesHotKey, "cell 6 10,alignx trailing"); //$NON-NLS-1$
		
		txtResetNamesHotKey = new JTextField();
		txtResetNamesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetNamesHotKey.setText(settings.getResetNamesHotKey());
		add(txtResetNamesHotKey, "cell 7 10,alignx left"); //$NON-NLS-1$
		txtResetNamesHotKey.setColumns(10);
		
		JLabel lblUndoHotKey = new JLabel(Messages.getString("HotKeysPanel.Undo", settings.getGameType())); //$NON-NLS-1$
		add(lblUndoHotKey, "cell 9 10,alignx trailing"); //$NON-NLS-1$
		
		txtUndoHotKey = new JTextField();
		txtUndoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtUndoHotKey.setText(settings.getUndoHotKey());
		add(txtUndoHotKey, "cell 10 10,alignx left"); //$NON-NLS-1$
		txtUndoHotKey.setColumns(10);
		
		JLabel lblGameCounts = new JLabel(Messages.getString("HotKeysPanel.GameCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCounts, "cell 0 11,alignx right"); //$NON-NLS-1$
		
		JLabel lblTimeOuts = new JLabel(Messages.getString("HotKeysPanel.TimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOuts, "cell 3 11,alignx right"); //$NON-NLS-1$
		
		JLabel lblResetScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetScores", settings.getGameType())); //$NON-NLS-1$
		add(lblResetScoresHotKey, "cell 6 11,alignx trailing"); //$NON-NLS-1$
		
		txtResetScoresHotKey = new JTextField();
		txtResetScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetScoresHotKey.setText(settings.getResetScoresHotKey());
		add(txtResetScoresHotKey, "cell 7 11,alignx left"); //$NON-NLS-1$
		txtResetScoresHotKey.setColumns(10);
		
		JLabel lblRedoHotKey = new JLabel(Messages.getString("HotKeysPanel.Redo", settings.getGameType())); //$NON-NLS-1$
		add(lblRedoHotKey, "cell 9 11,alignx trailing"); //$NON-NLS-1$
		
		txtRedoHotKey = new JTextField();
		txtRedoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedoHotKey.setText(settings.getRedoHotKey());
		add(txtRedoHotKey, "cell 10 11,alignx left"); //$NON-NLS-1$
		txtRedoHotKey.setColumns(10);
		
		JLabel lblGameCount1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementGameCountTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount1MinusHotKey, "cell 0 12,alignx trailing"); //$NON-NLS-1$
		
		txtGameCount1MinusHotKey = new JTextField();
		txtGameCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1MinusHotKey.setText(settings.getGameCount1MinusHotKey());
		add(txtGameCount1MinusHotKey, "cell 1 12,alignx left"); //$NON-NLS-1$
		txtGameCount1MinusHotKey.setColumns(10);
		
		JLabel lblTimeOut1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.ReturnTimeOutTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1MinusHotKey, "cell 3 12,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOut1MinusHotKey = new JTextField();
		txtTimeOut1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1MinusHotKey.setText(settings.getTimeOut1MinusHotKey());
		add(txtTimeOut1MinusHotKey, "cell 4 12,alignx left"); //$NON-NLS-1$
		txtTimeOut1MinusHotKey.setColumns(10);
		
		JLabel lblResetGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetGameCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblResetGameCountsHotKey, "cell 6 12,alignx trailing"); //$NON-NLS-1$
		
		txtResetGameCountsHotKey = new JTextField();
		txtResetGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetGameCountsHotKey.setText(settings.getResetGameCountsHotKey());
		add(txtResetGameCountsHotKey, "cell 7 12,alignx left"); //$NON-NLS-1$
		txtResetGameCountsHotKey.setColumns(10);
		
		JLabel lblGameCount1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementGameCountTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount1PlusHotKey, "cell 0 13,alignx trailing"); //$NON-NLS-1$
		
		txtGameCount1PlusHotKey = new JTextField();
		txtGameCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1PlusHotKey.setText(settings.getGameCount1PlusHotKey());
		add(txtGameCount1PlusHotKey, "cell 1 13,alignx left"); //$NON-NLS-1$
		txtGameCount1PlusHotKey.setColumns(10);
		
		JLabel lblTimeOut1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.UseTimeOutTeam1", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1PlusHotKey, "cell 3 13,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOut1PlusHotKey = new JTextField();
		txtTimeOut1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1PlusHotKey.setText(settings.getTimeOut1PlusHotKey());
		add(txtTimeOut1PlusHotKey, "cell 4 13,alignx left"); //$NON-NLS-1$
		txtTimeOut1PlusHotKey.setColumns(10);
		
		JLabel lblResetTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblResetTimeOutsHotKey, "cell 6 13,alignx trailing"); //$NON-NLS-1$
		
		txtResetTimeOutsHotKey = new JTextField();
		txtResetTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimeOutsHotKey.setText(settings.getResetTimeOutsHotKey());
		add(txtResetTimeOutsHotKey, "cell 7 13,alignx left"); //$NON-NLS-1$
		txtResetTimeOutsHotKey.setColumns(10);
		
		JLabel lblGameCount2MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementGameCountTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount2MinusHotKey, "cell 0 14,alignx trailing"); //$NON-NLS-1$
		
		txtGameCount2MinusHotKey = new JTextField();
		txtGameCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2MinusHotKey.setText(settings.getGameCount2MinusHotKey());
		add(txtGameCount2MinusHotKey, "cell 1 14,alignx left"); //$NON-NLS-1$
		txtGameCount2MinusHotKey.setColumns(10);
		
		JLabel lblTimeOut2MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.ReturnTimeOutTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut2MinusHotKey, "cell 3 14,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOut2MinusHotKey = new JTextField();
		txtTimeOut2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2MinusHotKey.setText(settings.getTimeOut2MinusHotKey());
		add(txtTimeOut2MinusHotKey, "cell 4 14,alignx left"); //$NON-NLS-1$
		txtTimeOut2MinusHotKey.setColumns(10);
		
		JLabel lblResetResetWarnHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetResetWarn", settings.getGameType())); //$NON-NLS-1$
		add(lblResetResetWarnHotKey, "cell 6 14,alignx trailing"); //$NON-NLS-1$
		
		txtResetResetWarnHotKey = new JTextField();
		txtResetResetWarnHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetResetWarnHotKey.setText(settings.getResetResetWarnHotKey());
		add(txtResetResetWarnHotKey, "cell 7 14,alignx left"); //$NON-NLS-1$
		txtResetResetWarnHotKey.setColumns(10);
		
		JLabel lblGameCount2PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementGameCountTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount2PlusHotKey, "cell 0 15,alignx trailing"); //$NON-NLS-1$
		
		txtGameCount2PlusHotKey = new JTextField();
		txtGameCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2PlusHotKey.setText(settings.getGameCount2PlusHotKey());
		add(txtGameCount2PlusHotKey, "cell 1 15,alignx left"); //$NON-NLS-1$
		txtGameCount2PlusHotKey.setColumns(10);
		
		JLabel lblTimeOut2PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.UseTimeOutTeam2", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut2PlusHotKey, "cell 3 15,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOut2PlusHotKey = new JTextField();
		txtTimeOut2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2PlusHotKey.setText(settings.getTimeOut2PlusHotKey());
		add(txtTimeOut2PlusHotKey, "cell 4 15,alignx left"); //$NON-NLS-1$
		txtTimeOut2PlusHotKey.setColumns(10);
		
		JLabel lblResetAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetAll", settings.getGameType())); //$NON-NLS-1$
		add(lblResetAllHotKey, "cell 6 15,alignx trailing"); //$NON-NLS-1$
		
		txtResetAllHotKey = new JTextField();
		txtResetAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetAllHotKey.setText(settings.getResetAllHotKey());
		add(txtResetAllHotKey, "cell 7 15,alignx left"); //$NON-NLS-1$
		txtResetAllHotKey.setColumns(10);
		
		JLabel lblSwitchGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchGameCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchGameCountsHotKey, "cell 0 16,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchGameCountsHotKey = new JTextField();
		txtSwitchGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchGameCountsHotKey.setText(settings.getSwitchGameCountsHotKey());
		add(txtSwitchGameCountsHotKey, "cell 1 16,alignx left"); //$NON-NLS-1$
		txtSwitchGameCountsHotKey.setColumns(10);
		
		JLabel lblSwitchTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchTimeOutsHotKey, "cell 3 16,alignx trailing"); //$NON-NLS-1$
		
		txtSwitchTimeOutsHotKey = new JTextField();
		txtSwitchTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTimeOutsHotKey.setText(settings.getSwitchTimeOutsHotKey());
		add(txtSwitchTimeOutsHotKey, "cell 4 16,alignx left"); //$NON-NLS-1$
		txtSwitchTimeOutsHotKey.setColumns(10);
		
		JLabel lblSwitchPlayer1HotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPlayer1", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchPlayer1HotKey, "cell 9 12,alignx trailing"); //$NON-NLS-1$
		                            
		txtSwitchPlayer1HotKey = new JTextField();
		txtSwitchPlayer1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchPlayer1HotKey.setText(settings.getSwitchPlayer1HotKey());
		add(txtSwitchPlayer1HotKey, "cell 10 12,alignx left"); //$NON-NLS-1$

		txtSwitchPlayer1HotKey.setColumns(10);
		
		JLabel lblSwitchPlayer2HotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPlayer2", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchPlayer2HotKey, "cell 9 13,alignx trailing"); //$NON-NLS-1$

		txtSwitchPlayer2HotKey = new JTextField();
		txtSwitchPlayer2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchPlayer2HotKey.setText(settings.getSwitchPlayer2HotKey());
		add(txtSwitchPlayer2HotKey, "cell 10 13,alignx left"); //$NON-NLS-1$

		txtSwitchPlayer2HotKey.setColumns(10);

		JLabel lblShowSkunkHotKey = new JLabel(Messages.getString("HotKeysPanel.ShowSkunk", settings.getGameType())); //$NON-NLS-1$
		add(lblShowSkunkHotKey, "cell 9 14,alignx trailing"); //$NON-NLS-1$

		txtShowSkunkHotKey = new JTextField();
		txtShowSkunkHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowSkunkHotKey.setText(settings.getShowSkunkHotKey());
		add(txtShowSkunkHotKey, "cell 10 14,alignx left"); //$NON-NLS-1$

		txtShowSkunkHotKey.setColumns(10);
	
		JLabel lblStartStreamHotKey = new JLabel(Messages.getString("HotKeysPanel.StartStream", settings.getGameType())); //$NON-NLS-1$
		add(lblStartStreamHotKey, "cell 9 15,alignx trailing"); //$NON-NLS-1$

		txtStartStreamHotKey = new JTextField();
		txtStartStreamHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartStreamHotKey.setText(settings.getStartStreamHotKey());
		add(txtStartStreamHotKey, "cell 10 15,alignx left"); //$NON-NLS-1$

		txtStartStreamHotKey.setColumns(10);
		
		btnGenerateHotKeyScripts = new JButton(Messages.getString("HotKeysPanel.GenerateHotKeyScripts", settings.getGameType())); //$NON-NLS-1$
		btnGenerateHotKeyScripts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.generateHotKeyScripts();
				JOptionPane.showMessageDialog(null, "Done");
			}
		});
		add(btnGenerateHotKeyScripts, "cell 0 18, spanx 2, alignx center"); //$NON-NLS-1$
	
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 6 18,alignx center"); //$NON-NLS-1$
		
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 7 18,alignx center"); //$NON-NLS-1$
		
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 9 18, spanx 2, alignx trailing"); //$NON-NLS-1$

		JButton btnSelectPath = new JButton(Messages.getString("HotKeysPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("HotKeysPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(HotKeysPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (chooser.getSelectedFile().exists()) {
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					} else {
						String directoryName = chooser.getSelectedFile().getAbsolutePath();
						if(!Files.exists(Paths.get(directoryName))) {
							try {
								Files.createDirectory(Paths.get(directoryName));
							} catch (IOException e1) {
								System.out.println(Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage()); //$NON-NLS-1$
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					try {
						settings.setHotKeyScriptPath(formattedTxtPath.getText());
						settings.saveHotKeyConfig();;
					} catch (IOException ex) {
						System.out.print(Messages.getString("HotKeysPanel.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
					}
				}
			}
		});
		add(btnSelectPath, "cell 0 19, alignx right"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField();
		formattedTxtPath.setText(settings.getHotKeyScriptPath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setHotKeyScriptPath(formattedTxtPath.getText());
					settings.saveHotKeyConfig();
		    	} catch (IOException ex) {
		    		System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						settings.setHotKeyScriptPath(formattedTxtPath.getText());
						settings.saveHotKeyConfig();
			    	} catch (IOException ex) {
			    		System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 1 19 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);
	}
	private void restoreDefaults(Settings settings) {
		formattedTxtPath.setText(settings.getDefaultHotKeyScriptPath());
		txtStartMatchHotKey.setText(settings.getDefaultStartMatchHotKey());
		txtPauseMatchHotKey.setText(settings.getDefaultPauseMatchHotKey());
		txtStartGameHotKey.setText(settings.getDefaultStartGameHotKey());
		txtTeam1ClearHotKey.setText(settings.getDefaultTeam1ClearHotKey());
		txtTeam1SwitchPositionsHotKey.setText(settings.getDefaultTeam1SwitchPositionsHotKey());
		txtTeam2ClearHotKey.setText(settings.getDefaultTeam2ClearHotKey());
		txtTeam2SwitchPositionsHotKey.setText(settings.getDefaultTeam2SwitchPositionsHotKey());
		txtSwitchTeamsHotKey.setText(settings.getDefaultSwitchTeamsHotKey());
		txtGameCount1MinusHotKey.setText(settings.getDefaultGameCount1MinusHotKey());
		txtGameCount1PlusHotKey.setText(settings.getDefaultGameCount1PlusHotKey());
		txtGameCount2MinusHotKey.setText(settings.getDefaultGameCount2MinusHotKey());
		txtGameCount2PlusHotKey.setText(settings.getDefaultGameCount2PlusHotKey());
		txtSwitchGameCountsHotKey.setText(settings.getDefaultSwitchGameCountsHotKey());
		txtScore1MinusHotKey.setText(settings.getDefaultScore1MinusHotKey());
		txtScore1PlusHotKey.setText(settings.getDefaultScore1PlusHotKey());
		txtScore2MinusHotKey.setText(settings.getDefaultScore2MinusHotKey());
		txtScore2PlusHotKey.setText(settings.getDefaultScore2PlusHotKey());
		txtSwitchScoresHotKey.setText(settings.getDefaultSwitchScoresHotKey());
		txtTimeOut1MinusHotKey.setText(settings.getDefaultTimeOut1MinusHotKey());
		txtTimeOut1PlusHotKey.setText(settings.getDefaultTimeOut1PlusHotKey());
		txtTimeOut2MinusHotKey.setText(settings.getDefaultTimeOut2MinusHotKey());
		txtTimeOut2PlusHotKey.setText(settings.getDefaultTimeOut2PlusHotKey());
		txtSwitchTimeOutsHotKey.setText(settings.getDefaultSwitchTimeOutsHotKey());
		txtReset1HotKey.setText(settings.getDefaultReset1HotKey());
		txtReset2HotKey.setText(settings.getDefaultReset2HotKey());
		txtWarn1HotKey.setText(settings.getDefaultWarn1HotKey());
		txtWarn2HotKey.setText(settings.getDefaultWarn2HotKey());
		txtSwitchResetWarnsHotKey.setText(settings.getDefaultSwitchResetWarnsHotKey());
		txtSwitchSidesHotKey.setText(settings.getDefaultSwitchPositionsHotKey());
		txtResetNamesHotKey.setText(settings.getDefaultResetNamesHotKey());
		txtResetGameCountsHotKey.setText(settings.getDefaultResetGameCountsHotKey());
		txtResetScoresHotKey.setText(settings.getDefaultResetScoresHotKey());
		txtResetTimeOutsHotKey.setText(settings.getDefaultResetTimeOutsHotKey());
		txtResetResetWarnHotKey.setText(settings.getDefaultResetResetWarnHotKey());
		txtResetAllHotKey.setText(settings.getDefaultResetAllHotKey());
		txtClearAllHotKey.setText(settings.getDefaultClearAllHotKey());
		txtShotTimerHotKey.setText(settings.getDefaultShotTimerHotKey());
		txtPassTimerHotKey.setText(settings.getDefaultPassTimerHotKey());
		txtTimeOutTimerHotKey.setText(settings.getDefaultTimeOutTimerHotKey());
		txtGameTimerHotKey.setText(settings.getDefaultGameTimerHotKey());
		txtRecallTimerHotKey.setText(settings.getDefaultRecallTimerHotKey());
		txtResetTimersHotKey.setText(settings.getDefaultResetTimersHotKey());
		txtUndoHotKey.setText(settings.getDefaultUndoHotKey());
		txtRedoHotKey.setText(settings.getDefaultRedoHotKey());
		txtSwitchPlayer1HotKey.setText(settings.getDefaultSwitchPlayer1HotKey());
		txtSwitchPlayer2HotKey.setText(settings.getDefaultSwitchPlayer2HotKey());
		txtShowSkunkHotKey.setText(settings.getDefaultShowSkunkHotKey());
		txtStartStreamHotKey.setText(settings.getDefaultStartStreamHotKey());
		hotKeyBaseScriptText = settings.getDefaultHotKeyBaseScript();
	}
	
	public boolean saveSettings(Settings settings) {
		boolean okToCloseWindow = false;
		settings.setHotKeyScriptPath(formattedTxtPath.toString());
		if(checkForUniqueHotKeys()) {
			settings.setStartMatchHotKey(txtStartMatchHotKey.getText());
			settings.setPauseMatchHotKey(txtPauseMatchHotKey.getText());
			settings.setStartGameHotKey(txtStartGameHotKey.getText());
			settings.setTeam1ClearHotKey(txtTeam1ClearHotKey.getText());
			settings.setTeam1SwitchPositionsHotKey(txtTeam1SwitchPositionsHotKey.getText());
			settings.setTeam2ClearHotKey(txtTeam2ClearHotKey.getText());
			settings.setTeam2SwitchPositionsHotKey(txtTeam2SwitchPositionsHotKey.getText());
			settings.setSwitchTeamsHotKey(txtSwitchTeamsHotKey.getText());
			settings.setGameCount1MinusHotKey(txtGameCount1MinusHotKey.getText());
			settings.setGameCount1PlusHotKey(txtGameCount1PlusHotKey.getText());
			settings.setGameCount2MinusHotKey(txtGameCount2MinusHotKey.getText());
			settings.setGameCount2PlusHotKey(txtGameCount2PlusHotKey.getText());
			settings.setSwitchGameCountsHotKey(txtSwitchGameCountsHotKey.getText());
			settings.setScore1MinusHotKey(txtScore1MinusHotKey.getText());
			settings.setScore1PlusHotKey(txtScore1PlusHotKey.getText());
			settings.setScore2MinusHotKey(txtScore2MinusHotKey.getText());
			settings.setScore2PlusHotKey(txtScore2PlusHotKey.getText());
			settings.setSwitchScoresHotKey(txtSwitchScoresHotKey.getText());
			settings.setTimeOut1MinusHotKey(txtTimeOut1MinusHotKey.getText());
			settings.setTimeOut1PlusHotKey(txtTimeOut1PlusHotKey.getText());
			settings.setTimeOut2MinusHotKey(txtTimeOut2MinusHotKey.getText());
			settings.setTimeOut2PlusHotKey(txtTimeOut2PlusHotKey.getText());
			settings.setSwitchTimeOutsHotKey(txtSwitchTimeOutsHotKey.getText());
			settings.setReset1HotKey(txtReset1HotKey.getText());
			settings.setReset2HotKey(txtReset2HotKey.getText());
			settings.setWarn1HotKey(txtWarn1HotKey.getText());
			settings.setWarn2HotKey(txtWarn2HotKey.getText());
			settings.setSwitchResetWarnsHotKey(txtSwitchResetWarnsHotKey.getText());
			settings.setSwitchSidesHotKey(txtSwitchSidesHotKey.getText());
			settings.setResetNamesHotKey(txtResetNamesHotKey.getText());
			settings.setResetGameCountsHotKey(txtResetGameCountsHotKey.getText());
			settings.setResetScoresHotKey(txtResetScoresHotKey.getText());
			settings.setResetTimeOutsHotKey(txtResetTimeOutsHotKey.getText());
			settings.setResetResetWarnHotKey(txtResetResetWarnHotKey.getText());
			settings.setResetAllHotKey(txtResetAllHotKey.getText());
			settings.setClearAllHotKey(txtClearAllHotKey.getText());
			settings.setShotTimerHotKey(txtShotTimerHotKey.getText());
			settings.setPassTimerHotKey(txtPassTimerHotKey.getText());
			settings.setTimeOutTimerHotKey(txtTimeOutTimerHotKey.getText());
			settings.setGameTimerHotKey(txtGameTimerHotKey.getText());
			settings.setRecallTimerHotKey(txtRecallTimerHotKey.getText());
			settings.setResetTimersHotKey(txtResetTimersHotKey.getText());
			settings.setUndoHotKey(txtUndoHotKey.getText());
			settings.setRedoHotKey(txtRedoHotKey.getText());
			settings.setSwitchPlayer1HotKey(txtSwitchPlayer1HotKey.getText());
			settings.setSwitchPlayer2HotKey(txtSwitchPlayer2HotKey.getText());
			settings.setShowSkunkHotKey(txtShowSkunkHotKey.getText());
			settings.setStartStreamHotKey(txtStartStreamHotKey.getText());
			settings.setHotKeyBaseScript(hotKeyBaseScriptText);
			try {
				settings.saveHotKeyConfig();
				okToCloseWindow = true;
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), "Settings Error", 1);		 //$NON-NLS-1$
			}
		}
		return okToCloseWindow;
	}
	private boolean checkForUniqueHotKeys() {
		boolean allClear = true;
		HashSet<String> checkUnique = new HashSet<String>();
		Component[] componentArray = this.getComponents();
		for(int i=0; i<componentArray.length; i++) {
			if(componentArray[i] instanceof JTextField) {
				String text = ((JTextField)componentArray[i]).getText();
				if (!text.equals("")) {
					if(!checkUnique.add(text)) {
						allClear = false;
						i = componentArray.length + 1;
						JOptionPane.showMessageDialog(null, Messages.getString("Errors.DuplicateHotKey") + " " + text, "Settings Error", 1);
					}
				}
			}
		}
		return allClear;
	}
	////// Listeners \\\\\\
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
}
