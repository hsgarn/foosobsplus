package com.midsouthfoosball.foosobsplus.commands;

import controller.TeamController;

public class XPT1Command implements Command {
	private TeamController teamController;
	
	public XPT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.switchPositions("Team 1");
	}
}
