/**
Copyright © 2021-2025 Hugh Garner
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

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class SourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTeam1Name;
	private JTextField txtTeam2Name;
	private JTextField txtTeam3Name;
	private JTextField txtTeam1Forward;
	private JTextField txtTeam2Forward;
	private JTextField txtTeam3Forward;
	private JTextField txtTeam1Goalie;
	private JTextField txtTeam2Goalie;
	private JTextField txtTeam3Goalie;
	private JTextField txtTeam1Score;
	private JTextField txtTeam2Score;
	private JTextField txtTeam3Score;
	private JTextField txtTeam1GameCount;
	private JTextField txtTeam2GameCount;
	private JTextField txtTeam3GameCount;
	private JTextField txtTeam1MatchCount;
	private JTextField txtTeam2MatchCount;
	private JTextField txtTeam3MatchCount;
	private JTextField txtTeam1TimeOut;
	private JTextField txtTeam2TimeOut;
	private JTextField txtTeam3TimeOut;
	private JTextField txtTeam1Reset;
	private JTextField txtTeam2Reset;
	private JTextField txtTeam3Reset;
	private JTextField txtTeam1Warn;
	private JTextField txtTeam2Warn;
	private JTextField txtTeam3Warn;
	private JTextField txtTeam1KingSeat;
	private JTextField txtTeam2KingSeat;
	private JTextField txtTeam3KingSeat;
	private JTextField txtTeam1Game1Show;
	private JTextField txtTeam2Game1Show;
	private JTextField txtTeam3Game1Show;
	private JTextField txtTeam1Game2Show;
	private JTextField txtTeam2Game2Show;
	private JTextField txtTeam3Game2Show;
	private JTextField txtTeam1Game3Show;
	private JTextField txtTeam2Game3Show;
	private JTextField txtTeam3Game3Show;
	private JTextField txtTournament;
	private JTextField txtEvent;
	private JTextField txtTableName;
	private JTextField txtTimerInUse;
	private JTextField txtTimeRemaining;
	private JTextField txtGameTime;
	private JTextField txtMatchTime;
	private JTextField txtStreamTime;
	private JTextField txtLastScored;
	private JTextField txtMatchWinner;
	private JTextField txtMeatball;
	private JTextField txtGameResults;
	private JTextField txtShowScores;
	private JTextField txtShowTimer;
	private JTextField txtShowCutthroat;
	private JButton btnApply;
	private JButton btnSave;
    private final Map<String, JTextField> sourcesMap = new HashMap<>();
	private static final String TEAM1 = "1"; //$NON-NLS-1$
	private static final String TEAM2 = "2"; //$NON-NLS-1$
	private static final String TEAM3 = "3"; //$NON-NLS-1$
	private static final transient Logger logger = LoggerFactory.getLogger(SourcesPanel.class);
	// Create the Panel.
	public SourcesPanel() {
		setupLayout();
		loadSourceMap();
	}
	private void loadSourceMap() {
		sourcesMap.put("Tournament",txtTournament); //$NON-NLS-1$
		sourcesMap.put("Event",txtEvent); //$NON-NLS-1$
		sourcesMap.put("Team1Name",txtTeam1Name); //$NON-NLS-1$
		sourcesMap.put("Team1Forward",txtTeam1Forward); //$NON-NLS-1$
		sourcesMap.put("Team1Goalie",txtTeam1Goalie); //$NON-NLS-1$
		sourcesMap.put("Team2Name",txtTeam2Name); //$NON-NLS-1$
		sourcesMap.put("Team2Forward",txtTeam2Forward); //$NON-NLS-1$
		sourcesMap.put("Team2Goalie",txtTeam2Goalie); //$NON-NLS-1$
		sourcesMap.put("Team3Name",txtTeam3Name); //$NON-NLS-1$
		sourcesMap.put("Team3Forward",txtTeam3Forward); //$NON-NLS-1$
		sourcesMap.put("Team3Goalie",txtTeam3Goalie); //$NON-NLS-1$
		sourcesMap.put("TableName",txtTableName); //$NON-NLS-1$
		sourcesMap.put("Team1GameCount",txtTeam1GameCount); //$NON-NLS-1$
		sourcesMap.put("Team2GameCount",txtTeam2GameCount); //$NON-NLS-1$
		sourcesMap.put("Team3GameCount",txtTeam3GameCount); //$NON-NLS-1$
		sourcesMap.put("Team1MatchCount",txtTeam1MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team2MatchCount",txtTeam2MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team3MatchCount",txtTeam3MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team1Score",txtTeam1Score); //$NON-NLS-1$
		sourcesMap.put("Team2Score",txtTeam2Score); //$NON-NLS-1$
		sourcesMap.put("Team3Score",txtTeam3Score); //$NON-NLS-1$
		sourcesMap.put("Team1TimeOut",txtTeam1TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team2TimeOut",txtTeam2TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team3TimeOut",txtTeam3TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team1Reset",txtTeam1Reset); //$NON-NLS-1$
		sourcesMap.put("Team2Reset",txtTeam2Reset); //$NON-NLS-1$
		sourcesMap.put("Team3Reset",txtTeam3Reset); //$NON-NLS-1$
		sourcesMap.put("Team1Warn",txtTeam1Warn); //$NON-NLS-1$
		sourcesMap.put("Team2Warn",txtTeam2Warn); //$NON-NLS-1$
		sourcesMap.put("Team3Warn",txtTeam3Warn); //$NON-NLS-1$
		sourcesMap.put("Team1KingSeat",txtTeam1KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team2KingSeat",txtTeam2KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team3KingSeat",txtTeam3KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team1Game1Show",txtTeam1Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game1Show",txtTeam2Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game1Show",txtTeam3Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team1Game2Show",txtTeam1Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game2Show",txtTeam2Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game2Show",txtTeam3Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team1Game3Show",txtTeam1Game3Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game3Show",txtTeam2Game3Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game3Show",txtTeam3Game3Show); //$NON-NLS-1$
		sourcesMap.put("TimeRemaining",txtTimeRemaining); //$NON-NLS-1$
		sourcesMap.put("TimerInUse",txtTimerInUse); //$NON-NLS-1$
		sourcesMap.put("MatchWinner",txtMatchWinner); //$NON-NLS-1$
		sourcesMap.put("Meatball",txtMeatball); //$NON-NLS-1$
		sourcesMap.put("GameResults",txtGameResults); //$NON-NLS-1$
		sourcesMap.put("LastScored",txtLastScored); //$NON-NLS-1$
		sourcesMap.put("GameTime",txtGameTime); //$NON-NLS-1$
		sourcesMap.put("MatchTime",txtMatchTime); //$NON-NLS-1$
		sourcesMap.put("StreamTime",txtStreamTime); //$NON-NLS-1$
		sourcesMap.put("ShowScores",txtShowScores); //$NON-NLS-1$
		sourcesMap.put("ShowTimer",txtShowTimer); //$NON-NLS-1$
		sourcesMap.put("ShowCutthroat",txtShowCutthroat); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getDefaultSource(sourceName));
		});
	}
	private void revertChanges() {
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getSourceParameter(sourceName));
		});
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		sourcesMap.forEach((sourceName, textField) -> {
			Settings.setSource(sourceName, textField.getText());
		});
		try {
			Settings.saveSourceConfig();
			okToCloseWindow = true;
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
		return okToCloseWindow;
	}
	private void setupLayout() {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Column Headers
		JLabel lblTeam1Column = new JLabel(Messages.getString("SourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column = new JLabel(Messages.getString("SourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam3Column = new JLabel(Messages.getString("SourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol1 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol1, "cell 2 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol2 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol2, "cell 3 2,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblSourceCol3 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol4 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol4.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
		//Team Name
		JLabel lblName = new JLabel(Messages.getString("SourcesPanel.Name")); //$NON-NLS-1$
		add(lblName, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam1Name = new JTextField();
		txtTeam1Name.setText(Settings.getTeamSourceParameter(TEAM1,"Name")); //$NON-NLS-1$
		txtTeam1Name.setColumns(10);
		add(txtTeam1Name, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2Name = new JTextField();
		txtTeam2Name.setText(Settings.getTeamSourceParameter(TEAM2,"Name")); //$NON-NLS-1$
		txtTeam2Name.setColumns(10);
		add(txtTeam2Name, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam3Name = new JTextField();
		txtTeam3Name.setText(Settings.getTeamSourceParameter(TEAM3,"Name")); //$NON-NLS-1$
		txtTeam3Name.setColumns(10);
		add(txtTeam3Name, "cell 4 3,alignx left"); //$NON-NLS-1$
		//Forward Name
		JLabel lblTeam1Forward = new JLabel(Messages.getString("SourcesPanel.ForwardName")); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1Forward = new JTextField();
		txtTeam1Forward.setText(Settings.getTeamSourceParameter(TEAM1,"Forward")); //$NON-NLS-1$
		txtTeam1Forward.setColumns(10);
		add(txtTeam1Forward, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam2Forward = new JTextField();
		txtTeam2Forward.setText(Settings.getTeamSourceParameter(TEAM2,"Forward")); //$NON-NLS-1$
		txtTeam2Forward.setColumns(10);
		add(txtTeam2Forward, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam3Forward = new JTextField();
		txtTeam3Forward.setText(Settings.getTeamSourceParameter(TEAM3,"Forward")); //$NON-NLS-1$
		txtTeam3Forward.setColumns(10);
		add(txtTeam3Forward, "cell 4 4,alignx left"); //$NON-NLS-1$
		//Goalie Name
		JLabel lblGoalieName = new JLabel(Messages.getString("SourcesPanel.GoalieName")); //$NON-NLS-1$
		add(lblGoalieName, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam1Goalie = new JTextField();
		txtTeam1Goalie.setText(Settings.getTeamSourceParameter(TEAM1,"Goalie")); //$NON-NLS-1$
		txtTeam1Goalie.setColumns(10);
		add(txtTeam1Goalie, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam2Goalie = new JTextField();
		txtTeam2Goalie.setText(Settings.getTeamSourceParameter(TEAM2,"Goalie")); //$NON-NLS-1$
		txtTeam2Goalie.setColumns(10);
		add(txtTeam2Goalie, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtTeam3Goalie = new JTextField();
		txtTeam3Goalie.setText(Settings.getTeamSourceParameter(TEAM3,"Goalie")); //$NON-NLS-1$
		txtTeam3Goalie.setColumns(10);
		add(txtTeam3Goalie, "cell 4 5,alignx left"); //$NON-NLS-1$
		//Score
		JLabel lblScore = new JLabel(Messages.getString("SourcesPanel.Score")); //$NON-NLS-1$
		add(lblScore, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1Score = new JTextField();
		txtTeam1Score.setText(Settings.getTeamSourceParameter(TEAM1,"Score")); //$NON-NLS-1$
		txtTeam1Score.setColumns(10);
		add(txtTeam1Score, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTeam2Score = new JTextField();
		txtTeam2Score.setText(Settings.getTeamSourceParameter(TEAM2,"Score")); //$NON-NLS-1$
		txtTeam2Score.setColumns(10);
		add(txtTeam2Score, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtTeam3Score = new JTextField();
		txtTeam3Score.setText(Settings.getTeamSourceParameter(TEAM3,"Score")); //$NON-NLS-1$
		txtTeam3Score.setColumns(10);
		add(txtTeam3Score, "cell 4 6,alignx left"); //$NON-NLS-1$
		//Game Count
		JLabel lblGameCount = new JLabel(Messages.getString("SourcesPanel.GameCount")); //$NON-NLS-1$
		add(lblGameCount, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam1GameCount = new JTextField();
		txtTeam1GameCount.setText(Settings.getTeamSourceParameter(TEAM1,"GameCount")); //$NON-NLS-1$
		txtTeam1GameCount.setColumns(10);
		add(txtTeam1GameCount, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtTeam2GameCount = new JTextField();
		txtTeam2GameCount.setText(Settings.getTeamSourceParameter(TEAM2,"GameCount")); //$NON-NLS-1$
		txtTeam2GameCount.setColumns(10);
		add(txtTeam2GameCount, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtTeam3GameCount = new JTextField();
		txtTeam3GameCount.setText(Settings.getTeamSourceParameter(TEAM3,"GameCount")); //$NON-NLS-1$
		txtTeam3GameCount.setColumns(10);
		add(txtTeam3GameCount, "cell 4 7,alignx left"); //$NON-NLS-1$
		//Match Count
		JLabel lblMatchCount = new JLabel(Messages.getString("SourcesPanel.MatchCount")); //$NON-NLS-1$
		add(lblMatchCount, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1MatchCount = new JTextField();
		txtTeam1MatchCount.setText(Settings.getTeamSourceParameter(TEAM1,"MatchCount")); //$NON-NLS-1$
		txtTeam1MatchCount.setColumns(10);
		add(txtTeam1MatchCount, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTeam2MatchCount = new JTextField();
		txtTeam2MatchCount.setText(Settings.getTeamSourceParameter(TEAM2,"MatchCount")); //$NON-NLS-1$
		txtTeam2MatchCount.setColumns(10);
		add(txtTeam2MatchCount, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtTeam3MatchCount = new JTextField();
		txtTeam3MatchCount.setText(Settings.getTeamSourceParameter(TEAM3,"MatchCount")); //$NON-NLS-1$
		txtTeam3MatchCount.setColumns(10);
		add(txtTeam3MatchCount, "cell 4 8,alignx left"); //$NON-NLS-1$
		//Time Out
		JLabel lblTimeOut = new JLabel(Messages.getString("SourcesPanel.TimeOut")); //$NON-NLS-1$
		add(lblTimeOut, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam1TimeOut = new JTextField();
		txtTeam1TimeOut.setText(Settings.getTeamSourceParameter(TEAM1,"TimeOut")); //$NON-NLS-1$
		txtTeam1TimeOut.setColumns(10);
		add(txtTeam1TimeOut, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTeam2TimeOut = new JTextField();
		txtTeam2TimeOut.setText(Settings.getTeamSourceParameter(TEAM2,"TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOut.setColumns(10);
		add(txtTeam2TimeOut, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtTeam3TimeOut = new JTextField();
		txtTeam3TimeOut.setText(Settings.getTeamSourceParameter(TEAM3,"TimeOut")); //$NON-NLS-1$
		txtTeam3TimeOut.setColumns(10);
		add(txtTeam3TimeOut, "cell 4 9,alignx left"); //$NON-NLS-1$
		//Reset
		JLabel lblReset = new JLabel(Messages.getString("SourcesPanel.Reset")); //$NON-NLS-1$
		add(lblReset, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1Reset = new JTextField();
		txtTeam1Reset.setText(Settings.getTeamSourceParameter(TEAM1,"Reset")); //$NON-NLS-1$
		txtTeam1Reset.setColumns(10);
		add(txtTeam1Reset, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTeam2Reset = new JTextField();
		txtTeam2Reset.setText(Settings.getTeamSourceParameter(TEAM2,"Reset")); //$NON-NLS-1$
		txtTeam2Reset.setColumns(10);
		add(txtTeam2Reset, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtTeam3Reset = new JTextField();
		txtTeam3Reset.setText(Settings.getTeamSourceParameter(TEAM3,"Reset")); //$NON-NLS-1$
		txtTeam3Reset.setColumns(10);
		add(txtTeam3Reset, "cell 4 10,alignx left"); //$NON-NLS-1$
		//Warn
		JLabel lblWarn = new JLabel(Messages.getString("SourcesPanel.Warn")); //$NON-NLS-1$
		add(lblWarn, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam1Warn = new JTextField();
		txtTeam1Warn.setText(Settings.getTeamSourceParameter(TEAM1,"Warn")); //$NON-NLS-1$
		txtTeam1Warn.setColumns(10);
		add(txtTeam1Warn, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTeam2Warn = new JTextField();
		txtTeam2Warn.setText(Settings.getTeamSourceParameter(TEAM2,"Warn")); //$NON-NLS-1$
		txtTeam2Warn.setColumns(10);
		add(txtTeam2Warn, "cell 3 11,alignx left"); //$NON-NLS-1$
		txtTeam3Warn = new JTextField();
		txtTeam3Warn.setText(Settings.getTeamSourceParameter(TEAM3,"Warn")); //$NON-NLS-1$
		txtTeam3Warn.setColumns(10);
		add(txtTeam3Warn, "cell 4 11,alignx left"); //$NON-NLS-1$
		//KingSeat
		JLabel lblKingSeat = new JLabel(Messages.getString("SourcesPanel.KingSeat")); //$NON-NLS-1$
		add(lblKingSeat, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1KingSeat = new JTextField();
		txtTeam1KingSeat.setText(Settings.getTeamSourceParameter(TEAM1,"KingSeat")); //$NON-NLS-1$
		txtTeam1KingSeat.setColumns(10);
		add(txtTeam1KingSeat, "cell 2 12, alignx left"); //$NON-NLS-1$
		txtTeam2KingSeat = new JTextField();
		txtTeam2KingSeat.setText(Settings.getTeamSourceParameter(TEAM2,"KingSeat")); //$NON-NLS-1$
		txtTeam2KingSeat.setColumns(10);
		add(txtTeam2KingSeat, "cell 3 12, alignx left"); //$NON-NLS-1$
		txtTeam3KingSeat = new JTextField();
		txtTeam3KingSeat.setText(Settings.getTeamSourceParameter(TEAM3,"KingSeat")); //$NON-NLS-1$
		txtTeam3KingSeat.setColumns(10);
		add(txtTeam3KingSeat, "cell 4 12, alignx left"); //$NON-NLS-1$
		//Game 1 Show Source
		JLabel lblGame1Show = new JLabel(Messages.getString("SourcesPanel.Game1ShowSource")); //$NON-NLS-1$
		add(lblGame1Show, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam1Game1Show = new JTextField();
		txtTeam1Game1Show.setText(Settings.getTeamGameShowSource(1,1));
		txtTeam1Game1Show.setColumns(10);
		add(txtTeam1Game1Show, "cell 2 13, alignx left"); //$NON-NLS-1$
		txtTeam2Game1Show = new JTextField();
		txtTeam2Game1Show.setText(Settings.getTeamGameShowSource(2,1));
		txtTeam2Game1Show.setColumns(10);
		add(txtTeam2Game1Show, "cell 3 13, alignx left"); //$NON-NLS-1$
		txtTeam3Game1Show = new JTextField();
		txtTeam3Game1Show.setText(Settings.getTeamGameShowSource(3,1));
		txtTeam3Game1Show.setColumns(10);
		add(txtTeam3Game1Show, "cell 4 13, alignx left"); //$NON-NLS-1$
		//Game 2 Show Source
		JLabel lblGame2Show = new JLabel(Messages.getString("SourcesPanel.Game2ShowSource")); //$NON-NLS-1$
		add(lblGame2Show, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1Game2Show = new JTextField();
		txtTeam1Game2Show.setText(Settings.getTeamGameShowSource(1,2));
		txtTeam1Game2Show.setColumns(10);
		add(txtTeam1Game2Show, "cell 2 14, alignx left"); //$NON-NLS-1$
		txtTeam2Game2Show = new JTextField();
		txtTeam2Game2Show.setText(Settings.getTeamGameShowSource(2,2));
		txtTeam2Game2Show.setColumns(10);
		add(txtTeam2Game2Show, "cell 3 14, alignx left"); //$NON-NLS-1$
		txtTeam3Game2Show = new JTextField();
		txtTeam3Game2Show.setText(Settings.getTeamGameShowSource(3,2));
		txtTeam3Game2Show.setColumns(10);
		add(txtTeam3Game2Show, "cell 4 14, alignx left"); //$NON-NLS-1$
		//Game 3 Show Source
		JLabel lblGame3Show = new JLabel(Messages.getString("SourcesPanel.Game3ShowSource")); //$NON-NLS-1$
		add(lblGame3Show, "cell 1 15,alignx right"); //$NON-NLS-1$
		txtTeam1Game3Show = new JTextField();
		txtTeam1Game3Show.setText(Settings.getTeamGameShowSource(1,3));
		txtTeam1Game3Show.setColumns(10);
		add(txtTeam1Game3Show, "cell 2 15, alignx left"); //$NON-NLS-1$
		txtTeam2Game3Show = new JTextField();
		txtTeam2Game3Show.setText(Settings.getTeamGameShowSource(2,3));
		txtTeam2Game3Show.setColumns(10);
		add(txtTeam2Game3Show, "cell 3 15, alignx left"); //$NON-NLS-1$
		txtTeam3Game3Show = new JTextField();
		txtTeam3Game3Show.setText(Settings.getTeamGameShowSource(3,3));
		txtTeam3Game3Show.setColumns(10);
		add(txtTeam3Game3Show, "cell 4 15, alignx left"); //$NON-NLS-1$
		//Tournament
		JLabel lblTournament = new JLabel(Messages.getString("SourcesPanel.Tournament")); //$NON-NLS-1$
		add(lblTournament, "cell 5 3,alignx right"); //$NON-NLS-1$
		txtTournament = new JTextField();
		txtTournament.setText(Settings.getSourceParameter("Tournament")); //$NON-NLS-1$
		txtTournament.setColumns(10);
		add(txtTournament, "cell 6 3,alignx left"); //$NON-NLS-1$
		//Event
		JLabel lblEvent = new JLabel(Messages.getString("SourcesPanel.Event")); //$NON-NLS-1$
		add(lblEvent, "cell 5 4,alignx right"); //$NON-NLS-1$
		txtEvent = new JTextField();
		txtEvent.setText(Settings.getSourceParameter("Event")); //$NON-NLS-1$
		add(txtEvent, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtEvent.setColumns(10);
		//Table Name
		JLabel lblTableName = new JLabel(Messages.getString("SourcesPanel.TableName")); //$NON-NLS-1$
		add(lblTableName, "cell 5 5,alignx right"); //$NON-NLS-1$
		txtTableName = new JTextField();
		txtTableName.setText(Settings.getSourceParameter("TableName")); //$NON-NLS-1$
		txtTableName.setColumns(10);
		add(txtTableName, "cell 6 5,alignx left"); //$NON-NLS-1$
		//Timer in Use
		JLabel lblTimer = new JLabel(Messages.getString("SourcesPanel.Timer")); //$NON-NLS-1$
		add(lblTimer, "cell 5 6,alignx right"); //$NON-NLS-1$
		txtTimerInUse = new JTextField();
		txtTimerInUse.setText(Settings.getSourceParameter("TimerInUse")); //$NON-NLS-1$
		txtTimerInUse.setColumns(10);
		add(txtTimerInUse, "cell 6 6,alignx left"); //$NON-NLS-1$
		//Time Remaining
		JLabel lblTimeRemaining = new JLabel(Messages.getString("SourcesPanel.TimeRemaining")); //$NON-NLS-1$
		add(lblTimeRemaining, "cell 5 7,alignx right"); //$NON-NLS-1$
		txtTimeRemaining = new JTextField();
		txtTimeRemaining.setText(Settings.getSourceParameter("TimeRemaining")); //$NON-NLS-1$
		txtTimeRemaining.setColumns(10);
		add(txtTimeRemaining, "cell 6 7,alignx left"); //$NON-NLS-1$
		//Game Time
		JLabel lblGameTime = new JLabel(Messages.getString("SourcesPanel.GameTime")); //$NON-NLS-1$
		add(lblGameTime, "cell 5 8,alignx right"); //$NON-NLS-1$
		txtGameTime = new JTextField();
		txtGameTime.setText(Settings.getSourceParameter("GameTime")); //$NON-NLS-1$
		txtGameTime.setColumns(10);
		add(txtGameTime, "cell 6 8,alignx left"); //$NON-NLS-1$
		//Match Time
		JLabel lblMatchTime = new JLabel(Messages.getString("SourcesPanel.MatchTime")); //$NON-NLS-1$
		add(lblMatchTime, "cell 5 9,alignx right"); //$NON-NLS-1$
		txtMatchTime = new JTextField();
		txtMatchTime.setText(Settings.getSourceParameter("MatchTime")); //$NON-NLS-1$
		txtMatchTime.setColumns(10);
		add(txtMatchTime, "cell 6 9,alignx left"); //$NON-NLS-1$
		//Stream Time
		JLabel lblStreamTime = new JLabel(Messages.getString("SourcesPanel.StreamTime")); //$NON-NLS-1$
		add(lblStreamTime, "cell 5 10,alignx right"); //$NON-NLS-1$
		txtStreamTime = new JTextField();
		txtStreamTime.setText(Settings.getSourceParameter("StreamTime")); //$NON-NLS-1$
		txtStreamTime.setColumns(10);
		add(txtStreamTime, "cell 6 10,alignx left"); //$NON-NLS-1$
		//Last Scored
		JLabel lblLastScored = new JLabel(Messages.getString("SourcesPanel.LastScored")); //$NON-NLS-1$
		add(lblLastScored, "cell 5 11,alignx right"); //$NON-NLS-1$
		txtLastScored = new JTextField();
		txtLastScored.setText(Settings.getSourceParameter("LastScored")); //$NON-NLS-1$
		txtLastScored.setColumns(10);
		add(txtLastScored, "cell 6 11,alignx left"); //$NON-NLS-1$
		//Match Winner
		JLabel lblMatchWinner = new JLabel(Messages.getString("SourcesPanel.MatchWinner")); //$NON-NLS-1$
		add(lblMatchWinner, "cell 5 12,alignx right"); //$NON-NLS-1$
		txtMatchWinner = new JTextField();
		txtMatchWinner.setText(Settings.getSourceParameter("MatchWinner")); //$NON-NLS-1$
		txtMatchWinner.setColumns(10);
		add(txtMatchWinner, "cell 6 12,alignx left"); //$NON-NLS-1$
		//Meatball
		JLabel lblMeatball = new JLabel(Messages.getString("SourcesPanel.Meatball")); //$NON-NLS-1$
		add(lblMeatball, "cell 5 13,alignx right"); //$NON-NLS-1$
		txtMeatball = new JTextField();
		txtMeatball.setText(Settings.getSourceParameter("Meatball")); //$NON-NLS-1$
		txtMeatball.setColumns(10);
		add(txtMeatball, "cell 6 13,alignx left"); //$NON-NLS-1$
		//Game Results
		JLabel lblGameResults = new JLabel(Messages.getString("SourcesPanel.GameResults")); //$NON-NLS-1$
		add(lblGameResults, "cell 5 14,alignx right"); //$NON-NLS-1$
		txtGameResults = new JTextField();
		txtGameResults.setText(Settings.getSourceParameter("GameResults")); //$NON-NLS-1$
		txtGameResults.setColumns(10);
		add(txtGameResults, "cell 6 14,alignx left"); //$NON-NLS-1$
		//Show Scores
		JLabel lblShowScores = new JLabel(Messages.getString("SourcesPanel.ShowScores")); //$NON-NLS-1$
		add(lblShowScores, "cell 5 15,alignx trailing"); //$NON-NLS-1$
		txtShowScores = new JTextField();
		txtShowScores.setText(Settings.getSourceParameter("ShowScores")); //$NON-NLS-1$
		txtShowScores.setColumns(10);
		add(txtShowScores, "cell 6 15,alignx left"); //$NON-NLS-1$
		//Show Timer
		JLabel lblShowTimer = new JLabel(Messages.getString("SourcesPanel.ShowTimer")); //$NON-NLS-1$
		add(lblShowTimer, "cell 5 16,alignx trailing"); //$NON-NLS-1$
		txtShowTimer = new JTextField();
		txtShowTimer.setText(Settings.getSourceParameter("ShowTimer")); //$NON-NLS-1$
		txtShowTimer.setColumns(10);
		add(txtShowTimer, "cell 6 16,alignx left"); //$NON-NLS-1$
		//Show Cutthroat
		JLabel lblShowCutthroat = new JLabel(Messages.getString("SourcesPanel.ShowCutthroat")); //$NON-NLS-1$
		add(lblShowCutthroat, "cell 5 17,alignx trailing"); //$NON-NLS-1$
		txtShowCutthroat = new JTextField();
		txtShowCutthroat.setText(Settings.getSourceParameter("ShowCutthroatSource")); //$NON-NLS-1$
		txtShowCutthroat.setColumns(10);
		add(txtShowCutthroat, "cell 6 17,alignx left"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 2 20,alignx left"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 3 20,alignx left"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancel, "cell 4 20,alignx left"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 20,alignx left"); //$NON-NLS-1$
	}
	/////// Listeners \\\\\\\
	public void addSaveListener(ActionListener listenForBtnSaveSources) {
		btnSave.addActionListener(listenForBtnSaveSources);
	}
	public void addApplyListener(ActionListener listenForBtnApplySources) {
		btnApply.addActionListener(listenForBtnApplySources);
	}
}
