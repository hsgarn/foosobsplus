/**
Copyright © 2020-2024 Hugh Garner
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import com.midsouthfoosball.foosobsplus.main.StreamIndexer;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.view.GameTableWindowPanel;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;

public class MatchController {
	private Match match;
	private Stats stats;
	private GameClock gameClock;
	private LastScoredClock lastScored1Clock;
	private LastScoredClock lastScored2Clock;
	private MatchPanel matchPanel;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private SwitchPanel switchPanel;
	private GameTableWindowPanel gameTableWindowPanel;
	private TeamController teamController;
	private StreamIndexer streamIndexer;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public MatchController(Match match, Stats stats, GameClock gameClock, LastScoredClock lastScored1Clock, LastScoredClock lastScored2Clock, MatchPanel matchPanel, StatsEntryPanel statsEntryPanel, StatsDisplayPanel statsDisplayPanel, SwitchPanel switchPanel, GameTableWindowPanel gameTableWindowPanel, TeamController teamController, StreamIndexer streamIndexer) {
		this.match = match;
		this.stats = stats;
		this.gameClock = gameClock;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.matchPanel = matchPanel;
		this.statsEntryPanel = statsEntryPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.switchPanel = switchPanel;
		this.gameTableWindowPanel = gameTableWindowPanel;
		this.teamController = teamController;
		this.gameClock.addGameClockTimerListener(new GameClockTimerListener());
		this.streamIndexer = streamIndexer;
		updateGameTables();
	}
	////// Match Panel Listener Objects \\\\\\
	private class GameClockTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			matchPanel.updateElapsedTime(gameClock.getMatchTime());
			String time = gameClock.getGameTime();
			match.setGameTime(time);
			matchPanel.updateGameTime(time);
			matchPanel.setTime(time);
			gameTableWindowPanel.setTime(time);
		}
	}
	////// Utility Methods \\\\\\
	public int incrementScore(int teamNumber) {
		// return 1 to rotate after team1 wins (Cutthroat)
		// return 2 to rotate after team2 or team3 scores (Cutthroat)
		int rotate = 0;
		if (match.getMatchWon()) {
			match.resetMatch();
			teamController.resetScores();
			teamController.resetGameCounts();
			startMatch(createMatchId());
			if (Settings.getControlParameter("CutThroatMode",Function.identity()).equals("1")) {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Auto Start Match: " + teamController.getForwardName(1) + "/" + teamController.getGoalieName(1) + " vs " + teamController.getForwardName(2) + "/" + teamController.getGoalieName(2) + " vs " + teamController.getForwardName(3) + "/" + teamController.getGoalieName(3) + "\r\n");
			} else {
				streamIndexer.appendStreamIndexer(dtf.format(LocalDateTime.now()) + ": " + gameClock.getStreamTime() + ": Auto Start Match: " + teamController.getForwardName(1) + "/" + teamController.getGoalieName(1) + " vs " + teamController.getForwardName(2) + "/" + teamController.getGoalieName(2) + "\r\n");
			}
		}
		if (Settings.getControlParameter("CutThroatMode",Function.identity()).equals("1")) {
			int won = 0;
			if (teamNumber == 1) {
				//Single player scores
				won = teamController.incrementScore(teamNumber);
				if (won > 0) {
					//Rotate teams 2 & 3
					rotate = 1;
				}
			} else {
				//Rotate all players and scores
				rotate = 2;
			}
		} else {
			int winState = teamController.incrementScore(teamNumber);
			if (winState==1) {
				match.increaseCurrentGameNumber();
				startGame();
			}
			if (winState==2) {
				gameClock.stopMatchTimer();
				gameClock.stopGameTimer();
				match.incrementMatchCount(teamNumber);
			}
			switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
			updateGameTables();
		}
		return rotate;
	}
	public void decrementScore(int teamNumber) {
		teamController.decrementScore(teamNumber);
		switchPanel.setLastScored(Settings.getLastScoredStrings()[match.getLastScored()]);
		updateGameTables();
	}
	public void updateGameTables() {
		statsDisplayPanel.updateTeams(1,teamController.getDisplayName(1),teamController.getTeamName(1));
		statsDisplayPanel.updateTeams(2,teamController.getDisplayName(2),teamController.getTeamName(2));
		matchPanel.setGameWinners(match.getGameWinners());
		matchPanel.setMatchWinner(match.getMatchWinner());
		matchPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getScoresTeam3(), match.getTimes(), match.getCurrentGameNumber());
		gameTableWindowPanel.setTeams(teamController.getDisplayName(1) + ":",teamController.getDisplayName(2) + ":",teamController.getDisplayName(3) + ":");
		gameTableWindowPanel.setGameWinners(match.getGameWinners());
		gameTableWindowPanel.setMatchWinner(match.getMatchWinner());
		gameTableWindowPanel.updateGameTable(match.getScoresTeam1(), match.getScoresTeam2(), match.getScoresTeam3(), match.getTimes(),match.getCurrentGameNumber());
	}
	public void switchSides() {
		match.switchSides();
		updateGameTables();
	}
	public void startMatch(String matchId) {
		match.setMatchStarted(true);
		teamController.resetAll();
		match.resetMatch();
		displayAllStats();
		gameClock.startMatchTimer();
		match.startMatch(matchId);
		matchPanel.setPauseLabel("Pause Match");
		matchPanel.updateStartTime(match.getStartTime());
		startGame();
		String tmpCode  = stats.getLastCode();
		stats.clearAll();
		statsEntryPanel.clearAll();
		stats.setIsCommand(true);
		stats.addCodeToHistory(tmpCode);
		statsEntryPanel.updateCodeHistory(tmpCode);
	}
	public void startGame() {
		if(match.isMatchPaused()==true) {
			pauseMatch();
		}
		gameClock.startGameTimer();
		match.startGame();
		int winState = match.getWinState();
		if (winState>=1) {
			teamController.resetScores();
			teamController.resetTimeOuts();
			teamController.resetResetWarns();
		}
		updateGameTables();
	}
	public String createMatchId() {
		return match.createMatchId();
	}
	public void endMatch() {
		match.setMatchStarted(false);
		gameClock.pauseGameTimer(true);
		gameClock.pauseMatchTimer(true);
		lastScored1Clock.pauseLastScoredTimer(true);
		lastScored2Clock.pauseLastScoredTimer(true);
	}
	public void pauseMatch() {
		if(match.isMatchPaused()==true) {
			match.setMatchPaused(false);
			matchPanel.setPauseLabel("Pause Match");
			boolean pause = match.isMatchPaused();
			gameClock.pauseMatchTimer(pause);
			gameClock.pauseGameTimer(pause);
			lastScored1Clock.pauseLastScoredTimer(pause);
			lastScored2Clock.pauseLastScoredTimer(pause);
		} else {
			if(match.isMatchStarted()) {
				match.setMatchPaused(true);
				matchPanel.setPauseLabel("Unpause Match");
				boolean pause = match.isMatchPaused();
				gameClock.pauseMatchTimer(pause);
				gameClock.pauseGameTimer(pause);
				lastScored1Clock.pauseLastScoredTimer(pause);
				lastScored2Clock.pauseLastScoredTimer(pause);
			}
		}
	}
	public void resetMatch() {
		match.resetMatch();
	}
	
	public void displayAllStats() {
		statsDisplayPanel.updateTeams(1,teamController.getDisplayName(1),teamController.getTeamName(1));
		statsDisplayPanel.updateTeams(2,teamController.getDisplayName(2),teamController.getTeamName(2));
		statsDisplayPanel.updatePassStats(1, teamController.getPassCompletes(1),teamController.getPassAttempts(1),teamController.getPassPercent(1));
		statsDisplayPanel.updatePassStats(2, teamController.getPassCompletes(2),teamController.getPassAttempts(2),teamController.getPassPercent(2));
		statsDisplayPanel.updateShotStats(1, teamController.getShotCompletes(1),teamController.getShotAttempts(1),teamController.getShotPercent(1));
		statsDisplayPanel.updateShotStats(2, teamController.getShotCompletes(2),teamController.getShotAttempts(2),teamController.getShotPercent(2));
		statsDisplayPanel.updateClearStats(1, teamController.getClearCompletes(1),teamController.getClearAttempts(1),teamController.getClearPercent(1));
		statsDisplayPanel.updateClearStats(2, teamController.getClearCompletes(2),teamController.getClearAttempts(2),teamController.getClearPercent(2));
		statsDisplayPanel.updateTwoBarPassStats(1, teamController.getTwoBarPassCompletes(1),teamController.getTwoBarPassAttempts(1),teamController.getTwoBarPassPercent(1));
		statsDisplayPanel.updateTwoBarPassStats(2, teamController.getTwoBarPassCompletes(2),teamController.getTwoBarPassAttempts(2),teamController.getTwoBarPassPercent(2));
		statsDisplayPanel.updateScoring(1, teamController.getScoring(1));
		statsDisplayPanel.updateScoring(2, teamController.getScoring(2));
		statsDisplayPanel.updateThreeBarScoring(1, teamController.getThreeBarScoring(1));
		statsDisplayPanel.updateThreeBarScoring(2, teamController.getThreeBarScoring(2));
		statsDisplayPanel.updateFiveBarScoring(1, teamController.getFiveBarScoring(1));
		statsDisplayPanel.updateFiveBarScoring(2, teamController.getFiveBarScoring(2));
		statsDisplayPanel.updateTwoBarScoring(1, teamController.getTwoBarScoring(1));
		statsDisplayPanel.updateTwoBarScoring(2, teamController.getTwoBarScoring(2));
		statsDisplayPanel.updateStuffs(1, teamController.getStuffs(1));
		statsDisplayPanel.updateStuffs(2, teamController.getStuffs(2));
		statsDisplayPanel.updateBreaks(1, teamController.getBreaks(1));
		statsDisplayPanel.updateBreaks(2, teamController.getBreaks(2));
		statsDisplayPanel.updateAces(1, teamController.getAces(1));
		statsDisplayPanel.updateAces(2, teamController.getAces(2));
		statsDisplayPanel.updateShotsOnGoal(1, teamController.getShotsOnGoal(1));
		statsDisplayPanel.updateShotsOnGoal(2, teamController.getShotsOnGoal(2));
	}

}
