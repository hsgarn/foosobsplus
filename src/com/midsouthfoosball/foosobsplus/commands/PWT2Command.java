package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PWT2Command implements Command {
	private TeamController teamController;
	
	public PWT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.toggleWarn("Team 2");
	}
}
