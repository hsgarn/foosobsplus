package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.SwitchPanel;

public class PCT2Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	private Team team2;
	private Match match;
	private SwitchPanel switchPanel;
	private Settings settings;
	
	public PCT2Command(StatsController statsController, TeamController teamController, Team team2, Match match, SwitchPanel switchPanel, Settings settings) {
		this.statsController = statsController;
		this.teamController = teamController;
		this.team2 = team2;
		this.match = match;
		this.switchPanel = switchPanel;
		this.settings = settings;
	}
	
	public void execute() {
		team2.clearAll();
		teamController.displayAll();
		match.clearAll();
		switchPanel.setLastScored(settings.getLastScoredStrings()[match.getLastScored()]);
		statsController.displayAllStats();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
