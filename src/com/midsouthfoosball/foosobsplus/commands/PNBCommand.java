package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.main.Main;

public class PNBCommand implements Command {
	private StatsController statsController;
	private Main main;
	
	public PNBCommand(StatsController statsController, Main main) {
		this.statsController = statsController;
		this.main = main;
	}
	
	public void execute() {
		main.resetNineBall();
	}

	public String getCode() {
		return statsController.getLastCode();
	}

}
