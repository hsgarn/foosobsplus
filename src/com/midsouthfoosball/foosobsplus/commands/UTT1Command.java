package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class UTT1Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public UTT1Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.callTimeOut("Team 1");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
