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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class StatsDisplayPanel extends JPanel {
	private JLabel lblTeamName1;
	private JLabel lblTeamName2;
	private JLabel lblPlayerNames1;
	private JLabel lblPlayerNames2;
	private JLabel lblCompleted1;
	private JLabel lblAttempted1;
	private JLabel lblPercentage1;
	private JLabel lblCompleted2;
	private JLabel lblAttempted2;
	private JLabel lblPercentage2;
	private JLabel lblPassing;
	private JLabel lblShooting;
	private JLabel lblClearing;
	private JLabel lblTwoBarPassing;
	private JLabel lblCount1;
	private JLabel lblCount2;
	private JLabel lblShotsOnGoal;
	private JLabel lblScoring;
	private JLabel lblThreeBarScoring;
	private JLabel lblFiveBarScoring;
	private JLabel lblTwoBarScoring;
	private JLabel lblBreaks;
	private JLabel lblStuffs;
	private JLabel lblAces;
	private JLabel lblTeam1PassAttempts;
	private JLabel lblTeam1PassCompletes;
	private JLabel lblTeam1Passing;
	private JLabel lblTeam1ShotAttempts;
	private JLabel lblTeam1ShotCompletes;
	private JLabel lblTeam1Shooting;
	private JLabel lblTeam1ClearAttempts;
	private JLabel lblTeam1ClearCompletes;
	private JLabel lblTeam1Clearing;
	private JLabel lblTeam1TwoBarPassAttempts;
	private JLabel lblTeam1TwoBarPassCompletes;
	private JLabel lblTeam1TwoBarPassing;
	private JLabel lblTeam1ShotsOnGoal;
	private JLabel lblTeam1Scoring;
	private JLabel lblTeam1ThreeBarScoring;
	private JLabel lblTeam1FiveBarScoring;
	private JLabel lblTeam1TwoBarScoring;
	private JLabel lblTeam1Breaks;
	private JLabel lblTeam1Aces;
	private JLabel lblTeam1Stuffs;
	private JLabel lblTeam2PassAttempts;
	private JLabel lblTeam2PassCompletes;
	private JLabel lblTeam2Passing;
	private JLabel lblTeam2ShotAttempts;
	private JLabel lblTeam2ShotCompletes;
	private JLabel lblTeam2Shooting;
	private JLabel lblTeam2ClearAttempts;
	private JLabel lblTeam2ClearCompletes;
	private JLabel lblTeam2Clearing;
	private JLabel lblTeam2TwoBarPassAttempts;
	private JLabel lblTeam2TwoBarPassCompletes;
	private JLabel lblTeam2TwoBarPassing;
	private JLabel lblTeam2ShotsOnGoal;
	private JLabel lblTeam2Scoring;
	private JLabel lblTeam2ThreeBarScoring;
	private JLabel lblTeam2FiveBarScoring;
	private JLabel lblTeam2TwoBarScoring;
	private JLabel lblTeam2Breaks;
	private JLabel lblTeam2Aces;
	private JLabel lblTeam2Stuffs;
	private DecimalFormat df = new DecimalFormat("###.#"); //$NON-NLS-1$
	private Border innerBorder;

	public StatsDisplayPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 550;
		setPreferredSize(dim);
		setName(buildTitle());
		
		addMouseListener(new DoubleClickListener());

		lblTeamName1 = new JLabel(Messages.getString("StatsDisplayPanel.TeamName1", Settings.getGameType())); //$NON-NLS-1$
		lblTeamName2 = new JLabel(Messages.getString("StatsDisplayPanel.TeamName2", Settings.getGameType())); //$NON-NLS-1$
		lblPlayerNames1 = new JLabel(Messages.getString("StatsDisplayPanel.Team1", Settings.getGameType())); //$NON-NLS-1$
		lblPlayerNames2 = new JLabel(Messages.getString("StatsDisplayPanel.Team2", Settings.getGameType())); //$NON-NLS-1$
		lblCompleted1 = new JLabel(Messages.getString("StatsDisplayPanel.Completed1", Settings.getGameType())); //$NON-NLS-1$
		lblAttempted1 = new JLabel(Messages.getString("StatsDisplayPanel.Attempted1", Settings.getGameType())); //$NON-NLS-1$
		lblPercentage1 = new JLabel(Messages.getString("StatsDisplayPanel.Percentage1", Settings.getGameType())); //$NON-NLS-1$
		lblCompleted2 = new JLabel(Messages.getString("StatsDisplayPanel.Completed2", Settings.getGameType())); //$NON-NLS-1$
		lblAttempted2 = new JLabel(Messages.getString("StatsDisplayPanel.Attempted2", Settings.getGameType())); //$NON-NLS-1$
		lblPercentage2 = new JLabel(Messages.getString("StatsDisplayPanel.Percentage2", Settings.getGameType())); //$NON-NLS-1$
		lblPassing = new JLabel(Messages.getString("StatsDisplayPanel.Passing", Settings.getGameType())); //$NON-NLS-1$
		lblShooting = new JLabel(Messages.getString("StatsDisplayPanel.Shooting", Settings.getGameType())); //$NON-NLS-1$
		lblClearing = new JLabel(Messages.getString("StatsDisplayPanel.Clearing", Settings.getGameType())); //$NON-NLS-1$
		lblTwoBarPassing = new JLabel(Messages.getString("StatsDisplayPanel.2BarPassing", Settings.getGameType())); //$NON-NLS-1$
		lblCount1 = new JLabel(Messages.getString("StatsDisplayPanel.Count1", Settings.getGameType())); //$NON-NLS-1$
		lblCount2 = new JLabel(Messages.getString("StatsDisplayPanel.Count2", Settings.getGameType())); //$NON-NLS-1$
		lblShotsOnGoal = new JLabel(Messages.getString("StatsDisplayPanel.ShotsOnGoal", Settings.getGameType())); //$NON-NLS-1$
		lblScoring = new JLabel(Messages.getString("StatsDisplayPanel.Scoring", Settings.getGameType())); //$NON-NLS-1$
		lblThreeBarScoring = new JLabel(Messages.getString("StatsDisplayPanel.3Bar", Settings.getGameType())); //$NON-NLS-1$
		lblFiveBarScoring = new JLabel(Messages.getString("StatsDisplayPanel.5Bar", Settings.getGameType())); //$NON-NLS-1$
		lblTwoBarScoring = new JLabel(Messages.getString("StatsDisplayPanel.2Bar", Settings.getGameType())); //$NON-NLS-1$
		lblBreaks = new JLabel(Messages.getString("StatsDisplayPanel.Breaks", Settings.getGameType())); //$NON-NLS-1$
		lblAces = new JLabel(Messages.getString("StatsDisplayPanel.Aces", Settings.getGameType())); //$NON-NLS-1$
		lblStuffs = new JLabel(Messages.getString("StatsDisplayPanel.Stuffs", Settings.getGameType())); //$NON-NLS-1$
		lblTeam1PassAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1PassCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Passing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ShotAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ShotCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Shooting = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ClearAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ClearCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Clearing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1TwoBarPassAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1TwoBarPassCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1TwoBarPassing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ShotsOnGoal = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Scoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1ThreeBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1FiveBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1TwoBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Breaks = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Aces = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam1Stuffs = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2PassAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2PassCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Passing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ShotAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ShotCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Shooting = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ClearAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ClearCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Clearing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2TwoBarPassAttempts = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2TwoBarPassCompletes = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2TwoBarPassing = new JLabel(String.format("%-5s","0%")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ShotsOnGoal = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Scoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2ThreeBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2FiveBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2TwoBarScoring = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Breaks = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Aces = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeam2Stuffs = new JLabel(String.format("%-3s","0")); //$NON-NLS-1$ //$NON-NLS-2$
		lblTeamName1.setName("TeamName1");
		lblTeamName2.setName("TeamName2");
		lblPlayerNames1.setName("PlayerNames1");
		lblPlayerNames2.setName("PlayerNames2");
		lblTeam1PassAttempts.setName("Team1PassAttempts");
		lblTeam1PassCompletes.setName("Team1PassCompletes");
		lblTeam1Passing.setName("Team1Passing");
		lblTeam1ShotAttempts.setName("Team1ShotAttempts");
		lblTeam1ShotCompletes.setName("Team1ShotCompletes");
		lblTeam1Shooting.setName("Team1Shooting");
		lblTeam1ClearAttempts.setName("Team1ClearAttempts");
		lblTeam1ClearCompletes.setName("Team1ClearCompletes");
		lblTeam1Clearing.setName("Team1Clearing");
		lblTeam1TwoBarPassAttempts.setName("Team1TwoBarPassAttempts");
		lblTeam1TwoBarPassCompletes.setName("Team1TwoBarPassCompletes");
		lblTeam1TwoBarPassing.setName("Team1TwoBarPassing");
		lblTeam1ShotsOnGoal.setName("Team1ShotsOnGoal");
		lblTeam1Scoring.setName("Team1Scoring");
		lblTeam1ThreeBarScoring.setName("Team1ThreeBarScoring");
		lblTeam1FiveBarScoring.setName("Team1FiveBarScoring");
		lblTeam1TwoBarScoring.setName("Team1TwoBarScoring");
		lblTeam1Breaks.setName("Team1Breaks");
		lblTeam1Aces.setName("Team1Aces");
		lblTeam1Stuffs.setName("Team1Stuffs");
		lblTeam2PassAttempts.setName("Team2PassAttempts");
		lblTeam2PassCompletes.setName("Team2PassCompletes");
		lblTeam2Passing.setName("Team2Passing");
		lblTeam2ShotAttempts.setName("Team2ShotAttempts");
		lblTeam2ShotCompletes.setName("Team2ShotCompletes");
		lblTeam2Shooting.setName("Team2Shooting");
		lblTeam2ClearAttempts.setName("Team2ClearAttempts");
		lblTeam2ClearCompletes.setName("Team2ClearCompletes");
		lblTeam2Clearing.setName("Team2Clearing");
		lblTeam2TwoBarPassAttempts.setName("Team2TwoBarPassAttempts");
		lblTeam2TwoBarPassCompletes.setName("Team2TwoBarPassCompletes");
		lblTeam2TwoBarPassing.setName("Team2TwoBarPassing");
		lblTeam2ShotsOnGoal.setName("Team2ShotsOnGoal");
		lblTeam2Scoring.setName("Team2Scoring");
		lblTeam2ThreeBarScoring.setName("Team2ThreeBarScoring");
		lblTeam2FiveBarScoring.setName("Team2FiveBarScoring");
		lblTeam2TwoBarScoring.setName("Team2TwoBarScoring");
		lblTeam2Breaks.setName("Team2Breaks");
		lblTeam2Aces.setName("Team2Aces");
		lblTeam2Stuffs.setName("Team2Stuffs");
		
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = -1;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeamName1, gc);

		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 4;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeamName2, gc);

		//////// Player Names ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblPlayerNames1, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 4;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblPlayerNames2, gc);
		
		////////Labels   //////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblCompleted1, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblAttempted1, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblPercentage1, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 4;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblPercentage2, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 5;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblCompleted2, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 6;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblAttempted2, gc);

		//////// Passing Row ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1PassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1PassAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Passing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblPassing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Passing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2PassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2PassAttempts, gc);
		
		//////// Shooting Row ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ShotCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ShotAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Shooting, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblShooting, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Shooting, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ShotCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ShotAttempts, gc);
		
		//////// Clearing Row ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ClearCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ClearAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Clearing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblClearing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Clearing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ClearCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ClearAttempts, gc);
		
		/////// Two Bar Passing Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1TwoBarPassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1TwoBarPassAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1TwoBarPassing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTwoBarPassing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2TwoBarPassing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2TwoBarPassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2TwoBarPassAttempts, gc);
		
		/////// Count Label Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblCount1, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 4;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblCount2, gc);

		/////// ShotsOnGoal Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ShotsOnGoal, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblShotsOnGoal, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ShotsOnGoal, gc);
		
		/////// Scoring Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Scoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Scoring, gc);
		
		/////// ThreeBarScoring Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1ThreeBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblThreeBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2ThreeBarScoring, gc);
		
		/////// FiveBarScoring Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1FiveBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblFiveBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2FiveBarScoring, gc);
		
		/////// TwoBarScoring Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1TwoBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTwoBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2TwoBarScoring, gc);
		
		/////// Breaks Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Breaks, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblBreaks, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Breaks, gc);
		
		/////// Stuffs Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Stuffs, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblStuffs, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Stuffs, gc);
		
		/////// Aces Row \\\\\\
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam1Aces, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblAces, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblTeam2Aces, gc);
	}

	////// Utility Methods \\\\\\
	public void updatePassStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1PassCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam1PassAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam1Passing.setText(String.format("%-5s",df.format(percent) + "%")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (teamNbr==2) {
			lblTeam2PassCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam2PassAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam2Passing.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void updateShotStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1ShotCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam1ShotAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam1Shooting.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (teamNbr==2) {
			lblTeam2ShotCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam2ShotAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam2Shooting.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void updateClearStats(int teamNbr, int completes, int attempts, Float percent) {
		if (teamNbr==1) {
			lblTeam1ClearCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam1ClearAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam1Clearing.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (teamNbr==2) {
			lblTeam2ClearCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam2ClearAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam2Clearing.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void updateTwoBarPassStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1TwoBarPassCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam1TwoBarPassAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam1TwoBarPassing.setText(String.format("%-5s",df.format(percent) + "%")); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (teamNbr==2) {
			lblTeam2TwoBarPassCompletes.setText(String.format("%-3s",Integer.toString(completes))); //$NON-NLS-1$
			lblTeam2TwoBarPassAttempts.setText(String.format("%-3s",Integer.toString(attempts))); //$NON-NLS-1$
			lblTeam2TwoBarPassing.setText(String.format("%-5s",df.format(percent)+"%")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void updateScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1Scoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2Scoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		}
	}
	public void updateThreeBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1ThreeBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2ThreeBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		}
	}
	public void updateFiveBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1FiveBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2FiveBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		}
	}
	public void updateTwoBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1TwoBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2TwoBarScoring.setText(String.format("%-3s", Integer.toString(scores))); //$NON-NLS-1$
		}
	}
	public void updateShotsOnGoal(int teamNbr, int sog) {
		if(teamNbr==1) {
			lblTeam1ShotsOnGoal.setText(String.format("%-3s", Integer.toString(sog))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2ShotsOnGoal.setText(String.format("%-3s", Integer.toString(sog))); //$NON-NLS-1$
		}
	}
	public void updateStuffs(int teamNbr, int stuffs) {
		if(teamNbr==1) {
			lblTeam1Stuffs.setText(String.format("%-3s", Integer.toString(stuffs))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2Stuffs.setText(String.format("%-3s", Integer.toString(stuffs))); //$NON-NLS-1$
		}
	}
	public void updateBreaks(int teamNbr, int breaks) {
		if(teamNbr==1) {
			lblTeam1Breaks.setText(String.format("%-3s", Integer.toString(breaks))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2Breaks.setText(String.format("%-3s", Integer.toString(breaks))); //$NON-NLS-1$
		}
	}
	public void updateAces(int teamNbr, int aces) {
		if(teamNbr==1) {
			lblTeam1Aces.setText(String.format("%-3s", Integer.toString(aces))); //$NON-NLS-1$
		} else if (teamNbr==2) {
			lblTeam2Aces.setText(String.format("%-3s", Integer.toString(aces))); //$NON-NLS-1$
		}
	}
	public void updateTeams(int teamNbr, String name, String teamName) {
		if(teamNbr==1) {
			lblTeamName1.setText(teamName);
			lblPlayerNames1.setText(name);
		} else if (teamNbr==2) {
			lblTeamName2.setText(teamName);
			lblPlayerNames2.setText(name);
		}
	}
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("StatsDisplayPanel.StatisticsDisplayPanel", Settings.getGameType()); //$NON-NLS-1$
	}
	public void copyLabelsToClipboard() {
		Component[] components = getComponents();
		StringBuilder lines = new StringBuilder();
		for (Component component:components) {
			if (component instanceof JLabel) {
				JLabel label = (JLabel) component;
				String name = label.getName();
				String value = label.getText();
				if (name != null && !value.isEmpty()) lines.append(name + ": " + value).append("\n"); 
			}
		}
		StringSelection selection = new StringSelection(lines.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
	}
	private class DoubleClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				copyLabelsToClipboard();
			}
		}
	}
}
