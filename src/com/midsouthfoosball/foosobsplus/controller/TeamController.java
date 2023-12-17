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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import io.obswebsocket.community.client.OBSRemoteController;

public class TeamController {
	private OBSInterface obsInterface;
	private Settings settings;
	private Team team1;
	private Team team2;
	private Team team3;
	private Match match;
	private TeamPanel teamPanel1;
	private TeamPanel teamPanel2;
	private TeamPanel teamPanel3;
	private SwitchPanel switchPanel;
	private MatchPanel matchPanel;
	private GameTableWindowPanel gameTableWindowPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private TimerController timerController;
	private LastScoredClock lastScored1Clock;
	private LastScoredClock lastScored2Clock;
	private LastScoredClock lastScored3Clock;
	private GameClock gameClock;
    private MainController mainController;
    private Map<Integer, Team> teamsMap = new HashMap<>();
    private Map<Integer, TeamPanel> teamPanelsMap = new HashMap<>();
    private Map<Integer, LastScoredClock> lastScoredClocksMap = new HashMap<>();
	private static transient Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
   
	public TeamController(OBSInterface obsInterface, Settings settings, Team team1, Team team2, Team team3, Match match, TeamPanel teamPanel1, TeamPanel teamPanel2, TeamPanel teamPanel3, SwitchPanel switchPanel, MatchPanel matchPanel, GameTableWindowPanel gameTableWindowPanel, StatsDisplayPanel statsDisplayPanel, TimerController timerController, LastScoredClock lastScored1Clock, LastScoredClock lastScored2Clock, LastScoredClock lastScored3Clock, GameClock gameClock, MainController mainController) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.match = match;
		this.teamPanel1 = teamPanel1;
		this.teamPanel2 = teamPanel2;
		this.teamPanel3 = teamPanel3;
		this.switchPanel = switchPanel;
		this.matchPanel = matchPanel;
		this.gameTableWindowPanel = gameTableWindowPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.timerController = timerController;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.lastScored3Clock = lastScored3Clock;
		this.gameClock = gameClock;
		this.mainController = mainController;
		
		////// Team Panel Listeners Methods //////
		
		this.teamPanel1.addTeamNameListener(new TeamNameListener());
		this.teamPanel2.addTeamNameListener(new TeamNameListener());
		this.teamPanel3.addTeamNameListener(new TeamNameListener());
		this.teamPanel1.addTeamNameFocusListener(new TeamNameFocusListener());
		this.teamPanel2.addTeamNameFocusListener(new TeamNameFocusListener());
		this.teamPanel3.addTeamNameFocusListener(new TeamNameFocusListener());
		this.teamPanel1.addTeamNameMouseListener(new TeamNameMouseListener());
		this.teamPanel2.addTeamNameMouseListener(new TeamNameMouseListener());
		this.teamPanel3.addTeamNameMouseListener(new TeamNameMouseListener());
		this.teamPanel1.addForwardNameListener(new ForwardNameListener());
		this.teamPanel2.addForwardNameListener(new ForwardNameListener());
		this.teamPanel3.addForwardNameListener(new ForwardNameListener());
		this.teamPanel1.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.teamPanel2.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.teamPanel3.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.teamPanel1.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.teamPanel2.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.teamPanel3.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.teamPanel1.addGoalieNameListener(new GoalieNameListener());
		this.teamPanel2.addGoalieNameListener(new GoalieNameListener());
		this.teamPanel3.addGoalieNameListener(new GoalieNameListener());
		this.teamPanel1.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.teamPanel2.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.teamPanel3.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.teamPanel1.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.teamPanel2.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.teamPanel3.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.teamPanel1.addScoreListener(new ScoreListener());
		this.teamPanel2.addScoreListener(new ScoreListener());
		this.teamPanel3.addScoreListener(new ScoreListener());
		this.teamPanel1.addScoreFocusListener(new ScoreFocusListener());
		this.teamPanel2.addScoreFocusListener(new ScoreFocusListener());
		this.teamPanel3.addScoreFocusListener(new ScoreFocusListener());
		this.teamPanel1.addGameCountListener(new GameCountListener());
		this.teamPanel2.addGameCountListener(new GameCountListener());
		this.teamPanel3.addGameCountListener(new GameCountListener());
		this.teamPanel1.addMatchCountListener(new MatchCountListener());
		this.teamPanel2.addMatchCountListener(new MatchCountListener());
		this.teamPanel3.addMatchCountListener(new MatchCountListener());
		this.teamPanel1.addGameCountFocusListener(new GameCountFocusListener());
		this.teamPanel2.addGameCountFocusListener(new GameCountFocusListener());
		this.teamPanel3.addGameCountFocusListener(new GameCountFocusListener());
		this.teamPanel1.addMatchCountFocusListener(new MatchCountFocusListener());
		this.teamPanel2.addMatchCountFocusListener(new MatchCountFocusListener());
		this.teamPanel3.addMatchCountFocusListener(new MatchCountFocusListener());
		this.teamPanel1.addTimeOutCountListener(new TimeOutCountListener());
		this.teamPanel2.addTimeOutCountListener(new TimeOutCountListener());
		this.teamPanel3.addTimeOutCountListener(new TimeOutCountListener());
		this.teamPanel1.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.teamPanel2.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.teamPanel3.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.lastScored1Clock.addLastScoredClockTimerListener(new LastScoredClockTimerListener());
		this.lastScored2Clock.addLastScoredClockTimerListener(new LastScoredClockTimerListener());
		this.lastScored3Clock.addLastScoredClockTimerListener(new LastScoredClockTimerListener());
		
