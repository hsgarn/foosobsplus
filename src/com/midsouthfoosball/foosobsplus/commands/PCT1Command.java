/**
Copyright 2020-2023 Hugh Garner
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

package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;

public class PCT1Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	private Team team1;
	private Match match;
	private SwitchPanel switchPanel;
	private Settings settings;
	
	public PCT1Command(StatsController statsController, TeamController teamController, Team team1, Match match, SwitchPanel switchPanel, Settings settings) {
		this.statsController = statsController;
		this.teamController = teamController;
		this.team1 = team1;
		this.match = match;
		this.switchPanel = switchPanel;
		this.settings = settings;
	}
	
	public void execute() {
		team1.clearAll();
		teamController.displayAll();
		match.clearAll();
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		statsController.displayAllStats();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
