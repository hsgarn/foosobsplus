package com.midsouthfoosball.foosobsplus.commands;

import controller.TeamController;

public class DGT2Command implements Command {
	private TeamController teamController;
	
	public DGT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementGameCount("Team 2");
	}
}
