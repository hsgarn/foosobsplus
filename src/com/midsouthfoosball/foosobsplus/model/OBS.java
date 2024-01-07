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
package com.midsouthfoosball.foosobsplus.model;

import io.obswebsocket.community.client.OBSRemoteController;

public final class OBS {
	private final static OBS instance = new OBS();
	private static String host;
	private static String port;
	private static String password;
	private static String scene;
	private static OBSRemoteController controller;
	transient private static Boolean connected;
	transient private static String currentScene;
	private OBS() {}
	public static OBS getInstance() {
		return instance;
	}
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		OBS.host = host;
	}
	public static String getPort() {
		return port;
	}
	public static String getScene() {
		return scene;
	}
	public static void setPort(String port) {
		OBS.port = port;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		OBS.password = password;
	}
	public static void setScene(String scene) {
		OBS.scene = scene;
	}
	public static OBSRemoteController getController() {
		return controller;
	}
	public static void setController(OBSRemoteController controller) {
		OBS.controller = controller;
	}
	public static Boolean getConnected() {
		return connected;
	}
	public static void setConnected(Boolean connected) {
		OBS.connected = connected;
	}
	public static String getCurrentScene() {
		return currentScene;
	}
	public static void setCurrentScene(String currentScene) {
		OBS.currentScene = currentScene;
	}
}
