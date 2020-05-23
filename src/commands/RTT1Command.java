package commands;

import controller.TeamController;

public class RTT1Command implements Command {
	private TeamController teamController;
	
	public RTT1Command(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.restoreTimeOut("Team 1");
	}
}