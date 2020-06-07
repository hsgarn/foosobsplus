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
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class ResetPanel extends JPanel {
	private JButton btnResetNames;
	private JButton btnResetScores;
	private JButton btnResetGameCounts;
	private JButton btnResetTimeOuts;
	private JButton btnResetResetWarns;
	private JButton btnResetAll;
	private Settings settings;

	public ResetPanel(Settings settings) {
		
		this.settings = settings;
		
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		btnResetNames = new JButton("Reset Names");
		btnResetScores = new JButton("Reset Scores");
		btnResetGameCounts = new JButton("Reset Game Counts");
		btnResetTimeOuts = new JButton("Reset Time Outs");
		btnResetResetWarns = new JButton("Reset Reset/Warns");
		btnResetAll = new JButton("Reset All");

		setMnemonics();

		Border innerBorder = BorderFactory.createTitledBorder("Reset Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();

	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Reset Names ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetNames, gc);
		
		//////// Reset Scores ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetScores, gc);
		
		//////// Reset Game Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetGameCounts, gc);
		
		//////// Reset Time Out Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetTimeOuts, gc);
		
		//////// Reset Reset Warns ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetResetWarns, gc);

		//////// Reset All ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnResetAll, gc);
	}
	private void setMnemonics() {
		if(settings.getResetNamesHotKey().isEmpty()) {
			btnResetNames.setMnemonic(-1);
		} else {
			btnResetNames.setMnemonic(settings.getResetNamesHotKey().charAt(0));
		};
		if(settings.getResetScoresHotKey().isEmpty()) {
			btnResetScores.setMnemonic(-1);
		} else {
			btnResetScores.setMnemonic(settings.getResetScoresHotKey().charAt(0));
		};
		if(settings.getResetGameCountsHotKey().isEmpty()) {
			btnResetGameCounts.setMnemonic(-1);
		} else {
			btnResetGameCounts.setMnemonic(settings.getResetGameCountsHotKey().charAt(0));
		};
		if(settings.getResetTimeOutsHotKey().isEmpty()) {
			btnResetTimeOuts.setMnemonic(-1);
		} else {
			btnResetTimeOuts.setMnemonic(settings.getResetTimeOutsHotKey().charAt(0));
		};
		if(settings.getResetResetWarnHotKey().isEmpty()) {
			btnResetResetWarns.setMnemonic(-1);
		} else {
			btnResetResetWarns.setMnemonic(settings.getResetResetWarnHotKey().charAt(0));
		};
		if(settings.getResetAllHotKey().isEmpty()) {
			btnResetAll.setMnemonic(-1);
		} else {
			btnResetAll.setMnemonic(settings.getResetAllHotKey().charAt(0));
		};
	}
	public void updateMnemonics() {
		setMnemonics();
	}
	////// Listeners //////
	public void addResetNamesListener(ActionListener listenForBtnResetNames) {
		btnResetNames.addActionListener(listenForBtnResetNames);
	}
	public void addResetScoresListener(ActionListener listenForBtnResetScores) {
		btnResetScores.addActionListener(listenForBtnResetScores);
	}
	public void addResetGameCountsListener(ActionListener listenForBtnResetGameCounts) {
		btnResetGameCounts.addActionListener(listenForBtnResetGameCounts);
	}
	public void addResetTimeOutsListener(ActionListener listenForBtnResetTimeOuts) {
		btnResetTimeOuts.addActionListener(listenForBtnResetTimeOuts);
	}
	public void addResetResetWarnsListener(ActionListener listenForBtnResetResetWarns) {
		btnResetResetWarns.addActionListener(listenForBtnResetResetWarns);
	}
	public void addResetAllListener(ActionListener listenForBtnResetAll) {
		btnResetAll.addActionListener(listenForBtnResetAll);
	}
}
