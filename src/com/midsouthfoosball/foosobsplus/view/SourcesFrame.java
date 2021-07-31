package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class SourcesFrame extends JFrame {
	
	private SourcesPanel sourcesPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public SourcesFrame(Settings settings, OBSInterface obsInterface) {
		super(programName + " " + Messages.getString("SourcesFrame.SourcesSettings")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			sourcesPanel = new SourcesPanel(settings, obsInterface);
		} catch (IOException e) {
			System.out.println(Messages.getString("Errors.LoadSettingsError")); //$NON-NLS-1$
			e.printStackTrace();
		}
		sourcesPanel.setPreferredSize(new Dimension(1100, 650));
		
		getContentPane().add(sourcesPanel);
		pack();
	}
}
