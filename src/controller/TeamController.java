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

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.Match;
import model.Team;
import view.SwitchPanel;
import view.TeamPanel;

public class TeamController {
	private Team team1;
	private Team team2;
	private Match match;
	private TeamPanel team1Panel;
	private TeamPanel team2Panel;
	private SwitchPanel switchPanel;
	private TimerController timerController;
	private String[] lastScoredStrings = {"     Last Scored     ", "<--- Last Scored     ", "     Last Scored --->"};
	
	public TeamController(Team team1, Team team2, Match match, TeamPanel team1Panel, TeamPanel team2Panel, SwitchPanel switchPanel, TimerController timerController) {
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
			};
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
			if(name.equals("Team 1")) {
				team1Panel.updateTimeOutCount(team1.getTimeOutCount());
			} else {
				team2Panel.updateTimeOutCount(team2.getTimeOutCount());
			};
		}
	}
	private class TimeOutCountDecreaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			String name = btn.getName();
			restoreTimeOut(name);
			if(name.equals("Team 1")) {
				team1Panel.updateTimeOutCount(team1.getTimeOutCount());
			} else {
				team2Panel.updateTimeOutCount(team2.getTimeOutCount());
			}
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
			match.setLastScored(0);
			switchPanel.setLastScored(lastScoredStrings[match.getLastScored()]);
		}
	}

	////// Utility Methods //////

	private String convertNumbers(String checkString) {
		try {
			Integer.parseInt(checkString);
			return checkString;
		} catch (NumberFormatException e) {
			return "0";
		}
	}
	public void incrementScore(String name) {
		int winState = 0;
		if(name.equals("Team 1")) {
			winState = match.incrementScore(1);
		} else {
			winState = match.incrementScore(2);
		};
		switchPanel.setLastScored(lastScoredStrings[match.getLastScored()]);
		timerController.resetTimer();
		if(winState==1) {timerController.startGameTimer();}
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
		if(name.equals("Team 1")) {
			match.incrementGameCount(team1);
			team1Panel.updateGameCount(team1.getGameCount());
		} else {
			match.incrementGameCount(team2);
			team2Panel.updateGameCount(team2.getGameCount());
		};
	}
	public void switchSides() {
		Team tmp = team1;
		team1 = team2;
		team2 = tmp;
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
		switchPanel.setLastScored(lastScoredStrings[0]);
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
	public void resetAll() {
		resetScores();
		resetGameCounts();
		resetTimeOuts();
		resetResetWarns();
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
		switchPanel.setLastScored(lastScoredStrings[match.getLastScored()]);
	}
	public void callTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.callTimeOut();
		} else {
			team2.callTimeOut();
		}
	}
	public void restoreTimeOut(String txt) {
		if(txt.equals("Team 1")) {
			team1.restoreTimeOut();
		} else {
			team2.restoreTimeOut();
		}
	}
}
