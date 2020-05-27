package com.midsouthfoosball.foosobsplus.commands;

import controller.TeamController;

public class PSSCommand implements Command {
	private TeamController teamController;
	
	public PSSCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchSides();
	}

}
