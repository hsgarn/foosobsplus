/**
Copyright © 2020-2024 Hugh Garner
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

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class ParametersPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtPointsToWin;
	private JTextField txtMaxWin;
	private JTextField txtGamesToWin;
	private JTextField txtWinBy;
	private JCheckBox chckbxAutoIncrementGame;
	private JCheckBox chckbxAnnounceWinner;
	private JCheckBox chckbxAnnounceMeatball;
	private JTextField txtMaxTimeOuts;
	private JTextField txtWinnerPrefix;
	private JTextField txtWinnerSuffix;
	private JTextField txtShotTime;
	private JTextField txtPassTime;
	private JTextField txtTimeOutTime;
	private JTextField txtGameTime;
	private JTextField txtRecallTime;
	private JTextField txtMeatball;
	private JCheckBox chckbxShowTimeOutsUsed;
	private JCheckBox chckbxAutoCapNames;
	private JCheckBox chckbxWinByFinalOnly;
	private JCheckBox chckbxEnableShowSkunk;
	private JCheckBox chckbxCutThroatMode;
	private JCheckBox chckbxRackMode;
	private JTextField txtTeam1LastScored;
	private JTextField txtTeam2LastScored;
	private JTextField txtClearLastScored;
	private JTextField txtSide1Color;
	private JTextField txtSide2Color;
	private JTextField txtBallsInRack;
	private JButton btnApply;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnRestoreDefaults;
	private final Integer maxGamesToWin = 6;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final String OFF = "0"; //$NON-NLS-1$
	private static Logger logger = LoggerFactory.getLogger(ParametersPanel.class);
	public ParametersPanel() throws IOException {
		setLayout(new MigLayout("", "[119.00][50.00:87.00,grow,left][78.00,grow][grow][]", "[][][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblParameter = new JLabel(Messages.getString("ParametersPanel.Parameter")); //$NON-NLS-1$
		lblParameter.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblParameter, "cell 0 0,alignx right"); //$NON-NLS-1$
		JLabel lblValue = new JLabel(Messages.getString("ParametersPanel.Value")); //$NON-NLS-1$
		lblValue.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblValue, "cell 1 0,alignx left"); //$NON-NLS-1$
		JLabel label = new JLabel(Messages.getString("ParametersPanel.Parameter")); //$NON-NLS-1$
		label.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(label, "cell 2 0,alignx right"); //$NON-NLS-1$
		JLabel label_1 = new JLabel(Messages.getString("ParametersPanel.Value")); //$NON-NLS-1$
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(label_1, "cell 3 0"); //$NON-NLS-1$
		JLabel lblPointsToWin = new JLabel(Messages.getString("ParametersPanel.PointsToWin")); //$NON-NLS-1$
		add(lblPointsToWin, "flowx,cell 0 1,alignx right"); //$NON-NLS-1$
		txtPointsToWin = new JTextField();
		txtPointsToWin.setText(Settings.getControlParameter("PointsToWin")); //$NON-NLS-1$
		txtPointsToWin.setColumns(10);
		add(txtPointsToWin, "cell 1 1,alignx left"); //$NON-NLS-1$
		JLabel lblShotTime = new JLabel(Messages.getString("ParametersPanel.ShotTime")); //$NON-NLS-1$
		add(lblShotTime, "cell 2 1,alignx trailing"); //$NON-NLS-1$
		txtShotTime = new JTextField();
		txtShotTime.setText(Settings.getControlParameter("ShotTime")); //$NON-NLS-1$
		txtShotTime.setColumns(10);
		add(txtShotTime, "cell 3 1,growx"); //$NON-NLS-1$
		JLabel lblMaxWin = new JLabel(Messages.getString("ParametersPanel.MaxWin")); //$NON-NLS-1$
		add(lblMaxWin, "flowx,cell 0 2,alignx right"); //$NON-NLS-1$
		txtMaxWin = new JTextField();
		txtMaxWin.setText(Settings.getControlParameter("MaxWin")); //$NON-NLS-1$
		txtMaxWin.setColumns(10);
		add(txtMaxWin, "cell 1 2,alignx left"); //$NON-NLS-1$
		JLabel lblPassTime = new JLabel(Messages.getString("ParametersPanel.PassTime")); //$NON-NLS-1$
		add(lblPassTime, "cell 2 2,alignx trailing"); //$NON-NLS-1$
		txtPassTime = new JTextField();
		txtPassTime.setText(Settings.getControlParameter("PassTime")); //$NON-NLS-1$
		txtPassTime.setColumns(10);
		add(txtPassTime, "cell 3 2,growx"); //$NON-NLS-1$
		JLabel lblWinBy = new JLabel(Messages.getString("ParametersPanel.WinBy")); //$NON-NLS-1$
		add(lblWinBy, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		txtWinBy = new JTextField();
		txtWinBy.setText(Settings.getControlParameter("WinBy")); //$NON-NLS-1$
		txtWinBy.setColumns(10);
		add(txtWinBy, "cell 1 3,alignx left"); //$NON-NLS-1$
		JLabel lblTimeOutTime = new JLabel(Messages.getString("ParametersPanel.TimeOutTime")); //$NON-NLS-1$
		add(lblTimeOutTime, "cell 2 3,alignx trailing"); //$NON-NLS-1$
		txtTimeOutTime = new JTextField();
		txtTimeOutTime.setText(Settings.getControlParameter("TimeOutTime")); //$NON-NLS-1$
		txtTimeOutTime.setColumns(10);
		add(txtTimeOutTime, "cell 3 3,growx"); //$NON-NLS-1$
		JLabel lblGamesToWin = new JLabel(Messages.getString("ParametersPanel.GamesToWin")); //$NON-NLS-1$
		add(lblGamesToWin, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		txtGamesToWin = new JTextField();
		txtGamesToWin.setText(Settings.getControlParameter("GamesToWin")); //$NON-NLS-1$
		txtGamesToWin.setColumns(10);
		add(txtGamesToWin, "cell 1 4,alignx left"); //$NON-NLS-1$
		JLabel lblGameTime = new JLabel(Messages.getString("ParametersPanel.GameTime")); //$NON-NLS-1$
		add(lblGameTime, "cell 2 4,alignx trailing"); //$NON-NLS-1$
		txtGameTime = new JTextField();
		txtGameTime.setText(Settings.getControlParameter("GameTime")); //$NON-NLS-1$
		txtGameTime.setColumns(10);
		add(txtGameTime, "cell 3 4,growx"); //$NON-NLS-1$
		JLabel lblMaxTimeOuts = new JLabel(Messages.getString("ParametersPanel.MaxTimeOuts")); //$NON-NLS-1$
		add(lblMaxTimeOuts, "flowx,cell 0 5,alignx right"); //$NON-NLS-1$
		txtMaxTimeOuts = new JTextField();
		txtMaxTimeOuts.setText(Settings.getControlParameter("MaxTimeOuts")); //$NON-NLS-1$
		txtMaxTimeOuts.setColumns(10);
		add(txtMaxTimeOuts, "cell 1 5"); //$NON-NLS-1$
		JLabel lblRecallTime = new JLabel(Messages.getString("ParametersPanel.RecallTime")); //$NON-NLS-1$
		add(lblRecallTime, "cell 2 5,alignx trailing"); //$NON-NLS-1$
		txtRecallTime = new JTextField();
		txtRecallTime.setText(Settings.getControlParameter("RecallTime")); //$NON-NLS-1$
		txtRecallTime.setColumns(10);
		add(txtRecallTime, "cell 3 5,growx,aligny top"); //$NON-NLS-1$
		JLabel lblTeam1LastScored = new JLabel(Messages.getString("ParametersPanel.Team1LastScored")); //$NON-NLS-1$
		add(lblTeam1LastScored, "cell 2 6,alignx trailing"); //$NON-NLS-1$
		txtTeam1LastScored = new JTextField();
		txtTeam1LastScored.setText(Settings.getControlParameter("Team1LastScored")); //$NON-NLS-1$
		txtTeam1LastScored.setColumns(10);
		add(txtTeam1LastScored, "cell 3 6,growx"); //$NON-NLS-1$
		JLabel lblTeam2LastScored = new JLabel(Messages.getString("ParametersPanel.Team2LastScored")); //$NON-NLS-1$
		add(lblTeam2LastScored, "cell 2 7,alignx trailing"); //$NON-NLS-1$
		txtTeam2LastScored = new JTextField();
		txtTeam2LastScored.setText(Settings.getControlParameter("Team2LastScored")); //$NON-NLS-1$
		txtTeam2LastScored.setColumns(10);
		add(txtTeam2LastScored, "cell 3 7,growx"); //$NON-NLS-1$
		JLabel lblClearLastScored = new JLabel(Messages.getString("ParametersPanel.ClearLastScored")); //$NON-NLS-1$
		add(lblClearLastScored, "cell 2 8,alignx trailing"); //$NON-NLS-1$
		txtClearLastScored = new JTextField();
		txtClearLastScored.setText(Settings.getControlParameter("ClearLastScored")); //$NON-NLS-1$
		txtClearLastScored.setColumns(10);
		add(txtClearLastScored, "cell 3 8,growx"); //$NON-NLS-1$
		JLabel lblSide1Color = new JLabel(Messages.getString("ParametersPanel.Team1Color")); //$NON-NLS-1$
		add(lblSide1Color, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		txtSide1Color = new JTextField();
		txtSide1Color.setText(Settings.getControlParameter("Side1Color")); //$NON-NLS-1$
		txtSide1Color.setColumns(10);
		add(txtSide1Color, "cell 1 6,growx,aligny top"); //$NON-NLS-1$
		JLabel lblSide2Color = new JLabel(Messages.getString("ParametersPanel.Team2Color")); //$NON-NLS-1$
		add(lblSide2Color, "cell 0 7,alignx trailing"); //$NON-NLS-1$
		txtSide2Color = new JTextField();
		txtSide2Color.setText(Settings.getControlParameter("Side2Color")); //$NON-NLS-1$
		txtSide2Color.setColumns(10);
		add(txtSide2Color, "cell 1 7,growx"); //$NON-NLS-1$
		JLabel lblBallsInRack = new JLabel(Messages.getString("ParametersPanel.BallsInRack")); //$NON-NLS-1$
		add(lblBallsInRack, "cell 0 8,alignx trailing"); //$NON-NLS-1$
		txtBallsInRack = new JTextField();
		txtBallsInRack.setText(Settings.getControlParameter("BallsInRack")); //$NON-NLS-1$
		txtBallsInRack.setColumns(10);
		add(txtBallsInRack, "cell 1 8,growx"); //$NON-NLS-1$
		JLabel lblRackMode = new JLabel(Messages.getString("ParametersPanel.RackMode")); //$NON-NLS-1$
		add(lblRackMode, "cell 0 9,alignx right"); //$NON-NLS-1$
		chckbxRackMode = new JCheckBox(""); //$NON-NLS-1$
		chckbxRackMode.setSelected(Settings.getControlParameter("RackMode").equals(ON)); //$NON-NLS-1$
		add(chckbxRackMode, "cell 1 9"); //$NON-NLS-1$
		JLabel lblAutoIncrementGame = new JLabel(Messages.getString("ParametersPanel.AutoIncrementGame")); //$NON-NLS-1$
		add(lblAutoIncrementGame, "cell 0 12,alignx right"); //$NON-NLS-1$
		chckbxAutoIncrementGame = new JCheckBox(""); //$NON-NLS-1$
		chckbxAutoIncrementGame.setSelected(Settings.getControlParameter("AutoIncrementGame").equals(ON)); //$NON-NLS-1$
		add(chckbxAutoIncrementGame, "cell 1 12"); //$NON-NLS-1$
		JLabel lblWinnerPrefix = new JLabel(Messages.getString("ParametersPanel.WinnerPrefix")); //$NON-NLS-1$
		add(lblWinnerPrefix, "cell 2 11,alignx center"); //$NON-NLS-1$
		JLabel lblWinnerSuffix = new JLabel(Messages.getString("ParametersPanel.WinnerSuffix")); //$NON-NLS-1$
		add(lblWinnerSuffix, "cell 3 11,alignx center"); //$NON-NLS-1$
		JLabel lblAnnounceWinner = new JLabel(Messages.getString("ParametersPanel.AnnounceWinner")); //$NON-NLS-1$
		add(lblAnnounceWinner, "cell 0 13,alignx right"); //$NON-NLS-1$
		chckbxAnnounceWinner = new JCheckBox(""); //$NON-NLS-1$
		chckbxAnnounceWinner.setSelected(Settings.getControlParameter("AnnounceWinner").equals(ON)); //$NON-NLS-1$
		add(chckbxAnnounceWinner, "cell 1 13,alignx left"); //$NON-NLS-1$
		txtWinnerPrefix = new JTextField();
		txtWinnerPrefix.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWinnerPrefix.setText(Settings.getControlParameter("WinnerPrefix")); //$NON-NLS-1$
		txtWinnerPrefix.setColumns(10);
		add(txtWinnerPrefix, "cell 2 12,alignx center"); //$NON-NLS-1$
		txtWinnerSuffix = new JTextField();
		txtWinnerSuffix.setText(Settings.getControlParameter("WinnerSuffix")); //$NON-NLS-1$
		txtWinnerSuffix.setColumns(10);
		add(txtWinnerSuffix, "cell 3 12,growx,aligny top"); //$NON-NLS-1$
		JLabel lblAnnounceMeatball = new JLabel(Messages.getString("ParametersPanel.AnnounceMeatball")); //$NON-NLS-1$
		add(lblAnnounceMeatball, "cell 0 14,alignx right"); //$NON-NLS-1$
		chckbxAnnounceMeatball = new JCheckBox(""); //$NON-NLS-1$
		chckbxAnnounceMeatball.setSelected(Settings.getControlParameter("AnnounceMeatball").equals(ON)); //$NON-NLS-1$
		add(chckbxAnnounceMeatball, "cell 1 14,alignx left"); //$NON-NLS-1$
		txtMeatball = new JTextField();
		txtMeatball.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeatball.setText(Settings.getControlParameter("Meatball")); //$NON-NLS-1$
		txtMeatball.setColumns(10);
		add(txtMeatball, "cell 2 13,alignx center"); //$NON-NLS-1$
		JLabel lblShowTimeOutsUsed = new JLabel(Messages.getString("ParametersPanel.ShowTimeOutsUsed")); //$NON-NLS-1$
		add(lblShowTimeOutsUsed, "cell 0 15,alignx right"); //$NON-NLS-1$
		chckbxShowTimeOutsUsed = new JCheckBox(""); //$NON-NLS-1$
		chckbxShowTimeOutsUsed.setSelected(Settings.getControlParameter("ShowTimeOutsUsed").equals(ON)); //$NON-NLS-1$
		add(chckbxShowTimeOutsUsed, "cell 1 15"); //$NON-NLS-1$
		JLabel lblAutoCapitalizeTeam = new JLabel(Messages.getString("ParametersPanel.AutoCapitalizeNames")); //$NON-NLS-1$
		add(lblAutoCapitalizeTeam, "cell 0 16,alignx right"); //$NON-NLS-1$
		chckbxAutoCapNames = new JCheckBox(""); //$NON-NLS-1$
		chckbxAutoCapNames.setSelected(Settings.getControlParameter("AutoCapNames").equals(ON)); //$NON-NLS-1$
		add(chckbxAutoCapNames, "cell 1 16"); //$NON-NLS-1$
		JLabel lblWinByFinalOnly = new JLabel(Messages.getString("ParametersPanel.WinByFinalGameOnly")); //$NON-NLS-1$
		add(lblWinByFinalOnly, "cell 0 17,alignx right"); //$NON-NLS-1$
		chckbxWinByFinalOnly = new JCheckBox(""); //$NON-NLS-1$
		chckbxWinByFinalOnly.setSelected(Settings.getControlParameter("WinByFinalOnly").equals(ON)); //$NON-NLS-1$
		add(chckbxWinByFinalOnly, "cell 1 17"); //$NON-NLS-1$
		JLabel lblEnableShowSkunk = new JLabel(Messages.getString("ParametersPanel.EnableShowSkunk")); //$NON-NLS-1$
		add(lblEnableShowSkunk, "cell 0 18,alignx right"); //$NON-NLS-1$
		chckbxEnableShowSkunk = new JCheckBox(""); //$NON-NLS-1$
		chckbxEnableShowSkunk.setSelected(Settings.getControlParameter("ShowSkunk").equals(ON)); //$NON-NLS-1$
		add(chckbxEnableShowSkunk, "cell 1 18"); //$NON-NLS-1$
		JLabel lblCutThroatMode = new JLabel(Messages.getString("ParametersPanel.CutThroatMode")); //$NON-NLS-1$
		add(lblCutThroatMode, "cell 0 19, alignx right"); //$NON-NLS-1$
		chckbxCutThroatMode = new JCheckBox(""); //$NON-NLS-1$
		chckbxCutThroatMode.setSelected(Settings.getControlParameter("CutThroatMode").equals(ON)); //$NON-NLS-1$
		add(chckbxCutThroatMode, "cell 1 19"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 0 21,alignx center"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 1 21,alignx center"); //$NON-NLS-1$
		btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revertChanges();
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 2 21,alignx center"); //$NON-NLS-1$
		btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restoreDefaults();
			}
		});
		btnRestoreDefaults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnRestoreDefaults, "cell 3 21,alignx center"); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		txtPointsToWin.setText(Settings.getDefaultParameter("PointsToWin")); //$NON-NLS-1$
		txtShotTime.setText(Settings.getDefaultParameter("ShotTime")); //$NON-NLS-1$
		txtMaxWin.setText(Settings.getDefaultParameter("MaxWin")); //$NON-NLS-1$
		txtPassTime.setText(Settings.getDefaultParameter("PassTime")); //$NON-NLS-1$
		txtWinBy.setText(Settings.getDefaultParameter("WinBy"));; //$NON-NLS-1$
		txtTimeOutTime.setText(Settings.getDefaultParameter("TimeOutTime")); //$NON-NLS-1$
		int checkValue = Integer.parseInt(Settings.getDefaultParameter("GamesToWin")); //$NON-NLS-1$
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Settings.getDefaultParameter("GameTime")); //$NON-NLS-1$
		txtMaxTimeOuts.setText(Settings.getDefaultParameter("MaxTimeOuts")); //$NON-NLS-1$
		txtRecallTime.setText(Settings.getDefaultParameter("RecallTime")); //$NON-NLS-1$
		chckbxRackMode.setSelected(Settings.getDefaultParameter("RackMode").equals(ON)); //$NON-NLS-1$
		chckbxAutoIncrementGame.setSelected(Settings.getDefaultParameter("AutoIncrementGame").equals(ON)); //$NON-NLS-1$
		chckbxAnnounceWinner.setSelected(Settings.getDefaultParameter("AnnounceWinner").equals(ON)); //$NON-NLS-1$
		chckbxAnnounceMeatball.setSelected(Settings.getDefaultParameter("AnnounceMeatball").equals(ON)); //$NON-NLS-1$
		chckbxShowTimeOutsUsed.setSelected(Settings.getDefaultParameter("ShowTimeOutsUsed").equals(ON)); //$NON-NLS-1$
		chckbxAutoCapNames.setSelected(Settings.getDefaultParameter("AutoCapNames").equals(ON)); //$NON-NLS-1$
		chckbxWinByFinalOnly.setSelected(Settings.getDefaultParameter("WinByFinalOnly").equals(ON)); //$NON-NLS-1$
		chckbxEnableShowSkunk.setSelected(Settings.getDefaultParameter("ShowSkunk").equals(ON)); //$NON-NLS-1$
		chckbxCutThroatMode.setSelected(Settings.getDefaultParameter("CutThroatMode").equals(ON)); //$NON-NLS-1$
		txtWinnerPrefix.setText(Settings.getDefaultParameter("WinnerPrefix")); //$NON-NLS-1$
		txtWinnerSuffix.setText(Settings.getDefaultParameter("WinnerSuffix")); //$NON-NLS-1$
		txtMeatball.setText(Settings.getDefaultParameter("Meatball")); //$NON-NLS-1$
		txtTeam1LastScored.setText(Settings.getDefaultParameter("Team1LastScored")); //$NON-NLS-1$
		txtTeam2LastScored.setText(Settings.getDefaultParameter("Team2LastScored")); //$NON-NLS-1$
		txtClearLastScored.setText(Settings.getDefaultParameter("ClearLastScored")); //$NON-NLS-1$
		txtSide1Color.setText(Settings.getDefaultParameter("Side1Color")); //$NON-NLS-1$
		txtSide2Color.setText(Settings.getDefaultParameter("Side2Color")); //$NON-NLS-1$
		txtBallsInRack.setText(Settings.getDefaultParameter("BallsInRack")); //$NON-NLS-1$
	}
	private void revertChanges() {
		txtPointsToWin.setText(Settings.getControlParameter("PointsToWin")); //$NON-NLS-1$
		txtShotTime.setText(Settings.getControlParameter("ShotTime")); //$NON-NLS-1$
		txtMaxWin.setText(Settings.getControlParameter("MaxWin")); //$NON-NLS-1$
		txtPassTime.setText(Settings.getControlParameter("PassTime")); //$NON-NLS-1$
		txtWinBy.setText(Settings.getControlParameter("WinBy"));; //$NON-NLS-1$
		txtTimeOutTime.setText(Settings.getControlParameter("TimeOutTime")); //$NON-NLS-1$
		int checkValue = Integer.parseInt(Settings.getControlParameter("GamesToWin")); //$NON-NLS-1$
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Settings.getControlParameter("GameTime")); //$NON-NLS-1$
		txtMaxTimeOuts.setText(Settings.getControlParameter("MaxTimeOuts")); //$NON-NLS-1$
		txtRecallTime.setText(Settings.getControlParameter("RecallTime")); //$NON-NLS-1$
		chckbxRackMode.setSelected(Settings.getControlParameter("RackMode").equals(ON)); //$NON-NLS-1$
		chckbxAutoIncrementGame.setSelected(Settings.getControlParameter("AutoIncrementGame").equals(ON)); //$NON-NLS-1$
		chckbxAnnounceMeatball.setSelected(Settings.getControlParameter("AnnounceMeatball").equals(ON)); //$NON-NLS-1$
		chckbxAnnounceWinner.setSelected(Settings.getControlParameter("AnnounceWinner").equals(ON)); //$NON-NLS-1$
		chckbxShowTimeOutsUsed.setSelected(Settings.getControlParameter("ShowTimeOutsUsed").equals(ON)); //$NON-NLS-1$
		chckbxAutoCapNames.setSelected(Settings.getControlParameter("AutoCapNames").equals(ON)); //$NON-NLS-1$
		chckbxWinByFinalOnly.setSelected(Settings.getControlParameter("WinByFinalOnly").equals(ON)); //$NON-NLS-1$
		chckbxEnableShowSkunk.setSelected(Settings.getControlParameter("ShowSkunk").equals(ON)); //$NON-NLS-1$
		chckbxCutThroatMode.setSelected(Settings.getControlParameter("CutThroatMode").equals(ON)); //$NON-NLS-1$
		txtWinnerPrefix.setText(Settings.getControlParameter("WinnerPrefix")); //$NON-NLS-1$
		txtWinnerSuffix.setText(Settings.getControlParameter("WinnerSuffix")); //$NON-NLS-1$
		txtMeatball.setText(Settings.getControlParameter("Meatball")); //$NON-NLS-1$
		txtTeam1LastScored.setText(Settings.getControlParameter("Team1LastScored")); //$NON-NLS-1$
		txtTeam2LastScored.setText(Settings.getControlParameter("Team2LastScored")); //$NON-NLS-1$
		txtClearLastScored.setText(Settings.getControlParameter("ClearLastScored")); //$NON-NLS-1$
		txtSide1Color.setText(Settings.getControlParameter("Side1Color")); //$NON-NLS-1$
		txtSide2Color.setText(Settings.getControlParameter("Side2Color")); //$NON-NLS-1$
		txtBallsInRack.setText(Settings.getControlParameter("BallsInRack")); //$NON-NLS-1$
		setEnableShowSkunk(Settings.getControlParameter("ShowSkunk").equals(ON)); //$NON-NLS-1$
	}
	private void saveIntegerSetting(String parameter, String value) {
		if (isValidInteger(value)) {
			Settings.setControlParameter(parameter, value);
		}
	}
	private void saveMaxWin(String parameter, String maxWin, String pointsToWin) {
	    if (isValidInteger(maxWin)) {
	        int intMaxWin = Integer.parseInt(maxWin);
	        int intPointsToWin = Integer.parseInt(pointsToWin);
	        if (intPointsToWin > intMaxWin) {
	            Settings.setControlParameter(parameter, pointsToWin);
	            txtMaxWin.setText(pointsToWin);
	        } else {
	            Settings.setControlParameter(parameter, maxWin);
	        }
	    } else {
	    	Settings.setControlParameter(parameter, pointsToWin);
	    }
	}
	public void saveSettings(Settings settings) {
		saveIntegerSetting("PointsToWin",txtPointsToWin.getText()); //$NON-NLS-1$
		saveMaxWin("MaxWin", txtMaxWin.getText(), Settings.getControlParameter("PointsToWin")); //$NON-NLS-1$ //$NON-NLS-2$
		saveIntegerSetting("WinBy",txtWinBy.getText()); //$NON-NLS-1$
    	if (isValidInteger(txtGamesToWin.getText())) {
    		Integer checkValue = Integer.parseInt(txtGamesToWin.getText());
    		if (checkValue > maxGamesToWin) {
    			checkValue = maxGamesToWin;
    			JOptionPane.showMessageDialog(null,Messages.getString("ParametersPanel.GamesToWinExceedError") + maxGamesToWin + Messages.getString("ParametersPanel.SettingTo") + maxGamesToWin + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		}
    		txtGamesToWin.setText(Integer.toString(checkValue));
    		Settings.setControlParameter("GamesToWin",txtGamesToWin.getText()); //$NON-NLS-1$
    	}
    	saveIntegerSetting("MaxTimeOuts", txtMaxTimeOuts.getText()); //$NON-NLS-1$
   		Settings.setControlParameter("RackMode", chckbxRackMode.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("AutoIncrementGame", chckbxAutoIncrementGame.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("AnnounceWinner", chckbxAnnounceWinner.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("AnnounceMeatball", chckbxAnnounceMeatball.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("Meatball",txtMeatball.getText()); //$NON-NLS-1$
		Settings.setControlParameter("WinnerPrefix",txtWinnerPrefix.getText()); //$NON-NLS-1$
		Settings.setControlParameter("WinnerSuffix",txtWinnerSuffix.getText()); //$NON-NLS-1$
		Settings.setControlParameter("Team1LastScored",txtTeam1LastScored.getText()); //$NON-NLS-1$
		Settings.setControlParameter("Team2LastScored",txtTeam2LastScored.getText()); //$NON-NLS-1$
		Settings.setControlParameter("ClearLastScored",txtClearLastScored.getText()); //$NON-NLS-1$
		Settings.setControlParameter("Side1Color",txtSide1Color.getText()); //$NON-NLS-1$
		Settings.setControlParameter("Side2Color",txtSide2Color.getText()); //$NON-NLS-1$
		Settings.setControlParameter("BallsInRack",txtBallsInRack.getText()); //$NON-NLS-1$
    	saveIntegerSetting("ShotTime",txtShotTime.getText()); //$NON-NLS-1$
    	saveIntegerSetting("PassTime",txtPassTime.getText()); //$NON-NLS-1$
    	saveIntegerSetting("TimeOutTime",txtTimeOutTime.getText()); //$NON-NLS-1$
    	saveIntegerSetting("GameTime",txtGameTime.getText()); //$NON-NLS-1$
    	saveIntegerSetting("RecallTime",txtRecallTime.getText()); //$NON-NLS-1$
    	Settings.setControlParameter("ShowTimeOutsUsed", chckbxShowTimeOutsUsed.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("AutoCapNames", chckbxAutoCapNames.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("WinByFinalOnly", chckbxWinByFinalOnly.isSelected() ? ON : OFF); //$NON-NLS-1$
    	Settings.setControlParameter("ShowSkunk", chckbxEnableShowSkunk.isSelected() ? ON : OFF); //$NON-NLS-1$
		Settings.setControlParameter("CutThroatMode", chckbxCutThroatMode.isSelected() ? ON : OFF); //$NON-NLS-1$
		try {
			Settings.saveControlConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, ex.getMessage(), Messages.getString("Errors.ErrorSavingPropertiesFile"), 1);//$NON-NLS-1$
		}
	}
	private boolean isValidInteger(String checkString) {
    	try {
    		Integer.parseInt(checkString);
    		return true;
    	} catch (NumberFormatException e) {
    		logger.error(e.toString());
    		return false;
    	}
	}
	public void setEnableShowSkunk(boolean enableFlag) {
		chckbxEnableShowSkunk.setSelected(enableFlag);
	}
	////// Listeners \\\\\\
	public void addApplyListener(ActionListener listenforBtnApply) {
		btnApply.addActionListener(listenforBtnApply);
	}
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
	public void addEnableShowSkunkListener(ActionListener listenForChckbxEnableShowSkunk) {
		chckbxEnableShowSkunk.addActionListener(listenForChckbxEnableShowSkunk);
	}
}
