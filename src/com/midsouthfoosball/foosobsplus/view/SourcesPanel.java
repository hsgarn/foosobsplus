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
	private JTextField txtTournamentSource;
	private JTextField txtEventSource;
	private JTextField txtTableNameSource;
	private JTextField txtTeam1NameSource;
	private JTextField txtTeam1ForwardSource;
	private JTextField txtTeam1GoalieSource;
	private JTextField txtTeam2NameSource;
	private JTextField txtTeam2ForwardSource;
	private JTextField txtTeam2GoalieSource;
	private JTextField txtTeam3NameSource;
	private JTextField txtTeam3ForwardSource;
	private JTextField txtTeam3GoalieSource;
	private JTextField txtGameCount1Source;
	private JTextField txtGameCount2Source;
	private JTextField txtGameCount3Source;
	private JTextField txtScore1Source;
	private JTextField txtScore2Source;
	private JTextField txtScore3Source;
	private JTextField txtTimeOut1Source;
	private JTextField txtTimeOut2Source;
	private JTextField txtTimeOut3Source;
	private JTextField txtReset1Source;
	private JTextField txtReset2Source;
	private JTextField txtReset3Source;
	private JTextField txtWarn1Source;
	private JTextField txtWarn2Source;
	private JTextField txtWarn3Source;
	private JTextField txtTimeRemainingSource;
	private JTextField txtTimerInUseSource;
	private JTextField txtMatchWinnerSource;
	private JTextField txtMeatballSource;
	private JTextField txtLastScoredSource;
	private JTextField txtGameTimeSource;
	private JTextField txtMatchTimeSource;
	private JTextField txtStreamTimeSource;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtShowScoresSource;
	private JTextField txtShowTimerSource;
	private JTextField txtShowCutthroatSource;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$
	private static transient Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// Create the Panel.

	public SourcesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);
	}

	private void restoreDefaults(Settings settings) {
		txtTournamentSource.setText(settings.getDefaultTournamentSource());
		txtEventSource.setText(settings.getDefaultEventSource());
		txtTeam1NameSource.setText(settings.getDefaultTeam1NameSource());
		txtTeam1ForwardSource.setText(settings.getDefaultTeam1ForwardSource());
		txtTeam1GoalieSource.setText(settings.getDefaultTeam1GoalieSource());
		txtTeam2NameSource.setText(settings.getDefaultTeam2NameSource());
		txtTeam2ForwardSource.setText(settings.getDefaultTeam2ForwardSource());
		txtTeam2GoalieSource.setText(settings.getDefaultTeam2GoalieSource());
		txtTeam3NameSource.setText(settings.getDefaultTeam3NameSource());
		txtTeam3ForwardSource.setText(settings.getDefaultTeam3ForwardSource());
		txtTeam3GoalieSource.setText(settings.getDefaultTeam3GoalieSource());
		txtTableNameSource.setText(settings.getDefaultTableNameSource());
		txtGameCount1Source.setText(settings.getDefaultGameCount1Source());
		txtGameCount2Source.setText(settings.getDefaultGameCount2Source());
		txtGameCount3Source.setText(settings.getDefaultGameCount3Source());
		txtScore1Source.setText(settings.getDefaultScore1Source());
		txtScore2Source.setText(settings.getDefaultScore2Source());
		txtScore3Source.setText(settings.getDefaultScore3Source());
		txtTimeOut1Source.setText(settings.getDefaultTimeOut1Source());
		txtTimeOut2Source.setText(settings.getDefaultTimeOut2Source());
		txtTimeOut3Source.setText(settings.getDefaultTimeOut3Source());
		txtReset1Source.setText(settings.getDefaultReset1Source());
		txtReset2Source.setText(settings.getDefaultReset2Source());
		txtReset3Source.setText(settings.getDefaultReset3Source());
		txtWarn1Source.setText(settings.getDefaultWarn1Source());
		txtWarn2Source.setText(settings.getDefaultWarn2Source());
		txtWarn3Source.setText(settings.getDefaultWarn3Source());
		txtTimeRemainingSource.setText(settings.getDefaultTimeRemainingSource());
		txtTimerInUseSource.setText(settings.getDefaultTimerInUseSource());
		txtMatchWinnerSource.setText(settings.getDefaultMatchWinnerSource());
		txtMeatballSource.setText(settings.getDefaultMeatballSource());
		txtLastScoredSource.setText(settings.getDefaultLastScoredSource());
		txtGameTimeSource.setText(settings.getDefaultGameTimeSource());
		txtMatchTimeSource.setText(settings.getDefaultMatchTimeSource());
		txtStreamTimeSource.setText(settings.getDefaultStreamTimeSource());
		txtShowScoresSource.setText(settings.getDefaultShowScoresSource());
		txtShowTimerSource.setText(settings.getDefaultShowTimerSource());
		txtShowCutthroatSource.setText(settings.getDefaultShowCutthroatSource());
	}

	private void saveSettings(Settings settings) {
		settings.setSource("TournamentSource",txtTournamentSource.getText());
		settings.setSource("EventSource",txtEventSource.getText());
		settings.setSource("TableNameSource",txtTableNameSource.getText());
		settings.setSource("Team1NameSource",txtTeam1NameSource.getText());
		settings.setSource("Team1ForwardSource",txtTeam1ForwardSource.getText());
		settings.setSource("Team1GoalieSource",txtTeam1GoalieSource.getText());
		settings.setSource("Team2NameSource",txtTeam2NameSource.getText());
		settings.setSource("Team2ForwardSource",txtTeam2ForwardSource.getText());
		settings.setSource("Team2GoalieSource",txtTeam2GoalieSource.getText());
		settings.setSource("Team3NameSource",txtTeam3NameSource.getText());
		settings.setSource("Team3ForwardSource",txtTeam3ForwardSource.getText());
		settings.setSource("Team3GoalieSource",txtTeam3GoalieSource.getText());
		settings.setSource("GameCount1Source",txtGameCount1Source.getText());
		settings.setSource("GameCount2Source",txtGameCount2Source.getText());
		settings.setSource("GameCount3Source",txtGameCount3Source.getText());
		settings.setSource("Score1Source",txtScore1Source.getText());
		settings.setSource("Score2Source",txtScore2Source.getText());
		settings.setSource("Score3Source",txtScore3Source.getText());
		settings.setSource("TimeOut1Source",txtTimeOut1Source.getText());
		settings.setSource("TimeOut2Source",txtTimeOut2Source.getText());
		settings.setSource("TimeOut3Source",txtTimeOut3Source.getText());
		settings.setSource("Reset1Source",txtReset1Source.getText());
		settings.setSource("Reset2Source",txtReset2Source.getText());
		settings.setSource("Reset3Source",txtReset3Source.getText());
		settings.setSource("Warn1Source",txtWarn1Source.getText());
		settings.setSource("Warn2Source",txtWarn2Source.getText());
		settings.setSource("Warn3Source",txtWarn3Source.getText());
		settings.setSource("LastScoredSource",txtLastScoredSource.getText());
		settings.setSource("TimeRemainingSource",txtTimeRemainingSource.getText());
		settings.setSource("TimerInUseSource",txtTimerInUseSource.getText());
		settings.setSource("MatchWinnerSource",txtMatchWinnerSource.getText());
		settings.setSource("MeatballSource",txtMeatballSource.getText());
		settings.setSource("GameTimeSource",txtGameTimeSource.getText());
		settings.setSource("MatchTimeSource",txtMatchTimeSource.getText());
		settings.setSource("StreamTimeSource",txtStreamTimeSource.getText());
		settings.setSource("ShowScoresSource",txtShowScoresSource.getText());
		settings.setSource("ShowTimerSource",txtShowTimerSource.getText());
		settings.setSource("ShowCutthroatSource", txtShowCutthroatSource.getText());
		try {
			settings.saveSourceConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public void setLayout(Settings settings) {	
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
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveSourceConfig();
					} catch (IOException ex) {
						logger.error(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile")); //$NON-NLS-1$
						logger.error(ex.toString());
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveSourceConfig();
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
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveSourceConfig();
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
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$

		JLabel lblTeam2Column = new JLabel(Messages.getString("SourcesPanel.Team2Column")); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblTeam3Column = new JLabel(Messages.getString("SourcesPanel.Team3Column")); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$

		JLabel lblSourceCol1 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol1, "cell 2 2,alignx left"); //$NON-NLS-1$

		JLabel lblSourceCol2 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol2, "cell 3 2,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblSourceCol3 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		
		JLabel lblSourceCol4 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
		
		//Team Name
		JLabel lblNameSource = new JLabel(Messages.getString("SourcesPanel.Name", settings.getGameType())); //$NON-NLS-1$
		add(lblNameSource, "cell 1 3,alignx right"); //$NON-NLS-1$

		txtTeam1NameSource = new JTextField();
		txtTeam1NameSource.setText(settings.getTeamNameSource("1"));
		add(txtTeam1NameSource, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam1NameSource.setColumns(10);

		txtTeam2NameSource = new JTextField();
		txtTeam2NameSource.setText(settings.getTeamNameSource("2"));
		add(txtTeam2NameSource, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam2NameSource.setColumns(10);

		txtTeam3NameSource = new JTextField();
		txtTeam3NameSource.setText(settings.getTeamNameSource("3"));
		add(txtTeam3NameSource, "cell 4 3,alignx left"); //$NON-NLS-1$
		txtTeam3NameSource.setColumns(10);

		//Forward Name
		JLabel lblTeam1Forward = new JLabel(Messages.getString("SourcesPanel.ForwardName", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 1 4,alignx right"); //$NON-NLS-1$

		txtTeam1ForwardSource = new JTextField();
		txtTeam1ForwardSource.setText(settings.getTeamForwardSource("1"));
		add(txtTeam1ForwardSource, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam1ForwardSource.setColumns(10);
		
		txtTeam2ForwardSource = new JTextField();
		txtTeam2ForwardSource.setText(settings.getTeamForwardSource("2"));
		add(txtTeam2ForwardSource, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam2ForwardSource.setColumns(10);

		txtTeam3ForwardSource = new JTextField();
		txtTeam3ForwardSource.setText(settings.getTeamForwardSource("3"));
		add(txtTeam3ForwardSource, "cell 4 4,alignx left"); //$NON-NLS-1$
		txtTeam3ForwardSource.setColumns(10);

		//Goalie Name
		JLabel lblGoalieName = new JLabel(Messages.getString("SourcesPanel.GoalieName", settings.getGameType())); //$NON-NLS-1$
		add(lblGoalieName, "cell 1 5,alignx right"); //$NON-NLS-1$

		txtTeam1GoalieSource = new JTextField();
		txtTeam1GoalieSource.setText(settings.getTeamGoalieSource("1"));
		add(txtTeam1GoalieSource, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam1GoalieSource.setColumns(10);
		
		txtTeam2GoalieSource = new JTextField();
		txtTeam2GoalieSource.setText(settings.getTeamGoalieSource("2"));
		add(txtTeam2GoalieSource, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtTeam2GoalieSource.setColumns(10);

		txtTeam3GoalieSource = new JTextField();
		txtTeam3GoalieSource.setText(settings.getTeamGoalieSource("3"));
		add(txtTeam3GoalieSource, "cell 4 5,alignx left"); //$NON-NLS-1$
		txtTeam3GoalieSource.setColumns(10);

		//Score
		JLabel lblScore = new JLabel(Messages.getString("SourcesPanel.Score", settings.getGameType())); //$NON-NLS-1$
		add(lblScore, "cell 1 6,alignx right"); //$NON-NLS-1$

		txtScore1Source = new JTextField();
		txtScore1Source.setText(settings.getScoreSource("1"));
		add(txtScore1Source, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtScore1Source.setColumns(10);

		txtScore2Source = new JTextField();
		txtScore2Source.setText(settings.getScoreSource("2"));
		add(txtScore2Source, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtScore2Source.setColumns(10);

		txtScore3Source = new JTextField();
		txtScore3Source.setText(settings.getScoreSource("3"));
		add(txtScore3Source, "cell 4 6,alignx left"); //$NON-NLS-1$
		txtScore3Source.setColumns(10);

		//Game Count
		JLabel lblGameCount = new JLabel(Messages.getString("SourcesPanel.GameCount", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount, "cell 1 7,alignx right"); //$NON-NLS-1$

		txtGameCount1Source = new JTextField();
		txtGameCount1Source.setText(settings.getGameCountSource("1"));
		add(txtGameCount1Source, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtGameCount1Source.setColumns(10);

		txtGameCount2Source = new JTextField();
		txtGameCount2Source.setText(settings.getGameCountSource("2"));
		add(txtGameCount2Source, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtGameCount2Source.setColumns(10);

		txtGameCount3Source = new JTextField();
		txtGameCount3Source.setText(settings.getGameCountSource("3"));
		add(txtGameCount3Source, "cell 4 7,alignx left"); //$NON-NLS-1$
		txtGameCount3Source.setColumns(10);

		//Time Out
		JLabel lblTimeOut = new JLabel(Messages.getString("SourcesPanel.TimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut, "cell 1 8,alignx right"); //$NON-NLS-1$

		txtTimeOut1Source = new JTextField();
		txtTimeOut1Source.setText(settings.getTimeOutSource("1"));
		add(txtTimeOut1Source, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTimeOut1Source.setColumns(10);

		txtTimeOut2Source = new JTextField();
		txtTimeOut2Source.setText(settings.getTimeOutSource("2"));
		add(txtTimeOut2Source, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtTimeOut2Source.setColumns(10);

		txtTimeOut3Source = new JTextField();
		txtTimeOut3Source.setText(settings.getTimeOutSource("3"));
		add(txtTimeOut3Source, "cell 4 8,alignx left"); //$NON-NLS-1$
		txtTimeOut3Source.setColumns(10);

		//Reset
		JLabel lblReset = new JLabel(Messages.getString("SourcesPanel.Reset", settings.getGameType())); //$NON-NLS-1$
		add(lblReset, "cell 1 9,alignx right"); //$NON-NLS-1$

		txtReset1Source = new JTextField();
		txtReset1Source.setText(settings.getResetSource("1"));
		add(txtReset1Source, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtReset1Source.setColumns(10);

		txtReset2Source = new JTextField();
		txtReset2Source.setText(settings.getResetSource("2"));
		add(txtReset2Source, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtReset2Source.setColumns(10);

		txtReset3Source = new JTextField();
		txtReset3Source.setText(settings.getResetSource("3"));
		add(txtReset3Source, "cell 4 9,alignx left"); //$NON-NLS-1$
		txtReset3Source.setColumns(10);
		
		//Warn
		JLabel lblWarn = new JLabel(Messages.getString("SourcesPanel.Warn", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn, "cell 1 10,alignx right"); //$NON-NLS-1$

		txtWarn1Source = new JTextField();
		txtWarn1Source.setText(settings.getWarnSource("1"));
		add(txtWarn1Source, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtWarn1Source.setColumns(10);

		txtWarn2Source = new JTextField();
		txtWarn2Source.setText(settings.getWarnSource("2"));
		add(txtWarn2Source, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtWarn2Source.setColumns(10);
		
		txtWarn3Source = new JTextField();
		txtWarn3Source.setText(settings.getWarnSource("3"));
		add(txtWarn3Source, "cell 4 10,alignx left"); //$NON-NLS-1$
		txtWarn3Source.setColumns(10);
		
		//Tournament
		JLabel lblTournamentSource = new JLabel(Messages.getString("SourcesPanel.Tournament", settings.getGameType())); //$NON-NLS-1$
		add(lblTournamentSource, "cell 5 3,alignx right"); //$NON-NLS-1$

		txtTournamentSource = new JTextField();
		txtTournamentSource.setText(settings.getTournamentSource());
		add(txtTournamentSource, "cell 6 3,alignx left"); //$NON-NLS-1$
		txtTournamentSource.setColumns(10);

		//Event
		JLabel lblEventSource = new JLabel(Messages.getString("SourcesPanel.Event", settings.getGameType())); //$NON-NLS-1$
		add(lblEventSource, "cell 5 4,alignx right"); //$NON-NLS-1$

		txtEventSource = new JTextField();
		txtEventSource.setText(settings.getEventSource());
		add(txtEventSource, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtEventSource.setColumns(10);
		
		//Table Name
		JLabel lblTableNameSource = new JLabel(Messages.getString("SourcesPanel.TableName", settings.getGameType())); //$NON-NLS-1$
		add(lblTableNameSource, "cell 5 5,alignx right"); //$NON-NLS-1$
		
		txtTableNameSource = new JTextField();
		txtTableNameSource.setText(settings.getTableNameSource());
		add(txtTableNameSource, "cell 6 5,alignx left");
		txtTableNameSource.setColumns(10);

		//Timer in Use
		JLabel lblTimerSource = new JLabel(Messages.getString("SourcesPanel.Timer", settings.getGameType())); //$NON-NLS-1$
		add(lblTimerSource, "cell 5 6,alignx right"); //$NON-NLS-1$

		txtTimerInUseSource = new JTextField();
		txtTimerInUseSource.setText(settings.getTimerInUseSource());
		add(txtTimerInUseSource, "cell 6 6,alignx left"); //$NON-NLS-1$
		txtTimerInUseSource.setColumns(10);

		//Time Remaining
		JLabel lblTimeRemainingSource = new JLabel(Messages.getString("SourcesPanel.TimeRemaining", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeRemainingSource, "cell 5 7,alignx right"); //$NON-NLS-1$

		txtTimeRemainingSource = new JTextField();
		txtTimeRemainingSource.setText(settings.getTimeRemainingSource());
		add(txtTimeRemainingSource, "cell 6 7,alignx left"); //$NON-NLS-1$
		txtTimeRemainingSource.setColumns(10);

		//Game Time
		JLabel lblGameTimeSource = new JLabel(Messages.getString("SourcesPanel.GameTime", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimeSource, "cell 5 8,alignx right"); //$NON-NLS-1$
		
		txtGameTimeSource = new JTextField();
		txtGameTimeSource.setText(settings.getGameTimeSource());
		add(txtGameTimeSource, "cell 6 8,alignx left"); //$NON-NLS-1$
		txtGameTimeSource.setColumns(10);
		
		//Match Time
		JLabel lblMatchTimeSource = new JLabel(Messages.getString("SourcesPanel.MatchTime", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchTimeSource, "cell 5 9,alignx right"); //$NON-NLS-1$
		
		txtMatchTimeSource = new JTextField();
		txtMatchTimeSource.setText(settings.getMatchTimeSource());
		add(txtMatchTimeSource, "cell 6 9,alignx left"); //$NON-NLS-1$
		txtMatchTimeSource.setColumns(10);
		
		//Stream Time
		JLabel lblStreamTimeSource = new JLabel(Messages.getString("SourcesPanel.StreamTime", settings.getGameType())); //$NON-NLS-1$
		add(lblStreamTimeSource, "cell 5 10,alignx right"); //$NON-NLS-1$
		
		txtStreamTimeSource = new JTextField();
		txtStreamTimeSource.setText(settings.getStreamTimeSource());
		add(txtStreamTimeSource, "cell 6 10,alignx left"); //$NON-NLS-1$
		txtStreamTimeSource.setColumns(10);

		//Last Scored
		JLabel lblLastScoredSource = new JLabel(Messages.getString("SourcesPanel.LastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblLastScoredSource, "cell 5 11,alignx right"); //$NON-NLS-1$

		txtLastScoredSource = new JTextField();
		txtLastScoredSource.setText(settings.getLastScoredSource());
		add(txtLastScoredSource, "cell 6 11,alignx left"); //$NON-NLS-1$
		txtLastScoredSource.setColumns(10);

		//Match Winner
		JLabel lblMatchWinnerSource = new JLabel(Messages.getString("SourcesPanel.MatchWinner", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchWinnerSource, "cell 5 12,alignx right"); //$NON-NLS-1$

		txtMatchWinnerSource = new JTextField();
		txtMatchWinnerSource.setText(settings.getMatchWinnerSource());
		add(txtMatchWinnerSource, "cell 6 12,alignx left"); //$NON-NLS-1$
		txtMatchWinnerSource.setColumns(10);

		//Meatball
		JLabel lblMeatball = new JLabel(Messages.getString("SourcesPanel.Meatball", settings.getGameType())); //$NON-NLS-1$
		add(lblMeatball, "cell 5 13,alignx right"); //$NON-NLS-1$

		txtMeatballSource = new JTextField();
		txtMeatballSource.setText(settings.getMeatballSource());
		add(txtMeatballSource, "cell 6 13,alignx left"); //$NON-NLS-1$
		txtMeatballSource.setColumns(10);

		//Show Scores
		JLabel lblShowScoresSource = new JLabel(Messages.getString("SourcesPanel.ShowScoresSource", settings.getGameType())); //$NON-NLS-1$
		add(lblShowScoresSource, "cell 1 11,alignx trailing"); //$NON-NLS-1$
		
		txtShowScoresSource = new JTextField();
		txtShowScoresSource.setText(settings.getShowScoresSource());
		add(txtShowScoresSource, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtShowScoresSource.setColumns(10);
		
		//Show Timer
		JLabel lblShowTimerSource = new JLabel(Messages.getString("SourcesPanel.ShowTimerSource", settings.getGameType())); //$NON-NLS-1$
		add(lblShowTimerSource, "cell 1 12,alignx trailing"); //$NON-NLS-1$
		
		txtShowTimerSource = new JTextField();
		txtShowTimerSource.setText(settings.getShowTimerSource());
		add(txtShowTimerSource, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtShowTimerSource.setColumns(10);
		
		//Show Cutthroat
		JLabel lblShowCutthroatSource = new JLabel(Messages.getString("SourcesPanel.ShowCutthroatSource", settings.getGameType())); //$NON-NLS-1$
		add(lblShowCutthroatSource, "cell 1 13,alignx trailing"); //$NON-NLS-1$
		
		txtShowCutthroatSource = new JTextField();
		txtShowCutthroatSource.setText(settings.getShowCutthroatSource());
		add(txtShowCutthroatSource, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtShowCutthroatSource.setColumns(10);
		
		JButton btnSaveSources = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveSources, "cell 2 16,alignx left"); //$NON-NLS-1$
		
		JButton btnCancelSources = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelSources, "cell 3 16,alignx left"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 4 16,alignx left"); //$NON-NLS-1$
	}
}
