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
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.midsouthfoosball.foosobsplus.main.Main;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame implements WindowListener {
	
	private TablePanel tablePanel;
	private TeamPanel team1Panel;
	private TeamPanel team2Panel;
	private SwitchPanel switchPanel;
	private ResetPanel resetPanel;
	private TimerPanel timerPanel;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private MatchPanel matchPanel;
	public ParametersFrame parametersFrame;
	public HotKeysFrame hotKeysFrame;
	public FileNamesFrame fileNamesFrame;
	private Main main;
	private JCheckBoxMenuItem viewTimerWindow;
	private JCheckBoxMenuItem viewAlwaysOnTop;
	private JCheckBoxMenuItem viewLastScored1Window;
	private JCheckBoxMenuItem viewLastScored2Window;
	private JCheckBoxMenuItem viewGameTableWindow;
	private JCheckBoxMenuItem viewAllWindows;
	private JCheckBoxMenuItem helpShowParsed;
	
	public MainFrame(TablePanel tablePanel, TimerPanel timerPanel, TeamPanel team1Panel, TeamPanel team2Panel, StatsEntryPanel statsEntryPanel,
			SwitchPanel switchPanel, ResetPanel resetPanel, StatsDisplayPanel statsDisplayPanel, MatchPanel matchPanel, ParametersFrame parametersFrame, HotKeysFrame hotKeysFrame,
			FileNamesFrame fileNamesFrame, Main main) {

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
		this.parametersFrame = parametersFrame;
		this.hotKeysFrame = hotKeysFrame;
		this.fileNamesFrame = fileNamesFrame;
		this.main = main;
		
		viewLastScored2Window = new JCheckBoxMenuItem("Team 2 Last Scored Window");
		viewLastScored1Window = new JCheckBoxMenuItem("Team 1 Last Scored Window");
		viewTimerWindow = new JCheckBoxMenuItem("Timer Window");
		viewGameTableWindow = new JCheckBoxMenuItem("Game Table Window");
		viewAllWindows = new JCheckBoxMenuItem("Show All Windows");
		viewAlwaysOnTop = new JCheckBoxMenuItem("Always on Top");
		helpShowParsed = new JCheckBoxMenuItem("Show Parsed");
		
		setLayout(new GridBagLayout());
		
		setJMenuBar(createMenuBar());
		layoutComponents();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				dispose();
				System.gc();
			}
		});

