package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.StatsController;

public class CodeCommand implements Command {
	private StatsController statsController;
	private String theCode;
	
	public CodeCommand(StatsController statsController) {
		this.statsController = statsController;
	}

	public void execute() {
		theCode = statsController.getLastCode();
	}

	public String getCode() {
		return theCode;
	}
	
	

}
