/**
Copyright 2020, 2021, 2022 Hugh Garner
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class AutoScorePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtServerHostName;
	private JTextField txtServerAddress;
	private JTextField txtServerPort;
	private JButton btnSave;

	public AutoScorePanel(Settings settings) throws IOException {
		setLayout(new MigLayout("", "[][grow][10.00][][grow][10.00][][grow][10.00][][grow]", "[][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		JLabel lblServerHostName = new JLabel(Messages.getString("AutoScorePanel.ServerHostName", settings.getGameType())); //$NON-NLS-1$
		add(lblServerHostName, "cell 0 1,alignx trailing"); //$NON-NLS-1$
		
		txtServerHostName = new JTextField();
		txtServerHostName.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerHostName.setText(settings.getAutoScoreServerHostName());
		add(txtServerHostName, "cell 1 1,alignx left"); //$NON-NLS-1$
		txtServerHostName.setColumns(10);
		
		JLabel lblServerAddress = new JLabel(Messages.getString("AutoScorePanel.ServerAddress", settings.getGameType())); //$NON-NLS-1$
		add(lblServerAddress, "cell 0 2,alignx trailing"); //$NON-NLS-1$
		
		txtServerAddress = new JTextField();
		txtServerAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerAddress.setText(settings.getAutoScoreServerAddress());
		add(txtServerAddress, "cell 1 2,alignx left"); //$NON-NLS-1$
		txtServerAddress.setColumns(10);
		
		JLabel lblServerPort = new JLabel(Messages.getString("AutoScorePanel.ServerPort", settings.getGameType())); //$NON-NLS-1$
		add(lblServerPort, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		
		txtServerPort = new JTextField();
		txtServerPort.setHorizontalAlignment(SwingConstants.CENTER);
		txtServerPort.setText(Integer.toString(settings.getAutoScoreServerPort()));
		add(txtServerPort, "cell 1 4,alignx left,aligny top"); //$NON-NLS-1$
		txtServerPort.setColumns(10);
		
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 3 18,alignx center"); //$NON-NLS-1$
		
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 6 18,alignx center"); //$NON-NLS-1$
		
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 9 18,alignx center"); //$NON-NLS-1$

	}
	private void restoreDefaults(Settings settings) {
		txtServerHostName.setText(settings.getDefaultAutoScoreServerHostName());
		txtServerAddress.setText(settings.getDefaultAutoScoreServerAddress());
		txtServerPort.setText(Integer.toString(settings.getDefaultAutoScoreServerPort()));
	}
	
	public void saveSettings(Settings settings) {
		settings.setAutoScoreServerHostName(txtServerHostName.getText());
		settings.setAutoScoreServerAddress(txtServerAddress.getText());
		settings.setAutoScoreServerPort(txtServerPort.getText());
		try {
			settings.saveAutoScoreConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		}
	}
	////// Listeners \\\\\\
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
}
