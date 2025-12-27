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

import java.util.HashMap;

/**
 * Callback interface for OBS Manager to communicate with the UI layer.
 * This decouples OBS operations from specific UI implementations.
 */
public interface OBSUICallback {

    /**
     * Called when OBS connection status changes.
     * @param connected true if connected, false if disconnected
     */
    void onConnectionStatusChanged(boolean connected);

    /**
     * Called to add a message to the OBS connection message log.
     * @param message the message to display
     */
    void onMessage(String message);

    /**
     * Called when the monitor list is fetched from OBS.
     * @param monitorMap map of monitor index to monitor name
     */
    void onMonitorListFetched(HashMap<Integer, String> monitorMap);

    /**
     * Called when the scene list is fetched from OBS.
     * @param sceneMap map of scene index to scene name
     */
    void onSceneListFetched(HashMap<Integer, String> sceneMap);

    /**
     * Called when ShowTimer source state changes.
     * @param visible true if visible, false otherwise
     */
    void onShowTimerStateChanged(boolean visible);

    /**
     * Called when ShowScores source state changes.
     * @param visible true if visible, false otherwise
     */
    void onShowScoresStateChanged(boolean visible);

    /**
     * Called when ShowCutthroat source state changes.
     * @param visible true if visible, false otherwise
     */
    void onShowCutthroatStateChanged(boolean visible);

    /**
     * Called when the OBS connect window should be closed (after successful connect with CloseOnConnect setting).
     */
    void onCloseConnectWindow();

    /**
     * Called to display an error message dialog to the user.
     * @param title the dialog title
     * @param message the error message
     */
    void showErrorDialog(String title, String message);
}
