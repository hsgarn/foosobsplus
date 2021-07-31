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

public class FileNamesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTournamentFileName;
	private JTextField txtEventFileName;
	private JTextField txtTeam1NameFileName;
	private JTextField txtTeam1ForwardFileName;
	private JTextField txtTeam1GoalieFileName;
	private JTextField txtTeam2NameFileName;
	private JTextField txtTeam2ForwardFileName;
	private JTextField txtTeam2GoalieFileName;
	private JTextField txtGameCount1FileName;
	private JTextField txtGameCount2FileName;
	private JTextField txtScore1FileName;
	private JTextField txtScore2FileName;
	private JTextField txtTimeOut1FileName;
	private JTextField txtTimeOut2FileName;
	private JTextField txtReset1FileName;
	private JTextField txtReset2FileName;
	private JTextField txtWarn1FileName;
	private JTextField txtWarn2FileName;
	private JTextField txtTimeRemainingFileName;
	private JTextField txtTimerInUseFileName;
	private JTextField txtMatchWinnerFileName;
	private JTextField txtMeatballFileName;
	private JTextField txtLastScoredFileName;
	private JTextField txtGameTimeFileName;
	private JTextField txtMatchTimeFileName;
	private JTextField txtStuffs1FileName;
	private JTextField txtStuffs2FileName;
	private JTextField txtBreaks1FileName;
	private JTextField txtBreaks2FileName;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtTeam1PassAttemptsFileName;
	private JTextField txtTeam1PassCompletesFileName;
	private JTextField txtTeam2PassAttemptsFileName;
	private JTextField txtTeam2PassCompletesFileName;
	private JTextField txtTeam1ShotAttemptsFileName;
	private JTextField txtTeam1ShotCompletesFileName;
	private JTextField txtTeam2ShotAttemptsFileName;
	private JTextField txtTeam2ShotCompletesFileName;
	private JTextField txtTeam1ClearAttemptsFileName;
	private JTextField txtTeam1ClearCompletesFileName;
	private JTextField txtTeam2ClearAttemptsFileName;
	private JTextField txtTeam2ClearCompletesFileName;
	private JTextField txtTeam1PassPercentFileName;
	private JTextField txtTeam2PassPercentFileName;
	private JTextField txtTeam1ShotPercentFileName;
	private JTextField txtTeam2ShotPercentFileName;
	private JTextField txtTeam1ClearPercentFileName;
	private JTextField txtTeam2ClearPercentFileName;
	private JTextField txtTeam1TwoBarPassAttemptsFileName;
	private JTextField txtTeam1TwoBarPassCompletesFileName;
	private JTextField txtTeam2TwoBarPassAttemptsFileName;
	private JTextField txtTeam2TwoBarPassCompletesFileName;
	private JTextField txtTeam1ScoringFileName;
	private JTextField txtTeam2ScoringFileName;
	private JTextField txtTeam1ThreeBarScoringFileName;
	private JTextField txtTeam2ThreeBarScoringFileName;
	private JTextField txtTeam1FiveBarScoringFileName;
	private JTextField txtTeam2FiveBarScoringFileName;
	private JTextField txtTeam1TwoBarScoringFileName;
	private JTextField txtTeam2TwoBarScoringFileName;
	private JTextField txtTeam1ShotsOnGoalFileName;
	private JTextField txtTeam2ShotsOnGoalFileName;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$

	// Create the Panel.

	public FileNamesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);

	}

	private void restoreDefaults(Settings settings) {
		txtTeam1NameFileName.setText(settings.getDefaultTeam1NameFileName());
		txtTeam1ForwardFileName.setText(settings.getDefaultTeam1ForwardFileName());
		txtTeam1GoalieFileName.setText(settings.getDefaultTeam1GoalieFileName());
		txtTournamentFileName.setText(settings.getDefaultTournamentFileName());
		txtTeam2NameFileName.setText(settings.getDefaultTeam2NameFileName());
		txtTeam2ForwardFileName.setText(settings.getDefaultTeam2ForwardFileName());
		txtTeam2GoalieFileName.setText(settings.getDefaultTeam2GoalieFileName());
		txtEventFileName.setText(settings.getDefaultEventFileName());
		txtGameCount1FileName.setText(settings.getDefaultGameCount1FileName());
		txtTimeRemainingFileName.setText(settings.getDefaultTimeRemainingFileName());
		txtGameCount2FileName.setText(settings.getDefaultGameCount2FileName());
		txtTimerInUseFileName.setText(settings.getDefaultTimerInUseFileName());
		txtScore1FileName.setText(settings.getDefaultScore1FileName());
		txtMatchWinnerFileName.setText(settings.getDefaultMatchWinnerFileName());
		txtMeatballFileName.setText(settings.getDefaultMeatballFileName());
		txtScore2FileName.setText(settings.getDefaultScore2FileName());
		txtTimeOut1FileName.setText(settings.getDefaultTimeOut1FileName());
		txtTimeOut2FileName.setText(settings.getDefaultTimeOut2FileName());
		txtReset1FileName.setText(settings.getDefaultReset1FileName());
		txtReset2FileName.setText(settings.getDefaultReset2FileName());
		txtWarn1FileName.setText(settings.getDefaultWarn1FileName());
		txtWarn2FileName.setText(settings.getDefaultWarn2FileName());
		txtLastScoredFileName.setText(settings.getDefaultLastScoredFileName());
		txtGameTimeFileName.setText(settings.getDefaultGameTimeFileName());
		txtMatchTimeFileName.setText(settings.getDefaultMatchTimeFileName());
		txtStuffs1FileName.setText(settings.getDefaultStuffs1FileName());
		txtStuffs2FileName.setText(settings.getDefaultStuffs2FileName());
		txtBreaks1FileName.setText(settings.getDefaultBreaks1FileName());
		txtBreaks2FileName.setText(settings.getDefaultBreaks2FileName());
		txtTeam1PassAttemptsFileName.setText(settings.getDefaultTeam1PassAttemptsFileName());
		txtTeam2PassAttemptsFileName.setText(settings.getDefaultTeam2PassAttemptsFileName());
		txtTeam1ShotAttemptsFileName.setText(settings.getDefaultTeam1ShotAttemptsFileName());
		txtTeam2ShotAttemptsFileName.setText(settings.getDefaultTeam2ShotAttemptsFileName());
		txtTeam1ClearAttemptsFileName.setText(settings.getDefaultTeam1ClearAttemptsFileName());
		txtTeam2ClearAttemptsFileName.setText(settings.getDefaultTeam2ClearAttemptsFileName());
		txtTeam1PassCompletesFileName.setText(settings.getDefaultTeam1PassCompletesFileName());
		txtTeam2PassCompletesFileName.setText(settings.getDefaultTeam2PassCompletesFileName());
		txtTeam1ShotCompletesFileName.setText(settings.getDefaultTeam1ShotCompletesFileName());
		txtTeam2ShotCompletesFileName.setText(settings.getDefaultTeam2ShotCompletesFileName());
		txtTeam1ClearCompletesFileName.setText(settings.getDefaultTeam1ClearCompletesFileName());
		txtTeam2ClearCompletesFileName.setText(settings.getDefaultTeam2ClearCompletesFileName());
		txtTeam1PassPercentFileName.setText(settings.getDefaultTeam1PassPercentFileName());
		txtTeam2PassPercentFileName.setText(settings.getDefaultTeam2PassPercentFileName());
		txtTeam1ShotPercentFileName.setText(settings.getDefaultTeam1ShotPercentFileName());
		txtTeam2ShotPercentFileName.setText(settings.getDefaultTeam2ShotPercentFileName());
		txtTeam1ClearPercentFileName.setText(settings.getDefaultTeam1ClearPercentFileName());
		txtTeam2ClearPercentFileName.setText(settings.getDefaultTeam2ClearPercentFileName());
		txtTeam1ScoringFileName.setText(settings.getDefaultTeam1ScoringFileName());
		txtTeam2ScoringFileName.setText(settings.getDefaultTeam2ScoringFileName());
		txtTeam1ThreeBarScoringFileName.setText(settings.getDefaultTeam1ThreeBarScoringFileName());
		txtTeam2ThreeBarScoringFileName.setText(settings.getDefaultTeam2ThreeBarScoringFileName());
		txtTeam1FiveBarScoringFileName.setText(settings.getDefaultTeam1FiveBarScoringFileName());
		txtTeam2FiveBarScoringFileName.setText(settings.getDefaultTeam2FiveBarScoringFileName());
		txtTeam1TwoBarScoringFileName.setText(settings.getDefaultTeam1TwoBarScoringFileName());
		txtTeam2TwoBarScoringFileName.setText(settings.getDefaultTeam2TwoBarScoringFileName());
		txtTeam1ShotsOnGoalFileName.setText(settings.getDefaultTeam1ShotsOnGoalFileName());
		txtTeam2ShotsOnGoalFileName.setText(settings.getDefaultTeam2ShotsOnGoalFileName());
	}

	private void saveSettings(Settings settings) {
		settings.setTeam1NameFileName(txtTeam1NameFileName.getText());
		settings.setTeam1ForwardFileName(txtTeam1ForwardFileName.getText());
		settings.setTeam1GoalieFileName(txtTeam1GoalieFileName.getText());
		settings.setTournamentFileName(txtTournamentFileName.getText());
		settings.setTeam2NameFileName(txtTeam2NameFileName.getText());
		settings.setTeam2ForwardFileName(txtTeam2ForwardFileName.getText());
		settings.setTeam2GoalieFileName(txtTeam2GoalieFileName.getText());
		settings.setEventFileName(txtEventFileName.getText());
		settings.setGameCount1FileName(txtGameCount1FileName.getText());
		settings.setTimeRemainingFileName(txtTimeRemainingFileName.getText());
		settings.setGameCount2FileName(txtGameCount2FileName.getText());
		settings.setTimerInUseFileName(txtTimerInUseFileName.getText());
		settings.setScore1FileName(txtScore1FileName.getText());
		settings.setMatchWinnerFileName(txtMatchWinnerFileName.getText());
		settings.setMeatballFileName(txtMeatballFileName.getText());
		settings.setScore2FileName(txtScore2FileName.getText());
		settings.setTimeOut1FileName(txtTimeOut1FileName.getText());
		settings.setTimeOut2FileName(txtTimeOut2FileName.getText());
		settings.setReset1FileName(txtReset1FileName.getText());
		settings.setReset2FileName(txtReset2FileName.getText());
		settings.setWarn1FileName(txtWarn1FileName.getText());
		settings.setWarn2FileName(txtWarn2FileName.getText());
		settings.setLastScoredFileName(txtLastScoredFileName.getText());
		settings.setGameTimeFileName(txtGameTimeFileName.getText());
		settings.setMatchTimeFileName(txtMatchTimeFileName.getText());
		settings.setStuffs1FileName(txtStuffs1FileName.getText());
		settings.setStuffs2FileName(txtStuffs2FileName.getText());
		settings.setBreaks1FileName(txtBreaks1FileName.getText());
		settings.setBreaks2FileName(txtBreaks2FileName.getText());
		settings.setTeam1PassAttemptsFileName(txtTeam1PassAttemptsFileName.getText());
		settings.setTeam2PassAttemptsFileName(txtTeam2PassAttemptsFileName.getText());
		settings.setTeam1ShotAttemptsFileName(txtTeam1ShotAttemptsFileName.getText());
		settings.setTeam2ShotAttemptsFileName(txtTeam2ShotAttemptsFileName.getText());
		settings.setTeam1ClearAttemptsFileName(txtTeam1ClearAttemptsFileName.getText());
		settings.setTeam2ClearAttemptsFileName(txtTeam2ClearAttemptsFileName.getText());
		settings.setTeam1PassCompletesFileName(txtTeam1PassCompletesFileName.getText());
		settings.setTeam2PassCompletesFileName(txtTeam2PassCompletesFileName.getText());
		settings.setTeam1ShotCompletesFileName(txtTeam1ShotCompletesFileName.getText());
		settings.setTeam2ShotCompletesFileName(txtTeam2ShotCompletesFileName.getText());
		settings.setTeam1ClearCompletesFileName(txtTeam1ClearCompletesFileName.getText());
		settings.setTeam2ClearCompletesFileName(txtTeam2ClearCompletesFileName.getText());
		settings.setTeam1PassPercentFileName(txtTeam1PassPercentFileName.getText());
		settings.setTeam2PassPercentFileName(txtTeam2PassPercentFileName.getText());
		settings.setTeam1ShotPercentFileName(txtTeam1ShotPercentFileName.getText());
		settings.setTeam2ShotPercentFileName(txtTeam2ShotPercentFileName.getText());
		settings.setTeam1ClearPercentFileName(txtTeam1ClearPercentFileName.getText());
		settings.setTeam2ClearPercentFileName(txtTeam2ClearPercentFileName.getText());
		settings.setTeam1ScoringFileName(txtTeam1ScoringFileName.getText());
		settings.setTeam2ScoringFileName(txtTeam2ScoringFileName.getText());
		settings.setTeam1ThreeBarScoringFileName(txtTeam1ThreeBarScoringFileName.getText());
		settings.setTeam2ThreeBarScoringFileName(txtTeam2ThreeBarScoringFileName.getText());
		settings.setTeam1FiveBarScoringFileName(txtTeam1FiveBarScoringFileName.getText());
		settings.setTeam2FiveBarScoringFileName(txtTeam2FiveBarScoringFileName.getText());
		settings.setTeam1TwoBarScoringFileName(txtTeam1TwoBarScoringFileName.getText());
		settings.setTeam2TwoBarScoringFileName(txtTeam2TwoBarScoringFileName.getText());
		settings.setTeam1ShotsOnGoalFileName(txtTeam1ShotsOnGoalFileName.getText());
		settings.setTeam2ShotsOnGoalFileName(txtTeam2ShotsOnGoalFileName.getText());
		try {
			settings.saveToFileNameConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
		}
	}
	public void setLayout(Settings settings) {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton btnSelectPath = new JButton(Messages.getString("FileNamesPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("FileNamesPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(FileNamesPanel.this);
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
						settings.saveToFileNameConfig();
					} catch (IOException ex) {
						System.out.print(Messages.getString("FileNamesPanel.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
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
					settings.saveToFileNameConfig();
		    	} catch (IOException ex) {
		    		System.out.print(Messages.getString("FileNamesPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
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
						settings.saveToFileNameConfig();
			    	} catch (IOException ex) {
			    		System.out.print(Messages.getString("FileNamesPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

		JLabel lblFileName = new JLabel(Messages.getString("FileNamesPanel.FileName")); //$NON-NLS-1$
		add(lblFileName, "cell 2 1,alignx left"); //$NON-NLS-1$

		JLabel lblFileNameCol2 = new JLabel(Messages.getString("FileNamesPanel.FileName")); //$NON-NLS-1$
		add(lblFileNameCol2, "cell 5 1,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblFileNameCol3 = new JLabel(Messages.getString("FileNamesPanel.FileName")); //$NON-NLS-1$
		add(lblFileNameCol3, "cell 8 1,alignx left"); //$NON-NLS-1$
		
		JLabel lblFileNameCol4 = new JLabel(Messages.getString("FileNamesPanel.FileName")); //$NON-NLS-1$
		add(lblFileNameCol4, "cell 11 1"); //$NON-NLS-1$

		JLabel lblTeam1NameFileName = new JLabel(Messages.getString("FileNamesPanel.Team1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1NameFileName, "cell 1 2,alignx right"); //$NON-NLS-1$

		txtTeam1NameFileName = new JTextField();
		txtTeam1NameFileName.setText(settings.getTeamNameFileName(1));
		add(txtTeam1NameFileName, "cell 2 2,alignx left"); //$NON-NLS-1$
		txtTeam1NameFileName.setColumns(10);

		JLabel lblTeam1Forward = new JLabel(Messages.getString("FileNamesPanel.Team1Forward", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 4 2,alignx right"); //$NON-NLS-1$

		txtTeam1ForwardFileName = new JTextField();
		txtTeam1ForwardFileName.setText(settings.getTeamForwardFileName(1));
		add(txtTeam1ForwardFileName, "cell 5 2,alignx left"); //$NON-NLS-1$
		txtTeam1ForwardFileName.setColumns(10);
		
		JLabel lblTeam1PassAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team1PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassAttemptsFileName, "cell 7 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassAttemptsFileName = new JTextField();
		txtTeam1PassAttemptsFileName.setText(settings.getPassAttemptsFileName(1));
		add(txtTeam1PassAttemptsFileName, "cell 8 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1PassPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team1PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassPercentFileName, "cell 10 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassPercentFileName = new JTextField();
		txtTeam1PassPercentFileName.setText(settings.getPassPercentFileName(1));
		add(txtTeam1PassPercentFileName, "cell 11 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassPercentFileName.setColumns(20);

		JLabel lblTeam2NameFileName = new JLabel(Messages.getString("FileNamesPanel.Team2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2NameFileName, "cell 1 3,alignx right"); //$NON-NLS-1$

		txtTeam2NameFileName = new JTextField();
		txtTeam2NameFileName.setText(settings.getTeamNameFileName(2));
		add(txtTeam2NameFileName, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2NameFileName.setColumns(10);

		JLabel lblTeam1Goalie = new JLabel(Messages.getString("FileNamesPanel.Team1Goalie", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Goalie, "cell 4 3,alignx right"); //$NON-NLS-1$

		txtTeam1GoalieFileName = new JTextField();
		txtTeam1GoalieFileName.setText(settings.getTeamGoalieFileName(1));
		add(txtTeam1GoalieFileName, "cell 5 3,alignx left"); //$NON-NLS-1$
		txtTeam1GoalieFileName.setColumns(10);
		
		JLabel lblTeam1PassCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team1PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassCompletesFileName, "cell 7 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassCompletesFileName = new JTextField();
		txtTeam1PassCompletesFileName.setText(settings.getPassCompletesFileName(1));
		add(txtTeam1PassCompletesFileName, "cell 8 3,alignx left"); //$NON-NLS-1$
		txtTeam1PassCompletesFileName.setColumns(20);
		
		JLabel lblTeam2PassPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team2PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassPercentFileName, "cell 10 3,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassPercentFileName = new JTextField();
		txtTeam2PassPercentFileName.setText(settings.getPassPercentFileName(2));
		add(txtTeam2PassPercentFileName, "cell 11 3,alignx left,aligny top"); //$NON-NLS-1$
		txtTeam2PassPercentFileName.setColumns(20);

		JLabel lblGameCountFileName = new JLabel(Messages.getString("FileNamesPanel.Team1GameCount", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCountFileName, "cell 1 4,alignx right"); //$NON-NLS-1$

		txtGameCount1FileName = new JTextField();
		txtGameCount1FileName.setText(settings.getGameCountFileName(1));
		add(txtGameCount1FileName, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtGameCount1FileName.setColumns(10);

		JLabel lblTeam2Forward = new JLabel(Messages.getString("FileNamesPanel.Team2Forward", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Forward, "cell 4 4,alignx right"); //$NON-NLS-1$

		txtTeam2ForwardFileName = new JTextField();
		txtTeam2ForwardFileName.setText(settings.getTeamForwardFileName(2));
		add(txtTeam2ForwardFileName, "cell 5 4,alignx left"); //$NON-NLS-1$
		txtTeam2ForwardFileName.setColumns(10);

		JLabel lblTeam2PassAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team2PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassAttemptsFileName, "cell 7 4,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassAttemptsFileName = new JTextField();
		txtTeam2PassAttemptsFileName.setText(settings.getPassAttemptsFileName(2));
		add(txtTeam2PassAttemptsFileName, "cell 8 4,alignx left"); //$NON-NLS-1$
		txtTeam2PassAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ShotPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotPercentFileName, "cell 10 4,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotPercentFileName = new JTextField();
		txtTeam1ShotPercentFileName.setText(settings.getShotPercentFileName(1));
		add(txtTeam1ShotPercentFileName, "cell 11 4,alignx left"); //$NON-NLS-1$
		txtTeam1ShotPercentFileName.setColumns(20);
		
		JLabel lblGameCount2FileName = new JLabel(Messages.getString("FileNamesPanel.GameCount2", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount2FileName, "cell 1 5,alignx right"); //$NON-NLS-1$

		txtGameCount2FileName = new JTextField();
		txtGameCount2FileName.setText(settings.getGameCountFileName(2));
		add(txtGameCount2FileName, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtGameCount2FileName.setColumns(10);

		JLabel lblTeam2Name2 = new JLabel(Messages.getString("FileNamesPanel.Team2Goalie", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Name2, "cell 4 5,alignx right"); //$NON-NLS-1$

		txtTeam2GoalieFileName = new JTextField();
		txtTeam2GoalieFileName.setText(settings.getTeamGoalieFileName(2));
		add(txtTeam2GoalieFileName, "cell 5 5,alignx left"); //$NON-NLS-1$
		txtTeam2GoalieFileName.setColumns(10);

		JLabel lblTeam2PassCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team2PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassCompletesFileName, "cell 7 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassCompletesFileName = new JTextField();
		txtTeam2PassCompletesFileName.setText(settings.getPassCompletesFileName(2));
		add(txtTeam2PassCompletesFileName, "cell 8 5,alignx left"); //$NON-NLS-1$
		txtTeam2PassCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ShotPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotPercentFileName, "cell 10 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotPercentFileName = new JTextField();
		txtTeam2ShotPercentFileName.setText(settings.getShotPercentFileName(2));
		add(txtTeam2ShotPercentFileName, "cell 11 5,growx"); //$NON-NLS-1$
		txtTeam2ShotPercentFileName.setColumns(20);

		JLabel lblScore1FileName = new JLabel(Messages.getString("FileNamesPanel.Score1", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1FileName, "cell 1 6,alignx right"); //$NON-NLS-1$

		txtScore1FileName = new JTextField();
		txtScore1FileName.setText(settings.getScoreFileName(1));
		add(txtScore1FileName, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtScore1FileName.setColumns(10);

		JLabel lblTournamentFileName = new JLabel(Messages.getString("FileNamesPanel.Tournament", settings.getGameType())); //$NON-NLS-1$
		add(lblTournamentFileName, "cell 4 6,alignx right"); //$NON-NLS-1$

		txtTournamentFileName = new JTextField();
		txtTournamentFileName.setText(settings.getTournamentFileName());
		add(txtTournamentFileName, "cell 5 6,alignx left"); //$NON-NLS-1$
		txtTournamentFileName.setColumns(10);

		JLabel lblTeam1ShotAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotAttemptsFileName, "cell 7 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotAttemptsFileName = new JTextField();
		txtTeam1ShotAttemptsFileName.setText(settings.getShotAttemptsFileName(1));
		add(txtTeam1ShotAttemptsFileName, "cell 8 6,alignx left"); //$NON-NLS-1$
		txtTeam1ShotAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ClearPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearPercentFileName, "cell 10 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearPercentFileName = new JTextField();
		txtTeam1ClearPercentFileName.setText(settings.getClearPercentFileName(1));
		add(txtTeam1ClearPercentFileName, "cell 11 6,alignx left"); //$NON-NLS-1$
		txtTeam1ClearPercentFileName.setColumns(20);

		JLabel lblScore2FileName = new JLabel(Messages.getString("FileNamesPanel.Score2", settings.getGameType())); //$NON-NLS-1$
		add(lblScore2FileName, "cell 1 7,alignx right"); //$NON-NLS-1$

		txtScore2FileName = new JTextField();
		txtScore2FileName.setText(settings.getScoreFileName(2));
		add(txtScore2FileName, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtScore2FileName.setColumns(10);

		JLabel lblEventFileName = new JLabel(Messages.getString("FileNamesPanel.Event", settings.getGameType())); //$NON-NLS-1$
		add(lblEventFileName, "cell 4 7,alignx right"); //$NON-NLS-1$

		txtEventFileName = new JTextField();
		txtEventFileName.setText(settings.getEventFileName());
		add(txtEventFileName, "cell 5 7,alignx left"); //$NON-NLS-1$
		txtEventFileName.setColumns(10);

		JLabel lblTeam1ShotCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotCompletesFileName, "cell 7 7,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotCompletesFileName = new JTextField();
		txtTeam1ShotCompletesFileName.setText(settings.getShotCompletesFileName(1));
		add(txtTeam1ShotCompletesFileName, "cell 8 7,alignx left"); //$NON-NLS-1$
		txtTeam1ShotCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ClearPercentFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearPercentFileName, "cell 10 7,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearPercentFileName = new JTextField();
		txtTeam2ClearPercentFileName.setText(settings.getClearPercentFileName(2));
		add(txtTeam2ClearPercentFileName, "cell 11 7,alignx left"); //$NON-NLS-1$
		txtTeam2ClearPercentFileName.setColumns(20);

		JLabel lblTimeOut1FileName = new JLabel(Messages.getString("FileNamesPanel.TimeOut1", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1FileName, "cell 1 8,alignx right"); //$NON-NLS-1$

		txtTimeOut1FileName = new JTextField();
		txtTimeOut1FileName.setText(settings.getTimeOutFileName(1));
		add(txtTimeOut1FileName, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTimeOut1FileName.setColumns(10);

		JLabel lblTimeRemainingFileName = new JLabel(Messages.getString("FileNamesPanel.TimeRemaining", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeRemainingFileName, "cell 4 8,alignx right"); //$NON-NLS-1$

		txtTimeRemainingFileName = new JTextField();
		txtTimeRemainingFileName.setText(settings.getTimeRemainingFileName());
		add(txtTimeRemainingFileName, "cell 5 8,alignx left"); //$NON-NLS-1$
		txtTimeRemainingFileName.setColumns(10);

		JLabel lblTeam2ShotAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotAttemptsFileName, "cell 7 8,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotAttemptsFileName = new JTextField();
		txtTeam2ShotAttemptsFileName.setText(settings.getShotAttemptsFileName(2));
		add(txtTeam2ShotAttemptsFileName, "cell 8 8,alignx left"); //$NON-NLS-1$
		txtTeam2ShotAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team1Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ScoringFileName, "cell 10 8,alignx right"); //$NON-NLS-1$
		
		txtTeam1ScoringFileName = new JTextField();
		txtTeam1ScoringFileName.setText(settings.getScoringFileName(1));
		add(txtTeam1ScoringFileName, "cell 11 8,alignx left"); //$NON-NLS-1$
		txtTeam1ScoringFileName.setColumns(10);

		JLabel lblTimeOut2FileName = new JLabel(Messages.getString("FileNamesPanel.TimeOut2", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut2FileName, "cell 1 9,alignx right"); //$NON-NLS-1$

		txtTimeOut2FileName = new JTextField();
		txtTimeOut2FileName.setText(settings.getTimeOutFileName(2));
		add(txtTimeOut2FileName, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTimeOut2FileName.setColumns(10);

		JLabel lblTimerFileName = new JLabel(Messages.getString("FileNamesPanel.Timer", settings.getGameType())); //$NON-NLS-1$
		add(lblTimerFileName, "cell 4 9,alignx right"); //$NON-NLS-1$

		txtTimerInUseFileName = new JTextField();
		txtTimerInUseFileName.setText(settings.getTimerInUseFileName());
		add(txtTimerInUseFileName, "cell 5 9,alignx left"); //$NON-NLS-1$
		txtTimerInUseFileName.setColumns(10);

		JLabel lblTeam2ShotCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotCompletesFileName, "cell 7 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotCompletesFileName = new JTextField();
		txtTeam2ShotCompletesFileName.setText(settings.getShotCompletesFileName(2));
		add(txtTeam2ShotCompletesFileName, "cell 8 9,alignx left"); //$NON-NLS-1$
		txtTeam2ShotCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team2Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ScoringFileName, "cell 10 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ScoringFileName = new JTextField();
		txtTeam2ScoringFileName.setText(settings.getScoringFileName(2));
		add(txtTeam2ScoringFileName, "cell 11 9,alignx left"); //$NON-NLS-1$
		txtTeam2ScoringFileName.setColumns(10);

		JLabel lblReset1FileName = new JLabel(Messages.getString("FileNamesPanel.Reset1", settings.getGameType())); //$NON-NLS-1$
		add(lblReset1FileName, "cell 1 10,alignx right"); //$NON-NLS-1$

		txtReset1FileName = new JTextField();
		txtReset1FileName.setText(settings.getResetFileName(1));
		add(txtReset1FileName, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtReset1FileName.setColumns(10);

		JLabel lblMatchWinnerFileName = new JLabel(Messages.getString("FileNamesPanel.MatchWinner", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchWinnerFileName, "cell 4 10,alignx right"); //$NON-NLS-1$

		txtMatchWinnerFileName = new JTextField();
		txtMatchWinnerFileName.setText(settings.getMatchWinnerFileName());
		add(txtMatchWinnerFileName, "cell 5 10,alignx left"); //$NON-NLS-1$
		txtMatchWinnerFileName.setColumns(10);

		JLabel lblTeam1ClearAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearAttemptsFileName, "cell 7 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearAttemptsFileName = new JTextField();
		txtTeam1ClearAttemptsFileName.setText(settings.getClearAttemptsFileName(1));
		add(txtTeam1ClearAttemptsFileName, "cell 8 10,alignx left"); //$NON-NLS-1$
		txtTeam1ClearAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ThreeBarScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team13BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ThreeBarScoringFileName, "cell 10 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ThreeBarScoringFileName = new JTextField();
		txtTeam1ThreeBarScoringFileName.setText(settings.getThreeBarScoringFileName(1));
		add(txtTeam1ThreeBarScoringFileName, "cell 11 10,alignx left"); //$NON-NLS-1$
		txtTeam1ThreeBarScoringFileName.setColumns(10);

		JLabel lblReset2FileName = new JLabel(Messages.getString("FileNamesPanel.Reset2", settings.getGameType())); //$NON-NLS-1$
		add(lblReset2FileName, "cell 1 11,alignx right"); //$NON-NLS-1$

		txtReset2FileName = new JTextField();
		txtReset2FileName.setText(settings.getResetFileName(2));
		add(txtReset2FileName, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtReset2FileName.setColumns(10);

		JLabel lblMeatball = new JLabel(Messages.getString("FileNamesPanel.Meatball", settings.getGameType())); //$NON-NLS-1$
		add(lblMeatball, "cell 4 11,alignx right"); //$NON-NLS-1$

		txtMeatballFileName = new JTextField();
		txtMeatballFileName.setText(settings.getMeatballFileName());
		add(txtMeatballFileName, "cell 5 11,alignx left"); //$NON-NLS-1$
		txtMeatballFileName.setColumns(10);

		JLabel lblTeam1ClearCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team1ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearCompletesFileName, "cell 7 11,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearCompletesFileName = new JTextField();
		txtTeam1ClearCompletesFileName.setText(settings.getClearCompletesFileName(1));
		add(txtTeam1ClearCompletesFileName, "cell 8 11,alignx left"); //$NON-NLS-1$
		txtTeam1ClearCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ThreeBarScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team23BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ThreeBarScoringFileName, "cell 10 11,alignx right"); //$NON-NLS-1$
		
		txtTeam2ThreeBarScoringFileName = new JTextField();
		txtTeam2ThreeBarScoringFileName.setText(settings.getThreeBarScoringFileName(2));
		add(txtTeam2ThreeBarScoringFileName, "cell 11 11,alignx left"); //$NON-NLS-1$
		txtTeam2ThreeBarScoringFileName.setColumns(10);

		JLabel lblWarn1FileName = new JLabel(Messages.getString("FileNamesPanel.Warn1", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn1FileName, "cell 1 12,alignx right"); //$NON-NLS-1$

		txtWarn1FileName = new JTextField();
		txtWarn1FileName.setText(settings.getWarnFileName(1));
		add(txtWarn1FileName, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtWarn1FileName.setColumns(10);

		JLabel lblLastScoredFileName = new JLabel(Messages.getString("FileNamesPanel.LastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblLastScoredFileName, "cell 4 12,alignx right"); //$NON-NLS-1$

		txtLastScoredFileName = new JTextField();
		txtLastScoredFileName.setText(settings.getLastScoredFileName());
		add(txtLastScoredFileName, "cell 5 12,alignx left"); //$NON-NLS-1$
		txtLastScoredFileName.setColumns(10);

		JLabel lblTeam2ClearAttemptsFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearAttemptsFileName, "cell 7 12,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearAttemptsFileName = new JTextField();
		txtTeam2ClearAttemptsFileName.setText(settings.getClearAttemptsFileName(2));
		add(txtTeam2ClearAttemptsFileName, "cell 8 12,alignx left"); //$NON-NLS-1$
		txtTeam2ClearAttemptsFileName.setColumns(20);
		
		JLabel lblTeambar = new JLabel(Messages.getString("FileNamesPanel.Team15BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar, "cell 10 12,alignx right"); //$NON-NLS-1$
		
		txtTeam1FiveBarScoringFileName = new JTextField();
		txtTeam1FiveBarScoringFileName.setText(settings.getFiveBarScoringFileName(1));
		add(txtTeam1FiveBarScoringFileName, "cell 11 12,alignx left"); //$NON-NLS-1$
		txtTeam1FiveBarScoringFileName.setColumns(10);

		JLabel lblWarn2FileName = new JLabel(Messages.getString("FileNamesPanel.Warn2", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn2FileName, "cell 1 13,alignx right"); //$NON-NLS-1$

		txtWarn2FileName = new JTextField();
		txtWarn2FileName.setText(settings.getWarnFileName(2));
		add(txtWarn2FileName, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtWarn2FileName.setColumns(10);
		
		JLabel lblStuffs1FileName = new JLabel(Messages.getString("FileNamesPanel.Stuffs1", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs1FileName, "cell 4 13,alignx right"); //$NON-NLS-1$
		
		txtStuffs1FileName = new JTextField();
		txtStuffs1FileName.setText(settings.getStuffsFileName(1));
		add(txtStuffs1FileName, "cell 5 13,alignx left"); //$NON-NLS-1$
		txtStuffs1FileName.setColumns(10);

		JLabel lblTeam2ClearCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team2ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearCompletesFileName, "cell 7 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearCompletesFileName = new JTextField();
		txtTeam2ClearCompletesFileName.setText(settings.getClearCompletesFileName(2));		add(txtTeam2ClearCompletesFileName, "cell 8 13,alignx left"); //$NON-NLS-1$
		txtTeam2ClearCompletesFileName.setColumns(20);

		JButton btnSaveFileNames = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		
		JLabel lblTeam1SOG = new JLabel(Messages.getString("FileNamesPanel.Team1ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SOG, "cell 10 16,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1ShotsOnGoalFileName = new JTextField();
		txtTeam1ShotsOnGoalFileName.setText(settings.getShotsOnGoalFileName(1));
		add(txtTeam1ShotsOnGoalFileName, "cell 11 16,alignx left"); //$NON-NLS-1$
		txtTeam1ShotsOnGoalFileName.setColumns(10);
		
		JLabel lblTeam2SOG = new JLabel(Messages.getString("FileNamesPanel.Team2ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SOG, "cell 10 17,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2ShotsOnGoalFileName = new JTextField();
		txtTeam2ShotsOnGoalFileName.setText(settings.getShotsOnGoalFileName(2));
		add(txtTeam2ShotsOnGoalFileName, "cell 11 17,alignx left"); //$NON-NLS-1$
		txtTeam2ShotsOnGoalFileName.setColumns(10);
		add(btnSaveFileNames, "cell 2 19,alignx center"); //$NON-NLS-1$
		
		JLabel lblTeambar_1 = new JLabel(Messages.getString("FileNamesPanel.Team25BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar_1, "cell 10 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2FiveBarScoringFileName = new JTextField();
		txtTeam2FiveBarScoringFileName.setText(settings.getFiveBarScoringFileName(2));
		add(txtTeam2FiveBarScoringFileName, "cell 11 13,alignx left"); //$NON-NLS-1$
		txtTeam2FiveBarScoringFileName.setColumns(10);
		
		JLabel lblGameTimeFileName = new JLabel(Messages.getString("FileNamesPanel.GameTime", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimeFileName, "cell 1 14,alignx right"); //$NON-NLS-1$
		
		txtGameTimeFileName = new JTextField();
		txtGameTimeFileName.setText(settings.getGameTimeFileName());
		add(txtGameTimeFileName, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtGameTimeFileName.setColumns(10);
		
		JLabel lblStuffs2FileName = new JLabel(Messages.getString("FileNamesPanel.Stuffs2", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs2FileName, "cell 4 14,alignx right"); //$NON-NLS-1$
		
		txtStuffs2FileName = new JTextField();
		txtStuffs2FileName.setText(settings.getStuffsFileName(2));
		add(txtStuffs2FileName, "cell 5 14,alignx left"); //$NON-NLS-1$
		txtStuffs2FileName.setColumns(10);
		
		JLabel lblTeam1TwoBarPassAttemptsFileName_1 = new JLabel(Messages.getString("FileNamesPanel.Team12BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassAttemptsFileName_1, "cell 7 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassAttemptsFileName = new JTextField();
		txtTeam1TwoBarPassAttemptsFileName.setText(settings.getTwoBarPassAttemptsFileName(1));
		txtTeam1TwoBarPassAttemptsFileName.setColumns(20);
		add(txtTeam1TwoBarPassAttemptsFileName, "cell 8 14,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam1TwoBarScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team12BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarScoringFileName, "cell 10 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarScoringFileName = new JTextField();
		txtTeam1TwoBarScoringFileName.setText(settings.getTwoBarScoringFileName(1));
		add(txtTeam1TwoBarScoringFileName, "cell 11 14,alignx left"); //$NON-NLS-1$
		txtTeam1TwoBarScoringFileName.setColumns(10);
		
		JLabel lblMatchTimeFileName = new JLabel(Messages.getString("FileNamesPanel.MatchTime", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchTimeFileName, "cell 1 15,alignx right"); //$NON-NLS-1$
		
		txtMatchTimeFileName = new JTextField();
		txtMatchTimeFileName.setText(settings.getMatchTimeFileName());
		add(txtMatchTimeFileName, "cell 2 15,alignx left"); //$NON-NLS-1$
		txtMatchTimeFileName.setColumns(10);
		
		JLabel lblBreaks1FileName = new JLabel(Messages.getString("FileNamesPanel.Breaks1", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks1FileName, "cell 4 15,alignx right"); //$NON-NLS-1$
		
		txtBreaks1FileName = new JTextField();
		txtBreaks1FileName.setText(settings.getBreaksFileName(1));
		add(txtBreaks1FileName, "cell 5 15,alignx left"); //$NON-NLS-1$
		txtBreaks1FileName.setColumns(10);
		
		JLabel lblTeam1TwoBarPassCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team12BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassCompletesFileName, "cell 7 15,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassCompletesFileName = new JTextField();
		txtTeam1TwoBarPassCompletesFileName.setText(settings.getTwoBarPassCompletesFileName(1));
		txtTeam1TwoBarPassCompletesFileName.setColumns(20);
		add(txtTeam1TwoBarPassCompletesFileName, "cell 8 15,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarScoringFileName = new JLabel(Messages.getString("FileNamesPanel.Team22BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarScoringFileName, "cell 10 15,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarScoringFileName = new JTextField();
		txtTeam2TwoBarScoringFileName.setText(settings.getTwoBarScoringFileName(2));
		add(txtTeam2TwoBarScoringFileName, "cell 11 15,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarScoringFileName.setColumns(10);
		
		JLabel lblBreaks2FileName = new JLabel(Messages.getString("FileNamesPanel.Breaks2", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks2FileName, "cell 4 16,alignx right"); //$NON-NLS-1$
		
		txtBreaks2FileName = new JTextField();
		txtBreaks2FileName.setText(settings.getBreaksFileName(2));
		add(txtBreaks2FileName, "cell 5 16,alignx left"); //$NON-NLS-1$
		txtBreaks2FileName.setColumns(10);
		
		JLabel lblTeam2TwoBarPassAttemptsFileName_1_1 = new JLabel(Messages.getString("FileNamesPanel.Team22BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassAttemptsFileName_1_1, "cell 7 16,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassAttemptsFileName = new JTextField();
		txtTeam2TwoBarPassAttemptsFileName.setText(settings.getTwoBarPassAttemptsFileName(2));
		txtTeam2TwoBarPassAttemptsFileName.setColumns(20);
		add(txtTeam2TwoBarPassAttemptsFileName, "cell 8 16,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarPassCompletesFileName = new JLabel(Messages.getString("FileNamesPanel.Team22BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassCompletesFileName, "cell 7 17,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassCompletesFileName = new JTextField();
		txtTeam2TwoBarPassCompletesFileName.setText(settings.getTwoBarPassCompletesFileName(2));
		txtTeam2TwoBarPassCompletesFileName.setColumns(20);
		add(txtTeam2TwoBarPassCompletesFileName, "cell 8 17,alignx left"); //$NON-NLS-1$

		JButton btnCancelFileNames = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelFileNames, "cell 4 19,alignx center"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
}
