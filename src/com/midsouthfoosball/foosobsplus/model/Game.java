/**
Copyright 2020-2024 Hugh Garner
Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR 
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
OTHER DEALINGS IN THE SOFTWARE.  
**/
package com.midsouthfoosball.foosobsplus.model;

//import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class Game {
	private Team team1;
	private Team team2;
	private Team team3;
//	private Date startTime;
//	private Date endTime;
//	private int elapsedTime;
//	private int[] points; 
//	private int[] threeBarPoints;
//	private int[] fiveBarPoints;
//	private int[] twoBarPoints;
//	private int[] shotsOnGoal;
//	private int[] breaks;
//	private int[] stuffs;
	private int gameNumber; // Game Number
	private int maxGames;
	private int finalScoreTeam1;
	private int finalScoreTeam2;
	private int finalScoreTeam3;
	private String finalTime;
	private String matchId;

//	public Game(Team team1, Team team2, int gameNumber) {
//		this.team1 = team1;
//		this.team2 = team2;
//		this.team3 = null;
//		this.gameNumber = gameNumber;
//	}
	public Game(Team team1, Team team2, Team team3, int gameNumber) {
		this.team1 = team1;
		this.team2 = team2;
		this.team3 = team3;
		this.gameNumber = gameNumber;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public Team getTeam3() {
		return team3;
	}
	public void setTeam3(Team team3) {
		this.team3 = team3;
	}
	public int getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}
	public int getMaxGames() {
		return maxGames;
	}
	public void setMaxGames(int maxGames) {
		this.maxGames = maxGames;
	}
	public int getFinalScoreTeam1() {
		return finalScoreTeam1;
	}
	public void setFinalScoreTeam1(int finalScoreTeam1) {
		this.finalScoreTeam1 = finalScoreTeam1;
	}
	public int getFinalScoreTeam2() {
		return finalScoreTeam2;
	}
	public void setFinalScoreTeam2(int finalScoreTeam2) {
		this.finalScoreTeam2 = finalScoreTeam2;
	}
	public int getFinalScoreTeam3() {
		return finalScoreTeam3;
	}
	public void setFinalScoreTeam3(int finalScoreTeam3) {
		this.finalScoreTeam3 = finalScoreTeam3;
	}
	public String getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public void clearAll() {
		matchId = "'";
		maxGames = 0;
		finalScoreTeam1 = 0;
		finalScoreTeam2 = 0;
		finalScoreTeam3 = 0;
		finalTime = "";
	}

}
