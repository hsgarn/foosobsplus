/**
Copyright © 2022-2026 Hugh Garner
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
	private final JButton btnConnect;
	private final JButton btnDisconnect;
	private final JButton btnPull;
	private final JButton btnPush;
	private final JCheckBox ckbxShowScores;
	private final JCheckBox ckbxShowTimer;
	private final JCheckBox ckbxEnableSkunk;
	private final JToggleButton tglbtnStartStream;
	private final JCheckBox ckbxShowCutthroat;
	private final JLabel lblStreamTime;
	private final Border innerBorder;
	private static final String ON = "1"; //$NON-NLS-1$
	public OBSPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 225;
		setPreferredSize(dim);
		setName(buildTitle());
		boolean enableSkunkInitialState = false;
		if (Settings.getControlParameter("ShowSkunk").equals(ON)) { //$NON-NLS-1$
			enableSkunkInitialState = true;
		}
		btnConnect = new JButton(Messages.getString("OBSPanel.Connect")); //$NON-NLS-1$
		btnDisconnect = new JButton(Messages.getString("OBSPanel.Disconnect")); //$NON-NLS-1$
		btnPull = new JButton(Messages.getString("OBSPanel.Pull")); //$NON-NLS-1$
		btnPush = new JButton(Messages.getString("OBSPanel.Push")); //$NON-NLS-1$
		ckbxShowScores = new JCheckBox(Messages.getString("OBSPanel.ShowScores")); //$NON-NLS-1$
		ckbxShowTimer = new JCheckBox(Messages.getString("OBSPanel.ShowTimer")); //$NON-NLS-1$
		ckbxEnableSkunk = new JCheckBox(Messages.getString("OBSPanel.EnableSkunk")); //$NON-NLS-1$
		ckbxEnableSkunk.setSelected(enableSkunkInitialState);
		tglbtnStartStream = new JToggleButton(Messages.getString("OBSPanel.StartStreamTimer")); //$NON-NLS-1$
		ckbxShowCutthroat = new JCheckBox(Messages.getString("OBSPanel.ShowCutthroat")); //$NON-NLS-1$
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
	private void layoutComponents() {
		setLayout(new MigLayout("fillx")); //$NON-NLS-1$
		add(btnConnect,""); //$NON-NLS-1$
		add(btnDisconnect, "wrap"); //$NON-NLS-1$
		add(btnPush, ""); //$NON-NLS-1$
		add(btnPull, "wrap"); //$NON-NLS-1$
		add(tglbtnStartStream, ""); //$NON-NLS-1$
		add(lblStreamTime, "wrap"); //$NON-NLS-1$
		add(ckbxShowScores, ""); //$NON-NLS-1$
		add(ckbxEnableSkunk, "wrap"); //$NON-NLS-1$
		add(ckbxShowTimer, ""); //$NON-NLS-1$
		add(ckbxShowCutthroat, "wrap"); //$NON-NLS-1$
	}
	private void setMnemonics() {
		if(Settings.getHotKeyParameter("Connect").isEmpty()) { //$NON-NLS-1$
			btnConnect.setMnemonic(-1);
		} else {
			btnConnect.setMnemonic(Settings.getHotKeyParameter("Connect").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("Disconnect").isEmpty()) { //$NON-NLS-1$
			btnDisconnect.setMnemonic(-1);
		} else {
			btnDisconnect.setMnemonic(Settings.getHotKeyParameter("Disconnect").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("Push").isEmpty()) { //$NON-NLS-1$
			btnPush.setMnemonic(-1);
		} else {
			btnPush.setMnemonic(Settings.getHotKeyParameter("Push").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("Pull").isEmpty()) { //$NON-NLS-1$
			btnPull.setMnemonic(-1);
		} else {
			btnPull.setMnemonic(Settings.getHotKeyParameter("Pull").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("ShowScores").isEmpty()) { //$NON-NLS-1$
			ckbxShowScores.setMnemonic(-1);
		} else {
			ckbxShowScores.setMnemonic(Settings.getHotKeyParameter("ShowScores").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("ShowTimer").isEmpty()) { //$NON-NLS-1$
			ckbxShowTimer.setMnemonic(-1);
		} else {
			ckbxShowTimer.setMnemonic(Settings.getHotKeyParameter("ShowTimer").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("ShowSkunk").isEmpty()) { //$NON-NLS-1$
			ckbxEnableSkunk.setMnemonic(-1);
		} else {
			ckbxEnableSkunk.setMnemonic(Settings.getHotKeyParameter("ShowSkunk").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("StartStream").isEmpty()) { //$NON-NLS-1$
			tglbtnStartStream.setMnemonic(-1);
		} else {
			tglbtnStartStream.setMnemonic(Settings.getHotKeyParameter("StartStream").charAt(0)); //$NON-NLS-1$
		}
		if(Settings.getHotKeyParameter("ShowCutthroat").isEmpty()) { //$NON-NLS-1$
			ckbxShowCutthroat.setMnemonic(-1);
		} else {
			ckbxShowCutthroat.setMnemonic(Settings.getHotKeyParameter("ShowCutthroat").charAt(0)); //$NON-NLS-1$
		}
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
			tglbtnStartStream.setText(Messages.getString("OBSPanel.StopStreamTimer")); //$NON-NLS-1$
		} else {
			tglbtnStartStream.setText(Messages.getString("OBSPanel.StartStreamTimer")); //$NON-NLS-1$
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
		return Messages.getString("OBSPanel.OBSPanel"); //$NON-NLS-1$
	}
}
