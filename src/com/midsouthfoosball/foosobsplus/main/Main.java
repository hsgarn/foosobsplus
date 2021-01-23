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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.midsouthfoosball.foosobsplus.commands.CodeCommand;
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
import com.midsouthfoosball.foosobsplus.commands.PCACommand;
import com.midsouthfoosball.foosobsplus.commands.PCT1Command;
import com.midsouthfoosball.foosobsplus.commands.PCT2Command;
import com.midsouthfoosball.foosobsplus.commands.PPMCommand;
import com.midsouthfoosball.foosobsplus.commands.PRACommand;
import com.midsouthfoosball.foosobsplus.commands.PRGCommand;
import com.midsouthfoosball.foosobsplus.commands.PRNCommand;
import com.midsouthfoosball.foosobsplus.commands.PRRCommand;
import com.midsouthfoosball.foosobsplus.commands.PRSCommand;
import com.midsouthfoosball.foosobsplus.commands.PRT1Command;
import com.midsouthfoosball.foosobsplus.commands.PRT2Command;
import com.midsouthfoosball.foosobsplus.commands.PRTCommand;
import com.midsouthfoosball.foosobsplus.commands.PRTOCommand;
import com.midsouthfoosball.foosobsplus.commands.PSGCCommand;
import com.midsouthfoosball.foosobsplus.commands.PSGCommand;
import com.midsouthfoosball.foosobsplus.commands.PSMCommand;
import com.midsouthfoosball.foosobsplus.commands.PSRCommand;
import com.midsouthfoosball.foosobsplus.commands.PSSCCommand;
import com.midsouthfoosball.foosobsplus.commands.PSSCommand;
import com.midsouthfoosball.foosobsplus.commands.PSTCommand;
import com.midsouthfoosball.foosobsplus.commands.PSTOCommand;
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
import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TableController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.controller.TimerController;
import com.midsouthfoosball.foosobsplus.model.Game;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScored1Clock;
import com.midsouthfoosball.foosobsplus.model.LastScored2Clock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.model.Table;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.view.FileNamesFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.HotKeysFrame;
import com.midsouthfoosball.foosobsplus.view.HotKeysPanel;
import com.midsouthfoosball.foosobsplus.view.LastScored1WindowFrame;
import com.midsouthfoosball.foosobsplus.view.LastScored2WindowFrame;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.OBSConnectFrame;
import com.midsouthfoosball.foosobsplus.view.OBSConnectPanel;
import com.midsouthfoosball.foosobsplus.view.ParametersFrame;
import com.midsouthfoosball.foosobsplus.view.ParametersPanel;
import com.midsouthfoosball.foosobsplus.view.ResetPanel;
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

