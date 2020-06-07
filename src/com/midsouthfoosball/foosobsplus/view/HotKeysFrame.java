package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class HotKeysFrame extends JFrame {

	private HotKeysPanel hotKeysPanel;
	
	public HotKeysFrame(Settings settings) {
		super("FoosOBSPlus Hot Key Settings");
		
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
	public HotKeysPanel getHotKeysPanel() {
		return hotKeysPanel;
	}
}
