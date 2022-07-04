/**
Copyright 2022 Hugh Garner
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class AutoScoreMainPanel extends JPanel {
	private JButton btnConnect;
	private JButton btnDisconnect;
	private JButton btnSettings;
	private JCheckBox chkbxIgnore;
	private Settings settings;
	private Border innerBorder;

	public AutoScoreMainPanel(Settings settings) {
		
		this.settings = settings;
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		
		btnConnect = new JButton(Messages.getString("AutoScoreMainPanel.Connect", settings.getGameType())); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("AutoScoreMainPanel.Disconnect", settings.getGameType())); //$NON-NLS-1$
		btnSettings = new JButton(Messages.getString("AutoScoreMainPanel.Settings", settings.getGameType())); //$NON-NLS-1$
		chkbxIgnore = new JCheckBox(Messages.getString("AutoScoreMainPanel.Ignore", settings.getGameType())); //$NON-NLS-1$

		setMnemonics();
		
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	public void changeGameType() {
		btnConnect.setText(Messages.getString("AutoScoreMainPanel.Connect", settings.getGameType())); //$NON-NLS-1$
		btnDisconnect.setText(Messages.getString("AutoScoreMainPanel.Disconnect", settings.getGameType())); //$NON-NLS-1$
		btnSettings.setText(Messages.getString("AutoScoreMainPanel.Settings", settings.getGameType())); //$NON-NLS-1$
		setTitle();
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		//////// Connect ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnConnect, gc);

		//////// Disconnect ////////
//		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnDisconnect, gc);

		//////// Settings////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(btnSettings, gc);
		
		//////// Ignore Sensors \\\\\\\\\\\\\
		gc.weightx = .1;
		gc.weighty = .1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(chkbxIgnore, gc);
		
	}
	private void setMnemonics() {
		if(settings.getAutoScoreMainConnectHotKey().isEmpty()) {
			btnConnect.setMnemonic(-1);
		} else {
			btnConnect.setMnemonic(settings.getAutoScoreMainConnectHotKey().charAt(0));
		};
		if(settings.getAutoScoreMainDisconnectHotKey().isEmpty()) {
			btnDisconnect.setMnemonic(-1);
		} else {
			btnDisconnect.setMnemonic(settings.getAutoScoreMainDisconnectHotKey().charAt(0));
		};
		if(settings.getAutoScoreMainSettingsHotKey().isEmpty()) {
			btnSettings.setMnemonic(-1);
		} else {
			btnSettings.setMnemonic(settings.getAutoScoreMainSettingsHotKey().charAt(0));
		};
	}
	////// Listeners  //////
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
	public void addSettingsListener(ActionListener listenForBtnSettings) {
		btnSettings.addActionListener(listenForBtnSettings);
	}

	////// Utility Methods //////
	
	public void updateMnemonics() {
		setMnemonics();
	}
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("AutoScoreMainPanel.AutoScoreMainPanel", settings.getGameType()); //$NON-NLS-1$
	}
	public boolean isIgnored() {
		return chkbxIgnore.isSelected();
	}
}
