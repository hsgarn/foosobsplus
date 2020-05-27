package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class IGT2Command implements Command {
	private TeamController teamController;
	
	public IGT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.incrementGameCount("Team 2");
	}
}
