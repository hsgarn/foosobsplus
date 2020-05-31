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

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.GameClock;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.view.MatchPanel;

public class MatchController {
	private Settings settings;
	private OBSInterface obsInterface;
	private Match match;
	private GameClock gameClock;
	private MatchPanel matchPanel;
	private TeamController teamController;
	private StatsController statsController;
	public MatchController(OBSInterface obsInterface, Settings settings, Match match, GameClock gameClock, MatchPanel matchPanel, TeamController teamController, StatsController statsController) {
		this.settings = settings;
		this.obsInterface = obsInterface;
		this.match = match;
		this.gameClock = gameClock;
		this.matchPanel = matchPanel;
		this.teamController = teamController;
		this.statsController = statsController;

		this.matchPanel.addStartMatchListener(new StartMatchListener());
		this.gameClock.addGameClockTimerListener(new GameClockTimerListener());
	}
	
	////// Match Panel Listener Objects \\\\\\

	private class StartMatchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.resetAll();
			statsController.displayAllStats();
			gameClock.startMatchTimer();
			gameClock.startGameTimer();
			match.setStartTime(getCurrentTime());
			matchPanel.updateStartTime(match.getStartTime());
		}
	}
	private class GameClockTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			matchPanel.updateElapsedTime(gameClock.getMatchTime());
		}
	}
	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH;mm:ss");
		return formatter.format(date);
	}
	
}
