/**
Copyright © 2020-2024 Hugh Garner
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
import java.time.LocalDateTime;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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

public class Main implements MatchObserver {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e1) {
				logger.info("Can't set look and feel.");
				logger.error(e1.toString());
			}
		}
	}
	private static final String ON = "1";
	////// Settings and OBSInterface setup \\\\\\
	private OBSInterface 			obsInterface 			= new OBSInterface();
	private String					matchId					= "";
	private DateTimeFormatter 		dtf 					= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private boolean 				autoScoreConnected		= false;
	private Socket 					autoScoreSocket;
	private PrintWriter 			autoScoreSocketWriter;
	private StreamIndexer 			streamIndexer      		= new StreamIndexer(Settings.getControlParameter("datapath"));
	private Boolean 				allowAutoScoreReconnect	= true;
	private Boolean 				blockAutoScoreReconnect	= false;
    private Map<String, String>		teamGameShowSourcesMap	= new HashMap<>();

	////// Watch Service for File changes \\\\\\
	WatchService watchService;
	
	////// CommandStack and UndoRedo setup \\\\\\
	private int 			undoRedoPointer			= -1;
	private Stack<Command> 	commandStack 			= new Stack<>();
	private Stack<Memento> 	mementoStackTeam1 		= new Stack<>();
	private Stack<Memento> 	mementoStackTeam2		= new Stack<>();
	private Stack<Memento> 	mementoStackTeam3       = new Stack<>();
	private Stack<Memento> 	mementoStackStats 		= new Stack<>();
	private Stack<Memento> 	mementoStackMatch		= new Stack<>();
	private Stack<Memento> 	mementoStackGameClock	= new Stack<>();
	private Stack<String>	codeStack 				= new Stack<>();
	private CommandSwitch 	mySwitch;

	////// Generate the Data Models (Mvc) \\\\\\
	private Tournament		tournament				= new Tournament(obsInterface);
	private Team 			team1 					= new Team(obsInterface, 1, Settings.getControlParameter("Side1Color"));
	private Team 			team2 					= new Team(obsInterface, 2, Settings.getControlParameter("Side2Color"));
	private Team            team3              		= new Team(obsInterface, 3, "None");
	private Match 			match					= new Match(obsInterface, team1, team2, team3);
	private Stats 			stats 					= new Stats(team1, team2);
	
	////// Create a TimeClock to be the Timer \\\\\\
	private TimeClock 		timeClock 				= new TimeClock(obsInterface);
	private GameClock       gameClock           	= new GameClock(obsInterface);
	private LastScoredClock lastScored1Clock   		= new LastScoredClock();
	private LastScoredClock	lastScored2Clock    	= new LastScoredClock();
	private LastScoredClock lastScored3Clock		= new LastScoredClock();

	////// Create the View Panels to Display (mVc) \\\\\\
	private TournamentPanel 	tournamentPanel 	= new TournamentPanel();
	private TimerPanel 			timerPanel 			= new TimerPanel();
	private OBSPanel            obsPanel            = new OBSPanel();
	private AutoScoreMainPanel	autoScoreMainPanel  = new AutoScoreMainPanel();
	private MatchPanel			matchPanel			= new MatchPanel();
	private TeamPanel 			teamPanel1 			= new TeamPanel(1, Settings.getControlParameter("Side1Color"));
	private TeamPanel 			teamPanel2 			= new TeamPanel(2, Settings.getControlParameter("Side2Color"));
	private TeamPanel			teamPanel3			= new TeamPanel(3, "None");
	private StatsEntryPanel 	statsEntryPanel 	= new StatsEntryPanel();
	private SwitchPanel 		switchPanel 		= new SwitchPanel();
	private ResetPanel 			resetPanel 			= new ResetPanel();
	private StatsDisplayPanel 	statsDisplayPanel 	= new StatsDisplayPanel();

	////// Set up Timer and Settings Windows \\\\\\
	private ParametersFrame 		parametersFrame 		= new ParametersFrame();
	private ParametersPanel			parametersPanel			= parametersFrame.getSettingsPanel();
	private HotKeysFrame 			hotKeysFrame 			= new HotKeysFrame();
	private HotKeysPanel 			hotKeysPanel			= hotKeysFrame.getHotKeysPanel();
	private SourcesFrame			sourcesFrame			= new SourcesFrame();
	private SourcesPanel			sourcesPanel			= sourcesFrame.getSourcesPanel();
	private StatSourcesFrame		statSourcesFrame		= new StatSourcesFrame();
	private StatSourcesPanel		statSourcesPanel		= statSourcesFrame.getStatSourcesPanel();
	private FiltersFrame        	filtersFrame        	= new FiltersFrame(obsInterface);
	private FiltersPanel        	filtersPanel        	= filtersFrame.getFiltersPanel();
	private PartnerProgramFrame 	partnerProgramFrame 	= new PartnerProgramFrame();
	private OBSConnectFrame			obsConnectFrame			= new OBSConnectFrame();
	private OBSConnectPanel			obsConnectPanel			= obsConnectFrame.getOBSConnectPanel();
	private AutoScoreSettingsFrame	autoScoreSettingsFrame	= new AutoScoreSettingsFrame();
	private AutoScoreSettingsPanel	autoScoreSettingsPanel	= autoScoreSettingsFrame.getAutoScoreSettingsPanel();
	private AutoScoreConfigFrame	autoScoreConfigFrame	= new AutoScoreConfigFrame();
	private AutoScoreConfigPanel	autoScoreConfigPanel	= autoScoreConfigFrame.getAutoScoreConfigPanel();
	
	////// Display the View Panels on a JFrame \\\\\\
	private MainFrame mainFrame; // The Main Window JFrame with multiple View Panels on it

	////// Set up independent Windows \\\\\\
	private GameTableWindowPanel	gameTableWindowPanel;
	private GameTableWindowFrame	gameTableWindowFrame;
	private GameResultsWindowFrame  gameResultsWindowFrame;
	private TimerWindowFrame 		timerWindowFrame;
	private LastScoredWindowFrame 	lastScored1WindowFrame;
	private LastScoredWindowFrame 	lastScored2WindowFrame;
	private LastScoredWindowFrame   lastScored3WindowFrame;

	////// Build and Start the Controllers (mvC) \\\\\\
	private MainController 					mainController;
	private TimerController 				timerController;
	private TeamController 					teamController;
	private TournamentController 			tournamentController;
	private MatchController 				matchController;
	private StatsController 				statsController;
	private SwingWorker<Boolean, Integer> 	autoScoreWorker;
	private SwingWorker<Boolean, String> 	fileWatchWorker;
	public  Main() throws IOException {

		buildTeamGameShowSourcesMap();
	    
		loadWindowsAndControllers();
		
		OBS.setHost(Settings.getOBSParameter("OBSHost"));
		OBS.setPort(Settings.getOBSParameter("OBSPort"));
		OBS.setPassword(Settings.getOBSParameter("OBSPassword"));
		OBS.setScene(Settings.getOBSParameter("OBSScene"));
		updateOBSDisconnected();

		if (Settings.getOBSParameter("OBSAutoLogin").equals("1")) {
			if (OBS.getPassword().isEmpty() || OBS.getHost().isEmpty() || OBS.getPort().isEmpty()) {
				String msg = Messages.getString("Errors.Main.AutoLogin");
				String ttl = Messages.getString("Errors.Main.AutoLogin.Title");
				logger.warn(msg);
				JOptionPane.showMessageDialog(null, msg, ttl, 1);
			} else {
				connectToOBS();
			}
		}
		loadListeners();
		loadCommands();
		match.addObserver(this);
		createAutoScoreWorker();
		if (Settings.getAutoScoreParameter("AutoScoreSettingsAutoConnect").equals("1")) {
			connectAutoScore();
		}
		createFileWatchWorker();
		fileWatchWorker.execute();
	}
	
	private void buildTeamGameShowSourcesMap() {
		teamGameShowSourcesMap.clear();
		for (int x = 1; x <= 3; x++) {
			for (int y = 1; y <= 3; y++) {
				teamGameShowSourcesMap.put(Integer.toString(x)+Integer.toString(y), Settings.getTeamGameShowSource(x, y));
			}
		}
	}
	private void buildOBSController() {
		if(OBS.getController() != null) {
			OBS.getController().stop();
			OBS.setController(null);
		}
		OBS.setController( OBSRemoteController.builder()
			.autoConnect(false)
			.host(OBS.getHost())
			.port(Integer.parseInt(OBS.getPort()))
			.password(OBS.getPassword())
			.connectionTimeout(3)
			.lifecycle()
			.onReady(this::onReady)
			.onDisconnect(this::onDisconnect)
			.onClose(webSocketCloseCode -> this.onClose(webSocketCloseCode))
			.onControllerError(reason -> this.onControllerError(reason))
			.onCommunicatorError(reason -> this.onCommunicationError(reason))
			.and()
			.registerEventListener(InputActiveStateChangedEvent.class, this::checkActiveStateChange)
			.registerEventListener(SceneItemEnableStateChangedEvent.class, this::checkItemEnableStateChange)
			.registerEventListener(CurrentProgramSceneChangedEvent.class, this::checkCurrentProgramSceneChange)
			.registerEventListener(SceneItemCreatedEvent.class, this::checkSceneItemCreated)
			.registerEventListener(SceneItemRemovedEvent.class, this::checkSceneItemRemoved)
			.registerEventListener(SceneItemListReindexedEvent.class, this::checkSceneItemListReindexed)
			.build()
		);
	}
	private void checkSceneItemCreated(SceneItemCreatedEvent sceneItemCreatedEvent) {
		loadSceneItemMap();
	}
	private void checkSceneItemRemoved(SceneItemRemovedEvent sceneItemRemovedEvent) {
		loadSceneItemMap();
	}
	private void checkSceneItemListReindexed(SceneItemListReindexedEvent sceneItemListReindexedEvent) {
		loadSceneItemMap();
	}
	private void loadSceneItemMap() {
		
	}
	private void checkCurrentProgramSceneChange(CurrentProgramSceneChangedEvent currentProgramSceneChanged) {
    	boolean showParsed = Settings.getShowParsed();
		String sceneName = currentProgramSceneChanged.getSceneName();
		if (sceneName != null && !sceneName.isEmpty()) {
			OBS.setCurrentScene(sceneName);
			if(showParsed) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Scene changed to " + sceneName + ".");
			}
		} else {
			if(showParsed) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS checkCurrentProgramSceneChange failed: sceneName was null or empty.");
			}
	   }
	}
	private void checkItemEnableStateChange(SceneItemEnableStateChangedEvent sceneItemEnableStateChanged) {
    	boolean showParsed = Settings.getShowParsed();
		String sceneName = sceneItemEnableStateChanged.getSceneName();
		Number itemId = sceneItemEnableStateChanged.getSceneItemId();
		boolean show = sceneItemEnableStateChanged.getSceneItemEnabled();
		checkItemEnableStageChangeHelper(sceneName, show, Settings.getSourceParameter("ShowScores"), itemId, showParsed);
		checkItemEnableStageChangeHelper(sceneName, show, Settings.getSourceParameter("ShowCutthroat"), itemId, showParsed);
	}
	private void checkItemEnableStageChangeHelper(String sceneName, boolean show, String sourceToCheck, Number itemId, boolean showParsed) {
		OBS.getController().getSceneItemId(sceneName, sourceToCheck, null, getSceneItemIdResponse -> {
        	if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
	           	if (getSceneItemIdResponse.getSceneItemId().toString().equals(itemId.toString())) {
	           		if (sourceToCheck.equals(Settings.getSourceParameter("ShowScores"))) {
	           			obsPanel.setShowScores(show);
	           		} else if (sourceToCheck.equals(Settings.getSourceParameter("ShowCutthroat"))) {
	           			obsPanel.setShowCutthroat(show);
	           		}
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS " + sourceToCheck + " enable state change received and is now: " + show + ".");
					}
	           	}
	        } else {
				if(showParsed) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS checkItemEnableStateChange failed: getSceneItemIdResponse was null or unsuccessful.");
				}
	        }
	    });
	}
	private void checkActiveStateChange(InputActiveStateChangedEvent inputActiveStateChangedEvent) {
		String name = inputActiveStateChangedEvent.getInputName();
		if (!Settings.getSourceParameter("ShowTimer").isEmpty() && name.equals(Settings.getSourceParameter("ShowTimer"))) {
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowTimer(show);
			mainController.showTimerWindow(show);
		} else if (!Settings.getSourceParameter("ShowScores").isEmpty() && name.equals(Settings.getSourceParameter("ShowScores"))) {
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowScores(show);
		} else if (!Settings.getSourceParameter("ShowCutthroat").isEmpty() && name.equals(Settings.getSourceParameter("ShowCutthroat"))) {
			boolean show = inputActiveStateChangedEvent.getVideoActive();
			obsPanel.setShowCutthroat(show);
		}
		setFocusOnCode();
	}
	public void connectToOBS() {
		logger.info("Trying to connect to OBS...");
		buildOBSController();
		OBS.getController().connect();
	}
	public void updateOBSConnected() {
		OBS.setConnected(true);
		obsConnectPanel.disableConnect();
		mainFrame.enableConnect(false);
		mainFrame.setOBSIconConnected(true);
		if(Settings.getOBSParameter("OBSUpdateOnConnect").equals("1")) {
			tournamentController.writeAll();
			teamController.writeAll();
			statsController.displayAllStats();
		}
	}
	public void updateOBSDisconnected() {
		OBS.setConnected(false);
		obsConnectPanel.enableConnect();
		mainFrame.enableConnect(true);
		mainFrame.setOBSIconConnected(false);
	}
	private void onReady() {
		updateOBSConnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Ready. ");
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Controller Connected. ");
		OBS.getController().getVersion(versionInfo -> {
			if(versionInfo != null && versionInfo.isSuccessful()) {
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " OBS Version: " + versionInfo.getObsVersion());
				obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " WebSocket Version: " + versionInfo.getObsWebSocketVersion());
			}
		});
		OBS.getController().setCurrentProgramScene(Settings.getOBSParameter("OBSScene"), response -> { 
			String sceneName = Settings.getOBSParameter("OBSScene");
			if(response != null && response.isSuccessful()) {
				OBS.setCurrentScene(sceneName);
				if(Settings.getShowParsed()) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Scene set to: " + sceneName);
				}
			} else {
				if(Settings.getShowParsed()) {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Unable to set scene to: " + sceneName);
				}
			}
		});
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowTimer"), response -> 
			{boolean show = response.getMessageData().getResponseData().getVideoActive();
				obsPanel.setShowTimer(show);
				mainController.showTimerWindow(show);		
			});
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowScores"), response ->
			{boolean show = response.getMessageData().getResponseData().getVideoActive();
				obsPanel.setShowScores(show);
			});
		OBS.getController().getSourceActive(Settings.getSourceParameter("ShowCutthroat"), response ->
		{boolean show = response.getMessageData().getResponseData().getVideoActive();
			obsPanel.setShowCutthroat(show);
		});
		if(Settings.getOBSParameter("OBSCloseOnConnect").equals("1")) { 
			obsConnectFrame.setVisible(false);
		}
	}
	private void onClose(WebSocketCloseCode webSocketCloseCode) {
		updateOBSDisconnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Controller Closed. " + webSocketCloseCode.getCode());
	}
	private void onDisconnect() {
		updateOBSDisconnected();
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS Controller Disconnected. ");
	}
	private void onControllerError(ReasonThrowable reason) {
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " Controller Error: " + reason.getReason());
	}
	private void onCommunicationError(ReasonThrowable reason) {
		obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " Commucnication Error: " + reason.getReason());
	}
	private void createAutoScoreWorker() {
		autoScoreWorker = new SwingWorker<Boolean, Integer>() {
			BufferedReader dataIn;
			@Override
			protected Boolean doInBackground() throws Exception {
		    	boolean isConnected = false;
		    	String address = Settings.getAutoScoreParameter("AutoScoreSettingsServerAddress");
		    	int port = Settings.getAutoScoreParameter("AutoScoreSettingsServerPort",Integer::parseInt);
		    	try {
		            autoScoreSocket = new Socket(address, port);
		            autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Connected to " + address + ": " + port);
		            logger.info("Auto Score connected to " + address + ": " + port);
		        	dataIn = new BufferedReader(new InputStreamReader(autoScoreSocket.getInputStream()));
					try {
						autoScoreSocketWriter = new PrintWriter(autoScoreSocket.getOutputStream());
						if (autoScoreSocketWriter.checkError()) {
							logger.error("createAutoScoreWork doInBackground new PrintWriter error:");
						}
					} catch(IOException ex) {
						logger.error("createAutoScoreWork doInBackground PrintWriter exception:");
						logger.error(ex.toString());
					}
		        	isConnected = true;
		    		mainFrame.setAutoScoreIconConnected(true);
		    		autoScoreConnected = true;
		        }
		        catch(UnknownHostException uh) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Auto Score UnknownHostException");
		        	logger.error("Auto Score new Socket UnknownHostException");
		        	logger.error(uh.toString());
		        }
		        catch(IOException io) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Auto Score IOException");
		        	logger.error("Auto Score new Socket IOException");
		        	logger.error(io.toString());
		        }
		    	String raw = "";
		        String str[];
		        String cmd[];
		        while (isConnected) {
            		try {
           				raw = dataIn.readLine();
		            } catch(IOException io) {
		            	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString());
			        	logger.error(io.toString());
			        	isConnected = false;
		            }
            		if (raw != null && !raw.isEmpty()) {
                		logger.info("Received raw data: [" + raw + "]");
		        		cmd = raw.split(":");
		        		logger.info("Parse command: " + cmd[0]);
	                	if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals("1")) {
	                		autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Received " + raw);
	                	}
	                	if (cmd[0].equals("Team")) {
	    	                str = cmd[1].split("[,]",0);
			                if (str[0].equals("1")) {
			                	publish(1);
			                } else {
				                if (str[0].equals("2")) {
				                	publish(2);
				                }
			                }
	                	} else {
	                		if (cmd[0].equals("TO")) {
	                			str = cmd[1].split("[,]",0);
				                if (str[0].equals("1")) {
				                	publish(3);
				                } else {
					                if (str[0].equals("2")) {
					                	publish(4);
					                }
				                }
	                		}
	                	}
	                	if (cmd[0].equals("Read")) {
	                		autoScoreConfigPanel.clearConfigTextArea();
	                	}
	                	if (cmd[0].equals("Line")) {
	                		String line = cmd[1] + "\n";
	                		autoScoreConfigPanel.appendConfigTextArea(line);
	                	}
            		}
	                if (isCancelled()) {
	                	break;
	                }
		        }
		        autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Connection Terminated!!");
		        logger.info("Auto Score Connection Terminated!!");
		        try {
		            dataIn.close();
		            autoScoreSocket.close();
		            isConnected = false;
		        } catch(IOException io) {
		        	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + io.toString());
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
					autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Worker completed with isConnected: " + status);
			    } catch (InterruptedException e) {
			    	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString());
		        	logger.error(e.toString());
			    } catch (ExecutionException e) {
			    	autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": " + e.toString());
		        	logger.error(e.toString());
			    }
	    		mainFrame.setAutoScoreIconConnected(false);
	    		autoScoreConnected = false;			    	
			}
			@Override
			protected void process(List<Integer> chunks) {
				if (isCancelled()) return;
				int mostRecentValue = chunks.get(chunks.size()-1);
				boolean ignoreSensors = autoScoreMainPanel.isIgnored();
            	if (Settings.getAutoScoreParameter("AutoScoreSettingsDetailLog").equals("1")) {
            		if (ignoreSensors) {
            			if (mostRecentValue == 1 || mostRecentValue == 2) {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team "+mostRecentValue+ " scored! but ignored!");
            			} else {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team "+(mostRecentValue-2)+ " called Time Out.");
            			}
            		} else {
            			if (mostRecentValue == 1 || mostRecentValue == 2) {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team "+mostRecentValue+ " scored!");
            			} else {
            				autoScoreSettingsPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Team "+(mostRecentValue-2)+ " called Time Out.");
            			}
            		}
            	}
				if (!ignoreSensors && (mostRecentValue == 1)) {
					processCode("XIST1", false);
				} else {
					if (!ignoreSensors && (mostRecentValue == 2)) {
						processCode("XIST2", false);
					}
				}
				if (mostRecentValue == 3) {
					processCode("XUTT1", false);
				} else {
					if (mostRecentValue == 4) {
						processCode("XUTT2", false);
					}
				}
			}
		};
		autoScoreWorker.addPropertyChangeListener(new AutoScoreWorkerStateChangeListener());
	}
	private void checkFilters(String code) {
		String filter = "";
		Integer teamNbr = 0;
		if (code.equals("XIST1") || code.equals("XIST2")) {
			if (code.equals("XIST1")) {
				teamNbr = 1;
			} else {
				teamNbr = 2;
			}
			filter = "Team" + teamNbr + "Score";
			activateFilter(filter);
			int winState = match.getWinState();
			if (winState == 1) {
				int gn = match.getCurrentGameNumber();
				if (gn > 1) {
					int a = Integer.parseInt(match.getScoresTeam1()[gn-2]); 
					int b = Integer.parseInt(match.getScoresTeam2()[gn-2]);
					if (a == 0 || b == 0) {
						if(Settings.getControlParameter("ShowSkunk").equals(ON)) {
							filter = "Team" + teamNbr + "Skunk";
							activateFilter(filter);
						}
					}
					filter = "Team" + teamNbr + "WinGame";
					activateFilter(filter);
//					String result = "[" + gameClock.getStreamTime() + "] " + team1.getForwardName() + "/" + team1.getGoalieName() + " " + a + " vs " 
//							+ team2.getForwardName() + "/" + team2.getGoalieName() + " " + b + " (" + match.getTimes()[match.getCurrentGameNumber()] + ")";
					String result = "[" + gameClock.getStreamTime() + "] " + a + " " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " 
							+ team2.getForwardName() + "/" + team2.getGoalieName() + " " + b;
					gameResultsWindowFrame.addLine(result);
					StringBuilder results = new StringBuilder();
					results = gameResultsWindowFrame.buildGameResults();
					matchController.updateGameResults(results);					if(gameClock.isStreamTimerRunning()) {
						if(Settings.getControlParameter("CutThroatMode").equals(ON)) {
							int c = Integer.parseInt(match.getScoresTeam3()[gn-2]);
							streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Game end: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + ": " + a + " to " + b + " to " + c + "\r\n");
						} else {
							streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Game end: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + ": " + a + " to " + b + "\r\n");
						}
					}
				}
			} else {
				if (winState == 2) {
					int a = team1.getScore();
					int b = team2.getScore();
					if (a == 0 || b == 0) {
						if(Settings.getControlParameter("ShowSkunk").equals(ON)) {
							filter = "Team" + teamNbr + "Skunk";
							activateFilter(filter);
						}
					}
					filter = "Team" + teamNbr + "WinMatch";
					activateFilter(filter);
//					String result = "[" + gameClock.getStreamTime() + "] " + team1.getForwardName() + "/" + team1.getGoalieName() + " " + a + " vs " 
//							+ team2.getForwardName() + "/" + team2.getGoalieName() + " " + b + " (" + match.getTimes()[match.getCurrentGameNumber()] + ")";
					String result = "[" + gameClock.getStreamTime() + "] " + a + " " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " 
							+ team2.getForwardName() + "/" + team2.getGoalieName() + " " + b;
					gameResultsWindowFrame.addLine(result);
					StringBuilder results = new StringBuilder();
					results = gameResultsWindowFrame.buildGameResults();
					matchController.updateGameResults(results);
					if(gameClock.isStreamTimerRunning()) {
						if(Settings.getControlParameter("CutThroatMode").equals(ON)) {
							int c = team3.getScore();
							streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Match end: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + ": " + a + " to " + b + " to " + c + "\r\n");
						} else {
							streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Match end: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + ": " + a + " to " + b + "\r\n");
						}
					}
//				} else {
//					int gn = match.getCurrentGameNumber();
//					int a;
//					int b;
//					if (gn > 1) {
//						a = Integer.parseInt(match.getScoresTeam1()[gn-2]); 
//						b = Integer.parseInt(match.getScoresTeam2()[gn-2]);
//					} else {
//						a = 0;
//						b = 0;
//					}
//					String result = "[" + gameClock.getStreamTime() + "] " + team1.getForwardName() + "/" + team1.getGoalieName() + " " + a + " vs " 
//							+ team2.getForwardName() + "/" + team2.getGoalieName() + " " + b + " (" + match.getTimes()[match.getCurrentGameNumber()] + ")";
//					gameResultsWindowFrame.addLine(result);
//					StringBuilder results = new StringBuilder();
//					results = gameResultsWindowFrame.buildGameResults();
//					matchController.updateGameResults(results);
				}
			}
		} else {
			if (code.equals("XUTT1") || code.equals("XUTT2")) {
				if (code.equals("XUTT1")) {
					activateFilter("Team1TimeOut");
				} else {
					activateFilter("Team2TimeOut");
				}
			}	
		}
	}
	private void activateFilter(String filter) {
		setSourceFilterVisibility(OBS.getScene(), Settings.getFiltersFilter(filter), true);
	}
	public void loadWindowsAndControllers() {
		mainFrame = new MainFrame(Settings.getInstance(), tournamentPanel, timerPanel, obsPanel, autoScoreMainPanel, teamPanel1, teamPanel2, teamPanel3, statsEntryPanel, 
				switchPanel, resetPanel, statsDisplayPanel, matchPanel, parametersFrame, hotKeysFrame, sourcesFrame, statSourcesFrame, filtersFrame, 
				partnerProgramFrame, obsConnectFrame, autoScoreSettingsFrame, autoScoreConfigFrame, this);
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
	public void onMeatball() {
		activateFilter("Meatball");
	}
	public void cutthroatRotate(int rotate) {
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
	public void startEvent() {
		if(gameClock.isStreamTimerRunning()) {
			streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartEvent", Settings.getGameType()) + " Pressed: " + tournament.getTournamentName() + ": " + tournament.getEventName() + "\r\n");
		}
	}
	public void startMatch() {
		matchId = createMatchId();
		matchController.startMatch(matchId);
		if(gameClock.isStreamTimerRunning()) {
			if(Settings.getControlParameter("CutThroatMode").equals(ON)) {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartMatch", Settings.getGameType()) + " Pressed: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + "\r\n");
			} else {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartMatch", Settings.getGameType()) + " Pressed: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + "\r\n");
			}
		}
	}
	public void endMatch() {
		matchController.endMatch();
		if(gameClock.isStreamTimerRunning()) {
			streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.EndMatch", Settings.getGameType()) + " Pressed.\r\n");
		}
	}
	public void startGame() {
		if(gameClock.isStreamTimerRunning()) {
			if(Settings.getControlParameter("CutThroatMode").equals(ON)) {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartGame", Settings.getGameType()) + " Pressed: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + " vs " + team3.getForwardName() + "/" + team3.getGoalieName() + "\r\n");
			} else {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": " + Messages.getString("MatchPanel.StartGame", Settings.getGameType()) + " Pressed: " + team1.getForwardName() + "/" + team1.getGoalieName() + " vs " + team2.getForwardName() + "/" + team2.getGoalieName() + "\r\n");
			}
		}
		matchController.startGame();
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
		Settings.setControlParameter("ShowParsed",showParsed);
		try {
			Settings.saveControlConfig();
		} catch (IOException e) {
        	logger.error(e.toString());
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
				} else {
					checkFilters(code);
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
	private void makeMementos() {
		deleteElementsAfterPointer(undoRedoPointer);
		mementoStackTeam1.push(saveState(team1));
		mementoStackTeam2.push(saveState(team2));
		mementoStackTeam3.push(saveState(team3));
		mementoStackStats.push(saveState(stats));
		mementoStackMatch.push(saveState(match));
		mementoStackGameClock.push(saveState(gameClock));
	}
	private Memento saveState(Object object) {
		return new Memento(object);
	}
	public void undo() 	{
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
		        mementoStackTeam3.remove(i);
		        mementoStackStats.remove(i);
		        mementoStackMatch.remove(i);
		        mementoStackGameClock.remove(i);
		    }
	    }
    }
	private void loadCommands() {
		Command pse = new PSECommand(statsController, this);
		Command psm = new PSMCommand(statsController, this);
		Command pem = new PEMCommand(statsController, this);
		Command ppm = new PPMCommand(statsController, matchController);
		Command psg = new PSGCommand(statsController, this);
		Command sst = new SSTCommand(statsController, teamController);
		Command spt = new SPTCommand(statsController, teamController);
		Command sgt = new SGTCommand(statsController, teamController);
		Command stt = new STTCommand(statsController, teamController);
		Command srt = new SRTCommand(statsController, teamController);
		Command prt = new PRTCommand(statsController, teamController);
		Command ist1 = new ISTCommand(this, statsController, matchController, 1);
		Command ist2 = new ISTCommand(this, statsController, matchController, 2);
		Command ist3 = new ISTCommand(this, statsController, matchController, 3);
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
		Command pss = new PSSCommand(statsController, this);
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
		Command pca = new PCACommand(statsController, teamController, matchController, this);
		Command prn= new PRNCommand(statsController, teamController);
		Command prs = new PRSCommand(statsController, teamController);
		Command prg = new PRGCommand(statsController, teamController);
		Command prm = new PRMCommand(statsController, teamController);
		Command prto = new PRTOCommand(statsController, teamController);
		Command prr = new PRRCommand(statsController, teamController);
		Command pra = new PRACommand(statsController, teamController, matchController, this);
		Command codeCommand = new CodeCommand(statsController);
		Command ptca = new PTCACommand(statsController, tournamentController);
		mySwitch = new CommandSwitch();
		mySwitch.register("PSE", pse);
		mySwitch.register("PSM", psm);
		mySwitch.register("PEM", pem);
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
		mySwitch.register("IST3", ist3);
		mySwitch.register("DST1", dst1);
		mySwitch.register("DST2", dst2);
		mySwitch.register("DST3", dst3);
		mySwitch.register("IGT1", igt1);
		mySwitch.register("IGT2", igt2);
		mySwitch.register("IGT3", igt3);
		mySwitch.register("IMT1", imt1);
		mySwitch.register("IMT2", imt2);
		mySwitch.register("IMT3", imt3);
		mySwitch.register("DGT1", dgt1);
		mySwitch.register("DGT2", dgt2);
		mySwitch.register("DGT3", dgt3);
		mySwitch.register("DMT1", dmt1);
		mySwitch.register("DMT2", dmt2);
		mySwitch.register("DMT3", dmt3);
		mySwitch.register("UTT1", utt1);
		mySwitch.register("UTT2", utt2);
		mySwitch.register("UTT3", utt3);
		mySwitch.register("RTT1", rtt1);
		mySwitch.register("RTT2", rtt2);
		mySwitch.register("RTT3", rtt3);
		mySwitch.register("PRT1", prt1);
		mySwitch.register("PRT2", prt2);
		mySwitch.register("PRT3", prt3);
		mySwitch.register("PWT1", pwt1);
		mySwitch.register("PWT2", pwt2);
		mySwitch.register("PWT3", pwt3);
		mySwitch.register("PKT1", pkt1);
		mySwitch.register("PKT2", pkt2);
		mySwitch.register("PKT3", pkt3);
		mySwitch.register("PSS", pss);
		mySwitch.register("XPT1", xpt1);
		mySwitch.register("XPT2", xpt2);
		mySwitch.register("XPT3", xpt3);
		mySwitch.register("PST", pst);
		mySwitch.register("XP1", xp1);
		mySwitch.register("XP2", xp2);
		mySwitch.register("PSSC", pssc);
		mySwitch.register("PSGC", psgc);
		mySwitch.register("PSMC", psmc);
		mySwitch.register("PSTO", psto);
		mySwitch.register("PSR", psr);
		mySwitch.register("PCA", pca);
		mySwitch.register("PRN", prn);
		mySwitch.register("PRS", prs);
		mySwitch.register("PRG", prg);
		mySwitch.register("PRM", prm);
		mySwitch.register("PRTO", prto);
		mySwitch.register("PRR", prr);
		mySwitch.register("PRA", pra);
		mySwitch.register("code", codeCommand);
		mySwitch.register("PTCA", ptca);
	}
	public void showScores(boolean show) {
		obsPanel.setShowScores(show);
		showSource(Settings.getSourceParameter("ShowScores"), show);
		setFocusOnCode();
	}
	public void showTimer(boolean show) {
		mainController.showTimerWindow(show);
		showSource(Settings.getSourceParameter("ShowTimer"), show);
		setFocusOnCode();
	}
	public void setFocusOnCode() {
		statsEntryPanel.setFocusOnCode();
	}
	public void showCutthroat(boolean show) {
		obsPanel.setShowCutthroat(show);
		showSource(Settings.getSourceParameter("ShowCutthroat"), show);
		setFocusOnCode();
	}
	public void showSource(String source, boolean show) {
		String sceneName;
		String sourceItem;
		if (source == null || source.isEmpty()) return;
		if (source.contains(",")) {
			String[] parts = source.split(",");
			sceneName = parts[0];
			sourceItem = parts[1];
			if (OBS.getConnected()) {
				boolean showParsed = Settings.getShowParsed();
				if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSceneItemEnabled called: " + sceneName + ", " + sourceItem + ", " + show);
				OBS.getController().getSceneItemId(sceneName, sourceItem, null,	getSceneItemIdResponse -> { 
					if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
						if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
							OBS.getController().setSceneItemEnabled(sceneName,getSceneItemIdResponse.getSceneItemId(),show,setSceneItemEnabledResponse -> {
								if(showParsed) {
									obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSceneItemEnabled response obtained: " + sceneName + ", " + sourceItem + ", " + show);
	            				}
				             });
						}
					} else {
						if(showParsed) {
							obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSceneItemEnabled failed: " + sceneName + ", " + sourceItem + ", " + show);
						}
					}
				});
			}
		} else {
			sourceItem = source;
			if (OBS.getConnected()) {
				boolean showParsed = Settings.getShowParsed();
				if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSceneItemEnabled called: " + sourceItem + ", " + show);
				sceneName = OBS.getCurrentScene();
				OBS.getController().getSceneItemId(sceneName, sourceItem, null, getSceneItemIdResponse -> { 
		            if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
		                OBS.getController().setSceneItemEnabled(sceneName,getSceneItemIdResponse.getSceneItemId(),show,setSceneItemEnabledResponse -> {
		                	if(showParsed) {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSceneItemEnabled response obtained: " + sceneName + ", "+ sourceItem + ", " + show);
		                	}
		                });
		            }
				});
			}
		}
	}
	public void setSourceFilterVisibility(String source, String filter, boolean show) {
		if(source == null || filter == null) return;
		if (OBS.getConnected()) {
			boolean showParsed = Settings.getShowParsed();
			if (showParsed) obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceFilterEnabled called: " + source + ", " + filter + ", " + show);
			OBS.getController().setSourceFilterEnabled(source, filter, show, response -> {
				if(response != null && response.isSuccessful()) {
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceFilterEnabled response obtained: " + source + ", " + filter + ", " + show);
					}
				} else {
					if(showParsed) {
						obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": OBS setSourceFilterEnabled failed: " + source + ", " + filter + ", " + show);
					}
				}
			});
		}
	}
	private void connectAutoScore() {
		autoScoreSettingsPanel.addMessage("Trying to connect to AutoScore...");
		logger.info("Trying to connect to AutoScore...");
		createAutoScoreWorker();
		autoScoreWorker.execute();
	}
	private void disconnectAutoScore() {
		autoScoreSettingsPanel.addMessage("Disconnecting...");
		logger.info("Trying to disconnect from AutoScore...");
		autoScoreWorker.cancel(true);
		mainFrame.setAutoScoreIconConnected(false);
		autoScoreConnected = false;
	}
	private void readAutoScoreConfig() {
		if(autoScoreConnected) {
			autoScoreSocketWriter.println("read:");
			if (autoScoreSocketWriter.checkError()) {
				logger.error("readAutoScoreConfig println error sending read:");
			}
		}
	}
	private boolean validateAutoScoreConfig() {
		boolean validated = true;
		String configErrors = "";
		String[] paramNames = {"PORT","SENSOR1","SENSOR2","SENSOR3","LED1","LED2","DELAY_SENSOR","DELAY_PB","PB1","PB2"};
		String[] paramTests = {"PORT","PIN","PIN","PIN","PIN","PIN","TIME","TIME","PIN","PIN"};
		List<String> copyNames = new ArrayList<>(Arrays.asList(paramNames));
		List<String> copyTests = new ArrayList<>(Arrays.asList(paramTests));
		String config = autoScoreConfigPanel.getConfigTextArea();
		String[] lines = config.split("\n");
		for(String line:lines) {
			if(line.contains("=")) {
				String[] pair = line.split("=");
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
						if (test.equals("PORT")) {
							try {
								int value = Integer.parseInt(testValue);
								if (!((value > 0) && (value < 65535)) ) {
									String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "].  Must be between 0 and 65535.";
									configErrors = configErrors + msg + "\r\n";
									logger.info(msg);
									validated = false;
								}
							} catch (NumberFormatException e) {
								String msg = "Validation failed on " + name + ". Invalid port [" + testValue + "]. Must be between 0 and 65535.";
								configErrors = configErrors + msg + "\r\n";
								logger.error(msg);
								logger.error(e.toString());
								validated = false;
							}
						} else {
							if (test.equals("PIN")) {
								try {
									int value = Integer.parseInt(testValue);
									if (!(((value > -1) && (value < 23)) || ((value > 25) && (value < 29)))) {
										String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28."; 
										configErrors = configErrors + msg + "\r\n";
										logger.info(msg);
										validated = false;
									}
								} catch (NumberFormatException e) {
									String msg = "Validation failed on " + name + ". Invalid pin [" + testValue + "].  Must be 0-23,26,27,28.";
									configErrors = configErrors + msg + "\r\n";
									logger.error(msg);
									logger.error(e.toString());
									validated = false;
								}
							} else {
								if (test.equals("TIME")) {
									try {
										int value = Integer.parseInt(testValue);
										if (!((value > 0) && (value < 60000)) ) {
											String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "].  Must be between 0 and 60000."; 
											configErrors = configErrors + msg + "\r\n";
											logger.info(msg);
											validated = false;
										}
									} catch (NumberFormatException e) {
										String msg = "Validation failed on " + name + ". Invalid time [" + testValue + "]. Must be between 0 and 60000.";
										configErrors = configErrors + msg + "\r\n";
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
			String msg = "Validation Failed. Missing parameters:\r\n"; 
			for(String missing:copyNames) {
				msg = msg + missing + "\r\n";
			}
			configErrors = configErrors + msg + "\r\n";
			logger.info(msg);
			validated = false;
		}
		if (validated) {
			logger.info("Validation passed.");
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Configuration: " + configErrors, "Validation Results", 1);
		}
		return validated;
	}
	private void resetAutoScoreConfig() {
		if(autoScoreConnected) {
			autoScoreSocketWriter.println("reset:");
			if (autoScoreSocketWriter.checkError()) {
				logger.error("readAutoScoreConfig println error sending reset:");
			}
			disconnectAutoScore();
		}
	}
	private void clearAutoScoreConfig() {
		autoScoreConfigPanel.clearConfigTextArea();
	}
	private void writeAutoScoreConfig() {
		if(validateAutoScoreConfig()) {
			if(autoScoreConnected) {
				String config = autoScoreConfigPanel.getConfigTextArea();
				String dateStamp = dtf.format(LocalDateTime.now()) + "\r\n";
				dateStamp = dateStamp.replace(":","");
				dateStamp = dateStamp.replace("/","");
				dateStamp = dateStamp.replace(" ", "");
				dateStamp = "date = " + dateStamp;
				autoScoreSocketWriter.println("save:" + dateStamp + config + "End");
				if (autoScoreSocketWriter.checkError()) {
					logger.error("saveAutoScoreConfig println error sending save:" + dateStamp + config + "End");
				} else {
					logger.info("write autoscore config:" + dateStamp + config + "End");
				}
			}
		}
	}
	private static String ripTeamNumber(String name) {
		return name.replaceAll("[^0-9]", "");
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
			setFocusOnCode();	
		}
	}
	private class StatsClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stats.clearAll();
			statsEntryPanel.clearAll();
			undoRedoPointer = -1;
			setFocusOnCode();
		}
	}
	private void hotKeysSaveSettings() {
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
	private class HotKeysApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hotKeysSaveSettings();
		}
	}
	private class HotKeysSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hotKeysSaveSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
		}
	}
	private class SourcesApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (sourcesPanel.saveSettings()) {
				buildTeamGameShowSourcesMap();
			}
		}
	}
	private class SourcesSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (sourcesPanel.saveSettings()) {
				buildTeamGameShowSourcesMap();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		}
	}
	private class StatSourcesApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			statSourcesPanel.saveSettings();
		}
	}
	private class StatSourcesSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (statSourcesPanel.saveSettings()) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		}
	}
	private class AutoScoreSettingsApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			autoScoreSettingsPanel.saveSettings();
		}
	}
	private class AutoScoreSettingsSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			autoScoreSettingsPanel.saveSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
		}
	}
	private class AutoScoreSettingsConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = false;
			connectAutoScore();
		}
	}
	private class AutoScoreSettingsDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			logger.info("AutoScore Settings Window Disconnect Button Pressed.");
			blockAutoScoreReconnect = true;
			disconnectAutoScore();
		}
	}
	private class AutoScoreConfigReadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			readAutoScoreConfig();
		}
	}
	private class AutoScoreConfigWriteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			writeAutoScoreConfig();
		}
	}
	private class AutoScoreConfigValidateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (validateAutoScoreConfig()) {
				logger.info("Validation passed.");
				JOptionPane.showMessageDialog(null, "Validation passed.", "Validation Results", 1);
			}
		}
	}
	private class AutoScoreConfigResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			logger.info("AutoScore Configuration Reset Pico Button Pressed.");
			resetAutoScoreConfig();
		}
	}
	private class AutoScoreConfigClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearAutoScoreConfig();
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
			OBS.setScene(txt.getText());
		}
	}
	private class OBSDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Requesting disconnect.");
			OBS.getController().disconnect();
			setFocusOnCode();
		}
	}
	private class OBSConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.updateOBS();
			connectToOBS();
			setFocusOnCode();
		}
	}
	private class OBSDisconnectItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Requesting disconnect.");
			OBS.getController().disconnect();
			obsConnectPanel.updateOBS();
			setFocusOnCode();
		}
	}
	private class OBSSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsConnectPanel.saveSettings();
			setFocusOnCode();
		}
	}
	private class OBSPushListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				tournamentController.writeAll();
				teamController.writeAll();
				statsController.displayAllStats();
			}
			setFocusOnCode();
		}
	}
	private class OBSPullListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				teamController.fetchAll();
				teamController.displayAll();
				tournamentController.fetchAll();
			}
			setFocusOnCode();
		}
	}
	private class OBSShowScoresListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showScores(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private class OBSShowTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showTimer(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private class OBSShowCutthroatListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (OBS.getConnected()) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				showCutthroat(abstractButton.getModel().isSelected());
			}
			setFocusOnCode();
		}
	}
	private class OBSEnableSkunkListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AbstractButton abstractButton = (AbstractButton) e.getSource();
			boolean showSkunkFlag = abstractButton.getModel().isSelected();
			obsPanel.setEnableSkunk(showSkunkFlag);
			parametersPanel.setEnableShowSkunk(showSkunkFlag);
			if (showSkunkFlag) {
				Settings.setControlParameter("ShowSkunk","1");
			} else {
				Settings.setControlParameter("ShowSkunk","0");
			}
			setFocusOnCode();
		}
	}
	private class OBSStartStreamListener implements ActionListener {
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
	private class GameClockTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			obsPanel.updateTimerDisplay(gameClock.getStreamTime());
		}
	}
	private class ParametersApplyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			saveParameterSettings();
		}
	}
	private class ParametersSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			saveParameterSettings();
			JComponent comp = (JComponent) e.getSource();
			Window win = SwingUtilities.getWindowAncestor(comp);
			win.dispose();
			setFocusOnCode();
		}
	}
	private void saveParameterSettings() {
		int oldGamesToWin = Integer.parseInt(Settings.getControlParameter("GamesToWin"));
		int oldCutthroatMode = Integer.parseInt(Settings.getControlParameter("CutThroatMode"));
		parametersPanel.saveSettings(Settings.getInstance());
		int newGamesToWin = Integer.parseInt(Settings.getControlParameter("GamesToWin"));
		int newCutthroatMode = Integer.parseInt(Settings.getControlParameter("CutThroatMode"));
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
	private class AutoScoreMainPanelConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = false;
			logger.info("AutoScore Main Panel Connect Button Pressed.");
			connectAutoScore();
			setFocusOnCode();
		}
	}
	private class AutoScoreMainPanelDisconnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			blockAutoScoreReconnect = true;
			logger.info("AutoScore Main Panel Disconnect Button Pressed.");
			disconnectAutoScore();
			setFocusOnCode();
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
			String code = "XIST" + ripTeamNumber(btn.getName());//XIST1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class ScoreDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDST" + ripTeamNumber(btn.getName());//XDST1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class GameCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIGT" + ripTeamNumber(btn.getName());//XIGT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class GameCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDGT" + ripTeamNumber(btn.getName());//XDGT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class MatchCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XIMT" + ripTeamNumber(btn.getName());//XIMT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class MatchCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XDMT" + ripTeamNumber(btn.getName());//XDMT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class TimeOutCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XUTT" + ripTeamNumber(btn.getName());//XUTT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class TimeOutCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String code = "XRTT" + ripTeamNumber(btn.getName());//XRTT1
			processCode(code,false);
			setFocusOnCode();
		}
	}
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XPRT" + teamNumber;//XPRT1
			String filter = "Team" + teamNumber + "Reset";//Team1Reset
			processCode(code,false);
			if (btn.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private class WarnListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XPWT" + teamNumber;//XPWT1
			String filter = "Team" + teamNumber + "Warn";//Team1Warn
			processCode(code,false);
			if (btn.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private class KingSeatListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JCheckBox ckbx = (JCheckBox) e.getSource();
			String teamNumber = ripTeamNumber(ckbx.getName());
			String code = "XPKT" + teamNumber;//XPKT1
			String filter = "Team" + teamNumber + "KingSeat";//Team1KingSeat
			processCode(code,false);
			if (ckbx.isSelected()) activateFilter(filter);
			setFocusOnCode();
		}
	}
	private class SwitchPositionsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String teamNumber = ripTeamNumber(btn.getName());
			String code = "XXPT" + teamNumber;//XXPT1
			String filter = "Team" + teamNumber + "SwitchPositions";//Team1SwitchPositions
			processCode(code,false);
			activateFilter(filter);
			setFocusOnCode();
		}
	}
	private class SwitchSidesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSS",false);
			activateFilter("SwitchSides");
			setFocusOnCode();
		}
	}
	private class ShotTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSST",false);
			setFocusOnCode();
		}
	}
	private class PassTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSPT",false);
			setFocusOnCode();
		}
	}
	private class TimeOutTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSTT",false);
			setFocusOnCode();
		}
	}
	private class GameTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSGT",false);
			setFocusOnCode();
		}
	}
	private class RecallTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XSRT",false);
			setFocusOnCode();
		}
	}
	private class ResetTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRT",false);
			setFocusOnCode();
		}
	}
	private class StartEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSE",false);
			setFocusOnCode();
		}
	}
	private class StartMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSM",false);
			activateFilter("StartMatch");
			setFocusOnCode();
		}
	}
	private class PauseMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPPM",false);
			setFocusOnCode();
		}
	}
	private class EndMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPEM",false);
			setFocusOnCode();
		}
	}
	private class StartGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSG",false);
			activateFilter("StartGame");
			setFocusOnCode();
		}
	}
	private class SwitchTeamsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPST",false);
			setFocusOnCode();
		}
	}
	private class SwitchPlayer1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XXP1",false);
			setFocusOnCode();
		}
	}
	private class SwitchPlayer2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XXP2",false);
			setFocusOnCode();
		}
	}
	private class SwitchScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSSC",false);
			setFocusOnCode();
		}
	}
	private class SwitchGameCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSGC",false);
			setFocusOnCode();
		}
	}
	private class SwitchMatchCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSMC",false);
			setFocusOnCode();
		}
	}
	private class SwitchTimeOutsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSTO",false);
			setFocusOnCode();
		}
	}
	private class SwitchResetWarnsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPSR",false);
			setFocusOnCode();
		}
	}
	private class ClearAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPCA",false);
			setFocusOnCode();
		}
	}
	private class ResetNamesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRN",false);
			setFocusOnCode();
		}
	}
	private class ResetScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRS",false);
			setFocusOnCode();
		}
	}
	private class ResetGameCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRG",false);
			setFocusOnCode();
		}
	}
	private class ResetMatchCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRM",false);
			setFocusOnCode();
		}
	}
	private class ResetTimeOutsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRTO",false);
			setFocusOnCode();
		}
	}
	private class ResetResetWarnsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRR",false);
			setFocusOnCode();
		}
	}
	private class ResetAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			processCode("XPRA",false);
			statsController.displayAllStats();
			setFocusOnCode();
		}
	}
	private class TableClearAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			processCode("XPTCA",false);
			setFocusOnCode();
		}
	}
	private class TimerWindowCloseListener extends WindowAdapter {
		public void windowClosed(WindowEvent we) {
			showTimer(false);
		}
	}
	private class AutoScoreWorkerStateChangeListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent e) {
			SwingWorker.StateValue state = null;
			Object source = e.getSource();
			if (source == autoScoreWorker) {
				state = autoScoreWorker.getState();
			}
			if (state == null) {
				logger.info("AutoScoreWorker state is null so probably no AutoScore instance to connect to.");
			} else {
				logger.info("AutoScoreWorker state changed to: " + state.toString());
				if (state == SwingWorker.StateValue.DONE) {
					if (allowAutoScoreReconnect && !blockAutoScoreReconnect) {
						logger.info("Attempt reconnect to AutoScore...");
						connectAutoScore();
					}
				}
			}
		}
	}
	private class Team1ScoreFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Score");
		}
	}
	private class Team2ScoreFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Score");
		}
	}
	private class Team1WinGameFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1WinGame");
		}
	}
	private class Team2WinGameFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2WinGame");
		}
	}
	private class Team1WinMatchFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1WinMatch");
		}
	}
	private class Team2WinMatchFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2WinMatch");
		}
	}
	private class Team1TimeOutFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1TimeOut");
		}
	}
	private class Team2TimeOutFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2TimeOut");
		}
	}
	private class Team1ResetFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Reset");
		}
	}
	private class Team2ResetFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Reset");
		}
	}
	private class Team1WarnFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Warn");
		}
	}
	private class Team2WarnFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Warn");
		}
	}
	private class Team1SwitchPositionsFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1SwitchPositions");
		}
	}
	private class Team2SwitchPositionsFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2SwitchPositions");
		}
	}
	private class Team1SkunkFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team1Skunk");
		}
	}
	private class Team2SkunkFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Team2Skunk");
		}
	}
	private class StartMatchFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("StartMatch");
		}
	}
	private class StartGameFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("StartGame");
		}
	}
	private class SwitchSidesFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("SwitchSides");
		}
	}
	private class MeatballFilterListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			activateFilter("Meatball");
		}
	}
	private class TeamPropertyListener implements PropertyChangeListener{
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			String name = e.getPropertyName();
			if (name.equals("Team1Score")) {
//				System.out.println("Team1 new Score: " + e.getNewValue().toString());
//				System.out.println("Team1 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team2Score")) {
//				System.out.println("Team2 new Score: " + e.getNewValue().toString());
//				System.out.println("Team2 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team3Score")) {
//				System.out.println("Team3 new Score: " + e.getNewValue().toString());
//				System.out.println("Team3 old Score: " + e.getOldValue().toString());
			} else if (name.equals("Team1Game")) {
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team2Game")) {
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team3Game")) {
				setTeamGameCountVisible(name, e.getNewValue().toString());
			} else if (name.equals("Team1Match")) {
//				System.out.println("Team1 new Match: " + e.getNewValue().toString());
//				System.out.println("Team1 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team2Match")) {
//				System.out.println("Team2 new Match: " + e.getNewValue().toString());
//				System.out.println("Team2 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team3Match")) {
//				System.out.println("Team3 new Match: " + e.getNewValue().toString());
//				System.out.println("Team3 old Match: " + e.getOldValue().toString());
			} else if (name.equals("Team1TimeOut")) {
//				System.out.println("Team1 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team1 old TimeOut: " + e.getOldValue().toString());
			} else if (name.equals("Team2TimeOut")) {
//				System.out.println("Team2 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team2 old TimeOut: " + e.getOldValue().toString());
			} else if (name.equals("Team3TimeOut")) {
//				System.out.println("Team3 new TimeOut: " + e.getNewValue().toString());
//				System.out.println("Team3 old TimeOut: " + e.getOldValue().toString());
			}
		}
	}
	public void setTeamGameCountVisible(String name, String value) {
		String teamNumber = ripTeamNumber(name);
		if (value != null) {
			boolean show = false;
			int gameCount = Integer.valueOf(value);
			for (Integer x = 1; x < 4; x++) {
				show = (x <= gameCount) ? true : false;
				showSource(teamGameShowSourcesMap.get(teamNumber+x.toString()), show);
			}
		}
	}
//	private void testProcessCodes() {
//		String codes[] = {"XPSE", "XPSM", "Y5D", "Y3P", "BGS", "B5D", "B3P", "YGS" };
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
		if(OBS.getController() == null ) {
			obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " ERROR! Must connect before setting Scene");	
		} else {
			if (OBS.getScene() != null && !OBS.getScene().isEmpty()) {
				if (OBS.getConnected()) {
					OBS.getController().setCurrentProgramScene(Settings.getOBSParameter("OBSScene"), response -> { 
						if(Settings.getShowParsed()) {
							if (response != null && response.isSuccessful()) {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Scene set to: " + Settings.getOBSParameter("OBSScene"));
							} else {
								obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + ": Unable to set scene to: " + Settings.getOBSParameter("OBSScene"));
							}
						} 
					});
				}else {
					obsConnectPanel.addMessage(dtf.format(LocalDateTime.now()) + " ERROR! Must connect before setting Scene");
				}
			}
		}
	}
	private void createFileWatchWorker() {
		fileWatchWorker = new SwingWorker<Boolean, String>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				String partnerProgramDir = Settings.getPartnerProgramParameter("PartnerProgramPath");
				final String clearString = "XXX_ALREADY_READ_XXX";//= Settings.getPartnerProgramClearString();
				String partnerProgramPlayer1FileName = Settings.getPartnerProgramParameter("Player1FileName");
				String partnerProgramPlayer2FileName = Settings.getPartnerProgramParameter("Player2FileName");
				String partnerProgramPlayer3FileName = Settings.getPartnerProgramParameter("Player3FileName");
				String partnerProgramPlayer4FileName = Settings.getPartnerProgramParameter("Player4FileName");
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
		    		        	logger.error("Overflow in createFileWatchWorker doInBackground.");
		        				continue;
		        			}
		        			if (kind == StandardWatchEventKinds.ENTRY_CREATE || kind == StandardWatchEventKinds.ENTRY_MODIFY) {
		        				@SuppressWarnings("unchecked")
								final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
		        				final Path filePath = watchEventPath.context();
			        			String fileName = filePath.toString();
			        			if (fileName.equals(partnerProgramPlayer1FileName) || fileName.equals(partnerProgramPlayer2FileName) || fileName.equals(partnerProgramPlayer3FileName) || fileName.equals(partnerProgramPlayer4FileName)) {
				        			try {
				        				File file = new File(partnerProgramDir + "\\" + fileName);
				        				Scanner fileReader = new Scanner(file);
				        				if (fileReader.hasNextLine()) {
				        					String data = fileReader.nextLine();
				        					if (!data.equals(clearString)) {
				        						FileWriter fileWriter = new FileWriter(file);
				        						fileWriter.write(clearString);
				        						fileWriter.close();
				        						publish(fileName + "=" + data);
				        					}
				        				}
				        				fileReader.close();
				        			} catch (FileNotFoundException e) {
				    		        	logger.error(e.toString());
				        			} catch (Exception ee) {
				    		        	logger.error(ee.toString());
				        			}
			        			}
		        			}
		        		}
			        	boolean valid = watchKey.reset();
			        	if (!valid) {
			        		logger.error("watchKey wasn\'t valid so made a break for it in Main.doInBackground().");
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
			     logger.info("Worker completed with isConnected: " + status);
			    } catch (InterruptedException e) {
		        	logger.error(e.toString());
			    } catch (ExecutionException e) {
		        	logger.error(e.toString());
			    }
			}
			@Override
			protected void process(List<String> chunks) {
				if (isCancelled()) return;
				for (String value : chunks) {
					String partnerProgramPlayer1FileName = Settings.getPartnerProgramParameter("Player1FileName");
					String partnerProgramPlayer2FileName = Settings.getPartnerProgramParameter("Player2FileName");
					String partnerProgramPlayer3FileName = Settings.getPartnerProgramParameter("Player3FileName");
					String partnerProgramPlayer4FileName = Settings.getPartnerProgramParameter("Player4FileName");
					String newName;
					String[] pieces = value.split("=");
					if (pieces.length == 1) {
						newName = "";
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
						streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Auto Start Match from Name Change: " + teamController.getForwardName(1) + "/" + teamController.getGoalieName(1) + " vs " + teamController.getForwardName(2) + "/" + teamController.getGoalieName(2) + "\r\n");
					}
				}
			}
		};
	}

	public void updateGameResults(StringBuilder gameResults) {
		matchController.updateGameResults(gameResults);
		
	}
}
