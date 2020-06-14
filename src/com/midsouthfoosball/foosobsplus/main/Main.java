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
package com.midsouthfoosball.foosobsplus.main;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.commands.CommandSwitch;
import com.midsouthfoosball.foosobsplus.commands.DGT1Command;
import com.midsouthfoosball.foosobsplus.commands.DGT2Command;
import com.midsouthfoosball.foosobsplus.commands.DST1Command;
import com.midsouthfoosball.foosobsplus.commands.DST2Command;
import com.midsouthfoosball.foosobsplus.commands.IGT1Command;
import com.midsouthfoosball.foosobsplus.commands.IGT2Command;
import com.midsouthfoosball.foosobsplus.commands.IST1Command;
import com.midsouthfoosball.foosobsplus.commands.IST2Command;
import com.midsouthfoosball.foosobsplus.commands.Memento;
import com.midsouthfoosball.foosobsplus.commands.PRT1Command;
import com.midsouthfoosball.foosobsplus.commands.PRT2Command;
import com.midsouthfoosball.foosobsplus.commands.PRTCommand;
import com.midsouthfoosball.foosobsplus.commands.PSMCommand;
import com.midsouthfoosball.foosobsplus.commands.PSSCommand;
import com.midsouthfoosball.foosobsplus.commands.PWT1Command;
import com.midsouthfoosball.foosobsplus.commands.PWT2Command;
import com.midsouthfoosball.foosobsplus.commands.RTT1Command;
import com.midsouthfoosball.foosobsplus.commands.RTT2Command;
import com.midsouthfoosball.foosobsplus.commands.SGTCommand;
import com.midsouthfoosball.foosobsplus.commands.SPTCommand;
import com.midsouthfoosball.foosobsplus.commands.SRTCommand;
import com.midsouthfoosball.foosobsplus.commands.SSTCommand;
import com.midsouthfoosball.foosobsplus.commands.STTCommand;
import com.midsouthfoosball.foosobsplus.commands.UTT1Command;
import com.midsouthfoosball.foosobsplus.commands.UTT2Command;
import com.midsouthfoosball.foosobsplus.commands.XPT1Command;
import com.midsouthfoosball.foosobsplus.commands.XPT2Command;
import com.midsouthfoosball.foosobsplus.controller.MainController;
import com.midsouthfoosball.foosobsplus.controller.MatchController;
import com.midsouthfoosball.foosobsplus.controller.ResetController;
import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.SwitchController;
import com.midsouthfoosball.foosobsplus.controller.TableController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.controller.TimerController;
import com.midsouthfoosball.foosobsplus.model.Game;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.model.Table;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.view.FileNamesFrame;
import com.midsouthfoosball.foosobsplus.view.HotKeysFrame;
import com.midsouthfoosball.foosobsplus.view.HotKeysPanel;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.ResetPanel;
import com.midsouthfoosball.foosobsplus.view.SettingsFrame;
import com.midsouthfoosball.foosobsplus.view.SettingsPanel;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TablePanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;
import com.midsouthfoosball.foosobsplus.view.TimerPanel;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

