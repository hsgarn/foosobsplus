package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class DGT1Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public DGT1Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementGameCount("Team 1");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
