package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class DST2Command implements Command {
	private TeamController teamController;
	
	public DST2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementScore("Team 2");
	}
}
