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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.Timer;

import main.OBSInterface;

public class GameClock {
	private Timer timer;
	private int gameSeconds;
	private int gameMinutes;
	private int gameHours;
	private boolean gameTimerRunning;
	private int matchSeconds;
	private int matchMinutes;
	private int matchHours;
	private boolean matchTimerRunning;
	private OBSInterface obsInterface;
	private Settings settings;
	private DecimalFormat df = new DecimalFormat("00");
 
	
	public GameClock(OBSInterface obsInterface, Settings settings) {

		this.obsInterface = obsInterface;
		this.settings = settings;
		
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(gameTimerRunning) {
					gameSeconds++;
					if (gameSeconds>=60) {
						gameMinutes++;
						gameSeconds=0;
					}
					if (gameMinutes>=60) {
						gameHours++;
						gameMinutes=0;
					}
					writeGameTime();
				}
				if(matchTimerRunning) {
					matchSeconds++;
					if (matchSeconds>=60) {
						matchMinutes++;
						matchSeconds=0;
					}
					if (matchMinutes>=60) {
						matchHours++;
						matchMinutes=0;
					}
					writeMatchTime();
				}
			}
		};
		timer = new Timer(1000, action);
		timer.setInitialDelay(1000);
		timer.start();
	}
	public void restartGameTimer() {
		timer.restart();
	}
	public String getGameTime() {
		return df.format(gameHours) + ":" + df.format(gameMinutes) + ":" + df.format(gameSeconds);
	}
	public String getMatchTime() {
		return df.format(matchHours) + ":" + df.format(matchMinutes) + ":" + df.format(matchSeconds);
	}
	public void startGameTimer() {
		gameSeconds=0;
		gameMinutes=0;
		gameHours=0;
		gameTimerRunning=true;
		writeGameTime();
	}
	public void stopGameTimer() {
		gameTimerRunning=false;
	}
	public void startMatchTimer() {
		matchSeconds=0;
		matchMinutes=0;
		matchHours=0;
		matchTimerRunning=true;
		writeMatchTime();
	}
	public void stopMatchTimer() {
		matchTimerRunning=false;
	}
	public void addGameClockTimerListener(ActionListener alAction) {
		timer.addActionListener(alAction);
	}
	private void writeGameTime() {
		System.out.println("Game Timer: " + getGameTime());
		try {
			obsInterface.setContents(settings.getGameTimeFileName(), getGameTime());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void writeMatchTime() {
		System.out.println("Match Timer: " + getMatchTime());
		try {
			obsInterface.setContents(settings.getMatchTimeFileName(), getMatchTime());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
