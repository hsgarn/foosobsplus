package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class DGT2Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public DGT2Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementGameCount("Team 2");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
