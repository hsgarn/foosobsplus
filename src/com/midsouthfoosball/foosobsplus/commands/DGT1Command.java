package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class DGT1Command implements Command {
	private TeamController teamController;
	
	public DGT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementGameCount("Team 1");
	}
}
