/**
Copyright 2022-2023 Hugh Garner
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

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class FiltersFrame extends JFrame {
	
	private FiltersPanel filtersPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public FiltersFrame(Settings settings, OBSInterface obsInterface) {
		super(programName + " " + Messages.getString("FiltersFrame.FiltersSettings")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			filtersPanel = new FiltersPanel(settings, obsInterface);
		} catch (IOException e) {
			logger.error(Messages.getString("Errors.LoadSettingsError")); //$NON-NLS-1$
			logger.error(e.toString());
		}
		filtersPanel.setPreferredSize(new Dimension(750, 630));
		
		getContentPane().add(filtersPanel);
		pack();
	}
}
