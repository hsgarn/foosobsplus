package com.midsouthfoosball.foosobsplus.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class HotKeysFrame extends JFrame {

	private HotKeysPanel hotKeysPanel;
	private static final String programName = "FoosOBSPlus"; //$NON-NLS-1$
	
	public HotKeysFrame(Settings settings) {
		super(programName + " " + Messages.getString("HotKeysFrame.HotKeySettings")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		try {
			hotKeysPanel = new HotKeysPanel(settings);
		} catch (IOException e) {
			System.out.println(Messages.getString("HotKeysFrame.ErrorLoadingSettings")); //$NON-NLS-1$
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
