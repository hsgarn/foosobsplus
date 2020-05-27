package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class HotKeysFrame extends JFrame {

	private HotKeysPanel hotKeysPanel;
	
	public HotKeysFrame(Settings settings) {
		super("FoosOBSPlus Hot Key Settings");
		
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
			hotKeysPanel = new HotKeysPanel(settings);
		} catch (IOException e) {
			System.out.println("Problem loading settings.");
			e.printStackTrace();
		}
		hotKeysPanel.setPreferredSize(new Dimension(800, 550));
		
		getContentPane().add(hotKeysPanel);
		pack();
	}
}
