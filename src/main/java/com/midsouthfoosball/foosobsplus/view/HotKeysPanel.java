/**
Copyright © 2020-2026 Hugh Garner
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
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class HotKeysPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtStartMatchHotKey;
	private JTextField txtPauseMatchHotKey;
	private JTextField txtEndMatchHotKey;
	private JTextField txtStartGameHotKey;
	private JTextField txtTeam1SwitchPositionsHotKey;
	private JTextField txtTeam2SwitchPositionsHotKey;
	private JTextField txtTeam3SwitchPositionsHotKey;
	private JTextField txtSwitchTeamsHotKey;
	private JTextField txtGameCount1MinusHotKey;
	private JTextField txtGameCount1PlusHotKey;
	private JTextField txtGameCount2MinusHotKey;
	private JTextField txtGameCount2PlusHotKey;
	private JTextField txtGameCount3MinusHotKey;
	private JTextField txtGameCount3PlusHotKey;
	private JTextField txtMatchCount1MinusHotKey;
	private JTextField txtMatchCount1PlusHotKey;
	private JTextField txtMatchCount2MinusHotKey;
	private JTextField txtMatchCount2PlusHotKey;
	private JTextField txtMatchCount3MinusHotKey;
	private JTextField txtMatchCount3PlusHotKey;
	private JTextField txtSwitchGameCountsHotKey;
	private JTextField txtSwitchMatchCountsHotKey;
	private JTextField txtScore1MinusHotKey;
	private JTextField txtScore1PlusHotKey;
	private JTextField txtScore2MinusHotKey;
	private JTextField txtScore2PlusHotKey;
	private JTextField txtScore3MinusHotKey;
	private JTextField txtScore3PlusHotKey;
	private JTextField txtSwitchScoresHotKey;
	private JTextField txtTimeOut1MinusHotKey;
	private JTextField txtTimeOut1PlusHotKey;
	private JTextField txtTimeOut2MinusHotKey;
	private JTextField txtTimeOut2PlusHotKey;
	private JTextField txtTimeOut3MinusHotKey;
	private JTextField txtTimeOut3PlusHotKey;
	private JTextField txtSwitchTimeOutsHotKey;
	private JTextField txtReset1HotKey;
	private JTextField txtReset2HotKey;
	private JTextField txtReset3HotKey;
	private JTextField txtWarn1HotKey;
	private JTextField txtWarn2HotKey;
	private JTextField txtWarn3HotKey;
	private JTextField txtKingSeat1HotKey;
	private JTextField txtKingSeat2HotKey;
	private JTextField txtKingSeat3HotKey;
	private JTextField txtSwitchResetWarnsHotKey;
	private JTextField txtSwitchSidesHotKey;
	private JTextField txtResetGameCountsHotKey;
	private JTextField txtResetMatchCountsHotKey;
	private JTextField txtResetScoresHotKey;
	private JTextField txtResetTimeOutsHotKey;
	private JTextField txtResetResetWarnHotKey;
	private JTextField txtResetAllHotKey;
	private JTextField txtClearAllHotKey;
	private JTextField txtShotTimerHotKey;
	private JTextField txtPassTimerHotKey;
	private JTextField txtTimeOutTimerHotKey;
	private JTextField txtGameTimerHotKey;
	private JTextField txtRecallTimerHotKey;
	private JTextField txtResetTimersHotKey;
	private JTextField txtResetNamesHotKey;
	private JTextField txtUndoHotKey;
	private JTextField txtRedoHotKey;
	private JTextField txtSwitchForwardsHotKey;
	private JTextField txtSwitchGoaliesHotKey;
	private JTextField txtShowSkunkHotKey;
	private JTextField txtStartStreamHotKey;
	private JTextField txtClearTournamentHotKey;
	private JLabel lblAvailableKeys;
	private JButton btnApply;
	private JButton btnSave;
	private JButton btnGenerateHotKeyScripts;
	private String hotKeyBaseScriptText;
	private static final Logger logger = LoggerFactory.getLogger(HotKeysPanel.class);
	private final Map<String, JTextField> sourcesMap = new HashMap<>();
	private static final String VALIDKEYS = "abcdefghijklmnopqrstuvwxyz0123456789.,-;[]/\\"; //$NON-NLS-1$
	private static final String TEAM1 = "1"; //$NON-NLS-1$
	private static final String TEAM2 = "2"; //$NON-NLS-1$
	private static final String TEAM3 = "3"; //$NON-NLS-1$
	public HotKeysPanel() throws IOException {
		setupLayout();
		loadSourcesMap();
		loadAvailableListeners();
		lblAvailableKeys.setText(getAvailableKeys());
	}
	private void loadAvailableListeners() {
		sourcesMap.forEach((String sourceName, JTextField textField) -> {
			textField.addActionListener((ActionEvent e) -> {
                            lblAvailableKeys.setText(getAvailableKeys());
                        });
			textField.addFocusListener(new FocusListener() {
                                @Override
				public void focusLost(FocusEvent e) {
					lblAvailableKeys.setText(getAvailableKeys());
				}
                                @Override
				public void focusGained(FocusEvent e) {
				}
			});
		});
	}
	private void loadSourcesMap() {
		sourcesMap.put("Team1SwitchPositions",txtTeam1SwitchPositionsHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2SwitchPositions",txtTeam2SwitchPositionsHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3SwitchPositions",txtTeam3SwitchPositionsHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1ScorePlus",txtScore1PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2ScorePlus",txtScore2PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3ScorePlus",txtScore3PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1ScoreMinus",txtScore1MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2ScoreMinus",txtScore2MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3ScoreMinus",txtScore3MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1GameCountPlus",txtGameCount1PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2GameCountPlus",txtGameCount2PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3GameCountPlus",txtGameCount3PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1GameCountMinus",txtGameCount1MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2GameCountMinus",txtGameCount2MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3GameCountMinus",txtGameCount3MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1MatchCountPlus",txtMatchCount1PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2MatchCountPlus",txtMatchCount2PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3MatchCountPlus",txtMatchCount3PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1MatchCountMinus",txtMatchCount1MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2MatchCountMinus",txtMatchCount2MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3MatchCountMinus",txtMatchCount3MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1TimeOutPlus",txtTimeOut1PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2TimeOutPlus",txtTimeOut2PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3TimeOutPlus",txtTimeOut3PlusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1TimeOutMinus",txtTimeOut1MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team2TimeOutMinus",txtTimeOut2MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team3TimeOutMinus",txtTimeOut3MinusHotKey); //$NON-NLS-1$
		sourcesMap.put("Team1Reset",txtReset1HotKey); //$NON-NLS-1$
		sourcesMap.put("Team2Reset",txtReset2HotKey); //$NON-NLS-1$
		sourcesMap.put("Team3Reset",txtReset3HotKey); //$NON-NLS-1$
		sourcesMap.put("Team1Warn",txtWarn1HotKey); //$NON-NLS-1$
		sourcesMap.put("Team2Warn",txtWarn2HotKey); //$NON-NLS-1$
		sourcesMap.put("Team3Warn",txtWarn3HotKey); //$NON-NLS-1$
		sourcesMap.put("Team1KingSeat",txtKingSeat1HotKey); //$NON-NLS-1$
		sourcesMap.put("Team2KingSeat",txtKingSeat2HotKey); //$NON-NLS-1$
		sourcesMap.put("Team3KingSeat",txtKingSeat3HotKey); //$NON-NLS-1$
		sourcesMap.put("StartMatch",txtStartMatchHotKey); //$NON-NLS-1$
		sourcesMap.put("PauseMatch",txtPauseMatchHotKey); //$NON-NLS-1$
		sourcesMap.put("EndMatch",txtEndMatchHotKey); //$NON-NLS-1$
		sourcesMap.put("StartGame",txtStartGameHotKey); //$NON-NLS-1$
		sourcesMap.put("ShotTimer",txtShotTimerHotKey); //$NON-NLS-1$
		sourcesMap.put("PassTimer",txtPassTimerHotKey); //$NON-NLS-1$
		sourcesMap.put("TimeOutTimer",txtTimeOutTimerHotKey); //$NON-NLS-1$
		sourcesMap.put("GameTimer",txtGameTimerHotKey); //$NON-NLS-1$
		sourcesMap.put("RecallTimer",txtRecallTimerHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetTimers",txtResetTimersHotKey); //$NON-NLS-1$
		sourcesMap.put("ClearAll",txtClearAllHotKey); //$NON-NLS-1$
		sourcesMap.put("Undo",txtUndoHotKey); //$NON-NLS-1$
		sourcesMap.put("Redo",txtRedoHotKey); //$NON-NLS-1$
		sourcesMap.put("ShowSkunk",txtShowSkunkHotKey); //$NON-NLS-1$
		sourcesMap.put("StartStream",txtStartStreamHotKey); //$NON-NLS-1$
		sourcesMap.put("ClearTournament", txtClearTournamentHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchMatchCounts",txtSwitchMatchCountsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchSides",txtSwitchSidesHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchTeams",txtSwitchTeamsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchScores",txtSwitchScoresHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchGameCounts",txtSwitchGameCountsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchTimeOuts",txtSwitchTimeOutsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchResetWarns",txtSwitchResetWarnsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchForwards",txtSwitchForwardsHotKey); //$NON-NLS-1$
		sourcesMap.put("SwitchGoalies",txtSwitchGoaliesHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetNames",txtResetNamesHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetScores",txtResetScoresHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetGameCounts",txtResetGameCountsHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetTimeOuts",txtResetTimeOutsHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetResetWarn",txtResetResetWarnHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetAll",txtResetAllHotKey); //$NON-NLS-1$
		sourcesMap.put("ResetMatchCounts",txtResetMatchCountsHotKey); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		formattedTxtPath.setText(Settings.getDefaultHotKey("HotKeyScriptPath")); //$NON-NLS-1$
		hotKeyBaseScriptText = Settings.getDefaultHotKey("HotKeyBaseScript"); //$NON-NLS-1$
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getDefaultHotKey(sourceName));
		});
		lblAvailableKeys.setText(getAvailableKeys());
	}
	private void revertChanges() {
		formattedTxtPath.setText(Settings.getHotKeyParameter("HotKeyScriptPath")); //$NON-NLS-1$
		hotKeyBaseScriptText = Settings.getHotKeyParameter("HotKeyBaseScript"); //$NON-NLS-1$
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setText(Settings.getHotKeyParameter(sourceName));
		});
		lblAvailableKeys.setText(getAvailableKeys());
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		Settings.setHotKeyScriptPath(formattedTxtPath.getText());
		if(checkForUniqueHotKeys()) {
			Settings.setHotKey("HotKeyBaseScript", hotKeyBaseScriptText); //$NON-NLS-1$
			sourcesMap.forEach((sourceName, textField) -> {
				Settings.setHotKey(sourceName, textField.getText());
			});
			try {
				Settings.saveHotKeyConfig();
				okToCloseWindow = true;
			} catch (IOException ex) {
				logger.error(ex.toString());
				JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), Messages.getString("HotKeysPanel.SettingsError"), 1); //$NON-NLS-1$		 //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return okToCloseWindow;
	}
	private boolean checkForUniqueHotKeys() {
		boolean allClear = true;
		HashSet<String> checkUnique = new HashSet<>();
		Component[] componentArray = this.getComponents();
		for(int i=0; i<componentArray.length; i++) {
			if(componentArray[i] instanceof JTextField) {
				String text = ((JTextField)componentArray[i]).getText();
				if (!text.equals("")) { //$NON-NLS-1$
					if(!checkUnique.add(text)) {
						allClear = false;
						i = componentArray.length + 1;
						logger.warn(Messages.getString("Errors.DuplicateHotKey") + " " + text); //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.showMessageDialog(null, Messages.getString("Errors.DuplicateHotKey") + " " + text, Messages.getString("HotKeysPanel.HotKeysError"), 1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			}
		}
		return allClear;
	}
	private void setupLayout() {
		hotKeyBaseScriptText = Settings.getHotKeyParameter("HotKeyBaseScript"); //$NON-NLS-1$
		if (hotKeyBaseScriptText.isEmpty()) hotKeyBaseScriptText = Settings.getDefaultHotKey("HotKeyBaseScript"); //$NON-NLS-1$
		setLayout(new MigLayout("", "[][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JButton btnSelectPath = new JButton(Messages.getString("HotKeysPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener((ActionEvent e) -> {
                    final JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
                    chooser.setDialogTitle(Messages.getString("HotKeysPanel.SelectDirectoryForPath")); //$NON-NLS-1$
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    int returnVal = chooser.showOpenDialog(HotKeysPanel.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        if (chooser.getSelectedFile().exists()) {
                            formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
                        } else {
                            String directoryName = chooser.getSelectedFile().getAbsolutePath();
                            if(!Files.exists(Paths.get(directoryName))) {
                                try {
                                    Files.createDirectory(Paths.get(directoryName));
                                } catch (IOException e1) {
                                    logger.error(e1.toString());
                                    JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage(), Messages.getString("HotKeysPanel.HotKeysError"), 1); //$NON-NLS-1$ //$NON-NLS-2$
                                }
                            }
                            formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
                        }
                        try {
                            Settings.setHotKeyScriptPath(formattedTxtPath.getText());
                            Settings.saveHotKeyConfig();
                        } catch (IOException ex) {
                            logger.error(ex.toString());
                            JOptionPane.showMessageDialog(null, Messages.getString("HotKeysPanel.ErrorSavingPropertiesFile") + ex.getMessage(), Messages.getString("HotKeysPanel.HotKeysError"), 1); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                    }
                });
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$
		formattedTxtPath = new JFormattedTextField();
		formattedTxtPath.setText(Settings.getHotKeyParameter("HotKeyScriptPath")); //$NON-NLS-1$
		formattedTxtPath.addFocusListener(new FocusAdapter() {
                        @Override
			public void focusLost(FocusEvent arg0) {
		    	try {
					Settings.setHotKeyScriptPath(formattedTxtPath.getText());
					Settings.saveHotKeyConfig();
		    	} catch (IOException ex) {
		    		logger.error(ex.toString());
		    		JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), Messages.getString("HotKeysPanel.HotKeysError"), 1);		 //$NON-NLS-1$ //$NON-NLS-2$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
                        @Override
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						Settings.setHotKeyScriptPath(formattedTxtPath.getText());
						Settings.saveHotKeyConfig();
			    	} catch (IOException ex) {
			    		logger.error(ex.toString());
			    		JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), Messages.getString("HotKeysPanel.HotKeysError"), 1);		 //$NON-NLS-1$ //$NON-NLS-2$
			    	}
			    }
			}
		});
		formattedTxtPath.setColumns(50);
		add(formattedTxtPath, "cell 2 0 8 1,alignx left"); //$NON-NLS-1$
		//Column Headers
		JLabel lblTeam1Column = new JLabel(Messages.getString("HotKeysPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column = new JLabel(Messages.getString("HotKeysPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam3Column = new JLabel(Messages.getString("HotKeysPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$
		JLabel lblHotKeyCol1 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKeyCol1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKeyCol1, "cell 2 2,alignx left"); //$NON-NLS-1$
		JLabel lblHotKeyCol2 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKeyCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKeyCol2, "cell 3 2,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblHotKeyCol3 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKeyCol3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKeyCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		JLabel lblHotKeyCol4 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKeyCol4.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKeyCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
		JLabel lblHotKeyCol5 = new JLabel(Messages.getString("HotKeysPanel.HotKey")); //$NON-NLS-1$
		lblHotKeyCol5.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblHotKeyCol5, "cell 8 2,alignx left"); //$NON-NLS-1$
		JLabel lblTeam1SwitchPositionsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPositions")); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsHotKey, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam1SwitchPositionsHotKey = new JTextField();
		txtTeam1SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1SwitchPositionsHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"SwitchPositions")); //$NON-NLS-1$
		txtTeam1SwitchPositionsHotKey.setColumns(10);
		add(txtTeam1SwitchPositionsHotKey, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2SwitchPositionsHotKey = new JTextField();
		txtTeam2SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2SwitchPositionsHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsHotKey.setColumns(10);
		add(txtTeam2SwitchPositionsHotKey, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam3SwitchPositionsHotKey = new JTextField();
		txtTeam3SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam3SwitchPositionsHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"SwitchPositions")); //$NON-NLS-1$
		txtTeam3SwitchPositionsHotKey.setColumns(10);
		add(txtTeam3SwitchPositionsHotKey, "cell 4 3,alignx left"); //$NON-NLS-1$
		JLabel lblScore1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementScore")); //$NON-NLS-1$
		add(lblScore1PlusHotKey, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtScore1PlusHotKey = new JTextField();
		txtScore1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"ScorePlus")); //$NON-NLS-1$
		txtScore1PlusHotKey.setColumns(10);
		add(txtScore1PlusHotKey, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtScore2PlusHotKey = new JTextField();
		txtScore2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"ScorePlus")); //$NON-NLS-1$
		txtScore2PlusHotKey.setColumns(10);
		add(txtScore2PlusHotKey, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtScore3PlusHotKey = new JTextField();
		txtScore3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore3PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"ScorePlus")); //$NON-NLS-1$
		txtScore3PlusHotKey.setColumns(10);
		add(txtScore3PlusHotKey, "cell 4 4,alignx left"); //$NON-NLS-1$
		JLabel lblScore1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementScore")); //$NON-NLS-1$
		add(lblScore1MinusHotKey, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtScore1MinusHotKey = new JTextField();
		txtScore1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"ScoreMinus")); //$NON-NLS-1$
		txtScore1MinusHotKey.setColumns(10);
		add(txtScore1MinusHotKey, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtScore2MinusHotKey = new JTextField();
		txtScore2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"ScoreMinus")); //$NON-NLS-1$
		txtScore2MinusHotKey.setColumns(10);
		add(txtScore2MinusHotKey, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtScore3MinusHotKey = new JTextField();
		txtScore3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore3MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"ScoreMinus")); //$NON-NLS-1$
		txtScore3MinusHotKey.setColumns(10);
		add(txtScore3MinusHotKey, "cell 4 5,alignx left"); //$NON-NLS-1$
		JLabel lblGameCount1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementGameCount")); //$NON-NLS-1$
		add(lblGameCount1PlusHotKey, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtGameCount1PlusHotKey = new JTextField();
		txtGameCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"GameCountPlus")); //$NON-NLS-1$
		txtGameCount1PlusHotKey.setColumns(10);
		add(txtGameCount1PlusHotKey, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtGameCount2PlusHotKey = new JTextField();
		txtGameCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"GameCountPlus")); //$NON-NLS-1$
		txtGameCount2PlusHotKey.setColumns(10);
		add(txtGameCount2PlusHotKey, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtGameCount3PlusHotKey = new JTextField();
		txtGameCount3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount3PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"GameCountPlus")); //$NON-NLS-1$
		txtGameCount3PlusHotKey.setColumns(10);
		add(txtGameCount3PlusHotKey, "cell 4 6,alignx left"); //$NON-NLS-1$
		JLabel lblGameCount1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementGameCount")); //$NON-NLS-1$
		add(lblGameCount1MinusHotKey, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtGameCount1MinusHotKey = new JTextField();
		txtGameCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"GameCountMinus")); //$NON-NLS-1$
		txtGameCount1MinusHotKey.setColumns(10);
		add(txtGameCount1MinusHotKey, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtGameCount2MinusHotKey = new JTextField();
		txtGameCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"GameCountMinus")); //$NON-NLS-1$
		txtGameCount2MinusHotKey.setColumns(10);
		add(txtGameCount2MinusHotKey, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtGameCount3MinusHotKey = new JTextField();
		txtGameCount3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount3MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"GameCountMinus")); //$NON-NLS-1$
		txtGameCount3MinusHotKey.setColumns(10);
		add(txtGameCount3MinusHotKey, "cell 4 7,alignx left"); //$NON-NLS-1$
		JLabel lblMatchCount1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementMatchCount")); //$NON-NLS-1$
		add(lblMatchCount1PlusHotKey, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtMatchCount1PlusHotKey = new JTextField();
		txtMatchCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount1PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"MatchCountPlus")); //$NON-NLS-1$
		txtMatchCount1PlusHotKey.setColumns(10);
		add(txtMatchCount1PlusHotKey, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtMatchCount2PlusHotKey = new JTextField();
		txtMatchCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount2PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"MatchCountPlus")); //$NON-NLS-1$
		txtMatchCount2PlusHotKey.setColumns(10);
		add(txtMatchCount2PlusHotKey, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtMatchCount3PlusHotKey = new JTextField();
		txtMatchCount3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount3PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"MatchCountPlus")); //$NON-NLS-1$
		txtMatchCount3PlusHotKey.setColumns(10);
		add(txtMatchCount3PlusHotKey, "cell 4 8,alignx left"); //$NON-NLS-1$
		JLabel lblMatchCount1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementMatchCount")); //$NON-NLS-1$
		add(lblMatchCount1MinusHotKey, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtMatchCount1MinusHotKey = new JTextField();
		txtMatchCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount1MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"MatchCountMinus")); //$NON-NLS-1$
		txtMatchCount1MinusHotKey.setColumns(10);
		add(txtMatchCount1MinusHotKey, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtMatchCount2MinusHotKey = new JTextField();
		txtMatchCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount2MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"MatchCountMinus")); //$NON-NLS-1$
		txtMatchCount2MinusHotKey.setColumns(10);
		add(txtMatchCount2MinusHotKey, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtMatchCount3MinusHotKey = new JTextField();
		txtMatchCount3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount3MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"MatchCountMinus")); //$NON-NLS-1$
		txtMatchCount3MinusHotKey.setColumns(10);
		add(txtMatchCount3MinusHotKey, "cell 4 9,alignx left"); //$NON-NLS-1$
		JLabel lblTimeOut1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.UseTimeOut")); //$NON-NLS-1$
		add(lblTimeOut1PlusHotKey, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTimeOut1PlusHotKey = new JTextField();
		txtTimeOut1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"TimeOutPlus")); //$NON-NLS-1$
		txtTimeOut1PlusHotKey.setColumns(10);
		add(txtTimeOut1PlusHotKey, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTimeOut2PlusHotKey = new JTextField();
		txtTimeOut2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"TimeOutPlus")); //$NON-NLS-1$
		txtTimeOut2PlusHotKey.setColumns(10);
		add(txtTimeOut2PlusHotKey, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtTimeOut3PlusHotKey = new JTextField();
		txtTimeOut3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut3PlusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"TimeOutPlus")); //$NON-NLS-1$
		txtTimeOut3PlusHotKey.setColumns(10);
		add(txtTimeOut3PlusHotKey, "cell 4 10,alignx left"); //$NON-NLS-1$
		JLabel lblTimeOut1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.ReturnTimeOut")); //$NON-NLS-1$
		add(lblTimeOut1MinusHotKey, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTimeOut1MinusHotKey = new JTextField();
		txtTimeOut1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"TimeOutMinus")); //$NON-NLS-1$
		txtTimeOut1MinusHotKey.setColumns(10);
		add(txtTimeOut1MinusHotKey, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTimeOut2MinusHotKey = new JTextField();
		txtTimeOut2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"TimeOutMinus")); //$NON-NLS-1$
		txtTimeOut2MinusHotKey.setColumns(10);
		add(txtTimeOut2MinusHotKey, "cell 3 11,alignx left"); //$NON-NLS-1$
		txtTimeOut3MinusHotKey = new JTextField();
		txtTimeOut3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut3MinusHotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"TimeOutMinus")); //$NON-NLS-1$
		txtTimeOut3MinusHotKey.setColumns(10);
		add(txtTimeOut3MinusHotKey, "cell 4 11,alignx left"); //$NON-NLS-1$
		JLabel lblReset1HotKey = new JLabel(Messages.getString("HotKeysPanel.Reset")); //$NON-NLS-1$
		add(lblReset1HotKey, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtReset1HotKey = new JTextField();
		txtReset1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset1HotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"Reset")); //$NON-NLS-1$
		txtReset1HotKey.setColumns(10);
		add(txtReset1HotKey, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtReset2HotKey = new JTextField();
		txtReset2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset2HotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"Reset")); //$NON-NLS-1$
		txtReset2HotKey.setColumns(10);
		add(txtReset2HotKey, "cell 3 12,alignx left"); //$NON-NLS-1$
		txtReset3HotKey = new JTextField();
		txtReset3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset3HotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"Reset")); //$NON-NLS-1$
		txtReset3HotKey.setColumns(10);
		add(txtReset3HotKey, "cell 4 12,alignx left"); //$NON-NLS-1$
		JLabel lblWarn1HotKey = new JLabel(Messages.getString("HotKeysPanel.Warn")); //$NON-NLS-1$
		add(lblWarn1HotKey, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtWarn1HotKey = new JTextField();
		txtWarn1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn1HotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"Warn")); //$NON-NLS-1$
		add(txtWarn1HotKey, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtWarn1HotKey.setColumns(10);
		txtWarn2HotKey = new JTextField();
		txtWarn2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn2HotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"Warn")); //$NON-NLS-1$
		txtWarn2HotKey.setColumns(10);
		add(txtWarn2HotKey, "cell 3 13,alignx left"); //$NON-NLS-1$
		txtWarn3HotKey = new JTextField();
		txtWarn3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn3HotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"Warn")); //$NON-NLS-1$
		txtWarn3HotKey.setColumns(10);
		add(txtWarn3HotKey, "cell 4 13,alignx left"); //$NON-NLS-1$
		JLabel lblKingSeat1HotKey = new JLabel(Messages.getString("HotKeysPanel.KingSeat")); //$NON-NLS-1$
		add(lblKingSeat1HotKey, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtKingSeat1HotKey = new JTextField();
		txtKingSeat1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat1HotKey.setText(Settings.getTeamHotKeyParameter(TEAM1,"KingSeat")); //$NON-NLS-1$
		txtKingSeat1HotKey.setColumns(10);
		add(txtKingSeat1HotKey, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtKingSeat2HotKey = new JTextField();
		txtKingSeat2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat2HotKey.setText(Settings.getTeamHotKeyParameter(TEAM2,"KingSeat")); //$NON-NLS-1$
		txtKingSeat2HotKey.setColumns(10);
		add(txtKingSeat2HotKey, "cell 3 14,alignx left"); //$NON-NLS-1$
		txtKingSeat3HotKey = new JTextField();
		txtKingSeat3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat3HotKey.setText(Settings.getTeamHotKeyParameter(TEAM3,"KingSeat")); //$NON-NLS-1$
		txtKingSeat3HotKey.setColumns(10);
		add(txtKingSeat3HotKey, "cell 4 14,alignx left"); //$NON-NLS-1$
		JLabel lblStartMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.StartMatch")); //$NON-NLS-1$
		add(lblStartMatchHotKey, "cell 5 3,alignx right"); //$NON-NLS-1$
		txtStartMatchHotKey = new JTextField();
		txtStartMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartMatchHotKey.setText(Settings.getHotKeyParameter("StartMatch")); //$NON-NLS-1$
		txtStartMatchHotKey.setColumns(10);
		add(txtStartMatchHotKey, "cell 6 3,alignx left"); //$NON-NLS-1$
		JLabel lblPauseMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.PauseMatch")); //$NON-NLS-1$
		add(lblPauseMatchHotKey, "cell 5 4,alignx right"); //$NON-NLS-1$
		txtPauseMatchHotKey = new JTextField();
		txtPauseMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPauseMatchHotKey.setText(Settings.getHotKeyParameter("PauseMatch")); //$NON-NLS-1$
		txtPauseMatchHotKey.setColumns(10);
		add(txtPauseMatchHotKey, "cell 6 4,alignx left"); //$NON-NLS-1$
		JLabel lblEndMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.EndMatch")); //$NON-NLS-1$
		add(lblEndMatchHotKey, "cell 5 5,alignx right"); //$NON-NLS-1$
		txtEndMatchHotKey = new JTextField();
		txtEndMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndMatchHotKey.setText(Settings.getHotKeyParameter("EndMatch")); //$NON-NLS-1$
		txtEndMatchHotKey.setColumns(10);
		add(txtEndMatchHotKey, "cell 6 5,alignx left"); //$NON-NLS-1$
		JLabel lblStartGameHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGame")); //$NON-NLS-1$
		add(lblStartGameHotKey, "cell 5 6,alignx right"); //$NON-NLS-1$
		txtStartGameHotKey = new JTextField();
		txtStartGameHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartGameHotKey.setText(Settings.getHotKeyParameter("StartGame")); //$NON-NLS-1$
		txtStartGameHotKey.setColumns(10);
		add(txtStartGameHotKey, "cell 6 6,alignx left,aligny top"); //$NON-NLS-1$
		JLabel lblShotTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartShotTimer")); //$NON-NLS-1$
		add(lblShotTimerHotKey, "cell 5 7,alignx right"); //$NON-NLS-1$
		txtShotTimerHotKey = new JTextField();
		txtShotTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShotTimerHotKey.setText(Settings.getHotKeyParameter("ShotTimer")); //$NON-NLS-1$
		txtShotTimerHotKey.setColumns(10);
		add(txtShotTimerHotKey, "cell 6 7,alignx left"); //$NON-NLS-1$
		JLabel lblPassTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartPassTimer")); //$NON-NLS-1$
		add(lblPassTimerHotKey, "cell 5 8,alignx right"); //$NON-NLS-1$
		txtPassTimerHotKey = new JTextField();
		txtPassTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassTimerHotKey.setText(Settings.getHotKeyParameter("PassTimer")); //$NON-NLS-1$
		txtPassTimerHotKey.setColumns(10);
		add(txtPassTimerHotKey, "cell 6 8,alignx left"); //$NON-NLS-1$
		JLabel lblTimeOutTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartTimeOut")); //$NON-NLS-1$
		add(lblTimeOutTimerHotKey, "cell 5 9,alignx right"); //$NON-NLS-1$
		txtTimeOutTimerHotKey = new JTextField();
		txtTimeOutTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOutTimerHotKey.setText(Settings.getHotKeyParameter("TimeOutTimer")); //$NON-NLS-1$
		txtTimeOutTimerHotKey.setColumns(10);
		add(txtTimeOutTimerHotKey, "cell 6 9,alignx left"); //$NON-NLS-1$
		JLabel lblGameTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGameTimer")); //$NON-NLS-1$
		add(lblGameTimerHotKey, "cell 5 10,alignx right"); //$NON-NLS-1$
		txtGameTimerHotKey = new JTextField();
		txtGameTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameTimerHotKey.setText(Settings.getHotKeyParameter("GameTimer")); //$NON-NLS-1$
		txtGameTimerHotKey.setColumns(10);
		add(txtGameTimerHotKey, "cell 6 10,alignx left"); //$NON-NLS-1$
		JLabel lblRecallTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartRecallTimer")); //$NON-NLS-1$
		add(lblRecallTimerHotKey, "cell 5 11,alignx right"); //$NON-NLS-1$
		txtRecallTimerHotKey = new JTextField();
		txtRecallTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecallTimerHotKey.setText(Settings.getHotKeyParameter("RecallTimer")); //$NON-NLS-1$
		txtRecallTimerHotKey.setColumns(10);
		add(txtRecallTimerHotKey, "cell 6 11,alignx left"); //$NON-NLS-1$
		JLabel lblResetTimersHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimer")); //$NON-NLS-1$
		add(lblResetTimersHotKey, "cell 5 12,alignx right"); //$NON-NLS-1$
		txtResetTimersHotKey = new JTextField();
		txtResetTimersHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimersHotKey.setText(Settings.getHotKeyParameter("ResetTimers")); //$NON-NLS-1$
		txtResetTimersHotKey.setColumns(10);
		add(txtResetTimersHotKey, "cell 6 12,alignx left"); //$NON-NLS-1$
		JLabel lblClearAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearAll")); //$NON-NLS-1$
		add(lblClearAllHotKey, "cell 5 13,alignx right"); //$NON-NLS-1$
		txtClearAllHotKey = new JTextField();
		txtClearAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtClearAllHotKey.setText(Settings.getHotKeyParameter("ClearAll")); //$NON-NLS-1$
		txtClearAllHotKey.setColumns(10);
		add(txtClearAllHotKey, "cell 6 13,alignx left"); //$NON-NLS-1$
		JLabel lblUndoHotKey = new JLabel(Messages.getString("HotKeysPanel.Undo")); //$NON-NLS-1$
		add(lblUndoHotKey, "cell 5 14,alignx right"); //$NON-NLS-1$
		txtUndoHotKey = new JTextField();
		txtUndoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtUndoHotKey.setText(Settings.getHotKeyParameter("Undo")); //$NON-NLS-1$
		txtUndoHotKey.setColumns(10);
		add(txtUndoHotKey, "cell 6 14,alignx left"); //$NON-NLS-1$
		JLabel lblRedoHotKey = new JLabel(Messages.getString("HotKeysPanel.Redo")); //$NON-NLS-1$
		add(lblRedoHotKey, "cell 5 15,alignx right"); //$NON-NLS-1$
		txtRedoHotKey = new JTextField();
		txtRedoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedoHotKey.setText(Settings.getHotKeyParameter("Redo")); //$NON-NLS-1$
		txtRedoHotKey.setColumns(10);
		add(txtRedoHotKey, "cell 6 15,alignx left"); //$NON-NLS-1$
		JLabel lblShowSkunkHotKey = new JLabel(Messages.getString("HotKeysPanel.ShowSkunk")); //$NON-NLS-1$
		add(lblShowSkunkHotKey, "cell 5 16,alignx right"); //$NON-NLS-1$
		txtShowSkunkHotKey = new JTextField();
		txtShowSkunkHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowSkunkHotKey.setText(Settings.getHotKeyParameter("ShowSkunk")); //$NON-NLS-1$
		txtShowSkunkHotKey.setColumns(10);
		add(txtShowSkunkHotKey, "cell 6 16,alignx left"); //$NON-NLS-1$
		JLabel lblStartStreamHotKey = new JLabel(Messages.getString("HotKeysPanel.StartStream")); //$NON-NLS-1$
		add(lblStartStreamHotKey, "cell 5 17,alignx right"); //$NON-NLS-1$
		txtStartStreamHotKey = new JTextField();
		txtStartStreamHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartStreamHotKey.setText(Settings.getHotKeyParameter("StartStream")); //$NON-NLS-1$
		txtStartStreamHotKey.setColumns(10);
		add(txtStartStreamHotKey, "cell 6 17,alignx left"); //$NON-NLS-1$
		JLabel lblClearTournamentHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearTournament")); //$NON-NLS-1$
		add(lblClearTournamentHotKey, "cell 5 18,alignx right"); //$NON-NLS-1$
		txtClearTournamentHotKey = new JTextField();
		txtClearTournamentHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtClearTournamentHotKey.setText(Settings.getHotKeyParameter("ClearTournament")); //$NON-NLS-1$
		txtClearTournamentHotKey.setColumns(10);
		add(txtClearTournamentHotKey, "cell 6 18, alignx left"); //$NON-NLS-1$
		JLabel lblSwitchMatchCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchMatchCounts")); //$NON-NLS-1$
		add(lblSwitchMatchCountsHotKey, "cell 7 3,alignx right"); //$NON-NLS-1$
		txtSwitchMatchCountsHotKey = new JTextField();
		txtSwitchMatchCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchMatchCountsHotKey.setText(Settings.getHotKeyParameter("SwitchMatchCounts")); //$NON-NLS-1$
		txtSwitchMatchCountsHotKey.setColumns(10);
		add(txtSwitchMatchCountsHotKey, "cell 8 3,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchSidesHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchSides")); //$NON-NLS-1$
		add(lblSwitchSidesHotKey, "cell 7 4,alignx right"); //$NON-NLS-1$
		txtSwitchSidesHotKey = new JTextField();
		txtSwitchSidesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchSidesHotKey.setText(Settings.getHotKeyParameter("SwitchSides")); //$NON-NLS-1$
		txtSwitchSidesHotKey.setColumns(10);
		add(txtSwitchSidesHotKey, "cell 8 4,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchTeamsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTeams")); //$NON-NLS-1$
		add(lblSwitchTeamsHotKey, "cell 7 5,alignx right"); //$NON-NLS-1$
		txtSwitchTeamsHotKey = new JTextField();
		txtSwitchTeamsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTeamsHotKey.setText(Settings.getHotKeyParameter("SwitchTeams")); //$NON-NLS-1$
		txtSwitchTeamsHotKey.setColumns(10);
		add(txtSwitchTeamsHotKey, "cell 8 5,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchScores")); //$NON-NLS-1$
		add(lblSwitchScoresHotKey, "cell 7 6,alignx right"); //$NON-NLS-1$
		txtSwitchScoresHotKey = new JTextField();
		txtSwitchScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchScoresHotKey.setText(Settings.getHotKeyParameter("SwitchScores")); //$NON-NLS-1$
		txtSwitchScoresHotKey.setColumns(10);
		add(txtSwitchScoresHotKey, "cell 8 6,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchGameCounts")); //$NON-NLS-1$
		add(lblSwitchGameCountsHotKey, "cell 7 7,alignx right"); //$NON-NLS-1$
		txtSwitchGameCountsHotKey = new JTextField();
		txtSwitchGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchGameCountsHotKey.setText(Settings.getHotKeyParameter("SwitchGameCounts")); //$NON-NLS-1$
		txtSwitchGameCountsHotKey.setColumns(10);
		add(txtSwitchGameCountsHotKey, "cell 8 7,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTimeOuts")); //$NON-NLS-1$
		add(lblSwitchTimeOutsHotKey, "cell 7 8,alignx right"); //$NON-NLS-1$
		txtSwitchTimeOutsHotKey = new JTextField();
		txtSwitchTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTimeOutsHotKey.setText(Settings.getHotKeyParameter("SwitchTimeOuts")); //$NON-NLS-1$
		txtSwitchTimeOutsHotKey.setColumns(10);
		add(txtSwitchTimeOutsHotKey, "cell 8 8,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchResetWarnsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchResetWarns")); //$NON-NLS-1$
		add(lblSwitchResetWarnsHotKey, "cell 7 9,alignx right"); //$NON-NLS-1$
		txtSwitchResetWarnsHotKey = new JTextField();
		txtSwitchResetWarnsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchResetWarnsHotKey.setText(Settings.getHotKeyParameter("SwitchResetWarns")); //$NON-NLS-1$
		txtSwitchResetWarnsHotKey.setColumns(10);
		add(txtSwitchResetWarnsHotKey, "cell 8 9,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchForwardsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchForwards")); //$NON-NLS-1$
		add(lblSwitchForwardsHotKey, "cell 7 10,alignx right"); //$NON-NLS-1$
		txtSwitchForwardsHotKey = new JTextField();
		txtSwitchForwardsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchForwardsHotKey.setText(Settings.getHotKeyParameter("SwitchForwards")); //$NON-NLS-1$
		txtSwitchForwardsHotKey.setColumns(10);
		add(txtSwitchForwardsHotKey, "cell 8 10,alignx left"); //$NON-NLS-1$
		JLabel lblSwitchGoaliesHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchGoalies")); //$NON-NLS-1$
		add(lblSwitchGoaliesHotKey, "cell 7 11,alignx right"); //$NON-NLS-1$
		txtSwitchGoaliesHotKey = new JTextField();
		txtSwitchGoaliesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchGoaliesHotKey.setText(Settings.getHotKeyParameter("SwitchGoalies")); //$NON-NLS-1$
		txtSwitchGoaliesHotKey.setColumns(10);
		add(txtSwitchGoaliesHotKey, "cell 8 11,alignx left"); //$NON-NLS-1$
		JLabel lblResetNamesHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetNames")); //$NON-NLS-1$
		add(lblResetNamesHotKey, "cell 7 12,alignx right"); //$NON-NLS-1$
		txtResetNamesHotKey = new JTextField();
		txtResetNamesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetNamesHotKey.setText(Settings.getHotKeyParameter("ResetNames")); //$NON-NLS-1$
		txtResetNamesHotKey.setColumns(10);
		add(txtResetNamesHotKey, "cell 8 12,alignx left"); //$NON-NLS-1$
		JLabel lblResetScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetScores")); //$NON-NLS-1$
		add(lblResetScoresHotKey, "cell 7 13,alignx right"); //$NON-NLS-1$
		txtResetScoresHotKey = new JTextField();
		txtResetScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetScoresHotKey.setText(Settings.getHotKeyParameter("ResetScores")); //$NON-NLS-1$
		txtResetScoresHotKey.setColumns(10);
		add(txtResetScoresHotKey, "cell 8 13,alignx left"); //$NON-NLS-1$
		JLabel lblResetGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetGameCounts")); //$NON-NLS-1$
		add(lblResetGameCountsHotKey, "cell 7 14,alignx right"); //$NON-NLS-1$
		txtResetGameCountsHotKey = new JTextField();
		txtResetGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetGameCountsHotKey.setText(Settings.getHotKeyParameter("ResetGameCounts")); //$NON-NLS-1$
		txtResetGameCountsHotKey.setColumns(10);
		add(txtResetGameCountsHotKey, "cell 8 14,alignx left"); //$NON-NLS-1$
		JLabel lblResetTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimeOuts")); //$NON-NLS-1$
		add(lblResetTimeOutsHotKey, "cell 7 15,alignx right"); //$NON-NLS-1$
		txtResetTimeOutsHotKey = new JTextField();
		txtResetTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimeOutsHotKey.setText(Settings.getHotKeyParameter("ResetTimeOuts")); //$NON-NLS-1$
		txtResetTimeOutsHotKey.setColumns(10);
		add(txtResetTimeOutsHotKey, "cell 8 15,alignx left"); //$NON-NLS-1$
		JLabel lblResetResetWarnHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetResetWarn")); //$NON-NLS-1$
		add(lblResetResetWarnHotKey, "cell 7 16,alignx right"); //$NON-NLS-1$
		txtResetResetWarnHotKey = new JTextField();
		txtResetResetWarnHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetResetWarnHotKey.setText(Settings.getHotKeyParameter("ResetResetWarn")); //$NON-NLS-1$
		txtResetResetWarnHotKey.setColumns(10);
		add(txtResetResetWarnHotKey, "cell 8 16,alignx left"); //$NON-NLS-1$
		JLabel lblResetAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetAll")); //$NON-NLS-1$
		add(lblResetAllHotKey, "cell 7 17,alignx right"); //$NON-NLS-1$
		txtResetAllHotKey = new JTextField();
		txtResetAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetAllHotKey.setText(Settings.getHotKeyParameter("ResetAll")); //$NON-NLS-1$
		txtResetAllHotKey.setColumns(10);
		add(txtResetAllHotKey, "cell 8 17,alignx left"); //$NON-NLS-1$
		JLabel lblResetMatchCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetMatchCounts")); //$NON-NLS-1$
		add(lblResetMatchCountsHotKey, "cell 7 18,alignx right"); //$NON-NLS-1$
		txtResetMatchCountsHotKey = new JTextField();
		txtResetMatchCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetMatchCountsHotKey.setText(Settings.getHotKeyParameter("ResetMatchCounts")); //$NON-NLS-1$
		txtResetMatchCountsHotKey.setColumns(10);
		add(txtResetMatchCountsHotKey, "cell 8 18,alignx left"); //$NON-NLS-1$
		btnGenerateHotKeyScripts = new JButton(Messages.getString("HotKeysPanel.GenerateHotKeyScripts")); //$NON-NLS-1$
		JLabel lblAvailableHotKeys = new JLabel(Messages.getString("HotKeysPanel.AvailableHotKeys")); //$NON-NLS-1$
		add(lblAvailableHotKeys, "cell 1 17,alignx right"); //$NON-NLS-1$
		lblAvailableKeys = new JLabel(getAvailableKeys());
		add(lblAvailableKeys, "cell 2 17,alignx left"); //$NON-NLS-1$
		btnGenerateHotKeyScripts.addActionListener((ActionEvent e) -> {
                    Settings.generateHotKeyScripts();
                });
		add(btnGenerateHotKeyScripts, "cell 1 20, spanx 2, alignx center"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 3 20,alignx center"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 4 20,alignx center"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancel, "cell 6 20,alignx center"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 8 20, spanx 2, alignx right"); //$NON-NLS-1$
	}
	private String getAvailableKeys() {
		List<Character> remainingKeys = new ArrayList<>();
		for (char ch : VALIDKEYS.toCharArray()) {
			remainingKeys.add(ch);
		}
		String keys;
		Component[] componentArray = this.getComponents();
                for (Component componentArray1 : componentArray) {
                    if (componentArray1 instanceof JTextField) {
                        String text = ((JTextField) componentArray1).getText();
                        if (!text.isEmpty() && text.length()==1) {
                            char hk = text.charAt(0);
                            if (remainingKeys.contains(hk)) {
                                remainingKeys.remove((Character) hk);
                            }
                        }
                    }
                }
		if (remainingKeys.toString().length() <3 ) {
			keys = ""; //$NON-NLS-1$
		} else {
			keys = remainingKeys.toString().substring(1, remainingKeys.toString().length() - 1);
		}
		return keys;
	}
	////// Listeners \\\\\\
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
	public void addApplyListener(ActionListener listentForBtnApply) {
		btnApply.addActionListener(listentForBtnApply);
	}
}