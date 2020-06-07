package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class FileNamesFrame extends JFrame {
	
	private FileNamesPanel fileNamesPanel;
	
	public FileNamesFrame(Settings settings, OBSInterface obsInterface) {
		super("FoosOBSPlus File Names Settings");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			fileNamesPanel = new FileNamesPanel(settings, obsInterface);
		} catch (IOException e) {
			System.out.println("Problem loading settings.");
			e.printStackTrace();
		}
		fileNamesPanel.setPreferredSize(new Dimension(1100, 650));
		
		getContentPane().add(fileNamesPanel);
		pack();
	}
}
