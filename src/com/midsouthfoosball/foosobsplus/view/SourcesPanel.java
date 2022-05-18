/**
Copyright 2021, 2022 Hugh Garner
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
	private JTextField txtGameCount1Source;
	private JTextField txtGameCount2Source;
	private JTextField txtScore1Source;
	private JTextField txtScore2Source;
	private JTextField txtTimeOut1Source;
	private JTextField txtTimeOut2Source;
	private JTextField txtReset1Source;
	private JTextField txtReset2Source;
	private JTextField txtWarn1Source;
	private JTextField txtWarn2Source;
	private JTextField txtTimeRemainingSource;
	private JTextField txtTimerInUseSource;
	private JTextField txtMatchWinnerSource;
	private JTextField txtMeatballSource;
	private JTextField txtLastScoredSource;
	private JTextField txtGameTimeSource;
	private JTextField txtMatchTimeSource;
	private JTextField txtStuffs1Source;
	private JTextField txtStuffs2Source;
	private JTextField txtBreaks1Source;
	private JTextField txtBreaks2Source;
	private JTextField txtAces1Source;
	private JTextField txtAces2Source;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtTeam1PassAttemptsSource;
	private JTextField txtTeam1PassCompletesSource;
	private JTextField txtTeam2PassAttemptsSource;
	private JTextField txtTeam2PassCompletesSource;
	private JTextField txtTeam1ShotAttemptsSource;
	private JTextField txtTeam1ShotCompletesSource;
	private JTextField txtTeam2ShotAttemptsSource;
	private JTextField txtTeam2ShotCompletesSource;
	private JTextField txtTeam1ClearAttemptsSource;
	private JTextField txtTeam1ClearCompletesSource;
	private JTextField txtTeam2ClearAttemptsSource;
	private JTextField txtTeam2ClearCompletesSource;
	private JTextField txtTeam1PassPercentSource;
	private JTextField txtTeam2PassPercentSource;
	private JTextField txtTeam1ShotPercentSource;
	private JTextField txtTeam2ShotPercentSource;
	private JTextField txtTeam1ClearPercentSource;
	private JTextField txtTeam2ClearPercentSource;
	private JTextField txtTeam1TwoBarPassAttemptsSource;
	private JTextField txtTeam1TwoBarPassCompletesSource;
	private JTextField txtTeam2TwoBarPassAttemptsSource;
	private JTextField txtTeam2TwoBarPassCompletesSource;
	private JTextField txtTeam1ScoringSource;
	private JTextField txtTeam2ScoringSource;
	private JTextField txtTeam1ThreeBarScoringSource;
	private JTextField txtTeam2ThreeBarScoringSource;
	private JTextField txtTeam1FiveBarScoringSource;
	private JTextField txtTeam2FiveBarScoringSource;
	private JTextField txtTeam1TwoBarScoringSource;
	private JTextField txtTeam2TwoBarScoringSource;
	private JTextField txtTeam1ShotsOnGoalSource;
	private JTextField txtTeam2ShotsOnGoalSource;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$

	// Create the Panel.

	public SourcesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);

	}

	private void restoreDefaults(Settings settings) {
		txtTeam1NameSource.setText(settings.getDefaultTeam1NameSource());
		txtTeam1ForwardSource.setText(settings.getDefaultTeam1ForwardSource());
		txtTeam1GoalieSource.setText(settings.getDefaultTeam1GoalieSource());
		txtTournamentSource.setText(settings.getDefaultTournamentSource());
		txtTeam2NameSource.setText(settings.getDefaultTeam2NameSource());
		txtTeam2ForwardSource.setText(settings.getDefaultTeam2ForwardSource());
		txtTeam2GoalieSource.setText(settings.getDefaultTeam2GoalieSource());
		txtEventSource.setText(settings.getDefaultEventSource());
		txtTableNameSource.setText(settings.getDefaultTableNameSource());
		txtGameCount1Source.setText(settings.getDefaultGameCount1Source());
		txtTimeRemainingSource.setText(settings.getDefaultTimeRemainingSource());
		txtGameCount2Source.setText(settings.getDefaultGameCount2Source());
		txtTimerInUseSource.setText(settings.getDefaultTimerInUseSource());
		txtScore1Source.setText(settings.getDefaultScore1Source());
		txtMatchWinnerSource.setText(settings.getDefaultMatchWinnerSource());
		txtMeatballSource.setText(settings.getDefaultMeatballSource());
		txtScore2Source.setText(settings.getDefaultScore2Source());
		txtTimeOut1Source.setText(settings.getDefaultTimeOut1Source());
		txtTimeOut2Source.setText(settings.getDefaultTimeOut2Source());
		txtReset1Source.setText(settings.getDefaultReset1Source());
		txtReset2Source.setText(settings.getDefaultReset2Source());
		txtWarn1Source.setText(settings.getDefaultWarn1Source());
		txtWarn2Source.setText(settings.getDefaultWarn2Source());
		txtLastScoredSource.setText(settings.getDefaultLastScoredSource());
		txtGameTimeSource.setText(settings.getDefaultGameTimeSource());
		txtMatchTimeSource.setText(settings.getDefaultMatchTimeSource());
		txtStuffs1Source.setText(settings.getDefaultStuffs1Source());
		txtStuffs2Source.setText(settings.getDefaultStuffs2Source());
		txtBreaks1Source.setText(settings.getDefaultBreaks1Source());
		txtBreaks2Source.setText(settings.getDefaultBreaks2Source());
		txtAces1Source.setText(settings.getDefaultAces1Source());
		txtAces2Source.setText(settings.getDefaultAces2Source());
		txtTeam1PassAttemptsSource.setText(settings.getDefaultTeam1PassAttemptsSource());
		txtTeam2PassAttemptsSource.setText(settings.getDefaultTeam2PassAttemptsSource());
		txtTeam1ShotAttemptsSource.setText(settings.getDefaultTeam1ShotAttemptsSource());
		txtTeam2ShotAttemptsSource.setText(settings.getDefaultTeam2ShotAttemptsSource());
		txtTeam1ClearAttemptsSource.setText(settings.getDefaultTeam1ClearAttemptsSource());
		txtTeam2ClearAttemptsSource.setText(settings.getDefaultTeam2ClearAttemptsSource());
		txtTeam1PassCompletesSource.setText(settings.getDefaultTeam1PassCompletesSource());
		txtTeam2PassCompletesSource.setText(settings.getDefaultTeam2PassCompletesSource());
		txtTeam1ShotCompletesSource.setText(settings.getDefaultTeam1ShotCompletesSource());
		txtTeam2ShotCompletesSource.setText(settings.getDefaultTeam2ShotCompletesSource());
		txtTeam1ClearCompletesSource.setText(settings.getDefaultTeam1ClearCompletesSource());
		txtTeam2ClearCompletesSource.setText(settings.getDefaultTeam2ClearCompletesSource());
		txtTeam1PassPercentSource.setText(settings.getDefaultTeam1PassPercentSource());
		txtTeam2PassPercentSource.setText(settings.getDefaultTeam2PassPercentSource());
		txtTeam1ShotPercentSource.setText(settings.getDefaultTeam1ShotPercentSource());
		txtTeam2ShotPercentSource.setText(settings.getDefaultTeam2ShotPercentSource());
		txtTeam1ClearPercentSource.setText(settings.getDefaultTeam1ClearPercentSource());
		txtTeam2ClearPercentSource.setText(settings.getDefaultTeam2ClearPercentSource());
		txtTeam1ScoringSource.setText(settings.getDefaultTeam1ScoringSource());
		txtTeam2ScoringSource.setText(settings.getDefaultTeam2ScoringSource());
		txtTeam1ThreeBarScoringSource.setText(settings.getDefaultTeam1ThreeBarScoringSource());
		txtTeam2ThreeBarScoringSource.setText(settings.getDefaultTeam2ThreeBarScoringSource());
		txtTeam1FiveBarScoringSource.setText(settings.getDefaultTeam1FiveBarScoringSource());
		txtTeam2FiveBarScoringSource.setText(settings.getDefaultTeam2FiveBarScoringSource());
		txtTeam1TwoBarScoringSource.setText(settings.getDefaultTeam1TwoBarScoringSource());
		txtTeam2TwoBarScoringSource.setText(settings.getDefaultTeam2TwoBarScoringSource());
		txtTeam1ShotsOnGoalSource.setText(settings.getDefaultTeam1ShotsOnGoalSource());
		txtTeam2ShotsOnGoalSource.setText(settings.getDefaultTeam2ShotsOnGoalSource());
	}

	private void saveSettings(Settings settings) {
		settings.setTeam1NameSource(txtTeam1NameSource.getText());
		settings.setTeam1ForwardSource(txtTeam1ForwardSource.getText());
		settings.setTeam1GoalieSource(txtTeam1GoalieSource.getText());
		settings.setTournamentSource(txtTournamentSource.getText());
		settings.setTeam2NameSource(txtTeam2NameSource.getText());
		settings.setTeam2ForwardSource(txtTeam2ForwardSource.getText());
		settings.setTeam2GoalieSource(txtTeam2GoalieSource.getText());
		settings.setEventSource(txtEventSource.getText());
		settings.setTableNameSource(txtTableNameSource.getText());
		settings.setGameCount1Source(txtGameCount1Source.getText());
		settings.setTimeRemainingSource(txtTimeRemainingSource.getText());
		settings.setGameCount2Source(txtGameCount2Source.getText());
		settings.setTimerInUseSource(txtTimerInUseSource.getText());
		settings.setScore1Source(txtScore1Source.getText());
		settings.setMatchWinnerSource(txtMatchWinnerSource.getText());
		settings.setMeatballSource(txtMeatballSource.getText());
		settings.setScore2Source(txtScore2Source.getText());
		settings.setTimeOut1Source(txtTimeOut1Source.getText());
		settings.setTimeOut2Source(txtTimeOut2Source.getText());
		settings.setReset1Source(txtReset1Source.getText());
		settings.setReset2Source(txtReset2Source.getText());
		settings.setWarn1Source(txtWarn1Source.getText());
		settings.setWarn2Source(txtWarn2Source.getText());
		settings.setLastScoredSource(txtLastScoredSource.getText());
		settings.setGameTimeSource(txtGameTimeSource.getText());
		settings.setMatchTimeSource(txtMatchTimeSource.getText());
		settings.setStuffs1Source(txtStuffs1Source.getText());
		settings.setStuffs2Source(txtStuffs2Source.getText());
		settings.setBreaks1Source(txtBreaks1Source.getText());
		settings.setBreaks2Source(txtBreaks2Source.getText());
		settings.setAces1Source(txtAces1Source.getText());
		settings.setAces2Source(txtAces2Source.getText());
		settings.setTeam1PassAttemptsSource(txtTeam1PassAttemptsSource.getText());
		settings.setTeam2PassAttemptsSource(txtTeam2PassAttemptsSource.getText());
		settings.setTeam1ShotAttemptsSource(txtTeam1ShotAttemptsSource.getText());
		settings.setTeam2ShotAttemptsSource(txtTeam2ShotAttemptsSource.getText());
		settings.setTeam1ClearAttemptsSource(txtTeam1ClearAttemptsSource.getText());
		settings.setTeam2ClearAttemptsSource(txtTeam2ClearAttemptsSource.getText());
		settings.setTeam1PassCompletesSource(txtTeam1PassCompletesSource.getText());
		settings.setTeam2PassCompletesSource(txtTeam2PassCompletesSource.getText());
		settings.setTeam1ShotCompletesSource(txtTeam1ShotCompletesSource.getText());
		settings.setTeam2ShotCompletesSource(txtTeam2ShotCompletesSource.getText());
		settings.setTeam1ClearCompletesSource(txtTeam1ClearCompletesSource.getText());
		settings.setTeam2ClearCompletesSource(txtTeam2ClearCompletesSource.getText());
		settings.setTeam1PassPercentSource(txtTeam1PassPercentSource.getText());
		settings.setTeam2PassPercentSource(txtTeam2PassPercentSource.getText());
		settings.setTeam1ShotPercentSource(txtTeam1ShotPercentSource.getText());
		settings.setTeam2ShotPercentSource(txtTeam2ShotPercentSource.getText());
		settings.setTeam1ClearPercentSource(txtTeam1ClearPercentSource.getText());
		settings.setTeam2ClearPercentSource(txtTeam2ClearPercentSource.getText());
		settings.setTeam1ScoringSource(txtTeam1ScoringSource.getText());
		settings.setTeam2ScoringSource(txtTeam2ScoringSource.getText());
		settings.setTeam1ThreeBarScoringSource(txtTeam1ThreeBarScoringSource.getText());
		settings.setTeam2ThreeBarScoringSource(txtTeam2ThreeBarScoringSource.getText());
		settings.setTeam1FiveBarScoringSource(txtTeam1FiveBarScoringSource.getText());
		settings.setTeam2FiveBarScoringSource(txtTeam2FiveBarScoringSource.getText());
		settings.setTeam1TwoBarScoringSource(txtTeam1TwoBarScoringSource.getText());
		settings.setTeam2TwoBarScoringSource(txtTeam2TwoBarScoringSource.getText());
		settings.setTeam1ShotsOnGoalSource(txtTeam1ShotsOnGoalSource.getText());
		settings.setTeam2ShotsOnGoalSource(txtTeam2ShotsOnGoalSource.getText());
		try {
			settings.saveSourceConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
		}
	}
	public void setLayout(Settings settings) {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

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
								System.out.println(Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage()); //$NON-NLS-1$
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					obsInterface.setFilePath(formattedTxtPath.getText());
					try {
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveSourceConfig();
					} catch (IOException ex) {
						System.out.print(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
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
					obsInterface.setFilePath(formattedTxtPath.getText());
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveSourceConfig();
		    	} catch (IOException ex) {
		    		System.out.print(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						obsInterface.setFilePath(formattedTxtPath.getText());
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveSourceConfig();
			    	} catch (IOException ex) {
			    		System.out.print(Messages.getString("SourcesPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

		JLabel lblSource = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSource, "cell 2 1,alignx left"); //$NON-NLS-1$

		JLabel lblSourceCol2 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol2, "cell 5 1,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblSourceCol3 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol3, "cell 8 1,alignx left"); //$NON-NLS-1$
		
		JLabel lblSourceCol4 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol4, "cell 11 1"); //$NON-NLS-1$

		JLabel lblTeam1NameSource = new JLabel(Messages.getString("SourcesPanel.Team1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1NameSource, "cell 1 2,alignx right"); //$NON-NLS-1$

		txtTeam1NameSource = new JTextField();
		txtTeam1NameSource.setText(settings.getTeamNameSource(1));
		add(txtTeam1NameSource, "cell 2 2,alignx left"); //$NON-NLS-1$
		txtTeam1NameSource.setColumns(10);

		JLabel lblTeam1Forward = new JLabel(Messages.getString("SourcesPanel.Team1Forward", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 4 2,alignx right"); //$NON-NLS-1$

		txtTeam1ForwardSource = new JTextField();
		txtTeam1ForwardSource.setText(settings.getTeamForwardSource(1));
		add(txtTeam1ForwardSource, "cell 5 2,alignx left"); //$NON-NLS-1$
		txtTeam1ForwardSource.setColumns(10);
		
		JLabel lblTeam1PassAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team1PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassAttemptsSource, "cell 7 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassAttemptsSource = new JTextField();
		txtTeam1PassAttemptsSource.setText(settings.getPassAttemptsSource(1));
		add(txtTeam1PassAttemptsSource, "cell 8 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassAttemptsSource.setColumns(20);
		
		JLabel lblTeam1PassPercentSource = new JLabel(Messages.getString("SourcesPanel.Team1PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassPercentSource, "cell 10 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassPercentSource = new JTextField();
		txtTeam1PassPercentSource.setText(settings.getPassPercentSource(1));
		add(txtTeam1PassPercentSource, "cell 11 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassPercentSource.setColumns(20);

		JLabel lblTeam2NameSource = new JLabel(Messages.getString("SourcesPanel.Team2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2NameSource, "cell 1 3,alignx right"); //$NON-NLS-1$

		txtTeam2NameSource = new JTextField();
		txtTeam2NameSource.setText(settings.getTeamNameSource(2));
		add(txtTeam2NameSource, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2NameSource.setColumns(10);

		JLabel lblTeam1Goalie = new JLabel(Messages.getString("SourcesPanel.Team1Goalie", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Goalie, "cell 4 3,alignx right"); //$NON-NLS-1$

		txtTeam1GoalieSource = new JTextField();
		txtTeam1GoalieSource.setText(settings.getTeamGoalieSource(1));
		add(txtTeam1GoalieSource, "cell 5 3,alignx left"); //$NON-NLS-1$
		txtTeam1GoalieSource.setColumns(10);
		
		JLabel lblTeam1PassCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team1PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassCompletesSource, "cell 7 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassCompletesSource = new JTextField();
		txtTeam1PassCompletesSource.setText(settings.getPassCompletesSource(1));
		add(txtTeam1PassCompletesSource, "cell 8 3,alignx left"); //$NON-NLS-1$
		txtTeam1PassCompletesSource.setColumns(20);
		
		JLabel lblTeam2PassPercentSource = new JLabel(Messages.getString("SourcesPanel.Team2PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassPercentSource, "cell 10 3,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassPercentSource = new JTextField();
		txtTeam2PassPercentSource.setText(settings.getPassPercentSource(2));
		add(txtTeam2PassPercentSource, "cell 11 3,alignx left,aligny top"); //$NON-NLS-1$
		txtTeam2PassPercentSource.setColumns(20);

		JLabel lblGameCountSource = new JLabel(Messages.getString("SourcesPanel.Team1GameCount", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCountSource, "cell 1 4,alignx right"); //$NON-NLS-1$

		txtGameCount1Source = new JTextField();
		txtGameCount1Source.setText(settings.getGameCountSource(1));
		add(txtGameCount1Source, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtGameCount1Source.setColumns(10);

		JLabel lblTeam2Forward = new JLabel(Messages.getString("SourcesPanel.Team2Forward", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Forward, "cell 4 4,alignx right"); //$NON-NLS-1$

		txtTeam2ForwardSource = new JTextField();
		txtTeam2ForwardSource.setText(settings.getTeamForwardSource(2));
		add(txtTeam2ForwardSource, "cell 5 4,alignx left"); //$NON-NLS-1$
		txtTeam2ForwardSource.setColumns(10);

		JLabel lblTeam2PassAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team2PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassAttemptsSource, "cell 7 4,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassAttemptsSource = new JTextField();
		txtTeam2PassAttemptsSource.setText(settings.getPassAttemptsSource(2));
		add(txtTeam2PassAttemptsSource, "cell 8 4,alignx left"); //$NON-NLS-1$
		txtTeam2PassAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ShotPercentSource = new JLabel(Messages.getString("SourcesPanel.Team1ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotPercentSource, "cell 10 4,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotPercentSource = new JTextField();
		txtTeam1ShotPercentSource.setText(settings.getShotPercentSource(1));
		add(txtTeam1ShotPercentSource, "cell 11 4,alignx left"); //$NON-NLS-1$
		txtTeam1ShotPercentSource.setColumns(20);
		
		JLabel lblGameCount2Source = new JLabel(Messages.getString("SourcesPanel.GameCount2", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount2Source, "cell 1 5,alignx right"); //$NON-NLS-1$

		txtGameCount2Source = new JTextField();
		txtGameCount2Source.setText(settings.getGameCountSource(2));
		add(txtGameCount2Source, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtGameCount2Source.setColumns(10);

		JLabel lblTeam2Name2 = new JLabel(Messages.getString("SourcesPanel.Team2Goalie", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Name2, "cell 4 5,alignx right"); //$NON-NLS-1$

		txtTeam2GoalieSource = new JTextField();
		txtTeam2GoalieSource.setText(settings.getTeamGoalieSource(2));
		add(txtTeam2GoalieSource, "cell 5 5,alignx left"); //$NON-NLS-1$
		txtTeam2GoalieSource.setColumns(10);

		JLabel lblTeam2PassCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team2PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassCompletesSource, "cell 7 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassCompletesSource = new JTextField();
		txtTeam2PassCompletesSource.setText(settings.getPassCompletesSource(2));
		add(txtTeam2PassCompletesSource, "cell 8 5,alignx left"); //$NON-NLS-1$
		txtTeam2PassCompletesSource.setColumns(20);
		
		JLabel lblTeam2ShotPercentSource = new JLabel(Messages.getString("SourcesPanel.Team2ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotPercentSource, "cell 10 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotPercentSource = new JTextField();
		txtTeam2ShotPercentSource.setText(settings.getShotPercentSource(2));
		add(txtTeam2ShotPercentSource, "cell 11 5,growx"); //$NON-NLS-1$
		txtTeam2ShotPercentSource.setColumns(20);

		JLabel lblScore1Source = new JLabel(Messages.getString("SourcesPanel.Score1", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1Source, "cell 1 6,alignx right"); //$NON-NLS-1$

		txtScore1Source = new JTextField();
		txtScore1Source.setText(settings.getScoreSource(1));
		add(txtScore1Source, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtScore1Source.setColumns(10);

		JLabel lblTournamentSource = new JLabel(Messages.getString("SourcesPanel.Tournament", settings.getGameType())); //$NON-NLS-1$
		add(lblTournamentSource, "cell 4 6,alignx right"); //$NON-NLS-1$

		txtTournamentSource = new JTextField();
		txtTournamentSource.setText(settings.getTournamentSource());
		add(txtTournamentSource, "cell 5 6,alignx left"); //$NON-NLS-1$
		txtTournamentSource.setColumns(10);

		JLabel lblTeam1ShotAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team1ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotAttemptsSource, "cell 7 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotAttemptsSource = new JTextField();
		txtTeam1ShotAttemptsSource.setText(settings.getShotAttemptsSource(1));
		add(txtTeam1ShotAttemptsSource, "cell 8 6,alignx left"); //$NON-NLS-1$
		txtTeam1ShotAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ClearPercentSource = new JLabel(Messages.getString("SourcesPanel.Team1ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearPercentSource, "cell 10 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearPercentSource = new JTextField();
		txtTeam1ClearPercentSource.setText(settings.getClearPercentSource(1));
		add(txtTeam1ClearPercentSource, "cell 11 6,alignx left"); //$NON-NLS-1$
		txtTeam1ClearPercentSource.setColumns(20);

		JLabel lblScore2Source = new JLabel(Messages.getString("SourcesPanel.Score2", settings.getGameType())); //$NON-NLS-1$
		add(lblScore2Source, "cell 1 7,alignx right"); //$NON-NLS-1$

		txtScore2Source = new JTextField();
		txtScore2Source.setText(settings.getScoreSource(2));
		add(txtScore2Source, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtScore2Source.setColumns(10);

		JLabel lblEventSource = new JLabel(Messages.getString("SourcesPanel.Event", settings.getGameType())); //$NON-NLS-1$
		add(lblEventSource, "cell 4 7,alignx right"); //$NON-NLS-1$

		txtEventSource = new JTextField();
		txtEventSource.setText(settings.getEventSource());
		add(txtEventSource, "cell 5 7,alignx left"); //$NON-NLS-1$
		txtEventSource.setColumns(10);
		
		JLabel lblTableNameSource = new JLabel(Messages.getString("SourcesPanel.TableName", settings.getGameType())); //$NON-NLS-1$
		add(lblTableNameSource, "cell 1 16,alignx right"); //$NON-NLS-1$
		
		txtTableNameSource = new JTextField();
		txtTableNameSource.setText(settings.getTableNameSource());
		add(txtTableNameSource, "cell 2 16,alignx left");
		txtTableNameSource.setColumns(10);

		JLabel lblTeam1ShotCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team1ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotCompletesSource, "cell 7 7,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotCompletesSource = new JTextField();
		txtTeam1ShotCompletesSource.setText(settings.getShotCompletesSource(1));
		add(txtTeam1ShotCompletesSource, "cell 8 7,alignx left"); //$NON-NLS-1$
		txtTeam1ShotCompletesSource.setColumns(20);
		
		JLabel lblTeam2ClearPercentSource = new JLabel(Messages.getString("SourcesPanel.Team2ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearPercentSource, "cell 10 7,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearPercentSource = new JTextField();
		txtTeam2ClearPercentSource.setText(settings.getClearPercentSource(2));
		add(txtTeam2ClearPercentSource, "cell 11 7,alignx left"); //$NON-NLS-1$
		txtTeam2ClearPercentSource.setColumns(20);

		JLabel lblTimeOut1Source = new JLabel(Messages.getString("SourcesPanel.TimeOut1", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1Source, "cell 1 8,alignx right"); //$NON-NLS-1$

		txtTimeOut1Source = new JTextField();
		txtTimeOut1Source.setText(settings.getTimeOutSource(1));
		add(txtTimeOut1Source, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTimeOut1Source.setColumns(10);

		JLabel lblTimeRemainingSource = new JLabel(Messages.getString("SourcesPanel.TimeRemaining", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeRemainingSource, "cell 4 8,alignx right"); //$NON-NLS-1$

		txtTimeRemainingSource = new JTextField();
		txtTimeRemainingSource.setText(settings.getTimeRemainingSource());
		add(txtTimeRemainingSource, "cell 5 8,alignx left"); //$NON-NLS-1$
		txtTimeRemainingSource.setColumns(10);

		JLabel lblTeam2ShotAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team2ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotAttemptsSource, "cell 7 8,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotAttemptsSource = new JTextField();
		txtTeam2ShotAttemptsSource.setText(settings.getShotAttemptsSource(2));
		add(txtTeam2ShotAttemptsSource, "cell 8 8,alignx left"); //$NON-NLS-1$
		txtTeam2ShotAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ScoringSource = new JLabel(Messages.getString("SourcesPanel.Team1Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ScoringSource, "cell 10 8,alignx right"); //$NON-NLS-1$
		
		txtTeam1ScoringSource = new JTextField();
		txtTeam1ScoringSource.setText(settings.getScoringSource(1));
		add(txtTeam1ScoringSource, "cell 11 8,alignx left"); //$NON-NLS-1$
		txtTeam1ScoringSource.setColumns(10);

		JLabel lblTimeOut2Source = new JLabel(Messages.getString("SourcesPanel.TimeOut2", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut2Source, "cell 1 9,alignx right"); //$NON-NLS-1$

		txtTimeOut2Source = new JTextField();
		txtTimeOut2Source.setText(settings.getTimeOutSource(2));
		add(txtTimeOut2Source, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTimeOut2Source.setColumns(10);

		JLabel lblTimerSource = new JLabel(Messages.getString("SourcesPanel.Timer", settings.getGameType())); //$NON-NLS-1$
		add(lblTimerSource, "cell 4 9,alignx right"); //$NON-NLS-1$

		txtTimerInUseSource = new JTextField();
		txtTimerInUseSource.setText(settings.getTimerInUseSource());
		add(txtTimerInUseSource, "cell 5 9,alignx left"); //$NON-NLS-1$
		txtTimerInUseSource.setColumns(10);

		JLabel lblTeam2ShotCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team2ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotCompletesSource, "cell 7 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotCompletesSource = new JTextField();
		txtTeam2ShotCompletesSource.setText(settings.getShotCompletesSource(2));
		add(txtTeam2ShotCompletesSource, "cell 8 9,alignx left"); //$NON-NLS-1$
		txtTeam2ShotCompletesSource.setColumns(20);
		
		JLabel lblTeam2ScoringSource = new JLabel(Messages.getString("SourcesPanel.Team2Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ScoringSource, "cell 10 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ScoringSource = new JTextField();
		txtTeam2ScoringSource.setText(settings.getScoringSource(2));
		add(txtTeam2ScoringSource, "cell 11 9,alignx left"); //$NON-NLS-1$
		txtTeam2ScoringSource.setColumns(10);

		JLabel lblReset1Source = new JLabel(Messages.getString("SourcesPanel.Reset1", settings.getGameType())); //$NON-NLS-1$
		add(lblReset1Source, "cell 1 10,alignx right"); //$NON-NLS-1$

		txtReset1Source = new JTextField();
		txtReset1Source.setText(settings.getResetSource(1));
		add(txtReset1Source, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtReset1Source.setColumns(10);

		JLabel lblMatchWinnerSource = new JLabel(Messages.getString("SourcesPanel.MatchWinner", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchWinnerSource, "cell 4 10,alignx right"); //$NON-NLS-1$

		txtMatchWinnerSource = new JTextField();
		txtMatchWinnerSource.setText(settings.getMatchWinnerSource());
		add(txtMatchWinnerSource, "cell 5 10,alignx left"); //$NON-NLS-1$
		txtMatchWinnerSource.setColumns(10);

		JLabel lblTeam1ClearAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team1ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearAttemptsSource, "cell 7 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearAttemptsSource = new JTextField();
		txtTeam1ClearAttemptsSource.setText(settings.getClearAttemptsSource(1));
		add(txtTeam1ClearAttemptsSource, "cell 8 10,alignx left"); //$NON-NLS-1$
		txtTeam1ClearAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ThreeBarScoringSource = new JLabel(Messages.getString("SourcesPanel.Team13BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ThreeBarScoringSource, "cell 10 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ThreeBarScoringSource = new JTextField();
		txtTeam1ThreeBarScoringSource.setText(settings.getThreeBarScoringSource(1));
		add(txtTeam1ThreeBarScoringSource, "cell 11 10,alignx left"); //$NON-NLS-1$
		txtTeam1ThreeBarScoringSource.setColumns(10);

		JLabel lblReset2Source = new JLabel(Messages.getString("SourcesPanel.Reset2", settings.getGameType())); //$NON-NLS-1$
		add(lblReset2Source, "cell 1 11,alignx right"); //$NON-NLS-1$

		txtReset2Source = new JTextField();
		txtReset2Source.setText(settings.getResetSource(2));
		add(txtReset2Source, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtReset2Source.setColumns(10);

		JLabel lblMeatball = new JLabel(Messages.getString("SourcesPanel.Meatball", settings.getGameType())); //$NON-NLS-1$
		add(lblMeatball, "cell 4 11,alignx right"); //$NON-NLS-1$

		txtMeatballSource = new JTextField();
		txtMeatballSource.setText(settings.getMeatballSource());
		add(txtMeatballSource, "cell 5 11,alignx left"); //$NON-NLS-1$
		txtMeatballSource.setColumns(10);

		JLabel lblTeam1ClearCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team1ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearCompletesSource, "cell 7 11,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearCompletesSource = new JTextField();
		txtTeam1ClearCompletesSource.setText(settings.getClearCompletesSource(1));
		add(txtTeam1ClearCompletesSource, "cell 8 11,alignx left"); //$NON-NLS-1$
		txtTeam1ClearCompletesSource.setColumns(20);
		
		JLabel lblTeam2ThreeBarScoringSource = new JLabel(Messages.getString("SourcesPanel.Team23BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ThreeBarScoringSource, "cell 10 11,alignx right"); //$NON-NLS-1$
		
		txtTeam2ThreeBarScoringSource = new JTextField();
		txtTeam2ThreeBarScoringSource.setText(settings.getThreeBarScoringSource(2));
		add(txtTeam2ThreeBarScoringSource, "cell 11 11,alignx left"); //$NON-NLS-1$
		txtTeam2ThreeBarScoringSource.setColumns(10);

		JLabel lblWarn1Source = new JLabel(Messages.getString("SourcesPanel.Warn1", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn1Source, "cell 1 12,alignx right"); //$NON-NLS-1$

		txtWarn1Source = new JTextField();
		txtWarn1Source.setText(settings.getWarnSource(1));
		add(txtWarn1Source, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtWarn1Source.setColumns(10);

		JLabel lblLastScoredSource = new JLabel(Messages.getString("SourcesPanel.LastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblLastScoredSource, "cell 1 17,alignx right"); //$NON-NLS-1$

		txtLastScoredSource = new JTextField();
		txtLastScoredSource.setText(settings.getLastScoredSource());
		add(txtLastScoredSource, "cell 2 17,alignx left"); //$NON-NLS-1$
		txtLastScoredSource.setColumns(10);

		JLabel lblTeam2ClearAttemptsSource = new JLabel(Messages.getString("SourcesPanel.Team2ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearAttemptsSource, "cell 7 12,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearAttemptsSource = new JTextField();
		txtTeam2ClearAttemptsSource.setText(settings.getClearAttemptsSource(2));
		add(txtTeam2ClearAttemptsSource, "cell 8 12,alignx left"); //$NON-NLS-1$
		txtTeam2ClearAttemptsSource.setColumns(20);
		
		JLabel lblTeambar = new JLabel(Messages.getString("SourcesPanel.Team15BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar, "cell 10 12,alignx right"); //$NON-NLS-1$
		
		txtTeam1FiveBarScoringSource = new JTextField();
		txtTeam1FiveBarScoringSource.setText(settings.getFiveBarScoringSource(1));
		add(txtTeam1FiveBarScoringSource, "cell 11 12,alignx left"); //$NON-NLS-1$
		txtTeam1FiveBarScoringSource.setColumns(10);

		JLabel lblWarn2Source = new JLabel(Messages.getString("SourcesPanel.Warn2", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn2Source, "cell 1 13,alignx right"); //$NON-NLS-1$

		txtWarn2Source = new JTextField();
		txtWarn2Source.setText(settings.getWarnSource(2));
		add(txtWarn2Source, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtWarn2Source.setColumns(10);
		
		JLabel lblStuffs1Source = new JLabel(Messages.getString("SourcesPanel.Stuffs1", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs1Source, "cell 4 14,alignx right"); //$NON-NLS-1$
		
		txtStuffs1Source = new JTextField();
		txtStuffs1Source.setText(settings.getStuffsSource(1));
		add(txtStuffs1Source, "cell 5 14,alignx left"); //$NON-NLS-1$
		txtStuffs1Source.setColumns(10);

		JLabel lblTeam2ClearCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team2ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearCompletesSource, "cell 7 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearCompletesSource = new JTextField();
		txtTeam2ClearCompletesSource.setText(settings.getClearCompletesSource(2));		add(txtTeam2ClearCompletesSource, "cell 8 13,alignx left"); //$NON-NLS-1$
		txtTeam2ClearCompletesSource.setColumns(20);

		JButton btnSaveSources = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		
		JLabel lblTeam1SOG = new JLabel(Messages.getString("SourcesPanel.Team1ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SOG, "cell 10 16,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1ShotsOnGoalSource = new JTextField();
		txtTeam1ShotsOnGoalSource.setText(settings.getShotsOnGoalSource(1));
		add(txtTeam1ShotsOnGoalSource, "cell 11 16,alignx left"); //$NON-NLS-1$
		txtTeam1ShotsOnGoalSource.setColumns(10);
		
		JLabel lblTeam2SOG = new JLabel(Messages.getString("SourcesPanel.Team2ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SOG, "cell 10 17,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2ShotsOnGoalSource = new JTextField();
		txtTeam2ShotsOnGoalSource.setText(settings.getShotsOnGoalSource(2));
		add(txtTeam2ShotsOnGoalSource, "cell 11 17,alignx left"); //$NON-NLS-1$
		txtTeam2ShotsOnGoalSource.setColumns(10);
		add(btnSaveSources, "cell 2 19,alignx center"); //$NON-NLS-1$
		
		JLabel lblTeambar_1 = new JLabel(Messages.getString("SourcesPanel.Team25BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar_1, "cell 10 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2FiveBarScoringSource = new JTextField();
		txtTeam2FiveBarScoringSource.setText(settings.getFiveBarScoringSource(2));
		add(txtTeam2FiveBarScoringSource, "cell 11 13,alignx left"); //$NON-NLS-1$
		txtTeam2FiveBarScoringSource.setColumns(10);
		
		JLabel lblGameTimeSource = new JLabel(Messages.getString("SourcesPanel.GameTime", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimeSource, "cell 1 14,alignx right"); //$NON-NLS-1$
		
		txtGameTimeSource = new JTextField();
		txtGameTimeSource.setText(settings.getGameTimeSource());
		add(txtGameTimeSource, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtGameTimeSource.setColumns(10);
		
		JLabel lblStuffs2Source = new JLabel(Messages.getString("SourcesPanel.Stuffs2", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs2Source, "cell 4 15,alignx right"); //$NON-NLS-1$
		
		txtStuffs2Source = new JTextField();
		txtStuffs2Source.setText(settings.getStuffsSource(2));
		add(txtStuffs2Source, "cell 5 15,alignx left"); //$NON-NLS-1$
		txtStuffs2Source.setColumns(10);
		
		JLabel lblTeam1TwoBarPassAttemptsSource_1 = new JLabel(Messages.getString("SourcesPanel.Team12BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassAttemptsSource_1, "cell 7 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassAttemptsSource = new JTextField();
		txtTeam1TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource(1));
		txtTeam1TwoBarPassAttemptsSource.setColumns(20);
		add(txtTeam1TwoBarPassAttemptsSource, "cell 8 14,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam1TwoBarScoringSource = new JLabel(Messages.getString("SourcesPanel.Team12BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarScoringSource, "cell 10 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarScoringSource = new JTextField();
		txtTeam1TwoBarScoringSource.setText(settings.getTwoBarScoringSource(1));
		add(txtTeam1TwoBarScoringSource, "cell 11 14,alignx left"); //$NON-NLS-1$
		txtTeam1TwoBarScoringSource.setColumns(10);
		
		JLabel lblMatchTimeSource = new JLabel(Messages.getString("SourcesPanel.MatchTime", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchTimeSource, "cell 1 15,alignx right"); //$NON-NLS-1$
		
		txtMatchTimeSource = new JTextField();
		txtMatchTimeSource.setText(settings.getMatchTimeSource());
		add(txtMatchTimeSource, "cell 2 15,alignx left"); //$NON-NLS-1$
		txtMatchTimeSource.setColumns(10);
		
		JLabel lblBreaks1Source = new JLabel(Messages.getString("SourcesPanel.Breaks1", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks1Source, "cell 4 16,alignx right"); //$NON-NLS-1$
		
		txtBreaks1Source = new JTextField();
		txtBreaks1Source.setText(settings.getBreaksSource(1));
		add(txtBreaks1Source, "cell 5 16,alignx left"); //$NON-NLS-1$
		txtBreaks1Source.setColumns(10);
		
		JLabel lblTeam1TwoBarPassCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team12BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassCompletesSource, "cell 7 15,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassCompletesSource = new JTextField();
		txtTeam1TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource(1));
		txtTeam1TwoBarPassCompletesSource.setColumns(20);
		add(txtTeam1TwoBarPassCompletesSource, "cell 8 15,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarScoringSource = new JLabel(Messages.getString("SourcesPanel.Team22BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarScoringSource, "cell 10 15,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarScoringSource = new JTextField();
		txtTeam2TwoBarScoringSource.setText(settings.getTwoBarScoringSource(2));
		add(txtTeam2TwoBarScoringSource, "cell 11 15,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarScoringSource.setColumns(10);
		
		JLabel lblBreaks2Source = new JLabel(Messages.getString("SourcesPanel.Breaks2", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks2Source, "cell 4 17,alignx right"); //$NON-NLS-1$
		
		txtBreaks2Source = new JTextField();
		txtBreaks2Source.setText(settings.getBreaksSource(2));
		add(txtBreaks2Source, "cell 5 17,alignx left"); //$NON-NLS-1$
		txtBreaks2Source.setColumns(10);
		
		JLabel lblAces1Source = new JLabel(Messages.getString("SourcesPanel.Aces1", settings.getGameType())); //$NON-NLS-1$
		add(lblAces1Source, "cell 4 12,alignx right"); //$NON-NLS-1$
		
		txtAces1Source = new JTextField();
		txtAces1Source.setText(settings.getAcesSource(1));
		add(txtAces1Source, "cell 5 12,alignx left"); //$NON-NLS-1$
		txtAces1Source.setColumns(10);
		
		JLabel lblAces2Source = new JLabel(Messages.getString("SourcesPanel.Aces2", settings.getGameType())); //$NON-NLS-1$
		add(lblAces2Source, "cell 4 13,alignx right"); //$NON-NLS-1$
		
		txtAces2Source = new JTextField();
		txtAces2Source.setText(settings.getAcesSource(2));
		add(txtAces2Source, "cell 5 13,alignx left"); //$NON-NLS-1$
		txtAces2Source.setColumns(10);
		
		JLabel lblTeam2TwoBarPassAttemptsSource_1_1 = new JLabel(Messages.getString("SourcesPanel.Team22BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassAttemptsSource_1_1, "cell 7 16,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassAttemptsSource = new JTextField();
		txtTeam2TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource(2));
		txtTeam2TwoBarPassAttemptsSource.setColumns(20);
		add(txtTeam2TwoBarPassAttemptsSource, "cell 8 16,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarPassCompletesSource = new JLabel(Messages.getString("SourcesPanel.Team22BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassCompletesSource, "cell 7 17,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassCompletesSource = new JTextField();
		txtTeam2TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource(2));
		txtTeam2TwoBarPassCompletesSource.setColumns(20);
		add(txtTeam2TwoBarPassCompletesSource, "cell 8 17,alignx left"); //$NON-NLS-1$

		JButton btnCancelSources = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelSources, "cell 4 19,alignx center"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
}
