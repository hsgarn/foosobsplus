package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Team;

//import com.midsouthfoosball.foosobsplus.model.Match;
//import com.midsouthfoosball.foosobsplus.model.Team;

public abstract class Command {
	
	Match match;
	MatchMemento matchMemento;
	Team team1;
	Team1Memento team1Memento;
	Team team2;
	Team2Memento team2Memento;
	
	public abstract void execute();
	
	public abstract void unExecute();
	
}
