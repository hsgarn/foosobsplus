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

import java.io.IOException;

import main.OBSInterface;

public class Team {

	private String teamName;
	private String forwardName;
	private String goalieName;
	private int score = 0;
	private int gameCount = 0;
	private int timeOutCount;
	private boolean resetState = false;
	private boolean warnState = false;
	private OBSInterface obsInterface;
	private Settings settings;
	private int teamNbr;
	
	public Team(OBSInterface obsInterface, Settings settings, Integer teamNbr) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.teamNbr = teamNbr;
		resetTimeOuts();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		writeScore();
	}
	public void setScore(String score) {
		setScore(Integer.parseInt(score));
		writeScore();
	}
	public int incrementScore() {
		score++;
		writeScore();
		return score;
	}
	public int decrementScore() {
		if(score > 0) score--;
		writeScore();
		return score;
	}
	public int getGameCount() {
		return gameCount;
	}
	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
		writeGameCount();
	}
	public void setGameCount(String gameCount) {
		this.gameCount = Integer.parseInt(gameCount);
		writeGameCount();
	}
	public int incrementGameCount() {
		gameCount++;
		writeGameCount();
		return gameCount;
	}
	public int decrementGameCount() {
		if(gameCount > 0) gameCount--;
		writeGameCount();
		return gameCount;
	}	
	public int getTimeOutCount() {
		return timeOutCount;
	}
	public void setTimeOutCount(int timeOutCount) {
		this.timeOutCount = timeOutCount;
		writeTimeOuts();
	}
	public void setTimeOutCount(String timeOutCount) {
		this.timeOutCount = Integer.parseInt(timeOutCount);
		writeTimeOuts();
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
		writeTimeOuts();
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
		writeTimeOuts();
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
		writeForwardName();
		writeGoalieName();
		return names;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
		writeTeamName();
	}
	public String getForwardName() {
		return forwardName;
	}
	public String getGoalieName() {
		return goalieName;
	}
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
		writeForwardName();
	}
	public void setGoalieName(String goalieName) {
		this.goalieName = goalieName;
		writeGoalieName();
	}
	public void clearAll() {
		teamName = "";
		forwardName = "";
		goalieName = "";
		score = 0;
		gameCount = 0;
		resetTimeOuts();
		writeTeamName();
		writeForwardName();
		writeGoalieName();
		writeScore();
		writeGameCount();
	}
	public void resetTimeOuts() {
		if(settings.getShowTimeOutsUsed() == 1) {
			timeOutCount = 0;
		} else {
			timeOutCount = settings.getMaxTimeOuts();
		}
		writeTimeOuts();
	}
    private void writeTeamName() {
		try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getTeam1NameFileName(), getTeamName());
			} else {
	    		obsInterface.setContents(settings.getTeam2NameFileName(), getTeamName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeForwardName() {
		try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getTeam1ForwardFileName(), getForwardName());
			} else {
				obsInterface.setContents(settings.getTeam2ForwardFileName(), getForwardName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeGoalieName() {
		try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getTeam1GoalieFileName(), getGoalieName());
			} else {
				obsInterface.setContents(settings.getTeam2GoalieFileName(), getGoalieName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeScore() {
		try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getScore1FileName(), Integer.toString(getScore()));
			} else {
				obsInterface.setContents(settings.getScore2FileName(), Integer.toString(getScore()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeGameCount() {
    	try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getGameCount1FileName(), Integer.toString(getGameCount()));
			} else {
				obsInterface.setContents(settings.getGameCount2FileName(), Integer.toString(getGameCount()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTimeOuts() {
    	try {
			if (teamNbr==1) { 
				obsInterface.setContents(settings.getTimeOut1FileName(), Integer.toString(getTimeOutCount()));
			} else {
				obsInterface.setContents(settings.getTimeOut2FileName(), Integer.toString(getTimeOutCount()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	private void writeReset() {
		try {
			if (teamNbr==1) { 
				if(resetState) {
					obsInterface.setContents(settings.getReset1FileName(), "RESET");
				} else {
					obsInterface.setContents(settings.getReset1FileName(), "");
				}
			} else {
				if(resetState) {
					obsInterface.setContents(settings.getReset2FileName(), "RESET");
				} else {
					obsInterface.setContents(settings.getReset2FileName(), "");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void writeWarn() {
		try {
			if (teamNbr==1) { 
				if(warnState) {
					obsInterface.setContents(settings.getWarn1FileName(), "WARNING");
				} else {
					obsInterface.setContents(settings.getWarn1FileName(), "");
				}
			} else {
				if(warnState) {
					obsInterface.setContents(settings.getWarn2FileName(), "WARNING");
				} else {
					obsInterface.setContents(settings.getWarn2FileName(), "");
				}
				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
