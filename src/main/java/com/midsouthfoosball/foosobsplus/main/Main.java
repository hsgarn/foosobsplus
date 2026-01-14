/**
Copyright © 2020-2026 Hugh Garner
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.commands.CodeCommand;
import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.commands.CommandSwitch;
import com.midsouthfoosball.foosobsplus.commands.DGTCommand;
import com.midsouthfoosball.foosobsplus.commands.DMTCommand;
import com.midsouthfoosball.foosobsplus.commands.DSTCommand;
import com.midsouthfoosball.foosobsplus.commands.IGTCommand;
import com.midsouthfoosball.foosobsplus.commands.IMTCommand;
import com.midsouthfoosball.foosobsplus.commands.ISTCommand;
import com.midsouthfoosball.foosobsplus.commands.Memento;
import com.midsouthfoosball.foosobsplus.commands.PCACommand;
import com.midsouthfoosball.foosobsplus.commands.PEMCommand;
import com.midsouthfoosball.foosobsplus.commands.PKCommand;
import com.midsouthfoosball.foosobsplus.commands.PPMCommand;
import com.midsouthfoosball.foosobsplus.commands.PRACommand;
import com.midsouthfoosball.foosobsplus.commands.PRCommand;
import com.midsouthfoosball.foosobsplus.commands.PRGCommand;
import com.midsouthfoosball.foosobsplus.commands.PRMCommand;
import com.midsouthfoosball.foosobsplus.commands.PRNCommand;
import com.midsouthfoosball.foosobsplus.commands.PRRCommand;
import com.midsouthfoosball.foosobsplus.commands.PRSCommand;
import com.midsouthfoosball.foosobsplus.commands.PRTCommand;
import com.midsouthfoosball.foosobsplus.commands.PRTOCommand;
import com.midsouthfoosball.foosobsplus.commands.PSECommand;
import com.midsouthfoosball.foosobsplus.commands.PSGCCommand;
import com.midsouthfoosball.foosobsplus.commands.PSGCommand;
import com.midsouthfoosball.foosobsplus.commands.PSMCCommand;
import com.midsouthfoosball.foosobsplus.commands.PSMCommand;
import com.midsouthfoosball.foosobsplus.commands.PSRCommand;
import com.midsouthfoosball.foosobsplus.commands.PSSCCommand;
import com.midsouthfoosball.foosobsplus.commands.PSSCommand;
import com.midsouthfoosball.foosobsplus.commands.PSTCommand;
import com.midsouthfoosball.foosobsplus.commands.PSTOCommand;
import com.midsouthfoosball.foosobsplus.commands.PTCACommand;
import com.midsouthfoosball.foosobsplus.commands.PWCommand;
import com.midsouthfoosball.foosobsplus.commands.RTTCommand;
import com.midsouthfoosball.foosobsplus.commands.SGTCommand;
import com.midsouthfoosball.foosobsplus.commands.SPTCommand;
import com.midsouthfoosball.foosobsplus.commands.SRTCommand;
import com.midsouthfoosball.foosobsplus.commands.SSTCommand;
import com.midsouthfoosball.foosobsplus.commands.STTCommand;
import com.midsouthfoosball.foosobsplus.commands.UTTCommand;
import com.midsouthfoosball.foosobsplus.commands.XPCommand;
import com.midsouthfoosball.foosobsplus.commands.XPTCommand;
import com.midsouthfoosball.foosobsplus.commands.PHACommand;
import com.midsouthfoosball.foosobsplus.commands.PSACommand;
import com.midsouthfoosball.foosobsplus.commands.PNBCommand;
import com.midsouthfoosball.foosobsplus.controller.AutoScoreManager;
import com.midsouthfoosball.foosobsplus.controller.MainController;
import com.midsouthfoosball.foosobsplus.controller.MatchController;
import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.controller.TimerController;
import com.midsouthfoosball.foosobsplus.controller.TournamentController;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.MatchObserver;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.model.Tournament;
import com.midsouthfoosball.foosobsplus.view.AutoScoreConfigFrame;
import com.midsouthfoosball.foosobsplus.view.AutoScoreConfigPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreMainPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsFrame;
import com.midsouthfoosball.foosobsplus.view.APISettingsFrame;
import com.midsouthfoosball.foosobsplus.view.APISettingsPanel;
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsPanel;
import com.midsouthfoosball.foosobsplus.view.BallPanel;
import com.midsouthfoosball.foosobsplus.view.FiltersFrame;
import com.midsouthfoosball.foosobsplus.view.FiltersPanel;
import com.midsouthfoosball.foosobsplus.view.GameResultsWindowFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowFrame;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.HotKeysFrame;
import com.midsouthfoosball.foosobsplus.view.HotKeysPanel;
import com.midsouthfoosball.foosobsplus.view.LastScoredWindowFrame;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.Messages;
import com.midsouthfoosball.foosobsplus.view.OBSConnectFrame;
import com.midsouthfoosball.foosobsplus.view.OBSConnectPanel;
import com.midsouthfoosball.foosobsplus.view.OBSPanel;
import com.midsouthfoosball.foosobsplus.view.ParametersFrame;
import com.midsouthfoosball.foosobsplus.view.ParametersPanel;
import com.midsouthfoosball.foosobsplus.view.PartnerProgramFrame;
import com.midsouthfoosball.foosobsplus.view.ResetPanel;
import com.midsouthfoosball.foosobsplus.view.SourcesFrame;
import com.midsouthfoosball.foosobsplus.view.SourcesPanel;
import com.midsouthfoosball.foosobsplus.view.StatSourcesFrame;
import com.midsouthfoosball.foosobsplus.view.StatSourcesPanel;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;
import com.midsouthfoosball.foosobsplus.view.TimerPanel;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;
import com.midsouthfoosball.foosobsplus.view.TournamentPanel;
import com.midsouthfoosball.foosobsplus.obs.OBSManager;
import com.midsouthfoosball.foosobsplus.obs.OBSUICallback;

import java.nio.file.Files;
import java.util.concurrent.CountDownLatch;
/**
 * Main FoosOBS Object
 * @author Hugh Garner
 *
 */
