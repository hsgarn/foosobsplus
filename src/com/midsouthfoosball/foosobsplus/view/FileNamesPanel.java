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
	private String defaultFilePath = "c:\\temp";

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
			settings.saveToConfig();
		} catch (IOException ex) {
			System.out.print("Error saving properties file: " + ex.getMessage());
		}
	}
	public void setLayout(Settings settings) {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]"));

		JButton btnSelectPath = new JButton("Select Path");
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle("Select directory for path");
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
								System.out.println("Unable to create directory; " + e1.getMessage());
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					obsInterface.setFilePath(formattedTxtPath.getText());
					try {
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveToConfig();
					} catch (IOException ex) {
						System.out.print("Error saving properties file: " + ex.getMessage());
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0");

		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					obsInterface.setFilePath(formattedTxtPath.getText());
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveToConfig();
		    	} catch (IOException ex) {
		    		System.out.print("Error saving properties file: " + ex.getMessage());		
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
						settings.saveToConfig();
			    	} catch (IOException ex) {
			    		System.out.print("Error saving properties file: " + ex.getMessage());		
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left");
		formattedTxtPath.setColumns(50);

		JLabel lblFileName = new JLabel("File Name");
		add(lblFileName, "cell 2 1,alignx left");

		JLabel lblFileNameCol2 = new JLabel("File Name");
		add(lblFileNameCol2, "cell 5 1,alignx left,aligny center");
		
		JLabel lblFileNameCol3 = new JLabel("File Name");
		add(lblFileNameCol3, "cell 8 1,alignx left");
		
		JLabel lblFileNameCol4 = new JLabel("File Name");
		add(lblFileNameCol4, "cell 11 1");

		JLabel lblTeam1NameFileName = new JLabel("Team 1:");
		add(lblTeam1NameFileName, "cell 1 2,alignx right");

		txtTeam1NameFileName = new JTextField();
		txtTeam1NameFileName.setText(settings.getTeamNameFileName(1));
		add(txtTeam1NameFileName, "cell 2 2,alignx left");
		txtTeam1NameFileName.setColumns(10);

		JLabel lblTeam1Forward = new JLabel("Team 1 Forward");
		add(lblTeam1Forward, "cell 4 2,alignx right");

		txtTeam1ForwardFileName = new JTextField();
		txtTeam1ForwardFileName.setText(settings.getTeamForwardFileName(1));
		add(txtTeam1ForwardFileName, "cell 5 2,alignx left");
		txtTeam1ForwardFileName.setColumns(10);
		
		JLabel lblTeam1PassAttemptsFileName = new JLabel("Team 1 Pass Attempts");
		add(lblTeam1PassAttemptsFileName, "cell 7 2,alignx right");
		
		txtTeam1PassAttemptsFileName = new JTextField();
		txtTeam1PassAttemptsFileName.setText(settings.getPassAttemptsFileName(1));
		add(txtTeam1PassAttemptsFileName, "cell 8 2,alignx left");
		txtTeam1PassAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1PassPercentFileName = new JLabel("Team 1 Pass Percent");
		add(lblTeam1PassPercentFileName, "cell 10 2,alignx right");
		
		txtTeam1PassPercentFileName = new JTextField();
		txtTeam1PassPercentFileName.setText(settings.getPassPercentFileName(1));
		add(txtTeam1PassPercentFileName, "cell 11 2,alignx left");
		txtTeam1PassPercentFileName.setColumns(20);

		JLabel lblTeam2NameFileName = new JLabel("Team 2:");
		add(lblTeam2NameFileName, "cell 1 3,alignx right");

		txtTeam2NameFileName = new JTextField();
		txtTeam2NameFileName.setText(settings.getTeamNameFileName(2));
		add(txtTeam2NameFileName, "cell 2 3,alignx left");
		txtTeam2NameFileName.setColumns(10);

		JLabel lblTeam1Goalie = new JLabel("Team 1 Goalie");
		add(lblTeam1Goalie, "cell 4 3,alignx right");

		txtTeam1GoalieFileName = new JTextField();
		txtTeam1GoalieFileName.setText(settings.getTeamGoalieFileName(1));
		add(txtTeam1GoalieFileName, "cell 5 3,alignx left");
		txtTeam1GoalieFileName.setColumns(10);
		
		JLabel lblTeam1PassCompletesFileName = new JLabel("Team 1 Pass Completes");
		add(lblTeam1PassCompletesFileName, "cell 7 3,alignx right");
		
		txtTeam1PassCompletesFileName = new JTextField();
		txtTeam1PassCompletesFileName.setText(settings.getPassCompletesFileName(1));
		add(txtTeam1PassCompletesFileName, "cell 8 3,alignx left");
		txtTeam1PassCompletesFileName.setColumns(20);
		
		JLabel lblTeam2PassPercentFileName = new JLabel("Team 2 Pass Percent");
		add(lblTeam2PassPercentFileName, "cell 10 3,alignx right");
		
		txtTeam2PassPercentFileName = new JTextField();
		txtTeam2PassPercentFileName.setText(settings.getPassPercentFileName(2));
		add(txtTeam2PassPercentFileName, "cell 11 3,alignx left,aligny top");
		txtTeam2PassPercentFileName.setColumns(20);

		JLabel lblGameCountFileName = new JLabel("Game Count 1:");
		add(lblGameCountFileName, "cell 1 4,alignx right");

		txtGameCount1FileName = new JTextField();
		txtGameCount1FileName.setText(settings.getGameCountFileName(1));
		add(txtGameCount1FileName, "cell 2 4,alignx left");
		txtGameCount1FileName.setColumns(10);

		JLabel lblTeam2Forward = new JLabel("Team 2 Forward");
		add(lblTeam2Forward, "cell 4 4,alignx right");

		txtTeam2ForwardFileName = new JTextField();
		txtTeam2ForwardFileName.setText(settings.getTeamForwardFileName(2));
		add(txtTeam2ForwardFileName, "cell 5 4,alignx left");
		txtTeam2ForwardFileName.setColumns(10);

		JLabel lblTeam2PassAttemptsFileName = new JLabel("Team 2 Pass Attempts");
		add(lblTeam2PassAttemptsFileName, "cell 7 4,alignx right");
		
		txtTeam2PassAttemptsFileName = new JTextField();
		txtTeam2PassAttemptsFileName.setText(settings.getPassAttemptsFileName(2));
		add(txtTeam2PassAttemptsFileName, "cell 8 4,alignx left");
		txtTeam2PassAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ShotPercentFileName = new JLabel("Team 1 Shot Percent");
		add(lblTeam1ShotPercentFileName, "cell 10 4,alignx right");
		
		txtTeam1ShotPercentFileName = new JTextField();
		txtTeam1ShotPercentFileName.setText(settings.getShotPercentFileName(1));
		add(txtTeam1ShotPercentFileName, "cell 11 4,alignx left");
		txtTeam1ShotPercentFileName.setColumns(20);
		
		JLabel lblGameCount2FileName = new JLabel("Game Count 2:");
		add(lblGameCount2FileName, "cell 1 5,alignx right");

		txtGameCount2FileName = new JTextField();
		txtGameCount2FileName.setText(settings.getGameCountFileName(2));
		add(txtGameCount2FileName, "cell 2 5,alignx left");
		txtGameCount2FileName.setColumns(10);

		JLabel lblTeam2Name2 = new JLabel("Team 2 Goalie");
		add(lblTeam2Name2, "cell 4 5,alignx right");

		txtTeam2GoalieFileName = new JTextField();
		txtTeam2GoalieFileName.setText(settings.getTeamGoalieFileName(2));
		add(txtTeam2GoalieFileName, "cell 5 5,alignx left");
		txtTeam2GoalieFileName.setColumns(10);

		JLabel lblTeam2PassCompletesFileName = new JLabel("Team 2 Pass Completes");
		add(lblTeam2PassCompletesFileName, "cell 7 5,alignx right");
		
		txtTeam2PassCompletesFileName = new JTextField();
		txtTeam2PassCompletesFileName.setText(settings.getPassCompletesFileName(2));
		add(txtTeam2PassCompletesFileName, "cell 8 5,alignx left");
		txtTeam2PassCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ShotPercentFileName = new JLabel("Team 2 Shot Percent");
		add(lblTeam2ShotPercentFileName, "cell 10 5,alignx right");
		
		txtTeam2ShotPercentFileName = new JTextField();
		txtTeam2ShotPercentFileName.setText(settings.getShotPercentFileName(2));
		add(txtTeam2ShotPercentFileName, "cell 11 5,growx");
		txtTeam2ShotPercentFileName.setColumns(20);

		JLabel lblScore1FileName = new JLabel("Score 1:");
		add(lblScore1FileName, "cell 1 6,alignx right");

		txtScore1FileName = new JTextField();
		txtScore1FileName.setText(settings.getScoreFileName(1));
		add(txtScore1FileName, "cell 2 6,alignx left");
		txtScore1FileName.setColumns(10);

		JLabel lblTournamentFileName = new JLabel("Tournament:");
		add(lblTournamentFileName, "cell 4 6,alignx right");

		txtTournamentFileName = new JTextField();
		txtTournamentFileName.setText(settings.getTournamentFileName());
		add(txtTournamentFileName, "cell 5 6,alignx left");
		txtTournamentFileName.setColumns(10);

		JLabel lblTeam1ShotAttemptsFileName = new JLabel("Team 1 Shot Attempts");
		add(lblTeam1ShotAttemptsFileName, "cell 7 6,alignx right");
		
		txtTeam1ShotAttemptsFileName = new JTextField();
		txtTeam1ShotAttemptsFileName.setText(settings.getShotAttemptsFileName(1));
		add(txtTeam1ShotAttemptsFileName, "cell 8 6,alignx left");
		txtTeam1ShotAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ClearPercentFileName = new JLabel("Team 1 Clear Percent");
		add(lblTeam1ClearPercentFileName, "cell 10 6,alignx right");
		
		txtTeam1ClearPercentFileName = new JTextField();
		txtTeam1ClearPercentFileName.setText(settings.getClearPercentFileName(1));
		add(txtTeam1ClearPercentFileName, "cell 11 6,alignx left");
		txtTeam1ClearPercentFileName.setColumns(20);

		JLabel lblScore2FileName = new JLabel("Score 2:");
		add(lblScore2FileName, "cell 1 7,alignx right");

		txtScore2FileName = new JTextField();
		txtScore2FileName.setText(settings.getScoreFileName(2));
		add(txtScore2FileName, "cell 2 7,alignx left");
		txtScore2FileName.setColumns(10);

		JLabel lblEventFileName = new JLabel("Event:");
		add(lblEventFileName, "cell 4 7,alignx right");

		txtEventFileName = new JTextField();
		txtEventFileName.setText(settings.getEventFileName());
		add(txtEventFileName, "cell 5 7,alignx left");
		txtEventFileName.setColumns(10);

		JLabel lblTeam1ShotCompletesFileName = new JLabel("Team 1 Shot Completes");
		add(lblTeam1ShotCompletesFileName, "cell 7 7,alignx right");
		
		txtTeam1ShotCompletesFileName = new JTextField();
		txtTeam1ShotCompletesFileName.setText(settings.getShotCompletesFileName(1));
		add(txtTeam1ShotCompletesFileName, "cell 8 7,alignx left");
		txtTeam1ShotCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ClearPercentFileName = new JLabel("Team 2 Clear Percent");
		add(lblTeam2ClearPercentFileName, "cell 10 7,alignx right");
		
		txtTeam2ClearPercentFileName = new JTextField();
		txtTeam2ClearPercentFileName.setText(settings.getClearPercentFileName(2));
		add(txtTeam2ClearPercentFileName, "cell 11 7,alignx left");
		txtTeam2ClearPercentFileName.setColumns(20);

		JLabel lblTimeOut1FileName = new JLabel("Time Out 1:");
		add(lblTimeOut1FileName, "cell 1 8,alignx right");

		txtTimeOut1FileName = new JTextField();
		txtTimeOut1FileName.setText(settings.getTimeOutFileName(1));
		add(txtTimeOut1FileName, "cell 2 8,alignx left");
		txtTimeOut1FileName.setColumns(10);

		JLabel lblTimeRemainingFileName = new JLabel("Time Remaining:");
		add(lblTimeRemainingFileName, "cell 4 8,alignx right");

		txtTimeRemainingFileName = new JTextField();
		txtTimeRemainingFileName.setText(settings.getTimeRemainingFileName());
		add(txtTimeRemainingFileName, "cell 5 8,alignx left");
		txtTimeRemainingFileName.setColumns(10);

		JLabel lblTeam2ShotAttemptsFileName = new JLabel("Team 2 Shot Attempts");
		add(lblTeam2ShotAttemptsFileName, "cell 7 8,alignx right");
		
		txtTeam2ShotAttemptsFileName = new JTextField();
		txtTeam2ShotAttemptsFileName.setText(settings.getShotAttemptsFileName(2));
		add(txtTeam2ShotAttemptsFileName, "cell 8 8,alignx left");
		txtTeam2ShotAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ScoringFileName = new JLabel("Team 1 Scoring");
		add(lblTeam1ScoringFileName, "cell 10 8,alignx right");
		
		txtTeam1ScoringFileName = new JTextField();
		txtTeam1ScoringFileName.setText(settings.getScoringFileName(1));
		add(txtTeam1ScoringFileName, "cell 11 8,alignx left");
		txtTeam1ScoringFileName.setColumns(10);

		JLabel lblTimeOut2FileName = new JLabel("Time Out 2:");
		add(lblTimeOut2FileName, "cell 1 9,alignx right");

		txtTimeOut2FileName = new JTextField();
		txtTimeOut2FileName.setText(settings.getTimeOutFileName(2));
		add(txtTimeOut2FileName, "cell 2 9,alignx left");
		txtTimeOut2FileName.setColumns(10);

		JLabel lblTimerFileName = new JLabel("Timer:");
		add(lblTimerFileName, "cell 4 9,alignx right");

		txtTimerInUseFileName = new JTextField();
		txtTimerInUseFileName.setText(settings.getTimerInUseFileName());
		add(txtTimerInUseFileName, "cell 5 9,alignx left");
		txtTimerInUseFileName.setColumns(10);

		JLabel lblTeam2ShotCompletesFileName = new JLabel("Team 2 Shot Completes");
		add(lblTeam2ShotCompletesFileName, "cell 7 9,alignx right");
		
		txtTeam2ShotCompletesFileName = new JTextField();
		txtTeam2ShotCompletesFileName.setText(settings.getShotCompletesFileName(2));
		add(txtTeam2ShotCompletesFileName, "cell 8 9,alignx left");
		txtTeam2ShotCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ScoringFileName = new JLabel("Team 2 Scoring");
		add(lblTeam2ScoringFileName, "cell 10 9,alignx right");
		
		txtTeam2ScoringFileName = new JTextField();
		txtTeam2ScoringFileName.setText(settings.getScoringFileName(2));
		add(txtTeam2ScoringFileName, "cell 11 9,alignx left");
		txtTeam2ScoringFileName.setColumns(10);

		JLabel lblReset1FileName = new JLabel("Reset 1:");
		add(lblReset1FileName, "cell 1 10,alignx right");

		txtReset1FileName = new JTextField();
		txtReset1FileName.setText(settings.getResetFileName(1));
		add(txtReset1FileName, "cell 2 10,alignx left");
		txtReset1FileName.setColumns(10);

		JLabel lblMatchWinnerFileName = new JLabel("Match Winner:");
		add(lblMatchWinnerFileName, "cell 4 10,alignx right");

		txtMatchWinnerFileName = new JTextField();
		txtMatchWinnerFileName.setText(settings.getMatchWinnerFileName());
		add(txtMatchWinnerFileName, "cell 5 10,alignx left");
		txtMatchWinnerFileName.setColumns(10);

		JLabel lblTeam1ClearAttemptsFileName = new JLabel("Team 1 Clear Attempts");
		add(lblTeam1ClearAttemptsFileName, "cell 7 10,alignx right");
		
		txtTeam1ClearAttemptsFileName = new JTextField();
		txtTeam1ClearAttemptsFileName.setText(settings.getClearAttemptsFileName(1));
		add(txtTeam1ClearAttemptsFileName, "cell 8 10,alignx left");
		txtTeam1ClearAttemptsFileName.setColumns(20);
		
		JLabel lblTeam1ThreeBarScoringFileName = new JLabel("Team 1 3-Bar Scoring");
		add(lblTeam1ThreeBarScoringFileName, "cell 10 10,alignx right");
		
		txtTeam1ThreeBarScoringFileName = new JTextField();
		txtTeam1ThreeBarScoringFileName.setText(settings.getThreeBarScoringFileName(1));
		add(txtTeam1ThreeBarScoringFileName, "cell 11 10,alignx left");
		txtTeam1ThreeBarScoringFileName.setColumns(10);

		JLabel lblReset2FileName = new JLabel("Reset 2:");
		add(lblReset2FileName, "cell 1 11,alignx right");

		txtReset2FileName = new JTextField();
		txtReset2FileName.setText(settings.getResetFileName(2));
		add(txtReset2FileName, "cell 2 11,alignx left");
		txtReset2FileName.setColumns(10);

		JLabel lblMeatball = new JLabel("Meatball:");
		add(lblMeatball, "cell 4 11,alignx right");

		txtMeatballFileName = new JTextField();
		txtMeatballFileName.setText(settings.getMeatballFileName());
		add(txtMeatballFileName, "cell 5 11,alignx left");
		txtMeatballFileName.setColumns(10);

		JLabel lblTeam1ClearCompletesFileName = new JLabel("Team 1 Clear Completes");
		add(lblTeam1ClearCompletesFileName, "cell 7 11,alignx right");
		
		txtTeam1ClearCompletesFileName = new JTextField();
		txtTeam1ClearCompletesFileName.setText(settings.getClearCompletesFileName(1));
		add(txtTeam1ClearCompletesFileName, "cell 8 11,alignx left");
		txtTeam1ClearCompletesFileName.setColumns(20);
		
		JLabel lblTeam2ThreeBarScoringFileName = new JLabel("Team 2 3-Bar Scoring");
		add(lblTeam2ThreeBarScoringFileName, "cell 10 11,alignx right");
		
		txtTeam2ThreeBarScoringFileName = new JTextField();
		txtTeam2ThreeBarScoringFileName.setText(settings.getThreeBarScoringFileName(2));
		add(txtTeam2ThreeBarScoringFileName, "cell 11 11,alignx left");
		txtTeam2ThreeBarScoringFileName.setColumns(10);

		JLabel lblWarn1FileName = new JLabel("Warn 1:");
		add(lblWarn1FileName, "cell 1 12,alignx right");

		txtWarn1FileName = new JTextField();
		txtWarn1FileName.setText(settings.getWarnFileName(1));
		add(txtWarn1FileName, "cell 2 12,alignx left");
		txtWarn1FileName.setColumns(10);

		JLabel lblLastScoredFileName = new JLabel("Last Scored:");
		add(lblLastScoredFileName, "cell 4 12,alignx right");

		txtLastScoredFileName = new JTextField();
		txtLastScoredFileName.setText(settings.getLastScoredFileName());
		add(txtLastScoredFileName, "cell 5 12,alignx left");
		txtLastScoredFileName.setColumns(10);

		JLabel lblTeam2ClearAttemptsFileName = new JLabel("Team 2 Clear Attempts");
		add(lblTeam2ClearAttemptsFileName, "cell 7 12,alignx right");
		
		txtTeam2ClearAttemptsFileName = new JTextField();
		txtTeam2ClearAttemptsFileName.setText(settings.getClearAttemptsFileName(2));
		add(txtTeam2ClearAttemptsFileName, "cell 8 12,alignx left");
		txtTeam2ClearAttemptsFileName.setColumns(20);
		
		JLabel lblTeambar = new JLabel("Team 1 5-Bar Scoring");
		add(lblTeambar, "cell 10 12,alignx right");
		
		txtTeam1FiveBarScoringFileName = new JTextField();
		txtTeam1FiveBarScoringFileName.setText(settings.getFiveBarScoringFileName(1));
		add(txtTeam1FiveBarScoringFileName, "cell 11 12,alignx left");
		txtTeam1FiveBarScoringFileName.setColumns(10);

		JLabel lblWarn2FileName = new JLabel("Warn 2:");
		add(lblWarn2FileName, "cell 1 13,alignx right");

		txtWarn2FileName = new JTextField();
		txtWarn2FileName.setText(settings.getWarnFileName(2));
		add(txtWarn2FileName, "cell 2 13,alignx left");
		txtWarn2FileName.setColumns(10);
		
		JLabel lblStuffs1FileName = new JLabel("Stuffs 1");
		add(lblStuffs1FileName, "cell 4 13,alignx right");
		
		txtStuffs1FileName = new JTextField();
		txtStuffs1FileName.setText(settings.getStuffsFileName(1));
		add(txtStuffs1FileName, "cell 5 13,alignx left");
		txtStuffs1FileName.setColumns(10);

		JLabel lblTeam2ClearCompletesFileName = new JLabel("Team 2 Clear Completes");
		add(lblTeam2ClearCompletesFileName, "cell 7 13,alignx right");
		
		txtTeam2ClearCompletesFileName = new JTextField();
		txtTeam2ClearCompletesFileName.setText(settings.getClearCompletesFileName(2));		add(txtTeam2ClearCompletesFileName, "cell 8 13,alignx left");
		txtTeam2ClearCompletesFileName.setColumns(20);

		JButton btnSaveFileNames = new JButton("Save");
		btnSaveFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		
		JLabel lblTeam1SOG = new JLabel("Team 1 Shots On Goal");
		add(lblTeam1SOG, "cell 10 16,alignx trailing");
		
		txtTeam1ShotsOnGoalFileName = new JTextField();
		txtTeam1ShotsOnGoalFileName.setText(settings.getShotsOnGoalFileName(1));
		add(txtTeam1ShotsOnGoalFileName, "cell 11 16,alignx left");
		txtTeam1ShotsOnGoalFileName.setColumns(10);
		
		JLabel lblTeam2SOG = new JLabel("Team 2 Shots On Goal");
		add(lblTeam2SOG, "cell 10 17,alignx trailing");
		
		txtTeam2ShotsOnGoalFileName = new JTextField();
		txtTeam2ShotsOnGoalFileName.setText(settings.getShotsOnGoalFileName(2));
		add(txtTeam2ShotsOnGoalFileName, "cell 11 17,alignx left");
		txtTeam2ShotsOnGoalFileName.setColumns(10);
		add(btnSaveFileNames, "cell 2 19,alignx center");
		
		JLabel lblTeambar_1 = new JLabel("Team 2 5-Bar Scoring");
		add(lblTeambar_1, "cell 10 13,alignx right");
		
		txtTeam2FiveBarScoringFileName = new JTextField();
		txtTeam2FiveBarScoringFileName.setText(settings.getFiveBarScoringFileName(2));
		add(txtTeam2FiveBarScoringFileName, "cell 11 13,alignx left");
		txtTeam2FiveBarScoringFileName.setColumns(10);
		
		JLabel lblGameTimeFileName = new JLabel("Game Time");
		add(lblGameTimeFileName, "cell 1 14,alignx right");
		
		txtGameTimeFileName = new JTextField();
		txtGameTimeFileName.setText(settings.getGameTimeFileName());
		add(txtGameTimeFileName, "cell 2 14,alignx left");
		txtGameTimeFileName.setColumns(10);
		
		JLabel lblStuffs2FileName = new JLabel("Stuffs 2");
		add(lblStuffs2FileName, "cell 4 14,alignx right");
		
		txtStuffs2FileName = new JTextField();
		txtStuffs2FileName.setText(settings.getStuffsFileName(2));
		add(txtStuffs2FileName, "cell 5 14,alignx left");
		txtStuffs2FileName.setColumns(10);
		
		JLabel lblTeam1TwoBarPassAttemptsFileName_1 = new JLabel("Team 1 2-Bar Pass Attempts");
		add(lblTeam1TwoBarPassAttemptsFileName_1, "cell 7 14,alignx right");
		
		txtTeam1TwoBarPassAttemptsFileName = new JTextField();
		txtTeam1TwoBarPassAttemptsFileName.setText(settings.getTwoBarPassAttemptsFileName(1));
		txtTeam1TwoBarPassAttemptsFileName.setColumns(20);
		add(txtTeam1TwoBarPassAttemptsFileName, "cell 8 14,alignx left");
		
		JLabel lblTeam1TwoBarScoringFileName = new JLabel("Team 1 2-Bar Scoring");
		add(lblTeam1TwoBarScoringFileName, "cell 10 14,alignx right");
		
		txtTeam1TwoBarScoringFileName = new JTextField();
		txtTeam1TwoBarScoringFileName.setText(settings.getTwoBarScoringFileName(1));
		add(txtTeam1TwoBarScoringFileName, "cell 11 14,alignx left");
		txtTeam1TwoBarScoringFileName.setColumns(10);
		
		JLabel lblMatchTimeFileName = new JLabel("Match Time");
		add(lblMatchTimeFileName, "cell 1 15,alignx right");
		
		txtMatchTimeFileName = new JTextField();
		txtMatchTimeFileName.setText(settings.getMatchTimeFileName());
		add(txtMatchTimeFileName, "cell 2 15,alignx left");
		txtMatchTimeFileName.setColumns(10);
		
		JLabel lblBreaks1FileName = new JLabel("Breaks 1");
		add(lblBreaks1FileName, "cell 4 15,alignx right");
		
		txtBreaks1FileName = new JTextField();
		txtBreaks1FileName.setText(settings.getBreaksFileName(1));
		add(txtBreaks1FileName, "cell 5 15,alignx left");
		txtBreaks1FileName.setColumns(10);
		
		JLabel lblTeam1TwoBarPassCompletesFileName = new JLabel("Team 1 2-Bar Pass Completes");
		add(lblTeam1TwoBarPassCompletesFileName, "cell 7 15,alignx right");
		
		txtTeam1TwoBarPassCompletesFileName = new JTextField();
		txtTeam1TwoBarPassCompletesFileName.setText(settings.getTwoBarPassCompletesFileName(1));
		txtTeam1TwoBarPassCompletesFileName.setColumns(20);
		add(txtTeam1TwoBarPassCompletesFileName, "cell 8 15,alignx left");
		
		JLabel lblTeam2TwoBarScoringFileName = new JLabel("Team 2 2-Bar Scoring");
		add(lblTeam2TwoBarScoringFileName, "cell 10 15,alignx right");
		
		txtTeam2TwoBarScoringFileName = new JTextField();
		txtTeam2TwoBarScoringFileName.setText(settings.getTwoBarScoringFileName(2));
		add(txtTeam2TwoBarScoringFileName, "cell 11 15,alignx left");
		txtTeam2TwoBarScoringFileName.setColumns(10);
		
		JLabel lblBreaks2FileName = new JLabel("Breaks 2");
		add(lblBreaks2FileName, "cell 4 16,alignx right");
		
		txtBreaks2FileName = new JTextField();
		txtBreaks2FileName.setText(settings.getBreaksFileName(2));
		add(txtBreaks2FileName, "cell 5 16,alignx left");
		txtBreaks2FileName.setColumns(10);
		
		JLabel lblTeam2TwoBarPassAttemptsFileName_1_1 = new JLabel("Team 2 2-Bar Pass Attempts");
		add(lblTeam2TwoBarPassAttemptsFileName_1_1, "cell 7 16,alignx right");
		
		txtTeam2TwoBarPassAttemptsFileName = new JTextField();
		txtTeam2TwoBarPassAttemptsFileName.setText(settings.getTwoBarPassAttemptsFileName(2));
		txtTeam2TwoBarPassAttemptsFileName.setColumns(20);
		add(txtTeam2TwoBarPassAttemptsFileName, "cell 8 16,alignx left");
		
		JLabel lblTeam2TwoBarPassCompletesFileName = new JLabel("Team 2 2-Bar Pass Completes");
		add(lblTeam2TwoBarPassCompletesFileName, "cell 7 17,alignx right");
		
		txtTeam2TwoBarPassCompletesFileName = new JTextField();
		txtTeam2TwoBarPassCompletesFileName.setText(settings.getTwoBarPassCompletesFileName(2));
		txtTeam2TwoBarPassCompletesFileName.setColumns(20);
		add(txtTeam2TwoBarPassCompletesFileName, "cell 8 17,alignx left");

		JButton btnCancelFileNames = new JButton("Cancel");
		btnCancelFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelFileNames, "cell 4 19,alignx center");

		JButton btnRestoreDefaults = new JButton("Restore Defaults");
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center");
	}
}
