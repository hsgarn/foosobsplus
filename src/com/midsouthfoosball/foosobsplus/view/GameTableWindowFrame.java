/**
Copyright 2020-2023 Hugh Garner
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

import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class GameTableWindowFrame extends JFrame {
	
	private static final String programName = "FoosOBSPlus"; //$NON-NLS-1$
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public GameTableWindowFrame(GameTableWindowPanel gameTableWindowPanel, MainFrame mainFrame) {
		super(programName + " " + Messages.getString("GameTableWindowFrame.GameTableWindow")); //$NON-NLS-1$ //$NON-NLS-2$
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
//		setAlwaysOnTop(true);
		
		gameTableWindowPanel.setPreferredSize(new Dimension(440, 80));

		getContentPane().add(gameTableWindowPanel);
		setLocation(513,0);
		pack();
	}
	
	////// Listeners \\\\\\
	public void addGameTableWindowClosedListener(WindowListener listenForGameTableWindowClose) {
		this.addWindowListener(listenForGameTableWindowClose);
	}
}
