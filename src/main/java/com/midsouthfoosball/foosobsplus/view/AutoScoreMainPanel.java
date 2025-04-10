/**
Copyright © 2022-2025 Hugh Garner
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
	private final JButton btnConnect;
	private final JButton btnDisconnect;
	private final JButton btnSettings;
	private final JCheckBox chkbxIgnore;
	private final Border innerBorder;
	public AutoScoreMainPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 100;
		setPreferredSize(dim);
		setName(buildTitle());
		btnConnect = new JButton(Messages.getString("AutoScoreMainPanel.Connect")); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("AutoScoreMainPanel.Disconnect")); //$NON-NLS-1$
		btnSettings = new JButton(Messages.getString("AutoScoreMainPanel.Settings")); //$NON-NLS-1$
		chkbxIgnore = new JCheckBox(Messages.getString("AutoScoreMainPanel.Ignore")); //$NON-NLS-1$
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	private void layoutComponents() {
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
		if(Settings.getHotKeyParameter("AutoScoreMainConnect").isEmpty()) { //$NON-NLS-1$
			btnConnect.setMnemonic(-1);
		} else {
			btnConnect.setMnemonic(Settings.getHotKeyParameter("AutoScoreMainConnect").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("AutoScoreMainDisconnect").isEmpty()) { //$NON-NLS-1$
			btnDisconnect.setMnemonic(-1);
		} else {
			btnDisconnect.setMnemonic(Settings.getHotKeyParameter("AutoScoreMainDisconnect").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("AutoScoreMainSettings").isEmpty()) { //$NON-NLS-1$
			btnSettings.setMnemonic(-1);
		} else {
			btnSettings.setMnemonic(Settings.getHotKeyParameter("AutoScoreMainSettings").charAt(0)); //$NON-NLS-1$
		}
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
		return Messages.getString("AutoScoreMainPanel.Title"); //$NON-NLS-1$
	}
	public boolean isIgnored() {
		return chkbxIgnore.isSelected();
	}
}
