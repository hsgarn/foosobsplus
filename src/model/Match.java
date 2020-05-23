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
package model;

import java.io.IOException;

import main.OBSInterface;

public class Match {
	
	private Team team1;
	private Team team2;
	private Settings settings;
	private OBSInterface obsInterface;
	private int lastScored; // team number of the last team to score in this match
	
	public Match(OBSInterface obsInterface, Settings settings, Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.settings = settings;
		this.obsInterface = obsInterface;
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
	public void clearAll() {
		lastScored = 0;
		writeLastScored();
		clearMatchWinner();
		clearMeatball();
	}
	public int incrementScore(int teamNbr) {
		// return 0 if no winner
		// return 1 if game won
		// return 2 if match won
		int winState = 0;
		boolean matchWon = false;
		if(teamNbr==1) {
			team1.incrementScore();
			setLastScored(1);
			if(checkForWin(team1.getScore(), team2.getScore())) {
				matchWon = incrementGameCount(team1);
				resetScores();
				resetTimeOuts();
				resetResetWarn();
				winState = 1;
			}
		} else {
			team2.incrementScore();
			setLastScored(2);
			if(checkForWin(team2.getScore(), team1.getScore())) {
				matchWon = incrementGameCount(team2);
				resetScores();
				resetTimeOuts();
				resetResetWarn();
				winState = 1;
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
			resetGameCounts();
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
	private void resetGameCounts() {
		team1.setGameCount(0);
		team2.setGameCount(0);
	}
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
}