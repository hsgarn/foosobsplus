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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTextField;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;

import net.twasi.obsremotejava.OBSRemoteController;

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
	private StatsDisplayPanel statsDisplayPanel;
	private TimerController timerController;
	private LastScoredClock lastScored1Clock;
	private LastScoredClock lastScored2Clock;
	private GameClock gameClock;
	private Team teams[] = new Team[] {null,null};
	private TeamPanel teamPanels[] = new TeamPanel[] {null,null};
    private MainController mainController;
    
	public TeamController(OBSInterface obsInterface, Settings settings, Team team1, Team team2, Match match, TeamPanel teamPanel1, TeamPanel teamPanel2, SwitchPanel switchPanel, MatchPanel matchPanel, GameTableWindowPanel gameTableWindowPanel, StatsDisplayPanel statsDisplayPanel, TimerController timerController, LastScoredClock lastScored1Clock, LastScoredClock lastScored2Clock, GameClock gameClock, MainController mainController) {
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
		this.statsDisplayPanel = statsDisplayPanel;
		this.timerController = timerController;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.gameClock = gameClock;
		this.teams[0] = team1;
		this.teams[1] = team2;
		this.teamPanels[0] = teamPanel1;
		this.teamPanels[1] = teamPanel2;
		this.mainController = mainController;
		
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
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
//			updateGameTables();
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
	public void setTeam1ForwardName(String name) {
		team1.setForwardName(name);
		teamPanel1.updateForwardName(name);
	}
	public void setTeam1GoalieName(String name) {
		team1.setGoalieName(name);
		teamPanel1.updateGoalieName(name);
	}
	public void setTeam2ForwardName(String name) {
		team2.setForwardName(name);
		teamPanel2.updateForwardName(name);
	}
	public void setTeam2GoalieName(String name) {
		team2.setGoalieName(name);
		teamPanel2.updateGoalieName(name);
	}
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
//		boolean matchWon = false;
//		matchWon = match.incrementGameCount(teams[teamNumber-1]);
		match.syncCurrentGameNumber();
		teamPanels[teamNumber-1].updateGameCount(teams[teamNumber-1].getGameCount());
//		if(!matchWon) startGameTimer();
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
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startGameTimer() {
		timerController.startGameTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startShotTimer() {
		timerController.startShotTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
			mainController.setFocusOnCode();
		}
	}
	public void startPassTimer() {
		timerController.startPassTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startTimeOutTimer() {
		timerController.startTimeOutTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startRecallTimer() {
		timerController.startRecallTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
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
	public void switchPlayer1() {
		String tmp = team1.getForwardName();
		team1.setForwardName(team2.getForwardName());
		team2.setForwardName(tmp);
		teamPanel1.updateForwardName(team1.getForwardName());
		teamPanel2.updateForwardName(team2.getForwardName());
		updateGameTables();
	}
	public void switchPlayer2() {
		String tmp2 = team1.getGoalieName();
		team1.setGoalieName(team2.getGoalieName());
		team2.setGoalieName(tmp2);
		teamPanel1.updateGoalieName(team1.getGoalieName());
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
		match.resetGameCounts();
		match.setCurrentScoreTeam1(team1.getScore());
		match.setCurrentScoreTeam2(team2.getScore());
		updateGameTables();
		displayAll();
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
	public void clearAll() {
		team1.clearAll();
		team2.clearAll();
		match.clearAll();
		resetAll();
		displayAll();
		switchPanel.setLastScored(settings.getLastScoredStrings()[0]);
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
		match.resetMatch(); // probably should go through matchController, but it is not accessible here
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
		Map<String, Entry<Team, String>> teamMethodMap = new HashMap<String, Entry<Team, String>>();
		//Map<String is Source Name:Score 1, Entry<Team is Team Class Instance:team1|team2, String is Method Name to call:setScore>
		teamMethodMap.put(settings.getTeamNameSource(1), new SimpleEntry<Team, String>(team1, "setTeamName"));
		teamMethodMap.put(settings.getTeamNameSource(2), new SimpleEntry<Team, String>(team2, "setTeamName"));
		teamMethodMap.put(settings.getTeamForwardSource(1), new SimpleEntry<Team, String>(team1, "setForwardName"));
		teamMethodMap.put(settings.getTeamForwardSource(2), new SimpleEntry<Team, String>(team2, "setForwardName"));
		teamMethodMap.put(settings.getTeamGoalieSource(1), new SimpleEntry<Team, String>(team1, "setGoalieName"));
		teamMethodMap.put(settings.getTeamGoalieSource(2), new SimpleEntry<Team, String>(team2, "setGoalieName"));
		teamMethodMap.put(settings.getTeamNameSource(1), new SimpleEntry<Team, String>(team1, "setTeamName"));
		teamMethodMap.put(settings.getTeamNameSource(2), new SimpleEntry<Team, String>(team2, "setTeamName"));
		teamMethodMap.put(settings.getScoreSource(1), new SimpleEntry<Team, String>(team1, "setScore"));
		teamMethodMap.put(settings.getScoreSource(2), new SimpleEntry<Team, String>(team2, "setScore"));
		teamMethodMap.put(settings.getGameCountSource(1), new SimpleEntry<Team, String>(team1, "setGameCount"));
        teamMethodMap.put(settings.getGameCountSource(2), new SimpleEntry<Team, String>(team2, "setGameCount"));
		teamMethodMap.put(settings.getTimeOutSource(1), new SimpleEntry<Team, String>(team1, "setTimeOutCount"));
		teamMethodMap.put(settings.getTimeOutSource(2), new SimpleEntry<Team, String>(team2, "setTimeOutCount"));
		teamMethodMap.put(settings.getPassAttemptsSource(1), new SimpleEntry<Team, String>(team1, "setPassAttempts"));
		teamMethodMap.put(settings.getPassAttemptsSource(2), new SimpleEntry<Team, String>(team2, "setPassAttempts"));
		teamMethodMap.put(settings.getShotAttemptsSource(1), new SimpleEntry<Team, String>(team1, "setShotAttempts"));
		teamMethodMap.put(settings.getShotAttemptsSource(2), new SimpleEntry<Team, String>(team2, "setShotAttempts"));
		teamMethodMap.put(settings.getClearAttemptsSource(1), new SimpleEntry<Team, String>(team1, "setClearAttempts"));
		teamMethodMap.put(settings.getClearAttemptsSource(2), new SimpleEntry<Team, String>(team2, "setClearAttempts"));
		teamMethodMap.put(settings.getPassCompletesSource(1), new SimpleEntry<Team, String>(team1, "setPassCompletes"));
		teamMethodMap.put(settings.getPassCompletesSource(2), new SimpleEntry<Team, String>(team2, "setPassCompletes"));
		teamMethodMap.put(settings.getShotCompletesSource(1), new SimpleEntry<Team, String>(team1, "setShotCompletes"));
		teamMethodMap.put(settings.getShotCompletesSource(2), new SimpleEntry<Team, String>(team2, "setShotCompletes"));
		teamMethodMap.put(settings.getClearCompletesSource(1), new SimpleEntry<Team, String>(team1, "setClearCompletes"));
		teamMethodMap.put(settings.getClearCompletesSource(2), new SimpleEntry<Team, String>(team2, "setClearCompletes"));
		teamMethodMap.put(settings.getPassPercentSource(1), new SimpleEntry<Team, String>(team1, "setPassPercent"));
		teamMethodMap.put(settings.getPassPercentSource(2), new SimpleEntry<Team, String>(team2, "setPassPercent"));
		teamMethodMap.put(settings.getShotPercentSource(1), new SimpleEntry<Team, String>(team1, "setShotPercent"));
		teamMethodMap.put(settings.getShotPercentSource(2), new SimpleEntry<Team, String>(team2, "setShotPercent"));
		teamMethodMap.put(settings.getClearPercentSource(1), new SimpleEntry<Team, String>(team1, "setClearPercent"));
		teamMethodMap.put(settings.getClearPercentSource(2), new SimpleEntry<Team, String>(team2, "setClearPercent"));
		teamMethodMap.put(settings.getTwoBarPassAttemptsSource(1), new SimpleEntry<Team, String>(team1, "setTwoBarPassAttempts"));
		teamMethodMap.put(settings.getTwoBarPassAttemptsSource(2), new SimpleEntry<Team, String>(team2, "setTwoBarPassAttempts"));
		teamMethodMap.put(settings.getTwoBarPassCompletesSource(1), new SimpleEntry<Team, String>(team1, "setTwoBarPassCompletes"));
		teamMethodMap.put(settings.getTwoBarPassCompletesSource(2), new SimpleEntry<Team, String>(team2, "setTwoBarPassCompletes"));
		teamMethodMap.put(settings.getTwoBarPassPercentSource(1), new SimpleEntry<Team, String>(team1, "setTwoBarPassPercent"));
		teamMethodMap.put(settings.getTwoBarPassPercentSource(2), new SimpleEntry<Team, String>(team2, "setTwoBarPassPercent"));
		teamMethodMap.put(settings.getScoringSource(1), new SimpleEntry<Team, String>(team1, "setScoring"));
		teamMethodMap.put(settings.getScoringSource(2), new SimpleEntry<Team, String>(team2, "setScoring"));
		teamMethodMap.put(settings.getThreeBarScoringSource(1), new SimpleEntry<Team, String>(team1, "setThreeBarScoring"));
		teamMethodMap.put(settings.getThreeBarScoringSource(2), new SimpleEntry<Team, String>(team2, "setThreeBarScoring"));
		teamMethodMap.put(settings.getFiveBarScoringSource(1), new SimpleEntry<Team, String>(team1, "setFiveBarScoring"));
		teamMethodMap.put(settings.getFiveBarScoringSource(2), new SimpleEntry<Team, String>(team2, "setFiveBarScoring"));
		teamMethodMap.put(settings.getTwoBarScoringSource(1), new SimpleEntry<Team, String>(team1, "setTwoBarScoring"));
		teamMethodMap.put(settings.getTwoBarScoringSource(2), new SimpleEntry<Team, String>(team2, "setTwoBarScoring"));
		teamMethodMap.put(settings.getShotsOnGoalSource(1), new SimpleEntry<Team, String>(team1, "setShotsOnGoal"));
		teamMethodMap.put(settings.getShotsOnGoalSource(2), new SimpleEntry<Team, String>(team2, "setShotsOnGoal"));
		teamMethodMap.put(settings.getStuffsSource(1), new SimpleEntry<Team, String>(team1, "setStuffs"));
		teamMethodMap.put(settings.getStuffsSource(2), new SimpleEntry<Team, String>(team2, "setStuffs"));
		teamMethodMap.put(settings.getBreaksSource(1), new SimpleEntry<Team, String>(team1, "setBreaks"));
		teamMethodMap.put(settings.getBreaksSource(2), new SimpleEntry<Team, String>(team2, "setBreaks"));
		teamMethodMap.put(settings.getAcesSource(1), new SimpleEntry<Team, String>(team1, "setAces"));
		teamMethodMap.put(settings.getAcesSource(2), new SimpleEntry<Team, String>(team2, "setAces"));
		teamMethodMap.put(settings.getResetSource(1), new SimpleEntry<Team, String>(team1, "setReset"));
		teamMethodMap.put(settings.getResetSource(2), new SimpleEntry<Team, String>(team2, "setReset"));
		teamMethodMap.put(settings.getWarnSource(1), new SimpleEntry<Team, String>(team1, "setWarn"));
		teamMethodMap.put(settings.getWarnSource(2), new SimpleEntry<Team, String>(team2, "setWarn"));
		OBS obs = OBS.getInstance();
		OBSRemoteController obsController = obs.getController();
		if (!(obsController==null) && !obsController.isFailed())
		{
			for (String source : teamMethodMap.keySet()) {
				obsController.getTextGDIPlusProperties(source, response -> {
					// response comes back with null entries if source does not exist in OBS
					if (!((response.getSource() == null) || (response.getText() == null))) {
						setTextFromSource(response.getSource(),response.getText(), teamMethodMap);
					}
				});
				// This below sleep logic is apparently enough to keep the callbacks from overwriting themselves.  If the below is removed, then
				// some of the callbacks get overwritten and so do not properly update. tried to figure out a way to queue the responses, but
				// cannot find where the actual text in the response gets set.  Need to explore the Session class more.
				try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
				
			try {
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
	}
	private void setTextFromSource(String source, String text, Map<String, Entry<Team, String>> methodMap) {
		Entry<Team, String> methodMapEntry = methodMap.get(source);
		if(source.equals(settings.getResetSource(1)) || source.equals(settings.getResetSource(2)) || source.equals(settings.getWarnSource(1)) || source.equals(settings.getWarnSource(2)))
		{
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), boolean.class);
				method.invoke(methodMapEntry.getKey(), text.length()>0);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}			
		} else {
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), String.class);
				method.invoke(methodMapEntry.getKey(), text);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
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
	public int getAces(int teamNumber) {
		return teams[teamNumber-1].getAces();
	}
	public String getForwardName(int teamNumber) {
		return teams[teamNumber-1].getForwardName();
	}
	public String getGoalieName(int teamNumber) {
		return teams[teamNumber-1].getGoalieName();
	}
	public String getTeamName(int teamNumber) {
		return teams[teamNumber-1].getTeamName();
	}
}
