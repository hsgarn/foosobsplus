/**
Copyright 2021-2024 Hugh Garner
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class SourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtTeam1NameSource;
	private JTextField txtTeam2NameSource;
	private JTextField txtTeam3NameSource;
	private JTextField txtTeam1ForwardSource;
	private JTextField txtTeam2ForwardSource;
	private JTextField txtTeam3ForwardSource;
	private JTextField txtTeam1GoalieSource;
	private JTextField txtTeam2GoalieSource;
	private JTextField txtTeam3GoalieSource;
	private JTextField txtTeam1ScoreSource;
	private JTextField txtTeam2ScoreSource;
	private JTextField txtTeam3ScoreSource;
	private JTextField txtTeam1GameCountSource;
	private JTextField txtTeam2GameCountSource;
	private JTextField txtTeam3GameCountSource;
	private JTextField txtTeam1MatchCountSource;
	private JTextField txtTeam2MatchCountSource;
	private JTextField txtTeam3MatchCountSource;
	private JTextField txtTeam1TimeOutSource;
	private JTextField txtTeam2TimeOutSource;
	private JTextField txtTeam3TimeOutSource;
	private JTextField txtTeam1ResetSource;
	private JTextField txtTeam2ResetSource;
	private JTextField txtTeam3ResetSource;
	private JTextField txtTeam1WarnSource;
	private JTextField txtTeam2WarnSource;
	private JTextField txtTeam3WarnSource;
	private JTextField txtTeam1KingSeatSource;
	private JTextField txtTeam2KingSeatSource;
	private JTextField txtTeam3KingSeatSource;
	private JTextField txtTeam1Game1ShowSource;
	private JTextField txtTeam2Game1ShowSource;
	private JTextField txtTeam3Game1ShowSource;
	private JTextField txtTeam1Game2ShowSource;
	private JTextField txtTeam2Game2ShowSource;
	private JTextField txtTeam3Game2ShowSource;
	private JTextField txtTeam1Game3ShowSource;
	private JTextField txtTeam2Game3ShowSource;
	private JTextField txtTeam3Game3ShowSource;
	private JTextField txtTournamentSource;
	private JTextField txtEventSource;
	private JTextField txtTableNameSource;
	private JTextField txtTimerInUseSource;
	private JTextField txtTimeRemainingSource;
	private JTextField txtGameTimeSource;
	private JTextField txtMatchTimeSource;
	private JTextField txtStreamTimeSource;
	private JTextField txtLastScoredSource;
	private JTextField txtMatchWinnerSource;
	private JTextField txtMeatballSource;
	private JTextField txtGameResultsSource;
	private JTextField txtShowScoresSource;
	private JTextField txtShowTimerSource;
	private JTextField txtShowCutthroatSource;
	private JButton btnSaveSources;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$
	private static transient Logger logger = LoggerFactory.getLogger(SourcesPanel.class);
	// Create the Panel.
	public SourcesPanel(OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;
		setLayout();
	}
	private void restoreDefaults() {
		txtTournamentSource.setText(Settings.getDefaultTournamentSource());
		txtEventSource.setText(Settings.getDefaultEventSource());
		txtTeam1NameSource.setText(Settings.getDefaultTeam1NameSource());
		txtTeam1ForwardSource.setText(Settings.getDefaultTeam1ForwardSource());
		txtTeam1GoalieSource.setText(Settings.getDefaultTeam1GoalieSource());
		txtTeam2NameSource.setText(Settings.getDefaultTeam2NameSource());
		txtTeam2ForwardSource.setText(Settings.getDefaultTeam2ForwardSource());
		txtTeam2GoalieSource.setText(Settings.getDefaultTeam2GoalieSource());
		txtTeam3NameSource.setText(Settings.getDefaultTeam3NameSource());
		txtTeam3ForwardSource.setText(Settings.getDefaultTeam3ForwardSource());
		txtTeam3GoalieSource.setText(Settings.getDefaultTeam3GoalieSource());
		txtTableNameSource.setText(Settings.getDefaultTableNameSource());
		txtTeam1GameCountSource.setText(Settings.getDefaultTeam1GameCountSource());
		txtTeam2GameCountSource.setText(Settings.getDefaultTeam2GameCountSource());
		txtTeam3GameCountSource.setText(Settings.getDefaultTeam3GameCount3Source());
		txtTeam1MatchCountSource.setText(Settings.getDefaultTeam1MatchCountSource());
		txtTeam2MatchCountSource.setText(Settings.getDefaultTeam2MatchCountSource());
		txtTeam3MatchCountSource.setText(Settings.getDefaultTeam3MatchCount3Source());
		txtTeam1ScoreSource.setText(Settings.getDefaultTeam1ScoreSource());
		txtTeam2ScoreSource.setText(Settings.getDefaultTeam2ScoreSource());
		txtTeam3ScoreSource.setText(Settings.getDefaultTeam3ScoreSource());
		txtTeam1TimeOutSource.setText(Settings.getDefaultTeam1TimeOutSource());
		txtTeam2TimeOutSource.setText(Settings.getDefaultTeam2TimeOutSource());
		txtTeam3TimeOutSource.setText(Settings.getDefaultTeam3TimeOutSource());
		txtTeam1ResetSource.setText(Settings.getDefaultTeam1ResetSource());
		txtTeam2ResetSource.setText(Settings.getDefaultTeam2ResetSource());
		txtTeam3ResetSource.setText(Settings.getDefaultTeam3ResetSource());
		txtTeam1WarnSource.setText(Settings.getDefaultTeam1WarnSource());
		txtTeam2WarnSource.setText(Settings.getDefaultTeam2WarnSource());
		txtTeam3WarnSource.setText(Settings.getDefaultTeam3WarnSource());
		txtTeam1KingSeatSource.setText(Settings.getDefaultTeam1KingSeatSource());
		txtTeam2KingSeatSource.setText(Settings.getDefaultTeam2KingSeatSource());
		txtTeam3KingSeatSource.setText(Settings.getDefaultTeam3KingSeatSource());
		txtTeam1Game1ShowSource.setText(Settings.getDefaultTeamGameShowSource("1","1"));
		txtTeam2Game1ShowSource.setText(Settings.getDefaultTeamGameShowSource("2","1"));
		txtTeam3Game1ShowSource.setText(Settings.getDefaultTeamGameShowSource("3","1"));
		txtTeam1Game2ShowSource.setText(Settings.getDefaultTeamGameShowSource("1","2"));
		txtTeam2Game2ShowSource.setText(Settings.getDefaultTeamGameShowSource("2","2"));
		txtTeam3Game2ShowSource.setText(Settings.getDefaultTeamGameShowSource("3","2"));
		txtTeam1Game3ShowSource.setText(Settings.getDefaultTeamGameShowSource("1","3"));
		txtTeam2Game3ShowSource.setText(Settings.getDefaultTeamGameShowSource("2","3"));
		txtTeam3Game3ShowSource.setText(Settings.getDefaultTeamGameShowSource("3","3"));
		txtTimeRemainingSource.setText(Settings.getDefaultTimeRemainingSource());
		txtTimerInUseSource.setText(Settings.getDefaultTimerInUseSource());
		txtMatchWinnerSource.setText(Settings.getDefaultMatchWinnerSource());
		txtMeatballSource.setText(Settings.getDefaultMeatballSource());
		txtGameResultsSource.setText(Settings.getDefaultGameResultsSource());
		txtLastScoredSource.setText(Settings.getDefaultLastScoredSource());
		txtGameTimeSource.setText(Settings.getDefaultGameTimeSource());
		txtMatchTimeSource.setText(Settings.getDefaultMatchTimeSource());
		txtStreamTimeSource.setText(Settings.getDefaultStreamTimeSource());
		txtShowScoresSource.setText(Settings.getDefaultShowScoresSource());
		txtShowTimerSource.setText(Settings.getDefaultShowTimerSource());
		txtShowCutthroatSource.setText(Settings.getDefaultShowCutthroatSource());
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		Settings.setSource("Tournament",txtTournamentSource.getText());
		Settings.setSource("Event",txtEventSource.getText());
		Settings.setSource("TableName",txtTableNameSource.getText());
		Settings.setSource("Team1Name",txtTeam1NameSource.getText());
		Settings.setSource("Team1Forward",txtTeam1ForwardSource.getText());
		Settings.setSource("Team1Goalie",txtTeam1GoalieSource.getText());
		Settings.setSource("Team2Name",txtTeam2NameSource.getText());
		Settings.setSource("Team2Forward",txtTeam2ForwardSource.getText());
		Settings.setSource("Team2Goalie",txtTeam2GoalieSource.getText());
		Settings.setSource("Team3Name",txtTeam3NameSource.getText());
		Settings.setSource("Team3Forward",txtTeam3ForwardSource.getText());
		Settings.setSource("Team3Goalie",txtTeam3GoalieSource.getText());
		Settings.setSource("Team1GameCount",txtTeam1GameCountSource.getText());
		Settings.setSource("Team2GameCount",txtTeam2GameCountSource.getText());
		Settings.setSource("Team3GameCount",txtTeam3GameCountSource.getText());
		Settings.setSource("Team1MatchCount",txtTeam1MatchCountSource.getText());
		Settings.setSource("Team2MatchCount",txtTeam2MatchCountSource.getText());
		Settings.setSource("Team3MatchCount",txtTeam3MatchCountSource.getText());
		Settings.setSource("Team1Score",txtTeam1ScoreSource.getText());
		Settings.setSource("Team2Score",txtTeam2ScoreSource.getText());
		Settings.setSource("Team3Score",txtTeam3ScoreSource.getText());
		Settings.setSource("Team1TimeOut",txtTeam1TimeOutSource.getText());
		Settings.setSource("Team2TimeOut",txtTeam2TimeOutSource.getText());
		Settings.setSource("Team3TimeOut",txtTeam3TimeOutSource.getText());
		Settings.setSource("Team1Reset",txtTeam1ResetSource.getText());
		Settings.setSource("Team2Reset",txtTeam2ResetSource.getText());
		Settings.setSource("Team3Reset",txtTeam3ResetSource.getText());
		Settings.setSource("Team1Warn",txtTeam1WarnSource.getText());
		Settings.setSource("Team2Warn",txtTeam2WarnSource.getText());
		Settings.setSource("Team3Warn",txtTeam3WarnSource.getText());
		Settings.setSource("Team1KingSeat",txtTeam1KingSeatSource.getText());
		Settings.setSource("Team2KingSeat",txtTeam2KingSeatSource.getText());
		Settings.setSource("Team3KingSeat",txtTeam3KingSeatSource.getText());
		Settings.setSource("Team1Game1Show",txtTeam1Game1ShowSource.getText());
		Settings.setSource("Team2Game1Show",txtTeam2Game1ShowSource.getText());
		Settings.setSource("Team3Game1Show",txtTeam3Game1ShowSource.getText());
		Settings.setSource("Team1Game2Show",txtTeam1Game2ShowSource.getText());
		Settings.setSource("Team2Game2Show",txtTeam2Game2ShowSource.getText());
		Settings.setSource("Team3Game2Show",txtTeam3Game2ShowSource.getText());
		Settings.setSource("Team1Game3Show",txtTeam1Game3ShowSource.getText());
		Settings.setSource("Team2Game3Show",txtTeam2Game3ShowSource.getText());
		Settings.setSource("Team3Game3Show",txtTeam3Game3ShowSource.getText());
		Settings.setSource("LastScored",txtLastScoredSource.getText());
		Settings.setSource("TimeRemaining",txtTimeRemainingSource.getText());
		Settings.setSource("TimerInUse",txtTimerInUseSource.getText());
		Settings.setSource("MatchWinner",txtMatchWinnerSource.getText());
		Settings.setSource("Meatball",txtMeatballSource.getText());
		Settings.setSource("GameResults", txtGameResultsSource.getText());
		Settings.setSource("GameTime",txtGameTimeSource.getText());
		Settings.setSource("MatchTime",txtMatchTimeSource.getText());
		Settings.setSource("StreamTime",txtStreamTimeSource.getText());
		Settings.setSource("ShowScores",txtShowScoresSource.getText());
		Settings.setSource("ShowTimer",txtShowTimerSource.getText());
		Settings.setSource("ShowCutthroat", txtShowCutthroatSource.getText());
		try {
			Settings.saveSourceConfig();
			okToCloseWindow = true;
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
		return okToCloseWindow;
	}
	public void setLayout() {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		//Path
		JButton btnSelectPath = new JButton(Messages.getString("SourcesPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("SourcesPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(SourcesPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (chooser.getSelectedFile().exists()) {
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					} else {
						String directoryName = chooser.getSelectedFile().getAbsolutePath();
						if(!Files.exists(Paths.get(directoryName))) {
							try {
								Files.createDirectory(Paths.get(directoryName));
							} catch (IOException e1) {
								logger.error(Messages.getString("Errors.ErrorCreatingDirectory")); //$NON-NLS-1$
								logger.error(e1.toString());
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					try {
						Settings.setDatapath(formattedTxtPath.getText());
						Settings.saveSourceConfig();
					} catch (IOException ex) {
						logger.error(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile")); //$NON-NLS-1$
						logger.error(ex.toString());
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$
		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(Settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					Settings.setDatapath(formattedTxtPath.getText());
					Settings.saveSourceConfig();
		    	} catch (IOException ex) {
		    		logger.error(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
		    		logger.error(ex.toString());
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						Settings.setDatapath(formattedTxtPath.getText());
						Settings.saveSourceConfig();
			    	} catch (IOException ex) {
			    		logger.error(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
			    		logger.error(ex.toString());
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 6 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

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
		JLabel lblNameSource = new JLabel(Messages.getString("SourcesPanel.Name", Settings.getGameType())); //$NON-NLS-1$
		add(lblNameSource, "cell 1 3,alignx right"); //$NON-NLS-1$

		txtTeam1NameSource = new JTextField();
		txtTeam1NameSource.setText(Settings.getTeamNameSource("1"));
		txtTeam1NameSource.setColumns(10);
		add(txtTeam1NameSource, "cell 2 3,alignx left"); //$NON-NLS-1$

		txtTeam2NameSource = new JTextField();
		txtTeam2NameSource.setText(Settings.getTeamNameSource("2"));
		txtTeam2NameSource.setColumns(10);
		add(txtTeam2NameSource, "cell 3 3,alignx left"); //$NON-NLS-1$

		txtTeam3NameSource = new JTextField();
		txtTeam3NameSource.setText(Settings.getTeamNameSource("3"));
		txtTeam3NameSource.setColumns(10);
		add(txtTeam3NameSource, "cell 4 3,alignx left"); //$NON-NLS-1$

		//Forward Name
		JLabel lblTeam1Forward = new JLabel(Messages.getString("SourcesPanel.ForwardName", Settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 1 4,alignx right"); //$NON-NLS-1$

		txtTeam1ForwardSource = new JTextField();
		txtTeam1ForwardSource.setText(Settings.getTeamForwardSource("1"));
		txtTeam1ForwardSource.setColumns(10);
		add(txtTeam1ForwardSource, "cell 2 4,alignx left"); //$NON-NLS-1$
		
		txtTeam2ForwardSource = new JTextField();
		txtTeam2ForwardSource.setText(Settings.getTeamForwardSource("2"));
		txtTeam2ForwardSource.setColumns(10);
		add(txtTeam2ForwardSource, "cell 3 4,alignx left"); //$NON-NLS-1$

		txtTeam3ForwardSource = new JTextField();
		txtTeam3ForwardSource.setText(Settings.getTeamForwardSource("3"));
		txtTeam3ForwardSource.setColumns(10);
		add(txtTeam3ForwardSource, "cell 4 4,alignx left"); //$NON-NLS-1$

		//Goalie Name
		JLabel lblGoalieName = new JLabel(Messages.getString("SourcesPanel.GoalieName", Settings.getGameType())); //$NON-NLS-1$
		add(lblGoalieName, "cell 1 5,alignx right"); //$NON-NLS-1$

		txtTeam1GoalieSource = new JTextField();
		txtTeam1GoalieSource.setText(Settings.getTeamGoalieSource("1"));
		txtTeam1GoalieSource.setColumns(10);
		add(txtTeam1GoalieSource, "cell 2 5,alignx left"); //$NON-NLS-1$
		
		txtTeam2GoalieSource = new JTextField();
		txtTeam2GoalieSource.setText(Settings.getTeamGoalieSource("2"));
		txtTeam2GoalieSource.setColumns(10);
		add(txtTeam2GoalieSource, "cell 3 5,alignx left"); //$NON-NLS-1$

		txtTeam3GoalieSource = new JTextField();
		txtTeam3GoalieSource.setText(Settings.getTeamGoalieSource("3"));
		txtTeam3GoalieSource.setColumns(10);
		add(txtTeam3GoalieSource, "cell 4 5,alignx left"); //$NON-NLS-1$

		//Score
		JLabel lblScore = new JLabel(Messages.getString("SourcesPanel.Score", Settings.getGameType())); //$NON-NLS-1$
		add(lblScore, "cell 1 6,alignx right"); //$NON-NLS-1$

		txtTeam1ScoreSource = new JTextField();
		txtTeam1ScoreSource.setText(Settings.getTeamScoreSource("1"));
		txtTeam1ScoreSource.setColumns(10);
		add(txtTeam1ScoreSource, "cell 2 6,alignx left"); //$NON-NLS-1$

		txtTeam2ScoreSource = new JTextField();
		txtTeam2ScoreSource.setText(Settings.getTeamScoreSource("2"));
		txtTeam2ScoreSource.setColumns(10);
		add(txtTeam2ScoreSource, "cell 3 6,alignx left"); //$NON-NLS-1$

		txtTeam3ScoreSource = new JTextField();
		txtTeam3ScoreSource.setText(Settings.getTeamScoreSource("3"));
		txtTeam3ScoreSource.setColumns(10);
		add(txtTeam3ScoreSource, "cell 4 6,alignx left"); //$NON-NLS-1$

		//Game Count
		JLabel lblGameCount = new JLabel(Messages.getString("SourcesPanel.GameCount", Settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount, "cell 1 7,alignx right"); //$NON-NLS-1$

		txtTeam1GameCountSource = new JTextField();
		txtTeam1GameCountSource.setText(Settings.getTeamGameCountSource("1"));
		txtTeam1GameCountSource.setColumns(10);
		add(txtTeam1GameCountSource, "cell 2 7,alignx left"); //$NON-NLS-1$

		txtTeam2GameCountSource = new JTextField();
		txtTeam2GameCountSource.setText(Settings.getTeamGameCountSource("2"));
		txtTeam2GameCountSource.setColumns(10);
		add(txtTeam2GameCountSource, "cell 3 7,alignx left"); //$NON-NLS-1$

		txtTeam3GameCountSource = new JTextField();
		txtTeam3GameCountSource.setText(Settings.getTeamGameCountSource("3"));
		txtTeam3GameCountSource.setColumns(10);
		add(txtTeam3GameCountSource, "cell 4 7,alignx left"); //$NON-NLS-1$

		//Match Count
		JLabel lblMatchCount = new JLabel(Messages.getString("SourcesPanel.MatchCount", Settings.getGameType())); //$NON-NLS-1$
		add(lblMatchCount, "cell 1 8,alignx right"); //$NON-NLS-1$

		txtTeam1MatchCountSource = new JTextField();
		txtTeam1MatchCountSource.setText(Settings.getTeamMatchCountSource("1"));
		txtTeam1MatchCountSource.setColumns(10);
		add(txtTeam1MatchCountSource, "cell 2 8,alignx left"); //$NON-NLS-1$

		txtTeam2MatchCountSource = new JTextField();
		txtTeam2MatchCountSource.setText(Settings.getTeamMatchCountSource("2"));
		txtTeam2MatchCountSource.setColumns(10);
		add(txtTeam2MatchCountSource, "cell 3 8,alignx left"); //$NON-NLS-1$

		txtTeam3MatchCountSource = new JTextField();
		txtTeam3MatchCountSource.setText(Settings.getTeamMatchCountSource("3"));
		txtTeam3MatchCountSource.setColumns(10);
		add(txtTeam3MatchCountSource, "cell 4 8,alignx left"); //$NON-NLS-1$

		//Time Out
		JLabel lblTimeOut = new JLabel(Messages.getString("SourcesPanel.TimeOut", Settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut, "cell 1 9,alignx right"); //$NON-NLS-1$

		txtTeam1TimeOutSource = new JTextField();
		txtTeam1TimeOutSource.setText(Settings.getTeamTimeOutSource("1"));
		txtTeam1TimeOutSource.setColumns(10);
		add(txtTeam1TimeOutSource, "cell 2 9,alignx left"); //$NON-NLS-1$

		txtTeam2TimeOutSource = new JTextField();
		txtTeam2TimeOutSource.setText(Settings.getTeamTimeOutSource("2"));
		txtTeam2TimeOutSource.setColumns(10);
		add(txtTeam2TimeOutSource, "cell 3 9,alignx left"); //$NON-NLS-1$

		txtTeam3TimeOutSource = new JTextField();
		txtTeam3TimeOutSource.setText(Settings.getTeamTimeOutSource("3"));
		txtTeam3TimeOutSource.setColumns(10);
		add(txtTeam3TimeOutSource, "cell 4 9,alignx left"); //$NON-NLS-1$

		//Reset
		JLabel lblReset = new JLabel(Messages.getString("SourcesPanel.Reset", Settings.getGameType())); //$NON-NLS-1$
		add(lblReset, "cell 1 10,alignx right"); //$NON-NLS-1$

		txtTeam1ResetSource = new JTextField();
		txtTeam1ResetSource.setText(Settings.getTeamResetSource("1"));
		txtTeam1ResetSource.setColumns(10);
		add(txtTeam1ResetSource, "cell 2 10,alignx left"); //$NON-NLS-1$

		txtTeam2ResetSource = new JTextField();
		txtTeam2ResetSource.setText(Settings.getTeamResetSource("2"));
		txtTeam2ResetSource.setColumns(10);
		add(txtTeam2ResetSource, "cell 3 10,alignx left"); //$NON-NLS-1$

		txtTeam3ResetSource = new JTextField();
		txtTeam3ResetSource.setText(Settings.getTeamResetSource("3"));
		txtTeam3ResetSource.setColumns(10);
		add(txtTeam3ResetSource, "cell 4 10,alignx left"); //$NON-NLS-1$
		
		//Warn
		JLabel lblWarn = new JLabel(Messages.getString("SourcesPanel.Warn", Settings.getGameType())); //$NON-NLS-1$
		add(lblWarn, "cell 1 11,alignx right"); //$NON-NLS-1$

		txtTeam1WarnSource = new JTextField();
		txtTeam1WarnSource.setText(Settings.getTeamWarnSource("1"));
		txtTeam1WarnSource.setColumns(10);
		add(txtTeam1WarnSource, "cell 2 11,alignx left"); //$NON-NLS-1$

		txtTeam2WarnSource = new JTextField();
		txtTeam2WarnSource.setText(Settings.getTeamWarnSource("2"));
		txtTeam2WarnSource.setColumns(10);
		add(txtTeam2WarnSource, "cell 3 11,alignx left"); //$NON-NLS-1$
		
		txtTeam3WarnSource = new JTextField();
		txtTeam3WarnSource.setText(Settings.getTeamWarnSource("3"));
		txtTeam3WarnSource.setColumns(10);
		add(txtTeam3WarnSource, "cell 4 11,alignx left"); //$NON-NLS-1$
		
		//KingSeat
		JLabel lblKingSeat = new JLabel(Messages.getString("SourcesPanel.KingSeat", Settings.getGameType())); //$NON-NLS-1$
		add(lblKingSeat, "cell 1 12,alignx right"); //$NON-NLS-1$
		
		txtTeam1KingSeatSource = new JTextField();
		txtTeam1KingSeatSource.setText(Settings.getTeamKingSeatSource("1"));
		txtTeam1KingSeatSource.setColumns(10);
		add(txtTeam1KingSeatSource, "cell 2 12, alignx left"); //$NON-NLS-1$
		
		txtTeam2KingSeatSource = new JTextField();
		txtTeam2KingSeatSource.setText(Settings.getTeamKingSeatSource("2"));
		txtTeam2KingSeatSource.setColumns(10);
		add(txtTeam2KingSeatSource, "cell 3 12, alignx left"); //$NON-NLS-1$
		
		txtTeam3KingSeatSource = new JTextField();
		txtTeam3KingSeatSource.setText(Settings.getTeamKingSeatSource("3"));
		txtTeam3KingSeatSource.setColumns(10);
		add(txtTeam3KingSeatSource, "cell 4 12, alignx left"); //$NON-NLS-1$
		
		//Game 1 Show Source
		JLabel lblGame1ShowSource = new JLabel(Messages.getString("SourcesPanel.Game1ShowSource", Settings.getGameType())); //$NON-NLS-1$
		add(lblGame1ShowSource, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam1Game1ShowSource = new JTextField();
		txtTeam1Game1ShowSource.setText(Settings.getTeamGameShowSource(1,1));
		txtTeam1Game1ShowSource.setColumns(10);
		add(txtTeam1Game1ShowSource, "cell 2 13, alignx left"); //$NON-NLS-1$

		txtTeam2Game1ShowSource = new JTextField();
		txtTeam2Game1ShowSource.setText(Settings.getTeamGameShowSource(2,1));
		txtTeam2Game1ShowSource.setColumns(10);
		add(txtTeam2Game1ShowSource, "cell 3 13, alignx left"); //$NON-NLS-1$

		txtTeam3Game1ShowSource = new JTextField();
		txtTeam3Game1ShowSource.setText(Settings.getTeamGameShowSource(3,1));
		txtTeam3Game1ShowSource.setColumns(10);
		add(txtTeam3Game1ShowSource, "cell 4 13, alignx left"); //$NON-NLS-1$

		//Game 2 Show Source
		JLabel lblGame2ShowSource = new JLabel(Messages.getString("SourcesPanel.Game2ShowSource", Settings.getGameType())); //$NON-NLS-1$
		add(lblGame2ShowSource, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1Game2ShowSource = new JTextField();
		txtTeam1Game2ShowSource.setText(Settings.getTeamGameShowSource(1,2));
		txtTeam1Game2ShowSource.setColumns(10);
		add(txtTeam1Game2ShowSource, "cell 2 14, alignx left"); //$NON-NLS-1$

		txtTeam2Game2ShowSource = new JTextField();
		txtTeam2Game2ShowSource.setText(Settings.getTeamGameShowSource(2,2));
		txtTeam2Game2ShowSource.setColumns(10);
		add(txtTeam2Game2ShowSource, "cell 3 14, alignx left"); //$NON-NLS-1$

		txtTeam3Game2ShowSource = new JTextField();
		txtTeam3Game2ShowSource.setText(Settings.getTeamGameShowSource(3,2));
		txtTeam3Game2ShowSource.setColumns(10);
		add(txtTeam3Game2ShowSource, "cell 4 14, alignx left"); //$NON-NLS-1$

		//Game 3 Show Source
		JLabel lblGame3ShowSource = new JLabel(Messages.getString("SourcesPanel.Game3ShowSource", Settings.getGameType())); //$NON-NLS-1$
		add(lblGame3ShowSource, "cell 1 15,alignx right"); //$NON-NLS-1$
		txtTeam1Game3ShowSource = new JTextField();
		txtTeam1Game3ShowSource.setText(Settings.getTeamGameShowSource(1,3));
		txtTeam1Game3ShowSource.setColumns(10);
		add(txtTeam1Game3ShowSource, "cell 2 15, alignx left"); //$NON-NLS-1$

		txtTeam2Game3ShowSource = new JTextField();
		txtTeam2Game3ShowSource.setText(Settings.getTeamGameShowSource(2,3));
		txtTeam2Game3ShowSource.setColumns(10);
		add(txtTeam2Game3ShowSource, "cell 3 15, alignx left"); //$NON-NLS-1$

		txtTeam3Game3ShowSource = new JTextField();
		txtTeam3Game3ShowSource.setText(Settings.getTeamGameShowSource(3,3));
		txtTeam3Game3ShowSource.setColumns(10);
		add(txtTeam3Game3ShowSource, "cell 4 15, alignx left"); //$NON-NLS-1$

		
		//Tournament
		JLabel lblTournamentSource = new JLabel(Messages.getString("SourcesPanel.Tournament", Settings.getGameType())); //$NON-NLS-1$
		add(lblTournamentSource, "cell 5 3,alignx right"); //$NON-NLS-1$

		txtTournamentSource = new JTextField();
		txtTournamentSource.setText(Settings.getTournamentSource());
		add(txtTournamentSource, "cell 6 3,alignx left"); //$NON-NLS-1$
		txtTournamentSource.setColumns(10);

		//Event
		JLabel lblEventSource = new JLabel(Messages.getString("SourcesPanel.Event", Settings.getGameType())); //$NON-NLS-1$
		add(lblEventSource, "cell 5 4,alignx right"); //$NON-NLS-1$

		txtEventSource = new JTextField();
		txtEventSource.setText(Settings.getEventSource());
		add(txtEventSource, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtEventSource.setColumns(10);
		
		//Table Name
		JLabel lblTableNameSource = new JLabel(Messages.getString("SourcesPanel.TableName", Settings.getGameType())); //$NON-NLS-1$
		add(lblTableNameSource, "cell 5 5,alignx right"); //$NON-NLS-1$
		
		txtTableNameSource = new JTextField();
		txtTableNameSource.setText(Settings.getTableNameSource());
		add(txtTableNameSource, "cell 6 5,alignx left");
		txtTableNameSource.setColumns(10);

		//Timer in Use
		JLabel lblTimerSource = new JLabel(Messages.getString("SourcesPanel.Timer", Settings.getGameType())); //$NON-NLS-1$
		add(lblTimerSource, "cell 5 6,alignx right"); //$NON-NLS-1$

		txtTimerInUseSource = new JTextField();
		txtTimerInUseSource.setText(Settings.getTimerInUseSource());
		add(txtTimerInUseSource, "cell 6 6,alignx left"); //$NON-NLS-1$
		txtTimerInUseSource.setColumns(10);

		//Time Remaining
		JLabel lblTimeRemainingSource = new JLabel(Messages.getString("SourcesPanel.TimeRemaining", Settings.getGameType())); //$NON-NLS-1$
		add(lblTimeRemainingSource, "cell 5 7,alignx right"); //$NON-NLS-1$

		txtTimeRemainingSource = new JTextField();
		txtTimeRemainingSource.setText(Settings.getTimeRemainingSource());
		add(txtTimeRemainingSource, "cell 6 7,alignx left"); //$NON-NLS-1$
		txtTimeRemainingSource.setColumns(10);

		//Game Time
		JLabel lblGameTimeSource = new JLabel(Messages.getString("SourcesPanel.GameTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimeSource, "cell 5 8,alignx right"); //$NON-NLS-1$
		
		txtGameTimeSource = new JTextField();
		txtGameTimeSource.setText(Settings.getGameTimeSource());
		add(txtGameTimeSource, "cell 6 8,alignx left"); //$NON-NLS-1$
		txtGameTimeSource.setColumns(10);
		
		//Match Time
		JLabel lblMatchTimeSource = new JLabel(Messages.getString("SourcesPanel.MatchTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblMatchTimeSource, "cell 5 9,alignx right"); //$NON-NLS-1$
		
		txtMatchTimeSource = new JTextField();
		txtMatchTimeSource.setText(Settings.getMatchTimeSource());
		add(txtMatchTimeSource, "cell 6 9,alignx left"); //$NON-NLS-1$
		txtMatchTimeSource.setColumns(10);
		
		//Stream Time
		JLabel lblStreamTimeSource = new JLabel(Messages.getString("SourcesPanel.StreamTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblStreamTimeSource, "cell 5 10,alignx right"); //$NON-NLS-1$
		
		txtStreamTimeSource = new JTextField();
		txtStreamTimeSource.setText(Settings.getStreamTimeSource());
		add(txtStreamTimeSource, "cell 6 10,alignx left"); //$NON-NLS-1$
		txtStreamTimeSource.setColumns(10);

		//Last Scored
		JLabel lblLastScoredSource = new JLabel(Messages.getString("SourcesPanel.LastScored", Settings.getGameType())); //$NON-NLS-1$
		add(lblLastScoredSource, "cell 5 11,alignx right"); //$NON-NLS-1$

		txtLastScoredSource = new JTextField();
		txtLastScoredSource.setText(Settings.getLastScoredSource());
		add(txtLastScoredSource, "cell 6 11,alignx left"); //$NON-NLS-1$
		txtLastScoredSource.setColumns(10);

		//Match Winner
		JLabel lblMatchWinnerSource = new JLabel(Messages.getString("SourcesPanel.MatchWinner", Settings.getGameType())); //$NON-NLS-1$
		add(lblMatchWinnerSource, "cell 5 12,alignx right"); //$NON-NLS-1$

		txtMatchWinnerSource = new JTextField();
		txtMatchWinnerSource.setText(Settings.getMatchWinnerSource());
		add(txtMatchWinnerSource, "cell 6 12,alignx left"); //$NON-NLS-1$
		txtMatchWinnerSource.setColumns(10);

		//Meatball
		JLabel lblMeatball = new JLabel(Messages.getString("SourcesPanel.Meatball", Settings.getGameType())); //$NON-NLS-1$
		add(lblMeatball, "cell 5 13,alignx right"); //$NON-NLS-1$

		txtMeatballSource = new JTextField();
		txtMeatballSource.setText(Settings.getMeatballSource());
		add(txtMeatballSource, "cell 6 13,alignx left"); //$NON-NLS-1$
		txtMeatballSource.setColumns(10);
		
		//Game Results
		JLabel lblGameResults = new JLabel(Messages.getString("SourcesPanel.GameResults", Settings.getGameType())); //$NON-NLS-1$
		add(lblGameResults, "cell 5 14,alignx right"); //$NON-NLS-1$

		txtGameResultsSource = new JTextField();
		txtGameResultsSource.setText(Settings.getGameResultsSource());
		add(txtGameResultsSource, "cell 6 14,alignx left"); //$NON-NLS-1$
		txtGameResultsSource.setColumns(10);

		//Show Scores
		JLabel lblShowScoresSource = new JLabel(Messages.getString("SourcesPanel.ShowScores", Settings.getGameType())); //$NON-NLS-1$
		add(lblShowScoresSource, "cell 5 15,alignx trailing"); //$NON-NLS-1$
		
		txtShowScoresSource = new JTextField();
		txtShowScoresSource.setText(Settings.getShowScoresSource());
		add(txtShowScoresSource, "cell 6 15,alignx left"); //$NON-NLS-1$
		txtShowScoresSource.setColumns(10);
		
		//Show Timer
		JLabel lblShowTimerSource = new JLabel(Messages.getString("SourcesPanel.ShowTimer", Settings.getGameType())); //$NON-NLS-1$
		add(lblShowTimerSource, "cell 5 16,alignx trailing"); //$NON-NLS-1$
		
		txtShowTimerSource = new JTextField();
		txtShowTimerSource.setText(Settings.getShowTimerSource());
		add(txtShowTimerSource, "cell 6 16,alignx left"); //$NON-NLS-1$
		txtShowTimerSource.setColumns(10);
		
		//Show Cutthroat
		JLabel lblShowCutthroatSource = new JLabel(Messages.getString("SourcesPanel.ShowCutthroat", Settings.getGameType())); //$NON-NLS-1$
		add(lblShowCutthroatSource, "cell 5 17,alignx trailing"); //$NON-NLS-1$
		
		txtShowCutthroatSource = new JTextField();
		txtShowCutthroatSource.setText(Settings.getShowCutthroatSource());
		add(txtShowCutthroatSource, "cell 6 17,alignx left"); //$NON-NLS-1$
		txtShowCutthroatSource.setColumns(10);
		
		btnSaveSources = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSaveSources, "cell 2 20,alignx left"); //$NON-NLS-1$
		
		JButton btnCancelSources = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelSources, "cell 3 20,alignx left"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults();
			}
		});
		add(btnRestoreDefaults, "cell 4 20,alignx left"); //$NON-NLS-1$
	}
	/////// Listeners \\\\\\\
	public void addSaveListener(ActionListener listenForBtnSaveSources) {
		btnSaveSources.addActionListener(listenForBtnSaveSources);
	}
}
