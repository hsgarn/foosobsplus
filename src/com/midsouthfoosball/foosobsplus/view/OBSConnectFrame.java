package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class OBSConnectFrame extends JFrame {
	private OBSConnectPanel obsConnectPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public OBSConnectFrame(Settings settings) {
		super(programName + " " + Messages.getString("OBSConnectFrame.WindowTitle")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			obsConnectPanel = new OBSConnectPanel(settings);
		} catch (IOException e) {
			System.out.println(Messages.getString("Errors.LoadSettingsError")); //$NON-NLS-1$
			e.printStackTrace();
		}
		obsConnectPanel.setPreferredSize(new Dimension(650, 500));
		
		getContentPane().add(obsConnectPanel);
		pack();
	}
	public OBSConnectPanel getOBSConnectPanel() {
		return obsConnectPanel;
	}

}
