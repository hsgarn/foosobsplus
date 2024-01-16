/**
Copyright © 2020-2024 Hugh Garner
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class TimerPanel extends JPanel {
	private JLabel lblTimerDisplay;
	private JLabel lblTimerInUse;
	private JLabel lblGameTimer;
	private JLabel lblShotTimer;
	private JLabel lblPassTimer;
	private JLabel lblTimeOutTimer;
	private JLabel lblRecallTimer;
	private JButton btnGameTimer;
	private JButton btnShotTimer;
	private JButton btnPassTimer;
	private JButton btnTimeOutTimer;
	private JButton btnRecallTimer;
	private JButton btnResetTimer;
	private Border innerBorder;
	public TimerPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 225;
		setPreferredSize(dim);
		setName(buildTitle());
		lblTimerDisplay = new JLabel("0.0"); //$NON-NLS-1$
		lblTimerDisplay.setOpaque(true);
		lblTimerDisplay.setBackground(Color.GREEN);
		lblTimerInUse = new JLabel(Messages.getString("TimerPanel.TimerReset", Settings.getGameType())); //$NON-NLS-1$
		lblShotTimer = new JLabel(Messages.getString("TimerPanel.ShotTimer", Settings.getGameType())); //$NON-NLS-1$
		lblPassTimer = new JLabel(Messages.getString("TimerPanel.PassTimer", Settings.getGameType())); //$NON-NLS-1$
		lblTimeOutTimer = new JLabel(Messages.getString("TimerPanel.TimeOutTimer", Settings.getGameType())); //$NON-NLS-1$
		lblGameTimer = new JLabel(Messages.getString("TimerPanel.GameTimer", Settings.getGameType())); //$NON-NLS-1$
		lblRecallTimer = new JLabel(Messages.getString("TimerPanel.RecallTimer", Settings.getGameType())); //$NON-NLS-1$
		btnShotTimer = new JButton(Messages.getString("TimerPanel.Start", Settings.getGameType())); //$NON-NLS-1$
		btnPassTimer = new JButton(Messages.getString("TimerPanel.Start", Settings.getGameType())); //$NON-NLS-1$
		btnTimeOutTimer = new JButton(Messages.getString("TimerPanel.Start", Settings.getGameType())); //$NON-NLS-1$
		btnGameTimer = new JButton(Messages.getString("TimerPanel.Start", Settings.getGameType())); //$NON-NLS-1$
		btnRecallTimer = new JButton(Messages.getString("TimerPanel.Start", Settings.getGameType())); //$NON-NLS-1$
		btnResetTimer = new JButton(Messages.getString("TimerPanel.Reset", Settings.getGameType())); //$NON-NLS-1$
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Timer In Use ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTimerInUse, gc);

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTimerDisplay, gc);

		//////// Shot Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblShotTimer, gc);
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnShotTimer, gc);
		
		//////// Pass Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblPassTimer, gc);
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnPassTimer, gc);
		
		//////// Time Out Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTimeOutTimer, gc);
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnTimeOutTimer, gc);
		
		//////// Game Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblGameTimer, gc);
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnGameTimer, gc);
		
		//////// Recall Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblRecallTimer, gc);
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnRecallTimer, gc);
	
		//////// Reset Timer ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetTimer, gc);
	}
	private void setMnemonics() {
		if(Settings.getHotKeyParameter("ShotTimer").isEmpty()) {
			btnShotTimer.setMnemonic(-1);
		} else {
			btnShotTimer.setMnemonic(Settings.getHotKeyParameter("ShotTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("PassTimer").isEmpty()) {
			btnPassTimer.setMnemonic(-1);
		} else {
			btnPassTimer.setMnemonic(Settings.getHotKeyParameter("PassTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("TimeOutTimer").isEmpty()) {
			btnTimeOutTimer.setMnemonic(-1);
		} else {
			btnTimeOutTimer.setMnemonic(Settings.getHotKeyParameter("TimeOutTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("GameTimer").isEmpty()) {
			btnGameTimer.setMnemonic(-1);
		} else {
			btnGameTimer.setMnemonic(Settings.getHotKeyParameter("GameTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("RecallTimer").isEmpty()) {
			btnRecallTimer.setMnemonic(-1);
		} else {
			btnRecallTimer.setMnemonic(Settings.getHotKeyParameter("RecallTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("ResetTimers").isEmpty()) {
			btnResetTimer.setMnemonic(-1);
		} else {
			btnResetTimer.setMnemonic(Settings.getHotKeyParameter("ResetTimers").charAt(0));
		};
	}
	////// Listeners  //////
	public void addShotTimerListener(ActionListener listenForBtnShotTimer) {
		btnShotTimer.addActionListener(listenForBtnShotTimer);
	}
	public void addPassTimerListener(ActionListener listenForBtnPassTimer) {
		btnPassTimer.addActionListener(listenForBtnPassTimer);
	}
	public void addTimeOutTimerListener(ActionListener listenForBtnTimeOutTimer) {
		btnTimeOutTimer.addActionListener(listenForBtnTimeOutTimer);
	}
	public void addGameTimerListener(ActionListener listenForBtnGameTimer) {
		btnGameTimer.addActionListener(listenForBtnGameTimer);
	}
	public void addRecallTimerListener(ActionListener listenForBtnRecallTimer) {
		btnRecallTimer.addActionListener(listenForBtnRecallTimer);
	}
	public void addResetTimerListener(ActionListener listenForBtnResetTimer) {
		btnResetTimer.addActionListener(listenForBtnResetTimer);
	}

	////// Utility Methods //////
	public void updateTimerDisplay(int timeRemaining) {
		updateTimerDisplay(Integer.toString(timeRemaining));
	}
	public void updateTimerDisplay(String timeRemaining) {
		lblTimerDisplay.setText(timeRemaining);
	}
	public String getTimerDisplayText() {
		return lblTimerDisplay.getText();
	}
	public Color getTimerDisplayColor() {
		return lblTimerDisplay.getBackground();
	}
	public void setTimerDisplayColor(Color color) {
		lblTimerDisplay.setBackground(color);
	}
	public void updateTimerInUse(String timerName) {
		lblTimerInUse.setText(timerName);
	}
	public void updateMnemonics() {
		setMnemonics();
	}
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("TimerPanel.TimerPanel", Settings.getGameType()); //$NON-NLS-1$
	}
}
