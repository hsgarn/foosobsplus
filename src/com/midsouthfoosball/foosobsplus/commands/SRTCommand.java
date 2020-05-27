package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class SRTCommand implements Command {
	private TeamController teamController;
	
	public SRTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startRecallTimer();
	}
}
