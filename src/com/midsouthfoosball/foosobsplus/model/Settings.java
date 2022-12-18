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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.midsouthfoosball.foosobsplus.view.Messages;

public class Settings {
	
// Parameter settings
	private String separator 			= FileSystems.getDefault().getSeparator();
	private String[] lastScoredStrings 	= new String[3];
	private final String gameType       = "Foosball";

	// Property Settings
	private Properties defaultControlProps;
	private Properties defaultSourceProps;
	private Properties defaultFilterProps;
	private Properties defaultPartnerProgramProps;
	private Properties defaultHotKeyProps;
	private Properties defaultOBSProps;
	private Properties defaultAutoScoreSettingsProps;
	private Properties defaultAutoScoreConfigProps;
	
	public Properties configControlProps;
	public Properties configSourceProps;
	public Properties configFilterProps;
	public Properties configPartnerProgramProps;
	public Properties configHotKeyProps;
	public Properties configOBSProps;
	public Properties configAutoScoreSettingsProps;
	public Properties configAutoScoreConfigProps;
	
	private String configControlFileName 			= "control.properties";
	private String configSourceFileName 			= "source.properties";
	private String configFilterFileName             = "filter.properties";
	private String configPartnerProgramFileName     = "partnerprogram.properties";
	private String configHotKeyFileName 			= "hotkey.properties";
	private String configOBSFileName        		= "obs.properties";
	private String configAutoScoreSettingsFileName	= "autoscoresettings.properties";
	private String configAutoScoreConfigFileName	= "autoscoreconfig.properties";

	//////////////////////////////////////////////////////
	
