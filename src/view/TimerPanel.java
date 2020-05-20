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

	public TimerPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		lblTimerDisplay = new JLabel("0.0");
		lblTimerDisplay.setOpaque(true);
		lblTimerDisplay.setBackground(Color.GREEN);
		lblTimerInUse = new JLabel("Timer Reset");
		lblShotTimer = new JLabel("Shot Timer (2 & 3 row)");
		lblPassTimer = new JLabel("Pass Timer (5 row)");
		lblTimeOutTimer = new JLabel("Time Out Timer");
		lblGameTimer = new JLabel("Game Timer");
		lblRecallTimer = new JLabel("Recall Timer");
		btnShotTimer = new JButton("Start");
		btnPassTimer = new JButton("Start");
		btnTimeOutTimer = new JButton("Start");
		btnGameTimer = new JButton("Start");
		btnRecallTimer = new JButton("Start");
		btnResetTimer = new JButton("Reset Timer");

		Border innerBorder = BorderFactory.createTitledBorder("Timer Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
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

		//////// Timer Display ////////
//		gc.gridy++;

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
		gc.gridwidth = 1;
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
		lblTimerDisplay.setText(Integer.toString(timeRemaining));
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

}
