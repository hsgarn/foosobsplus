/**
Copyright © 2022-2026 Hugh Garner
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

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class PartnerProgramPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtPlayer1FileName;
	private JTextField txtPlayer2FileName;
	private JTextField txtPlayer3FileName;
	private JTextField txtPlayer4FileName;
	private JTextField txtEventFilename;
	private JTextField txtTournamentFilename;
	private static final Logger logger = LoggerFactory.getLogger(PartnerProgramPanel.class);
	// Create the Panel.
	public PartnerProgramPanel() throws IOException {
		setLayout();
	}
	private void restoreDefaults() {
		txtPlayer1FileName.setText(Settings.getDefaultPartnerProgram("Player1FileName")); //$NON-NLS-1$
		txtPlayer2FileName.setText(Settings.getDefaultPartnerProgram("Player2FileName")); //$NON-NLS-1$
		txtPlayer3FileName.setText(Settings.getDefaultPartnerProgram("Player3FileName")); //$NON-NLS-1$
		txtPlayer4FileName.setText(Settings.getDefaultPartnerProgram("Player4FileName")); //$NON-NLS-1$
		txtEventFilename.setText(Settings.getDefaultPartnerProgram("EventFilename")); //$NON-NLS-1$
		txtTournamentFilename.setText(Settings.getDefaultPartnerProgram("TournamentFilename")); //$NON-NLS-1$
	}
	private void revertChanges() {
		txtPlayer1FileName.setText(Settings.getPartnerProgramParameter("Player1FileName")); //$NON-NLS-1$
		txtPlayer2FileName.setText(Settings.getPartnerProgramParameter("Player2FileName")); //$NON-NLS-1$
		txtPlayer3FileName.setText(Settings.getPartnerProgramParameter("Player3FileName")); //$NON-NLS-1$
		txtPlayer4FileName.setText(Settings.getPartnerProgramParameter("Player4FileName")); //$NON-NLS-1$
		txtEventFilename.setText(Settings.getPartnerProgramParameter("EventFilename")); //$NON-NLS-1$
		txtTournamentFilename.setText(Settings.getPartnerProgramParameter("TournamentFilename")); //$NON-NLS-
	}
	private void saveSettings() {
		Settings.setPartnerProgram("Player1FileName",txtPlayer1FileName.getText()); //$NON-NLS-1$
		Settings.setPartnerProgram("Player2FileName",txtPlayer2FileName.getText()); //$NON-NLS-1$
		Settings.setPartnerProgram("Player3FileName",txtPlayer3FileName.getText()); //$NON-NLS-1$
		Settings.setPartnerProgram("Player4FileName",txtPlayer4FileName.getText()); //$NON-NLS-1$
		Settings.setPartnerProgram("EventFilename",txtEventFilename.getText()); //$NON-NLS-1$
		Settings.setPartnerProgram("TournamentFilename",txtTournamentFilename.getText()); //$NON-NLS
		try {
			Settings.savePartnerProgramConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public final void setLayout() {	
		setLayout(new MigLayout());
		JButton btnSelectPath = new JButton(Messages.getString("PartnerProgramPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener((ActionEvent e) -> {
                    final JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
                    chooser.setDialogTitle(Messages.getString("PartnerProgramPanel.SelectDirectoryForPath")); //$NON-NLS-1$
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    int returnVal = chooser.showOpenDialog(PartnerProgramPanel.this);
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
                            Settings.setPartnerProgramPath(formattedTxtPath.getText());
                            Settings.savePartnerProgramConfig();
                        } catch (IOException ex) {
                            logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
                            logger.error(ex.toString());
                        }
                    }
                });
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$
		formattedTxtPath = new JFormattedTextField();
		formattedTxtPath.setText(Settings.getPartnerProgramParameter("PartnerProgramPath")); //$NON-NLS-1$
		formattedTxtPath.addFocusListener(new FocusAdapter() {
                        @Override
			public void focusLost(FocusEvent arg0) {
		    	try {
					Settings.setPartnerProgramPath(formattedTxtPath.getText());
					Settings.savePartnerProgramConfig();
		    	} catch (IOException ex) {
		    		logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
		    		logger.error(ex.toString());
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
                        @Override
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						Settings.setPartnerProgramPath(formattedTxtPath.getText());
						Settings.savePartnerProgramConfig();
			    	} catch (IOException ex) {
			    		logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			    		logger.error(ex.toString());
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);
		JLabel lblFileName = new JLabel(Messages.getString("PartnerProgramPanel.FileName")); //$NON-NLS-1$
		add(lblFileName, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblPlayer1FileName = new JLabel(Messages.getString("PartnerProgramPanel.Player1")); //$NON-NLS-1$
		add(lblPlayer1FileName, "cell 1 2,alignx right"); //$NON-NLS-1$
		JLabel lblPlayer2FileName = new JLabel(Messages.getString("PartnerProgramPanel.Player2")); //$NON-NLS-1$
		add(lblPlayer2FileName, "cell 1 3,alignx right"); //$NON-NLS-1$
		JLabel lblPlayer3FileName = new JLabel(Messages.getString("PartnerProgramPanel.Player3")); //$NON-NLS-1$
		add(lblPlayer3FileName, "cell 1 4,alignx right"); //$NON-NLS-1$
		JLabel lblPlayer4FileName = new JLabel(Messages.getString("PartnerProgramPanel.Player4")); //$NON-NLS-1$
		add(lblPlayer4FileName, "cell 1 5,alignx right"); //$NON-NLS-1$
		JLabel lblEventFileName = new JLabel(Messages.getString("PartnerProgramPanel.Event")); //$NON-NLS-1$
		add(lblEventFileName, "cell 1 6,alignx right"); //$NON-NLS-1$
		JLabel lblTournamentFileName = new JLabel(Messages.getString("PartnerProgramPanel.Tournament")); //$NON-NLS-1$
		add(lblTournamentFileName, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtPlayer1FileName = new JTextField();
		txtPlayer1FileName.setText(Settings.getPartnerProgramParameter("Player1FileName")); //$NON-NLS-1$
		txtPlayer1FileName.setColumns(10);
		add(txtPlayer1FileName, "cell 2 2,alignx left"); //$NON-NLS-1$
		txtPlayer2FileName = new JTextField();
		txtPlayer2FileName.setText(Settings.getPartnerProgramParameter("Player2FileName")); //$NON-NLS-1$
		txtPlayer2FileName.setColumns(10);
		add(txtPlayer2FileName, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtPlayer3FileName = new JTextField();
		txtPlayer3FileName.setText(Settings.getPartnerProgramParameter("Player3FileName")); //$NON-NLS-1$
		txtPlayer3FileName.setColumns(10);
		add(txtPlayer3FileName, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtPlayer4FileName = new JTextField();
		txtPlayer4FileName.setText(Settings.getPartnerProgramParameter("Player4FileName")); //$NON-NLS-1$
		txtPlayer4FileName.setColumns(10);
		add(txtPlayer4FileName, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtEventFilename = new JTextField();
		txtEventFilename.setText(Settings.getPartnerProgramParameter("EventFilename")); //$NON-NLS-1$
		txtEventFilename.setColumns(10);
		add(txtEventFilename, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTournamentFilename = new JTextField();
		txtTournamentFilename.setText(Settings.getPartnerProgramParameter("TournamentFilename")); //$NON-NLS-1$
		txtTournamentFilename.setColumns(10);
		add(txtTournamentFilename, "cell 2 7,alignx left"); //$NON-NLS-1$
		JButton btnApplyPartnerProgram = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		btnApplyPartnerProgram.addActionListener((ActionEvent e) -> {
                    saveSettings();
                });
		add(btnApplyPartnerProgram, "cell 1 19,alignx center"); //$NON-NLS-1$
		JButton btnSavePartnerProgram = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSavePartnerProgram.addActionListener((ActionEvent e) -> {
                    saveSettings();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnSavePartnerProgram, "cell 2 19,alignx center"); //$NON-NLS-1$
		JButton btnCancelPartnerProgram = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelPartnerProgram.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancelPartnerProgram, "cell 4 19,alignx center"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
}
