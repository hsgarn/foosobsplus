package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class SettingsFrame extends JFrame {
	
	private SettingsPanel settingsPanel;
	
	public SettingsFrame(Settings settings) {
		super("FoosOBSPlus Parameter Settings");

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Can't set look and feel.");
		}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		
		try {
			settingsPanel = new SettingsPanel(settings);
		} catch (IOException e) {
			System.out.println("Problem loading settings.");
			e.printStackTrace();
		}
		settingsPanel.setPreferredSize(new Dimension(650, 500));
		
		getContentPane().add(settingsPanel);
		pack();
	}
}
