/**
Copyright © 2022-2024 Hugh Garner
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class OBSPanel extends JPanel {
	private JButton btnConnect;
	private JButton btnDisconnect;
	private JButton btnPull;
	private JButton btnPush;
	private JCheckBox ckbxShowScores;
	private JCheckBox ckbxShowTimer;
	private JCheckBox ckbxEnableSkunk;
	private JToggleButton tglbtnStartStream;
	private JCheckBox ckbxShowCutthroat;
	private JLabel lblStreamTime;
	private Border innerBorder;
	public OBSPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 225;
		setPreferredSize(dim);
		setName(buildTitle());
		boolean enableSkunkInitialState = false;
		if (Settings.getControlParameter("ShowSkunk").equals("1")) {
			enableSkunkInitialState = true;
		}
		btnConnect = new JButton(Messages.getString("OBSPanel.Connect", Settings.getGameType())); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("OBSPanel.Disconnect", Settings.getGameType())); //$NON-NLS-1$
		btnPull = new JButton(Messages.getString("OBSPanel.Pull", Settings.getGameType())); //$NON-NLS-1$
		btnPush = new JButton(Messages.getString("OBSPanel.Push", Settings.getGameType())); //$NON-NLS-1$
		ckbxShowScores = new JCheckBox(Messages.getString("OBSPanel.ShowScores", Settings.getGameType())); //$NON-NLS-1$
		ckbxShowTimer = new JCheckBox(Messages.getString("OBSPanel.ShowTimer", Settings.getGameType())); //$NON-NLS-1$
		ckbxEnableSkunk = new JCheckBox(Messages.getString("OBSPanel.EnableSkunk", Settings.getGameType())); //$NON-NLS-1$
		ckbxEnableSkunk.setSelected(enableSkunkInitialState);
		tglbtnStartStream = new JToggleButton(Messages.getString("OBSPanel.StartStreamTimer",Settings.getGameType())); //$NON-NLS-1$
		ckbxShowCutthroat = new JCheckBox(Messages.getString("OBSPanel.ShowCutthroat",Settings.getGameType())); //$NON-NLS-1$
		lblStreamTime = new JLabel("00:00:00"); //$NON-NLS-1$
		lblStreamTime.setOpaque(true);
		lblStreamTime.setBackground(Color.ORANGE);
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	public void layoutComponents() {
		setLayout(new MigLayout("fillx")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		add(btnConnect,"");
		add(btnDisconnect, "wrap");
		add(btnPush, "");
		add(btnPull, "wrap");
		add(tglbtnStartStream, "");
		add(lblStreamTime, "wrap");
		add(ckbxShowScores, "");
		add(ckbxEnableSkunk, "wrap");
		add(ckbxShowTimer, "");
		add(ckbxShowCutthroat, "wrap");
	}
	private void setMnemonics() {
		if(Settings.getHotKeyParameter("Connect").isEmpty()) {
			btnConnect.setMnemonic(-1);
		} else {
			btnConnect.setMnemonic(Settings.getHotKeyParameter("Connect").charAt(0));
		};
		if(Settings.getHotKeyParameter("Disconnect").isEmpty()) {
			btnDisconnect.setMnemonic(-1);
		} else {
			btnDisconnect.setMnemonic(Settings.getHotKeyParameter("Disconnect").charAt(0));
		};
		if(Settings.getHotKeyParameter("Push").isEmpty()) {
			btnPush.setMnemonic(-1);
		} else {
			btnPush.setMnemonic(Settings.getHotKeyParameter("Push").charAt(0));
		};
		if(Settings.getHotKeyParameter("Pull").isEmpty()) {
			btnPull.setMnemonic(-1);
		} else {
			btnPull.setMnemonic(Settings.getHotKeyParameter("Pull").charAt(0));
		};
		if(Settings.getHotKeyParameter("ShowScores").isEmpty()) {
			ckbxShowScores.setMnemonic(-1);
		} else {
			ckbxShowScores.setMnemonic(Settings.getHotKeyParameter("ShowScores").charAt(0));
		};
		if(Settings.getHotKeyParameter("ShowTimer").isEmpty()) {
			ckbxShowTimer.setMnemonic(-1);
		} else {
			ckbxShowTimer.setMnemonic(Settings.getHotKeyParameter("ShowTimer").charAt(0));
		};
		if(Settings.getHotKeyParameter("ShowSkunk").isEmpty()) {
			ckbxEnableSkunk.setMnemonic(-1);
		} else {
			ckbxEnableSkunk.setMnemonic(Settings.getHotKeyParameter("ShowSkunk").charAt(0));
		};
		if(Settings.getHotKeyParameter("StartStream").isEmpty()) {
			tglbtnStartStream.setMnemonic(-1);
		} else {
			tglbtnStartStream.setMnemonic(Settings.getHotKeyParameter("StartStream").charAt(0));
		};
		if(Settings.getHotKeyParameter("ShowCutthroat").isEmpty()) {
			ckbxShowCutthroat.setMnemonic(-1);
		} else {
			ckbxShowCutthroat.setMnemonic(Settings.getHotKeyParameter("ShowCutthroat").charAt(0));
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
		ckbxShowScores.addActionListener(listenForBtnShowScores);
	}
	public void addShowTimerListener(ActionListener listenForBtnShowTimer) {
		ckbxShowTimer.addActionListener(listenForBtnShowTimer);
	}
	public void addEnableSkunkListener(ActionListener listenForBtnEnableSkunk) {
		ckbxEnableSkunk.addActionListener(listenForBtnEnableSkunk);
	}
	public void addStartStreamListener(ActionListener listenForBtnStartStream) {
		tglbtnStartStream.addActionListener(listenForBtnStartStream);
	}
	public void addShowCutthroatListener(ActionListener listenForCkbxShowCutthroat) {
		ckbxShowCutthroat.addActionListener(listenForCkbxShowCutthroat);
	}
	////// Utility Methods //////
	public void setShowScores(boolean show) {
		ckbxShowScores.setSelected(show);
	}
	public void setEnableSkunk(boolean enableSkunkFlag) {
		ckbxEnableSkunk.setSelected(enableSkunkFlag);
	}
	public void setShowTimer(boolean showTimerFlag) {
		ckbxShowTimer.setSelected(showTimerFlag);
	}
	public void setStartStream(boolean startStream) {
		tglbtnStartStream.setSelected(startStream);
		if (startStream) {
			tglbtnStartStream.setText(Messages.getString("OBSPanel.StopStreamTimer", Settings.getGameType())); //$NON-NLS-1$
		} else {
			tglbtnStartStream.setText(Messages.getString("OBSPanel.StartStreamTimer", Settings.getGameType())); //$NON-NLS-1$
		}
	}
	public void setShowCutthroat(boolean showCutthroat) {
		ckbxShowCutthroat.setSelected(showCutthroat);
	}
	public void updateTimerDisplay(String timeElapsed) {
		lblStreamTime.setText(timeElapsed);
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
		return Messages.getString("OBSPanel.OBSPanel", Settings.getGameType()); //$NON-NLS-1$
	}
}
