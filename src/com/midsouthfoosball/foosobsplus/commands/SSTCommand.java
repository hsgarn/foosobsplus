package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;

public class SSTCommand implements Command {
	private TeamController teamController;
	
	public SSTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startShotTimer();
	}

}
