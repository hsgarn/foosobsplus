package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PSTOCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PSTOCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchTimeOuts();
	}

	public String getCode() {
		return statsController.getLastCode();
	}

}
