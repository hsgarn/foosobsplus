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
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class StatsDisplayPanel extends JPanel {
	private JLabel lblPassing;
	private JLabel lblShooting;
	private JLabel lblClearing;
	private JLabel lblTwoBarPassing;
	private JLabel lblShotsOnGoal;
	private JLabel lblScoring;
	private JLabel lblThreeBarScoring;
	private JLabel lblFiveBarScoring;
	private JLabel lblTwoBarScoring;
	private JLabel lblBreaks;
	private JLabel lblStuffs;
	private JLabel lblTeam1PassAttempts;
	private JLabel lblTeam1PassCompletes;
	private JLabel lblTeam1Passing;
	private JLabel lblTeam1ShotAttempts;
	private JLabel lblTeam1ShotCompletes;
	private JLabel lblTeam1Shooting;
	private JLabel lblTeam1ClearAttempts;
	private JLabel lblTeam1ClearCompletes;
	private JLabel lblTeam1Clearing;
	private JLabel lblTeam1TwoBarPassAttempts;
	private JLabel lblTeam1TwoBarPassCompletes;
	private JLabel lblTeam1TwoBarPassing;
	private JLabel lblTeam1ShotsOnGoal;
	private JLabel lblTeam1Scoring;
	private JLabel lblTeam1ThreeBarScoring;
	private JLabel lblTeam1FiveBarScoring;
	private JLabel lblTeam1TwoBarScoring;
	private JLabel lblTeam1Breaks;
	private JLabel lblTeam1Stuffs;
	private JLabel lblTeam2PassAttempts;
	private JLabel lblTeam2PassCompletes;
	private JLabel lblTeam2Passing;
	private JLabel lblTeam2ShotAttempts;
	private JLabel lblTeam2ShotCompletes;
	private JLabel lblTeam2Shooting;
	private JLabel lblTeam2ClearAttempts;
	private JLabel lblTeam2ClearCompletes;
	private JLabel lblTeam2Clearing;
	private JLabel lblTeam2TwoBarPassAttempts;
	private JLabel lblTeam2TwoBarPassCompletes;
	private JLabel lblTeam2TwoBarPassing;
	private JLabel lblTeam2ShotsOnGoal;
	private JLabel lblTeam2Scoring;
	private JLabel lblTeam2ThreeBarScoring;
	private JLabel lblTeam2FiveBarScoring;
	private JLabel lblTeam2TwoBarScoring;
	private JLabel lblTeam2Breaks;
	private JLabel lblTeam2Stuffs;
	private DecimalFormat df = new DecimalFormat("###.#");

	public StatsDisplayPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);

		lblPassing = new JLabel("Passing");
		lblShooting = new JLabel("Shooting");
		lblClearing = new JLabel("Clearing");
		lblTwoBarPassing = new JLabel("2-Bar Passing");
		lblShotsOnGoal = new JLabel("Shots On Goal");
		lblScoring = new JLabel("Scoring");
		lblThreeBarScoring = new JLabel("3-Bar");
		lblFiveBarScoring = new JLabel("5-Bar");
		lblTwoBarScoring = new JLabel("2-Bar");
		lblBreaks = new JLabel("Breaks");
		lblStuffs = new JLabel("Stuffs");
		lblTeam1PassAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam1PassCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam1Passing = new JLabel(String.format("%-5s","0%"));
		lblTeam1ShotAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam1ShotCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam1Shooting = new JLabel(String.format("%-5s","0%"));
		lblTeam1ClearAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam1ClearCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam1Clearing = new JLabel(String.format("%-5s","0%"));
		lblTeam1TwoBarPassAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam1TwoBarPassCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam1TwoBarPassing = new JLabel(String.format("%-5s","0%"));
		lblTeam1ShotsOnGoal = new JLabel(String.format("%-3s","0"));
		lblTeam1Scoring = new JLabel(String.format("%-3s","0"));
		lblTeam1ThreeBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam1FiveBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam1TwoBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam1Breaks = new JLabel(String.format("%-3s","0"));
		lblTeam1Stuffs = new JLabel(String.format("%-3s","0"));
		lblTeam2PassAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam2PassCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam2Passing = new JLabel(String.format("%-5s","0%"));
		lblTeam2ShotAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam2ShotCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam2Shooting = new JLabel(String.format("%-5s","0%"));
		lblTeam2ClearAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam2ClearCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam2Clearing = new JLabel(String.format("%-5s","0%"));
		lblTeam2TwoBarPassAttempts = new JLabel(String.format("%-3s","0"));
		lblTeam2TwoBarPassCompletes = new JLabel(String.format("%-3s","0"));
		lblTeam2TwoBarPassing = new JLabel(String.format("%-5s","0%"));
		lblTeam2ShotsOnGoal = new JLabel(String.format("%-3s","0"));
		lblTeam2Scoring = new JLabel(String.format("%-3s","0"));
		lblTeam2ThreeBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam2FiveBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam2TwoBarScoring = new JLabel(String.format("%-3s","0"));
		lblTeam2Breaks = new JLabel(String.format("%-3s","0"));
		lblTeam2Stuffs = new JLabel(String.format("%-3s","0"));
		
		Border innerBorder = BorderFactory.createTitledBorder("Statistics Display Panel");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();

	}

	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = -1;

		//////// Passing Row ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1PassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1PassAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Passing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblPassing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Passing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2PassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2PassAttempts, gc);
		
		//////// Shooting Row ////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ShotCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ShotAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Shooting, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblShooting, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Shooting, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ShotCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ShotAttempts, gc);
		
		//////// Clearing Row ////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ClearCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ClearAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Clearing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblClearing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Clearing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ClearCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ClearAttempts, gc);
		
		/////// Two Bar Passing Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1TwoBarPassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1TwoBarPassAttempts, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1TwoBarPassing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTwoBarPassing, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2TwoBarPassing, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2TwoBarPassCompletes, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2TwoBarPassAttempts, gc);
		
		/////// ShotsOnGoal Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ShotsOnGoal, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblShotsOnGoal, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ShotsOnGoal, gc);
		
		/////// Scoring Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Scoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Scoring, gc);
		
		/////// ThreeBarScoring Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1ThreeBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblThreeBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2ThreeBarScoring, gc);
		
		/////// FiveBarScoring Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1FiveBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblFiveBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2FiveBarScoring, gc);
		
		/////// TwoBarScoring Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1TwoBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTwoBarScoring, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2TwoBarScoring, gc);
		
		/////// Breaks Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Breaks, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblBreaks, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Breaks, gc);
		
		/////// Stuffs Row \\\\\\

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam1Stuffs, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblStuffs, gc);

		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lblTeam2Stuffs, gc);
		
	}

	////// Utility Methods \\\\\\

	public void updatePassStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1PassCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam1PassAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam1Passing.setText(String.format("%-5s",df.format(percent) + "%"));
		} else {
			lblTeam2PassCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam2PassAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam2Passing.setText(String.format("%-5s",df.format(percent)+"%"));
		}
	}
	public void updateShotStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1ShotCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam1ShotAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam1Shooting.setText(String.format("%-5s",df.format(percent)+"%"));
		} else {
			lblTeam2ShotCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam2ShotAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam2Shooting.setText(String.format("%-5s",df.format(percent)+"%"));
		}
	}
	public void updateClearStats(int teamNbr, int completes, int attempts, Float percent) {
		if (teamNbr==1) {
			lblTeam1ClearCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam1ClearAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam1Clearing.setText(String.format("%-5s",df.format(percent)+"%"));
		} else {
			lblTeam2ClearCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam2ClearAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam2Clearing.setText(String.format("%-5s",df.format(percent)+"%"));
		}
	}
	public void updateTwoBarPassStats(int teamNbr, int completes, int attempts, Float percent) {
		if(teamNbr==1) {
			lblTeam1TwoBarPassCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam1TwoBarPassAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam1TwoBarPassing.setText(String.format("%-5s",df.format(percent) + "%"));
		} else {
			lblTeam2TwoBarPassCompletes.setText(String.format("%-3s",Integer.toString(completes)));
			lblTeam2TwoBarPassAttempts.setText(String.format("%-3s",Integer.toString(attempts)));
			lblTeam2TwoBarPassing.setText(String.format("%-5s",df.format(percent)+"%"));
		}
	}
	public void updateScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1Scoring.setText(String.format("%-3s", Integer.toString(scores)));
		} else {
			lblTeam2Scoring.setText(String.format("%-3s", Integer.toString(scores)));
		}
	}
	public void updateThreeBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1ThreeBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		} else {
			lblTeam2ThreeBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		}
	}
	public void updateFiveBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1FiveBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		} else {
			lblTeam2FiveBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		}
	}
	public void updateTwoBarScoring(int teamNbr, int scores) {
		if(teamNbr==1) {
			lblTeam1TwoBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		} else {
			lblTeam2TwoBarScoring.setText(String.format("%-3s", Integer.toString(scores)));
		}
	}
	public void updateStuffs(int teamNbr, int stuffs) {
		if(teamNbr==1) {
			lblTeam1Stuffs.setText(String.format("%-3s", Integer.toString(stuffs)));
		} else {
			lblTeam2Stuffs.setText(String.format("%-3s", Integer.toString(stuffs)));
		}
	}
	public void updateBreaks(int teamNbr, int breaks) {
		if(teamNbr==1) {
			lblTeam1Breaks.setText(String.format("%-3s", Integer.toString(breaks)));
		} else {
			lblTeam2Breaks.setText(String.format("%-3s", Integer.toString(breaks)));
		}
	}
}
