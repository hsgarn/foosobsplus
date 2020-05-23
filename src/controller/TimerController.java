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
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import model.Settings;
import model.TimeClock;
import view.TimerPanel;
import view.TimerWindowFrame;

public class TimerController {
	private TimerPanel timerPanel;
	private TimerWindowFrame timerWindowFrame;
	private TimeClock timeClock;
	private Settings settings;
	private int displayWidth = 9;
	private int prefixWidth;
	private int suffixWidth = 3;
	
	public TimerController(TimerPanel timerPanel, TimerWindowFrame timerWindowFrame, TimeClock timeClock, Settings settings) {
		this.timerPanel = timerPanel;
		this.timerWindowFrame = timerWindowFrame;
		this.timeClock = timeClock;
		this.settings = settings;
		
		////// Timer Panel Listener Methods //////
	
		this.timerPanel.addShotTimerListener(new ShotTimerListener());
		this.timerPanel.addPassTimerListener(new PassTimerListener());
		this.timerPanel.addTimeOutTimerListener(new TimeOutTimerListener());
		this.timerPanel.addGameTimerListener(new GameTimerListener());
		this.timerPanel.addRecallTimerListener(new RecallTimerListener());
		this.timerPanel.addResetTimerListener(new ResetTimerListener());
		this.timeClock.addTimeClockTimerListener(alAction);
	}
	
	
	private class ShotTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startShotTimer();
		}
	}
	private class PassTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startPassTimer();
		}
	}
	private class TimeOutTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startTimeOutTimer();
		}
	}
	private class GameTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startGameTimer();
		}
	}
	private class RecallTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startRecallTimer();
		}
	}
	private class ResetTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			resetTimer();
		}
	}
	ActionListener alAction = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			updateTimerDisplay();
		}
	};
	
	////// Utility Methodss //////
	
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
	}
}
