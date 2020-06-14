package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class XPT1Command implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public XPT1Command(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchPositions("Team 1");
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