	public Settings() throws IOException {
		defaultControlProps 			= new Properties();
		defaultSourceProps 				= new Properties();
		defaultFilterProps              = new Properties();
		defaultPartnerProgramProps      = new Properties();
		defaultHotKeyProps 				= new Properties();
		defaultOBSProps         		= new Properties();
		defaultAutoScoreSettingsProps	= new Properties();
		defaultAutoScoreConfigProps		= new Properties();
		// sets default properties
		// Parameter settings
		defaultControlProps.setProperty("ShowParsed", "true");
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
		defaultControlProps.setProperty("BallsInRack", "9");
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
		defaultControlProps.setProperty("RackMode", "0");
		defaultControlProps.setProperty("LogoImageURL", "/imgs/MidsouthFoosballLogo4.png");
		defaultControlProps.setProperty("LogoLinkURI", "https://www.facebook.com/midsouthfoosball");
		//OBS
		defaultOBSProps.setProperty("OBSHost", "localhost");
		defaultOBSProps.setProperty("OBSPort", "4455");
		defaultOBSProps.setProperty("OBSPassword","");
		defaultOBSProps.setProperty("OBSScene", "FoosObs+ Main");
		defaultOBSProps.setProperty("OBSAutoLogin", "0");
		defaultOBSProps.setProperty("OBSSavePassword", "0");
		defaultOBSProps.setProperty("OBSCloseOnConnect", "1");
		defaultOBSProps.setProperty("OBSUpdateOnConnect",  "1");
		//Sources
		defaultSourceProps.setProperty("Tournament", "tablename");
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
		defaultSourceProps.setProperty("ShowScoresSource", "ScoresAndLabels");
		defaultSourceProps.setProperty("ShowTimerSource", "Foos OBS+ Timer");
		//Filters
		defaultFilterProps.setProperty("Team1Score", "team1score");
		defaultFilterProps.setProperty("Team2Score", "team2score");
		defaultFilterProps.setProperty("Team1WinGame", "team1wingame");
		defaultFilterProps.setProperty("Team2WinGame", "team2wingame");
		defaultFilterProps.setProperty("Team1WinMatch", "team1winmatch");
		defaultFilterProps.setProperty("Team2WinMatch", "team2winmatch");
		defaultFilterProps.setProperty("Team1TimeOut", "team1timeout");
		defaultFilterProps.setProperty("Team2TimeOut", "team2timeout");
		defaultFilterProps.setProperty("Team1Reset", "team1reset");
		defaultFilterProps.setProperty("Team2Reset", "team2reset");
		defaultFilterProps.setProperty("Team1Warn", "team1warn");
		defaultFilterProps.setProperty("Team2Warn", "team2warn");
		defaultFilterProps.setProperty("Team1SwitchPositions", "team1switchpositions");
		defaultFilterProps.setProperty("Team2SwitchPositions", "team2switchpositions");
		defaultFilterProps.setProperty("Team1Skunk", "team1skunk");
		defaultFilterProps.setProperty("Team2Skunk", "team2skunk");
		defaultFilterProps.setProperty("StartMatch", "startmatch");
		defaultFilterProps.setProperty("StartGame", "startgame");
		defaultFilterProps.setProperty("SwitchSides", "switchsides");
		//PartnerProgram
		defaultPartnerProgramProps.setProperty("PartnerProgramPath", "C:\\FoosTourney");
		defaultPartnerProgramProps.setProperty("Player1FileName", "Player1.txt");
		defaultPartnerProgramProps.setProperty("Player2FileName", "Player2.txt");
		defaultPartnerProgramProps.setProperty("Player3FileName", "Player3.txt");
		defaultPartnerProgramProps.setProperty("Player4FileName", "Player4.txt");
		//HotKeys
		defaultHotKeyProps.setProperty("StartMatchHotKey", "b");
		defaultHotKeyProps.setProperty("PauseMatchHotKey", ",");
		defaultHotKeyProps.setProperty("StartGameHotKey", ".");
		defaultHotKeyProps.setProperty("TournamentNameClearHotKey", "");
		defaultHotKeyProps.setProperty("Team1ClearHotKey", "f");
		defaultHotKeyProps.setProperty("Team1SwitchPositionsHotKey", "t");
		defaultHotKeyProps.setProperty("Team2ClearHotKey", "h");
		defaultHotKeyProps.setProperty("Team2SwitchPositionsHotKey", "m");
		defaultHotKeyProps.setProperty("SwitchTeamsHotKey", "e");
		defaultHotKeyProps.setProperty("SwitchPlayer1HotKey", ";");
		defaultHotKeyProps.setProperty("SwitchPlayer2HotKey", "x");
		defaultHotKeyProps.setProperty("GameCount1MinusHotKey", "j");
		defaultHotKeyProps.setProperty("GameCount1PlusHotKey", "5");
		defaultHotKeyProps.setProperty("GameCount2MinusHotKey", "i");
		defaultHotKeyProps.setProperty("GameCount2PlusHotKey", "6");
		defaultHotKeyProps.setProperty("GameCountSwitchHotKey", "l");
		defaultHotKeyProps.setProperty("Score1MinusHotKey", "4");
		defaultHotKeyProps.setProperty("Score1PlusHotKey", "1");
		defaultHotKeyProps.setProperty("Score2MinusHotKey", "8");
		defaultHotKeyProps.setProperty("Score2PlusHotKey", "2");
		defaultHotKeyProps.setProperty("SwitchScoresHotKey", "");
		defaultHotKeyProps.setProperty("SwitchGameCountsHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut1MinusHotKey", "n");
		defaultHotKeyProps.setProperty("TimeOut1PlusHotKey", "9");
		defaultHotKeyProps.setProperty("TimeOut2MinusHotKey", "q");
		defaultHotKeyProps.setProperty("TimeOut2PlusHotKey", "0");
		defaultHotKeyProps.setProperty("SwitchTimeOutsHotKey", "[");
		defaultHotKeyProps.setProperty("Reset1HotKey", "");
		defaultHotKeyProps.setProperty("Reset2HotKey", "");
		defaultHotKeyProps.setProperty("Warn1HotKey", "");
		defaultHotKeyProps.setProperty("Warn2HotKey", "");
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
		defaultHotKeyProps.setProperty("StartStreamHotKey", "z");
		defaultHotKeyProps.setProperty("AutoScoreMainConnectHotKey", "");
		defaultHotKeyProps.setProperty("AutoScoreMainDisconnectHotKey", "");
		defaultHotKeyProps.setProperty("AutoScoreMainSettingsHotKey", "");
		defaultHotKeyProps.setProperty("HotKeyBaseScript", Messages.getString("Settings.HotKeyBaseScript"));
		defaultHotKeyProps.setProperty("HotKeyScriptPath", "C:\\FoosOBSPlusScripts\\");
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
		configFilterProps               = new Properties(defaultFilterProps);
		configPartnerProgramProps       = new Properties(defaultPartnerProgramProps);
		configHotKeyProps 				= new Properties(defaultHotKeyProps);
		configOBSProps      			= new Properties(defaultOBSProps);
		configAutoScoreSettingsProps	= new Properties(defaultAutoScoreSettingsProps);
		configAutoScoreConfigProps		= new Properties(defaultAutoScoreConfigProps);
		
		loadFromControlConfig();
		loadFromOBSConfig();
		loadFromSourceConfig();
		loadFromFilterConfig();
		loadFromPartnerProgramConfig();
		loadFromHotKeyConfig();
		loadFromAutoScoreSettingsConfig();
		loadFromAutoScoreConfigConfig();
	}

