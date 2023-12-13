/**
Copyright 2020-2024 Hugh Garner
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

//import net.twasi.obsremotejava.OBSRemoteController;
import io.obswebsocket.community.client.OBSRemoteController;

public class OBS {
	private static OBS instance = new OBS();
	private String host;
	private String port;
	private String password;
	private String scene;
	private OBSRemoteController controller;
	private Boolean savePassword;
	private Boolean autoLogin;
	private Boolean updateOnConnect;
	private Boolean closeOnConnect;
	private Boolean connected;
	private String message;
	private String currentScene;

	private OBS() {}
	
	public static OBS getInstance() {
		return instance;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public String getScene() {
		return scene;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public OBSRemoteController getController() {
		return controller;
	}
	public void setController(OBSRemoteController controller) {
		this.controller = controller;
	}
	public Boolean getSavePassword() {
		return savePassword;
	}
	public void setSavePassword(Boolean savePassword) {
		this.savePassword = savePassword;
	}
	public Boolean getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(Boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	public Boolean getUpdateOnConnect() {
		return updateOnConnect;
	}
	public void setUpdateOnConnect(Boolean updateOnConnect) {
		this.updateOnConnect = updateOnConnect;
	}
	public Boolean getCloseOnConnect() {
		return closeOnConnect;
	}
	public void setCloseOnConnect(Boolean closeOnConnect) {
		this.closeOnConnect = closeOnConnect;
	}
	public Boolean getConnected() {
		return connected;
	}
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCurrentScene() {
		return currentScene;
	}
	public void setCurrentScene(String currentScene) {
		this.currentScene = currentScene;
	}	
}
