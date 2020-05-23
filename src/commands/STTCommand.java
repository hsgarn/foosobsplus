package commands;

import controller.TeamController;

public class STTCommand implements Command {
	private TeamController teamController;
	
	public STTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startTimeOutTimer();
	}
}
