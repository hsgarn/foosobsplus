package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class STTCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public STTCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startTimeOutTimer();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
