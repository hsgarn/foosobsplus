package commands;

import controller.TeamController;

public class PRT1Command implements Command {
	private TeamController teamController;
	
	public PRT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.toggleReset("Team 1");
	}
}
