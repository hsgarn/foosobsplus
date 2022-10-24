/**
Copyright 2022 Hugh Garner
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

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class PartnerProgramPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtPlayer1FileName;
	private JTextField txtPlayer2FileName;
	private JTextField txtPlayer3FileName;
	private JTextField txtPlayer4FileName;

	// Create the Panel.

	public PartnerProgramPanel(Settings settings) throws IOException {

		setLayout(settings);

	}

	private void restoreDefaults(Settings settings) {
		txtPlayer1FileName.setText(settings.getDefaultPlayer1FileName());
		txtPlayer2FileName.setText(settings.getDefaultPlayer2FileName());
		txtPlayer3FileName.setText(settings.getDefaultPlayer3FileName());
		txtPlayer4FileName.setText(settings.getDefaultPlayer4FileName());
	}

	private void saveSettings(Settings settings) {
		settings.setPlayer1FileName(txtPlayer1FileName.getText());
		settings.setPlayer2FileName(txtPlayer2FileName.getText());
		settings.setPlayer3FileName(txtPlayer3FileName.getText());
		settings.setPlayer4FileName(txtPlayer4FileName.getText());
		try {
			settings.savePartnerProgramConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
		}
	}
	public void setLayout(Settings settings) {	
		setLayout(new MigLayout());

		JButton btnSelectPath = new JButton(Messages.getString("PartnerProgramPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
								System.out.println(Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage()); //$NON-NLS-1$
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					try {
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveFileNameConfig();
					} catch (IOException ex) {
						System.out.print(Messages.getString("PartnerProgramPanel.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField();
		formattedTxtPath.setText(settings.getPartnerProgramPath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setPartnerProgramPath(formattedTxtPath.getText());
					settings.saveFileNameConfig();
		    	} catch (IOException ex) {
		    		System.out.print(Messages.getString("PartnerProgramPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						settings.setPartnerProgramPath(formattedTxtPath.getText());
						settings.saveFileNameConfig();
			    	} catch (IOException ex) {
			    		System.out.print(Messages.getString("PartnerProgramPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
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

		txtPlayer1FileName = new JTextField();
		txtPlayer1FileName.setText(settings.getPlayer1FileName());
		txtPlayer1FileName.setColumns(10);
		add(txtPlayer1FileName, "cell 2 2,alignx left"); //$NON-NLS-1$

		txtPlayer2FileName = new JTextField();
		txtPlayer2FileName.setText(settings.getPlayer2FileName());
		txtPlayer2FileName.setColumns(10);
		add(txtPlayer2FileName, "cell 2 3,alignx left"); //$NON-NLS-1$

		txtPlayer3FileName = new JTextField();
		txtPlayer3FileName.setText(settings.getPlayer3FileName());
		txtPlayer3FileName.setColumns(10);
		add(txtPlayer3FileName, "cell 2 4,alignx left"); //$NON-NLS-1$

		txtPlayer4FileName = new JTextField();
		txtPlayer4FileName.setText(settings.getPlayer4FileName());
		txtPlayer4FileName.setColumns(10);
		add(txtPlayer4FileName, "cell 2 5,alignx left"); //$NON-NLS-1$

		JButton btnSavePartnerProgram = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSavePartnerProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSavePartnerProgram, "cell 2 19,alignx center"); //$NON-NLS-1$

		JButton btnCancelPartnerProgram = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelPartnerProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelPartnerProgram, "cell 4 19,alignx center"); //$NON-NLS-1$
		
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
}