public final class Main implements MatchObserver {
	private static final Logger 				logger 					= LoggerFactory.getLogger(Main.class);
	private static final String 				ON 						= "1"; //$NON-NLS-1$
	private static final String					OFF						= "0"; //$NON-NLS-1$
	////// Settings and OBSInterface setup \\\\\\
	private static final OBSInterface 			obsInterface 			= new OBSInterface();
	private static String						matchId					= ""; //$NON-NLS-1$
	private static final DateTimeFormatter 		dtf 					= DateTimeFormatter.ofPattern(Messages.getString("Main.DateTimePattern")); //$NON-NLS-1$
	private static com.midsouthfoosball.foosobsplus.api.APIServer apiServer;
	private static com.midsouthfoosball.foosobsplus.api.TeamService teamService;
	private static final StreamIndexer 			streamIndexer      		= new StreamIndexer(Settings.getControlParameter("datapath")); //$NON-NLS-1$
	private static AutoScoreManager 			autoScoreManager;
    private static final Map<String, String>	teamGameShowSourcesMap	= new HashMap<>();
   	private static HashMap<String, Boolean> allBallsMap 	= new HashMap<>();
	private static HashMap<String, Boolean> nineBallsMap 	= new HashMap<>();
	private static OBSManager obsManager;
	////// Watch Service for File changes \\\\\\
	private static WatchService 				watchService;
	////// CommandStack and UndoRedo setup \\\\\\
	private static int 							undoRedoPointer			= -1;
	private static final Stack<Command> 		commandStack 			= new Stack<>();
	private static final Stack<Memento> 		mementoStackTeam1 		= new Stack<>();
	private static final Stack<Memento> 		mementoStackTeam2		= new Stack<>();
	private static final Stack<Memento> 		mementoStackTeam3       = new Stack<>();
	private static final Stack<Memento> 		mementoStackStats 		= new Stack<>();
	private static final Stack<Memento> 		mementoStackMatch		= new Stack<>();
	private static final Stack<Memento> 		mementoStackGameClock	= new Stack<>();
	private static final Stack<String>			codeStack 				= new Stack<>();
	private static CommandSwitch 				mySwitch;
	////// Generate the Data Models (Mvc) \\\\\\
	private static final Tournament				tournament				= new Tournament(obsInterface);
	private static final Team 					team1 					= new Team(obsInterface, 1, Settings.getControlParameter("Side1Color")); //$NON-NLS-1$
	private static final Team 					team2 					= new Team(obsInterface, 2, Settings.getControlParameter("Side2Color")); //$NON-NLS-1$
	private static final Team         			team3              		= new Team(obsInterface, 3, Messages.getString("Main.None")); //$NON-NLS-1$
	private static final Match 					match					= new Match(obsInterface, team1, team2, team3);
	private static final Stats 					stats 					= new Stats(team1, team2);
	////// Create a TimeClock to be the Timer \\\\\\
	private static final TimeClock 				timeClock 				= new TimeClock(obsInterface);
	private static final GameClock       		gameClock           	= new GameClock(obsInterface);
	private static final LastScoredClock 		lastScored1Clock   		= new LastScoredClock();
	private static final LastScoredClock		lastScored2Clock    	= new LastScoredClock();
	private static final LastScoredClock 		lastScored3Clock		= new LastScoredClock();
	////// Create the View Panels to Display (mVc) \\\\\\
	private static final TournamentPanel		tournamentPanel 		= new TournamentPanel();
	private static final TimerPanel 			timerPanel 				= new TimerPanel();
	private static final OBSPanel				obsPanel				= new OBSPanel();
	private static final AutoScoreMainPanel		autoScoreMainPanel  	= new AutoScoreMainPanel();
	private static final MatchPanel				matchPanel				= new MatchPanel();
	private static final TeamPanel 				teamPanel1 				= new TeamPanel(1);
	private static final TeamPanel 				teamPanel2 				= new TeamPanel(2);
	private static final TeamPanel				teamPanel3				= new TeamPanel(3);
	private static final StatsEntryPanel 		statsEntryPanel 		= new StatsEntryPanel();
	private static final SwitchPanel 			switchPanel 			= new SwitchPanel();
	private static final ResetPanel 			resetPanel 				= new ResetPanel();
	private static final StatsDisplayPanel 		statsDisplayPanel 		= new StatsDisplayPanel();
    private static final BallPanel              ballPanel               = new BallPanel();
	////// Set up Timer and Settings Windows \\\\\\
	private static final ParametersFrame 		parametersFrame 		= new ParametersFrame();
	private static final ParametersPanel		parametersPanel			= parametersFrame.getSettingsPanel();
	private static final HotKeysFrame 			hotKeysFrame 			= new HotKeysFrame();
	private static final HotKeysPanel 			hotKeysPanel			= hotKeysFrame.getHotKeysPanel();
	private static final SourcesFrame			sourcesFrame			= new SourcesFrame();
	private static final SourcesPanel			sourcesPanel			= sourcesFrame.getSourcesPanel();
	private static final StatSourcesFrame		statSourcesFrame		= new StatSourcesFrame();
	private static final StatSourcesPanel		statSourcesPanel		= statSourcesFrame.getStatSourcesPanel();
	private static final FiltersFrame        	filtersFrame        	= new FiltersFrame();
	private static final FiltersPanel        	filtersPanel        	= filtersFrame.getFiltersPanel();
	private static final APISettingsFrame		apiSettingsFrame		= new APISettingsFrame();
	private static final APISettingsPanel		apiSettingsPanel		= apiSettingsFrame.getSettingsPanel();
	private static final PartnerProgramFrame 	partnerProgramFrame 	= new PartnerProgramFrame();
	private static final OBSConnectFrame		obsConnectFrame			= new OBSConnectFrame();
	private static final OBSConnectPanel		obsConnectPanel			= obsConnectFrame.getOBSConnectPanel();
	private static final AutoScoreSettingsFrame	autoScoreSettingsFrame	= new AutoScoreSettingsFrame();
	private static final AutoScoreSettingsPanel	autoScoreSettingsPanel	= autoScoreSettingsFrame.getAutoScoreSettingsPanel();
	private static final AutoScoreConfigFrame	autoScoreConfigFrame	= new AutoScoreConfigFrame();
	private static final AutoScoreConfigPanel	autoScoreConfigPanel	= autoScoreConfigFrame.getAutoScoreConfigPanel();
	////// Set up The Main Window JFrame \\\\\\
	private static MainFrame 					mainFrame;
	////// Set up independent Windows \\\\\\
	private static GameTableWindowPanel			gameTableWindowPanel;
	private static GameTableWindowFrame			gameTableWindowFrame;
	private static GameResultsWindowFrame		gameResultsWindowFrame;
	private static TimerWindowFrame 			timerWindowFrame;
	private static LastScoredWindowFrame 		lastScored1WindowFrame;
	private static LastScoredWindowFrame 		lastScored2WindowFrame;
	private static LastScoredWindowFrame		lastScored3WindowFrame;
	////// Build and Start the Controllers (mvC) \\\\\\
	private static MainController 				mainController;
	private static TimerController 				timerController;
	private static TeamController 				teamController;
	private static TournamentController 		tournamentController;
	private static MatchController 				matchController;
	private static StatsController 				statsController;
	private static SwingWorker<Boolean,String>  fileWatchWorker;
    {
        match.addObserver(this);
    }
	public Main() throws IOException {
		buildTeamGameShowSourcesMap();
		loadWindowsAndControllers();
		initializeOBSManager();
		OBS.setHost(Settings.getOBSParameter("OBSHost")); //$NON-NLS-1$
		OBS.setPort(Settings.getOBSParameter("OBSPort")); //$NON-NLS-1$
		OBS.setPassword(Settings.getOBSParameter("OBSPassword")); //$NON-NLS-1$
		setMainScene(Settings.getOBSParameter("OBSMainScene")); //$NON-NLS-1$
		updateOBSDisconnected();
		if (Settings.getOBSParameter("OBSAutoLogin").equals(ON)) { //$NON-NLS-1$
			if (OBS.getPassword().isEmpty() || OBS.getHost().isEmpty() || OBS.getPort().isEmpty()) {
				String msg = Messages.getString("Errors.Main.AutoLogin"); //$NON-NLS-1$
				String ttl = Messages.getString("Errors.Main.AutoLogin.Title"); //$NON-NLS-1$
				logger.warn(msg);
				JOptionPane.showMessageDialog(null, msg, ttl, 1);
			} else {
				connectToOBS();
			}
		}
        loadBallMaps();
		loadListeners();
		loadCommands();
		initializeAutoScoreManager();
		if (Settings.getAutoScoreParameter("AutoScoreSettingsAutoConnect").equals(ON)) { //$NON-NLS-1$
			autoScoreManager.connect();
		}
		createFileWatchWorker();
		fileWatchWorker.execute();
		// Initialize and start REST API server if enabled
		try {
			String apiEnabled = Settings.getAPIParameter("APIEnabled");
			logger.info("API Enabled setting: " + apiEnabled);
			if (apiEnabled != null && apiEnabled.equals(ON)) {
				logger.info("Starting REST API server...");
				teamService = new com.midsouthfoosball.foosobsplus.api.TeamService(teamController, tournament);
				apiServer = new com.midsouthfoosball.foosobsplus.api.APIServer(teamService);
				apiServer.start();
				logger.info("REST API server started successfully");
			} else {
				logger.info("REST API server is disabled (APIEnabled=" + apiEnabled + ")");
			}
		} catch (Exception e) {
			logger.error("Failed to start REST API server", e);
		}
	}
	/**
	 * Initializes the OBSManager with callbacks to update UI components.
	 */
	private static void initializeOBSManager() {
		obsManager = new OBSManager(new OBSUICallback() {
			@Override
			public void onConnectionStatusChanged(boolean connected) {
				if (connected) {
					updateOBSConnected();
				} else {
					updateOBSDisconnected();
				}
			}

			@Override
			public void onMessage(String message) {
				obsConnectPanel.addMessage(message);
			}

			@Override
			public void onMonitorListFetched(HashMap<Integer, String> monitorMap) {
				obsConnectPanel.updateMonitorList(monitorMap);
			}

			@Override
			public void onSceneListFetched(HashMap<Integer, String> sceneMap) {
				obsConnectPanel.updateSceneList(sceneMap);
			}

			@Override
			public void onShowTimerStateChanged(boolean visible) {
				obsPanel.setShowTimer(visible);
			}

			@Override
			public void onShowScoresStateChanged(boolean visible) {
				obsPanel.setShowScores(visible);
			}

			@Override
			public void onShowCutthroatStateChanged(boolean visible) {
				obsPanel.setShowCutthroat(visible);
			}

			@Override
			public void onCloseConnectWindow() {
				obsConnectFrame.setVisible(false);
			}

			@Override
			public void showErrorDialog(String title, String message) {
				JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
			}
		});
		obsManager.setBallPanel(ballPanel);
		obsManager.setTimerWindowCallback(show -> mainController.showTimerWindow(show != null && show));
		obsManager.setUpdateOnConnectCallback(() -> {
			tournamentController.writeAll();
			teamController.writeAll();
			statsController.displayAllStats();
		});
	}

	private static void buildTeamGameShowSourcesMap() {
		teamGameShowSourcesMap.clear();
		for (int x = 1; x <= 3; x++) {
			for (int y = 1; y <= 3; y++) {
				teamGameShowSourcesMap.put(Integer.toString(x)+Integer.toString(y), Settings.getTeamGameShowSource(x, y));
			}
		}
	}
	public static void connectToOBS() {
		obsManager.connect();
	}
	public static void updateOBSConnected() {
		OBS.setConnected(true);
		obsConnectPanel.disableConnect();
		mainFrame.enableConnect(false);
		mainFrame.setOBSIconConnected(true);
		if(Settings.getOBSParameter("OBSUpdateOnConnect").equals(ON)) { //$NON-NLS-1$
			tournamentController.writeAll();
			teamController.writeAll();
			statsController.displayAllStats();
		}
	}
	public static void updateOBSDisconnected() {
		OBS.setConnected(false);
		obsConnectPanel.enableConnect();
		mainFrame.enableConnect(true);
		mainFrame.setOBSIconConnected(false);
	}
	public static void shutdownAPIServer() {
		if (apiServer != null && apiServer.isRunning()) {
			apiServer.stop();
			logger.info("REST API server stopped during shutdown");
		}
	}

