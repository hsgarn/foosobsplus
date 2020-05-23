package commands;

import controller.TeamController;

public class PRTCommand implements Command {
	private TeamController teamController;
	
	public PRTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.resetTimer();
	}
}
