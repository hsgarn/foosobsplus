/**
Copyright 2022-2024 Hugh Garner
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

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class AutoScoreConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblConfig;

	private JTextArea txtConfig;
	
	private JButton btnValidateConfig;
	private JButton btnWriteConfig;
	private JButton btnReadConfig;
	private JButton btnResetConfig;
	private JButton btnClearConfig;

	public AutoScoreConfigPanel() throws IOException {

		setLayout(new MigLayout());
		
		lblConfig = new JLabel(Messages.getString("AutoScoreConfigPanel.Config")); //$NON-NLS-1$
		add(lblConfig, "cell 0 0,alignx leading"); //$NON-NLS-1$
		
		txtConfig = new JTextArea(10,20);
		add(txtConfig, "cell 0 1 2 1, growx"); //$NON-NLS-1$
		
		btnReadConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Read")); //$NON-NLS-1$
		add(btnReadConfig, "cell 0 2 1 1");
		
		btnClearConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Clear")); //$NON-NLS-1$
		add(btnClearConfig, "cell 1 2 1 1");
		
		btnValidateConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Validate")); //$NON-NLS-1$
		add(btnValidateConfig, "cell 0 3 2 1, growx");
		
		btnWriteConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Write")); //$NON-NLS-1$
		add(btnWriteConfig, "cell 0 4 1 1"); //$NON-NLS-1$
		
		btnResetConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Reset")); //$NON-NLS-1$
		add(btnResetConfig, "cell 1 4 1 1, growx"); //$NON-NLS-1$
		
	}
	public void clearConfigTextArea() {
		txtConfig.setText("");
	}
	public void appendConfigTextArea(String line) {
		txtConfig.append(line);
	}
	public String getConfigTextArea() {
		String text = txtConfig.getText();
		return text;
	}
	////// Listeners \\\\\\
	public void addReadConfigListener(ActionListener listenForBtnReadConfig) {
		btnReadConfig.addActionListener(listenForBtnReadConfig);
	}
	public void addWriteConfigListener(ActionListener listenForBtnWriteConfig) {
		btnWriteConfig.addActionListener(listenForBtnWriteConfig);
	}
	public void addValidateConfigListener(ActionListener listenForBtnValidateConfig) {
		btnValidateConfig.addActionListener(listenForBtnValidateConfig);
	}
	public void addResetConfigListener(ActionListener listenForBtnResetConfig) {
		btnResetConfig.addActionListener(listenForBtnResetConfig);
	}
	public void addClearConfigListener(ActionListener listenForBtnClearConfig) {
		btnClearConfig.addActionListener(listenForBtnClearConfig);
	}
}
