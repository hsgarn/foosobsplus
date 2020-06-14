package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PWT2Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PWT2Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.toggleWarn("Team 2");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
