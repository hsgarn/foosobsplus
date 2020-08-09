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
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
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
	private GameTableWindowPanel gameTableWindowPanel;
	private TimerController timerController;
	private LastScored1Clock lastScored1Clock;
	private LastScored2Clock lastScored2Clock;
	private GameClock gameClock;
	private Team teams[] = new Team[] {	null,null};
	private TeamPanel teamPanels[] = new TeamPanel[] {null,null};
	
	public TeamController(OBSInterface obsInterface, Settings settings, Team team1, Team team2, Match match, TeamPanel teamPanel1, TeamPanel teamPanel2, SwitchPanel switchPanel, MatchPanel matchPanel, GameTableWindowPanel gameTableWindowPanel, TimerController timerController, LastScored1Clock lastScored1Clock, LastScored2Clock lastScored2Clock, GameClock gameClock) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.team1 = team1;
		this.team2 = team2;
		this.match = match;
		this.teamPanel1 = teamPanel1;
		this.teamPanel2 = teamPanel2;
		this.switchPanel = switchPanel;
		this.matchPanel = matchPanel;
		this.gameTableWindowPanel = gameTableWindowPanel;
		this.timerController = timerController;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.gameClock = gameClock;
		this.teams[0] = team1;
		this.teams[1] = team2;
		this.teamPanels[0] = teamPanel1;
		this.teamPanels[1] = teamPanel2;
		
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
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setTeamName(teamName);
			teamPanels[teamNumber-1].updateTeamName(teamName);
		}
	}

	private class TeamNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String teamName = txt.getText();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setTeamName(teamName);
			teamPanels[teamNumber-1].updateTeamName(teamName);
		}
	}
	private class TeamNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teamPanels[teamNumber-1].selectTeamName();
		}
	}
	private class ForwardNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String forwardName = txt.getText();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setForwardName(forwardName);
			teamPanels[teamNumber-1].updateForwardName(forwardName);
			updateGameTables();
		}
	}
	private class ForwardNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String forwardName = txt.getText();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setForwardName(forwardName);
			teamPanels[teamNumber-1].updateForwardName(forwardName);
			updateGameTables();
		}
	}
	private class ForwardNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teamPanels[teamNumber-1].selectForwardName();
			updateGameTables();
		}
	}
	private class GoalieNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String goalieName = txt.getText();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setGoalieName(goalieName);
			teamPanels[teamNumber-1].updateGoalieName(goalieName);
			updateGameTables();
		}
	}
	private class GoalieNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String goalieName = txt.getText();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setGoalieName(goalieName);
			teamPanels[teamNumber-1].updateGoalieName(goalieName);
			updateGameTables();
		}
	}
	private class GoalieNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teamPanels[teamNumber-1].selectGoalieName();
			updateGameTables();
		}
	}
	private class ScoreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String score = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setScore(score);
			teamPanels[teamNumber-1].updateScore(score);
			updateGameTables();
		}
	}
	private class ScoreFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String score = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setScore(score);
			teamPanels[teamNumber-1].updateScore(score);
			updateGameTables();
		}
	}
	private class GameCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String gameCount = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setGameCount(gameCount);
			teamPanels[teamNumber-1].updateGameCount(gameCount);
			updateGameTables();
		}
	}
	private class GameCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String gameCount = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setGameCount(gameCount);
			teamPanels[teamNumber-1].updateGameCount(gameCount);
			updateGameTables();
		}
	}
	private class TimeOutCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String timeOutCount = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setTimeOutCount(timeOutCount);
			teamPanels[teamNumber-1].updateTimeOutCount(timeOutCount);
		}
	}
	private class TimeOutCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String timeOutCount = convertNumbers(txt.getText());
			int teamNumber = 2;
			if(name.equals("Team 1")) {
				teamNumber = 1;
			}
			teams[teamNumber-1].setTimeOutCount(timeOutCount);
			teamPanels[teamNumber-1].updateTimeOutCount(timeOutCount);
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
		int teamNumber = 2;
		if(name.equals("Team 1")) {
			teamNumber = 1;
		}
		String[] names = teams[teamNumber-1].switchPositions();
		String forwardName = names[0];
		String goalieName = names[1];
		teamPanels[teamNumber-1].updateNames(forwardName, goalieName);
		updateGameTables();
	};
	public int incrementScore(int teamNumber) {
		int winState = 0;
		if(teamNumber == 1) {
			winState = match.incrementScore(1, gameClock.getGameTime());
			lastScored1Clock.startLastScoredTimer();
		} else {
			winState = match.incrementScore(2, gameClock.getGameTime());
			lastScored2Clock.startLastScoredTimer();
		};
		resetTimer();
		if(winState==1) {
			startGameTimer();
			gameClock.stopGameTimer();
		}
		displayAll();
		return winState;
	}
	public void decrementScore(int teamNumber) {
		match.decrementScore(teamNumber);
		teamPanels[teamNumber-1].updateScore(teams[teamNumber-1].getScore());
		updateGameTables();
	}
	public void incrementGameCount(int teamNumber) {
		boolean matchWon = false;
		matchWon = match.incrementGameCount(teams[teamNumber-1]);
		match.syncCurrentGameNumber();
		teamPanels[teamNumber-1].updateGameCount(teams[teamNumber-1].getGameCount());
		if(!matchWon) startGameTimer();
		updateGameTables();
	}
	public void decrementGameCount(int teamNumber) {
		teams[teamNumber-1].decrementGameCount();
		match.syncCurrentGameNumber();
		teamPanels[teamNumber-1].updateGameCount(teams[teamNumber-1].getGameCount());
		updateGameTables();
	}
	public void callTimeOut(int teamNumber) {
		teams[teamNumber-1].callTimeOut();
		teamPanels[teamNumber-1].updateTimeOutCount(teams[teamNumber-1].getTimeOutCount());
		startTimeOutTimer();
	}
	public void restoreTimeOut(int teamNumber) {
		teams[teamNumber-1].restoreTimeOut();
		teamPanels[teamNumber-1].updateTimeOutCount(teams[teamNumber-1].getTimeOutCount());
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
		updateGameTables();
	}
	public void switchScores() {
		int tmp = team1.getScore();
		team1.setScore(team2.getScore());
		team2.setScore(tmp);
		teamPanel1.updateScore(team1.getScore());
		teamPanel2.updateScore(team2.getScore());
		updateGameTables();
	}
	public void switchGameCounts() {
		int tmp = team1.getGameCount();
		team1.setGameCount(team2.getGameCount());
		team2.setGameCount(tmp);
		teamPanel1.updateGameCount(team1.getGameCount());
		teamPanel2.updateGameCount(team2.getGameCount());
		updateGameTables();
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
		updateGameTables();
	}
	public void resetScores() {
		team1.setScore(0);
		team2.setScore(0);
		teamPanel1.updateScore(team1.getScore());
		teamPanel2.updateScore(team2.getScore());
		updateGameTables();
	}
	public void resetGameCounts() {
		team1.setGameCount(0);
		team2.setGameCount(0);
		teamPanel1.updateGameCount(team1.getGameCount());
		teamPanel2.updateGameCount(team2.getGameCount());
		updateGameTables();
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
		updateGameTables();
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
		updateGameTables();
	}
	public void updateGameTables() {
		matchPanel.setGameWinners(match.getGameWinners());
		matchPanel.setMatchWinner(match.getMatchWinner());
		matchPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getTimes(), match.getCurrentGameNumber());
		gameTableWindowPanel.setTeams(getForwardName(1) + " / " + getGoalieName(1) + ":",getForwardName(2) + " / " + getGoalieName(2) + ":");
		gameTableWindowPanel.setGameWinners(match.getGameWinners());
		gameTableWindowPanel.setMatchWinner(match.getMatchWinner());
		gameTableWindowPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getTimes(), match.getCurrentGameNumber());
	}
	public void toggleReset(int teamNumber) {
		teams[teamNumber-1].setReset(!teams[teamNumber-1].getReset());
		teamPanels[teamNumber-1].updateReset(teams[teamNumber-1].getReset());
	}
	public void toggleWarn(int teamNumber) {
		teams[teamNumber-1].setWarn(!teams[teamNumber-1].getWarn());
		teamPanels[teamNumber-1].updateWarn(teams[teamNumber-1].getWarn());
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
			match.syncCurrentGameNumber();
			match.syncGameScores();
			updateGameTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeAll() {
		team1.writeAll();
		team2.writeAll();
	}
	public int getPassAttempts(int teamNumber) {
		return teams[teamNumber-1].getPassAttempts();
	}
	public int getPassCompletes(int teamNumber) {
		return teams[teamNumber-1].getPassCompletes();
	}
	public Float getPassPercent(int teamNumber) {
		return teams[teamNumber-1].getPassPercent();
	}
	public int getShotAttempts(int teamNumber) {
		return teams[teamNumber-1].getShotAttempts();
	}
	public int getShotCompletes(int teamNumber) {
		return teams[teamNumber-1].getShotCompletes();
	}
	public Float getShotPercent(int teamNumber) {
		return teams[teamNumber-1].getShotPercent();
	}
	public int getClearAttempts(int teamNumber) {
		return teams[teamNumber-1].getClearAttempts();
	}
	public int getClearCompletes(int teamNumber) {
		return teams[teamNumber-1].getClearCompletes();
	}
	public Float getClearPercent(int teamNumber) {
		return teams[teamNumber-1].getClearPercent();
	}
	public int getTwoBarPassAttempts(int teamNumber) {
		return teams[teamNumber-1].getTwoBarPassAttempts();
	}
	public int getTwoBarPassCompletes(int teamNumber) {
		return teams[teamNumber-1].getTwoBarPassCompletes();
	}
	public Float getTwoBarPassPercent(int teamNumber) {
		return teams[teamNumber-1].getTwoBarPassPercent();
	}
	public int getScoring(int teamNumber) {
		return teams[teamNumber-1].getScoring();
	}
	public int getThreeBarScoring(int teamNumber) {
		return teams[teamNumber-1].getThreeBarScoring();
	}
	public int getFiveBarScoring(int teamNumber) {
		return teams[teamNumber-1].getFiveBarScoring();
	}
	public int getTwoBarScoring(int teamNumber) {
		return teams[teamNumber-1].getTwoBarScoring();
	}
	public int getShotsOnGoal(int teamNumber) {
		return teams[teamNumber-1].getShotsOnGoal();
	}
	public int getStuffs(int teamNumber) {
		return teams[teamNumber-1].getStuffs();
	}
	public int getBreaks(int teamNumber) {
		return teams[teamNumber-1].getBreaks();
	}
	public String getForwardName(int teamNumber) {
		return teams[teamNumber-1].getForwardName();
	}
	public String getGoalieName(int teamNumber) {
		return teams[teamNumber-1].getGoalieName();
	}
}
