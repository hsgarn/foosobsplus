package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class ParametersFrame extends JFrame {
	
	private ParametersPanel parametersPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public ParametersFrame(Settings settings) {
		super(programName + " " + Messages.getString("ParametersFrame.ParameterSettings")); //$NON-NLS-1$ //$NON-NLS-2$

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			parametersPanel = new ParametersPanel(settings);
		} catch (IOException e) {
			System.out.println(Messages.getString("Errors.LoadSettingsError")); //$NON-NLS-1$
			e.printStackTrace();
		}
		parametersPanel.setPreferredSize(new Dimension(650, 500));
		
		getContentPane().add(parametersPanel);
		pack();
	}
	public ParametersPanel getSettingsPanel() {
		return parametersPanel;
	}
}
