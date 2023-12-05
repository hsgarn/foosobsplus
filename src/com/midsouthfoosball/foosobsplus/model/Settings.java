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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.view.Messages;

public class Settings {
	
// Parameter settings
	private String separator 			= FileSystems.getDefault().getSeparator();
	private String[] lastScoredStrings 	= new String[4];
	private final String gameType       = "Foosball";

	// Property Settings
	private Properties defaultControlProps;
	private Properties defaultSourceProps;
	private Properties defaultStatsSourceProps;
	private Properties defaultFilterProps;
	private Properties defaultPartnerProgramProps;
	private Properties defaultHotKeyProps;
	private Properties defaultOBSProps;
	private Properties defaultAutoScoreSettingsProps;
	
	public Properties configControlProps;
	public Properties configSourceProps;
	public Properties configStatsSourceProps;
	public Properties configFilterProps;
	public Properties configPartnerProgramProps;
	public Properties configHotKeyProps;
	public Properties configOBSProps;
	public Properties configAutoScoreSettingsProps;
	
	private String configControlFileName 			= "control.properties";
	private String configSourceFileName 			= "source.properties";
	private String configStatsSourceFileName 		= "statssource.properties";
	private String configFilterFileName             = "filter.properties";
	private String configPartnerProgramFileName     = "partnerprogram.properties";
	private String configHotKeyFileName 			= "hotkey.properties";
	private String configOBSFileName        		= "obs.properties";
	private String configAutoScoreSettingsFileName	= "autoscoresettings.properties";
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	//////////////////////////////////////////////////////
	
