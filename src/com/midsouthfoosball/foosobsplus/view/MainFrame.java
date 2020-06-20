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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame {
	
	private TablePanel tablePanel;
	private TeamPanel team1Panel;
	private TeamPanel team2Panel;
	private SwitchPanel switchPanel;
	private ResetPanel resetPanel;
	private TimerPanel timerPanel;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private MatchPanel matchPanel;
	public SettingsFrame settingsFrame;
	public HotKeysFrame hotKeysFrame;
	public FileNamesFrame fileNamesFrame;
	private JCheckBoxMenuItem viewTimerWindow;
	private JCheckBoxMenuItem viewAlwaysOnTop;
	
	public MainFrame(TablePanel tablePanel, TimerPanel timerPanel, TeamPanel team1Panel, TeamPanel team2Panel, StatsEntryPanel statsEntryPanel,
			SwitchPanel switchPanel, ResetPanel resetPanel, StatsDisplayPanel statsDisplayPanel, MatchPanel matchPanel, SettingsFrame settingsFrame, HotKeysFrame hotKeysFrame,
			FileNamesFrame fileNamesFrame) {

		super("FoosOBSPlus");

		this.tablePanel = tablePanel;
		this.timerPanel = timerPanel;
		this.team1Panel = team1Panel;
		this.team2Panel = team2Panel;
		this.statsEntryPanel = statsEntryPanel;
		this.switchPanel = switchPanel;
		this.resetPanel = resetPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.matchPanel = matchPanel;
		this.settingsFrame = settingsFrame;
		this.hotKeysFrame = hotKeysFrame;
		this.fileNamesFrame = fileNamesFrame;
		
		 viewTimerWindow = new JCheckBoxMenuItem("Timer Window");
		 viewAlwaysOnTop = new JCheckBoxMenuItem("Always on Top");
				
		setLayout(new GridBagLayout());
		
		setJMenuBar(createMenuBar());
		layoutComponents();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				dispose();
				System.gc();
			}
		});

		setSize(1300,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu editMenu = new JMenu("Edit");
		
		JMenu settingsMenu = new JMenu("Settings");
		JMenuItem settingsParamItem = new JMenuItem("Parameters");
		JMenuItem settingsHotKeyItem = new JMenuItem("Hot Keys");
		JMenuItem settingsFileItem = new JMenuItem("File Names");
		JMenuItem settingsStatItem = new JMenuItem("Statistics");
		
		settingsMenu.add(settingsParamItem);
		settingsMenu.add(settingsHotKeyItem);
		settingsMenu.add(settingsFileItem);
		settingsMenu.add(settingsStatItem);
		editMenu.add(settingsMenu);
		
		JMenu viewMenu = new JMenu("View");
		
		viewMenu.add(viewAlwaysOnTop);
		viewMenu.add(viewTimerWindow);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem helpPage = new JMenuItem("FoosOBSPlus Help");
		JMenuItem helpAbout = new JMenuItem("About");
		
		helpMenu.add(helpPage);
		helpMenu.addSeparator();
		helpMenu.add(helpAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		viewAlwaysOnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setAlwaysOnTop(viewAlwaysOnTop.isSelected());
 				}
		});
		
		settingsParamItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				settingsFrame.setVisible(true);
			}
			});

		settingsHotKeyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				hotKeysFrame.setVisible(true);
			}
		});

		settingsFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fileNamesFrame.setVisible(true);
			}
		});

		helpPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					URI helpURI = new URI("https://github.com/hsgarn/foosOBS");
					try {
						java.awt.Desktop.getDesktop().browse(helpURI);
					} catch (IOException ex) {
						System.out.print("Error calling URI: " + ex.getMessage());		
					}
		    	} catch (URISyntaxException ex) {
					System.out.print("Error in URI Syntax: " + ex.getMessage());		
				}
			}
		});

		helpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame aboutFrame = new JFrame("FoosOBSPlus About");
				aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				AboutPanel ap;
				aboutFrame.setAlwaysOnTop(true);
				ap = new AboutPanel(aboutFrame);
				ap.setPreferredSize(new Dimension(700, 500));

				aboutFrame.getContentPane().add(ap);
				aboutFrame.pack();
				aboutFrame.setVisible(true);
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener: listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));

					}
				}
			}
		});
		
		return menuBar;
		
	}
	
	private void layoutComponents() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = -1;

		//////// Table Panel ////////
		
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(tablePanel, gc);
		
		//////// Timer Panel ////////
		
		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 1;
		gc.gridwidth =1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(timerPanel, gc);

		//////// Stats Entry Panel ////////
		
		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 2;
		gc.gridwidth =1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(statsEntryPanel, gc);
		gc.gridheight = 1;
		
		//////// Stats Display Panel ////////
		
		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 3;
		gc.gridwidth =1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(statsDisplayPanel, gc);
		
		////////// Match Panel  ///////////
		
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(matchPanel, gc);

		////////// Team 1 Panel ///////////
		
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(team1Panel, gc);
		
		////////// Switch Panel ///////////

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 1;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(switchPanel, gc);

		//////// Team 2 Panel ////////

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 2;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(team2Panel, gc);

		//////// Reset Panel ////////

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 3;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(resetPanel, gc);
	}
	
	////// Listeners //////
	
	public void addTimerWindowListener(ActionListener listenForChkBoxTimerWindow) {
		viewTimerWindow.addActionListener(listenForChkBoxTimerWindow);
	}
	
	///// Utility Methods \\\\\\
	public HotKeysPanel getHotKeysPanel() {
		return hotKeysFrame.getHotKeysPanel();
	}
	public SettingsPanel getSettingsPanel() {
		return settingsFrame.getSettingsPanel();
	}
	public StatsEntryPanel getStatsEntryPanel() {
		return statsEntryPanel;
	}
/*	public void packFrames() {
		SwingUtilities.updateComponentTreeUI(this);
		this.pack();
	}
*/
}

