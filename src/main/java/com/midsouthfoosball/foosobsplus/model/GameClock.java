/**
Copyright © 2020-2026 Hugh Garner
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import java.io.IOException;
/**
 * Game Clock - Clock for Game, Match and Stream times.
 * @author Hugh Garner
 *
 */
public class GameClock implements Serializable {
	private static final long serialVersionUID = -4482700813986069170L;
	private transient Timer timer;
	private transient long gameStartTime;
	private transient long matchStartTime;
	private transient long streamStartTime;
	private transient long currentTime;
	private transient long pauseStartTime;
	private int gameSeconds;
	private int gameMinutes;
	private int gameHours;
	private boolean gameTimerRunning;
	private int matchSeconds;
	private int matchMinutes;
	private int matchHours;
	private boolean matchTimerRunning;
	private int streamSeconds;
	private int streamMinutes;
	private int streamHours;
	private String lastGameTime;
	private boolean streamTimerRunning;
	private final transient OBSInterface obsInterface;
	private final transient DecimalFormat df = new DecimalFormat("00");
	private final static transient Logger logger = LoggerFactory.getLogger(GameClock.class);
	public GameClock(OBSInterface obsInterface) {
		this.obsInterface = obsInterface;
		ActionListener action = (ActionEvent event) -> {
                    currentTime = System.currentTimeMillis();
                    if(gameTimerRunning) {
                        long checkTimeDiff = (currentTime - gameStartTime);
                        int checkTimeDiffSeconds = (int) (checkTimeDiff / 1000);
                        gameHours = checkTimeDiffSeconds / 3600;
                        gameMinutes = checkTimeDiffSeconds % 3600 / 60;
                        gameSeconds = checkTimeDiffSeconds % 3600 % 60;
                        writeGameTime();
                    }
                    if(matchTimerRunning) {
                        long checkTimeDiff = (currentTime - matchStartTime);
                        int checkTimeDiffSeconds = (int) (checkTimeDiff / 1000);
                        matchHours = checkTimeDiffSeconds / 3600;
                        matchMinutes = checkTimeDiffSeconds % 3600 / 60;
                        matchSeconds = checkTimeDiffSeconds % 3600 % 60;
                        writeMatchTime();
                    }
                    if(streamTimerRunning) {
                        long checkTimeDiff = (currentTime - streamStartTime);
                        int checkTimeDiffSeconds = (int) (checkTimeDiff / 1000);
                        streamHours = checkTimeDiffSeconds / 3600;
                        streamMinutes = checkTimeDiffSeconds % 3600 / 60;
                        streamSeconds = checkTimeDiffSeconds % 3600 % 60;
                        writeStreamTime();
                    }
                };
		timer = new Timer(1000, action);
		timer.setInitialDelay(1000);
		timer.start();
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public int getGameSeconds() {
		return gameSeconds;
	}
	public void setGameSeconds(int gameSeconds) {
		this.gameSeconds = gameSeconds;
	}
	public int getGameMinutes() {
		return gameMinutes;
	}
	public void setGameMinutes(int gameMinutes) {
		this.gameMinutes = gameMinutes;
	}
	public int getGameHours() {
		return gameHours;
	}
	public void setGameHours(int gameHours) {
		this.gameHours = gameHours;
	}
	public boolean isGameTimerRunning() {
		return gameTimerRunning;
	}
	public void setGameTimerRunning(boolean gameTimerRunning) {
		this.gameTimerRunning = gameTimerRunning;
	}
	public int getMatchSeconds() {
		return matchSeconds;
	}
	public void setMatchSeconds(int matchSeconds) {
		this.matchSeconds = matchSeconds;
	}
	public int getMatchMinutes() {
		return matchMinutes;
	}
	public void setMatchMinutes(int matchMinutes) {
		this.matchMinutes = matchMinutes;
	}
	public int getMatchHours() {
		return matchHours;
	}
	public void setMatchHours(int matchHours) {
		this.matchHours = matchHours;
	}
	public boolean isMatchTimerRunning() {
		return matchTimerRunning;
	}
	public void setMatchTimerRunning(boolean matchTimerRunning) {
		this.matchTimerRunning = matchTimerRunning;
	}
	public int getStreamSeconds() {
		return streamSeconds;
	}
	public void setStreamSeconds(int streamSeconds) {
		this.streamSeconds = streamSeconds;
	}
	public int getStreamMinutes() {
		return streamMinutes;
	}
	public void setStreamMinutes(int streamMinutes) {
		this.streamMinutes = streamMinutes;
	}
	public int getStreamHours() {
		return streamHours;
	}
	public void setStreamHours(int streamHours) {
		this.streamHours = streamHours;
	}
	public boolean isStreamTimerRunning() {
		return streamTimerRunning;
	}
	public void setStreamTimerRunning(boolean streamTimerRunning) {
		this.streamTimerRunning = streamTimerRunning;
	}
	public void restartGameTimer() {
		lastGameTime = getGameTime();
		timer.restart();
	}
	public String getGameTime() {
		return df.format(gameHours) + ":" + df.format(gameMinutes) + ":" + df.format(gameSeconds);
	}
	public String getLastGameTime() {
		return lastGameTime;
	}
	public String getMatchTime() {
		return df.format(matchHours) + ":" + df.format(matchMinutes) + ":" + df.format(matchSeconds);
	}
	public String getStreamTime() {
		return df.format(streamHours) + ":" + df.format(streamMinutes) + ":" + df.format(streamSeconds);
	}
	public void startGameTimer() {
		lastGameTime = getGameTime();
		gameStartTime = System.currentTimeMillis();
		pauseStartTime = System.currentTimeMillis();
		gameSeconds=0;
		gameMinutes=0;
		gameHours=0;
		gameTimerRunning=true;
		writeGameTime();
	}
	public void stopGameTimer() {
		gameTimerRunning=false;
	}
	public void pauseGameTimer(boolean pause) {
		if (pause) {
			gameTimerRunning=false;
			pauseStartTime=System.currentTimeMillis();
		} else {
			long diff = System.currentTimeMillis() - pauseStartTime;
			gameStartTime = gameStartTime + diff;
			matchStartTime = matchStartTime + diff;
			gameTimerRunning=true;
		}
	}
	public void startMatchTimer() {
		matchStartTime = System.currentTimeMillis();
		matchSeconds=0;
		matchMinutes=0;
		matchHours=0;
		matchTimerRunning=true;
		writeMatchTime();
	}
	public void stopMatchTimer() {
		matchTimerRunning=false;
	}
	public void pauseMatchTimer(boolean pause) {
            matchTimerRunning = !pause;
	}
	public void startStreamTimer() {
		streamStartTime = System.currentTimeMillis();
		streamSeconds=0;
		streamMinutes=0;
		streamHours=0;
		streamTimerRunning=true;
	}
	public void stopStreamTimer() {
		streamTimerRunning=false;
	}
	public void addGameClockTimerListener(ActionListener alAction) {
		timer.addActionListener(alAction);
	}
	private void writeGameTime() {
		writeData(Settings.getSourceParameter("GameTime"), getGameTime());
	}
	private void writeMatchTime() {
		writeData(Settings.getSourceParameter("MatchTime"), getMatchTime());
	}
	private void writeStreamTime() {
		writeData(Settings.getSourceParameter("StreamTime"), getStreamTime());
	}
    private void writeData(String source, String data) {
		obsInterface.writeData(source, data, "GameClock", Settings.getShowParsed());
    }
    public void restoreState(byte[] serializedObject) {
		GameClock tempGameClock = null;
		try {
			byte b[] = serializedObject;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			tempGameClock = (GameClock) si.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.toString());
		}
		this.setGameSeconds(tempGameClock.getGameSeconds());
		this.setGameMinutes(tempGameClock.getGameMinutes());
		this.setGameHours(tempGameClock.getGameHours());
		this.setGameTimerRunning(tempGameClock.isGameTimerRunning());
		this.setMatchSeconds(tempGameClock.getMatchSeconds());
		this.setMatchMinutes(tempGameClock.getMatchMinutes());
		this.setMatchHours(tempGameClock.getMatchHours());
		this.setMatchTimerRunning(tempGameClock.isMatchTimerRunning());
	}
}
