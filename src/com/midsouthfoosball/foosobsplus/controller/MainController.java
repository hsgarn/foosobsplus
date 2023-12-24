/**
Copyright 2020-2024 Hugh Garner
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBoxMenuItem;

import com.midsouthfoosball.foosobsplus.view.GameTableWindowFrame;
import com.midsouthfoosball.foosobsplus.view.LastScoredWindowFrame;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

public class MainController {
	private MainFrame mainFrame;
	private TimerWindowFrame timerWindowFrame;
	private LastScoredWindowFrame lastScored1WindowFrame;
	private LastScoredWindowFrame lastScored2WindowFrame;
	private LastScoredWindowFrame lastScored3WindowFrame;
	private GameTableWindowFrame gameTableWindowFrame;
	
	public MainController(MainFrame mainFrame, TimerWindowFrame timerWindowFrame, LastScoredWindowFrame lastScored1WindowFrame, LastScoredWindowFrame lastScored2WindowFrame, LastScoredWindowFrame lastScored3WindowFrame, GameTableWindowFrame gameTableWindowFrame) {
		this.mainFrame = mainFrame;
		this.timerWindowFrame = timerWindowFrame;
		this.lastScored1WindowFrame = lastScored1WindowFrame;
		this.lastScored2WindowFrame = lastScored2WindowFrame;
		this.lastScored3WindowFrame = lastScored3WindowFrame;
		this.gameTableWindowFrame = gameTableWindowFrame;
		this.mainFrame.addTimerWindowListener(new TimerWindowListener());
		this.mainFrame.addLastScored1WindowListener(new LastScored1WindowListener());
		this.mainFrame.addLastScored2WindowListener(new LastScored2WindowListener());
		this.mainFrame.addLastScored3WindowListener(new LastScored3WindowListener());
		this.mainFrame.addGameTableWindowListener(new GameTableWindowListener());
		this.mainFrame.addViewAllWindowsListener(new ViewAllWindowsListener());
		this.mainFrame.addViewTimerWindowItemListener(new TimerWindowViewItemListener());
		this.mainFrame.addLastScored1WindowItemListener(new LastScored1ViewItemListener());
		this.mainFrame.addLastScored2WindowItemListener(new LastScored2ViewItemListener());
		this.mainFrame.addLastScored3WindowItemListener(new LastScored3ViewItemListener());
		this.mainFrame.addGameTableWindowItemListener(new GameTableViewItemListener());
		this.timerWindowFrame.addTimerWindowClosedListener(new TimerWindowCloseListener());
		this.lastScored1WindowFrame.addLastScoredWindowClosedListener(new LastScoredWindow1CloseListener());
		this.lastScored2WindowFrame.addLastScoredWindowClosedListener(new LastScoredWindow2CloseListener());
		this.lastScored3WindowFrame.addLastScoredWindowClosedListener(new LastScoredWindow3CloseListener());
		this.gameTableWindowFrame.addGameTableWindowClosedListener(new GameTableWindowCloseListener());
	}
	public void setFocusOnCode() {
		mainFrame.setFocusOnCode();
	}
	public void showTimerWindow() {
		showTimerWindow(true);
	}
	public void showTimerWindow(boolean show) {
		timerWindowFrame.setVisible(show);
		mainFrame.setTimerWindowSelected(show);
	}
	public boolean getTimerWindowSelected() {
		return mainFrame.getTimerWindowSelected();
	}
	public void showAutoScore() {
		mainFrame.showAutoScoreSettings();
	}
	////// MainFrame Listener Objects //////
	private class TimerWindowCloseListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent we) {
			mainFrame.setTimerWindowSelected(false);
		}
	}
	private class LastScoredWindow1CloseListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent we) {
			mainFrame.setLastScored1WindowSelected(false);
		}
	}
	private class LastScoredWindow2CloseListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent we) {
			mainFrame.setLastScored2WindowSelected(false);
		}
	}
	private class LastScoredWindow3CloseListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent we) {
			mainFrame.setLastScored3WindowSelected(false);
		}
	}
	private class GameTableWindowCloseListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent we) {
			mainFrame.setGameTableWindowSelected(false);
		}
	}
	private class TimerWindowViewItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			checkForAllOff();
		}
	}
	private class LastScored1ViewItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			checkForAllOff();
		}
	}
	private class LastScored2ViewItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			checkForAllOff();
		}
	}
	private class LastScored3ViewItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			checkForAllOff();
		}
	}
	private class GameTableViewItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			checkForAllOff();
		}
	}
	private class TimerWindowListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			timerWindowFrame.setVisible(box.isSelected());
			checkForAllOff();
		}
	}
	private class LastScored1WindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			lastScored1WindowFrame.setVisible(box.isSelected());
			checkForAllOff();
		}
	}
	private class LastScored2WindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			lastScored2WindowFrame.setVisible(box.isSelected());
			checkForAllOff();
		}
	}
	private class LastScored3WindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			lastScored3WindowFrame.setVisible(box.isSelected());
			checkForAllOff();
		}
	}
	private class GameTableWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			gameTableWindowFrame.setVisible(box.isSelected());
			checkForAllOff();
		}
	}
	private class ViewAllWindowsListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			boolean state = box.isSelected();
			timerWindowFrame.setVisible(state);
			lastScored1WindowFrame.setVisible(state);
			lastScored2WindowFrame.setVisible(state);
			lastScored3WindowFrame.setVisible(state);
			gameTableWindowFrame.setVisible(state);
			mainFrame.setGameTableWindowSelected(state);
			mainFrame.setTimerWindowSelected(state);
			mainFrame.setLastScored1WindowSelected(state);
			mainFrame.setLastScored2WindowSelected(state);
			mainFrame.setLastScored3WindowSelected(state);
		}
	}
	private void checkForAllOff() {
		if (mainFrame.getAllWindowsSelected() 
				&& !mainFrame.getGameTableWindowSelected() 
				&& !mainFrame.getTimerWindowSelected() 
				&& !mainFrame.getLastScored1WindowSelected() 
				&& !mainFrame.getLastScored2WindowSelected()
				&& !mainFrame.getLastScored3WindowSelected()) {
			mainFrame.setAllWindowsSelected(false);
		}
	}
}
