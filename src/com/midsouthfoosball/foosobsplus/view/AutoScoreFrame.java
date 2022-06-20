/**
Copyright 2022 Hugh Garner
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
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class AutoScoreFrame extends JFrame {

	private AutoScorePanel autoScorePanel;
	private static final String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public AutoScoreFrame(Settings settings) {
		super(programName + " " + Messages.getString("AutoScoreFrame.AutoScoreSettings")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		try {
			autoScorePanel = new AutoScorePanel(settings);
		} catch (IOException e) {
			System.out.println(Messages.getString("AutoScoreFrame.ErrorLoadingSettings")); //$NON-NLS-1$
			e.printStackTrace();
		}
		autoScorePanel.setPreferredSize(new Dimension(800, 550));
		
		getContentPane().add(autoScorePanel);
		pack();
	}
	public AutoScorePanel getAutoScorePanel() {
		return autoScorePanel;
	}
}
