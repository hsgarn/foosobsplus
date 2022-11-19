/**
Copyright 2020, 2021, 2022 Hugh Garner
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
import java.awt.Image;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.midsouthfoosball.foosobsplus.main.Main;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame implements WindowListener {
	
	private TablePanel tablePanel;
	private TeamPanel team1Panel;
	private TeamPanel team2Panel;
	private SwitchPanel switchPanel;
	private ResetPanel resetPanel;
	private TimerPanel timerPanel;
	private OBSPanel obsPanel;
	private AutoScoreMainPanel autoScoreMainPanel;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private MatchPanel matchPanel;
	private ParametersFrame parametersFrame;
	private HotKeysFrame hotKeysFrame;
	private SourcesFrame sourcesFrame;
	private FileNamesFrame fileNamesFrame;
	private PartnerProgramFrame partnerProgramFrame;
	private OBSConnectFrame obsConnectFrame;
	private AutoScoreSettingsFrame autoScoreSettingsFrame;
	private AutoScoreConfigFrame autoScoreConfigFrame;
	private Main main;
	private JCheckBoxMenuItem viewTimerWindow;
	private JCheckBoxMenuItem viewAlwaysOnTop;
	private JCheckBoxMenuItem viewLastScored1Window;
	private JCheckBoxMenuItem viewLastScored2Window;
	private JCheckBoxMenuItem viewGameTableWindow;
	private JCheckBoxMenuItem viewAllWindows;
	private JCheckBoxMenuItem helpShowParsed;
	private JCheckBoxMenuItem showScores;
	private JMenuItem obsConnectItem;
	private JMenuItem obsDisconnectItem;
	private JMenu obsMenu;
	private JMenu autoScoreMenu;
	private JMenuItem autoScoreSettingsItem;
	private JMenuItem autoScoreConfigItem;
	private ImageIcon imgOBSConnected;
	private ImageIcon imgOBSDisconnected;
	private Icon imgIconOBSConnected;
	private Icon imgIconOBSDisconnected;
	private ImageIcon imgAutoScoreConnected;
	private ImageIcon imgAutoScoreDisconnected;
	private Icon imgIconAutoScoreConnected;
	private Icon imgIconAutoScoreDisconnected;	
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public MainFrame(Settings settings, TablePanel tablePanel, TimerPanel timerPanel, OBSPanel obsPanel, AutoScoreMainPanel autoScoreMainPanel, TeamPanel team1Panel, TeamPanel team2Panel, StatsEntryPanel statsEntryPanel,
			SwitchPanel switchPanel, ResetPanel resetPanel, StatsDisplayPanel statsDisplayPanel, MatchPanel matchPanel, ParametersFrame parametersFrame, HotKeysFrame hotKeysFrame,
			SourcesFrame sourcesFrame, FileNamesFrame fileNamesFrame, PartnerProgramFrame partnerProgramFrame, OBSConnectFrame obsConnectFrame, AutoScoreSettingsFrame autoScoreSettingsFrame, AutoScoreConfigFrame autoScoreConfigFrame, Main main) {

		super(programName + ": Foosball"); //$NON-NLS-1$

		this.tablePanel 			= tablePanel;
		this.timerPanel 			= timerPanel;
		this.obsPanel           	= obsPanel;
		this.autoScoreMainPanel 	= autoScoreMainPanel;
		this.team1Panel 			= team1Panel;
		this.team2Panel 			= team2Panel;
		this.statsEntryPanel 		= statsEntryPanel;
		this.switchPanel 			= switchPanel;
		this.resetPanel 			= resetPanel;
		this.statsDisplayPanel 		= statsDisplayPanel;
		this.matchPanel 			= matchPanel;
		this.parametersFrame 		= parametersFrame;
		this.hotKeysFrame 			= hotKeysFrame;
		this.sourcesFrame			= sourcesFrame;
		this.fileNamesFrame 		= fileNamesFrame;
		this.partnerProgramFrame    = partnerProgramFrame;
		this.obsConnectFrame 		= obsConnectFrame;
		this.autoScoreSettingsFrame = autoScoreSettingsFrame;
		this.autoScoreConfigFrame 	= autoScoreConfigFrame;
		this.main 					= main;
		
		viewLastScored2Window 	= new JCheckBoxMenuItem(Messages.getString("MainFrame.Team1LastScoredWindow")); //$NON-NLS-1$
		viewLastScored1Window 	= new JCheckBoxMenuItem(Messages.getString("MainFrame.Team2LastScoredWindow")); //$NON-NLS-1$
		viewTimerWindow 		= new JCheckBoxMenuItem(Messages.getString("MainFrame.TimerWindow")); //$NON-NLS-1$
		viewGameTableWindow 	= new JCheckBoxMenuItem(Messages.getString("MainFrame.GameTableWIndow")); //$NON-NLS-1$
		viewAllWindows 			= new JCheckBoxMenuItem(Messages.getString("MainFrame.ShowAllWindows")); //$NON-NLS-1$
		viewAlwaysOnTop 		= new JCheckBoxMenuItem(Messages.getString("MainFrame.AlwaysOnTop")); //$NON-NLS-1$
		helpShowParsed 			= new JCheckBoxMenuItem(Messages.getString("MainFrame.ShowParsed")); //$NON-NLS-1$
		helpShowParsed.setSelected(settings.getShowParsed());
		setLayout(new GridBagLayout());
		
		setJMenuBar(createMenuBar());
		layoutFoosballComponents();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				dispose();
				System.gc();
			}
		});
		
		setSize(1400,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu 			= new JMenu(Messages.getString("MainFrame.File")); //$NON-NLS-1$
		JMenuItem exportItem 	= new JMenuItem(Messages.getString("MainFrame.ExportStats")); //$NON-NLS-1$
		JMenuItem importItem 	= new JMenuItem(Messages.getString("MainFrame.ImportStats")); //$NON-NLS-1$
		JMenuItem exitItem 		= new JMenuItem(Messages.getString("MainFrame.Exit")); //$NON-NLS-1$
		
		fileMenu.add(importItem);
		fileMenu.add(exportItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu editMenu 			= new JMenu(Messages.getString("MainFrame.Edit")); //$NON-NLS-1$
		
		JMenu settingsMenu 				= new JMenu(Messages.getString("MainFrame.Settings")); //$NON-NLS-1$
		JMenuItem settingsParamItem 	= new JMenuItem(Messages.getString("MainFrame.Parameters")); //$NON-NLS-1$
		JMenuItem settingsHotKeyItem 	= new JMenuItem(Messages.getString("MainFrame.HotKeys")); //$NON-NLS-1$
		JMenuItem settingsSourceItem	= new JMenuItem(Messages.getString("MainFrame.Sources")); //$NON-NLS-1$
		JMenuItem settingsFileItem 		= new JMenuItem(Messages.getString("MainFrame.FileNames")); //$NON-NLS-1$
		JMenuItem settingsPartnerProgramItem = new JMenuItem(Messages.getString("MainFrame.PartnerProgram")); //$NON-NLS-1$
		JMenuItem settingsStatItem		= new JMenuItem(Messages.getString("MainFrame.Statistics")); //$NON-NLS-1$
		
		settingsMenu.add(settingsParamItem);
		settingsMenu.add(settingsHotKeyItem);
		settingsMenu.add(settingsSourceItem);
		settingsMenu.add(settingsFileItem);
		settingsMenu.add(settingsPartnerProgramItem);
		settingsMenu.add(settingsStatItem);
		editMenu.add(settingsMenu);
		
		obsMenu 			= new JMenu(Messages.getString("MainFrame.OBS")); //$NON-NLS-1$
		obsConnectItem 		= new JMenuItem(Messages.getString("MainFrame.OBSConnect")); //$NON-NLS-1$
		obsDisconnectItem 	= new JMenuItem(Messages.getString("MainFrame.OBSDisconnect")); //$NON-NLS-1$
		showScores          = new JCheckBoxMenuItem(Messages.getString("MainFrame.ShowScores")); //$NON-NLS-1$
		
		imgOBSConnected = new ImageIcon(this.getClass().getResource("Connected.png"));; //$NON-NLS-1$
		imgOBSConnected.setImage(imgOBSConnected.getImage().getScaledInstance(12, 12,  Image.SCALE_DEFAULT));
		imgIconOBSConnected = imgOBSConnected;
		imgOBSDisconnected = new ImageIcon(this.getClass().getResource("Disconnected.png"));; //$NON-NLS-1$
		imgOBSDisconnected.setImage(imgOBSDisconnected.getImage().getScaledInstance(12, 12,  Image.SCALE_DEFAULT));
		imgIconOBSDisconnected = imgOBSDisconnected;
		
		obsMenu.add(obsConnectItem);
		obsMenu.add(obsDisconnectItem);
		obsMenu.add(showScores);
		obsMenu.setIcon(imgIconOBSDisconnected);
		
		autoScoreMenu  = new JMenu(Messages.getString("MainFrame.AutoScore")); //$NON-NLS-1$
		autoScoreSettingsItem  = new JMenuItem(Messages.getString("MainFrame.AutoScoreSettings")); //$NON-NLS-1$
		autoScoreConfigItem  = new JMenuItem(Messages.getString("MainFrame.AutoScoreConfig")); //$NON-NLS-1$
		
		imgAutoScoreConnected = new ImageIcon(this.getClass().getResource("Connected.png"));; //$NON-NLS-1$
		imgAutoScoreConnected.setImage(imgAutoScoreConnected.getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT));
		imgIconAutoScoreConnected = imgAutoScoreConnected;
		imgAutoScoreDisconnected = new ImageIcon(this.getClass().getResource("Disconnected.png"));; //$NON-NLS-1$
		imgAutoScoreDisconnected.setImage(imgOBSDisconnected.getImage().getScaledInstance(12, 12,  Image.SCALE_DEFAULT));
		imgIconAutoScoreDisconnected = imgOBSDisconnected;
		
		autoScoreMenu.add(autoScoreSettingsItem);
		autoScoreMenu.setIcon(imgIconAutoScoreDisconnected);
		autoScoreMenu.add(autoScoreConfigItem);
		
		JMenu viewMenu = new JMenu(Messages.getString("MainFrame.View")); //$NON-NLS-1$
		viewMenu.add(viewAlwaysOnTop);
		viewMenu.add(viewTimerWindow);
		viewMenu.add(viewLastScored1Window);
		viewMenu.add(viewLastScored2Window);
		viewMenu.add(viewGameTableWindow);
		viewMenu.add(viewAllWindows);
//		viewMenu.add(showScores);

		JMenu helpMenu 		= new JMenu(Messages.getString("MainFrame.Help")); //$NON-NLS-1$
		JMenuItem helpPage 	= new JMenuItem(programName + " " + Messages.getString("MainFrame.Help")); //$NON-NLS-1$ //$NON-NLS-2$
		JMenuItem helpAbout = new JMenuItem(Messages.getString("MainFrame.About")); //$NON-NLS-1$
		
		helpMenu.add(helpPage);
		helpMenu.add(helpShowParsed);
		helpMenu.addSeparator();
		helpMenu.add(helpAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(obsMenu);
		menuBar.add(autoScoreMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		obsMenu.setIcon(imgIconOBSDisconnected);
		autoScoreMenu.setIcon(imgIconAutoScoreDisconnected);
		
		showScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				main.showScores(showScores.isSelected());
			}
		});
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
		autoScoreSettingsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				autoScoreSettingsFrame.setVisible(true);
			}
		});
		autoScoreConfigItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				autoScoreConfigFrame.setVisible(true);
			}
		});

		settingsSourceItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				sourcesFrame.setVisible(true);
			}
		});
		
		settingsFileItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				fileNamesFrame.setVisible(true);
			}
		});
		
		settingsPartnerProgramItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				partnerProgramFrame.setVisible(true);
			}
		});

		obsConnectItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				obsConnectFrame.setVisible(true);
			}
		});
		
		helpPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					URI helpURI = new URI("https://github.com/hsgarn/foosOBSPlus#foosOBSPlus"); //$NON-NLS-1$
					try {
						java.awt.Desktop.getDesktop().browse(helpURI);
					} catch (IOException ex) {
						System.out.print(Messages.getString("Errors.URICallingError") + ex.getMessage());		 //$NON-NLS-1$
					}
		    	} catch (URISyntaxException ex) {
					System.out.print(Messages.getString("Errors.URISyntaxError") + ex.getMessage());		 //$NON-NLS-1$
				}
			}
		});

		helpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame aboutFrame = new JFrame(programName + " " + Messages.getString("MainFrame.About")); //$NON-NLS-1$ //$NON-NLS-2$
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
				main.setShowParsed(helpShowParsed.isSelected());
 			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						Messages.getString("MainFrame.QuestionExit"), Messages.getString("MainFrame.ConfirmExit"), JOptionPane.OK_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
				
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
				chooser.setDialogTitle(Messages.getString("MainFrame.SelectImportFile")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(true);

				int returnVal = chooser.showOpenDialog(MainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileNameAndPath = chooser.getSelectedFile().toString();
					importStatsFile(fileNameAndPath);
				} else {
					System.out.println(Messages.getString("MainFrame.CancelledByUser")); //$NON-NLS-1$
				}

			}
		});
		
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle(Messages.getString("MainFrame.SelectExportFile")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(true);

				int returnVal = chooser.showOpenDialog(MainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileNameAndPath = chooser.getSelectedFile().toString();
					exportStatsFile(fileNameAndPath);
				} else {
					System.out.println(Messages.getString("MainFrame.CancelledByUser")); //$NON-NLS-1$
				}

			}
		});
		return menuBar;
	}
	public void setOBSIconConnected(Boolean connected) {
		if (connected) {
			obsMenu.setIcon(imgIconOBSConnected);
		} else {
			obsMenu.setIcon(imgIconOBSDisconnected);
		}
	}
	public void setAutoScoreIconConnected(Boolean connected) {
		if (connected) {
			autoScoreMenu.setIcon(imgIconAutoScoreConnected);
		} else {
			autoScoreMenu.setIcon(imgIconAutoScoreDisconnected);
		}
	}
	public void enableConnect(Boolean state) {
//		obsConnectItem.setEnabled(state);  // Leaving Connect... option always enabled.
		obsDisconnectItem.setEnabled(!state);
	}
	
	private void importStatsFile(String file) {
		List<String> lines = Collections.emptyList();
		try {lines = Files.readAllLines(Paths.get(file)); }
		catch (IOException e) {
			System.out.println(Messages.getString("Errors.ReadFileError") + file); //$NON-NLS-1$
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
			System.out.println(Messages.getString("Errors.WriteFileError") + file); //$NON-NLS-1$
			e.printStackTrace();
		}
	}
	public void closeWindow() {
		super.dispose();
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
	private void layoutFoosballComponents() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = -1;

		//////// Table Panel ////////
		
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		tablePanel.setPreferredSize(new Dimension(400,200));
		add(tablePanel, gc);
		
		//////// Timer Panel ////////
		
		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 1;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		timerPanel.setPreferredSize(new Dimension(250,200));
		add(timerPanel, gc);

		//////// Stats Entry Panel ////////
		
		gc.weightx = .5;
		gc.weighty = 1;
		
		gc.gridx = 2;
		gc.gridwidth =1;
		gc.gridheight = 4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(statsEntryPanel, gc);
		statsEntryPanel.setPreferredSize(new Dimension(400,350));
		gc.gridheight = 1;
		
		//////// Stats Display Panel ////////
		
		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 3;
		gc.gridwidth =1;
		gc.gridheight = 4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		statsDisplayPanel.setPreferredSize(new Dimension(300,400));
		add(statsDisplayPanel, gc);
		
		////////// Match Panel  ///////////
		
		gc.gridy++;
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth =1;
		gc.gridheight = 2;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		matchPanel.setPreferredSize(new Dimension(400,250));
		add(matchPanel, gc);

		////////// OBS Panel  ///////////
		
		gc.weightx = .5;
		gc.weighty = .25;
		
		gc.gridx = 1;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		obsPanel.setPreferredSize(new Dimension(250,100));
		add(obsPanel, gc);

		////////// AutoScore Main Panel  ///////////
		gc.gridy++;
		
		gc.weightx = .5;
		gc.weighty = .25;
		
		gc.gridx = 1;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		autoScoreMainPanel.setPreferredSize(new Dimension(250,100));
		add(autoScoreMainPanel, gc);

		////////// Team 1 Panel ///////////
		
		gc.gridy++;

		gc.weightx = .5;
		gc.weighty = .5;
		
		gc.gridx = 0;
		gc.gridwidth =1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
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
		gc.insets = new Insets(0, 0, 0, 0);
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
		gc.insets = new Insets(0, 0, 0, 0);
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
		gc.insets = new Insets(0, 0, 0, 0);
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
	public void addOBSDisconnectItemListener(ActionListener listenForOBSDisconnectItemListener) {
		obsDisconnectItem.addActionListener(listenForOBSDisconnectItemListener);
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
	public OBSConnectPanel getOBSConnectPanel() {
		return obsConnectFrame.getOBSConnectPanel();
	}
	public AutoScoreSettingsPanel getAutoScorePanel() {
		return autoScoreSettingsFrame.getAutoScoreSettingsPanel();
	}
	public void setFocusOnCode() {
		statsEntryPanel.setFocusOnCode();
	}
	public void showAutoScoreSettings() {
		autoScoreSettingsFrame.setVisible(true);
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

