package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.model.Match;

public class MatchMemento {
	private final Match match;
	
	public MatchMemento(Match match) {
		this.match = match;
	}
	public Match getState() {
		return match;
	}
	
}
