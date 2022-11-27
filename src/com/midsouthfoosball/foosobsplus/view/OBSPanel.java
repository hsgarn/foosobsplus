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
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class OBSPanel extends JPanel {
	private JButton btnConnect;
	private JButton btnDisconnect;
	private JButton btnPull;
	private JButton btnPush;
	private JToggleButton tglbtnShowScores;
	private JToggleButton tglbtnShowTimer;
	private JToggleButton tglbtnEnableSkunk;
	private JButton btnStartStream;
	private Settings settings;
	private Border innerBorder;

	public OBSPanel(Settings settings) {
		
		this.settings = settings;
		Dimension dim = getPreferredSize();
		dim.width = 350;
		dim.height = 50;
		setPreferredSize(dim);
		String enableSkunkText;
		boolean enableSkunkInitialState = false;
		if (settings.getShowSkunk()==1) {
			enableSkunkInitialState = true;
			enableSkunkText = Messages.getString("OBSPanel.DisableSkunk", settings.getGameType()); //$NON-NLS-1$
		} else {
			enableSkunkText = Messages.getString("OBSPanel.EnableSkunk", settings.getGameType()); //$NON-NLS-1$
		}
		
		btnConnect = new JButton(Messages.getString("OBSPanel.Connect", settings.getGameType())); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("OBSPanel.Disconnect", settings.getGameType())); //$NON-NLS-1$
		btnPull = new JButton(Messages.getString("OBSPanel.Pull", settings.getGameType())); //$NON-NLS-1$
		btnPush = new JButton(Messages.getString("OBSPanel.Push", settings.getGameType())); //$NON-NLS-1$
		tglbtnShowScores = new JToggleButton(Messages.getString("OBSPanel.ShowScores", settings.getGameType())); //$NON-NLS-1$
		tglbtnShowTimer = new JToggleButton(Messages.getString("OBSPanel.ShowTimer", settings.getGameType())); //$NON-NLS-1$
		tglbtnEnableSkunk = new JToggleButton(enableSkunkText);
		tglbtnEnableSkunk.setSelected(enableSkunkInitialState);
		
		btnStartStream = new JButton(Messages.getString("OBSPanel.StartStream",settings.getGameType())); //$NON-NLS-1$

		setMnemonics();
		
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		
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
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnConnect, gc);

		//////// Disconnect ////////
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnDisconnect, gc);

		//////// Push ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnPush, gc);
		
		//////// Pull ////////
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnPull, gc);
		
		//////// Show Scores ////////
		gc.gridy++;

		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tglbtnShowScores, gc);
		
		/////// Enable Skunk /////////
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tglbtnEnableSkunk, gc);
		
		/////// Show Timer /////////
		gc.gridy++;
		
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tglbtnShowTimer, gc);

		//////// Start Stream ////////
		gc.weightx = .1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(btnStartStream, gc);
		
	}
	private void setMnemonics() {
		if(settings.getConnectHotKey().isEmpty()) {
			btnConnect.setMnemonic(-1);
		} else {
			btnConnect.setMnemonic(settings.getConnectHotKey().charAt(0));
		};
		if(settings.getDisconnectHotKey().isEmpty()) {
			btnDisconnect.setMnemonic(-1);
		} else {
			btnDisconnect.setMnemonic(settings.getDisconnectHotKey().charAt(0));
		};
		if(settings.getPushHotKey().isEmpty()) {
			btnPush.setMnemonic(-1);
		} else {
			btnPush.setMnemonic(settings.getPushHotKey().charAt(0));
		};
		if(settings.getPullHotKey().isEmpty()) {
			btnPull.setMnemonic(-1);
		} else {
			btnPull.setMnemonic(settings.getPullHotKey().charAt(0));
		};
		if(settings.getShowScoresHotKey().isEmpty()) {
			tglbtnShowScores.setMnemonic(-1);
		} else {
			tglbtnShowScores.setMnemonic(settings.getShowScoresHotKey().charAt(0));
		};
		if(settings.getShowTimerHotKey().isEmpty()) {
			tglbtnShowTimer.setMnemonic(-1);
		} else {
			tglbtnShowTimer.setMnemonic(settings.getShowTimerHotKey().charAt(0));
		};
		if(settings.getShowSkunkHotKey().isEmpty()) {
			tglbtnEnableSkunk.setMnemonic(-1);
		} else {
			tglbtnEnableSkunk.setMnemonic(settings.getShowSkunkHotKey().charAt(0));
		};
		if(settings.getStartStreamHotKey().isEmpty()) {
			btnStartStream.setMnemonic(-1);
		} else {
			btnStartStream.setMnemonic(settings.getStartStreamHotKey().charAt(0));
		};
	}
	////// Listeners  //////
	public void addConnectListener(ActionListener listenForBtnConnect) {
		btnConnect.addActionListener(listenForBtnConnect);
	}
	public void addDisconnectListener(ActionListener listenForBtnDisconnect) {
		btnDisconnect.addActionListener(listenForBtnDisconnect);
	}
	public void addPushListener(ActionListener listenForBtnPush) {
		btnPush.addActionListener(listenForBtnPush);
	}
	public void addPullListener(ActionListener listenForBtnPull) {
		btnPull.addActionListener(listenForBtnPull);
	}
	public void addShowScoresListener(ActionListener listenForBtnShowScores) {
		tglbtnShowScores.addActionListener(listenForBtnShowScores);
	}
	public void addShowTimerListener(ActionListener listenForBtnShowTimer) {
		tglbtnShowTimer.addActionListener(listenForBtnShowTimer);
	}
	public void addEnableSkunkListener(ActionListener listenForBtnEnableSkunk) {
		tglbtnEnableSkunk.addActionListener(listenForBtnEnableSkunk);
	}
	public void addStartStreamListener(ActionListener listenForBtnStartStream) {
		btnStartStream.addActionListener(listenForBtnStartStream);
	}

	////// Utility Methods //////
	
	public void setShowScores(boolean show) {
		tglbtnShowScores.setSelected(show);
		if (show) {
			tglbtnShowScores.setText(Messages.getString("OBSPanel.HideScores", settings.getGameType())); //$NON-NLS-1$
		} else {
			tglbtnShowScores.setText(Messages.getString("OBSPanel.ShowScores", settings.getGameType())); //$NON-NLS-1$
		}
	}
	public void setEnableSkunk(boolean enableSkunkFlag) {
		tglbtnEnableSkunk.setSelected(enableSkunkFlag);
		if (enableSkunkFlag) {
			tglbtnEnableSkunk.setText(Messages.getString("OBSPanel.DisableSkunk", settings.getGameType())); //$NON-NLS-1$
		} else {
			tglbtnEnableSkunk.setText(Messages.getString("OBSPanel.EnableSkunk", settings.getGameType())); //$NON-NLS-1$
		}
	}
	public void setShowTimer(boolean showTimerFlag) {
		tglbtnShowTimer.setSelected(showTimerFlag);
		if (showTimerFlag) {
			tglbtnShowTimer.setText(Messages.getString("OBSPanel.HideTimer", settings.getGameType())); //$NON-NLS-1$
		} else {
			tglbtnShowTimer.setText(Messages.getString("OBSPanel.ShowTimer", settings.getGameType())); //$NON-NLS-1$
		}
	}
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
		return Messages.getString("OBSPanel.OBSPanel", settings.getGameType()); //$NON-NLS-1$
	}
}
