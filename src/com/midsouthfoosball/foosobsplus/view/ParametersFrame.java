package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class ParametersFrame extends JFrame {
	
	private ParametersPanel parametersPanel;
	
	public ParametersFrame(Settings settings) {
		super("FoosOBSPlus Parameter Settings");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			parametersPanel = new ParametersPanel(settings);
		} catch (IOException e) {
			System.out.println("Problem loading settings.");
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
