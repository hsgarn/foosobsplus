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

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import main.OBSInterface;
import model.Settings;
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
	private JFormattedTextField formattedTxtPath;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp";

	// Create the Panel.

	public FileNamesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;
		setBounds(100, 100, 853, 489);
		setLayout(new MigLayout("", "[][][151.00,grow][15.00][91.00][grow][]", "[][][][][][][][][][][][][][][][][]"));

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

						File directory = new File(directoryName);
						if (!directory.exists()) {
							directory.mkdirs();
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
		add(lblFileName, "cell 2 2,alignx left");

		JLabel lblNewLabel = new JLabel("File Name");
		add(lblNewLabel, "cell 5 2,alignx left,aligny center");

		JLabel lblTeam1NameFileName = new JLabel("Team 1:");
		add(lblTeam1NameFileName, "cell 1 3,alignx trailing");

		txtTeam1NameFileName = new JTextField();
		txtTeam1NameFileName.setText(settings.getTeamNameFileName(1));
		add(txtTeam1NameFileName, "cell 2 3,alignx left");
		txtTeam1NameFileName.setColumns(10);

		JLabel lblTeam1Forward = new JLabel("Team 1 Forward");
		add(lblTeam1Forward, "cell 4 3,alignx trailing");

		txtTeam1ForwardFileName = new JTextField();
		txtTeam1ForwardFileName.setText(settings.getTeamForwardFileName(1));
		add(txtTeam1ForwardFileName, "cell 5 3,alignx left");
		txtTeam1ForwardFileName.setColumns(10);

		JLabel lblTeam2NameFileName = new JLabel("Team 2:");
		add(lblTeam2NameFileName, "cell 1 4,alignx right");

		txtTeam2NameFileName = new JTextField();
		txtTeam2NameFileName.setText(settings.getTeamNameFileName(2));
		add(txtTeam2NameFileName, "cell 2 4,alignx left");
		txtTeam2NameFileName.setColumns(10);

		JLabel lblTeam1Goalie = new JLabel("Team 1 Goalie");
		add(lblTeam1Goalie, "cell 4 4,alignx trailing");

		txtTeam1GoalieFileName = new JTextField();
		txtTeam1GoalieFileName.setText(settings.getTeamGoalieFileName(1));
		add(txtTeam1GoalieFileName, "cell 5 4,alignx left");
		txtTeam1GoalieFileName.setColumns(10);

		JLabel lblGameCountFileName = new JLabel("Game Count 1:");
		add(lblGameCountFileName, "cell 1 5,alignx right");

		txtGameCount1FileName = new JTextField();
		txtGameCount1FileName.setText(settings.getGameCountFileName(1));
		add(txtGameCount1FileName, "cell 2 5,alignx left");
		txtGameCount1FileName.setColumns(10);

		JLabel lblTeam2Forward = new JLabel("Team 2 Forward");
		add(lblTeam2Forward, "cell 4 5,alignx trailing");

		txtTeam2ForwardFileName = new JTextField();
		txtTeam2ForwardFileName.setText(settings.getTeamForwardFileName(2));
		add(txtTeam2ForwardFileName, "cell 5 5,alignx left");
		txtTeam2ForwardFileName.setColumns(10);

		JLabel lblGameCount2FileName = new JLabel("Game Count 2:");
		add(lblGameCount2FileName, "cell 1 6,alignx right");

		txtGameCount2FileName = new JTextField();
		txtGameCount2FileName.setText(settings.getGameCountFileName(2));
		add(txtGameCount2FileName, "cell 2 6,alignx left");
		txtGameCount2FileName.setColumns(10);

		JLabel lblTeam2Name2 = new JLabel("Team 2 Goalie");
		add(lblTeam2Name2, "cell 4 6,alignx trailing");

		txtTeam2GoalieFileName = new JTextField();
		txtTeam2GoalieFileName.setText(settings.getTeamGoalieFileName(2));
		add(txtTeam2GoalieFileName, "cell 5 6,alignx left");
		txtTeam2GoalieFileName.setColumns(10);

		JLabel lblScore1FileName = new JLabel("Score 1:");
		add(lblScore1FileName, "cell 1 7,alignx trailing");

		txtScore1FileName = new JTextField();
		txtScore1FileName.setText(settings.getScoreFileName(1));
		add(txtScore1FileName, "cell 2 7,alignx left");
		txtScore1FileName.setColumns(10);

		JLabel lblTournamentFileName = new JLabel("Tournament:");
		add(lblTournamentFileName, "cell 4 7,alignx right");

		txtTournamentFileName = new JTextField();
		txtTournamentFileName.setText(settings.getTournamentFileName());
		add(txtTournamentFileName, "cell 5 7,alignx left");
		txtTournamentFileName.setColumns(10);

		JLabel lblScore2FileName = new JLabel("Score 2:");
		add(lblScore2FileName, "cell 1 8,alignx trailing");

		txtScore2FileName = new JTextField();
		txtScore2FileName.setText(settings.getScoreFileName(2));
		add(txtScore2FileName, "cell 2 8,alignx left");
		txtScore2FileName.setColumns(10);

		JLabel lblEventFileName = new JLabel("Event:");
		add(lblEventFileName, "cell 4 8,alignx trailing");

		txtEventFileName = new JTextField();
		txtEventFileName.setText(settings.getEventFileName());
		add(txtEventFileName, "cell 5 8,alignx left");
		txtEventFileName.setColumns(10);

		JLabel lblTimeOut1FileName = new JLabel("Time Out 1:");
		add(lblTimeOut1FileName, "cell 1 9,alignx trailing");

		txtTimeOut1FileName = new JTextField();
		txtTimeOut1FileName.setText(settings.getTimeOutFileName(1));
		add(txtTimeOut1FileName, "cell 2 9,alignx left");
		txtTimeOut1FileName.setColumns(10);

		JLabel lblTimeRemainingFileName = new JLabel("Time Remaining:");
		add(lblTimeRemainingFileName, "cell 4 9,alignx trailing");

		txtTimeRemainingFileName = new JTextField();
		txtTimeRemainingFileName.setText(settings.getTimeRemainingFileName());
		add(txtTimeRemainingFileName, "cell 5 9,alignx left");
		txtTimeRemainingFileName.setColumns(10);

		JLabel lblTimeOut2FileName = new JLabel("Time Out 2:");
		add(lblTimeOut2FileName, "cell 1 10,alignx trailing");

		txtTimeOut2FileName = new JTextField();
		txtTimeOut2FileName.setText(settings.getTimeOutFileName(2));
		add(txtTimeOut2FileName, "cell 2 10,alignx left");
		txtTimeOut2FileName.setColumns(10);

		JLabel lblTimerFileName = new JLabel("Timer:");
		add(lblTimerFileName, "cell 4 10,alignx trailing");

		txtTimerInUseFileName = new JTextField();
		txtTimerInUseFileName.setText(settings.getTimerInUseFileName());
		add(txtTimerInUseFileName, "cell 5 10,alignx left");
		txtTimerInUseFileName.setColumns(10);

		JLabel lblReset1FileName = new JLabel("Reset 1:");
		add(lblReset1FileName, "cell 1 11,alignx trailing");

		txtReset1FileName = new JTextField();
		txtReset1FileName.setText(settings.getResetFileName(1));
		add(txtReset1FileName, "cell 2 11,alignx left");
		txtReset1FileName.setColumns(10);

		JLabel lblMatchWinnerFileName = new JLabel("Match Winner:");
		add(lblMatchWinnerFileName, "cell 4 11,alignx trailing");

		txtMatchWinnerFileName = new JTextField();
		txtMatchWinnerFileName.setText(settings.getMatchWinnerFileName());
		add(txtMatchWinnerFileName, "cell 5 11,alignx left");
		txtMatchWinnerFileName.setColumns(10);

		JLabel lblReset2FileName = new JLabel("Reset 2:");
		add(lblReset2FileName, "cell 1 12,alignx trailing");

		txtReset2FileName = new JTextField();
		txtReset2FileName.setText(settings.getResetFileName(2));
		add(txtReset2FileName, "cell 2 12,alignx left");
		txtReset2FileName.setColumns(10);

		JLabel lblMeatball = new JLabel("Meatball:");
		add(lblMeatball, "cell 4 12,alignx trailing");

		txtMeatballFileName = new JTextField();
		txtMeatballFileName.setText(settings.getMeatballFileName());
		add(txtMeatballFileName, "cell 5 12,alignx left");
		txtMeatballFileName.setColumns(10);

		JLabel lblWarn1FileName = new JLabel("Warn 1:");
		add(lblWarn1FileName, "cell 1 13,alignx trailing");

		txtWarn1FileName = new JTextField();
		txtWarn1FileName.setText(settings.getWarnFileName(1));
		add(txtWarn1FileName, "cell 2 13,alignx left");
		txtWarn1FileName.setColumns(10);

		JLabel lblLastScored = new JLabel("Last Scored:");
		add(lblLastScored, "cell 4 13,alignx trailing");

		txtLastScoredFileName = new JTextField();
		txtLastScoredFileName.setText(settings.getLastScoredFileName());
		add(txtLastScoredFileName, "cell 5 13,alignx left");
		txtLastScoredFileName.setColumns(10);

		JLabel lblWarn2FileName = new JLabel("Warn 2:");
		add(lblWarn2FileName, "cell 1 14,alignx trailing");

		txtWarn2FileName = new JTextField();
		txtWarn2FileName.setText(settings.getWarnFileName(2));
		add(txtWarn2FileName, "cell 2 14,alignx left");
		txtWarn2FileName.setColumns(10);

		JButton btnSaveFileNames = new JButton("Save");
		btnSaveFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveFileNames, "cell 2 16,alignx center");

		JButton btnCancelFileNames = new JButton("Cancel");
		btnCancelFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelFileNames, "cell 4 16,alignx center");

		JButton btnRestoreDefaults = new JButton("Restore Defaults");
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 16,alignx center");
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
		try {
			settings.saveToConfig();
		} catch (IOException ex) {
			System.out.print("Error saving properties file: " + ex.getMessage());
		}
	}
}
