package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRT1Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PRT1Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.toggleReset("Team 1");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
