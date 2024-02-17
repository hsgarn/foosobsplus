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
import java.util.function.Function;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.view.Messages;

public final class Settings {
	private final static Settings instance 			= new Settings();
	private final static String ON = "1";
	private final static String OFF = "0";
	// Parameter settings
	private final static String separator 			= FileSystems.getDefault().getSeparator();
	private final static String gameType			= "Foosball";
	private final static String[] lastScoredStrings = new String[4];
	private final static int borderTop				= 2;
	private final static int borderBottom 			= 0;
	private final static int borderLeft				= 0;
	private final static int borderRight 			= 0;
	// Property Settings
	private final static Properties defaultControlProps 			= new Properties();
	private final static Properties defaultSourceProps 				= new Properties();
	private final static Properties defaultStatsSourceProps 		= new Properties();
	private final static Properties defaultFilterProps 				= new Properties();
	private final static Properties defaultPartnerProgramProps 		= new Properties();
	private final static Properties defaultHotKeyProps 				= new Properties();
	private final static Properties defaultOBSProps 				= new Properties();
	private final static Properties defaultAutoScoreSettingsProps 	= new Properties();
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
		defaultControlProps.setProperty("AutoIncrementGame", ON);
		defaultControlProps.setProperty("AnnounceWinner", ON);
		defaultControlProps.setProperty("AnnounceMeatball", ON);
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
		defaultControlProps.setProperty("ShowTimeOutsUsed", ON);
		defaultControlProps.setProperty("AutoCapNames",  ON);
		defaultControlProps.setProperty("WinByFinalOnly", ON);
		defaultControlProps.setProperty("ShowSkunk",  ON);
		defaultControlProps.setProperty("CutThroatMode", OFF);
		defaultControlProps.setProperty("RackMode", OFF);
		defaultControlProps.setProperty("LogoImageURL", "/imgs/MidsouthFoosballLogo4.png");
		defaultControlProps.setProperty("LogoLinkURI", "https://www.facebook.com/midsouthfoosball");
		//OBS
		defaultOBSProps.setProperty("OBSHost", "localhost");
		defaultOBSProps.setProperty("OBSPort", "4455");
		defaultOBSProps.setProperty("OBSPassword","OBSWebSocketPasswordGoesHere");
		defaultOBSProps.setProperty("OBSMainScene", "FoosObs+ Main");
		defaultOBSProps.setProperty("OBSAutoLogin", OFF);
		defaultOBSProps.setProperty("OBSSavePassword", OFF);
		defaultOBSProps.setProperty("OBSCloseOnConnect", ON);
		defaultOBSProps.setProperty("OBSUpdateOnConnect",  ON);
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
		defaultFilterProps.setProperty("Meatball", "meatball");
		//PartnerProgram
		defaultPartnerProgramProps.setProperty("PartnerProgramPath", "C:\\FoosTourney");
		defaultPartnerProgramProps.setProperty("Player1FileName", "Player1.txt");
		defaultPartnerProgramProps.setProperty("Player2FileName", "Player2.txt");
		defaultPartnerProgramProps.setProperty("Player3FileName", "Player3.txt");
		defaultPartnerProgramProps.setProperty("Player4FileName", "Player4.txt");
		//HotKeys
		defaultHotKeyProps.setProperty("Team1SwitchPositions", "t");
		defaultHotKeyProps.setProperty("Team2SwitchPositions", "m");
		defaultHotKeyProps.setProperty("Team3SwitchPositions", "");
		defaultHotKeyProps.setProperty("Team1ScorePlus", "1");
		defaultHotKeyProps.setProperty("Team2ScorePlus", "2");
		defaultHotKeyProps.setProperty("Team3ScorePlus", "");
		defaultHotKeyProps.setProperty("Team1ScoreMinus", "4");
		defaultHotKeyProps.setProperty("Team2ScoreMinus", "8");
		defaultHotKeyProps.setProperty("Team3ScoreMinus", "");
		defaultHotKeyProps.setProperty("Team1GameCountPlus", "5");
		defaultHotKeyProps.setProperty("Team2GameCountPlus", "6");
		defaultHotKeyProps.setProperty("Team3GameCountPlus", "");
		defaultHotKeyProps.setProperty("Team1GameCountMinus", "j");
		defaultHotKeyProps.setProperty("Team2GameCountMinus", "i");
		defaultHotKeyProps.setProperty("Team3GameCountMinus", "");
		defaultHotKeyProps.setProperty("Team1MatchCountPlus", "");
		defaultHotKeyProps.setProperty("Team2MatchCountPlus", "");
		defaultHotKeyProps.setProperty("Team3MatchCountPlus", "");
		defaultHotKeyProps.setProperty("Team1MatchCountMinus", "");
		defaultHotKeyProps.setProperty("Team2MatchCountMinus", "");
		defaultHotKeyProps.setProperty("Team3MatchCountMinus", "");
		defaultHotKeyProps.setProperty("Team1TimeOutPlus", "9");
		defaultHotKeyProps.setProperty("Team2TimeOutPlus", "0");
		defaultHotKeyProps.setProperty("Team3TimeOutPlus", "");
		defaultHotKeyProps.setProperty("Team1TimeOutMinus", "n");
		defaultHotKeyProps.setProperty("Team2TimeOutMinus", "q");
		defaultHotKeyProps.setProperty("Team3TimeOutMinus", "");
		defaultHotKeyProps.setProperty("Team1Reset", "");
		defaultHotKeyProps.setProperty("Team2Reset", "");
		defaultHotKeyProps.setProperty("Team3Reset", "");
		defaultHotKeyProps.setProperty("Team1Warn", "");
		defaultHotKeyProps.setProperty("Team2Warn", "");
		defaultHotKeyProps.setProperty("Team3Warn", "");
		defaultHotKeyProps.setProperty("Team1KingSeat", "");
		defaultHotKeyProps.setProperty("Team2KingSeat", "");
		defaultHotKeyProps.setProperty("Team3KingSeat", "");
		defaultHotKeyProps.setProperty("StartMatch", "b");
		defaultHotKeyProps.setProperty("PauseMatch", ",");
		defaultHotKeyProps.setProperty("EndMatch", "");
		defaultHotKeyProps.setProperty("StartGame", ".");
		defaultHotKeyProps.setProperty("ShotTimer", "s");
		defaultHotKeyProps.setProperty("PassTimer", "p");
		defaultHotKeyProps.setProperty("TimeOutTimer", "o");
		defaultHotKeyProps.setProperty("GameTimer", "g");
		defaultHotKeyProps.setProperty("RecallTimer", "c");
		defaultHotKeyProps.setProperty("ResetTimers", "r");
		defaultHotKeyProps.setProperty("ClearAll", "");
		defaultHotKeyProps.setProperty("Undo", "u");
		defaultHotKeyProps.setProperty("Redo", "d");
		defaultHotKeyProps.setProperty("ShowSkunk", "k");
		defaultHotKeyProps.setProperty("StartStream", "z");
		defaultHotKeyProps.setProperty("ClearTournament", "");
		defaultHotKeyProps.setProperty("SwitchMatchCounts", "");
		defaultHotKeyProps.setProperty("SwitchSides", "w");
		defaultHotKeyProps.setProperty("SwitchTeams", "e");
		defaultHotKeyProps.setProperty("SwitchScores", "");
		defaultHotKeyProps.setProperty("SwitchGameCounts", "");
		defaultHotKeyProps.setProperty("SwitchTimeOuts", "[");
		defaultHotKeyProps.setProperty("SwitchResetWarns", "");
		defaultHotKeyProps.setProperty("SwitchForwards", ";");
		defaultHotKeyProps.setProperty("SwitchGoalies", "x");
		defaultHotKeyProps.setProperty("ResetNames", "");
		defaultHotKeyProps.setProperty("ResetScores", "3");
		defaultHotKeyProps.setProperty("ResetGameCounts", "7");
		defaultHotKeyProps.setProperty("ResetTimeOuts", "-");
		defaultHotKeyProps.setProperty("ResetResetWarn", "");
		defaultHotKeyProps.setProperty("ResetAll", "a");
		defaultHotKeyProps.setProperty("ResetMatchCounts", "");

