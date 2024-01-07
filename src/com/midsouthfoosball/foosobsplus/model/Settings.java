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

public final class Settings {
	private final static Settings instance 		= new Settings();
	// Parameter settings
	private final static String separator 		= FileSystems.getDefault().getSeparator();
	private final static String gameType		= "Foosball";
	private static String[] lastScoredStrings 	= new String[4];
	private final static int borderTop			= 2;
	private final static int borderBottom 		= 0;
	private final static int borderLeft			= 0;
	private final static int borderRight 		= 0;

	// Property Settings
	private static Properties defaultControlProps 			= new Properties();
	private static Properties defaultSourceProps 			= new Properties();
	private static Properties defaultStatsSourceProps 		= new Properties();
	private static Properties defaultFilterProps 			= new Properties();
	private static Properties defaultPartnerProgramProps 	= new Properties();
	private static Properties defaultHotKeyProps 			= new Properties();
	private static Properties defaultOBSProps 				= new Properties();
	private static Properties defaultAutoScoreSettingsProps = new Properties();
	
	public static Properties configControlProps;
	public static Properties configSourceProps;
	public static Properties configStatsSourceProps;
	public static Properties configFilterProps;
	public static Properties configPartnerProgramProps;
	public static Properties configHotKeyProps;
	public static Properties configOBSProps;
	public static Properties configAutoScoreSettingsProps;
	
