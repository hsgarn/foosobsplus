package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class IGT1Command implements Command {
	private TeamController teamController;
	
	public IGT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.incrementGameCount("Team 1");
	}
}