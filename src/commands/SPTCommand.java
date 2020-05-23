package commands;

import controller.TeamController;

public class SPTCommand implements Command {
	private TeamController teamController;
	
	public SPTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startPassTimer();
	}
}
