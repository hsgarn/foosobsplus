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
package com.midsouthfoosball.foosobsplus.main;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
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
import com.midsouthfoosball.foosobsplus.commands.PNBCommand;
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
import com.midsouthfoosball.foosobsplus.commands.PSOCommand;
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
import com.midsouthfoosball.foosobsplus.commands.XP1Command;
import com.midsouthfoosball.foosobsplus.commands.XP2Command;
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
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.model.Table;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.view.AutoScoreConfigFrame;
import com.midsouthfoosball.foosobsplus.view.AutoScoreConfigPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreMainPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsFrame;
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsPanel;
import com.midsouthfoosball.foosobsplus.view.BallPanel;
import com.midsouthfoosball.foosobsplus.view.FileNamesFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.HotKeysFrame;
import com.midsouthfoosball.foosobsplus.view.HotKeysPanel;
import com.midsouthfoosball.foosobsplus.view.LastScoredWindowFrame;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.OBSConnectFrame;
import com.midsouthfoosball.foosobsplus.view.OBSConnectPanel;
import com.midsouthfoosball.foosobsplus.view.OBSPanel;
import com.midsouthfoosball.foosobsplus.view.ParametersFrame;
import com.midsouthfoosball.foosobsplus.view.ParametersPanel;
import com.midsouthfoosball.foosobsplus.view.ResetPanel;
import com.midsouthfoosball.foosobsplus.view.SourcesFrame;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TablePanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;
import com.midsouthfoosball.foosobsplus.view.TimerPanel;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

import net.twasi.obsremotejava.OBSRemoteController;

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
	
	final   OBS obs			 						= OBS.getInstance();
	private OBSRemoteController obsController;
	private Settings			settings			= new Settings();
	public  OBSInterface 		obsInterface 		= new OBSInterface(settings);
	public  String				matchId				= "";
	private HashMap<String, Boolean> allBallsMap 	= new HashMap<>();
	private HashMap<String, Boolean> nineBallsMap 	= new HashMap<>();
	private DateTimeFormatter dtf 					= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private String obsSceneName 					= "FoosOBS+ Main";
	private String obsShowScoresSource 				= "ScoresAndLabels";
	private String obsShowTimerSource               = "Foos OBS+ Timer";
	private boolean autoScoreConnected				= false; 
	
	////// CommandStack and UndoRedo setup \\\\\\
	
	private int undoRedoPointer 					= -1;
	private Stack<Command> commandStack 			= new Stack<>();
	private Stack<Memento> mementoStackTeam1 		= new Stack<>();
	private Stack<Memento> mementoStackTeam2		= new Stack<>();
	private Stack<Memento> mementoStackStats 		= new Stack<>();
	private Stack<Memento> mementoStackMatch		= new Stack<>();
	private Stack<Memento> mementoStackGameClock	= new Stack<>();
	private Stack<String> codeStack 				= new Stack<>();
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

	private Stats 				stats 				= new Stats(settings, team1, team2);
	
	////// Create a TimeClock to be the Timer \\\\\\
	
	private TimeClock 			timeClock 			= new TimeClock(obsInterface, settings);
	private GameClock           gameClock           = new GameClock(obsInterface, settings);
	private LastScoredClock  	lastScored1Clock    = new LastScoredClock();
	private LastScoredClock		lastScored2Clock    = new LastScoredClock();
	
	////// Create the View Panels to Display (mVc) \\\\\\
	
	private TablePanel 			tablePanel 			= new TablePanel(settings);
	private TimerPanel 			timerPanel 			= new TimerPanel(settings);
	private OBSPanel            obsPanel            = new OBSPanel(settings);
	private AutoScoreMainPanel	autoScoreMainPanel  = new AutoScoreMainPanel(settings);
	private MatchPanel			matchPanel			= new MatchPanel(settings);
	private BallPanel			ballPanel			= new BallPanel(settings);
	private TeamPanel 			teamPanel1 			= new TeamPanel(1, settings.getSide1Color(), settings);
	private TeamPanel 			teamPanel2 			= new TeamPanel(2, settings.getSide2Color(), settings);
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel(settings);
	private SwitchPanel 		switchPanel 		= new SwitchPanel(settings);
	private ResetPanel 			resetPanel 			= new ResetPanel(settings);
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel(settings);

	////// Set up Timer and Settings Windows \\\\\\
	
	private ParametersFrame 	parametersFrame 	= new ParametersFrame(settings);
	private ParametersPanel		parametersPanel		= parametersFrame.getSettingsPanel();
	private HotKeysFrame 		hotKeysFrame 		= new HotKeysFrame(settings);
	private HotKeysPanel 		hotKeysPanel		= hotKeysFrame.getHotKeysPanel();
	private SourcesFrame		sourcesFrame		= new SourcesFrame(settings, obsInterface);
	private FileNamesFrame		fileNamesFrame		= new FileNamesFrame(settings, obsInterface);
	private OBSConnectFrame		obsConnectFrame		= new OBSConnectFrame(settings);
	private OBSConnectPanel		obsConnectPanel		= obsConnectFrame.getOBSConnectPanel();
	private AutoScoreSettingsFrame		autoScoreSettingsFrame		= new AutoScoreSettingsFrame(settings);
	private AutoScoreSettingsPanel		autoScoreSettingsPanel		= autoScoreSettingsFrame.getAutoScoreSettingsPanel();
	private AutoScoreConfigFrame		autoScoreConfigFrame		= new AutoScoreConfigFrame(settings);
	private AutoScoreConfigPanel		autoScoreConfigPanel		= autoScoreConfigFrame.getAutoScoreConfigPanel();
	
	
	////// Display the View Panels on a JFrame \\\\\\
	
	private MainFrame mainFrame; // The Main Window JFrame with multiple View Panels on it

	////// Set up independent Windows \\\\\\
	
	private GameTableWindowPanel	gameTableWindowPanel;
	private GameTableWindowFrame	gameTableWindowFrame;
	private TimerWindowFrame 		timerWindowFrame;
	private LastScoredWindowFrame 	lastScored1WindowFrame;
	private LastScoredWindowFrame 	lastScored2WindowFrame;

	////// Build and Start the Controllers (mvC) \\\\\\
	
