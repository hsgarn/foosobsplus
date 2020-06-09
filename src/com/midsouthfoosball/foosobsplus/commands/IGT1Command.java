package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Team;

public class IGT1Command extends Command {
	private Match match;
	private MatchMemento matchMemento;
	private Team team1;
	private Team1Memento team1Memento;
	private Team team2;
	private Team2Memento team2Memento;
	private TeamController teamController;

	public IGT1Command(TeamController teamController, Match match, Team team1, Team team2) {
		this.teamController = teamController;
		this.match = match;
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public void execute() {
		// Save current state to mementos
		matchMemento = new MatchMemento(match);
		team1Memento = new Team1Memento(team1);
		team2Memento = new Team2Memento(team2);
		System.out.println("Stored t1GameCount: " + team1.getGameCount());
		System.out.println("Stored team1object: " + team1);
		teamController.incrementGameCount("Team 1");
		System.out.println("new t1GameCount: " + team1.getGameCount());
	}

	public void unExecute() {
		// Retrieve states from mementos
		System.out.println("current t1GameCount: " + team1.getGameCount());
		System.out.println("current team1object: " + this.team1);
		this.match = matchMemento.getState();
		this.team1 = team1Memento.getState();
		this.team2 = team2Memento.getState();
		System.out.println("restored t1GameCount: " + this.team1.getGameCount());
		System.out.println("restored team1object: " + this.team1);
	}
}
