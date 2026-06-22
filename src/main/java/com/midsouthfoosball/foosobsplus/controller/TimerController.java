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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.LastScoredClock;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableSession;
import com.midsouthfoosball.foosobsplus.model.TimeClock;
import com.midsouthfoosball.foosobsplus.view.LastScoredWindowFrame;
import com.midsouthfoosball.foosobsplus.view.TimerPanel;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

public class TimerController {
	private final OBSInterface obsInterface;
	private final TimerPanel timerPanel;
	private final TimerWindowFrame timerWindowFrame;
	private TimeClock timeClock;
	private final LastScoredWindowFrame lastScored1WindowFrame;
	private LastScoredClock lastScored1Clock;
	private final LastScoredWindowFrame lastScored2WindowFrame;
	private LastScoredClock lastScored2Clock;
	private final LastScoredWindowFrame lastScored3WindowFrame;
	private LastScoredClock lastScored3Clock;
	private final static int DISPLAYWIDTH = 9;
	private int prefixWidth;
	private final static int SUFFIXWIDTH = 3;
	public TimerController(OBSInterface obsInterface, TimerPanel timerPanel, TimerWindowFrame timerWindowFrame, TimeClock timeClock, LastScoredWindowFrame lastScored1WindowFrame, LastScoredClock lastScored1Clock, LastScoredWindowFrame lastScored2WindowFrame, LastScoredClock lastScored2Clock, LastScoredWindowFrame lastScored3WindowFrame, LastScoredClock lastScored3Clock) {
		this.obsInterface = obsInterface;
		this.timerPanel = timerPanel;
		this.timerWindowFrame = timerWindowFrame;
		this.timeClock = timeClock;
		this.lastScored1WindowFrame = lastScored1WindowFrame;
		this.lastScored1Clock = lastScored1Clock;
		this.lastScored2WindowFrame = lastScored2WindowFrame;
		this.lastScored2Clock = lastScored2Clock;
		this.lastScored3WindowFrame = lastScored3WindowFrame;
		this.lastScored3Clock = lastScored3Clock;
		////// Timer Listener Methods //////
		this.timeClock.addTimeClockTimerListener(timeClockListener);
		this.lastScored1Clock.addLastScoredClockTimerListener(lastScored1ClockListener);
		this.lastScored2Clock.addLastScoredClockTimerListener(lastScored2ClockListener);
		this.lastScored3Clock.addLastScoredClockTimerListener(lastScored3ClockListener);
	}
	ActionListener timeClockListener = (ActionEvent event) -> {
            updateTimerDisplay();
        };
	ActionListener lastScored1ClockListener = (ActionEvent event) -> {
            updateLastScored1Display();
        };
	ActionListener lastScored2ClockListener = (ActionEvent event) -> {
            updateLastScored2Display();
        };
	ActionListener lastScored3ClockListener = (ActionEvent event) -> {
            updateLastScored3Display();
        };
	////// Session Binding //////
	// Repoints this controller at the active table's clocks. The timer listeners
	// read these bound fields (not the event source), so once repointed the
	// display follows the new active session automatically; background clocks
	// firing the same listeners simply re-render the active values.
	public void bindSession(TableSession session) {
		this.timeClock = session.getTimeClock();
		this.lastScored1Clock = session.getLastScored1Clock();
		this.lastScored2Clock = session.getLastScored2Clock();
		this.lastScored3Clock = session.getLastScored3Clock();
	}
	// Attaches this controller's clock listeners to a session's clocks. Call once
	// per session at creation; the constructor already wires the initial session.
	public void attachListeners(TableSession session) {
		session.getTimeClock().addTimeClockTimerListener(timeClockListener);
		session.getLastScored1Clock().addLastScoredClockTimerListener(lastScored1ClockListener);
		session.getLastScored2Clock().addLastScoredClockTimerListener(lastScored2ClockListener);
		session.getLastScored3Clock().addLastScoredClockTimerListener(lastScored3ClockListener);
	}
	////// Utility Methods //////
	public void startShotTimer() {
		int count = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_SHOT_TIME)) * 10;
		timeClock.setTimerInUse("Shot Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startPassTimer() {
		int count = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_PASS_TIME)) * 10;
		timeClock.setTimerInUse("Pass Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startTimeOutTimer() {
		int count = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_TIME_OUT_TIME)) * 10;
		timeClock.setTimerInUse("Time Out Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startGameTimer() {
		int count = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_GAME_TIME)) * 10;
		timeClock.setTimerInUse("Game Timer");
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		timerPanel.setTimerDisplayColor(Color.GREEN);
		timerWindowFrame.setTimerDisplayColor(Color.GREEN);
		timerPanel.updateTimerDisplay(count);
		timeClock.setTimer(count);
	}
	public void startRecallTimer() {
		int count = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_RECALL_TIME)) * 600;
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
	// Re-renders the bound clocks to the timer + last-scored displays without
	// mutating them. Used after a table switch (or a background score that briefly
	// bound the controller to another table) so the panels show the active table.
	public void refreshDisplay() {
		timerPanel.updateTimerInUse(timeClock.getTimerInUse());
		updateTimerDisplay();
		updateLastScored1Display();
		updateLastScored2Display();
		updateLastScored3Display();
	}
	private void updateTimerDisplay() {
		int timeRemaining = timeClock.getTimeRemaining();
		int nbrOfSeconds = timeClock.getNbrOfSeconds();
		int nbrOfMinutes;
		int displaySeconds;
		if(timeRemaining <= 0 && nbrOfSeconds != 0) {
			timerPanel.setTimerDisplayColor(Color.RED);
			timerWindowFrame.setTimerDisplayColor(Color.RED);
		}
		float tr = (float) timeRemaining / 10f;
		if(Float.compare(tr, 60f) > 0) {
			nbrOfMinutes = (int) (tr / 60);
			displaySeconds = (timeRemaining - (nbrOfMinutes * 600))/10;
			String timeLeft = nbrOfMinutes + ":" + String.format("%02d", displaySeconds);
			prefixWidth = DISPLAYWIDTH - timeLeft.length() - SUFFIXWIDTH;
			char[] c1 = new char[prefixWidth];
		    Arrays.fill(c1, ' ');
		    char[] c2 = new char[SUFFIXWIDTH];
		    Arrays.fill(c2, ' ');
			timerPanel.updateTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
			timerWindowFrame.setTimerDisplay(String.valueOf(c1) + timeLeft + String.valueOf(c2));
			
		} else {

			String timeLeft = Float.toString(tr);
			prefixWidth = DISPLAYWIDTH - timeLeft.length() - SUFFIXWIDTH;
			char[] c1 = new char[prefixWidth];
		    Arrays.fill(c1, ' ');
		    char[] c2 = new char[SUFFIXWIDTH];
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
	private void updateLastScored3Display() {
		lastScored3WindowFrame.setTimerDisplay(lastScored3Clock.getLastScoredTime());
	}
	private void writeTimeRemaining() {
		obsInterface.writeData(Settings.getSourceParameter(SettingsKeys.SRC_TIME_REMAINING), timerPanel.getTimerDisplayText(),"TimerController",Settings.getShowParsed());
	}
}
