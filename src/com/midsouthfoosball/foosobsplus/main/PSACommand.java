package com.midsouthfoosball.foosobsplus.main;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.controller.StatsController;

public class PSACommand implements Command {
	private StatsController statsController;
	private Main main;
	
	public PSACommand(StatsController statsController, Main main) {
		this.statsController = statsController;
		this.main = main;
	}
	
	public void execute() {
		main.showAllBalls();
	}

	public String getCode() {
		return statsController.getLastCode();
	}

}
