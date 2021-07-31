/**
Copyright 2020 Hugh Garner
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
	
// Parameter settings
	private String separator = FileSystems.getDefault().getSeparator();
	private String[] lastScoredStrings = new String[3];
	private String obsPassword;

	// Property Settings
	private Properties defaultControlProps;
	private Properties defaultSourceProps;
	private Properties defaultFileNameProps;
	private Properties defaultHotKeyProps;
	
	public Properties configControlProps;
	public Properties configSourceProps;
	public Properties configFileNameProps;
	public Properties configHotKeyProps;
	
	private String configControlFileName = "control.properties";
	private String configSourceFileName = "source.properties";
	private String configFileNameFileName = "filename.properties";
	private String configHotKeyFileName = "hotkey.properties";
	
	//////////////////////////////////////////////////////
	
	public Settings() throws IOException {
		defaultControlProps = new Properties();
		defaultSourceProps = new Properties();
		defaultFileNameProps = new Properties();
		defaultHotKeyProps = new Properties();
		// sets default properties
		// Parameter settings
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
		defaultControlProps.setProperty("LogoImageURL", "/imgs/MidsouthFoosballLogo4.png");
		defaultControlProps.setProperty("LogoLinkURI", "https://www.facebook.com/midsouthfoosball");
		//OBS
		defaultControlProps.setProperty("OBSHost", "localhost");
		defaultControlProps.setProperty("OBSPort", "4444");
		defaultControlProps.setProperty("OBSPassword","");
		defaultControlProps.setProperty("OBSAutoLogin", "0");
		defaultControlProps.setProperty("OBSSavePassword", "0");
		defaultControlProps.setProperty("OBSCloseOnConnect", "1");
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
		defaultFileNameProps.setProperty("TableFileName", "tablename.txt");
		defaultFileNameProps.setProperty("Team1NameFileName", "team1name.txt");
		defaultFileNameProps.setProperty("Team1ForwardFileName", "team1forward.txt");
		defaultFileNameProps.setProperty("Team1GoalieFileName", "team1goalie.txt");
		defaultFileNameProps.setProperty("TournamentFileName", "tournament.txt");
		defaultFileNameProps.setProperty("Team2NameFileName", "team2name.txt");
		defaultFileNameProps.setProperty("Team2ForwardFileName", "team2forward.txt");
		defaultFileNameProps.setProperty("Team2GoalieFileName", "team2goalie.txt");
		defaultFileNameProps.setProperty("EventFileName", "event.txt");
		defaultFileNameProps.setProperty("GameCount1FileName", "gamecount1.txt");
		defaultFileNameProps.setProperty("TimeRemainingFileName", "timeremaining.txt");
		defaultFileNameProps.setProperty("GameCount2FileName", "gamecount2.txt");
		defaultFileNameProps.setProperty("TimerInUseFileName", "timerinuse.txt");
		defaultFileNameProps.setProperty("Score1FileName", "score1.txt");
		defaultFileNameProps.setProperty("MatchWinnerFileName", "matchwinner.txt");
		defaultFileNameProps.setProperty("MeatballFileName",  "meatball.txt");
		defaultFileNameProps.setProperty("Score2FileName", "score2.txt");
		defaultFileNameProps.setProperty("TimeOut1FileName", "timeout1.txt");
		defaultFileNameProps.setProperty("TimeOut2FileName", "timeout2.txt");
		defaultFileNameProps.setProperty("Reset1FileName", "reset1.txt");
		defaultFileNameProps.setProperty("Reset2FileName", "reset2.txt");
		defaultFileNameProps.setProperty("Warn1FileName", "warn1.txt");
		defaultFileNameProps.setProperty("Warn2FileName", "warn2.txt");
		defaultFileNameProps.setProperty("LastScoredFileName","lastscored.txt");
		defaultFileNameProps.setProperty("GameTimeFileName", "gametime.txt");
		defaultFileNameProps.setProperty("MatchTimeFileName", "matchtime.txt");
		defaultFileNameProps.setProperty("Stuffs1FileName", "stuffs1.txt");
		defaultFileNameProps.setProperty("Stuffs2FileName", "stuffs2.txt");
		defaultFileNameProps.setProperty("Breaks1FileName", "breaks1.txt");
		defaultFileNameProps.setProperty("Breaks2FileName", "breaks2.txt");
		defaultFileNameProps.setProperty("Team1PassAttemptsFileName", "team1passattempts.txt");
		defaultFileNameProps.setProperty("Team2PassAttemptsFileName", "team2passattempts.txt");
		defaultFileNameProps.setProperty("Team1PassCompletesFileName", "team1passcompletes.txt");
		defaultFileNameProps.setProperty("Team2PassCompletesFileName", "team2passcompletes.txt");
		defaultFileNameProps.setProperty("Team1ShotAttemptsFileName", "team1shotattempts.txt");
		defaultFileNameProps.setProperty("Team2ShotAttemptsFileName", "team2shotattempts.txt");
		defaultFileNameProps.setProperty("Team1ShotCompletesFileName", "team1shotcompletes.txt");
		defaultFileNameProps.setProperty("Team2ShotCompletesFileName", "team2shotcompletes.txt");
		defaultFileNameProps.setProperty("Team1ClearAttemptsFileName", "team1clearattempts.txt");
		defaultFileNameProps.setProperty("Team2ClearAttemptsFileName", "team2clearattempts.txt");
		defaultFileNameProps.setProperty("Team1ClearCompletesFileName", "team1clearcompletes.txt");
		defaultFileNameProps.setProperty("Team2ClearCompletesFileName", "team2clearcompletes.txt");
		defaultFileNameProps.setProperty("Team1PassPercentFileName", "team1passpercent.txt");
		defaultFileNameProps.setProperty("Team2PassPercentFileName", "team2passpercent.txt");
		defaultFileNameProps.setProperty("Team1ShotPercentFileName", "team1shotpercent.txt");
		defaultFileNameProps.setProperty("Team2ShotPercentFileName", "team2shotpercent.txt");
		defaultFileNameProps.setProperty("Team1ClearPercentFileName", "team1clearpercent.txt");
		defaultFileNameProps.setProperty("Team2ClearPercentFileName", "team2clearpercent.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassAttemptsFileName", "team1twobarpassattempts.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassAttemptsFileName", "team2twobarpassattempts.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassCompletesFileName", "team1twobarpasscompletes.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassCompletesFileName", "team2twobarpasscompletes.txt");
		defaultFileNameProps.setProperty("Team1TwoBarPassPercentFileName", "team1twobarpasspercent.txt");
		defaultFileNameProps.setProperty("Team2TwoBarPassPercentFileName", "team2twobarpasspercent.txt");
		defaultFileNameProps.setProperty("Team1ScoringFileName", "team1scoring.txt");
		defaultFileNameProps.setProperty("Team2ScoringFileName", "team2scoring.txt");
		defaultFileNameProps.setProperty("Team1ThreeBarScoringFileName", "team1threebarscoring.txt");
		defaultFileNameProps.setProperty("Team2ThreeBarScoringFileName", "team2threebarscoring.txt");
		defaultFileNameProps.setProperty("Team1FiveBarScoringFileName", "team1fivebarscoring.txt");
		defaultFileNameProps.setProperty("Team2FiveBarScoringFileName", "team2fivebarscoring.txt");
		defaultFileNameProps.setProperty("Team1TwoBarScoringFileName", "team1twobarscoring.txt");
		defaultFileNameProps.setProperty("Team2TwoBarScoringFileName", "team2twobarscoring.txt");
		defaultFileNameProps.setProperty("Team1ShotsOnGoalFileName", "team1shotsongoal.txt");
		defaultFileNameProps.setProperty("Team2ShotsOnGoalFileName", "team2shotsongoal.txt");
		defaultFileNameProps.setProperty("Side1ColorFileName","side1color.txt");
		defaultFileNameProps.setProperty("Side2ColorFileName","side2color.txt");
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
		//Config Properties
		configControlProps = new Properties(defaultControlProps);
		configSourceProps = new Properties(defaultSourceProps);
		configFileNameProps = new Properties(defaultFileNameProps);
		configHotKeyProps = new Properties(defaultHotKeyProps);
		loadFromControlConfig();
		loadFromSourceConfig();
		loadFromFileNameConfig();
		loadFromHotKeyConfig();
	}

	//Getters
	//Control Parameters
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
	public String getLogoImageURL() {return configControlProps.getProperty("LogoImageURL");}
	public String getLogoLinkURI() {return configControlProps.getProperty("LogoLinkURI");}
	//OBS
	public String getOBSHost() {return configControlProps.getProperty("OBSHost");}
	public String getOBSPort() {return configControlProps.getProperty("OBSPort");}
	public String getOBSPassword() {return configControlProps.getProperty("OBSPassword");}
	public int getOBSSavePassword() {return Integer.parseInt(configControlProps.getProperty("OBSSavePassword"));}
	public int getOBSAutoLogin() {return Integer.parseInt(configControlProps.getProperty("OBSAutoLogin"));}
	public int getOBSCloseOnConnect() {return Integer.parseInt(configControlProps.getProperty("OBSCloseOnConnect"));}
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
	public String getTableSource() {return configSourceProps.getProperty("Table");}
	public String getTournamentSource() {return configSourceProps.getProperty("Tournament");}
	public String getEventSource() {return configSourceProps.getProperty("Event");}
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
//	public String getFileName(String property, int teamNbr) {
//		String fileName;
//		return fileName;
//	}
	public String getTeamNameFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1NameFileName");
		} else {
			return configFileNameProps.getProperty("Team2NameFileName");
		}
	}
	public String getTeamForwardFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ForwardFileName");
		} else {
			return configFileNameProps.getProperty("Team2ForwardFileName");
		}
	}
	public String getTeamGoalieFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1GoalieFileName");
		} else {
			return configFileNameProps.getProperty("Team2GoalieFileName");
		}
	}
	public String getGameCountFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("GameCount1FileName");
		} else {
			return configFileNameProps.getProperty("GameCount2FileName");
		}
	}
	public String getScoreFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Score1FileName");
		} else {
			return configFileNameProps.getProperty("Score2FileName");
		}
	}
	public String getTimeOutFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("TimeOut1FileName");
		} else {
			return configFileNameProps.getProperty("TimeOut2FileName");
		}
	}
	public String getResetFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Reset1FileName");
		} else {
			return configFileNameProps.getProperty("Reset2FileName");
		}
	}
	public String getWarnFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Warn1FileName");
		} else {
			return configFileNameProps.getProperty("Warn2FileName");
		}
	}
	public String getPassAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassAttemptsFileName");
		} else {
			return configFileNameProps.getProperty("Team2PassAttemptsFileName");
		}
	}
	public String getPassCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassCompletesFileName");
		} else {
			return configFileNameProps.getProperty("Team2PassCompletesFileName");
		}
	}
	public String getShotAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotAttemptsFileName");
		} else {
			return configFileNameProps.getProperty("Team2ShotAttemptsFileName");
		}
	}
	public String getShotCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotCompletesFileName");
		} else {
			return configFileNameProps.getProperty("Team2ShotCompletesFileName");
		}
	}
	public String getClearAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearAttemptsFileName");
		} else {
			return configFileNameProps.getProperty("Team2ClearAttemptsFileName");
		}
	}
	public String getClearCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearCompletesFileName");
		} else {
			return configFileNameProps.getProperty("Team2ClearCompletesFileName");
		}
	}
	public String getPassPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1PassPercentFileName");
		} else {
			return configFileNameProps.getProperty("Team2PassPercentFileName");
		}
	}
	public String getShotPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotPercentFileName");
		} else {
			return configFileNameProps.getProperty("Team2ShotPercentFileName");
		}
	}
	public String getClearPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ClearPercentFileName");
		} else {
			return configFileNameProps.getProperty("Team2ClearPercentFileName");
		}
	}
	public String getTwoBarPassAttemptsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassAttemptsFileName");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassAttemptsFileName");
		}
	}
	public String getTwoBarPassCompletesFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassCompletesFileName");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassCompletesFileName");
		}
	}
	public String getTwoBarPassPercentFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarPassPercentFileName");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarPassPercentFileName");
		}
	}
	public String getScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ScoringFileName");
		} else {
			return configFileNameProps.getProperty("Team2ScoringFileName");
		}
	}
	public String getThreeBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ThreeBarScoringFileName");
		} else {
			return configFileNameProps.getProperty("Team2ThreeBarScoringFileName");
		}
	}
	public String getFiveBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1FiveBarScoringFileName");
		} else {
			return configFileNameProps.getProperty("Team2FiveBarScoringFileName");
		}
	}
	public String getTwoBarScoringFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1TwoBarScoringFileName");
		} else {
			return configFileNameProps.getProperty("Team2TwoBarScoringFileName");
		}
	}
	public String getShotsOnGoalFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Team1ShotsOnGoalFileName");
		} else {
			return configFileNameProps.getProperty("Team2ShotsOnGoalFileName");
		}
	}
	public String getStuffsFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Stuffs1FileName");
		} else {
			return configFileNameProps.getProperty("Stuffs2FileName");
		}
	}
	public String getBreaksFileName(int teamNbr) {
		if(teamNbr==1) {
			return configFileNameProps.getProperty("Breaks1FileName");
		} else {
			return configFileNameProps.getProperty("Breaks2FileName");
		}
	}
	public String getTableFileName() {return configFileNameProps.getProperty("TableFileName");}
	public String getTournamentFileName() {return configFileNameProps.getProperty("TournamentFileName");}
	public String getEventFileName() {return configFileNameProps.getProperty("EventFileName");}
	public String getTimeRemainingFileName() {return configFileNameProps.getProperty("TimeRemainingFileName");}
	public String getTimerInUseFileName() {return configFileNameProps.getProperty("TimerInUseFileName");}
	public String getMatchWinnerFileName() {return configFileNameProps.getProperty("MatchWinnerFileName");}
	public String getMeatballFileName() {return configFileNameProps.getProperty("MeatballFileName");}
	public String getLastScoredFileName() {return configFileNameProps.getProperty("LastScoredFileName");}
	public String getGameTimeFileName() {return configFileNameProps.getProperty("GameTimeFileName");}
	public String getMatchTimeFileName() {return configFileNameProps.getProperty("MatchTimeFileName");}
	public String getSide1ColorFileName() {return configFileNameProps.getProperty("Side1ColorFileName");}
	public String getSide2ColorFileName() {return configFileNameProps.getProperty("Side2ColorFileName");}
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
//Setters
	//Control Parameters
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
	public void setLogoImageURL(String logoImageURL) {
		configControlProps.setProperty("LogoImageURL", logoImageURL);
	}
	public void setLogoLinkURI(String logoLinkURI) {
		configControlProps.setProperty("LogoLinkURI", logoLinkURI);
	}
	//OBS
	public void setOBSHost(String obsHost) {
		configControlProps.setProperty("OBSHost", obsHost);
	}
	public void setOBSPort(String obsPort) {
		configControlProps.setProperty("OBSPort", obsPort);
	}
	public void setOBSPassword(String obsPassword) {
		this.obsPassword = obsPassword;
		configControlProps.setProperty("OBSPassword", obsPassword);
	}
	public void setOBSSavePassword(int obsSavePassword) {
		configControlProps.setProperty("OBSSavePassword", Integer.toString(obsSavePassword));
	}
	public void setOBSAutoLogin(int obsAutoLogin) {
		configControlProps.setProperty("OBSAutoLogin", Integer.toString(obsAutoLogin));
	}
	public void setOBSCloseOnConnect(int obsCloseOnConnect) {
		configControlProps.setProperty("OBSCloseOnConnect", Integer.toString(obsCloseOnConnect));
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
	public void setSide1ColorSource(String side1ColorSource) {
		configSourceProps.setProperty("Side1Color", side1ColorSource);
	}
	public void setSide2ColorSource(String side2ColorSource) {
		configSourceProps.setProperty("Side2Color", side2ColorSource);
	}
	//FileNames
	public void setTeam1PassAttemptsFileName(String team1PassAttemptsFileName) {
		configFileNameProps.setProperty("Team1PassAttemptsFileName", team1PassAttemptsFileName);
	}
	public void setTeam2PassAttemptsFileName(String team2PassAttemptsFileName) {
		configFileNameProps.setProperty("Team2PassAttemptsFileName", team2PassAttemptsFileName);
	}
	public void setTeam1PassCompletesFileName(String team1PassCompletesFileName) {
		configFileNameProps.setProperty("Team1PassCompletesFileName", team1PassCompletesFileName);
	}
	public void setTeam2PassCompletesFileName(String team2PassCompletesFileName) {
		configFileNameProps.setProperty("Team2PassCompletesFileName", team2PassCompletesFileName);
	}
	public void setTeam1ShotAttemptsFileName(String team1ShotAttemptsFileName) {
		configFileNameProps.setProperty("Team1ShotAttemptsFileName", team1ShotAttemptsFileName);
	}
	public void setTeam2ShotAttemptsFileName(String team2ShotAttemptsFileName) {
		configFileNameProps.setProperty("Team2ShotAttemptsFileName", team2ShotAttemptsFileName);
	}
	public void setTeam1ShotCompletesFileName(String team1ShotCompletesFileName) {
		configFileNameProps.setProperty("Team1ShotCompletesFileName", team1ShotCompletesFileName);
	}
	public void setTeam2ShotCompletesFileName(String team2ShotCompletesFileName) {
		configFileNameProps.setProperty("Team2ShotCompletesFileName", team2ShotCompletesFileName);
	}
	public void setTeam1ClearAttemptsFileName(String team1ClearAttemptsFileName) {
		configFileNameProps.setProperty("Team1ClearAttemptsFileName", team1ClearAttemptsFileName);
	}
	public void setTeam2ClearAttemptsFileName(String team2ClearAttemptsFileName) {
		configFileNameProps.setProperty("Team2ClearAttemptsFileName", team2ClearAttemptsFileName);
	}
	public void setTeam1ClearCompletesFileName(String team1ClearCompletesFileName) {
		configFileNameProps.setProperty("Team1ClearCompletesFileName", team1ClearCompletesFileName);
	}
	public void setTeam2ClearCompletesFileName(String team2ClearCompletesFileName) {
		configFileNameProps.setProperty("Team2ClearCompletesFileName", team2ClearCompletesFileName);
	}
	public void setTeam1PassPercentFileName(String team1PassPercentFileName) {
		configFileNameProps.setProperty("Team1PassPercentFileName", team1PassPercentFileName);
	}
	public void setTeam2PassPercentFileName(String team2PassPercentFileName) {
		configFileNameProps.setProperty("Team2PassPercentFileName", team2PassPercentFileName);
	}
	public void setTeam1ShotPercentFileName(String team1ShotPercentFileName) {
		configFileNameProps.setProperty("Team1ShotPercentFileName", team1ShotPercentFileName);
	}
	public void setTeam2ShotPercentFileName(String team2ShotPercentFileName) {
		configFileNameProps.setProperty("Team2ShotPercentFileName", team2ShotPercentFileName);
	}
	public void setTeam1ClearPercentFileName(String team1ClearPercentFileName) {
		configFileNameProps.setProperty("Team1ClearPercentFileName", team1ClearPercentFileName);
	}
	public void setTeam2ClearPercentFileName(String team2ClearPercentFileName) {
		configFileNameProps.setProperty("Team2ClearPercentFileName", team2ClearPercentFileName);
	}
	public void setTeam1TwoBarPassAttemptsFileName(String team1TwoBarPassAttemptsFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassAttemptsFileName", team1TwoBarPassAttemptsFileName);
	}
	public void setTeam2TwoBarPassAttemptsFileName(String team2TwoBarPassAttemptsFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassAttemptsFileName", team2TwoBarPassAttemptsFileName);
	}
	public void setTeam1TwoBarPassCompletesFileName(String team1TwoBarPassCompletesFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassCompletesFileName", team1TwoBarPassCompletesFileName);
	}
	public void setTeam2TwoBarPassCompletesFileName(String team2TwoBarPassCompletesFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassCompletesFileName", team2TwoBarPassCompletesFileName);
	}
	public void setTeam1TwoBarPassPercentFileName(String team1TwoBarPassPercentFileName) {
		configFileNameProps.setProperty("Team1TwoBarPassPercentFileName", team1TwoBarPassPercentFileName);
	}
	public void setTeam2TwoBarPassPercentFileName(String team2TwoBarPassPercentFileName) {
		configFileNameProps.setProperty("Team2TwoBarPassPercentFileName", team2TwoBarPassPercentFileName);
	}
	public void setTeam1ScoringFileName(String team1ScoringFileName) {
		configFileNameProps.setProperty("Team1ScoringFileName", team1ScoringFileName);
	}
	public void setTeam2ScoringFileName(String team2ScoringFileName) {
		configFileNameProps.setProperty("Team2ScoringFileName", team2ScoringFileName);
	}
	public void setTeam1ThreeBarScoringFileName(String team1ThreeBarScoringFileName) {
		configFileNameProps.setProperty("Team1ThreeBarScoringFileName", team1ThreeBarScoringFileName);
	}
	public void setTeam2ThreeBarScoringFileName(String team2ThreeBarScoringFileName) {
		configFileNameProps.setProperty("Team2ThreeBarScoringFileName", team2ThreeBarScoringFileName);
	}
	public void setTeam1FiveBarScoringFileName(String team1FiveBarScoringFileName) {
		configFileNameProps.setProperty("Team1FiveBarScoringFileName", team1FiveBarScoringFileName);
	}
	public void setTeam2FiveBarScoringFileName(String team2FiveBarScoringFileName) {
		configFileNameProps.setProperty("Team2FiveBarScoringFileName", team2FiveBarScoringFileName);
	}
	public void setTeam1TwoBarScoringFileName(String team1TwoBarScoringFileName) {
		configFileNameProps.setProperty("Team1TwoBarScoringFileName", team1TwoBarScoringFileName);
	}
	public void setTeam2TwoBarScoringFileName(String team2TwoBarScoringFileName) {
		configFileNameProps.setProperty("Team2TwoBarScoringFileName", team2TwoBarScoringFileName);
	}
	public void setTeam1ShotsOnGoalFileName(String team1ShotsOnGoalFileName) {
		configFileNameProps.setProperty("Team1ShotsOnGoalFileName", team1ShotsOnGoalFileName);
	}
	public void setTeam2ShotsOnGoalFileName(String team2ShotsOnGoalFileName) {
		configFileNameProps.setProperty("Team2ShotsOnGoalFileName", team2ShotsOnGoalFileName);
	}
	public void setTableFileName(String tableFileName) {
		configFileNameProps.setProperty("TableFileName", tableFileName);
	}
	public void setTeam1NameFileName(String team1NameFileName) {
		configFileNameProps.setProperty("Team1NameFileName", team1NameFileName);
	}
	public void setTeam1ForwardFileName(String team1ForwardFileName) {
		configFileNameProps.setProperty("Team1ForwardFileName", team1ForwardFileName);
	}
	public void setTeam1GoalieFileName(String team1GoalieFileName) {
		configFileNameProps.setProperty("Team1GoalieFileName", team1GoalieFileName);
	}
	public void setTournamentFileName(String tournamentFileName) {
		configFileNameProps.setProperty("TournamentFileName", tournamentFileName);
	}
	public void setTeam2NameFileName(String team2NameFileName) {
		configFileNameProps.setProperty("Team2NameFileName", team2NameFileName);
	}
	public void setTeam2ForwardFileName(String team2ForwardFileName) {
		configFileNameProps.setProperty("Team2ForwardFileName", team2ForwardFileName);
	}
	public void setTeam2GoalieFileName(String team2GoalieFileName) {
		configFileNameProps.setProperty("Team2GoalieFileName", team2GoalieFileName);
	}
	public void setEventFileName(String eventFileName) {
		configFileNameProps.setProperty("EventFileName", eventFileName);
	}
	public void setGameCount1FileName(String gameCount1FileName) {
		configFileNameProps.setProperty("GameCount1FileName", gameCount1FileName);
	}
	public void setTimeRemainingFileName(String timeRemainingFileName) {
		configFileNameProps.setProperty("TimeRemainingFileName", timeRemainingFileName);
	}
	public void setGameCount2FileName(String gameCount2FileName) {
		configFileNameProps.setProperty("GameCount2FileName", gameCount2FileName);
	}
	public void setTimerInUseFileName(String timerInUseFileName) {
		configFileNameProps.setProperty("TimerInUseFileName", timerInUseFileName);
	}
	public void setScore1FileName(String score1FileName) {
		configFileNameProps.setProperty("Score1FileName", score1FileName);
	}
	public void setMatchWinnerFileName(String matchWinnerFileName) {
		configFileNameProps.setProperty("MatchWinnerFileName", matchWinnerFileName);
	}
	public void setMeatballFileName(String meatballFileName) {
		configFileNameProps.setProperty("MeatballFileName", meatballFileName);
	}
	public void setScore2FileName(String score2FileName) {
		configFileNameProps.setProperty("Score2FileName", score2FileName);
	}
	public void setTimeOut1FileName(String timeOut1FileName) {
		configFileNameProps.setProperty("TimeOut1FileName", timeOut1FileName);
	}
	public void setTimeOut2FileName(String timeOut2FileName) {
		configFileNameProps.setProperty("TimeOut2FileName", timeOut2FileName);
	}
	public void setReset1FileName(String reset1FileName) {
		configFileNameProps.setProperty("Reset1FileName", reset1FileName);
	}
	public void setReset2FileName(String reset2FileName) {
		configFileNameProps.setProperty("Reset2FileName", reset2FileName);
	}
	public void setWarn1FileName(String warn1FileName) {
		configFileNameProps.setProperty("Warn1FileName", warn1FileName);
	}
	public void setWarn2FileName(String warn2FileName) {
		configFileNameProps.setProperty("Warn2FileName", warn2FileName);
	}
	public void setLastScoredFileName(String lastScoredFileName) {
		configFileNameProps.setProperty("LastScoredFileName", lastScoredFileName);
	}
	public void setGameTimeFileName(String gameTimeFileName) {
		configFileNameProps.setProperty("GameTimeFileName", gameTimeFileName);
	}
	public void setMatchTimeFileName(String matchTimeFileName) {
		configFileNameProps.setProperty("MatchTimeFileName", matchTimeFileName);
	}
	public void setStuffs1FileName(String stuffs1FileName) {
		configFileNameProps.setProperty("Stuffs1FileName", stuffs1FileName);
	}
	public void setStuffs2FileName(String stuffs2FileName) {
		configFileNameProps.setProperty("Stuffs2FileName", stuffs2FileName);
	}
	public void setBreaks1FileName(String breaks1FileName) {
		configFileNameProps.setProperty("Breaks1FileName", breaks1FileName);
	}
	public void setBreaks2FileName(String breaks2FileName) {
		configFileNameProps.setProperty("Breaks2FileName", breaks2FileName);
	}
	public void setSide1ColorFileName(String side1ColorFileName) {
		configFileNameProps.setProperty("Side1ColorFileName", side1ColorFileName);
	}
	public void setSide2ColorFileName(String side2ColorFileName) {
		configFileNameProps.setProperty("Side2ColorFileName", side2ColorFileName);
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
//Get Defaults
	//Control Parameters
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
//	public String getDefaultLogoImageURL() {return defaultControlProps.getProperty("LogoImageURL");}
//	public String getDefaultLogoLinkURI() {return defaultControlProps.getProperty("LogoLinkURI");}
	//OBS
	public String getDefaultOBSHost() {return defaultControlProps.getProperty("OBSHost");}
	public String getDefaultOBSPort() {return defaultControlProps.getProperty("OBSPort");}
	public String getDefaultOBSPassword() {return defaultControlProps.getProperty("OBSPassword");}
	public int getDefaultOBSAutoLogin() {return Integer.parseInt(defaultControlProps.getProperty("OBSAutoLogin"));}
	public int getDefaultOBSSavePassword() {return Integer.parseInt(defaultControlProps.getProperty("OBSSavePassword"));}
	public int getDefaultOBSCloseOnConnect() {return Integer.parseInt(defaultControlProps.getProperty("OBSCloseOnConnect"));}
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
	public String getDefaultTableFileName() {return defaultFileNameProps.getProperty("TableFileName");}
	public String getDefaultTeam1NameFileName() {return defaultFileNameProps.getProperty("Team1NameFileName");}
	public String getDefaultTeam1ForwardFileName() {return defaultFileNameProps.getProperty("Team1ForwardFileName");}
	public String getDefaultTeam1GoalieFileName() {return defaultFileNameProps.getProperty("Team1GoalieFileName");}
	public String getDefaultTournamentFileName() {return defaultFileNameProps.getProperty("TournamentFileName");}
	public String getDefaultTeam2NameFileName() {return defaultFileNameProps.getProperty("Team2NameFileName");}
	public String getDefaultTeam2ForwardFileName() {return defaultFileNameProps.getProperty("Team2ForwardFileName");}
	public String getDefaultTeam2GoalieFileName() {return defaultFileNameProps.getProperty("Team2GoalieFileName");}
	public String getDefaultEventFileName() {return defaultFileNameProps.getProperty("EventFileName");}
	public String getDefaultGameCount1FileName() {return defaultFileNameProps.getProperty("GameCount1FileName");}
	public String getDefaultTimeRemainingFileName() {return defaultFileNameProps.getProperty("TimeRemainingFileName");}
	public String getDefaultGameCount2FileName() {return defaultFileNameProps.getProperty("GameCount2FileName");}
	public String getDefaultTimerInUseFileName() {return defaultFileNameProps.getProperty("TimerInUseFileName");}
	public String getDefaultScore1FileName() {return defaultFileNameProps.getProperty("Score1FileName");}
	public String getDefaultMatchWinnerFileName() {return defaultFileNameProps.getProperty("MatchWinnerFileName");}
	public String getDefaultMeatballFileName() {return defaultFileNameProps.getProperty("MeatballFileName");}
	public String getDefaultScore2FileName() {return defaultFileNameProps.getProperty("Score2FileName");}
	public String getDefaultTimeOut1FileName() {return defaultFileNameProps.getProperty("TimeOut1FileName");}
	public String getDefaultTimeOut2FileName() {return defaultFileNameProps.getProperty("TimeOut2FileName");}
	public String getDefaultReset1FileName() {return defaultFileNameProps.getProperty("Reset1FileName");}
	public String getDefaultReset2FileName() {return defaultFileNameProps.getProperty("Reset2FileName");}
	public String getDefaultWarn1FileName() {return defaultFileNameProps.getProperty("Warn1FileName");}
	public String getDefaultWarn2FileName() {return defaultFileNameProps.getProperty("Warn2FileName");}
	public String getDefaultLastScoredFileName() {return defaultFileNameProps.getProperty("LastScoredFileName");}
	public String getDefaultGameTimeFileName() {return defaultFileNameProps.getProperty("GameTimeFileName");}
	public String getDefaultMatchTimeFileName() {return defaultFileNameProps.getProperty("MatchTimeFileName");}
	public String getDefaultStuffs1FileName() {return defaultFileNameProps.getProperty("Stuffs1FileName");}
	public String getDefaultStuffs2FileName() {return defaultFileNameProps.getProperty("Stuffs2FileName");}
	public String getDefaultBreaks1FileName() {return defaultFileNameProps.getProperty("Breaks1FileName");}
	public String getDefaultBreaks2FileName() {return defaultFileNameProps.getProperty("Breaks2FileName");}
	public String getDefaultTeam1PassAttemptsFileName() {return defaultFileNameProps.getProperty("Team1PassAttemptsFileName");}
	public String getDefaultTeam1PassCompletesFileName() {return defaultFileNameProps.getProperty("Team1PassCompletesFileName");}
	public String getDefaultTeam2PassAttemptsFileName() {return defaultFileNameProps.getProperty("Team2PassAttemptsFileName");}
	public String getDefaultTeam2PassCompletesFileName() {return defaultFileNameProps.getProperty("Team2PassCompletesFileName");}
	public String getDefaultTeam1ShotAttemptsFileName() {return defaultFileNameProps.getProperty("Team1ShotAttemptsFileName");}
	public String getDefaultTeam1ShotCompletesFileName() {return defaultFileNameProps.getProperty("Team1ShotCompletesFileName");}
	public String getDefaultTeam2ShotAttemptsFileName() {return defaultFileNameProps.getProperty("Team2ShotAttemptsFileName");}
	public String getDefaultTeam2ShotCompletesFileName() {return defaultFileNameProps.getProperty("Team2ShotCompletesFileName");}
	public String getDefaultTeam1ClearAttemptsFileName() {return defaultFileNameProps.getProperty("Team1ClearAttemptsFileName");}
	public String getDefaultTeam1ClearCompletesFileName() {return defaultFileNameProps.getProperty("Team1ClearCompletesFileName");}
	public String getDefaultTeam2ClearAttemptsFileName() {return defaultFileNameProps.getProperty("Team2ClearAttemptsFileName");}
	public String getDefaultTeam2ClearCompletesFileName() {return defaultFileNameProps.getProperty("Team2ClearCompletesFileName");}
	public String getDefaultSide1ColorFileName() {return defaultFileNameProps.getProperty("Side1ColorFileName");}
	public String getDefaultSide2ColorFileName() {return defaultFileNameProps.getProperty("Side2ColorFileName");}
	public String getDefaultTeam1PassPercentFileName() {return defaultFileNameProps.getProperty("Team1PassPercentFileName");}
	public String getDefaultTeam2PassPercentFileName() {return defaultFileNameProps.getProperty("Team2PassPercentFileName");}
	public String getDefaultTeam1ShotPercentFileName() {return defaultFileNameProps.getProperty("Team1ShotPercentFileName");}
	public String getDefaultTeam2ShotPercentFileName() {return defaultFileNameProps.getProperty("Team2ShotPercentFileName");}
	public String getDefaultTeam1ClearPercentFileName() {return defaultFileNameProps.getProperty("Team1ClearPercentFileName");}
	public String getDefaultTeam2ClearPercentFileName() {return defaultFileNameProps.getProperty("Team2ClearPercentFileName");}
	public String getDefaultTeam1TwoBarPassAttemptsFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassAttemptsFileName");}
	public String getDefaultTeam1TwoBarPassCompletesFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassCompletesFileName");}
	public String getDefaultTeam2TwoBarPassAttemptsFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassAttemptsFileName");}
	public String getDefaultTeam2TwoBarPassCompletesFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassCompletesFileName");}
	public String getDefaultTeam1TwoBarPassPercentFileName() {return defaultFileNameProps.getProperty("Team1TwoBarPassPercentFileName");}
	public String getDefaultTeam2TwoBarPassPercentFileName() {return defaultFileNameProps.getProperty("Team2TwoBarPassPercentFileName");}
	public String getDefaultTeam1ScoringFileName() {return defaultFileNameProps.getProperty("Team1ScoringFileName");}
	public String getDefaultTeam2ScoringFileName() {return defaultFileNameProps.getProperty("Team2ScoringFileName");}
	public String getDefaultTeam1ThreeBarScoringFileName() {return defaultFileNameProps.getProperty("Team1ThreeBarScoringFileName");}
	public String getDefaultTeam2ThreeBarScoringFileName() {return defaultFileNameProps.getProperty("Team2ThreeBarScoringFileName");}
	public String getDefaultTeam1FiveBarScoringFileName() {return defaultFileNameProps.getProperty("Team1FiveBarScoringFileName");}
	public String getDefaultTeam2FiveBarScoringFileName() {return defaultFileNameProps.getProperty("Team2FiveBarScoringFileName");}
	public String getDefaultTeam1TwoBarScoringFileName() {return defaultFileNameProps.getProperty("Team1TwoBarScoringFileName");}	
	public String getDefaultTeam2TwoBarScoringFileName() {return defaultFileNameProps.getProperty("Team2TwoBarScoringFileName");}	
	public String getDefaultTeam1ShotsOnGoalFileName() {return defaultFileNameProps.getProperty("Team1ShotsOnGoalFileName");}
	public String getDefaultTeam2ShotsOnGoalFileName() {return defaultFileNameProps.getProperty("Team2ShotsOnGoalFileName");}
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
	
	public void loadFromControlConfig() throws IOException {
		try {
			try(InputStream inputStream = Files.newInputStream(Paths.get(configControlFileName))) {
				configControlProps.load(inputStream);
			} 
		} catch (FileNotFoundException e) {
			Files.createFile(Paths.get(configControlFileName));
			configControlProps = defaultControlProps;
			loadFromControlConfig();
			saveToControlConfig();
		}
//		//OBS
//		obsPassword = configControlProps.getProperty("OBSPassword");
//		obsSavePassword = Integer.parseInt(configControlProps.getProperty("OBSSavePassword"));
	}
	public void loadFromSourceConfig() throws IOException {
		try {
			try(InputStream inputStream = Files.newInputStream(Paths.get(configSourceFileName))) {
				configSourceProps.load(inputStream);
			} 
		} catch (FileNotFoundException e) {
			Files.createFile(Paths.get(configSourceFileName));
			configSourceProps = defaultSourceProps;
			loadFromSourceConfig();
			saveToSourceConfig();
		}
		configSourceProps.keySet().stream()
//				.map(key -> key + ": " + configSourceProps.getProperty(key.toString()))
				.forEach(System.out::println);
		System.out.println("\r\n\r\n");
	}
	public void loadFromFileNameConfig() throws IOException {

		try {
			try(InputStream inputStream = Files.newInputStream(Paths.get(configFileNameFileName))) {
				configFileNameProps.load(inputStream);
			} 
		} catch (FileNotFoundException e) {
			Files.createFile(Paths.get(configFileNameFileName));
			configFileNameProps = defaultFileNameProps;
			loadFromFileNameConfig();
			saveToFileNameConfig();
		}
		configFileNameProps.keySet().stream()
//				.map(key -> key + ": " + configFileNameProps.getProperty(key.toString()))
				.forEach(System.out::println);
		System.out.println("\r\n\r\n");
	}
	public void loadFromHotKeyConfig() throws IOException {
		try {
			try(InputStream inputStream = Files.newInputStream(Paths.get(configHotKeyFileName))) {
				configHotKeyProps.load(inputStream);
			} 
		} catch (FileNotFoundException e) {
			Files.createFile(Paths.get(configHotKeyFileName));
			configHotKeyProps = defaultHotKeyProps;
			loadFromHotKeyConfig();
			saveToHotKeyConfig();
		}
//HotKeys
	}
	public void saveToConfig() throws IOException {
		saveToControlConfig();
		saveToSourceConfig();
		saveToFileNameConfig();
		saveToHotKeyConfig();
	}
	public void saveToControlConfig() throws IOException {
		//Control Parameters
		configControlProps.setProperty("GameType", this.getGameType());
		configControlProps.setProperty("TableName", this.getTableName());
		configControlProps.setProperty("datapath", this.getDatapath());
		configControlProps.setProperty("PointsToWin", Integer.toString(this.getPointsToWin()));
		configControlProps.setProperty("MaxWin", Integer.toString(this.getMaxWin()));
		configControlProps.setProperty("WinBy", Integer.toString(this.getWinBy()));
		configControlProps.setProperty("GamesToWin", Integer.toString(this.getGamesToWin()));
		configControlProps.setProperty("MaxTimeOuts", Integer.toString(this.getMaxTimeOuts()));
		configControlProps.setProperty("AutoIncrementGame", Integer.toString(this.getAutoIncrementGame()));
		configControlProps.setProperty("AnnounceWinner", Integer.toString(this.getAnnounceWinner()));
		configControlProps.setProperty("AnnounceMeatball", Integer.toString(this.getAnnounceMeatball()));
		configControlProps.setProperty("WinnerPrefix", this.getWinnerPrefix());
		configControlProps.setProperty("WinnerSuffix", this.getWinnerSuffix());
		configControlProps.setProperty("Meatball",  this.getMeatball());
		configControlProps.setProperty("Team1LastScored", getTeam1LastScored());
		configControlProps.setProperty("Team2LastScored", getTeam2LastScored());
		configControlProps.setProperty("ClearLastScored", getClearLastScored());
		configControlProps.setProperty("Side1Color", getSide1Color());
		configControlProps.setProperty("Side2Color", getSide2Color());
		configControlProps.setProperty("ShotTime", Integer.toString(this.getShotTime()));
		configControlProps.setProperty("PassTime", Integer.toString(this.getPassTime()));
		configControlProps.setProperty("TimeOutTime", Integer.toString(this.getTimeOutTime()));
		configControlProps.setProperty("GameTime", Integer.toString(this.getGameTime()));
		configControlProps.setProperty("RecallTime", Integer.toString(this.getRecallTime()));
		configControlProps.setProperty("ShowTimeOutsUsed", Integer.toString(this.getShowTimeOutsUsed()));
		configControlProps.setProperty("AutoCapNames", Integer.toString(this.getAutoCapNames()));
		configControlProps.setProperty("WinByFinalOnly", Integer.toString(this.getWinByFinalOnly()));
		//OBS
		configControlProps.setProperty("OBSHost", this.getOBSHost());
		configControlProps.setProperty("OBSPort", this.getOBSPort());
		if (this.getOBSSavePassword()==1) {
			configControlProps.setProperty("OBSPassword", this.getOBSPassword());
		} else {
			configControlProps.setProperty("OBSPassword", "");
		}
		configControlProps.setProperty("OBSAutoLogin", Integer.toString(this.getOBSAutoLogin()));
		configControlProps.setProperty("OBSSavePassword", Integer.toString(this.getOBSSavePassword()));
		configControlProps.setProperty("OBSCloseOnConnect", Integer.toString(this.getOBSCloseOnConnect()));
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configControlFileName))) {
			configControlProps.store(outputStream, "FoosOBSPlus Control settings");
		}
	}
	public void saveToSourceConfig() throws IOException {
		//Source
		configSourceProps.setProperty("Table", this.getTableSource());
		configSourceProps.setProperty("Team1Name", this.getTeamNameSource(1));
		configSourceProps.setProperty("Team1Forward", this.getTeamForwardSource(1));
		configSourceProps.setProperty("Team1Goalie", this.getTeamGoalieSource(1));
		configSourceProps.setProperty("Tournament", this.getTournamentSource());
		configSourceProps.setProperty("Team2Name", this.getTeamNameSource(2));
		configSourceProps.setProperty("Team2Forward", this.getTeamForwardSource(2));
		configSourceProps.setProperty("Team2Goalie", this.getTeamGoalieSource(2));
		configSourceProps.setProperty("Event", this.getEventSource());
		configSourceProps.setProperty("GameCount1", this.getGameCountSource(1));
		configSourceProps.setProperty("TimeRemaining", this.getTimeRemainingSource());
		configSourceProps.setProperty("GameCount2", this.getGameCountSource(2));
		configSourceProps.setProperty("TimerInUse", this.getTimerInUseSource());
		configSourceProps.setProperty("Score1", this.getScoreSource(1));
		configSourceProps.setProperty("MatchWinner", this.getMatchWinnerSource());
		configSourceProps.setProperty("Meatball", this.getMeatballSource());
		configSourceProps.setProperty("Score2", this.getScoreSource(2));
		configSourceProps.setProperty("TimeOut1", this.getTimeOutSource(1));
		configSourceProps.setProperty("TimeOut2", this.getTimeOutSource(2));
		configSourceProps.setProperty("Reset1", this.getResetSource(1));
		configSourceProps.setProperty("Reset2", this.getResetSource(2));
		configSourceProps.setProperty("Warn1", this.getWarnSource(1));
		configSourceProps.setProperty("Warn2", this.getWarnSource(2));
		configSourceProps.setProperty("LastScored", this.getLastScoredSource());
		configSourceProps.setProperty("GameTime", this.getGameTimeSource());
		configSourceProps.setProperty("MatchTime", this.getMatchTimeSource());
		configSourceProps.setProperty("Stuffs1", this.getStuffsSource(1));
		configSourceProps.setProperty("Stuffs2", this.getStuffsSource(2));
		configSourceProps.setProperty("Breaks1", this.getBreaksSource(1));
		configSourceProps.setProperty("Breaks2", this.getBreaksSource(2));
		configSourceProps.setProperty("Team1PassAttempts", this.getPassAttemptsSource(1));
		configSourceProps.setProperty("Team1PassCompletes", this.getPassCompletesSource(1));
		configSourceProps.setProperty("Team2PassAttempts", this.getPassAttemptsSource(2));
		configSourceProps.setProperty("Team2PassCompletes", this.getPassCompletesSource(2));
		configSourceProps.setProperty("Team1ShotAttempts", this.getShotAttemptsSource(1));
		configSourceProps.setProperty("Team1ShotCompletes", this.getShotCompletesSource(1));
		configSourceProps.setProperty("Team2ShotAttempts", this.getShotAttemptsSource(2));
		configSourceProps.setProperty("Team2ShotCompletes", this.getShotCompletesSource(2));
		configSourceProps.setProperty("Team1ClearAttempts", this.getClearAttemptsSource(1));
		configSourceProps.setProperty("Team1ClearCompletes", this.getClearCompletesSource(1));
		configSourceProps.setProperty("Team2ClearAttempts", this.getClearAttemptsSource(2));
		configSourceProps.setProperty("Team2ClearCompletes", this.getClearCompletesSource(2));
		configSourceProps.setProperty("Team1PassPercent", this.getPassPercentSource(1));
		configSourceProps.setProperty("Team2PassPercent", this.getPassPercentSource(2));
		configSourceProps.setProperty("Team1ShotPercent", this.getShotPercentSource(1));
		configSourceProps.setProperty("Team2ShotPercent", this.getShotPercentSource(2));
		configSourceProps.setProperty("Team1ClearPercent", this.getClearPercentSource(1));
		configSourceProps.setProperty("Team2ClearPercent", this.getClearPercentSource(2));
		configSourceProps.setProperty("Team1TwoBarPassAttempts", this.getTwoBarPassAttemptsSource(1));
		configSourceProps.setProperty("Team1TwoBarPassCompletes", this.getTwoBarPassCompletesSource(1));
		configSourceProps.setProperty("Team2TwoBarPassAttempts", this.getTwoBarPassAttemptsSource(2));
		configSourceProps.setProperty("Team2TwoBarPassCompletes", this.getTwoBarPassCompletesSource(2));
		configSourceProps.setProperty("Team1TwoBarPassPercent", this.getTwoBarPassPercentSource(1));
		configSourceProps.setProperty("Team2TwoBarPassPercent", this.getTwoBarPassPercentSource(2));
		configSourceProps.setProperty("Team1Scoring", this.getScoringSource(1));
		configSourceProps.setProperty("Team2Scoring", this.getScoringSource(2));
		configSourceProps.setProperty("Team1ThreeBarScoring", this.getThreeBarScoringSource(1));
		configSourceProps.setProperty("Team2ThreeBarScoring", this.getThreeBarScoringSource(2));
		configSourceProps.setProperty("Team1FiveBarScoring", this.getFiveBarScoringSource(1));
		configSourceProps.setProperty("Team2FiveBarScoring", this.getFiveBarScoringSource(2));
		configSourceProps.setProperty("Team1TwoBarScoring", this.getTwoBarScoringSource(1));
		configSourceProps.setProperty("Team2TwoBarScoring", this.getTwoBarScoringSource(2));
		configSourceProps.setProperty("Team1ShotsOnGoal", this.getShotsOnGoalSource(1));
		configSourceProps.setProperty("Team2ShotsOnGoal", this.getShotsOnGoalSource(2));
		configSourceProps.setProperty("Side1Color", this.getSide1ColorSource());
		configSourceProps.setProperty("Side2Color", this.getSide2ColorSource());
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configSourceFileName))) {
			configSourceProps.store(outputStream, "FoosOBSPlus Source Settings");
		}
	}
	public void saveToFileNameConfig() throws IOException {
		//FileNames
		configFileNameProps.setProperty("TableFileName", this.getTableFileName());
		configFileNameProps.setProperty("Team1NameFileName", this.getTeamNameFileName(1));
		configFileNameProps.setProperty("Team1ForwardFileName", this.getTeamForwardFileName(1));
		configFileNameProps.setProperty("Team1GoalieFileName", this.getTeamGoalieFileName(1));
		configFileNameProps.setProperty("TournamentFileName", this.getTournamentFileName());
		configFileNameProps.setProperty("Team2NameFileName", this.getTeamNameFileName(2));
		configFileNameProps.setProperty("Team2ForwardFileName", this.getTeamForwardFileName(2));
		configFileNameProps.setProperty("Team2GoalieFileName", this.getTeamGoalieFileName(2));
		configFileNameProps.setProperty("EventFileName", this.getEventFileName());
		configFileNameProps.setProperty("GameCount1FileName", this.getGameCountFileName(1));
		configFileNameProps.setProperty("TimeRemainingFileName", this.getTimeRemainingFileName());
		configFileNameProps.setProperty("GameCount2FileName", this.getGameCountFileName(2));
		configFileNameProps.setProperty("TimerInUseFileName", this.getTimerInUseFileName());
		configFileNameProps.setProperty("Score1FileName", this.getScoreFileName(1));
		configFileNameProps.setProperty("MatchWinnerFileName", this.getMatchWinnerFileName());
		configFileNameProps.setProperty("MeatballFileName", this.getMeatballFileName());
		configFileNameProps.setProperty("Score2FileName", this.getScoreFileName(2));
		configFileNameProps.setProperty("TimeOut1FileName", this.getTimeOutFileName(1));
		configFileNameProps.setProperty("TimeOut2FileName", this.getTimeOutFileName(2));
		configFileNameProps.setProperty("Reset1FileName", this.getResetFileName(1));
		configFileNameProps.setProperty("Reset2FileName", this.getResetFileName(2));
		configFileNameProps.setProperty("Warn1FileName", this.getWarnFileName(1));
		configFileNameProps.setProperty("Warn2FileName", this.getWarnFileName(2));
		configFileNameProps.setProperty("LastScoredFileName", this.getLastScoredFileName());
		configFileNameProps.setProperty("GameTimeFileName", this.getGameTimeFileName());
		configFileNameProps.setProperty("MatchTimeFileName", this.getMatchTimeFileName());
		configFileNameProps.setProperty("Stuffs1FIleName", this.getStuffsFileName(1));
		configFileNameProps.setProperty("Stuffs2FIleName", this.getStuffsFileName(2));
		configFileNameProps.setProperty("Breaks1FIleName", this.getBreaksFileName(1));
		configFileNameProps.setProperty("Breaks2FIleName", this.getBreaksFileName(2));
		configFileNameProps.setProperty("Team1PassAttemptsFileName", this.getPassAttemptsFileName(1));
		configFileNameProps.setProperty("Team1PassCompletesFileName", this.getPassCompletesFileName(1));
		configFileNameProps.setProperty("Team2PassAttemptsFileName", this.getPassAttemptsFileName(2));
		configFileNameProps.setProperty("Team2PassCompletesFileName", this.getPassCompletesFileName(2));
		configFileNameProps.setProperty("Team1ShotAttemptsFileName", this.getShotAttemptsFileName(1));
		configFileNameProps.setProperty("Team1ShotCompletesFileName", this.getShotCompletesFileName(1));
		configFileNameProps.setProperty("Team2ShotAttemptsFileName", this.getShotAttemptsFileName(2));
		configFileNameProps.setProperty("Team2ShotCompletesFileName", this.getShotCompletesFileName(2));
		configFileNameProps.setProperty("Team1ClearAttemptsFileName", this.getClearAttemptsFileName(1));
		configFileNameProps.setProperty("Team1ClearCompletesFileName", this.getClearCompletesFileName(1));
		configFileNameProps.setProperty("Team2ClearAttemptsFileName", this.getClearAttemptsFileName(2));
		configFileNameProps.setProperty("Team2ClearCompletesFileName", this.getClearCompletesFileName(2));
		configFileNameProps.setProperty("Team1PassPercentFileName", this.getPassPercentFileName(1));
		configFileNameProps.setProperty("Team2PassPercentFileName", this.getPassPercentFileName(2));
		configFileNameProps.setProperty("Team1ShotPercentFileName", this.getShotPercentFileName(1));
		configFileNameProps.setProperty("Team2ShotPercentFileName", this.getShotPercentFileName(2));
		configFileNameProps.setProperty("Team1ClearPercentFileName", this.getClearPercentFileName(1));
		configFileNameProps.setProperty("Team2ClearPercentFileName", this.getClearPercentFileName(2));
		configFileNameProps.setProperty("Team1TwoBarPassAttemptsFileName", this.getTwoBarPassAttemptsFileName(1));
		configFileNameProps.setProperty("Team1TwoBarPassCompletesFileName", this.getTwoBarPassCompletesFileName(1));
		configFileNameProps.setProperty("Team2TwoBarPassAttemptsFileName", this.getTwoBarPassAttemptsFileName(2));
		configFileNameProps.setProperty("Team2TwoBarPassCompletesFileName", this.getTwoBarPassCompletesFileName(2));
		configFileNameProps.setProperty("Team1TwoBarPassPercentFileName", this.getTwoBarPassPercentFileName(1));
		configFileNameProps.setProperty("Team2TwoBarPassPercentFileName", this.getTwoBarPassPercentFileName(2));
		configFileNameProps.setProperty("Team1ScoringFileName", this.getScoringFileName(1));
		configFileNameProps.setProperty("Team2ScoringFileName", this.getScoringFileName(2));
		configFileNameProps.setProperty("Team1ThreeBarScoringFileName", this.getThreeBarScoringFileName(1));
		configFileNameProps.setProperty("Team2ThreeBarScoringFileName", this.getThreeBarScoringFileName(2));
		configFileNameProps.setProperty("Team1FiveBarScoringFileName", this.getFiveBarScoringFileName(1));
		configFileNameProps.setProperty("Team2FiveBarScoringFileName", this.getFiveBarScoringFileName(2));
		configFileNameProps.setProperty("Team1TwoBarScoringFileName", this.getTwoBarScoringFileName(1));
		configFileNameProps.setProperty("Team2TwoBarScoringFileName", this.getTwoBarScoringFileName(2));
		configFileNameProps.setProperty("Team1ShotsOnGoalFileName", this.getShotsOnGoalFileName(1));
		configFileNameProps.setProperty("Team2ShotsOnGoalFileName", this.getShotsOnGoalFileName(2));
		configFileNameProps.setProperty("Side1ColorFileName", this.getSide1ColorFileName());
		configFileNameProps.setProperty("Side2ColorFileName", this.getSide2ColorFileName());
		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configFileNameFileName))) {
			configFileNameProps.store(outputStream, "FoosOBSPlus File Name Settings");
		}
	}
	public void saveToHotKeyConfig() throws IOException {
		//HotKeys
		configHotKeyProps.setProperty("StartMatchHotKey", this.getStartMatchHotKey());
		configHotKeyProps.setProperty("PauseMatchHotKey", this.getPauseMatchHotKey());
		configHotKeyProps.setProperty("StartGameHotKey", this.getStartGameHotKey());
		configHotKeyProps.setProperty("Team1ClearHotKey", this.getTeam1ClearHotKey());
		configHotKeyProps.setProperty("Team1SwitchPositionsHotKey", this.getTeam1SwitchPositionsHotKey());
		configHotKeyProps.setProperty("Team2ClearHotKey", this.getTeam2ClearHotKey());
		configHotKeyProps.setProperty("Team2SwitchPositionsHotKey", this.getTeam2SwitchPositionsHotKey());
		configHotKeyProps.setProperty("SwitchTeamsHotKey", this.getSwitchTeamsHotKey());
		configHotKeyProps.setProperty("SwitchPlayer1HotKey", this.getSwitchPlayer1HotKey());
		configHotKeyProps.setProperty("SwitchPlayer2HotKey", this.getSwitchPlayer2HotKey());
		configHotKeyProps.setProperty("GameCount1MinusHotKey", this.getGameCount1MinusHotKey());
		configHotKeyProps.setProperty("GameCount1PlusHotKey", this.getGameCount1PlusHotKey());
		configHotKeyProps.setProperty("GameCount2MinusHotKey", this.getGameCount2MinusHotKey());
		configHotKeyProps.setProperty("GameCount2PlusHotKey", this.getGameCount2PlusHotKey());
		configHotKeyProps.setProperty("SwitchGameCountsHotKey", this.getSwitchGameCountsHotKey());
		configHotKeyProps.setProperty("Score1MinusHotKey", this.getScore1MinusHotKey());
		configHotKeyProps.setProperty("Score1PlusHotKey", this.getScore1PlusHotKey());
		configHotKeyProps.setProperty("Score2MinusHotKey", this.getScore2MinusHotKey());
		configHotKeyProps.setProperty("Score2PlusHotKey", this.getScore2PlusHotKey());
		configHotKeyProps.setProperty("SwitchScoresHotKey", this.getSwitchScoresHotKey());
		configHotKeyProps.setProperty("TimeOut1MinusHotKey", this.getTimeOut1MinusHotKey());
		configHotKeyProps.setProperty("TimeOut1PlusHotKey", this.getTimeOut1PlusHotKey());
		configHotKeyProps.setProperty("TimeOut2MinusHotKey", this.getTimeOut2MinusHotKey());
		configHotKeyProps.setProperty("TimeOut2PlusHotKey", this.getTimeOut2PlusHotKey());
		configHotKeyProps.setProperty("SwitchTimeOutsHotKey", this.getSwitchTimeOutsHotKey());
		configHotKeyProps.setProperty("Reset1HotKey", this.getReset1HotKey());
		configHotKeyProps.setProperty("Reset2HotKey", this.getReset2HotKey());
		configHotKeyProps.setProperty("Warn1HotKey", this.getWarn1HotKey());
		configHotKeyProps.setProperty("Warn2HotKey", this.getWarn2HotKey());
		configHotKeyProps.setProperty("SwitchResetWarnsHotKey", this.getSwitchResetWarnsHotKey());
		configHotKeyProps.setProperty("SwitchSidesHotKey", this.getSwitchSidesHotKey());
		configHotKeyProps.setProperty("ResetNamesHotKey", this.getResetNamesHotKey());
		configHotKeyProps.setProperty("ResetGameCountsHotKey", this.getResetGameCountsHotKey());
		configHotKeyProps.setProperty("ResetScoresHotKey", this.getResetScoresHotKey());
		configHotKeyProps.setProperty("ResetTimeOutsHotKey", this.getResetTimeOutsHotKey());
		configHotKeyProps.setProperty("ResetResetWarnHotKey", this.getResetResetWarnHotKey());
		configHotKeyProps.setProperty("ResetAllHotKey", this.getResetAllHotKey());
		configHotKeyProps.setProperty("ClearAllHotKey", this.getClearAllHotKey());
		configHotKeyProps.setProperty("ShotTimerHotKey", this.getShotTimerHotKey());
		configHotKeyProps.setProperty("PassTimerHotKey", this.getPassTimerHotKey());
		configHotKeyProps.setProperty("TimeOutTimerHotKey", this.getTimeOutTimerHotKey());
		configHotKeyProps.setProperty("GameTimerHotKey", this.getGameTimerHotKey());
		configHotKeyProps.setProperty("RecallTimerHotKey", this.getRecallTimerHotKey());
		configHotKeyProps.setProperty("ResetTimersHotKey", this.getResetTimersHotKey());
		configHotKeyProps.setProperty("UndoHotKey", this.getUndoHotKey());
		configHotKeyProps.setProperty("RedoHotKey", this.getRedoHotKey());

		try(OutputStream outputStream = Files.newOutputStream(Paths.get(configHotKeyFileName))) {
			configHotKeyProps.store(outputStream, "FoosOBSPlus Hot Key Settings");
		}
	}
}
