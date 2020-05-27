package commands;

import controller.TeamController;

public class SRTCommand implements Command {
	private TeamController teamController;
	
	public SRTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startRecallTimer();
	}
}
