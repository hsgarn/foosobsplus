package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class UTT2Command implements Command {
	private TeamController teamController;
	
	public UTT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.callTimeOut("Team 2");
	}
}
