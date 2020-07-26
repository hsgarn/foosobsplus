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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.Timer;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class LastScoredClock2 {

	private Timer timer;
	private int lastScoredSeconds;
	private int lastScoredMinutes;
	private int lastScoredHours;
	private boolean  lastScoredTimerRunning;
	private OBSInterface obsInterface;
	private Settings settings;
	private DecimalFormat df = new DecimalFormat("00");
	
	public LastScoredClock2(OBSInterface obsInterface, Settings settings) {
		
		this.obsInterface = obsInterface;
		this.settings = settings;
		
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
					writeLastScoredTime();
				}
			}
		};

		timer = new Timer(1000, action);
		timer.setInitialDelay(1000);
		timer.start();
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
		writeLastScoredTime();
	}
	public void stopLastScoredTimer() {
		lastScoredTimerRunning=false;
	}
	public void pauseLastScoredTimer() {
		if(lastScoredTimerRunning==true) {
			lastScoredTimerRunning=false;
		}
		else {
			lastScoredTimerRunning=true;
		}
	}
	public void addLastScoredClockTimerListener(ActionListener alAction) {
		timer.addActionListener(alAction);
	}
	private void writeLastScoredTime() {
/*
 		try {
			obsInterface.setContents(settings.getLastScoredTimeFileName(team), getLastScoredTime());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
*/
	}
}

