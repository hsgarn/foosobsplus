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
package main;

import java.io.IOException;

import controller.MainController;
import controller.ResetController;
import controller.StatsController;
import controller.SwitchController;
import controller.TableController;
import controller.TeamController;
import controller.TimerController;
import model.Match;
import model.Settings;
import model.Stats;
import model.Table;
import model.Team;
import model.TimeClock;
import view.FileNamesPanel;
import view.HotKeysPanel;
import view.MainFrame;
import view.ResetPanel;
import view.SetLookAndFeel;
import view.SettingsPanel;
import view.StatsDisplayPanel;
import view.StatsEntryPanel;
import view.SwitchPanel;
import view.TablePanel;
import view.TeamPanel;
import view.TimerPanel;
import view.TimerWindowFrame;

public class Main {
	
	private Settings			settings			= new Settings();
	public OBSInterface 		obsInterface 		= new OBSInterface();
	SetLookAndFeel				setLookAndFeel		= new SetLookAndFeel();

	////// Generate the Data Models (Mvc) \\\\\\
	
	private Table 				table 				= new Table();
	private Team 				team1 				= new Team(settings);
	private Team 				team2 				= new Team(settings);
	private Match 				match				= new Match(team1, team2, settings);
	private Stats 				stats 				= new Stats();
	
	////// Create a TimeClock to be the Timer \\\\\\
	
	private TimeClock 			timeClock 			= new TimeClock();
	
	////// Create the View Panels to Display (mVc) \\\\\\
	
	private TablePanel 			tablePanel 			= new TablePanel();
	private TimerPanel 			timerPanel 			= new TimerPanel();
	private TeamPanel 			teamPanel1 			= new TeamPanel(1);
	private TeamPanel 			teamPanel2 			= new TeamPanel(2);
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel();
	private SwitchPanel 		switchPanel 		= new SwitchPanel();
	private ResetPanel 			resetPanel 			= new ResetPanel();
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel();
	private SettingsPanel		settingsPanel		= new SettingsPanel(settings);
	private HotKeysPanel		hotKeysPanel		= new HotKeysPanel(settings);
	private FileNamesPanel		fileNamesPanel		= new FileNamesPanel(settings, obsInterface);

	////// Set up Timer Window \\\\\\
	
	private TimerWindowFrame timerWindowFrame = new TimerWindowFrame();
	
	////// Display the View Panels on a JFrame \\\\\\
	
	private MainFrame mainFrame = new MainFrame(tablePanel, timerPanel, teamPanel1, teamPanel2, statsEntryPanel, 
												switchPanel, resetPanel, statsDisplayPanel, settingsPanel, hotKeysPanel, 
												fileNamesPanel, settings, obsInterface);

	////// Build and Start the Controllers (mvC) \\\\\\
	
	MainController 		mainController 		= new MainController(mainFrame, timerWindowFrame);
	TimerController 	timerController 	= new TimerController(timerPanel, timerWindowFrame, timeClock, settings);
	TableController 	tableController 	= new TableController(table, match, tablePanel, switchPanel);
	TeamController 		teamController 		= new TeamController(team1, team2, match, teamPanel1, teamPanel2, switchPanel, timerController);
	StatsController 	statsController 	= new StatsController(stats, statsEntryPanel, teamController);
	SwitchController 	switchController 	= new SwitchController(switchPanel, teamController);
	ResetController 	resetController 	= new ResetController(resetPanel, teamController);
	
	public Main() throws IOException {
		obsInterface.setTableName("T1");
		obsInterface.setFilePath(settings.getDatapath());
	}
}
