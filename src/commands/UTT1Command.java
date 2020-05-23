package commands;

import controller.TeamController;

public class UTT1Command implements Command {
	private TeamController teamController;
	
	public UTT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.callTimeOut("Team 1");
	}
}
