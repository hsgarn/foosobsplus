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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextField;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScored1Clock;
import com.midsouthfoosball.foosobsplus.model.LastScored2Clock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;

public class TeamController {
	private OBSInterface obsInterface;
	private Settings settings;
	private Team team1;
	private Team team2;
	private Match match;
	private TeamPanel teamPanel1;
	private TeamPanel teamPanel2;
	private SwitchPanel switchPanel;
	private MatchPanel matchPanel;
	private TimerController timerController;
	private LastScored1Clock lastScored1Clock;
	private LastScored2Clock lastScored2Clock;
	private GameClock gameClock;
	
	public TeamController(OBSInterface obsInterface, Settings settings, Team team1, Team team2, Match match, TeamPanel teamPanel1, TeamPanel teamPanel2, SwitchPanel switchPanel, MatchPanel matchPanel, TimerController timerController, LastScored1Clock lastScored1Clock, LastScored2Clock lastScored2Clock, GameClock gameClock) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.team1 = team1;
		this.team2 = team2;
		this.match = match;
		this.teamPanel1 = teamPanel1;
		this.teamPanel2 = teamPanel2;
		this.switchPanel = switchPanel;
		this.matchPanel = matchPanel;
		this.timerController = timerController;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.gameClock = gameClock;
		
		////// Team Panel Listeners Methods //////
		
