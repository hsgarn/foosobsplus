package com.midsouthfoosball.foosobsplus.model;

import net.twasi.obsremotejava.OBSRemoteController;

public class OBS {
	private static OBS instance = new OBS();
	private String host;
	private String port;
	private String password;
	private OBSRemoteController controller;
	private Boolean savePassword;
	private Boolean autoLogin;
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
	public void setPort(String port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