		teamsMap.put(1, team1);
		teamsMap.put(2, team2);
		teamsMap.put(3, team3);
		
		teamPanelsMap.put(1, teamPanel1);
		teamPanelsMap.put(2, teamPanel2);
		teamPanelsMap.put(3, teamPanel3);
		
		lastScoredClocksMap.put(1,  lastScored1Clock);
		lastScoredClocksMap.put(2,  lastScored2Clock);
		lastScoredClocksMap.put(3,  lastScored3Clock);
	}
	
	////// Team Panel Listener Objects //////

	private class TeamNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			teamNameChange(txt);
		}
	}

	private class TeamNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			teamNameChange(txt);
		}
	}
	private static String capitalizeWords(String str) {
		if(str.isEmpty()) {return str;}
		String words[]=str.split("\\s");
		String capitalizeWord="";
		for(String w:words) {
			String first=w.substring(0,1);
			String afterfirst=w.substring(1);
			capitalizeWord+=first.toUpperCase()+afterfirst+" ";
		}
		return capitalizeWord.trim();
	}
	private void teamNameChange(JTextField txt) {
		String teamName;
		if(settings.getAutoCapNames()==1) {
			teamName = capitalizeWords(txt.getText());
		} else {
			teamName = txt.getText();
		}
		int teamNumber = convertToTeamNumber(txt.getName());
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setTeamName(teamName);
			teamPanel.updateTeamName(teamName);
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
			if (match.getWinState() > 0) {
				resetForNewGame();
			}
			updateGameTables();
		}
	}
	private class TeamNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			int teamNumber = convertToTeamNumber(txt.getName());
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (teamPanel != null) {
				teamPanel.selectTeamName();
			}
		}
	}
	private class ForwardNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			forwardNameChange(txt);
		}
	}
	private class ForwardNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			forwardNameChange(txt);
		}
	}
	private void forwardNameChange(JTextField txt) {
		String forwardName;
		if(settings.getAutoCapNames()==1) {
			forwardName = capitalizeWords(txt.getText());
		} else {
			forwardName = txt.getText();
		}
		int teamNumber = convertToTeamNumber(txt.getName());
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setForwardName(forwardName);
			teamPanel.updateForwardName(forwardName);
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
			checkResetForNewGame();
			updateGameTables();
		}
	}
	private class ForwardNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			int teamNumber = convertToTeamNumber(txt.getName());
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (teamPanel != null) {
				teamPanel.selectForwardName();
				statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
				updateGameTables();
			}
		}
	}
	private class GoalieNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			goalieNameChange(txt);
		}
	}
	private class GoalieNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			goalieNameChange(txt);
		}
	}
	private void goalieNameChange(JTextField txt) {
		String goalieName;
		if(settings.getAutoCapNames()==1) {
			goalieName = capitalizeWords(txt.getText());
		} else {
			goalieName = txt.getText();
		}
		int teamNumber = convertToTeamNumber(txt.getName());
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setGoalieName(goalieName);
			teamPanel.updateGoalieName(goalieName);
			statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
			checkResetForNewGame();
			updateGameTables();
		}
	}
	private class GoalieNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			int teamNumber = convertToTeamNumber(txt.getName());
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (teamPanel != null) {
				teamPanel.selectGoalieName();
				statsDisplayPanel.updateTeams(teamNumber,getForwardName(teamNumber) + "/" + getGoalieName(teamNumber),getTeamName(teamNumber));
				updateGameTables();
			}
		}
	}
	private class ScoreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String score = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setScore(score);
				teamPanel.updateScore(score);
				updateGameTables();
			}
		}
	}
	private class ScoreFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String score = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setScore(score);
				teamPanel.updateScore(score);
				updateGameTables();
			}
		}
	}
	private class GameCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String gameCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setGameCount(gameCount);
				teamPanel.updateGameCount(gameCount);
				updateGameTables();
			}
		}
	}
	private class GameCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String gameCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setGameCount(gameCount);
				teamPanel.updateGameCount(gameCount);
				updateGameTables();
			}
		}
	}
	private class MatchCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String matchCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setMatchCount(matchCount);
				teamPanel.updateMatchCount(matchCount);
				updateGameTables();
			}
		}
	}
	private class MatchCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String matchCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setMatchCount(matchCount);
				teamPanel.updateMatchCount(matchCount);
				updateGameTables();
			}
		}
	}
	private class TimeOutCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String timeOutCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setTimeOutCount(timeOutCount);
				teamPanel.updateTimeOutCount(timeOutCount);
			}
		}
	}
	private class TimeOutCountFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String timeOutCount = convertNumbers(txt.getText());
			int teamNumber = convertToTeamNumber(txt.getName());
			Team team = teamsMap.getOrDefault(teamNumber, null);
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (team != null) {
				team.setTimeOutCount(timeOutCount);
				teamPanel.updateTimeOutCount(timeOutCount);
			}
		}
	}

	private class LastScoredClockTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateLastScoredTimes();
		}
	}
	
	////// Utility Methods \\\\\\
	private static int convertToTeamNumber(String name) {
		String numericPart = name.replaceAll("[^0-9]", "");
		try {
			return Integer.parseInt(numericPart);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid team name: " + name);
		}
	}
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
	public void setTeam3ForwardName(String name) {
		team3.setForwardName(name);
		teamPanel3.updateForwardName(name);
	}
	public void setTeam3GoalieName(String name) {
		team3.setGoalieName(name);
		teamPanel3.updateGoalieName(name);
	}
	public void updateLastScoredTimes() {
		teamPanel1.updateLastScoredTime(lastScored1Clock.getLastScoredTime());
		teamPanel2.updateLastScoredTime(lastScored2Clock.getLastScoredTime());
		teamPanel3.updateLastScoredTime(lastScored3Clock.getLastScoredTime());
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
		int teamNumber = convertToTeamNumber(name);
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			String[] names = team.switchPositions();
			String forwardName = names[0];
			String goalieName = names[1];
			teamPanel.updateNames(forwardName, goalieName);
			checkResetForNewGame();
			displayAll();
			updateGameTables();
		}
	};
	public void cutThroatGameRotate() {
		Team tmp = team2;
		team2 = team3;
		team3 = tmp;
		displayAll();
	};
	public int incrementScore(int teamNumber) {
		int winState = 0;
		Team team = teamsMap.getOrDefault(teamNumber, null);
		LastScoredClock lastScoredClock = lastScoredClocksMap.getOrDefault(teamNumber, null);
		if (team != null) {
			winState = match.incrementScore(teamNumber, gameClock.getGameTime());
			lastScoredClock.startLastScoredTimer();
		}
		resetTimer();
		if(winState==1) {
			startGameTimer();
			gameClock.stopGameTimer();
		}
		displayAll();
		return winState;
	}
	public void decrementScore(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			match.decrementScore(teamNumber);
			teamPanel.updateScore(team.getScore());
			updateGameTables();
		}
	}
	public void incrementGameCount(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			boolean matchWon = match.incrementGameCount(team);
			match.syncCurrentGameNumber();
			teamPanel.updateGameCount(team.getGameCount());
			if(matchWon) {
				resetKingSeats();
			} else {
				startGameTimer();
			}
			updateGameTables();
		}
	}
	public void decrementGameCount(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.decrementGameCount();
			match.syncCurrentGameNumber();
			teamPanel.updateGameCount(team.getGameCount());
			updateGameTables();
		}
	}
	public void incrementMatchCount(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			match.incrementMatchCount(team);
			teamPanel.updateMatchCount(team.getMatchCount());
//			if(matchWon) {
//				resetKingSeats();
//			} else {
//				startGameTimer();
//			}
//			updateGameTables();
		}
	}
	public void decrementMatchCount(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.decrementMatchCount();
//			match.syncCurrentGameNumber();
			teamPanel.updateMatchCount(team.getMatchCount());
//			updateGameTables();
		}
	}
	public void callTimeOut(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.callTimeOut();
			teamPanel.updateTimeOutCount(team.getTimeOutCount());
			startTimeOutTimer();
		}
	}
	public void restoreTimeOut(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.restoreTimeOut();
			teamPanel.updateTimeOutCount(team.getTimeOutCount());
		}
	}
	public void resetTimer() {
		timerController.resetTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startGameTimer() {
		checkResetForNewGame();
		timerController.startGameTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startShotTimer() {
		checkResetForNewGame();
		timerController.startShotTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
			mainController.setFocusOnCode();
		}
	}
	public void checkResetForNewGame() {
		int winState = match.getWinState();
		if (winState > 0) {
			resetForNewGame();
		}
	}
	public void resetForNewGame() {
		resetScores();
		resetTimeOuts();
		resetResetWarns();
		if (match.getWinState()==2) {
			resetGameCounts();
		}
	}
	public void startPassTimer() {
		checkResetForNewGame();
		timerController.startPassTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startTimeOutTimer() {
		checkResetForNewGame();
		timerController.startTimeOutTimer();
		if (!mainController.getTimerWindowSelected()) {
			mainController.showTimerWindow();
		}
	}
	public void startRecallTimer() {
		checkResetForNewGame();
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
		checkResetForNewGame();
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
	public void switchPlayer(int playerNumber) {
		checkResetForNewGame();
		if (playerNumber == 1) {
			String tmp = team1.getForwardName();
			team1.setForwardName(team2.getForwardName());
			team2.setForwardName(tmp);
			teamPanel1.updateForwardName(team1.getForwardName());
			teamPanel2.updateForwardName(team2.getForwardName());
		} else {
			String tmp2 = team1.getGoalieName();
			team1.setGoalieName(team2.getGoalieName());
			team2.setGoalieName(tmp2);
			teamPanel1.updateGoalieName(team1.getGoalieName());
			teamPanel2.updateGoalieName(team2.getGoalieName());
		}
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
	public void switchMatchCounts() {
		int tmp = team1.getMatchCount();
		team1.setMatchCount(team2.getMatchCount());
		team2.setMatchCount(tmp);
		teamPanel1.updateMatchCount(team1.getMatchCount());
		teamPanel2.updateMatchCount(team2.getMatchCount());
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
		team3.setTeamName("");
		team1.setForwardName("");
		team2.setForwardName("");
		team3.setForwardName("");
		team1.setGoalieName("");
		team2.setGoalieName("");
		team3.setGoalieName("");
		teamPanel1.updateTeamName(team1.getTeamName());
		teamPanel2.updateTeamName(team2.getTeamName());
		teamPanel3.updateTeamName(team3.getTeamName());
		teamPanel1.updateForwardName(team1.getForwardName());
		teamPanel2.updateForwardName(team2.getForwardName());
		teamPanel3.updateForwardName(team3.getForwardName());
		teamPanel1.updateGoalieName(team1.getGoalieName());
		teamPanel2.updateGoalieName(team2.getGoalieName());
		teamPanel3.updateGoalieName(team3.getGoalieName());
		updateGameTables();
	}
	public void resetScores() {
		resetTheScores();
		updateGameTables();
		displayAll();
	}
	public void resetScoresFromButton() {
		resetTheScores();
		match.setCurrentScoreTeam1(team1.getScore());
		match.setCurrentScoreTeam2(team2.getScore());
		match.setCurrentScoreTeam3(team3.getScore());
		updateGameTables();
		displayAll();
	}
	private void resetTheScores() {
		team1.setScore(0);
		team2.setScore(0);
		team3.setScore(0);
		teamPanel1.updateScore(team1.getScore());
		teamPanel2.updateScore(team2.getScore());
		teamPanel3.updateScore(team3.getScore());
	}
	public void resetGameCounts() {
		team1.setGameCount(0);
		team2.setGameCount(0);
		team3.setGameCount(0);
		teamPanel1.updateGameCount(team1.getGameCount());
		teamPanel2.updateGameCount(team2.getGameCount());
		teamPanel3.updateGameCount(team3.getGameCount());
		match.resetGameCounts();
		match.setCurrentScoreTeam1(team1.getScore());
		match.setCurrentScoreTeam2(team2.getScore());
		match.setCurrentScoreTeam3(team3.getScore());
		updateGameTables();
		displayAll();
	}
	public void resetMatchCounts() {
		team1.setMatchCount(0);
		team2.setMatchCount(0);
		team3.setMatchCount(0);
		updateGameTables();
		displayAll();
	}
	public void resetTimeOuts() {
		team1.resetTimeOuts();
		team2.resetTimeOuts();
		team3.resetTimeOuts();
		teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
		teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
		teamPanel3.updateTimeOutCount(team3.getTimeOutCount());
	}
	public void resetResetWarns() {
		team1.setReset(false);
		team2.setReset(false);
		team3.setReset(false);
		team1.setWarn(false);
		team2.setWarn(false);
		team3.setWarn(false);
		teamPanel1.updateReset(team1.getReset());
		teamPanel2.updateReset(team2.getReset());
		teamPanel3.updateReset(team3.getReset());
		teamPanel1.updateWarn(team1.getWarn());
		teamPanel2.updateWarn(team2.getWarn());
		teamPanel3.updateWarn(team3.getWarn());
	}
	public void resetKingSeats() {
		team1.setKingSeat(false);
		team2.setKingSeat(false);
		team3.setKingSeat(false);
		teamPanel1.updateKingSeat(team1.getKingSeat());
		teamPanel2.updateKingSeat(team2.getKingSeat());
		teamPanel3.updateKingSeat(team3.getKingSeat());
	}
	public void resetStats() {
		team1.resetStats();
		team2.resetStats();
		team3.resetStats();
	}
	public void clearAll() {
		checkResetForNewGame();
		team1.clearAll();
		team2.clearAll();
		team3.clearAll();
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
		resetKingSeats();
		resetStats();
		resetLastScoredClocks();
		resetLastScored();
		team1.writeAll();
		team2.writeAll();
		team3.writeAll();
		match.resetMatch(); // probably should go through matchController, but it is not accessible here
		updateGameTables();
	}
	public void clearTeamFromButton(int teamNbr) {
		Team team = teamsMap.getOrDefault(teamNbr, null);
		if (team != null) {
			team.clearAll();
			match.setCurrentScoreForTeam(teamNbr, team.getScore());
		}
		displayAll();
		match.clearAll();
		updateGameTables();
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
	}
	private void resetLastScored() {
		match.setLastScored(0);
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
	}
	private void resetLastScoredClocks() {
		lastScored1Clock.startLastScoredTimer();
		lastScored2Clock.startLastScoredTimer();
		lastScored3Clock.startLastScoredTimer();
	}
	public void displayAll() {
		String forwardName1;
		String goalieName1;
		String forwardName2;
		String goalieName2;
		String forwardName3;
		String goalieName3;
		String teamName1 = team1.getTeamName();
		int score1 = team1.getScore();
		int gameCount1 = team1.getGameCount();
		int matchCount1 = team1.getMatchCount();
		int timeOutCount1 = team1.getTimeOutCount();
		boolean isReset1 = team1.getReset();
		boolean isWarn1 = team1.getWarn();
		boolean isKingSeat1 = team1.getKingSeat();
		String teamName2 = team2.getTeamName();
		int score2 = team2.getScore();
		int gameCount2 = team2.getGameCount();
		int matchCount2 = team2.getMatchCount();
		int timeOutCount2 = team2.getTimeOutCount();
		boolean isReset2 = team2.getReset();
		boolean isWarn2 = team2.getWarn();
		boolean isKingSeat2 = team2.getKingSeat();
		String teamName3 = team3.getTeamName();
		int score3 = team3.getScore();
		int gameCount3 = team3.getGameCount();
		int matchCount3 = team3.getMatchCount();
		int timeOutCount3 = team3.getTimeOutCount();
		boolean isReset3 = team3.getReset();
		boolean isWarn3 = team3.getWarn();
		boolean isKingSeat3 = team3.getKingSeat();
		forwardName1 = team1.getForwardName();
		goalieName1 = team1.getGoalieName();
		forwardName2 = team2.getForwardName();
		goalieName2 = team2.getGoalieName();
		forwardName3 = team3.getForwardName();
		goalieName3 = team3.getGoalieName();
		team1.writeAll();
		team2.writeAll();
		team3.writeAll();
		teamPanel1.displayAllFields(teamName1, forwardName1, goalieName1, score1, gameCount1, matchCount1, timeOutCount1, isReset1, isWarn1, isKingSeat1);
		teamPanel2.displayAllFields(teamName2, forwardName2, goalieName2, score2, gameCount2, matchCount2, timeOutCount2, isReset2, isWarn2, isKingSeat2);
		teamPanel3.displayAllFields(teamName3, forwardName3, goalieName3, score3, gameCount3, matchCount3, timeOutCount3, isReset3, isWarn3, isKingSeat3);
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		updateGameTables();
	}
	public void cutThroatPointRotate() {
		Team tmp = team1;
		team1 = team2;
		team2 = team3;
		team3 = tmp;
		displayAll();
	}
	public void updateGameTables() {
		matchPanel.setGameWinners(match.getGameWinners());
		matchPanel.setMatchWinner(match.getMatchWinner());
		matchPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getScoresTeam3(), match.getTimes(), match.getCurrentGameNumber());
		gameTableWindowPanel.setTeams(getForwardName(1) + " / " + getGoalieName(1) + ":",getForwardName(2) + " / " + getGoalieName(2) + ":", getForwardName(3) + " / " + getGoalieName(3));
		gameTableWindowPanel.setGameWinners(match.getGameWinners());
		gameTableWindowPanel.setMatchWinner(match.getMatchWinner());
		gameTableWindowPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getScoresTeam3(), match.getTimes(), match.getCurrentGameNumber());
	}
	public void toggleReset(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setReset(!team.getReset());
			teamPanel.updateReset(team.getReset());
		}
	}
	public void toggleWarn(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setWarn(!team.getWarn());
			teamPanel.updateWarn(team.getWarn());
		}
	}
	public void toggleKingSeat(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			if (!(team.getKingSeat())) {
				teamsMap.forEach((k,v) -> v.setKingSeat(false));
				teamPanelsMap.forEach((k,v) -> v.updateKingSeat(false));
			}
			team.setKingSeat(!team.getKingSeat());
			teamPanel.updateKingSeat(team.getKingSeat());
		}
	}
	public void fetchAll() {
		Map<String, Entry<Team, String>> teamMethodMap = new HashMap<String, Entry<Team, String>>();
		//Map<String is Source Name:Score 1, Entry<Team is Team Class Instance:team1|team2, String is Method Name to call:setScore>
		teamMethodMap.put(settings.getTeamNameSource("1"), new SimpleEntry<Team, String>(team1, "setTeamName"));
		teamMethodMap.put(settings.getTeamNameSource("2"), new SimpleEntry<Team, String>(team2, "setTeamName"));
		teamMethodMap.put(settings.getTeamNameSource("3"), new SimpleEntry<Team, String>(team3, "setTeamName"));
		teamMethodMap.put(settings.getTeamForwardSource("1"), new SimpleEntry<Team, String>(team1, "setForwardName"));
		teamMethodMap.put(settings.getTeamForwardSource("2"), new SimpleEntry<Team, String>(team2, "setForwardName"));
		teamMethodMap.put(settings.getTeamForwardSource("3"), new SimpleEntry<Team, String>(team3, "setForwardName"));
		teamMethodMap.put(settings.getTeamGoalieSource("1"), new SimpleEntry<Team, String>(team1, "setGoalieName"));
		teamMethodMap.put(settings.getTeamGoalieSource("2"), new SimpleEntry<Team, String>(team2, "setGoalieName"));
		teamMethodMap.put(settings.getTeamGoalieSource("3"), new SimpleEntry<Team, String>(team3, "setGoalieName"));
		teamMethodMap.put(settings.getTeamScoreSource("1"), new SimpleEntry<Team, String>(team1, "setScore"));
		teamMethodMap.put(settings.getTeamScoreSource("2"), new SimpleEntry<Team, String>(team2, "setScore"));
		teamMethodMap.put(settings.getTeamScoreSource("3"), new SimpleEntry<Team, String>(team3, "setScore"));
		teamMethodMap.put(settings.getTeamGameCountSource("1"), new SimpleEntry<Team, String>(team1, "setGameCount"));
        teamMethodMap.put(settings.getTeamGameCountSource("2"), new SimpleEntry<Team, String>(team2, "setGameCount"));
		teamMethodMap.put(settings.getTeamGameCountSource("3"), new SimpleEntry<Team, String>(team3, "setGameCount"));
		teamMethodMap.put(settings.getTeamMatchCountSource("1"), new SimpleEntry<Team, String>(team1, "setMatchCount"));
        teamMethodMap.put(settings.getTeamMatchCountSource("2"), new SimpleEntry<Team, String>(team2, "setMatchCount"));
		teamMethodMap.put(settings.getTeamMatchCountSource("3"), new SimpleEntry<Team, String>(team3, "setMatchCount"));
		teamMethodMap.put(settings.getTeamTimeOutSource("1"), new SimpleEntry<Team, String>(team1, "setTimeOutCount"));
		teamMethodMap.put(settings.getTeamTimeOutSource("2"), new SimpleEntry<Team, String>(team2, "setTimeOutCount"));
		teamMethodMap.put(settings.getTeamTimeOutSource("3"), new SimpleEntry<Team, String>(team3, "setTimeOutCount"));
		teamMethodMap.put(settings.getPassAttemptsSource("1"), new SimpleEntry<Team, String>(team1, "setPassAttempts"));
		teamMethodMap.put(settings.getPassAttemptsSource("2"), new SimpleEntry<Team, String>(team2, "setPassAttempts"));
		teamMethodMap.put(settings.getPassAttemptsSource("3"), new SimpleEntry<Team, String>(team3, "setPassAttempts"));
		teamMethodMap.put(settings.getShotAttemptsSource("1"), new SimpleEntry<Team, String>(team1, "setShotAttempts"));
		teamMethodMap.put(settings.getShotAttemptsSource("2"), new SimpleEntry<Team, String>(team2, "setShotAttempts"));
		teamMethodMap.put(settings.getShotAttemptsSource("3"), new SimpleEntry<Team, String>(team3, "setShotAttempts"));
		teamMethodMap.put(settings.getClearAttemptsSource("1"), new SimpleEntry<Team, String>(team1, "setClearAttempts"));
		teamMethodMap.put(settings.getClearAttemptsSource("2"), new SimpleEntry<Team, String>(team2, "setClearAttempts"));
		teamMethodMap.put(settings.getClearAttemptsSource("3"), new SimpleEntry<Team, String>(team3, "setClearAttempts"));
		teamMethodMap.put(settings.getPassCompletesSource("1"), new SimpleEntry<Team, String>(team1, "setPassCompletes"));
		teamMethodMap.put(settings.getPassCompletesSource("2"), new SimpleEntry<Team, String>(team2, "setPassCompletes"));
		teamMethodMap.put(settings.getPassCompletesSource("3"), new SimpleEntry<Team, String>(team3, "setPassCompletes"));
		teamMethodMap.put(settings.getShotCompletesSource("1"), new SimpleEntry<Team, String>(team1, "setShotCompletes"));
		teamMethodMap.put(settings.getShotCompletesSource("2"), new SimpleEntry<Team, String>(team2, "setShotCompletes"));
		teamMethodMap.put(settings.getShotCompletesSource("3"), new SimpleEntry<Team, String>(team3, "setShotCompletes"));
		teamMethodMap.put(settings.getClearCompletesSource("1"), new SimpleEntry<Team, String>(team1, "setClearCompletes"));
		teamMethodMap.put(settings.getClearCompletesSource("2"), new SimpleEntry<Team, String>(team2, "setClearCompletes"));
		teamMethodMap.put(settings.getClearCompletesSource("3"), new SimpleEntry<Team, String>(team3, "setClearCompletes"));
		teamMethodMap.put(settings.getPassPercentSource("1"), new SimpleEntry<Team, String>(team1, "setPassPercent"));
		teamMethodMap.put(settings.getPassPercentSource("2"), new SimpleEntry<Team, String>(team2, "setPassPercent"));
		teamMethodMap.put(settings.getPassPercentSource("3"), new SimpleEntry<Team, String>(team3, "setPassPercent"));
		teamMethodMap.put(settings.getShotPercentSource("1"), new SimpleEntry<Team, String>(team1, "setShotPercent"));
		teamMethodMap.put(settings.getShotPercentSource("2"), new SimpleEntry<Team, String>(team2, "setShotPercent"));
		teamMethodMap.put(settings.getShotPercentSource("3"), new SimpleEntry<Team, String>(team3, "setShotPercent"));
		teamMethodMap.put(settings.getClearPercentSource("1"), new SimpleEntry<Team, String>(team1, "setClearPercent"));
		teamMethodMap.put(settings.getClearPercentSource("2"), new SimpleEntry<Team, String>(team2, "setClearPercent"));
		teamMethodMap.put(settings.getClearPercentSource("3"), new SimpleEntry<Team, String>(team3, "setClearPercent"));
		teamMethodMap.put(settings.getTwoBarPassAttemptsSource("1"), new SimpleEntry<Team, String>(team1, "setTwoBarPassAttempts"));
		teamMethodMap.put(settings.getTwoBarPassAttemptsSource("2"), new SimpleEntry<Team, String>(team2, "setTwoBarPassAttempts"));
		teamMethodMap.put(settings.getTwoBarPassAttemptsSource("3"), new SimpleEntry<Team, String>(team3, "setTwoBarPassAttempts"));
		teamMethodMap.put(settings.getTwoBarPassCompletesSource("1"), new SimpleEntry<Team, String>(team1, "setTwoBarPassCompletes"));
		teamMethodMap.put(settings.getTwoBarPassCompletesSource("2"), new SimpleEntry<Team, String>(team2, "setTwoBarPassCompletes"));
		teamMethodMap.put(settings.getTwoBarPassCompletesSource("3"), new SimpleEntry<Team, String>(team3, "setTwoBarPassCompletes"));
		teamMethodMap.put(settings.getTwoBarPassPercentSource("1"), new SimpleEntry<Team, String>(team1, "setTwoBarPassPercent"));
		teamMethodMap.put(settings.getTwoBarPassPercentSource("2"), new SimpleEntry<Team, String>(team2, "setTwoBarPassPercent"));
		teamMethodMap.put(settings.getTwoBarPassPercentSource("3"), new SimpleEntry<Team, String>(team3, "setTwoBarPassPercent"));
		teamMethodMap.put(settings.getScoringSource("1"), new SimpleEntry<Team, String>(team1, "setScoring"));
		teamMethodMap.put(settings.getScoringSource("2"), new SimpleEntry<Team, String>(team2, "setScoring"));
		teamMethodMap.put(settings.getScoringSource("3"), new SimpleEntry<Team, String>(team3, "setScoring"));
		teamMethodMap.put(settings.getThreeBarScoringSource("1"), new SimpleEntry<Team, String>(team1, "setThreeBarScoring"));
		teamMethodMap.put(settings.getThreeBarScoringSource("2"), new SimpleEntry<Team, String>(team2, "setThreeBarScoring"));
		teamMethodMap.put(settings.getThreeBarScoringSource("3"), new SimpleEntry<Team, String>(team3, "setThreeBarScoring"));
		teamMethodMap.put(settings.getFiveBarScoringSource("1"), new SimpleEntry<Team, String>(team1, "setFiveBarScoring"));
		teamMethodMap.put(settings.getFiveBarScoringSource("2"), new SimpleEntry<Team, String>(team2, "setFiveBarScoring"));
		teamMethodMap.put(settings.getFiveBarScoringSource("3"), new SimpleEntry<Team, String>(team3, "setFiveBarScoring"));
		teamMethodMap.put(settings.getTwoBarScoringSource("1"), new SimpleEntry<Team, String>(team1, "setTwoBarScoring"));
		teamMethodMap.put(settings.getTwoBarScoringSource("2"), new SimpleEntry<Team, String>(team2, "setTwoBarScoring"));
		teamMethodMap.put(settings.getTwoBarScoringSource("3"), new SimpleEntry<Team, String>(team3, "setTwoBarScoring"));
		teamMethodMap.put(settings.getShotsOnGoalSource("1"), new SimpleEntry<Team, String>(team1, "setShotsOnGoal"));
		teamMethodMap.put(settings.getShotsOnGoalSource("2"), new SimpleEntry<Team, String>(team2, "setShotsOnGoal"));
		teamMethodMap.put(settings.getShotsOnGoalSource("3"), new SimpleEntry<Team, String>(team3, "setShotsOnGoal"));
		teamMethodMap.put(settings.getStuffsSource("1"), new SimpleEntry<Team, String>(team1, "setStuffs"));
		teamMethodMap.put(settings.getStuffsSource("2"), new SimpleEntry<Team, String>(team2, "setStuffs"));
		teamMethodMap.put(settings.getStuffsSource("3"), new SimpleEntry<Team, String>(team3, "setStuffs"));
		teamMethodMap.put(settings.getBreaksSource("1"), new SimpleEntry<Team, String>(team1, "setBreaks"));
		teamMethodMap.put(settings.getBreaksSource("2"), new SimpleEntry<Team, String>(team2, "setBreaks"));
		teamMethodMap.put(settings.getBreaksSource("3"), new SimpleEntry<Team, String>(team3, "setBreaks"));
		teamMethodMap.put(settings.getAcesSource("1"), new SimpleEntry<Team, String>(team1, "setAces"));
		teamMethodMap.put(settings.getAcesSource("2"), new SimpleEntry<Team, String>(team2, "setAces"));
		teamMethodMap.put(settings.getAcesSource("3"), new SimpleEntry<Team, String>(team3, "setAces"));
		teamMethodMap.put(settings.getTeamResetSource("1"), new SimpleEntry<Team, String>(team1, "setReset"));
		teamMethodMap.put(settings.getTeamResetSource("2"), new SimpleEntry<Team, String>(team2, "setReset"));
		teamMethodMap.put(settings.getTeamResetSource("3"), new SimpleEntry<Team, String>(team3, "setReset"));
		teamMethodMap.put(settings.getTeamWarnSource("1"), new SimpleEntry<Team, String>(team1, "setWarn"));
		teamMethodMap.put(settings.getTeamWarnSource("2"), new SimpleEntry<Team, String>(team2, "setWarn"));
		teamMethodMap.put(settings.getTeamWarnSource("3"), new SimpleEntry<Team, String>(team3, "setWarn"));
		OBS obs = OBS.getInstance();
		OBSRemoteController obsRemoteController = obs.getController();
		if (!(obsRemoteController==null) && obs.getConnected())
		{
			for (String source : teamMethodMap.keySet()) {
				obsRemoteController.getInputSettings(source, response -> {
					if (response != null && response.isSuccessful()) {
						setTextFromSource(source,response.getInputSettings().get("text").getAsString(), teamMethodMap);
					}
				});
			}
			match.setLastScored(obsInterface.getContents(settings.getLastScoredSource()));
			teamPanel1.updateTeamName(team1.getTeamName());
			teamPanel2.updateTeamName(team2.getTeamName());
			teamPanel3.updateTeamName(team3.getTeamName());
			teamPanel1.updateForwardName(team1.getForwardName());
			teamPanel2.updateForwardName(team2.getForwardName());
			teamPanel3.updateForwardName(team3.getForwardName());
			teamPanel1.updateGoalieName(team1.getGoalieName());
			teamPanel2.updateGoalieName(team2.getGoalieName());
			teamPanel3.updateGoalieName(team3.getGoalieName());
			teamPanel1.updateScore(team1.getScore());
			teamPanel2.updateScore(team2.getScore());
			teamPanel3.updateScore(team3.getScore());
			teamPanel1.updateGameCount(team1.getGameCount());
			teamPanel2.updateGameCount(team2.getGameCount());
			teamPanel3.updateGameCount(team3.getGameCount());
			teamPanel1.updateMatchCount(team1.getMatchCount());
			teamPanel2.updateMatchCount(team2.getMatchCount());
			teamPanel3.updateMatchCount(team3.getMatchCount());
			teamPanel1.updateTimeOutCount(team1.getTimeOutCount());
			teamPanel2.updateTimeOutCount(team2.getTimeOutCount());
			teamPanel3.updateTimeOutCount(team3.getTimeOutCount());
			teamPanel1.updateReset(team1.getReset());
			teamPanel2.updateReset(team2.getReset());
			teamPanel3.updateReset(team3.getReset());
			teamPanel1.updateWarn(team1.getWarn());
			teamPanel2.updateWarn(team2.getWarn());
			teamPanel3.updateWarn(team3.getWarn());
			switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
			match.syncCurrentGameNumber();
			match.syncGameScores();
			updateGameTables();
		}
	}
	private void setTextFromSource(String source, String text, Map<String, Entry<Team, String>> methodMap) {
		Entry<Team, String> methodMapEntry = methodMap.get(source);
		if(source.equals(settings.getTeamResetSource("1")) || source.equals(settings.getTeamResetSource("2")) || source.equals(settings.getTeamWarnSource("1")) || source.equals(settings.getTeamWarnSource("2")))
		{
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), boolean.class);
				method.invoke(methodMapEntry.getKey(), text.length()>0);
			} catch (NoSuchMethodException e) {
				logger.error(e.toString());
			} catch (SecurityException e) {
				logger.error(e.toString());
			} catch (IllegalAccessException e) {
				logger.error(e.toString());
			} catch (IllegalArgumentException e) {
				logger.error(e.toString());
			} catch (InvocationTargetException e) {
				logger.error(e.toString());
			}			
		} else {
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), String.class);
				method.invoke(methodMapEntry.getKey(), text);
			} catch (NoSuchMethodException e) {
				logger.error(e.toString());
			} catch (SecurityException e) {
				logger.error(e.toString());
			} catch (IllegalAccessException e) {
				logger.error(e.toString());
			} catch (IllegalArgumentException e) {
				logger.error(e.toString());
			} catch (InvocationTargetException e) {
				logger.error(e.toString());
			}
		}
	}
	public void writeAll() {
		team1.writeAll();
		team2.writeAll();
		team3.writeAll();
	}
	public int getPassAttempts(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getPassAttempts();
		} else {
			return -1;
		}
	}
	public int getPassCompletes(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getPassCompletes();
		} else {
			return -1;
		}
	}
	public Float getPassPercent(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getPassPercent();
		} else {
			return -1F;
		}
	}
	public int getShotAttempts(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getShotAttempts();
		} else {
			return -1;
		}
	}
	public int getShotCompletes(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getShotCompletes();
		} else {
			return -1;
		}
	}
	public Float getShotPercent(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getShotPercent();
		} else {
			return -1F;
		}
	}
	public int getClearAttempts(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getClearAttempts();
		} else {
			return -1;
		}
	}
	public int getClearCompletes(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getClearCompletes();
		} else {
			return -1;
		}
	}
	public Float getClearPercent(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getClearPercent();
		} else {
			return -1F;
		}
	}
	public int getTwoBarPassAttempts(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getTwoBarPassAttempts();
		} else {
			return -1;
		}
	}
	public int getTwoBarPassCompletes(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getTwoBarPassCompletes();
		} else {
			return -1;
		}
	}
	public Float getTwoBarPassPercent(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getTwoBarPassPercent();
		} else {
			return -1F;
		}
	}
	public int getScoring(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getScoring();
		} else {
			return -1;
		}
	}
	public int getThreeBarScoring(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getThreeBarScoring();
		} else {
			return -1;
		}
	}
	public int getFiveBarScoring(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getFiveBarScoring();
		} else {
			return -1;
		}
	}
	public int getTwoBarScoring(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getTwoBarScoring();
		} else {
			return -1;
		}
	}
	public int getShotsOnGoal(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getShotsOnGoal();
		} else {
			return -1;
		}
	}
	public int getStuffs(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getStuffs();
		} else {
			return -1;
		}
	}
	public int getBreaks(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getBreaks();
		} else {
			return -1;
		}
	}
	public int getAces(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getAces();
		} else {
			return -1;
		}
	}
	public String getForwardName(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getForwardName();
		} else {
			return "";
		}
	}
	public String getGoalieName(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getGoalieName();
		} else {
			return "";
		}
	}
	public String getTeamName(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			return team.getTeamName();
		} else {
			return "";
		}
	}
}
