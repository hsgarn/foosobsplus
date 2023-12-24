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
import java.util.HashSet;

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
	private JButton btnSave;
	private JButton btnGenerateHotKeyScripts;
	private String hotKeyBaseScriptText;
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public HotKeysPanel(Settings settings) throws IOException {
		hotKeyBaseScriptText = settings.getHotKeyBaseScript();
		if (hotKeyBaseScriptText.isEmpty()) hotKeyBaseScriptText = settings.getDefaultHotKeyBaseScript();
		setLayout(new MigLayout("", "[][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton btnSelectPath = new JButton(Messages.getString("HotKeysPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
								logger.error(e1.toString()); //$NON-NLS-1$
								JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage(), "Hot Keys Error", 1); //$NON-NLS-1$
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					try {
						settings.setHotKeyScriptPath(formattedTxtPath.getText());
						settings.saveHotKeyConfig();;
					} catch (IOException ex) {
						logger.error(ex.toString());
						JOptionPane.showMessageDialog(null, Messages.getString("HotKeysPanel.ErrorSavingPropertiesFile") + ex.getMessage(), "Hot Keys Error", 1); //$NON-NLS-1$
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$
		formattedTxtPath = new JFormattedTextField();
		formattedTxtPath.setText(settings.getHotKeyScriptPath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setHotKeyScriptPath(formattedTxtPath.getText());
					settings.saveHotKeyConfig();
		    	} catch (IOException ex) {
		    		logger.error(ex.toString());
		    		JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), "Hot Keys Error", 1);		 //$NON-NLS-1$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						settings.setHotKeyScriptPath(formattedTxtPath.getText());
						settings.saveHotKeyConfig();
			    	} catch (IOException ex) {
			    		logger.error(ex.toString());
			    		JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), "Hot Keys Error", 1);		 //$NON-NLS-1$
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

		JLabel lblTeam1SwitchPositionsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchPositions", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsHotKey, "cell 1 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1SwitchPositionsHotKey = new JTextField();
		txtTeam1SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1SwitchPositionsHotKey.setText(settings.getTeamSwitchPositionsHotKey("1"));
		txtTeam1SwitchPositionsHotKey.setColumns(10);
		add(txtTeam1SwitchPositionsHotKey, "cell 2 3,alignx left"); //$NON-NLS-1$
		
		txtTeam2SwitchPositionsHotKey = new JTextField();
		txtTeam2SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2SwitchPositionsHotKey.setText(settings.getTeamSwitchPositionsHotKey("2"));
		txtTeam2SwitchPositionsHotKey.setColumns(10);
		add(txtTeam2SwitchPositionsHotKey, "cell 3 3,alignx left"); //$NON-NLS-1$
		
		txtTeam3SwitchPositionsHotKey = new JTextField();
		txtTeam3SwitchPositionsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam3SwitchPositionsHotKey.setText(settings.getTeamSwitchPositionsHotKey("3"));
		txtTeam3SwitchPositionsHotKey.setColumns(10);
		add(txtTeam3SwitchPositionsHotKey, "cell 4 3,alignx left"); //$NON-NLS-1$

		JLabel lblScore1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementScore", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1PlusHotKey, "cell 1 4,alignx right"); //$NON-NLS-1$
		
		txtScore1PlusHotKey = new JTextField();
		txtScore1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1PlusHotKey.setText(settings.getScorePlusHotKey("1"));
		txtScore1PlusHotKey.setColumns(10);
		add(txtScore1PlusHotKey, "cell 2 4,alignx left"); //$NON-NLS-1$
		
		txtScore2PlusHotKey = new JTextField();
		txtScore2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2PlusHotKey.setText(settings.getScorePlusHotKey("2"));
		txtScore2PlusHotKey.setColumns(10);
		add(txtScore2PlusHotKey, "cell 3 4,alignx left"); //$NON-NLS-1$
		
		txtScore3PlusHotKey = new JTextField();
		txtScore3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore3PlusHotKey.setText(settings.getScorePlusHotKey("3"));
		txtScore3PlusHotKey.setColumns(10);
		add(txtScore3PlusHotKey, "cell 4 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblScore1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementScore", settings.getGameType())); //$NON-NLS-1$
		add(lblScore1MinusHotKey, "cell 1 5,alignx right"); //$NON-NLS-1$
		
		txtScore1MinusHotKey = new JTextField();
		txtScore1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore1MinusHotKey.setText(settings.getScoreMinusHotKey("1"));
		txtScore1MinusHotKey.setColumns(10);
		add(txtScore1MinusHotKey, "cell 2 5,alignx left"); //$NON-NLS-1$
		
		txtScore2MinusHotKey = new JTextField();
		txtScore2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore2MinusHotKey.setText(settings.getScoreMinusHotKey("2"));
		txtScore2MinusHotKey.setColumns(10);
		add(txtScore2MinusHotKey, "cell 3 5,alignx left"); //$NON-NLS-1$
		
		txtScore3MinusHotKey = new JTextField();
		txtScore3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtScore3MinusHotKey.setText(settings.getScoreMinusHotKey("3"));
		txtScore3MinusHotKey.setColumns(10);
		add(txtScore3MinusHotKey, "cell 4 5,alignx left"); //$NON-NLS-1$
		
		JLabel lblGameCount1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementGameCount", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount1PlusHotKey, "cell 1 6,alignx right"); //$NON-NLS-1$
		
		txtGameCount1PlusHotKey = new JTextField();
		txtGameCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1PlusHotKey.setText(settings.getGameCountPlusHotKey("1"));
		txtGameCount1PlusHotKey.setColumns(10);
		add(txtGameCount1PlusHotKey, "cell 2 6,alignx left"); //$NON-NLS-1$
		
		txtGameCount2PlusHotKey = new JTextField();
		txtGameCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2PlusHotKey.setText(settings.getGameCountPlusHotKey("2"));
		txtGameCount2PlusHotKey.setColumns(10);
		add(txtGameCount2PlusHotKey, "cell 3 6,alignx left"); //$NON-NLS-1$
		
		txtGameCount3PlusHotKey = new JTextField();
		txtGameCount3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount3PlusHotKey.setText(settings.getGameCountPlusHotKey("3"));
		txtGameCount3PlusHotKey.setColumns(10);
		add(txtGameCount3PlusHotKey, "cell 4 6,alignx left"); //$NON-NLS-1$
		
		JLabel lblGameCount1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementGameCount", settings.getGameType())); //$NON-NLS-1$
		add(lblGameCount1MinusHotKey, "cell 1 7,alignx right"); //$NON-NLS-1$
		
		txtGameCount1MinusHotKey = new JTextField();
		txtGameCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount1MinusHotKey.setText(settings.getGameCountMinusHotKey("1"));
		txtGameCount1MinusHotKey.setColumns(10);
		add(txtGameCount1MinusHotKey, "cell 2 7,alignx left"); //$NON-NLS-1$
		
		txtGameCount2MinusHotKey = new JTextField();
		txtGameCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount2MinusHotKey.setText(settings.getGameCountMinusHotKey("2"));
		txtGameCount2MinusHotKey.setColumns(10);
		add(txtGameCount2MinusHotKey, "cell 3 7,alignx left"); //$NON-NLS-1$
		
		txtGameCount3MinusHotKey = new JTextField();
		txtGameCount3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameCount3MinusHotKey.setText(settings.getGameCountMinusHotKey("3"));
		txtGameCount3MinusHotKey.setColumns(10);
		add(txtGameCount3MinusHotKey, "cell 4 7,alignx left"); //$NON-NLS-1$
		
		JLabel lblMatchCount1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.IncrementMatchCount", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchCount1PlusHotKey, "cell 1 8,alignx right"); //$NON-NLS-1$
		
		txtMatchCount1PlusHotKey = new JTextField();
		txtMatchCount1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount1PlusHotKey.setText(settings.getMatchCountPlusHotKey("1"));
		txtMatchCount1PlusHotKey.setColumns(10);
		add(txtMatchCount1PlusHotKey, "cell 2 8,alignx left"); //$NON-NLS-1$
		
		txtMatchCount2PlusHotKey = new JTextField();
		txtMatchCount2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount2PlusHotKey.setText(settings.getMatchCountPlusHotKey("2"));
		txtMatchCount2PlusHotKey.setColumns(10);
		add(txtMatchCount2PlusHotKey, "cell 3 8,alignx left"); //$NON-NLS-1$
		
		txtMatchCount3PlusHotKey = new JTextField();
		txtMatchCount3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount3PlusHotKey.setText(settings.getMatchCountPlusHotKey("3"));
		txtMatchCount3PlusHotKey.setColumns(10);
		add(txtMatchCount3PlusHotKey, "cell 4 8,alignx left"); //$NON-NLS-1$
		
		JLabel lblMatchCount1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.DecrementMatchCount", settings.getGameType())); //$NON-NLS-1$
		add(lblMatchCount1MinusHotKey, "cell 1 9,alignx right"); //$NON-NLS-1$
		
		txtMatchCount1MinusHotKey = new JTextField();
		txtMatchCount1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount1MinusHotKey.setText(settings.getMatchCountMinusHotKey("1"));
		txtMatchCount1MinusHotKey.setColumns(10);
		add(txtMatchCount1MinusHotKey, "cell 2 9,alignx left"); //$NON-NLS-1$
		
		txtMatchCount2MinusHotKey = new JTextField();
		txtMatchCount2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount2MinusHotKey.setText(settings.getMatchCountMinusHotKey("2"));
		txtMatchCount2MinusHotKey.setColumns(10);
		add(txtMatchCount2MinusHotKey, "cell 3 9,alignx left"); //$NON-NLS-1$
		
		txtMatchCount3MinusHotKey = new JTextField();
		txtMatchCount3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatchCount3MinusHotKey.setText(settings.getMatchCountMinusHotKey("3"));
		txtMatchCount3MinusHotKey.setColumns(10);
		add(txtMatchCount3MinusHotKey, "cell 4 9,alignx left"); //$NON-NLS-1$
		
		JLabel lblTimeOut1PlusHotKey = new JLabel(Messages.getString("HotKeysPanel.UseTimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1PlusHotKey, "cell 1 10,alignx right"); //$NON-NLS-1$
		
		txtTimeOut1PlusHotKey = new JTextField();
		txtTimeOut1PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1PlusHotKey.setText(settings.getTimeOutPlusHotKey("1"));
		txtTimeOut1PlusHotKey.setColumns(10);
		add(txtTimeOut1PlusHotKey, "cell 2 10,alignx left"); //$NON-NLS-1$
		
		txtTimeOut2PlusHotKey = new JTextField();
		txtTimeOut2PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2PlusHotKey.setText(settings.getTimeOutPlusHotKey("2"));
		txtTimeOut2PlusHotKey.setColumns(10);
		add(txtTimeOut2PlusHotKey, "cell 3 10,alignx left"); //$NON-NLS-1$
		
		txtTimeOut3PlusHotKey = new JTextField();
		txtTimeOut3PlusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut3PlusHotKey.setText(settings.getTimeOutPlusHotKey("3"));
		txtTimeOut3PlusHotKey.setColumns(10);
		add(txtTimeOut3PlusHotKey, "cell 4 10,alignx left"); //$NON-NLS-1$
		
		JLabel lblTimeOut1MinusHotKey = new JLabel(Messages.getString("HotKeysPanel.ReturnTimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOut1MinusHotKey, "cell 1 11,alignx right"); //$NON-NLS-1$
		
		txtTimeOut1MinusHotKey = new JTextField();
		txtTimeOut1MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut1MinusHotKey.setText(settings.getTimeOutMinusHotKey("1"));
		txtTimeOut1MinusHotKey.setColumns(10);
		add(txtTimeOut1MinusHotKey, "cell 2 11,alignx left"); //$NON-NLS-1$
		
		txtTimeOut2MinusHotKey = new JTextField();
		txtTimeOut2MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut2MinusHotKey.setText(settings.getTimeOutMinusHotKey("2"));
		txtTimeOut2MinusHotKey.setColumns(10);
		add(txtTimeOut2MinusHotKey, "cell 3 11,alignx left"); //$NON-NLS-1$
		
		txtTimeOut3MinusHotKey = new JTextField();
		txtTimeOut3MinusHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOut3MinusHotKey.setText(settings.getTimeOutMinusHotKey("3"));
		txtTimeOut3MinusHotKey.setColumns(10);
		add(txtTimeOut3MinusHotKey, "cell 4 11,alignx left"); //$NON-NLS-1$
		
		JLabel lblReset1HotKey = new JLabel(Messages.getString("HotKeysPanel.Reset", settings.getGameType())); //$NON-NLS-1$
		add(lblReset1HotKey, "cell 1 12,alignx right"); //$NON-NLS-1$
		
		txtReset1HotKey = new JTextField();
		txtReset1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset1HotKey.setText(settings.getResetHotKey("1"));
		txtReset1HotKey.setColumns(10);
		add(txtReset1HotKey, "cell 2 12,alignx left"); //$NON-NLS-1$
		
		txtReset2HotKey = new JTextField();
		txtReset2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset2HotKey.setText(settings.getResetHotKey("2"));
		txtReset2HotKey.setColumns(10);
		add(txtReset2HotKey, "cell 3 12,alignx left"); //$NON-NLS-1$
		
		txtReset3HotKey = new JTextField();
		txtReset3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtReset3HotKey.setText(settings.getResetHotKey("3"));
		txtReset3HotKey.setColumns(10);
		add(txtReset3HotKey, "cell 4 12,alignx left"); //$NON-NLS-1$
		
		JLabel lblWarn1HotKey = new JLabel(Messages.getString("HotKeysPanel.Warn", settings.getGameType())); //$NON-NLS-1$
		add(lblWarn1HotKey, "cell 1 13,alignx right"); //$NON-NLS-1$
		
		txtWarn1HotKey = new JTextField();
		txtWarn1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn1HotKey.setText(settings.getWarnHotKey("1"));
		add(txtWarn1HotKey, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtWarn1HotKey.setColumns(10);
		
		txtWarn2HotKey = new JTextField();
		txtWarn2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn2HotKey.setText(settings.getWarnHotKey("2"));
		txtWarn2HotKey.setColumns(10);
		add(txtWarn2HotKey, "cell 3 13,alignx left"); //$NON-NLS-1$
		
		txtWarn3HotKey = new JTextField();
		txtWarn3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtWarn3HotKey.setText(settings.getWarnHotKey("3"));
		txtWarn3HotKey.setColumns(10);
		add(txtWarn3HotKey, "cell 4 13,alignx left"); //$NON-NLS-1$
		
		JLabel lblKingSeat1HotKey = new JLabel(Messages.getString("HotKeysPanel.KingSeat", settings.getGameType())); //$NON-NLS-1$
		add(lblKingSeat1HotKey, "cell 1 14,alignx right"); //$NON-NLS-1$
		
		txtKingSeat1HotKey = new JTextField();
		txtKingSeat1HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat1HotKey.setText(settings.getKingSeatHotKey("1"));
		txtKingSeat1HotKey.setColumns(10);
		add(txtKingSeat1HotKey, "cell 2 14,alignx left"); //$NON-NLS-1$
		
		txtKingSeat2HotKey = new JTextField();
		txtKingSeat2HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat2HotKey.setText(settings.getKingSeatHotKey("2"));
		txtKingSeat2HotKey.setColumns(10);
		add(txtKingSeat2HotKey, "cell 3 14,alignx left"); //$NON-NLS-1$
		
		txtKingSeat3HotKey = new JTextField();
		txtKingSeat3HotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKingSeat3HotKey.setText(settings.getKingSeatHotKey("3"));
		txtKingSeat3HotKey.setColumns(10);
		add(txtKingSeat3HotKey, "cell 4 14,alignx left"); //$NON-NLS-1$
	
		JLabel lblStartMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.StartMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblStartMatchHotKey, "cell 5 3,alignx right"); //$NON-NLS-1$
		
		txtStartMatchHotKey = new JTextField();
		txtStartMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartMatchHotKey.setText(settings.getStartMatchHotKey());
		txtStartMatchHotKey.setColumns(10);
		add(txtStartMatchHotKey, "cell 6 3,alignx left"); //$NON-NLS-1$
		
		JLabel lblPauseMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.PauseMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblPauseMatchHotKey, "cell 5 4,alignx right"); //$NON-NLS-1$
		
		txtPauseMatchHotKey = new JTextField();
		txtPauseMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPauseMatchHotKey.setText(settings.getPauseMatchHotKey());
		txtPauseMatchHotKey.setColumns(10);
		add(txtPauseMatchHotKey, "cell 6 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblEndMatchHotKey = new JLabel(Messages.getString("HotKeysPanel.EndMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblEndMatchHotKey, "cell 5 5,alignx right"); //$NON-NLS-1$
		
		txtEndMatchHotKey = new JTextField();
		txtEndMatchHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndMatchHotKey.setText(settings.getEndMatchHotKey());
		txtEndMatchHotKey.setColumns(10);
		add(txtEndMatchHotKey, "cell 6 5,alignx left"); //$NON-NLS-1$
		
		JLabel lblStartGameHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGame", settings.getGameType())); //$NON-NLS-1$
		add(lblStartGameHotKey, "cell 5 6,alignx right"); //$NON-NLS-1$
		
		txtStartGameHotKey = new JTextField();
		txtStartGameHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartGameHotKey.setText(settings.getStartGameHotKey());
		txtStartGameHotKey.setColumns(10);
		add(txtStartGameHotKey, "cell 6 6,alignx left,aligny top"); //$NON-NLS-1$
		
		JLabel lblShotTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartShotTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblShotTimerHotKey, "cell 5 7,alignx right"); //$NON-NLS-1$
		
		txtShotTimerHotKey = new JTextField();
		txtShotTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShotTimerHotKey.setText(settings.getShotTimerHotKey());
		txtShotTimerHotKey.setColumns(10);
		add(txtShotTimerHotKey, "cell 6 7,alignx left"); //$NON-NLS-1$
		
		JLabel lblPassTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartPassTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblPassTimerHotKey, "cell 5 8,alignx right"); //$NON-NLS-1$
		
		txtPassTimerHotKey = new JTextField();
		txtPassTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassTimerHotKey.setText(settings.getPassTimerHotKey());
		txtPassTimerHotKey.setColumns(10);
		add(txtPassTimerHotKey, "cell 6 8,alignx left"); //$NON-NLS-1$
		
		JLabel lblTimeOutTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartTimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOutTimerHotKey, "cell 5 9,alignx right"); //$NON-NLS-1$
		
		txtTimeOutTimerHotKey = new JTextField();
		txtTimeOutTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimeOutTimerHotKey.setText(settings.getTimeOutTimerHotKey());
		txtTimeOutTimerHotKey.setColumns(10);
		add(txtTimeOutTimerHotKey, "cell 6 9,alignx left"); //$NON-NLS-1$
		
		JLabel lblGameTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartGameTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTimerHotKey, "cell 5 10,alignx right"); //$NON-NLS-1$
		
		txtGameTimerHotKey = new JTextField();
		txtGameTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameTimerHotKey.setText(settings.getGameTimerHotKey());
		txtGameTimerHotKey.setColumns(10);
		add(txtGameTimerHotKey, "cell 6 10,alignx left"); //$NON-NLS-1$
		
		JLabel lblRecallTimerHotKey = new JLabel(Messages.getString("HotKeysPanel.StartRecallTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblRecallTimerHotKey, "cell 5 11,alignx right"); //$NON-NLS-1$
		
		txtRecallTimerHotKey = new JTextField();
		txtRecallTimerHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecallTimerHotKey.setText(settings.getRecallTimerHotKey());
		txtRecallTimerHotKey.setColumns(10);
		add(txtRecallTimerHotKey, "cell 6 11,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetTimersHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimer", settings.getGameType())); //$NON-NLS-1$
		add(lblResetTimersHotKey, "cell 5 12,alignx right"); //$NON-NLS-1$
		
		txtResetTimersHotKey = new JTextField();
		txtResetTimersHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimersHotKey.setText(settings.getResetTimersHotKey());
		txtResetTimersHotKey.setColumns(10);
		add(txtResetTimersHotKey, "cell 6 12,alignx left"); //$NON-NLS-1$

		JLabel lblClearAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ClearAll", settings.getGameType())); //$NON-NLS-1$
		add(lblClearAllHotKey, "cell 5 13,alignx right"); //$NON-NLS-1$
		
		txtClearAllHotKey = new JTextField();
		txtClearAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtClearAllHotKey.setText(settings.getClearAllHotKey());
		txtClearAllHotKey.setColumns(10);
		add(txtClearAllHotKey, "cell 6 13,alignx left"); //$NON-NLS-1$
		
		JLabel lblUndoHotKey = new JLabel(Messages.getString("HotKeysPanel.Undo", settings.getGameType())); //$NON-NLS-1$
		add(lblUndoHotKey, "cell 5 14,alignx right"); //$NON-NLS-1$
		
		txtUndoHotKey = new JTextField();
		txtUndoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtUndoHotKey.setText(settings.getUndoHotKey());
		txtUndoHotKey.setColumns(10);
		add(txtUndoHotKey, "cell 6 14,alignx left"); //$NON-NLS-1$
		
		JLabel lblRedoHotKey = new JLabel(Messages.getString("HotKeysPanel.Redo", settings.getGameType())); //$NON-NLS-1$
		add(lblRedoHotKey, "cell 5 15,alignx right"); //$NON-NLS-1$
		
		txtRedoHotKey = new JTextField();
		txtRedoHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtRedoHotKey.setText(settings.getRedoHotKey());
		txtRedoHotKey.setColumns(10);
		add(txtRedoHotKey, "cell 6 15,alignx left"); //$NON-NLS-1$
		
		JLabel lblShowSkunkHotKey = new JLabel(Messages.getString("HotKeysPanel.ShowSkunk", settings.getGameType())); //$NON-NLS-1$
		add(lblShowSkunkHotKey, "cell 5 16,alignx right"); //$NON-NLS-1$

		txtShowSkunkHotKey = new JTextField();
		txtShowSkunkHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowSkunkHotKey.setText(settings.getShowSkunkHotKey());
		txtShowSkunkHotKey.setColumns(10);
		add(txtShowSkunkHotKey, "cell 6 16,alignx left"); //$NON-NLS-1$

		JLabel lblStartStreamHotKey = new JLabel(Messages.getString("HotKeysPanel.StartStream", settings.getGameType())); //$NON-NLS-1$
		add(lblStartStreamHotKey, "cell 5 17,alignx right"); //$NON-NLS-1$

		txtStartStreamHotKey = new JTextField();
		txtStartStreamHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartStreamHotKey.setText(settings.getStartStreamHotKey());
		txtStartStreamHotKey.setColumns(10);
		add(txtStartStreamHotKey, "cell 6 17,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchMatchCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchMatchCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchMatchCountsHotKey, "cell 7 3,alignx right"); //$NON-NLS-1$

		txtSwitchMatchCountsHotKey = new JTextField();
		txtSwitchMatchCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchMatchCountsHotKey.setText(settings.getSwitchMatchCountsHotKey());
		txtSwitchMatchCountsHotKey.setColumns(10);
		add(txtSwitchMatchCountsHotKey, "cell 8 3,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchSidesHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchSides", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchSidesHotKey, "cell 7 4,alignx right"); //$NON-NLS-1$
		
		txtSwitchSidesHotKey = new JTextField();
		txtSwitchSidesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchSidesHotKey.setText(settings.getSwitchSidesHotKey());
		txtSwitchSidesHotKey.setColumns(10);
		add(txtSwitchSidesHotKey, "cell 8 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchTeamsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTeams", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchTeamsHotKey, "cell 7 5,alignx right"); //$NON-NLS-1$
		
		txtSwitchTeamsHotKey = new JTextField();
		txtSwitchTeamsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTeamsHotKey.setText(settings.getSwitchTeamsHotKey());
		txtSwitchTeamsHotKey.setColumns(10);
		add(txtSwitchTeamsHotKey, "cell 8 5,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchScores", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchScoresHotKey, "cell 7 6,alignx right"); //$NON-NLS-1$
		
		txtSwitchScoresHotKey = new JTextField();
		txtSwitchScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchScoresHotKey.setText(settings.getSwitchScoresHotKey());
		txtSwitchScoresHotKey.setColumns(10);
		add(txtSwitchScoresHotKey, "cell 8 6,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchGameCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchGameCountsHotKey, "cell 7 7,alignx right"); //$NON-NLS-1$
		
		txtSwitchGameCountsHotKey = new JTextField();
		txtSwitchGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchGameCountsHotKey.setText(settings.getSwitchGameCountsHotKey());
		txtSwitchGameCountsHotKey.setColumns(10);
		add(txtSwitchGameCountsHotKey, "cell 8 7,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchTimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchTimeOutsHotKey, "cell 7 8,alignx right"); //$NON-NLS-1$
		
		txtSwitchTimeOutsHotKey = new JTextField();
		txtSwitchTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchTimeOutsHotKey.setText(settings.getSwitchTimeOutsHotKey());
		txtSwitchTimeOutsHotKey.setColumns(10);
		add(txtSwitchTimeOutsHotKey, "cell 8 8,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchResetWarnsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchResetWarns", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchResetWarnsHotKey, "cell 7 9,alignx right"); //$NON-NLS-1$
		
		txtSwitchResetWarnsHotKey = new JTextField();
		txtSwitchResetWarnsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchResetWarnsHotKey.setText(settings.getSwitchResetWarnsHotKey());
		txtSwitchResetWarnsHotKey.setColumns(10);
		add(txtSwitchResetWarnsHotKey, "cell 8 9,alignx left"); //$NON-NLS-1$

		JLabel lblSwitchForwardsHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchForwards", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchForwardsHotKey, "cell 7 10,alignx right"); //$NON-NLS-1$
		                            
		txtSwitchForwardsHotKey = new JTextField();
		txtSwitchForwardsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchForwardsHotKey.setText(settings.getSwitchForwardsHotKey());
		txtSwitchForwardsHotKey.setColumns(10);
		add(txtSwitchForwardsHotKey, "cell 8 10,alignx left"); //$NON-NLS-1$
		
		JLabel lblSwitchGoaliesHotKey = new JLabel(Messages.getString("HotKeysPanel.SwitchGoalies", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchGoaliesHotKey, "cell 7 11,alignx right"); //$NON-NLS-1$

		txtSwitchGoaliesHotKey = new JTextField();
		txtSwitchGoaliesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchGoaliesHotKey.setText(settings.getSwitchGoaliesHotKey());
		txtSwitchGoaliesHotKey.setColumns(10);
		add(txtSwitchGoaliesHotKey, "cell 8 11,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetNamesHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetNames", settings.getGameType())); //$NON-NLS-1$
		add(lblResetNamesHotKey, "cell 7 12,alignx right"); //$NON-NLS-1$
		
		txtResetNamesHotKey = new JTextField();
		txtResetNamesHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetNamesHotKey.setText(settings.getResetNamesHotKey());
		txtResetNamesHotKey.setColumns(10);
		add(txtResetNamesHotKey, "cell 8 12,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetScoresHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetScores", settings.getGameType())); //$NON-NLS-1$
		add(lblResetScoresHotKey, "cell 7 13,alignx right"); //$NON-NLS-1$
		
		txtResetScoresHotKey = new JTextField();
		txtResetScoresHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetScoresHotKey.setText(settings.getResetScoresHotKey());
		txtResetScoresHotKey.setColumns(10);
		add(txtResetScoresHotKey, "cell 8 13,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetGameCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetGameCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblResetGameCountsHotKey, "cell 7 14,alignx right"); //$NON-NLS-1$
		
		txtResetGameCountsHotKey = new JTextField();
		txtResetGameCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetGameCountsHotKey.setText(settings.getResetGameCountsHotKey());
		txtResetGameCountsHotKey.setColumns(10);
		add(txtResetGameCountsHotKey, "cell 8 14,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetTimeOutsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetTimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblResetTimeOutsHotKey, "cell 7 15,alignx right"); //$NON-NLS-1$
		
		txtResetTimeOutsHotKey = new JTextField();
		txtResetTimeOutsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetTimeOutsHotKey.setText(settings.getResetTimeOutsHotKey());
		txtResetTimeOutsHotKey.setColumns(10);
		add(txtResetTimeOutsHotKey, "cell 8 15,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetResetWarnHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetResetWarn", settings.getGameType())); //$NON-NLS-1$
		add(lblResetResetWarnHotKey, "cell 7 16,alignx right"); //$NON-NLS-1$
		
		txtResetResetWarnHotKey = new JTextField();
		txtResetResetWarnHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetResetWarnHotKey.setText(settings.getResetResetWarnHotKey());
		txtResetResetWarnHotKey.setColumns(10);
		add(txtResetResetWarnHotKey, "cell 8 16,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetAllHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetAll", settings.getGameType())); //$NON-NLS-1$
		add(lblResetAllHotKey, "cell 7 17,alignx right"); //$NON-NLS-1$
		
		txtResetAllHotKey = new JTextField();
		txtResetAllHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetAllHotKey.setText(settings.getResetAllHotKey());
		txtResetAllHotKey.setColumns(10);
		add(txtResetAllHotKey, "cell 8 17,alignx left"); //$NON-NLS-1$
		
		JLabel lblResetMatchCountsHotKey = new JLabel(Messages.getString("HotKeysPanel.ResetMatchCounts", settings.getGameType())); //$NON-NLS-1$
		add(lblResetMatchCountsHotKey, "cell 7 18,alignx right"); //$NON-NLS-1$

		txtResetMatchCountsHotKey = new JTextField();
		txtResetMatchCountsHotKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetMatchCountsHotKey.setText(settings.getResetMatchCountsHotKey());
		txtResetMatchCountsHotKey.setColumns(10);
		add(txtResetMatchCountsHotKey, "cell 8 18,alignx left"); //$NON-NLS-1$
		
		btnGenerateHotKeyScripts = new JButton(Messages.getString("HotKeysPanel.GenerateHotKeyScripts", settings.getGameType())); //$NON-NLS-1$
		btnGenerateHotKeyScripts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.generateHotKeyScripts();
			}
		});
		add(btnGenerateHotKeyScripts, "cell 1 20, spanx 2, alignx center"); //$NON-NLS-1$
	
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 4 20,alignx center"); //$NON-NLS-1$
		
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 6 20,alignx center"); //$NON-NLS-1$
		
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 8 20, spanx 2, alignx right"); //$NON-NLS-1$
	}

	private void restoreDefaults(Settings settings) {
		formattedTxtPath.setText(settings.getDefaultHotKeyScriptPath());
		txtStartMatchHotKey.setText(settings.getDefaultStartMatchHotKey());
		txtPauseMatchHotKey.setText(settings.getDefaultPauseMatchHotKey());
		txtStartGameHotKey.setText(settings.getDefaultStartGameHotKey());
		txtTeam1SwitchPositionsHotKey.setText(settings.getDefaultTeamSwitchPositionsHotKey("1"));
		txtTeam2SwitchPositionsHotKey.setText(settings.getDefaultTeamSwitchPositionsHotKey("2"));
		txtTeam3SwitchPositionsHotKey.setText(settings.getDefaultTeamSwitchPositionsHotKey("3"));
		txtSwitchTeamsHotKey.setText(settings.getDefaultSwitchTeamsHotKey());
		txtGameCount1MinusHotKey.setText(settings.getDefaultGameCountMinusHotKey("1"));
		txtGameCount1PlusHotKey.setText(settings.getDefaultGameCountPlusHotKey("1"));
		txtGameCount2MinusHotKey.setText(settings.getDefaultGameCountMinusHotKey("2"));
		txtGameCount2PlusHotKey.setText(settings.getDefaultGameCountPlusHotKey("2"));
		txtGameCount3MinusHotKey.setText(settings.getDefaultGameCountMinusHotKey("3"));
		txtGameCount3PlusHotKey.setText(settings.getDefaultGameCountPlusHotKey("3"));
		txtMatchCount1MinusHotKey.setText(settings.getDefaultMatchCountMinusHotKey("1"));
		txtMatchCount1PlusHotKey.setText(settings.getDefaultMatchCountPlusHotKey("1"));
		txtMatchCount2MinusHotKey.setText(settings.getDefaultMatchCountMinusHotKey("2"));
		txtMatchCount2PlusHotKey.setText(settings.getDefaultMatchCountPlusHotKey("2"));
		txtMatchCount3MinusHotKey.setText(settings.getDefaultMatchCountMinusHotKey("3"));
		txtMatchCount3PlusHotKey.setText(settings.getDefaultMatchCountPlusHotKey("3"));
		txtSwitchGameCountsHotKey.setText(settings.getDefaultSwitchGameCountsHotKey());
		txtSwitchMatchCountsHotKey.setText(settings.getDefaultSwitchMatchCountsHotKey());
		txtScore1MinusHotKey.setText(settings.getDefaultScoreMinusHotKey("1"));
		txtScore1PlusHotKey.setText(settings.getDefaultScorePlusHotKey("1"));
		txtScore2MinusHotKey.setText(settings.getDefaultScoreMinusHotKey("2"));
		txtScore2PlusHotKey.setText(settings.getDefaultScorePlusHotKey("2"));
		txtScore3MinusHotKey.setText(settings.getDefaultScoreMinusHotKey("3"));
		txtScore3PlusHotKey.setText(settings.getDefaultScorePlusHotKey("3"));
		txtSwitchScoresHotKey.setText(settings.getDefaultSwitchScoresHotKey());
		txtTimeOut1MinusHotKey.setText(settings.getDefaultTimeOutMinusHotKey("1"));
		txtTimeOut1PlusHotKey.setText(settings.getDefaultTimeOutPlusHotKey("1"));
		txtTimeOut2MinusHotKey.setText(settings.getDefaultTimeOutMinusHotKey("2"));
		txtTimeOut2PlusHotKey.setText(settings.getDefaultTimeOutPlusHotKey("2"));
		txtTimeOut3MinusHotKey.setText(settings.getDefaultTimeOutMinusHotKey("3"));
		txtTimeOut3PlusHotKey.setText(settings.getDefaultTimeOutPlusHotKey("3"));
		txtSwitchTimeOutsHotKey.setText(settings.getDefaultSwitchTimeOutsHotKey());
		txtReset1HotKey.setText(settings.getDefaultResetHotKey("1"));
		txtReset2HotKey.setText(settings.getDefaultResetHotKey("2"));
		txtReset3HotKey.setText(settings.getDefaultResetHotKey("3"));
		txtWarn1HotKey.setText(settings.getDefaultWarnHotKey("1"));
		txtWarn2HotKey.setText(settings.getDefaultWarnHotKey("2"));
		txtWarn3HotKey.setText(settings.getDefaultWarnHotKey("3"));
		txtKingSeat1HotKey.setText(settings.getDefaultKingSeatHotKey("1"));
		txtKingSeat2HotKey.setText(settings.getDefaultKingSeatHotKey("2"));
		txtKingSeat3HotKey.setText(settings.getDefaultKingSeatHotKey("3"));
		txtSwitchResetWarnsHotKey.setText(settings.getDefaultSwitchResetWarnsHotKey());
		txtSwitchSidesHotKey.setText(settings.getDefaultSwitchPositionsHotKey());
		txtResetNamesHotKey.setText(settings.getDefaultResetNamesHotKey());
		txtResetGameCountsHotKey.setText(settings.getDefaultResetGameCountsHotKey());
		txtResetMatchCountsHotKey.setText(settings.getDefaultResetMatchCountsHotKey());
		txtResetScoresHotKey.setText(settings.getDefaultResetScoresHotKey());
		txtResetTimeOutsHotKey.setText(settings.getDefaultResetTimeOutsHotKey());
		txtResetResetWarnHotKey.setText(settings.getDefaultResetResetWarnHotKey());
		txtResetAllHotKey.setText(settings.getDefaultResetAllHotKey());
		txtClearAllHotKey.setText(settings.getDefaultClearAllHotKey());
		txtShotTimerHotKey.setText(settings.getDefaultShotTimerHotKey());
		txtPassTimerHotKey.setText(settings.getDefaultPassTimerHotKey());
		txtTimeOutTimerHotKey.setText(settings.getDefaultTimeOutTimerHotKey());
		txtGameTimerHotKey.setText(settings.getDefaultGameTimerHotKey());
		txtRecallTimerHotKey.setText(settings.getDefaultRecallTimerHotKey());
		txtResetTimersHotKey.setText(settings.getDefaultResetTimersHotKey());
		txtUndoHotKey.setText(settings.getDefaultUndoHotKey());
		txtRedoHotKey.setText(settings.getDefaultRedoHotKey());
		txtSwitchForwardsHotKey.setText(settings.getDefaultSwitchForwardsHotKey());
		txtSwitchGoaliesHotKey.setText(settings.getDefaultSwitchGoaliesHotKey());
		txtShowSkunkHotKey.setText(settings.getDefaultShowSkunkHotKey());
		txtStartStreamHotKey.setText(settings.getDefaultStartStreamHotKey());
		hotKeyBaseScriptText = settings.getDefaultHotKeyBaseScript();
	}
	
	public boolean saveSettings(Settings settings) {
		boolean okToCloseWindow = false;
		settings.setHotKeyScriptPath(formattedTxtPath.toString());
		if(checkForUniqueHotKeys()) {
			settings.setHotKey("StartMatchHotKey",txtStartMatchHotKey.getText());
			settings.setHotKey("PauseMatchHotKey",txtPauseMatchHotKey.getText());
			settings.setHotKey("EndMatchHotKey",txtEndMatchHotKey.getText());
			settings.setHotKey("StartGameHotKey",txtStartGameHotKey.getText());
			settings.setHotKey("Team1SwitchPositionsHotKey",txtTeam1SwitchPositionsHotKey.getText());
			settings.setHotKey("Team2SwitchPositionsHotKey",txtTeam2SwitchPositionsHotKey.getText());
			settings.setHotKey("Team3SwitchPositionsHotKey",txtTeam3SwitchPositionsHotKey.getText());
			settings.setHotKey("SwitchTeamsHotKey",txtSwitchTeamsHotKey.getText());
			settings.setHotKey("GameCount1MinusHotKey",txtGameCount1MinusHotKey.getText());
			settings.setHotKey("GameCount1PlusHotKey",txtGameCount1PlusHotKey.getText());
			settings.setHotKey("GameCount2MinusHotKey",txtGameCount2MinusHotKey.getText());
			settings.setHotKey("GameCount2PlusHotKey",txtGameCount2PlusHotKey.getText());
			settings.setHotKey("GameCount3MinusHotKey",txtGameCount3MinusHotKey.getText());
			settings.setHotKey("GameCount3PlusHotKey",txtGameCount3PlusHotKey.getText());
			settings.setHotKey("MatchCount1MinusHotKey",txtMatchCount1MinusHotKey.getText());
			settings.setHotKey("MatchCount1PlusHotKey",txtMatchCount1PlusHotKey.getText());
			settings.setHotKey("MatchCount2MinusHotKey",txtMatchCount2MinusHotKey.getText());
			settings.setHotKey("MatchCount2PlusHotKey",txtMatchCount2PlusHotKey.getText());
			settings.setHotKey("MatchCount3MinusHotKey",txtMatchCount3MinusHotKey.getText());
			settings.setHotKey("MatchCount3PlusHotKey",txtMatchCount3PlusHotKey.getText());
			settings.setHotKey("SwitchGameCountsHotKey",txtSwitchGameCountsHotKey.getText());
			settings.setHotKey("SwitchMatchCountsHotKey",txtSwitchMatchCountsHotKey.getText());
			settings.setHotKey("Score1MinusHotKey",txtScore1MinusHotKey.getText());
			settings.setHotKey("Score1PlusHotKey",txtScore1PlusHotKey.getText());
			settings.setHotKey("Score2MinusHotKey",txtScore2MinusHotKey.getText());
			settings.setHotKey("Score2PlusHotKey",txtScore2PlusHotKey.getText());
			settings.setHotKey("Score3MinusHotKey",txtScore3MinusHotKey.getText());
			settings.setHotKey("Score3PlusHotKey",txtScore3PlusHotKey.getText());
			settings.setHotKey("SwitchScoresHotKey",txtSwitchScoresHotKey.getText());
			settings.setHotKey("TimeOut1MinusHotKey",txtTimeOut1MinusHotKey.getText());
			settings.setHotKey("TimeOut1PlusHotKey",txtTimeOut1PlusHotKey.getText());
			settings.setHotKey("TimeOut2MinusHotKey",txtTimeOut2MinusHotKey.getText());
			settings.setHotKey("TimeOut2PlusHotKey",txtTimeOut2PlusHotKey.getText());
			settings.setHotKey("TimeOut3MinusHotKey",txtTimeOut3MinusHotKey.getText());
			settings.setHotKey("TimeOut3PlusHotKey",txtTimeOut3PlusHotKey.getText());
			settings.setHotKey("SwitchTimeOutsHotKey",txtSwitchTimeOutsHotKey.getText());
			settings.setHotKey("Reset1HotKey",txtReset1HotKey.getText());
			settings.setHotKey("Reset2HotKey",txtReset2HotKey.getText());
			settings.setHotKey("Reset3HotKey",txtReset3HotKey.getText());
			settings.setHotKey("Warn1HotKey",txtWarn1HotKey.getText());
			settings.setHotKey("Warn2HotKey",txtWarn2HotKey.getText());
			settings.setHotKey("Warn3HotKey",txtWarn3HotKey.getText());
			settings.setHotKey("KingSeat1HotKey", txtKingSeat1HotKey.getText());
			settings.setHotKey("KingSeat2HotKey", txtKingSeat2HotKey.getText());
			settings.setHotKey("KingSeat3HotKey", txtKingSeat3HotKey.getText());
			settings.setHotKey("SwitchResetWarnsHotKey",txtSwitchResetWarnsHotKey.getText());
			settings.setHotKey("SwitchSidesHotKey",txtSwitchSidesHotKey.getText());
			settings.setHotKey("ResetNamesHotKey",txtResetNamesHotKey.getText());
			settings.setHotKey("ResetGameCountsHotKey",txtResetGameCountsHotKey.getText());
			settings.setHotKey("ResetMatchCountsHotKey",txtResetMatchCountsHotKey.getText());
			settings.setHotKey("ResetScoresHotKey",txtResetScoresHotKey.getText());
			settings.setHotKey("ResetTimeOutsHotKey",txtResetTimeOutsHotKey.getText());
			settings.setHotKey("ResetResetWarnHotKey",txtResetResetWarnHotKey.getText());
			settings.setHotKey("ResetAllHotKey",txtResetAllHotKey.getText());
			settings.setHotKey("ClearAllHotKey",txtClearAllHotKey.getText());
			settings.setHotKey("ShotTimerHotKey",txtShotTimerHotKey.getText());
			settings.setHotKey("PassTimerHotKey",txtPassTimerHotKey.getText());
			settings.setHotKey("TimeOutTimerHotKey",txtTimeOutTimerHotKey.getText());
			settings.setHotKey("GameTimerHotKey",txtGameTimerHotKey.getText());
			settings.setHotKey("RecallTimerHotKey",txtRecallTimerHotKey.getText());
			settings.setHotKey("ResetTimersHotKey",txtResetTimersHotKey.getText());
			settings.setHotKey("UndoHotKey",txtUndoHotKey.getText());
			settings.setHotKey("RedoHotKey",txtRedoHotKey.getText());
			settings.setHotKey("SwitchForwardsHotKey",txtSwitchForwardsHotKey.getText());
			settings.setHotKey("SwitchGoaliesHotKey",txtSwitchGoaliesHotKey.getText());
			settings.setHotKey("ShowSkunkHotKey",txtShowSkunkHotKey.getText());
			settings.setHotKey("StartStreamHotKey",txtStartStreamHotKey.getText());
			settings.setHotKey("HotKeyBaseScript", hotKeyBaseScriptText);
			try {
				settings.saveHotKeyConfig();
				okToCloseWindow = true;
			} catch (IOException ex) {
				logger.error(ex.toString());
				JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage(), "Settings Error", 1);		 //$NON-NLS-1$
			}
		}
		return okToCloseWindow;
	}
	private boolean checkForUniqueHotKeys() {
		boolean allClear = true;
		HashSet<String> checkUnique = new HashSet<String>();
		Component[] componentArray = this.getComponents();
		for(int i=0; i<componentArray.length; i++) {
			if(componentArray[i] instanceof JTextField) {
				String text = ((JTextField)componentArray[i]).getText();
				if (!text.equals("")) {
					if(!checkUnique.add(text)) {
						allClear = false;
						i = componentArray.length + 1;
						logger.warn(Messages.getString("Errors.DuplicateHotKey") + " " + text);
						JOptionPane.showMessageDialog(null, Messages.getString("Errors.DuplicateHotKey") + " " + text, "Hot Keys Error", 1);
					}
				}
			}
		}
		return allClear;
	}
	////// Listeners \\\\\\
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
}
