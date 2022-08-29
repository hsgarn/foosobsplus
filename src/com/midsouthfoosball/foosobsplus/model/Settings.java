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
package com.midsouthfoosball.foosobsplus.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
	
// Parameter settings
	private String separator 			= FileSystems.getDefault().getSeparator();
	private String[] lastScoredStrings 	= new String[3];

	// Property Settings
	private Properties defaultControlProps;
	private Properties defaultSourceProps;
	private Properties defaultFileNameProps;
	private Properties defaultHotKeyProps;
	private Properties defaultOBSProps;
	private Properties defaultAutoScoreSettingsProps;
	private Properties defaultAutoScoreConfigProps;
	
	public Properties configControlProps;
	public Properties configSourceProps;
	public Properties configFileNameProps;
	public Properties configHotKeyProps;
	public Properties configOBSProps;
	public Properties configAutoScoreSettingsProps;
	public Properties configAutoScoreConfigProps;
	
	private String configControlFileName 			= "control.properties";
	private String configSourceFileName 			= "source.properties";
	private String configFileNameFileName 			= "filename.properties";
	private String configHotKeyFileName 			= "hotkey.properties";
	private String configOBSFileName        		= "obs.properties";
	private String configAutoScoreSettingsFileName	= "autoscoresettings.properties";
	private String configAutoScoreConfigFileName	= "autoscoreconfig.properties";

	//////////////////////////////////////////////////////
	
	public Settings() throws IOException {
		defaultControlProps 			= new Properties();
		defaultSourceProps 				= new Properties();
		defaultFileNameProps 			= new Properties();
		defaultHotKeyProps 				= new Properties();
		defaultOBSProps         		= new Properties();
		defaultAutoScoreSettingsProps	= new Properties();
		defaultAutoScoreConfigProps		= new Properties();
		// sets default properties
		// Parameter settings
		defaultControlProps.setProperty("ShowParsed", "true");
		defaultControlProps.setProperty("GameType", "Foosball");
		defaultControlProps.setProperty("TableName", "");
		defaultControlProps.setProperty("datapath", "c:" + separator + "temp");
		defaultControlProps.setProperty("PointsToWin", "5");
		defaultControlProps.setProperty("MaxWin", "8");
		defaultControlProps.setProperty("WinBy", "1");
		defaultControlProps.setProperty("GamesToWin", "2");
		defaultControlProps.setProperty("MaxTimeOuts", "2");
		defaultControlProps.setProperty("AutoIncrementGame", "1");
		defaultControlProps.setProperty("AnnounceWinner", "1");
		defaultControlProps.setProperty("AnnounceMeatball", "1");
		defaultControlProps.setProperty("WinnerPrefix", "Match Winner: ");
		defaultControlProps.setProperty("WinnerSuffix", "!!!");
		defaultControlProps.setProperty("Meatball", "Meatball!!!");
		defaultControlProps.setProperty("Team1LastScored", "<--- Last Scored");
		defaultControlProps.setProperty("Team2LastScored", "Last Scored --->");
		defaultControlProps.setProperty("ClearLastScored", "Last Scored");
		defaultControlProps.setProperty("Side1Color", "Yellow");
		defaultControlProps.setProperty("Side2Color", "Black");
		defaultControlProps.setProperty("NameSeparator","/");
		defaultControlProps.setProperty("ShotTime", "15");
		defaultControlProps.setProperty("PassTime", "10");
		defaultControlProps.setProperty("TimeOutTime", "30");
		defaultControlProps.setProperty("GameTime", "90");
		defaultControlProps.setProperty("RecallTime", "10");
		defaultControlProps.setProperty("ShowTimeOutsUsed", "1");
		defaultControlProps.setProperty("AutoCapNames",  "1");
		defaultControlProps.setProperty("WinByFinalOnly", "1");
		defaultControlProps.setProperty("ShowSkunk",  "1");
		defaultControlProps.setProperty("CutThroatMode", "0");
		defaultControlProps.setProperty("LogoImageURL", "/imgs/MidsouthFoosballLogo4.png");
		defaultControlProps.setProperty("LogoLinkURI", "https://www.facebook.com/midsouthfoosball");
		//OBS
		defaultOBSProps.setProperty("OBSHost", "localhost");
		defaultOBSProps.setProperty("OBSPort", "4444");
		defaultOBSProps.setProperty("OBSPassword","");
		defaultOBSProps.setProperty("OBSScene", "FoosObs+ Main");
		defaultOBSProps.setProperty("OBSAutoLogin", "0");
		defaultOBSProps.setProperty("OBSSavePassword", "0");
		defaultOBSProps.setProperty("OBSCloseOnConnect", "1");
		defaultOBSProps.setProperty("OBSUpdateOnConnect",  "1");
		defaultOBSProps.setProperty("OBSScoreSource", "ScoresAndLabels");
		defaultOBSProps.setProperty("OBSTimerSource", "Foos OBS+ Timer");
		defaultOBSProps.setProperty("OBSSkunkFilter", "Skunk 2");
		//Sources
		defaultSourceProps.setProperty("Table", "tablename");
		defaultSourceProps.setProperty("Team1Name", "team1name");
		defaultSourceProps.setProperty("Team1Forward", "team1forward");
		defaultSourceProps.setProperty("Team1Goalie", "team1goalie");
		defaultSourceProps.setProperty("Tournament", "tournament");
		defaultSourceProps.setProperty("Team2Name", "team2name");
		defaultSourceProps.setProperty("Team2Forward", "team2forward");
		defaultSourceProps.setProperty("Team2Goalie", "team2goalie");
		defaultSourceProps.setProperty("Event", "event");
		defaultSourceProps.setProperty("TableName", "tablename");
		defaultSourceProps.setProperty("GameCount1", "gamecount1");
		defaultSourceProps.setProperty("TimeRemaining", "timeremaining");
		defaultSourceProps.setProperty("GameCount2", "gamecount2");
		defaultSourceProps.setProperty("TimerInUse", "timerinuse");
		defaultSourceProps.setProperty("Score1", "score1");
		defaultSourceProps.setProperty("MatchWinner", "matchwinner");
		defaultSourceProps.setProperty("Meatball",  "meatball");
		defaultSourceProps.setProperty("Score2", "score2");
		defaultSourceProps.setProperty("TimeOut1", "timeout1");
		defaultSourceProps.setProperty("TimeOut2", "timeout2");
		defaultSourceProps.setProperty("Reset1", "reset1");
		defaultSourceProps.setProperty("Reset2", "reset2");
		defaultSourceProps.setProperty("Warn1", "warn1");
		defaultSourceProps.setProperty("Warn2", "warn2");
		defaultSourceProps.setProperty("LastScored","lastscored");
		defaultSourceProps.setProperty("GameTime", "gametime");
		defaultSourceProps.setProperty("MatchTime", "matchtime");
		defaultSourceProps.setProperty("Stuffs1", "stuffs1");
		defaultSourceProps.setProperty("Stuffs2", "stuffs2");
		defaultSourceProps.setProperty("Breaks1", "breaks1");
		defaultSourceProps.setProperty("Breaks2", "breaks2");
		defaultSourceProps.setProperty("Aces1",  "aces1");
		defaultSourceProps.setProperty("Aces2",  "aces2");
		defaultSourceProps.setProperty("Team1PassAttempts", "team1passattempts");
		defaultSourceProps.setProperty("Team2PassAttempts", "team2passattempts");
		defaultSourceProps.setProperty("Team1PassCompletes", "team1passcompletes");
		defaultSourceProps.setProperty("Team2PassCompletes", "team2passcompletes");
		defaultSourceProps.setProperty("Team1ShotAttempts", "team1shotattempts");
		defaultSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultSourceProps.setProperty("Team1ShotCompletes", "team1shotcompletes");
		defaultSourceProps.setProperty("Team2ShotCompletes", "team2shotcompletes");
		defaultSourceProps.setProperty("Team1ClearAttempts", "team1clearattempts");
		defaultSourceProps.setProperty("Team2ClearAttempts", "team2clearattempts");
		defaultSourceProps.setProperty("Team1ClearCompletes", "team1clearcompletes");
		defaultSourceProps.setProperty("Team2ClearCompletes", "team2clearcompletes");
		defaultSourceProps.setProperty("Team1PassPercent", "team1passpercent");
		defaultSourceProps.setProperty("Team2PassPercent", "team2passpercent");
		defaultSourceProps.setProperty("Team1ShotPercent", "team1shotpercent");
		defaultSourceProps.setProperty("Team2ShotPercent", "team2shotpercent");
		defaultSourceProps.setProperty("Team1ClearPercent", "team1clearpercent");
		defaultSourceProps.setProperty("Team2ClearPercent", "team2clearpercent");
		defaultSourceProps.setProperty("Team1TwoBarPassAttempts", "team1twobarpassattempts");
		defaultSourceProps.setProperty("Team2TwoBarPassAttempts", "team2twobarpassattempts");
		defaultSourceProps.setProperty("Team1TwoBarPassCompletes", "team1twobarpasscompletes");
		defaultSourceProps.setProperty("Team2TwoBarPassCompletes", "team2twobarpasscompletes");
		defaultSourceProps.setProperty("Team1TwoBarPassPercent", "team1twobarpasspercent");
		defaultSourceProps.setProperty("Team2TwoBarPassPercent", "team2twobarpasspercent");
		defaultSourceProps.setProperty("Team1Scoring", "team1scoring");
		defaultSourceProps.setProperty("Team2Scoring", "team2scoring");
		defaultSourceProps.setProperty("Team1ThreeBarScoring", "team1threebarscoring");
		defaultSourceProps.setProperty("Team2ThreeBarScoring", "team2threebarscoring");
		defaultSourceProps.setProperty("Team1FiveBarScoring", "team1fivebarscoring");
		defaultSourceProps.setProperty("Team2FiveBarScoring", "team2fivebarscoring");
		defaultSourceProps.setProperty("Team1TwoBarScoring", "team1twobarscoring");
		defaultSourceProps.setProperty("Team2TwoBarScoring", "team2twobarscoring");
		defaultSourceProps.setProperty("Team1ShotsOnGoal", "team1shotsongoal");
		defaultSourceProps.setProperty("Team2ShotsOnGoal", "team2shotsongoal");
		defaultSourceProps.setProperty("Side1Color","side1color");
		defaultSourceProps.setProperty("Side2Color","side2color");
		//FileNames
		defaultFileNameProps.setProperty("Table", "tablename.txt");
		defaultFileNameProps.setProperty("Team1Name", "team1name.txt");
		defaultFileNameProps.setProperty("Team1Forward", "team1forward.txt");
		defaultFileNameProps.setProperty("Team1Goalie", "team1goalie.txt");
		defaultFileNameProps.setProperty("Tournament", "tournament.txt");
		defaultFileNameProps.setProperty("Team2Name", "team2name.txt");
		defaultFileNameProps.setProperty("Team2Forward", "team2forward.txt");
		defaultFileNameProps.setProperty("Team2Goalie", "team2goalie.txt");
		defaultFileNameProps.setProperty("Event", "event.txt");
		defaultFileNameProps.setProperty("TableName", "tablename.txt");
		defaultFileNameProps.setProperty("GameCount1", "gamecount1.txt");
		defaultFileNameProps.setProperty("TimeRemaining", "timeremaining.txt");
		defaultFileNameProps.setProperty("GameCount2", "gamecount2.txt");
		defaultFileNameProps.setProperty("TimerInUse", "timerinuse.txt");
		defaultFileNameProps.setProperty("Score1", "score1.txt");
		defaultFileNameProps.setProperty("MatchWinner", "matchwinner.txt");
		defaultFileNameProps.setProperty("Meatball",  "meatball.txt");
		defaultFileNameProps.setProperty("Score2", "score2.txt");
		defaultFileNameProps.setProperty("TimeOut1", "timeout1.txt");
		defaultFileNameProps.setProperty("TimeOut2", "timeout2.txt");
		defaultFileNameProps.setProperty("Reset1", "reset1.txt");
		defaultFileNameProps.setProperty("Reset2", "reset2.txt");
		defaultFileNameProps.setProperty("Warn1", "warn1.txt");
		defaultFileNameProps.setProperty("Warn2", "warn2.txt");
		defaultFileNameProps.setProperty("LastScored","lastscored.txt");
		defaultFileNameProps.setProperty("GameTime", "gametime.txt");
		defaultFileNameProps.setProperty("MatchTime", "matchtime.txt");
		defaultFileNameProps.setProperty("Stuffs1", "stuffs1.txt");
		defaultFileNameProps.setProperty("Stuffs2", "stuffs2.txt");
		defaultFileNameProps.setProperty("Breaks1", "breaks1.txt");
		defaultFileNameProps.setProperty("Breaks2", "breaks2.txt");
		defaultFileNameProps.setProperty("Aces1", "aces1.txt");
		defaultFileNameProps.setProperty("Aces2", "aces2.txt");
		defaultFileNameProps.setProperty("Team1PassAttempts", "team1passattempts.txt");
		defaultFileNameProps.setProperty("Team2PassAttempts", "team2passattempts.txt");
		defaultFileNameProps.setProperty("Team1PassCompletes", "team1passcompletes.txt");
		defaultFileNameProps.setProperty("Team2PassCompletes", "team2passcompletes.txt");
		defaultFileNameProps.setProperty("Team1ShotAttempts", "team1shotattempts.txt");
		defaultFileNameProps.setProperty("Team2ShotAttempts", "team2shotattempts.txt");
		defaultFileNameProps.setProperty("Team1ShotCompletes", "team1shotcompletes.txt");
		defaultFileNameProps.setProperty("Team2ShotCompletes", "team2shotcompletes.txt");
		defaultFileNameProps.setProperty("Team1ClearAttempts", "team1clearattempts.txt");
		defaultFileNameProps.setProperty("Team2ClearAttempts", "team2clearattempts.txt");
		defaultFileNameProps.setProperty("Team1ClearCompletes", "team1clearcompletes.txt");
		defaultFileNameProps.setProperty("Team2ClearCompletes", "team2clearcompletes.txt");
		defaultFileNameProps.setProperty("Team1PassPercent", "team1passpercent.txt");
		defaultFileNameProps.setProperty("Team2PassPercent", "team2passpercent.txt");
		defaultFileNameProps.setProperty("Team1ShotPercent", "team1shotpercent.txt");
		defaultFileNameProps.setProperty("Team2ShotPercent", "team2shotpercent.txt");
		defaultFileNameProps.setProperty("Team1ClearPercent", "team1clearpercent.txt");
		defaultFileNameProps.setProperty("Team2ClearPercent", "team2clearpercent.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassAttempts", "team1twobarpassattempts.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassAttempts", "team2twobarpassattempts.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassCompletes", "team1twobarpasscompletes.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassCompletes", "team2twobarpasscompletes.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassPercent", "team1twobarpasspercent.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassPercent", "team2twobarpasspercent.txt");
		defaultFileNameProps.setProperty("Team1Scoring", "team1scoring.txt");
		defaultFileNameProps.setProperty("Team2Scoring", "team2scoring.txt");
		defaultFileNameProps.setProperty("Team1ThreeBarScoring", "team1threebarscoring.txt");
		defaultFileNameProps.setProperty("Team2ThreeBarScoring", "team2threebarscoring.txt");
		defaultFileNameProps.setProperty("Team1FiveBarScoring", "team1fivebarscoring.txt");
		defaultFileNameProps.setProperty("Team2FiveBarScoring", "team2fivebarscoring.txt");
		defaultFileNameProps.setProperty("Team1TwoBarScoring", "team1twobarscoring.txt");
		defaultFileNameProps.setProperty("Team2TwoBarScoring", "team2twobarscoring.txt");
		defaultFileNameProps.setProperty("Team1ShotsOnGoal", "team1shotsongoal.txt");
		defaultFileNameProps.setProperty("Team2ShotsOnGoal", "team2shotsongoal.txt");
		defaultFileNameProps.setProperty("Side1Color","side1color.txt");
		defaultFileNameProps.setProperty("Side2Color","side2color.txt");
		//HotKeys
		defaultHotKeyProps.setProperty("StartMatchHotKey", "");
		defaultHotKeyProps.setProperty("PauseMatchHotKey", "");
		defaultHotKeyProps.setProperty("StartGameHotKey", "");
		defaultHotKeyProps.setProperty("TournamentNameClearHotKey", "");
		defaultHotKeyProps.setProperty("Team1ClearHotKey", "");
		defaultHotKeyProps.setProperty("Team1SwitchPositionsHotKey", "t");
		defaultHotKeyProps.setProperty("Team2ClearHotKey", "");
		defaultHotKeyProps.setProperty("Team2SwitchPositionsHotKey", "m");
		defaultHotKeyProps.setProperty("SwitchTeamsHotKey", "e");
		defaultHotKeyProps.setProperty("SwitchPlayer1HotKey", "");
		defaultHotKeyProps.setProperty("SwitchPlayer2HotKey", "");
		defaultHotKeyProps.setProperty("GameCount1MinusHotKey", "");
		defaultHotKeyProps.setProperty("GameCount1PlusHotKey", "5");
		defaultHotKeyProps.setProperty("GameCount2MinusHotKey", "");
		defaultHotKeyProps.setProperty("GameCount2PlusHotKey", "6");
		defaultHotKeyProps.setProperty("GameCountSwitchHotKey", "");
		defaultHotKeyProps.setProperty("Score1MinusHotKey", "");
		defaultHotKeyProps.setProperty("Score1PlusHotKey", "1");
		defaultHotKeyProps.setProperty("Score2MinusHotKey", "");
		defaultHotKeyProps.setProperty("Score2PlusHotKey", "2");
		defaultHotKeyProps.setProperty("SwitchScoresHotKey", "");
		defaultHotKeyProps.setProperty("SwitchGameCountsHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut1MinusHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut1PlusHotKey", "9");
		defaultHotKeyProps.setProperty("TimeOut2MinusHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut2PlusHotKey", "0");
		defaultHotKeyProps.setProperty("SwitchTimeOutsHotKey", "");
		defaultHotKeyProps.setProperty("Reset1HotKey", "z");
		defaultHotKeyProps.setProperty("Reset2HotKey", ",");
		defaultHotKeyProps.setProperty("Warn1HotKey", "x");
		defaultHotKeyProps.setProperty("Warn2HotKey", ".");
		defaultHotKeyProps.setProperty("SwitchResetWarnsHotKey", "");
		defaultHotKeyProps.setProperty("SwitchSidesHotKey", "w");
		defaultHotKeyProps.setProperty("ResetNamesHotKey", "");
		defaultHotKeyProps.setProperty("ResetGameCountsHotKey", "7");
		defaultHotKeyProps.setProperty("ResetScoresHotKey", "3");
		defaultHotKeyProps.setProperty("ResetTimeOutsHotKey", "-");
		defaultHotKeyProps.setProperty("ResetResetWarnHotKey", "");
		defaultHotKeyProps.setProperty("ResetAllHotKey", "a");
		defaultHotKeyProps.setProperty("ClearAllHotKey", "");
		defaultHotKeyProps.setProperty("ShotTimerHotKey", "s");
		defaultHotKeyProps.setProperty("PassTimerHotKey", "p");
		defaultHotKeyProps.setProperty("TimeOutTimerHotKey", "o");
		defaultHotKeyProps.setProperty("GameTimerHotKey", "g");
		defaultHotKeyProps.setProperty("RecallTimerHotKey", "c");
		defaultHotKeyProps.setProperty("ResetTimersHotKey", "r");
		defaultHotKeyProps.setProperty("UndoHotKey", "u");
		defaultHotKeyProps.setProperty("RedoHotKey", "d");
		defaultHotKeyProps.setProperty("ConnectHotKey", "");
		defaultHotKeyProps.setProperty("DisconnectHotKey", "");
		defaultHotKeyProps.setProperty("PushHotKey", "");
		defaultHotKeyProps.setProperty("PullHotKey", "");
		defaultHotKeyProps.setProperty("ShowScoresHotKey", "");
		defaultHotKeyProps.setProperty("ShowTimerHotKey", "");
		defaultHotKeyProps.setProperty("ShowSkunkHotKey", "k");
		defaultHotKeyProps.setProperty("AutoScoreMainConnectHotKey", "");
		defaultHotKeyProps.setProperty("AutoScoreMainDisconnectHotKey", "");
		defaultHotKeyProps.setProperty("AutoScoreMainSettingsHotKey", "");
		//AutoScore Settings Properties
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerAddress", "192.168.68.69");
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerPort", "5051");
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsAutoConnect", "0");
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsDetailLog", "0");
		//AutoScore Config Properties
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1Pin1", "40");  
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1Pin2", "0");   
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2Pin1", "35");  
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2Pin2", "37");  
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1LEDPin", "33");
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2LEDPin", "18");
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigSwitchPin", "7");   
		defaultAutoScoreConfigProps.setProperty("AutoScoreConfigResetSeconds", "5");

		//Config Properties
		configControlProps 				= new Properties(defaultControlProps);
		configSourceProps 				= new Properties(defaultSourceProps);
		configFileNameProps 			= new Properties(defaultFileNameProps);
		configHotKeyProps 				= new Properties(defaultHotKeyProps);
		configOBSProps      			= new Properties(defaultOBSProps);
		configAutoScoreSettingsProps	= new Properties(defaultAutoScoreSettingsProps);
		configAutoScoreConfigProps		= new Properties(defaultAutoScoreConfigProps);
		
		loadFromControlConfig();
		loadFromOBSConfig();
		loadFromSourceConfig();
		loadFromFileNameConfig();
		loadFromHotKeyConfig();
		loadFromAutoScoreSettingsConfig();
		loadFromAutoScoreConfigConfig();
	}

	//Getters
	//Control Parameters
	public boolean getShowParsed() {return Boolean.parseBoolean(configControlProps.getProperty("ShowParsed"));}
	public String getGameType() {return configControlProps.getProperty("GameType");}
	public String getTableName() {return configControlProps.getProperty("TableName");}
	public String getDatapath() {return configControlProps.getProperty("datapath");}
	public int getPointsToWin() {return Integer.parseInt(configControlProps.getProperty("PointsToWin"));}
	public int getMaxWin() {return Integer.parseInt(configControlProps.getProperty("MaxWin"));}
	public int getWinBy() {return Integer.parseInt(configControlProps.getProperty("WinBy"));}
	public int getGamesToWin() {return Integer.parseInt(configControlProps.getProperty("GamesToWin"));}
	public int getMaxTimeOuts() {return Integer.parseInt(configControlProps.getProperty("MaxTimeOuts"));}
	public int getAutoIncrementGame() {return Integer.parseInt(configControlProps.getProperty("AutoIncrementGame"));}
	public int getAnnounceWinner() {return Integer.parseInt(configControlProps.getProperty("AnnounceWinner"));}
	public int getAnnounceMeatball() {return Integer.parseInt(configControlProps.getProperty("AnnounceMeatball"));}
	public String getWinnerPrefix() {return configControlProps.getProperty("WinnerPrefix");}
	public String getWinnerSuffix() {return configControlProps.getProperty("WinnerSuffix");}
	public String getMeatball() {return configControlProps.getProperty("Meatball");}
	public String getTeam1LastScored() {return configControlProps.getProperty("Team1LastScored");}
	public String getTeam2LastScored() {return configControlProps.getProperty("Team2LastScored");}
	public String getClearLastScored() {return configControlProps.getProperty("ClearLastScored");}
	public String[] getLastScoredStrings() {
		lastScoredStrings[0] = getClearLastScored();
		lastScoredStrings[1] = getTeam1LastScored();
		lastScoredStrings[2] = getTeam2LastScored();
		return lastScoredStrings;
	}
	public String getSide1Color() {return configControlProps.getProperty("Side1Color");}
	public String getSide2Color() {return configControlProps.getProperty("Side2Color");}
	public int getShotTime() {return Integer.parseInt(configControlProps.getProperty("ShotTime"));}
	public int getPassTime() {return Integer.parseInt(configControlProps.getProperty("PassTime"));}
	public int getTimeOutTime() {return Integer.parseInt(configControlProps.getProperty("TimeOutTime"));}
	public int getGameTime() {return Integer.parseInt(configControlProps.getProperty("GameTime"));}
	public int getRecallTime() {return Integer.parseInt(configControlProps.getProperty("RecallTime"));}
	public int getShowTimeOutsUsed() {return Integer.parseInt(configControlProps.getProperty("ShowTimeOutsUsed"));}
	public int getAutoCapNames() {return Integer.parseInt(configControlProps.getProperty("AutoCapNames"));}
	public int getWinByFinalOnly() {return Integer.parseInt(configControlProps.getProperty("WinByFinalOnly"));}
	public int getShowSkunk() {return Integer.parseInt(configControlProps.getProperty("ShowSkunk"));}
	public int getCutThroatMode() {return Integer.parseInt(configControlProps.getProperty("CutThroatMode"));}
	public String getLogoImageURL() {return configControlProps.getProperty("LogoImageURL");}
	public String getLogoLinkURI() {return configControlProps.getProperty("LogoLinkURI");}
	//OBS
	public String getOBSHost() {return configOBSProps.getProperty("OBSHost");}
	public String getOBSPort() {return configOBSProps.getProperty("OBSPort");}
	public String getOBSPassword() {return configOBSProps.getProperty("OBSPassword");}
	public String getOBSScene() {return configOBSProps.getProperty("OBSScene");}
	public int getOBSSavePassword() {return Integer.parseInt(configOBSProps.getProperty("OBSSavePassword"));}
	public int getOBSAutoLogin() {return Integer.parseInt(configOBSProps.getProperty("OBSAutoLogin"));}
	public int getOBSCloseOnConnect() {return Integer.parseInt(configOBSProps.getProperty("OBSCloseOnConnect"));}
	public int getOBSUpdateOnConnect()  {return Integer.parseInt(configOBSProps.getProperty("OBSUpdateOnConnect"));}
	public String getOBSScoreSource() {return configOBSProps.getProperty("OBSScoreSource");}
	public String getOBSTimerSource() {return configOBSProps.getProperty("OBSTimerSource");}
	public String getOBSSkunkFilter() {return configOBSProps.getProperty("OBSSkunkFilter");}
	//Sources
	public String getTeamNameSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1Name");
		} else {
			return configSourceProps.getProperty("Team2Name");
		}
	}
	public String getTeamForwardSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1Forward");
		} else {
			return configSourceProps.getProperty("Team2Forward");
		}
	}
	public String getTeamGoalieSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1Goalie");
		} else {
			return configSourceProps.getProperty("Team2Goalie");
		}
	}
	public String getGameCountSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("GameCount1");
		} else {
			return configSourceProps.getProperty("GameCount2");
		}
	}
	public String getScoreSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Score1");
		} else {
			return configSourceProps.getProperty("Score2");
		}
	}
	public String getTimeOutSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("TimeOut1");
		} else {
			return configSourceProps.getProperty("TimeOut2");
		}
	}
	public String getResetSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Reset1");
		} else {
			return configSourceProps.getProperty("Reset2");
		}
	}
	public String getWarnSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Warn1");
		} else {
			return configSourceProps.getProperty("Warn2");
		}
	}
	public String getPassAttemptsSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1PassAttempts");
		} else {
			return configSourceProps.getProperty("Team2PassAttempts");
		}
	}
	public String getPassCompletesSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1PassCompletes");
		} else {
			return configSourceProps.getProperty("Team2PassCompletes");
		}
	}
	public String getShotAttemptsSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ShotAttempts");
		} else {
			return configSourceProps.getProperty("Team2ShotAttempts");
		}
	}
	public String getShotCompletesSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ShotCompletes");
		} else {
			return configSourceProps.getProperty("Team2ShotCompletes");
		}
	}
	public String getClearAttemptsSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ClearAttempts");
		} else {
			return configSourceProps.getProperty("Team2ClearAttempts");
		}
	}
	public String getClearCompletesSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ClearCompletes");
		} else {
			return configSourceProps.getProperty("Team2ClearCompletes");
		}
	}
	public String getPassPercentSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1PassPercent");
		} else {
			return configSourceProps.getProperty("Team2PassPercent");
		}
	}
	public String getShotPercentSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ShotPercent");
		} else {
			return configSourceProps.getProperty("Team2ShotPercent");
		}
	}
	public String getClearPercentSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ClearPercent");
		} else {
			return configSourceProps.getProperty("Team2ClearPercent");
		}
	}
	public String getTwoBarPassAttemptsSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1TwoBarPassAttempts");
		} else {
			return configSourceProps.getProperty("Team2TwoBarPassAttempts");
		}
	}
	public String getTwoBarPassCompletesSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1TwoBarPassCompletes");
		} else {
			return configSourceProps.getProperty("Team2TwoBarPassCompletes");
		}
	}
	public String getTwoBarPassPercentSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1TwoBarPassPercent");
		} else {
			return configSourceProps.getProperty("Team2TwoBarPassPercent");
		}
	}
	public String getScoringSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1Scoring");
		} else {
			return configSourceProps.getProperty("Team2Scoring");
		}
	}
	public String getThreeBarScoringSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ThreeBarScoring");
		} else {
			return configSourceProps.getProperty("Team2ThreeBarScoring");
		}
	}
	public String getFiveBarScoringSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1FiveBarScoring");
		} else {
			return configSourceProps.getProperty("Team2FiveBarScoring");
		}
	}
	public String getTwoBarScoringSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1TwoBarScoring");
		} else {
			return configSourceProps.getProperty("Team2TwoBarScoring");
		}
	}
	public String getShotsOnGoalSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Team1ShotsOnGoal");
		} else {
			return configSourceProps.getProperty("Team2ShotsOnGoal");
		}
	}
	public String getStuffsSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Stuffs1");
		} else {
			return configSourceProps.getProperty("Stuffs2");
		}
	}
	public String getBreaksSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Breaks1");
		} else {
			return configSourceProps.getProperty("Breaks2");
		}
	}
	public String getAcesSource(int teamNbr) {
		if(teamNbr==1) {
			return configSourceProps.getProperty("Aces1");
		} else {
			return configSourceProps.getProperty("Aces2");
		}
	}
	public String getTableSource() {return configSourceProps.getProperty("Table");}
	public String getTournamentSource() {return configSourceProps.getProperty("Tournament");}
	public String getEventSource() {return configSourceProps.getProperty("Event");}
	public String getTableNameSource() {return configSourceProps.getProperty("TableName");}
	public String getTimeRemainingSource() {return configSourceProps.getProperty("TimeRemaining");}
	public String getTimerInUseSource() {return configSourceProps.getProperty("TimerInUse");}
	public String getMatchWinnerSource() {return configSourceProps.getProperty("MatchWinner");}
	public String getMeatballSource() {return configSourceProps.getProperty("Meatball");}
	public String getLastScoredSource() {return configSourceProps.getProperty("LastScored");}
	public String getGameTimeSource() {return configSourceProps.getProperty("GameTime");}
	public String getMatchTimeSource() {return configSourceProps.getProperty("MatchTime");}
	public String getSide1ColorSource() {return configSourceProps.getProperty("Side1Color");}
	public String getSide2ColorSource() {return configSourceProps.getProperty("Side2Color");}
	//FileNames
	public String getTeamNameFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1Name");
		} else {
			return configFileNameProps.getProperty("Team2Name");
		}
	}
	public String getTeamForwardFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1Forward");
		} else {
			return configFileNameProps.getProperty("Team2Forward");
		}
	}
	public String getTeamGoalieFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1Goalie");
		} else {
			return configFileNameProps.getProperty("Team2Goalie");
		}
	}
	public String getGameCountFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("GameCount1");
		} else {
			return configFileNameProps.getProperty("GameCount2");
		}
	}
	public String getScoreFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Score1");
		} else {
			return configFileNameProps.getProperty("Score2");
		}
	}
	public String getTimeOutFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("TimeOut1");
		} else {
			return configFileNameProps.getProperty("TimeOut2");
		}
	}
	public String getResetFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Reset1");
		} else {
			return configFileNameProps.getProperty("Reset2");
		}
	}
	public String getWarnFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Warn1");
		} else {
			return configFileNameProps.getProperty("Warn2");
		}
	}
	public String getPassAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassAttempts");
		} else {
			return configFileNameProps.getProperty("Team2PassAttempts");
		}
	}
	public String getPassCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassCompletes");
		} else {
			return configFileNameProps.getProperty("Team2PassCompletes");
		}
	}
	public String getShotAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotAttempts");
		} else {
			return configFileNameProps.getProperty("Team2ShotAttempts");
		}
	}
	public String getShotCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotCompletes");
		} else {
			return configFileNameProps.getProperty("Team2ShotCompletes");
		}
	}
	public String getClearAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearAttempts");
		} else {
			return configFileNameProps.getProperty("Team2ClearAttempts");
		}
	}
	public String getClearCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearCompletes");
		} else {
			return configFileNameProps.getProperty("Team2ClearCompletes");
		}
	}
	public String getPassPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassPercent");
		} else {
			return configFileNameProps.getProperty("Team2PassPercent");
		}
	}
	public String getShotPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotPercent");
		} else {
			return configFileNameProps.getProperty("Team2ShotPercent");
		}
	}
	public String getClearPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearPercent");
		} else {
			return configFileNameProps.getProperty("Team2ClearPercent");
		}
	}
	public String getTwoBarPassAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassAttempts");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassAttempts");
		}
	}
	public String getTwoBarPassCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassCompletes");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassCompletes");
		}
	}
	public String getTwoBarPassPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassPercent");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassPercent");
		}
	}
	public String getScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1Scoring");
		} else {
			return configFileNameProps.getProperty("Team2Scoring");
		}
	}
	public String getThreeBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ThreeBarScoring");
		} else {
			return configFileNameProps.getProperty("Team2ThreeBarScoring");
		}
	}
	public String getFiveBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1FiveBarScoring");
		} else {
			return configFileNameProps.getProperty("Team2FiveBarScoring");
		}
	}
	public String getTwoBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarScoring");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarScoring");
		}
	}
	public String getShotsOnGoalFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotsOnGoal");
		} else {
			return configFileNameProps.getProperty("Team2ShotsOnGoal");
		}
	}
	public String getStuffsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Stuffs1");
		} else {
			return configFileNameProps.getProperty("Stuffs2");
		}
	}
	public String getBreaksFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Breaks1");
		} else {
			return configFileNameProps.getProperty("Breaks2");
		}
	}
	public String getAcesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Aces1");
		} else {
			return configFileNameProps.getProperty("Aces2");
		}
	}
	public String getTableFileName() {return configFileNameProps.getProperty("Table");}
	public String getTournamentFileName() {return configFileNameProps.getProperty("Tournament");}
	public String getEventFileName() {return configFileNameProps.getProperty("Event");}
	public String getTableNameFileName() {return configFileNameProps.getProperty("TableName");}
	public String getTimeRemainingFileName() {return configFileNameProps.getProperty("TimeRemaining");}
	public String getTimerInUseFileName() {return configFileNameProps.getProperty("TimerInUse");}
	public String getMatchWinnerFileName() {return configFileNameProps.getProperty("MatchWinner");}
	public String getMeatballFileName() {return configFileNameProps.getProperty("Meatball");}
	public String getLastScoredFileName() {return configFileNameProps.getProperty("LastScored");}
	public String getGameTimeFileName() {return configFileNameProps.getProperty("GameTime");}
	public String getMatchTimeFileName() {return configFileNameProps.getProperty("MatchTime");}
	public String getSide1ColorFileName() {return configFileNameProps.getProperty("Side1Color");}
	public String getSide2ColorFileName() {return configFileNameProps.getProperty("Side2Color");}
	//HotKeys
	public String getStartMatchHotKey() {return configHotKeyProps.getProperty("StartMatchHotKey");}
	public String getPauseMatchHotKey() {return configHotKeyProps.getProperty("PauseMatchHotKey");}
	public String getStartGameHotKey() {return configHotKeyProps.getProperty("StartGameHotKey");}
	public String getTeam1ClearHotKey() {return configHotKeyProps.getProperty("Team1ClearHotKey");}
	public String getTeam1SwitchPositionsHotKey() {return configHotKeyProps.getProperty("Team1SwitchPositionsHotKey");}
	public String getTeam2ClearHotKey() {return configHotKeyProps.getProperty("Team2ClearHotKey");}
	public String getTeam2SwitchPositionsHotKey() {return configHotKeyProps.getProperty("Team2SwitchPositionsHotKey");}
	public String getSwitchTeamsHotKey() {return configHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public String getSwitchPlayer1HotKey() {return configHotKeyProps.getProperty("SwitchPlayer1HotKey");}
	public String getSwitchPlayer2HotKey() {return configHotKeyProps.getProperty("SwitchPlayer2HotKey");}
	public String getGameCount1MinusHotKey() {return configHotKeyProps.getProperty("GameCount1MinusHotKey");}
	public String getGameCount1PlusHotKey() {return configHotKeyProps.getProperty("GameCount1PlusHotKey");}
	public String getGameCount2MinusHotKey() {return configHotKeyProps.getProperty("GameCount2MinusHotKey");}
	public String getGameCount2PlusHotKey() {return configHotKeyProps.getProperty("GameCount2PlusHotKey");}
	public String getSwitchGameCountsHotKey() {return configHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public String getScore1MinusHotKey() {return configHotKeyProps.getProperty("Score1MinusHotKey");}
	public String getScore1PlusHotKey() {return configHotKeyProps.getProperty("Score1PlusHotKey");}
	public String getScore2MinusHotKey() {return configHotKeyProps.getProperty("Score2MinusHotKey");}
	public String getScore2PlusHotKey() {return configHotKeyProps.getProperty("Score2PlusHotKey");}
	public String getSwitchScoresHotKey() {return configHotKeyProps.getProperty("SwitchScoresHotKey");}
	public String getTimeOut1MinusHotKey() {return configHotKeyProps.getProperty("TimeOut1MinusHotKey");}
	public String getTimeOut1PlusHotKey() {return configHotKeyProps.getProperty("TimeOut1PlusHotKey");}
	public String getTimeOut2MinusHotKey() {return configHotKeyProps.getProperty("TimeOut2MinusHotKey");}
	public String getTimeOut2PlusHotKey() {return configHotKeyProps.getProperty("TimeOut2PlusHotKey");}
	public String getSwitchTimeOutsHotKey() {return configHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
	public String getReset1HotKey() {return configHotKeyProps.getProperty("Reset1HotKey");}
	public String getReset2HotKey() {return configHotKeyProps.getProperty("Reset2HotKey");}
	public String getWarn1HotKey() {return configHotKeyProps.getProperty("Warn1HotKey");}
	public String getWarn2HotKey() {return configHotKeyProps.getProperty("Warn2HotKey");}
	public String getSwitchResetWarnsHotKey() {return configHotKeyProps.getProperty("SwitchResetWarnsHotKey");}
	public String getSwitchSidesHotKey() {return configHotKeyProps.getProperty("SwitchSidesHotKey");}
	public String getResetNamesHotKey() {return configHotKeyProps.getProperty("ResetNamesHotKey");}
	public String getResetGameCountsHotKey() {return configHotKeyProps.getProperty("ResetGameCountsHotKey");}
	public String getResetScoresHotKey() {return configHotKeyProps.getProperty("ResetScoresHotKey");}
	public String getResetTimeOutsHotKey() {return configHotKeyProps.getProperty("ResetTimeOutsHotKey");}
	public String getResetResetWarnHotKey() {return configHotKeyProps.getProperty("ResetResetWarnHotKey");}
	public String getResetAllHotKey() {return configHotKeyProps.getProperty("ResetAllHotKey");}
	public String getClearAllHotKey() {return configHotKeyProps.getProperty("ClearAllHotKey");}
	public String getShotTimerHotKey() {return configHotKeyProps.getProperty("ShotTimerHotKey");}
	public String getPassTimerHotKey() {return configHotKeyProps.getProperty("PassTimerHotKey");}
	public String getTimeOutTimerHotKey() {return configHotKeyProps.getProperty("TimeOutTimerHotKey");}
	public String getGameTimerHotKey() {return configHotKeyProps.getProperty("GameTimerHotKey");}
	public String getRecallTimerHotKey() {return configHotKeyProps.getProperty("RecallTimerHotKey");}
	public String getResetTimersHotKey() {return configHotKeyProps.getProperty("ResetTimersHotKey");}
	public String getUndoHotKey() {return configHotKeyProps.getProperty("UndoHotKey");}
	public String getRedoHotKey() {return configHotKeyProps.getProperty("RedoHotKey");}
	public String getConnectHotKey() {return configHotKeyProps.getProperty("ConnectHotKey");}
	public String getDisconnectHotKey() {return configHotKeyProps.getProperty("DisconnectHotKey");}
	public String getPushHotKey() {return configHotKeyProps.getProperty("PushHotKey");}
	public String getPullHotKey() {return configHotKeyProps.getProperty("PullHotKey");}
	public String getShowScoresHotKey() {return configHotKeyProps.getProperty("ShowScoresHotKey");}
	public String getShowTimerHotKey() {return configHotKeyProps.getProperty("ShowTimerHotKey");}
	public String getShowSkunkHotKey() {return configHotKeyProps.getProperty("ShowSkunkHotKey");}
	public String getAutoScoreMainConnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainConnectHotKey");}
	public String getAutoScoreMainDisconnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainDisconnectHotKey");}
	public String getAutoScoreMainSettingsHotKey() {return configHotKeyProps.getProperty("AutoScoreMainSettingsHotKey");}
	//AutoScore Settings
	public String getAutoScoreSettingsServerAddress() {return configAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerAddress");}
	public int getAutoScoreSettingsServerPort() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerPort"));}
	public int getAutoScoreSettingsAutoConnect() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsAutoConnect"));}
	public int getAutoScoreSettingsDetailLog() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsDetailLog"));}
	//AutoScore Config
	public int getAutoScoreConfigTeam1Pin1() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1Pin1"));}
	public int getAutoScoreConfigTeam1Pin2() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1Pin2"));}
	public int getAutoScoreConfigTeam2Pin1() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2Pin1"));}
	public int getAutoScoreConfigTeam2Pin2() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2Pin2"));}
	public int getAutoScoreConfigTeam1LEDPin() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1LEDPin"));}
	public int getAutoScoreConfigTeam2LEDPin() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2LEDPin"));}
	public int getAutoScoreConfigSwitchPin() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigSwitchPin"));}
	public int getAutoScoreConfigResetSeconds() {return Integer.parseInt(configAutoScoreConfigProps.getProperty("AutoScoreConfigResetSeconds"));}
	//Setters
	//Control Parameters
	public void setShowParsed(boolean showParsed) {
		configControlProps.setProperty("ShowParsed", Boolean.toString(showParsed));
	}
	public void setGameType(String gameType) {
		configControlProps.setProperty("GameType", gameType);
	}
	public void setTableName(String tableName) {
		configControlProps.setProperty("TableName", tableName);
	}
	public void setDatapath(String datapath) {
		configControlProps.setProperty("datapath", datapath);
	}
	public void setPointsToWin(int pointsToWin) {
		configControlProps.setProperty("PointsToWin", Integer.toString(pointsToWin));
		}
	public void setMaxWin(int maxWin) {
		configControlProps.setProperty("MaxWin", Integer.toString(maxWin));
		}
	public void setWinBy(int winBy) {
		configControlProps.setProperty("WinBy", Integer.toString(winBy));
		}
	public void setGamesToWin(int gamesToWin) {
		configControlProps.setProperty("GamesToWin", Integer.toString(gamesToWin));
		}
	public void setMaxTimeOuts(int maxTimeOuts) {
		configControlProps.setProperty("MaxTimeOuts", Integer.toString(maxTimeOuts));
		}
	public void setAutoIncrementGame(int autoIncrementGame) {
		configControlProps.setProperty("AutoIncrementGame", Integer.toString(autoIncrementGame));
		}
	public void setAnnounceWinner(int announceWinner) {
		configControlProps.setProperty("AnnounceWinner", Integer.toString(announceWinner));
		}
	public void setAnnounceMeatball(int announceMeatball) {
		configControlProps.setProperty("AnnounceMeatball", Integer.toString(announceMeatball));
		}
	public void setWinnerPrefix(String winnerPrefix) {
		configControlProps.setProperty("WinnerPrefix", winnerPrefix);
	}
	public void setWinnerSuffix(String winnerSuffix) {
		configControlProps.setProperty("WinnerSuffix", winnerSuffix);
	}
	public void setMeatball(String meatball) {
		configControlProps.setProperty("Meatball", meatball);
	}
	public void setTeam1LastScored(String team1LastScored) {
		configControlProps.setProperty("Team1LastScored", team1LastScored);
	}
	public void setTeam2LastScored(String team2LastScored) {
		configControlProps.setProperty("Team2LastScored", team2LastScored);
	}
	public void setClearLastScored(String clearLastScored) {
		configControlProps.setProperty("ClearLastScored", clearLastScored);
	}
	public void setSide1Color(String side1Color) {
		configControlProps.setProperty("Side1Color", side1Color);
	}
	public void setSide2Color(String side2Color) {
		configControlProps.setProperty("Side2Color", side2Color);
	}
	public void setShotTime(int shotTime) {
		configControlProps.setProperty("ShotTime", Integer.toString(shotTime));
		}
	public void setPassTime(int passTime) {
		configControlProps.setProperty("PassTime", Integer.toString(passTime));
		}
	public void setTimeOutTime(int timeOutTime) {
		configControlProps.setProperty("TimeOutTime", Integer.toString(timeOutTime));
		}
	public void setGameTime(int gameTime) {
		configControlProps.setProperty("GameTime", Integer.toString(gameTime));
		}
	public void setRecallTime(int recallTime) {
		configControlProps.setProperty("RecallTime", Integer.toString(recallTime));
		}
	public void setShowTimeOutsUsed(int showTimeOutsUsed) {
		configControlProps.setProperty("ShowTimeOutsUsed", Integer.toString(showTimeOutsUsed));
	}
	public void setAutoCapNames(int autoCapNames) {
		configControlProps.setProperty("AutoCapNames", Integer.toString(autoCapNames));
	}
	public void setWinByFinalOnly(int winByFinalOnly) {
		configControlProps.setProperty("WinByFinalOnly", Integer.toString(winByFinalOnly));
	}
	public void setShowSkunk(int showSkunk) {
		configControlProps.setProperty("ShowSkunk", Integer.toString(showSkunk));
	}
	public void setCutThroatMode(int cutThroatMode) {
		configControlProps.setProperty("CutThroatMode", Integer.toString(cutThroatMode));
	}
	public void setLogoImageURL(String logoImageURL) {
		configControlProps.setProperty("LogoImageURL", logoImageURL);
	}
	public void setLogoLinkURI(String logoLinkURI) {
		configControlProps.setProperty("LogoLinkURI", logoLinkURI);
	}
	//OBS
	public void setOBSHost(String obsHost) {
		configOBSProps.setProperty("OBSHost", obsHost);
	}
	public void setOBSPort(String obsPort) {
		configOBSProps.setProperty("OBSPort", obsPort);
	}
	public void setOBSPassword(String obsPassword) {
		configOBSProps.setProperty("OBSPassword", obsPassword);
	}
	public void setOBSSavePassword(int obsSavePassword) {
		configOBSProps.setProperty("OBSSavePassword", Integer.toString(obsSavePassword));
	}
	public void setOBSScene(String obsScene) {
		configOBSProps.setProperty("OBSScene", obsScene);
	}
	public void setOBSAutoLogin(int obsAutoLogin) {
		configOBSProps.setProperty("OBSAutoLogin", Integer.toString(obsAutoLogin));
	}
	public void setOBSCloseOnConnect(int obsCloseOnConnect) {
		configOBSProps.setProperty("OBSCloseOnConnect", Integer.toString(obsCloseOnConnect));
	}
	public void setOBSUpdateOnConnect(int obsUpdateOnConnect) {
		configOBSProps.setProperty("OBSUpdateOnConnect", Integer.toString(obsUpdateOnConnect));
	}
	public void setOBSScoreSource(String obsScoreSource) {
		configOBSProps.setProperty("OBSScoreSource", obsScoreSource);
	}
	public void setOBSTimerSource(String obsTimerSource) {
		configOBSProps.setProperty("OBSTimerSource", obsTimerSource);
	}
	public void setOBSSkunkFilter(String obsSkunkFilter) {
		configOBSProps.setProperty("OBSSkunkFilter", obsSkunkFilter);
	}
	//Sources
	public void setTeam1PassAttemptsSource(String team1PassAttemptsSource) {
		configSourceProps.setProperty("Team1PassAttempts", team1PassAttemptsSource);
	}
	public void setTeam2PassAttemptsSource(String team2PassAttemptsSource) {
		configSourceProps.setProperty("Team2PassAttempts", team2PassAttemptsSource);
	}
	public void setTeam1PassCompletesSource(String team1PassCompletesSource) {
		configSourceProps.setProperty("Team1PassCompletes", team1PassCompletesSource);
	}
	public void setTeam2PassCompletesSource(String team2PassCompletesSource) {
		configSourceProps.setProperty("Team2PassCompletes", team2PassCompletesSource);
	}
	public void setTeam1ShotAttemptsSource(String team1ShotAttemptsSource) {
		configSourceProps.setProperty("Team1ShotAttempts", team1ShotAttemptsSource);
	}
	public void setTeam2ShotAttemptsSource(String team2ShotAttemptsSource) {
		configSourceProps.setProperty("Team2ShotAttempts", team2ShotAttemptsSource);
	}
	public void setTeam1ShotCompletesSource(String team1ShotCompletesSource) {
		configSourceProps.setProperty("Team1ShotCompletes", team1ShotCompletesSource);
	}
	public void setTeam2ShotCompletesSource(String team2ShotCompletesSource) {
		configSourceProps.setProperty("Team2ShotCompletes", team2ShotCompletesSource);
	}
	public void setTeam1ClearAttemptsSource(String team1ClearAttemptsSource) {
		configSourceProps.setProperty("Team1ClearAttempts", team1ClearAttemptsSource);
	}
	public void setTeam2ClearAttemptsSource(String team2ClearAttemptsSource) {
		configSourceProps.setProperty("Team2ClearAttempts", team2ClearAttemptsSource);
	}
	public void setTeam1ClearCompletesSource(String team1ClearCompletesSource) {
		configSourceProps.setProperty("Team1ClearCompletes", team1ClearCompletesSource);
	}
	public void setTeam2ClearCompletesSource(String team2ClearCompletesSource) {
		configSourceProps.setProperty("Team2ClearCompletes", team2ClearCompletesSource);
	}
	public void setTeam1PassPercentSource(String team1PassPercentSource) {
		configSourceProps.setProperty("Team1PassPercent", team1PassPercentSource);
	}
	public void setTeam2PassPercentSource(String team2PassPercentSource) {
		configSourceProps.setProperty("Team2PassPercent", team2PassPercentSource);
	}
	public void setTeam1ShotPercentSource(String team1ShotPercentSource) {
		configSourceProps.setProperty("Team1ShotPercent", team1ShotPercentSource);
	}
	public void setTeam2ShotPercentSource(String team2ShotPercentSource) {
		configSourceProps.setProperty("Team2ShotPercent", team2ShotPercentSource);
	}
	public void setTeam1ClearPercentSource(String team1ClearPercentSource) {
		configSourceProps.setProperty("Team1ClearPercent", team1ClearPercentSource);
	}
	public void setTeam2ClearPercentSource(String team2ClearPercentSource) {
		configSourceProps.setProperty("Team2ClearPercent", team2ClearPercentSource);
	}
	public void setTeam1TwoBarPassAttemptsSource(String team1TwoBarPassAttemptsSource) {
		configSourceProps.setProperty("Team1TwoBarPassAttempts", team1TwoBarPassAttemptsSource);
	}
	public void setTeam2TwoBarPassAttemptsSource(String team2TwoBarPassAttemptsSource) {
		configSourceProps.setProperty("Team2TwoBarPassAttempts", team2TwoBarPassAttemptsSource);
	}
	public void setTeam1TwoBarPassCompletesSource(String team1TwoBarPassCompletesSource) {
		configSourceProps.setProperty("Team1TwoBarPassCompletes", team1TwoBarPassCompletesSource);
	}
	public void setTeam2TwoBarPassCompletesSource(String team2TwoBarPassCompletesSource) {
		configSourceProps.setProperty("Team2TwoBarPassCompletes", team2TwoBarPassCompletesSource);
	}
	public void setTeam1TwoBarPassPercentSource(String team1TwoBarPassPercentSource) {
		configSourceProps.setProperty("Team1TwoBarPassPercent", team1TwoBarPassPercentSource);
	}
	public void setTeam2TwoBarPassPercentSource(String team2TwoBarPassPercentSource) {
		configSourceProps.setProperty("Team2TwoBarPassPercent", team2TwoBarPassPercentSource);
	}
	public void setTeam1ScoringSource(String team1ScoringSource) {
		configSourceProps.setProperty("Team1Scoring", team1ScoringSource);
	}
	public void setTeam2ScoringSource(String team2ScoringSource) {
		configSourceProps.setProperty("Team2Scoring", team2ScoringSource);
	}
	public void setTeam1ThreeBarScoringSource(String team1ThreeBarScoringSource) {
		configSourceProps.setProperty("Team1ThreeBarScoring", team1ThreeBarScoringSource);
	}
	public void setTeam2ThreeBarScoringSource(String team2ThreeBarScoringSource) {
		configSourceProps.setProperty("Team2ThreeBarScoring", team2ThreeBarScoringSource);
	}
	public void setTeam1FiveBarScoringSource(String team1FiveBarScoringSource) {
		configSourceProps.setProperty("Team1FiveBarScoring", team1FiveBarScoringSource);
	}
	public void setTeam2FiveBarScoringSource(String team2FiveBarScoringSource) {
		configSourceProps.setProperty("Team2FiveBarScoring", team2FiveBarScoringSource);
	}
	public void setTeam1TwoBarScoringSource(String team1TwoBarScoringSource) {
		configSourceProps.setProperty("Team1TwoBarScoring", team1TwoBarScoringSource);
	}
	public void setTeam2TwoBarScoringSource(String team2TwoBarScoringSource) {
		configSourceProps.setProperty("Team2TwoBarScoring", team2TwoBarScoringSource);
	}
	public void setTeam1ShotsOnGoalSource(String team1ShotsOnGoalSource) {
		configSourceProps.setProperty("Team1ShotsOnGoal", team1ShotsOnGoalSource);
	}
	public void setTeam2ShotsOnGoalSource(String team2ShotsOnGoalSource) {
		configSourceProps.setProperty("Team2ShotsOnGoal", team2ShotsOnGoalSource);
	}
	public void setTableSource(String tableSource) {
		configSourceProps.setProperty("Table", tableSource);
	}
	public void setTeam1NameSource(String team1NameSource) {
		configSourceProps.setProperty("Team1Name", team1NameSource);
	}
	public void setTeam1ForwardSource(String team1ForwardSource) {
		configSourceProps.setProperty("Team1Forward", team1ForwardSource);
	}
	public void setTeam1GoalieSource(String team1GoalieSource) {
		configSourceProps.setProperty("Team1Goalie", team1GoalieSource);
	}
	public void setTournamentSource(String tournamentSource) {
		configSourceProps.setProperty("Tournament", tournamentSource);
	}
	public void setTeam2NameSource(String team2NameSource) {
		configSourceProps.setProperty("Team2Name", team2NameSource);
	}
	public void setTeam2ForwardSource(String team2ForwardSource) {
		configSourceProps.setProperty("Team2Forward", team2ForwardSource);
	}
	public void setTeam2GoalieSource(String team2GoalieSource) {
		configSourceProps.setProperty("Team2Goalie", team2GoalieSource);
	}
	public void setEventSource(String eventSource) {
		configSourceProps.setProperty("Event", eventSource);
	}
	public void setTableNameSource(String tableNameSource) {
		configSourceProps.setProperty("TableName", tableNameSource);
	}
	public void setGameCount1Source(String gameCount1Source) {
		configSourceProps.setProperty("GameCount1", gameCount1Source);
	}
	public void setTimeRemainingSource(String timeRemainingSource) {
		configSourceProps.setProperty("TimeRemaining", timeRemainingSource);
	}
	public void setGameCount2Source(String gameCount2Source) {
		configSourceProps.setProperty("GameCount2", gameCount2Source);
	}
	public void setTimerInUseSource(String timerInUseSource) {
		configSourceProps.setProperty("TimerInUse", timerInUseSource);
	}
	public void setScore1Source(String score1Source) {
		configSourceProps.setProperty("Score1", score1Source);
	}
	public void setMatchWinnerSource(String matchWinnerSource) {
		configSourceProps.setProperty("MatchWinner", matchWinnerSource);
	}
	public void setMeatballSource(String meatballSource) {
		configSourceProps.setProperty("Meatball", meatballSource);
	}
	public void setScore2Source(String score2Source) {
		configSourceProps.setProperty("Score2", score2Source);
	}
	public void setTimeOut1Source(String timeOut1Source) {
		configSourceProps.setProperty("TimeOut1", timeOut1Source);
	}
	public void setTimeOut2Source(String timeOut2Source) {
		configSourceProps.setProperty("TimeOut2", timeOut2Source);
	}
	public void setReset1Source(String reset1Source) {
		configSourceProps.setProperty("Reset1", reset1Source);
	}
	public void setReset2Source(String reset2Source) {
		configSourceProps.setProperty("Reset2", reset2Source);
	}
	public void setWarn1Source(String warn1Source) {
		configSourceProps.setProperty("Warn1", warn1Source);
	}
	public void setWarn2Source(String warn2Source) {
		configSourceProps.setProperty("Warn2", warn2Source);
	}
	public void setLastScoredSource(String lastScoredSource) {
		configSourceProps.setProperty("LastScored", lastScoredSource);
	}
	public void setGameTimeSource(String gameTimeSource) {
		configSourceProps.setProperty("GameTime", gameTimeSource);
	}
	public void setMatchTimeSource(String matchTimeSource) {
		configSourceProps.setProperty("MatchTime", matchTimeSource);
	}
	public void setStuffs1Source(String stuffs1Source) {
		configSourceProps.setProperty("Stuffs1", stuffs1Source);
	}
	public void setStuffs2Source(String stuffs2Source) {
		configSourceProps.setProperty("Stuffs2", stuffs2Source);
	}
	public void setBreaks1Source(String breaks1Source) {
		configSourceProps.setProperty("Breaks1", breaks1Source);
	}
	public void setBreaks2Source(String breaks2Source) {
		configSourceProps.setProperty("Breaks2", breaks2Source);
	}
	public void setAces1Source(String aces1Source) {
		configSourceProps.setProperty("Aces1",  aces1Source);
	}
	public void setAces2Source(String aces2Source) {
		configSourceProps.setProperty("Aces2",  aces2Source);
	}
	public void setSide1ColorSource(String side1ColorSource) {
		configSourceProps.setProperty("Side1Color", side1ColorSource);
	}
	public void setSide2ColorSource(String side2ColorSource) {
		configSourceProps.setProperty("Side2Color", side2ColorSource);
	}
	//FileNames
	public void setTeam1PassAttemptsFileName(String team1PassAttemptsFileName) {
		configFileNameProps.setProperty("Team1PassAttempts", team1PassAttemptsFileName);
	}
	public void setTeam2PassAttemptsFileName(String team2PassAttemptsFileName) {
		configFileNameProps.setProperty("Team2PassAttempts", team2PassAttemptsFileName);
	}
	public void setTeam1PassCompletesFileName(String team1PassCompletesFileName) {
		configFileNameProps.setProperty("Team1PassCompletes", team1PassCompletesFileName);
	}
	public void setTeam2PassCompletesFileName(String team2PassCompletesFileName) {
		configFileNameProps.setProperty("Team2PassCompletes", team2PassCompletesFileName);
	}
	public void setTeam1ShotAttemptsFileName(String team1ShotAttemptsFileName) {
		configFileNameProps.setProperty("Team1ShotAttempts", team1ShotAttemptsFileName);
	}
	public void setTeam2ShotAttemptsFileName(String team2ShotAttemptsFileName) {
		configFileNameProps.setProperty("Team2ShotAttempts", team2ShotAttemptsFileName);
	}
	public void setTeam1ShotCompletesFileName(String team1ShotCompletesFileName) {
		configFileNameProps.setProperty("Team1ShotCompletes", team1ShotCompletesFileName);
	}
	public void setTeam2ShotCompletesFileName(String team2ShotCompletesFileName) {
		configFileNameProps.setProperty("Team2ShotCompletes", team2ShotCompletesFileName);
	}
	public void setTeam1ClearAttemptsFileName(String team1ClearAttemptsFileName) {
		configFileNameProps.setProperty("Team1ClearAttempts", team1ClearAttemptsFileName);
	}
	public void setTeam2ClearAttemptsFileName(String team2ClearAttemptsFileName) {
		configFileNameProps.setProperty("Team2ClearAttempts", team2ClearAttemptsFileName);
	}
	public void setTeam1ClearCompletesFileName(String team1ClearCompletesFileName) {
		configFileNameProps.setProperty("Team1ClearCompletes", team1ClearCompletesFileName);
	}
	public void setTeam2ClearCompletesFileName(String team2ClearCompletesFileName) {
		configFileNameProps.setProperty("Team2ClearCompletes", team2ClearCompletesFileName);
	}
	public void setTeam1PassPercentFileName(String team1PassPercentFileName) {
		configFileNameProps.setProperty("Team1PassPercent", team1PassPercentFileName);
	}
	public void setTeam2PassPercentFileName(String team2PassPercentFileName) {
		configFileNameProps.setProperty("Team2PassPercent", team2PassPercentFileName);
	}
	public void setTeam1ShotPercentFileName(String team1ShotPercentFileName) {
		configFileNameProps.setProperty("Team1ShotPercent", team1ShotPercentFileName);
	}
	public void setTeam2ShotPercentFileName(String team2ShotPercentFileName) {
		configFileNameProps.setProperty("Team2ShotPercent", team2ShotPercentFileName);
	}
	public void setTeam1ClearPercentFileName(String team1ClearPercentFileName) {
		configFileNameProps.setProperty("Team1ClearPercent", team1ClearPercentFileName);
	}
	public void setTeam2ClearPercentFileName(String team2ClearPercentFileName) {
		configFileNameProps.setProperty("Team2ClearPercent", team2ClearPercentFileName);
	}
	public void setTeam1TwoBarPassAttemptsFileName(String team1TwoBarPassAttemptsFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassAttempts", team1TwoBarPassAttemptsFileName);
	}
	public void setTeam2TwoBarPassAttemptsFileName(String team2TwoBarPassAttemptsFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassAttempts", team2TwoBarPassAttemptsFileName);
	}
	public void setTeam1TwoBarPassCompletesFileName(String team1TwoBarPassCompletesFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassCompletes", team1TwoBarPassCompletesFileName);
	}
	public void setTeam2TwoBarPassCompletesFileName(String team2TwoBarPassCompletesFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassCompletes", team2TwoBarPassCompletesFileName);
	}
	public void setTeam1TwoBarPassPercentFileName(String team1TwoBarPassPercentFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassPercent", team1TwoBarPassPercentFileName);
	}
	public void setTeam2TwoBarPassPercentFileName(String team2TwoBarPassPercentFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassPercent", team2TwoBarPassPercentFileName);
	}
	public void setTeam1ScoringFileName(String team1ScoringFileName) {
		configFileNameProps.setProperty("Team1Scoring", team1ScoringFileName);
	}
	public void setTeam2ScoringFileName(String team2ScoringFileName) {
		configFileNameProps.setProperty("Team2Scoring", team2ScoringFileName);
	}
	public void setTeam1ThreeBarScoringFileName(String team1ThreeBarScoringFileName) {
		configFileNameProps.setProperty("Team1ThreeBarScoring", team1ThreeBarScoringFileName);
	}
	public void setTeam2ThreeBarScoringFileName(String team2ThreeBarScoringFileName) {
		configFileNameProps.setProperty("Team2ThreeBarScoring", team2ThreeBarScoringFileName);
	}
	public void setTeam1FiveBarScoringFileName(String team1FiveBarScoringFileName) {
		configFileNameProps.setProperty("Team1FiveBarScoring", team1FiveBarScoringFileName);
	}
	public void setTeam2FiveBarScoringFileName(String team2FiveBarScoringFileName) {
		configFileNameProps.setProperty("Team2FiveBarScoring", team2FiveBarScoringFileName);
	}
	public void setTeam1TwoBarScoringFileName(String team1TwoBarScoringFileName) {
		configFileNameProps.setProperty("Team1TwoBarScoring", team1TwoBarScoringFileName);
	}
	public void setTeam2TwoBarScoringFileName(String team2TwoBarScoringFileName) {
		configFileNameProps.setProperty("Team2TwoBarScoring", team2TwoBarScoringFileName);
	}
	public void setTeam1ShotsOnGoalFileName(String team1ShotsOnGoalFileName) {
		configFileNameProps.setProperty("Team1ShotsOnGoal", team1ShotsOnGoalFileName);
	}
	public void setTeam2ShotsOnGoalFileName(String team2ShotsOnGoalFileName) {
		configFileNameProps.setProperty("Team2ShotsOnGoal", team2ShotsOnGoalFileName);
	}
	public void setTableFileName(String tableFileName) {
		configFileNameProps.setProperty("Table", tableFileName);
	}
	public void setTeam1NameFileName(String team1NameFileName) {
		configFileNameProps.setProperty("Team1Name", team1NameFileName);
	}
	public void setTeam1ForwardFileName(String team1ForwardFileName) {
		configFileNameProps.setProperty("Team1Forward", team1ForwardFileName);
	}
	public void setTeam1GoalieFileName(String team1GoalieFileName) {
		configFileNameProps.setProperty("Team1Goalie", team1GoalieFileName);
	}
	public void setTournamentFileName(String tournamentFileName) {
		configFileNameProps.setProperty("Tournament", tournamentFileName);
	}
	public void setTeam2NameFileName(String team2NameFileName) {
		configFileNameProps.setProperty("Team2Name", team2NameFileName);
	}
	public void setTeam2ForwardFileName(String team2ForwardFileName) {
		configFileNameProps.setProperty("Team2Forward", team2ForwardFileName);
	}
	public void setTeam2GoalieFileName(String team2GoalieFileName) {
		configFileNameProps.setProperty("Team2Goalie", team2GoalieFileName);
	}
	public void setEventFileName(String eventFileName) {
		configFileNameProps.setProperty("Event", eventFileName);
	}
	public void setTableNameFileName(String tableNameFileName) {
		configFileNameProps.setProperty("TableName", tableNameFileName);
	}
	public void setGameCount1FileName(String gameCount1FileName) {
		configFileNameProps.setProperty("GameCount1", gameCount1FileName);
	}
	public void setTimeRemainingFileName(String timeRemainingFileName) {
		configFileNameProps.setProperty("TimeRemaining", timeRemainingFileName);
	}
	public void setGameCount2FileName(String gameCount2FileName) {
		configFileNameProps.setProperty("GameCount2", gameCount2FileName);
	}
	public void setTimerInUseFileName(String timerInUseFileName) {
		configFileNameProps.setProperty("TimerInUse", timerInUseFileName);
	}
	public void setScore1FileName(String score1FileName) {
		configFileNameProps.setProperty("Score1", score1FileName);
	}
	public void setMatchWinnerFileName(String matchWinnerFileName) {
		configFileNameProps.setProperty("MatchWinner", matchWinnerFileName);
	}
	public void setMeatballFileName(String meatballFileName) {
		configFileNameProps.setProperty("Meatball", meatballFileName);
	}
	public void setScore2FileName(String score2FileName) {
		configFileNameProps.setProperty("Score2", score2FileName);
	}
	public void setTimeOut1FileName(String timeOut1FileName) {
		configFileNameProps.setProperty("TimeOut1", timeOut1FileName);
	}
	public void setTimeOut2FileName(String timeOut2FileName) {
		configFileNameProps.setProperty("TimeOut2", timeOut2FileName);
	}
	public void setReset1FileName(String reset1FileName) {
		configFileNameProps.setProperty("Reset1", reset1FileName);
	}
	public void setReset2FileName(String reset2FileName) {
		configFileNameProps.setProperty("Reset2", reset2FileName);
	}
	public void setWarn1FileName(String warn1FileName) {
		configFileNameProps.setProperty("Warn1", warn1FileName);
	}
	public void setWarn2FileName(String warn2FileName) {
		configFileNameProps.setProperty("Warn2", warn2FileName);
	}
	public void setLastScoredFileName(String lastScoredFileName) {
		configFileNameProps.setProperty("LastScored", lastScoredFileName);
	}
	public void setGameTimeFileName(String gameTimeFileName) {
		configFileNameProps.setProperty("GameTime", gameTimeFileName);
	}
	public void setMatchTimeFileName(String matchTimeFileName) {
		configFileNameProps.setProperty("MatchTime", matchTimeFileName);
	}
	public void setStuffs1FileName(String stuffs1FileName) {
		configFileNameProps.setProperty("Stuffs1", stuffs1FileName);
	}
	public void setStuffs2FileName(String stuffs2FileName) {
		configFileNameProps.setProperty("Stuffs2", stuffs2FileName);
	}
	public void setBreaks1FileName(String breaks1FileName) {
		configFileNameProps.setProperty("Breaks1", breaks1FileName);
	}
	public void setBreaks2FileName(String breaks2FileName) {
		configFileNameProps.setProperty("Breaks2", breaks2FileName);
	}
	public void setAces1FileName(String aces1FileName) {
		configFileNameProps.setProperty("Aces1", aces1FileName);
	}
	public void setAces2FileName(String aces2FileName) {
		configFileNameProps.setProperty("Aces2", aces2FileName);
	}
	public void setSide1ColorFileName(String side1ColorFileName) {
		configFileNameProps.setProperty("Side1Color", side1ColorFileName);
	}
	public void setSide2ColorFileName(String side2ColorFileName) {
		configFileNameProps.setProperty("Side2Color", side2ColorFileName);
	}
	//HotKeys
	public void setStartMatchHotKey(String startMatchHotKey) {
		configHotKeyProps.setProperty("StartMatchHotKey", startMatchHotKey);
	}
	public void setPauseMatchHotKey(String pauseMatchHotKey) {
		configHotKeyProps.setProperty("PauseMatchHotKey", pauseMatchHotKey);
	}
	public void setStartGameHotKey(String startGameHotKey) {
		configHotKeyProps.setProperty("StartGameHotKey", startGameHotKey);
	}
	public void setTeam1ClearHotKey(String team1ClearHotKey) {
		configHotKeyProps.setProperty("Team1ClearHotKey", team1ClearHotKey);
	}
	public void setTeam1SwitchPositionsHotKey(String team1SwitchPositionsHotKey) {
		configHotKeyProps.setProperty("Team1SwitchPositionsHotKey", team1SwitchPositionsHotKey);
	}
	public void setTeam2ClearHotKey(String team2ClearHotKey) {
		configHotKeyProps.setProperty("Team2ClearHotKey", team2ClearHotKey);
	}
	public void setTeam2SwitchPositionsHotKey(String team2SwitchPositionsHotKey) {
		configHotKeyProps.setProperty("Team2SwitchPositionsHotKey", team2SwitchPositionsHotKey);
	}
	public void setSwitchTeamsHotKey(String switchTeamsHotKey) {
		configHotKeyProps.setProperty("SwitchTeamsHotKey", switchTeamsHotKey);
	}
	public void setSwitchPlayer1HotKey(String switchPlayer1HotKey) {
		configHotKeyProps.setProperty("SwitchPlayer1HotKey", switchPlayer1HotKey);
	}
	public void setSwitchPlayer2HotKey(String switchPlayer2HotKey) {
		configHotKeyProps.setProperty("SwitchPlayer2HotKey", switchPlayer2HotKey);
	}
	public void setGameCount1MinusHotKey(String gameCount1MinusHotKey) {
		configHotKeyProps.setProperty("GameCount1MinusHotKey", gameCount1MinusHotKey);
	}
	public void setGameCount1PlusHotKey(String gameCount1PlusHotKey) {
		configHotKeyProps.setProperty("GameCount1PlusHotKey", gameCount1PlusHotKey);
	}
	public void setGameCount2MinusHotKey(String gameCount2MinusHotKey) {
		configHotKeyProps.setProperty("GameCount2MinusHotKey", gameCount2MinusHotKey);
	}
	public void setGameCount2PlusHotKey(String gameCount2PlusHotKey) {
		configHotKeyProps.setProperty("GameCount2PlusHotKey", gameCount2PlusHotKey);
	}
	public void setSwitchGameCountsHotKey(String switchGameCountsHotKey) {
		configHotKeyProps.setProperty("SwitchGameCountsHotKey", switchGameCountsHotKey);
	}
	public void setScore1MinusHotKey(String score1MinusHotKey) {
		configHotKeyProps.setProperty("Score1MinusHotKey", score1MinusHotKey);
	}
	public void setScore1PlusHotKey(String score1PlusHotKey) {
		configHotKeyProps.setProperty("Score1PlusHotKey", score1PlusHotKey);
	}
	public void setScore2MinusHotKey(String score2MinusHotKey) {
		configHotKeyProps.setProperty("Score2MinusHotKey", score2MinusHotKey);
	}
	public void setScore2PlusHotKey(String score2PlusHotKey) {
		configHotKeyProps.setProperty("Score2PlusHotKey", score2PlusHotKey);
	}
	public void setSwitchScoresHotKey(String switchScoresHotKey) {
		configHotKeyProps.setProperty("SwitchScoresHotKey", switchScoresHotKey);
	}
	public void setTimeOut1MinusHotKey(String timeOut1MinusHotKey) {
		configHotKeyProps.setProperty("TimeOut1MinusHotKey", timeOut1MinusHotKey);
	}
	public void setTimeOut1PlusHotKey(String timeOut1PlusHotKey) {
		configHotKeyProps.setProperty("TimeOut1PlusHotKey", timeOut1PlusHotKey);
	}
	public void setTimeOut2MinusHotKey(String timeOut2MinusHotKey) {
		configHotKeyProps.setProperty("TimeOut2MinusHotKey", timeOut2MinusHotKey);
	}
	public void setTimeOut2PlusHotKey(String timeOut2PlusHotKey) {
		configHotKeyProps.setProperty("TimeOut2PlusHotKey", timeOut2PlusHotKey);
	}
	public void setSwitchTimeOutsHotKey(String switchTimeOutsHotKey) {
		configHotKeyProps.setProperty("SwitchTimeOutsHotKey", switchTimeOutsHotKey);
	}
	public void setReset1HotKey(String reset1HotKey) {
		configHotKeyProps.setProperty("Reset1HotKey", reset1HotKey);
	}
	public void setReset2HotKey(String reset2HotKey) {
		configHotKeyProps.setProperty("Reset2HotKey", reset2HotKey);
	}
	public void setWarn1HotKey(String warn1HotKey) {
		configHotKeyProps.setProperty("Warn1HotKey", warn1HotKey);
	}
	public void setWarn2HotKey(String warn2HotKey) {
		configHotKeyProps.setProperty("Warn2HotKey", warn2HotKey);
	}
	public void setSwitchResetWarnsHotKey(String switchResetWarnsHotKey) {
		configHotKeyProps.setProperty("SwitchResetWarnsHotKey", switchResetWarnsHotKey);
	}
	public void setSwitchSidesHotKey(String switchSidesHotKey) {
		configHotKeyProps.setProperty("SwitchSidesHotKey", switchSidesHotKey);
	}
	public void setResetNamesHotKey(String resetNamesHotKey) {
		configHotKeyProps.setProperty("ResetNamesHotKey", resetNamesHotKey);
	}
	public void setResetGameCountsHotKey(String resetGameCountsHotKey) {
		configHotKeyProps.setProperty("ResetGameCountsHotKey", resetGameCountsHotKey);
	}
	public void setResetScoresHotKey(String resetScoresHotKey) {
		configHotKeyProps.setProperty("ResetScoresHotKey", resetScoresHotKey);
	}
	public void setResetTimeOutsHotKey(String resetTimeOutsHotKey) {
		configHotKeyProps.setProperty("ResetTimeOutsHotKey", resetTimeOutsHotKey);
	}
	public void setResetResetWarnHotKey(String resetResetWarnHotKey) {
		configHotKeyProps.setProperty("ResetResetWarnHotKey", resetResetWarnHotKey);
	}
	public void setResetAllHotKey(String resetAllHotKey) {
		configHotKeyProps.setProperty("ResetAllHotKey", resetAllHotKey);
	}
	public void setClearAllHotKey(String clearAllHotKey) {
		configHotKeyProps.setProperty("ClearAllHotKey", clearAllHotKey);
	}
	public void setShotTimerHotKey(String shotTimerHotKey) {
		configHotKeyProps.setProperty("ShotTimerHotKey", shotTimerHotKey);
	}
	public void setPassTimerHotKey(String passTimerHotKey) {
		configHotKeyProps.setProperty("PassTimerHotKey", passTimerHotKey);
	}
	public void setTimeOutTimerHotKey(String timeOutTimerHotKey) {
		configHotKeyProps.setProperty("TimeOutTimerHotKey", timeOutTimerHotKey);
	}
	public void setGameTimerHotKey(String gameTimerHotKey) {
		configHotKeyProps.setProperty("GameTimerHotKey", gameTimerHotKey);
	}
	public void setRecallTimerHotKey(String recallTimerHotKey) {
		configHotKeyProps.setProperty("RecallTimerHotKey", recallTimerHotKey);
	}
	public void setResetTimersHotKey(String resetTimersHotKey) {
		configHotKeyProps.setProperty("ResetTimersHotKey", resetTimersHotKey);
	}
	public void setUndoHotKey(String undoHotKey) {
		configHotKeyProps.setProperty("UndoHotKey", undoHotKey);
	}
	public void setRedoHotKey(String redoHotKey) {
		configHotKeyProps.setProperty("RedoHotKey", redoHotKey);
	}
	public void setConnectHotKey(String connectHotKey) {
		configHotKeyProps.setProperty("ConnectHotKey", connectHotKey);
	}
	public void setDisconnectHotKey(String disconnectHotKey) {
		configHotKeyProps.setProperty("DisconnectHotKey", disconnectHotKey);
	}
	public void setPushHotKey(String pushHotKey) {
		configHotKeyProps.setProperty("PushHotKey", pushHotKey);
	}
	public void setPullHotKey(String pullHotKey) {
		configHotKeyProps.setProperty("PullHotKey", pullHotKey);
	}
	public void setShowScoresHotKey(String showScoresHotKey) {
		configHotKeyProps.setProperty("ShowScoresHotKey", showScoresHotKey);
	}
	public void setShowTimerHotKey(String showTimerHotKey) {
		configHotKeyProps.setProperty("ShowTimerHotKey", showTimerHotKey);
	}
	public void setShowSkunkHotKey(String showSkunkHotKey) {
		configHotKeyProps.setProperty("ShowSkunkHotKey", showSkunkHotKey);
	}
	//AutoScore Settings
	public void setAutoScoreSettingsServerAddress(String autoScoreSettingsServerAddress) {
		configAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerAddress", autoScoreSettingsServerAddress);
	}
	public void setAutoScoreSettingsServerPort(String autoScoreSettingsServerPort) {
		configAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerPort", autoScoreSettingsServerPort);
	}
	public void setAutoScoreSettingsAutoConnect(int autoScoreSettingsAutoConnect) {
		configAutoScoreSettingsProps.setProperty("AutoScoreSettingsAutoConnect", Integer.toString(autoScoreSettingsAutoConnect));
	}
	public void setAutoScoreSettingsDetailLog(int autoScoreSettingsDetailLog) {
		configAutoScoreSettingsProps.setProperty("AutoScoreSettingsDetailLog", Integer.toString(autoScoreSettingsDetailLog));
	}
	//AutoScore Config
	public void setAutoScoreConfigTeam1Pin1(String autoScoreConfigTeam1Pin1) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1Pin1", autoScoreConfigTeam1Pin1);
	}
	public void setAutoScoreConfigTeam1Pin2(String autoScoreConfigTeam1Pin2) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1Pin2", autoScoreConfigTeam1Pin2);
	}
	public void setAutoScoreConfigTeam2Pin1(String autoScoreConfigTeam2Pin1) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2Pin1", autoScoreConfigTeam2Pin1);
	}
	public void setAutoScoreConfigTeam2Pin2(String autoScoreConfigTeam2Pin2) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2Pin2", autoScoreConfigTeam2Pin2);
	}
	public void setAutoScoreConfigTeam1LEDPin(String autoScoreConfigTeam1LEDPin) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam1LEDPin", autoScoreConfigTeam1LEDPin);
	}
	public void setAutoScoreConfigTeam2LEDPin(String autoScoreConfigTeam2LEDPin) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigTeam2LEDPin", autoScoreConfigTeam2LEDPin);
	}
	public void setAutoScoreConfigSwitchPin(String autoScoreConfigSwitchPin) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigSwitchPin", autoScoreConfigSwitchPin);
	}
	public void setAutoScoreConfigResetSeconds(String autoScoreConfigResetSeconds) {
		configAutoScoreConfigProps.setProperty("AutoScoreConfigResetSeconds", autoScoreConfigResetSeconds);
	}
