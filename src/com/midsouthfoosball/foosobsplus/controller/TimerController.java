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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.view.LastScoredWindowFrame;
import com.midsouthfoosball.foosobsplus.view.TimerPanel;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

public class TimerController {
	private OBSInterface obsInterface;
	private Settings settings;
	private TimerPanel timerPanel;
	private TimerWindowFrame timerWindowFrame;
	private TimeClock timeClock;
	private LastScoredWindowFrame lastScored1WindowFrame;
	private LastScoredClock lastScored1Clock;
	private LastScoredWindowFrame lastScored2WindowFrame;
	private LastScoredClock lastScored2Clock;
	private int displayWidth = 9;
	private int prefixWidth;
	private int suffixWidth = 3;
	
	public TimerController(OBSInterface obsInterface, Settings settings, TimerPanel timerPanel, TimerWindowFrame timerWindowFrame, TimeClock timeClock, LastScoredWindowFrame lastScored1WindowFrame, LastScoredClock lastScored1Clock, LastScoredWindowFrame lastScored2WindowFrame, LastScoredClock lastScored2Clock) {
		this.obsInterface = obsInterface;
		this.settings = settings;
		this.timerPanel = timerPanel;
		this.timerWindowFrame = timerWindowFrame;
		this.timeClock = timeClock;
		this.lastScored1WindowFrame = lastScored1WindowFrame;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2WindowFrame = lastScored2WindowFrame;
		this.lastScored2Clock = lastScored2Clock;
		
		////// Timer Listener Methods //////
	
		this.timeClock.addTimeClockTimerListener(timeClockListener);
		this.lastScored1Clock.addLastScoredClockTimerListener(lastScored1ClockListener);
		this.lastScored2Clock.addLastScoredClockTimerListener(lastScored2ClockListener);
	}
	
	ActionListener timeClockListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			updateTimerDisplay();
		}
	};
	ActionListener lastScored1ClockListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			updateLastScored1Display();
		}
	};
	ActionListener lastScored2ClockListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			updateLastScored2Display();
		}
	};
	
	////// Utility Methods //////
	
	public void startShotTimer() {
		int count = settings.getShotTime() * 10;
		timeClock.setTimerInUse("Shot Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startPassTimer() {
		int count = settings.getPassTime() * 10;
		timeClock.setTimerInUse("Pass Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startTimeOutTimer() {
		int count = settings.getTimeOutTime() * 10;
		timeClock.setTimerInUse("Time Out Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startGameTimer() {
		int count = settings.getGameTime() * 10;
		timeClock.setTimerInUse("Game Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startRecallTimer() {
		int count = settings.getRecallTime() * 600;
		timeClock.setTimerInUse("Recall Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void resetTimer() {
		timeClock.setTimerInUse("Timer Reset");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timeClock.setTimer(0);
	}
	private void updateTimerDisplay() {
		int timeRemaining = timeClock.getTimeRemaining();
		int nbrOfSeconds = timeClock.getNbrOfSeconds();
		int nbrOfMinutes = 0;
		int displaySeconds = 0;
		if(timeRemaining <= 0 && nbrOfSeconds != 0) {
			timerPanel.setTimerDisplayColor(Color.RED);
			timerWindowFrame.setTimerDisplayColor(Color.RED);
		}
		float tr = (float) timeRemaining / 10f;
		if(Float.compare(tr, 60f) > 0) {
			nbrOfMinutes = (int) (tr / 60);
			displaySeconds = (timeRemaining - (nbrOfMinutes * 600))/10;

			String timeLeft = new String(nbrOfMinutes + ":" + String.format("%02d", displaySeconds));
			prefixWidth = displayWidth - timeLeft.length() - suffixWidth;
			char[] c1 = new char[prefixWidth];
		    Arrays.fill(c1, ' ');
		    char[] c2 = new char[suffixWidth];
		    Arrays.fill(c2, ' ');
			timerPanel.updateTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
			timerWindowFrame.setTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
			
		} else {

			String timeLeft = new String(Float.toString(tr));
			prefixWidth = displayWidth - timeLeft.length() - suffixWidth;
			char[] c1 = new char[prefixWidth];
		    Arrays.fill(c1, ' ');
		    char[] c2 = new char[suffixWidth];
		    Arrays.fill(c2, ' ');
		    timerPanel.updateTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
		    timerWindowFrame.setTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
		}
		writeTimeRemaining();
	}
	private void updateLastScored1Display() {
		lastScored1WindowFrame.setTimerDisplay(lastScored1Clock.getLastScoredTime());
	}
	private void updateLastScored2Display() {
		lastScored2WindowFrame.setTimerDisplay(lastScored2Clock.getLastScoredTime());
	}
	private void writeTimeRemaining() {
		obsInterface.writeData(settings.getTimeRemainingSource(), timerPanel.getTimerDisplayText(),"TimerController",settings.getShowParsed());
	}
}
