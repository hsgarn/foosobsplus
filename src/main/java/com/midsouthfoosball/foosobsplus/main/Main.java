/**
Copyright © 2020-2025 Hugh Garner
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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
import java.util.ArrayList;
import java.util.Arrays;
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
import com.midsouthfoosball.foosobsplus.view.AutoScoreSettingsPanel;
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

import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.WebSocketCloseCode;
import io.obswebsocket.community.client.listener.lifecycle.ReasonThrowable;
import io.obswebsocket.community.client.message.event.inputs.InputActiveStateChangedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemCreatedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemEnableStateChangedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemListReindexedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemRemovedEvent;
import io.obswebsocket.community.client.message.event.scenes.CurrentProgramSceneChangedEvent;
import io.obswebsocket.community.client.message.response.sceneitems.GetSceneItemIdResponse;
import io.obswebsocket.community.client.message.response.scenes.GetSceneListResponse;
import io.obswebsocket.community.client.message.response.ui.GetMonitorListResponse;
import io.obswebsocket.community.client.model.Monitor;
import io.obswebsocket.community.client.model.Scene;
/**
 * Main FoosOBS Object
 * @author Hugh Garner
 *
 */
public final class Main implements MatchObserver {
	private static final Logger 					logger 					= LoggerFactory.getLogger(Main.class);
	private static final String 					ON 						= "1"; //$NON-NLS-1$
	private static final String						OFF						= "0"; //$NON-NLS-1$
	////// Settings and OBSInterface setup \\\\\\
	private static final OBSInterface 				obsInterface 			= new OBSInterface();
	private static String							matchId					= ""; //$NON-NLS-1$
	private static final DateTimeFormatter 			dtf 					= DateTimeFormatter.ofPattern(Messages.getString("Main.DateTimePattern")); //$NON-NLS-1$
	private static boolean 							autoScoreConnected		= false;
	private static Socket 							autoScoreSocket;
	private static PrintWriter 						autoScoreSocketWriter;
	private static final StreamIndexer 					streamIndexer      		= new StreamIndexer(Settings.getControlParameter("datapath")); //$NON-NLS-1$
	private static Boolean 							blockAutoScoreReconnect	= false;
    private static final Map<String, String>				teamGameShowSourcesMap	= new HashMap<>();
	////// Watch Service for File changes \\\\\\
	private static WatchService 					watchService;
	////// CommandStack and UndoRedo setup \\\\\\
	private static int 								undoRedoPointer			= -1;
	private static final Stack<Command> 					commandStack 			= new Stack<>();
	private static final Stack<Memento> 					mementoStackTeam1 		= new Stack<>();
	private static final Stack<Memento> 					mementoStackTeam2		= new Stack<>();
	private static final Stack<Memento> 					mementoStackTeam3       = new Stack<>();
	private static final Stack<Memento> 					mementoStackStats 		= new Stack<>();
	private static final Stack<Memento> 					mementoStackMatch		= new Stack<>();
	private static final Stack<Memento> 					mementoStackGameClock	= new Stack<>();
	private static final Stack<String>					codeStack 				= new Stack<>();
	private static CommandSwitch 					mySwitch;
	////// Generate the Data Models (Mvc) \\\\\\
	private static final Tournament						tournament				= new Tournament(obsInterface);
	private static final Team 							team1 					= new Team(obsInterface, 1, Settings.getControlParameter("Side1Color")); //$NON-NLS-1$
	private static final Team 							team2 					= new Team(obsInterface, 2, Settings.getControlParameter("Side2Color")); //$NON-NLS-1$
	private static final Team         					team3              		= new Team(obsInterface, 3, Messages.getString("Main.None")); //$NON-NLS-1$
	private static final Match 							match					= new Match(obsInterface, team1, team2, team3);
	private static final Stats 							stats 					= new Stats(team1, team2);
	////// Create a TimeClock to be the Timer \\\\\\
	private static final TimeClock 						timeClock 				= new TimeClock(obsInterface);
	private static final GameClock       					gameClock           	= new GameClock(obsInterface);
	private static final LastScoredClock 					lastScored1Clock   		= new LastScoredClock();
	private static final LastScoredClock					lastScored2Clock    	= new LastScoredClock();
	private static final LastScoredClock 					lastScored3Clock		= new LastScoredClock();
	////// Create the View Panels to Display (mVc) \\\\\\
	private static final TournamentPanel					tournamentPanel 		= new TournamentPanel();
	private static final TimerPanel 						timerPanel 				= new TimerPanel();
	private static final OBSPanel							obsPanel				= new OBSPanel();
	private static final AutoScoreMainPanel				autoScoreMainPanel  	= new AutoScoreMainPanel();
	private static final MatchPanel						matchPanel				= new MatchPanel();
	private static final TeamPanel 						teamPanel1 				= new TeamPanel(1);
	private static final TeamPanel 						teamPanel2 				= new TeamPanel(2);
	private static final TeamPanel						teamPanel3				= new TeamPanel(3);
	private static final StatsEntryPanel 					statsEntryPanel 		= new StatsEntryPanel();
	private static final SwitchPanel 						switchPanel 			= new SwitchPanel();
	private static final ResetPanel 						resetPanel 				= new ResetPanel();
	private static final StatsDisplayPanel 				statsDisplayPanel 		= new StatsDisplayPanel();
	////// Set up Timer and Settings Windows \\\\\\
	private static final ParametersFrame 					parametersFrame 		= new ParametersFrame();
	private static final ParametersPanel					parametersPanel			= parametersFrame.getSettingsPanel();
	private static final HotKeysFrame 					hotKeysFrame 			= new HotKeysFrame();
	private static final HotKeysPanel 					hotKeysPanel			= hotKeysFrame.getHotKeysPanel();
	private static final SourcesFrame						sourcesFrame			= new SourcesFrame();
	private static final SourcesPanel						sourcesPanel			= sourcesFrame.getSourcesPanel();
	private static final StatSourcesFrame					statSourcesFrame		= new StatSourcesFrame();
	private static final StatSourcesPanel					statSourcesPanel		= statSourcesFrame.getStatSourcesPanel();
	private static final FiltersFrame        				filtersFrame        	= new FiltersFrame();
	private static final FiltersPanel        				filtersPanel        	= filtersFrame.getFiltersPanel();
	private static final PartnerProgramFrame 				partnerProgramFrame 	= new PartnerProgramFrame();
	private static final OBSConnectFrame					obsConnectFrame			= new OBSConnectFrame();
	private static final OBSConnectPanel					obsConnectPanel			= obsConnectFrame.getOBSConnectPanel();
	private static final AutoScoreSettingsFrame			autoScoreSettingsFrame	= new AutoScoreSettingsFrame();
	private static final AutoScoreSettingsPanel			autoScoreSettingsPanel	= autoScoreSettingsFrame.getAutoScoreSettingsPanel();
	private static final AutoScoreConfigFrame				autoScoreConfigFrame	= new AutoScoreConfigFrame();
	private static final AutoScoreConfigPanel				autoScoreConfigPanel	= autoScoreConfigFrame.getAutoScoreConfigPanel();
	////// Set up The Main Window JFrame \\\\\\
	private static MainFrame 						mainFrame;
	////// Set up independent Windows \\\\\\
	private static GameTableWindowPanel				gameTableWindowPanel;
	private static GameTableWindowFrame				gameTableWindowFrame;
	private static GameResultsWindowFrame			gameResultsWindowFrame;
	private static TimerWindowFrame 				timerWindowFrame;
	private static LastScoredWindowFrame 			lastScored1WindowFrame;
	private static LastScoredWindowFrame 			lastScored2WindowFrame;
	private static LastScoredWindowFrame			lastScored3WindowFrame;
	////// Build and Start the Controllers (mvC) \\\\\\
	private static MainController 					mainController;
	private static TimerController 					timerController;
	private static TeamController 					teamController;
	private static TournamentController 			tournamentController;
	private static MatchController 					matchController;
	private static StatsController 					statsController;
	private static SwingWorker<Boolean, Integer> 	autoScoreWorker;
	private static SwingWorker<Boolean, String> 	fileWatchWorker;
    {
        match.addObserver(this);
    }
	public Main() throws IOException {
		buildTeamGameShowSourcesMap();
		loadWindowsAndControllers();
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
		loadListeners();
		loadCommands();
		createAutoScoreWorker();
		if (Settings.getAutoScoreParameter("AutoScoreSettingsAutoConnect").equals(ON)) { //$NON-NLS-1$
			connectAutoScore();
		}
		createFileWatchWorker();
		fileWatchWorker.execute();
	}
	private static void buildTeamGameShowSourcesMap() {
		teamGameShowSourcesMap.clear();
		for (int x = 1; x <= 3; x++) {
			for (int y = 1; y <= 3; y++) {
				teamGameShowSourcesMap.put(Integer.toString(x)+Integer.toString(y), Settings.getTeamGameShowSource(x, y));
			}
		}
	}
	private static void buildOBSController() {
		if(OBS.getController() != null) {
			OBS.getController().stop();
			OBS.setController(null);
		}
		OBS.setController(OBSRemoteController.builder()
			.autoConnect(false)
			.host(OBS.getHost())
			.port(Integer.parseInt(OBS.getPort()))
			.password(OBS.getPassword())
			.connectionTimeout(3)
			.lifecycle()
			.onReady(Main::onReady)
			.onDisconnect(Main::onDisconnect)
			.onClose(webSocketCloseCode -> Main.onClose(webSocketCloseCode))
			.onControllerError(reason -> Main.onControllerError(reason))
			.onCommunicatorError(reason -> Main.onCommunicationError(reason))
			.and()
			.registerEventListener(InputActiveStateChangedEvent.class, Main::checkActiveStateChange)
			.registerEventListener(SceneItemEnableStateChangedEvent.class, Main::checkItemEnableStateChange)
			.registerEventListener(CurrentProgramSceneChangedEvent.class, Main::checkCurrentProgramSceneChange)
			.registerEventListener(SceneItemCreatedEvent.class, Main::checkSceneItemCreated)
			.registerEventListener(SceneItemRemovedEvent.class, Main::checkSceneItemRemoved)
			.registerEventListener(SceneItemListReindexedEvent.class, Main::checkSceneItemListReindexed)
			.build()
		);
	}
	private static void checkSceneItemCreated(SceneItemCreatedEvent sceneItemCreatedEvent) {
		loadSceneItemMap();
	}
	private static void checkSceneItemRemoved(SceneItemRemovedEvent sceneItemRemovedEvent) {
		loadSceneItemMap();
	}
	private static void checkSceneItemListReindexed(SceneItemListReindexedEvent sceneItemListReindexedEvent) {
		loadSceneItemMap();
	}
	private static void loadSceneItemMap() {
	}
	private static void checkCurrentProgramSceneChange(CurrentProgramSceneChangedEvent currentProgramSceneChanged) {
    	boolean showParsed = Settings.getShowParsed();
		String sceneName = currentProgramSceneChanged.getSceneName();
		if (sceneName != null && !sceneName.isEmpty()) {
			OBS.setCurrentScene(sceneName);
			if(showParsed) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSceneChangedTo") + sceneName + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} else {
			if(showParsed) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSCheckCurrentProgramSceneChangeFailed")); //$NON-NLS-1$
			}
	   }
	}
	private static void checkItemEnableStateChange(SceneItemEnableStateChangedEvent sceneItemEnableStateChanged) {
    	boolean showParsed = Settings.getShowParsed();
		String sceneName = sceneItemEnableStateChanged.getSceneName();
		Number itemId = sceneItemEnableStateChanged.getSceneItemId();
		boolean show = sceneItemEnableStateChanged.getSceneItemEnabled();
		checkItemEnableStageChangeHelper(sceneName, show, Settings.getSourceParameter("ShowScores"), itemId, showParsed); //$NON-NLS-1$
		checkItemEnableStageChangeHelper(sceneName, show, Settings.getSourceParameter("ShowCutthroat"), itemId, showParsed); //$NON-NLS-1$
	}
	private static void checkItemEnableStageChangeHelper(String sceneName, boolean show, String sourceToCheck, Number itemId, boolean showParsed) {
		OBS.getController().getSceneItemId(sceneName, sourceToCheck, null, getSceneItemIdResponse -> {
        	if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
	           	if (getSceneItemIdResponse.getSceneItemId().toString().equals(itemId.toString())) {
	           		if (sourceToCheck.equals(Settings.getSourceParameter("ShowScores"))) { //$NON-NLS-1$
	           			obsPanel.setShowScores(show);
	           		} else if (sourceToCheck.equals(Settings.getSourceParameter("ShowCutthroat"))) { //$NON-NLS-1$
	           			obsPanel.setShowCutthroat(show);
	           		}
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS " + sourceToCheck + Messages.getString("Main.EnableStateChangeReceived") + show + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
	           	}
	        } else {
				if(showParsed) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSEnableStateChangeFailed")); //$NON-NLS-1$
				}
	        }
	    });
	}
	private static void checkActiveStateChange(InputActiveStateChangedEvent inputActiveStateChangedEvent) {
		String name = inputActiveStateChangedEvent.getInputName();
		if (!Settings.getSourceParameter("ShowTimer").isEmpty() && name.equals(Settings.getSourceParameter("ShowTimer"))) { //$NON-NLS-1$ //$NON-NLS-2$
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowTimer(show);
			mainController.showTimerWindow(show);
		} else if (!Settings.getSourceParameter("ShowScores").isEmpty() && name.equals(Settings.getSourceParameter("ShowScores"))) { //$NON-NLS-1$ //$NON-NLS-2$
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowScores(show);
		} else if (!Settings.getSourceParameter("ShowCutthroat").isEmpty() && name.equals(Settings.getSourceParameter("ShowCutthroat"))) { //$NON-NLS-1$ //$NON-NLS-2$
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowCutthroat(show);
		}
		setFocusOnCode();
	}
	public static void connectToOBS() {
		logger.info("Trying to connect to OBS..."); //$NON-NLS-1$
		buildOBSController();
		OBS.getController().connect();
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
	private static void onReady() {
		updateOBSConnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSReady")); //$NON-NLS-1$
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSControllerConnected")); //$NON-NLS-1$
		OBS.getController().getVersion(versionInfo -> {
			if(versionInfo != null && versionInfo.isSuccessful()) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSVersion") + versionInfo.getObsVersion()); //$NON-NLS-1$
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.WebsocketVersion") + versionInfo.getObsWebSocketVersion()); //$NON-NLS-1$
			}
		});
		String sceneName = Settings.getOBSParameter("OBSMainScene"); //$NON-NLS-1$
		OBS.getController().setCurrentProgramScene(sceneName, response -> { 
			if(response != null && response.isSuccessful()) {
				OBS.setCurrentScene(sceneName);
				if(Settings.getShowParsed()) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.SceneSetTo") + sceneName); //$NON-NLS-1$
				}
			} else {
				String msg = dtf.format(LocalDateTime.now()) + Messages.getString("Main.UnableToSetSceneTo") + sceneName; //$NON-NLS-1$
				logger.warn(msg);
				if(Settings.getShowParsed()) {
					obsConnectPanel.addMessage(msg);
				}
			}
		});
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowTimer"), response ->  //$NON-NLS-1$
			{boolean show = response.getVideoActive();
				obsPanel.setShowTimer(show);
				mainController.showTimerWindow(show);		
			});
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowScores"), response -> obsPanel.setShowScores(response.getVideoActive())); //$NON-NLS-1$
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowCutthroat"), response -> obsPanel.setShowCutthroat(response.getVideoActive())); //$NON-NLS-1$
		if(Settings.getOBSParameter("OBSCloseOnConnect").equals(ON)) {  //$NON-NLS-1$
			obsConnectFrame.setVisible(false);
		}
		fetchMonitorList();
		fetchSceneList();
	}
	private static void projectSource() {
		if (OBS.getConnected()) {
			Number monitorIndex = obsConnectPanel.getSelectedMonitor();
			if (monitorIndex.intValue() != -1) {
				String sceneName = obsConnectPanel.getSelectedSceneName();
				if (sceneName.isEmpty()) sceneName = OBS.getMainScene();
				OBS.getController().openSourceProjector(sceneName, monitorIndex, null, response -> 
				{
					if (!response.isSuccessful()) {
						String msg = Messages.getString("Errors.Main.ProjectError"); //$NON-NLS-1$
						String ttl = Messages.getString("Errors.Main.Project.Title"); //$NON-NLS-1$
						logger.warn(msg);
						JOptionPane.showMessageDialog(null, msg, ttl, 1);
					}
				});
			}
		}
	}
	private static void fetchMonitorList() {
		if (OBS.getConnected()) {
			OBS.getController().getMonitorList((GetMonitorListResponse response) -> {
				if(Settings.getShowParsed()) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.FetchingMonitors")); //$NON-NLS-1$
				}
				List<Monitor> monitors;
				if(response != null && response.isSuccessful()) {
					monitors = response.getMonitors();
					HashMap<Integer, String> monitorMap = new HashMap<>();
					for (Monitor monitor : monitors) {
						monitorMap.put(monitor.getMonitorIndex(),  monitor.getMonitorName());
					}
					obsConnectPanel.updateMonitorList(monitorMap);
					if(Settings.getShowParsed()) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.MonitorsFetched")); //$NON-NLS-1$
					}
				} else {
					String msg = Messages.getString("Errors.Main.FetchMonitorError"); //$NON-NLS-1$
					String ttl = Messages.getString("Errors.Main.FetchMonitor.Title"); //$NON-NLS-1$
					logger.warn(msg);
					JOptionPane.showMessageDialog(null, msg, ttl, 1);
				}
			});
		}
	}
	private static void fetchSceneList() {
		if (OBS.getConnected()) {
			OBS.getController().getSceneList((GetSceneListResponse response) -> {
				List<Scene> scenes;
				if(response != null && response.isSuccessful()) {
					scenes = response.getScenes();
					HashMap<Integer, String> sceneMap = new HashMap<>();
					for (Scene scene : scenes) {
						sceneMap.put(scene.getSceneIndex(), scene.getSceneName());
					}
					obsConnectPanel.updateSceneList(sceneMap);
				} else {
					String msg = Messages.getString("Errors.Main.FetchSourceError"); //$NON-NLS-1$
					String ttl = Messages.getString("Errors.Main.FetchSource.Title"); //$NON-NLS-1$
					logger.warn(msg);
					JOptionPane.showMessageDialog(null, msg, ttl, 1);
				}
			});
		}
	}
	private static void onClose(WebSocketCloseCode webSocketCloseCode) {
		updateOBSDisconnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSControllerClosed") + webSocketCloseCode.getCode()); //$NON-NLS-1$
	}
	private static void onDisconnect() {
		updateOBSDisconnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSControllerDisconnected")); //$NON-NLS-1$
	}
	private static void onControllerError(ReasonThrowable reason) {
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ControllerError") + reason.getReason()); //$NON-NLS-1$
	}
	private static void onCommunicationError(ReasonThrowable reason) {
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.CommunicationError") + reason.getReason()); //$NON-NLS-1$
	}
	private static void createAutoScoreWorker() {
		autoScoreWorker = new SwingWorker<Boolean, Integer>() {
			BufferedReader dataIn;
			@Override
			protected Boolean doInBackground() throws Exception {
		    	boolean isConnected = false;
		    	String address = Settings.getAutoScoreParameter("AutoScoreSettingsServerAddress"); //$NON-NLS-1$
		    	int port = Settings.getAutoScoreParameter("AutoScoreSettingsServerPort",Integer::parseInt); //$NON-NLS-1$
		    	try {
		            autoScoreSocket = new Socket(address, port);
		            autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ConnectedTo") + address + ": " + port); //$NON-NLS-1$ //$NON-NLS-2$
		            logger.info("Auto Score connected to " + address + ": " + port); //$NON-NLS-1$ //$NON-NLS-2$
		        	dataIn = new BufferedReader(new InputStreamReader(autoScoreSocket.getInputStream()));
					try {
						autoScoreSocketWriter = new PrintWriter(autoScoreSocket.getOutputStream());
						if (autoScoreSocketWriter.checkError()) {
							logger.error("createAutoScoreWork doInBackground new PrintWriter error:"); //$NON-NLS-1$
						}
					} catch(IOException ex) {
						logger.error("createAutoScoreWork doInBackground PrintWriter exception:"); //$NON-NLS-1$
						logger.error(ex.toString());
					}
		        	isConnected = true;
		    		mainFrame.setAutoScoreIconConnected(true);
		    		autoScoreConnected = true;
		        }
		        catch(UnknownHostException uh) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.AutoScoreUnknownHostException")); //$NON-NLS-1$
		        	logger.error("Auto Score new Socket UnknownHostException"); //$NON-NLS-1$
		        	logger.error(uh.toString());
		        }
		        catch(IOException io) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.AutoScoreIOException")); //$NON-NLS-1$
		        	logger.error("Auto Score new Socket IOException"); //$NON-NLS-1$
		        	logger.error(io.toString());
		        }
		    	String raw = ""; //$NON-NLS-1$
		        String str[];
		        String cmd[];
		        while (isConnected) {
            		try {
           				raw = dataIn.readLine();
		            } catch(IOException io) {
		            	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString()); //$NON-NLS-1$
			        	logger.error(io.toString());
			        	isConnected = false;
		            }
            		if (raw != null && !raw.isEmpty()) {
                		logger.info("Received raw data: [" + raw + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		        		cmd = raw.split(":"); //$NON-NLS-1$
		        		logger.info("Parse command: " + cmd[0]); //$NON-NLS-1$
	                	if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals(ON)) { //$NON-NLS-1$
	                		autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Received") + raw); //$NON-NLS-1$
	                	}
	                	if (cmd[0].equals("Team")) { //$NON-NLS-1$
	    	                str = cmd[1].split("[,]",0); //$NON-NLS-1$
			                if (str[0].equals("1")) { //$NON-NLS-1$
			                	publish(1);
			                } else {
				                if (str[0].equals("2")) { //$NON-NLS-1$
				                	publish(2);
				                }
			                }
	                	} else {
	                		if (cmd[0].equals("TO")) { //$NON-NLS-1$
	                			str = cmd[1].split("[,]",0); //$NON-NLS-1$
				                if (str[0].equals("1")) { //$NON-NLS-1$
				                	publish(3);
				                } else {
					                if (str[0].equals("2")) { //$NON-NLS-1$
					                	publish(4);
					                }
				                }
	                		}
	                	}
	                	if (cmd[0].equals("Read")) { //$NON-NLS-1$
	                		autoScoreConfigPanel.clearConfigTextArea();
	                	}
	                	if (cmd[0].equals("Line")) { //$NON-NLS-1$
	                		String line = cmd[1] + "\n"; //$NON-NLS-1$
	                		autoScoreConfigPanel.appendConfigTextArea(line);
	                	}
            		}
	                if (isCancelled()) {
	                	break;
	                }
		        }
		        autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.ConnectionTerminated")); //$NON-NLS-1$
		        logger.info("Auto Score Connection Terminated!!"); //$NON-NLS-1$
		        try {
		            dataIn.close();
		            autoScoreSocket.close();
		            isConnected = false;
		        } catch(IOException io) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString()); //$NON-NLS-1$
		        	logger.error(io.toString());
		        }
		    	return isConnected;
			}
			@Override
			protected void done() {
				boolean status;
				if (isCancelled()) return;
			    try {
					status = get();
					autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.WorkerCompletedWithIsConnected") + status); //$NON-NLS-1$
			    } catch (InterruptedException | ExecutionException e) {
			    	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString()); //$NON-NLS-1$
		        	logger.error(e.toString());
			    }
                //$NON-NLS-1$
	    		mainFrame.setAutoScoreIconConnected(false);
	    		autoScoreConnected = false;			    	
			}
			@Override
			protected void process(List<Integer> chunks) {
				if (isCancelled()) return;
				int mostRecentValue = chunks.get(chunks.size()-1);
				boolean ignoreSensors = autoScoreMainPanel.isIgnored();
            	if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals(ON)) { //$NON-NLS-1$
            		if (ignoreSensors) {
            			if (mostRecentValue == 1 || mostRecentValue == 2) {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team")+mostRecentValue+ Messages.getString("Main.ScoredButIgnored")); //$NON-NLS-1$ //$NON-NLS-2$
            			} else {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team")+(mostRecentValue-2)+ Messages.getString("Main.CalledTimeOut")); //$NON-NLS-1$ //$NON-NLS-2$
            			}
            		} else {
            			if (mostRecentValue == 1 || mostRecentValue == 2) {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team")+mostRecentValue+ Messages.getString("Main.Scored")); //$NON-NLS-1$ //$NON-NLS-2$
            			} else {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.Team")+(mostRecentValue-2)+ Messages.getString("Main.CalledTimeOut")); //$NON-NLS-1$ //$NON-NLS-2$
            			}
            		}
            	}
				if (!ignoreSensors && (mostRecentValue == 1)) {
					processCode("XIST1", false); //$NON-NLS-1$
				} else {
					if (!ignoreSensors && (mostRecentValue == 2)) {
						processCode("XIST2", false); //$NON-NLS-1$
					}
				}
				if (mostRecentValue == 3) {
					processCode("XUTT1", false); //$NON-NLS-1$
				} else {
					if (mostRecentValue == 4) {
						processCode("XUTT2", false); //$NON-NLS-1$
					}
				}
			}
		};
		autoScoreWorker.addPropertyChangeListener(new AutoScoreWorkerStateChangeListener());
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
				partnerProgramFrame, obsConnectFrame, autoScoreSettingsFrame, autoScoreConfigFrame);
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
	public static void loadListeners() {
		hotKeysPanel.addApplyListener(new HotKeysApplyListener());
		hotKeysPanel.addSaveListener(new HotKeysSaveListener());
		sourcesPanel.addApplyListener(new SourcesApplyListener());
		sourcesPanel.addSaveListener(new SourcesSaveListener());
		statSourcesPanel.addApplyListener(new StatSourcesApplyListener());
		statSourcesPanel.addSaveListener(new StatSourcesSaveListener());
		autoScoreSettingsPanel.addApplyListener(new AutoScoreSettingsApplyListener());
		autoScoreSettingsPanel.addSaveListener(new AutoScoreSettingsSaveListener());
		autoScoreSettingsPanel.addConnectListener(new AutoScoreSettingsConnectListener());
		autoScoreSettingsPanel.addDisconnectListener(new AutoScoreSettingsDisconnectListener());
		autoScoreConfigPanel.addReadConfigListener(new AutoScoreConfigReadListener());
		autoScoreConfigPanel.addWriteConfigListener(new AutoScoreConfigWriteListener());
		autoScoreConfigPanel.addValidateConfigListener(new AutoScoreConfigValidateListener());
		autoScoreConfigPanel.addResetConfigListener(new AutoScoreConfigResetListener());
		autoScoreConfigPanel.addClearConfigListener(new AutoScoreConfigClearListener());
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
		autoScoreMainPanel.addConnectListener(new AutoScoreMainPanelConnectListener());
		autoScoreMainPanel.addDisconnectListener(new AutoScoreMainPanelDisconnectListener());
		autoScoreMainPanel.addSettingsListener(new AutoScoreMainPanelSettingsListener());
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
		Command pse = new PSECommand(statsController);
		Command psm = new PSMCommand(statsController);
		Command pem = new PEMCommand(statsController);
		Command ppm = new PPMCommand(statsController, matchController);
		Command psg = new PSGCommand(statsController);
		Command sst = new SSTCommand(statsController, teamController);
		Command spt = new SPTCommand(statsController, teamController);
		Command sgt = new SGTCommand(statsController, teamController);
		Command stt = new STTCommand(statsController, teamController);
		Command srt = new SRTCommand(statsController, teamController);
		Command prt = new PRTCommand(statsController, teamController);
		Command ist1 = new ISTCommand(statsController, matchController, 1);
		Command ist2 = new ISTCommand(statsController, matchController, 2);
		Command ist3 = new ISTCommand(statsController, matchController, 3);
		Command dst1 = new DSTCommand(statsController, matchController, 1);
		Command dst2 = new DSTCommand(statsController, matchController, 2);
		Command dst3 = new DSTCommand(statsController, matchController, 3);
		Command igt1 = new IGTCommand(statsController, teamController, 1);
		Command igt2 = new IGTCommand(statsController, teamController, 2);
		Command igt3 = new IGTCommand(statsController, teamController, 3);
		Command imt1 = new IMTCommand(statsController, teamController, 1);
		Command imt2 = new IMTCommand(statsController, teamController, 2);
		Command imt3 = new IMTCommand(statsController, teamController, 3);
		Command dgt1 = new DGTCommand(statsController, teamController, 1);
		Command dgt2 = new DGTCommand(statsController, teamController, 2);
		Command dgt3 = new DGTCommand(statsController, teamController, 3);
		Command dmt1 = new DMTCommand(statsController, teamController, 1);
		Command dmt2 = new DMTCommand(statsController, teamController, 2);
		Command dmt3 = new DMTCommand(statsController, teamController, 3);
		Command utt1 = new UTTCommand(statsController, teamController, 1);
		Command utt2 = new UTTCommand(statsController, teamController, 2);
		Command utt3 = new UTTCommand(statsController, teamController, 3);
		Command rtt1 = new RTTCommand(statsController, teamController, 1);
		Command rtt2 = new RTTCommand(statsController, teamController, 2);
		Command rtt3 = new RTTCommand(statsController, teamController, 3);
		Command prt1 = new PRCommand(statsController, teamController, 1);
		Command prt2 = new PRCommand(statsController, teamController, 2);
		Command prt3 = new PRCommand(statsController, teamController, 3);
		Command pwt1 = new PWCommand(statsController, teamController, 1);
		Command pwt2 = new PWCommand(statsController, teamController, 2);
		Command pwt3 = new PWCommand(statsController, teamController, 3);
		Command pkt1 = new PKCommand(statsController, teamController, 1);
		Command pkt2 = new PKCommand(statsController, teamController, 2);
		Command pkt3 = new PKCommand(statsController, teamController, 3);
		Command pss = new PSSCommand(statsController);
		Command xpt1 = new XPTCommand(statsController, teamController, 1);
		Command xpt2 = new XPTCommand(statsController, teamController, 2);
		Command xpt3 = new XPTCommand(statsController, teamController, 3);
		Command pst = new PSTCommand(statsController, teamController);
		Command xp1 = new XPCommand(statsController, teamController, 1);
		Command xp2 = new XPCommand(statsController, teamController, 2);
		Command pssc = new PSSCCommand(statsController, teamController);
		Command psgc = new PSGCCommand(statsController, teamController);
		Command psmc = new PSMCCommand(statsController, teamController);
		Command psto = new PSTOCommand(statsController, teamController);
		Command psr = new PSRCommand(statsController, teamController);
		Command pca = new PCACommand(statsController, teamController, matchController);
		Command prn= new PRNCommand(statsController, teamController);
		Command prs = new PRSCommand(statsController, teamController);
		Command prg = new PRGCommand(statsController, teamController);
		Command prm = new PRMCommand(statsController, teamController);
		Command prto = new PRTOCommand(statsController, teamController);
		Command prr = new PRRCommand(statsController, teamController);
		Command pra = new PRACommand(statsController, teamController, matchController);
		Command ptca = new PTCACommand(statsController, tournamentController);
		Command codeCommand = new CodeCommand(statsController);
		mySwitch = new CommandSwitch();
		mySwitch.register("PSE", pse); //$NON-NLS-1$
		mySwitch.register("PSM", psm); //$NON-NLS-1$
		mySwitch.register("PEM", pem); //$NON-NLS-1$
		mySwitch.register("PPM", ppm); //$NON-NLS-1$
		mySwitch.register("PSG", psg); //$NON-NLS-1$
		mySwitch.register("SST", sst); //$NON-NLS-1$
		mySwitch.register("SPT", spt); //$NON-NLS-1$
		mySwitch.register("SGT", sgt); //$NON-NLS-1$
		mySwitch.register("STT", stt); //$NON-NLS-1$
		mySwitch.register("SRT", srt); //$NON-NLS-1$
		mySwitch.register("PRT", prt); //$NON-NLS-1$
		mySwitch.register("IST1", ist1); //$NON-NLS-1$
		mySwitch.register("IST2", ist2); //$NON-NLS-1$
		mySwitch.register("IST3", ist3); //$NON-NLS-1$
		mySwitch.register("DST1", dst1); //$NON-NLS-1$
		mySwitch.register("DST2", dst2); //$NON-NLS-1$
		mySwitch.register("DST3", dst3); //$NON-NLS-1$
		mySwitch.register("IGT1", igt1); //$NON-NLS-1$
		mySwitch.register("IGT2", igt2); //$NON-NLS-1$
		mySwitch.register("IGT3", igt3); //$NON-NLS-1$
		mySwitch.register("IMT1", imt1); //$NON-NLS-1$
		mySwitch.register("IMT2", imt2); //$NON-NLS-1$
		mySwitch.register("IMT3", imt3); //$NON-NLS-1$
		mySwitch.register("DGT1", dgt1); //$NON-NLS-1$
		mySwitch.register("DGT2", dgt2); //$NON-NLS-1$
		mySwitch.register("DGT3", dgt3); //$NON-NLS-1$
		mySwitch.register("DMT1", dmt1); //$NON-NLS-1$
		mySwitch.register("DMT2", dmt2); //$NON-NLS-1$
		mySwitch.register("DMT3", dmt3); //$NON-NLS-1$
		mySwitch.register("UTT1", utt1); //$NON-NLS-1$
		mySwitch.register("UTT2", utt2); //$NON-NLS-1$
		mySwitch.register("UTT3", utt3); //$NON-NLS-1$
		mySwitch.register("RTT1", rtt1); //$NON-NLS-1$
		mySwitch.register("RTT2", rtt2); //$NON-NLS-1$
		mySwitch.register("RTT3", rtt3); //$NON-NLS-1$
		mySwitch.register("PRT1", prt1); //$NON-NLS-1$
		mySwitch.register("PRT2", prt2); //$NON-NLS-1$
		mySwitch.register("PRT3", prt3); //$NON-NLS-1$
		mySwitch.register("PWT1", pwt1); //$NON-NLS-1$
		mySwitch.register("PWT2", pwt2); //$NON-NLS-1$
		mySwitch.register("PWT3", pwt3); //$NON-NLS-1$
		mySwitch.register("PKT1", pkt1); //$NON-NLS-1$
		mySwitch.register("PKT2", pkt2); //$NON-NLS-1$
		mySwitch.register("PKT3", pkt3); //$NON-NLS-1$
		mySwitch.register("PSS", pss); //$NON-NLS-1$
		mySwitch.register("XPT1", xpt1); //$NON-NLS-1$
		mySwitch.register("XPT2", xpt2); //$NON-NLS-1$
		mySwitch.register("XPT3", xpt3); //$NON-NLS-1$
		mySwitch.register("PST", pst); //$NON-NLS-1$
		mySwitch.register("XP1", xp1); //$NON-NLS-1$
		mySwitch.register("XP2", xp2); //$NON-NLS-1$
		mySwitch.register("PSSC", pssc); //$NON-NLS-1$
		mySwitch.register("PSGC", psgc); //$NON-NLS-1$
		mySwitch.register("PSMC", psmc); //$NON-NLS-1$
		mySwitch.register("PSTO", psto); //$NON-NLS-1$
		mySwitch.register("PSR", psr); //$NON-NLS-1$
		mySwitch.register("PCA", pca); //$NON-NLS-1$
		mySwitch.register("PRN", prn); //$NON-NLS-1$
		mySwitch.register("PRS", prs); //$NON-NLS-1$
		mySwitch.register("PRG", prg); //$NON-NLS-1$
		mySwitch.register("PRM", prm); //$NON-NLS-1$
		mySwitch.register("PRTO", prto); //$NON-NLS-1$
		mySwitch.register("PRR", prr); //$NON-NLS-1$
		mySwitch.register("PRA", pra); //$NON-NLS-1$
		mySwitch.register("PTCA", ptca); //$NON-NLS-1$
		mySwitch.register("code", codeCommand); //$NON-NLS-1$
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
		String sceneName;
		String sourceItem;
		if (source == null || source.isEmpty()) return;
		if (source.contains(",")) { //$NON-NLS-1$
			String[] parts = source.split(","); //$NON-NLS-1$
			sceneName = parts[0];
			sourceItem = parts[1];
			if (OBS.getConnected()) {
				boolean showParsed = Settings.getShowParsed();
				if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSceneItemEnabledCalled") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				OBS.getController().getSceneItemId(sceneName, sourceItem, null,	(GetSceneItemIdResponse response) -> { 
					if (response != null && response.isSuccessful()) {
                        OBS.getController().setSceneItemEnabled(sceneName,response.getSceneItemId(),show,setSceneItemEnabledResponse -> {
                            if(showParsed) {
                                obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSceneItemEnabledResponseObtained") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            }
                         });
					} else {
						if(showParsed) {
							obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSceneItemEnabledFailed") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}
					}
				});
			}
		} else {
			sourceItem = source;
			if (OBS.getConnected()) {
				boolean showParsed = Settings.getShowParsed();
				if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSceneItemEnabledCalled") + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$
				sceneName = OBS.getCurrentScene();
				OBS.getController().getSceneItemId(sceneName, sourceItem, null, getSceneItemIdResponse -> { 
		            if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
		                OBS.getController().setSceneItemEnabled(sceneName,getSceneItemIdResponse.getSceneItemId(),show,setSceneItemEnabledResponse -> {
		                	if(showParsed) {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSceneItemEnabledResponseObtained") + sceneName + ", "+ sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		                	}
		                });
		            }
				});
			}
		}
	}
	public static void setSourceFilterVisibility(String source, String filter, boolean show) {
		if(source == null || filter == null) return;
		if (OBS.getConnected()) {
			boolean showParsed = Settings.getShowParsed();
			if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSourceFilterEnabledCalled") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			OBS.getController().setSourceFilterEnabled(source, filter, show, response -> {
				if(response != null && response.isSuccessful()) {
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSourceFilterEnabledResponseObtained") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				} else {
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.OBSSetSourceFilterEnabledFailed") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			});
		}
	}
	private static void connectAutoScore() {
		autoScoreSettingsPanel.addMessage(Messages.getString("Main.TryingToConnectToAutoScore")); //$NON-NLS-1$
		logger.info("Trying to connect to AutoScore..."); //$NON-NLS-1$
		createAutoScoreWorker();
		autoScoreWorker.execute();
	}
	private static void disconnectAutoScore() {
		autoScoreSettingsPanel.addMessage(Messages.getString("Main.Disconnecting")); //$NON-NLS-1$
		logger.info("Trying to disconnect from AutoScore..."); //$NON-NLS-1$
		autoScoreWorker.cancel(true);
		mainFrame.setAutoScoreIconConnected(false);
		autoScoreConnected = false;
	}
	private static void readAutoScoreConfig() {
		if(autoScoreConnected) {
			autoScoreSocketWriter.println("read:"); //$NON-NLS-1$
			if (autoScoreSocketWriter.checkError()) {
				logger.error("readAutoScoreConfig println error sending read:"); //$NON-NLS-1$
			}
		}
	}
	private static boolean validateAutoScoreConfig() {
		boolean validated = true;
		String configErrors = ""; //$NON-NLS-1$
		String[] paramNames = {"PORT","SENSOR1","SENSOR2","SENSOR3","LED1","LED2","DELAY_SENSOR","DELAY_PB","PB1","PB2"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
		String[] paramTests = {"PORT","PIN","PIN","PIN","PIN","PIN","TIME","TIME","PIN","PIN"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
		List<String> copyNames = new ArrayList<>(Arrays.asList(paramNames));
		List<String> copyTests = new ArrayList<>(Arrays.asList(paramTests));
		String config = autoScoreConfigPanel.getConfigTextArea();
		String[] lines = config.split("\n"); //$NON-NLS-1$
		for(String line:lines) {
			if(line.contains("=")) { //$NON-NLS-1$
				String[] pair = line.split("="); //$NON-NLS-1$
				String name = pair[0].trim();
				String testValue = pair[1].trim();
				if(copyNames.contains(name)) {
					int pos = -1;
					boolean found = false; 
					for(String param:copyNames) {
						pos += 1;
						if(param.equals(name)) {
							found = true;
							break;
						}
					}
					if(found) {
						String test = copyTests.get(pos);
						copyNames.remove(pos);
						copyTests.remove(pos);
						if (test.equals("PORT")) { //$NON-NLS-1$
							try {
								int value = Integer.parseInt(testValue);
								if (!((value > 0) && (value < 65535)) ) {
									String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "].  Must be between 0 and 65535."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
									configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
									logger.info(msg);
									validated = false;
								}
							} catch (NumberFormatException e) {
								String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "]. Must be between 0 and 65535."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
								logger.error(msg);
								logger.error(e.toString());
								validated = false;
							}
						} else {
							if (test.equals("PIN")) { //$NON-NLS-1$
								try {
									int value = Integer.parseInt(testValue);
									if (!(((value > -1) && (value < 23)) || ((value > 25) && (value < 29)))) {
										String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28.";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
										configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
										logger.info(msg);
										validated = false;
									}
								} catch (NumberFormatException e) {
									String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
									configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
									logger.error(msg);
									logger.error(e.toString());
									validated = false;
								}
							} else {
								if (test.equals("TIME")) { //$NON-NLS-1$
									try {
										int value = Integer.parseInt(testValue);
										if (!((value > 0) && (value < 60000)) ) {
											String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "].  Must be between 0 and 60000.";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
											configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
											logger.info(msg);
											validated = false;
										}
									} catch (NumberFormatException e) {
										String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "]. Must be between 0 and 60000."; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
										configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
										logger.error(msg);
										logger.error(e.toString());
										validated = false;
									}
								}
							}
						}
					}
				}
			}
		}
		if (!copyNames.isEmpty()) {
			String msg = "Validation Failed. Missing parameters:\r\n";  //$NON-NLS-1$
			for(String missing:copyNames) {
				msg = msg + missing + "\r\n"; //$NON-NLS-1$
			}
			configErrors = configErrors + msg + "\r\n"; //$NON-NLS-1$
			logger.info(msg);
			validated = false;
		}
		if (validated) {
			logger.info("Validation passed."); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null, Messages.getString("Main.InvalidConfiguration") + configErrors, Messages.getString("Main.ValidationResults"), 1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return validated;
	}
	private static void resetAutoScoreConfig() {
		if(autoScoreConnected) {
			autoScoreSocketWriter.println("reset:"); //$NON-NLS-1$
			if (autoScoreSocketWriter.checkError()) {
				logger.error("readAutoScoreConfig println error sending reset:"); //$NON-NLS-1$
			}
			disconnectAutoScore();
		}
	}
	private static void clearAutoScoreConfig() {
		autoScoreConfigPanel.clearConfigTextArea();
	}
	private static void writeAutoScoreConfig() {
		if(validateAutoScoreConfig()) {
			if(autoScoreConnected) {
				String config = autoScoreConfigPanel.getConfigTextArea();
				String dateStamp = dtf.format(LocalDateTime.now()) + "\r\n"; //$NON-NLS-1$
				dateStamp = dateStamp.replace(":",""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = dateStamp.replace("/",""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = dateStamp.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				dateStamp = "date = " + dateStamp; //$NON-NLS-1$
				autoScoreSocketWriter.println("save:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				if (autoScoreSocketWriter.checkError()) {
					logger.error("saveAutoScoreConfig println error sending save:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					logger.info("write autoscore config:" + dateStamp + config + "End"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
	}
	private static String ripTeamNumber(String name) {
		return name.replaceAll("[^0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}
	////// Listeners \\\\\\
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
	private static class AutoScoreSettingsApplyListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			autoScoreSettingsPanel.saveSettings();
		}
	}
	private static class AutoScoreSettingsSaveListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			autoScoreSettingsPanel.saveSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
		}
	}
	private static class AutoScoreSettingsConnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = false;
			connectAutoScore();
		}
	}
	private static class AutoScoreSettingsDisconnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			logger.info("AutoScore Settings Window Disconnect Button Pressed."); //$NON-NLS-1$
			blockAutoScoreReconnect = true;
			disconnectAutoScore();
		}
	}
	private static class AutoScoreConfigReadListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			readAutoScoreConfig();
		}
	}
	private static class AutoScoreConfigWriteListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			writeAutoScoreConfig();
		}
	}
	private static class AutoScoreConfigValidateListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			if (validateAutoScoreConfig()) {
				logger.info("Validation passed."); //$NON-NLS-1$
				JOptionPane.showMessageDialog(null, Messages.getString("Main.ValidationPassed"), Messages.getString("Main.ValidationResults"), 1); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
	private static class AutoScoreConfigResetListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			logger.info("AutoScore Configuration Reset Pico Button Pressed."); //$NON-NLS-1$
			resetAutoScoreConfig();
		}
	}
	private static class AutoScoreConfigClearListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			clearAutoScoreConfig();
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
			fetchMonitorList();
		}
	}
	private static class OBSProjectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			projectSource();
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
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.RequestingDisconnect")); //$NON-NLS-1$
			OBS.getController().disconnect();
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
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + Messages.getString("Main.RequestingDisconnect")); //$NON-NLS-1$
			OBS.getController().disconnect();
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
	private static class AutoScoreMainPanelConnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = false;
			logger.info("AutoScore Main Panel Connect Button Pressed."); //$NON-NLS-1$
			connectAutoScore();
			setFocusOnCode();
		}
	}
	private static class AutoScoreMainPanelDisconnectListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = true;
			logger.info("AutoScore Main Panel Disconnect Button Pressed."); //$NON-NLS-1$
			disconnectAutoScore();
			setFocusOnCode();
		}
	}
	private static class AutoScoreMainPanelSettingsListener implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			mainController.showAutoScore();
		}
	}
	private static class ScoreIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIST" + ripTeamNumber(btn.getName());//XIST1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class ScoreDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDST" + ripTeamNumber(btn.getName());//XDST1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class GameCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIGT" + ripTeamNumber(btn.getName());//XIGT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class GameCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDGT" + ripTeamNumber(btn.getName());//XDGT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class MatchCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIMT" + ripTeamNumber(btn.getName());//XIMT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class MatchCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDMT" + ripTeamNumber(btn.getName());//XDMT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class TimeOutCountIncreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XUTT" + ripTeamNumber(btn.getName());//XUTT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class TimeOutCountDecreaseListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XRTT" + ripTeamNumber(btn.getName());//XRTT1 //$NON-NLS-1$
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private static class ResetListener implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XPRT" + teamNumber;//XPRT1 //$NON-NLS-1$
			String filter = "Team" + teamNumber + "Reset";//Team1Reset //$NON-NLS-1$ //$NON-NLS-2$
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
			String code = "XPWT" + teamNumber;//XPWT1 //$NON-NLS-1$
			String filter = "Team" + teamNumber + "Warn";//Team1Warn //$NON-NLS-1$ //$NON-NLS-2$
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
			String code = "XPKT" + teamNumber;//XPKT1 //$NON-NLS-1$
			String filter = "Team" + teamNumber + "KingSeat";//Team1KingSeat //$NON-NLS-1$ //$NON-NLS-2$
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
			String code = "XXPT" + teamNumber;//XXPT1 //$NON-NLS-1$
			String filter = "Team" + teamNumber + "SwitchPositions";//Team1SwitchPositions //$NON-NLS-1$ //$NON-NLS-2$
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
	private static class AutoScoreWorkerStateChangeListener implements PropertyChangeListener {
        @Override
		public void propertyChange(PropertyChangeEvent e) {
			SwingWorker.StateValue state = null;
			Object source = e.getSource();
			if (source == autoScoreWorker) {
				state = autoScoreWorker.getState();
			}
			if (state == null) {
				logger.info("AutoScoreWorker state is null so probably no AutoScore instance to connect to."); //$NON-NLS-1$
			} else {
				logger.info("AutoScoreWorker state changed to: " + state.toString()); //$NON-NLS-1$
				if (state == SwingWorker.StateValue.DONE) {
					if (!blockAutoScoreReconnect) {
						logger.info("Attempt reconnect to AutoScore..."); //$NON-NLS-1$
						connectAutoScore();
					}
				}
			}
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
