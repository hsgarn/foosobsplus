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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import com.midsouthfoosball.foosobsplus.view.LastScored1WindowFrame;
import com.midsouthfoosball.foosobsplus.view.LastScored2WindowFrame;
import com.midsouthfoosball.foosobsplus.view.MainFrame;
import com.midsouthfoosball.foosobsplus.view.TimerWindowFrame;

public class MainController {
	private MainFrame mainFrame;
	private TimerWindowFrame timerWindowFrame;
	private LastScored1WindowFrame lastScored1WindowFrame;
	private LastScored2WindowFrame lastScored2WindowFrame;
	
	public MainController(MainFrame mainFrame, TimerWindowFrame timerWindowFrame, LastScored1WindowFrame lastScored1WindowFrame, LastScored2WindowFrame lastScored2WindowFrame) {
		this.mainFrame = mainFrame;
		this.timerWindowFrame = timerWindowFrame;
		this.lastScored1WindowFrame = lastScored1WindowFrame;
		this.lastScored2WindowFrame = lastScored2WindowFrame;
		
		this.mainFrame.addTimerWindowListener(new TimerWindowListener());
		this.mainFrame.addLastScored1WindowListener(new LastScored1WindowListener());
		this.mainFrame.addLastScored2WindowListener(new LastScored2WindowListener());
	}

	////// MainFrame Listener Objects //////
	
	private class TimerWindowListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			timerWindowFrame.setVisible(box.isSelected());
		}
	}
	private class LastScored1WindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			lastScored1WindowFrame.setVisible(box.isSelected());
		}
	}
	private class LastScored2WindowListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JCheckBoxMenuItem box = (JCheckBoxMenuItem) ae.getSource();
			lastScored2WindowFrame.setVisible(box.isSelected());
		}
	}
}
