package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class FileNamesFrame extends JFrame {
	
	private FileNamesPanel fileNamesPanel;
	private final static String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public FileNamesFrame(Settings settings, OBSInterface obsInterface) {
		super(programName + " " + Messages.getString("FileNamesFrame.FileNamesSettings")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			fileNamesPanel = new FileNamesPanel(settings, obsInterface);
		} catch (IOException e) {
			System.out.println(Messages.getString("FileNamesFrame.LoadSettingsError")); //$NON-NLS-1$
			e.printStackTrace();
		}
		fileNamesPanel.setPreferredSize(new Dimension(1100, 650));
		
		getContentPane().add(fileNamesPanel);
		pack();
	}
}