//Get Defaults
	//Control Parameters
	public Boolean getDefaultShowParsed() {return Boolean.parseBoolean(defaultControlProps.getProperty("ShowParsed"));}
	public String getDefaultGameType() {return defaultControlProps.getProperty("GameType");}
	public int getDefaultPointsToWin() {return Integer.parseInt(defaultControlProps.getProperty("PointsToWin"));}
	public int getDefaultMaxWin() {return Integer.parseInt(defaultControlProps.getProperty("MaxWin"));}
	public int getDefaultWinBy() {return Integer.parseInt(defaultControlProps.getProperty("WinBy"));}
	public int getDefaultGamesToWin() {return Integer.parseInt(defaultControlProps.getProperty("GamesToWin"));}
	public int getDefaultMaxTimeOuts() {return Integer.parseInt(defaultControlProps.getProperty("MaxTimeOuts"));}
	public int getDefaultAutoIncrementGame() {return Integer.parseInt(defaultControlProps.getProperty("AutoIncrementGame"));}
	public int getDefaultAnnounceWinner() {return Integer.parseInt(defaultControlProps.getProperty("AnnounceWinner"));}
	public int getDefaultAnnounceMeatball() {return Integer.parseInt(defaultControlProps.getProperty("AnnounceMeatball"));}
	public String getDefaultWinnerPrefix() {return defaultControlProps.getProperty("WinnerPrefix");}
	public String getDefaultWinnerSuffix() {return defaultControlProps.getProperty("WinnerSuffix");}
	public String getDefaultMeatball() {return defaultControlProps.getProperty("Meatball");}
	public String getDefaultTeam1LastScored() {return defaultControlProps.getProperty("Team1LastScored");}
	public String getDefaultTeam2LastScored() {return defaultControlProps.getProperty("Team2LastScored");}
	public String getDefaultClearLastScored() {return defaultControlProps.getProperty("ClearLastScored");}
	public String getDefaultSide1Color() {return defaultControlProps.getProperty("Side1Color");}
	public String getDefaultSide2Color() {return defaultControlProps.getProperty("Side2Color");}
	public String getDefaultNameSeparator() {return defaultControlProps.getProperty("NameSeparator");}
	public int getDefaultShotTime() {return Integer.parseInt(defaultControlProps.getProperty("ShotTime"));}
	public int getDefaultPassTime() {return Integer.parseInt(defaultControlProps.getProperty("PassTime"));}
	public int getDefaultTimeOutTime() {return Integer.parseInt(defaultControlProps.getProperty("TimeOutTime"));}
	public int getDefaultGameTime() {return Integer.parseInt(defaultControlProps.getProperty("GameTime"));}
	public int getDefaultRecallTime() {return Integer.parseInt(defaultControlProps.getProperty("RecallTime"));}
	public int getDefaultShowTimeOutsUsed() {return Integer.parseInt(defaultControlProps.getProperty("ShowTimeOutsUsed"));}
	public int getDefaultAutoCapNames() {return Integer.parseInt(defaultControlProps.getProperty("AutoCapNames"));}
	public int getDefaultWinByFinalOnly() {return Integer.parseInt(defaultControlProps.getProperty("WinByFinalOnly"));}
	public int getDefaultShowSkunk() {return Integer.parseInt(defaultControlProps.getProperty("ShowSkunk"));}
	public int getDefaultCutThroatMode() {return Integer.parseInt(defaultControlProps.getProperty("CutThroatMode"));}
	//OBS
	public String getDefaultOBSHost() {return defaultOBSProps.getProperty("OBSHost");}
	public String getDefaultOBSPort() {return defaultOBSProps.getProperty("OBSPort");}
	public String getDefaultOBSPassword() {return defaultOBSProps.getProperty("OBSPassword");}
	public String getDefaultOBSScene() {return defaultOBSProps.getProperty("OBSScene");}
	public int getDefaultOBSAutoLogin() {return Integer.parseInt(defaultOBSProps.getProperty("OBSAutoLogin"));}
	public int getDefaultOBSSavePassword() {return Integer.parseInt(defaultOBSProps.getProperty("OBSSavePassword"));}
	public int getDefaultOBSCloseOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSCloseOnConnect"));}
	public int getDefaultOBSUpdateOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSUpdateOnConnect"));}
	public String getDefaultOBSScoreSource() {return defaultOBSProps.getProperty("OBSScoreSource");}
	public String getDefaultOBSTimerSource() {return defaultOBSProps.getProperty("OBSTimerSource");}
	public String getDefaultOBSSkunkFilter() {return defaultOBSProps.getProperty("OBSSkunkFilter");}
	//Sources
	public String getDefaultTableSource() {return defaultSourceProps.getProperty("Table");}
	public String getDefaultTeam1NameSource() {return defaultSourceProps.getProperty("Team1Name");}
	public String getDefaultTeam1ForwardSource() {return defaultSourceProps.getProperty("Team1Forward");}
	public String getDefaultTeam1GoalieSource() {return defaultSourceProps.getProperty("Team1Goalie");}
	public String getDefaultTournamentSource() {return defaultSourceProps.getProperty("Tournament");}
	public String getDefaultTeam2NameSource() {return defaultSourceProps.getProperty("Team2Name");}
	public String getDefaultTeam2ForwardSource() {return defaultSourceProps.getProperty("Team2Forward");}
	public String getDefaultTeam2GoalieSource() {return defaultSourceProps.getProperty("Team2Goalie");}
	public String getDefaultEventSource() {return defaultSourceProps.getProperty("Event");}
	public String getDefaultTableNameSource() {return defaultSourceProps.getProperty("TableName");}
	public String getDefaultGameCount1Source() {return defaultSourceProps.getProperty("GameCount1");}
	public String getDefaultTimeRemainingSource() {return defaultSourceProps.getProperty("TimeRemaining");}
	public String getDefaultGameCount2Source() {return defaultSourceProps.getProperty("GameCount2");}
	public String getDefaultTimerInUseSource() {return defaultSourceProps.getProperty("TimerInUse");}
	public String getDefaultScore1Source() {return defaultSourceProps.getProperty("Score1");}
	public String getDefaultMatchWinnerSource() {return defaultSourceProps.getProperty("MatchWinner");}
	public String getDefaultMeatballSource() {return defaultSourceProps.getProperty("Meatball");}
	public String getDefaultScore2Source() {return defaultSourceProps.getProperty("Score2");}
	public String getDefaultTimeOut1Source() {return defaultSourceProps.getProperty("TimeOut1");}
	public String getDefaultTimeOut2Source() {return defaultSourceProps.getProperty("TimeOut2");}
	public String getDefaultReset1Source() {return defaultSourceProps.getProperty("Reset1");}
	public String getDefaultReset2Source() {return defaultSourceProps.getProperty("Reset2");}
	public String getDefaultWarn1Source() {return defaultSourceProps.getProperty("Warn1");}
	public String getDefaultWarn2Source() {return defaultSourceProps.getProperty("Warn2");}
	public String getDefaultLastScoredSource() {return defaultSourceProps.getProperty("LastScored");}
	public String getDefaultGameTimeSource() {return defaultSourceProps.getProperty("GameTime");}
	public String getDefaultMatchTimeSource() {return defaultSourceProps.getProperty("MatchTime");}
	public String getDefaultStuffs1Source() {return defaultSourceProps.getProperty("Stuffs1");}
	public String getDefaultStuffs2Source() {return defaultSourceProps.getProperty("Stuffs2");}
	public String getDefaultBreaks1Source() {return defaultSourceProps.getProperty("Breaks1");}
	public String getDefaultBreaks2Source() {return defaultSourceProps.getProperty("Breaks2");}
	public String getDefaultAces1Source() {return defaultSourceProps.getProperty("Aces1");}
	public String getDefaultAces2Source() {return defaultSourceProps.getProperty("Aces2");}
	public String getDefaultTeam1PassAttemptsSource() {return defaultSourceProps.getProperty("Team1PassAttempts");}
	public String getDefaultTeam1PassCompletesSource() {return defaultSourceProps.getProperty("Team1PassCompletes");}
	public String getDefaultTeam2PassAttemptsSource() {return defaultSourceProps.getProperty("Team2PassAttempts");}
	public String getDefaultTeam2PassCompletesSource() {return defaultSourceProps.getProperty("Team2PassCompletes");}
	public String getDefaultTeam1ShotAttemptsSource() {return defaultSourceProps.getProperty("Team1ShotAttempts");}
	public String getDefaultTeam1ShotCompletesSource() {return defaultSourceProps.getProperty("Team1ShotCompletes");}
	public String getDefaultTeam2ShotAttemptsSource() {return defaultSourceProps.getProperty("Team2ShotAttempts");}
	public String getDefaultTeam2ShotCompletesSource() {return defaultSourceProps.getProperty("Team2ShotCompletes");}
	public String getDefaultTeam1ClearAttemptsSource() {return defaultSourceProps.getProperty("Team1ClearAttempts");}
	public String getDefaultTeam1ClearCompletesSource() {return defaultSourceProps.getProperty("Team1ClearCompletes");}
	public String getDefaultTeam2ClearAttemptsSource() {return defaultSourceProps.getProperty("Team2ClearAttempts");}
	public String getDefaultTeam2ClearCompletesSource() {return defaultSourceProps.getProperty("Team2ClearCompletes");}
	public String getDefaultSide1ColorSource() {return defaultSourceProps.getProperty("Side1Color");}
	public String getDefaultSide2ColorSource() {return defaultSourceProps.getProperty("Side2Color");}
	public String getDefaultTeam1PassPercentSource() {return defaultSourceProps.getProperty("Team1PassPercent");}
	public String getDefaultTeam2PassPercentSource() {return defaultSourceProps.getProperty("Team2PassPercent");}
	public String getDefaultTeam1ShotPercentSource() {return defaultSourceProps.getProperty("Team1ShotPercent");}
	public String getDefaultTeam2ShotPercentSource() {return defaultSourceProps.getProperty("Team2ShotPercent");}
	public String getDefaultTeam1ClearPercentSource() {return defaultSourceProps.getProperty("Team1ClearPercent");}
	public String getDefaultTeam2ClearPercentSource() {return defaultSourceProps.getProperty("Team2ClearPercent");}
	public String getDefaultTeam1TwoBarPassAttemptsSource() {return defaultSourceProps.getProperty("Team1TwoBarPassAttempts");}
	public String getDefaultTeam1TwoBarPassCompletesSource() {return defaultSourceProps.getProperty("Team1TwoBarPassCompletes");}
	public String getDefaultTeam2TwoBarPassAttemptsSource() {return defaultSourceProps.getProperty("Team2TwoBarPassAttempts");}
	public String getDefaultTeam2TwoBarPassCompletesSource() {return defaultSourceProps.getProperty("Team2TwoBarPassCompletes");}
	public String getDefaultTeam1TwoBarPassPercentSource() {return defaultSourceProps.getProperty("Team1TwoBarPassPercent");}
	public String getDefaultTeam2TwoBarPassPercentSource() {return defaultSourceProps.getProperty("Team2TwoBarPassPercent");}
	public String getDefaultTeam1ScoringSource() {return defaultSourceProps.getProperty("Team1Scoring");}
	public String getDefaultTeam2ScoringSource() {return defaultSourceProps.getProperty("Team2Scoring");}
	public String getDefaultTeam1ThreeBarScoringSource() {return defaultSourceProps.getProperty("Team1ThreeBarScoring");}
	public String getDefaultTeam2ThreeBarScoringSource() {return defaultSourceProps.getProperty("Team2ThreeBarScoring");}
	public String getDefaultTeam1FiveBarScoringSource() {return defaultSourceProps.getProperty("Team1FiveBarScoring");}
	public String getDefaultTeam2FiveBarScoringSource() {return defaultSourceProps.getProperty("Team2FiveBarScoring");}
	public String getDefaultTeam1TwoBarScoringSource() {return defaultSourceProps.getProperty("Team1TwoBarScoring");}	
	public String getDefaultTeam2TwoBarScoringSource() {return defaultSourceProps.getProperty("Team2TwoBarScoring");}	
	public String getDefaultTeam1ShotsOnGoalSource() {return defaultSourceProps.getProperty("Team1ShotsOnGoal");}
	public String getDefaultTeam2ShotsOnGoalSource() {return defaultSourceProps.getProperty("Team2ShotsOnGoal");}
	//FileNames
	public String getDefaultTableFileName() {return defaultFileNameProps.getProperty("Table");}
	public String getDefaultTeam1NameFileName() {return defaultFileNameProps.getProperty("Team1Name");}
	public String getDefaultTeam1ForwardFileName() {return defaultFileNameProps.getProperty("Team1Forward");}
	public String getDefaultTeam1GoalieFileName() {return defaultFileNameProps.getProperty("Team1Goalie");}
	public String getDefaultTournamentFileName() {return defaultFileNameProps.getProperty("Tournament");}
	public String getDefaultTeam2NameFileName() {return defaultFileNameProps.getProperty("Team2Name");}
	public String getDefaultTeam2ForwardFileName() {return defaultFileNameProps.getProperty("Team2Forward");}
	public String getDefaultTeam2GoalieFileName() {return defaultFileNameProps.getProperty("Team2Goalie");}
	public String getDefaultEventFileName() {return defaultFileNameProps.getProperty("Event");}
	public String getDefaultTableNameFileName() {return defaultFileNameProps.getProperty("TableName");}
	public String getDefaultGameCount1FileName() {return defaultFileNameProps.getProperty("GameCount1");}
	public String getDefaultTimeRemainingFileName() {return defaultFileNameProps.getProperty("TimeRemaining");}
	public String getDefaultGameCount2FileName() {return defaultFileNameProps.getProperty("GameCount2");}
	public String getDefaultTimerInUseFileName() {return defaultFileNameProps.getProperty("TimerInUse");}
	public String getDefaultScore1FileName() {return defaultFileNameProps.getProperty("Score1");}
	public String getDefaultMatchWinnerFileName() {return defaultFileNameProps.getProperty("MatchWinner");}
	public String getDefaultMeatballFileName() {return defaultFileNameProps.getProperty("Meatball");}
	public String getDefaultScore2FileName() {return defaultFileNameProps.getProperty("Score2");}
	public String getDefaultTimeOut1FileName() {return defaultFileNameProps.getProperty("TimeOut1");}
	public String getDefaultTimeOut2FileName() {return defaultFileNameProps.getProperty("TimeOut2");}
	public String getDefaultReset1FileName() {return defaultFileNameProps.getProperty("Reset1");}
	public String getDefaultReset2FileName() {return defaultFileNameProps.getProperty("Reset2");}
	public String getDefaultWarn1FileName() {return defaultFileNameProps.getProperty("Warn1");}
	public String getDefaultWarn2FileName() {return defaultFileNameProps.getProperty("Warn2");}
	public String getDefaultLastScoredFileName() {return defaultFileNameProps.getProperty("LastScored");}
	public String getDefaultGameTimeFileName() {return defaultFileNameProps.getProperty("GameTime");}
	public String getDefaultMatchTimeFileName() {return defaultFileNameProps.getProperty("MatchTime");}
	public String getDefaultStuffs1FileName() {return defaultFileNameProps.getProperty("Stuffs1");}
	public String getDefaultStuffs2FileName() {return defaultFileNameProps.getProperty("Stuffs2");}
	public String getDefaultBreaks1FileName() {return defaultFileNameProps.getProperty("Breaks1");}
	public String getDefaultBreaks2FileName() {return defaultFileNameProps.getProperty("Breaks2");}
	public String getDefaultAces1FileName() {return defaultFileNameProps.getProperty("Aces1");}
	public String getDefaultAces2FileName() {return defaultFileNameProps.getProperty("Aces2");}
	public String getDefaultTeam1PassAttemptsFileName() {return defaultFileNameProps.getProperty("Team1PassAttempts");}
	public String getDefaultTeam1PassCompletesFileName() {return defaultFileNameProps.getProperty("Team1PassCompletes");}
	public String getDefaultTeam2PassAttemptsFileName() {return defaultFileNameProps.getProperty("Team2PassAttempts");}
	public String getDefaultTeam2PassCompletesFileName() {return defaultFileNameProps.getProperty("Team2PassCompletes");}
	public String getDefaultTeam1ShotAttemptsFileName() {return defaultFileNameProps.getProperty("Team1ShotAttempts");}
	public String getDefaultTeam1ShotCompletesFileName() {return defaultFileNameProps.getProperty("Team1ShotCompletes");}
	public String getDefaultTeam2ShotAttemptsFileName() {return defaultFileNameProps.getProperty("Team2ShotAttempts");}
	public String getDefaultTeam2ShotCompletesFileName() {return defaultFileNameProps.getProperty("Team2ShotCompletes");}
	public String getDefaultTeam1ClearAttemptsFileName() {return defaultFileNameProps.getProperty("Team1ClearAttempts");}
	public String getDefaultTeam1ClearCompletesFileName() {return defaultFileNameProps.getProperty("Team1ClearCompletes");}
	public String getDefaultTeam2ClearAttemptsFileName() {return defaultFileNameProps.getProperty("Team2ClearAttempts");}
	public String getDefaultTeam2ClearCompletesFileName() {return defaultFileNameProps.getProperty("Team2ClearCompletes");}
	public String getDefaultSide1ColorFileName() {return defaultFileNameProps.getProperty("Side1Color");}
	public String getDefaultSide2ColorFileName() {return defaultFileNameProps.getProperty("Side2Color");}
	public String getDefaultTeam1PassPercentFileName() {return defaultFileNameProps.getProperty("Team1PassPercent");}
	public String getDefaultTeam2PassPercentFileName() {return defaultFileNameProps.getProperty("Team2PassPercent");}
	public String getDefaultTeam1ShotPercentFileName() {return defaultFileNameProps.getProperty("Team1ShotPercent");}
	public String getDefaultTeam2ShotPercentFileName() {return defaultFileNameProps.getProperty("Team2ShotPercent");}
	public String getDefaultTeam1ClearPercentFileName() {return defaultFileNameProps.getProperty("Team1ClearPercent");}
	public String getDefaultTeam2ClearPercentFileName() {return defaultFileNameProps.getProperty("Team2ClearPercent");}
	public String getDefaultTeam1TwoBarPassAttemptsFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassAttempts");}
	public String getDefaultTeam1TwoBarPassCompletesFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassCompletes");}
	public String getDefaultTeam2TwoBarPassAttemptsFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassAttempts");}
	public String getDefaultTeam2TwoBarPassCompletesFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassCompletes");}
	public String getDefaultTeam1TwoBarPassPercentFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassPercent");}
	public String getDefaultTeam2TwoBarPassPercentFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassPercent");}
	public String getDefaultTeam1ScoringFileName() {return defaultFileNameProps.getProperty("Team1Scoring");}
	public String getDefaultTeam2ScoringFileName() {return defaultFileNameProps.getProperty("Team2Scoring");}
	public String getDefaultTeam1ThreeBarScoringFileName() {return defaultFileNameProps.getProperty("Team1ThreeBarScoring");}
	public String getDefaultTeam2ThreeBarScoringFileName() {return defaultFileNameProps.getProperty("Team2ThreeBarScoring");}
	public String getDefaultTeam1FiveBarScoringFileName() {return defaultFileNameProps.getProperty("Team1FiveBarScoring");}
	public String getDefaultTeam2FiveBarScoringFileName() {return defaultFileNameProps.getProperty("Team2FiveBarScoring");}
	public String getDefaultTeam1TwoBarScoringFileName() {return defaultFileNameProps.getProperty("Team1TwoBarScoring");}	
	public String getDefaultTeam2TwoBarScoringFileName() {return defaultFileNameProps.getProperty("Team2TwoBarScoring");}	
	public String getDefaultTeam1ShotsOnGoalFileName() {return defaultFileNameProps.getProperty("Team1ShotsOnGoal");}
	public String getDefaultTeam2ShotsOnGoalFileName() {return defaultFileNameProps.getProperty("Team2ShotsOnGoal");}
	//HotKeys
	public String getDefaultStartMatchHotKey() {return defaultHotKeyProps.getProperty("StartMatchHotKey");}
	public String getDefaultPauseMatchHotKey() {return defaultHotKeyProps.getProperty("PauseMatchHotKey");}
	public String getDefaultStartGameHotKey() {return defaultHotKeyProps.getProperty("StartGameHotKey");}
	public String getDefaultTeam1ClearHotKey() {return defaultHotKeyProps.getProperty("Team1ClearHotKey");}
	public String getDefaultTeam1SwitchPositionsHotKey() {return defaultHotKeyProps.getProperty("Team1SwitchPositionsHotKey");}
	public String getDefaultTeam2ClearHotKey() {return defaultHotKeyProps.getProperty("Team2ClearHotKey");}
	public String getDefaultTeam2SwitchPositionsHotKey() {return defaultHotKeyProps.getProperty("Team2SwitchPositionsHotKey");}
	public String getDefaultSwitchTeamsHotKey() {return defaultHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public String getDefaultSwitchPlayer1HotKey() {return defaultHotKeyProps.getProperty("SwitchPlayer1HotKey");}
	public String getDefaultSwitchPlayer2HotKey() {return defaultHotKeyProps.getProperty("SwitchPlayer2HotKey");}
	public String getDefaultGameCount1MinusHotKey() {return defaultHotKeyProps.getProperty("GameCount1MinusHotKey");}
	public String getDefaultGameCount1PlusHotKey() {return defaultHotKeyProps.getProperty("GameCount1PlusHotKey");}
	public String getDefaultGameCount2MinusHotKey() {return defaultHotKeyProps.getProperty("GameCount2MinusHotKey");}
	public String getDefaultGameCount2PlusHotKey() {return defaultHotKeyProps.getProperty("GameCount2PlusHotKey");}
	public String getDefaultSwitchGameCountsHotKey() {return defaultHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public String getDefaultScore1MinusHotKey() {return defaultHotKeyProps.getProperty("Score1MinusHotKey");}
	public String getDefaultScore1PlusHotKey() {return defaultHotKeyProps.getProperty("Score1PlusHotKey");}
	public String getDefaultScore2MinusHotKey() {return defaultHotKeyProps.getProperty("Score2MinusHotKey");}
	public String getDefaultScore2PlusHotKey() {return defaultHotKeyProps.getProperty("Score2PlusHotKey");}
	public String getDefaultSwitchScoresHotKey() {return defaultHotKeyProps.getProperty("SwitchScoresHotKey");}
	public String getDefaultTimeOut1MinusHotKey() {return defaultHotKeyProps.getProperty("TimeOut1MinusHotKey");}
	public String getDefaultTimeOut1PlusHotKey() {return defaultHotKeyProps.getProperty("TimeOut1PlusHotKey");}
	public String getDefaultTimeOut2MinusHotKey() {return defaultHotKeyProps.getProperty("TimeOut2MinusHotKey");}
	public String getDefaultTimeOut2PlusHotKey() {return defaultHotKeyProps.getProperty("TimeOut2PlusHotKey");}
	public String getDefaultSwitchTimeOutsHotKey() {return defaultHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
	public String getDefaultReset1HotKey() {return defaultHotKeyProps.getProperty("Reset1HotKey");}
	public String getDefaultReset2HotKey() {return defaultHotKeyProps.getProperty("Reset2HotKey");}
	public String getDefaultWarn1HotKey() {return defaultHotKeyProps.getProperty("Warn1HotKey");}
	public String getDefaultWarn2HotKey() {return defaultHotKeyProps.getProperty("Warn2HotKey");}
	public String getDefaultSwitchResetWarnsHotKey() {return defaultHotKeyProps.getProperty("SwitchResetWarnsHotKey");}
	public String getDefaultSwitchPositionsHotKey() {return defaultHotKeyProps.getProperty("SwitchSidesHotKey");}
	public String getDefaultResetNamesHotKey() {return defaultHotKeyProps.getProperty("resetNamesHotKey");}
	public String getDefaultResetGameCountsHotKey() {return defaultHotKeyProps.getProperty("ResetGameCountsHotKey");}
	public String getDefaultResetScoresHotKey() {return defaultHotKeyProps.getProperty("ResetScoresHotKey");}
	public String getDefaultResetTimeOutsHotKey() {return defaultHotKeyProps.getProperty("ResetTimeOutsHotKey");}
	public String getDefaultResetResetWarnHotKey() {return defaultHotKeyProps.getProperty("ResetResetWarnHotKey");}
	public String getDefaultResetAllHotKey() {return defaultHotKeyProps.getProperty("ResetAllHotKey");}
	public String getDefaultClearAllHotKey() {return defaultHotKeyProps.getProperty("ClearAllHotKey");}
	public String getDefaultShotTimerHotKey() {return defaultHotKeyProps.getProperty("ShotTimerHotKey");}
	public String getDefaultPassTimerHotKey() {return defaultHotKeyProps.getProperty("PassTimerHotKey");}
	public String getDefaultTimeOutTimerHotKey() {return defaultHotKeyProps.getProperty("TimeOutTimerHotKey");}
	public String getDefaultGameTimerHotKey() {return defaultHotKeyProps.getProperty("GameTimerHotKey");}
	public String getDefaultRecallTimerHotKey() {return defaultHotKeyProps.getProperty("RecallTimerHotKey");}
	public String getDefaultResetTimersHotKey() {return defaultHotKeyProps.getProperty("ResetTimersHotKey");}
	public String getDefaultUndoHotKey() {return defaultHotKeyProps.getProperty("UndoHotKey");}
	public String getDefaultRedoHotKey() {return defaultHotKeyProps.getProperty("RedoHotKey");}
	public String getConnectDefaultHotKey() {return defaultHotKeyProps.getProperty("ConnectHotKey");}
	public String getDisconnectDefaultHotKey() {return defaultHotKeyProps.getProperty("DisconnectHotKey");}
	public String getPushDefaultHotKey() {return defaultHotKeyProps.getProperty("PushHotKey");}
	public String getPullDefaultHotKey() {return defaultHotKeyProps.getProperty("PullHotKey");}
	public String getDefaultShowScoresHotKey() {return defaultHotKeyProps.getProperty("ShowScoresHotKey");}
	public String getDefaultShowTimerHotKey() {return defaultHotKeyProps.getProperty("ShowTimerHotKey");}
	public String getDefaultShowSkunkHotKey() {return defaultHotKeyProps.getProperty("ShowSkunkHotKey");}
	//AutoScore Settings
	public String getDefaultAutoScoreSettingsServerAddress() {return defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerAddress");}
	public int getDefaultAutoScoreSettingsServerPort() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerPort"));}
	public int getDefaultAutoScoreSettingsAutoConnect() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsAutoConnect"));}
	public int getDefaultAutoScoreSettingsDetailLog() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsDetailLog"));}
	//AutoScore Config
	public int getDefaultAutoScoreConfigTeam1Pin1() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1Pin1"));}
	public int getDefaultAutoScoreConfigTeam1Pin2() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1Pin2"));}
	public int getDefaultAutoScoreConfigTeam2Pin1() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2Pin1"));}
	public int getDefaultAutoScoreConfigTeam2Pin2() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2Pin2"));}
	public int getDefaultAutoScoreConfigTeam1LEDPin() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam1LEDPin"));}
	public int getDefaultAutoScoreConfigTeam2LEDPin() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigTeam2LEDPin"));}
	public int getDefaultAutoScoreConfigSwitchPin() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigSwitchPin"));}
	public int getDefaultAutoScoreConfigResetSeconds() {return Integer.parseInt(defaultAutoScoreConfigProps.getProperty("AutoScoreConfigResetSeconds"));}

	public void loadFromControlConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configControlFileName))) {
			configControlProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configControlFileName));
			configControlProps = defaultControlProps;
			saveControlConfig();
