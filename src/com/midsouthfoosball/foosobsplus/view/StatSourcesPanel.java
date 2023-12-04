/**
Copyright 2023-2024 Hugh Garner
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class StatSourcesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtStuffs1Source;
	private JTextField txtStuffs2Source;
	private JTextField txtBreaks1Source;
	private JTextField txtBreaks2Source;
	private JTextField txtAces1Source;
	private JTextField txtAces2Source;
	private JFormattedTextField formattedTxtPath;
	private JTextField txtTeam1PassAttemptsSource;
	private JTextField txtTeam1PassCompletesSource;
	private JTextField txtTeam2PassAttemptsSource;
	private JTextField txtTeam2PassCompletesSource;
	private JTextField txtTeam1ShotAttemptsSource;
	private JTextField txtTeam1ShotCompletesSource;
	private JTextField txtTeam2ShotAttemptsSource;
	private JTextField txtTeam2ShotCompletesSource;
	private JTextField txtTeam1ClearAttemptsSource;
	private JTextField txtTeam1ClearCompletesSource;
	private JTextField txtTeam2ClearAttemptsSource;
	private JTextField txtTeam2ClearCompletesSource;
	private JTextField txtTeam1PassPercentSource;
	private JTextField txtTeam2PassPercentSource;
	private JTextField txtTeam1ShotPercentSource;
	private JTextField txtTeam2ShotPercentSource;
	private JTextField txtTeam1ClearPercentSource;
	private JTextField txtTeam2ClearPercentSource;
	private JTextField txtTeam1TwoBarPassAttemptsSource;
	private JTextField txtTeam1TwoBarPassCompletesSource;
	private JTextField txtTeam2TwoBarPassAttemptsSource;
	private JTextField txtTeam2TwoBarPassCompletesSource;
	private JTextField txtTeam1ScoringSource;
	private JTextField txtTeam2ScoringSource;
	private JTextField txtTeam1ThreeBarScoringSource;
	private JTextField txtTeam2ThreeBarScoringSource;
	private JTextField txtTeam1FiveBarScoringSource;
	private JTextField txtTeam2FiveBarScoringSource;
	private JTextField txtTeam1TwoBarScoringSource;
	private JTextField txtTeam2TwoBarScoringSource;
	private JTextField txtTeam1ShotsOnGoalSource;
	private JTextField txtTeam2ShotsOnGoalSource;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$
	private static transient Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// Create the Panel.

	public StatSourcesPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);
	}

	private void restoreDefaults(Settings settings) {
		txtStuffs1Source.setText(settings.getDefaultStuffs1Source());
		txtStuffs2Source.setText(settings.getDefaultStuffs2Source());
		txtBreaks1Source.setText(settings.getDefaultBreaks1Source());
		txtBreaks2Source.setText(settings.getDefaultBreaks2Source());
		txtAces1Source.setText(settings.getDefaultAces1Source());
		txtAces2Source.setText(settings.getDefaultAces2Source());
		txtTeam1PassAttemptsSource.setText(settings.getDefaultTeam1PassAttemptsSource());
		txtTeam2PassAttemptsSource.setText(settings.getDefaultTeam2PassAttemptsSource());
		txtTeam1ShotAttemptsSource.setText(settings.getDefaultTeam1ShotAttemptsSource());
		txtTeam2ShotAttemptsSource.setText(settings.getDefaultTeam2ShotAttemptsSource());
		txtTeam1ClearAttemptsSource.setText(settings.getDefaultTeam1ClearAttemptsSource());
		txtTeam2ClearAttemptsSource.setText(settings.getDefaultTeam2ClearAttemptsSource());
		txtTeam1PassCompletesSource.setText(settings.getDefaultTeam1PassCompletesSource());
		txtTeam2PassCompletesSource.setText(settings.getDefaultTeam2PassCompletesSource());
		txtTeam1ShotCompletesSource.setText(settings.getDefaultTeam1ShotCompletesSource());
		txtTeam2ShotCompletesSource.setText(settings.getDefaultTeam2ShotCompletesSource());
		txtTeam1ClearCompletesSource.setText(settings.getDefaultTeam1ClearCompletesSource());
		txtTeam2ClearCompletesSource.setText(settings.getDefaultTeam2ClearCompletesSource());
		txtTeam1PassPercentSource.setText(settings.getDefaultTeam1PassPercentSource());
		txtTeam2PassPercentSource.setText(settings.getDefaultTeam2PassPercentSource());
		txtTeam1ShotPercentSource.setText(settings.getDefaultTeam1ShotPercentSource());
		txtTeam2ShotPercentSource.setText(settings.getDefaultTeam2ShotPercentSource());
		txtTeam1ClearPercentSource.setText(settings.getDefaultTeam1ClearPercentSource());
		txtTeam2ClearPercentSource.setText(settings.getDefaultTeam2ClearPercentSource());
		txtTeam1ScoringSource.setText(settings.getDefaultTeam1ScoringSource());
		txtTeam2ScoringSource.setText(settings.getDefaultTeam2ScoringSource());
		txtTeam1ThreeBarScoringSource.setText(settings.getDefaultTeam1ThreeBarScoringSource());
		txtTeam2ThreeBarScoringSource.setText(settings.getDefaultTeam2ThreeBarScoringSource());
		txtTeam1FiveBarScoringSource.setText(settings.getDefaultTeam1FiveBarScoringSource());
		txtTeam2FiveBarScoringSource.setText(settings.getDefaultTeam2FiveBarScoringSource());
		txtTeam1TwoBarScoringSource.setText(settings.getDefaultTeam1TwoBarScoringSource());
		txtTeam2TwoBarScoringSource.setText(settings.getDefaultTeam2TwoBarScoringSource());
		txtTeam1ShotsOnGoalSource.setText(settings.getDefaultTeam1ShotsOnGoalSource());
		txtTeam2ShotsOnGoalSource.setText(settings.getDefaultTeam2ShotsOnGoalSource());
	}

	private void saveSettings(Settings settings) {
		settings.setSource("Stuffs1Source",txtStuffs1Source.getText());
		settings.setSource("Stuffs2Source",txtStuffs2Source.getText());
		settings.setSource("Breaks1Source",txtBreaks1Source.getText());
		settings.setSource("Breaks2Source",txtBreaks2Source.getText());
		settings.setSource("Aces1Source",txtAces1Source.getText());
		settings.setSource("Aces2Source",txtAces2Source.getText());
		settings.setSource("Team1PassAttemptsSource",txtTeam1PassAttemptsSource.getText());
		settings.setSource("Team2PassAttemptsSource",txtTeam2PassAttemptsSource.getText());
		settings.setSource("Team1ShotAttemptsSource",txtTeam1ShotAttemptsSource.getText());
		settings.setSource("Team2ShotAttemptsSource",txtTeam2ShotAttemptsSource.getText());
		settings.setSource("Team1ClearAttemptsSource",txtTeam1ClearAttemptsSource.getText());
		settings.setSource("Team2ClearAttemptsSource",txtTeam2ClearAttemptsSource.getText());
		settings.setSource("Team1PassCompletesSource",txtTeam1PassCompletesSource.getText());
		settings.setSource("Team2PassCompletesSource",txtTeam2PassCompletesSource.getText());
		settings.setSource("Team1ShotCompletesSource",txtTeam1ShotCompletesSource.getText());
		settings.setSource("Team2ShotCompletesSource",txtTeam2ShotCompletesSource.getText());
		settings.setSource("Team1ClearCompletesSource",txtTeam1ClearCompletesSource.getText());
		settings.setSource("Team2ClearCompletesSource",txtTeam2ClearCompletesSource.getText());
		settings.setSource("Team1PassPercentSource",txtTeam1PassPercentSource.getText());
		settings.setSource("Team2PassPercentSource",txtTeam2PassPercentSource.getText());
		settings.setSource("Team1ShotPercentSource",txtTeam1ShotPercentSource.getText());
		settings.setSource("Team2ShotPercentSource",txtTeam2ShotPercentSource.getText());
		settings.setSource("Team1ClearPercentSource",txtTeam1ClearPercentSource.getText());
		settings.setSource("Team2ClearPercentSource",txtTeam2ClearPercentSource.getText());
		settings.setSource("Team1ScoringSource",txtTeam1ScoringSource.getText());
		settings.setSource("Team2ScoringSource",txtTeam2ScoringSource.getText());
		settings.setSource("Team1ThreeBarScoringSource",txtTeam1ThreeBarScoringSource.getText());
		settings.setSource("Team2ThreeBarScoringSource",txtTeam2ThreeBarScoringSource.getText());
		settings.setSource("Team1FiveBarScoringSource",txtTeam1FiveBarScoringSource.getText());
		settings.setSource("Team2FiveBarScoringSource",txtTeam2FiveBarScoringSource.getText());
		settings.setSource("Team1TwoBarScoringSource",txtTeam1TwoBarScoringSource.getText());
		settings.setSource("Team2TwoBarScoringSource",txtTeam2TwoBarScoringSource.getText());
		settings.setSource("Team1ShotsOnGoalSource",txtTeam1ShotsOnGoalSource.getText());
		settings.setSource("Team2ShotsOnGoalSource",txtTeam2ShotsOnGoalSource.getText());
		try {
			settings.saveStatsSourceConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public void setLayout(Settings settings) {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton btnSelectPath = new JButton(Messages.getString("StatSourcesPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("StatSourcesPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(StatSourcesPanel.this);
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
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveStatsSourceConfig();
					} catch (IOException ex) {
						logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile")); //$NON-NLS-1$
						logger.error(ex.toString());
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveStatsSourceConfig();
		    	} catch (IOException ex) {
		    		logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
		    		logger.error(ex.toString());
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveStatsSourceConfig();
			    	} catch (IOException ex) {
			    		logger.error(Messages.getString("StatSourcesPanel.ErrorSavingPropertiesFile"));		 //$NON-NLS-1$
			    		logger.error(ex.toString());
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

		JLabel lblSource = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		add(lblSource, "cell 2 1,alignx left"); //$NON-NLS-1$

		JLabel lblSourceCol2 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol2, "cell 5 1,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblSourceCol3 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol3, "cell 8 1,alignx left"); //$NON-NLS-1$
		
		JLabel lblSourceCol4 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		add(lblSourceCol4, "cell 11 1"); //$NON-NLS-1$

		JLabel lblTeam1PassAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team1PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassAttemptsSource, "cell 7 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassAttemptsSource = new JTextField();
		txtTeam1PassAttemptsSource.setText(settings.getPassAttemptsSource(1));
		add(txtTeam1PassAttemptsSource, "cell 8 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassAttemptsSource.setColumns(20);
		
		JLabel lblTeam1PassPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team1PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassPercentSource, "cell 10 2,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassPercentSource = new JTextField();
		txtTeam1PassPercentSource.setText(settings.getPassPercentSource(1));
		add(txtTeam1PassPercentSource, "cell 11 2,alignx left"); //$NON-NLS-1$
		txtTeam1PassPercentSource.setColumns(20);

		JLabel lblTeam2NameSource = new JLabel(Messages.getString("StatSourcesPanel.Team2", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2NameSource, "cell 1 3,alignx right"); //$NON-NLS-1$

		JLabel lblTeam1PassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team1PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1PassCompletesSource, "cell 7 3,alignx right"); //$NON-NLS-1$
		
		txtTeam1PassCompletesSource = new JTextField();
		txtTeam1PassCompletesSource.setText(settings.getPassCompletesSource(1));
		add(txtTeam1PassCompletesSource, "cell 8 3,alignx left"); //$NON-NLS-1$
		txtTeam1PassCompletesSource.setColumns(20);
		
		JLabel lblTeam2PassPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team2PassPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassPercentSource, "cell 10 3,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassPercentSource = new JTextField();
		txtTeam2PassPercentSource.setText(settings.getPassPercentSource(2));
		add(txtTeam2PassPercentSource, "cell 11 3,alignx left,aligny top"); //$NON-NLS-1$
		txtTeam2PassPercentSource.setColumns(20);

		JLabel lblTeam2PassAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team2PassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassAttemptsSource, "cell 7 4,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassAttemptsSource = new JTextField();
		txtTeam2PassAttemptsSource.setText(settings.getPassAttemptsSource(2));
		add(txtTeam2PassAttemptsSource, "cell 8 4,alignx left"); //$NON-NLS-1$
		txtTeam2PassAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ShotPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotPercentSource, "cell 10 4,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotPercentSource = new JTextField();
		txtTeam1ShotPercentSource.setText(settings.getShotPercentSource(1));
		add(txtTeam1ShotPercentSource, "cell 11 4,alignx left"); //$NON-NLS-1$
		txtTeam1ShotPercentSource.setColumns(20);
		
		JLabel lblTeam2PassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team2PassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2PassCompletesSource, "cell 7 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2PassCompletesSource = new JTextField();
		txtTeam2PassCompletesSource.setText(settings.getPassCompletesSource(2));
		add(txtTeam2PassCompletesSource, "cell 8 5,alignx left"); //$NON-NLS-1$
		txtTeam2PassCompletesSource.setColumns(20);
		
		JLabel lblTeam2ShotPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ShotPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotPercentSource, "cell 10 5,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotPercentSource = new JTextField();
		txtTeam2ShotPercentSource.setText(settings.getShotPercentSource(2));
		add(txtTeam2ShotPercentSource, "cell 11 5,growx"); //$NON-NLS-1$
		txtTeam2ShotPercentSource.setColumns(20);

		JLabel lblTeam1ShotAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotAttemptsSource, "cell 7 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotAttemptsSource = new JTextField();
		txtTeam1ShotAttemptsSource.setText(settings.getShotAttemptsSource(1));
		add(txtTeam1ShotAttemptsSource, "cell 8 6,alignx left"); //$NON-NLS-1$
		txtTeam1ShotAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ClearPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearPercentSource, "cell 10 6,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearPercentSource = new JTextField();
		txtTeam1ClearPercentSource.setText(settings.getClearPercentSource(1));
		add(txtTeam1ClearPercentSource, "cell 11 6,alignx left"); //$NON-NLS-1$
		txtTeam1ClearPercentSource.setColumns(20);

		JLabel lblTeam1ShotCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ShotCompletesSource, "cell 7 7,alignx right"); //$NON-NLS-1$
		
		txtTeam1ShotCompletesSource = new JTextField();
		txtTeam1ShotCompletesSource.setText(settings.getShotCompletesSource(1));
		add(txtTeam1ShotCompletesSource, "cell 8 7,alignx left"); //$NON-NLS-1$
		txtTeam1ShotCompletesSource.setColumns(20);
		
		JLabel lblTeam2ClearPercentSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ClearPercent", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearPercentSource, "cell 10 7,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearPercentSource = new JTextField();
		txtTeam2ClearPercentSource.setText(settings.getClearPercentSource(2));
		add(txtTeam2ClearPercentSource, "cell 11 7,alignx left"); //$NON-NLS-1$
		txtTeam2ClearPercentSource.setColumns(20);

		JLabel lblTeam2ShotAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ShotAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotAttemptsSource, "cell 7 8,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotAttemptsSource = new JTextField();
		txtTeam2ShotAttemptsSource.setText(settings.getShotAttemptsSource(2));
		add(txtTeam2ShotAttemptsSource, "cell 8 8,alignx left"); //$NON-NLS-1$
		txtTeam2ShotAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team1Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ScoringSource, "cell 10 8,alignx right"); //$NON-NLS-1$
		
		txtTeam1ScoringSource = new JTextField();
		txtTeam1ScoringSource.setText(settings.getScoringSource(1));
		add(txtTeam1ScoringSource, "cell 11 8,alignx left"); //$NON-NLS-1$
		txtTeam1ScoringSource.setColumns(10);

		JLabel lblTeam2ShotCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ShotCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ShotCompletesSource, "cell 7 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ShotCompletesSource = new JTextField();
		txtTeam2ShotCompletesSource.setText(settings.getShotCompletesSource(2));
		add(txtTeam2ShotCompletesSource, "cell 8 9,alignx left"); //$NON-NLS-1$
		txtTeam2ShotCompletesSource.setColumns(20);
		
		JLabel lblTeam2ScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team2Scoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ScoringSource, "cell 10 9,alignx right"); //$NON-NLS-1$
		
		txtTeam2ScoringSource = new JTextField();
		txtTeam2ScoringSource.setText(settings.getScoringSource(2));
		add(txtTeam2ScoringSource, "cell 11 9,alignx left"); //$NON-NLS-1$
		txtTeam2ScoringSource.setColumns(10);

		JLabel lblTeam1ClearAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearAttemptsSource, "cell 7 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearAttemptsSource = new JTextField();
		txtTeam1ClearAttemptsSource.setText(settings.getClearAttemptsSource(1));
		add(txtTeam1ClearAttemptsSource, "cell 8 10,alignx left"); //$NON-NLS-1$
		txtTeam1ClearAttemptsSource.setColumns(20);
		
		JLabel lblTeam1ThreeBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team13BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ThreeBarScoringSource, "cell 10 10,alignx right"); //$NON-NLS-1$
		
		txtTeam1ThreeBarScoringSource = new JTextField();
		txtTeam1ThreeBarScoringSource.setText(settings.getThreeBarScoringSource(1));
		add(txtTeam1ThreeBarScoringSource, "cell 11 10,alignx left"); //$NON-NLS-1$
		txtTeam1ThreeBarScoringSource.setColumns(10);

		JLabel lblTeam1ClearCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team1ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ClearCompletesSource, "cell 7 11,alignx right"); //$NON-NLS-1$
		
		txtTeam1ClearCompletesSource = new JTextField();
		txtTeam1ClearCompletesSource.setText(settings.getClearCompletesSource(1));
		add(txtTeam1ClearCompletesSource, "cell 8 11,alignx left"); //$NON-NLS-1$
		txtTeam1ClearCompletesSource.setColumns(20);
		
		JLabel lblTeam2ThreeBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team23BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ThreeBarScoringSource, "cell 10 11,alignx right"); //$NON-NLS-1$
		
		txtTeam2ThreeBarScoringSource = new JTextField();
		txtTeam2ThreeBarScoringSource.setText(settings.getThreeBarScoringSource(2));
		add(txtTeam2ThreeBarScoringSource, "cell 11 11,alignx left"); //$NON-NLS-1$
		txtTeam2ThreeBarScoringSource.setColumns(10);

		JLabel lblTeam2ClearAttemptsSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ClearAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearAttemptsSource, "cell 7 12,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearAttemptsSource = new JTextField();
		txtTeam2ClearAttemptsSource.setText(settings.getClearAttemptsSource(2));
		add(txtTeam2ClearAttemptsSource, "cell 8 12,alignx left"); //$NON-NLS-1$
		txtTeam2ClearAttemptsSource.setColumns(20);
		
		JLabel lblTeambar = new JLabel(Messages.getString("StatSourcesPanel.Team15BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar, "cell 10 12,alignx right"); //$NON-NLS-1$
		
		txtTeam1FiveBarScoringSource = new JTextField();
		txtTeam1FiveBarScoringSource.setText(settings.getFiveBarScoringSource(1));
		add(txtTeam1FiveBarScoringSource, "cell 11 12,alignx left"); //$NON-NLS-1$
		txtTeam1FiveBarScoringSource.setColumns(10);

		JLabel lblStuffs1Source = new JLabel(Messages.getString("StatSourcesPanel.Stuffs1", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs1Source, "cell 4 14,alignx right"); //$NON-NLS-1$
		
		txtStuffs1Source = new JTextField();
		txtStuffs1Source.setText(settings.getStuffsSource(1));
		add(txtStuffs1Source, "cell 5 14,alignx left"); //$NON-NLS-1$
		txtStuffs1Source.setColumns(10);

		JLabel lblTeam2ClearCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team2ClearCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ClearCompletesSource, "cell 7 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2ClearCompletesSource = new JTextField();
		txtTeam2ClearCompletesSource.setText(settings.getClearCompletesSource(2));
		add(txtTeam2ClearCompletesSource, "cell 8 13,alignx left"); //$NON-NLS-1$
		txtTeam2ClearCompletesSource.setColumns(20);

		JLabel lblTeam1SOG = new JLabel(Messages.getString("StatSourcesPanel.Team1ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SOG, "cell 10 16,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1ShotsOnGoalSource = new JTextField();
		txtTeam1ShotsOnGoalSource.setText(settings.getShotsOnGoalSource(1));
		add(txtTeam1ShotsOnGoalSource, "cell 11 16,alignx left"); //$NON-NLS-1$
		txtTeam1ShotsOnGoalSource.setColumns(10);
		
		JLabel lblTeam2SOG = new JLabel(Messages.getString("StatSourcesPanel.Team2ShotsOnGoal", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SOG, "cell 10 17,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2ShotsOnGoalSource = new JTextField();
		txtTeam2ShotsOnGoalSource.setText(settings.getShotsOnGoalSource(2));
		add(txtTeam2ShotsOnGoalSource, "cell 11 17,alignx left"); //$NON-NLS-1$
		txtTeam2ShotsOnGoalSource.setColumns(10);
		
		JLabel lblTeambar_1 = new JLabel(Messages.getString("StatSourcesPanel.Team25BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeambar_1, "cell 10 13,alignx right"); //$NON-NLS-1$
		
		txtTeam2FiveBarScoringSource = new JTextField();
		txtTeam2FiveBarScoringSource.setText(settings.getFiveBarScoringSource(2));
		add(txtTeam2FiveBarScoringSource, "cell 11 13,alignx left"); //$NON-NLS-1$
		txtTeam2FiveBarScoringSource.setColumns(10);
		
		JLabel lblStuffs2Source = new JLabel(Messages.getString("StatSourcesPanel.Stuffs2", settings.getGameType())); //$NON-NLS-1$
		add(lblStuffs2Source, "cell 4 15,alignx right"); //$NON-NLS-1$
		
		txtStuffs2Source = new JTextField();
		txtStuffs2Source.setText(settings.getStuffsSource(2));
		add(txtStuffs2Source, "cell 5 15,alignx left"); //$NON-NLS-1$
		txtStuffs2Source.setColumns(10);
		
		JLabel lblTeam1TwoBarPassAttemptsSource_1 = new JLabel(Messages.getString("StatSourcesPanel.Team12BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassAttemptsSource_1, "cell 7 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassAttemptsSource = new JTextField();
		txtTeam1TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource(1));
		txtTeam1TwoBarPassAttemptsSource.setColumns(20);
		add(txtTeam1TwoBarPassAttemptsSource, "cell 8 14,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam1TwoBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team12BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarScoringSource, "cell 10 14,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarScoringSource = new JTextField();
		txtTeam1TwoBarScoringSource.setText(settings.getTwoBarScoringSource(1));
		add(txtTeam1TwoBarScoringSource, "cell 11 14,alignx left"); //$NON-NLS-1$
		txtTeam1TwoBarScoringSource.setColumns(10);
		
		JLabel lblBreaks1Source = new JLabel(Messages.getString("StatSourcesPanel.Breaks1", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks1Source, "cell 4 16,alignx right"); //$NON-NLS-1$
		
		txtBreaks1Source = new JTextField();
		txtBreaks1Source.setText(settings.getBreaksSource(1));
		add(txtBreaks1Source, "cell 5 16,alignx left"); //$NON-NLS-1$
		txtBreaks1Source.setColumns(10);
		
		JLabel lblTeam1TwoBarPassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team12BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TwoBarPassCompletesSource, "cell 7 15,alignx right"); //$NON-NLS-1$
		
		txtTeam1TwoBarPassCompletesSource = new JTextField();
		txtTeam1TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource(1));
		txtTeam1TwoBarPassCompletesSource.setColumns(20);
		add(txtTeam1TwoBarPassCompletesSource, "cell 8 15,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarScoringSource = new JLabel(Messages.getString("StatSourcesPanel.Team22BarScoring", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarScoringSource, "cell 10 15,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarScoringSource = new JTextField();
		txtTeam2TwoBarScoringSource.setText(settings.getTwoBarScoringSource(2));
		add(txtTeam2TwoBarScoringSource, "cell 11 15,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarScoringSource.setColumns(10);
		
		JLabel lblBreaks2Source = new JLabel(Messages.getString("StatSourcesPanel.Breaks2", settings.getGameType())); //$NON-NLS-1$
		add(lblBreaks2Source, "cell 4 17,alignx right"); //$NON-NLS-1$
		
		txtBreaks2Source = new JTextField();
		txtBreaks2Source.setText(settings.getBreaksSource(2));
		add(txtBreaks2Source, "cell 5 17,alignx left"); //$NON-NLS-1$
		txtBreaks2Source.setColumns(10);
		
		JLabel lblAces1Source = new JLabel(Messages.getString("StatSourcesPanel.Aces1", settings.getGameType())); //$NON-NLS-1$
		add(lblAces1Source, "cell 4 12,alignx right"); //$NON-NLS-1$
		
		txtAces1Source = new JTextField();
		txtAces1Source.setText(settings.getAcesSource(1));
		add(txtAces1Source, "cell 5 12,alignx left"); //$NON-NLS-1$
		txtAces1Source.setColumns(10);
		
		JLabel lblAces2Source = new JLabel(Messages.getString("StatSourcesPanel.Aces2", settings.getGameType())); //$NON-NLS-1$
		add(lblAces2Source, "cell 4 13,alignx right"); //$NON-NLS-1$
		
		txtAces2Source = new JTextField();
		txtAces2Source.setText(settings.getAcesSource(2));
		add(txtAces2Source, "cell 5 13,alignx left"); //$NON-NLS-1$
		txtAces2Source.setColumns(10);
		
		JLabel lblTeam2TwoBarPassAttemptsSource_1_1 = new JLabel(Messages.getString("StatSourcesPanel.Team22BarPassAttempts", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassAttemptsSource_1_1, "cell 7 16,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassAttemptsSource = new JTextField();
		txtTeam2TwoBarPassAttemptsSource.setText(settings.getTwoBarPassAttemptsSource(2));
		txtTeam2TwoBarPassAttemptsSource.setColumns(20);
		add(txtTeam2TwoBarPassAttemptsSource, "cell 8 16,alignx left"); //$NON-NLS-1$
		
		JLabel lblTeam2TwoBarPassCompletesSource = new JLabel(Messages.getString("StatSourcesPanel.Team22BarPassCompletes", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TwoBarPassCompletesSource, "cell 7 17,alignx right"); //$NON-NLS-1$
		
		txtTeam2TwoBarPassCompletesSource = new JTextField();
		txtTeam2TwoBarPassCompletesSource.setText(settings.getTwoBarPassCompletesSource(2));
		txtTeam2TwoBarPassCompletesSource.setColumns(20);
		add(txtTeam2TwoBarPassCompletesSource, "cell 8 17,alignx left"); //$NON-NLS-1$

		JButton btnSaveSources = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveSources, "cell 2 19,alignx center"); //$NON-NLS-1$
		
		JButton btnCancelSources = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelSources, "cell 4 19,alignx center"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
}
