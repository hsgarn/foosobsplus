package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class PRTCommand implements Command {
	private TeamController teamController;
	
	public PRTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.resetTimer();
	}
}
