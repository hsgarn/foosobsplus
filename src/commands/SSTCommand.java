package commands;

import controller.TeamController;

public class SSTCommand implements Command {
	private TeamController teamController;
	
	public SSTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startShotTimer();
	}

}