public class Main {
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Can't set look and feel.");
		}
	}
	
	////// Settings and OBSInterface setup \\\\\\
	
	private Settings			settings			= new Settings();
	public OBSInterface 		obsInterface 		= new OBSInterface(settings);
	private int 				maxGames			= settings.getGamesToWin() * 2 + 1;
	public static int			currentGameNbr		= 0;

	////// CommandStack and UndoRedo setup \\\\\\
	
	private int undoRedoPointer = -1;
	private Stack<Command> commandStack = new Stack<>();
	private Stack<Memento> mementoStackTeam1 = new Stack<>();
	private Stack<Memento> mementoStackTeam2 = new Stack<>();
	private Stack<Memento> mementoStackStats = new Stack<>();
	private Stack<Memento> mementoStackMatch = new Stack<>();
	private CommandSwitch mySwitch;

	////// Generate the Data Models (Mvc) \\\\\\
	
	private Table 				table 				= new Table(obsInterface, settings);
	private Team 				team1 				= new Team(obsInterface, settings, 1, settings.getSide1Color());
	private Team 				team2 				= new Team(obsInterface, settings, 2, settings.getSide2Color());
	private Match 				match				= new Match(obsInterface, settings, team1, team2);
	private Game				games[]	 			= new Game[] {	new Game(obsInterface, settings, team1, team2, 1, maxGames), 
																	new Game(obsInterface, settings, team1, team2, 2, maxGames), 
																	new Game(obsInterface, settings, team1, team2, 3, maxGames), 
																	new Game(obsInterface, settings, team1, team2, 4, maxGames), 
																	new Game(obsInterface, settings, team1, team2, 5, maxGames)
																};
	private Stats 				stats 				= new Stats(team1, team2, games, match);
	
	////// Create a TimeClock to be the Timer \\\\\\
	
	private TimeClock 			timeClock 			= new TimeClock(obsInterface, settings);
	private GameClock           gameClock           = new GameClock(obsInterface, settings);
	
	////// Create the View Panels to Display (mVc) \\\\\\
	
	private TablePanel 			tablePanel 			= new TablePanel();
	private TimerPanel 			timerPanel 			= new TimerPanel(settings);
	private MatchPanel			matchPanel			= new MatchPanel(settings);
	private TeamPanel 			teamPanel1 			= new TeamPanel(1, settings.getSide1Color(), settings);
	private TeamPanel 			teamPanel2 			= new TeamPanel(2, settings.getSide2Color(), settings);
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel();
	private SwitchPanel 		switchPanel 		= new SwitchPanel(settings);
	private ResetPanel 			resetPanel 			= new ResetPanel(settings);
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel();

	////// Set up Timer and Settings Windows \\\\\\
	
	private TimerWindowFrame 	timerWindowFrame 	= new TimerWindowFrame();
	private SettingsFrame 		settingsFrame 		= new SettingsFrame(settings);
	private SettingsPanel		settingsPanel		= settingsFrame.getSettingsPanel();
	private HotKeysFrame 		hotKeysFrame 		= new HotKeysFrame(settings);
	private HotKeysPanel 		hotKeysPanel		= hotKeysFrame.getHotKeysPanel();
	private FileNamesFrame		fileNamesFrame		= new FileNamesFrame(settings, obsInterface);
	
	////// Display the View Panels on a JFrame \\\\\\
	
	private MainFrame mainFrame = new MainFrame(tablePanel, timerPanel, teamPanel1, teamPanel2, statsEntryPanel, 
												switchPanel, resetPanel, statsDisplayPanel, matchPanel, settingsFrame, hotKeysFrame, 
												fileNamesFrame);

	////// Build and Start the Controllers (mvC) \\\\\\
	
	MainController 		mainController 		= new MainController(mainFrame, timerWindowFrame);
	TimerController 	timerController 	= new TimerController(timerPanel, timerWindowFrame, timeClock, settings);
	TeamController 		teamController 		= new TeamController(obsInterface, settings, team1, team2, match, teamPanel1, teamPanel2, switchPanel, timerController);
	TableController 	tableController 	= new TableController(obsInterface, settings, table, match, tablePanel, teamController);
	MatchController     matchController     = new MatchController(obsInterface, settings, match, stats, gameClock, matchPanel, statsEntryPanel, statsDisplayPanel, teamController);
	StatsController 	statsController 	= new StatsController(stats, statsEntryPanel, statsDisplayPanel, teamController);
	SwitchController 	switchController 	= new SwitchController(switchPanel, teamController, statsController);
	ResetController 	resetController 	= new ResetController(resetPanel, teamController, statsController);

	public Main() throws IOException {
		obsInterface.setFilePath(settings.getDatapath());
		obsInterface.setTableName(settings.getTableName());
		fetchAll(settings.getTableName());
		this.hotKeysPanel.addSaveListener(new HotKeysSaveListener());
		this.settingsPanel.addSaveListener(new SettingsSaveListener());
		this.statsEntryPanel.addUndoListener(new StatsEntryUndoListener());
		this.statsEntryPanel.addRedoListener(new StatsEntryRedoListener());
		this.statsEntryPanel.addCodeListener(new CodeListener());
		loadCommands();
	}
	private void fetchAll(String tableNbr) {
		tableController.fetchAll(tableNbr);
		teamController.fetchAll();
		statsController.displayAllStats();
	}
	public int getCurrentGameNbr() {
		return currentGameNbr;
	}
	public static void setCurrentGameNbr(int gameNbr) {
		currentGameNbr = gameNbr;
	}
	private class HotKeysSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hotKeysPanel.saveSettings(settings);
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			matchPanel.updateMnemonics();
			teamPanel1.updateMnemonics();
			teamPanel2.updateMnemonics();
			timerPanel.updateMnemonics();
			switchPanel.updateMnemonics();
			resetPanel.updateMnemonics();
		}
	}
	private class SettingsSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			settingsPanel.saveSettings(settings);
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			teamPanel1.setTitle();
			teamPanel2.setTitle();
		}
	}
	private class StatsEntryUndoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			undo();
			teamController.displayAll();
		}
	}
	private class StatsEntryRedoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			redo();
			teamController.displayAll();
		}
	}
	private class CodeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String code = txt.getText().toUpperCase();
			stats.setCode(code);
			stats.addCodeToHistory(code);
			statsEntryPanel.updateCode("");
			statsEntryPanel.updateCodeHistory(code);
			if (stats.getIsCommand()) {
				processCommand(stats.getCommand());
			} else {
				if (stats.getTeam1Scored()) teamController.incrementScore("Team 1");
				if (stats.getTeam2Scored()) teamController.incrementScore("Team 2");
				if (stats.getTeam1TimeOut()) {
					teamController.callTimeOut("Team 1");
				} else if (stats.getTeam2TimeOut()) {
					teamController.callTimeOut("Team 2");
				} else {
					if (stats.getIsThreeRod()||stats.getIsTwoRod()) teamController.startShotTimer();
					if (stats.getIsFiveRod()) teamController.startPassTimer();
				}
			}
			statsController.displayAllStats();
		}
	}
	private void processCommand(String command) {
		deleteElementsAfterPointer(undoRedoPointer);
		mementoStackTeam1.push(saveState(team1));
		mementoStackTeam2.push(saveState(team2));
		mementoStackStats.push(saveState(stats));
		mementoStackMatch.push(saveState(match));
		commandStack.push(mySwitch.execute(command));
		undoRedoPointer++;
		return;
	}
	private Memento saveState(Object object) {
		Memento memento = new Memento(object);
		return memento;
	}
	public void undo()
	{
		if(undoRedoPointer < 0) return;
	    commandStack.get(undoRedoPointer);
	    Memento mementoTeam1 = mementoStackTeam1.get(undoRedoPointer);
	    team1.restoreState(mementoTeam1.getState());
	    Memento mementoTeam2 = mementoStackTeam2.get(undoRedoPointer);
	    team2.restoreState(mementoTeam2.getState());
	    Memento mementoStats = mementoStackStats.get(undoRedoPointer);
	    stats.restoreState(mementoStats.getState());
	    Memento mementoMatch = mementoStackMatch.get(undoRedoPointer);
	    match.restoreState(mementoMatch.getState());
	    undoRedoPointer--;
	    statsEntryPanel.removeCodeHistory();
	}

	public void redo()
	{
	    if(undoRedoPointer == commandStack.size() - 1)
	        return;
	    undoRedoPointer++;
	    Command command = commandStack.get(undoRedoPointer);
	    command.execute();
	    statsEntryPanel.updateCodeHistory(command.getCode());
	}
	private void deleteElementsAfterPointer(int undoRedoPointer)
	{
	    if (commandStack.size() >= 1)  {
		    for(int i = commandStack.size()-1; i > undoRedoPointer; i--)
		    {
		        commandStack.remove(i);
		    }
		    for(int i = mementoStackTeam1.size()-1; i > undoRedoPointer; i--)
		    {
		        mementoStackTeam1.remove(i);
		    }
		    for(int i = mementoStackTeam2.size()-1; i > undoRedoPointer; i--)
		    {
		        mementoStackTeam2.remove(i);
		    }
		    for(int i = mementoStackStats.size()-1; i > undoRedoPointer; i--)
		    {
		        mementoStackStats.remove(i);
		    }
		    for(int i = mementoStackMatch.size()-1; i > undoRedoPointer; i--)
		    {
		        mementoStackMatch.remove(i);
		    }
	    }
    }
	private void loadCommands() {
		Command psm = new PSMCommand(statsController, matchController);
		Command sst = new SSTCommand(statsController, teamController);
		Command spt = new SPTCommand(statsController, teamController);
		Command sgt = new SGTCommand(statsController, teamController);
		Command stt = new STTCommand(statsController, teamController);
		Command srt = new SRTCommand(statsController, teamController);
		Command prt = new PRTCommand(statsController, teamController);
		Command ist1 = new IST1Command(statsController, teamController);
		Command ist2 = new IST2Command(statsController, teamController);
		Command dst1 = new DST1Command(statsController, teamController);
		Command dst2 = new DST2Command(statsController, teamController);
		Command igt1 = new IGT1Command(statsController, teamController);
		Command igt2 = new IGT2Command(statsController, teamController);
		Command dgt1 = new DGT1Command(statsController, teamController);
		Command dgt2 = new DGT2Command(statsController, teamController);
		Command utt1 = new UTT1Command(statsController, teamController);
		Command utt2 = new UTT2Command(statsController, teamController);
		Command rtt1 = new RTT1Command(statsController, teamController);
		Command rtt2 = new RTT2Command(statsController, teamController);
		Command prt1 = new PRT1Command(statsController, teamController);
		Command prt2 = new PRT2Command(statsController, teamController);
		Command pwt1 = new PWT1Command(statsController, teamController);
		Command pwt2 = new PWT2Command(statsController, teamController);
		Command pss = new PSSCommand(statsController, teamController);
		Command xpt1 = new XPT1Command(statsController, teamController);
		Command xpt2 = new XPT2Command(statsController, teamController);

		mySwitch = new CommandSwitch();
		mySwitch.register("PSM", psm);
		mySwitch.register("SST", sst);
		mySwitch.register("SPT", spt);
		mySwitch.register("SGT", sgt);
		mySwitch.register("STT", stt);
		mySwitch.register("SRT", srt);
		mySwitch.register("PRT", prt);
		mySwitch.register("IST1", ist1);
		mySwitch.register("IST2", ist2);
		mySwitch.register("DST1", dst1);
		mySwitch.register("DST2", dst2);
		mySwitch.register("IGT1", igt1);
		mySwitch.register("IGT2", igt2);
		mySwitch.register("DGT1", dgt1);
		mySwitch.register("DGT2", dgt2);
		mySwitch.register("UTT1", utt1);
		mySwitch.register("UTT2", utt2);
		mySwitch.register("RTT1", rtt1);
		mySwitch.register("RTT2", rtt2);
		mySwitch.register("PRT1", prt1);
		mySwitch.register("PRT2", prt2);
		mySwitch.register("PWT1", pwt1);
		mySwitch.register("PWT2", pwt2);
		mySwitch.register("PSS", pss);
		mySwitch.register("XPT1", xpt1);
		mySwitch.register("XPT2", xpt2);

	}
}
