package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.MatchController;

public class PSMCommand implements Command {
	private MatchController matchController;
	
	public PSMCommand(MatchController matchController) {
		this.matchController = matchController;
	}

	public void execute() {
		matchController.startMatch();
	}
}
