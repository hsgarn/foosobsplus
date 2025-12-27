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
package com.midsouthfoosball.foosobsplus.obs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.view.BallPanel;
import com.midsouthfoosball.foosobsplus.view.Messages;

import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.message.event.inputs.InputActiveStateChangedEvent;
import io.obswebsocket.community.client.message.event.scenes.CurrentProgramSceneChangedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemCreatedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemEnableStateChangedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemListReindexedEvent;
import io.obswebsocket.community.client.message.event.sceneitems.SceneItemRemovedEvent;
import io.obswebsocket.community.client.message.response.general.GetVersionResponse;
import io.obswebsocket.community.client.message.response.sources.GetSourceActiveResponse;
import io.obswebsocket.community.client.message.response.sceneitems.GetSceneItemIdResponse;
import io.obswebsocket.community.client.message.response.ui.GetMonitorListResponse;
import io.obswebsocket.community.client.model.Monitor;
import io.obswebsocket.community.client.model.Scene;
import io.obswebsocket.community.client.WebSocketCloseCode;

/**
 * Manages all OBS (Open Broadcaster Software) interactions.
 * Centralizes OBS connection, scene/source operations, and ball visualization.
 * Decouples OBS logic from the Main class for better maintainability.
 */
public class OBSManager {

    private static final Logger logger = LoggerFactory.getLogger(OBSManager.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //$NON-NLS-1$
    private static final String ON = "1"; //$NON-NLS-1$

    private final OBSUICallback uiCallback;
    private final List<OBSConnectionListener> connectionListeners;
    private BallPanel ballPanel;

    // Ball maps for tracking ball visibility states
    private final HashMap<String, Boolean> allBallsMap;
    private final HashMap<String, Boolean> nineBallsMap;

    // Callbacks for timer window and data updates
    private Consumer<Boolean> timerWindowCallback;
    private Runnable updateOnConnectCallback;

    /**
     * Creates a new OBSManager with the specified UI callback.
     * @param uiCallback callback interface for UI updates
     */
    public OBSManager(OBSUICallback uiCallback) {
        this.uiCallback = uiCallback;
        this.connectionListeners = new ArrayList<>();
        this.allBallsMap = new HashMap<>();
        this.nineBallsMap = new HashMap<>();
        initializeBallMaps();
    }

    /**
     * Sets the BallPanel reference for ball visualization.
     * @param ballPanel the ball panel
     */
    public void setBallPanel(BallPanel ballPanel) {
        this.ballPanel = ballPanel;
    }

    /**
     * Sets the callback for timer window visibility changes.
     * @param callback consumer that accepts visibility state
     */
    public void setTimerWindowCallback(Consumer<Boolean> callback) {
        this.timerWindowCallback = callback;
    }

    /**
     * Sets the callback to execute when update on connect setting is enabled.
     * @param callback runnable to execute
     */
    public void setUpdateOnConnectCallback(Runnable callback) {
        this.updateOnConnectCallback = callback;
    }

    /**
     * Adds a connection listener to be notified of OBS connection events.
     * @param listener the listener to add
     */
    public void addConnectionListener(OBSConnectionListener listener) {
        connectionListeners.add(listener);
    }

    /**
     * Removes a connection listener.
     * @param listener the listener to remove
     */
    public void removeConnectionListener(OBSConnectionListener listener) {
        connectionListeners.remove(listener);
    }

    /**
     * Initializes the ball visibility maps for 9-ball and full rack games.
     */
    private void initializeBallMaps() {
        // All balls (16 ball pool)
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

        // 9-ball setup (only cue through 9)
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

    // ==================== Connection Management ====================

    /**
     * Builds and configures the OBS remote controller.
     */
    public void buildController() {
        if (OBS.getController() != null) {
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
            .onReady(this::onReady)
            .onDisconnect(this::onDisconnect)
            .onClose(this::onClose)
            .onControllerError(reason -> this.onControllerError(reason.getReason()))
            .onCommunicatorError(reason -> this.onCommunicationError(reason.getReason()))
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

    /**
     * Connects to OBS using the current connection settings.
     */
    public void connect() {
        logger.info("Trying to connect to OBS..."); //$NON-NLS-1$
        buildController();
        OBS.getController().connect();
    }

    /**
     * Disconnects from OBS.
     */
    public void disconnect() {
        if (OBS.getController() != null) {
            addMessage(Messages.getString("Main.RequestingDisconnect")); //$NON-NLS-1$
            OBS.getController().stop();
        }
    }

    /**
     * Checks if currently connected to OBS.
     * @return true if connected
     */
    public boolean isConnected() {
        return OBS.getConnected() != null && OBS.getConnected();
    }

    /**
     * Updates the connection status and notifies UI.
     */
    private void updateConnected() {
        OBS.setConnected(true);
        uiCallback.onConnectionStatusChanged(true);
        if (Settings.getOBSParameter("OBSUpdateOnConnect").equals(ON) && updateOnConnectCallback != null) { //$NON-NLS-1$
            updateOnConnectCallback.run();
        }
    }

    /**
     * Updates the disconnection status and notifies UI.
     */
    private void updateDisconnected() {
        OBS.setConnected(false);
        uiCallback.onConnectionStatusChanged(false);
    }

    // ==================== Connection Callbacks ====================

    private void onReady() {
        updateConnected();
        addMessage(Messages.getString("Main.OBSReady")); //$NON-NLS-1$
        addMessage(Messages.getString("Main.OBSControllerConnected")); //$NON-NLS-1$

        // Get and display OBS version info
        OBS.getController().getVersion((GetVersionResponse versionInfo) -> {
            if (versionInfo != null && versionInfo.isSuccessful()) {
                addMessage(Messages.getString("Main.OBSVersion") + versionInfo.getObsVersion()); //$NON-NLS-1$
                addMessage(Messages.getString("Main.WebsocketVersion") + versionInfo.getObsWebSocketVersion()); //$NON-NLS-1$
            }
        });

        // Set the main scene
        String sceneName = Settings.getOBSParameter("OBSMainScene"); //$NON-NLS-1$
        OBS.getController().setCurrentProgramScene(sceneName, response -> {
            if (response != null && response.isSuccessful()) {
                OBS.setCurrentScene(sceneName);
                if (Settings.getShowParsed()) {
                    addMessage(Messages.getString("Main.SceneSetTo") + sceneName); //$NON-NLS-1$
                }
            } else {
                String msg = Messages.getString("Main.UnableToSetSceneTo") + sceneName; //$NON-NLS-1$
                logger.warn(msg);
                if (Settings.getShowParsed()) {
                    addMessage(msg);
                }
            }
        });

        // Check and set source active states
        checkAndSetSourceActive("ShowTimer", uiCallback::onShowTimerStateChanged, timerWindowCallback);
        checkAndSetSourceActive("ShowScores", uiCallback::onShowScoresStateChanged, null);
        checkAndSetSourceActive("ShowCutthroat", uiCallback::onShowCutthroatStateChanged, null);

        // Close connect window if setting is enabled
        if (Settings.getOBSParameter("OBSCloseOnConnect").equals(ON)) { //$NON-NLS-1$
            uiCallback.onCloseConnectWindow();
        }

        // Fetch monitor and scene lists
        fetchMonitorList();
        fetchSceneList();

        // Notify listeners
        for (OBSConnectionListener listener : connectionListeners) {
            listener.onConnected();
        }
    }

    private void onDisconnect() {
        updateDisconnected();
        addMessage(Messages.getString("Main.OBSControllerDisconnected")); //$NON-NLS-1$
        for (OBSConnectionListener listener : connectionListeners) {
            listener.onDisconnected();
        }
    }

    private void onClose(WebSocketCloseCode webSocketCloseCode) {
        updateDisconnected();
        addMessage(Messages.getString("Main.OBSControllerClosed") + webSocketCloseCode.getCode()); //$NON-NLS-1$
        for (OBSConnectionListener listener : connectionListeners) {
            listener.onDisconnected();
        }
    }

    private void onControllerError(String reason) {
        addMessage(Messages.getString("Main.ControllerError") + reason); //$NON-NLS-1$
        for (OBSConnectionListener listener : connectionListeners) {
            listener.onConnectionFailed(reason);
        }
    }

    private void onCommunicationError(String reason) {
        addMessage(Messages.getString("Main.CommunicationError") + reason); //$NON-NLS-1$
        for (OBSConnectionListener listener : connectionListeners) {
            listener.onCommunicationError(reason);
        }
    }

    // ==================== Event Handlers ====================

    private void checkSceneItemCreated(SceneItemCreatedEvent event) {
        loadSceneItemMap();
    }

    private void checkSceneItemRemoved(SceneItemRemovedEvent event) {
        loadSceneItemMap();
    }

    private void checkSceneItemListReindexed(SceneItemListReindexedEvent event) {
        loadSceneItemMap();
    }

    private void loadSceneItemMap() {
        // Placeholder for scene item map loading if needed
    }

    private void checkCurrentProgramSceneChange(CurrentProgramSceneChangedEvent event) {
        boolean showParsed = Settings.getShowParsed();
        String sceneName = event.getSceneName();
        if (sceneName != null && !sceneName.isEmpty()) {
            OBS.setCurrentScene(sceneName);
            if (showParsed) {
                addMessage(Messages.getString("Main.OBSSceneChangedTo") + sceneName + "."); //$NON-NLS-1$ //$NON-NLS-2$
            }
        } else {
            if (showParsed) {
                addMessage(Messages.getString("Main.OBSCheckCurrentProgramSceneChangeFailed")); //$NON-NLS-1$
            }
        }
    }

    private void checkItemEnableStateChange(SceneItemEnableStateChangedEvent event) {
        boolean showParsed = Settings.getShowParsed();
        String sceneName = event.getSceneName();
        Number itemId = event.getSceneItemId();
        boolean show = event.getSceneItemEnabled();
        checkItemEnableStateChangeHelper(sceneName, show, Settings.getSourceParameter("ShowScores"), itemId, showParsed); //$NON-NLS-1$
        checkItemEnableStateChangeHelper(sceneName, show, Settings.getSourceParameter("ShowCutthroat"), itemId, showParsed); //$NON-NLS-1$
    }

    private void checkItemEnableStateChangeHelper(String sceneName, boolean show, String sourceToCheck, Number itemId, boolean showParsed) {
        OBS.getController().getSceneItemId(sceneName, sourceToCheck, null, getSceneItemIdResponse -> {
            if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
                if (getSceneItemIdResponse.getSceneItemId().toString().equals(itemId.toString())) {
                    if (sourceToCheck.equals(Settings.getSourceParameter("ShowScores"))) { //$NON-NLS-1$
                        uiCallback.onShowScoresStateChanged(show);
                    } else if (sourceToCheck.equals(Settings.getSourceParameter("ShowCutthroat"))) { //$NON-NLS-1$
                        uiCallback.onShowCutthroatStateChanged(show);
                    }
                    if (showParsed) {
                        addMessage(": OBS " + sourceToCheck + Messages.getString("Main.EnableStateChangeReceived") + show + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    }
                }
            } else {
                if (showParsed) {
                    addMessage(Messages.getString("Main.OBSEnableStateChangeFailed")); //$NON-NLS-1$
                }
            }
        });
    }

    private void checkActiveStateChange(InputActiveStateChangedEvent event) {
        String name = event.getInputName();
        if (!Settings.getSourceParameter("ShowTimer").isEmpty() && name.equals(Settings.getSourceParameter("ShowTimer"))) { //$NON-NLS-1$ //$NON-NLS-2$
            boolean show = event.getVideoActive();
            uiCallback.onShowTimerStateChanged(show);
            if (timerWindowCallback != null) {
                timerWindowCallback.accept(show);
            }
        } else if (!Settings.getSourceParameter("ShowScores").isEmpty() && name.equals(Settings.getSourceParameter("ShowScores"))) { //$NON-NLS-1$ //$NON-NLS-2$
            boolean show = event.getVideoActive();
            uiCallback.onShowScoresStateChanged(show);
        } else if (!Settings.getSourceParameter("ShowCutthroat").isEmpty() && name.equals(Settings.getSourceParameter("ShowCutthroat"))) { //$NON-NLS-1$ //$NON-NLS-2$
            boolean show = event.getVideoActive();
            uiCallback.onShowCutthroatStateChanged(show);
        }
    }

    private void checkAndSetSourceActive(String settingKey, Consumer<Boolean> setter, Consumer<Boolean> additionalAction) {
        String sourceName = Settings.getSourceParameter(settingKey);
        if (sourceName == null || sourceName.isEmpty()) {
            String msg = "Source [" + settingKey + "] not defined in Settings.";
            logger.warn(msg);
            addMessage(msg);
            setter.accept(false);
            if (additionalAction != null) {
                additionalAction.accept(false);
            }
            return;
        }
        OBS.getController().getSourceActive(sourceName, response -> {
            try {
                if (response != null && response.isSuccessful()) {
                    boolean isActive = response.getVideoActive();
                    setter.accept(isActive);
                    if (additionalAction != null) {
                        additionalAction.accept(isActive);
                    }
                } else {
                    String msg = "Source [" + sourceName + "] not found in OBS";
                    logger.warn(msg);
                    addMessage(msg);
                    setter.accept(false);
                    if (additionalAction != null) {
                        additionalAction.accept(false);
                    }
                }
            } catch (Exception e) {
                String msg = "Exception for Source [" + sourceName + "]: " + e.toString();
                logger.warn(msg);
                addMessage(msg);
                setter.accept(false);
                if (additionalAction != null) {
                    additionalAction.accept(false);
                }
            }
        });
    }

    // ==================== Scene/Source Operations ====================

    /**
     * Fetches the list of monitors from OBS.
     */
    public void fetchMonitorList() {
        if (!isConnected()) return;

        OBS.getController().getMonitorList((GetMonitorListResponse response) -> {
            if (Settings.getShowParsed()) {
                addMessage(Messages.getString("Main.FetchingMonitors")); //$NON-NLS-1$
            }
            if (response != null && response.isSuccessful()) {
                List<Monitor> monitors = response.getMonitors();
                HashMap<Integer, String> monitorMap = new HashMap<>();
                for (Monitor monitor : monitors) {
                    monitorMap.put(monitor.getMonitorIndex(), monitor.getMonitorName());
                }
                uiCallback.onMonitorListFetched(monitorMap);
                if (Settings.getShowParsed()) {
                    addMessage(Messages.getString("Main.MonitorsFetched")); //$NON-NLS-1$
                }
            } else {
                String msg = Messages.getString("Errors.Main.FetchMonitorError"); //$NON-NLS-1$
                String ttl = Messages.getString("Errors.Main.FetchMonitor.Title"); //$NON-NLS-1$
                logger.warn(msg);
                uiCallback.showErrorDialog(ttl, msg);
            }
        });
    }

    /**
     * Fetches the list of scenes from OBS.
     */
    public void fetchSceneList() {
        if (!isConnected()) return;

        OBS.getController().getSceneList(response -> {
            if (response != null && response.isSuccessful()) {
                List<Scene> scenes = response.getScenes();
                HashMap<Integer, String> sceneMap = new HashMap<>();
                for (Scene scene : scenes) {
                    sceneMap.put(scene.getSceneIndex(), scene.getSceneName());
                }
                uiCallback.onSceneListFetched(sceneMap);
            } else {
                String msg = Messages.getString("Errors.Main.FetchSourceError"); //$NON-NLS-1$
                String ttl = Messages.getString("Errors.Main.FetchSource.Title"); //$NON-NLS-1$
                logger.warn(msg);
                uiCallback.showErrorDialog(ttl, msg);
            }
        });
    }

    /**
     * Activates (switches to) the specified scene.
     * @param sceneName the scene to activate
     */
    public void activateScene(String sceneName) {
        if (!isConnected()) {
            addMessage(Messages.getString("Main.ErrorMustConnectBeforeActivatingScene")); //$NON-NLS-1$
            return;
        }
        OBS.getController().setCurrentProgramScene(sceneName, response -> {
            if (response != null && response.isSuccessful()) {
                addMessage(Messages.getString("Main.Scene") + sceneName + Messages.getString("Main.Activated")); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                addMessage(Messages.getString("Main.UnableToActivateScene") + sceneName); //$NON-NLS-1$
            }
        });
    }

    /**
     * Projects a source to a monitor.
     * @param sceneName the scene name
     * @param monitorIndex the monitor index
     */
    public void projectSource(String sceneName, Number monitorIndex) {
        if (!isConnected()) return;

        if (monitorIndex.intValue() != -1) {
            if (sceneName == null || sceneName.isEmpty()) {
                sceneName = OBS.getMainScene();
            }
            OBS.getController().openSourceProjector(sceneName, monitorIndex, null, response -> {
                if (!response.isSuccessful()) {
                    String msg = Messages.getString("Errors.Main.ProjectError"); //$NON-NLS-1$
                    String ttl = Messages.getString("Errors.Main.Project.Title"); //$NON-NLS-1$
                    logger.warn(msg);
                    uiCallback.showErrorDialog(ttl, msg);
                }
            });
        }
    }

    /**
     * Shows or hides a source in OBS.
     * @param source the source name (may include scene,source format)
     * @param show true to show, false to hide
     */
    public void showSource(String source, boolean show) {
        if (source == null || source.isEmpty()) return;
        if (!isConnected()) return;

        String sceneName;
        String sourceItem;
        boolean showParsed = Settings.getShowParsed();

        if (source.contains(",")) { //$NON-NLS-1$
            String[] parts = source.split(","); //$NON-NLS-1$
            sceneName = parts[0];
            sourceItem = parts[1];
            if (showParsed) {
                addMessage(Messages.getString("Main.OBSSetSceneItemEnabledCalled") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            OBS.getController().getSceneItemId(sceneName, sourceItem, null, (GetSceneItemIdResponse response) -> {
                if (response != null && response.isSuccessful()) {
                    OBS.getController().setSceneItemEnabled(sceneName, response.getSceneItemId(), show, setResponse -> {
                        if (showParsed) {
                            addMessage(Messages.getString("Main.OBSSetSceneItemEnabledResponseObtained") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        }
                    });
                } else {
                    if (showParsed) {
                        addMessage(Messages.getString("Main.OBSSetSceneItemEnabledFailed") + sceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    }
                }
            });
        } else {
            sourceItem = source;
            sceneName = OBS.getCurrentScene();
            if (showParsed) {
                addMessage(Messages.getString("Main.OBSSetSceneItemEnabledCalled") + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$
            }
            OBS.getController().getSceneItemId(sceneName, sourceItem, null, getSceneItemIdResponse -> {
                if (getSceneItemIdResponse != null && getSceneItemIdResponse.isSuccessful()) {
                    final String finalSceneName = sceneName;
                    OBS.getController().setSceneItemEnabled(sceneName, getSceneItemIdResponse.getSceneItemId(), show, setResponse -> {
                        if (showParsed) {
                            addMessage(Messages.getString("Main.OBSSetSceneItemEnabledResponseObtained") + finalSceneName + ", " + sourceItem + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        }
                    });
                }
            });
        }
    }

    /**
     * Sets the visibility of a source filter.
     * @param source the source name
     * @param filter the filter name
     * @param show true to enable, false to disable
     */
    public void setSourceFilterVisibility(String source, String filter, boolean show) {
        if (source == null || filter == null) return;
        if (!isConnected()) return;

        boolean showParsed = Settings.getShowParsed();
        if (showParsed) {
            addMessage(Messages.getString("Main.OBSSetSourceFilterEnabledCalled") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        OBS.getController().setSourceFilterEnabled(source, filter, show, response -> {
            if (response != null && response.isSuccessful()) {
                if (showParsed) {
                    addMessage(Messages.getString("Main.OBSSetSourceFilterEnabledResponseObtained") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                }
            } else {
                if (showParsed) {
                    addMessage(Messages.getString("Main.OBSSetSourceFilterEnabledFailed") + source + ", " + filter + ", " + show); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                }
            }
        });
    }

    // ==================== Ball Visualization ====================

    /**
     * Sets visibility of a ball source in OBS.
     * @param sourceName the ball source name
     * @param show true to show, false to hide
     */
    private void setBallVisible(String sourceName, boolean show) {
        if (!isConnected()) return;

        OBS.getController().getSourceActive(sourceName, (GetSourceActiveResponse response) -> {
            if (response != null && response.isSuccessful()) {
                showSource(sourceName, show);
            } else {
                String msg = "Source [" + sourceName + "] not found in OBS";
                logger.warn(msg);
                addMessage(msg);
            }
        });
    }

    /**
     * Syncs all ball visibility states between UI and OBS.
     */
    public void syncBalls() {
        if (!isConnected() || ballPanel == null) return;

        allBallsMap.forEach((ball, show) -> {
            setBallVisible(ball + "Ball", !ballPanel.getBallSelectedState(ball));
        });
    }

    /**
     * Resets balls to 9-ball configuration.
     */
    public void resetNineBall() {
        nineBallsMap.forEach((ball, show) -> {
            if (isConnected()) {
                setBallVisible(ball + "Ball", show);
            }
            if (ballPanel != null) {
                ballPanel.setBallSelected(ball, !show);
            }
        });
    }

    /**
     * Shows all balls (full rack).
     */
    public void showAllBalls() {
        allBallsMap.forEach((ball, show) -> {
            if (isConnected()) {
                setBallVisible(ball + "Ball", show);
            }
            if (ballPanel != null) {
                ballPanel.setBallSelected(ball, !show);
            }
        });
    }

    /**
     * Hides all balls.
     */
    public void hideAllBalls() {
        allBallsMap.forEach((ball, show) -> {
            if (isConnected()) {
                setBallVisible(ball + "Ball", !show);
            }
            if (ballPanel != null) {
                ballPanel.setBallSelected(ball, show);
            }
        });
    }

    /**
     * Toggles a specific ball's visibility.
     * @param ballName the ball name
     * @param show true to show, false to hide
     */
    public void toggleBall(String ballName, boolean show) {
        if (isConnected()) {
            setBallVisible(ballName + "Ball", show);
        }
        if (ballPanel != null) {
            ballPanel.setBallSelected(ballName, !show);
        }
    }

    // ==================== Utility Methods ====================

    /**
     * Adds a timestamped message to the UI.
     * @param message the message to add
     */
    private void addMessage(String message) {
        uiCallback.onMessage(dtf.format(LocalDateTime.now()) + " " + message);
    }

    /**
     * Gets the all balls map.
     * @return map of ball names to visibility states
     */
    public HashMap<String, Boolean> getAllBallsMap() {
        return allBallsMap;
    }

    /**
     * Gets the nine balls map.
     * @return map of ball names to visibility states for 9-ball
     */
    public HashMap<String, Boolean> getNineBallsMap() {
        return nineBallsMap;
    }
}
