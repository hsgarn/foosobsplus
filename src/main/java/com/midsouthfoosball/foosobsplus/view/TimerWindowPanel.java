/**
Copyright © 2020-2025 Hugh Garner
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

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

public class TimerWindowPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel lblTimerDisplay;
	private final int displayWidth = 9;
	private int prefixWidth;
	private final int suffixWidth = 3;

	/**
	 * Create the panel.
            * @param timeToDisplay
            * @param backgroundColor
	 */
	public TimerWindowPanel(String timeToDisplay, Color backgroundColor) {
		setLayout(new MigLayout("", "[280.00]", "[67.00]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		prefixWidth = displayWidth - timeToDisplay.length() - suffixWidth;
		char[] c1 = new char[prefixWidth];
	    Arrays.fill(c1, ' ');
	    char[] c2 = new char[suffixWidth];
	    Arrays.fill(c2, ' ');
		lblTimerDisplay = new JLabel(String.valueOf(c1) + timeToDisplay + String.valueOf(c2));
		lblTimerDisplay.setFont(new Font("Consolas", Font.BOLD, 50)); //$NON-NLS-1$
		lblTimerDisplay.setOpaque(true);
		lblTimerDisplay.setBackground(backgroundColor);
		add(lblTimerDisplay, "cell 0 0,alignx center,aligny baseline"); //$NON-NLS-1$

	}
	
	public void setTimerWindowText(String timeToDisplay) {
		prefixWidth = displayWidth - timeToDisplay.length() - suffixWidth;
		if(prefixWidth<0) prefixWidth = 0;
		char[] c1 = new char[prefixWidth];
	    Arrays.fill(c1, ' ');
	    char[] c2 = new char[suffixWidth];
	    Arrays.fill(c2, ' ');
		lblTimerDisplay.setText(String.valueOf(c1) + timeToDisplay + String.valueOf(c2));
	}
	
	public void setTimerWindowColor(Color colorToDisplay) {
		lblTimerDisplay.setBackground(colorToDisplay);
	}
}
