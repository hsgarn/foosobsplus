package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class RTT2Command implements Command {
	private TeamController teamController;
	
	public RTT2Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.restoreTimeOut("Team 2");
	}
}
