package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PSSCommand implements Command {
	private TeamController teamController;
	
	public PSSCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchSides();
	}

}
