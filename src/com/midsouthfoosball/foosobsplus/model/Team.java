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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class Team implements Serializable {

	private static final long serialVersionUID = 6807215509861474745L;
	private String teamName = "";
	private String forwardName = "";
	private String goalieName = "";
	private int score = 0;
	private int forwardScore = 0;
	private int goalieScore = 0;
	private int forwardGameCount = 0;
	private int goalieGameCount = 0;
	private int gameCount = 0;
	private int timeOutCount;
	private boolean resetState = false;
	private boolean warnState = false;
	private transient OBSInterface obsInterface;
	private transient Settings settings;
	private int teamNbr;
	private String teamColor;
	private int passAttempts = 0;
	private int passCompletes = 0;
	private int passBreaks = 0;
	private Float passPercent = 0f;
	private int shotAttempts = 0;
	private int shotCompletes = 0;
	private Float shotPercent = 0f;
	private int shotBreaks = 0;
	private int clearAttempts = 0;
	private int clearCompletes = 0;
	private Float clearPercent = 0f;
	private int twoBarPassAttempts = 0;
	private int twoBarPassCompletes = 0;
	private Float twoBarPassPercent = 0f;
	private int aces = 0;
	private int breaks = 0;
	private int stuffs = 0;
	private int scoring = 0;
	private int threeBarScoring = 0;
	private int fiveBarScoring = 0;
	private int twoBarScoring = 0;
	private int shotsOnGoal = 0;
	private DecimalFormat df = new DecimalFormat("###.#");
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public Team(OBSInterface obsInterface, Settings settings, Integer teamNbr, String teamColor) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.teamNbr = teamNbr;
		this.teamColor = teamColor;
	}
	public int getTeamNbr() {
		return teamNbr;
	}
	public void setTeamNbr(int teamNbr) {
		this.teamNbr = teamNbr;
	}
	public String getTeamColor() {
		return teamColor;
	}
	public void setTeamColor(String teamColor) {
		this.teamColor = teamColor;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		writeScore();
	}
	public void setScore(String score) {
		if(score=="") {
			setScore(0);
		} else {
			setScore(Integer.parseInt(score));
		}
	}
	public int getForwardScore() {
		return forwardScore;
	}
	public void setForwardScore(int forwardScore) {
		this.forwardScore = forwardScore;
	}
	public int getGoalieScore() {
		return goalieScore;
	}
	public void setGoalieScore(int goalieScore) {
		this.goalieScore = goalieScore;
	}
	public int getForwardGameCount() {
		return forwardGameCount;
	}
	public void setForwardGameCount(int forwardGameCount) {
		this.forwardGameCount = forwardGameCount;
	}
	public int getGoalieGameCount() {
		return goalieGameCount;
	}
	public void setGoalieGameCount(int goalieGameCount) {
		this.goalieGameCount = goalieGameCount;
	}
	public int incrementForwardScore() {
		forwardScore++;
		writeForwardScore();
		return forwardScore;
	}
	public int incrementGoalieScore() {
		goalieScore++;
		writeGoalieScore();
		return goalieScore;
	}
	public int incrementForwardGameCount() {
		forwardGameCount++;
		writeForwardGameCount();
		return forwardGameCount;
	}
	public int incrementGoalieGameCount() {
		goalieGameCount++;
		writeGoalieGameCount();
		return goalieGameCount;
	}
	public int incrementScore() {
		score++;
		writeScore();
		return score;
	}
	public int decrementForwardScore() {
		if(forwardScore > 0) forwardScore--;
		writeForwardScore();
		return forwardScore;
	}
	public int decrementGoalieScore() {
		if(goalieScore > 0) goalieScore--;
		writeGoalieScore();
		return goalieScore;
	}
	public int decrementForwardGameCount() {
		if(forwardGameCount > 0) forwardGameCount--;
		writeForwardGameCount();
		return forwardGameCount;
	}
	public int decrementGoalieGameCount() {
		if(goalieGameCount > 0) goalieGameCount--;
		writeGoalieGameCount();
		return goalieGameCount;
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
		if(gameCount=="") {
			setGameCount(0);
		} else {
			setGameCount(Integer.parseInt(gameCount));
		}
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
		if(timeOutCount=="") {
			setTimeOutCount(0);
		} else {
			setTimeOutCount(Integer.parseInt(timeOutCount));
		}
	}
	public int callTimeOut() {
		if(settings.getShowTimeOutsUsed() == 1) {
			timeOutCount++;
//			if(timeOutCount>settings.getMaxTimeOuts()) {
//				timeOutCount=settings.getMaxTimeOuts();
//			}
		} else {
			timeOutCount--;
//			if(timeOutCount<0) {
//				timeOutCount=0;
//			}
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
		writeReset();
	}
	public void setWarn(boolean state) {
		warnState = state;
		writeWarn();
	}
	public int getShotsOnGoal() {
		return shotsOnGoal;
	}
	public void setShotsOnGoal(int shotsOnGoal) {
		this.shotsOnGoal = shotsOnGoal;
		writeShotsOnGoal();
	}
	public void setShotsOnGoal(String shotsOnGoal) {
		if(shotsOnGoal=="") {
			setShotsOnGoal(0);
		} else {
			setShotsOnGoal(Integer.parseInt(shotsOnGoal));
		}
	}
	public void resetStats() {
		passAttempts = 0;
		passCompletes = 0;
		passBreaks = 0;
		passPercent = 0f;
		shotAttempts = 0;
		shotCompletes = 0;
		shotPercent = 0f;
		shotBreaks = 0;
		clearAttempts = 0;
		clearCompletes = 0;
		clearPercent = 0f;
		aces = 0;
		twoBarPassAttempts = 0;
		twoBarPassCompletes = 0;
		twoBarPassPercent = 0f;
		shotsOnGoal = 0;
		scoring = 0;
		threeBarScoring = 0;
		fiveBarScoring = 0;
		twoBarScoring = 0;
		breaks = 0;
		stuffs = 0;
		writeStats();
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
		forwardScore = 0;
		goalieScore = 0;
		forwardGameCount = 0;
		goalieGameCount = 0;
		gameCount = 0;
		passAttempts = 0;
		passCompletes = 0;
		passBreaks = 0;
		passPercent = 0f;
		shotAttempts = 0;
		shotCompletes = 0;
		shotPercent = 0f;
		shotBreaks = 0;
		clearAttempts = 0;
		clearCompletes = 0;
		clearPercent = 0f;
		aces = 0;
		twoBarPassAttempts = 0;
		twoBarPassCompletes = 0;
		twoBarPassPercent = 0f;
		shotsOnGoal = 0;
		scoring = 0;
		threeBarScoring = 0;
		fiveBarScoring = 0;
		twoBarScoring = 0;
		breaks = 0;
		stuffs = 0;
		resetTimeOuts();
		resetResetWarns();
		writeAll();
	}
	public void resetTimeOuts() {
		if(settings.getShowTimeOutsUsed() == 1) {
			timeOutCount = 0;
		} else {
			timeOutCount = settings.getMaxTimeOuts();
		}
		writeTimeOuts();
	}
	public void resetResetWarns() {
		setReset(false);
		setWarn(false);
		writeReset();
		writeWarn();
	}
	public int getPassAttempts() {
		return passAttempts;
	}
	public int getPassCompletes() {
		return passCompletes;
	}
	public Float getPassPercent() {
		return passPercent;
	}
	public int getPassBreaks() {
		return passBreaks;
	}
	public int getShotAttempts() {
		return shotAttempts;
	}
	public int getShotCompletes() {
		return shotCompletes;
	}
	public Float getShotPercent() {
		return shotPercent;
	}
	public int getShotBreaks() {
		return shotBreaks;
	}
	public int getClearAttempts() {
		return clearAttempts;
	}
	public int getClearCompletes() {
		return clearCompletes;
	}
	public Float getClearPercent() {
		return clearPercent;
	}
	public int getTwoBarPassAttempts() {
		return twoBarPassAttempts;
	}
	public int getTwoBarPassCompletes() {
		return twoBarPassCompletes;
	}
	public Float getTwoBarPassPercent() {
		return twoBarPassPercent;
	}
	public int getScoring() {
		return scoring;
	}
	public int getThreeBarScoring() {
		return threeBarScoring;
	}
	public int getFiveBarScoring() {
		return fiveBarScoring;
	}
	public int getTwoBarScoring() {
		return twoBarScoring;
	}
    public int getStuffs() {
		return stuffs;
	}
    public int getBreaks() {
    	return breaks;
    }
    public int getAces() {
    	return aces;
    }
	
	public void setPassAttempts(int passAttempts) {
		this.passAttempts = passAttempts;
		writePassAttempts();
	}
	public void setPassCompletes(int passCompletes) {
		this.passCompletes = passCompletes;
		writePassCompletes();
	}
	public void setPassPercent(Float passPercent) {
		this.passPercent = passPercent;
		writePassPercent();
	}
	public void setPassAttempts(String passAttempts) {
		if(passAttempts=="") {
			setPassAttempts(0);
		} else {
			setPassAttempts(Integer.parseInt(passAttempts));
		}
	}
	public void setPassCompletes(String passCompletes) {
		if(passCompletes=="") {
			setPassCompletes(0);
		} else {
			setPassCompletes(Integer.parseInt(passCompletes));
		}
	}
	public void setPassPercent(String passPercent) {
		if(passPercent=="") {
			setPassPercent(0f);
		} else {
			setPassPercent(Float.parseFloat(passPercent.replaceAll("[^\\d.]", ""))); // only get numbers - drop the % sign
		}
	}
	public void setPassBreaks(int passBreaks) {
		this.passBreaks = passBreaks;
		writePassBreaks();
	}
	public void setPassBreaks(String passBreaks) {
		if(passBreaks=="") {
			setPassBreaks(0);
		} else {
			setPassBreaks(Integer.parseInt(passBreaks));
		}
	}
	public void setShotAttempts(int shotAttempts) {
		this.shotAttempts = shotAttempts; 
		writeShotAttempts();
	}
	public void setShotCompletes(int shotCompletes) {
		this.shotCompletes = shotCompletes;
		writeShotCompletes();
	}
	public void setShotPercent(Float shotPercent) {
		this.shotPercent = shotPercent;
		writeShotPercent();
	}
	public void setShotBreaks(int shotBreaks) {
		this.shotBreaks = shotBreaks;
		writeShotBreaks();
	}
	public void setShotBreaks(String shotBreaks) {
		if(shotBreaks=="") {
			setShotBreaks(0);
		} else {
			setShotBreaks(Integer.parseInt(shotBreaks));
		}
	}
	public void setShotAttempts(String shotAttempts) {
		if(shotAttempts=="") {
			setShotAttempts(0);
		} else {
			setShotAttempts(Integer.parseInt(shotAttempts));
		}
	}
	public void setShotCompletes(String shotCompletes) {
		if(shotCompletes=="") {
			setShotCompletes(0);
		} else {
			setShotCompletes(Integer.parseInt(shotCompletes));
		}
	}
	public void setShotPercent(String shotPercent) {
		if(shotPercent=="") {
			setShotPercent(0f);
		} else {
			setShotPercent(Float.parseFloat(shotPercent.replaceAll("[^\\d.]", ""))); // only get numbers - drop the % sign
		}
	}
	public void setClearAttempts(int clearAttempts) {
		this.clearAttempts = clearAttempts;
		writeClearAttempts();
	}
	public void setClearCompletes(int clearCompletes) {
		this.clearCompletes = clearCompletes;
		writeClearCompletes();
	}
	public void setClearPercent(Float clearPercent) {
		this.clearPercent = clearPercent;
		writeClearPercent();
	}
	public void setClearAttempts(String clearAttempts) {
		if(clearAttempts=="") {
			setClearAttempts(0);
		} else {
			setClearAttempts(Integer.parseInt(clearAttempts));
		}
	}
	public void setClearCompletes(String clearCompletes) {
		if(clearCompletes=="") {
			setClearCompletes(0);
		} else {
			setClearCompletes(Integer.parseInt(clearCompletes));
		}
	}
	public void setClearPercent(String clearPercent) {
		if(clearPercent=="") {
			setClearPercent(0f);
		} else {
			setClearPercent(Float.parseFloat(clearPercent.replaceAll("[^\\d.]", ""))); // only get numbers - drop the % sign
		}
	}
	public void setTwoBarPassAttempts(int twoBarPassAttempts) {
		this.twoBarPassAttempts = twoBarPassAttempts;
		writeTwoBarPassAttempts();
	}
	public void setTwoBarPassCompletes(int twoBarPassCompletes) {
		this.twoBarPassCompletes = twoBarPassCompletes;
		writeTwoBarPassCompletes();
	}
	public void setTwoBarPassPercent(Float twoBarPassPercent) {
		this.twoBarPassPercent = twoBarPassPercent;
		writeTwoBarPassPercent();
	}
	public void setTwoBarPassAttempts(String twoBarPassAttempts) {
		if(twoBarPassAttempts=="") {
			setTwoBarPassAttempts(0);
		} else {
			setTwoBarPassAttempts(Integer.parseInt(twoBarPassAttempts));
		}
	}
	public void setTwoBarPassCompletes(String twoBarPassCompletes) {
		if(twoBarPassCompletes=="") {
			setTwoBarPassCompletes(0);
		} else {
			setTwoBarPassCompletes(Integer.parseInt(twoBarPassCompletes));
		}
	}
	public void setTwoBarPassPercent(String twoBarPassPercent) {
		if(twoBarPassPercent=="") {
			setTwoBarPassPercent(0f);
		} else {
			setTwoBarPassPercent(Float.parseFloat(twoBarPassPercent.replaceAll("[^\\d.]", ""))); // only get numbers - drop the % sign
		}
	}
	public void setScoring(int scores) {
		this.scoring = scores;
		writeScoring();
	}
	public void setThreeBarScoring(int scores) {
		this.threeBarScoring = scores;
		writeThreeBarScoring();
	}
	public void setFiveBarScoring(int scores) {
		this.fiveBarScoring = scores;
		writeFiveBarScoring();
	}
	public void setTwoBarScoring(int scores) {
		this.twoBarScoring = scores;
		writeTwoBarScoring();
	}
	public void setStuffs(int stuffs) {
		this.stuffs = stuffs;
		writeStuffs();
	}
	public void setScoring(String scoring) {
		if(scoring=="") {
			setScoring(0);
		} else {
			setScoring(Integer.parseInt(scoring));
		}
	}
	public void setThreeBarScoring(String scoring) {
		if(scoring=="") {
			setThreeBarScoring(0);
		} else {
			setThreeBarScoring(Integer.parseInt(scoring));
		}
	}
	public void setFiveBarScoring(String scoring) {
		if(scoring=="") {
			setFiveBarScoring(0);
		} else {
			setFiveBarScoring(Integer.parseInt(scoring));
		}
	}
	public void setTwoBarScoring(String scoring) {
		if(scoring=="") {
			setTwoBarScoring(0);
		} else {
			setTwoBarScoring(Integer.parseInt(scoring));
		}
	}
	public void setStuffs(String stuffs) {
		if(stuffs=="") {
			setStuffs(0);
		} else {
			setStuffs(Integer.parseInt(stuffs));
		}
	}
	public void setBreaks(int breaks) {
		this.breaks = breaks;
		writeBreaks();
	}
	public void setBreaks(String breaks) {
		if(breaks=="") {
			setBreaks(0);
		} else {
			setBreaks(Integer.parseInt(breaks));
		}
	}
	public void setAces(int aces) {
		this.aces = aces;
		writeAces();
	}
	public void setAces(String aces) {
		if(aces=="") {
			setAces(0);
		} else {
			setAces(Integer.parseInt(aces));
		}
	}

	////// Writes to Files \\\\\\
	
	private void writeTeamName() {
		writeData(settings.getTeamNameSource(Integer.toString(teamNbr)), getTeamName());
    }
    private void writeForwardName() {
    	String forwardName;
    	if(settings.getCutThroatMode()==1) {
    		forwardName = getForwardGameCount() + " " + getForwardName() + " " + getForwardScore();
    	} else {
    		forwardName = getForwardName();
    	}
    	writeData(settings.getTeamForwardSource(Integer.toString(teamNbr)), forwardName);
    }
    private void writeGoalieName() {
    	String goalieName;
    	if(settings.getCutThroatMode()==1) {
    		goalieName = getGoalieGameCount() + " " + getGoalieName() + " " + getGoalieScore();
    	} else {
    		goalieName = getGoalieName();
    	}
    	writeData(settings.getTeamGoalieSource(Integer.toString(teamNbr)), goalieName);
    }
    private void writeGameCount() {
    	writeData(settings.getTeamGameCountSource(Integer.toString(teamNbr)), Integer.toString(getGameCount()));
    }
    private void writeTimeOuts() {
   		writeData(settings.getTeamTimeOutSource(Integer.toString(teamNbr)), Integer.toString(getTimeOutCount()));
    }
	private void writeReset() {
		String src = settings.getTeamResetSource(Integer.toString(teamNbr));
		if(resetState) {
			writeData(src, "RESET");
		} else {
			writeData(src, "");
		}
	}
	private void writeWarn() {
		String src = settings.getTeamWarnSource(Integer.toString(teamNbr));
		if(warnState) {
			writeData(src, "WARNING");
		} else {
			writeData(src, "");
		}
	}
    private void writePassAttempts() {
		writeData(settings.getPassAttemptsSource(Integer.toString(teamNbr)), Integer.toString(getPassAttempts()));
    }
    private void writePassCompletes() {
		writeData(settings.getPassCompletesSource(Integer.toString(teamNbr)), Integer.toString(getPassCompletes()));
    }
    private void writePassPercent() {
		writeData(settings.getPassPercentSource(Integer.toString(teamNbr)), String.format("%-5s",df.format(getPassPercent())+"%"));
    }
    private void writePassBreaks() {
// 		writeData(settings.getPassBreaksSource(Integer.toString(teamNbr)), Integer.toString(getPassBreaks()));
    }
    private void writeShotBreaks() {
// 		writeData(settings.getShotBreaksSource(Integer.toString(teamNbr)), Integer.toString(getShotBreaks()));
    }
    private void writeShotAttempts() {
		writeData(settings.getShotAttemptsSource(Integer.toString(teamNbr)), Integer.toString(getShotAttempts()));
    }
    private void writeShotCompletes() {
		writeData(settings.getShotCompletesSource(Integer.toString(teamNbr)), Integer.toString(getShotCompletes()));
    }
    private void writeShotPercent() {
		writeData(settings.getShotPercentSource(Integer.toString(teamNbr)), String.format("%-5s",df.format(getShotPercent())+"%"));
    }
    private void writeClearAttempts() {
		writeData(settings.getClearAttemptsSource(Integer.toString(teamNbr)), Integer.toString(getClearAttempts()));
    }
    private void writeClearCompletes() {
		writeData(settings.getClearCompletesSource(Integer.toString(teamNbr)), Integer.toString(getClearCompletes()));
    }
    private void writeClearPercent() {
		writeData(settings.getClearPercentSource(Integer.toString(teamNbr)), String.format("%-5s",df.format(getClearPercent())+"%"));
    }
    private void writeTwoBarPassAttempts() {
		writeData(settings.getTwoBarPassAttemptsSource(Integer.toString(teamNbr)), Integer.toString(getTwoBarPassAttempts()));
    }
    private void writeTwoBarPassCompletes() {
		writeData(settings.getTwoBarPassCompletesSource(Integer.toString(teamNbr)), Integer.toString(getTwoBarPassCompletes()));
    }
    private void writeTwoBarPassPercent() {
		writeData(settings.getTwoBarPassPercentSource(Integer.toString(teamNbr)), String.format("%-5s",df.format(getTwoBarPassPercent())+"%"));
    }
	private void writeScoring() {
		writeData(settings.getScoringSource(Integer.toString(teamNbr)), Integer.toString(getScoring()));
	}
	private void writeThreeBarScoring() {
		writeData(settings.getThreeBarScoringSource(Integer.toString(teamNbr)), Integer.toString(getThreeBarScoring()));
	}
	private void writeFiveBarScoring() {
		writeData(settings.getFiveBarScoringSource(Integer.toString(teamNbr)), Integer.toString(getFiveBarScoring()));
	}
	private void writeTwoBarScoring() {
		writeData(settings.getTwoBarScoringSource(Integer.toString(teamNbr)), Integer.toString(getTwoBarScoring()));
	}
	   private void writeShotsOnGoal() {
		writeData(settings.getShotsOnGoalSource(Integer.toString(teamNbr)), Integer.toString(getShotsOnGoal()));
    }
	private void writeStuffs() {
		writeData(settings.getStuffsSource(Integer.toString(teamNbr)), Integer.toString(getStuffs()));
	}
	private void writeBreaks() {
		writeData(settings.getBreaksSource(Integer.toString(teamNbr)), Integer.toString(getBreaks()));
	}
	private void writeAces() {
		writeData(settings.getAcesSource(Integer.toString(teamNbr)), Integer.toString(getAces()));
	}
    private void writeScore() {
    	writeData(settings.getTeamScoreSource(Integer.toString(teamNbr)), Integer.toString(getScore()));
    }
    private void writeForwardScore() {
    	
    }
    private void writeGoalieScore() {
    	
    }
    private void writeForwardGameCount() {
    	
    }
    private void writeGoalieGameCount() {
    	
    }
	private void writeData(String source, String data) {
		obsInterface.writeData(source, data, "Team", settings.getShowParsed());
	}
    public void writeStats() {
   		writePassAttempts();
   		writePassCompletes();
   		writePassPercent();
   		writeShotAttempts();
   		writeShotCompletes();
   		writeShotPercent();
   		writeClearAttempts();
   		writeClearCompletes();
   		writeClearPercent();
   		writeTwoBarPassAttempts();
   		writeTwoBarPassCompletes();
   		writeTwoBarPassPercent();
   		writeScoring();
   		writeThreeBarScoring();
   		writeFiveBarScoring();
   		writeTwoBarScoring();
   		writeShotsOnGoal();
   		writeStuffs();
   		writeBreaks();
   		writeAces();
    }
	public void writeAll() {
		writeTeamName();
		writeForwardName();
		writeGoalieName();
		writeScore();
		writeGameCount();
		writeReset();
		writeWarn();
		writeTimeOuts();
		writeStats();
	}
	public void restoreState(byte[] serializedObject) {

		Team tempTeam = null;
		try {
			byte b[] = serializedObject;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			tempTeam = (Team) si.readObject();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		this.setTeamNbr(tempTeam.getTeamNbr());
		this.setTeamName(tempTeam.getTeamName());
		this.setForwardName(tempTeam.getForwardName());
		this.setGoalieName(tempTeam.getGoalieName());
		this.setScore(tempTeam.getScore());
		this.setGameCount(tempTeam.getGameCount());
		this.setTimeOutCount(tempTeam.getTimeOutCount());
		this.setReset(tempTeam.getReset());
		this.setWarn(tempTeam.getWarn());
		this.setTeamColor(tempTeam.getTeamColor());
		this.setPassAttempts(tempTeam.getPassAttempts());
		this.setPassCompletes(tempTeam.getPassCompletes());
		this.setPassBreaks(tempTeam.getPassBreaks());
		this.setPassPercent(tempTeam.getPassPercent());
		this.setShotAttempts(tempTeam.getShotAttempts());
		this.setShotCompletes(tempTeam.getShotCompletes());
		this.setShotPercent(tempTeam.getShotPercent());
		this.setShotBreaks(tempTeam.getShotBreaks());
		this.setClearAttempts(tempTeam.getClearAttempts());
		this.setClearCompletes(tempTeam.getClearCompletes());
		this.setClearPercent(tempTeam.getClearPercent());
		this.setAces(tempTeam.getAces());
		this.setTwoBarPassAttempts(tempTeam.getTwoBarPassAttempts());
		this.setTwoBarPassCompletes(tempTeam.getTwoBarPassCompletes());
		this.setTwoBarPassPercent(tempTeam.getTwoBarPassPercent());
		this.setShotsOnGoal(tempTeam.getShotsOnGoal());
		this.setScoring(tempTeam.getScoring());
		this.setThreeBarScoring(tempTeam.getThreeBarScoring());
		this.setFiveBarScoring(tempTeam.getFiveBarScoring());
		this.setTwoBarScoring(tempTeam.getTwoBarScoring());
		this.setBreaks(tempTeam.getBreaks());
		this.setStuffs(tempTeam.getStuffs());
	}
}