	private final static String configControlFileName			= "control.properties";
	private final static String configSourceFileName 			= "source.properties";
	private final static String configStatsSourceFileName 		= "statssource.properties";
	private final static String configFilterFileName            = "filter.properties";
	private final static String configPartnerProgramFileName    = "partnerprogram.properties";
	private final static String configHotKeyFileName 			= "hotkey.properties";
	private final static String configOBSFileName        		= "obs.properties";
	private final static String configAutoScoreSettingsFileName	= "autoscoresettings.properties";
	private final static Logger logger = LoggerFactory.getLogger(Settings.class);
	static {
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
		defaultSourceProps.setProperty("TimeRemaining", "timeremaining");
		defaultSourceProps.setProperty("TimerInUse", "timerinuse");
		defaultSourceProps.setProperty("Team1GameCount", "team1gamecount");
		defaultSourceProps.setProperty("Team2GameCount", "team2gamecount");
		defaultSourceProps.setProperty("Team3GameCount", "team3gamecount");
		defaultSourceProps.setProperty("Team1MatchCount", "team1matchcount");
		defaultSourceProps.setProperty("Team2MatchCount", "team2matchcount");
		defaultSourceProps.setProperty("Team3MatchCount", "team3matchcount");
		defaultSourceProps.setProperty("Team1Score", "team1score");
		defaultSourceProps.setProperty("MatchWinner", "matchwinner");
		defaultSourceProps.setProperty("Meatball",  "meatball");
		defaultSourceProps.setProperty("GameResults", "gameresults");
		defaultSourceProps.setProperty("Team2Score", "team2score");
		defaultSourceProps.setProperty("Team3Score", "team3score");
		defaultSourceProps.setProperty("Team1TimeOut", "team1timeout");
		defaultSourceProps.setProperty("Team2TimeOut", "team2timeout");
		defaultSourceProps.setProperty("Team3TimeOut", "team3timeout");
		defaultSourceProps.setProperty("Team1Reset", "team1reset");
		defaultSourceProps.setProperty("Team2Reset", "team2reset");
		defaultSourceProps.setProperty("Team3Reset", "team3reset");
		defaultSourceProps.setProperty("Team1Warn", "team1warn");
		defaultSourceProps.setProperty("Team2Warn", "team2warn");
		defaultSourceProps.setProperty("Team3Warn", "team3warn");
		defaultSourceProps.setProperty("Team1KingSeat", "team1kingseat");
		defaultSourceProps.setProperty("Team2KingSeat", "team2kingseat");
		defaultSourceProps.setProperty("Team3KingSeat", "team3kingseat");
		defaultSourceProps.setProperty("Team1Game1Show", "team1game1");
		defaultSourceProps.setProperty("Team2Game1Show", "team2game1");
		defaultSourceProps.setProperty("Team3Game1Show", "team3game1");
		defaultSourceProps.setProperty("Team1Game2Show", "team1game2");
		defaultSourceProps.setProperty("Team2Game2Show", "team2game2");
		defaultSourceProps.setProperty("Team3Game2Show", "team3game2");
		defaultSourceProps.setProperty("Team1Game3Show", "team1game3");
		defaultSourceProps.setProperty("Team2Game3Show", "team2game3");
		defaultSourceProps.setProperty("Team3Game3Show", "team3game3");
		defaultSourceProps.setProperty("LastScored","lastscored");
		defaultSourceProps.setProperty("GameTime", "gametime");
		defaultSourceProps.setProperty("MatchTime", "matchtime");
		defaultSourceProps.setProperty("StreamTime", "streamtime");
		defaultSourceProps.setProperty("Side1Color","side1color");
		defaultSourceProps.setProperty("Side2Color","side2color");
		defaultSourceProps.setProperty("Side3Color","side3color");
		defaultSourceProps.setProperty("ShowScores", "ScoresAndLabels");
		defaultSourceProps.setProperty("ShowTimer", "Foos OBS+ Timer");
		defaultSourceProps.setProperty("ShowCutthroat", "Cutthroat");
			//Stat Sources
		defaultStatsSourceProps.setProperty("Team1PassAttempts", "team1passattempts");
		defaultStatsSourceProps.setProperty("Team2PassAttempts", "team2passattempts");
		defaultStatsSourceProps.setProperty("Team3PassAttempts", "team3passattempts");
		defaultStatsSourceProps.setProperty("Team1PassCompletes", "team1passcompletes");
		defaultStatsSourceProps.setProperty("Team2PassCompletes", "team2passcompletes");
		defaultStatsSourceProps.setProperty("Team3PassCompletes", "team3passcompletes");
		defaultStatsSourceProps.setProperty("Team1PassPercent", "team1passpercent");
		defaultStatsSourceProps.setProperty("Team2PassPercent", "team2passpercent");
		defaultStatsSourceProps.setProperty("Team3PassPercent", "team3passpercent");
		defaultStatsSourceProps.setProperty("Team1ShotAttempts", "team1shotattempts");
		defaultStatsSourceProps.setProperty("Team2ShotAttempts", "team2shotattempts");
		defaultStatsSourceProps.setProperty("Team3ShotAttempts", "team3shotattempts");
		defaultStatsSourceProps.setProperty("Team1ShotCompletes", "team1shotcompletes");
		defaultStatsSourceProps.setProperty("Team2ShotCompletes", "team2shotcompletes");
		defaultStatsSourceProps.setProperty("Team3ShotCompletes", "team3shotcompletes");
		defaultStatsSourceProps.setProperty("Team1ShotPercent", "team1shotpercent");
		defaultStatsSourceProps.setProperty("Team2ShotPercent", "team2shotpercent");
		defaultStatsSourceProps.setProperty("Team3ShotPercent", "team3shotpercent");
		defaultStatsSourceProps.setProperty("Team1ClearAttempts", "team1clearattempts");
		defaultStatsSourceProps.setProperty("Team2ClearAttempts", "team2clearattempts");
		defaultStatsSourceProps.setProperty("Team3ClearAttempts", "team3clearattempts");
		defaultStatsSourceProps.setProperty("Team1ClearCompletes", "team1clearcompletes");
		defaultStatsSourceProps.setProperty("Team2ClearCompletes", "team2clearcompletes");
		defaultStatsSourceProps.setProperty("Team3ClearCompletes", "team3clearcompletes");
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
		defaultStatsSourceProps.setProperty("Team1Aces",  "team1aces");
		defaultStatsSourceProps.setProperty("Team2Aces",  "team2aces");
		defaultStatsSourceProps.setProperty("Team3Aces",  "team3aces");
		defaultStatsSourceProps.setProperty("Team1Stuffs", "team1stuffs");
		defaultStatsSourceProps.setProperty("Team2Stuffs", "team2stuffs");
		defaultStatsSourceProps.setProperty("Team3Stuffs", "team3stuffs");
		defaultStatsSourceProps.setProperty("Team1Breaks", "team1breaks");
		defaultStatsSourceProps.setProperty("Team2Breaks", "team2breaks");
		defaultStatsSourceProps.setProperty("Team3Breaks", "team3breaks");
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
		defaultHotKeyProps.setProperty("EndMatchHotKey", "");
		defaultHotKeyProps.setProperty("StartGameHotKey", ".");
		defaultHotKeyProps.setProperty("TournamentNameClearHotKey", "");
		defaultHotKeyProps.setProperty("Team1SwitchPositionsHotKey", "t");
		defaultHotKeyProps.setProperty("Team2SwitchPositionsHotKey", "m");
		defaultHotKeyProps.setProperty("Team3SwitchPositionsHotKey", "");
		defaultHotKeyProps.setProperty("SwitchTeamsHotKey", "e");
		defaultHotKeyProps.setProperty("SwitchForwardsHotKey", ";");
		defaultHotKeyProps.setProperty("SwitchGoaliesHotKey", "x");
		defaultHotKeyProps.setProperty("GameCount1MinusHotKey", "j");
		defaultHotKeyProps.setProperty("GameCount2MinusHotKey", "i");
		defaultHotKeyProps.setProperty("GameCount3MinusHotKey", "");
		defaultHotKeyProps.setProperty("GameCount1PlusHotKey", "5");
		defaultHotKeyProps.setProperty("GameCount2PlusHotKey", "6");
		defaultHotKeyProps.setProperty("GameCount3PlusHotKey", "");
		defaultHotKeyProps.setProperty("GameCountSwitchHotKey", "l");
		defaultHotKeyProps.setProperty("MatchCount1MinusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCount2MinusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCount3MinusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCount1PlusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCount2PlusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCount3PlusHotKey", "");
		defaultHotKeyProps.setProperty("MatchCountSwitchHotKey", "");
		defaultHotKeyProps.setProperty("Score1MinusHotKey", "4");
		defaultHotKeyProps.setProperty("Score2MinusHotKey", "8");
		defaultHotKeyProps.setProperty("Score3MinusHotKey", "");
		defaultHotKeyProps.setProperty("Score1PlusHotKey", "1");
		defaultHotKeyProps.setProperty("Score2PlusHotKey", "2");
		defaultHotKeyProps.setProperty("Score3PlusHotKey", "");
		defaultHotKeyProps.setProperty("SwitchScoresHotKey", "");
		defaultHotKeyProps.setProperty("SwitchGameCountsHotKey", "");
		defaultHotKeyProps.setProperty("SwitchMatchCountsHotKey", "");
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
		defaultHotKeyProps.setProperty("KingSeat1HotKey", "");
		defaultHotKeyProps.setProperty("KingSeat2HotKey", "");
		defaultHotKeyProps.setProperty("KingSeat3HotKey", "");
		defaultHotKeyProps.setProperty("SwitchResetWarnsHotKey", "");
		defaultHotKeyProps.setProperty("SwitchSidesHotKey", "w");
		defaultHotKeyProps.setProperty("ResetNamesHotKey", "");
		defaultHotKeyProps.setProperty("ResetGameCountsHotKey", "7");
		defaultHotKeyProps.setProperty("ResetMatchCountsHotKey", "");
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
		defaultHotKeyProps.setProperty("ShowCutthroatHotKey", "");
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
		try {
			loadFromControlConfig();
			loadFromOBSConfig();
			loadFromSourceConfig();
			loadFromStatsSourceConfig();
			loadFromFilterConfig();
			loadFromPartnerProgramConfig();
			loadFromHotKeyConfig();
			loadFromAutoScoreSettingsConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Settings() {
	}
	public static Settings getInstance() {
		return instance;
	}
	public static int getBorderTop() {return borderTop;};
	public static int getBorderBottom() {return borderBottom;};
	public static int getBorderLeft() {return borderLeft;};
	public static int getBorderRight() {return borderRight;};
	//Getters
	//Control Parameters
	public static String getGameType() {return gameType;}
	public static boolean getShowParsed() {return Boolean.parseBoolean(configControlProps.getProperty("ShowParsed"));}
	public static String getTableName() {return configControlProps.getProperty("TableName");}
	public static String getDatapath() {return configControlProps.getProperty("datapath");}
	public static int getPointsToWin() {return Integer.parseInt(configControlProps.getProperty("PointsToWin"));}
	public static int getMaxWin() {return Integer.parseInt(configControlProps.getProperty("MaxWin"));}
	public static int getWinBy() {return Integer.parseInt(configControlProps.getProperty("WinBy"));}
	public static int getGamesToWin() {return Integer.parseInt(configControlProps.getProperty("GamesToWin"));}
	public static int getMaxTimeOuts() {return Integer.parseInt(configControlProps.getProperty("MaxTimeOuts"));}
	public static int getAutoIncrementGame() {return Integer.parseInt(configControlProps.getProperty("AutoIncrementGame"));}
	public static int getAnnounceWinner() {return Integer.parseInt(configControlProps.getProperty("AnnounceWinner"));}
	public static int getAnnounceMeatball() {return Integer.parseInt(configControlProps.getProperty("AnnounceMeatball"));}
	public static String getWinnerPrefix() {return configControlProps.getProperty("WinnerPrefix");}
	public static String getWinnerSuffix() {return configControlProps.getProperty("WinnerSuffix");}
	public static String getMeatball() {return configControlProps.getProperty("Meatball");}
	public static String getTeam1LastScored() {return configControlProps.getProperty("Team1LastScored");}
	public static String getTeam2LastScored() {return configControlProps.getProperty("Team2LastScored");}
	public static String getTeam3LastScored() {return configControlProps.getProperty("Team3LastScored");}
	public static String getClearLastScored() {return configControlProps.getProperty("ClearLastScored");}
	public static String[] getLastScoredStrings() {
		lastScoredStrings[0] = getClearLastScored();
		lastScoredStrings[1] = getTeam1LastScored();
		lastScoredStrings[2] = getTeam2LastScored();
		lastScoredStrings[3] = getTeam3LastScored();
		return lastScoredStrings;
	}
	public static String getSide1Color() {return configControlProps.getProperty("Side1Color");}
	public static String getSide2Color() {return configControlProps.getProperty("Side2Color");}
	public static int getBallsInRack() {return Integer.parseInt(configControlProps.getProperty("BallsInRack"));}
	public static int getShotTime() {return Integer.parseInt(configControlProps.getProperty("ShotTime"));}
	public static int getPassTime() {return Integer.parseInt(configControlProps.getProperty("PassTime"));}
	public static int getTimeOutTime() {return Integer.parseInt(configControlProps.getProperty("TimeOutTime"));}
	public static int getGameTime() {return Integer.parseInt(configControlProps.getProperty("GameTime"));}
	public static int getRecallTime() {return Integer.parseInt(configControlProps.getProperty("RecallTime"));}
	public static int getShowTimeOutsUsed() {return Integer.parseInt(configControlProps.getProperty("ShowTimeOutsUsed"));}
	public static int getAutoCapNames() {return Integer.parseInt(configControlProps.getProperty("AutoCapNames"));}
	public static int getWinByFinalOnly() {return Integer.parseInt(configControlProps.getProperty("WinByFinalOnly"));}
	public static int getShowSkunk() {return Integer.parseInt(configControlProps.getProperty("ShowSkunk"));}
	public static int getCutThroatMode() {return Integer.parseInt(configControlProps.getProperty("CutThroatMode"));}
	public static int getRackMode() {return Integer.parseInt(configControlProps.getProperty("RackMode"));}
	public static String getLogoImageURL() {return configControlProps.getProperty("LogoImageURL");}
	public static String getLogoLinkURI() {return configControlProps.getProperty("LogoLinkURI");}
	//OBS
	public static String getOBSHost() {return configOBSProps.getProperty("OBSHost");}
	public static String getOBSPort() {return configOBSProps.getProperty("OBSPort").isEmpty()?"0": configOBSProps.getProperty("OBSPort");}
	public static String getOBSPassword() {return configOBSProps.getProperty("OBSPassword");}
	public static String getOBSScene() {return configOBSProps.getProperty("OBSScene");}
	public static int getOBSSavePassword() {return Integer.parseInt(configOBSProps.getProperty("OBSSavePassword"));}
	public static int getOBSAutoLogin() {return Integer.parseInt(configOBSProps.getProperty("OBSAutoLogin"));}
	public static int getOBSCloseOnConnect() {return Integer.parseInt(configOBSProps.getProperty("OBSCloseOnConnect"));}
	public static int getOBSUpdateOnConnect()  {return Integer.parseInt(configOBSProps.getProperty("OBSUpdateOnConnect"));}
	//Sources
	public static String getTeamNameSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Name");//Team1Name
	}
	public static String getTeamForwardSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Forward");//Team1Forward
	}
	public static String getTeamGoalieSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Goalie");//Team1Goalie
	}
	public static String getTeamGameCountSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "GameCount");//Team1GameCount
	}
	public static String getTeamMatchCountSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "MatchCount");//Team1MatchCount
	}
	public static String getTeamScoreSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Score");//Team1Score
	}
	public static String getTeamTimeOutSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "TimeOut");//Team1TimeOut
	}
	public static String getTeamResetSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Reset");//Team1Reset
	}
	public static String getTeamWarnSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Warn");//Team1Warn
	}
	public static String getTeamKingSeatSource(String teamNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "KingSeat");//Team1KingSeat
	}
	public static String getTeamGameShowSource(int teamNumber, int gameNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Game" + gameNumber + "Show");
	}
	public static String getTournamentSource() {return configSourceProps.getProperty("Tournament");}
	public static String getEventSource() {return configSourceProps.getProperty("Event");}
	public static String getTableNameSource() {return configSourceProps.getProperty("TableName");}
	public static String getTimeRemainingSource() {return configSourceProps.getProperty("TimeRemaining");}
	public static String getTimerInUseSource() {return configSourceProps.getProperty("TimerInUse");}
	public static String getMatchWinnerSource() {return configSourceProps.getProperty("MatchWinner");}
	public static String getMeatballSource() {return configSourceProps.getProperty("Meatball");}
	public static String getGameResultsSource() {return configSourceProps.getProperty("GameResults");}
	public static String getLastScoredSource() {return configSourceProps.getProperty("LastScored");}
	public static String getGameTimeSource() {return configSourceProps.getProperty("GameTime");}
	public static String getMatchTimeSource() {return configSourceProps.getProperty("MatchTime");}
	public static String getStreamTimeSource() {return configSourceProps.getProperty("StreamTime");}
	public static String getSide1ColorSource() {return configSourceProps.getProperty("Side1Color");}
	public static String getSide2ColorSource() {return configSourceProps.getProperty("Side2Color");}
	public static String getSide3ColorSource() {return configSourceProps.getProperty("Side3Color");}
	public static String getShowScoresSource() {return configSourceProps.getProperty("ShowScores");}
	public static String getShowTimerSource() {return configSourceProps.getProperty("ShowTimer");}
	public static String getShowCutthroatSource() {return configSourceProps.getProperty("ShowCutthroat");}
	//Stat Sources
	public static String getPassAttemptsSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassAttempts");//Team1PassAttempts
	}
	public static String getPassCompletesSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassCompletes");//Team1PassCompletes
	}
	public static String getShotAttemptsSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotAttempts");//Team1ShotAttempts
	}
	public static String getShotCompletesSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotCompletes");//Team1ShotCompletes
	}
	public static String getClearAttemptsSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearAttempts");//Team1ClearAttempts
	}
	public static String getClearCompletesSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearCompletes");//Team1ClearCompletes
	}
	public static String getPassPercentSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "PassPercent");//Team1PassPercent
	}
	public static String getShotPercentSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotPercent");//Team1ShotPercent
	}
	public static String getClearPercentSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ClearPercent");//Team1ClearPercent
	}
	public static String getTwoBarPassAttemptsSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassAttempts");//Team1TwoBarPassAttempts
	}
	public static String getTwoBarPassCompletesSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassCompletes");//Team1TwoBarPassCompletes
	}
	public static String getTwoBarPassPercentSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassPercent");//Team1TwoBarPassPercent
	}
	public static String getScoringSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "Scoring");//Team1Scoring
	}
	public static String getThreeBarScoringSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ThreeBarScoring");//Team1ThreeBarScoring
	}
	public static String getFiveBarScoringSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "FiveBarScoring");//Team1FiveBarScoring
	}
	public static String getTwoBarScoringSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarScoring");//Team1TwoBarScoring
	}
	public static String getShotsOnGoalSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "ShotsOnGoal");//Team1ShotsOnGoal
	}
	public static String getStuffsSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "Stuffs");//Stuffs1
	}
	public static String getBreaksSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "Breaks");//Breaks1
	}
	public static String getAcesSource(String teamNumber) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + "Aces");//Aces1
	}
	//Filters
	public static String getFiltersFilter(String filter) {return configFilterProps.getProperty(filter);}
	public static String getTeam1ScoreFilter() {return configFilterProps.getProperty("Team1Score");}
	public static String getTeam2ScoreFilter() {return configFilterProps.getProperty("Team2Score");}
	public static String getTeam1WinGameFilter() {return configFilterProps.getProperty("Team1WinGame");}
	public static String getTeam2WinGameFilter() {return configFilterProps.getProperty("Team2WinGame");}
	public static String getTeam1WinMatchFilter() {return configFilterProps.getProperty("Team1WinMatch");}
	public static String getTeam2WinMatchFilter() {return configFilterProps.getProperty("Team2WinMatch");}
	public static String getTeam1TimeOutFilter() {return configFilterProps.getProperty("Team1TimeOut");}
	public static String getTeam2TimeOutFilter() {return configFilterProps.getProperty("Team2TimeOut");}
	public static String getTeam1ResetFilter() {return configFilterProps.getProperty("Team1Reset");}
	public static String getTeam2ResetFilter() {return configFilterProps.getProperty("Team2Reset");}
	public static String getTeam1WarnFilter() {return configFilterProps.getProperty("Team1Warn");}
	public static String getTeam2WarnFilter() {return configFilterProps.getProperty("Team2Warn");}
	public static String getTeam1SwitchPositionsFilter() {return configFilterProps.getProperty("Team1SwitchPositions");}
	public static String getTeam2SwitchPositionsFilter() {return configFilterProps.getProperty("Team2SwitchPositions");}
	public static String getTeam1SkunkFilter() {return configFilterProps.getProperty("Team1Skunk");}
	public static String getTeam2SkunkFilter() {return configFilterProps.getProperty("Team2Skunk");}
	public static String getStartMatchFilter() {return configFilterProps.getProperty("StartMatch");}
	public static String getStartGameFilter() {return configFilterProps.getProperty("StartGame");}
	public static String getSwitchSidesFilter() {return configFilterProps.getProperty("SwitchSides");}	//PartnerProgram
	public static String getPartnerProgramPath() {return configPartnerProgramProps.getProperty("PartnerProgramPath");}
	public static String getPlayer1FileName() {return configPartnerProgramProps.getProperty("Player1FileName");}
	public static String getPlayer2FileName() {return configPartnerProgramProps.getProperty("Player2FileName");}
	public static String getPlayer3FileName() {return configPartnerProgramProps.getProperty("Player3FileName");}
	public static String getPlayer4FileName() {return configPartnerProgramProps.getProperty("Player4FileName");}
	//HotKeys
	public static String getStartMatchHotKey() {return configHotKeyProps.getProperty("StartMatchHotKey");}
	public static String getPauseMatchHotKey() {return configHotKeyProps.getProperty("PauseMatchHotKey");}
	public static String getEndMatchHotKey() {return configHotKeyProps.getProperty("EndMatchHotKey");}
	public static String getStartGameHotKey() {return configHotKeyProps.getProperty("StartGameHotKey");}
	public static String getTeamSwitchPositionsHotKey(String teamNumber) {return configHotKeyProps.getProperty("Team" + teamNumber + "SwitchPositionsHotKey");}
	public static String getSwitchForwardsHotKey() {return configHotKeyProps.getProperty("SwitchForwardsHotKey");}
	public static String getSwitchGoaliesHotKey() {return configHotKeyProps.getProperty("SwitchGoaliesHotKey");}
	public static String getGameCountMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("GameCount" + teamNumber + "MinusHotKey");}
	public static String getGameCountPlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("GameCount" + teamNumber + "PlusHotKey");}
	public static String getMatchCountMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("MatchCount" + teamNumber + "MinusHotKey");}
	public static String getMatchCountPlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("MatchCount" + teamNumber + "PlusHotKey");}
	public static String getTimeOutMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("TimeOut" + teamNumber + "MinusHotKey");}
	public static String getTimeOutPlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("TimeOut" + teamNumber + "PlusHotKey");}
	public static String getScoreMinusHotKey(String teamNumber) {return configHotKeyProps.getProperty("Score" + teamNumber + "MinusHotKey");}
	public static String getScorePlusHotKey(String teamNumber) {return configHotKeyProps.getProperty("Score" + teamNumber + "PlusHotKey");}
	public static String getResetHotKey(String teamNumber) {return configHotKeyProps.getProperty("Reset" + teamNumber + "HotKey");}
	public static String getWarnHotKey(String teamNumber) {return configHotKeyProps.getProperty("Warn" + teamNumber + "HotKey");}
	public static String getKingSeatHotKey(String teamNumber) {return configHotKeyProps.getProperty("KingSeat" + teamNumber + "HotKey");}
	public static String getSwitchTeamsHotKey() {return configHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public static String getSwitchGameCountsHotKey() {return configHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public static String getSwitchMatchCountsHotKey() {return configHotKeyProps.getProperty("SwitchMatchCountsHotKey");}
	public static String getSwitchScoresHotKey() {return configHotKeyProps.getProperty("SwitchScoresHotKey");}
	public static String getSwitchTimeOutsHotKey() {return configHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
	public static String getSwitchResetWarnsHotKey() {return configHotKeyProps.getProperty("SwitchResetWarnsHotKey");}
	public static String getSwitchSidesHotKey() {return configHotKeyProps.getProperty("SwitchSidesHotKey");}
	public static String getResetNamesHotKey() {return configHotKeyProps.getProperty("ResetNamesHotKey");}
	public static String getResetGameCountsHotKey() {return configHotKeyProps.getProperty("ResetGameCountsHotKey");}
	public static String getResetMatchCountsHotKey() {return configHotKeyProps.getProperty("ResetMatchCountsHotKey");}
	public static String getResetScoresHotKey() {return configHotKeyProps.getProperty("ResetScoresHotKey");}
	public static String getResetTimeOutsHotKey() {return configHotKeyProps.getProperty("ResetTimeOutsHotKey");}
	public static String getResetResetWarnHotKey() {return configHotKeyProps.getProperty("ResetResetWarnHotKey");}
	public static String getResetAllHotKey() {return configHotKeyProps.getProperty("ResetAllHotKey");}
	public static String getClearAllHotKey() {return configHotKeyProps.getProperty("ClearAllHotKey");}
	public static String getShotTimerHotKey() {return configHotKeyProps.getProperty("ShotTimerHotKey");}
	public static String getPassTimerHotKey() {return configHotKeyProps.getProperty("PassTimerHotKey");}
	public static String getTimeOutTimerHotKey() {return configHotKeyProps.getProperty("TimeOutTimerHotKey");}
	public static String getGameTimerHotKey() {return configHotKeyProps.getProperty("GameTimerHotKey");}
	public static String getRecallTimerHotKey() {return configHotKeyProps.getProperty("RecallTimerHotKey");}
	public static String getResetTimersHotKey() {return configHotKeyProps.getProperty("ResetTimersHotKey");}
	public static String getUndoHotKey() {return configHotKeyProps.getProperty("UndoHotKey");}
	public static String getRedoHotKey() {return configHotKeyProps.getProperty("RedoHotKey");}
	public static String getConnectHotKey() {return configHotKeyProps.getProperty("ConnectHotKey");}
	public static String getDisconnectHotKey() {return configHotKeyProps.getProperty("DisconnectHotKey");}
	public static String getPushHotKey() {return configHotKeyProps.getProperty("PushHotKey");}
	public static String getPullHotKey() {return configHotKeyProps.getProperty("PullHotKey");}
	public static String getShowScoresHotKey() {return configHotKeyProps.getProperty("ShowScoresHotKey");}
	public static String getShowTimerHotKey() {return configHotKeyProps.getProperty("ShowTimerHotKey");}
	public static String getShowSkunkHotKey() {return configHotKeyProps.getProperty("ShowSkunkHotKey");}
	public static String getStartStreamHotKey() {return configHotKeyProps.getProperty("StartStreamHotKey");}
	public static String getShowCutthroatHotKey() {return configHotKeyProps.getProperty("ShowCutthroatHotKey");}
	public static String getAutoScoreMainConnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainConnectHotKey");}
	public static String getAutoScoreMainDisconnectHotKey() {return configHotKeyProps.getProperty("AutoScoreMainDisconnectHotKey");}
	public static String getAutoScoreMainSettingsHotKey() {return configHotKeyProps.getProperty("AutoScoreMainSettingsHotKey");}
	public static String getHotKeyBaseScript() {return configHotKeyProps.getProperty("HotKeyBaseScript");}
	public static String getHotKeyScriptPath() {return configHotKeyProps.getProperty("HotKeyScriptPath");}
	//AutoScore Settings
	public static String getAutoScoreSettingsServerAddress() {return configAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerAddress");}
	public static int getAutoScoreSettingsServerPort() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerPort"));}
	public static int getAutoScoreSettingsAutoConnect() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsAutoConnect"));}
	public static int getAutoScoreSettingsDetailLog() {return Integer.parseInt(configAutoScoreSettingsProps.getProperty("AutoScoreSettingsDetailLog"));}
	//Setters
	//Control Parameters
	public static void setShowParsed(boolean showParsed) {
		configControlProps.setProperty("ShowParsed", Boolean.toString(showParsed));
	}
	public static void setTableName(String tableName) {
		configControlProps.setProperty("TableName", tableName);
	}
	public static void setDatapath(String datapath) {
		configControlProps.setProperty("datapath", datapath);
	}
	public static void setPointsToWin(int pointsToWin) {
		configControlProps.setProperty("PointsToWin", Integer.toString(pointsToWin));
		}
	public static void setMaxWin(int maxWin) {
		configControlProps.setProperty("MaxWin", Integer.toString(maxWin));
		}
	public static void setWinBy(int winBy) {
		configControlProps.setProperty("WinBy", Integer.toString(winBy));
		}
	public static void setGamesToWin(int gamesToWin) {
		configControlProps.setProperty("GamesToWin", Integer.toString(gamesToWin));
		}
	public static void setMaxTimeOuts(int maxTimeOuts) {
		configControlProps.setProperty("MaxTimeOuts", Integer.toString(maxTimeOuts));
		}
	public static void setAutoIncrementGame(int autoIncrementGame) {
		configControlProps.setProperty("AutoIncrementGame", Integer.toString(autoIncrementGame));
		}
	public static void setAnnounceWinner(int announceWinner) {
		configControlProps.setProperty("AnnounceWinner", Integer.toString(announceWinner));
		}
	public static void setAnnounceMeatball(int announceMeatball) {
		configControlProps.setProperty("AnnounceMeatball", Integer.toString(announceMeatball));
		}
	public static void setWinnerPrefix(String winnerPrefix) {
		configControlProps.setProperty("WinnerPrefix", winnerPrefix);
	}
	public static void setWinnerSuffix(String winnerSuffix) {
		configControlProps.setProperty("WinnerSuffix", winnerSuffix);
	}
	public static void setMeatball(String meatball) {
		configControlProps.setProperty("Meatball", meatball);
	}
	public static void setTeam1LastScored(String team1LastScored) {
		configControlProps.setProperty("Team1LastScored", team1LastScored);
	}
	public static void setTeam2LastScored(String team2LastScored) {
		configControlProps.setProperty("Team2LastScored", team2LastScored);
	}
	public static void setTeam3LastScored(String team3LastScored) {
		configControlProps.setProperty("Team3LastScored", team3LastScored);
	}
	public static void setClearLastScored(String clearLastScored) {
		configControlProps.setProperty("ClearLastScored", clearLastScored);
	}
	public static void setSide1Color(String side1Color) {
		configControlProps.setProperty("Side1Color", side1Color);
	}
	public static void setSide2Color(String side2Color) {
		configControlProps.setProperty("Side2Color", side2Color);
	}
	public static void setBallsInRack(String ballsInRack) {
		configControlProps.setProperty("BallsInRack", ballsInRack);
	}
	public static void setShotTime(int shotTime) {
		configControlProps.setProperty("ShotTime", Integer.toString(shotTime));
		}
	public static void setPassTime(int passTime) {
		configControlProps.setProperty("PassTime", Integer.toString(passTime));
		}
	public static void setTimeOutTime(int timeOutTime) {
		configControlProps.setProperty("TimeOutTime", Integer.toString(timeOutTime));
		}
	public static void setGameTime(int gameTime) {
		configControlProps.setProperty("GameTime", Integer.toString(gameTime));
		}
	public static void setRecallTime(int recallTime) {
		configControlProps.setProperty("RecallTime", Integer.toString(recallTime));
		}
	public static void setShowTimeOutsUsed(int showTimeOutsUsed) {
		configControlProps.setProperty("ShowTimeOutsUsed", Integer.toString(showTimeOutsUsed));
	}
	public static void setAutoCapNames(int autoCapNames) {
		configControlProps.setProperty("AutoCapNames", Integer.toString(autoCapNames));
	}
	public static void setWinByFinalOnly(int winByFinalOnly) {
		configControlProps.setProperty("WinByFinalOnly", Integer.toString(winByFinalOnly));
	}
	public static void setShowSkunk(int showSkunk) {
		configControlProps.setProperty("ShowSkunk", Integer.toString(showSkunk));
	}
	public static void setCutThroatMode(int cutThroatMode) {
		configControlProps.setProperty("CutThroatMode", Integer.toString(cutThroatMode));
	}
	public static void setRackMode(int rackMode) {
		configControlProps.setProperty("RackMode", Integer.toString(rackMode));
	}
	public static void setLogoImageURL(String logoImageURL) {
		configControlProps.setProperty("LogoImageURL", logoImageURL);
	}
	public static void setLogoLinkURI(String logoLinkURI) {
		configControlProps.setProperty("LogoLinkURI", logoLinkURI);
	}
	//OBS
	public static void setOBS(String property, String OBSValue) {
		configOBSProps.setProperty(property, OBSValue);
	}
	public static void setOBS(String property, int value) {
		configOBSProps.setProperty(property, Integer.toString(value));
	}
	//Sources
	public static void setSource(String property, String source) {
		configSourceProps.setProperty(property, source);
	}
	//Stat Sources
	public static void setStatsSource(String property, String source) {
		configStatsSourceProps.setProperty(property, source);
	}
	//Filters
	public static void setFilter(String property, String filter) {
		configFilterProps.setProperty(property, filter);
	}
	//PartnerProgram
	public static void setPartnerProgram(String property, String data) {
		configPartnerProgramProps.setProperty(property, data);
	}
	public static void setPartnerProgramPath(String path) {
		configPartnerProgramProps.setProperty("PartnerProgramPath", path);
	}
	//HotKeys
	public static void setHotKey(String property, String hotKey) {
		configHotKeyProps.setProperty(property, hotKey);
	}
	public static void setHotKeyScriptPath(String hotKeyScriptPath) {
		if (!hotKeyScriptPath.isEmpty() && hotKeyScriptPath.charAt(hotKeyScriptPath.length()-1)!='\\') {
			hotKeyScriptPath = hotKeyScriptPath + "\\";
		}
		configHotKeyProps.setProperty("HotKeyScriptPath", hotKeyScriptPath);
	}
	//AutoScore Settings
	public static void setAutoScore(String property, String value) {
		configAutoScoreSettingsProps.setProperty(property, value);
	}
	public static void setAutoScore(String property, int value) {
		configAutoScoreSettingsProps.setProperty(property, Integer.toString(value));
	}
//Get Defaults
	//Control Parameters
	public static Boolean getDefaultShowParsed() {return Boolean.parseBoolean(defaultControlProps.getProperty("ShowParsed"));}
	public static int getDefaultPointsToWin() {return Integer.parseInt(defaultControlProps.getProperty("PointsToWin"));}
	public static int getDefaultMaxWin() {return Integer.parseInt(defaultControlProps.getProperty("MaxWin"));}
	public static int getDefaultWinBy() {return Integer.parseInt(defaultControlProps.getProperty("WinBy"));}
	public static int getDefaultGamesToWin() {return Integer.parseInt(defaultControlProps.getProperty("GamesToWin"));}
	public static int getDefaultMaxTimeOuts() {return Integer.parseInt(defaultControlProps.getProperty("MaxTimeOuts"));}
	public static int getDefaultAutoIncrementGame() {return Integer.parseInt(defaultControlProps.getProperty("AutoIncrementGame"));}
	public static int getDefaultAnnounceWinner() {return Integer.parseInt(defaultControlProps.getProperty("AnnounceWinner"));}
	public static int getDefaultAnnounceMeatball() {return Integer.parseInt(defaultControlProps.getProperty("AnnounceMeatball"));}
	public static String getDefaultWinnerPrefix() {return defaultControlProps.getProperty("WinnerPrefix");}
	public static String getDefaultWinnerSuffix() {return defaultControlProps.getProperty("WinnerSuffix");}
	public static String getDefaultMeatball() {return defaultControlProps.getProperty("Meatball");}
	public static String getDefaultTeam1LastScored() {return defaultControlProps.getProperty("Team1LastScored");}
	public static String getDefaultTeam2LastScored() {return defaultControlProps.getProperty("Team2LastScored");}
	public static String getDefaultTeam3LastScored() {return defaultControlProps.getProperty("Team3LastScored");}
	public static String getDefaultClearLastScored() {return defaultControlProps.getProperty("ClearLastScored");}
	public static String getDefaultSide1Color() {return defaultControlProps.getProperty("Side1Color");}
	public static String getDefaultSide2Color() {return defaultControlProps.getProperty("Side2Color");}
	public static String getDefaultSide3Color() {return defaultControlProps.getProperty("Side3Color");}
	public static String getDefaultBallsInRack() {return defaultControlProps.getProperty("BallsInRack");}
	public static String getDefaultNameSeparator() {return defaultControlProps.getProperty("NameSeparator");}
	public static int getDefaultShotTime() {return Integer.parseInt(defaultControlProps.getProperty("ShotTime"));}
	public static int getDefaultPassTime() {return Integer.parseInt(defaultControlProps.getProperty("PassTime"));}
	public static int getDefaultTimeOutTime() {return Integer.parseInt(defaultControlProps.getProperty("TimeOutTime"));}
	public static int getDefaultGameTime() {return Integer.parseInt(defaultControlProps.getProperty("GameTime"));}
	public static int getDefaultRecallTime() {return Integer.parseInt(defaultControlProps.getProperty("RecallTime"));}
	public static int getDefaultShowTimeOutsUsed() {return Integer.parseInt(defaultControlProps.getProperty("ShowTimeOutsUsed"));}
	public static int getDefaultAutoCapNames() {return Integer.parseInt(defaultControlProps.getProperty("AutoCapNames"));}
	public static int getDefaultWinByFinalOnly() {return Integer.parseInt(defaultControlProps.getProperty("WinByFinalOnly"));}
	public static int getDefaultShowSkunk() {return Integer.parseInt(defaultControlProps.getProperty("ShowSkunk"));}
	public static int getDefaultCutThroatMode() {return Integer.parseInt(defaultControlProps.getProperty("CutThroatMode"));}
	public static int getDefaultRackMode() {return Integer.parseInt(defaultControlProps.getProperty("RackMode"));}
	//OBS
	public static String getDefaultOBSHost() {return defaultOBSProps.getProperty("OBSHost");}
	public static String getDefaultOBSPort() {return defaultOBSProps.getProperty("OBSPort");}
	public static String getDefaultOBSPassword() {return defaultOBSProps.getProperty("OBSPassword");}
	public static String getDefaultOBSScene() {return defaultOBSProps.getProperty("OBSScene");}
	public static int getDefaultOBSAutoLogin() {return Integer.parseInt(defaultOBSProps.getProperty("OBSAutoLogin"));}
	public static int getDefaultOBSSavePassword() {return Integer.parseInt(defaultOBSProps.getProperty("OBSSavePassword"));}
	public static int getDefaultOBSCloseOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSCloseOnConnect"));}
	public static int getDefaultOBSUpdateOnConnect() {return Integer.parseInt(defaultOBSProps.getProperty("OBSUpdateOnConnect"));}
	//Sources
	public static String getDefaultTeam1NameSource() {return defaultSourceProps.getProperty("Team1Name");}
	public static String getDefaultTeam1ForwardSource() {return defaultSourceProps.getProperty("Team1Forward");}
	public static String getDefaultTeam1GoalieSource() {return defaultSourceProps.getProperty("Team1Goalie");}
	public static String getDefaultTournamentSource() {return defaultSourceProps.getProperty("Tournament");}
	public static String getDefaultTeam2NameSource() {return defaultSourceProps.getProperty("Team2Name");}
	public static String getDefaultTeam2ForwardSource() {return defaultSourceProps.getProperty("Team2Forward");}
	public static String getDefaultTeam2GoalieSource() {return defaultSourceProps.getProperty("Team2Goalie");}
	public static String getDefaultEventSource() {return defaultSourceProps.getProperty("Event");}
	public static String getDefaultTeam3NameSource() {return defaultSourceProps.getProperty("Team3Name");}
	public static String getDefaultTeam3ForwardSource() {return defaultSourceProps.getProperty("Team3Forward");}
	public static String getDefaultTeam3GoalieSource() {return defaultSourceProps.getProperty("Team3Goalie");}
	public static String getDefaultTableNameSource() {return defaultSourceProps.getProperty("TableName");}
	public static String getDefaultTimeRemainingSource() {return defaultSourceProps.getProperty("TimeRemaining");}
	public static String getDefaultTeam1GameCountSource() {return defaultSourceProps.getProperty("Team1GameCount");}
	public static String getDefaultTeam2GameCountSource() {return defaultSourceProps.getProperty("Team2GameCount");}
	public static String getDefaultTeam3GameCount3Source() {return defaultSourceProps.getProperty("Team3GameCount");}
	public static String getDefaultTeam1MatchCountSource() {return defaultSourceProps.getProperty("Team1MatchCount");}
	public static String getDefaultTeam2MatchCountSource() {return defaultSourceProps.getProperty("Team2MatchCount");}
	public static String getDefaultTeam3MatchCount3Source() {return defaultSourceProps.getProperty("Team3MatchCount");}
	public static String getDefaultTimerInUseSource() {return defaultSourceProps.getProperty("TimerInUse");}
	public static String getDefaultTeam1ScoreSource() {return defaultSourceProps.getProperty("Team1Score");}
	public static String getDefaultMatchWinnerSource() {return defaultSourceProps.getProperty("MatchWinner");}
	public static String getDefaultMeatballSource() {return defaultSourceProps.getProperty("Meatball");}
	public static String getDefaultGameResultsSource() {return defaultSourceProps.getProperty("GameResults");}
	public static String getDefaultTeam2ScoreSource() {return defaultSourceProps.getProperty("Team2Score");}
	public static String getDefaultTeam3ScoreSource() {return defaultSourceProps.getProperty("Team3Score");}
	public static String getDefaultTeam1TimeOutSource() {return defaultSourceProps.getProperty("Team1TimeOut");}
	public static String getDefaultTeam2TimeOutSource() {return defaultSourceProps.getProperty("Team2TimeOut");}
	public static String getDefaultTeam3TimeOutSource() {return defaultSourceProps.getProperty("Team3TimeOut");}
	public static String getDefaultTeam1ResetSource() {return defaultSourceProps.getProperty("Team1Reset");}
	public static String getDefaultTeam2ResetSource() {return defaultSourceProps.getProperty("Team2Reset");}
	public static String getDefaultTeam3ResetSource() {return defaultSourceProps.getProperty("Team3Reset");}
	public static String getDefaultTeam1WarnSource() {return defaultSourceProps.getProperty("Team1Warn");}
	public static String getDefaultTeam2WarnSource() {return defaultSourceProps.getProperty("Team2Warn");}
	public static String getDefaultTeam3WarnSource() {return defaultSourceProps.getProperty("Team3Warn");}
	public static String getDefaultTeam1KingSeatSource() {return defaultSourceProps.getProperty("Team1KingSeat");}
	public static String getDefaultTeam2KingSeatSource() {return defaultSourceProps.getProperty("Team2KingSeat");}
	public static String getDefaultTeam3KingSeatSource() {return defaultSourceProps.getProperty("Team3KingSeat");}
	public static String getDefaultTeamGameShowSource(String teamNumber, String gameNumber) {return defaultSourceProps.getProperty("Team"+teamNumber+"Game"+gameNumber+"Show");}
	public static String getDefaultLastScoredSource() {return defaultSourceProps.getProperty("LastScored");}
	public static String getDefaultGameTimeSource() {return defaultSourceProps.getProperty("GameTime");}
	public static String getDefaultMatchTimeSource() {return defaultSourceProps.getProperty("MatchTime");}
	public static String getDefaultStreamTimeSource() {return defaultSourceProps.getProperty("StreamTime");}
	public static String getDefaultShowScoresSource() {return defaultSourceProps.getProperty("ShowScores");}
	public static String getDefaultShowTimerSource() {return defaultSourceProps.getProperty("ShowTimer");}
	public static String getDefaultShowCutthroatSource() {return defaultSourceProps.getProperty("ShowCutthroat");}
	//Stat Sources
	public static String getDefaultTeamStuffsSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "Stuffs");}
	public static String getDefaultTeamBreaksSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "Breaks");}
	public static String getDefaultTeamAcesSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "Aces");}
	public static String getDefaultTeamPassAttemptsSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "PassAttempts");}
	public static String getDefaultTeamPassCompletesSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "PassCompletes");}
	public static String getDefaultTeamShotAttemptsSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ShotAttempts");}
	public static String getDefaultTeamShotCompletesSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ShotCompletes");}
	public static String getDefaultTeamClearAttemptsSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ClearAttempts");}
	public static String getDefaultTeamClearCompletesSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ClearCompletes");}
	public static String getDefaultSideColorSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Side" + teamNumber + "Color");}
	public static String getDefaultTeamPassPercentSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "PassPercent");}
	public static String getDefaultTeamShotPercentSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ShotPercent");}
	public static String getDefaultTeamClearPercentSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ClearPercent");}
	public static String getDefaultTeamTwoBarPassAttemptsSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassAttempts");}
	public static String getDefaultTeamTwoBarPassCompletesSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassCompletes");}
	public static String getDefaultTeamTwoBarPassPercentSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarPassPercent");}
	public static String getDefaultTeamScoringSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "Scoring");}
	public static String getDefaultTeamThreeBarScoringSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ThreeBarScoring");}
	public static String getDefaultTeamFiveBarScoringSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "FiveBarScoring");}
	public static String getDefaultTeamTwoBarScoringSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "TwoBarScoring");}	
	public static String getDefaultTeamShotsOnGoalSource(String teamNumber) {return defaultStatsSourceProps.getProperty("Team" + teamNumber + "ShotsOnGoal");}
	//Filters
	public static String getDefaultTeam1ScoreFilter() {return defaultFilterProps.getProperty("Team1Score");}
	public static String getDefaultTeam2ScoreFilter() {return defaultFilterProps.getProperty("Team2Score");}
	public static String getDefaultTeam1WinGameFilter() {return defaultFilterProps.getProperty("Team1WinGame");}
	public static String getDefaultTeam2WinGameFilter() {return defaultFilterProps.getProperty("Team2WinGame");}
	public static String getDefaultTeam1WinMatchFilter() {return defaultFilterProps.getProperty("Team1WinMatch");}
	public static String getDefaultTeam2WinMatchFilter() {return defaultFilterProps.getProperty("Team2WinMatch");}
	public static String getDefaultTeam1TimeOutFilter() {return defaultFilterProps.getProperty("Team1TimeOut");}
	public static String getDefaultTeam2TimeOutFilter() {return defaultFilterProps.getProperty("Team2TimeOut");}
	public static String getDefaultTeam1ResetFilter() {return defaultFilterProps.getProperty("Team1Reset");}
	public static String getDefaultTeam2ResetFilter() {return defaultFilterProps.getProperty("Team2Reset");}
	public static String getDefaultTeam1WarnFilter() {return defaultFilterProps.getProperty("Team1Warn");}
	public static String getDefaultTeam2WarnFilter() {return defaultFilterProps.getProperty("Team2Warn");}
	public static String getDefaultTeam1SwitchPositionsFilter() {return defaultFilterProps.getProperty("Team1SwitchPositions");}
	public static String getDefaultTeam2SwitchPositionsFilter() {return defaultFilterProps.getProperty("Team2SwitchPositions");}
	public static String getDefaultTeam1SkunkFilter() {return defaultFilterProps.getProperty("Team1Skunk");}
	public static String getDefaultTeam2SkunkFilter() {return defaultFilterProps.getProperty("Team2Skunk");}
	public static String getDefaultStartMatchFilter() {return defaultFilterProps.getProperty("StartMatch");}
	public static String getDefaultStartGameFilter() {return defaultFilterProps.getProperty("StartGame");}
	public static String getDefaultSwitchSidesFilter() {return defaultFilterProps.getProperty("SwitchSides");}
	//PartnerProgram
	public static String getDefaultPartnerProgramPath() {return defaultPartnerProgramProps.getProperty("PartnerProgramPath");}
	public static String getDefaultPlayer1FileName() {return defaultPartnerProgramProps.getProperty("Player1FileName");}
	public static String getDefaultPlayer2FileName() {return defaultPartnerProgramProps.getProperty("Player2FileName");}
	public static String getDefaultPlayer3FileName() {return defaultPartnerProgramProps.getProperty("Player3FileName");}
	public static String getDefaultPlayer4FileName() {return defaultPartnerProgramProps.getProperty("Player4FileName");}
	//HotKeys
	public static String getDefaultStartMatchHotKey() {return defaultHotKeyProps.getProperty("StartMatchHotKey");}
	public static String getDefaultPauseMatchHotKey() {return defaultHotKeyProps.getProperty("PauseMatchHotKey");}
	public static String getDefaultEndMatchHotKey() {return defaultHotKeyProps.getProperty("EndMatchHotKey");}
	public static String getDefaultStartGameHotKey() {return defaultHotKeyProps.getProperty("StartGameHotKey");}
	public static String getDefaultSwitchTeamsHotKey() {return defaultHotKeyProps.getProperty("SwitchTeamsHotKey");}
	public static String getDefaultSwitchGameCountsHotKey() {return defaultHotKeyProps.getProperty("SwitchGameCountsHotKey");}
	public static String getDefaultSwitchMatchCountsHotKey() {return defaultHotKeyProps.getProperty("SwitchMatchCountsHotKey");}
	public static String getDefaultTeamSwitchPositionsHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Team" + teamNumber + "SwitchPositionsHotKey");}
	public static String getDefaultSwitchForwardsHotKey() {return defaultHotKeyProps.getProperty("SwitchForwardsHotKey");}
	public static String getDefaultSwitchGoaliesHotKey() {return defaultHotKeyProps.getProperty("SwitchGoaliesHotKey");}
	public static String getDefaultGameCountMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("GameCount" + teamNumber + "MinusHotKey");}
	public static String getDefaultGameCountPlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("GameCount" + teamNumber + "PlusHotKey");}
	public static String getDefaultMatchCountMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("MatchCount" + teamNumber + "MinusHotKey");}
	public static String getDefaultMatchCountPlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("MatchCount" + teamNumber + "PlusHotKey");}
	public static String getDefaultTimeOutMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("TimeOut" + teamNumber + "MinusHotKey");}
	public static String getDefaultTimeOutPlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("TimeOut" + teamNumber + "PlusHotKey");}
	public static String getDefaultResetHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Reset" + teamNumber + "HotKey");}
	public static String getDefaultWarnHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Warn" + teamNumber + "HotKey");}
	public static String getDefaultKingSeatHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("KingSeat" + teamNumber + "HotKey");}
	public static String getDefaultScoreMinusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Score" + teamNumber + "MinusHotKey");}
	public static String getDefaultScorePlusHotKey(String teamNumber) {return defaultHotKeyProps.getProperty("Score" + teamNumber + "PlusHotKey");}
	public static String getDefaultSwitchScoresHotKey() {return defaultHotKeyProps.getProperty("SwitchScoresHotKey");}
	public static String getDefaultSwitchTimeOutsHotKey() {return defaultHotKeyProps.getProperty("SwitchTimeOutsHotKey");}
	public static String getDefaultSwitchResetWarnsHotKey() {return defaultHotKeyProps.getProperty("SwitchResetWarnsHotKey");}
	public static String getDefaultSwitchPositionsHotKey() {return defaultHotKeyProps.getProperty("SwitchSidesHotKey");}
	public static String getDefaultResetNamesHotKey() {return defaultHotKeyProps.getProperty("resetNamesHotKey");}
	public static String getDefaultResetGameCountsHotKey() {return defaultHotKeyProps.getProperty("ResetGameCountsHotKey");}
	public static String getDefaultResetMatchCountsHotKey() {return defaultHotKeyProps.getProperty("ResetMatchCountsHotKey");}
	public static String getDefaultResetScoresHotKey() {return defaultHotKeyProps.getProperty("ResetScoresHotKey");}
	public static String getDefaultResetTimeOutsHotKey() {return defaultHotKeyProps.getProperty("ResetTimeOutsHotKey");}
	public static String getDefaultResetResetWarnHotKey() {return defaultHotKeyProps.getProperty("ResetResetWarnHotKey");}
	public static String getDefaultResetAllHotKey() {return defaultHotKeyProps.getProperty("ResetAllHotKey");}
	public static String getDefaultClearAllHotKey() {return defaultHotKeyProps.getProperty("ClearAllHotKey");}
	public static String getDefaultShotTimerHotKey() {return defaultHotKeyProps.getProperty("ShotTimerHotKey");}
	public static String getDefaultPassTimerHotKey() {return defaultHotKeyProps.getProperty("PassTimerHotKey");}
	public static String getDefaultTimeOutTimerHotKey() {return defaultHotKeyProps.getProperty("TimeOutTimerHotKey");}
	public static String getDefaultGameTimerHotKey() {return defaultHotKeyProps.getProperty("GameTimerHotKey");}
	public static String getDefaultRecallTimerHotKey() {return defaultHotKeyProps.getProperty("RecallTimerHotKey");}
	public static String getDefaultResetTimersHotKey() {return defaultHotKeyProps.getProperty("ResetTimersHotKey");}
	public static String getDefaultUndoHotKey() {return defaultHotKeyProps.getProperty("UndoHotKey");}
	public static String getDefaultRedoHotKey() {return defaultHotKeyProps.getProperty("RedoHotKey");}
	public static String getConnectDefaultHotKey() {return defaultHotKeyProps.getProperty("ConnectHotKey");}
	public static String getDisconnectDefaultHotKey() {return defaultHotKeyProps.getProperty("DisconnectHotKey");}
	public static String getPushDefaultHotKey() {return defaultHotKeyProps.getProperty("PushHotKey");}
	public static String getPullDefaultHotKey() {return defaultHotKeyProps.getProperty("PullHotKey");}
	public static String getDefaultShowScoresHotKey() {return defaultHotKeyProps.getProperty("ShowScoresHotKey");}
	public static String getDefaultShowTimerHotKey() {return defaultHotKeyProps.getProperty("ShowTimerHotKey");}
	public static String getDefaultShowCutthroatHotKey() {return defaultHotKeyProps.getProperty("ShowCutthroatHotKey");}
	public static String getDefaultShowSkunkHotKey() {return defaultHotKeyProps.getProperty("ShowSkunkHotKey");}
	public static String getDefaultStartStreamHotKey() {return defaultHotKeyProps.getProperty("StartStreamHotKey");}
	public static String getDefaultHotKeyBaseScript() {return defaultHotKeyProps.getProperty("HotKeyBaseScript");}
	public static String getDefaultHotKeyScriptPath() {return defaultHotKeyProps.getProperty("HotKeyScriptPath");}
	//AutoScore Settings
	public static String getDefaultAutoScoreSettingsServerAddress() {return defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerAddress");}
	public static int getDefaultAutoScoreSettingsServerPort() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsServerPort"));}
	public static int getDefaultAutoScoreSettingsAutoConnect() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsAutoConnect"));}
	public static int getDefaultAutoScoreSettingsDetailLog() {return Integer.parseInt(defaultAutoScoreSettingsProps.getProperty("AutoScoreSettingsDetailLog"));}

	public static void loadFromControlConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configControlFileName))) {
			configControlProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configControlFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configControlFileName));
			configControlProps = defaultControlProps;
			saveControlConfig();
		}
	}
	public static void loadFromOBSConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configOBSFileName))) {
			configOBSProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configOBSFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configOBSFileName));
			configOBSProps = defaultOBSProps;
			saveOBSConfig();
		}
	}
	public static void loadFromSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configSourceFileName))) {
			configSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configSourceFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configSourceFileName));
			configSourceProps = defaultSourceProps;
			saveSourceConfig();
		}
	}
	public static void loadFromStatsSourceConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configStatsSourceFileName))) {
			configStatsSourceProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configStatsSourceFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configStatsSourceFileName));
			configStatsSourceProps = defaultStatsSourceProps;
			saveStatsSourceConfig();
		}
	}
	public static void loadFromFilterConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configFilterFileName))) {
			configFilterProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configFilterFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configFilterFileName));
			configFilterProps = defaultFilterProps;
			saveFilterConfig();
		}
	}
	public static void loadFromPartnerProgramConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configPartnerProgramFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configPartnerProgramFileName));
			configPartnerProgramProps = defaultPartnerProgramProps;
			savePartnerProgramConfig();
		}
	}
	public static void loadFromHotKeyConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configHotKeyFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configHotKeyFileName));
			configHotKeyProps = defaultHotKeyProps;
			saveHotKeyConfig();
		}
	}
	public static void loadFromAutoScoreSettingsConfig() throws IOException {
		try(InputStream inputStream = Files.newInputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.load(inputStream);
		} catch (NoSuchFileException e) {
			logger.info(Paths.get(configAutoScoreSettingsFileName) + " not found. Writing defaults.");
			Files.createFile(Paths.get(configAutoScoreSettingsFileName));
			configAutoScoreSettingsProps = defaultAutoScoreSettingsProps;
			saveAutoScoreSettingsConfig();
		}
	}
	public static void saveControlConfig() throws IOException {
		//Control Parameters
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configControlFileName))) {
			configControlProps.store(outputStream, "FoosOBSPlus Control settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configControlFileName));
		}
	}
	public static void saveOBSConfig() throws IOException {
		//OBS
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configOBSFileName))) {
			configOBSProps.store(outputStream, "FoosOBSPlus OBS Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configOBSFileName));
		}
	}
	public static void saveSourceConfig() throws IOException {
		//Source
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configSourceFileName))) {
			configSourceProps.store(outputStream, "FoosOBSPlus Source Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configSourceFileName));
		}
	}
	public static void saveStatsSourceConfig() throws IOException {
		//Stats Source
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configStatsSourceFileName))) {
			configStatsSourceProps.store(outputStream, "FoosOBSPlus Stats Source Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configStatsSourceFileName));
		}
	}
	public static void saveFilterConfig() throws IOException {
		//Filter
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configFilterFileName))) {
			configFilterProps.store(outputStream, "FoosOBSPlus Filter Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configFilterFileName));
		}
	}
	public static void savePartnerProgramConfig() throws IOException {
		//PartnerProgram
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configPartnerProgramFileName))) {
			configPartnerProgramProps.store(outputStream, "FoosOBSPlus Partner Program Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configPartnerProgramFileName));
		}
	}
	public static void saveHotKeyConfig() throws IOException {
		//HotKeys
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.store(outputStream, "FoosOBSPlus Hot Key Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configHotKeyFileName));
		}
	}
	public static void saveAutoScoreSettingsConfig() throws IOException {
		//AutoScore Settings
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configAutoScoreSettingsFileName))) {
			configAutoScoreSettingsProps.store(outputStream, "FoosOBSPlus AutoScore Settings");
		} catch (Exception e) {
			logger.error("Could not write to " + Paths.get(configAutoScoreSettingsFileName));
		}
	}
	public static void generateHotKeyScripts() {
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
	private static void createHotKeyScript(String keyFunction, String hotKey, String[] baseScript, String basePath) {
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
	public static int getMaxGameNumber() {
		// 	for cutthroat -> settings.getGamesToWin()*3-2;
		//  for regular   -> settings.getGamesToWin()*2-1;
		return getGamesToWin()*(2+getCutThroatMode())-(1+getCutThroatMode());
	}
}
