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
package view;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
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
	private JTextField txtTournamentNameClearHotKey;
	private JTextField txtEventClearHotKey;
	private JTextField txtTeam1ClearHotKey;
	private JTextField txtTeam1ForwardSwitchHotKey;
	private JTextField txtTeam1GoalieSwitchHotKey;
	private JTextField txtTeam2ClearHotKey;
	private JTextField txtTeam2ForwardSwitchHotKey;
	private JTextField txtTeam2GoalieSwitchHotKey;
	private JTextField txtTeamSwitchHotKey;
	private JTextField txtGameCount1MinusHotKey;
	private JTextField txtGameCount1PlusHotKey;
	private JTextField txtGameCount2MinusHotKey;
	private JTextField txtGameCount2PlusHotKey;
	private JTextField txtGameCountSwitchHotKey;
	private JTextField txtScore1MinusHotKey;
	private JTextField txtScore1PlusHotKey;
	private JTextField txtScore2MinusHotKey;
	private JTextField txtScore2PlusHotKey;
	private JTextField txtScoreSwitchHotKey;
	private JTextField txtTimeOut1MinusHotKey;
	private JTextField txtTimeOut1PlusHotKey;
	private JTextField txtTimeOut2MinusHotKey;
	private JTextField txtTimeOut2PlusHotKey;
	private JTextField txtTimeOutSwitchHotKey;
	private JTextField txtReset1HotKey;
	private JTextField txtReset2HotKey;
	private JTextField txtWarn1HotKey;
	private JTextField txtWarn2HotKey;
	private JTextField txtResetSwitchHotKey;
	private JTextField txtAllSwitchHotKey;
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
	private JTextField txtSelectPathHotKey;
	private JTextField txtSetPathHotKey;
	private JTextField txtFetchDataHotKey;
	private JTextField txtSaveAllHotKey;
	private JTextField txtSettingsHotKey;

	public HotKeysPanel(Settings settings) throws IOException {
		setLayout(new MigLayout("", "[][grow][10.00][][grow][10.00][][grow][10.00][][grow]", "[][][][][][][][][][][][][][][][][]"));
		
		JLabel lblButton = new JLabel("Button");
		lblButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblButton, "cell 0 0,alignx right");
		
		JLabel lblHotKey = new JLabel("Hot Key");
		lblHotKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHotKey, "cell 1 0,alignx center");
		
		JLabel lblButton1 = new JLabel("Button");
		lblButton1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblButton1, "cell 3 0,alignx right");
		
		JLabel lblHotKey1 = new JLabel("Hot Key");
		lblHotKey1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHotKey1, "cell 4 0,alignx center");
		
		JLabel lblButton2 = new JLabel("Button");
		lblButton2.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblButton2, "cell 6 0,alignx right");
		
		JLabel lblHotKey2 = new JLabel("Hot Key");
		lblHotKey2.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHotKey2, "cell 7 0,alignx center");
		
		JLabel lblButton3 = new JLabel("Button");
		lblButton3.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblButton3, "cell 9 0,alignx right");
		
		JLabel lblHotKey3 = new JLabel("Hot Key");
		lblHotKey3.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblHotKey3, "cell 10 0,alignx center");
		
		JLabel lblTournamentNameClearHotKey = new JLabel("Clear (Tournament)");
		add(lblTournamentNameClearHotKey, "cell 0 1,alignx trailing");
		
		txtTournamentNameClearHotKey = new JTextField();
		txtTournamentNameClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTournamentNameClearHotKey.setText(settings.getTournamentNameClearHotKey());
		add(txtTournamentNameClearHotKey, "cell 1 1,alignx left");
		txtTournamentNameClearHotKey.setColumns(10);
		
		JLabel lblReset1HotKey = new JLabel("Reset  (Team 1)");
		add(lblReset1HotKey, "cell 3 1,alignx trailing");
		
		txtReset1HotKey = new JTextField();
		txtReset1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset1HotKey.setText(settings.getReset1HotKey());
		add(txtReset1HotKey, "cell 4 1,alignx left");
		txtReset1HotKey.setColumns(10);
		
		JLabel lblWarn1HotKey = new JLabel("Warn (Team 1)");
		add(lblWarn1HotKey, "cell 6 1,alignx trailing");
		
		txtWarn1HotKey = new JTextField();
		txtWarn1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn1HotKey.setText(settings.getWarn1HotKey());
		add(txtWarn1HotKey, "cell 7 1,alignx left");
		txtWarn1HotKey.setColumns(10);
		
		JLabel lblTimers = new JLabel("TImers:");
		add(lblTimers, "cell 9 1,alignx right");
		
		JLabel lblEventClearHotKey = new JLabel("Clear (Event)");
		add(lblEventClearHotKey, "cell 0 2,alignx trailing");
		
		txtEventClearHotKey = new JTextField();
		txtEventClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventClearHotKey.setText(settings.getEventClearHotKey());
		add(txtEventClearHotKey, "cell 1 2,alignx left,aligny top");
		txtEventClearHotKey.setColumns(10);
		
		JLabel lblReset2HotKey = new JLabel("Reset (Team 2)");
		add(lblReset2HotKey, "cell 3 2,alignx trailing");
		
		txtReset2HotKey = new JTextField();
		txtReset2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset2HotKey.setText(settings.getReset2HotKey());
		add(txtReset2HotKey, "cell 4 2,growx");
		txtReset2HotKey.setColumns(10);
		
		JLabel lblWarn2HotKey = new JLabel("Warn (Team 2)");
		add(lblWarn2HotKey, "cell 6 2,alignx trailing");
		
		txtWarn2HotKey = new JTextField();
		txtWarn2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn2HotKey.setText(settings.getWarn2HotKey());
		add(txtWarn2HotKey, "cell 7 2,alignx left");
		txtWarn2HotKey.setColumns(10);
		
		JLabel lblShotTimerHotKey = new JLabel("Start (Shot)");
		add(lblShotTimerHotKey, "cell 9 2,alignx trailing");
		
		txtShotTimerHotKey = new JTextField();
		txtShotTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShotTimerHotKey.setText(settings.getShotTimerHotKey());
		add(txtShotTimerHotKey, "cell 10 2,alignx left");
		txtShotTimerHotKey.setColumns(10);
		
		JLabel lblTeamNames = new JLabel("Team Names:");
		add(lblTeamNames, "cell 0 3,alignx right");
		
		JLabel lblScores = new JLabel("Scores:");
		add(lblScores, "cell 3 3,alignx right");
		
		JLabel lblResetSwitchHotKey = new JLabel("<-Switch-> (Reset/Warn)");
		add(lblResetSwitchHotKey, "cell 6 3,alignx trailing");
		
		txtResetSwitchHotKey = new JTextField();
		txtResetSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetSwitchHotKey.setText(settings.getResetSwitchHotKey());
		add(txtResetSwitchHotKey, "cell 7 3,alignx left");
		txtResetSwitchHotKey.setColumns(10);
		
		JLabel lblPassTimerHotKey = new JLabel("Start (Pass)");
		add(lblPassTimerHotKey, "cell 9 3,alignx trailing");
		
		txtPassTimerHotKey = new JTextField();
		txtPassTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassTimerHotKey.setText(settings.getPassTimerHotKey());
		add(txtPassTimerHotKey, "cell 10 3,alignx left");
		txtPassTimerHotKey.setColumns(10);
		
		JLabel lblTeam1ClearHotKey = new JLabel("Clear (Team 1)");
		add(lblTeam1ClearHotKey, "cell 0 4,alignx trailing");
		
		txtTeam1ClearHotKey = new JTextField();
		txtTeam1ClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1ClearHotKey.setText(settings.getTeam1ClearHotKey());
		add(txtTeam1ClearHotKey, "cell 1 4,alignx left");
		txtTeam1ClearHotKey.setColumns(10);
		
		JLabel lblScore1MinusHotKey = new JLabel("- (Team 1)");
		add(lblScore1MinusHotKey, "cell 3 4,alignx trailing");
		
		txtScore1MinusHotKey = new JTextField();
		txtScore1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1MinusHotKey.setText(settings.getScore1MinusHotKey());
		add(txtScore1MinusHotKey, "cell 4 4,alignx left");
		txtScore1MinusHotKey.setColumns(10);
		
		JLabel lblTimeOutTimerHotKey = new JLabel("Start (Time Out)");
		add(lblTimeOutTimerHotKey, "cell 9 4,alignx trailing");
		
		txtTimeOutTimerHotKey = new JTextField();
		txtTimeOutTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOutTimerHotKey.setText(settings.getTimeOutTimerHotKey());
		add(txtTimeOutTimerHotKey, "cell 10 4,alignx left");
		txtTimeOutTimerHotKey.setColumns(10);
		
		JLabel lblTeam1ForwardSwitchHotKey = new JLabel("<-Switch-> (Team 1)");
		add(lblTeam1ForwardSwitchHotKey, "cell 0 5,alignx trailing");
		
		txtTeam1ForwardSwitchHotKey = new JTextField();
		txtTeam1ForwardSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1ForwardSwitchHotKey.setText(settings.getTeam1ForwardSwitchHotKey());
		add(txtTeam1ForwardSwitchHotKey, "cell 1 5,alignx left");
		txtTeam1ForwardSwitchHotKey.setColumns(10);
		
		JLabel lblScore1PlusHotKey = new JLabel("+ (Team 1)");
		add(lblScore1PlusHotKey, "cell 3 5,alignx trailing");
		
		txtScore1PlusHotKey = new JTextField();
		txtScore1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1PlusHotKey.setText(settings.getScore1PlusHotKey());
		add(txtScore1PlusHotKey, "cell 4 5,alignx left");
		txtScore1PlusHotKey.setColumns(10);
		
		JLabel lblAllSwitchHotKey = new JLabel("<-Switch Sides->");
		add(lblAllSwitchHotKey, "cell 6 5,alignx trailing");
		
		txtAllSwitchHotKey = new JTextField();
		txtAllSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtAllSwitchHotKey.setText(settings.getAllSwitchHotKey());
		add(txtAllSwitchHotKey, "cell 7 5,alignx left");
		txtAllSwitchHotKey.setColumns(10);
		
		JLabel lblGameTimerHotKey = new JLabel("Start (Game)");
		add(lblGameTimerHotKey, "cell 9 5,alignx trailing");
		
		txtGameTimerHotKey = new JTextField();
		txtGameTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameTimerHotKey.setText(settings.getGameTimerHotKey());
		add(txtGameTimerHotKey, "cell 10 5,alignx left");
		txtGameTimerHotKey.setColumns(10);
		
		JLabel lblTeam2ClearHotKey = new JLabel("Clear (Team 2)");
		add(lblTeam2ClearHotKey, "cell 0 6,alignx trailing");
		
		txtTeam2ClearHotKey = new JTextField();
		txtTeam2ClearHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2ClearHotKey.setText(settings.getTeam2ClearHotKey());
		add(txtTeam2ClearHotKey, "cell 1 6,alignx left");
		txtTeam2ClearHotKey.setColumns(10);
		
		JLabel lblScore2MinusHotKey = new JLabel("- (Team 2)");
		add(lblScore2MinusHotKey, "cell 3 6,alignx trailing");
		
		txtScore2MinusHotKey = new JTextField();
		txtScore2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2MinusHotKey.setText(settings.getScore2MinusHotKey());
		add(txtScore2MinusHotKey, "cell 4 6,alignx left");
		txtScore2MinusHotKey.setColumns(10);
		
		JLabel lblRecallTimerHotKey = new JLabel("Start (Recall)");
		add(lblRecallTimerHotKey, "cell 9 6,alignx trailing");
		
		txtRecallTimerHotKey = new JTextField();
		txtRecallTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecallTimerHotKey.setText(settings.getRecallTimerHotKey());
		add(txtRecallTimerHotKey, "cell 10 6,alignx left");
		txtRecallTimerHotKey.setColumns(10);
		
		JLabel lblTeam2NameSwitchHotKey = new JLabel("<-Switch-> (Team 2)");
		add(lblTeam2NameSwitchHotKey, "cell 0 7,alignx trailing");
		
		txtTeam2ForwardSwitchHotKey = new JTextField();
		txtTeam2ForwardSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2ForwardSwitchHotKey.setText(settings.getTeam2ForwardSwitchHotKey());
		add(txtTeam2ForwardSwitchHotKey, "cell 1 7,alignx left");
		txtTeam2ForwardSwitchHotKey.setColumns(10);
		
		JLabel lblScore2PlusHotKey = new JLabel("+ (Team 2)");
		add(lblScore2PlusHotKey, "cell 3 7,alignx trailing");
		
		txtScore2PlusHotKey = new JTextField();
		txtScore2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2PlusHotKey.setText(settings.getScore2PlusHotKey());
		add(txtScore2PlusHotKey, "cell 4 7,alignx left,aligny top");
		txtScore2PlusHotKey.setColumns(10);
		
		JLabel lblResetScoresHotKey = new JLabel("Reset Scores");
		add(lblResetScoresHotKey, "cell 6 7,alignx trailing");
		
		txtResetScoresHotKey = new JTextField();
		txtResetScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetScoresHotKey.setText(settings.getResetScoresHotKey());
		add(txtResetScoresHotKey, "cell 7 7,alignx left");
		txtResetScoresHotKey.setColumns(10);
		
		JLabel lblResetTimersHotKey = new JLabel("Reset Timer");
		add(lblResetTimersHotKey, "cell 9 7,alignx trailing");
		
		txtResetTimersHotKey = new JTextField();
		txtResetTimersHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimersHotKey.setText(settings.getResetTimersHotKey());
		add(txtResetTimersHotKey, "cell 10 7,alignx left");
		txtResetTimersHotKey.setColumns(10);
		
		JLabel lblTeamSwitchHotKey = new JLabel("<-Switch->");
		add(lblTeamSwitchHotKey, "cell 0 8,alignx trailing");
		
		txtTeamSwitchHotKey = new JTextField();
		txtTeamSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeamSwitchHotKey.setText(settings.getTeamSwitchHotKey());
		add(txtTeamSwitchHotKey, "cell 1 8,alignx left");
		txtTeamSwitchHotKey.setColumns(10);
		
		JLabel lblScoreSwitchHotKey = new JLabel("<-Switch->");
		add(lblScoreSwitchHotKey, "cell 3 8,alignx trailing");
		
		txtScoreSwitchHotKey = new JTextField();
		txtScoreSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScoreSwitchHotKey.setText(settings.getScoreSwitchHotKey());
		add(txtScoreSwitchHotKey, "cell 4 8,alignx left");
		txtScoreSwitchHotKey.setColumns(10);
		
		JLabel lblResetGameCountsHotKey = new JLabel("Reset Game Counts");
		add(lblResetGameCountsHotKey, "cell 6 8,alignx trailing");
		
		txtResetGameCountsHotKey = new JTextField();
		txtResetGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetGameCountsHotKey.setText(settings.getResetGameCountsHotKey());
		add(txtResetGameCountsHotKey, "cell 7 8,alignx left");
		txtResetGameCountsHotKey.setColumns(10);
		
		JLabel lblGameCounts = new JLabel("Game Counts:");
		add(lblGameCounts, "cell 0 9,alignx right");
		
		JLabel lblTimeOuts = new JLabel("Time Outs:");
		add(lblTimeOuts, "cell 3 9,alignx right");
		
		JLabel lblResetTimeOutsHotKey = new JLabel("Reset Time Outs");
		add(lblResetTimeOutsHotKey, "cell 6 9,alignx trailing");
		
		txtResetTimeOutsHotKey = new JTextField();
		txtResetTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimeOutsHotKey.setText(settings.getResetTimeOutsHotKey());
		add(txtResetTimeOutsHotKey, "cell 7 9,alignx left");
		txtResetTimeOutsHotKey.setColumns(10);
		
		JLabel lblSelectPathHotKey = new JLabel("Select Path");
		add(lblSelectPathHotKey, "cell 9 9,alignx trailing");
		
		txtSelectPathHotKey = new JTextField();
		txtSelectPathHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelectPathHotKey.setText(settings.getSelectPathHotKey());
		add(txtSelectPathHotKey, "cell 10 9,alignx left");
		txtSelectPathHotKey.setColumns(10);
		
		JLabel lblGameCount1MinusHotKey = new JLabel("- (Team 1)");
		add(lblGameCount1MinusHotKey, "cell 0 10,alignx trailing");
		
		txtGameCount1MinusHotKey = new JTextField();
		txtGameCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1MinusHotKey.setText(settings.getGameCount1MinusHotKey());
		add(txtGameCount1MinusHotKey, "cell 1 10,alignx left");
		txtGameCount1MinusHotKey.setColumns(10);
		
		JLabel lblTimeOut1MinusHotKey = new JLabel("Return TO (Team 1)");
		add(lblTimeOut1MinusHotKey, "cell 3 10,alignx trailing");
		
		txtTimeOut1MinusHotKey = new JTextField();
		txtTimeOut1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1MinusHotKey.setText(settings.getTimeOut1MinusHotKey());
		add(txtTimeOut1MinusHotKey, "cell 4 10,alignx left");
		txtTimeOut1MinusHotKey.setColumns(10);
		
		JLabel lblResetResetWarnHotKey = new JLabel("Reset Reset/Warn");
		add(lblResetResetWarnHotKey, "cell 6 10,alignx trailing");
		
		txtResetResetWarnHotKey = new JTextField();
		txtResetResetWarnHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetResetWarnHotKey.setText(settings.getResetResetWarnHotKey());
		add(txtResetResetWarnHotKey, "cell 7 10,alignx left");
		txtResetResetWarnHotKey.setColumns(10);
		
		JLabel lblSetPathHotKey = new JLabel("Set Path");
		add(lblSetPathHotKey, "cell 9 10,alignx trailing");
		
		txtSetPathHotKey = new JTextField();
		txtSetPathHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSetPathHotKey.setText(settings.getSetPathHotKey());
		add(txtSetPathHotKey, "cell 10 10,alignx left");
		txtSetPathHotKey.setColumns(10);
		
		JLabel lblGameCount1PlusHotKey = new JLabel("+ (Team 1)");
		add(lblGameCount1PlusHotKey, "cell 0 11,alignx trailing");
		
		txtGameCount1PlusHotKey = new JTextField();
		txtGameCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1PlusHotKey.setText(settings.getGameCount1PlusHotKey());
		add(txtGameCount1PlusHotKey, "cell 1 11,alignx left");
		txtGameCount1PlusHotKey.setColumns(10);
		
		JLabel lblTimeOut1PlusHotKey = new JLabel("Use TO (Team 1)");
		add(lblTimeOut1PlusHotKey, "cell 3 11,alignx trailing");
		
		txtTimeOut1PlusHotKey = new JTextField();
		txtTimeOut1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1PlusHotKey.setText(settings.getTimeOut1PlusHotKey());
		add(txtTimeOut1PlusHotKey, "cell 4 11,alignx left");
		txtTimeOut1PlusHotKey.setColumns(10);
		
		JLabel lblResetAllHotKey = new JLabel("Reset All");
		add(lblResetAllHotKey, "cell 6 11,alignx trailing");
		
		txtResetAllHotKey = new JTextField();
		txtResetAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetAllHotKey.setText(settings.getResetAllHotKey());
		add(txtResetAllHotKey, "cell 7 11,alignx left");
		txtResetAllHotKey.setColumns(10);
		
		JLabel lblFetchDataHotKey = new JLabel("Fetch Data");
		add(lblFetchDataHotKey, "cell 9 11,alignx trailing");
		
		txtFetchDataHotKey = new JTextField();
		txtFetchDataHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtFetchDataHotKey.setText(settings.getFetchDataHotKey());
		add(txtFetchDataHotKey, "cell 10 11,alignx left");
		txtFetchDataHotKey.setColumns(10);
		
		JLabel lblGameCount2MinusHotKey = new JLabel("- (Team 2)");
		add(lblGameCount2MinusHotKey, "cell 0 12,alignx trailing");
		
		txtGameCount2MinusHotKey = new JTextField();
		txtGameCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2MinusHotKey.setText(settings.getGameCount2MinusHotKey());
		add(txtGameCount2MinusHotKey, "cell 1 12,alignx left");
		txtGameCount2MinusHotKey.setColumns(10);
		
		JLabel lblTimeOut2MinusHotKey = new JLabel("Return TO (Team 2)");
		add(lblTimeOut2MinusHotKey, "cell 3 12,alignx trailing");
		
		txtTimeOut2MinusHotKey = new JTextField();
		txtTimeOut2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2MinusHotKey.setText(settings.getTimeOut2MinusHotKey());
		add(txtTimeOut2MinusHotKey, "cell 4 12,alignx left");
		txtTimeOut2MinusHotKey.setColumns(10);
		
		JLabel lblSaveAllHotKey = new JLabel("Save All");
		add(lblSaveAllHotKey, "cell 9 12,alignx trailing");
		
		txtSaveAllHotKey = new JTextField();
		txtSaveAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaveAllHotKey.setText(settings.getSaveAllHotKey());
		add(txtSaveAllHotKey, "cell 10 12,alignx left");
		txtSaveAllHotKey.setColumns(10);
		
		JLabel lblGameCount2PlusHotKey = new JLabel("+ (Teamt 2)");
		add(lblGameCount2PlusHotKey, "cell 0 13,alignx trailing");
		
		txtGameCount2PlusHotKey = new JTextField();
		txtGameCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2PlusHotKey.setText(settings.getGameCount2PlusHotKey());
		add(txtGameCount2PlusHotKey, "cell 1 13,alignx left");
		txtGameCount2PlusHotKey.setColumns(10);
		
		JLabel lblTimeOut2PlusHotKey = new JLabel("Use  TO (Team 2)");
		add(lblTimeOut2PlusHotKey, "cell 3 13,alignx trailing");
		
		txtTimeOut2PlusHotKey = new JTextField();
		txtTimeOut2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2PlusHotKey.setText(settings.getTimeOut2PlusHotKey());
		add(txtTimeOut2PlusHotKey, "cell 4 13,alignx left");
		txtTimeOut2PlusHotKey.setColumns(10);
		
		JLabel lblGameCountSwitchHotKey = new JLabel("<-Switch->");
		add(lblGameCountSwitchHotKey, "cell 0 14,alignx trailing");
		
		txtGameCountSwitchHotKey = new JTextField();
		txtGameCountSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCountSwitchHotKey.setText(settings.getGameCountSwitchHotKey());
		add(txtGameCountSwitchHotKey, "cell 1 14,alignx left");
		txtGameCountSwitchHotKey.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				JOptionPane.showMessageDialog(comp, "Changed HotKeys will take affect once program is restarted.");
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		
		JLabel lblTimeOutSwitchHotKey = new JLabel("<-Switch->");
		add(lblTimeOutSwitchHotKey, "cell 3 14,alignx trailing");
		
		txtTimeOutSwitchHotKey = new JTextField();
		txtTimeOutSwitchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOutSwitchHotKey.setText(settings.getTimeOutSwitchHotKey());
		add(txtTimeOutSwitchHotKey, "cell 4 14,alignx left");
		txtTimeOutSwitchHotKey.setColumns(10);
		
		JLabel lblClearAllHotKey = new JLabel("Clear All");
		add(lblClearAllHotKey, "cell 6 14,alignx trailing");
		
		txtClearAllHotKey = new JTextField();
		txtClearAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtClearAllHotKey.setText(settings.getClearAllHotKey());
		add(txtClearAllHotKey, "cell 7 14,alignx left");
		txtClearAllHotKey.setColumns(10);
		
		JLabel lblSettingsHotKey = new JLabel("Settings");
		add(lblSettingsHotKey, "cell 9 14,alignx trailing");
		
		txtSettingsHotKey = new JTextField();
		txtSettingsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSettingsHotKey.setText(settings.getSettingsHotKey());
		add(txtSettingsHotKey, "cell 10 14,alignx left");
		txtSettingsHotKey.setColumns(10);
		add(btnSave, "cell 3 16,alignx center");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 6 16,alignx center");
		
		JButton btnRestoreDefaults = new JButton("Restore Defaults");
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 9 16,alignx center");

	}
	private void restoreDefaults(Settings settings) {
		txtTournamentNameClearHotKey.setText(settings.getDefaultTournamentNameClearHotKey());
		txtEventClearHotKey.setText(settings.getDefaultEventClearHotKey());
		txtTeam1ClearHotKey.setText(settings.getDefaultTeam1ClearHotKey());
		txtTeam1ForwardSwitchHotKey.setText(settings.getDefaultTeam1ForwardSwitchHotKey());
		txtTeam2ClearHotKey.setText(settings.getDefaultTeam2ClearHotKey());
		txtTeam2ForwardSwitchHotKey.setText(settings.getDefaultTeam2ForwardSwitchHotKey());
		txtTeamSwitchHotKey.setText(settings.getDefaultTeamSwitchHotKey());
		txtGameCount1MinusHotKey.setText(settings.getDefaultGameCount1MinusHotKey());
		txtGameCount1PlusHotKey.setText(settings.getDefaultGameCount1PlusHotKey());
		txtGameCount2MinusHotKey.setText(settings.getDefaultGameCount2MinusHotKey());
		txtGameCount2PlusHotKey.setText(settings.getDefaultGameCount2PlusHotKey());
		txtGameCountSwitchHotKey.setText(settings.getDefaultGameCountSwitchHotKey());
		txtScore1MinusHotKey.setText(settings.getDefaultScore1MinusHotKey());
		txtScore1PlusHotKey.setText(settings.getDefaultScore1PlusHotKey());
		txtScore2MinusHotKey.setText(settings.getDefaultScore2MinusHotKey());
		txtScore2PlusHotKey.setText(settings.getDefaultScore2PlusHotKey());
		txtScoreSwitchHotKey.setText(settings.getDefaultScoreSwitchHotKey());
		txtTimeOut1MinusHotKey.setText(settings.getDefaultTimeOut1MinusHotKey());
		txtTimeOut1PlusHotKey.setText(settings.getDefaultTimeOut1PlusHotKey());
		txtTimeOut2MinusHotKey.setText(settings.getDefaultTimeOut2MinusHotKey());
		txtTimeOut2PlusHotKey.setText(settings.getDefaultTimeOut2PlusHotKey());
		txtTimeOutSwitchHotKey.setText(settings.getDefaultTimeOutSwitchHotKey());
		txtReset1HotKey.setText(settings.getDefaultReset1HotKey());
		txtReset2HotKey.setText(settings.getDefaultReset2HotKey());
		txtWarn1HotKey.setText(settings.getDefaultWarn1HotKey());
		txtWarn2HotKey.setText(settings.getDefaultWarn2HotKey());
		txtResetSwitchHotKey.setText(settings.getDefaultResetSwitchHotKey());
		txtAllSwitchHotKey.setText(settings.getDefaultAllSwitchHotKey());
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
		txtSelectPathHotKey.setText(settings.getDefaultSelectPathHotKey());
		txtSetPathHotKey.setText(settings.getDefaultSetPathHotKey());
		txtFetchDataHotKey.setText(settings.getDefaultFetchDataHotKey());
		txtSaveAllHotKey.setText(settings.getDefaultSaveAllHotKey());
		txtSettingsHotKey.setText(settings.getDefaultSettingsHotKey());
	}
	
	private void saveSettings(Settings settings) {
		settings.setTournamentNameClearHotKey(txtTournamentNameClearHotKey.getText());
		settings.setEventClearHotKey(txtEventClearHotKey.getText());
		settings.setTeam1ClearHotKey(txtTeam1ClearHotKey.getText());
		settings.setTeam1ForwardSwitchHotKey(txtTeam1ForwardSwitchHotKey.getText());
		settings.setTeam2ClearHotKey(txtTeam2ClearHotKey.getText());
		settings.setTeam2ForwardSwitchHotKey(txtTeam2ForwardSwitchHotKey.getText());
		settings.setTeamSwitchHotKey(txtTeamSwitchHotKey.getText());
		settings.setGameCount1MinusHotKey(txtGameCount1MinusHotKey.getText());
		settings.setGameCount1PlusHotKey(txtGameCount1PlusHotKey.getText());
		settings.setGameCount2MinusHotKey(txtGameCount2MinusHotKey.getText());
		settings.setGameCount2PlusHotKey(txtGameCount2PlusHotKey.getText());
		settings.setGameCountSwitchHotKey(txtGameCountSwitchHotKey.getText());
		settings.setScore1MinusHotKey(txtScore1MinusHotKey.getText());
		settings.setScore1PlusHotKey(txtScore1PlusHotKey.getText());
		settings.setScore2MinusHotKey(txtScore2MinusHotKey.getText());
		settings.setScore2PlusHotKey(txtScore2PlusHotKey.getText());
		settings.setScoreSwitchHotKey(txtScoreSwitchHotKey.getText());
		settings.setTimeOut1MinusHotKey(txtTimeOut1MinusHotKey.getText());
		settings.setTimeOut1PlusHotKey(txtTimeOut1PlusHotKey.getText());
		settings.setTimeOut2MinusHotKey(txtTimeOut2MinusHotKey.getText());
		settings.setTimeOut2PlusHotKey(txtTimeOut2PlusHotKey.getText());
		settings.setTimeOutSwitchHotKey(txtTimeOutSwitchHotKey.getText());
		settings.setReset1HotKey(txtReset1HotKey.getText());
		settings.setReset2HotKey(txtReset2HotKey.getText());
		settings.setWarn1HotKey(txtWarn1HotKey.getText());
		settings.setWarn2HotKey(txtWarn2HotKey.getText());
		settings.setResetSwitchHotKey(txtResetSwitchHotKey.getText());
		settings.setAllSwitchHotKey(txtAllSwitchHotKey.getText());
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
		settings.setSelectPathHotKey(txtSelectPathHotKey.getText());
		settings.setSetPathHotKey(txtSetPathHotKey.getText());
		settings.setFetchDataHotKey(txtFetchDataHotKey.getText());
		settings.setSaveAllHotKey(txtSaveAllHotKey.getText());
		settings.setSettingsHotKey(txtSettingsHotKey.getText());
		try {
			settings.saveToConfig();
		} catch (IOException ex) {
			System.out.print("Error saving properties file: " + ex.getMessage());		
		}
	}
}
