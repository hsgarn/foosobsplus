package view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.midsouthfoosball.foosobsplus.model.Settings;

import main.OBSInterface;

@SuppressWarnings("serial")
public class FileNamesFrame extends JFrame {
	
	private FileNamesPanel fileNamesPanel;
	
	public FileNamesFrame(Settings settings, OBSInterface obsInterface) {
		super("FoosOBSPlus File Names Settings");
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
			fileNamesPanel = new FileNamesPanel(settings, obsInterface);
		} catch (IOException e) {
			System.out.println("Problem loading settings.");
			e.printStackTrace();
		}
		fileNamesPanel.setPreferredSize(new Dimension(1100, 550));
		
		getContentPane().add(fileNamesPanel);
		pack();
	}
}
