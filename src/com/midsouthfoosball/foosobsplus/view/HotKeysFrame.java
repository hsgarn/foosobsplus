/**
Copyright © 2020-2024 Hugh Garner
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
public class HotKeysFrame extends JFrame {
	private HotKeysPanel hotKeysPanel;
	private static final String programName = AppConfig.PROGRAM_NAME;
	private static Logger logger = LoggerFactory.getLogger(HotKeysFrame.class);
	public HotKeysFrame() {
		super(programName + " " + Messages.getString("HotKeysFrame.HotKeySettings")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		try {
			hotKeysPanel = new HotKeysPanel();
		} catch (IOException e) {
			logger.error(Messages.getString("HotKeysFrame.ErrorLoadingSettings")); //$NON-NLS-1$
			logger.error(e.toString());
		}
		hotKeysPanel.setPreferredSize(new Dimension(850, 630));
		getContentPane().add(hotKeysPanel);
		pack();
	}
	public HotKeysPanel getHotKeysPanel() {
		return hotKeysPanel;
	}
}