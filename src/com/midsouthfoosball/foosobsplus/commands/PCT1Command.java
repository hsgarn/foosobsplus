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
