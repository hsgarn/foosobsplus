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
import view.FileNamesFrame;
import view.HotKeysFrame;
import view.MainFrame;
import view.ResetPanel;
import view.SettingsFrame;
import view.StatsDisplayPanel;
import view.StatsEntryPanel;
import view.SwitchPanel;
import view.TablePanel;
import view.TeamPanel;
import view.TimerPanel;
import view.TimerWindowFrame;

public class Main {
	
	private Settings			settings			= new Settings();
	public OBSInterface 		obsInterface 		= new OBSInterface(settings);

	////// Generate the Data Models (Mvc) \\\\\\
	
	private Table 				table 				= new Table(obsInterface, settings);
	private Team 				team1 				= new Team(obsInterface, settings, 1, settings.getSide1Color());
	private Team 				team2 				= new Team(obsInterface, settings, 2, settings.getSide2Color());
	private Match 				match				= new Match(obsInterface, settings, team1, team2);
	private Stats 				stats 				= new Stats();
	
	////// Create a TimeClock to be the Timer \\\\\\
	
	private TimeClock 			timeClock 			= new TimeClock(obsInterface, settings);
	
	////// Create the View Panels to Display (mVc) \\\\\\
	
	private TablePanel 			tablePanel 			= new TablePanel();
	private TimerPanel 			timerPanel 			= new TimerPanel();
	private TeamPanel 			teamPanel1 			= new TeamPanel(1, settings.getSide1Color());
	private TeamPanel 			teamPanel2 			= new TeamPanel(2, settings.getSide2Color());
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel();
	private SwitchPanel 		switchPanel 		= new SwitchPanel();
	private ResetPanel 			resetPanel 			= new ResetPanel();
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel();

	////// Set up Timer and Settings Windows \\\\\\
	
	private TimerWindowFrame 	timerWindowFrame 	= new TimerWindowFrame();
	private SettingsFrame 		settingsFrame 		= new SettingsFrame(settings);
	private HotKeysFrame 		hotKeysFrame 		= new HotKeysFrame(settings);
	private FileNamesFrame		fileNamesFrame		= new FileNamesFrame(settings, obsInterface);
	
	////// Display the View Panels on a JFrame \\\\\\
	
	private MainFrame mainFrame = new MainFrame(tablePanel, timerPanel, teamPanel1, teamPanel2, statsEntryPanel, 
												switchPanel, resetPanel, statsDisplayPanel, settingsFrame, hotKeysFrame, 
												fileNamesFrame);

	////// Build and Start the Controllers (mvC) \\\\\\
	
	MainController 		mainController 		= new MainController(mainFrame, timerWindowFrame);
	TimerController 	timerController 	= new TimerController(timerPanel, timerWindowFrame, timeClock, settings);
	TeamController 		teamController 		= new TeamController(obsInterface, settings, team1, team2, match, teamPanel1, teamPanel2, switchPanel, timerController);
	TableController 	tableController 	= new TableController(obsInterface, settings, table, match, tablePanel, teamController);
	StatsController 	statsController 	= new StatsController(stats, statsEntryPanel, statsDisplayPanel, teamController);
	SwitchController 	switchController 	= new SwitchController(switchPanel, teamController);
	ResetController 	resetController 	= new ResetController(resetPanel, teamController);
	
	public Main() throws IOException {
		obsInterface.setFilePath(settings.getDatapath());
		obsInterface.setTableName(settings.getTableName());
		fetchAll(settings.getTableName());
	}
	private void fetchAll(String tableNbr) {
		tableController.fetchAll(tableNbr);
		teamController.fetchAll();
	}
}
