package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PSRCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PSRCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchResetWarns();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
