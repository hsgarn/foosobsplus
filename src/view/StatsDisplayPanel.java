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
	private JLabel lblTeam1PassAttempts;
	private JLabel lblTeam1PassCompletes;
	private JLabel lblTeam1Passing;
	private JLabel lblTeam1ShotAttempts;
	private JLabel lblTeam1ShotCompletes;
	private JLabel lblTeam1Shooting;
	private JLabel lblTeam1ClearAttempts;
	private JLabel lblTeam1ClearCompletes;
	private JLabel lblTeam1Clearing;
	private JLabel lblTeam2PassAttempts;
	private JLabel lblTeam2PassCompletes;
	private JLabel lblTeam2Passing;
	private JLabel lblTeam2ShotAttempts;
	private JLabel lblTeam2ShotCompletes;
	private JLabel lblTeam2Shooting;
	private JLabel lblTeam2ClearAttempts;
	private JLabel lblTeam2ClearCompletes;
	private JLabel lblTeam2Clearing;
	private DecimalFormat df = new DecimalFormat("###.#");

	public StatsDisplayPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);

		lblPassing = new JLabel("Passing");
		lblShooting = new JLabel("Shooting");
		lblClearing = new JLabel("Clearing");
		lblTeam1PassAttempts = new JLabel("0");
		lblTeam1PassCompletes = new JLabel("0");
		lblTeam1Passing = new JLabel("0%");
		lblTeam1ShotAttempts = new JLabel("0");
		lblTeam1ShotCompletes = new JLabel("0");
		lblTeam1Shooting = new JLabel("0%");
		lblTeam1ClearAttempts = new JLabel("0");
		lblTeam1ClearCompletes = new JLabel("0");
		lblTeam1Clearing = new JLabel("%");
		lblTeam2PassAttempts = new JLabel("0");
		lblTeam2PassCompletes = new JLabel("0");
		lblTeam2Passing = new JLabel("0%");
		lblTeam2ShotAttempts = new JLabel("0");
		lblTeam2ShotCompletes = new JLabel("0");
		lblTeam2Shooting = new JLabel("0%");
		lblTeam2ClearAttempts = new JLabel("0");
		lblTeam2ClearCompletes = new JLabel("0");
		lblTeam2Clearing = new JLabel("0%");
		
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
	}

	////// Utility Methods \\\\\\

	public void updateTeam1PassStats(int successes, int attempts) {
		lblTeam1PassCompletes.setText(Integer.toString(successes));
		lblTeam1PassAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam1Passing.setText(df.format(percent) + "%");
	}
	public void updateTeam2PassStats(int successes, int attempts) {
		lblTeam2PassCompletes.setText(Integer.toString(successes));
		lblTeam2PassAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam2Passing.setText(df.format(percent)+"%");
	}

	public void updateTeam1ShotStats(int successes, int attempts) {
		lblTeam1ShotCompletes.setText(Integer.toString(successes));
		lblTeam1ShotAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam1Shooting.setText(df.format(percent)+"%");
	}
	public void updateTeam2ShotStats(int successes, int attempts) {
		lblTeam2ShotCompletes.setText(Integer.toString(successes));
		lblTeam2ShotAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam2Shooting.setText(df.format(percent)+"%");
	}

	public void updateTeam1ClearStats(int successes, int attempts) {
		lblTeam1ClearCompletes.setText(Integer.toString(successes));
		lblTeam1ClearAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam1Clearing.setText(df.format(percent)+"%");
	}
	public void updateTeam2ClearStats(int successes, int attempts) {
		lblTeam2ClearCompletes.setText(Integer.toString(successes));
		lblTeam2ClearAttempts.setText(Integer.toString(attempts));
		float percent = 0;
		if(attempts > 0) {
			percent = (float) successes/ (float) attempts;
			percent = percent * 100f;
		}
		lblTeam2Clearing.setText(df.format(percent)+"%");
	}
}
