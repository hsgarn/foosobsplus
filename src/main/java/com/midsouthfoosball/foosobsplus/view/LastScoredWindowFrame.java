/**
Copyright © 2021-2026 Hugh Garner
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
import com.midsouthfoosball.foosobsplus.model.AppConfig;

@SuppressWarnings("serial")
public class LastScoredWindowFrame extends JFrame {
	private final LastScoredWindowPanel lastScoredWindowPanel;
	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;
	public LastScoredWindowFrame(MainFrame mainFrame, int teamNbr) {
		super(PROGRAMNAME + " " + Messages.getString("LastScoredWindowFrame.Team") + teamNbr + Messages.getString("LastScoredWindowFrame.LastScoredTimerWindow")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lastScoredWindowPanel = new LastScoredWindowPanel("0",Color.CYAN); //$NON-NLS-1$
		lastScoredWindowPanel.setPreferredSize(new Dimension(256, 70));
		getContentPane().add(lastScoredWindowPanel);
            switch (teamNbr) {
                case 1:
                    setLocation(257,0);
                    break;
                case 2:
                    setLocation(954,0);
                    break;
                default:
                    setLocation(1211,0);
                    break;
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