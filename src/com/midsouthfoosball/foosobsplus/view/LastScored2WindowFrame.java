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
public class LastScored2WindowFrame extends JFrame {
	
	private LastScored2WindowPanel lastScored2WindowPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$

	public LastScored2WindowFrame(MainFrame mainFrame) {
		super(programName + " " + Messages.getString("LastScored2WindowFrame.Team2LastScoredTimerWindow")); //$NON-NLS-1$ //$NON-NLS-2$
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
//		setAlwaysOnTop(true);
		
		lastScored2WindowPanel = new LastScored2WindowPanel("0",Color.CYAN); //$NON-NLS-1$
		lastScored2WindowPanel.setPreferredSize(new Dimension(256, 70));

		getContentPane().add(lastScored2WindowPanel);
		setLocation(954,0);
		pack();
	}
	public void setTimerDisplay(String timeToDisplay) {
		lastScored2WindowPanel.setWindowText(timeToDisplay);
	}
	public void setTimerDisplayColor(Color colorToDisplay) {
		lastScored2WindowPanel.setWindowColor(colorToDisplay);
	}
	////// Listeners \\\\\\
	public void addLastScored2WindowClosedListener(WindowListener listenForLastScored2WindowClose) {
		this.addWindowListener(listenForLastScored2WindowClose);
	}
}
