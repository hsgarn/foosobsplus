package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRT2Command implements Command {
	private TeamController teamController;
	
	public PRT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.toggleReset("Team 2");
	}
}