	//Getters
	//Control Parameters
	public String getGameType() {return gameType;}
	public boolean getShowParsed() {return Boolean.parseBoolean(configControlProps.getProperty("ShowParsed"));}
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
	public int getBallsInRack() {return Integer.parseInt(configControlProps.getProperty("BallsInRack"));}
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
	public int getRackMode() {return Integer.parseInt(configControlProps.getProperty("RackMode"));}
	public String getLogoImageURL() {return configControlProps.getProperty("LogoImageURL");}
	public String getLogoLinkURI() {return configControlProps.getProperty("LogoLinkURI");}
	//OBS
	public String getOBSHost() {return configOBSProps.getProperty("OBSHost");}
	public String getOBSPort() {return configOBSProps.getProperty("OBSPort").isEmpty()?"0": configOBSProps.getProperty("OBSPort");}
	public String getOBSPassword() {return configOBSProps.getProperty("OBSPassword");}
	public String getOBSScene() {return configOBSProps.getProperty("OBSScene");}
	public int getOBSSavePassword() {return Integer.parseInt(configOBSProps.getProperty("OBSSavePassword"));}
	public int getOBSAutoLogin() {return Integer.parseInt(configOBSProps.getProperty("OBSAutoLogin"));}
	public int getOBSCloseOnConnect() {return Integer.parseInt(configOBSProps.getProperty("OBSCloseOnConnect"));}
	public int getOBSUpdateOnConnect()  {return Integer.parseInt(configOBSProps.getProperty("OBSUpdateOnConnect"));}
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
	public String getShowScoresSource() {return configSourceProps.getProperty("ShowScoresSource");}
	public String getShowTimerSource() {return configSourceProps.getProperty("ShowTimerSource");}
	//Filters
	public String getFiltersFilter(String filter) {return configFilterProps.getProperty(filter);}
	public String getTeam1ScoreFilter() {return configFilterProps.getProperty("Team1Score");}
	public String getTeam2ScoreFilter() {return configFilterProps.getProperty("Team2Score");}
	public String getTeam1WinGameFilter() {return configFilterProps.getProperty("Team1WinGame");}
	public String getTeam2WinGameFilter() {return configFilterProps.getProperty("Team2WinGame");}
	public String getTeam1WinMatchFilter() {return configFilterProps.getProperty("Team1WinMatch");}
	public String getTeam2WinMatchFilter() {return configFilterProps.getProperty("Team2WinMatch");}
	public String getTeam1TimeOutFilter() {return configFilterProps.getProperty("Team1TimeOut");}
	public String getTeam2TimeOutFilter() {return configFilterProps.getProperty("Team2TimeOut");}
	public String getTeam1ResetFilter() {return configFilterProps.getProperty("Team1Reset");}
	public String getTeam2ResetFilter() {return configFilterProps.getProperty("Team2Reset");}
	public String getTeam1WarnFilter() {return configFilterProps.getProperty("Team1Warn");}
	public String getTeam2WarnFilter() {return configFilterProps.getProperty("Team2Warn");}
	public String getTeam1SwitchPositionsFilter() {return configFilterProps.getProperty("Team1SwitchPositions");}
	public String getTeam2SwitchPositionsFilter() {return configFilterProps.getProperty("Team2SwitchPositions");}
	public String getTeam1SkunkFilter() {return configFilterProps.getProperty("Team1Skunk");}
	public String getTeam2SkunkFilter() {return configFilterProps.getProperty("Team2Skunk");}
	public String getStartMatchFilter() {return configFilterProps.getProperty("StartMatch");}
	public String getStartGameFilter() {return configFilterProps.getProperty("StartGame");}
	public String getSwitchSidesFilter() {return configFilterProps.getProperty("SwitchSides");}	//PartnerProgram
	public String getPartnerProgramPath() {return configPartnerProgramProps.getProperty("PartnerProgramPath");}
	public String getPlayer1FileName() {return configPartnerProgramProps.getProperty("Player1FileName");}
	public String getPlayer2FileName() {return configPartnerProgramProps.getProperty("Player2FileName");}
	public String getPlayer3FileName() {return configPartnerProgramProps.getProperty("Player3FileName");}
	public String getPlayer4FileName() {return configPartnerProgramProps.getProperty("Player4FileName");}
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
	public String getStartStreamHotKey() {return configHotKeyProps.getProperty("StartStreamHotKey");}
	public String getAutoScoreMainConnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainConnectHotKey");}
	public String getAutoScoreMainDisconnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainDisconnectHotKey");}
	public String getAutoScoreMainSettingsHotKey() {return configHotKeyProps.getProperty("AutoScoreMainSettingsHotKey");}
	public String getHotKeyBaseScript() {return configHotKeyProps.getProperty("HotKeyBaseScript");}
	public String getHotKeyScriptPath() {return configHotKeyProps.getProperty("HotKeyScriptPath");}
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
	public void setBallsInRack(String ballsInRack) {
		configControlProps.setProperty("BallsInRack", ballsInRack);
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
	public void setRackMode(int rackMode) {
		configControlProps.setProperty("RackMode", Integer.toString(rackMode));
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
	public void setShowScoresSource(String showScoresSource) {
		configSourceProps.setProperty("ShowScoresSource", showScoresSource);
	}
	public void setShowTimerSource(String showTimerSource) {
		configSourceProps.setProperty("ShowTimerSource", showTimerSource);
	}
	//Filters
	public void setTeam1ScoreFilter(String team1Score) {
		configFilterProps.setProperty("Team1Score", team1Score);
	}
	public void setTeam2ScoreFilter(String team2Score) {
		configFilterProps.setProperty("Team2Score", team2Score);
	}
	public void setTeam1WinGameFilter(String team1WinGame) {
		configFilterProps.setProperty("Team1WinGame", team1WinGame);
	}
	public void setTeam2WinGameFilter(String team2WinGame) {
		configFilterProps.setProperty("Team2WinGame", team2WinGame);
	}
	public void setTeam1WinMatchFilter(String team1WinMatch) {
		configFilterProps.setProperty("Team1WinMatch", team1WinMatch);
	}
	public void setTeam2WinMatchFilter(String team2WinMatch) {
		configFilterProps.setProperty("Team2WinMatch", team2WinMatch);
	}
	public void setTeam1TimeOutFilter(String team1TimeOut) {
		configFilterProps.setProperty("Team1TimeOut", team1TimeOut);
	}
	public void setTeam2TimeOutFilter(String team2TimeOut) {
		configFilterProps.setProperty("Team2TimeOut", team2TimeOut);
	}
	public void setTeam1ResetFilter(String team1Reset) {
		configFilterProps.setProperty("Team1Reset", team1Reset);
	}
	public void setTeam2ResetFilter(String team2Reset) {
		configFilterProps.setProperty("Team2Reset", team2Reset);
	}
	public void setTeam1WarnFilter(String team1Warn) {
		configFilterProps.setProperty("Team1Warn", team1Warn);
	}
	public void setTeam2WarnFilter(String team2Warn) {
		configFilterProps.setProperty("Team2Warn", team2Warn);
	}
	public void setTeam1SwitchPositionsFilter(String team1SwitchPositions) {
		configFilterProps.setProperty("Team1SwitchPositions", team1SwitchPositions);
	}
	public void setTeam2SwitchPositionsFilter(String team2SwitchPositions) {
		configFilterProps.setProperty("Team2SwitchPositions", team2SwitchPositions);
	}
	public void setTeam1SkunkFilter(String team1Skunk) {
		configFilterProps.setProperty("Team1Skunk", team1Skunk);
	}
	public void setTeam2SkunkFilter(String team2Skunk) {
		configFilterProps.setProperty("Team2Skunk", team2Skunk);
	}
	public void setStartMatchFilter(String startMatch) {
		configFilterProps.setProperty("StartMatch", startMatch);
	}
	public void setStartGameFilter(String startGame) {
		configFilterProps.setProperty("StartGame", startGame);
	}
	public void setSwitchSidesFilter(String switchSides) {
		configFilterProps.setProperty("SwitchSides", switchSides);
	}
	//PartnerProgram
	public void setPartnerProgramPath(String path) {
		configPartnerProgramProps.setProperty("PartnerProgramPath", path);
	}
	public void setPlayer1FileName(String name) {
		configPartnerProgramProps.setProperty("Player1FileName", name);
	}
	public void setPlayer2FileName(String name) {
		configPartnerProgramProps.setProperty("Player2FileName", name);
	}
	public void setPlayer3FileName(String name) {
		configPartnerProgramProps.setProperty("Player3FileName", name);
	}
	public void setPlayer4FileName(String name) {
		configPartnerProgramProps.setProperty("Player4FileName", name);
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
	public void setStartStreamHotKey(String startStreamHotKey) {
		configHotKeyProps.setProperty("StartStreamHotKey", startStreamHotKey);
	}
	public void setHotKeyBaseScript(String hotKeyBaseScript) {
		configHotKeyProps.setProperty("HotKeyBaseScript", hotKeyBaseScript);
	}
	public void setHotKeyScriptPath(String hotKeyScriptPath) {
		if (!hotKeyScriptPath.isEmpty() && hotKeyScriptPath.charAt(hotKeyScriptPath.length()-1)!='\\') {
			hotKeyScriptPath = hotKeyScriptPath + "\\";
		}
		configHotKeyProps.setProperty("HotKeyScriptPath", hotKeyScriptPath);
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
	public String getDefaultBallsInRack() {return defaultControlProps.getProperty("BallsInRack");}
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
	public int getDefaultRackMode() {return Integer.parseInt(defaultControlProps.getProperty("RackMode"));}
	//OBS
	public String getDefaultOBSHost() {return defaultOBSProps.getProperty("OBSHost");}
	public String getDefaultOBSPort() {return defaultOBSProps.getProperty("OBSPort");}
	public String getDefaultOBSPassword() {return defaultOBSProps.getProperty("OBSPassword");}
	public String getDefaultOBSScene() {return defaultOBSProps.getProperty("OBSScene");}
	public int getDefaultOBSAutoLogin() {return Integer.parseInt(defaultOBSProps.getProperty("OBSAutoLogin"));}
	public int getDefaultOBSSavePassword() {return Integer.parseInt(defaultOBSProps.getProperty("OBSSavePassword"));}
	public int getDefaultOBSCloseOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSCloseOnConnect"));}
	public int getDefaultOBSUpdateOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSUpdateOnConnect"));}
	//Sources
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
	public String getDefaultShowScoresSource() {return defaultSourceProps.getProperty("ShowScoresSource");}
	public String getDefaultShowTimerSource() {return defaultSourceProps.getProperty("ShowTimerSource");}
	//Filters
	public String getDefaultTeam1ScoreFilter() {return defaultFilterProps.getProperty("Team1Score");}
	public String getDefaultTeam2ScoreFilter() {return defaultFilterProps.getProperty("Team2Score");}
	public String getDefaultTeam1WinGameFilter() {return defaultFilterProps.getProperty("Team1WinGame");}
	public String getDefaultTeam2WinGameFilter() {return defaultFilterProps.getProperty("Team2WinGame");}
	public String getDefaultTeam1WinMatchFilter() {return defaultFilterProps.getProperty("Team1WinMatch");}
	public String getDefaultTeam2WinMatchFilter() {return defaultFilterProps.getProperty("Team2WinMatch");}
	public String getDefaultTeam1TimeOutFilter() {return defaultFilterProps.getProperty("Team1TimeOut");}
	public String getDefaultTeam2TimeOutFilter() {return defaultFilterProps.getProperty("Team2TimeOut");}
	public String getDefaultTeam1ResetFilter() {return defaultFilterProps.getProperty("Team1Reset");}
	public String getDefaultTeam2ResetFilter() {return defaultFilterProps.getProperty("Team2Reset");}
	public String getDefaultTeam1WarnFilter() {return defaultFilterProps.getProperty("Team1Warn");}
	public String getDefaultTeam2WarnFilter() {return defaultFilterProps.getProperty("Team2Warn");}
	public String getDefaultTeam1SwitchPositionsFilter() {return defaultFilterProps.getProperty("Team1SwitchPositions");}
	public String getDefaultTeam2SwitchPositionsFilter() {return defaultFilterProps.getProperty("Team2SwitchPositions");}
	public String getDefaultTeam1SkunkFilter() {return defaultFilterProps.getProperty("Team1Skunk");}
	public String getDefaultTeam2SkunkFilter() {return defaultFilterProps.getProperty("Team2Skunk");}
	public String getDefaultStartMatchFilter() {return defaultFilterProps.getProperty("StartMatch");}
	public String getDefaultStartGameFilter() {return defaultFilterProps.getProperty("StartGame");}
	public String getDefaultSwitchSidesFilter() {return defaultFilterProps.getProperty("SwitchSides");}
	//PartnerProgram
	public String getDefaultPartnerProgramPath() {return defaultPartnerProgramProps.getProperty("PartnerProgramPath");}
	public String getDefaultPlayer1FileName() {return defaultPartnerProgramProps.getProperty("Player1FileName");}
	public String getDefaultPlayer2FileName() {return defaultPartnerProgramProps.getProperty("Player2FileName");}
	public String getDefaultPlayer3FileName() {return defaultPartnerProgramProps.getProperty("Player3FileName");}
	public String getDefaultPlayer4FileName() {return defaultPartnerProgramProps.getProperty("Player4FileName");}
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
	public String getDefaultStartStreamHotKey() {return defaultHotKeyProps.getProperty("StartStreamHotKey");}
	public String getDefaultHotKeyBaseScript() {return defaultHotKeyProps.getProperty("HotKeyBaseScript");}
	public String getDefaultHotKeyScriptPath() {return defaultHotKeyProps.getProperty("HotKeyScriptPath");}
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
		}
	}
	public void loadFromOBSConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configOBSFileName))) {
			configOBSProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configOBSFileName));
			configOBSProps = defaultOBSProps;
			saveOBSConfig();
		}
	}
	public void loadFromSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configSourceFileName))) {
			configSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configSourceFileName));
			configSourceProps = defaultSourceProps;
			saveSourceConfig();
		}
	}
	public void loadFromFilterConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configFilterFileName))) {
			configFilterProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configFilterFileName));
			configFilterProps = defaultFilterProps;
			saveFilterConfig();
		}
	}
	public void loadFromPartnerProgramConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configPartnerProgramFileName));
			configPartnerProgramProps = defaultPartnerProgramProps;
			savePartnerProgramConfig();
		}
	}
	public void loadFromHotKeyConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.load(inputStream);
		} catch (NoSuchFileException e) {
			Files.createFile(Paths.get(configHotKeyFileName));
			configHotKeyProps = defaultHotKeyProps;
			saveHotKeyConfig();
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
	public void saveFilterConfig() throws IOException {
		//Filter
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configFilterFileName))) {
			configFilterProps.store(outputStream, "FoosOBSPlus Filter Settings");
		}
	}
	public void savePartnerProgramConfig() throws IOException {
		//PartnerProgram
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.store(outputStream, "FoosOBSPlus Partner Program Settings");
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
	public void generateHotKeyScripts() {
		String basePath = System.getProperty("user.dir");
		String baseScriptText = getHotKeyBaseScript();
		String hotKeyScriptPath = getHotKeyScriptPath();
		if(Files.exists(Paths.get(basePath))) {
			if(Files.exists(Paths.get(hotKeyScriptPath))) {
				if (baseScriptText.isEmpty()) {
					JOptionPane.showMessageDialog(null, Messages.getString("Errors.BaseScriptFile"), "Scripting Error", 1);
				} else {
					String[] baseScript = baseScriptText.split("\\r\\n");
					configHotKeyProps.entrySet()
						.stream()
						.filter(e -> e.getValue().toString().length() > 0)
						.forEach(e->createHotKeyScript(e.getKey().toString(), e.getValue().toString(), baseScript, basePath));
					JOptionPane.showMessageDialog(null, "Done");
				}
			} else {
				JOptionPane.showMessageDialog(null,  Messages.getString("Errors.DirectoryDoesNotExist") + " " + hotKeyScriptPath);
			}
		} else {
			JOptionPane.showMessageDialog(null, Messages.getString("Errors.DirectoryDoesNotExist") + " " + basePath);
		}
	}
	private void createHotKeyScript(String keyFunction, String hotKey, String[] baseScript, String basePath) {
		keyFunction = keyFunction.substring(0, keyFunction.length()-6);
		try {
			File scriptFile = new File(getHotKeyScriptPath() + File.separator + keyFunction + ".ahk");
			scriptFile.createNewFile();
			FileWriter fileWriter = new FileWriter(scriptFile);
			for (String ln: baseScript) {
				fileWriter.write(ln.replace("~function~", keyFunction).replace("~hotkey~", hotKey).replace("~basepath~", basePath) + "\r\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, Messages.getString("Errors.ScriptWriteFailure") + " " + keyFunction, "Scripting Error", 1);
		}
	}
}
