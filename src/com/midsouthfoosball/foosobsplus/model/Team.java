/**
Copyright 2020, 2021, 2022 Hugh Garner
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

import net.twasi.obsremotejava.OBSRemoteController;

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
		writeData(settings.getTeamNameFileName(teamNbr), settings.getTeamNameSource(teamNbr), getTeamName());
    }
    private void writeForwardName() {
    	writeData(settings.getTeamForwardFileName(teamNbr), settings.getTeamForwardSource(teamNbr), getForwardName());
    }
    private void writeGoalieName() {
    	writeData(settings.getTeamGoalieFileName(teamNbr), settings.getTeamGoalieSource(teamNbr), getGoalieName());
    }
    private void writeGameCount() {
    	writeData(settings.getGameCountFileName(teamNbr), settings.getGameCountSource(teamNbr), Integer.toString(getGameCount()));
    }
    private void writeTimeOuts() {
   		writeData(settings.getTimeOutFileName(teamNbr), settings.getTimeOutSource(teamNbr), Integer.toString(getTimeOutCount()));
    }
	private void writeReset() {
		String fn = settings.getResetFileName(teamNbr);
		String src = settings.getResetSource(teamNbr);
		if(resetState) {
			writeData(fn, src, "RESET");
		} else {
			writeData(fn, src, "");
		}
	}
	private void writeWarn() {
		String fn = settings.getWarnFileName(teamNbr);
		String src = settings.getWarnSource(teamNbr);
		if(warnState) {
			writeData(fn, src, "WARNING");
		} else {
			writeData(fn, src, "");
		}
	}
    private void writePassAttempts() {
		writeData(settings.getPassAttemptsFileName(teamNbr), settings.getPassAttemptsSource(teamNbr), Integer.toString(getPassAttempts()));
    }
    private void writePassCompletes() {
		writeData(settings.getPassCompletesFileName(teamNbr), settings.getPassCompletesSource(teamNbr), Integer.toString(getPassCompletes()));
    }
    private void writePassPercent() {
		writeData(settings.getPassPercentFileName(teamNbr), settings.getPassPercentSource(teamNbr), String.format("%-5s",df.format(getPassPercent())+"%"));
    }
    private void writePassBreaks() {
// 		writeData(settings.getPassBreaksFileName(teamNbr), settings.getPassBreaksSource(teamNbr), Integer.toString(getPassBreaks()));
    }
    private void writeShotAttempts() {
		writeData(settings.getShotAttemptsFileName(teamNbr), settings.getShotAttemptsSource(teamNbr), Integer.toString(getShotAttempts()));
    }
    private void writeShotCompletes() {
		writeData(settings.getShotCompletesFileName(teamNbr), settings.getShotCompletesSource(teamNbr), Integer.toString(getShotCompletes()));
    }
    private void writeShotPercent() {
		writeData(settings.getShotPercentFileName(teamNbr), settings.getShotPercentSource(teamNbr), String.format("%-5s",df.format(getShotPercent())+"%"));
    }
    private void writeClearAttempts() {
		writeData(settings.getClearAttemptsFileName(teamNbr), settings.getClearAttemptsSource(teamNbr), Integer.toString(getClearAttempts()));
    }
    private void writeClearCompletes() {
		writeData(settings.getClearCompletesFileName(teamNbr), settings.getClearCompletesSource(teamNbr), Integer.toString(getClearCompletes()));
    }
    private void writeClearPercent() {
		writeData(settings.getClearPercentFileName(teamNbr), settings.getClearPercentSource(teamNbr), String.format("%-5s",df.format(getClearPercent())+"%"));
    }
    private void writeTwoBarPassAttempts() {
		writeData(settings.getTwoBarPassAttemptsFileName(teamNbr), settings.getTwoBarPassAttemptsSource(teamNbr), Integer.toString(getTwoBarPassAttempts()));
    }
    private void writeTwoBarPassCompletes() {
		writeData(settings.getTwoBarPassCompletesFileName(teamNbr), settings.getTwoBarPassCompletesSource(teamNbr), Integer.toString(getTwoBarPassCompletes()));
    }
    private void writeTwoBarPassPercent() {
		writeData(settings.getTwoBarPassPercentFileName(teamNbr), settings.getTwoBarPassPercentSource(teamNbr), String.format("%-5s",df.format(getTwoBarPassPercent())+"%"));
    }
	private void writeScoring() {
		writeData(settings.getScoringFileName(teamNbr), settings.getScoringSource(teamNbr), Integer.toString(getScoring()));
	}
	private void writeThreeBarScoring() {
		writeData(settings.getThreeBarScoringFileName(teamNbr), settings.getThreeBarScoringSource(teamNbr), Integer.toString(getThreeBarScoring()));
	}
	private void writeFiveBarScoring() {
		writeData(settings.getFiveBarScoringFileName(teamNbr), settings.getFiveBarScoringSource(teamNbr), Integer.toString(getFiveBarScoring()));
	}
	private void writeTwoBarScoring() {
		writeData(settings.getTwoBarScoringFileName(teamNbr), settings.getTwoBarScoringSource(teamNbr), Integer.toString(getTwoBarScoring()));
	}
	   private void writeShotsOnGoal() {
		writeData(settings.getShotsOnGoalFileName(teamNbr), settings.getShotsOnGoalSource(teamNbr), Integer.toString(getShotsOnGoal()));
    }
	private void writeStuffs() {
		writeData(settings.getStuffsFileName(teamNbr), settings.getStuffsSource(teamNbr), Integer.toString(getStuffs()));
	}
	private void writeBreaks() {
		writeData(settings.getBreaksFileName(teamNbr), settings.getBreaksSource(teamNbr), Integer.toString(getBreaks()));
	}
	private void writeAces() {
		writeData(settings.getAcesFileName(teamNbr), settings.getAcesSource(teamNbr), Integer.toString(getAces()));
	}
    private void writeScore() {
    	writeData(settings.getScoreFileName(teamNbr), settings.getScoreSource(teamNbr), Integer.toString(getScore()));
    }
	private void writeData(String filename, String source, String data) {
    	OBS obs = OBS.getInstance();
    	OBSRemoteController obsController = obs.getController();
    	if (obsController == null || !obs.getConnected()) {
		   	try {
		    		obsInterface.setContents(filename, data);
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		} else {
	   		obsController.setTextGDIPlusProperties(source, data, false, response -> {
	   			if(settings.getShowParsed())
	   				System.out.println("Team class: Source: [" + source + "] Text: [" + data + "] " + response + "]");
	   			});
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
