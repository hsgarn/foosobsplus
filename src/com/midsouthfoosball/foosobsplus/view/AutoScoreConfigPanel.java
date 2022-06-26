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
import java.io.IOException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class AutoScoreConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblTeam1;
	private JLabel lblTeam2;
	private JLabel lblTeam1Pin1; 
	private JLabel lblTeam1Pin2;
	private JLabel lblTeam2Pin1;
	private JLabel lblTeam2Pin2;
	private JLabel lblTeam1LEDPin;
	private JLabel lblTeam2LEDPin;
	private JLabel lblSwitchPin;
	private JLabel lblResetSeconds;

	private JTextField txtTeam1Pin1;
	private JTextField txtTeam1Pin2;
	private JTextField txtTeam2Pin1;
	private JTextField txtTeam2Pin2;
	private JTextField txtTeam1LEDPin;	
	private JTextField txtTeam2LEDPin;
	private JTextField txtSwitchPin;
	private JTextField txtResetSeconds;
	
	private JButton btnSendConfig;
	private JButton btnSave;
	private Settings settings;

	public AutoScoreConfigPanel(Settings settings) throws IOException {
		this.settings = settings;
		
		setLayout(new MigLayout());
		
		lblTeam1 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1, "cell 1 1,alignx leading"); //$NON-NLS-1$
		
		lblTeam2 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2, "cell 3 1,alignx leading"); //$NON-NLS-1$

		lblTeam1Pin1 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team1Pin1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Pin1, "cell 0 2,alignx trailing"); //$NON-NLS-1$
		
		lblTeam1Pin2 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team1Pin2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1Pin2, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		
		lblTeam2Pin1 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team2Pin1", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Pin1, "cell 2 2,alignx trailing"); //$NON-NLS-1$
		
		lblTeam2Pin2 = new JLabel(Messages.getString("AutoScoreConfigPanel.Team2Pin2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2Pin2, "cell 2 3,alignx trailing"); //$NON-NLS-1$
		
		lblTeam1LEDPin = new JLabel(Messages.getString("AutoScoreConfigPanel.Team1LEDPin", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1LEDPin, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		
		lblTeam2LEDPin = new JLabel(Messages.getString("AutoScoreConfigPanel.Team2LEDPin", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2LEDPin, "cell 2 4,alignx trailing"); //$NON-NLS-1$
		
		lblSwitchPin = new JLabel(Messages.getString("AutoScoreConfigPanel.SwitchPin", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchPin, "cell 0 5,alignx trailing"); //$NON-NLS-1$
		
		lblResetSeconds = new JLabel(Messages.getString("AutoScoreConfigPanel.ResetSeconds", settings.getGameType())); //$NON-NLS-1$
		add(lblResetSeconds, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1Pin1 = new JTextField();
		txtTeam1Pin1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1Pin1.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam1Pin1.setText(Integer.toString(settings.getAutoScoreConfigTeam1Pin1()));
		add(txtTeam1Pin1, "cell 1 2,alignx left"); //$NON-NLS-1$
		txtTeam1Pin1.setColumns(10);
		
		txtTeam1Pin2 = new JTextField();
		txtTeam1Pin2.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1Pin2.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam1Pin2.setText(Integer.toString(settings.getAutoScoreConfigTeam1Pin2()));
		add(txtTeam1Pin2, "cell 1 3,alignx left"); //$NON-NLS-1$
		txtTeam1Pin2.setColumns(10);
		
		txtTeam2Pin1 = new JTextField();
		txtTeam2Pin1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2Pin1.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam2Pin1.setText(Integer.toString(settings.getAutoScoreConfigTeam2Pin1()));
		add(txtTeam2Pin1, "cell 3 2,alignx left"); //$NON-NLS-1$
		txtTeam2Pin1.setColumns(10);
		
		txtTeam2Pin2 = new JTextField();
		txtTeam2Pin2.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2Pin2.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam2Pin2.setText(Integer.toString(settings.getAutoScoreConfigTeam2Pin2()));
		add(txtTeam2Pin2, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam2Pin2.setColumns(10);

		txtTeam1LEDPin = new JTextField();
		txtTeam1LEDPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam1LEDPin.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam1LEDPin.setText(Integer.toString(settings.getAutoScoreConfigTeam1LEDPin()));
		add(txtTeam1LEDPin, "cell 1 4,alignx left"); //$NON-NLS-1$
		txtTeam1LEDPin.setColumns(10);
		
		txtTeam2LEDPin = new JTextField();
		txtTeam2LEDPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeam2LEDPin.setInputVerifier(new IntegerPinInputVerifier());
		txtTeam2LEDPin.setText(Integer.toString(settings.getAutoScoreConfigTeam2LEDPin()));
		add(txtTeam2LEDPin, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam2LEDPin.setColumns(10);
		
		txtSwitchPin = new JTextField();
		txtSwitchPin.setHorizontalAlignment(SwingConstants.CENTER);
		txtSwitchPin.setInputVerifier(new IntegerPinInputVerifier());
		txtSwitchPin.setText(Integer.toString(settings.getAutoScoreConfigSwitchPin()));
		add(txtSwitchPin, "cell 1 5,alignx left"); //$NON-NLS-1$
		txtSwitchPin.setColumns(10);
		
		txtResetSeconds = new JTextField();
		txtResetSeconds.setHorizontalAlignment(SwingConstants.CENTER);
		txtResetSeconds.setInputVerifier(new IntegerResetSecondsInputVerifier());
		txtResetSeconds.setText(Integer.toString(settings.getAutoScoreConfigResetSeconds()));
		add(txtResetSeconds, "cell 1 6,alignx left"); //$NON-NLS-1$
		txtResetSeconds.setColumns(10);

		btnSendConfig = new JButton("Send Config");
		add(btnSendConfig, "flowx,cell 1 7,growx");
		
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "flowx,cell 1 18,alignx center"); //$NON-NLS-1$
		
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 1 18,alignx center"); //$NON-NLS-1$
		
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 2 18,alignx center"); //$NON-NLS-1$
	}
	private void restoreDefaults(Settings settings) {
		txtTeam1Pin1.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam1Pin1()));
		txtTeam1Pin2.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam1Pin2()));
		txtTeam2Pin1.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam2Pin1()));
		txtTeam2Pin2.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam2Pin2()));
		txtTeam1LEDPin.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam1LEDPin()));
		txtTeam2LEDPin.setText(Integer.toString(settings.getDefaultAutoScoreConfigTeam2LEDPin()));
		txtSwitchPin.setText(Integer.toString(settings.getDefaultAutoScoreConfigSwitchPin()));
		txtResetSeconds.setText(Integer.toString(settings.getDefaultAutoScoreConfigResetSeconds()));
	}
	
	public void saveSettings() {
		settings.setAutoScoreConfigTeam1Pin1(txtTeam1Pin1.getText());
		settings.setAutoScoreConfigTeam1Pin2(txtTeam1Pin2.getText());
		settings.setAutoScoreConfigTeam2Pin1(txtTeam2Pin1.getText());
		settings.setAutoScoreConfigTeam2Pin2(txtTeam2Pin2.getText());
		settings.setAutoScoreConfigTeam1LEDPin(txtTeam1LEDPin.getText());
		settings.setAutoScoreConfigTeam2LEDPin(txtTeam2LEDPin.getText());
		settings.setAutoScoreConfigSwitchPin(txtSwitchPin.getText());
		settings.setAutoScoreConfigResetSeconds(txtResetSeconds.getText());
		try {
			settings.saveAutoScoreConfigConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		}
	}
	public class IntegerPinInputVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			try {
				int value = Integer.parseInt(text);
				if ((value > -1) && (value < 41)) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Only 0 - 40 accepted. 0 to disable pin", "Invalid Input", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Only 0 - 40 accepted. 0 to disable pin", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
	}
	public class IntegerResetSecondsInputVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			try {
				int value = Integer.parseInt(text);
				if (value > -1) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Reset Seconds must be a positive integer less than " + Integer.MAX_VALUE + " or 0.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Reset Seconds must be a positive integer less than " + Integer.MAX_VALUE + " or 0.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
	}
	////// Listeners \\\\\\
	public void addSendConfigListener(ActionListener listenForBtnSendConfig) {
		btnSendConfig.addActionListener(listenForBtnSendConfig);
	}
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
}
