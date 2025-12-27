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

/**
 * Listener interface for OBS connection state changes.
 * Allows multiple components to be notified of OBS connection events.
 */
public interface OBSConnectionListener {

    /**
     * Called when successfully connected to OBS.
     */
    void onConnected();

    /**
     * Called when disconnected from OBS.
     */
    void onDisconnected();

    /**
     * Called when connection to OBS fails.
     * @param reason the reason for the failure
     */
    void onConnectionFailed(String reason);

    /**
     * Called when a communication error occurs with OBS.
     * @param reason the reason for the error
     */
    void onCommunicationError(String reason);
}
