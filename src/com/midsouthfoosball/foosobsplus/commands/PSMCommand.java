package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.MatchController;
import com.midsouthfoosball.foosobsplus.controller.StatsController;

public class PSMCommand implements Command {
	private StatsController statsController;
	private MatchController matchController;
	
	public PSMCommand(StatsController statsController, MatchController matchController) {
		this.statsController = statsController;
		this.matchController = matchController;
	}

	public void execute() {
		matchController.startMatch();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
