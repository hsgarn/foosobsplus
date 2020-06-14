package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class SGTCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public SGTCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startGameTimer();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
