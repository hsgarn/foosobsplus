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
import java.text.SimpleDateFormat;
import java.util.Date;

import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.LastScored1Clock;
import com.midsouthfoosball.foosobsplus.model.LastScored2Clock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;

public class MatchController {
	private Match match;
	private Stats stats;
	private GameClock gameClock;
	private LastScored1Clock lastScored1Clock;
	private LastScored2Clock lastScored2Clock;
	private MatchPanel matchPanel;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private TeamController teamController;
	
	public MatchController(Match match, Stats stats, GameClock gameClock, LastScored1Clock lastScored1Clock, LastScored2Clock lastScored2Clock, MatchPanel matchPanel, StatsEntryPanel statsEntryPanel, StatsDisplayPanel statsDisplayPanel, TeamController teamController) {
		this.match = match;
		this.stats = stats;
		this.gameClock = gameClock;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2Clock = lastScored2Clock;
		this.matchPanel = matchPanel;
		this.statsEntryPanel = statsEntryPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.teamController = teamController;

		this.gameClock.addGameClockTimerListener(new GameClockTimerListener());
	}
	
	////// Match Panel Listener Objects \\\\\\

	private class GameClockTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			matchPanel.updateElapsedTime(gameClock.getMatchTime());
		}
	}
	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(date);
	}
	public void startMatch() {
		teamController.resetAll();
		displayAllStats();
		gameClock.startMatchTimer();
		gameClock.startGameTimer();
		match.setStartTime(getCurrentTime());
		matchPanel.updateStartTime(match.getStartTime());
		String tmpCode  = stats.getLastCode();
		stats.clearAll();
		statsEntryPanel.clearAll();
		stats.setIsCommand(true);
		stats.addCodeToHistory(tmpCode);
		statsEntryPanel.updateCodeHistory(tmpCode);
		match.setMatchPaused(false);
		matchPanel.setPauseLabel("Pause Match");
	}
	public void pauseMatch() {
		if(match.isMatchPaused()==true) {
			match.setMatchPaused(false);
			matchPanel.setPauseLabel("Unpause Match");
		} else {
			match.setMatchPaused(true);
			matchPanel.setPauseLabel("Pause Match");
		}
		boolean pause = match.isMatchPaused();
		gameClock.pauseMatchTimer(pause);
		gameClock.pauseGameTimer(pause);
		lastScored1Clock.pauseLastScoredTimer(pause);
		lastScored2Clock.pauseLastScoredTimer(pause);
	}
	
	public void displayAllStats() {
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
		statsDisplayPanel.updateStuffs(1,  teamController.getStuffs(1));
		statsDisplayPanel.updateStuffs(2,  teamController.getStuffs(2));
		statsDisplayPanel.updateBreaks(1,  teamController.getBreaks(1));
		statsDisplayPanel.updateBreaks(2,  teamController.getBreaks(2));
		statsDisplayPanel.updateShotsOnGoal(1, teamController.getShotsOnGoal(1));
		statsDisplayPanel.updateShotsOnGoal(2, teamController.getShotsOnGoal(2));
	}

}
