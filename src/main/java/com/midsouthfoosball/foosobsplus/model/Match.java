/**
Copyright © 2020-2025 Hugh Garner
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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import java.io.IOException;

public class Match implements Serializable {
	private static final long serialVersionUID = -3958726389588837391L;
	private int winState = 0;
	private transient Team team1;
	private transient Team team2;
	private transient Team team3;
	private final transient OBSInterface obsInterface;
	private transient String startTime;
        private final transient Map<Integer, Team> teamsMap = new HashMap<>();
	private int lastScored; // team number of the last team to score in this match
	private boolean isMatchPaused;
	private boolean isGamePaused;
	private int currentGameNumber = 1;
	private boolean matchWon = false;
	private int matchWinner = 0;
	private int maxGameCount = 5;
	private String matchId = "";
	private boolean isMatchStarted = false;
	private final int maxGamesToShow = 12;
	private int gameWinners[];
	private String[] scoresTeam1;
	private String[] scoresTeam2;
	private String[] scoresTeam3;
	private String[] times;
	private static final transient Logger logger = LoggerFactory.getLogger(Match.class);
	private final StringBuilder gameResults = new StringBuilder();
	private final List<MatchObserver> observers = new ArrayList<>();
	private static final String ON = "1";
	public Match(OBSInterface obsInterface, Team team1, Team team2, Team team3) {
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.obsInterface = obsInterface;
		this.maxGameCount=Settings.getMaxGameNumber();
		this.gameWinners  = new int[maxGamesToShow];
		this.scoresTeam1 = new String[maxGamesToShow];
		this.scoresTeam2 = new String[maxGamesToShow];
		this.scoresTeam3 = new String[maxGamesToShow];
		this.times = new String[maxGamesToShow];
		for(int i=0;i < maxGamesToShow; i++) {
			this.gameWinners[i] = 0;
			this.scoresTeam1[i] = "0";
			this.scoresTeam2[i] = "0";
			this.scoresTeam3[i] = "0";
			this.times[i] = "00:00:00";
		}
		teamsMap.put(1, team1);
		teamsMap.put(2, team2);
		teamsMap.put(3, team3);
	}
	public void addObserver(MatchObserver observer) {
		observers.add(observer);
	}
	private void notifyObservers() {
		for (MatchObserver observer : observers) {
			observer.onMeatball();
		}
	}
	public void startMatch(String matchId) {
		setMatchId(matchId);
		setStartTime(getCurrentTime());
		setMatchPaused(false);
		setGamePaused(false);
		setMatchWon(false);
		setMatchWinner(0);
		setCurrentGameNumber(0);
		setLastScored(0);
		clearScores();
		clearWinState();
		clearTimes();
		clearGameWinners();
		clearMatchWinner();
		clearMeatball();
		startGame();
	}
	public StringBuilder getGameResults() {
		return gameResults;
	}
	public StringBuilder clearGameResults() {
		gameResults.setLength(0);
		return gameResults;
	}
	public StringBuilder addGameResults(String result) {
		gameResults.append(result).append("\r\n");
		return gameResults;
	}
	public int getWinState() {
		return winState;
	}
	public void clearWinState() {
		winState = 0;
	}
	public String createMatchId() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd.HH:mm:ss.SSS");
		return formatter.format(date);
	}
	public void startGame() {
		setGamePaused(false);
		setMatchPaused(false);
		if (currentGameNumber==0) increaseCurrentGameNumber();
	}
	public void resetMatch() {
		setMatchWon(false);
		setMatchWinner(0);
		setCurrentGameNumber(1);
		resetGameCounts();
		setLastScored(0);
		clearScores();
		clearWinState();
		clearTimes();
		clearGameWinners();
		clearMatchWinner();
		clearMeatball();
	}
	public void resetGameCounts() {
		setMatchWon(false);
		setMatchWinner(0);
		setCurrentGameNumber(1);
		clearScoresAfterFirst();
		clearTimesAfterFirst();
		clearGameWinners();
		clearMatchWinner();
		clearMeatball();
	}
	public void increaseCurrentGameNumber() {
		if(/*!matchWon &&*/ (currentGameNumber==0 || checkForGameWinOnly())) {
			currentGameNumber++;
			if (currentGameNumber > maxGameCount) currentGameNumber = maxGameCount;
		}
	}
	public void syncCurrentGameNumber() {
		currentGameNumber = team1.getGameCount() + team2.getGameCount() + team3.getGameCount() + 1;
		if (currentGameNumber > maxGameCount) currentGameNumber = maxGameCount;
	}
	public void syncGameScores() {
		scoresTeam1[currentGameNumber-1] = Integer.toString(team1.getScore());
		scoresTeam2[currentGameNumber-1] = Integer.toString(team2.getScore());
		scoresTeam3[currentGameNumber-1] = Integer.toString(team3.getScore());
	}
	public int getMaxPossibleGames() {
		return maxGameCount;
	}
	public void setMaxPossibleGames(int maxPossibleGames) {
		if (maxPossibleGames<=0) {
			maxPossibleGames = Settings.getMaxGameNumber();
		}
		this.maxGameCount = maxPossibleGames;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public boolean isMatchStarted() {
		return isMatchStarted;
	}
	public void setMatchStarted(boolean isMatchStarted) {
		this.isMatchStarted = isMatchStarted;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public void setTeam3(Team team3) {
		this.team3 = team3;
	}
	private void clearScores() {
		for (int i = 0; i < maxGameCount; i++) {
			scoresTeam1[i] = "0";
			scoresTeam2[i] = "0";
			scoresTeam3[i] = "0";
		}
	}
	private void clearScoresAfterFirst() {
		for (int i =1; i < maxGameCount; i++) {
			scoresTeam1[i] = "0";
			scoresTeam2[i] = "0";
			scoresTeam3[i] = "0";
		}
	}
	private void clearTimes() {
		for (int i = 0; i < maxGameCount; i++) {
			times[i] = "00:00:00";
		}
	}
	private void clearTimesAfterFirst() {
		for (int i = 1; i < maxGameCount; i++) {
			times[i] = "00:00:00";
		}
	}
	public boolean getMatchWon() {
		return matchWon;
	}
	private void setMatchWon(boolean matchWon) {
		this.matchWon = matchWon;
	}
	public int getMatchWinner() {
		return matchWinner;
	}
	private void setMatchWinner(int matchWinner) {
		this.matchWinner = matchWinner;
	}
	public int[] getGameWinners() {
		return gameWinners;
	}
	public void setGameWinners(int[] gameWinners) {
		this.gameWinners = gameWinners;
	}
	public String[] getScoresTeam1() {
		return scoresTeam1;
	}
	public String[] getScoresTeam2() {
		return scoresTeam2;
	}
	public String[] getScoresTeam3() {
		return scoresTeam3;
	}
	public String[] getTimes() {
		return times;
	}
	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}
	public int getCurrentGameNumber() {
		return currentGameNumber;
	}
	public void setCurrentGameNumber(int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
	}
	public void setLastScored(int lastScored) {
		this.lastScored = lastScored;
		writeLastScored();
	}
	public void setLastScored(String lastScoredtxt) {
		if (lastScoredtxt.equals(Settings.getLastScoredStrings()[2])) {
			this.lastScored = 2;
		} else {
			if (lastScoredtxt.equals(Settings.getLastScoredStrings()[1])) {
				this.lastScored = 1;
			} else {
				this.lastScored = 0;
			}
		}
	}
	public int getLastScored() {
		return lastScored;
	}
	public void switchSides() {
		switchLastScored();
		String temp[] = scoresTeam1;
		scoresTeam1 = scoresTeam2;
		scoresTeam2 = temp;
	}
	public void switchLastScored() {
		switch (lastScored) {
		case 1:
			lastScored = 2;
			break;
		case 2:
			lastScored = 1;
			break;
		default:
			break;
		}
		setLastScored(lastScored);
	}
	public boolean isMatchPaused() {
		return isMatchPaused;
	}
	public void setMatchPaused(boolean isMatchPaused) {
		this.isMatchPaused = isMatchPaused;
	}
	public boolean isGamePaused() {
		return isGamePaused;
	}
	public void setGamePaused(boolean isGamePaused) {
		this.isGamePaused = isGamePaused;
	}
	public void setScoresTeam1(String[] scoresTeam1) {
		this.scoresTeam1 = scoresTeam1;
	}
	public void setScoresTeam2(String[] scoresTeam2) {
		this.scoresTeam2 = scoresTeam2;
	}
	public void setTimes(String[] times) {
		this.times = times;
	}
	public void setScoresTeam3(String[] scoresTeam3) {
		this.scoresTeam3 = scoresTeam3;
	}
	public void setGameTime(String time) {
		times[currentGameNumber-1] = time;
	}
	public void clearAll() {
		lastScored = 0;
		writeLastScored();
		clearMatchWinner();
		clearMeatball();
	}
	public void setCurrentScoreForTeam(int teamNbr, int score) {
            switch (teamNbr) {
                case 1:
                    setCurrentScoreTeam1(score);
                    break;
                case 2:
                    setCurrentScoreTeam2(score);
                    break;
                default:
                    setCurrentScoreTeam3(score);
                    break;
            }
	}
	public void setCurrentScoreTeam1 (int score) {
		scoresTeam1[currentGameNumber-1] = Integer.toString(score);
	}
	public void setCurrentScoreTeam2 (int score) {
		scoresTeam2[currentGameNumber-1] = Integer.toString(score);
	}
	public void setCurrentScoreTeam3 (int score) {
		scoresTeam3[currentGameNumber-1] = Integer.toString(score);
	}
	private void setCurrentTime (String gameTime) {
		times[currentGameNumber-1] = gameTime;
	}
	public void decrementScore(int teamNbr) {
		Team team = teamsMap.getOrDefault(teamNbr, null);
		if (team != null) {
			team.decrementScore();
			setCurrentScoreForTeam(teamNbr, team.getScore());
		}
		if (team1.getScore() > 0 && team2.getScore() == 0) {
			setLastScored(1);
		} else {
			if (team2.getScore() > 0 && team1.getScore() == 0) {
				setLastScored(2);
			} else {
				if (team2.getScore() == 0 && team1.getScore() == 0) {
					setLastScored(0);
				}
			}
		}
	}
	public int incrementScore(int teamNbr, String gameTime) {
		// return 0 if no winner
		// return 1 if game won
		// return 2 if match won
		if (winState>=1) {
			resetScores();
			resetTimeOuts();
			resetResetWarn();
			int maximumGameCount = Settings.getMaxGameNumber();
			if (currentGameNumber > maximumGameCount) currentGameNumber = maximumGameCount;
			winState = 0;
		}
            switch (teamNbr) {
                case 1:
                    {
                        team1.incrementScore();
                        setLastScored(1);
                        setCurrentScoreTeam1(team1.getScore());
                        int whoWon = checkForGameWin(team1.getScore(), team2.getScore(), team3.getScore());
                        if(whoWon == 1 || whoWon == 3) {
                            gameWinners[currentGameNumber-1]=1;
                            matchWon = incrementGameCount(team1);
                            if(matchWon) matchWinner=1;
                            winState = 1;
                        } else {
                            if(whoWon == 2) {
                                gameWinners[currentGameNumber-1]=2;
                                matchWon = incrementGameCount(team2);
                                if(matchWon) matchWinner=2;
                                winState = 1;
                            }
                        }
                        break;
                    }
                case 2:
                    {
                        team2.incrementScore();
                        setLastScored(2);
                        setCurrentScoreTeam2(team2.getScore());
                        int whoWon = checkForGameWin(team2.getScore(), team1.getScore(), team3.getScore());
                        if (whoWon == 1 || whoWon == 3) {
                            gameWinners[currentGameNumber-1]=2;
                            matchWon = incrementGameCount(team2);
                            if(matchWon) matchWinner=2;
                            winState = 1;
                        } else {
                            if(whoWon == 2) {
                                gameWinners[currentGameNumber-1]=1;
                                matchWon = incrementGameCount(team1);
                                if(matchWon) matchWinner=1;
                                winState = 1;
                            }
                        }
                        break;
                    }
                default:
                    {
                        team3.incrementScore();
                        setLastScored(0);
                        setCurrentScoreTeam3(team3.getScore());
                        int whoWon = checkForGameWin(team3.getScore(), team1.getScore(), team2.getScore());
                        if(whoWon == 1 || whoWon == 3) {
                            gameWinners[currentGameNumber-1]=1;
                            matchWon = incrementGameCount(team3);
                            if(matchWon) matchWinner=3;
                            winState = 1;
                        } else {
                            if(whoWon == 2) {
                                gameWinners[currentGameNumber-1]=2;
                                matchWon = incrementGameCount(team1);
                                if(matchWon) matchWinner=1;
                                winState = 1;
                            }
                        }
                        break;
                    }
            }
		if (winState>0) {
//			String result = "[" + getCurrentTime() + "] " + team1.getForwardName() + "/" + team1.getGoalieName() + " " + team1.getScore() + " vs " + team2.getForwardName()
//				+ "/" + team2.getGoalieName() + " " + team2.getScore() + " (" + getTimes()[currentGameNumber-1] + ")"; 
//			writeData(Settings.getSourceParameter("GameResults"), addGameResults(result).toString());
			setCurrentTime(gameTime);
		}
		if(matchWon) {
			winState = 2;
		} else {
			clearMatchWinner();
		}
		checkMeatball();
		return winState;
	}
	public void updateGameResults(StringBuilder results) {
		writeData(Settings.getSourceParameter("GameResults"), results.toString());
	}
	public void updateGameResults() {
		updateGameResults(getGameResults());
	}
	private int checkForGameWin(int score1, int score2, int score3) {
		int whoWon = 0;// rack mode returns 0, 1 or 2 (1 if scoring team won or 2 if other team won). non rack mode returns 0, 3 (3=team who called checkForGameWin won)  .
		int pointsToWin = Integer.parseInt(Settings.getControlParameter("PointsToWin"));
		int maxWin = Integer.parseInt(Settings.getControlParameter("MaxWin"));
		int winBy = Integer.parseInt(Settings.getControlParameter("WinBy"));
		int winByFinalOnly = Integer.parseInt(Settings.getControlParameter("WinByFinalOnly"));
		int ballsInRack = Integer.parseInt(Settings.getControlParameter("BallsInRack"));
		boolean rackMode = Settings.getControlParameter("RackMode").equals(ON);
		if (Settings.getControlParameter("AutoIncrementGame").equals(ON)) {
			if (rackMode) {
				if (score1 + score2 >= ballsInRack) {
					if(score1 > score2) {
						whoWon=1;
					} else {
						whoWon=2;
					}
					clearMeatball();
				} else {
					clearMatchWinner();
				}
			} else {
				if (score1 >= maxWin || 
						((score1 >= pointsToWin) && 
								((score1 >= score2 + winBy) || ((winByFinalOnly==1) && currentGameNumber != maxGameCount)) )) 
				{
					clearMeatball();
					whoWon = 3;
				} else {
					clearMatchWinner();
				}
			}
		}
		return whoWon;
	}
	public boolean incrementGameCount(Team team) {
		team.incrementGameCount();
		this.matchWon = checkForMatchWin(team);
		String name = null;
		if(matchWon) {
			clearMeatball();
			if (Settings.getControlParameter("AnnounceWinner").equals("1")) {
				boolean skipMatchWinnerDisplay = false;
				if (team.getTeamName() == null || team.getTeamName().isEmpty()) {
					if (team.getGoalieName() == null || team.getGoalieName().isEmpty()) {
						if (team.getForwardName() == null || team.getForwardName().isEmpty()) {
							skipMatchWinnerDisplay = true;
						} else {
							name = team.getForwardName();
						}
					} else {
						if (team.getForwardName() == null || team.getForwardName().isEmpty()) {
							name = team.getGoalieName();
						} else {
							name = team.getForwardName() + ", " + team.getGoalieName();
						}
					}
				} else {
					name = team.getTeamName();
				}
				if (!skipMatchWinnerDisplay) {
					writeMatchWinner(Settings.getControlParameter("WinnerPrefix") + name + Settings.getControlParameter("WinnerSuffix"));
				}
			}
//			resetScores();
//			resetGameCounts();
		} else {
			clearMatchWinner();
		}
		return matchWon;
	}
	public boolean incrementMatchCount(int teamNbr) {
		Team team = teamsMap.getOrDefault(teamNbr, null);
		if (team != null) {
			return incrementMatchCount(team);
		}
		return false;
	}
	public boolean incrementMatchCount(Team team) {
		team.incrementMatchCount();
// if any kingseat is set and they won, then set result
// if kingseat is set and other team won and has more than 1 match, then set result
// if kingseat not set, then result is false
		return false;
	}
	private void checkMeatball() {
		int points1 = team1.getScore();
		int points2 = team2.getScore();
		int points3 = team3.getScore();
		int gameCount1 = team1.getGameCount();
		int gameCount2 = team2.getGameCount();
		int gameCount3 = team3.getGameCount();
		Boolean isCutthroat = Settings.getControlParameter("CutThroatMode").equals(ON);
		int winBy = Integer.parseInt(Settings.getControlParameter("WinBy"));
		int pointsToWin = Integer.parseInt(Settings.getControlParameter("PointsToWin"));
		boolean isPotentialMeatball = false;
		if (Settings.getControlParameter("AnnounceMeatball").equals(ON)) {
			if (isCutthroat) {
				if (points1 == points2 && points1 == points3 && gameCount1 == gameCount2 && gameCount1 == gameCount3 && winBy < 2) {
					isPotentialMeatball = true;
				}
			} else {
				if (points1 == points2 && gameCount1 == gameCount2 && winBy < 2) {
					isPotentialMeatball = true;
				}
			}
			if (isPotentialMeatball) {			
				int meatballPoint = pointsToWin - 1;
				if (points1 == meatballPoint) {
					if (gameCount1 == pointsToWin - 1) {
						writeMeatball();
						notifyObservers();
						return;
					}
				}
			}
		}
		clearMeatball();
	}
	private boolean checkForGameWinOnly() {
		boolean isGameWon = false;
		if (currentGameNumber!=0) {
			int pointsToWin = Integer.parseInt(Settings.getControlParameter("PointsToWin"));
			int winBy = Integer.parseInt(Settings.getControlParameter("WinBy"));
			int maxWin = Integer.parseInt(Settings.getControlParameter("MaxWin"));
			int winByFinalOnly = Integer.parseInt(Settings.getControlParameter("WinByFinalOnly"));
			int ballsInRack = Integer.parseInt(Settings.getControlParameter("BallsInRack"));
			boolean rackMode = Settings.getControlParameter("RackMode").equals(ON);
			int score1 = Integer.parseInt(scoresTeam1[currentGameNumber-1]);
			int score2 = Integer.parseInt(scoresTeam2[currentGameNumber-1]);
			int score3 = Integer.parseInt(scoresTeam3[currentGameNumber-1]);
			if (score2 > score1 && score2 > score3) {
				score1 = score2;
				score2 = Integer.parseInt(scoresTeam1[currentGameNumber-1]);
			} else if (score3 > score1 && score3 > score2) {
				score1 = score3;
				score3 = Integer.parseInt(scoresTeam1[currentGameNumber-1]);
			}
			if (rackMode) {
				if (score1 + score2 >= ballsInRack) {
					isGameWon = true;
				}
			} else {
				if (score1 >= maxWin || 
						((score1 >= pointsToWin) && ((score1 >= score2 + winBy) || ((winByFinalOnly==1) && currentGameNumber != maxGameCount)) 
								&& ((score1 >= score3 + winBy) || ((winByFinalOnly==1) && currentGameNumber != maxGameCount)))) 
				{
					isGameWon = true;
				}
			}
		}
		return isGameWon;
	}
	private boolean checkForMatchWin(Team team) {
		this.matchWon = false;
		if(team.getGameCount()==Integer.parseInt(Settings.getControlParameter("GamesToWin"))) {
			matchWon=true;
		}
		return matchWon;
	}
	private void resetScores() {
		team1.setScore(0);
		team2.setScore(0);
		team3.setScore(0);
	}
	private void resetTimeOuts() {
		team1.resetTimeOuts();
		team2.resetTimeOuts();
		team3.resetTimeOuts();
	}
	private void resetResetWarn() {
		team1.setReset(false);
		team1.setWarn(false);
		team2.setReset(false);
		team2.setWarn(false);
		team3.setReset(false);
		team3.setWarn(false);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	private void writeLastScored() {
		writeData(Settings.getSourceParameter("LastScored"), Settings.getLastScoredStrings()[lastScored]);
	}
	private void writeMatchWinner(String theContents) {
		writeData(Settings.getSourceParameter("MatchWinner"), theContents);
	}
	private void clearMatchWinner() {
		writeData(Settings.getSourceParameter("MatchWinner"), "");
	}
	private void writeMeatball() {
		writeData(Settings.getSourceParameter("Meatball"), Settings.getControlParameter("Meatball"));
	}
	private void clearMeatball() {
		writeData(Settings.getSourceParameter("Meatball"), "");
	}
    private void writeData(String source, String data) {
		obsInterface.writeData(source, data, "Match", Settings.getShowParsed());
    }
    public void restoreState(byte[] serializedObject) {

		Match tempMatch = null;
		try {
			byte b[] = serializedObject;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			tempMatch = (Match) si.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.toString());
		}
		this.setLastScored(tempMatch.getLastScored());
		this.setMatchPaused(tempMatch.isMatchPaused());
		this.setGamePaused(tempMatch.isGamePaused());
		this.setCurrentGameNumber(tempMatch.getCurrentGameNumber());
		this.setGameWinners(tempMatch.getGameWinners());
		this.setMatchWon(tempMatch.getMatchWon());
		this.setMatchWinner(tempMatch.getMatchWinner());
		this.setMaxPossibleGames(tempMatch.getMaxPossibleGames());
		this.setMatchId(tempMatch.getMatchId());
		this.setScoresTeam1(tempMatch.getScoresTeam1());
		this.setScoresTeam2(tempMatch.getScoresTeam2());
		this.setScoresTeam3(tempMatch.getScoresTeam3());
		this.setTimes(tempMatch.getTimes());
	}
	public void clearGameWinners() {
		for(int i=0; i<gameWinners.length;i++) {
			gameWinners[i] = 0;
		}
	}
	public void clearGameWinnersAfterFirst() {
		for(int i=1; i<gameWinners.length;i++) {
			gameWinners[i] = 0;
		}
	}
}
