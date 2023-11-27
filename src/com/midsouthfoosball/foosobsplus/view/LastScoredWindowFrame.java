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
package com.midsouthfoosball.foosobsplus.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class LastScoredWindowFrame extends JFrame {
	
	private LastScoredWindowPanel lastScoredWindowPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public LastScoredWindowFrame(MainFrame mainFrame, int teamNbr) {
		super(programName + " " + Messages.getString("LastScoredWindowFrame.Team" + teamNbr + "LastScoredTimerWindow")); //$NON-NLS-1$ //$NON-NLS-2$
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) { //$NON-NLS-1$
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    logger.error(Messages.getString("Errors.LookAndFeelException")); //$NON-NLS-1$
		    logger.error(e.toString());
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lastScoredWindowPanel = new LastScoredWindowPanel("0",Color.CYAN); //$NON-NLS-1$
		lastScoredWindowPanel.setPreferredSize(new Dimension(256, 70));

		getContentPane().add(lastScoredWindowPanel);
		if (teamNbr == 1) {
			setLocation(257,0);
		} else if (teamNbr == 2) {
			setLocation(954,0);
		} else {
			setLocation(1211,0);
		}
		pack();
	}
	public void setTimerDisplay(String timeToDisplay) {
		lastScoredWindowPanel.setWindowText(timeToDisplay);
	}
	public void setTimerDisplayColor(Color colorToDisplay) {
		lastScoredWindowPanel.setWindowColor(colorToDisplay);
	}
	////// Listeners \\\\\\
	public void addLastScoredWindowClosedListener(WindowListener listenForLastScoredWindowClose) {
		this.addWindowListener(listenForLastScoredWindowClose);
	}

}
