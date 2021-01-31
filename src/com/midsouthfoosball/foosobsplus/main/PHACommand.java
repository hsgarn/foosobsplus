package com.midsouthfoosball.foosobsplus.main;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.controller.StatsController;

public class PHACommand implements Command {
	private StatsController statsController;
	private Main main;
	
	public PHACommand(StatsController statsController, Main main) {
		this.statsController = statsController;
		this.main = main;
	}
	
	public void execute() {
		main.hideAllBalls();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
