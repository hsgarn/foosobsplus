package commands;

import controller.TeamController;

public class DST1Command implements Command {
	private TeamController teamController;
	
	public DST1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.decrementScore("Team 1");
	}
}
