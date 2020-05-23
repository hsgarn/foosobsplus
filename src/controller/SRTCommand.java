package controller;

import commands.Command;

public class SRTCommand implements Command {
	private TeamController teamController;
	
	public SRTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startRecallTimer();
	}
}
