package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class STTCommand implements Command {
	private TeamController teamController;
	
	public STTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startTimeOutTimer();
	}
}