//		addWindowListener(this); //not sure why this is here
		
		setSize(1350,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportItem = new JMenuItem("Export Stats");
		JMenuItem importItem = new JMenuItem("Import Stats");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(importItem);
		fileMenu.add(exportItem);
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
		viewMenu.add(viewLastScored1Window);
		viewMenu.add(viewLastScored2Window);
		viewMenu.add(viewGameTableWindow);
		viewMenu.add(viewAllWindows);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem helpPage = new JMenuItem("FoosOBSPlus Help");
		JMenuItem helpAbout = new JMenuItem("About");
		
		helpMenu.add(helpPage);
		helpMenu.add(helpShowParsed);
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
				parametersFrame.setVisible(true);
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
					URI helpURI = new URI("https://github.com/hsgarn/foosOBSPlus#foosOBSPlus");
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

		helpShowParsed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				main.setIsShowParsed(helpShowParsed.isSelected());
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
		
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Select file to import:");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(true);

				int returnVal = chooser.showOpenDialog(MainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileNameAndPath = chooser.getSelectedFile().toString();
					importStatsFile(fileNameAndPath);
				} else {
					System.out.println("Cancelled by user");
				}

			}
		});
		
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Select file to export:");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(true);

				int returnVal = chooser.showOpenDialog(MainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileNameAndPath = chooser.getSelectedFile().toString();
					exportStatsFile(fileNameAndPath);
				} else {
					System.out.println("Cancelled by user");
				}

			}
		});
		return menuBar;
	}
	
	private void importStatsFile(String file) {
		List<String> lines = Collections.emptyList();
		try {lines = Files.readAllLines(Paths.get(file)); }
		catch (IOException e) {
			System.out.println("Error reading file " + file);
			e.printStackTrace();
		}
		lines.forEach(line -> main.processCode(line.toUpperCase(), false));
	}
	
	private void exportStatsFile(String file) {
		List<String> codes = main.getCodeHistory();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for(String code:codes) {
				bw.write(code);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error writing file " + file);
			e.printStackTrace();
		}
	}
	public boolean getGameTableWindowSelected() {
		return viewGameTableWindow.isSelected();
	}
	public boolean getTimerWindowSelected() {
		return viewTimerWindow.isSelected();
	}
	public boolean getLastScored1WindowSelected() { 
		return viewLastScored1Window.isSelected();
	}
	public boolean getLastScored2WindowSelected() { 
		return viewLastScored2Window.isSelected();
	}
	public boolean getAllWindowsSelected() {
		return viewAllWindows.isSelected();
	}
	public void setGameTableWindowSelected(Boolean state) {
		viewGameTableWindow.setSelected(state);
	}
	public void setTimerWindowSelected(Boolean state) {
		viewTimerWindow.setSelected(state);
	}
	public void setLastScored1WindowSelected(Boolean state) { 
		viewLastScored1Window.setSelected(state);
	}
	public void setLastScored2WindowSelected(Boolean state) {
		viewLastScored2Window.setSelected(state);
	}
	public void setAllWindowsSelected(Boolean state) {
		viewAllWindows.setSelected(state);
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
		tablePanel.setPreferredSize(new Dimension(400,200));
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
		timerPanel.setPreferredSize(new Dimension(250,400));
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
		statsEntryPanel.setPreferredSize(new Dimension(400,400));
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
		statsDisplayPanel.setPreferredSize(new Dimension(300,400));
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
		matchPanel.setPreferredSize(new Dimension(400,250));
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
		team1Panel.setPreferredSize(new Dimension(400,400));
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
		switchPanel.setPreferredSize(new Dimension(250,400));
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
		team2Panel.setPreferredSize(new Dimension(400,400));
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
		resetPanel.setPreferredSize(new Dimension(300,400));
		add(resetPanel, gc);
	}
	
	////// Listeners //////
	
	public void addTimerWindowListener(ActionListener listenForChkBoxTimerWindow) {
		viewTimerWindow.addActionListener(listenForChkBoxTimerWindow);
	}
	public void addLastScored1WindowListener(ActionListener listenForChkBoxLastScored1Window) {
		viewLastScored1Window.addActionListener(listenForChkBoxLastScored1Window);
	}
	public void addLastScored2WindowListener(ActionListener listenForChkBoxLastScored2Window) {
		viewLastScored2Window.addActionListener(listenForChkBoxLastScored2Window);
	}
	public void addGameTableWindowListener(ActionListener listenForChkBoxGameTableWindow) {
		viewGameTableWindow.addActionListener(listenForChkBoxGameTableWindow);
	}
	public void addViewAllWindowsListener(ActionListener listenForChkBoxViewAllWindows) {
		viewAllWindows.addActionListener(listenForChkBoxViewAllWindows);
	}
	public void addViewTimerWindowItemListener(ItemListener listenForViewTimerWindowItem) {
		viewTimerWindow.addItemListener(listenForViewTimerWindowItem);
	}
	public void addLastScored1WindowItemListener(ItemListener listenForLastScored1WindowItem) {
		viewLastScored1Window.addItemListener(listenForLastScored1WindowItem);
	}
	public void addLastScored2WindowItemListener(ItemListener listenForLastScored2WindowItem) {
		viewLastScored2Window.addItemListener(listenForLastScored2WindowItem);
	}
	public void addGameTableWindowItemListener(ItemListener listenForGameTableWindowItem) {
		viewGameTableWindow.addItemListener(listenForGameTableWindowItem);
	}
	
	///// Utility Methods \\\\\\
	public HotKeysPanel getHotKeysPanel() {
		return hotKeysFrame.getHotKeysPanel();
	}
	public ParametersPanel getSettingsPanel() {
		return parametersFrame.getSettingsPanel();
	}
	public StatsEntryPanel getStatsEntryPanel() {
		return statsEntryPanel;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		statsEntryPanel.setFocusOnCode();
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}

