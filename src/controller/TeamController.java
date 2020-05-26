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
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import main.OBSInterface;
import model.Match;
import model.Settings;
import model.Team;
import view.SwitchPanel;
import view.TeamPanel;

public class TeamController {
	private OBSInterface obsInterface;
	private Settings settings;
	private Team team1;
	private Team team2;
	private Match match;
	private TeamPanel team1Panel;
	private TeamPanel team2Panel;
	private SwitchPanel switchPanel;
	private TimerController timerController;
	
	public TeamController(OBSInterface obsInterface, Settings settings, Team team1, Team team2, Match match, TeamPanel team1Panel, TeamPanel team2Panel, SwitchPanel switchPanel, TimerController timerController) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.team1 = team1;
		this.team2 = team2;
		this.match = match;
		this.team1Panel = team1Panel;
		this.team2Panel = team2Panel;
		this.switchPanel = switchPanel;
		this.timerController = timerController;
		
		////// Team Panel Listeners Methods //////
		
		this.team1Panel.addTeamNameListener(new TeamNameListener());
		this.team2Panel.addTeamNameListener(new TeamNameListener());
		this.team1Panel.addTeamNameFocusListener(new TeamNameFocusListener());
		this.team2Panel.addTeamNameFocusListener(new TeamNameFocusListener());
		this.team1Panel.addTeamNameMouseListener(new TeamNameMouseListener());
		this.team2Panel.addTeamNameMouseListener(new TeamNameMouseListener());
		this.team1Panel.addForwardNameListener(new ForwardNameListener());
		this.team2Panel.addForwardNameListener(new ForwardNameListener());
		this.team1Panel.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.team2Panel.addForwardNameFocusListener(new ForwardNameFocusListener());
		this.team1Panel.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.team2Panel.addForwardNameMouseListener(new ForwardNameMouseListener());
		this.team1Panel.addGoalieNameListener(new GoalieNameListener());
		this.team2Panel.addGoalieNameListener(new GoalieNameListener());
		this.team1Panel.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.team2Panel.addGoalieNameFocusListener(new GoalieNameFocusListener());
		this.team1Panel.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.team2Panel.addGoalieNameMouseListener(new GoalieNameMouseListener());
		this.team1Panel.addSwitchPositionsListener(new SwitchPositionsListener());
		this.team2Panel.addSwitchPositionsListener(new SwitchPositionsListener());
		this.team1Panel.addScoreListener(new ScoreListener());
		this.team2Panel.addScoreListener(new ScoreListener());
		this.team1Panel.addScoreFocusListener(new ScoreFocusListener());
		this.team2Panel.addScoreFocusListener(new ScoreFocusListener());
		this.team1Panel.addScoreIncreaseListener(new ScoreIncreaseListener());
		this.team2Panel.addScoreIncreaseListener(new ScoreIncreaseListener());
		this.team1Panel.addScoreDecreaseListener(new ScoreDecreaseListener());
		this.team2Panel.addScoreDecreaseListener(new ScoreDecreaseListener());
		this.team1Panel.addGameCountListener(new GameCountListener());
		this.team2Panel.addGameCountListener(new GameCountListener());
		this.team1Panel.addGameCountFocusListener(new GameCountFocusListener());
		this.team2Panel.addGameCountFocusListener(new GameCountFocusListener());
		this.team1Panel.addGameCountIncreaseListener(new GameCountIncreaseListener());
		this.team2Panel.addGameCountIncreaseListener(new GameCountIncreaseListener());
		this.team1Panel.addGameCountDecreaseListener(new GameCountDecreaseListener());
		this.team2Panel.addGameCountDecreaseListener(new GameCountDecreaseListener());
		this.team1Panel.addTimeOutCountListener(new TimeOutCountListener());
		this.team2Panel.addTimeOutCountListener(new TimeOutCountListener());
		this.team1Panel.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.team2Panel.addTimeOutCountFocusListener(new TimeOutCountFocusListener());
		this.team1Panel.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		this.team2Panel.addTimeOutCountIncreaseListener(new TimeOutCountIncreaseListener());
		this.team1Panel.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		this.team2Panel.addTimeOutCountDecreaseListener(new TimeOutCountDecreaseListener());
		this.team1Panel.addResetListener(new ResetListener());
		this.team2Panel.addResetListener(new ResetListener());
		this.team1Panel.addWarnListener(new WarnListener());
		this.team2Panel.addWarnListener(new WarnListener());
		this.team1Panel.addClearAllListener(new ClearAllListener());
		this.team2Panel.addClearAllListener(new ClearAllListener());

	}
	
	////// Team Panel Listener Objects //////

	private class TeamNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String teamName = txt.getText();
			if(name.equals("Team 1")) {
				team1.setTeamName(teamName);
				team1Panel.updateTeamName(teamName);
			} else {
				team2.setTeamName(teamName);
				team2Panel.updateTeamName(teamName);
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
				team1Panel.updateTeamName(teamName);
			} else {
				team2.setTeamName(teamName);
				team2Panel.updateTeamName(teamName);
			}
		}
	}
	private class TeamNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				team1Panel.selectTeamName();
			} else {
				team2Panel.selectTeamName();
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
				team1Panel.updateForwardName(forwardName);
			} else {
				team2.setForwardName(forwardName);
				team2Panel.updateForwardName(forwardName);
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
				team1Panel.updateForwardName(forwardName);
			} else {
				team2.setForwardName(forwardName);
				team2Panel.updateForwardName(forwardName);
			}
		}
	}
	private class ForwardNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				team1Panel.selectForwardName();
			} else {
				team2Panel.selectForwardName();
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
				team1Panel.updateGoalieName(goalieName);
			} else {
				team2.setGoalieName(goalieName);
				team2Panel.updateGoalieName(goalieName);
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
				team1Panel.updateGoalieName(goalieName);
			} else {
				team2.setGoalieName(goalieName);
				team2Panel.updateGoalieName(goalieName);
			}
		}
	}
	private class GoalieNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			if(name.equals("Team 1")) {
				team1Panel.selectGoalieName();
			} else {
				team2Panel.selectGoalieName();
			}
		}
	}
	private class SwitchPositionsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			switchPositions(name);
		}
	}
	private class ScoreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String score = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setScore(score);
				team1Panel.updateScore(score);
			} else {
				team2.setScore(score);
				team2Panel.updateScore(score);
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
				team1Panel.updateScore(score);
			} else {
				team2.setScore(score);
				team2Panel.updateScore(score);
			}
		}
	}
	private class ScoreIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			incrementScore(name);
		}
	}
	private class ScoreDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			decrementScore(name);
		}
	}
	private class GameCountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String name = txt.getName();
			String gameCount = convertNumbers(txt.getText());
			if(name.equals("Team 1")) {
				team1.setGameCount(gameCount);
				team1Panel.updateGameCount(gameCount);
			} else {
				team2.setGameCount(gameCount);
				team2Panel.updateGameCount(gameCount);
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
				team1Panel.updateGameCount(gameCount);
			} else {
				team2.setGameCount(gameCount);
				team2Panel.updateGameCount(gameCount);
			}
		}
	}
	private class GameCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			incrementGameCount(name);
		}
	}
	private class GameCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				team1Panel.updateGameCount(team1.decrementGameCount());
			} else {
				team2Panel.updateGameCount(team2.decrementGameCount());
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
				team1Panel.updateTimeOutCount(timeOutCount);
			} else {
				team2.setTimeOutCount(timeOutCount);
				team2Panel.updateTimeOutCount(timeOutCount);
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
				team1Panel.updateTimeOutCount(timeOutCount);
			} else {
				team2.setTimeOutCount(timeOutCount);
				team2Panel.updateTimeOutCount(timeOutCount);
			}
		}
	}
	private class TimeOutCountIncreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			callTimeOut(name);
		}
	}
	private class TimeOutCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			restoreTimeOut(name);
		}
	}
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				team1.setReset(btn.isSelected());
				team1Panel.updateReset(team1.getReset());
			} else {
				team2.setReset(btn.isSelected());
				team2Panel.updateReset(team2.getReset());
			}
		}
	}
	private class WarnListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JToggleButton btn = (JToggleButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				team1.setWarn(btn.isSelected());
				team1Panel.updateWarn(team1.getWarn());
			} else {
				team2.setWarn(btn.isSelected());
				team2Panel.updateWarn(team2.getWarn());
			}
		}
	}
	private class ClearAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			if(name.equals("Team 1")) {
				team1.clearAll();
				displayAll();
			} else {
				team2.clearAll();
				displayAll();
			}
			match.clearAll();
			switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		}
	}

	////// Utility Methods \\\\\\
	
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
			team1Panel.updateNames(forwardName, goalieName);
		} else {
			String[] names = team2.switchPositions();
			String forwardName = names[0];
			String goalieName = names[1];
			team2Panel.updateNames(forwardName, goalieName);
		}
	};
	public void incrementScore(String name) {
		int winState = 0;
		if(name.equals("Team 1")) {
			winState = match.incrementScore(1);
		} else {
			winState = match.incrementScore(2);
		};
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		resetTimer();
		if(winState==1) startGameTimer();
		displayAll();
	}
	public void decrementScore(String name) {
		if(name.equals("Team 1")) {
			team1Panel.updateScore(team1.decrementScore());
		} else {
			team2Panel.updateScore(team2.decrementScore());
		}
	}
	public void incrementGameCount(String name) {
		boolean matchWon = false;
		if(name.equals("Team 1")) {
			matchWon = match.incrementGameCount(team1);
			team1Panel.updateGameCount(team1.getGameCount());
		} else {
			matchWon = match.incrementGameCount(team2);
			team2Panel.updateGameCount(team2.getGameCount());
		};
		if(!matchWon) startGameTimer();
	}
	public void decrementGameCount(String name) {
		if(name.equals("Team 1")) {
			team1.decrementGameCount();
			team1Panel.updateGameCount(team1.getGameCount());
		} else {
			team2.decrementGameCount();
			team2Panel.updateGameCount(team2.getGameCount());
		}
	}
	public void callTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.callTimeOut();
			team1Panel.updateTimeOutCount(team1.getTimeOutCount());
		} else {
			team2.callTimeOut();
			team2Panel.updateTimeOutCount(team2.getTimeOutCount());
		}
		startTimeOutTimer();
	}
	public void restoreTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.restoreTimeOut();
			team1Panel.updateTimeOutCount(team1.getTimeOutCount());
		} else {
			team2.restoreTimeOut();
			team2Panel.updateTimeOutCount(team2.getTimeOutCount());
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
		Team tmp = team1;
		team1 = team2;
		team1.setTeamNbr(1);
		team2 = tmp;
		team2.setTeamNbr(2);
		team1.writeAll();
		team2.writeAll();
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
		team1Panel.updateTeamName(team1.getTeamName());
		team1Panel.updateForwardName(team1.getForwardName());
		team1Panel.updateGoalieName(team1.getGoalieName());
		team2Panel.updateTeamName(team2.getTeamName());
		team2Panel.updateForwardName(team2.getForwardName());
		team2Panel.updateGoalieName(team2.getGoalieName());
	}
	public void switchScores() {
		int tmp = team1.getScore();
		team1.setScore(team2.getScore());
		team2.setScore(tmp);
		team1Panel.updateScore(team1.getScore());
		team2Panel.updateScore(team2.getScore());
	}
	public void switchGameCounts() {
		int tmp = team1.getGameCount();
		team1.setGameCount(team2.getGameCount());
		team2.setGameCount(tmp);
		team1Panel.updateGameCount(team1.getGameCount());
		team2Panel.updateGameCount(team2.getGameCount());
	}
	public void switchTimeOuts() {
		int tmp = team1.getTimeOutCount();
		team1.setTimeOutCount(team2.getTimeOutCount());
		team2.setTimeOutCount(tmp);
		team1Panel.updateTimeOutCount(team1.getTimeOutCount());
		team2Panel.updateTimeOutCount(team2.getTimeOutCount());
	}
	public void switchResetWarns() {
		boolean tmp = team1.getReset();
		team1.setReset(team2.getReset());
		team2.setReset(tmp);
		boolean tmp2 = team1.getWarn();
		team1.setWarn(team2.getWarn());
		team2.setWarn(tmp2);
		team1Panel.updateReset(team1.getReset());
		team2Panel.updateReset(team2.getReset());
		team1Panel.updateWarn(team1.getWarn());
		team2Panel.updateWarn(team2.getWarn());
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
		team1Panel.updateTeamName(team1.getTeamName());
		team2Panel.updateTeamName(team2.getTeamName());
		team1Panel.updateForwardName(team1.getForwardName());
		team2Panel.updateForwardName(team2.getForwardName());
		team1Panel.updateGoalieName(team1.getGoalieName());
		team2Panel.updateGoalieName(team2.getGoalieName());
	}
	public void resetScores() {
		team1.setScore(0);
		team2.setScore(0);
		team1Panel.updateScore(team1.getScore());
		team2Panel.updateScore(team2.getScore());
	}
	public void resetGameCounts() {
		team1.setGameCount(0);
		team2.setGameCount(0);
		team1Panel.updateGameCount(team1.getGameCount());
		team2Panel.updateGameCount(team2.getGameCount());
	}
	public void resetTimeOuts() {
		team1.resetTimeOuts();
		team2.resetTimeOuts();
		team1Panel.updateTimeOutCount(team1.getTimeOutCount());
		team2Panel.updateTimeOutCount(team2.getTimeOutCount());
	}
	public void resetResetWarns() {
		team1.setReset(false);
		team2.setReset(false);
		team1.setWarn(false);
		team2.setWarn(false);
		team1Panel.updateReset(team1.getReset());
		team2Panel.updateReset(team2.getReset());
		team1Panel.updateWarn(team1.getWarn());
		team2Panel.updateWarn(team2.getWarn());
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
		team1.writeAll();
		team2.writeAll();
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
		team1Panel.displayAllFields(teamName1, forwardName1, goalieName1, score1, gameCount1, timeOutCount1, isReset1, isWarn1);
		team2Panel.displayAllFields(teamName2, forwardName2, goalieName2, score2, gameCount2, timeOutCount2, isReset2, isWarn2);
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
	}
	public void toggleReset(String txt) {
		if(txt.equals("Team 1")) {
			team1.setReset(!team1.getReset());
			team1Panel.updateReset(team1.getReset());
		} else {
			team2.setReset(!team2.getReset());
			team2Panel.updateReset(team2.getReset());
		}
	}
	public void toggleWarn(String txt) {
		if(txt.equals("Team 1")) {
			team1.setWarn(!team1.getWarn());
			team1Panel.updateWarn(team1.getWarn());
		} else {
			team2.setWarn(!team2.getWarn());
			team2Panel.updateWarn(team2.getWarn());
		}
	}
	public void setReset(String txt, boolean state) {
		if(txt.equals("Team 1")) {
			team1.setReset(state);
			team1Panel.updateReset(team1.getReset());
		} else {
			team2.setReset(state);
			team2Panel.updateReset(team2.getReset());
		}
	}
	public void setWarn(String txt, boolean state) {
		if(txt.equals("Team 1")) {
			team1.setWarn(state);
			team1Panel.updateWarn(team1.getWarn());
		} else {
			team2.setWarn(state);
			team2Panel.updateWarn(team2.getWarn());
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
			match.setLastScored(obsInterface.getContents(settings.getLastScoredFileName()));
			team1Panel.updateTeamName(team1.getTeamName());
			team2Panel.updateTeamName(team2.getTeamName());
			team1Panel.updateForwardName(team1.getForwardName());
			team2Panel.updateForwardName(team2.getForwardName());
			team1Panel.updateGoalieName(team1.getGoalieName());
			team2Panel.updateGoalieName(team2.getGoalieName());
			team1Panel.updateScore(team1.getScore());
			team2Panel.updateScore(team2.getScore());
			team1Panel.updateGameCount(team1.getGameCount());
			team2Panel.updateGameCount(team2.getGameCount());
			team1Panel.updateTimeOutCount(team1.getTimeOutCount());
			team2Panel.updateTimeOutCount(team2.getTimeOutCount());
			team1Panel.updateReset(team1.getReset());
			team2Panel.updateReset(team2.getReset());
			team1Panel.updateWarn(team1.getWarn());
			team2Panel.updateWarn(team2.getWarn());
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
}
