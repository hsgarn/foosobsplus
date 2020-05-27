package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class IST2Command implements Command {
	private TeamController teamController;
	
	public IST2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.incrementScore("Team 2");
	}
}