	public static void restartAPIServer() {
		try {
			String apiEnabled = Settings.getAPIParameter("APIEnabled");

			if (apiServer != null && apiServer.isRunning()) {
				apiServer.stop();
				logger.info("REST API server stopped");
			}

			// Check if API should be enabled
			if (apiEnabled != null && apiEnabled.equals(ON)) {
				// Create new instance to pick up new settings
				apiServer = new com.midsouthfoosball.foosobsplus.api.APIServer(teamService);
				apiServer.start();
				logger.info("REST API server restarted with new settings");
			} else {
				logger.info("REST API server is disabled (APIEnabled=" + apiEnabled + ")");
				apiServer = null;
			}
		} catch (Exception e) {
			logger.error("Error restarting REST API server", e);
		}
	}
	public static void obsSyncBalls() {
		if (obsManager != null) {
			obsManager.syncBalls();
		}
	}
	public static void resetNineBall() {
		if (obsManager != null) {
			obsManager.resetNineBall();
		}
	}
	public static void showAllBalls() {
		if (obsManager != null) {
			obsManager.showAllBalls();
		}
	}
	public static void hideAllBalls() {
		if (obsManager != null) {
			obsManager.hideAllBalls();
		}
	}

    private static void initializeAutoScoreManager() {
		autoScoreManager = new AutoScoreManager(
			autoScoreSettingsPanel,
			autoScoreConfigPanel,
			autoScoreMainPanel
		);
		autoScoreManager.setConnectionStateListener(
			connected -> mainFrame.setAutoScoreIconConnected(connected)
		);
		autoScoreManager.setScoreEventListener(
			code -> processCode(code, false)
		);
		// Register AutoScore listeners
		autoScoreSettingsPanel.addApplyListener(autoScoreManager.createSettingsApplyListener());
		autoScoreSettingsPanel.addSaveListener(autoScoreManager.createSettingsSaveListener());
		autoScoreSettingsPanel.addConnectListener(autoScoreManager.createSettingsConnectListener());
		autoScoreSettingsPanel.addDisconnectListener(autoScoreManager.createSettingsDisconnectListener());
		autoScoreSettingsPanel.addSearchListener(autoScoreManager.createSettingsSearchListener());
		autoScoreConfigPanel.addReadConfigListener(autoScoreManager.createConfigReadListener());
		autoScoreConfigPanel.addWriteConfigListener(autoScoreManager.createConfigWriteListener());
		autoScoreConfigPanel.addValidateConfigListener(autoScoreManager.createConfigValidateListener());
		autoScoreConfigPanel.addResetConfigListener(autoScoreManager.createConfigResetListener());
		autoScoreConfigPanel.addClearConfigListener(autoScoreManager.createConfigClearListener());
		autoScoreMainPanel.addConnectListener(autoScoreManager.createMainPanelConnectListener());
		autoScoreMainPanel.addDisconnectListener(autoScoreManager.createMainPanelDisconnectListener());
		autoScoreMainPanel.addSettingsListener(e -> mainController.showAutoScore());
	}
	private static void checkFilters(String code) {
		String filter;
		Integer teamNbr;
		if (code.equals("XIST1") || code.equals("XIST2")) { //$NON-NLS-1$ //$NON-NLS-2$
			if (code.equals("XIST1")) { //$NON-NLS-1$
				teamNbr = 1;
			} else {
				teamNbr = 2;
			}
			filter = "Team" + teamNbr + "Score"; //$NON-NLS-1$ //$NON-NLS-2$
			activateFilter(filter);
			int winState = match.getWinState();
			if (winState == 1) {
				winFilters(winState, teamNbr);
			} else {
				if (winState == 2) {
					winFilters(winState, teamNbr);
				}
			}
		} else {
			if (code.equals("XUTT1") || code.equals("XUTT2")) { //$NON-NLS-1$ //$NON-NLS-2$
				if (code.equals("XUTT1")) { //$NON-NLS-1$
					activateFilter("Team1TimeOut"); //$NON-NLS-1$
				} else {
					activateFilter("Team2TimeOut"); //$NON-NLS-1$
				}
			}	
		}
	}
	private static void winFilters(int winState, int teamNbr) {
		int a;
		int b;
		int c;
		String filter;
		String gameDuration;
		String type;
		boolean cutThroatMode = Settings.getControlParameter("CutThroatMode").equals(ON); //$NON-NLS-1$
		boolean isStreamTimerRunning = gameClock.isStreamTimerRunning();
		if(winState==1) {
			int gn = match.getCurrentGameNumber();
			if (gn > 1) {
				a = Integer.parseInt(match.getScoresTeam1()[gn-2]); 
				b = Integer.parseInt(match.getScoresTeam2()[gn-2]);
				c = Integer.parseInt(match.getScoresTeam3()[gn-2]);
			} else {
				a = 0;
				b = 0;
				c = 0;
			}
			filter = "Team" + teamNbr + "WinGame"; //$NON-NLS-1$ //$NON-NLS-2$
			gameDuration = gameClock.getLastGameTime();
			type = "Game"; //$NON-NLS-1$
		} else {
			a = team1.getScore();
			b = team2.getScore();
			c = team3.getScore();
			filter = "Team" + teamNbr + "WinMatch"; //$NON-NLS-1$ //$NON-NLS-2$
			gameDuration = match.getTimes()[match.getCurrentGameNumber()-1];
			type = "Match"; //$NON-NLS-1$
		}
		if (a == 0 || b == 0 || (cutThroatMode && c == 0)) {
			if(Settings.getControlParameter("ShowSkunk").equals(ON)) { //$NON-NLS-1$
				filter = "Team" + teamNbr + "Skunk"; //$NON-NLS-1$ //$NON-NLS-2$
				activateFilter(filter);
			}
		}
		activateFilter(filter);
		String result;
		String gameEndTime = gameClock.getStreamTime();
		LocalTime endTime = LocalTime.parse(gameEndTime, DateTimeFormatter.ofPattern("HH:mm:ss")); //$NON-NLS-1$
		Duration gameLength = Duration.ofHours(Long.parseLong(gameDuration.substring(0,2)))
				.plus(Duration.ofMinutes(Long.parseLong(gameDuration.substring(3,5))))
				.plus(Duration.ofSeconds(Long.parseLong(gameDuration.substring(6,8))));
		LocalTime startTime = endTime.minus(gameLength);
		if(cutThroatMode) {
			result = a + " " + team1.getForwardName() + " vs " + b + " " + team2.getForwardName() + "/" + team3.getForwardName() + " " + c + " (" + gameDuration + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		} else {
			result = a + " " + combinePlayerNames(1) + " vs " + combinePlayerNames(2) + " " + b + " (" + gameDuration + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		gameResultsWindowFrame.addLine("[" + ((isStreamTimerRunning) ? startTime : "00:00:00") + "] " + result); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		StringBuilder results;
		results = gameResultsWindowFrame.buildGameResults();
		matchController.updateGameResults(results);
		if(isStreamTimerRunning) {
			streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameEndTime + ": " + type + " end: " + result + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		
	}
	private static void activateFilter(String filter) {
		setSourceFilterVisibility(OBS.getMainScene(), Settings.getFiltersFilter(filter), true);
	}
	public static void loadWindowsAndControllers() {
		mainFrame = new MainFrame(Settings.getInstance(), tournamentPanel, timerPanel, obsPanel, autoScoreMainPanel, teamPanel1, teamPanel2, teamPanel3, statsEntryPanel,
				switchPanel, resetPanel, statsDisplayPanel, matchPanel, parametersFrame, hotKeysFrame, sourcesFrame, statSourcesFrame, filtersFrame,
				partnerProgramFrame, apiSettingsFrame, obsConnectFrame, autoScoreSettingsFrame, autoScoreConfigFrame, ballPanel);
		////// Set up independent Windows \\\\\\
		mainFrame.windowActivated(null);
		gameTableWindowPanel		= new GameTableWindowPanel();
		gameTableWindowFrame		= new GameTableWindowFrame(gameTableWindowPanel, mainFrame);
		gameResultsWindowFrame		= new GameResultsWindowFrame();
		timerWindowFrame 			= new TimerWindowFrame(mainFrame);
		lastScored1WindowFrame 		= new LastScoredWindowFrame(mainFrame, 1);
		lastScored2WindowFrame 		= new LastScoredWindowFrame(mainFrame, 2);
		lastScored3WindowFrame  	= new LastScoredWindowFrame(mainFrame, 3);
		////// Build and Start the Controllers (mvC) \\\\\\
		mainController 			= new MainController(mainFrame, timerWindowFrame, lastScored1WindowFrame, lastScored2WindowFrame, lastScored3WindowFrame, gameTableWindowFrame, gameResultsWindowFrame);
		timerController 		= new TimerController(obsInterface, timerPanel, timerWindowFrame, timeClock, lastScored1WindowFrame, lastScored1Clock, lastScored2WindowFrame, lastScored2Clock, lastScored3WindowFrame, lastScored3Clock);
		teamController 			= new TeamController(obsInterface, team1, team2, team3, match, teamPanel1, teamPanel2, teamPanel3, switchPanel, matchPanel, gameTableWindowPanel, statsDisplayPanel, timerController, lastScored1Clock, lastScored2Clock, lastScored3Clock, gameClock, mainController);
		tournamentController 	= new TournamentController(obsInterface, tournament, match, tournamentPanel);
		matchController 		= new MatchController(match, stats, gameClock, lastScored1Clock, lastScored2Clock, matchPanel, statsEntryPanel, statsDisplayPanel, switchPanel, gameTableWindowPanel, teamController, streamIndexer);
		statsController 		= new StatsController(stats, statsDisplayPanel, teamController);
		gameClock.addGameClockTimerListener(new GameClockTimerListener());
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
		hotKeysPanel.addApplyListener(new HotKeysApplyListener());
		hotKeysPanel.addSaveListener(new HotKeysSaveListener());
		sourcesPanel.addApplyListener(new SourcesApplyListener());
		sourcesPanel.addSaveListener(new SourcesSaveListener());
		statSourcesPanel.addApplyListener(new StatSourcesApplyListener());
		statSourcesPanel.addSaveListener(new StatSourcesSaveListener());
		apiSettingsPanel.addApplyListener((ActionEvent ae) -> {
			apiSettingsPanel.applySettings();
		});
		apiSettingsPanel.addApplyCloseListener((ActionEvent ae) -> {
			apiSettingsPanel.saveSettings();
			apiSettingsFrame.setVisible(false);
		});
		apiSettingsPanel.addCancelListener((ActionEvent ae) -> {
			apiSettingsPanel.reloadSettings();
			apiSettingsFrame.setVisible(false);
		});
		parametersPanel.addApplyListener(new ParametersApplyListener());
		parametersPanel.addSaveListener(new ParametersSaveListener());
		parametersPanel.addEnableShowSkunkListener(new OBSEnableSkunkListener());
		obsConnectPanel.addSetMainSceneListener(new OBSSetMainSceneListener());
		obsConnectPanel.addFetchMonitorsListener(new OBSFetchMonitorsListener());
		obsConnectPanel.addProjectListener(new OBSProjectListener());
		obsConnectPanel.addActivateSceneListener(new OBSActivateSceneListener());
		obsConnectPanel.addConnectListener(new OBSConnectListener());
		obsConnectPanel.addDisconnectListener(new OBSDisconnectListener());
		obsConnectPanel.addMainSceneFocusListener(new OBSMainSceneFocusListener());
		obsConnectPanel.addMainSceneListener(new OBSMainSceneListener());
		obsConnectPanel.addApplyListener(new OBSApplyListener());
		obsConnectPanel.addSaveListener(new OBSSaveListener());
		obsPanel.addConnectListener(new OBSConnectListener());
		obsPanel.addDisconnectListener(new OBSDisconnectListener());
		obsPanel.addPushListener(new OBSPushListener());
		obsPanel.addPullListener(new OBSPullListener());
		obsPanel.addShowScoresListener(new OBSShowScoresListener());
		obsPanel.addShowTimerListener(new OBSShowTimerListener());
		obsPanel.addEnableSkunkListener(new OBSEnableSkunkListener());
		obsPanel.addStartStreamListener(new OBSStartStreamListener());
		obsPanel.addShowCutthroatListener(new OBSShowCutthroatListener());
		statsEntryPanel.addUndoListener(new StatsEntryUndoListener());
		statsEntryPanel.addRedoListener(new StatsEntryRedoListener());
		statsEntryPanel.addCodeListener(new CodeListener());
		tournamentPanel.addClearListener(new TableClearAllListener());
		statsEntryPanel.addStatsClearListener(new StatsClearListener());
		teamPanel1.addScoreIncreaseListener(new ScoreIncreaseListener());
		teamPanel2.addScoreIncreaseListener(new ScoreIncreaseListener());
		teamPanel3.addScoreIncreaseListener(new ScoreIncreaseListener());
		teamPanel1.addScoreDecreaseListener(new ScoreDecreaseListener());
		teamPanel2.addScoreDecreaseListener(new ScoreDecreaseListener());
		teamPanel3.addScoreDecreaseListener(new ScoreDecreaseListener());
		teamPanel1.addGameCountIncreaseListener(new GameCountIncreaseListener());
		teamPanel2.addGameCountIncreaseListener(new GameCountIncreaseListener());
		teamPanel3.addGameCountIncreaseListener(new GameCountIncreaseListener());
		teamPanel1.addGameCountDecreaseListener(new GameCountDecreaseListener());
		teamPanel2.addGameCountDecreaseListener(new GameCountDecreaseListener());
		teamPanel3.addGameCountDecreaseListener(new GameCountDecreaseListener());
		teamPanel1.addMatchCountIncreaseListener(new MatchCountIncreaseListener());
		teamPanel2.addMatchCountIncreaseListener(new MatchCountIncreaseListener());
		teamPanel3.addMatchCountIncreaseListener(new MatchCountIncreaseListener());
		teamPanel1.addMatchCountDecreaseListener(new MatchCountDecreaseListener());
		teamPanel2.addMatchCountDecreaseListener(new MatchCountDecreaseListener());
		teamPanel3.addMatchCountDecreaseListener(new MatchCountDecreaseListener());
		teamPanel1.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		teamPanel2.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		teamPanel3.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		teamPanel1.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		teamPanel2.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		teamPanel3.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		teamPanel1.addResetListener(new ResetListener());
		teamPanel2.addResetListener(new ResetListener());
		teamPanel3.addResetListener(new ResetListener());
		teamPanel1.addWarnListener(new WarnListener());
		teamPanel2.addWarnListener(new WarnListener());
		teamPanel3.addWarnListener(new WarnListener());
		teamPanel1.addKingSeatListener(new KingSeatListener());
		teamPanel2.addKingSeatListener(new KingSeatListener());
		teamPanel3.addKingSeatListener(new KingSeatListener());
		teamPanel1.addSwitchPositionsListener(new SwitchPositionsListener());
		teamPanel2.addSwitchPositionsListener(new SwitchPositionsListener());
		teamPanel3.addSwitchPositionsListener(new SwitchPositionsListener());
		timerPanel.addShotTimerListener(new ShotTimerListener());
		timerPanel.addPassTimerListener(new PassTimerListener());
		timerPanel.addTimeOutTimerListener(new TimeOutTimerListener());
		timerPanel.addGameTimerListener(new GameTimerListener());
		timerPanel.addRecallTimerListener(new RecallTimerListener());
		timerPanel.addResetTimerListener(new ResetTimerListener());
		matchPanel.addStartEventListener(new StartEventListener());
		matchPanel.addStartMatchListener(new StartMatchListener());
		matchPanel.addPauseMatchListener(new PauseMatchListener());
		matchPanel.addEndMatchListener(new EndMatchListener());
		matchPanel.addStartGameListener(new StartGameListener());
		switchPanel.addSwitchSidesListener(new SwitchSidesListener());
		switchPanel.addSwitchTeamsListener(new SwitchTeamsListener());
		switchPanel.addSwitchPlayer1Listener(new SwitchPlayer1Listener());
		switchPanel.addSwitchPlayer2Listener(new SwitchPlayer2Listener());
		switchPanel.addSwitchScoresListener(new SwitchScoresListener());
		switchPanel.addSwitchGameCountsListener(new SwitchGameCountsListener());
		switchPanel.addSwitchMatchCountsListener(new SwitchMatchCountsListener());
		switchPanel.addSwitchTimeOutsListener(new SwitchTimeOutsListener());
		switchPanel.addSwitchResetWarnsListener(new SwitchResetWarnsListener());
		switchPanel.addClearAllListener(new ClearAllListener());
		resetPanel.addResetNamesListener(new ResetNamesListener());
		resetPanel.addResetScoresListener(new ResetScoresListener());
		resetPanel.addResetGameCountsListener(new ResetGameCountsListener());
		resetPanel.addResetMatchCountsListener(new ResetMatchCountsListener());
		resetPanel.addResetTimeOutsListener(new ResetTimeOutsListener());
		resetPanel.addResetResetWarnsListener(new ResetResetWarnsListener());
		resetPanel.addResetAllListener(new ResetAllListener());
		mainFrame.addOBSDisconnectItemListener(new OBSDisconnectItemListener());
		timerWindowFrame.addTimerWindowClosedListener(new TimerWindowCloseListener());
		filtersPanel.addTeam1ScoreFilterListener(new Team1ScoreFilterListener());
		filtersPanel.addTeam2ScoreFilterListener(new Team2ScoreFilterListener());
		filtersPanel.addTeam1WinGameFilterListener(new Team1WinGameFilterListener());
		filtersPanel.addTeam2WinGameFilterListener(new Team2WinGameFilterListener());
		filtersPanel.addTeam1WinMatchFilterListener(new Team1WinMatchFilterListener());
		filtersPanel.addTeam2WinMatchFilterListener(new Team2WinMatchFilterListener());
		filtersPanel.addTeam1TimeOutFilterListener(new Team1TimeOutFilterListener());
		filtersPanel.addTeam2TimeOutFilterListener(new Team2TimeOutFilterListener());
		filtersPanel.addTeam1ResetFilterListener(new Team1ResetFilterListener());
		filtersPanel.addTeam2ResetFilterListener(new Team2ResetFilterListener());
		filtersPanel.addTeam1WarnFilterListener(new Team1WarnFilterListener());
		filtersPanel.addTeam2WarnFilterListener(new Team2WarnFilterListener());
		filtersPanel.addTeam1SwitchPositionsFilterListener(new Team1SwitchPositionsFilterListener());
		filtersPanel.addTeam2SwitchPositionsFilterListener(new Team2SwitchPositionsFilterListener());
		filtersPanel.addTeam1SkunkFilterListener(new Team1SkunkFilterListener());
		filtersPanel.addTeam2SkunkFilterListener(new Team2SkunkFilterListener());
		filtersPanel.addStartMatchFilterListener(new StartMatchFilterListener());
		filtersPanel.addStartGameFilterListener(new StartGameFilterListener());
		filtersPanel.addSwitchSidesFilterListener(new SwitchSidesFilterListener());
		filtersPanel.addMeatballFilterListener(new MeatballFilterListener());
		team1.addPropertyChangeListener(new TeamPropertyListener());
		team2.addPropertyChangeListener(new TeamPropertyListener());
		team3.addPropertyChangeListener(new TeamPropertyListener());
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

    /**
     * Called when Match detects meatball condition
     */
    @Override
	public void onMeatball() {
		activateFilter("Meatball"); //$NON-NLS-1$
	}
	public static void cutthroatRotate(int rotate) {
		if (rotate ==1) {
			Memento tmpTeam2 = saveState(team2);
			Memento tmpTeam3 = saveState(team3);
			team2.restoreState(tmpTeam3.getState());
			team3.restoreState(tmpTeam2.getState());
			team2.setTeamNbr(2);
			team3.setTeamNbr(3);
			matchController.displayAllStats();
			teamController.displayAll();
		} else if (rotate ==2 ) {
			Memento tmpTeam1 = saveState(team1);
			Memento tmpTeam2 = saveState(team2);
			Memento tmpTeam3 = saveState(team3);
			team1.restoreState(tmpTeam2.getState());
			team2.restoreState(tmpTeam3.getState());
			team3.restoreState(tmpTeam1.getState());
			team1.setTeamNbr(1);
			team2.setTeamNbr(2);
			team3.setTeamNbr(3);
			matchController.displayAllStats();
			teamController.displayAll();
		}
	}
	public static void startEvent() {
		if(gameClock.isStreamTimerRunning()) {
			streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartEvent") + Messages.getString("Main.Pressed") + tournament.getTournamentName() + ": " + tournament.getEventName() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		}
	}
	public static void startMatch() {
		matchId = createMatchId();
		matchController.startMatch(matchId);
		if(gameClock.isStreamTimerRunning()) {
			if(Settings.getControlParameter("CutThroatMode").equals(ON)) { //$NON-NLS-1$
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartMatch") + Messages.getString("Main.Pressed") + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
			} else {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartMatch") + Messages.getString("Main.Pressed") + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			}
		}
	}
	public static void endMatch() {
		matchController.endMatch();
		if(gameClock.isStreamTimerRunning()) {
			streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.EndMatch") + " Pressed.\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
	}
	public static void startGame() {
		if(gameClock.isStreamTimerRunning()) {
			if(Settings.getControlParameter("CutThroatMode").equals(ON)) { //$NON-NLS-1$
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartGame") + Messages.getString("Main.Pressed") + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
			} else {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartGame") + Messages.getString("Main.Pressed") + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			}
		}
		matchController.startGame();
	}
	public static void switchSides() {
		Memento tmpTeam1 = saveState(team1);
		Memento tmpTeam2 = saveState(team2);
		team1.restoreState(tmpTeam2.getState());
		team2.restoreState(tmpTeam1.getState());
		team1.setTeamNbr(1);
		team2.setTeamNbr(2);
		matchController.switchSides();
		teamController.switchSides();
	}
	public static void setShowParsed(boolean showParsed) {
		Settings.setControlParameter("ShowParsed",showParsed); //$NON-NLS-1$
		try {
			Settings.saveControlConfig();
		} catch (IOException e) {
        	logger.error(e.toString());
		}
	}
	private static String createMatchId() {
		return matchController.createMatchId();
	}
	public static void processCode(String code, Boolean isRedo) {
		Command commandStatus;
		if (!isRedo) { 
  			makeMementos();
            codeStack.push(code);
		}
		stats.setCode(code);
		stats.addCodeToHistory(code);
		statsEntryPanel.updateCode(""); //$NON-NLS-1$
		statsEntryPanel.updateCodeHistory(code);
		if (stats.getIsCommand()) {
			if (!isRedo) { 
				commandStatus = commandStack.push(mySwitch.execute(stats.getCommand()));
				if (commandStatus == null) {
					statsEntryPanel.errorCodeHistory();
				} else {
					checkFilters(code);
				}
				undoRedoPointer++;
			}
		} else {
			if (!isRedo) { 
				commandStatus = commandStack.push(mySwitch.execute("code")); //$NON-NLS-1$
				if (commandStatus == null) {
					statsEntryPanel.errorCodeHistory();
				}
				undoRedoPointer++;
			}
			if (stats.getIsError()) {
				statsEntryPanel.errorCodeHistory();
			}
			int rotate = 0;
			if (stats.getTeamScored(1)) {
				rotate = matchController.incrementScore(1);
			} else if (stats.getTeamScored(2)) {
				rotate = matchController.incrementScore(2);
			}
			if (rotate > 0) cutthroatRotate(rotate);
			if (stats.getTeamTimeOut(1)) {
				teamController.callTimeOut(1);
			} else if (stats.getTeamTimeOut(2)) {
				teamController.callTimeOut(2);
			} else if (stats.getTeamTimeOut(3)) {
				teamController.callTimeOut(3);
			} else {
				if (stats.getIsThreeRod()||stats.getIsTwoRod()) teamController.startShotTimer();
				if (stats.getIsFiveRod()) teamController.startPassTimer();
			}
		}
		statsController.displayAllStats();
	}
	public static boolean getLastCodeError() {
		return stats.getIsError();
	}
	public static String getLastCodeErrorMsg() {
		return stats.getErrorMsg();
	}
	/**
	 * Gets the OBSManager instance for OBS operations.
	 * @return the OBSManager instance
	 */
	public static OBSManager getOBSManager() {
		return obsManager;
	}
	private static void makeMementos() {
		deleteElementsAfterPointer(undoRedoPointer);
        mementoStackTeam1.push(saveState(team1));
		mementoStackTeam2.push(saveState(team2));
		mementoStackTeam3.push(saveState(team3));
   		mementoStackStats.push(saveState(stats));
   		mementoStackMatch.push(saveState(match));
   		mementoStackGameClock.push(saveState(gameClock));
	}
	private static Memento saveState(Object object) {
		return new Memento(object);
	}
	public static void undo() 	{
		if(undoRedoPointer < 0) return;
		codeStack.get(undoRedoPointer);
	    commandStack.get(undoRedoPointer);
	    Memento mementoTeam1 = mementoStackTeam1.get(undoRedoPointer);
	    team1.restoreState(mementoTeam1.getState());
	    Memento mementoTeam2 = mementoStackTeam2.get(undoRedoPointer);
	    team2.restoreState(mementoTeam2.getState());
	    Memento mementoTeam3 = mementoStackTeam3.get(undoRedoPointer);
	    team3.restoreState(mementoTeam3.getState());
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
		setFocusOnCode();	
		matchController.updateGameTables();
	}
 	public static void redo() 	{
 		char commandChar = 'X';
 		Boolean isRedo = true;
 		String tempCode;
 		Boolean isCommand;
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
		    	statsEntryPanel.updateCodeHistory(tempCode + "<Unknown"); //$NON-NLS-1$
	    	}
	    } else {
	    	processCode(tempCode,isRedo);
	    }
		teamController.displayAll();
		statsController.displayAllStats();
		matchController.updateGameTables();
	}
	private static void deleteElementsAfterPointer(int undoRedoPointer) {
	    if (commandStack.size() >= 1)  {
		    for(int i = commandStack.size()-1; i > undoRedoPointer; i--)
		    {
		        commandStack.remove(i);
		        codeStack.remove(i);
		        mementoStackTeam1.remove(i);
		        mementoStackTeam2.remove(i);
		        mementoStackTeam3.remove(i);
		        mementoStackStats.remove(i);
		        mementoStackMatch.remove(i);
		        mementoStackGameClock.remove(i);
		    }
	    }
    }
	private static void loadCommands() {
		mySwitch = new CommandSwitch();

		// Stats-only commands
		mySwitch.register("PSE", new PSECommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PSM", new PSMCommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PEM", new PEMCommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PSG", new PSGCommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PSS", new PSSCommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PNB", new PNBCommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PSA", new PSACommand(statsController)); //$NON-NLS-1$
		mySwitch.register("PHA", new PHACommand(statsController)); //$NON-NLS-1$

		// Stats + match commands
		mySwitch.register("PPM", new PPMCommand(statsController, matchController)); //$NON-NLS-1$

		// Stats + team commands
		mySwitch.register("SST", new SSTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("SPT", new SPTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("SGT", new SGTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("STT", new STTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("SRT", new SRTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRT", new PRTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PST", new PSTCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PSSC", new PSSCCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PSGC", new PSGCCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PSMC", new PSMCCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PSTO", new PSTOCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PSR", new PSRCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRN", new PRNCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRS", new PRSCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRG", new PRGCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRM", new PRMCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRTO", new PRTOCommand(statsController, teamController)); //$NON-NLS-1$
		mySwitch.register("PRR", new PRRCommand(statsController, teamController)); //$NON-NLS-1$

		// Stats + team + match commands
		mySwitch.register("PCA", new PCACommand(statsController, teamController, matchController)); //$NON-NLS-1$
		mySwitch.register("PRA", new PRACommand(statsController, teamController, matchController)); //$NON-NLS-1$

		// Stats + tournament commands
		mySwitch.register("PTCA", new PTCACommand(statsController, tournamentController)); //$NON-NLS-1$

		// Numbered commands (1-3) - stats + match
		for (int i = 1; i <= 3; i++) {
			mySwitch.register("IST" + i, new ISTCommand(statsController, matchController, i)); //$NON-NLS-1$
			mySwitch.register("DST" + i, new DSTCommand(statsController, matchController, i)); //$NON-NLS-1$
		}

		// Numbered commands (1-3) - stats + team
		for (int i = 1; i <= 3; i++) {
			mySwitch.register("IGT" + i, new IGTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("IMT" + i, new IMTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("DGT" + i, new DGTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("DMT" + i, new DMTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("UTT" + i, new UTTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("RTT" + i, new RTTCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("PRT" + i, new PRCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("PWT" + i, new PWCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("PKT" + i, new PKCommand(statsController, teamController, i)); //$NON-NLS-1$
			mySwitch.register("XPT" + i, new XPTCommand(statsController, teamController, i)); //$NON-NLS-1$
		}

		// Numbered commands (1-2 only) - stats + team
		for (int i = 1; i <= 2; i++) {
			mySwitch.register("XP" + i, new XPCommand(statsController, teamController, i)); //$NON-NLS-1$
		}

		// Code command
		mySwitch.register("code", new CodeCommand(statsController)); //$NON-NLS-1$
	}
	public static void showScores(boolean show) {
		obsPanel.setShowScores(show);
		showSource(Settings.getSourceParameter("ShowScores"), show); //$NON-NLS-1$
		setFocusOnCode();
	}
	public static void showTimer(boolean show) {
		mainController.showTimerWindow(show);
		showSource(Settings.getSourceParameter("ShowTimer"), show); //$NON-NLS-1$
		setFocusOnCode();
	}
	public static void setFocusOnCode() {
		statsEntryPanel.setFocusOnCode();
	}
	public static void showCutthroat(boolean show) {
		obsPanel.setShowCutthroat(show);
		showSource(Settings.getSourceParameter("ShowCutthroat"), show); //$NON-NLS-1$
		setFocusOnCode();
	}
	public static void showSource(String source, boolean show) {
		if (obsManager != null) {
			obsManager.showSource(source, show);
		}
	}
	public static void setSourceFilterVisibility(String source, String filter, boolean show) {
		if (obsManager != null) {
			obsManager.setSourceFilterVisibility(source, filter, show);
		}
	}
	private static String ripTeamNumber(String name) {
		return name.replaceAll("[^0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}
	////// Listeners \\\\\\
	private static class BtnCueBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Cue", !ballPanel.getCueBallSelectedState());
		}
	}
	private static class BtnOneBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("One", !ballPanel.getOneBallSelectedState());
		}
	}
	private static class BtnTwoBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Two", !ballPanel.getTwoBallSelectedState());
		}
	}
	private static class BtnThreeBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Three", !ballPanel.getThreeBallSelectedState());
		}
	}
	private static class BtnFourBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Four", !ballPanel.getFourBallSelectedState());
		}
	}
	private static class BtnFiveBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Five", !ballPanel.getFiveBallSelectedState());
		}
	}
	private static class BtnSixBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Six", !ballPanel.getSixBallSelectedState());
		}
	}
	private static class BtnSevenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Seven", !ballPanel.getSevenBallSelectedState());
		}
	}
	private static class BtnEightBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Eight", !ballPanel.getEightBallSelectedState());
		}
	}
	private static class BtnNineBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Nine", !ballPanel.getNineBallSelectedState());
		}
	}
	private static class BtnTenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Ten", !ballPanel.getTenBallSelectedState());
		}
	}
	private static class BtnElevenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Eleven", !ballPanel.getElevenBallSelectedState());
		}
	}
	private static class BtnTwelveBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Twelve", !ballPanel.getTwelveBallSelectedState());
		}
	}
	private static class BtnThirteenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Thirteen", !ballPanel.getThirteenBallSelectedState());
		}
	}
	private static class BtnFourteenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Fourteen", !ballPanel.getFourteenBallSelectedState());
		}
	}
	private static class BtnFifteenBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.toggleBall("Fifteen", !ballPanel.getFifteenBallSelectedState());
		}
	}
	private static class BtnSyncOBSListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsSyncBalls();
		}
	}
	private static class BtnResetNineBallListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			resetNineBall();
		}
	}
	private static class BtnShowAllBallsListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			showAllBalls();
		}
	}
	private static class BtnHideAllBallsListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			hideAllBalls();
		}
	}
    private static class CodeListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			Boolean isRedo = false;
			JTextField txt = (JTextField) e.getSource();
			String code = txt.getText().toUpperCase();
			if(code.equals("XU")) { //$NON-NLS-1$
				undo();
			} else {
				if(code.equals("XR")) { //$NON-NLS-1$
					redo();
					statsEntryPanel.updateCode(null);
				} else {
					processCode(code, isRedo);
				}
			}
		}
	}
	private static class StatsEntryUndoListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			undo();
		}
	}
	private static class StatsEntryRedoListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			redo();
			setFocusOnCode();	
		}
	}
	private static class StatsClearListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			stats.clearAll();
			statsEntryPanel.clearAll();
			undoRedoPointer = -1;
			setFocusOnCode();
		}
	}
	private static void hotKeysSaveSettings() {
		if (hotKeysPanel.saveSettings()) {
			matchPanel.updateMnemonics();
			teamPanel1.updateMnemonics();
			teamPanel2.updateMnemonics();
			teamPanel3.updateMnemonics();
			timerPanel.updateMnemonics();
			switchPanel.updateMnemonics();
			resetPanel.updateMnemonics();
			statsEntryPanel.updateMnemonics();
			obsPanel.updateMnemonics();
		}
	}
	private static class HotKeysApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			hotKeysSaveSettings();
		}
	}
	private static class HotKeysSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			hotKeysSaveSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
		}
	}
	private static class SourcesApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (sourcesPanel.saveSettings()) {
				buildTeamGameShowSourcesMap();
			}
		}
	}
	private static class SourcesSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (sourcesPanel.saveSettings()) {
				buildTeamGameShowSourcesMap();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		}
	}
	private static class StatSourcesApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			statSourcesPanel.saveSettings();
		}
	}
	private static class StatSourcesSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (statSourcesPanel.saveSettings()) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		}
	}
	private static class OBSMainSceneListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			setMainScene(txt.getText());
		}
	}
	private static class OBSSetMainSceneListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			String scene = obsConnectPanel.getSelectedSceneName();
			if (scene != null && !scene.isEmpty()) {
				obsConnectPanel.updateMainScene(scene);
				setMainScene(scene);
			}
		}
	}
	private static class OBSFetchMonitorsListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.fetchMonitorList();
		}
	}
	private static class OBSProjectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			String sceneName = obsConnectPanel.getSelectedSceneName();
			Number monitorIndex = obsConnectPanel.getSelectedMonitor();
			obsManager.projectSource(sceneName, monitorIndex);
		}
	}
	private static class OBSActivateSceneListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			String scene = obsConnectPanel.getSelectedSceneName();
			activateOBSScene(scene);
		}
	}
	private static class OBSMainSceneFocusListener extends FocusAdapter {
        @Override
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			setMainScene(txt.getText());
		}
	}
	private static class OBSDisconnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.disconnect();
			setFocusOnCode();
		}
	}
	private static class OBSConnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.updateOBS();
			connectToOBS();
			setFocusOnCode();
		}
	}
	private static class OBSDisconnectItemListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsManager.disconnect();
			obsConnectPanel.updateOBS();
			setFocusOnCode();
		}
	}
	private static class OBSApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.saveSettings();
			setFocusOnCode();
		}
	}	private static class OBSSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.saveSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			setFocusOnCode();
		}
	}
	private static class OBSPushListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				tournamentController.writeAll();
				teamController.writeAll();
				statsController.displayAllStats();
                setTeamGameCountVisible("Team1", team1.getGameCount());
                setTeamGameCountVisible("Team2", team2.getGameCount());
                setTeamGameCountVisible("Team3", team3.getGameCount());
			}
			setFocusOnCode();
		}
	}
	private static class OBSPullListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				teamController.fetchAll();
				teamController.displayAll();
				tournamentController.fetchAll();
                setTeamGameCountVisible("Team1", team1.getGameCount());
                setTeamGameCountVisible("Team2", team2.getGameCount());
                setTeamGameCountVisible("Team3", team3.getGameCount());
			}
			setFocusOnCode();
		}
	}
	private static class OBSShowScoresListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showScores(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private static class OBSShowTimerListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showTimer(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private static class OBSShowCutthroatListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showCutthroat(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private static class OBSEnableSkunkListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			boolean showSkunkFlag = abstractButton.getModel().isSelected();
			obsPanel.setEnableSkunk(showSkunkFlag);
			parametersPanel.setEnableShowSkunk(showSkunkFlag);
			if (showSkunkFlag) {
				Settings.setControlParameter("ShowSkunk",ON); //$NON-NLS-1$
			} else {
				Settings.setControlParameter("ShowSkunk",OFF); //$NON-NLS-1$
			}
			setFocusOnCode();
		}
	}
	private static class OBSStartStreamListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			boolean startStreamFlag = abstractButton.getModel().isSelected();
			obsPanel.setStartStream(startStreamFlag);
			if (startStreamFlag) {
				gameClock.startStreamTimer();
			} else {
				gameClock.stopStreamTimer();
			}
			setFocusOnCode();
		}
	}
	private static class GameClockTimerListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			obsPanel.updateTimerDisplay(gameClock.getStreamTime());
		}
	}
	private static class ParametersApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			saveParameterSettings();
		}
	}
	private static class ParametersSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			saveParameterSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			setFocusOnCode();
		}
	}
	private static void saveParameterSettings() {
		int oldGamesToWin = Integer.parseInt(Settings.getControlParameter("GamesToWin")); //$NON-NLS-1$
		int oldCutthroatMode = Integer.parseInt(Settings.getControlParameter("CutThroatMode")); //$NON-NLS-1$
		parametersPanel.saveSettings(Settings.getInstance());
		int newGamesToWin = Integer.parseInt(Settings.getControlParameter("GamesToWin")); //$NON-NLS-1$
		int newCutthroatMode = Integer.parseInt(Settings.getControlParameter("CutThroatMode")); //$NON-NLS-1$
		if (oldGamesToWin != newGamesToWin || oldCutthroatMode != newCutthroatMode) {
			matchPanel.resizeGameTable();
			gameTableWindowPanel.resizeGameTable();
			match.setMaxPossibleGames(Settings.getMaxGameNumber());
		}
		teamPanel1.setTitle();
		teamPanel2.setTitle();
		teamPanel3.setTitle();
		teamController.displayAll();
	}
	private static class ScoreIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIST" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class ScoreDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDST" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class GameCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIGT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class GameCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDGT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class MatchCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIMT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class MatchCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDMT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class TimeOutCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XUTT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class TimeOutCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XRTT" + ripTeamNumber(btn.getName()); //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class ResetListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XPRT" + teamNumber; //$NON-NLS-1$
			String filter = "Team" + teamNumber + "Reset"; //$NON-NLS-1$ //$NON-NLS-2$
			processCode(code,false);
			if (btn.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private static class WarnListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XPWT" + teamNumber; //$NON-NLS-1$
			String filter = "Team" + teamNumber + "Warn"; //$NON-NLS-1$ //$NON-NLS-2$
			processCode(code,false);
			if (btn.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private static class KingSeatListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox ckbx = (JCheckBox) e.getSource();
			String teamNumber = ripTeamNumber(ckbx.getName());
			String code = "XPKT" + teamNumber; //$NON-NLS-1$
			String filter = "Team" + teamNumber + "KingSeat"; //$NON-NLS-1$ //$NON-NLS-2$
			processCode(code,false);
			if (ckbx.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private static class SwitchPositionsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XXPT" + teamNumber; //$NON-NLS-1$
			String filter = "Team" + teamNumber + "SwitchPositions"; //$NON-NLS-1$ //$NON-NLS-2$
			processCode(code,false);
			activateFilter(filter);
			setFocusOnCode();
		}
	}
	private static class SwitchSidesListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSS",false); //$NON-NLS-1$
			activateFilter("SwitchSides"); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ShotTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XSST",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class PassTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XSPT",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class TimeOutTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XSTT",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class GameTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XSGT",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class RecallTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XSRT",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetTimerListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRT",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class StartEventListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSE",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class StartMatchListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSM",false); //$NON-NLS-1$
			activateFilter("StartMatch"); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class PauseMatchListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPPM",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class EndMatchListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPEM",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class StartGameListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSG",false); //$NON-NLS-1$
			activateFilter("StartGame"); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchTeamsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPST",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchPlayer1Listener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XXP1",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchPlayer2Listener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XXP2",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchScoresListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSSC",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchGameCountsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSGC",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchMatchCountsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSMC",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchTimeOutsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSTO",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class SwitchResetWarnsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPSR",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ClearAllListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPCA",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetNamesListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRN",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetScoresListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRS",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetGameCountsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRG",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetMatchCountsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRM",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetTimeOutsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRTO",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetResetWarnsListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRR",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class ResetAllListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPRA",false); //$NON-NLS-1$
			statsController.displayAllStats();
			setFocusOnCode();
		}
	}
	private static class TableClearAllListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			processCode("XPTCA",false); //$NON-NLS-1$
			setFocusOnCode();
		}
	}
	private static class TimerWindowCloseListener extends WindowAdapter {
        @Override
		public void windowClosed(WindowEvent we) {
			showTimer(false);
		}
	}
	private static class Team1ScoreFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Score"); //$NON-NLS-1$
		}
	}
	private static class Team2ScoreFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Score"); //$NON-NLS-1$
		}
	}
	private static class Team1WinGameFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1WinGame"); //$NON-NLS-1$
		}
	}
	private static class Team2WinGameFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2WinGame"); //$NON-NLS-1$
		}
	}
	private static class Team1WinMatchFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1WinMatch"); //$NON-NLS-1$
		}
	}
	private static class Team2WinMatchFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2WinMatch"); //$NON-NLS-1$
		}
	}
	private static class Team1TimeOutFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1TimeOut"); //$NON-NLS-1$
		}
	}
	private static class Team2TimeOutFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2TimeOut"); //$NON-NLS-1$
		}
	}
	private static class Team1ResetFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Reset"); //$NON-NLS-1$
		}
	}
	private static class Team2ResetFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Reset"); //$NON-NLS-1$
		}
	}
	private static class Team1WarnFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Warn"); //$NON-NLS-1$
		}
	}
	private static class Team2WarnFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Warn"); //$NON-NLS-1$
		}
	}
	private static class Team1SwitchPositionsFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1SwitchPositions"); //$NON-NLS-1$
		}
	}
	private static class Team2SwitchPositionsFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2SwitchPositions"); //$NON-NLS-1$
		}
	}
	private static class Team1SkunkFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Skunk"); //$NON-NLS-1$
		}
	}
	private static class Team2SkunkFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Skunk"); //$NON-NLS-1$
		}
	}
	private static class StartMatchFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("StartMatch"); //$NON-NLS-1$
		}
	}
	private static class StartGameFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("StartGame"); //$NON-NLS-1$
		}
	}
	private static class SwitchSidesFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("SwitchSides"); //$NON-NLS-1$
		}
	}
	private static class MeatballFilterListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			activateFilter("Meatball"); //$NON-NLS-1$
		}
	}
	private static class TeamPropertyListener implements PropertyChangeListener{
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			String name = e.getPropertyName();
			if (name.equals("Team1Score")) { //$NON-NLS-1$
//				System.out.println("Team1 new Score: " + e.getNewValue().toString());
//				System.out.println("Team1 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team2Score")) { //$NON-NLS-1$
//				System.out.println("Team2 new Score: " + e.getNewValue().toString());
//				System.out.println("Team2 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team3Score")) { //$NON-NLS-1$
//				System.out.println("Team3 new Score: " + e.getNewValue().toString());
//				System.out.println("Team3 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team1Game")) { //$NON-NLS-1$
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team2Game")) { //$NON-NLS-1$
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team3Game")) { //$NON-NLS-1$
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team1Match")) { //$NON-NLS-1$
//				System.out.println("Team1 new Match: " + e.getNewValue().toString());
//				System.out.println("Team1 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team2Match")) { //$NON-NLS-1$
//				System.out.println("Team2 new Match: " + e.getNewValue().toString());
//				System.out.println("Team2 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team3Match")) { //$NON-NLS-1$
//				System.out.println("Team3 new Match: " + e.getNewValue().toString());
//				System.out.println("Team3 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team1TimeOut")) { //$NON-NLS-1$
//				System.out.println("Team1 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team1 old TimeOut: " + e.getOldValue().toString());
			} else if (name.equals("Team2TimeOut")) { //$NON-NLS-1$
//				System.out.println("Team2 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team2 old TimeOut: " + e.getOldValue().toString());
			} else if (name.equals("Team3TimeOut")) { //$NON-NLS-1$
//				System.out.println("Team3 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team3 old TimeOut: " + e.getOldValue().toString());
			}
		}
	}
	public static String combinePlayerNames(int teamNumber) {
		String forwardName = teamController.getForwardName(teamNumber);
		String goalieName = teamController.getGoalieName(teamNumber);
		String name = (forwardName.isEmpty() && goalieName.isEmpty()) ? "?" : (  //$NON-NLS-1$
				((forwardName.isEmpty()) ? "" : forwardName) + //$NON-NLS-1$
				((!forwardName.isEmpty() && !goalieName.isEmpty()) ? "/" : "") + //$NON-NLS-1$ //$NON-NLS-2$
				((goalieName.isEmpty()) ? "" : goalieName)); //$NON-NLS-1$
		return name;
	}
	public static void setTeamGameCountVisible(String name, String value) {
		String teamNumber = ripTeamNumber(name);
		if (value != null) {
			boolean show;
			int gameCount = Integer.parseInt(value);
			for (Integer x = 1; x < 4; x++) {
				show = (x <= gameCount);
				showSource(teamGameShowSourcesMap.get(teamNumber+x.toString()), show);
			}
		}
	}
	public static void setTeamGameCountVisible(String name, int value) {
		String teamNumber = ripTeamNumber(name);
		if (value >= 0) {
			boolean show;
			for (Integer x = 1; x < 4; x++) {
				show = (x <= value);
				showSource(teamGameShowSourcesMap.get(teamNumber+x.toString()), show);
			}
		}
	}
