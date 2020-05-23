package commands;

import controller.TeamController;

public class IST1Command implements Command {
	private TeamController teamController;
	
	public IST1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.incrementScore("Team 1");
	}
}