		defaultHotKeyProps.setProperty("Connect", "");
		defaultHotKeyProps.setProperty("Disconnect", "");
		defaultHotKeyProps.setProperty("Push", "");
		defaultHotKeyProps.setProperty("Pull", "");
		defaultHotKeyProps.setProperty("ShowScores", "");
		defaultHotKeyProps.setProperty("ShowTimer", "");
		defaultHotKeyProps.setProperty("ShowCutthroat", "");
		defaultHotKeyProps.setProperty("AutoScoreMainConnect", "");
		defaultHotKeyProps.setProperty("AutoScoreMainDisconnect", "");
		defaultHotKeyProps.setProperty("AutoScoreMainSettings", "");
		defaultHotKeyProps.setProperty("HotKeyBaseScript", Messages.getString("Settings.HotKeyBaseScript"));
		defaultHotKeyProps.setProperty("HotKeyScriptPath", Messages.getString("Settings.HotKeyScriptPath"));
		//AutoScore Settings Properties
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerAddress", "192.168.68.69");
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsServerPort", "5051");
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsAutoConnect", OFF);
		defaultAutoScoreSettingsProps.setProperty("AutoScoreSettingsDetailLog", OFF);
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
			JOptionPane.showMessageDialog(null, Messages.getString("Errors.ErrorLoadingConfig") + " " + e.getLocalizedMessage());
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
	public static String[] getLastScoredStrings() {
		lastScoredStrings[0] = getControlParameter("ClearLastScored");
		lastScoredStrings[1] = getControlParameter("Team1LastScored");
		lastScoredStrings[2] = getControlParameter("Team2LastScored");
		lastScoredStrings[3] = getControlParameter("Team3LastScored");
		return lastScoredStrings;
	}
	public static String getGameType() {return gameType;}
	//Getters
	//Control Parameters
//	public static <T> T getControlParameter(String parameter, Function<String, T> parser) {return parser.apply(configControlProps.getProperty(parameter));}
	public static String getControlParameter(String parameter) {return configControlProps.getProperty(parameter);}
	public static boolean getShowParsed() {return Boolean.parseBoolean(configControlProps.getProperty("ShowParsed"));}
	//OBS
	public static String getOBSParameter(String parameter) {return configOBSProps.getProperty(parameter);}
	//Sources
	public static String getSourceParameter(String parameter) {return configSourceProps.getProperty(parameter);}
	public static String getTeamGameShowSource(int teamNumber, int gameNumber) {
		return configSourceProps.getProperty("Team" + teamNumber + "Game" + gameNumber + "Show");
	}
	public static String getTeamSourceParameter(String teamNumber, String source) {
		return configSourceProps.getProperty("Team" + teamNumber + source);
	}
	//Stat Sources
	public static String getStatsSourceParameter(String parameter) {return configStatsSourceProps.getProperty(parameter);}
	public static String getTeamStatsSourceParameter(String teamNumber, String source) {
		return configStatsSourceProps.getProperty("Team" + teamNumber + source);
	}
	//Filters
	public static String getFiltersFilter(String filter) {return configFilterProps.getProperty(filter);}
	//Partner Program
	public static String getPartnerProgramParameter(String parameter) {return configPartnerProgramProps.getProperty(parameter);}
	//HotKeys
	public static String getHotKeyParameter(String parameter) {return configHotKeyProps.getProperty(parameter);}
	public static String getTeamHotKeyParameter(String teamNumber, String parameter) {return configHotKeyProps.getProperty("Team" + teamNumber + parameter);}
	//AutoScore Settings
	public static <T> T getAutoScoreParameter(String parameter, Function<String, T> parser) {return parser.apply(configAutoScoreSettingsProps.getProperty(parameter));}
	public static String getAutoScoreParameter(String parameter) {return configAutoScoreSettingsProps.getProperty(parameter);}
	//Setters
	//Control Parameters
	public static void setControlParameter(String parameter, String value) {
		configControlProps.setProperty(parameter, value);
	}
	public static void setControlParameter(String parameter, Boolean value) {
		configControlProps.setProperty(parameter, Boolean.toString(value));
	}
	//OBS
	public static void setOBS(String property, String OBSValue) {
		configOBSProps.setProperty(property, OBSValue);
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
//Get Defaults
	//Control Parameters
	public static String getDefaultParameter(String parameter) {return defaultControlProps.getProperty(parameter);}
	//OBS
	public static String getDefaultOBSParameter(String property) {return defaultOBSProps.getProperty(property);}
//	public static <T> T getDefaultOBSParameter(String parameter, Function<String, T> parser) {return parser.apply(defaultOBSProps.getProperty(parameter));}
	//Sources
	public static String getDefaultSource(String property) {return defaultSourceProps.getProperty(property);}
	//Stat Sources
	public static String getDefaultStatsSource(String property) {return defaultStatsSourceProps.getProperty(property);}
	//Filters
	public static String getDefaultFilter(String property) {return defaultFilterProps.getProperty(property);}
	//PartnerProgram
	public static String getDefaultPartnerProgram(String property) {return defaultPartnerProgramProps.getProperty(property);}
	//HotKeys
	public static String getDefaultHotKey(String property) {return defaultHotKeyProps.getProperty(property);}
	//AutoScore Settings
	public static String getDefaultAutoScoreSettings(String property) {return defaultAutoScoreSettingsProps.getProperty(property);}
	//Load Configs
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
		String baseScriptText = getHotKeyParameter("HotKeyBaseScript");
		String hotKeyScriptPath = getHotKeyParameter("HotKeyScriptPath");
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
		try {
			File scriptFile = new File(getHotKeyParameter("HotKeyScriptPath") + File.separator + keyFunction + ".ahk");
			scriptFile.createNewFile();
			FileWriter fileWriter = new FileWriter(scriptFile);
			for (String ln: baseScript) {
				fileWriter.write(ln.replace("~function~", keyFunction).replace("~hotkey~", hotKey).replace("~basepath~", basePath) + "\r\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			logger.error("Could not write to " + getHotKeyParameter("HotKeyScriptPath") + File.separator + keyFunction + ".ahk");
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, Messages.getString("Errors.ScriptWriteFailure") + " " + keyFunction, "Scripting Error", 1);
		}
	}
	public static int getMaxGameNumber() {
		// 	for cutthroat -> GamesToWin*3-2;
		//  for regular   -> GamesToWin*2-1;
		int gamesToWin = Integer.parseInt(getControlParameter("GamesToWin"));
		int cutThroatMode = Integer.parseInt(getControlParameter("CutThroatMode"));
		return gamesToWin*(2+cutThroatMode)-(1+cutThroatMode);
	}
}
