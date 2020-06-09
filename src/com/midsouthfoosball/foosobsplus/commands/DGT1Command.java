package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Team;

public class DGT1Command extends Command {
	private TeamController teamController;
	private Match match;
	private Team team1;
	private Team team2;
	private MatchMemento matchMemento;
	private Team1Memento team1Memento;
	private Team2Memento team2Memento;
	
	public DGT1Command(TeamController teamController, Match match, Team team1, Team team2) {
		this.teamController = teamController;
		this.match = match;
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public void execute() {
		matchMemento = new MatchMemento(match);
		team1Memento = new Team1Memento(team1);
		team2Memento = new Team2Memento(team2);
		teamController.decrementGameCount("Team 1");
	}

	public void unExecute() {
		this.match = matchMemento.getState();
		this.team1 = team1Memento.getState();
		this.team2 = team2Memento.getState();
	}
}
