package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class IST2Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public IST2Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.incrementScore("Team 2");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
