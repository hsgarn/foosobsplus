/**
Copyright © 2022-2025 Hugh Garner
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.AppConfig;

@SuppressWarnings("serial")
public class AutoScoreSettingsFrame extends JFrame {
	private AutoScoreSettingsPanel autoScoreSettingsPanel;
	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreSettingsFrame.class);
	public AutoScoreSettingsFrame() {
		super(PROGRAMNAME + " " + Messages.getString("AutoScoreSettingsFrame.Title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		try {
			autoScoreSettingsPanel = new AutoScoreSettingsPanel();
		} catch (IOException e) {
			logger.error(Messages.getString("AutoScoreSettingsFrame.ErrorLoadingSettings")); //$NON-NLS-1$
			logger.error(e.toString());
		}
		autoScoreSettingsPanel.setPreferredSize(new Dimension(500, 400));
		getContentPane().add(autoScoreSettingsPanel);
		pack();
	}
	public AutoScoreSettingsPanel getAutoScoreSettingsPanel() {
		return autoScoreSettingsPanel;
	}
}