//	private static void testProcessCodes() {
//		String codes[] = {"XPSE", "XPSM", "Y5D", "Y3P", "BGS", "B5D", "B3P", "YGS" };
//		autoProcessCodes(codes);
//	}
//	private static void autoProcessCodes(String[] codes) {
//		for (String code: codes) {
//			processCode(code, false);
//		}
//	}
	public static List<String> getCodeHistory() {
		List<String> codes = stats.getCodeHistoryAsList();
		return codes;
	}
	private static void setMainScene(String scene) {
		OBS.setMainScene(scene);
	}
	private static void activateOBSScene(String scene) {
		if(OBS.getController() == null ) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ErrorMustConnectBeforeActivatingScene")); //$NON-NLS-1$
		} else {
			if (scene != null && !scene.isEmpty()) {
				if (OBS.getConnected()) {
					OBS.getController().setCurrentProgramScene(scene, response -> { 
						if(Settings.getShowParsed()) {
							if (response != null && response.isSuccessful()) {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Scene") + scene + Messages.getString("Main.Activated")); //$NON-NLS-1$ //$NON-NLS-2$
							} else {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.UnableToActivateScene") + scene); //$NON-NLS-1$
							}
						} 
					});
				}else {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ErrorMustConnectBeforeActivatingScene")); //$NON-NLS-1$
				}
			}
		}
	}
	private static void createFileWatchWorker() {
		fileWatchWorker = new SwingWorker<Boolean, String>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				String partnerProgramDir = Settings.getPartnerProgramParameter("PartnerProgramPath"); //$NON-NLS-1$
				final String clearString = "XXX_ALREADY_READ_XXX";//= Settings.getPartnerProgramClearString(); //$NON-NLS-1$
				String partnerProgramPlayer1FileName = Settings.getPartnerProgramParameter("Player1FileName"); //$NON-NLS-1$
				String partnerProgramPlayer2FileName = Settings.getPartnerProgramParameter("Player2FileName"); //$NON-NLS-1$
				String partnerProgramPlayer3FileName = Settings.getPartnerProgramParameter("Player3FileName"); //$NON-NLS-1$
				String partnerProgramPlayer4FileName = Settings.getPartnerProgramParameter("Player4FileName"); //$NON-NLS-1$
				Path partnerPath = Paths.get(partnerProgramDir);

                // Check if directory exists
                if (!Files.exists(partnerPath)) {
                    // Use a flag to track user's decision
                    final boolean[] shouldCreate = new boolean[1];
                    final CountDownLatch latch = new CountDownLatch(1);

                    SwingUtilities.invokeLater(() -> {
                        String message = String.format(
                            "The Partner Program interface directory does not exist: %s\n\nWithout this directory, the Partner Program will not be able to update FoosOBSPlus.\n\nWould you like to create it?",
                            partnerProgramDir
                        );

                        int result = JOptionPane.showConfirmDialog(
                            null,
                            message,
                            "Create Directory",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );

                        shouldCreate[0] = (result == JOptionPane.YES_OPTION);
                        latch.countDown();
                    });

                    // Wait for user response
                    latch.await();

                    if (shouldCreate[0]) {
                        try {
                            Files.createDirectories(partnerPath);
                            logger.info("Created directory: " + partnerProgramDir);
                        } catch (IOException e) {
                            logger.error("Failed to create directory: " + e);
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Failed to create directory:\n" + e.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                );
                            });
                            return false;
                        }
                    } else {
                        logger.info("User declined to create directory");
                        return false;
                    }
                }

                watchService = FileSystems.getDefault().newWatchService(); 
				partnerPath.getFileSystem().newWatchService();
				partnerPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		        WatchKey watchKey;
		        while (!isCancelled())
		        {
		        	watchKey = watchService.poll();
		        	if (watchKey != null) {
		        		for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
		        			final Kind<?> kind = watchEvent.kind();
		        			if (kind==StandardWatchEventKinds.OVERFLOW) {
		    		        	logger.error("Overflow in createFileWatchWorker doInBackground."); //$NON-NLS-1$
		        				continue;
		        			}
		        			if (kind == StandardWatchEventKinds.ENTRY_CREATE || kind == StandardWatchEventKinds.ENTRY_MODIFY) {
		        				@SuppressWarnings("unchecked")
								final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
		        				final Path filePath = watchEventPath.context();
			        			String fileName = filePath.toString();
			        			if (fileName.equals(partnerProgramPlayer1FileName) || fileName.equals(partnerProgramPlayer2FileName) || fileName.equals(partnerProgramPlayer3FileName) || fileName.equals(partnerProgramPlayer4FileName)) {
				        			try {
				        				File file = new File(partnerProgramDir + "\\" + fileName); //$NON-NLS-1$
                                        try (Scanner fileReader = new Scanner(file)) {
                                            if (fileReader.hasNextLine()) {
                                                String data = fileReader.nextLine();
                                                if (!data.equals(clearString)) {
                                                    try (FileWriter fileWriter = new FileWriter(file)) {
                                                        fileWriter.write(clearString);
                                                    }
                                                    publish(fileName + "=" + data); //$NON-NLS-1$
                                                }
                                            }
                                        }
				        			} catch (FileNotFoundException e) {
				    		        	logger.error(e.toString());
				        			} catch (IOException e) {
				    		        	logger.error(e.toString());
				        			}
			        			}
		        			}
		        		}
			        	boolean valid = watchKey.reset();
			        	if (!valid) {
			        		logger.error("watchKey wasn\'t valid so made a break for it in Main.doInBackground()."); //$NON-NLS-1$
			        		break;
			        	}
	        		}
		        }
		        watchService.close();
		        return true;
			}
			@Override
			protected void done() {
				boolean status;
				if (isCancelled()) return;
			    try {
			     status = get();
			     logger.info("Worker completed with isConnected: " + status); //$NON-NLS-1$
			    } catch (InterruptedException | ExecutionException e) {
		        	logger.error(e.toString());
			    }
			}
			@Override
			protected void process(List<String> chunks) {
				if (isCancelled()) return;
				for (String value : chunks) {
					String partnerProgramPlayer1FileName = Settings.getPartnerProgramParameter("Player1FileName"); //$NON-NLS-1$
					String partnerProgramPlayer2FileName = Settings.getPartnerProgramParameter("Player2FileName"); //$NON-NLS-1$
					String partnerProgramPlayer3FileName = Settings.getPartnerProgramParameter("Player3FileName"); //$NON-NLS-1$
					String partnerProgramPlayer4FileName = Settings.getPartnerProgramParameter("Player4FileName"); //$NON-NLS-1$
					String newName;
					String[] pieces = value.split("="); //$NON-NLS-1$
					if (pieces.length == 1) {
						newName = ""; //$NON-NLS-1$
					}
					else {
						newName = pieces[1];
					}
					if (pieces[0].equals(partnerProgramPlayer1FileName)) {
						teamController.setTeam1ForwardName(newName);
					} else {
						if (pieces[0].equals(partnerProgramPlayer2FileName)) {
							teamController.setTeam1GoalieName(newName);
						} else {
							if (pieces[0].equals(partnerProgramPlayer3FileName)) {
								teamController.setTeam2ForwardName(newName);
							} else {
								if (pieces[0].equals(partnerProgramPlayer4FileName)) {
									teamController.setTeam2GoalieName(newName);
								}
							}
						}
					}
					if (match.getMatchWon()) {
						match.resetMatch();
						teamController.resetScores();
						teamController.resetGameCounts();
						teamController.resetMatchCounts();
						matchController.startMatch(createMatchId());
						streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + Messages.getString("Main.AutoStartMatchFromNameChange") + teamController.getForwardName(1) + "/" + teamController.getGoalieName(1) + " vs " + teamController.getForwardName(2) + "/" + teamController.getGoalieName(2) + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
					}
				}
			}
		};
	}

	public static void updateGameResults(StringBuilder gameResults) {
		matchController.updateGameResults(gameResults);
	}
}
