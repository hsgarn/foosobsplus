/**
Copyright 2023-2024 Hugh Garner
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

public class StatSourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtTeam1PassAttemptsSource;
	private JTextField txtTeam2PassAttemptsSource;
	private JTextField txtTeam3PassAttemptsSource;
	private JTextField txtTeam1PassCompletesSource;
	private JTextField txtTeam2PassCompletesSource;
	private JTextField txtTeam3PassCompletesSource;
	private JTextField txtTeam1PassPercentSource;
	private JTextField txtTeam2PassPercentSource;
	private JTextField txtTeam3PassPercentSource;
	private JTextField txtTeam1ShotAttemptsSource;
	private JTextField txtTeam2ShotAttemptsSource;
	private JTextField txtTeam3ShotAttemptsSource;
	private JTextField txtTeam1ShotCompletesSource;
	private JTextField txtTeam2ShotCompletesSource;
	private JTextField txtTeam3ShotCompletesSource;
	private JTextField txtTeam1ShotPercentSource;
	private JTextField txtTeam2ShotPercentSource;
	private JTextField txtTeam3ShotPercentSource;
	private JTextField txtTeam1ClearAttemptsSource;
	private JTextField txtTeam2ClearAttemptsSource;
	private JTextField txtTeam3ClearAttemptsSource;
	private JTextField txtTeam1ClearCompletesSource;
	private JTextField txtTeam2ClearCompletesSource;
	private JTextField txtTeam3ClearCompletesSource;
	private JTextField txtTeam1ClearPercentSource;
	private JTextField txtTeam2ClearPercentSource;
	private JTextField txtTeam3ClearPercentSource;
	private JTextField txtTeam1TwoBarPassAttemptsSource;
	private JTextField txtTeam2TwoBarPassAttemptsSource;
	private JTextField txtTeam3TwoBarPassAttemptsSource;
	private JTextField txtTeam1TwoBarPassCompletesSource;
	private JTextField txtTeam2TwoBarPassCompletesSource;
	private JTextField txtTeam3TwoBarPassCompletesSource;
	private JTextField txtTeam1TwoBarPassPercentSource;
	private JTextField txtTeam2TwoBarPassPercentSource;
	private JTextField txtTeam3TwoBarPassPercentSource;
	private JTextField txtTeam1AcesSource;
	private JTextField txtTeam2AcesSource;
	private JTextField txtTeam3AcesSource;
	private JTextField txtTeam1StuffsSource;
	private JTextField txtTeam2StuffsSource;
	private JTextField txtTeam3StuffsSource;
	private JTextField txtTeam1BreaksSource;
	private JTextField txtTeam2BreaksSource;
	private JTextField txtTeam3BreaksSource;
	private JTextField txtTeam1ScoringSource;
	private JTextField txtTeam2ScoringSource;
	private JTextField txtTeam3ScoringSource;
	private JTextField txtTeam1ThreeBarScoringSource;
	private JTextField txtTeam2ThreeBarScoringSource;
	private JTextField txtTeam3ThreeBarScoringSource;
	private JTextField txtTeam1FiveBarScoringSource;
	private JTextField txtTeam2FiveBarScoringSource;
	private JTextField txtTeam3FiveBarScoringSource;
	private JTextField txtTeam1TwoBarScoringSource;
	private JTextField txtTeam2TwoBarScoringSource;
	private JTextField txtTeam3TwoBarScoringSource;
	private JTextField txtTeam1ShotsOnGoalSource;
	private JTextField txtTeam2ShotsOnGoalSource;
	private JTextField txtTeam3ShotsOnGoalSource;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$
	private static transient Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// Create the Panel.

	public StatSourcesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);
	}
	
	private void restoreDefaults(Settings settings) {
		txtTeam1PassAttemptsSource.setText(settings.getDefaultTeamPassAttemptsSource("1"));
		txtTeam2PassAttemptsSource.setText(settings.getDefaultTeamPassAttemptsSource("2"));
		txtTeam3PassAttemptsSource.setText(settings.getDefaultTeamPassAttemptsSource("3"));
		txtTeam1PassCompletesSource.setText(settings.getDefaultTeamPassCompletesSource("1"));
		txtTeam2PassCompletesSource.setText(settings.getDefaultTeamPassCompletesSource("2"));
		txtTeam3PassCompletesSource.setText(settings.getDefaultTeamPassCompletesSource("3"));
		txtTeam1PassPercentSource.setText(settings.getDefaultTeamPassPercentSource("1"));
		txtTeam2PassPercentSource.setText(settings.getDefaultTeamPassPercentSource("2"));
		txtTeam3PassPercentSource.setText(settings.getDefaultTeamPassPercentSource("3"));
		txtTeam1ShotAttemptsSource.setText(settings.getDefaultTeamShotAttemptsSource("1"));
		txtTeam2ShotAttemptsSource.setText(settings.getDefaultTeamShotAttemptsSource("2"));
		txtTeam3ShotAttemptsSource.setText(settings.getDefaultTeamShotAttemptsSource("3"));
		txtTeam1ShotCompletesSource.setText(settings.getDefaultTeamShotCompletesSource("1"));
		txtTeam2ShotCompletesSource.setText(settings.getDefaultTeamShotCompletesSource("2"));
		txtTeam3ShotCompletesSource.setText(settings.getDefaultTeamShotCompletesSource("3"));
		txtTeam1ShotPercentSource.setText(settings.getDefaultTeamShotPercentSource("1"));
		txtTeam2ShotPercentSource.setText(settings.getDefaultTeamShotPercentSource("2"));
		txtTeam3ShotPercentSource.setText(settings.getDefaultTeamShotPercentSource("3"));
		txtTeam1ClearAttemptsSource.setText(settings.getDefaultTeamClearAttemptsSource("1"));
		txtTeam2ClearAttemptsSource.setText(settings.getDefaultTeamClearAttemptsSource("2"));
		txtTeam3ClearAttemptsSource.setText(settings.getDefaultTeamClearAttemptsSource("3"));
		txtTeam1ClearCompletesSource.setText(settings.getDefaultTeamClearCompletesSource("1"));
		txtTeam2ClearCompletesSource.setText(settings.getDefaultTeamClearCompletesSource("2"));
		txtTeam3ClearCompletesSource.setText(settings.getDefaultTeamClearCompletesSource("3"));
		txtTeam1ClearPercentSource.setText(settings.getDefaultTeamClearPercentSource("1"));
		txtTeam2ClearPercentSource.setText(settings.getDefaultTeamClearPercentSource("2"));
		txtTeam3ClearPercentSource.setText(settings.getDefaultTeamClearPercentSource("3"));
		txtTeam1TwoBarPassAttemptsSource.setText(settings.getDefaultTeamTwoBarPassAttemptsSource("1"));
		txtTeam2TwoBarPassAttemptsSource.setText(settings.getDefaultTeamTwoBarPassAttemptsSource("2"));
		txtTeam3TwoBarPassAttemptsSource.setText(settings.getDefaultTeamTwoBarPassAttemptsSource("3"));
		txtTeam1TwoBarPassCompletesSource.setText(settings.getDefaultTeamTwoBarPassCompletesSource("1"));
		txtTeam2TwoBarPassCompletesSource.setText(settings.getDefaultTeamTwoBarPassCompletesSource("2"));
		txtTeam3TwoBarPassCompletesSource.setText(settings.getDefaultTeamTwoBarPassCompletesSource("3"));
		txtTeam1TwoBarPassPercentSource.setText(settings.getDefaultTeamTwoBarPassPercentSource("1"));
		txtTeam2TwoBarPassPercentSource.setText(settings.getDefaultTeamTwoBarPassPercentSource("2"));
		txtTeam3TwoBarPassPercentSource.setText(settings.getDefaultTeamTwoBarPassPercentSource("3"));
		txtTeam1AcesSource.setText(settings.getDefaultTeamAcesSource("1"));
		txtTeam2AcesSource.setText(settings.getDefaultTeamAcesSource("2"));
		txtTeam3AcesSource.setText(settings.getDefaultTeamAcesSource("3"));
		txtTeam1StuffsSource.setText(settings.getDefaultTeamStuffsSource("1"));
		txtTeam2StuffsSource.setText(settings.getDefaultTeamStuffsSource("2"));
		txtTeam3StuffsSource.setText(settings.getDefaultTeamStuffsSource("3"));
		txtTeam1BreaksSource.setText(settings.getDefaultTeamBreaksSource("1"));
		txtTeam2BreaksSource.setText(settings.getDefaultTeamBreaksSource("2"));
		txtTeam3BreaksSource.setText(settings.getDefaultTeamBreaksSource("3"));
		txtTeam1ScoringSource.setText(settings.getDefaultTeamScoringSource("1"));
		txtTeam2ScoringSource.setText(settings.getDefaultTeamScoringSource("2"));
		txtTeam3ScoringSource.setText(settings.getDefaultTeamScoringSource("3"));
		txtTeam1ThreeBarScoringSource.setText(settings.getDefaultTeamThreeBarScoringSource("1"));
		txtTeam2ThreeBarScoringSource.setText(settings.getDefaultTeamThreeBarScoringSource("2"));
		txtTeam3ThreeBarScoringSource.setText(settings.getDefaultTeamThreeBarScoringSource("3"));
		txtTeam1FiveBarScoringSource.setText(settings.getDefaultTeamFiveBarScoringSource("1"));
		txtTeam2FiveBarScoringSource.setText(settings.getDefaultTeamFiveBarScoringSource("2"));
		txtTeam3FiveBarScoringSource.setText(settings.getDefaultTeamFiveBarScoringSource("3"));
		txtTeam1TwoBarScoringSource.setText(settings.getDefaultTeamTwoBarScoringSource("1"));
		txtTeam2TwoBarScoringSource.setText(settings.getDefaultTeamTwoBarScoringSource("2"));
		txtTeam3TwoBarScoringSource.setText(settings.getDefaultTeamTwoBarScoringSource("3"));
		txtTeam1ShotsOnGoalSource.setText(settings.getDefaultTeamShotsOnGoalSource("1"));
		txtTeam2ShotsOnGoalSource.setText(settings.getDefaultTeamShotsOnGoalSource("2"));
		txtTeam3ShotsOnGoalSource.setText(settings.getDefaultTeamShotsOnGoalSource("3"));
	}

	private void saveSettings(Settings settings) {
		settings.setSource("Team1PassAttemptsSource",txtTeam1PassAttemptsSource.getText());
		settings.setSource("Team2PassAttemptsSource",txtTeam2PassAttemptsSource.getText());
		settings.setSource("Team3PassAttemptsSource",txtTeam3PassAttemptsSource.getText());
		settings.setSource("Team1PassCompletesSource",txtTeam1PassCompletesSource.getText());
		settings.setSource("Team2PassCompletesSource",txtTeam2PassCompletesSource.getText());
		settings.setSource("Team3PassCompletesSource",txtTeam3PassCompletesSource.getText());
		settings.setSource("Team1PassPercentSource",txtTeam1PassPercentSource.getText());
		settings.setSource("Team2PassPercentSource",txtTeam2PassPercentSource.getText());
		settings.setSource("Team3PassPercentSource",txtTeam3PassPercentSource.getText());
		settings.setSource("Team1ShotAttemptsSource",txtTeam1ShotAttemptsSource.getText());
		settings.setSource("Team2ShotAttemptsSource",txtTeam2ShotAttemptsSource.getText());
		settings.setSource("Team3ShotAttemptsSource",txtTeam3ShotAttemptsSource.getText());
		settings.setSource("Team1ShotCompletesSource",txtTeam1ShotCompletesSource.getText());
		settings.setSource("Team2ShotCompletesSource",txtTeam2ShotCompletesSource.getText());
		settings.setSource("Team3ShotCompletesSource",txtTeam3ShotCompletesSource.getText());
		settings.setSource("Team1ShotPercentSource",txtTeam1ShotPercentSource.getText());
		settings.setSource("Team2ShotPercentSource",txtTeam2ShotPercentSource.getText());
		settings.setSource("Team3ShotPercentSource",txtTeam3ShotPercentSource.getText());
		settings.setSource("Team1ClearAttemptsSource",txtTeam1ClearAttemptsSource.getText());
		settings.setSource("Team2ClearAttemptsSource",txtTeam2ClearAttemptsSource.getText());
		settings.setSource("Team3ClearAttemptsSource",txtTeam3ClearAttemptsSource.getText());
		settings.setSource("Team1ClearCompletesSource",txtTeam1ClearCompletesSource.getText());
		settings.setSource("Team2ClearCompletesSource",txtTeam2ClearCompletesSource.getText());
		settings.setSource("Team3ClearCompletesSource",txtTeam3ClearCompletesSource.getText());
		settings.setSource("Team1ClearPercentSource",txtTeam1ClearPercentSource.getText());
		settings.setSource("Team2ClearPercentSource",txtTeam2ClearPercentSource.getText());
		settings.setSource("Team3ClearPercentSource",txtTeam3ClearPercentSource.getText());
		settings.setSource("Aces1Source",txtTeam1AcesSource.getText());
		settings.setSource("Aces2Source",txtTeam2AcesSource.getText());
		settings.setSource("Aces3Source",txtTeam3AcesSource.getText());
		settings.setSource("Stuffs1Source",txtTeam1StuffsSource.getText());
		settings.setSource("Stuffs2Source",txtTeam2StuffsSource.getText());
		settings.setSource("Stuffs3Source",txtTeam3StuffsSource.getText());
		settings.setSource("Breaks1Source",txtTeam1BreaksSource.getText());
		settings.setSource("Breaks2Source",txtTeam2BreaksSource.getText());
		settings.setSource("Breaks3Source",txtTeam3BreaksSource.getText());
		settings.setSource("Team1ScoringSource",txtTeam1ScoringSource.getText());
		settings.setSource("Team2ScoringSource",txtTeam2ScoringSource.getText());
		settings.setSource("Team3ScoringSource",txtTeam3ScoringSource.getText());
		settings.setSource("Team1ThreeBarScoringSource",txtTeam1ThreeBarScoringSource.getText());
		settings.setSource("Team2ThreeBarScoringSource",txtTeam2ThreeBarScoringSource.getText());
		settings.setSource("Team3ThreeBarScoringSource",txtTeam3ThreeBarScoringSource.getText());
		settings.setSource("Team1FiveBarScoringSource",txtTeam1FiveBarScoringSource.getText());
		settings.setSource("Team2FiveBarScoringSource",txtTeam2FiveBarScoringSource.getText());
		settings.setSource("Team3FiveBarScoringSource",txtTeam3FiveBarScoringSource.getText());
		settings.setSource("Team1TwoBarScoringSource",txtTeam1TwoBarScoringSource.getText());
		settings.setSource("Team2TwoBarScoringSource",txtTeam2TwoBarScoringSource.getText());
		settings.setSource("Team3TwoBarScoringSource",txtTeam3TwoBarScoringSource.getText());
		settings.setSource("Team1ShotsOnGoalSource",txtTeam1ShotsOnGoalSource.getText());
		settings.setSource("Team2ShotsOnGoalSource",txtTeam2ShotsOnGoalSource.getText());
		settings.setSource("Team3ShotsOnGoalSource",txtTeam3ShotsOnGoalSource.getText());
		try {
			settings.saveStatsSourceConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public void setLayout(Settings settings) {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton btnSelectPath = new JButton(Messages.getString("StatSourcesPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("StatSourcesPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(StatSourcesPanel.this);
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
						settings.saveStatsSourceConfig();
					} catch (IOException ex) {
						logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile")); //$NON-NLS-1$
						logger.error(ex.toString());
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0,alignx center"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveStatsSourceConfig();
		    	} catch (IOException ex) {
		    		logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
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
						settings.saveStatsSourceConfig();
			    	} catch (IOException ex) {
			    		logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
			    		logger.error(ex.toString());
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 3 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

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

		JLabel lblTeamPassAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.TeamPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamPassAttemptsSource, "cell 1 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassAttemptsSource = new JTextField();
		txtTeam1PassAttemptsSource.setText(settings.getPassAttemptsSource("1"));
		txtTeam1PassAttemptsSource.setColumns(15);
		add(txtTeam1PassAttemptsSource, "cell 2 3,alignx left"); //$NON-NLS-1$
		
		txtTeam2PassAttemptsSource = new JTextField();
		txtTeam2PassAttemptsSource.setText(settings.getPassAttemptsSource("2"));
		txtTeam2PassAttemptsSource.setColumns(15);
		add(txtTeam2PassAttemptsSource, "cell 3 3,alignx left"); //$NON-NLS-1$
		
		txtTeam3PassAttemptsSource = new JTextField();
		txtTeam3PassAttemptsSource.setText(settings.getPassAttemptsSource("3"));
		txtTeam3PassAttemptsSource.setColumns(15);
		add(txtTeam3PassAttemptsSource, "cell 4 3,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamPassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.TeamPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamPassCompletesSource, "cell 1 4,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassCompletesSource = new JTextField();
		txtTeam1PassCompletesSource.setText(settings.getPassCompletesSource("1"));
		txtTeam1PassCompletesSource.setColumns(15);
		add(txtTeam1PassCompletesSource, "cell 2 4,alignx left"); //$NON-NLS-1$
		
		txtTeam2PassCompletesSource = new JTextField();
		txtTeam2PassCompletesSource.setText(settings.getPassCompletesSource("2"));
		txtTeam2PassCompletesSource.setColumns(15);
		add(txtTeam2PassCompletesSource, "cell 3 4,alignx left"); //$NON-NLS-1$
		
		txtTeam3PassCompletesSource = new JTextField();
		txtTeam3PassCompletesSource.setText(settings.getPassCompletesSource("3"));
		txtTeam3PassCompletesSource.setColumns(15);
		add(txtTeam3PassCompletesSource, "cell 4 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamPassPercentSource = new JLabel(Messages.getString("StatSourcesPanel.TeamPassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamPassPercentSource, "cell 1 5,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassPercentSource = new JTextField();
		txtTeam1PassPercentSource.setText(settings.getPassPercentSource("1"));
		txtTeam1PassPercentSource.setColumns(15);
		add(txtTeam1PassPercentSource, "cell 2 5,alignx left"); //$NON-NLS-1$

		txtTeam2PassPercentSource = new JTextField();
		txtTeam2PassPercentSource.setText(settings.getPassPercentSource("2"));
		txtTeam2PassPercentSource.setColumns(15);
		add(txtTeam2PassPercentSource, "cell 3 5,alignx left"); //$NON-NLS-1$

		txtTeam3PassPercentSource = new JTextField();
		txtTeam3PassPercentSource.setText(settings.getPassPercentSource("3"));
		txtTeam3PassPercentSource.setColumns(15);
		add(txtTeam3PassPercentSource, "cell 4 5,alignx left"); //$NON-NLS-1$

		JLabel lblTeamShotAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.TeamShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamShotAttemptsSource, "cell 1 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotAttemptsSource = new JTextField();
		txtTeam1ShotAttemptsSource.setText(settings.getShotAttemptsSource("1"));
		txtTeam1ShotAttemptsSource.setColumns(15);
		add(txtTeam1ShotAttemptsSource, "cell 2 6,alignx left"); //$NON-NLS-1$
		
		txtTeam2ShotAttemptsSource = new JTextField();
		txtTeam2ShotAttemptsSource.setText(settings.getShotAttemptsSource("2"));
		txtTeam2ShotAttemptsSource.setColumns(15);
		add(txtTeam2ShotAttemptsSource, "cell 3 6,alignx left"); //$NON-NLS-1$
		
		txtTeam3ShotAttemptsSource = new JTextField();
		txtTeam3ShotAttemptsSource.setText(settings.getShotAttemptsSource("3"));
		txtTeam3ShotAttemptsSource.setColumns(15);
		add(txtTeam3ShotAttemptsSource, "cell 4 6,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamShotCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.TeamShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamShotCompletesSource, "cell 1 7,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotCompletesSource = new JTextField();
		txtTeam1ShotCompletesSource.setText(settings.getShotCompletesSource("1"));
		txtTeam1ShotCompletesSource.setColumns(15);
		add(txtTeam1ShotCompletesSource, "cell 2 7,alignx left"); //$NON-NLS-1$
		
		txtTeam2ShotCompletesSource = new JTextField();
		txtTeam2ShotCompletesSource.setText(settings.getShotCompletesSource("2"));
		txtTeam2ShotCompletesSource.setColumns(15);
		add(txtTeam2ShotCompletesSource, "cell 3 7,alignx left"); //$NON-NLS-1$
		
		txtTeam3ShotCompletesSource = new JTextField();
		txtTeam3ShotCompletesSource.setText(settings.getShotCompletesSource("3"));
		txtTeam3ShotCompletesSource.setColumns(15);
		add(txtTeam3ShotCompletesSource, "cell 4 7,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamShotPercentSource = new JLabel(Messages.getString("StatSourcesPanel.TeamShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamShotPercentSource, "cell 1 8,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotPercentSource = new JTextField();
		txtTeam1ShotPercentSource.setText(settings.getShotPercentSource("1"));
		txtTeam1ShotPercentSource.setColumns(15);
		add(txtTeam1ShotPercentSource, "cell 2 8,alignx left"); //$NON-NLS-1$
		
		txtTeam2ShotPercentSource = new JTextField();
		txtTeam2ShotPercentSource.setText(settings.getShotPercentSource("2"));
		txtTeam2ShotPercentSource.setColumns(15);
		add(txtTeam2ShotPercentSource, "cell 3 8,alignx left"); //$NON-NLS-1$

		txtTeam3ShotPercentSource = new JTextField();
		txtTeam3ShotPercentSource.setText(settings.getShotPercentSource("3"));
		txtTeam3ShotPercentSource.setColumns(15);
		add(txtTeam3ShotPercentSource, "cell 4 8,alignx left"); //$NON-NLS-1$

		JLabel lblTeamClearAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.TeamClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamClearAttemptsSource, "cell 1 9,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearAttemptsSource = new JTextField();
		txtTeam1ClearAttemptsSource.setText(settings.getClearAttemptsSource("1"));
		txtTeam1ClearAttemptsSource.setColumns(15);
		add(txtTeam1ClearAttemptsSource, "cell 2 9,alignx left"); //$NON-NLS-1$
		
		txtTeam2ClearAttemptsSource = new JTextField();
		txtTeam2ClearAttemptsSource.setText(settings.getClearAttemptsSource("2"));
		txtTeam2ClearAttemptsSource.setColumns(15);
		add(txtTeam2ClearAttemptsSource, "cell 3 9,alignx left"); //$NON-NLS-1$
		
		txtTeam3ClearAttemptsSource = new JTextField();
		txtTeam3ClearAttemptsSource.setText(settings.getClearAttemptsSource("3"));
		txtTeam3ClearAttemptsSource.setColumns(15);
		add(txtTeam3ClearAttemptsSource, "cell 4 9,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamClearCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.TeamClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamClearCompletesSource, "cell 1 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearCompletesSource = new JTextField();
		txtTeam1ClearCompletesSource.setText(settings.getClearCompletesSource("1"));
		txtTeam1ClearCompletesSource.setColumns(15);
		add(txtTeam1ClearCompletesSource, "cell 2 10,alignx left"); //$NON-NLS-1$
		
		txtTeam2ClearCompletesSource = new JTextField();
		txtTeam2ClearCompletesSource.setText(settings.getClearCompletesSource("2"));
		txtTeam2ClearCompletesSource.setColumns(15);
		add(txtTeam2ClearCompletesSource, "cell 3 10,alignx left"); //$NON-NLS-1$

		txtTeam3ClearCompletesSource = new JTextField();
		txtTeam3ClearCompletesSource.setText(settings.getClearCompletesSource("3"));
		txtTeam3ClearCompletesSource.setColumns(15);
		add(txtTeam3ClearCompletesSource, "cell 4 10,alignx left"); //$NON-NLS-1$

		JLabel lblTeamClearPercentSource = new JLabel(Messages.getString("StatSourcesPanel.TeamClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamClearPercentSource, "cell 1 11,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearPercentSource = new JTextField();
		txtTeam1ClearPercentSource.setText(settings.getClearPercentSource("1"));
		txtTeam1ClearPercentSource.setColumns(15);
		add(txtTeam1ClearPercentSource, "cell 2 11,alignx left"); //$NON-NLS-1$

		txtTeam2ClearPercentSource = new JTextField();
		txtTeam2ClearPercentSource.setText(settings.getClearPercentSource("2"));
		txtTeam2ClearPercentSource.setColumns(15);
		add(txtTeam2ClearPercentSource, "cell 3 11,alignx left"); //$NON-NLS-1$

		txtTeam3ClearPercentSource = new JTextField();
		txtTeam3ClearPercentSource.setText(settings.getClearPercentSource("3"));
		txtTeam3ClearPercentSource.setColumns(15);
		add(txtTeam3ClearPercentSource, "cell 4 11,alignx left"); //$NON-NLS-1$

		JLabel lblTeamTwoBarPassAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamTwoBarPassAttemptsSource, "cell 1 12,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassAttemptsSource = new JTextField();
		txtTeam1TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource("1"));
		txtTeam1TwoBarPassAttemptsSource.setColumns(15);
		add(txtTeam1TwoBarPassAttemptsSource, "cell 2 12,alignx left"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassAttemptsSource = new JTextField();
		txtTeam2TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource("2"));
		txtTeam2TwoBarPassAttemptsSource.setColumns(15);
		add(txtTeam2TwoBarPassAttemptsSource, "cell 3 12,alignx left"); //$NON-NLS-1$
		
		txtTeam3TwoBarPassAttemptsSource = new JTextField();
		txtTeam3TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource("3"));
		txtTeam3TwoBarPassAttemptsSource.setColumns(15);
		add(txtTeam3TwoBarPassAttemptsSource, "cell 4 12,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamTwoBarPassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamTwoBarPassCompletesSource, "cell 1 13,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassCompletesSource = new JTextField();
		txtTeam1TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource("1"));
		txtTeam1TwoBarPassCompletesSource.setColumns(15);
		add(txtTeam1TwoBarPassCompletesSource, "cell 2 13,alignx left"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassCompletesSource = new JTextField();
		txtTeam2TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource("2"));
		txtTeam2TwoBarPassCompletesSource.setColumns(15);
		add(txtTeam2TwoBarPassCompletesSource, "cell 3 13,alignx left"); //$NON-NLS-1$

		txtTeam3TwoBarPassCompletesSource = new JTextField();
		txtTeam3TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource("3"));
		txtTeam3TwoBarPassCompletesSource.setColumns(15);
		add(txtTeam3TwoBarPassCompletesSource, "cell 4 13,alignx left"); //$NON-NLS-1$

		JLabel lblTeamTwoBarPassPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamTwoBarPassPercentSource, "cell 1 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassPercentSource = new JTextField();
		txtTeam1TwoBarPassPercentSource.setText(settings.getTwoBarPassPercentSource("1"));
		txtTeam1TwoBarPassPercentSource.setColumns(15);
		add(txtTeam1TwoBarPassPercentSource, "cell 2 14,alignx left"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassPercentSource = new JTextField();
		txtTeam2TwoBarPassPercentSource.setText(settings.getTwoBarPassPercentSource("2"));
		txtTeam2TwoBarPassPercentSource.setColumns(15);
		add(txtTeam2TwoBarPassPercentSource, "cell 3 14,alignx left"); //$NON-NLS-1$

		txtTeam3TwoBarPassPercentSource = new JTextField();
		txtTeam3TwoBarPassPercentSource.setText(settings.getTwoBarPassPercentSource("3"));
		txtTeam3TwoBarPassPercentSource.setColumns(15);
		add(txtTeam3TwoBarPassPercentSource, "cell 4 14,alignx left"); //$NON-NLS-1$

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

		JLabel lblAcesSource = new JLabel(Messages.getString("StatSourcesPanel.Aces", settings.getGameType())); //$NON-NLS-1$
		add(lblAcesSource, "cell 5 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1AcesSource = new JTextField();
		txtTeam1AcesSource.setText(settings.getAcesSource("1"));
		txtTeam1AcesSource.setColumns(15);
		add(txtTeam1AcesSource, "cell 6 3,alignx left"); //$NON-NLS-1$
		
		txtTeam2AcesSource = new JTextField();
		txtTeam2AcesSource.setText(settings.getAcesSource("2"));
		txtTeam2AcesSource.setColumns(15);
		add(txtTeam2AcesSource, "cell 7 3,alignx left"); //$NON-NLS-1$
		
		txtTeam3AcesSource = new JTextField();
		txtTeam3AcesSource.setText(settings.getAcesSource("3"));
		txtTeam3AcesSource.setColumns(15);
		add(txtTeam3AcesSource, "cell 8 3,alignx left"); //$NON-NLS-1$
		
		JLabel lblStuffsSource = new JLabel(Messages.getString("StatSourcesPanel.Stuffs", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffsSource, "cell 5 4,alignx right"); //$NON-NLS-1$
		
		txtTeam1StuffsSource = new JTextField();
		txtTeam1StuffsSource.setText(settings.getStuffsSource("1"));
		txtTeam1StuffsSource.setColumns(15);
		add(txtTeam1StuffsSource, "cell 6 4,alignx left"); //$NON-NLS-1$

		txtTeam2StuffsSource = new JTextField();
		txtTeam2StuffsSource.setText(settings.getStuffsSource("2"));
		txtTeam2StuffsSource.setColumns(15);
		add(txtTeam2StuffsSource, "cell 7 4,alignx left"); //$NON-NLS-1$
		
		txtTeam3StuffsSource = new JTextField();
		txtTeam3StuffsSource.setText(settings.getStuffsSource("3"));
		txtTeam3StuffsSource.setColumns(15);
		add(txtTeam3StuffsSource, "cell 8 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblBreaksSource = new JLabel(Messages.getString("StatSourcesPanel.Breaks", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaksSource, "cell 5 5,alignx right"); //$NON-NLS-1$
		
		txtTeam1BreaksSource = new JTextField();
		txtTeam1BreaksSource.setText(settings.getBreaksSource("1"));
		txtTeam1BreaksSource.setColumns(15);
		add(txtTeam1BreaksSource, "cell 6 5,alignx left"); //$NON-NLS-1$
		
		txtTeam2BreaksSource = new JTextField();
		txtTeam2BreaksSource.setText(settings.getBreaksSource("2"));
		txtTeam2BreaksSource.setColumns(15);
		add(txtTeam2BreaksSource, "cell 7 5,alignx left"); //$NON-NLS-1$
		
		txtTeam3BreaksSource = new JTextField();
		txtTeam3BreaksSource.setText(settings.getBreaksSource("3"));
		txtTeam3BreaksSource.setColumns(15);
		add(txtTeam3BreaksSource, "cell 8 5,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamScoringSource = new JLabel(Messages.getString("StatSourcesPanel.TeamScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamScoringSource, "cell 5 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ScoringSource = new JTextField();
		txtTeam1ScoringSource.setText(settings.getScoringSource("1"));
		txtTeam1ScoringSource.setColumns(15);
		add(txtTeam1ScoringSource, "cell 6 6,alignx left"); //$NON-NLS-1$

		txtTeam2ScoringSource = new JTextField();
		txtTeam2ScoringSource.setText(settings.getScoringSource("2"));
		txtTeam2ScoringSource.setColumns(15);
		add(txtTeam2ScoringSource, "cell 7 6,alignx left"); //$NON-NLS-1$

		txtTeam3ScoringSource = new JTextField();
		txtTeam3ScoringSource.setText(settings.getScoringSource("3"));
		txtTeam3ScoringSource.setColumns(15);
		add(txtTeam3ScoringSource, "cell 8 6,alignx left"); //$NON-NLS-1$

		JLabel lblTeamThreeBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team3BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamThreeBarScoringSource, "cell 5 7,alignx right"); //$NON-NLS-1$
		
		txtTeam1ThreeBarScoringSource = new JTextField();
		txtTeam1ThreeBarScoringSource.setText(settings.getThreeBarScoringSource("1"));
		txtTeam1ThreeBarScoringSource.setColumns(15);
		add(txtTeam1ThreeBarScoringSource, "cell 6 7,alignx left"); //$NON-NLS-1$

		txtTeam2ThreeBarScoringSource = new JTextField();
		txtTeam2ThreeBarScoringSource.setText(settings.getThreeBarScoringSource("2"));
		txtTeam2ThreeBarScoringSource.setColumns(15);
		add(txtTeam2ThreeBarScoringSource, "cell 7 7,alignx left"); //$NON-NLS-1$

		txtTeam3ThreeBarScoringSource = new JTextField();
		txtTeam3ThreeBarScoringSource.setText(settings.getThreeBarScoringSource("3"));
		txtTeam3ThreeBarScoringSource.setColumns(15);
		add(txtTeam3ThreeBarScoringSource, "cell 8 7,alignx left"); //$NON-NLS-1$

		JLabel lblTeam5BarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team5BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam5BarScoring, "cell 5 8,alignx right"); //$NON-NLS-1$
		
		txtTeam1FiveBarScoringSource = new JTextField();
		txtTeam1FiveBarScoringSource.setText(settings.getFiveBarScoringSource("1"));
		txtTeam1FiveBarScoringSource.setColumns(15);
		add(txtTeam1FiveBarScoringSource, "cell 6 8,alignx left"); //$NON-NLS-1$

		txtTeam2FiveBarScoringSource = new JTextField();
		txtTeam2FiveBarScoringSource.setText(settings.getFiveBarScoringSource("2"));
		txtTeam2FiveBarScoringSource.setColumns(15);
		add(txtTeam2FiveBarScoringSource, "cell 7 8,alignx left"); //$NON-NLS-1$
		
		txtTeam3FiveBarScoringSource = new JTextField();
		txtTeam3FiveBarScoringSource.setText(settings.getFiveBarScoringSource("3"));
		txtTeam3FiveBarScoringSource.setColumns(15);
		add(txtTeam3FiveBarScoringSource, "cell 8 8,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamTwoBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team2BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamTwoBarScoringSource, "cell 5 9,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarScoringSource = new JTextField();
		txtTeam1TwoBarScoringSource.setText(settings.getTwoBarScoringSource("1"));
		txtTeam1TwoBarScoringSource.setColumns(15);
		add(txtTeam1TwoBarScoringSource, "cell 6 9,alignx left"); //$NON-NLS-1$
		
		txtTeam2TwoBarScoringSource = new JTextField();
		txtTeam2TwoBarScoringSource.setText(settings.getTwoBarScoringSource("2"));
		txtTeam2TwoBarScoringSource.setColumns(15);
		add(txtTeam2TwoBarScoringSource, "cell 7 9,alignx left"); //$NON-NLS-1$
		
		txtTeam3TwoBarScoringSource = new JTextField();
		txtTeam3TwoBarScoringSource.setText(settings.getTwoBarScoringSource("3"));
		txtTeam3TwoBarScoringSource.setColumns(15);
		add(txtTeam3TwoBarScoringSource, "cell 8 9,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeamSOG = new JLabel(Messages.getString("StatSourcesPanel.TeamShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeamSOG, "cell 5 10,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1ShotsOnGoalSource = new JTextField();
		txtTeam1ShotsOnGoalSource.setText(settings.getShotsOnGoalSource("1"));
		txtTeam1ShotsOnGoalSource.setColumns(15);
		add(txtTeam1ShotsOnGoalSource, "cell 6 10,alignx left"); //$NON-NLS-1$
		
		txtTeam2ShotsOnGoalSource = new JTextField();
		txtTeam2ShotsOnGoalSource.setText(settings.getShotsOnGoalSource("2"));
		txtTeam2ShotsOnGoalSource.setColumns(15);
		add(txtTeam2ShotsOnGoalSource, "cell 7 10,alignx left"); //$NON-NLS-1$
		
		txtTeam3ShotsOnGoalSource = new JTextField();
		txtTeam3ShotsOnGoalSource.setText(settings.getShotsOnGoalSource("3"));
		txtTeam3ShotsOnGoalSource.setColumns(15);
		add(txtTeam3ShotsOnGoalSource, "cell 8 10,alignx left"); //$NON-NLS-1$
		
		JButton btnSaveSources = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveSources, "cell 2 15,alignx left"); //$NON-NLS-1$
		
		JButton btnCancelSources = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelSources, "cell 3 15,alignx left"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 4 15,alignx left"); //$NON-NLS-1$
	}
}
