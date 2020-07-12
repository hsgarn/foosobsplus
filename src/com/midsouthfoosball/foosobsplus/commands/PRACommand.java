package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRACommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PRACommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.resetAll();
		statsController.displayAllStats();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
