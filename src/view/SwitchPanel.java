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
public class SwitchPanel extends JPanel {
	
	private JButton btnSwitchSides;
	private JButton btnSwitchTeams;
	private JButton btnSwitchScores;
	private JButton btnSwitchGameCounts;
	private JButton btnSwitchTimeOuts;
	private JButton btnSwitchResetWarns;
	private JButton btnClearAll;
	private JLabel lblLastScored;

	public SwitchPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		btnSwitchSides = new JButton("Switch Sides");
		btnSwitchTeams = new JButton("Switch Teams");
		btnSwitchScores = new JButton("Switch Scores");
		btnSwitchGameCounts = new JButton("Switch Game Counts");
		btnSwitchTimeOuts = new JButton("Switch Time Outs");
		btnSwitchResetWarns = new JButton("Switch Reset/Warns");
		btnClearAll = new JButton("Clear All");
		lblLastScored = new JLabel("     Last Scored     ");
		lblLastScored.setBorder(BorderFactory.createLoweredBevelBorder());

		Border innerBorder = BorderFactory.createTitledBorder("Switch Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Switch Sides ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchSides, gc);
		
		//////// Switch Teams ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchTeams, gc);
		
		//////// Switch Scores ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchScores, gc);
		
		//////// Switch Game Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchGameCounts, gc);
		
		//////// Switch Time Out Counts ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchTimeOuts, gc);
		
		//////// Switch Reset Warns ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSwitchResetWarns, gc);

		//////// Clear All ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnClearAll, gc);

		//////// Last Scored ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblLastScored, gc);
}
	
	////// Listeners \\\\\\
	
	public void addSwitchSidesListener(ActionListener listenForBtnSwitchSides) {
		btnSwitchSides.addActionListener(listenForBtnSwitchSides);
	}
	public void addSwitchTeamsListener(ActionListener listenForBtnSwitchTeams) {
		btnSwitchTeams.addActionListener(listenForBtnSwitchTeams);
	}
	public void addSwitchScoresListener(ActionListener listenForBtnSwitchScores) {
		btnSwitchScores.addActionListener(listenForBtnSwitchScores);
	}
	public void addSwitchGameCountsListener(ActionListener listenForBtnSwitchGameCounts) {
		btnSwitchGameCounts.addActionListener(listenForBtnSwitchGameCounts);
	}
	public void addSwitchTimeOutsListener(ActionListener listenForBtnSwitchTimeOuts) {
		btnSwitchTimeOuts.addActionListener(listenForBtnSwitchTimeOuts);
	}
	public void addSwitchResetWarnsListener(ActionListener listenForBtnSwitchResetWarns) {
		btnSwitchResetWarns.addActionListener(listenForBtnSwitchResetWarns);
	}
	public void addClearAllListener(ActionListener listenForBtnClearAll) {
		btnClearAll.addActionListener(listenForBtnClearAll);
	}
	
	////// Utility Methods \\\\\\

	public void setLastScored(String txt) {
		lblLastScored.setText(txt);
	}
}
