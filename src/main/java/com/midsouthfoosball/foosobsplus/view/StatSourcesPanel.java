/**
Copyright © 2023-2025 Hugh Garner
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

public class StatSourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTeam1PassAttempts;
	private JTextField txtTeam2PassAttempts;
	private JTextField txtTeam3PassAttempts;
	private JTextField txtTeam1PassCompletes;
	private JTextField txtTeam2PassCompletes;
	private JTextField txtTeam3PassCompletes;
	private JTextField txtTeam1PassPercent;
	private JTextField txtTeam2PassPercent;
	private JTextField txtTeam3PassPercent;
	private JTextField txtTeam1ShotAttempts;
	private JTextField txtTeam2ShotAttempts;
	private JTextField txtTeam3ShotAttempts;
	private JTextField txtTeam1ShotCompletes;
	private JTextField txtTeam2ShotCompletes;
	private JTextField txtTeam3ShotCompletes;
	private JTextField txtTeam1ShotPercent;
	private JTextField txtTeam2ShotPercent;
	private JTextField txtTeam3ShotPercent;
	private JTextField txtTeam1ClearAttempts;
	private JTextField txtTeam2ClearAttempts;
	private JTextField txtTeam3ClearAttempts;
	private JTextField txtTeam1ClearCompletes;
	private JTextField txtTeam2ClearCompletes;
	private JTextField txtTeam3ClearCompletes;
	private JTextField txtTeam1ClearPercent;
	private JTextField txtTeam2ClearPercent;
	private JTextField txtTeam3ClearPercent;
	private JTextField txtTeam1TwoBarPassAttempts;
	private JTextField txtTeam2TwoBarPassAttempts;
	private JTextField txtTeam3TwoBarPassAttempts;
	private JTextField txtTeam1TwoBarPassCompletes;
	private JTextField txtTeam2TwoBarPassCompletes;
	private JTextField txtTeam3TwoBarPassCompletes;
	private JTextField txtTeam1TwoBarPassPercent;
	private JTextField txtTeam2TwoBarPassPercent;
	private JTextField txtTeam3TwoBarPassPercent;
	private JTextField txtTeam1Aces;
	private JTextField txtTeam2Aces;
	private JTextField txtTeam3Aces;
	private JTextField txtTeam1Stuffs;
	private JTextField txtTeam2Stuffs;
	private JTextField txtTeam3Stuffs;
	private JTextField txtTeam1Breaks;
	private JTextField txtTeam2Breaks;
	private JTextField txtTeam3Breaks;
	private JTextField txtTeam1Scoring;
	private JTextField txtTeam2Scoring;
	private JTextField txtTeam3Scoring;
	private JTextField txtTeam1ThreeBarScoring;
	private JTextField txtTeam2ThreeBarScoring;
	private JTextField txtTeam3ThreeBarScoring;
	private JTextField txtTeam1FiveBarScoring;
	private JTextField txtTeam2FiveBarScoring;
	private JTextField txtTeam3FiveBarScoring;
	private JTextField txtTeam1TwoBarScoring;
	private JTextField txtTeam2TwoBarScoring;
	private JTextField txtTeam3TwoBarScoring;
	private JTextField txtTeam1ShotsOnGoal;
	private JTextField txtTeam2ShotsOnGoal;
	private JTextField txtTeam3ShotsOnGoal;
	private JButton btnApply;
	private JButton btnSave;
	private static final String TEAM1 = "1"; //$NON-NLS-1$
	private static final String TEAM2 = "2"; //$NON-NLS-1$
	private static final String TEAM3 = "3"; //$NON-NLS-1$
        private final Map<String, JTextField> sourcesMap = new HashMap<>();
	private static final transient Logger logger = LoggerFactory.getLogger(StatSourcesPanel.class);
	// Create the Panel.
	public StatSourcesPanel() {
		setLayout();
		loadSourceMap();
	}
	private void loadSourceMap() {
		sourcesMap.put("Team1PassAttempts",txtTeam1PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2PassAttempts",txtTeam2PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3PassAttempts",txtTeam3PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1PassCompletes",txtTeam1PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2PassCompletes",txtTeam2PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3PassCompletes",txtTeam3PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1PassPercent",txtTeam1PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team2PassPercent",txtTeam2PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team3PassPercent",txtTeam3PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team1ShotAttempts",txtTeam1ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2ShotAttempts",txtTeam2ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3ShotAttempts",txtTeam3ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1ShotCompletes",txtTeam1ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2ShotCompletes",txtTeam2ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3ShotCompletes",txtTeam3ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1ShotPercent",txtTeam1ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team2ShotPercent",txtTeam2ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team3ShotPercent",txtTeam3ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team1ClearAttempts",txtTeam1ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2ClearAttempts",txtTeam2ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3ClearAttempts",txtTeam3ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1ClearCompletes",txtTeam1ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2ClearCompletes",txtTeam2ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3ClearCompletes",txtTeam3ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1ClearPercent",txtTeam1ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team2ClearPercent",txtTeam2ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team3ClearPercent",txtTeam3ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassAttempts",txtTeam1TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassAttempts",txtTeam2TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassAttempts",txtTeam3TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassCompletes",txtTeam1TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassCompletes",txtTeam2TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassCompletes",txtTeam3TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassPercent",txtTeam1TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassPercent",txtTeam2TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassPercent",txtTeam3TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team1Aces",txtTeam1Aces); //$NON-NLS-1$
		sourcesMap.put("Team2Aces",txtTeam2Aces); //$NON-NLS-1$
		sourcesMap.put("Team3Aces",txtTeam3Aces); //$NON-NLS-1$
		sourcesMap.put("Team1Stuffs",txtTeam1Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team2Stuffs",txtTeam2Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team3Stuffs",txtTeam3Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team1Breaks",txtTeam1Breaks); //$NON-NLS-1$
		sourcesMap.put("Team2Breaks",txtTeam2Breaks); //$NON-NLS-1$
		sourcesMap.put("Team3Breaks",txtTeam3Breaks); //$NON-NLS-1$
		sourcesMap.put("Team1Scoring",txtTeam1Scoring); //$NON-NLS-1$
		sourcesMap.put("Team2Scoring",txtTeam2Scoring); //$NON-NLS-1$
		sourcesMap.put("Team3Scoring",txtTeam3Scoring); //$NON-NLS-1$
		sourcesMap.put("Team1ThreeBarScoring",txtTeam1ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2ThreeBarScoring",txtTeam2ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3ThreeBarScoring",txtTeam3ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1FiveBarScoring",txtTeam1FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2FiveBarScoring",txtTeam2FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3FiveBarScoring",txtTeam3FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarScoring",txtTeam1TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarScoring",txtTeam2TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarScoring",txtTeam3TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1ShotsOnGoal",txtTeam1ShotsOnGoal); //$NON-NLS-1$
		sourcesMap.put("Team2ShotsOnGoal",txtTeam2ShotsOnGoal); //$NON-NLS-1$
		sourcesMap.put("Team3ShotsOnGoal",txtTeam3ShotsOnGoal); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getDefaultStatsSource(sourceName));
		});
	}
	private void revertChanges() {
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getStatsSourceParameter(sourceName));
		});
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		sourcesMap.forEach((sourceName, textField) -> {
			Settings.setStatsSource(sourceName, textField.getText());
		});
		try {
			Settings.saveStatsSourceConfig();
			okToCloseWindow = true;
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
		return okToCloseWindow;
	}
	private void setLayout() {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblTeam1Column = new JLabel(Messages.getString("StatSourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column = new JLabel(Messages.getString("StatSourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam3Column = new JLabel(Messages.getString("StatSourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol1 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol1, "cell 2 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol2 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol2, "cell 3 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol3 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamPassAttempts")); //$NON-NLS-1$
		add(lblTeamPassAttempts, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam1PassAttempts = new JTextField();
		txtTeam1PassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM1,"PassAttempts")); //$NON-NLS-1$
		txtTeam1PassAttempts.setColumns(15);
		add(txtTeam1PassAttempts, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2PassAttempts = new JTextField();
		txtTeam2PassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM2,"PassAttempts")); //$NON-NLS-1$
		txtTeam2PassAttempts.setColumns(15);
		add(txtTeam2PassAttempts, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam3PassAttempts = new JTextField();
		txtTeam3PassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM3,"PassAttempts")); //$NON-NLS-1$
		txtTeam3PassAttempts.setColumns(15);
		add(txtTeam3PassAttempts, "cell 4 3,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamPassCompletes")); //$NON-NLS-1$
		add(lblTeamPassCompletes, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1PassCompletes = new JTextField();
		txtTeam1PassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM1,"PassCompletes")); //$NON-NLS-1$
		txtTeam1PassCompletes.setColumns(15);
		add(txtTeam1PassCompletes, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam2PassCompletes = new JTextField();
		txtTeam2PassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM2,"PassCompletes")); //$NON-NLS-1$
		txtTeam2PassCompletes.setColumns(15);
		add(txtTeam2PassCompletes, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam3PassCompletes = new JTextField();
		txtTeam3PassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM3,"PassCompletes")); //$NON-NLS-1$
		txtTeam3PassCompletes.setColumns(15);
		add(txtTeam3PassCompletes, "cell 4 4,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamPassPercent")); //$NON-NLS-1$
		add(lblTeamPassPercent, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam1PassPercent = new JTextField();
		txtTeam1PassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM1,"PassPercent")); //$NON-NLS-1$
		txtTeam1PassPercent.setColumns(15);
		add(txtTeam1PassPercent, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam2PassPercent = new JTextField();
		txtTeam2PassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM2,"PassPercent")); //$NON-NLS-1$
		txtTeam2PassPercent.setColumns(15);
		add(txtTeam2PassPercent, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtTeam3PassPercent = new JTextField();
		txtTeam3PassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM3,"PassPercent")); //$NON-NLS-1$
		txtTeam3PassPercent.setColumns(15);
		add(txtTeam3PassPercent, "cell 4 5,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamShotAttempts")); //$NON-NLS-1$
		add(lblTeamShotAttempts, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1ShotAttempts = new JTextField();
		txtTeam1ShotAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ShotAttempts")); //$NON-NLS-1$
		txtTeam1ShotAttempts.setColumns(15);
		add(txtTeam1ShotAttempts, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTeam2ShotAttempts = new JTextField();
		txtTeam2ShotAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ShotAttempts")); //$NON-NLS-1$
		txtTeam2ShotAttempts.setColumns(15);
		add(txtTeam2ShotAttempts, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtTeam3ShotAttempts = new JTextField();
		txtTeam3ShotAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ShotAttempts")); //$NON-NLS-1$
		txtTeam3ShotAttempts.setColumns(15);
		add(txtTeam3ShotAttempts, "cell 4 6,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamShotCompletes")); //$NON-NLS-1$
		add(lblTeamShotCompletes, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam1ShotCompletes = new JTextField();
		txtTeam1ShotCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ShotCompletes")); //$NON-NLS-1$
		txtTeam1ShotCompletes.setColumns(15);
		add(txtTeam1ShotCompletes, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtTeam2ShotCompletes = new JTextField();
		txtTeam2ShotCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ShotCompletes")); //$NON-NLS-1$
		txtTeam2ShotCompletes.setColumns(15);
		add(txtTeam2ShotCompletes, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtTeam3ShotCompletes = new JTextField();
		txtTeam3ShotCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ShotCompletes")); //$NON-NLS-1$
		txtTeam3ShotCompletes.setColumns(15);
		add(txtTeam3ShotCompletes, "cell 4 7,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamShotPercent")); //$NON-NLS-1$
		add(lblTeamShotPercent, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1ShotPercent = new JTextField();
		txtTeam1ShotPercent.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ShotPercent")); //$NON-NLS-1$
		txtTeam1ShotPercent.setColumns(15);
		add(txtTeam1ShotPercent, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTeam2ShotPercent = new JTextField();
		txtTeam2ShotPercent.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ShotPercent")); //$NON-NLS-1$
		txtTeam2ShotPercent.setColumns(15);
		add(txtTeam2ShotPercent, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtTeam3ShotPercent = new JTextField();
		txtTeam3ShotPercent.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ShotPercent")); //$NON-NLS-1$
		txtTeam3ShotPercent.setColumns(15);
		add(txtTeam3ShotPercent, "cell 4 8,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamClearAttempts")); //$NON-NLS-1$
		add(lblTeamClearAttempts, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam1ClearAttempts = new JTextField();
		txtTeam1ClearAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ClearAttempts")); //$NON-NLS-1$
		txtTeam1ClearAttempts.setColumns(15);
		add(txtTeam1ClearAttempts, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTeam2ClearAttempts = new JTextField();
		txtTeam2ClearAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ClearAttempts")); //$NON-NLS-1$
		txtTeam2ClearAttempts.setColumns(15);
		add(txtTeam2ClearAttempts, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtTeam3ClearAttempts = new JTextField();
		txtTeam3ClearAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ClearAttempts")); //$NON-NLS-1$
		txtTeam3ClearAttempts.setColumns(15);
		add(txtTeam3ClearAttempts, "cell 4 9,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamClearCompletes")); //$NON-NLS-1$
		add(lblTeamClearCompletes, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1ClearCompletes = new JTextField();
		txtTeam1ClearCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ClearCompletes")); //$NON-NLS-1$
		txtTeam1ClearCompletes.setColumns(15);
		add(txtTeam1ClearCompletes, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTeam2ClearCompletes = new JTextField();
		txtTeam2ClearCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ClearCompletes")); //$NON-NLS-1$
		txtTeam2ClearCompletes.setColumns(15);
		add(txtTeam2ClearCompletes, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtTeam3ClearCompletes = new JTextField();
		txtTeam3ClearCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ClearCompletes")); //$NON-NLS-1$
		txtTeam3ClearCompletes.setColumns(15);
		add(txtTeam3ClearCompletes, "cell 4 10,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamClearPercent")); //$NON-NLS-1$
		add(lblTeamClearPercent, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam1ClearPercent = new JTextField();
		txtTeam1ClearPercent.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ClearPercent")); //$NON-NLS-1$
		txtTeam1ClearPercent.setColumns(15);
		add(txtTeam1ClearPercent, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTeam2ClearPercent = new JTextField();
		txtTeam2ClearPercent.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ClearPercent")); //$NON-NLS-1$
		txtTeam2ClearPercent.setColumns(15);
		add(txtTeam2ClearPercent, "cell 3 11,alignx left"); //$NON-NLS-1$
		txtTeam3ClearPercent = new JTextField();
		txtTeam3ClearPercent.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ClearPercent")); //$NON-NLS-1$
		txtTeam3ClearPercent.setColumns(15);
		add(txtTeam3ClearPercent, "cell 4 11,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassAttempts = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassAttempts")); //$NON-NLS-1$
		add(lblTeamTwoBarPassAttempts, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassAttempts = new JTextField();
		txtTeam1TwoBarPassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam1TwoBarPassAttempts.setColumns(15);
		add(txtTeam1TwoBarPassAttempts, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassAttempts = new JTextField();
		txtTeam2TwoBarPassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam2TwoBarPassAttempts.setColumns(15);
		add(txtTeam2TwoBarPassAttempts, "cell 3 12,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassAttempts = new JTextField();
		txtTeam3TwoBarPassAttempts.setText(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam3TwoBarPassAttempts.setColumns(15);
		add(txtTeam3TwoBarPassAttempts, "cell 4 12,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassCompletes = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassCompletes")); //$NON-NLS-1$
		add(lblTeamTwoBarPassCompletes, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassCompletes = new JTextField();
		txtTeam1TwoBarPassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam1TwoBarPassCompletes.setColumns(15);
		add(txtTeam1TwoBarPassCompletes, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassCompletes = new JTextField();
		txtTeam2TwoBarPassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam2TwoBarPassCompletes.setColumns(15);
		add(txtTeam2TwoBarPassCompletes, "cell 3 13,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassCompletes = new JTextField();
		txtTeam3TwoBarPassCompletes.setText(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam3TwoBarPassCompletes.setColumns(15);
		add(txtTeam3TwoBarPassCompletes, "cell 4 13,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassPercent = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassPercent")); //$NON-NLS-1$
		add(lblTeamTwoBarPassPercent, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassPercent = new JTextField();
		txtTeam1TwoBarPassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam1TwoBarPassPercent.setColumns(15);
		add(txtTeam1TwoBarPassPercent, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassPercent = new JTextField();
		txtTeam2TwoBarPassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam2TwoBarPassPercent.setColumns(15);
		add(txtTeam2TwoBarPassPercent, "cell 3 14,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassPercent = new JTextField();
		txtTeam3TwoBarPassPercent.setText(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam3TwoBarPassPercent.setColumns(15);
		add(txtTeam3TwoBarPassPercent, "cell 4 14,alignx left"); //$NON-NLS-1$
		JLabel lblTeam1Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column2, "cell 6 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column2, "cell 7 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam3Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column2, "cell 8 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol4 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol4.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol5 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol5.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol5, "cell 7 2,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblSourceCol6 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol6.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol6, "cell 8 2,alignx left"); //$NON-NLS-1$
		JLabel lblAces = new JLabel(Messages.getString("StatSourcesPanel.Aces")); //$NON-NLS-1$
		add(lblAces, "cell 5 3,alignx right"); //$NON-NLS-1$
		txtTeam1Aces = new JTextField();
		txtTeam1Aces.setText(Settings.getTeamStatsSourceParameter(TEAM1,"Aces")); //$NON-NLS-1$
		txtTeam1Aces.setColumns(15);
		add(txtTeam1Aces, "cell 6 3,alignx left"); //$NON-NLS-1$
		txtTeam2Aces = new JTextField();
		txtTeam2Aces.setText(Settings.getTeamStatsSourceParameter(TEAM2,"Aces")); //$NON-NLS-1$
		txtTeam2Aces.setColumns(15);
		add(txtTeam2Aces, "cell 7 3,alignx left"); //$NON-NLS-1$
		txtTeam3Aces = new JTextField();
		txtTeam3Aces.setText(Settings.getTeamStatsSourceParameter(TEAM3,"Aces")); //$NON-NLS-1$
		txtTeam3Aces.setColumns(15);
		add(txtTeam3Aces, "cell 8 3,alignx left"); //$NON-NLS-1$
		JLabel lblStuffs = new JLabel(Messages.getString("StatSourcesPanel.Stuffs")); //$NON-NLS-1$
		add(lblStuffs, "cell 5 4,alignx right"); //$NON-NLS-1$
		txtTeam1Stuffs = new JTextField();
		txtTeam1Stuffs.setText(Settings.getTeamStatsSourceParameter(TEAM1,"Stuffs")); //$NON-NLS-1$
		txtTeam1Stuffs.setColumns(15);
		add(txtTeam1Stuffs, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtTeam2Stuffs = new JTextField();
		txtTeam2Stuffs.setText(Settings.getTeamStatsSourceParameter(TEAM2,"Stuffs")); //$NON-NLS-1$
		txtTeam2Stuffs.setColumns(15);
		add(txtTeam2Stuffs, "cell 7 4,alignx left"); //$NON-NLS-1$
		txtTeam3Stuffs = new JTextField();
		txtTeam3Stuffs.setText(Settings.getTeamStatsSourceParameter(TEAM3,"Stuffs")); //$NON-NLS-1$
		txtTeam3Stuffs.setColumns(15);
		add(txtTeam3Stuffs, "cell 8 4,alignx left"); //$NON-NLS-1$
		JLabel lblBreaks = new JLabel(Messages.getString("StatSourcesPanel.Breaks")); //$NON-NLS-1$
		add(lblBreaks, "cell 5 5,alignx right"); //$NON-NLS-1$
		txtTeam1Breaks = new JTextField();
		txtTeam1Breaks.setText(Settings.getTeamStatsSourceParameter(TEAM1,"Breaks")); //$NON-NLS-1$
		txtTeam1Breaks.setColumns(15);
		add(txtTeam1Breaks, "cell 6 5,alignx left"); //$NON-NLS-1$
		txtTeam2Breaks = new JTextField();
		txtTeam2Breaks.setText(Settings.getTeamStatsSourceParameter(TEAM2,"Breaks")); //$NON-NLS-1$
		txtTeam2Breaks.setColumns(15);
		add(txtTeam2Breaks, "cell 7 5,alignx left"); //$NON-NLS-1$
		txtTeam3Breaks = new JTextField();
		txtTeam3Breaks.setText(Settings.getTeamStatsSourceParameter(TEAM3,"Breaks")); //$NON-NLS-1$
		txtTeam3Breaks.setColumns(15);
		add(txtTeam3Breaks, "cell 8 5,alignx left"); //$NON-NLS-1$
		JLabel lblTeamScoring = new JLabel(Messages.getString("StatSourcesPanel.TeamScoring")); //$NON-NLS-1$
		add(lblTeamScoring, "cell 5 6,alignx right"); //$NON-NLS-1$
		txtTeam1Scoring = new JTextField();
		txtTeam1Scoring.setText(Settings.getTeamStatsSourceParameter(TEAM1,"Scoring")); //$NON-NLS-1$
		txtTeam1Scoring.setColumns(15);
		add(txtTeam1Scoring, "cell 6 6,alignx left"); //$NON-NLS-1$
		txtTeam2Scoring = new JTextField();
		txtTeam2Scoring.setText(Settings.getTeamStatsSourceParameter(TEAM2,"Scoring")); //$NON-NLS-1$
		txtTeam2Scoring.setColumns(15);
		add(txtTeam2Scoring, "cell 7 6,alignx left"); //$NON-NLS-1$
		txtTeam3Scoring = new JTextField();
		txtTeam3Scoring.setText(Settings.getTeamStatsSourceParameter(TEAM3,"Scoring")); //$NON-NLS-1$
		txtTeam3Scoring.setColumns(15);
		add(txtTeam3Scoring, "cell 8 6,alignx left"); //$NON-NLS-1$
		JLabel lblTeamThreeBarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team3BarScoring")); //$NON-NLS-1$
		add(lblTeamThreeBarScoring, "cell 5 7,alignx right"); //$NON-NLS-1$
		txtTeam1ThreeBarScoring = new JTextField();
		txtTeam1ThreeBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam1ThreeBarScoring.setColumns(15);
		add(txtTeam1ThreeBarScoring, "cell 6 7,alignx left"); //$NON-NLS-1$
		txtTeam2ThreeBarScoring = new JTextField();
		txtTeam2ThreeBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam2ThreeBarScoring.setColumns(15);
		add(txtTeam2ThreeBarScoring, "cell 7 7,alignx left"); //$NON-NLS-1$
		txtTeam3ThreeBarScoring = new JTextField();
		txtTeam3ThreeBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam3ThreeBarScoring.setColumns(15);
		add(txtTeam3ThreeBarScoring, "cell 8 7,alignx left"); //$NON-NLS-1$
		JLabel lblTeam5BarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team5BarScoring")); //$NON-NLS-1$
		add(lblTeam5BarScoring, "cell 5 8,alignx right"); //$NON-NLS-1$
		txtTeam1FiveBarScoring = new JTextField();
		txtTeam1FiveBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM1,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam1FiveBarScoring.setColumns(15);
		add(txtTeam1FiveBarScoring, "cell 6 8,alignx left"); //$NON-NLS-1$
		txtTeam2FiveBarScoring = new JTextField();
		txtTeam2FiveBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM2,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam2FiveBarScoring.setColumns(15);
		add(txtTeam2FiveBarScoring, "cell 7 8,alignx left"); //$NON-NLS-1$
		txtTeam3FiveBarScoring = new JTextField();
		txtTeam3FiveBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM3,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam3FiveBarScoring.setColumns(15);
		add(txtTeam3FiveBarScoring, "cell 8 8,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team2BarScoring")); //$NON-NLS-1$
		add(lblTeamTwoBarScoring, "cell 5 9,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarScoring = new JTextField();
		txtTeam1TwoBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam1TwoBarScoring.setColumns(15);
		add(txtTeam1TwoBarScoring, "cell 6 9,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarScoring = new JTextField();
		txtTeam2TwoBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam2TwoBarScoring.setColumns(15);
		add(txtTeam2TwoBarScoring, "cell 7 9,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarScoring = new JTextField();
		txtTeam3TwoBarScoring.setText(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam3TwoBarScoring.setColumns(15);
		add(txtTeam3TwoBarScoring, "cell 8 9,alignx left"); //$NON-NLS-1$
		JLabel lblTeamSOG = new JLabel(Messages.getString("StatSourcesPanel.TeamShotsOnGoal")); //$NON-NLS-1$
		add(lblTeamSOG, "cell 5 10,alignx trailing"); //$NON-NLS-1$
		txtTeam1ShotsOnGoal = new JTextField();
		txtTeam1ShotsOnGoal.setText(Settings.getTeamStatsSourceParameter(TEAM1,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam1ShotsOnGoal.setColumns(15);
		add(txtTeam1ShotsOnGoal, "cell 6 10,alignx left"); //$NON-NLS-1$
		txtTeam2ShotsOnGoal = new JTextField();
		txtTeam2ShotsOnGoal.setText(Settings.getTeamStatsSourceParameter(TEAM2,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam2ShotsOnGoal.setColumns(15);
		add(txtTeam2ShotsOnGoal, "cell 7 10,alignx left"); //$NON-NLS-1$
		txtTeam3ShotsOnGoal = new JTextField();
		txtTeam3ShotsOnGoal.setText(Settings.getTeamStatsSourceParameter(TEAM3,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam3ShotsOnGoal.setColumns(15);
		add(txtTeam3ShotsOnGoal, "cell 8 10,alignx left"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 2 15,alignx right"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 3 15,alignx center"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancel, "cell 4 15,alignx left"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 6 15,alignx left"); //$NON-NLS-1$
	}
	/////// Listeners \\\\\\\
	public void addSaveListener(ActionListener listenForBtnSaveSources) {
		btnSave.addActionListener(listenForBtnSaveSources);
	}
	public void addApplyListener(ActionListener listenForBtnApplySources) {
		btnApply.addActionListener(listenForBtnApplySources);
	}
}
