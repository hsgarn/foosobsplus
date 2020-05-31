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
public class MatchPanel extends JPanel {
	
	private JButton btnStartMatch;
	private JLabel lblStartTimeLabel;
	private JLabel lblElapsedTimeLabel;
	private JLabel lblStartTime;
	private JLabel lblElapsedTime;
	
	public MatchPanel() {

		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		btnStartMatch = new JButton("Start Match");
		lblStartTimeLabel = new JLabel("Start Time:");
		lblElapsedTimeLabel = new JLabel("Elapsed Time:");
		lblStartTime = new JLabel("00:00:00");
		lblElapsedTime = new JLabel("00:00:00");
		
		Border innerBorder = BorderFactory.createTitledBorder("Match Information");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		////////  Start ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridheight =2 ;
		gc.fill = GridBagConstraints.VERTICAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 5, 5, 5);
		add(btnStartMatch, gc);
		gc.gridheight = 1;
		
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblStartTimeLabel, gc);

		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblStartTime, gc);

		////////  Elapsed Time ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(5, 5, 5, 5);
		add(lblElapsedTimeLabel, gc);
		
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblElapsedTime, gc);
				
	}		

	////// Listeners  //////
	public void addStartMatchListener(ActionListener listenForBtnStartMatch) {
		btnStartMatch.addActionListener(listenForBtnStartMatch);
	}

	public void updateElapsedTime(String elapsedTime) {
		lblElapsedTime.setText(elapsedTime);
	}
	public void updateStartTime(String startTime) {
		lblStartTime.setText(startTime);
	}
}
