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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class Match implements Serializable {
	
	private static final long serialVersionUID = -3958726389588837391L;
	private transient Team team1;
	private transient Team team2;
	private transient Settings settings;
	private transient OBSInterface obsInterface;
	private int lastScored; // team number of the last team to score in this match
	private boolean isMatchPaused;
	private boolean isGamePaused;
	private transient String startTime;
	private int currentGame = 1;
	private boolean matchWon = false;
	private int matchWinner = 0;
	private String[] scoresTeam1 = {"0","0","0","0","0"};
	private String[] scoresTeam2 = {"0","0","0","0","0"};
	private String[] times = {"00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"};
//	private transient String endTime;
//	private transient int elapsedTime;
//	private transient int[] points;
//	private transient int[] threeBarPoints;
//	private transient int[] fiveBarPoints;
//	private transient int[] twoBarPoints;
//	private transient int[] shotsOnGoal;
//	private transient int[] breaks;
//	private transient int[] stuffs;
//	private transient Game[] games;

	public Match(OBSInterface obsInterface, Settings settings, Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.settings = settings;
		this.obsInterface = obsInterface;
	}
	public void startMatch() {
		setStartTime(getCurrentTime());
		setMatchPaused(false);
		setMatchWon(false);
		setMatchWinner(0);
		setCurrentGame(1);
		setLastScored(0);
		clearScores();
		clearTimes();
	}
	public void startGame() {
		setGamePaused(false);
	}
	private void clearScores() {
		for (int i = 0; i < 5; i++) {
			scoresTeam1[i] = "0";
			scoresTeam2[i] = "0";
		}
	}
	private void clearTimes() {
		for (int i = 0; i < 5; i++) {
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
	public String[] getScoresTeam1() {
		return scoresTeam1;
	}
	public String[] getScoresTeam2() {
		return scoresTeam2;
	}
	public String[] getTimes() {
		return times;
	}
	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}
	public int getCurrentGame() {
		return currentGame;
	}
	public void setCurrentGame(int currentGame) {
		this.currentGame = currentGame;
	}
	public void setLastScored(int lastScored) {
		this.lastScored = lastScored;
		writeLastScored();
	}
	public void setLastScored(String lastScoredtxt) {
		if (lastScoredtxt.equals(settings.getLastScoredStrings()[2])) {
			lastScored = 2;
		} else {
			if (lastScoredtxt.equals(settings.getLastScoredStrings()[1])) {
				lastScored = 1;
			} else {
				lastScored = 0;
			}
		}
	}
	public int getLastScored() {
		return lastScored;
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
		writeLastScored();
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
	public void clearAll() {
		lastScored = 0;
		writeLastScored();
		clearMatchWinner();
		clearMeatball();
	}
	private void setCurrentScoreTeam1 (int score) {
		scoresTeam1[currentGame-1] = Integer.toString(score);
	}
	private void setCurrentScoreTeam2 (int score) {
		scoresTeam2[currentGame-1] = Integer.toString(score);
	}
	private void setCurrentTime (String gameTime) {
		times[currentGame-1] = gameTime;
	}
	public void decrementScore(int teamNbr) {
		if(teamNbr==1) {
			team1.decrementScore();
			setCurrentScoreTeam1(team1.getScore());
		} else {
			team2.decrementScore();
			setCurrentScoreTeam2(team2.getScore());
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
		int winState = 0;
		if(teamNbr==1) {
			team1.incrementScore();
			setLastScored(1);
			setCurrentScoreTeam1(team1.getScore());
			if(checkForWin(team1.getScore(), team2.getScore())) {
				matchWon = incrementGameCount(team1);
				if(matchWon) matchWinner=1;
				resetScores();
				resetTimeOuts();
				resetResetWarn();
				winState = 1;
			}
		} else {
			team2.incrementScore();
			setLastScored(2);
			setCurrentScoreTeam2(team2.getScore());
			if(checkForWin(team2.getScore(), team1.getScore())) {
				matchWon = incrementGameCount(team2);
				if(matchWon) matchWinner=2;
				resetScores();
				resetTimeOuts();
				resetResetWarn();
				winState = 1;
			}
		}
		if (winState>0) {
			setCurrentTime(gameTime);
			if (!matchWon) {
				currentGame++;
				int maxGameCount = settings.getGamesToWin() * 2 - 1;
				if (currentGame > maxGameCount) currentGame = maxGameCount;
			}
		}

		if(matchWon) {
			winState = 2;
		} else {
			clearMatchWinner();
		};
		checkMeatball();
		return winState;
	}
	public boolean incrementGameCount(Team team) {
		team.incrementGameCount();
		boolean matchWon = checkForMatchWin(team);
		if(matchWon) {
			clearMeatball();
			if (settings.getAnnounceWinner()==1) {
				writeMatchWinner(settings.getWinnerPrefix() + team.getTeamName() + settings.getWinnerSuffix());
			}
			resetScores();
//			resetGameCounts();
		} else {
			clearMatchWinner();
		}
		return matchWon;
		
	}
	private void checkMeatball() {
		int points1 = team1.getScore();
		int points2 = team2.getScore();
		int gameCount1 = team1.getGameCount();
		int gameCount2 = team2.getGameCount();
		if (settings.getAnnounceMeatball() == 1) {
			if (points1 == points2) {
				int meatballPoint = settings.getPointsToWin() - 1;
				if (settings.getWinBy() < 2) {
					if (points1 == meatballPoint) {
						if (gameCount1 == gameCount2) {
							if (gameCount1 == settings.getGamesToWin()-1) {
								writeMeatball();
								return;
							}
						}
					}
				}
			}
		}
		clearMeatball();
	}
	private boolean checkForWin(int score1, int score2) {
		int pointsToWin = settings.getPointsToWin();
		int maxWin = settings.getMaxWin();
		int winBy = settings.getWinBy();
		if (settings.getAutoIncrementGame()==1) {
			if (score1 >= maxWin || (score1 >= pointsToWin && score1 >= score2 + winBy)) {
				clearMeatball();
				return true;
			} else {
				clearMatchWinner();
			}
		}
		return false;
	}
	private boolean checkForMatchWin(Team team) {
		boolean matchWon = false;
		if(team.getGameCount()==settings.getGamesToWin()) {
			matchWon=true;
		}
		return matchWon;
	}
	private void resetScores() {
		team1.setScore(0);
		team2.setScore(0);
	}
//	private void resetGameCounts() {
//		team1.setGameCount(0);
//		team2.setGameCount(0);
//	}
	private void resetTimeOuts() {
		team1.resetTimeOuts();
		team2.resetTimeOuts();
	}
	private void resetResetWarn() {
		team1.setReset(false);
		team1.setWarn(false);
		team2.setReset(false);
		team2.setWarn(false);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	private void writeLastScored() {
		try {
			obsInterface.setContents(settings.getLastScoredFileName(), settings.getLastScoredStrings()[lastScored]);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void writeMatchWinner(String theContents) {
		try {
			obsInterface.setContents(settings.getMatchWinnerFileName(), theContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void clearMatchWinner() {
		try {
			obsInterface.setContents(settings.getMatchWinnerFileName(), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeMeatball() {
		try {
			obsInterface.setContents(settings.getMeatballFileName(), settings.getMeatball());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void clearMeatball() {
		try {
			obsInterface.setContents(settings.getMeatballFileName(), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void restoreState(byte[] serializedObject) {

		Match tempMatch = null;
		try {
			byte b[] = serializedObject;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			tempMatch = (Match) si.readObject();
		} catch (Exception e) {
			System.out.println(e);
		}
		this.setLastScored(tempMatch.getLastScored());
	}
}
