/**
Copyright © 2026 Hugh Garner
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.AppConfig;

@SuppressWarnings("serial")
public class AutoScoreTablesFrame extends JFrame {
	private final AutoScoreTablesPanel autoScoreTablesPanel;
	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;
	public AutoScoreTablesFrame() {
		super(PROGRAMNAME + " " + Messages.getString("AutoScoreTablesFrame.Title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		autoScoreTablesPanel = new AutoScoreTablesPanel();
		autoScoreTablesPanel.setPreferredSize(new Dimension(720, 420));
		getContentPane().add(autoScoreTablesPanel);
		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				autoScoreTablesPanel.confirmClose(AutoScoreTablesFrame.this);
			}
			@Override
			public void windowOpened(WindowEvent e) {
				autoScoreTablesPanel.reload();
			}
		});
	}
	public AutoScoreTablesPanel getAutoScoreTablesPanel() {
		return autoScoreTablesPanel;
	}
}
