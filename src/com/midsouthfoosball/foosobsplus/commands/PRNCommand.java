package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRNCommand implements Command {
	private StatsController statsController;
	private TeamController teamController;
	
	public PRNCommand(StatsController statsController, TeamController teamController) {
		this.statsController = statsController;
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.resetNames();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}