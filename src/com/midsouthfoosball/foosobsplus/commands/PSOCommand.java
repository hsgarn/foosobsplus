package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.main.Main;

public class PSOCommand implements Command {
	private StatsController statsController;
	private Main main;
	
	public PSOCommand(StatsController statsController, Main main) {
		this.statsController = statsController;
		this.main = main;
	}
	
	public void execute() {
		main.obsSyncBalls();
	}

	public String getCode() {
		return statsController.getLastCode();
	}
}
