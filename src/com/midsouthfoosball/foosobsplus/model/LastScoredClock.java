/**
Copyright 2021-2024 Hugh Garner
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
import java.text.DecimalFormat;

import javax.swing.Timer;


public class LastScoredClock {

	private Timer timer;
	private int lastScoredSeconds;
	private int lastScoredMinutes;
	private int lastScoredHours;
	private boolean  lastScoredTimerRunning;
	private DecimalFormat df = new DecimalFormat("00");
	
	public LastScoredClock() {
		
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(lastScoredTimerRunning) {
					lastScoredSeconds++;
					if (lastScoredSeconds>=60) {
						lastScoredMinutes++;
						lastScoredSeconds=0;
					}
					if (lastScoredMinutes>=60) {
						lastScoredHours++;
						lastScoredMinutes=0;
					}
				}
			}
		};

		timer = new Timer(1000, action);
		timer.setInitialDelay(1000);
		timer.start();
	}
	public int getLastScoredSeconds() {
		return lastScoredSeconds;
	}
	public void setLastScoredSeconds(int lastScoredSeconds) {
		this.lastScoredSeconds = lastScoredSeconds;
	}
	public int getLastScoredMinutes() {
		return lastScoredMinutes;
	}
	public void setLastScoredMinutes(int lastScoredMinutes) {
		this.lastScoredMinutes = lastScoredMinutes;
	}
	public int getLastScoredHours() {
		return lastScoredHours;
	}
	public void setLastScoredHours(int lastScoredHours) {
		this.lastScoredHours = lastScoredHours;
	}
	public boolean isLastScoredTimerRunning() {
		return lastScoredTimerRunning;
	}
	public void setLastScoredTimerRunning(boolean lastScoredTimerRunning) {
		this.lastScoredTimerRunning = lastScoredTimerRunning;
	}
	public void restartLastScoredTimer() {
		timer.restart();
	}
	public String getLastScoredTime() {
		return df.format(lastScoredHours) + ":" + df.format(lastScoredMinutes) + ":" + df.format(lastScoredSeconds);
	}
	public void startLastScoredTimer() {
		lastScoredSeconds=0;
		lastScoredMinutes=0;
		lastScoredHours=0;
		lastScoredTimerRunning=true;
	}
	public void stopLastScoredTimer() {
		lastScoredTimerRunning=false;
	}
	public void pauseLastScoredTimer(boolean pause) {
		if(pause) {
			lastScoredTimerRunning=false;
		} else {
			lastScoredTimerRunning=true;
		}
	}
	public void addLastScoredClockTimerListener(ActionListener alAction) {
		timer.addActionListener(alAction);
	}
}