//			loadFromControlConfig();
		}
	}
	public void loadFromOBSConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configOBSFileName))) {
			configOBSProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configOBSFileName));
			configOBSProps = defaultOBSProps;
			saveOBSConfig();
//			loadFromOBSConfig();
		}
	}
	public void loadFromSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configSourceFileName))) {
			configSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configSourceFileName));
			configSourceProps = defaultSourceProps;
			saveSourceConfig();
//			loadFromSourceConfig();
		}
	}
	public void loadFromFileNameConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configFileNameFileName))) {
			configFileNameProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configFileNameFileName));
			configFileNameProps = defaultFileNameProps;
			saveFileNameConfig();
//			loadFromFileNameConfig();
		}
	}
	public void loadFromHotKeyConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configHotKeyFileName));
			configHotKeyProps = defaultHotKeyProps;
			saveHotKeyConfig();
//			loadFromHotKeyConfig();
		}
	}
	public void loadFromAutoScoreSettingsConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configAutoScoreSettingsFileName));
			configAutoScoreSettingsProps = defaultAutoScoreSettingsProps;
			saveAutoScoreSettingsConfig();
		}
	}
	public void loadFromAutoScoreConfigConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configAutoScoreConfigFileName))) {
			configAutoScoreConfigProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configAutoScoreConfigFileName));
			configAutoScoreConfigProps = defaultAutoScoreConfigProps;
			saveAutoScoreConfigConfig();
		}
	}
	public void saveControlConfig() throws IOException {
		//Control Parameters
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configControlFileName))) {
			configControlProps.store(outputStream, "FoosOBSPlus Control settings");
		}
	}
	public void saveOBSConfig() throws IOException {
		//OBS
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configOBSFileName))) {
			configOBSProps.store(outputStream, "FoosOBSPlus OBS Settings");
		}
	}
	public void saveSourceConfig() throws IOException {
		//Source
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configSourceFileName))) {
			configSourceProps.store(outputStream, "FoosOBSPlus Source Settings");
		}
	}
	public void saveFileNameConfig() throws IOException {
		//FileNames
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configFileNameFileName))) {
			configFileNameProps.store(outputStream, "FoosOBSPlus File Name Settings");
		}
	}
	public void saveHotKeyConfig() throws IOException {
		//HotKeys
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.store(outputStream, "FoosOBSPlus Hot Key Settings");
		}
	}
	public void saveAutoScoreSettingsConfig() throws IOException {
		//AutoScore Settings
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.store(outputStream, "FoosOBSPlus AutoScore Settings");
		}
	}
	public void saveAutoScoreConfigConfig() throws IOException {
		//AutoScore Config
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configAutoScoreConfigFileName))) {
			configAutoScoreConfigProps.store(outputStream, "FoosOBSPlus AutoScore Config");
		}
	}
}
