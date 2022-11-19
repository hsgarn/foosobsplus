/**
Copyright 2021, 2022 Hugh Garner
Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR 
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
OTHER DEALINGS IN THE SOFTWARE.  
**/
package com.midsouthfoosball.foosobsplus.view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class StatSettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtTeam1NameFileName;
	private Settings settings;


	/**
	 * Create the Panel.
	 */
	public StatSettingsPanel(Settings settings) throws IOException {
		this.settings = settings;
		
		setBounds(100, 100, 853, 489);
		setLayout(new MigLayout("", "[][][151.00][15.00][91.00][grow][]", "[][][][][][][][][][][][][][][]"));
		
		JLabel lblFileName = new JLabel("File Name");
		add(lblFileName, "cell 2 0,alignx left");
		
		JLabel lblNewLabel = new JLabel("File Name");
		add(lblNewLabel, "cell 5 0,alignx left,aligny center");
		
		JLabel lblTeam1NameFileName = new JLabel("Team 1:");
		add(lblTeam1NameFileName, "cell 1 1,alignx trailing");
		
		txtTeam1NameFileName = new JTextField();
		txtTeam1NameFileName.setText(settings.getTeamNameSource(1));
		add(txtTeam1NameFileName, "cell 2 1,alignx left");
		txtTeam1NameFileName.setColumns(10);
		
		JButton btnSaveFileNames = new JButton("Save");
		btnSaveFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveFileNames, "cell 2 14,alignx center");
		
		JButton btnCancelFileNames = new JButton("Cancel");
		btnCancelFileNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelFileNames, "cell 4 14,alignx center");
		
		JButton btnRestoreDefaults = new JButton("Restore Defaults");
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 14,alignx center");
	}

	private void restoreDefaults(Settings settings) {
		txtTeam1NameFileName.setText(settings.getDefaultTeam1NameSource());
	}
	
	private void saveSettings() {
		settings.setTeam1NameSource(txtTeam1NameFileName.getText());
//		try {
//			settings.saveStatsConfig();
//		} catch (IOException ex) {
//			System.out.print("Error saving properties file: " + ex.getMessage());		
//		}
	}
}
