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
package model;

public class Team {

	private String teamName;
	private String forwardName;
	private String goalieName;
	private int score = 0;
	private int gameCount = 0;
	private int timeOutCount;
	private boolean resetState = false;
	private boolean warnState = false;
	private Settings settings;
	
	public Team(Settings settings) {
		this.settings = settings;
		resetTimeOuts();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setScore(String score) {
		setScore(Integer.parseInt(score));
	}
	public int incrementScore() {
		score++;
		return score;
	}
	public int decrementScore() {
		if(score > 0) score--;
		return score;
	}
	public int getGameCount() {
		return gameCount;
	}
	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}
	public void setGameCount(String gameCount) {
		this.gameCount = Integer.parseInt(gameCount);
	}
	public int incrementGameCount() {
		gameCount++;
		return gameCount;
	}
	public int decrementGameCount() {
		if(gameCount > 0) gameCount--;
		return gameCount;
	}	
	public int getTimeOutCount() {
		return timeOutCount;
	}
	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
	}
	public void setTimeOutCount(String timeOutCount) {
		this.timeOutCount = Integer.parseInt(timeOutCount);
	}
	public int callTimeOut() {
		if(settings.getShowTimeOutsUsed() == 1) {
			timeOutCount++;
			if(timeOutCount>settings.getMaxTimeOuts()) {
				timeOutCount=settings.getMaxTimeOuts();
			}
		} else {
			timeOutCount--;
			if(timeOutCount<0) {
				timeOutCount=0;
			}
		}
		return timeOutCount;
	}
	public int restoreTimeOut() {
		if(settings.getShowTimeOutsUsed()==1) {
			timeOutCount--;
			if(timeOutCount<0) {
				timeOutCount=0;
			}
		} else {
			timeOutCount++;
			if(timeOutCount>settings.getMaxTimeOuts()) {
				timeOutCount=settings.getMaxTimeOuts();
			}
		}
		return timeOutCount;
	}
	public boolean getReset() {
		return resetState;
	}
	public boolean getWarn() {
		return warnState;
	}
	public void setReset(boolean state) {
		resetState = state;
	}
	public void setWarn(boolean state) {
		warnState = state;
	}
	public String[] switchPositions() {
		String[] names = new String[2];
		String tmp = forwardName;
		forwardName = goalieName;
		goalieName = tmp;
		names[0] = forwardName;
		names[1] = goalieName;
		return names;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getForwardName() {
		return forwardName;
	}
	public String getGoalieName() {
		return goalieName;
	}
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}
	public void setGoalieName(String goalieName) {
		this.goalieName = goalieName;
	}
	public void clearAll() {
		teamName = "";
		forwardName = "";
		goalieName = "";
		score = 0;
		gameCount = 0;
		resetTimeOuts();
	}
	public void resetTimeOuts() {
		if(settings.getShowTimeOutsUsed() == 1) {
			timeOutCount = 0;
		} else {
			timeOutCount = settings.getMaxTimeOuts();
		}

	}
}
