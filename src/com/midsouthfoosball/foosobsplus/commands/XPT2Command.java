package com.midsouthfoosball.foosobsplus.commands;

import controller.TeamController;

public class XPT2Command implements Command {
	private TeamController teamController;
	
	public XPT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchPositions("Team 2");
	}

}