//		OBSRemoteController controller = new OBSRemoteController("ws://localhost:4444", false, "1Wbs98");
//		controller.registerConnectCallback(response -> {System.out.println(response.getObsStudioVersion());});
//		if (controller.isFailed()) {
//			System.out.println("It didn't work!!!!");
//		} else {
//			System.out.println("Weeeellll Doggie, it worked!");
//		}
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		controller.getCurrentScene(message -> {System.out.println("Scene: " + message.getName());});
//		controller.setCurrentScene("InstantReplayScene", message -> {System.out.println("Scene set, maybe: " + message.getMessageId() + ", " + message.getStatus() + ", " + message.getError());} );
//		controller.getCurrentScene(message -> {System.out.println("Scene: " + message.getName());});
//		controller.setCurrentScene("Scene", message -> {System.out.println("Scene set, maybe: " + message.getMessageId() + ", " + message.getStatus() + ", " + message.getError());} );
//		controller.getCurrentScene(message -> {System.out.println("Scene: " + message.getName());});
//		controller.setSourceVisibility("Scene", "MakeItWork", false, null);

	}	
	////// Settings and OBSInterface setup \\\\\\
	
	private Settings			settings			= new Settings();
	public OBSInterface 		obsInterface 		= new OBSInterface(settings);
	public String				matchId				= "";

	////// CommandStack and UndoRedo setup \\\\\\
	
	private int undoRedoPointer = -1;
	private Stack<Command> commandStack = new Stack<>();
	private Stack<Memento> mementoStackTeam1 = new Stack<>();
	private Stack<Memento> mementoStackTeam2 = new Stack<>();
	private Stack<Memento> mementoStackStats = new Stack<>();
	private Stack<Memento> mementoStackMatch = new Stack<>();
	private Stack<Memento> mementoStackGameClock = new Stack<>();
	private Stack<String> codeStack = new Stack<>();
	private CommandSwitch mySwitch;

	////// Generate the Data Models (Mvc) \\\\\\
	
	private Table 				table 				= new Table(obsInterface, settings);
	private Team 				team1 				= new Team(obsInterface, settings, 1, settings.getSide1Color());
	private Team 				team2 				= new Team(obsInterface, settings, 2, settings.getSide2Color());
	private Match 				match				= new Match(obsInterface, settings, team1, team2);
	private Game				games[]	 			= new Game[] {	new Game(obsInterface, settings, team1, team2, 1), 
																	new Game(obsInterface, settings, team1, team2, 2), 
																	new Game(obsInterface, settings, team1, team2, 3), 
																	new Game(obsInterface, settings, team1, team2, 4), 
																	new Game(obsInterface, settings, team1, team2, 5)
																};

	private Stats 				stats 				= new Stats(team1, team2);
	
	////// Create a TimeClock to be the Timer \\\\\\
	
	private TimeClock 			timeClock 			= new TimeClock(obsInterface, settings);
	private GameClock           gameClock           = new GameClock(obsInterface, settings);
	private LastScored1Clock    lastScored1Clock    = new LastScored1Clock(obsInterface, settings);
	private LastScored2Clock    lastScored2Clock    = new LastScored2Clock(obsInterface, settings);
	
	////// Create the View Panels to Display (mVc) \\\\\\
	
	private TablePanel 			tablePanel 			= new TablePanel(settings);
	private TimerPanel 			timerPanel 			= new TimerPanel(settings);
	private MatchPanel			matchPanel			= new MatchPanel(settings);
	private TeamPanel 			teamPanel1 			= new TeamPanel(1, settings.getSide1Color(), settings);
	private TeamPanel 			teamPanel2 			= new TeamPanel(2, settings.getSide2Color(), settings);
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel(settings);
	private SwitchPanel 		switchPanel 		= new SwitchPanel(settings);
	private ResetPanel 			resetPanel 			= new ResetPanel(settings);
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel(settings);

	////// Set up Timer and Settings Windows \\\\\\
	
	private ParametersFrame 		parametersFrame 		= new ParametersFrame(settings);
	private ParametersPanel			parametersPanel			= parametersFrame.getSettingsPanel();
	private HotKeysFrame 			hotKeysFrame 			= new HotKeysFrame(settings);
	private HotKeysPanel 			hotKeysPanel			= hotKeysFrame.getHotKeysPanel();
	private FileNamesFrame			fileNamesFrame			= new FileNamesFrame(settings, obsInterface);
	private OBSConnectFrame			obsConnectFrame			= new OBSConnectFrame(settings);
	private OBSConnectPanel			obsConnectPanel			= obsConnectFrame.getOBSConnectPanel();
	
	////// Display the View Panels on a JFrame \\\\\\
	
	private MainFrame mainFrame = new MainFrame(tablePanel, timerPanel, teamPanel1, teamPanel2, statsEntryPanel, 
												switchPanel, resetPanel, statsDisplayPanel, matchPanel, parametersFrame, hotKeysFrame, 
												fileNamesFrame, obsConnectFrame, this);

	////// Set up independent Windows \\\\\\
	
	private GameTableWindowPanel	gameTableWindowPanel	= new GameTableWindowPanel(settings);
	private GameTableWindowFrame	gameTableWindowFrame	= new GameTableWindowFrame(gameTableWindowPanel, mainFrame);
	private TimerWindowFrame 		timerWindowFrame 		= new TimerWindowFrame(mainFrame);
	private LastScored1WindowFrame 	lastScored1WindowFrame 	= new LastScored1WindowFrame(mainFrame);
	private LastScored2WindowFrame 	lastScored2WindowFrame 	= new LastScored2WindowFrame(mainFrame);

	////// Build and Start the Controllers (mvC) \\\\\\
	
	MainController 		mainController 		= new MainController(mainFrame, timerWindowFrame, lastScored1WindowFrame, lastScored2WindowFrame, gameTableWindowFrame);
	TimerController 	timerController 	= new TimerController(obsInterface, settings, timerPanel, timerWindowFrame, timeClock, lastScored1WindowFrame, lastScored1Clock, lastScored2WindowFrame, lastScored2Clock);
	TeamController 		teamController 		= new TeamController(obsInterface, settings, team1, team2, match, teamPanel1, teamPanel2, switchPanel, matchPanel, gameTableWindowPanel, statsDisplayPanel, timerController, lastScored1Clock, lastScored2Clock, gameClock);
	TableController 	tableController 	= new TableController(obsInterface, settings, table, match, tablePanel, teamController);
	MatchController     matchController     = new MatchController(settings, match, stats, gameClock, lastScored1Clock, lastScored2Clock, matchPanel, statsEntryPanel, statsDisplayPanel, switchPanel, gameTableWindowPanel, teamController);
	StatsController 	statsController 	= new StatsController(stats, statsDisplayPanel, teamController);

	public Main() throws IOException {
		obsInterface.setFilePath(settings.getDatapath());
		obsInterface.setTableName(settings.getTableName());
		fetchAll(settings.getTableName());
		this.hotKeysPanel.addSaveListener(new HotKeysSaveListener());
		this.parametersPanel.addSaveListener(new SettingsSaveListener());
		this.statsEntryPanel.addUndoListener(new StatsEntryUndoListener());
		this.statsEntryPanel.addRedoListener(new StatsEntryRedoListener());
		this.statsEntryPanel.addCodeListener(new CodeListener());
		this.teamPanel1.addClearAllListener(new TeamClearAllListener());
		this.teamPanel2.addClearAllListener(new TeamClearAllListener());
		this.tablePanel.addClearListener(new TableClearAllListener());
		this.tablePanel.addLoadListener(new TableLoadListener());
		this.tablePanel.addSetListener(new TableSetListener());
		this.statsEntryPanel.addStatsClearListener(new StatsClearListener());
		this.teamPanel1.addScoreIncreaseListener(new ScoreIncreaseListener());
		this.teamPanel2.addScoreIncreaseListener(new ScoreIncreaseListener());
		this.teamPanel1.addScoreDecreaseListener(new ScoreDecreaseListener());
		this.teamPanel2.addScoreDecreaseListener(new ScoreDecreaseListener());
		this.teamPanel1.addGameCountIncreaseListener(new GameCountIncreaseListener());
		this.teamPanel2.addGameCountIncreaseListener(new GameCountIncreaseListener());
		this.teamPanel1.addGameCountDecreaseListener(new GameCountDecreaseListener());
		this.teamPanel2.addGameCountDecreaseListener(new GameCountDecreaseListener());
		this.teamPanel1.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		this.teamPanel2.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		this.teamPanel1.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		this.teamPanel2.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		this.teamPanel1.addResetListener(new ResetListener());
		this.teamPanel2.addResetListener(new ResetListener());
		this.teamPanel1.addWarnListener(new WarnListener());
		this.teamPanel2.addWarnListener(new WarnListener());
		this.teamPanel1.addSwitchPositionsListener(new SwitchPositionsListener());
		this.teamPanel2.addSwitchPositionsListener(new SwitchPositionsListener());
		this.switchPanel.addSwitchSidesListener(new SwitchSidesListener());
		this.timerPanel.addShotTimerListener(new ShotTimerListener());
		this.timerPanel.addPassTimerListener(new PassTimerListener());
		this.timerPanel.addTimeOutTimerListener(new TimeOutTimerListener());
		this.timerPanel.addGameTimerListener(new GameTimerListener());
		this.timerPanel.addRecallTimerListener(new RecallTimerListener());
		this.timerPanel.addResetTimerListener(new ResetTimerListener());
		this.matchPanel.addStartMatchListener(new StartMatchListener());
		this.matchPanel.addPauseMatchListener(new PauseMatchListener());
		this.matchPanel.addStartGameListener(new StartGameListener());
		this.switchPanel.addSwitchTeamsListener(new SwitchTeamsListener());
		this.switchPanel.addSwitchScoresListener(new SwitchScoresListener());
		this.switchPanel.addSwitchGameCountsListener(new SwitchGameCountsListener());
		this.switchPanel.addSwitchTimeOutsListener(new SwitchTimeOutsListener());
		this.switchPanel.addSwitchResetWarnsListener(new SwitchResetWarnsListener());
		this.switchPanel.addClearAllListener(new ClearAllListener());
		this.resetPanel.addResetNamesListener(new ResetNamesListener());
		this.resetPanel.addResetScoresListener(new ResetScoresListener());
		this.resetPanel.addResetGameCountsListener(new ResetGameCountsListener());
		this.resetPanel.addResetTimeOutsListener(new ResetTimeOutsListener());
		this.resetPanel.addResetResetWarnsListener(new ResetResetWarnsListener());
		this.resetPanel.addResetAllListener(new ResetAllListener());
		loadCommands();
	}
	public void startMatch() {
		if(match.isMatchStarted()) {
			matchController.endMatch();
		} else {
			matchId = createMatchId();
			matchController.startMatch(matchId);
			for(Game game: games) {
				game.clearAll();
				game.setMatchId(matchId);
			}
		}
	}

	private void fetchAll(String tableNbr) {
		tableController.fetchAll(tableNbr);
		teamController.fetchAll();
		statsController.displayAllStats();
	}
	public void switchSides() {
		Memento tmpTeam1 = saveState(team1);
		Memento tmpTeam2 = saveState(team2);
		team1.restoreState(tmpTeam2.getState());
		team2.restoreState(tmpTeam1.getState());
		team1.setTeamNbr(1);
		team2.setTeamNbr(2);
		teamController.switchSides();
		matchController.switchSides();
	}
	public void setIsShowParsed(boolean isShowParsed) {
		stats.setIsShowParsed(isShowParsed);
	}
	private String createMatchId() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd.HH:mm:ss.SSS");
		return formatter.format(date);
	}
	public void processCode(String code, Boolean isRedo) {
		Command commandStatus;
		if (!isRedo) { 
			makeMementos();
			codeStack.push(code);
		}
		stats.setCode(code);
		stats.addCodeToHistory(code);
		statsEntryPanel.updateCode("");
		statsEntryPanel.updateCodeHistory(code);
		if (stats.getIsCommand()) {
			if (!isRedo) { 
				commandStatus = commandStack.push(mySwitch.execute(stats.getCommand()));
				if (commandStatus == null) {
					statsEntryPanel.errorCodeHistory();;
				}
				undoRedoPointer++;
			}
		} else {
			if (!isRedo) { 
				commandStatus = commandStack.push(mySwitch.execute("code"));
				if (commandStatus == null) {
					statsEntryPanel.errorCodeHistory();;
				}
				undoRedoPointer++;
			}
			if (stats.getIsError()) {
				statsEntryPanel.errorCodeHistory();
			}
			if (stats.getTeamScored(1)) matchController.incrementScore(1);
			if (stats.getTeamScored(2)) matchController.incrementScore(2);
			if (stats.getTeamTimeOut(1)) {
				teamController.callTimeOut(1);
			} else if (stats.getTeamTimeOut(2)) {
				teamController.callTimeOut(2);
			} else {
				if (stats.getIsThreeRod()||stats.getIsTwoRod()) teamController.startShotTimer();
				if (stats.getIsFiveRod()) teamController.startPassTimer();
			}
		}
		statsController.displayAllStats();
	}
	private void makeMementos() {
		deleteElementsAfterPointer(undoRedoPointer);
		mementoStackTeam1.push(saveState(team1));
		mementoStackTeam2.push(saveState(team2));
		mementoStackStats.push(saveState(stats));
		mementoStackMatch.push(saveState(match));
		mementoStackGameClock.push(saveState(gameClock));
	}
	private Memento saveState(Object object) {
		Memento memento = new Memento(object);
		return memento;
	}
	public void undo() 	{
		if(undoRedoPointer < 0) return;
		codeStack.get(undoRedoPointer);
	    commandStack.get(undoRedoPointer);
	    Memento mementoTeam1 = mementoStackTeam1.get(undoRedoPointer);
	    team1.restoreState(mementoTeam1.getState());
	    Memento mementoTeam2 = mementoStackTeam2.get(undoRedoPointer);
	    team2.restoreState(mementoTeam2.getState());
	    Memento mementoStats = mementoStackStats.get(undoRedoPointer);
	    stats.restoreState(mementoStats.getState());
	    Memento mementoMatch = mementoStackMatch.get(undoRedoPointer);
	    match.restoreState(mementoMatch.getState());
	    Memento mementoGameClock = mementoStackGameClock.get(undoRedoPointer);
	    gameClock.restoreState(mementoGameClock.getState());
	    undoRedoPointer--;
	    statsEntryPanel.removeCodeHistory();
	    stats.showParsed();
		teamController.displayAll();
		statsController.displayAllStats();
		statsEntryPanel.setFocusOnCode();	
		matchController.updateGameTables();
	}
 	public void redo() 	{
 		char commandChar = new Character('X');
 		Boolean isRedo = true;
 		String tempCode;
 		Boolean isCommand = false;
	    if(undoRedoPointer == commandStack.size() - 1)  return;
	    undoRedoPointer++;
	    tempCode = codeStack.get(undoRedoPointer);
	    isCommand = tempCode.charAt(0)==commandChar;
	    if(isCommand) {
		    Command command = commandStack.get(undoRedoPointer);
		    if (command != null) {
		    	command.execute();
			    statsEntryPanel.updateCodeHistory(tempCode);
		    } else {
		    	statsEntryPanel.updateCodeHistory(tempCode + "<Unknown");
	    	}
	    } else {
	    	processCode(tempCode,isRedo);
	    }
		teamController.displayAll();
		statsController.displayAllStats();
		matchController.updateGameTables();
	}
	private void deleteElementsAfterPointer(int undoRedoPointer) {
	    if (commandStack.size() >= 1)  {
		    for(int i = commandStack.size()-1; i > undoRedoPointer; i--)
		    {
		        commandStack.remove(i);
		        codeStack.remove(i);
		        mementoStackTeam1.remove(i);
		        mementoStackTeam2.remove(i);
		        mementoStackStats.remove(i);
		        mementoStackMatch.remove(i);
		        mementoStackGameClock.remove(i);
		    }
	    }
    }
	private void loadCommands() {
		Command psm = new PSMCommand(statsController, this);
		Command ppm = new PPMCommand(statsController, matchController);
		Command psg = new PSGCommand(statsController, matchController);
		Command sst = new SSTCommand(statsController, teamController);
		Command spt = new SPTCommand(statsController, teamController);
		Command sgt = new SGTCommand(statsController, teamController);
		Command stt = new STTCommand(statsController, teamController);
		Command srt = new SRTCommand(statsController, teamController);
		Command prt = new PRTCommand(statsController, teamController);
		Command ist1 = new IST1Command(statsController, matchController);
		Command ist2 = new IST2Command(statsController, matchController);
		Command dst1 = new DST1Command(statsController, matchController);
		Command dst2 = new DST2Command(statsController, matchController);
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
		Command pss = new PSSCommand(statsController, this);
		Command xpt1 = new XPT1Command(statsController, teamController);
		Command xpt2 = new XPT2Command(statsController, teamController);
		Command pst = new PSTCommand(statsController, teamController);
		Command pssc = new PSSCCommand(statsController, teamController);
		Command psgc = new PSGCCommand(statsController, teamController);
		Command psto = new PSTOCommand(statsController, teamController);
		Command psr = new PSRCommand(statsController, teamController);
		Command pca = new PCACommand(statsController, teamController);
		Command pct1 = new PCT1Command(statsController, teamController, team1, match, switchPanel, settings);
		Command pct2 = new PCT2Command(statsController, teamController, team2, match, switchPanel, settings);
		Command prn= new PRNCommand(statsController, teamController);
		Command prs = new PRSCommand(statsController, teamController);
		Command prg = new PRGCommand(statsController, teamController);
		Command prto = new PRTOCommand(statsController, teamController);
		Command prr = new PRRCommand(statsController, teamController);
		Command pra = new PRACommand(statsController, teamController);
		Command codeCommand = new CodeCommand(statsController);

		mySwitch = new CommandSwitch();
		mySwitch.register("PSM", psm);
		mySwitch.register("PPM", ppm);
		mySwitch.register("PSG", psg);
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
		mySwitch.register("PST", pst);
		mySwitch.register("PSSC", pssc);
		mySwitch.register("PSGC", psgc);
		mySwitch.register("PSTO", psto);
		mySwitch.register("PSR", psr);
		mySwitch.register("PCA", pca);
		mySwitch.register("PCT1", pct1);
		mySwitch.register("PCT2", pct2);
		mySwitch.register("PRN", prn);
		mySwitch.register("PRS", prs);
		mySwitch.register("PRG", prg);
		mySwitch.register("PRTO", prto);
		mySwitch.register("PRR", prr);
		mySwitch.register("PRA", pra);
		mySwitch.register("code", codeCommand);
	}
	////// Listeners \\\\\\
	private class CodeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Boolean isRedo = false;
			JTextField txt = (JTextField) e.getSource();
			String code = txt.getText().toUpperCase();
			if(code.equals("XU")) {
				undo();
			} else {
				if(code.equals("XR")) {
					redo();
					statsEntryPanel.updateCode(null);
				} else {
					processCode(code, isRedo);
				}
			}
		}
	}
	private class StatsEntryUndoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			undo();
		}
	}
	private class StatsEntryRedoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			redo();
			statsEntryPanel.setFocusOnCode();	
		}
	}
	private class StatsClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stats.clearAll();
			statsEntryPanel.clearAll();
			undoRedoPointer = -1;
			statsEntryPanel.setFocusOnCode();
		}
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
			statsEntryPanel.updateMnemonics();
		}
	}
	private class SettingsSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int oldGamesToWin = settings.getGamesToWin();
			String oldGameType = settings.getGameType();
			parametersPanel.saveSettings(settings);
			int newGamesToWin = settings.getGamesToWin();
			String newGameType = settings.getGameType();
			if (oldGamesToWin != newGamesToWin) {
				matchPanel.resizeGameTable();
				gameTableWindowPanel.resizeGameTable();
			}
			if (!oldGameType.equals(newGameType)) {
				teamPanel1.changeGameType();
				teamPanel2.changeGameType();
				switchPanel.changeGameType();
				resetPanel.changeGameType();
				matchPanel.changeGameType();
				tablePanel.changeGameType();
				timerPanel.changeGameType();
				statsEntryPanel.changeGameType();
				statsDisplayPanel.changeGameType();
			} else {
				teamPanel1.setTitle();
				teamPanel2.setTitle();
			}
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
		}
	}
	private class ScoreIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XIST1",false);
			} else {
				processCode("XIST2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ScoreDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XDST1",false);
			} else {
				processCode("XDST2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class GameCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XIGT1",false);
			} else {
				processCode("XIGT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class GameCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XDGT1",false);
			} else {
				processCode("XDGT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TimeOutCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XUTT1",false);
			} else {
				processCode("XUTT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TimeOutCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XRTT1",false);
			} else {
				processCode("XRTT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XPRT1",false);
			} else {
				processCode("XPRT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class WarnListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XPWT1",false);
			} else {
				processCode("XPWT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchPositionsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XXPT1",false);
			} else {
				processCode("XXPT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchSidesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSS",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ShotTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSST",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class PassTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSPT",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TimeOutTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSTT",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class GameTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSGT",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class RecallTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSRT",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRT",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class StartMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSM",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class PauseMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPPM",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class StartGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSG",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchTeamsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPST",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSSC",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchGameCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSGC",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchTimeOutsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSTO",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchResetWarnsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSR",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ClearAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPCA",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetNamesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRN",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRS",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetGameCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRG",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetTimeOutsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRTO",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetResetWarnsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRR",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class ResetAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRA",false);
			statsController.displayAllStats();
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TeamClearAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				processCode("XPCT1",false);
			} else {
				processCode("XPCT2",false);
			}
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TableClearAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TableLoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class TableSetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			statsEntryPanel.setFocusOnCode();
		}
	}
//	private void testProcessCodes() {
//		String codes[] = {"XPSM", "Y5D", "Y3P", "BGS", "B5D", "B3P", "YGS" };
//		autoProcessCodes(codes);
//	}
//	private void autoProcessCodes(String[] codes) {
//		for (String code: codes) {
//			processCode(code, false);
//		}
//	}
	public List<String> getCodeHistory() {
		List<String> codes = stats.getCodeHistoryAsList();
		return codes;
	}

}
