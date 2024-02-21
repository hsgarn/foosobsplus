/**
Copyright © 2020-2024 Hugh Garner
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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.view.Messages;

public class Team implements Serializable {

	private static final long serialVersionUID = 6807215509861474745L;
	protected PropertyChangeSupport propertyChangeSupport;
	private String teamName = "";
	private String forwardName = "";
	private String goalieName = "";
	private int score = 0;
	private int gameCount = 0;
	private int matchCount = 0;
	private int timeOutCount;
	private boolean resetState = false;
	private boolean warnState = false;
	private boolean kingSeatState = false;
	private transient OBSInterface obsInterface;
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
	private static final String ON = "1";
	private DecimalFormat df = new DecimalFormat("###.#");
	private static Logger logger = LoggerFactory.getLogger(Team.class);
	
	public Team(OBSInterface obsInterface, Integer teamNbr, String teamColor) {
		this.obsInterface = obsInterface;
		this.teamNbr = teamNbr;
		this.teamColor = teamColor;
		propertyChangeSupport = new PropertyChangeSupport(this);
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
		int oldScore = this.score;
		this.score = score;
		propertyChangeSupport.firePropertyChange("Team"+teamNbr+"Score",oldScore,score);
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
		setScore(getScore()+1);
		return getScore();
	}
	public int decrementScore() {
		if(score > 0) {
			setScore(getScore()-1);
		}
		return getScore();
	}
	public int getGameCount() {
		return gameCount;
	}
	public int getMatchCount() {
		return matchCount;
	}
	public void setGameCount(int gameCount) {
		int oldGameCount = this.gameCount;
		this.gameCount = gameCount;
		propertyChangeSupport.firePropertyChange("Team"+teamNbr+"Game",oldGameCount,gameCount);
		writeGameCount();
	}
	public void setGameCount(String gameCount) {
		if(gameCount=="") {
			setGameCount(0);
		} else {
			setGameCount(Integer.parseInt(gameCount));
		}
	}
	public void setMatchCount(int matchCount) {
		int oldMatchCount = this.matchCount;
		this.matchCount = matchCount;
		propertyChangeSupport.firePropertyChange("Team"+teamNbr+"Match",oldMatchCount,matchCount);
		writeMatchCount();
	}
	public void setMatchCount(String matchCount) {
		if(matchCount=="") {
			setMatchCount(0);
		} else {
			setMatchCount(Integer.parseInt(matchCount));
		}
	}
	public int incrementGameCount() {
		setGameCount(getGameCount()+1);
		return getGameCount();
	}
	public int decrementGameCount() {
		if(gameCount > 0) {
			setGameCount(getGameCount()-1);
		}
		return getGameCount();
	}	
	public int incrementMatchCount() {
		setMatchCount(getMatchCount()+1);
		return getMatchCount();
	}
	public int decrementMatchCount() {
		if(matchCount > 0) {
			setMatchCount(getMatchCount()-1);
		}
		return getMatchCount();
	}	
	public int getTimeOutCount() {
		return timeOutCount;
	}
	public void setTimeOutCount(int timeOutCount) {
		int oldTimeOutCount = this.timeOutCount;
		this.timeOutCount = timeOutCount;
		propertyChangeSupport.firePropertyChange("Team"+teamNbr+"TimeOut",oldTimeOutCount,timeOutCount);
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
		if(Settings.getControlParameter("ShowTimeOutsUsed").equals(ON)) {
			setTimeOutCount(getTimeOutCount()+1);
		} else {
			setTimeOutCount(getTimeOutCount()-1);
		}
		return getTimeOutCount();
	}
	public int restoreTimeOut() {
		if(Settings.getControlParameter("ShowTimeOutsUsed").equals(ON)) {
			setTimeOutCount(getTimeOutCount()-1);
			if(getTimeOutCount()<0) {
				setTimeOutCount(0);
			}
		} else {
			setTimeOutCount(getTimeOutCount()+1);
			int maxTimeOuts = Integer.parseInt(Settings.getControlParameter("MaxTimeOuts"));
			if(getTimeOutCount()>maxTimeOuts) {
				setTimeOutCount(maxTimeOuts);
			}
		}
		return getTimeOutCount();
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
	public boolean getKingSeat() {
		return kingSeatState;
	}
	public void setKingSeat(boolean state) {
		kingSeatState = state;
		writeKingSeat();
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
		matchCount = 0;
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
		resetKingSeats();
		writeAll();
	}
	public void resetTimeOuts() {
		if(Settings.getControlParameter("ShowTimeOutsUsed").equals(ON)) {
			setTimeOutCount(0);
		} else {
			setTimeOutCount(Integer.parseInt(Settings.getControlParameter("MaxTimeOuts")));
		}
	}
	public void resetResetWarns() {
		setReset(false);
		setWarn(false);
		writeReset();
		writeWarn();
	}
	public void resetKingSeats() {
		setKingSeat(false);
		writeKingSeat();
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
		writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr), "Name"), getTeamName());
    }
    private void writeForwardName() {
    	String forwardName;
   		forwardName = getForwardName();
    	writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr), "Forward"), forwardName);
    }
    private void writeGoalieName() {
    	String goalieName;
   		goalieName = getGoalieName();
    	writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr), "Goalie"), goalieName);
    }
    private void writeGameCount() {
    	writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr),"GameCount"), Integer.toString(getGameCount()));
    }
    private void writeMatchCount() {
    	writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr),"MatchCount"), Integer.toString(getMatchCount()));
    }
    private void writeTimeOuts() {
   		writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr),"TimeOut"), Integer.toString(getTimeOutCount()));
    }
	private void writeReset() {
		String src = Settings.getTeamSourceParameter(Integer.toString(teamNbr),"Reset");
		if(resetState) {
			writeData(src, "RESET");
		} else {
			writeData(src, "");
		}
	}
	private void writeWarn() {
		String src = Settings.getTeamSourceParameter(Integer.toString(teamNbr),"Warn");
		if(warnState) {
			writeData(src, "WARNING");
		} else {
			writeData(src, "");
		}
	}
	private void writeKingSeat() {
		String src = Settings.getTeamSourceParameter(Integer.toString(teamNbr),"KingSeat");
		if(kingSeatState) {
			writeData(src, Messages.getString("Global.KingSeat")); //$NON-NLS-1$
		} else {
			writeData(src, "");
		}
	}
    private void writePassBreaks() {
// 		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"PassBreaks"), Integer.toString(getPassBreaks()));
    }
    private void writeShotBreaks() {
// 		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ShotBreaks"), Integer.toString(getShotBreaks()));
    }
    private void writePassAttempts() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"PassAttempts"), Integer.toString(getPassAttempts()));
    }
    private void writePassCompletes() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"PassCompletes"), Integer.toString(getPassCompletes()));
    }
    private void writePassPercent() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"PassPercent"), String.format("%-5s",df.format(getPassPercent())+"%"));
    }
    private void writeShotAttempts() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ShotAttempts"), Integer.toString(getShotAttempts()));
    }
    private void writeShotCompletes() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ShotCompletes"), Integer.toString(getShotCompletes()));
    }
    private void writeShotPercent() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ShotPercent"), String.format("%-5s",df.format(getShotPercent())+"%"));
    }
    private void writeClearAttempts() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ClearAttempts"), Integer.toString(getClearAttempts()));
    }
    private void writeClearCompletes() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ClearCompletes"), Integer.toString(getClearCompletes()));
    }
    private void writeClearPercent() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ClearPercent"), String.format("%-5s",df.format(getClearPercent())+"%"));
    }
    private void writeTwoBarPassAttempts() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"TwoBarPassAttempts"), Integer.toString(getTwoBarPassAttempts()));
    }
    private void writeTwoBarPassCompletes() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"TwoBarPassCompletes"), Integer.toString(getTwoBarPassCompletes()));
    }
    private void writeTwoBarPassPercent() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"TwoBarPassPercent"), String.format("%-5s",df.format(getTwoBarPassPercent())+"%"));
    }
	private void writeScoring() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"Scoring"), Integer.toString(getScoring()));
	}
	private void writeThreeBarScoring() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ThreeBarScoring"), Integer.toString(getThreeBarScoring()));
	}
	private void writeFiveBarScoring() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"FiveBarScoring"), Integer.toString(getFiveBarScoring()));
	}
	private void writeTwoBarScoring() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"TwoBarScoring"), Integer.toString(getTwoBarScoring()));
	}
	   private void writeShotsOnGoal() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"ShotsOnGoal"), Integer.toString(getShotsOnGoal()));
    }
	private void writeStuffs() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"Stuffs"), Integer.toString(getStuffs()));
	}
	private void writeBreaks() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"Breaks"), Integer.toString(getBreaks()));
	}
	private void writeAces() {
		writeData(Settings.getTeamStatsSourceParameter(Integer.toString(teamNbr),"Aces"), Integer.toString(getAces()));
	}
    private void writeScore() {
    	writeData(Settings.getTeamSourceParameter(Integer.toString(teamNbr),"Score"), Integer.toString(getScore()));
    }
	private void writeData(String source, String data) {
		obsInterface.writeData(source, data, "Team", Settings.getShowParsed());
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
		writeMatchCount();
		writeReset();
		writeWarn();
		writeKingSeat();
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
		this.setMatchCount(tempTeam.getMatchCount());
		this.setTimeOutCount(tempTeam.getTimeOutCount());
		this.setReset(tempTeam.getReset());
		this.setWarn(tempTeam.getWarn());
		this.setKingSeat(tempTeam.getKingSeat());
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
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
}
