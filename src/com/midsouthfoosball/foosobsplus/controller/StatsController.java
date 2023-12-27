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

import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;

public class StatsController {

	private Stats stats;
	private StatsDisplayPanel statsDisplayPanel;
	private TeamController teamController;
	
	public StatsController(Stats stats, StatsDisplayPanel statsDisplayPanel, TeamController teamController) {
		this.stats = stats;
		this.statsDisplayPanel = statsDisplayPanel;
		this.teamController = teamController;
	}

	////// Utility Methods \\\\\\
	
	public String getLastCode() {
		return stats.getLastCode();
	}
	
	public void displayAllStats() {
		String name1;
		String name2;
		if (teamController.getGoalieName(1).isEmpty()) {
			name1 = teamController.getForwardName(1);
		} else {
			name1 = teamController.getForwardName(1) + "/" + teamController.getGoalieName(1);
		}
		if (teamController.getGoalieName(2).isEmpty()) {
			name2 = teamController.getForwardName(2);
		} else {
			name2 = teamController.getForwardName(2) + "/" + teamController.getGoalieName(2);
		}
		statsDisplayPanel.updateTeams(1,name1,teamController.getTeamName(1));
		statsDisplayPanel.updateTeams(2,name2,teamController.getTeamName(2));
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
		statsDisplayPanel.updateAces(1, teamController.getAces(1));
		statsDisplayPanel.updateAces(2, teamController.getAces(2));
		statsDisplayPanel.updateShotsOnGoal(1, teamController.getShotsOnGoal(1));
		statsDisplayPanel.updateShotsOnGoal(2, teamController.getShotsOnGoal(2));
	}
}