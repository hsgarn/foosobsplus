package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRGCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PRGCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.resetGameCounts();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
