package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.model.Team;

public class Team1Memento {
	private final Team team1;
	
	public Team1Memento(Team team1) {
		this.team1 = team1;
		System.out.println("team1memento stored: " + this.team1.getGameCount());
	}

	public Team getState() {
		System.out.println("team1memento retrieved: " + team1.getGameCount());
		return team1;
	}
	
}