	public Settings() throws IOException {
		defaultControlProps 			= new Properties();
		defaultSourceProps 				= new Properties();
		defaultStatsSourceProps 		= new Properties();
		defaultFilterProps              = new Properties();
		defaultPartnerProgramProps      = new Properties();
		defaultHotKeyProps 				= new Properties();
		defaultOBSProps         		= new Properties();
		defaultAutoScoreSettingsProps	= new Properties();
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
		defaultControlProps.setProperty("Team3LastScored", "---> Team 3 <---");
		defaultControlProps.setProperty("ClearLastScored", "Last Scored");
		defaultControlProps.setProperty("Side1Color", "Yellow");
		defaultControlProps.setProperty("Side2Color", "Black");
		defaultControlProps.setProperty("Side3Color", "Black");
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
		defaultSourceProps.setProperty("Tournament", "tournament");
		defaultSourceProps.setProperty("Team1Name", "team1name");
		defaultSourceProps.setProperty("Team1Forward", "team1forward");
		defaultSourceProps.setProperty("Team1Goalie", "team1goalie");
		defaultSourceProps.setProperty("Tournament", "tournament");
		defaultSourceProps.setProperty("Team2Name", "team2name");
		defaultSourceProps.setProperty("Team2Forward", "team2forward");
		defaultSourceProps.setProperty("Team2Goalie", "team2goalie");
		defaultSourceProps.setProperty("Event", "event");
		defaultSourceProps.setProperty("TableName", "tablename");
		defaultSourceProps.setProperty("Team3Name", "team3name");
		defaultSourceProps.setProperty("Team3Forward", "team3forward");
		defaultSourceProps.setProperty("Team3Goalie", "team3goalie");
		defaultSourceProps.setProperty("GameCount1", "gamecount1");
		defaultSourceProps.setProperty("TimeRemaining", "timeremaining");
		defaultSourceProps.setProperty("GameCount2", "gamecount2");
		defaultSourceProps.setProperty("TimerInUse", "timerinuse");
		defaultSourceProps.setProperty("GameCount3", "gamecount3");
		defaultSourceProps.setProperty("Score1", "score1");
		defaultSourceProps.setProperty("MatchWinner", "matchwinner");
		defaultSourceProps.setProperty("Meatball",  "meatball");
		defaultSourceProps.setProperty("Score2", "score2");
		defaultSourceProps.setProperty("Score3", "score3");
		defaultSourceProps.setProperty("TimeOut1", "timeout1");
		defaultSourceProps.setProperty("TimeOut2", "timeout2");
		defaultSourceProps.setProperty("TimeOut3", "timeout3");
		defaultSourceProps.setProperty("Reset1", "reset1");
		defaultSourceProps.setProperty("Reset2", "reset2");
		defaultSourceProps.setProperty("Reset3", "reset3");
		defaultSourceProps.setProperty("Warn1", "warn1");
		defaultSourceProps.setProperty("Warn2", "warn2");
		defaultSourceProps.setProperty("Warn3", "warn3");
		defaultSourceProps.setProperty("LastScored","lastscored");
		defaultSourceProps.setProperty("GameTime", "gametime");
		defaultSourceProps.setProperty("MatchTime", "matchtime");
		defaultSourceProps.setProperty("StreamTime", "streamtime");
		defaultSourceProps.setProperty("Side1Color","side1color");
		defaultSourceProps.setProperty("Side2Color","side2color");
		defaultSourceProps.setProperty("Side3Color","side3color");
		defaultSourceProps.setProperty("ShowScoresSource", "ScoresAndLabels");
		defaultSourceProps.setProperty("ShowTimerSource", "Foos OBS+ Timer");
		//Stat Sources
		defaultStatsSourceProps.setProperty("Stuffs1", "stuffs1");
		defaultStatsSourceProps.setProperty("Stuffs2", "stuffs2");
		defaultStatsSourceProps.setProperty("Stuffs3", "stuffs3");
		defaultStatsSourceProps.setProperty("Breaks1", "breaks1");
		defaultStatsSourceProps.setProperty("Breaks2", "breaks2");
		defaultStatsSourceProps.setProperty("Breaks3", "breaks3");
		defaultStatsSourceProps.setProperty("Aces1",  "aces1");
		defaultStatsSourceProps.setProperty("Aces2",  "aces2");
		defaultStatsSourceProps.setProperty("Aces3",  "aces3");
		defaultStatsSourceProps.setProperty("Team1PassAttempts", "team1passattempts");
		defaultStatsSourceProps.setProperty("Team2PassAttempts", "team2passattempts");
		defaultStatsSourceProps.setProperty("Team3PassAttempts", "team3passattempts");
		defaultStatsSourceProps.setProperty("Team1PassCompletes", "team1passcompletes");
		defaultStatsSourceProps.setProperty("Team2PassCompletes", "team2passcompletes");
		defaultStatsSourceProps.setProperty("Team3PassCompletes", "team3passcompletes");
		defaultStatsSourceProps.setProperty("Team1ShotAttempts", "team1shotattempts");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ShotCompletes", "team1shotcompletes");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ClearAttempts", "team1clearattempts");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ClearCompletes", "team1clearcompletes");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1PassPercent", "team1passpercent");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ShotPercent", "team1shotpercent");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ClearPercent", "team1clearpercent");
		defaultStatsSourceProps.setProperty("Team2ClearPercent", "team2clearpercent");
		defaultStatsSourceProps.setProperty("Team3ClearPercent", "team3clearpercent");
		defaultStatsSourceProps.setProperty("Team1TwoBarPassAttempts", "team1twobarpassattempts");
		defaultStatsSourceProps.setProperty("Team2TwoBarPassAttempts", "team2twobarpassattempts");
		defaultStatsSourceProps.setProperty("Team3TwoBarPassAttempts", "team3twobarpassattempts");
		defaultStatsSourceProps.setProperty("Team1TwoBarPassCompletes", "team1twobarpasscompletes");
		defaultStatsSourceProps.setProperty("Team2TwoBarPassCompletes", "team2twobarpasscompletes");
		defaultStatsSourceProps.setProperty("Team3TwoBarPassCompletes", "team3twobarpasscompletes");
		defaultStatsSourceProps.setProperty("Team1TwoBarPassPercent", "team1twobarpasspercent");
		defaultStatsSourceProps.setProperty("Team2TwoBarPassPercent", "team2twobarpasspercent");
		defaultStatsSourceProps.setProperty("Team3TwoBarPassPercent", "team3twobarpasspercent");
		defaultStatsSourceProps.setProperty("Team1Scoring", "team1scoring");
		defaultStatsSourceProps.setProperty("Team2Scoring", "team2scoring");
		defaultStatsSourceProps.setProperty("Team3Scoring", "team3scoring");
		defaultStatsSourceProps.setProperty("Team1ThreeBarScoring", "team1threebarscoring");
		defaultStatsSourceProps.setProperty("Team2ThreeBarScoring", "team2threebarscoring");
		defaultStatsSourceProps.setProperty("Team3ThreeBarScoring", "team3threebarscoring");
		defaultStatsSourceProps.setProperty("Team1FiveBarScoring", "team1fivebarscoring");
		defaultStatsSourceProps.setProperty("Team2FiveBarScoring", "team2fivebarscoring");
		defaultStatsSourceProps.setProperty("Team3FiveBarScoring", "team3fivebarscoring");
		defaultStatsSourceProps.setProperty("Team1TwoBarScoring", "team1twobarscoring");
		defaultStatsSourceProps.setProperty("Team2TwoBarScoring", "team2twobarscoring");
		defaultStatsSourceProps.setProperty("Team3TwoBarScoring", "team3twobarscoring");
		defaultStatsSourceProps.setProperty("Team1ShotsOnGoal", "team1shotsongoal");
		defaultStatsSourceProps.setProperty("Team2ShotsOnGoal", "team2shotsongoal");
		defaultStatsSourceProps.setProperty("Team3ShotsOnGoal", "team3shotsongoal");
		//Filters
		defaultFilterProps.setProperty("Team1Score", "team1score");
		defaultFilterProps.setProperty("Team2Score", "team2score");
		defaultFilterProps.setProperty("Team3Score", "team3score");
		defaultFilterProps.setProperty("Team1WinGame", "team1wingame");
		defaultFilterProps.setProperty("Team2WinGame", "team2wingame");
		defaultFilterProps.setProperty("Team3WinGame", "team3wingame");
		defaultFilterProps.setProperty("Team1WinMatch", "team1winmatch");
		defaultFilterProps.setProperty("Team2WinMatch", "team2winmatch");
		defaultFilterProps.setProperty("Team3WinMatch", "team3winmatch");
		defaultFilterProps.setProperty("Team1TimeOut", "team1timeout");
		defaultFilterProps.setProperty("Team2TimeOut", "team2timeout");
		defaultFilterProps.setProperty("Team3TimeOut", "team3timeout");
		defaultFilterProps.setProperty("Team1Reset", "team1reset");
		defaultFilterProps.setProperty("Team2Reset", "team2reset");
		defaultFilterProps.setProperty("Team3Reset", "team3reset");
		defaultFilterProps.setProperty("Team1Warn", "team1warn");
		defaultFilterProps.setProperty("Team2Warn", "team2warn");
		defaultFilterProps.setProperty("Team3Warn", "team3warn");
		defaultFilterProps.setProperty("Team1SwitchPositions", "team1switchpositions");
		defaultFilterProps.setProperty("Team2SwitchPositions", "team2switchpositions");
		defaultFilterProps.setProperty("Team3SwitchPositions", "team3switchpositions");
		defaultFilterProps.setProperty("Team1Skunk", "team1skunk");
		defaultFilterProps.setProperty("Team2Skunk", "team2skunk");
		defaultFilterProps.setProperty("Team3Skunk", "team3skunk");
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
		defaultHotKeyProps.setProperty("Team1SwitchPositionsHotKey", "t");
		defaultHotKeyProps.setProperty("Team2SwitchPositionsHotKey", "m");
		defaultHotKeyProps.setProperty("Team3SwitchPositionsHotKey", "");
		defaultHotKeyProps.setProperty("SwitchTeamsHotKey", "e");
		defaultHotKeyProps.setProperty("SwitchPlayer1HotKey", ";");
		defaultHotKeyProps.setProperty("SwitchPlayer2HotKey", "x");
		defaultHotKeyProps.setProperty("SwitchPlayer3HotKey", "");
		defaultHotKeyProps.setProperty("GameCount1MinusHotKey", "j");
		defaultHotKeyProps.setProperty("GameCount2MinusHotKey", "i");
		defaultHotKeyProps.setProperty("GameCount3MinusHotKey", "");
		defaultHotKeyProps.setProperty("GameCount1PlusHotKey", "5");
		defaultHotKeyProps.setProperty("GameCount2PlusHotKey", "6");
		defaultHotKeyProps.setProperty("GameCount3PlusHotKey", "");
		defaultHotKeyProps.setProperty("GameCountSwitchHotKey", "l");
		defaultHotKeyProps.setProperty("Score1MinusHotKey", "4");
		defaultHotKeyProps.setProperty("Score2MinusHotKey", "8");
		defaultHotKeyProps.setProperty("Score3MinusHotKey", "");
		defaultHotKeyProps.setProperty("Score1PlusHotKey", "1");
		defaultHotKeyProps.setProperty("Score2PlusHotKey", "2");
		defaultHotKeyProps.setProperty("Score3PlusHotKey", "");
		defaultHotKeyProps.setProperty("SwitchScoresHotKey", "");
		defaultHotKeyProps.setProperty("SwitchGameCountsHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut1MinusHotKey", "n");
		defaultHotKeyProps.setProperty("TimeOut2MinusHotKey", "q");
		defaultHotKeyProps.setProperty("TimeOut3MinusHotKey", "");
		defaultHotKeyProps.setProperty("TimeOut1PlusHotKey", "9");
		defaultHotKeyProps.setProperty("TimeOut2PlusHotKey", "0");
		defaultHotKeyProps.setProperty("TimeOut3PlusHotKey", "");
		defaultHotKeyProps.setProperty("SwitchTimeOutsHotKey", "[");
		defaultHotKeyProps.setProperty("Reset1HotKey", "");
		defaultHotKeyProps.setProperty("Reset2HotKey", "");
		defaultHotKeyProps.setProperty("Reset3HotKey", "");
		defaultHotKeyProps.setProperty("Warn1HotKey", "");
		defaultHotKeyProps.setProperty("Warn2HotKey", "");
		defaultHotKeyProps.setProperty("Warn3HotKey", "");
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

		//Config Properties
		configControlProps 				= new Properties(defaultControlProps);
		configSourceProps 				= new Properties(defaultSourceProps);
		configStatsSourceProps 			= new Properties(defaultStatsSourceProps);
		configFilterProps               = new Properties(defaultFilterProps);
		configPartnerProgramProps       = new Properties(defaultPartnerProgramProps);
		configHotKeyProps 				= new Properties(defaultHotKeyProps);
		configOBSProps      			= new Properties(defaultOBSProps);
		configAutoScoreSettingsProps	= new Properties(defaultAutoScoreSettingsProps);
		
		loadFromControlConfig();
		loadFromOBSConfig();
		loadFromSourceConfig();
		loadFromStatsSourceConfig();
		loadFromFilterConfig();
		loadFromPartnerProgramConfig();
		loadFromHotKeyConfig();
		loadFromAutoScoreSettingsConfig();
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
	public String getTeam3LastScored() {return configControlProps.getProperty("Team3LastScored");}
	public String getClearLastScored() {return configControlProps.getProperty("ClearLastScored");}
	public String[] getLastScoredStrings() {
		lastScoredStrings[0] = getClearLastScored();
		lastScoredStrings[1] = getTeam1LastScored();
		lastScoredStrings[2] = getTeam2LastScored();
		lastScoredStrings[3] = getTeam3LastScored();
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
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Team" + teamNumber + "Name");//Team1Name
	}
	public String getTeamForwardSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Team" + teamNumber + "Forward");//Team1Forward
	}
	public String getTeamGoalieSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Team" + teamNumber + "Goalie");//Team1Goalie
	}
	public String getGameCountSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("GameCount" + teamNumber);//GameCount1
	}
	public String getScoreSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Score" + teamNumber);//Score1
	}
	public String getTimeOutSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("TimeOut" + teamNumber);//TimeOut1
	}
	public String getResetSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Reset" + teamNumber);//Reset1
	}
	public String getWarnSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configSourceProps.getProperty("Warn" + teamNumber);//Warn1
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
	public String getStreamTimeSource() {return configSourceProps.getProperty("StreamTime");}
	public String getSide1ColorSource() {return configSourceProps.getProperty("Side1Color");}
	public String getSide2ColorSource() {return configSourceProps.getProperty("Side2Color");}
	public String getSide3ColorSource() {return configSourceProps.getProperty("Side3Color");}
	public String getShowScoresSource() {return configSourceProps.getProperty("ShowScoresSource");}
	public String getShowTimerSource() {return configSourceProps.getProperty("ShowTimerSource");}
	//Stat Sources
	public String getPassAttemptsSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassAttempts");//Team1PassAttempts
	}
	public String getPassCompletesSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassCompletes");//Team1PassCompletes
	}
	public String getShotAttemptsSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotAttempts");//Team1ShotAttempts
	}
	public String getShotCompletesSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotCompletes");//Team1ShotCompletes
	}
	public String getClearAttemptsSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearAttempts");//Team1ClearAttempts
	}
	public String getClearCompletesSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearCompletes");//Team1ClearCompletes
	}
	public String getPassPercentSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassPercent");//Team1PassPercent
	}
	public String getShotPercentSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotPercent");//Team1ShotPercent
	}
	public String getClearPercentSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearPercent");//Team1ClearPercent
	}
	public String getTwoBarPassAttemptsSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassAttempts");//Team1TwoBarPassAttempts
	}
	public String getTwoBarPassCompletesSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassCompletes");//Team1TwoBarPassCompletes
	}
	public String getTwoBarPassPercentSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassPercent");//Team1TwoBarPassPercent
	}
	public String getScoringSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "Scoring");//Team1Scoring
	}
	public String getThreeBarScoringSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ThreeBarScoring");//Team1ThreeBarScoring
	}
	public String getFiveBarScoringSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "FiveBarScoring");//Team1FiveBarScoring
	}
	public String getTwoBarScoringSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarScoring");//Team1TwoBarScoring
	}
	public String getShotsOnGoalSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotsOnGoal");//Team1ShotsOnGoal
	}
	public String getStuffsSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Stuffs" + teamNumber);//Stuffs1
	}
	public String getBreaksSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Breaks" + teamNumber);//Breaks1
	}
	public String getAcesSource(int teamNbr) {
		String teamNumber = Integer.toString(teamNbr);
		return configStatsSourceProps.getProperty("Aces" + teamNumber);//Aces1
	}
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
	public String getTeamSwitchPositionsHotKey(String teamNumber) {return configHotKeyProps.getProperty("Team" + teamNumber + "SwitchPositionsHotKey");}
	public String getSwitchPlayerHotKey(String teamNumber) {return configHotKeyProps.getProperty("SwitchPlayer" + teamNumber + "HotKey");}
	public String getGameCountMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("GameCount" + teamNumber + "MinusHotKey");}
	public String getGameCountPlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("GameCount" + teamNumber + "PlusHotKey");}
	public String getTimeOutMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("TimeOut" + teamNumber + "MinusHotKey");}
	public String getTimeOutPlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("TimeOut" + teamNumber + "PlusHotKey");}
	public String getScoreMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("Score" + teamNumber + "MinusHotKey");}
	public String getScorePlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("Score" + teamNumber + "PlusHotKey");}
	public String getResetHotKey(String teamNumber) {return configHotKeyProps.getProperty("Reset" + teamNumber + "HotKey");}
	public String getWarnHotKey(String teamNumber) {return configHotKeyProps.getProperty("Warn" + teamNumber + "HotKey");}
	public String getSwitchTeamsHotKey() {return configHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public String getSwitchGameCountsHotKey() {return configHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public String getSwitchScoresHotKey() {return configHotKeyProps.getProperty("SwitchScoresHotKey");}
	public String getSwitchTimeOutsHotKey() {return configHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
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
	public void setTeam3LastScored(String team3LastScored) {
		configControlProps.setProperty("Team3LastScored", team3LastScored);
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
	public void setOBS(String property, String OBSValue) {
		configOBSProps.setProperty(property, OBSValue);
	}
	public void setOBS(String property, int value) {
		configOBSProps.setProperty(property, Integer.toString(value));
	}
	//Sources
	public void setSource(String property, String source) {
		configSourceProps.setProperty(property, source);
	}
	//Stat Sources
	public void setStatsSource(String property, String source) {
		configStatsSourceProps.setProperty(property, source);
	}
	//Filters
	public void setFilter(String property, String filter) {
		configFilterProps.setProperty(property, filter);
	}
	//PartnerProgram
	public void setPartnerProgram(String property, String data) {
		configPartnerProgramProps.setProperty(property, data);
	}
	public void setPartnerProgramPath(String path) {
		configPartnerProgramProps.setProperty("PartnerProgramPath", path);
	}
	//HotKeys
	public void setHotKey(String property, String hotKey) {
		configHotKeyProps.setProperty(property, hotKey);
	}
	public void setHotKeyScriptPath(String hotKeyScriptPath) {
		if (!hotKeyScriptPath.isEmpty() && hotKeyScriptPath.charAt(hotKeyScriptPath.length()-1)!='\\') {
			hotKeyScriptPath = hotKeyScriptPath + "\\";
		}
		configHotKeyProps.setProperty("HotKeyScriptPath", hotKeyScriptPath);
	}
	//AutoScore Settings
	public void setAutoScore(String property, String value) {
		configAutoScoreSettingsProps.setProperty(property, value);
	}
	public void setAutoScore(String property, int value) {
		configAutoScoreSettingsProps.setProperty(property, Integer.toString(value));
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
	public String getDefaultTeam3LastScored() {return defaultControlProps.getProperty("Team3LastScored");}
	public String getDefaultClearLastScored() {return defaultControlProps.getProperty("ClearLastScored");}
	public String getDefaultSide1Color() {return defaultControlProps.getProperty("Side1Color");}
	public String getDefaultSide2Color() {return defaultControlProps.getProperty("Side2Color");}
	public String getDefaultSide3Color() {return defaultControlProps.getProperty("Side3Color");}
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
	public String getDefaultTeam3NameSource() {return defaultSourceProps.getProperty("Team3Name");}
	public String getDefaultTeam3ForwardSource() {return defaultSourceProps.getProperty("Team3Forward");}
	public String getDefaultTeam3GoalieSource() {return defaultSourceProps.getProperty("Team3Goalie");}
	public String getDefaultTableNameSource() {return defaultSourceProps.getProperty("TableName");}
	public String getDefaultGameCount1Source() {return defaultSourceProps.getProperty("GameCount1");}
	public String getDefaultTimeRemainingSource() {return defaultSourceProps.getProperty("TimeRemaining");}
	public String getDefaultGameCount2Source() {return defaultSourceProps.getProperty("GameCount2");}
	public String getDefaultGameCount3Source() {return defaultSourceProps.getProperty("GameCount3");}
	public String getDefaultTimerInUseSource() {return defaultSourceProps.getProperty("TimerInUse");}
	public String getDefaultScore1Source() {return defaultSourceProps.getProperty("Score1");}
	public String getDefaultMatchWinnerSource() {return defaultSourceProps.getProperty("MatchWinner");}
	public String getDefaultMeatballSource() {return defaultSourceProps.getProperty("Meatball");}
	public String getDefaultScore2Source() {return defaultSourceProps.getProperty("Score2");}
	public String getDefaultScore3Source() {return defaultSourceProps.getProperty("Score3");}
	public String getDefaultTimeOut1Source() {return defaultSourceProps.getProperty("TimeOut1");}
	public String getDefaultTimeOut2Source() {return defaultSourceProps.getProperty("TimeOut2");}
	public String getDefaultTimeOut3Source() {return defaultSourceProps.getProperty("TimeOut3");}
	public String getDefaultReset1Source() {return defaultSourceProps.getProperty("Reset1");}
	public String getDefaultReset2Source() {return defaultSourceProps.getProperty("Reset2");}
	public String getDefaultReset3Source() {return defaultSourceProps.getProperty("Reset3");}
	public String getDefaultWarn1Source() {return defaultSourceProps.getProperty("Warn1");}
	public String getDefaultWarn2Source() {return defaultSourceProps.getProperty("Warn2");}
	public String getDefaultWarn3Source() {return defaultSourceProps.getProperty("Warn3");}
	public String getDefaultLastScoredSource() {return defaultSourceProps.getProperty("LastScored");}
	public String getDefaultGameTimeSource() {return defaultSourceProps.getProperty("GameTime");}
	public String getDefaultMatchTimeSource() {return defaultSourceProps.getProperty("MatchTime");}
	public String getDefaultStreamTimeSource() {return defaultSourceProps.getProperty("StreamTime");}
	//Stat Sources
	public String getDefaultStuffs1Source() {return defaultStatsSourceProps.getProperty("Stuffs1");}
	public String getDefaultStuffs2Source() {return defaultStatsSourceProps.getProperty("Stuffs2");}
	public String getDefaultStuffs3Source() {return defaultStatsSourceProps.getProperty("Stuffs3");}
	public String getDefaultBreaks1Source() {return defaultStatsSourceProps.getProperty("Breaks1");}
	public String getDefaultBreaks2Source() {return defaultStatsSourceProps.getProperty("Breaks2");}
	public String getDefaultBreaks3Source() {return defaultStatsSourceProps.getProperty("Breaks3");}
	public String getDefaultAces1Source() {return defaultStatsSourceProps.getProperty("Aces1");}
	public String getDefaultAces2Source() {return defaultStatsSourceProps.getProperty("Aces2");}
	public String getDefaultAces3Source() {return defaultStatsSourceProps.getProperty("Aces3");}
	public String getDefaultTeam1PassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team1PassAttempts");}
	public String getDefaultTeam1PassCompletesSource() {return defaultStatsSourceProps.getProperty("Team1PassCompletes");}
	public String getDefaultTeam2PassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team2PassAttempts");}
	public String getDefaultTeam2PassCompletesSource() {return defaultStatsSourceProps.getProperty("Team2PassCompletes");}
	public String getDefaultTeam3PassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team3PassAttempts");}
	public String getDefaultTeam3PassCompletesSource() {return defaultStatsSourceProps.getProperty("Team3PassCompletes");}
	public String getDefaultTeam1ShotAttemptsSource() {return defaultStatsSourceProps.getProperty("Team1ShotAttempts");}
	public String getDefaultTeam1ShotCompletesSource() {return defaultStatsSourceProps.getProperty("Team1ShotCompletes");}
	public String getDefaultTeam2ShotAttemptsSource() {return defaultStatsSourceProps.getProperty("Team2ShotAttempts");}
	public String getDefaultTeam2ShotCompletesSource() {return defaultStatsSourceProps.getProperty("Team2ShotCompletes");}
	public String getDefaultTeam3ShotAttemptsSource() {return defaultStatsSourceProps.getProperty("Team3ShotAttempts");}
	public String getDefaultTeam3ShotCompletesSource() {return defaultStatsSourceProps.getProperty("Team3ShotCompletes");}
	public String getDefaultTeam1ClearAttemptsSource() {return defaultStatsSourceProps.getProperty("Team1ClearAttempts");}
	public String getDefaultTeam1ClearCompletesSource() {return defaultStatsSourceProps.getProperty("Team1ClearCompletes");}
	public String getDefaultTeam2ClearAttemptsSource() {return defaultStatsSourceProps.getProperty("Team2ClearAttempts");}
	public String getDefaultTeam2ClearCompletesSource() {return defaultStatsSourceProps.getProperty("Team2ClearCompletes");}
	public String getDefaultTeam3ClearAttemptsSource() {return defaultStatsSourceProps.getProperty("Team3ClearAttempts");}
	public String getDefaultTeam3ClearCompletesSource() {return defaultStatsSourceProps.getProperty("Team3ClearCompletes");}
	public String getDefaultSide1ColorSource() {return defaultStatsSourceProps.getProperty("Side1Color");}
	public String getDefaultSide2ColorSource() {return defaultStatsSourceProps.getProperty("Side2Color");}
	public String getDefaultSide3ColorSource() {return defaultStatsSourceProps.getProperty("Side3Color");}
	public String getDefaultTeam1PassPercentSource() {return defaultStatsSourceProps.getProperty("Team1PassPercent");}
	public String getDefaultTeam2PassPercentSource() {return defaultStatsSourceProps.getProperty("Team2PassPercent");}
	public String getDefaultTeam3PassPercentSource() {return defaultStatsSourceProps.getProperty("Team3PassPercent");}
	public String getDefaultTeam1ShotPercentSource() {return defaultStatsSourceProps.getProperty("Team1ShotPercent");}
	public String getDefaultTeam2ShotPercentSource() {return defaultStatsSourceProps.getProperty("Team2ShotPercent");}
	public String getDefaultTeam3ShotPercentSource() {return defaultStatsSourceProps.getProperty("Team3ShotPercent");}
	public String getDefaultTeam1ClearPercentSource() {return defaultStatsSourceProps.getProperty("Team1ClearPercent");}
	public String getDefaultTeam2ClearPercentSource() {return defaultStatsSourceProps.getProperty("Team2ClearPercent");}
	public String getDefaultTeam3ClearPercentSource() {return defaultStatsSourceProps.getProperty("Team3ClearPercent");}
	public String getDefaultTeam1TwoBarPassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team1TwoBarPassAttempts");}
	public String getDefaultTeam1TwoBarPassCompletesSource() {return defaultStatsSourceProps.getProperty("Team1TwoBarPassCompletes");}
	public String getDefaultTeam2TwoBarPassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team2TwoBarPassAttempts");}
	public String getDefaultTeam2TwoBarPassCompletesSource() {return defaultStatsSourceProps.getProperty("Team2TwoBarPassCompletes");}
	public String getDefaultTeam3TwoBarPassAttemptsSource() {return defaultStatsSourceProps.getProperty("Team3TwoBarPassAttempts");}
	public String getDefaultTeam3TwoBarPassCompletesSource() {return defaultStatsSourceProps.getProperty("Team3TwoBarPassCompletes");}
	public String getDefaultTeam1TwoBarPassPercentSource() {return defaultStatsSourceProps.getProperty("Team1TwoBarPassPercent");}
	public String getDefaultTeam2TwoBarPassPercentSource() {return defaultStatsSourceProps.getProperty("Team2TwoBarPassPercent");}
	public String getDefaultTeam3TwoBarPassPercentSource() {return defaultStatsSourceProps.getProperty("Team3TwoBarPassPercent");}
	public String getDefaultTeam1ScoringSource() {return defaultStatsSourceProps.getProperty("Team1Scoring");}
	public String getDefaultTeam2ScoringSource() {return defaultStatsSourceProps.getProperty("Team2Scoring");}
	public String getDefaultTeam3ScoringSource() {return defaultStatsSourceProps.getProperty("Team3Scoring");}
	public String getDefaultTeam1ThreeBarScoringSource() {return defaultStatsSourceProps.getProperty("Team1ThreeBarScoring");}
	public String getDefaultTeam2ThreeBarScoringSource() {return defaultStatsSourceProps.getProperty("Team2ThreeBarScoring");}
	public String getDefaultTeam3ThreeBarScoringSource() {return defaultStatsSourceProps.getProperty("Team3ThreeBarScoring");}
	public String getDefaultTeam1FiveBarScoringSource() {return defaultStatsSourceProps.getProperty("Team1FiveBarScoring");}
	public String getDefaultTeam2FiveBarScoringSource() {return defaultStatsSourceProps.getProperty("Team2FiveBarScoring");}
	public String getDefaultTeam3FiveBarScoringSource() {return defaultStatsSourceProps.getProperty("Team3FiveBarScoring");}
	public String getDefaultTeam1TwoBarScoringSource() {return defaultStatsSourceProps.getProperty("Team1TwoBarScoring");}	
	public String getDefaultTeam2TwoBarScoringSource() {return defaultStatsSourceProps.getProperty("Team2TwoBarScoring");}	
	public String getDefaultTeam3TwoBarScoringSource() {return defaultStatsSourceProps.getProperty("Team3TwoBarScoring");}	
	public String getDefaultTeam1ShotsOnGoalSource() {return defaultStatsSourceProps.getProperty("Team1ShotsOnGoal");}
	public String getDefaultTeam2ShotsOnGoalSource() {return defaultStatsSourceProps.getProperty("Team2ShotsOnGoal");}
	public String getDefaultTeam3ShotsOnGoalSource() {return defaultStatsSourceProps.getProperty("Team3ShotsOnGoal");}
	public String getDefaultShowScoresSource() {return defaultStatsSourceProps.getProperty("ShowScoresSource");}
	public String getDefaultShowTimerSource() {return defaultStatsSourceProps.getProperty("ShowTimerSource");}
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
	public String getDefaultSwitchTeamsHotKey() {return defaultHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public String getDefaultSwitchGameCountsHotKey() {return defaultHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public String getDefaultTeamSwitchPositionsHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Team" + teamNumber + "SwitchPositionsHotKey");}
	public String getDefaultSwitchPlayerHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("SwitchPlayer" + teamNumber + "HotKey");}
	public String getDefaultGameCountMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("GameCount" + teamNumber + "MinusHotKey");}
	public String getDefaultGameCountPlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("GameCount" + teamNumber + "PlusHotKey");}
	public String getDefaultTimeOutMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("TimeOut" + teamNumber + "MinusHotKey");}
	public String getDefaultTimeOutPlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("TimeOut" + teamNumber + "PlusHotKey");}
	public String getDefaultResetHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Reset" + teamNumber + "HotKey");}
	public String getDefaultWarnHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Warn" + teamNumber + "HotKey");}
	public String getDefaultScoreMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Score" + teamNumber + "MinusHotKey");}
	public String getDefaultScorePlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Score" + teamNumber + "PlusHotKey");}
	public String getDefaultSwitchScoresHotKey() {return defaultHotKeyProps.getProperty("SwitchScoresHotKey");}
	public String getDefaultSwitchTimeOutsHotKey() {return defaultHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
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

	public void loadFromControlConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configControlFileName))) {
			configControlProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configControlFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configControlFileName));
			configControlProps = defaultControlProps;
			saveControlConfig();
		}
	}
	public void loadFromOBSConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configOBSFileName))) {
			configOBSProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configOBSFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configOBSFileName));
			configOBSProps = defaultOBSProps;
			saveOBSConfig();
		}
	}
	public void loadFromSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configSourceFileName))) {
			configSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configSourceFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configSourceFileName));
			configSourceProps = defaultSourceProps;
			saveSourceConfig();
		}
	}
	public void loadFromStatsSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configStatsSourceFileName))) {
			configStatsSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configStatsSourceFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configStatsSourceFileName));
			configStatsSourceProps = defaultStatsSourceProps;
			saveStatsSourceConfig();
		}
	}
	public void loadFromFilterConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configFilterFileName))) {
			configFilterProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configFilterFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configFilterFileName));
			configFilterProps = defaultFilterProps;
			saveFilterConfig();
		}
	}
	public void loadFromPartnerProgramConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configPartnerProgramFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configPartnerProgramFileName));
			configPartnerProgramProps = defaultPartnerProgramProps;
			savePartnerProgramConfig();
		}
	}
	public void loadFromHotKeyConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configHotKeyFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configHotKeyFileName));
			configHotKeyProps = defaultHotKeyProps;
			saveHotKeyConfig();
		}
	}
	public void loadFromAutoScoreSettingsConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configAutoScoreSettingsFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configAutoScoreSettingsFileName));
			configAutoScoreSettingsProps = defaultAutoScoreSettingsProps;
			saveAutoScoreSettingsConfig();
		}
	}
	public void saveControlConfig() throws IOException {
		//Control Parameters
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configControlFileName))) {
			configControlProps.store(outputStream, "FoosOBSPlus Control settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configControlFileName));
		}
	}
	public void saveOBSConfig() throws IOException {
		//OBS
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configOBSFileName))) {
			configOBSProps.store(outputStream, "FoosOBSPlus OBS Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configOBSFileName));
		}
	}
	public void saveSourceConfig() throws IOException {
		//Source
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configSourceFileName))) {
			configSourceProps.store(outputStream, "FoosOBSPlus Source Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configSourceFileName));
		}
	}
	public void saveStatsSourceConfig() throws IOException {
		//Stats Source
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configStatsSourceFileName))) {
			configStatsSourceProps.store(outputStream, "FoosOBSPlus Stats Source Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configStatsSourceFileName));
		}
	}
	public void saveFilterConfig() throws IOException {
		//Filter
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configFilterFileName))) {
			configFilterProps.store(outputStream, "FoosOBSPlus Filter Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configFilterFileName));
		}
	}
	public void savePartnerProgramConfig() throws IOException {
		//PartnerProgram
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.store(outputStream, "FoosOBSPlus Partner Program Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configPartnerProgramFileName));
		}
	}
	public void saveHotKeyConfig() throws IOException {
		//HotKeys
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.store(outputStream, "FoosOBSPlus Hot Key Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configHotKeyFileName));
		}
	}
	public void saveAutoScoreSettingsConfig() throws IOException {
		//AutoScore Settings
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.store(outputStream, "FoosOBSPlus AutoScore Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configAutoScoreSettingsFileName));
		}
	}
	public void generateHotKeyScripts() {
		String basePath = System.getProperty("user.dir");
		String baseScriptText = getHotKeyBaseScript();
		String hotKeyScriptPath = getHotKeyScriptPath();
		if(Files.exists(Paths.get(basePath))) {
			if(!Files.exists(Paths.get(hotKeyScriptPath))) {
				try {
					Files.createDirectory(Paths.get(hotKeyScriptPath));
				} catch (IOException e1) {
					logger.error(Messages.getString("Errors.ErrorCreatingDirectory") + " " + hotKeyScriptPath);
					logger.error(e1.toString());
					JOptionPane.showMessageDialog(null,  Messages.getString("Errors.ErrorCreatingDirectory") + " " + hotKeyScriptPath);
					return;
				}
				logger.error("Hot key script directory " + hotKeyScriptPath + " successfully created.");
			}
			if(Files.exists(Paths.get(hotKeyScriptPath))) {
				if (baseScriptText.isEmpty()) {
					JOptionPane.showMessageDialog(null, Messages.getString("Errors.BaseScriptFile"), "Scripting Error", 1);
				} else {
					String[] baseScript = baseScriptText.split("\\r\\n");
					configHotKeyProps.entrySet()
						.stream()
						.filter(e -> e.getValue().toString().length() > 0)
						.forEach(e->createHotKeyScript(e.getKey().toString(), e.getValue().toString(), baseScript, basePath));
					logger.error("Hot key scripts generated successfully to " + hotKeyScriptPath + ".");
					JOptionPane.showMessageDialog(null, "Done");
				}
			} else {
				logger.error("Could not find " + hotKeyScriptPath + " when trying to generate hot key scripts.");
				JOptionPane.showMessageDialog(null, Messages.getString("Errors.DirectoryDoesNotExist") + " " + hotKeyScriptPath);
			}
		} else {
			logger.error("Could not find " + basePath + " when trying to generate hot key scripts.");
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
			logger.error("Could not write to " + getHotKeyScriptPath() + File.separator + keyFunction + ".ahk");
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, Messages.getString("Errors.ScriptWriteFailure") + " " + keyFunction, "Scripting Error", 1);
		}
	}
	public int getMaxGameNumber() {
		// 	for cutthroat -> settings.getGamesToWin()*3-2;
		//  for regular   -> settings.getGamesToWin()*2-1;
		return getGamesToWin()*(2+getCutThroatMode())-(1+getCutThroatMode());
	}
}
