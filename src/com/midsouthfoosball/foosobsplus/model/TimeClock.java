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

import javax.swing.Timer;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

public class TimeClock {
	private int nbrOfSeconds;
	private int timeRemaining;
	private Timer timer;
	private String timerInUse;
	private OBSInterface obsInterface;
	private Settings settings;
	
	public TimeClock(OBSInterface obsInterface, Settings settings) {
		
		this.obsInterface = obsInterface;
		this.settings = settings;
		
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				timeRemaining--;
 				if(timeRemaining < 0) {
 					timer.stop();
 				} else if(timeRemaining < 1 ) {
					timeRemaining = 0;
				}
			}
		};

		timer = new Timer(100, action);
		timer.setInitialDelay(0);
	}
	public String getTimerInUse() {
		return timerInUse;
	}
	public void setTimerInUse(String timerInUse) {
		this.timerInUse = timerInUse;
		writeTimerInUse();
	}
	public void setTimer(int nbrOfSeconds) {
		this.nbrOfSeconds = nbrOfSeconds;
		this.timeRemaining = this.nbrOfSeconds;
		timer.restart();
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}
	public int getNbrOfSeconds() {
		return nbrOfSeconds;
	}
	public void addTimeClockTimerListener(ActionListener alAction) {
		timer.addActionListener(alAction);
	}
	private void writeTimerInUse() {
		try {
			obsInterface.setContents(settings.getTimerInUseFileName(), getTimerInUse());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