//	@SuppressWarnings("unused")
	private MainController 	mainController;
	private TimerController timerController;
	private TeamController 	teamController;
	private TableController tableController;
	private MatchController matchController;
	private StatsController statsController;
	private SwingWorker<Boolean, Integer> autoScoreWorker;
	public Main() throws IOException {

		loadWindowsAndControllers();
		
		obsInterface.setFilePath(settings.getDatapath());
		obsInterface.setTableName(settings.getTableName());
		updateOBSDisconnected();
		if (settings.getOBSAutoLogin()==1) {
			connectToOBS();
		}

		loadBallMaps();
		
		loadListeners();

		loadCommands();

		fetchAll(settings.getTableName());
		
		createAutoScoreWorker();
		
		if (settings.getAutoScoreSettingsAutoConnect() == 1) {
			connectAutoScore();
		}
	}
	private void createAutoScoreWorker() {
		autoScoreWorker = new SwingWorker<Boolean, Integer>() {
			Socket skt;
			BufferedReader dataIn;
			String team1 = "Team1";
			String team2 = "Team2";
			@Override
			protected Boolean doInBackground() throws Exception {
		    	boolean isConnected = false;
		    	String address = settings.getAutoScoreSettingsServerAddress();
		    	int port = settings.getAutoScoreSettingsServerPort();
		    	try
		        {
		            skt = new Socket(address, port);
		            autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Connected to " + address + ": " + port);
		        	dataIn = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		        	isConnected = true;
		        }
		        catch(UnknownHostException uh)
		        {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + uh.toString());
		        }
		        catch(IOException io)
		        {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString());
		        }
		        String str = "";
		        while (isConnected)
		        {
		        	try
		            {
		                str = dataIn.readLine();
		                if (str.equals(team1))
		                {
		                	publish(1);
		                	if (settings.getAutoScoreSettingsDetailLog()==1) {
		                		autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team 1 Scored." );
		                	}
		                } else {
			                if (str.equals(team2)) {
			                	publish(2);
			                	if (settings.getAutoScoreSettingsDetailLog()==1) {
			                		autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team 2 Scored." );
			                	}
			                } else {
			                	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + str);
			                }
		                }
		                if (isCancelled()) {
		                	break;
		                }
		            }
		            catch(IOException io)
		            {
		            	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString());
		                isConnected = false;
		            }
		        }
		        autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Connection Terminated!!");
		        try
		        {
		            dataIn.close();
		            skt.close();
		            isConnected = false;
		        }
		        catch(IOException io)
		        {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString());
		        }
		        return isConnected;
			}
			@Override
			protected void done() {
				boolean status;
				if (isCancelled()) return;
			    try {
			     status = get();
			     autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Worker completed with isConnected: " + status);
			    } catch (InterruptedException e) {
			    	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString());
			    } catch (ExecutionException e) {
			    	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString());
			    }
			}
			@Override
			protected void process(List<Integer> chunks) {
				if (isCancelled()) return;
				int mostRecentValue = chunks.get(chunks.size()-1);
            	if (settings.getAutoScoreSettingsDetailLog()==1) {
            		autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team "+mostRecentValue+ " scored!");
            	}
				if (mostRecentValue == 1) {
					processCode("XIST1", false);
				}	else {
						if (mostRecentValue == 2) {
							processCode("XIST2", false);
						}
				}
			}
		};
	}
	public void loadWindowsAndControllers() {
		mainFrame = new MainFrame(settings, tablePanel, timerPanel, obsPanel, autoScoreMainPanel, teamPanel1, teamPanel2, statsEntryPanel, 
				switchPanel, resetPanel, statsDisplayPanel, matchPanel, ballPanel, 
				parametersFrame, hotKeysFrame, sourcesFrame, fileNamesFrame, obsConnectFrame, autoScoreSettingsFrame, autoScoreConfigFrame, this);

		////// Set up independent Windows \\\\\\
		
		gameTableWindowPanel	= new GameTableWindowPanel(settings);
		gameTableWindowFrame	= new GameTableWindowFrame(gameTableWindowPanel, mainFrame);
		timerWindowFrame 		= new TimerWindowFrame(mainFrame);
		lastScored1WindowFrame 	= new LastScoredWindowFrame(mainFrame, 1);
		lastScored2WindowFrame 	= new LastScoredWindowFrame(mainFrame, 2);

		////// Build and Start the Controllers (mvC) \\\\\\
		
		mainController 	= new MainController(mainFrame, timerWindowFrame, lastScored1WindowFrame, lastScored2WindowFrame, gameTableWindowFrame);
		timerController = new TimerController(obsInterface, settings, timerPanel, timerWindowFrame, timeClock, lastScored1WindowFrame, lastScored1Clock, lastScored2WindowFrame, lastScored2Clock);
		teamController 	= new TeamController(obsInterface, settings, team1, team2, match, teamPanel1, teamPanel2, switchPanel, matchPanel, gameTableWindowPanel, statsDisplayPanel, timerController, lastScored1Clock, lastScored2Clock, gameClock, mainController);
		tableController = new TableController(obsInterface, settings, table, match, tablePanel, teamController);
		matchController = new MatchController(settings, match, stats, gameClock, lastScored1Clock, lastScored2Clock, matchPanel, statsEntryPanel, statsDisplayPanel, switchPanel, gameTableWindowPanel, teamController);
		statsController = new StatsController(stats, statsDisplayPanel, teamController);
	}
	public void loadListeners() {
		ballPanel.addBtnCueBallListener(new BtnCueBallListener());
		ballPanel.addBtnOneBallListener(new BtnOneBallListener());
		ballPanel.addBtnTwoBallListener(new BtnTwoBallListener());
		ballPanel.addBtnThreeBallListener(new BtnThreeBallListener());
		ballPanel.addBtnFourBallListener(new BtnFourBallListener());
		ballPanel.addBtnFiveBallListener(new BtnFiveBallListener());
		ballPanel.addBtnSixBallListener(new BtnSixBallListener());
		ballPanel.addBtnSevenBallListener(new BtnSevenBallListener());
		ballPanel.addBtnEightBallListener(new BtnEightBallListener());
		ballPanel.addBtnNineBallListener(new BtnNineBallListener());
		ballPanel.addBtnTenBallListener(new BtnTenBallListener());
		ballPanel.addBtnElevenBallListener(new BtnElevenBallListener());
		ballPanel.addBtnTwelveBallListener(new BtnTwelveBallListener());
		ballPanel.addBtnThirteenBallListener(new BtnThirteenBallListener());
		ballPanel.addBtnFourteenBallListener(new BtnFourteenBallListener());
		ballPanel.addBtnFifteenBallListener(new BtnFifteenBallListener());
		ballPanel.addBtnSyncOBSListener(new BtnSyncOBSListener());
		ballPanel.addBtnResetNineBallListener(new BtnResetNineBallListener());
		ballPanel.addBtnShowAllBallsListener(new BtnShowAllBallsListener());
		ballPanel.addBtnHideAllBallsListener(new BtnHideAllBallsListener());
		
		hotKeysPanel.addSaveListener(new HotKeysSaveListener());
		autoScoreSettingsPanel.addSaveListener(new AutoScoreSettingsSaveListener());
		autoScoreSettingsPanel.addConnectListener(new AutoScoreSettingsConnectListener());
		autoScoreSettingsPanel.addDisconnectListener(new AutoScoreSettingsDisconnectListener());
		autoScoreConfigPanel.addSaveListener(new AutoScoreConfigSaveListener());
		autoScoreConfigPanel.addSendConfigListener(new AutoScoreConfigSendConfigListener());
		parametersPanel.addSaveListener(new SettingsSaveListener());
		obsConnectPanel.addSetSceneListener(new OBSSetSceneListener());
		obsConnectPanel.addConnectListener(new OBSConnectListener());
		obsConnectPanel.addDisconnectListener(new OBSDisconnectListener());
		obsConnectPanel.addSceneFocusListener(new OBSSceneFocusListener());
		obsConnectPanel.addSceneListener(new OBSSceneListener());
		obsConnectPanel.addSaveListener(new OBSSaveListener());
		obsPanel.addConnectListener(new OBSConnectListener());
		obsPanel.addDisconnectListener(new OBSDisconnectListener());
		obsPanel.addPushListener(new OBSPushListener());
		obsPanel.addPullListener(new OBSPullListener());
		obsPanel.addShowScoresListener(new OBSShowScoresListener());
		obsPanel.addShowTimerListener(new OBSShowTimerListener());
		autoScoreMainPanel.addConnectListener(new AutoScoreMainPanelConnectListener());
		autoScoreMainPanel.addDisconnectListener(new AutoScoreMainPanelDisconnectListener());
		autoScoreMainPanel.addSettingsListener(new AutoScoreMainPanelSettingsListener());
		statsEntryPanel.addUndoListener(new StatsEntryUndoListener());
		statsEntryPanel.addRedoListener(new StatsEntryRedoListener());
		statsEntryPanel.addCodeListener(new CodeListener());
		teamPanel1.addClearAllListener(new TeamClearAllListener());
		teamPanel2.addClearAllListener(new TeamClearAllListener());
		tablePanel.addClearListener(new TableClearAllListener());
		tablePanel.addLoadListener(new TableLoadListener());
		tablePanel.addSetListener(new TableSetListener());
		statsEntryPanel.addStatsClearListener(new StatsClearListener());
		teamPanel1.addScoreIncreaseListener(new ScoreIncreaseListener());
		teamPanel2.addScoreIncreaseListener(new ScoreIncreaseListener());
		teamPanel1.addScoreDecreaseListener(new ScoreDecreaseListener());
		teamPanel2.addScoreDecreaseListener(new ScoreDecreaseListener());
		teamPanel1.addGameCountIncreaseListener(new GameCountIncreaseListener());
		teamPanel2.addGameCountIncreaseListener(new GameCountIncreaseListener());
		teamPanel1.addGameCountDecreaseListener(new GameCountDecreaseListener());
		teamPanel2.addGameCountDecreaseListener(new GameCountDecreaseListener());
		teamPanel1.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		teamPanel2.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		teamPanel1.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		teamPanel2.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		teamPanel1.addResetListener(new ResetListener());
		teamPanel2.addResetListener(new ResetListener());
		teamPanel1.addWarnListener(new WarnListener());
		teamPanel2.addWarnListener(new WarnListener());
		teamPanel1.addSwitchPositionsListener(new SwitchPositionsListener());
		teamPanel2.addSwitchPositionsListener(new SwitchPositionsListener());
		switchPanel.addSwitchSidesListener(new SwitchSidesListener());
		timerPanel.addShotTimerListener(new ShotTimerListener());
		timerPanel.addPassTimerListener(new PassTimerListener());
		timerPanel.addTimeOutTimerListener(new TimeOutTimerListener());
		timerPanel.addGameTimerListener(new GameTimerListener());
		timerPanel.addRecallTimerListener(new RecallTimerListener());
		timerPanel.addResetTimerListener(new ResetTimerListener());
		matchPanel.addStartMatchListener(new StartMatchListener());
		matchPanel.addPauseMatchListener(new PauseMatchListener());
		matchPanel.addStartGameListener(new StartGameListener());
		switchPanel.addSwitchTeamsListener(new SwitchTeamsListener());
		switchPanel.addSwitchPlayer1Listener(new SwitchPlayer1Listener());
		switchPanel.addSwitchPlayer2Listener(new SwitchPlayer2Listener());
		switchPanel.addSwitchScoresListener(new SwitchScoresListener());
		switchPanel.addSwitchGameCountsListener(new SwitchGameCountsListener());
		switchPanel.addSwitchTimeOutsListener(new SwitchTimeOutsListener());
		switchPanel.addSwitchResetWarnsListener(new SwitchResetWarnsListener());
		switchPanel.addClearAllListener(new ClearAllListener());
		resetPanel.addResetNamesListener(new ResetNamesListener());
		resetPanel.addResetScoresListener(new ResetScoresListener());
		resetPanel.addResetGameCountsListener(new ResetGameCountsListener());
		resetPanel.addResetTimeOutsListener(new ResetTimeOutsListener());
		resetPanel.addResetResetWarnsListener(new ResetResetWarnsListener());
		resetPanel.addResetAllListener(new ResetAllListener());
		mainFrame.addOBSDisconnectItemListener(new OBSDisconnectItemListener());
	}
	public void loadBallMaps() {
		allBallsMap.put("Cue", true);
		allBallsMap.put("One", true);
		allBallsMap.put("Two", true);
		allBallsMap.put("Three", true);
		allBallsMap.put("Four", true);
		allBallsMap.put("Five", true);
		allBallsMap.put("Six", true);
		allBallsMap.put("Seven", true);
		allBallsMap.put("Eight", true);
		allBallsMap.put("Nine", true);
		allBallsMap.put("Ten", true);
		allBallsMap.put("Eleven", true);
		allBallsMap.put("Twelve", true);
		allBallsMap.put("Thirteen", true);
		allBallsMap.put("Fourteen", true);
		allBallsMap.put("Fifteen", true);
		
		nineBallsMap.put("Cue", true);
		nineBallsMap.put("One", true);
		nineBallsMap.put("Two", true);
		nineBallsMap.put("Three", true);
		nineBallsMap.put("Four", true);
		nineBallsMap.put("Five", true);
		nineBallsMap.put("Six", true);
		nineBallsMap.put("Seven", true);
		nineBallsMap.put("Eight", true);
		nineBallsMap.put("Nine", true);
		nineBallsMap.put("Ten", false);
		nineBallsMap.put("Eleven", false);
		nineBallsMap.put("Twelve", false);
		nineBallsMap.put("Thirteen", false);
		nineBallsMap.put("Fourteen", false);
		nineBallsMap.put("Fifteen", false);
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
		matchController.switchSides();
		teamController.switchSides();
	}
	public void setShowParsed(boolean showParsed) {
		settings.setShowParsed(showParsed);
		try {
			settings.saveControlConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String createMatchId() {
		return matchController.createMatchId();
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
		Command xp1 = new XP1Command(statsController, teamController);
		Command xp2 = new XP2Command(statsController, teamController);
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
		Command pso = new PSOCommand(statsController, this);
		Command pnb = new PNBCommand(statsController, this);
		Command psa = new PSACommand(statsController, this);
		Command pha = new PHACommand(statsController, this);
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
		mySwitch.register("XP1", xp1);
		mySwitch.register("XP2", xp2);
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
		mySwitch.register("PSO", pso);
		mySwitch.register("PNB", pnb);
		mySwitch.register("PSA", psa);
		mySwitch.register("PHA", pha);
		mySwitch.register("code", codeCommand);
	}
	public void showScores(boolean show) {
		showSource(obsShowScoresSource, show);
//		if (obs.getConnected()) obsController.setSourceVisibility(obs.getScene(), obsShowScoresSource, show, response -> {
//			if(settings.getShowParsed()) {
//				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceVisibility called: " + obs.getScene() + ", " + obsShowScoresSource + ", " + show );
//			}
//		});
	}
	public void showTimer(boolean show) {
		showSource(obsShowTimerSource, show);
//		if (obs.getConnected()) obsController.setSourceVisibility(obs.getScene(), obsShowTimerSource, show, response -> {
//			if(settings.getShowParsed()) {
//				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceVisibility called: " + obs.getScene() + ", " + obsShowTimerSource + ", " + show );
//			}
//		});
	}
	public void showSource(String source, boolean show) {
		if (obs.getConnected()) obsController.setSourceVisibility(obs.getScene(), source, show, response -> {
			if(settings.getShowParsed()) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceVisibility called: " + obs.getScene() + ", " + source + ", " + show );
			}
		});
	}
	public void updateOBSConnected() {
		obs.setConnected(true);
		obsConnectPanel.disableConnect();
		mainFrame.enableConnect(false);
		mainFrame.setOBSIconConnected(true);
		if(settings.getOBSUpdateOnConnect()==1) {
			tableController.writeAll();
			teamController.writeAll();
			statsController.displayAllStats();
		}
	}
	public void updateOBSDisconnected() {
		obs.setConnected(false);
		obsConnectPanel.enableConnect();
		mainFrame.enableConnect(true);
		mainFrame.setOBSIconConnected(false);
	}

	public void connectToOBS() { 
		AtomicReference<String> connectionFailedResult = new AtomicReference<>();
		AtomicReference<String> disconnectResult = new AtomicReference<>();
		AtomicReference<String> onErrorReason = new AtomicReference<>();
		AtomicReference<String> connectResult = new AtomicReference<>(); 
		AtomicReference<String> onCloseResult = new AtomicReference<>();
//		obsConnectPanel.saveSettings();
//		System.out.println("main 704: host: " + obs.getHost() + ", port: " + obs.getPort());
//		if (obs.getHost().length() < 1) 
			obs.setHost(settings.getOBSHost());
//		if (obs.getPort().length() < 1) 
			obs.setPort(settings.getOBSPort());
//		if (obs.getPassword().length() < 1) 
			obs.setPassword(settings.getOBSPassword());
//		if (obs.getScene().length() < 1) 
			obs.setScene(settings.getOBSScene());
		String connectString = "ws://"+obs.getHost()+":"+obs.getPort();
// need to make controller - we are getting it from OBS object, but it has not been set yet.
		obsController = new OBSRemoteController(connectString,false,obs.getPassword(),false);
		obs.setController(obsController);
		  
		if (obsController.isFailed()) {
		  obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Controller failed!"); 
		}
			  
		obsController.registerConnectCallback(response -> {
			connectResult.set(dtf.format(LocalDateTime.now()) + ": Connected! Studio Version: " + response.getObsStudioVersion() + ", WebSocket Version: " + response.getObsWebsocketVersion());
			updateOBSConnected(); obsConnectPanel.addMessage(connectResult.get());
			if(settings.getOBSCloseOnConnect()==1) { 
				obsConnectFrame.setVisible(false);
			}
		});
		  
		obsController.registerDisconnectCallback(() -> {
			disconnectResult.set(dtf.format(LocalDateTime.now()) + ": OBS Disconnect Confirmed!"); updateOBSDisconnected();
			obsConnectPanel.addMessage(disconnectResult.get());
		});
		  
		obsController.registerOnError((message, throwable) -> {
			onErrorReason.set(dtf.format(LocalDateTime.now()) + ": OBS onError called unexpectedly. May need to disconnect/reconnect.");
			obsConnectPanel.addMessage(onErrorReason.get());
		});
		  
		obsController.registerCloseCallback((errorNumber, message) -> {
			onCloseResult.set(dtf.format(LocalDateTime.now()) + ": OBS Connection closed.  Code: " + errorNumber);
			obsConnectPanel.addMessage(onCloseResult.get());
		});
		  
		obsController.registerConnectionFailedCallback(message -> {
			connectionFailedResult.set(message);
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": From ConnectionFailedCallback: " + connectionFailedResult.get());
		});
		  
		if(obsController.isFailed()) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " Failed to connect to OBS.  Is OBS running and WebSocket plugin enabled?");
		}
		try {
			obsController.connect();

			if(!obsController.isFailed()) { 
				obsController.setCurrentScene(obsSceneName, response -> { 
					if(settings.getShowParsed()) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Setting scene to: " + obsSceneName);
						obsController.getCurrentScene(response2 -> { 
							obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " Scene Set to: " + response2.getName());
						});
					} 
				});
			}
		} catch (Exception e) {
			System.out.println("WooWee, that was some kind of exception there: " + e.getMessage() );
		}
		
	}
	 
	private void obsSetBallVisible(String source, boolean show) {
		if (obs.getConnected()) {
			obsController.setSourceVisibility("Scene", source, show, response -> {
				if(settings.getShowParsed()) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceVisibility called");
				}
			});
		}
	}
	public void obsSyncBalls() {
		if (obs.getConnected()) {
			allBallsMap.forEach((ball,show) -> {
				obsSetBallVisible(ball+"Ball", !ballPanel.getBallSelectedState(ball));
			});
		}
	}
	public void resetNineBall() {
		nineBallsMap.forEach((ball, show) -> {
			if (obs.getConnected()) obsSetBallVisible(ball+"Ball", show);
			ballPanel.setBallSelected(ball, !show);
		});
	}
	public void showAllBalls() {
		allBallsMap.forEach((ball, show) -> {
			if (obs.getConnected()) obsSetBallVisible(ball+"Ball", show);
			ballPanel.setBallSelected(ball, !show);
		});
	}
	public void hideAllBalls() {
		allBallsMap.forEach((ball, show) -> {
			if (obs.getConnected()) obsSetBallVisible(ball+"Ball", !show);
			ballPanel.setBallSelected(ball, show);
		});
	}
	private void connectAutoScore() {
		autoScoreSettingsPanel.addMessage("Trying to connect...");
		createAutoScoreWorker();
		autoScoreWorker.execute();
		mainFrame.setAutoScoreIconConnected(true);
		autoScoreConnected = true;
	}
	private void disconnectAutoScore() {
		autoScoreSettingsPanel.addMessage("Disconnecting...");
		autoScoreWorker.cancel(true);
		mainFrame.setAutoScoreIconConnected(false);
		autoScoreConnected = false;
	}
	private void sendAutoScoreConfig() {
		if(autoScoreConnected) {
			
		}
	}

	////// Listeners \\\\\\
	private class BtnCueBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("CueBall", !ballPanel.getCueBallSelectedState());
		}
	}
	private class BtnOneBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("OneBall", !ballPanel.getOneBallSelectedState());
		}
	}
	private class BtnTwoBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("TwoBall", !ballPanel.getTwoBallSelectedState());
		}
	}
	private class BtnThreeBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("ThreeBall", !ballPanel.getThreeBallSelectedState());
		}
	}
	private class BtnFourBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("FourBall", !ballPanel.getFourBallSelectedState());
		}
	}
	private class BtnFiveBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("FiveBall", !ballPanel.getFiveBallSelectedState());
		}
	}
	private class BtnSixBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("SixBall", !ballPanel.getSixBallSelectedState());
		}
	}
	private class BtnSevenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("SevenBall", !ballPanel.getSevenBallSelectedState());
		}
	}
	private class BtnEightBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("EightBall", !ballPanel.getEightBallSelectedState());
		}
	}
	private class BtnNineBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("NineBall", !ballPanel.getNineBallSelectedState());
		}
	}
	private class BtnTenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("TenBall", !ballPanel.getTenBallSelectedState());
		}
	}
	private class BtnElevenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("ElevenBall", !ballPanel.getElevenBallSelectedState());
		}
	}
	private class BtnTwelveBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("TwelveBall", !ballPanel.getTwelveBallSelectedState());
		}
	}
	private class BtnThirteenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("ThirteenBall", !ballPanel.getThirteenBallSelectedState());
		}
	}
	private class BtnFourteenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("FourteenBall", !ballPanel.getFourteenBallSelectedState());
		}
	}
	private class BtnFifteenBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSetBallVisible("FifteenBall", !ballPanel.getFifteenBallSelectedState());
		}
	}
	private class BtnSyncOBSListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsSyncBalls();
		}
	}
	private class BtnResetNineBallListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetNineBall();
		}
	}
	private class BtnShowAllBallsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showAllBalls();
		}
	}
	private class BtnHideAllBallsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hideAllBalls();
		}
	}

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
	private class AutoScoreSettingsSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			autoScoreSettingsPanel.saveSettings();
		}
	}
	private class AutoScoreSettingsConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			connectAutoScore();
		}
	}
	private class AutoScoreSettingsDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			disconnectAutoScore();
		}
	}
	private class AutoScoreConfigSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			autoScoreConfigPanel.saveSettings();
		}
	}
	private class AutoScoreConfigSendConfigListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sendAutoScoreConfig();
		}
	}
	private class OBSSceneListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setScene();
		}
	}
	private class OBSSetSceneListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setScene();
		}
	}
	private class OBSSceneFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			obs.setScene(txt.getText());
		}
	}

	private class OBSDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsController.disconnect();
			updateOBSDisconnected();
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Disconnected!");
		}
	}
	private class OBSConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			connectToOBS();
		}
	}
	private class OBSDisconnectItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsController.disconnect();
			updateOBSDisconnected();
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Disconnected!");
		}
	}
	private class OBSSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.saveSettings();
		}
	}
	private class OBSPushListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (obs.getConnected()) {
				tableController.writeAll();
				teamController.writeAll();
				statsController.displayAllStats();
			}
		}
	}
	private class OBSPullListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (obs.getConnected()) {
				teamController.fetchAll();
				teamController.displayAll();
//				tableController.writeAll();
//				teamController.writeAll();
//				statsController.displayAllStats();
			}
		}
	}
	private class OBSShowScoresListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (obs.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showScores(abstractButton.getModel().isSelected());
			}
		}
	}
	private class OBSShowTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (obs.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showTimer(abstractButton.getModel().isSelected());
			}
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
				//mainFrame.setVisible(false);
				mainFrame.closeWindow();
				loadWindowsAndControllers();
				mainFrame.setOBSIconConnected(obs.getConnected());
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
	private class AutoScoreMainPanelConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			connectAutoScore();
		}
	}
	private class AutoScoreMainPanelDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			disconnectAutoScore();
		}
	}
	private class AutoScoreMainPanelSettingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainController.showAutoScore();
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
	private class SwitchPlayer1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XXP1",false);
			statsEntryPanel.setFocusOnCode();
		}
	}
	private class SwitchPlayer2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XXP2",false);
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
	private void setScene() {
		if(obsController == null ) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " ERROR! Must connect before setting Scene");	
		} else {
			if(obs.getConnected()) {
			  if (!obsController.isFailed() && obs.getScene() != null && !obs.getScene().isEmpty()) {
				if (obs.getConnected()) {
					obsController.setCurrentScene(obs.getScene(), response -> { 
						if(settings.getShowParsed()) {
							obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Setting scene to: " + obs.getScene());
							obsController.getCurrentScene(response2 -> { 
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " Scene Set to: " + response2.getName());
							});
						} 
					});
				}else {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " ERROR! Must connect before setting Scene");
				}
			  }
			} else {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " ERROR! Must connect before setting Scene");	
			}
		}
	}
}
