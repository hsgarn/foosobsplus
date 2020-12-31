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
package com.midsouthfoosball.foosobsplus.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class TimerWindowFrame extends JFrame {
	
	private TimerWindowPanel timerWindowPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$

	public TimerWindowFrame(MainFrame mainFrame) {
		super(programName + " " + Messages.getString("TimerWindowFrame.TimerWindow")); //$NON-NLS-1$ //$NON-NLS-2$
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) { //$NON-NLS-1$
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    System.out.println(Messages.getString("Errors.LookAndFeelException")); //$NON-NLS-1$
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		timerWindowPanel = new TimerWindowPanel("0",Color.GREEN); //$NON-NLS-1$
		timerWindowPanel.setPreferredSize(new Dimension(256, 70));

		getContentPane().add(timerWindowPanel);
		setLocation(0,0);
		pack();
	}
	public void setTimerDisplay(String timeToDisplay) {
		timerWindowPanel.setTimerWindowText(timeToDisplay);
	}
	public void setTimerDisplayColor(Color colorToDisplay) {
		timerWindowPanel.setTimerWindowColor(colorToDisplay);
	}
	////// Listeners \\\\\\
	public void addTimerWindowClosedListener(WindowListener listenForTimerWindowClose) {
		this.addWindowListener(listenForTimerWindowClose);
	}

}