		this.teamPanel1.addTeamNameListener(new TeamNameListener());
		this.teamPanel2.addTeamNameListener(new TeamNameListener());
		this.teamPanel1.addTeamNameFocusListener(new TeamNameFocusListener());
		this.teamPanel2.addTeamNameFocusListener(new TeamNameFocusListener());
		this.teamPanel1.addTeamNameMouseListener(new TeamNameMouseListener());
		this.teamPanel2.addTeamNameMouseListener(new TeamNameMouseListener());
		this.teamPanel1.addForwardNameListener(new ForwardNameListener());
		this.teamPanel2.addForwardNameListener(new ForwardNameListener());
		this.teamPanel1.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.teamPanel2.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.teamPanel1.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.teamPanel2.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.teamPanel1.addGoalieNameListener(new GoalieNameListener());
		this.teamPanel2.addGoalieNameListener(new GoalieNameListener());
		this.teamPanel1.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.teamPanel2.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.teamPanel1.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.teamPanel2.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.teamPanel1.addScoreListener(new ScoreListener());
		this.teamPanel2.addScoreListener(new ScoreListener());
		this.teamPanel1.addScoreFocusListener(new ScoreFocusListener());
		this.teamPanel2.addScoreFocusListener(new ScoreFocusListener());
		this.teamPanel1.addGameCountListener(new GameCountListener());
		this.teamPanel2.addGameCountListener(new GameCountListener());
		this.teamPanel1.addGameCountFocusListener(new GameCountFocusListener());
		this.teamPanel2.addGameCountFocusListener(new GameCountFocusListener());
		this.teamPanel1.addTimeOutCountListener(new TimeOutCountListener());
		this.teamPanel2.addTimeOutCountListener(new TimeOutCountListener());
		this.teamPanel1.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.teamPanel2.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.lastScored1Clock.addLastScoredClockTimerListener(new LastScoredClockTimerListener());
		this.lastScored2Clock.addLastScoredClockTimerListener(new LastScoredClockTimerListener());
	}
	
	////// Team Panel Listener Objects //////

	private class TeamNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String teamName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setTeamName(teamName);
				teamPanel1.updateTeamName(teamName);
			} else {
				team2.setTeamName(teamName);
				teamPanel2.updateTeamName(teamName);
			}
		}
	}
	private class TeamNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String teamName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setTeamName(teamName);
				teamPanel1.updateTeamName(teamName);
			} else {
				team2.setTeamName(teamName);
				teamPanel2.updateTeamName(teamName);
			}
		}
	}
	private class TeamNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				teamPanel1.selectTeamName();
			} else {
				teamPanel2.selectTeamName();
			}
		}
	}
	private class ForwardNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String forwardName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setForwardName(forwardName);
				teamPanel1.updateForwardName(forwardName);
			} else {
				team2.setForwardName(forwardName);
				teamPanel2.updateForwardName(forwardName);
			}
		}
	}
	private class ForwardNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String forwardName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setForwardName(forwardName);
				teamPanel1.updateForwardName(forwardName);
			} else {
				team2.setForwardName(forwardName);
				teamPanel2.updateForwardName(forwardName);
			}
		}
	}
	private class ForwardNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				teamPanel1.selectForwardName();
			} else {
				teamPanel2.selectForwardName();
			}
		}
	}
	private class GoalieNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String goalieName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setGoalieName(goalieName);
				teamPanel1.updateGoalieName(goalieName);
			} else {
				team2.setGoalieName(goalieName);
				teamPanel2.updateGoalieName(goalieName);
			}
		}
	}
	private class GoalieNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String goalieName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setGoalieName(goalieName);
				teamPanel1.updateGoalieName(goalieName);
			} else {
				team2.setGoalieName(goalieName);
				teamPanel2.updateGoalieName(goalieName);
			}
		}
	}
	private class GoalieNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				teamPanel1.selectGoalieName();
			} else {
				teamPanel2.selectGoalieName();
			}
		}
	}
	private class ScoreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String score = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setScore(score);
				teamPanel1.updateScore(score);
			} else {
				team2.setScore(score);
				teamPanel2.updateScore(score);
			};
		}
	}
	private class ScoreFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String score = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setScore(score);
				teamPanel1.updateScore(score);
			} else {
				team2.setScore(score);
				teamPanel2.updateScore(score);
			}
		}
	}
	private class GameCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String gameCount = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setGameCount(gameCount);
				teamPanel1.updateGameCount(gameCount);
			} else {
				team2.setGameCount(gameCount);
				teamPanel2.updateGameCount(gameCount);
			};
		}
	}
	private class GameCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String gameCount = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setGameCount(gameCount);
				teamPanel1.updateGameCount(gameCount);
			} else {
				team2.setGameCount(gameCount);
				teamPanel2.updateGameCount(gameCount);
			}
		}
	}
	private class TimeOutCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String timeOutCount = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setTimeOutCount(timeOutCount);
				teamPanel1.updateTimeOutCount(timeOutCount);
			} else {
				team2.setTimeOutCount(timeOutCount);
				teamPanel2.updateTimeOutCount(timeOutCount);
			};
		}
	}
	private class TimeOutCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String timeOutCount = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setTimeOutCount(timeOutCount);
				teamPanel1.updateTimeOutCount(timeOutCount);
			} else {
				team2.setTimeOutCount(timeOutCount);
				teamPanel2.updateTimeOutCount(timeOutCount);
			}
		}
	}

	private class LastScoredClockTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateLastScoredTimes();
		}
	}
	
	////// Utility Methods \\\\\\

	public void updateLastScoredTimes() {
		String lastScoredTime1 = lastScored1Clock.getLastScoredTime();
		String lastScoredTime2 = lastScored2Clock.getLastScoredTime();
		teamPanel1.updateLastScoredTime(lastScoredTime1);
		teamPanel2.updateLastScoredTime(lastScoredTime2);
	}
	private String convertNumbers(String checkString) {
		try {
			Integer.parseInt(checkString);
			return checkString;
		} catch (NumberFormatException e) {
			return "0";
		}
	}
	public void switchPositions(String name) {
		if(name.equals("Team 1")) {
			String[] names = team1.switchPositions();
			String forwardName = names[0];
			String goalieName = names[1];
			teamPanel1.updateNames(forwardName, goalieName);
		} else {
			String[] names = team2.switchPositions();
			String forwardName = names[0];
			String goalieName = names[1];
			teamPanel2.updateNames(forwardName, goalieName);
		}
	};
	public void incrementScore(String name) {
		int winState = 0;
		if(name.equals("Team 1")) {
			winState = match.incrementScore(1, gameClock.getGameTime());
			lastScored1Clock.startLastScoredTimer();
		} else {
			winState = match.incrementScore(2, gameClock.getGameTime());
			lastScored2Clock.startLastScoredTimer();
		};
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		resetTimer();
		if(winState==1) {
			startGameTimer();
			gameClock.stopGameTimer();
		}
		displayAll();
	}
	public void decrementScore(String name) {
		if(name.equals("Team 1")) {
			match.decrementScore(1);
			teamPanel1.updateScore(team1.getScore());
		} else {
			match.decrementScore(2);
			teamPanel2.updateScore(team2.getScore());
		}
		matchPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getTimes());	
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
	}
	public void incrementGameCount(String name) {
		boolean matchWon = false;
		if(name.equals("Team 1")) {
			matchWon = match.incrementGameCount(team1);
			teamPanel1.updateGameCount(team1.getGameCount());
		} else {
			matchWon = match.incrementGameCount(team2);
			teamPanel2.updateGameCount(team2.getGameCount());
		};
		if(!matchWon) startGameTimer();
	}
	public void decrementGameCount(String name) {
		if(name.equals("Team 1")) {
			team1.decrementGameCount();
			teamPanel1.updateGameCount(team1.getGameCount());
		} else {
			team2.decrementGameCount();
			teamPanel2.updateGameCount(team2.getGameCount());
		}
	}
	public void callTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.callTimeOut();
			teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
		} else {
			team2.callTimeOut();
			teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
		}
		startTimeOutTimer();
	}
	public void restoreTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.restoreTimeOut();
			teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
		} else {
			team2.restoreTimeOut();
			teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
		}
	}
	public void resetTimer() {
		timerController.resetTimer();
	}
	public void startGameTimer() {
		timerController.startGameTimer();
	}
	public void startShotTimer() {
		timerController.startShotTimer();
	}
	public void startPassTimer() {
		timerController.startPassTimer();
	}
	public void startTimeOutTimer() {
		timerController.startTimeOutTimer();
	}
	public void startRecallTimer() {
		timerController.startRecallTimer();
	}
	public void switchSides() {
		team1.writeAll();
		team2.writeAll();
		int tmpSeconds = lastScored1Clock.getLastScoredSeconds();
		int tmpMinutes = lastScored1Clock.getLastScoredMinutes();
		int tmpHours = lastScored1Clock.getLastScoredHours();
		lastScored1Clock.setLastScoredSeconds(lastScored2Clock.getLastScoredSeconds());
		lastScored1Clock.setLastScoredMinutes(lastScored2Clock.getLastScoredMinutes());
		lastScored1Clock.setLastScoredHours(lastScored2Clock.getLastScoredHours());
		lastScored2Clock.setLastScoredSeconds(tmpSeconds);
		lastScored2Clock.setLastScoredMinutes(tmpMinutes);
		lastScored2Clock.setLastScoredHours(tmpHours);
		match.switchLastScored();
		displayAll();
	}
	public void switchTeams() {
		String temp = team1.getTeamName();
		team1.setTeamName(team2.getTeamName());
		team2.setTeamName(temp);
		String tmp = team1.getForwardName();
		team1.setForwardName(team2.getForwardName());
		team2.setForwardName(tmp);
		String tmp2 = team1.getGoalieName();
		team1.setGoalieName(team2.getGoalieName());
		team2.setGoalieName(tmp2);
		teamPanel1.updateTeamName(team1.getTeamName());
		teamPanel1.updateForwardName(team1.getForwardName());
		teamPanel1.updateGoalieName(team1.getGoalieName());
		teamPanel2.updateTeamName(team2.getTeamName());
		teamPanel2.updateForwardName(team2.getForwardName());
		teamPanel2.updateGoalieName(team2.getGoalieName());
	}
	public void switchScores() {
		int tmp = team1.getScore();
		team1.setScore(team2.getScore());
		team2.setScore(tmp);
		teamPanel1.updateScore(team1.getScore());
		teamPanel2.updateScore(team2.getScore());
	}
	public void switchGameCounts() {
		int tmp = team1.getGameCount();
		team1.setGameCount(team2.getGameCount());
		team2.setGameCount(tmp);
		teamPanel1.updateGameCount(team1.getGameCount());
		teamPanel2.updateGameCount(team2.getGameCount());
	}
	public void switchTimeOuts() {
		int tmp = team1.getTimeOutCount();
		team1.setTimeOutCount(team2.getTimeOutCount());
		team2.setTimeOutCount(tmp);
		teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
		teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
	}
	public void switchResetWarns() {
		boolean tmp = team1.getReset();
		team1.setReset(team2.getReset());
		team2.setReset(tmp);
		boolean tmp2 = team1.getWarn();
		team1.setWarn(team2.getWarn());
		team2.setWarn(tmp2);
		teamPanel1.updateReset(team1.getReset());
		teamPanel2.updateReset(team2.getReset());
		teamPanel1.updateWarn(team1.getWarn());
		teamPanel2.updateWarn(team2.getWarn());
	}
	public void clearAll() {
		team1.clearAll();
		team2.clearAll();
		match.clearAll();
		displayAll();
		switchPanel.setLastScored(settings.getLastScoredStrings()[0]);
	}
	public void resetNames() {
		team1.setTeamName("");
		team2.setTeamName("");
		team1.setForwardName("");
		team2.setForwardName("");
		team1.setGoalieName("");
		team2.setGoalieName("");
		teamPanel1.updateTeamName(team1.getTeamName());
		teamPanel2.updateTeamName(team2.getTeamName());
		teamPanel1.updateForwardName(team1.getForwardName());
		teamPanel2.updateForwardName(team2.getForwardName());
		teamPanel1.updateGoalieName(team1.getGoalieName());
		teamPanel2.updateGoalieName(team2.getGoalieName());
	}
	public void resetScores() {
		team1.setScore(0);
		team2.setScore(0);
		teamPanel1.updateScore(team1.getScore());
		teamPanel2.updateScore(team2.getScore());
	}
	public void resetGameCounts() {
		team1.setGameCount(0);
		team2.setGameCount(0);
		teamPanel1.updateGameCount(team1.getGameCount());
		teamPanel2.updateGameCount(team2.getGameCount());
	}
	public void resetTimeOuts() {
		team1.resetTimeOuts();
		team2.resetTimeOuts();
		teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
		teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
	}
	public void resetResetWarns() {
		team1.setReset(false);
		team2.setReset(false);
		team1.setWarn(false);
		team2.setWarn(false);
		teamPanel1.updateReset(team1.getReset());
		teamPanel2.updateReset(team2.getReset());
		teamPanel1.updateWarn(team1.getWarn());
		teamPanel2.updateWarn(team2.getWarn());
	}
	public void resetStats() {
		team1.resetStats();
		team2.resetStats();
	}
	public void resetAll() {
		resetScores();
		resetGameCounts();
		resetTimeOuts();
		resetResetWarns();
		resetStats();
		resetLastScoredClocks();
		resetLastScored();
		team1.writeAll();
		team2.writeAll();
	}
	private void resetLastScored() {
		match.setLastScored(0);
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
	}
	private void resetLastScoredClocks() {
		lastScored1Clock.startLastScoredTimer();
		lastScored2Clock.startLastScoredTimer();
	}
	public void displayAll() {
		String teamName1 = team1.getTeamName();
		String forwardName1 = team1.getForwardName();
		String goalieName1 = team1.getGoalieName();
		int score1 = team1.getScore();
		int gameCount1 = team1.getGameCount();
		int timeOutCount1 = team1.getTimeOutCount();
		boolean isReset1 = team1.getReset();
		boolean isWarn1 = team1.getWarn();
		String teamName2 = team2.getTeamName();
		String forwardName2 = team2.getForwardName();
		String goalieName2 = team2.getGoalieName();
		int score2 = team2.getScore();
		int gameCount2 = team2.getGameCount();
		int timeOutCount2 = team2.getTimeOutCount();
		boolean isReset2 = team2.getReset();
		boolean isWarn2 = team2.getWarn();
		teamPanel1.displayAllFields(teamName1, forwardName1, goalieName1, score1, gameCount1, timeOutCount1, isReset1, isWarn1);
		teamPanel2.displayAllFields(teamName2, forwardName2, goalieName2, score2, gameCount2, timeOutCount2, isReset2, isWarn2);
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		matchPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getTimes());	
	}
	public void toggleReset(String txt) {
		if(txt.equals("Team 1")) {
			team1.setReset(!team1.getReset());
			teamPanel1.updateReset(team1.getReset());
		} else {
			team2.setReset(!team2.getReset());
			teamPanel2.updateReset(team2.getReset());
		}
	}
	public void toggleWarn(String txt) {
		if(txt.equals("Team 1")) {
			team1.setWarn(!team1.getWarn());
			teamPanel1.updateWarn(team1.getWarn());
		} else {
			team2.setWarn(!team2.getWarn());
			teamPanel2.updateWarn(team2.getWarn());
		}
	}
	public void setReset(String txt, boolean state) {
		if(txt.equals("Team 1")) {
			team1.setReset(state);
			teamPanel1.updateReset(team1.getReset());
		} else {
			team2.setReset(state);
			teamPanel2.updateReset(team2.getReset());
		}
	}
	public void setWarn(String txt, boolean state) {
		if(txt.equals("Team 1")) {
			team1.setWarn(state);
			teamPanel1.updateWarn(team1.getWarn());
		} else {
			team2.setWarn(state);
			teamPanel2.updateWarn(team2.getWarn());
		}
	}
	public void fetchAll() {
		try {
			team1.setTeamName(obsInterface.getContents(settings.getTeamNameFileName(1)));
			team2.setTeamName(obsInterface.getContents(settings.getTeamNameFileName(2)));
			team1.setForwardName(obsInterface.getContents(settings.getTeamForwardFileName(1)));
			team2.setForwardName(obsInterface.getContents(settings.getTeamForwardFileName(2)));
			team1.setGoalieName(obsInterface.getContents(settings.getTeamGoalieFileName(1)));
			team2.setGoalieName(obsInterface.getContents(settings.getTeamGoalieFileName(2)));
			team1.setScore(obsInterface.getContents(settings.getScoreFileName(1)));
			team2.setScore(obsInterface.getContents(settings.getScoreFileName(2)));
			team1.setGameCount(obsInterface.getContents(settings.getGameCountFileName(1)));
			team2.setGameCount(obsInterface.getContents(settings.getGameCountFileName(2)));
			team1.setTimeOutCount(obsInterface.getContents(settings.getTimeOutFileName(1)));
			team2.setTimeOutCount(obsInterface.getContents(settings.getTimeOutFileName(2)));
			team1.setReset(obsInterface.getContents(settings.getResetFileName(1))!=null);
			team2.setReset(obsInterface.getContents(settings.getResetFileName(2))!=null);
			team1.setWarn(obsInterface.getContents(settings.getWarnFileName(1))!=null);
			team2.setWarn(obsInterface.getContents(settings.getWarnFileName(2))!=null);
			team1.setPassAttempts(obsInterface.getContents(settings.getPassAttemptsFileName(1)));
			team2.setPassAttempts(obsInterface.getContents(settings.getPassAttemptsFileName(2)));
			team1.setShotAttempts(obsInterface.getContents(settings.getShotAttemptsFileName(1)));
			team2.setShotAttempts(obsInterface.getContents(settings.getShotAttemptsFileName(2)));
			team1.setClearAttempts(obsInterface.getContents(settings.getClearAttemptsFileName(1)));
			team2.setClearAttempts(obsInterface.getContents(settings.getClearAttemptsFileName(2)));
			team1.setPassCompletes(obsInterface.getContents(settings.getPassCompletesFileName(1)));
			team2.setPassCompletes(obsInterface.getContents(settings.getPassCompletesFileName(2)));
			team1.setShotCompletes(obsInterface.getContents(settings.getShotCompletesFileName(1)));
			team2.setShotCompletes(obsInterface.getContents(settings.getShotCompletesFileName(2)));
			team1.setClearCompletes(obsInterface.getContents(settings.getClearCompletesFileName(1)));
			team2.setClearCompletes(obsInterface.getContents(settings.getClearCompletesFileName(2)));
			team1.setPassPercent(obsInterface.getContents(settings.getPassPercentFileName(1)));
			team2.setPassPercent(obsInterface.getContents(settings.getPassPercentFileName(2)));
			team1.setShotPercent(obsInterface.getContents(settings.getShotPercentFileName(1)));
			team2.setShotPercent(obsInterface.getContents(settings.getShotPercentFileName(2)));
			team1.setClearPercent(obsInterface.getContents(settings.getClearPercentFileName(1)));
			team2.setClearPercent(obsInterface.getContents(settings.getClearPercentFileName(2)));
			team1.setTwoBarPassAttempts(obsInterface.getContents(settings.getTwoBarPassAttemptsFileName(1)));
			team2.setTwoBarPassAttempts(obsInterface.getContents(settings.getTwoBarPassAttemptsFileName(2)));
			team1.setTwoBarPassCompletes(obsInterface.getContents(settings.getTwoBarPassCompletesFileName(1)));
			team2.setTwoBarPassCompletes(obsInterface.getContents(settings.getTwoBarPassCompletesFileName(2)));
			team1.setTwoBarPassPercent(obsInterface.getContents(settings.getTwoBarPassPercentFileName(1)));
			team2.setTwoBarPassPercent(obsInterface.getContents(settings.getTwoBarPassPercentFileName(2)));
			team1.setScoring(obsInterface.getContents(settings.getScoringFileName(1)));
			team2.setScoring(obsInterface.getContents(settings.getScoringFileName(2)));
			team1.setThreeBarScoring(obsInterface.getContents(settings.getThreeBarScoringFileName(1)));
			team2.setThreeBarScoring(obsInterface.getContents(settings.getThreeBarScoringFileName(2)));
			team1.setFiveBarScoring(obsInterface.getContents(settings.getFiveBarScoringFileName(1)));
			team2.setFiveBarScoring(obsInterface.getContents(settings.getFiveBarScoringFileName(2)));
			team1.setTwoBarScoring(obsInterface.getContents(settings.getTwoBarScoringFileName(1)));
			team2.setTwoBarScoring(obsInterface.getContents(settings.getTwoBarScoringFileName(2)));
			team1.setShotsOnGoal(obsInterface.getContents(settings.getShotsOnGoalFileName(1)));
			team2.setShotsOnGoal(obsInterface.getContents(settings.getShotsOnGoalFileName(2)));
			team1.setStuffs(obsInterface.getContents(settings.getStuffsFileName(1)));
			team2.setStuffs(obsInterface.getContents(settings.getStuffsFileName(2)));
			team1.setBreaks(obsInterface.getContents(settings.getBreaksFileName(1)));
			team2.setBreaks(obsInterface.getContents(settings.getBreaksFileName(2)));
			match.setLastScored(obsInterface.getContents(settings.getLastScoredFileName()));
			teamPanel1.updateTeamName(team1.getTeamName());
			teamPanel2.updateTeamName(team2.getTeamName());
			teamPanel1.updateForwardName(team1.getForwardName());
			teamPanel2.updateForwardName(team2.getForwardName());
			teamPanel1.updateGoalieName(team1.getGoalieName());
			teamPanel2.updateGoalieName(team2.getGoalieName());
			teamPanel1.updateScore(team1.getScore());
			teamPanel2.updateScore(team2.getScore());
			teamPanel1.updateGameCount(team1.getGameCount());
			teamPanel2.updateGameCount(team2.getGameCount());
			teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
			teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
			teamPanel1.updateReset(team1.getReset());
			teamPanel2.updateReset(team2.getReset());
			teamPanel1.updateWarn(team1.getWarn());
			teamPanel2.updateWarn(team2.getWarn());
			switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeAll() {
		team1.writeAll();
		team2.writeAll();
	}
	public int getPassAttempts(int teamNbr) {
		if (teamNbr==1) {
			return team1.getPassAttempts();
		} else {
			return team2.getPassAttempts();
		}
	}
	public int getPassCompletes(int teamNbr) {
		if (teamNbr==1) {
			return team1.getPassCompletes();
		} else {
			return team2.getPassCompletes();
		}
	}
	public Float getPassPercent(int teamNbr) {
		if (teamNbr==1) {
			return team1.getPassPercent();
		} else {
			return team2.getPassPercent();
		}
	}
	public int getShotAttempts(int teamNbr) {
		if (teamNbr==1) {
			return team1.getShotAttempts();
		} else {
			return team2.getShotAttempts();
		}
	}
	public int getShotCompletes(int teamNbr) {
		if (teamNbr==1) {
			return team1.getShotCompletes();
		} else {
			return team2.getShotCompletes();
		}
	}
	public Float getShotPercent(int teamNbr) {
		if (teamNbr==1) {
			return team1.getShotPercent();
		} else {
			return team2.getShotPercent();
		}
	}
	public int getClearAttempts(int teamNbr) {
		if (teamNbr==1) {
			return team1.getClearAttempts();
		} else {
			return team2.getClearAttempts();
		}
	}
	public int getClearCompletes(int teamNbr) {
		if (teamNbr==1) {
			return team1.getClearCompletes();
		} else {
			return team2.getClearCompletes();
		}
	}
	public Float getClearPercent(int teamNbr) {
		if (teamNbr==1) {
			return team1.getClearPercent();
		} else {
			return team2.getClearPercent();
		}
	}
	public int getTwoBarPassAttempts(int teamNbr) {
		if (teamNbr==1) {
			return team1.getTwoBarPassAttempts();
		} else {
			return team2.getTwoBarPassAttempts();
		}
	}
	public int getTwoBarPassCompletes(int teamNbr) {
		if (teamNbr==1) {
			return team1.getTwoBarPassCompletes();
		} else {
			return team2.getTwoBarPassCompletes();
		}
	}
	public Float getTwoBarPassPercent(int teamNbr) {
		if (teamNbr==1) {
			return team1.getTwoBarPassPercent();
		} else {
			return team2.getTwoBarPassPercent();
		}
	}
	public int getScoring(int teamNbr) {
		if (teamNbr==1) {
			return team1.getScoring();
		} else {
			return team2.getScoring();
		}
	}
	public int getThreeBarScoring(int teamNbr) {
		if (teamNbr==1) {
			return team1.getThreeBarScoring();
		} else {
			return team2.getThreeBarScoring();
		}
	}
	public int getFiveBarScoring(int teamNbr) {
		if (teamNbr==1) {
			return team1.getFiveBarScoring();
		} else {
			return team2.getFiveBarScoring();
		}
	}
	public int getTwoBarScoring(int teamNbr) {
		if (teamNbr==1) {
			return team1.getTwoBarScoring();
		} else {
			return team2.getTwoBarScoring();
		}
	}
	public int getShotsOnGoal(int teamNbr) {
		if (teamNbr==1) {
			return team1.getShotsOnGoal();
		} else {
			return team2.getShotsOnGoal();
		}
	}
	public int getStuffs(int teamNbr) {
		if (teamNbr==1) {
			return team1.getStuffs();
		} else {
			return team2.getStuffs();
		}
	}
	public int getBreaks(int teamNbr) {
		if (teamNbr==1) {
			return team1.getBreaks();
		} else {
			return team2.getBreaks();
		}
	}
}
