package commands;

import controller.TeamController;

public class SGTCommand implements Command {
	private TeamController teamController;
	
	public SGTCommand(TeamController teamController) {
		this.teamController = teamController;
	}
	
	public void execute() {
		teamController.startGameTimer();
	}
}
