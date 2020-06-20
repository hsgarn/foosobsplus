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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class Team implements Serializable {

	private static final long serialVersionUID = 6807215509861474745L;
	private String teamName = "";
	private String forwardName = "";
	private String goalieName = "";
	private int score = 0;
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
	private int aces = 0;
	private int twoBarPassAttempts = 0;
	private int twoBarPassCompletes = 0;
	private Float twoBarPassPercent = 0f;
	private int shotsOnGoal = 0;
	private int scoring = 0;
	private int threeBarScoring = 0;
	private int fiveBarScoring = 0;
	private int twoBarScoring = 0;
	private int breaks = 0;
	private int stuffs = 0;
	private DecimalFormat df = new DecimalFormat("###.#");
	
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
		writeReset();
	}
	public void setWarn(boolean state) {
		warnState = state;
		writeWarn();
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

	////// Writes to Files \\\\\\
	
	private void writeTeamName() {
		try {
			obsInterface.setContents(settings.getTeamNameFileName(teamNbr), getTeamName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeForwardName() {
		try {
			obsInterface.setContents(settings.getTeamForwardFileName(teamNbr), getForwardName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeGoalieName() {
		try {
			obsInterface.setContents(settings.getTeamGoalieFileName(teamNbr), getGoalieName());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeScore() {
		try {
			obsInterface.setContents(settings.getScoreFileName(teamNbr), Integer.toString(getScore()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeGameCount() {
    	try {
			obsInterface.setContents(settings.getGameCountFileName(teamNbr), Integer.toString(getGameCount()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTimeOuts() {
    	try {
			obsInterface.setContents(settings.getTimeOutFileName(teamNbr), Integer.toString(getTimeOutCount()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	private void writeReset() {
		try {
			String fn = settings.getResetFileName(teamNbr);
			if(resetState) {
				obsInterface.setContents(fn, "RESET");
			} else {
				obsInterface.setContents(fn, "");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void writeWarn() {
		try {
			String fn = settings.getWarnFileName(teamNbr);
			if(warnState) {
				obsInterface.setContents(fn, "WARNING");
			} else {
				obsInterface.setContents(fn, "");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
    private void writePassAttempts() {
		try {
			obsInterface.setContents(settings.getPassAttemptsFileName(teamNbr), Integer.toString(getPassAttempts()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writePassCompletes() {
		try {
			obsInterface.setContents(settings.getPassCompletesFileName(teamNbr), Integer.toString(getPassCompletes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writePassPercent() {
		try {
			obsInterface.setContents(settings.getPassPercentFileName(teamNbr), String.format("%-5s",df.format(getPassPercent())+"%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writePassBreaks() {
//    	try {
//   		obsInterface.setContents(settings.getPassBreaksFileName(teamNbr), Integer.toString(getPassBreaks()));
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
    }
    private void writeShotAttempts() {
		try {
			obsInterface.setContents(settings.getShotAttemptsFileName(teamNbr), Integer.toString(getShotAttempts()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeShotCompletes() {
		try {
			obsInterface.setContents(settings.getShotCompletesFileName(teamNbr), Integer.toString(getShotCompletes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeShotPercent() {
		try {
			obsInterface.setContents(settings.getShotPercentFileName(teamNbr), String.format("%-5s",df.format(getShotPercent())+"%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeClearAttempts() {
		try {
			obsInterface.setContents(settings.getClearAttemptsFileName(teamNbr), Integer.toString(getClearAttempts()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeClearCompletes() {
		try {
			obsInterface.setContents(settings.getClearCompletesFileName(teamNbr), Integer.toString(getClearCompletes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeClearPercent() {
		try {
			obsInterface.setContents(settings.getClearPercentFileName(teamNbr), String.format("%-5s",df.format(getClearPercent())+"%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTwoBarPassAttempts() {
		try {
			obsInterface.setContents(settings.getTwoBarPassAttemptsFileName(teamNbr), Integer.toString(getTwoBarPassAttempts()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTwoBarPassCompletes() {
		try {
			obsInterface.setContents(settings.getTwoBarPassCompletesFileName(teamNbr), Integer.toString(getTwoBarPassCompletes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void writeTwoBarPassPercent() {
		try {
			obsInterface.setContents(settings.getTwoBarPassPercentFileName(teamNbr), String.format("%-5s",df.format(getTwoBarPassPercent())+"%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	private void writeScoring() {
		try {
			obsInterface.setContents(settings.getScoringFileName(teamNbr), Integer.toString(getScoring()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeThreeBarScoring() {
		try {
			obsInterface.setContents(settings.getThreeBarScoringFileName(teamNbr), Integer.toString(getThreeBarScoring()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeFiveBarScoring() {
		try {
			obsInterface.setContents(settings.getFiveBarScoringFileName(teamNbr), Integer.toString(getFiveBarScoring()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeTwoBarScoring() {
		try {
			obsInterface.setContents(settings.getTwoBarScoringFileName(teamNbr), Integer.toString(getTwoBarScoring()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeStuffs() {
		try {
			obsInterface.setContents(settings.getStuffsFileName(teamNbr), Integer.toString(getStuffs()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeBreaks() {
		try {
			obsInterface.setContents(settings.getBreaksFileName(teamNbr), Integer.toString(getBreaks()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
   		writeStuffs();
   		writeBreaks();
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
			System.out.println(e);
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
//		this.setShotBreaks(tempTeam.getShotBreaks());
		this.setClearAttempts(tempTeam.getClearAttempts());
		this.setClearCompletes(tempTeam.getClearCompletes());
		this.setClearPercent(tempTeam.getClearPercent());
//		this.setAces(tempTeam.getAces());
		this.setTwoBarPassAttempts(tempTeam.getTwoBarPassAttempts());
		this.setTwoBarPassCompletes(tempTeam.getTwoBarPassCompletes());
		this.setTwoBarPassPercent(tempTeam.getTwoBarPassPercent());
//		this.setShotsOnGoal(tempTeam.getShotsOnGoal());
		this.setScoring(tempTeam.getScoring());
		this.setThreeBarScoring(tempTeam.getThreeBarScoring());
		this.setFiveBarScoring(tempTeam.getFiveBarScoring());
		this.setTwoBarScoring(tempTeam.getTwoBarScoring());
		this.setBreaks(tempTeam.getBreaks());
		this.setStuffs(tempTeam.getStuffs());
	}
}
