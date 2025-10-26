/**
Copyright © 2020-2026 Hugh Garner
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

import com.midsouthfoosball.foosobsplus.main.Main;
import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.OBS;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.Messages;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;
import com.midsouthfoosball.foosobsplus.view.TeamPanel;

import io.obswebsocket.community.client.OBSRemoteController;

public class TeamController {
	private final OBSInterface obsInterface;
	private Team team1;
	private Team team2;
	private Team team3;
	private final Match match;
	private final TeamPanel teamPanel1;
	private final TeamPanel teamPanel2;
	private final TeamPanel teamPanel3;
	private final SwitchPanel switchPanel;
	private final MatchPanel matchPanel;
	private final GameTableWindowPanel gameTableWindowPanel;
	private final StatsDisplayPanel statsDisplayPanel;
	private final TimerController timerController;
	private final LastScoredClock lastScored1Clock;
	private final LastScoredClock lastScored2Clock;
	private final LastScoredClock lastScored3Clock;
	private final GameClock gameClock;
        private final MainController mainController;
        private final Map<Integer, Team> teamsMap;
        private final Map<Integer, TeamPanel> teamPanelsMap;
        private final Map<Integer, LastScoredClock> lastScoredClocksMap;
	private static final transient Logger logger = LoggerFactory.getLogger(TeamController.class);
	private static final String TEAM1 = "1";
	private static final String TEAM2 = "2";
	private static final String TEAM3 = "3";
	private static final String ON = "1";
	public TeamController(OBSInterface obsInterface, Team team1, Team team2, Team team3, Match match, TeamPanel teamPanel1, TeamPanel teamPanel2, TeamPanel teamPanel3, SwitchPanel switchPanel, MatchPanel matchPanel, GameTableWindowPanel gameTableWindowPanel, StatsDisplayPanel statsDisplayPanel, TimerController timerController, LastScoredClock lastScored1Clock, LastScoredClock lastScored2Clock, LastScoredClock lastScored3Clock, GameClock gameClock, MainController mainController) {
                this.lastScoredClocksMap = new HashMap<>();
                this.teamPanelsMap = new HashMap<>();
                this.teamsMap = new HashMap<>();
		this.obsInterface = obsInterface;
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
		checkTeamNames();
	}
	////// Team Panel Listener Objects //////
	private class TeamNameListener implements ActionListener{
                @Override
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			teamNameChange(txt);
		}
	}
	private class TeamNameFocusListener extends FocusAdapter{
                @Override
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			teamNameChange(txt);
		}
	}
	private class TeamNameMouseListener extends MouseAdapter{
                @Override
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
                @Override
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			forwardNameChange(txt);
		}
	}
	private class ForwardNameFocusListener extends FocusAdapter{
                @Override
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			forwardNameChange(txt);
		}
	}
	private class ForwardNameMouseListener extends MouseAdapter{
                @Override
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			int teamNumber = convertToTeamNumber(txt.getName());
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (teamPanel != null) {
				teamPanel.selectForwardName();
				statsDisplayPanel.updateTeams(teamNumber,Main.combinePlayerNames(teamNumber),getTeamName(teamNumber));
				updateGameTables();
			}
		}
	}
	private class GoalieNameListener implements ActionListener{
                @Override
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			goalieNameChange(txt);
		}
	}
	private class GoalieNameFocusListener extends FocusAdapter{
                @Override
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			goalieNameChange(txt);
		}
	}
	private class GoalieNameMouseListener extends MouseAdapter{
                @Override
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			int teamNumber = convertToTeamNumber(txt.getName());
			TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
			if (teamPanel != null) {
				teamPanel.selectGoalieName();
				statsDisplayPanel.updateTeams(teamNumber,Main.combinePlayerNames(teamNumber),getTeamName(teamNumber));
				updateGameTables();
			}
		}
	}
	private class ScoreListener implements ActionListener{
                @Override
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
                @Override
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
                @Override
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
                @Override
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
                @Override
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
                @Override
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
                @Override
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
                @Override
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
                @Override
		public void actionPerformed(ActionEvent e) {
			updateLastScoredTimes();
		}
	}
	////// Utility Methods \\\\\\
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
		int teamNumber = convertToTeamNumber(txt.getName());
		String teamName;
		String name = txt.getText();
		if (name == null || name.isEmpty()) {
			teamName = Messages.getString("TeamPanel.Team") + teamNumber; 
		} else if(Settings.getControlParameter("AutoCapNames").equals(ON)) {
			teamName = capitalizeWords(name);
		} else {
			teamName = name;
		}
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			team.setTeamName(teamName);
			teamPanel.updateTeamName(teamName);
			statsDisplayPanel.updateTeams(teamNumber,Main.combinePlayerNames(teamNumber),getTeamName(teamNumber));
			if (match.getWinState() > 0) {
				resetForNewGame();
			}
			updateGameTables();
		}
	}
	private void checkTeamNames() {
		teamsMap.forEach((k,v) -> {
			String name = v.getTeamName();
			if (name == null || name.isEmpty()) {
				String newName = Messages.getString("TeamPanel.Team") + k;
				v.setTeamName(newName);
				teamPanelsMap.get(k).updateTeamName(newName);
			}
		});
	}
	private void forwardNameChange(JTextField txt) {
		String forwardName;
		if(Settings.getControlParameter("AutoCapNames").equals(ON)) {
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
			statsDisplayPanel.updateTeams(teamNumber,Main.combinePlayerNames(teamNumber),getTeamName(teamNumber));
			checkResetForNewGame();
			updateGameTables();
		}
	}
	private void goalieNameChange(JTextField txt) {
		String goalieName;
		if(Settings.getControlParameter("AutoCapNames").equals(ON)) {
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
			statsDisplayPanel.updateTeams(teamNumber,Main.combinePlayerNames(teamNumber),getTeamName(teamNumber));
			checkResetForNewGame();
			updateGameTables();
		}
	}
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
			Integer.valueOf(checkString);
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
	public void decrementScore(int teamNumber) {
		Team team = teamsMap.getOrDefault(teamNumber, null);
		TeamPanel teamPanel = teamPanelsMap.getOrDefault(teamNumber, null);
		if (team != null) {
			match.decrementScore(teamNumber);
			teamPanel.updateScore(team.getScore());
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
		checkTeamNames();
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
		matchPanel.clearKingSeat();
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
		switchPanel.setLastScored(Settings.getLastScoredStrings()[0]);
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
//		match.resetMatch(); // probably should go through matchController, but it is not accessible here
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
		switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
	}
	private void resetLastScored() {
		match.setLastScored(0);
		switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
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
		switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
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
		matchPanel.clearKingSeat();
		for (int i = 1; i < 4; i++) {
		    if (teamsMap.get(i).getKingSeat() ) matchPanel.setKingSeat(i);
		}
		updateGameTableWindowNames();
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
			matchPanel.clearKingSeat();
			updateGameTableWindowNames();
			if (team.getKingSeat()) {
				matchPanel.setKingSeat(teamNumber);
			}
		}
	}
	public void updateGameTableWindowNames() {
		String[] teamPrefixes = new String[3];
		String[] teamNames = new String[3];
		for (int i = 0; i < 3; i++) {
		    if (teamsMap.get(i+1).getKingSeat() ) {
		        teamPrefixes[i] = Messages.getString("Global.KingSeat"); //$NON-NLS-1$
		    } else {
		        teamPrefixes[i] = "";
		    }
		    teamNames[i] = teamPrefixes[i] + Main.combinePlayerNames(i + 1) + ":";
		}
		gameTableWindowPanel.setTeams(teamNames[0], teamNames[1], teamNames[2]);
	}
	public void fetchAll() {
		Map<String, Entry<Team, String>> teamMethodMap = new HashMap<>();
		//Map<String is Source Name:Score 1, Entry<Team is Team Class Instance:team1|team2, String is Method Name to call:setScore>
		teamMethodMap.put(Settings.getSourceParameter("Team1Name"), new SimpleEntry<>(team1, "setTeamName"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Name"), new SimpleEntry<>(team2, "setTeamName"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Name"), new SimpleEntry<>(team3, "setTeamName"));
		teamMethodMap.put(Settings.getSourceParameter("Team1Forward"), new SimpleEntry<>(team1, "setForwardName"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Forward"), new SimpleEntry<>(team2, "setForwardName"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Forward"), new SimpleEntry<>(team3, "setForwardName"));
		teamMethodMap.put(Settings.getSourceParameter("Team1Goalie"), new SimpleEntry<>(team1, "setGoalieName"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Goalie"), new SimpleEntry<>(team2, "setGoalieName"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Goalie"), new SimpleEntry<>(team3, "setGoalieName"));
		teamMethodMap.put(Settings.getSourceParameter("Team1Score"), new SimpleEntry<>(team1, "setScore"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Score"), new SimpleEntry<>(team2, "setScore"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Score"), new SimpleEntry<>(team3, "setScore"));
		teamMethodMap.put(Settings.getSourceParameter("Team1GameCount"), new SimpleEntry<>(team1, "setGameCount"));
        teamMethodMap.put(Settings.getSourceParameter("Team2GameCount"), new SimpleEntry<>(team2, "setGameCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team3GameCount"), new SimpleEntry<>(team3, "setGameCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team1MatchCount"), new SimpleEntry<>(team1, "setMatchCount"));
        teamMethodMap.put(Settings.getSourceParameter("Team2MatchCount"), new SimpleEntry<>(team2, "setMatchCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team3MatchCount"), new SimpleEntry<>(team3, "setMatchCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team1TimeOut"), new SimpleEntry<>(team1, "setTimeOutCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team2TimeOut"), new SimpleEntry<>(team2, "setTimeOutCount"));
		teamMethodMap.put(Settings.getSourceParameter("Team3TimeOut"), new SimpleEntry<>(team3, "setTimeOutCount"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter("1","PassAttempts"), new SimpleEntry<>(team1, "setPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"PassAttempts"), new SimpleEntry<>(team2, "setPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"PassAttempts"), new SimpleEntry<>(team3, "setPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ShotAttempts"), new SimpleEntry<>(team1, "setShotAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ShotAttempts"), new SimpleEntry<>(team2, "setShotAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ShotAttempts"), new SimpleEntry<>(team3, "setShotAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ClearAttempts"), new SimpleEntry<>(team1, "setClearAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ClearAttempts"), new SimpleEntry<>(team2, "setClearAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ClearAttempts"), new SimpleEntry<>(team3, "setClearAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"PassCompletes"), new SimpleEntry<>(team1, "setPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"PassCompletes"), new SimpleEntry<>(team2, "setPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"PassCompletes"), new SimpleEntry<>(team3, "setPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ShotCompletes"), new SimpleEntry<>(team1, "setShotCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ShotCompletes"), new SimpleEntry<>(team2, "setShotCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ShotCompletes"), new SimpleEntry<>(team3, "setShotCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ClearCompletes"), new SimpleEntry<>(team1, "setClearCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ClearCompletes"), new SimpleEntry<>(team2, "setClearCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ClearCompletes"), new SimpleEntry<>(team3, "setClearCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"PassPercent"), new SimpleEntry<>(team1, "setPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"PassPercent"), new SimpleEntry<>(team2, "setPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"PassPercent"), new SimpleEntry<>(team3, "setPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ShotPercent"), new SimpleEntry<>(team1, "setShotPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ShotPercent"), new SimpleEntry<>(team2, "setShotPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ShotPercent"), new SimpleEntry<>(team3, "setShotPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ClearPercent"), new SimpleEntry<>(team1, "setClearPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ClearPercent"), new SimpleEntry<>(team2, "setClearPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ClearPercent"), new SimpleEntry<>(team3, "setClearPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassAttempts"), new SimpleEntry<>(team1, "setTwoBarPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassAttempts"), new SimpleEntry<>(team2, "setTwoBarPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassAttempts"), new SimpleEntry<>(team3, "setTwoBarPassAttempts"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassCompletes"), new SimpleEntry<>(team1, "setTwoBarPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassCompletes"), new SimpleEntry<>(team2, "setTwoBarPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassCompletes"), new SimpleEntry<>(team3, "setTwoBarPassCompletes"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassPercent"), new SimpleEntry<>(team1, "setTwoBarPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassPercent"), new SimpleEntry<>(team2, "setTwoBarPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassPercent"), new SimpleEntry<>(team3, "setTwoBarPassPercent"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"Scoring"), new SimpleEntry<>(team1, "setScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"Scoring"), new SimpleEntry<>(team2, "setScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"Scoring"), new SimpleEntry<>(team3, "setScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ThreeBarScoring"), new SimpleEntry<>(team1, "setThreeBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ThreeBarScoring"), new SimpleEntry<>(team2, "setThreeBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ThreeBarScoring"), new SimpleEntry<>(team3, "setThreeBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"FiveBarScoring"), new SimpleEntry<>(team1, "setFiveBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"FiveBarScoring"), new SimpleEntry<>(team2, "setFiveBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"FiveBarScoring"), new SimpleEntry<>(team3, "setFiveBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarScoring"), new SimpleEntry<>(team1, "setTwoBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarScoring"), new SimpleEntry<>(team2, "setTwoBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarScoring"), new SimpleEntry<>(team3, "setTwoBarScoring"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"ShotsOnGoal"), new SimpleEntry<>(team1, "setShotsOnGoal"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"ShotsOnGoal"), new SimpleEntry<>(team2, "setShotsOnGoal"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"ShotsOnGoal"), new SimpleEntry<>(team3, "setShotsOnGoal"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"Stuffs"), new SimpleEntry<>(team1, "setStuffs"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"Stuffs"), new SimpleEntry<>(team2, "setStuffs"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"Stuffs"), new SimpleEntry<>(team3, "setStuffs"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"Breaks"), new SimpleEntry<>(team1, "setBreaks"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"Breaks"), new SimpleEntry<>(team2, "setBreaks"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"Breaks"), new SimpleEntry<>(team3, "setBreaks"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM1,"Aces"), new SimpleEntry<>(team1, "setAces"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM2,"Aces"), new SimpleEntry<>(team2, "setAces"));
		teamMethodMap.put(Settings.getTeamStatsSourceParameter(TEAM3,"Aces"), new SimpleEntry<>(team3, "setAces"));
		teamMethodMap.put(Settings.getSourceParameter("Team1Reset"), new SimpleEntry<>(team1, "setReset"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Reset"), new SimpleEntry<>(team2, "setReset"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Reset"), new SimpleEntry<>(team3, "setReset"));
		teamMethodMap.put(Settings.getSourceParameter("Team1Warn"), new SimpleEntry<>(team1, "setWarn"));
		teamMethodMap.put(Settings.getSourceParameter("Team2Warn"), new SimpleEntry<>(team2, "setWarn"));
		teamMethodMap.put(Settings.getSourceParameter("Team3Warn"), new SimpleEntry<>(team3, "setWarn"));
		OBSRemoteController obsRemoteController = OBS.getController();
		if (!(obsRemoteController==null) && OBS.getConnected())
		{
			for (String source : teamMethodMap.keySet()) {
				obsRemoteController.getInputSettings(source, response -> {
					if (response != null && response.isSuccessful()) {
						setTextFromSource(source,response.getInputSettings().get("text").getAsString(), teamMethodMap);
					}
				});
			}
			match.setLastScored(obsInterface.getContents(Settings.getSourceParameter("LastScored")));
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
			switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
			match.syncCurrentGameNumber();
			match.syncGameScores();
			updateGameTables();
		}
	}
	private void setTextFromSource(String source, String text, Map<String, Entry<Team, String>> methodMap) {
		Entry<Team, String> methodMapEntry = methodMap.get(source);
		if(source.equals(Settings.getSourceParameter("Team1Reset")) || source.equals(Settings.getSourceParameter("Team2Reset")) || source.equals(Settings.getSourceParameter("Team1Warn")) || source.equals(Settings.getSourceParameter("Team2Warn")))
		{
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), boolean.class);
				method.invoke(methodMapEntry.getKey(), text.length()>0);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error(e.toString());
			}			
		} else {
			try {
				Method method = methodMapEntry.getKey().getClass().getMethod(methodMapEntry.getValue(), String.class);
				method.invoke(methodMapEntry.getKey(), text);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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
