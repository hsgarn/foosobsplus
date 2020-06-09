package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.model.Team;

public class Team2Memento {
	private final Team team2;
	
	public Team2Memento(Team team2) {
		this.team2 = team2;
	}
	public Team getState() {
		return team2;
	}
	

}
