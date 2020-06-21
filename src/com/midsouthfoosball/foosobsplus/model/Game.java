/**
Copyright 2020 Hugh Garner
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

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class Game {
	private Team team1;
	private Team team2;
	private Settings settings;
	private OBSInterface obsInterface;
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
	private int number; // Game Number
	private int maxGames;
	
	public Game(OBSInterface obsInterface, Settings settings,Team team1, Team team2, int number, int maxGames) {
		this.team1 = team1;
		this.team2 = team2;
		this.settings = settings;
		this.obsInterface = obsInterface;
		this.number = number;
		this.maxGames = maxGames;
//		System.out.println("objects: " + this.obsInterface + this.settings + this.team1 + this.team2 + this.number + this.maxGames);
	}

}
