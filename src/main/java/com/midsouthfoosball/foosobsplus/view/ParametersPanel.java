/**
Copyright © 2020-2026 Hugh Garner
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

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

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
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;

import net.miginfocom.swing.MigLayout;

public class ParametersPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JTextField txtPointsToWin;
	private final JTextField txtMaxWin;
	private final JTextField txtGamesToWin;
	private final JTextField txtWinBy;
	private final JCheckBox chckbxAutoIncrementGame;
	private final JCheckBox chckbxAnnounceWinner;
	private final JCheckBox chckbxAnnounceMeatball;
	private final JTextField txtMaxTimeOuts;
	private final JTextField txtWinnerPrefix;
	private final JTextField txtWinnerSuffix;
	private final JTextField txtShotTime;
	private final JTextField txtPassTime;
	private final JTextField txtTimeOutTime;
	private final JTextField txtGameTime;
	private final JTextField txtRecallTime;
	private final JTextField txtMeatball;
	private final JCheckBox chckbxShowTimeOutsUsed;
	private final JCheckBox chckbxAutoCapNames;
	private final JCheckBox chckbxWinByFinalOnly;
	private final JCheckBox chckbxEnableShowSkunk;
	private final JCheckBox chckbxCutThroatMode;
	private final JCheckBox chckbxRackMode;
	private final JTextField txtTeam1LastScored;
	private final JTextField txtTeam2LastScored;
	private final JTextField txtClearLastScored;
	private final JTextField txtSide1Color;
	private final JTextField txtSide2Color;
	private final JTextField txtBallsInRack;
	private final JButton btnApply;
	private final JButton btnSave;
	private final JButton btnCancel;
	private final JButton btnRestoreDefaults;
	private final Integer maxGamesToWin = 6;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final String OFF = "0"; //$NON-NLS-1$
	private final Map<Component, Object> snapshot = new HashMap<>();
	private BooleanSupplier saveCallback = () -> { saveSettings(null); return true; };
	private static final Logger logger = LoggerFactory.getLogger(ParametersPanel.class);
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
		txtPointsToWin.setText(Settings.getControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN));
		txtPointsToWin.setColumns(10);
		add(txtPointsToWin, "cell 1 1,alignx left"); //$NON-NLS-1$
		JLabel lblShotTime = new JLabel(Messages.getString("ParametersPanel.ShotTime")); //$NON-NLS-1$
		add(lblShotTime, "cell 2 1,alignx trailing"); //$NON-NLS-1$
		txtShotTime = new JTextField();
		txtShotTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_SHOT_TIME));
		txtShotTime.setColumns(10);
		add(txtShotTime, "cell 3 1,growx"); //$NON-NLS-1$
		JLabel lblMaxWin = new JLabel(Messages.getString("ParametersPanel.MaxWin")); //$NON-NLS-1$
		add(lblMaxWin, "flowx,cell 0 2,alignx right"); //$NON-NLS-1$
		txtMaxWin = new JTextField();
		txtMaxWin.setText(Settings.getControlParameter(SettingsKeys.CTRL_MAX_WIN));
		txtMaxWin.setColumns(10);
		add(txtMaxWin, "cell 1 2,alignx left"); //$NON-NLS-1$
		JLabel lblPassTime = new JLabel(Messages.getString("ParametersPanel.PassTime")); //$NON-NLS-1$
		add(lblPassTime, "cell 2 2,alignx trailing"); //$NON-NLS-1$
		txtPassTime = new JTextField();
		txtPassTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_PASS_TIME));
		txtPassTime.setColumns(10);
		add(txtPassTime, "cell 3 2,growx"); //$NON-NLS-1$
		JLabel lblWinBy = new JLabel(Messages.getString("ParametersPanel.WinBy")); //$NON-NLS-1$
		add(lblWinBy, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		txtWinBy = new JTextField();
		txtWinBy.setText(Settings.getControlParameter(SettingsKeys.CTRL_WIN_BY));
		txtWinBy.setColumns(10);
		add(txtWinBy, "cell 1 3,alignx left"); //$NON-NLS-1$
		JLabel lblTimeOutTime = new JLabel(Messages.getString("ParametersPanel.TimeOutTime")); //$NON-NLS-1$
		add(lblTimeOutTime, "cell 2 3,alignx trailing"); //$NON-NLS-1$
		txtTimeOutTime = new JTextField();
		txtTimeOutTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_TIME_OUT_TIME));
		txtTimeOutTime.setColumns(10);
		add(txtTimeOutTime, "cell 3 3,growx"); //$NON-NLS-1$
		JLabel lblGamesToWin = new JLabel(Messages.getString("ParametersPanel.GamesToWin")); //$NON-NLS-1$
		add(lblGamesToWin, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		txtGamesToWin = new JTextField();
		txtGamesToWin.setText(Settings.getControlParameter(SettingsKeys.CTRL_GAMES_TO_WIN));
		txtGamesToWin.setColumns(10);
		add(txtGamesToWin, "cell 1 4,alignx left"); //$NON-NLS-1$
		JLabel lblGameTime = new JLabel(Messages.getString("ParametersPanel.GameTime")); //$NON-NLS-1$
		add(lblGameTime, "cell 2 4,alignx trailing"); //$NON-NLS-1$
		txtGameTime = new JTextField();
		txtGameTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_GAME_TIME));
		txtGameTime.setColumns(10);
		add(txtGameTime, "cell 3 4,growx"); //$NON-NLS-1$
		JLabel lblMaxTimeOuts = new JLabel(Messages.getString("ParametersPanel.MaxTimeOuts")); //$NON-NLS-1$
		add(lblMaxTimeOuts, "flowx,cell 0 5,alignx right"); //$NON-NLS-1$
		txtMaxTimeOuts = new JTextField();
		txtMaxTimeOuts.setText(Settings.getControlParameter(SettingsKeys.CTRL_MAX_TIME_OUTS));
		txtMaxTimeOuts.setColumns(10);
		add(txtMaxTimeOuts, "cell 1 5"); //$NON-NLS-1$
		JLabel lblRecallTime = new JLabel(Messages.getString("ParametersPanel.RecallTime")); //$NON-NLS-1$
		add(lblRecallTime, "cell 2 5,alignx trailing"); //$NON-NLS-1$
		txtRecallTime = new JTextField();
		txtRecallTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_RECALL_TIME));
		txtRecallTime.setColumns(10);
		add(txtRecallTime, "cell 3 5,growx,aligny top"); //$NON-NLS-1$
		JLabel lblTeam1LastScored = new JLabel(Messages.getString("ParametersPanel.Team1LastScored")); //$NON-NLS-1$
		add(lblTeam1LastScored, "cell 2 6,alignx trailing"); //$NON-NLS-1$
		txtTeam1LastScored = new JTextField();
		txtTeam1LastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_TEAM1_LAST_SCORED));
		txtTeam1LastScored.setColumns(10);
		add(txtTeam1LastScored, "cell 3 6,growx"); //$NON-NLS-1$
		JLabel lblTeam2LastScored = new JLabel(Messages.getString("ParametersPanel.Team2LastScored")); //$NON-NLS-1$
		add(lblTeam2LastScored, "cell 2 7,alignx trailing"); //$NON-NLS-1$
		txtTeam2LastScored = new JTextField();
		txtTeam2LastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_TEAM2_LAST_SCORED));
		txtTeam2LastScored.setColumns(10);
		add(txtTeam2LastScored, "cell 3 7,growx"); //$NON-NLS-1$
		JLabel lblClearLastScored = new JLabel(Messages.getString("ParametersPanel.ClearLastScored")); //$NON-NLS-1$
		add(lblClearLastScored, "cell 2 8,alignx trailing"); //$NON-NLS-1$
		txtClearLastScored = new JTextField();
		txtClearLastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_CLEAR_LAST_SCORED));
		txtClearLastScored.setColumns(10);
		add(txtClearLastScored, "cell 3 8,growx"); //$NON-NLS-1$
		JLabel lblSide1Color = new JLabel(Messages.getString("ParametersPanel.Team1Color")); //$NON-NLS-1$
		add(lblSide1Color, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		txtSide1Color = new JTextField();
		txtSide1Color.setText(Settings.getControlParameter(SettingsKeys.CTRL_SIDE1_COLOR));
		txtSide1Color.setColumns(10);
		add(txtSide1Color, "cell 1 6,growx,aligny top"); //$NON-NLS-1$
		JLabel lblSide2Color = new JLabel(Messages.getString("ParametersPanel.Team2Color")); //$NON-NLS-1$
		add(lblSide2Color, "cell 0 7,alignx trailing"); //$NON-NLS-1$
		txtSide2Color = new JTextField();
		txtSide2Color.setText(Settings.getControlParameter(SettingsKeys.CTRL_SIDE2_COLOR));
		txtSide2Color.setColumns(10);
		add(txtSide2Color, "cell 1 7,growx"); //$NON-NLS-1$
		JLabel lblBallsInRack = new JLabel(Messages.getString("ParametersPanel.BallsInRack")); //$NON-NLS-1$
		add(lblBallsInRack, "cell 0 8,alignx trailing"); //$NON-NLS-1$
		txtBallsInRack = new JTextField();
		txtBallsInRack.setText(Settings.getControlParameter(SettingsKeys.CTRL_BALLS_IN_RACK));
		txtBallsInRack.setColumns(10);
		add(txtBallsInRack, "cell 1 8,growx"); //$NON-NLS-1$
		JLabel lblRackMode = new JLabel(Messages.getString("ParametersPanel.RackMode")); //$NON-NLS-1$
		add(lblRackMode, "cell 0 9,alignx right"); //$NON-NLS-1$
		chckbxRackMode = new JCheckBox(""); //$NON-NLS-1$
		chckbxRackMode.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_RACK_MODE).equals(ON));
		add(chckbxRackMode, "cell 1 9"); //$NON-NLS-1$
		JLabel lblAutoIncrementGame = new JLabel(Messages.getString("ParametersPanel.AutoIncrementGame")); //$NON-NLS-1$
		add(lblAutoIncrementGame, "cell 0 12,alignx right"); //$NON-NLS-1$
		chckbxAutoIncrementGame = new JCheckBox(""); //$NON-NLS-1$
		chckbxAutoIncrementGame.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_AUTO_INCREMENT_GAME).equals(ON));
		add(chckbxAutoIncrementGame, "cell 1 12"); //$NON-NLS-1$
		JLabel lblWinnerPrefix = new JLabel(Messages.getString("ParametersPanel.WinnerPrefix")); //$NON-NLS-1$
		add(lblWinnerPrefix, "cell 2 11,alignx center"); //$NON-NLS-1$
		JLabel lblWinnerSuffix = new JLabel(Messages.getString("ParametersPanel.WinnerSuffix")); //$NON-NLS-1$
		add(lblWinnerSuffix, "cell 3 11,alignx center"); //$NON-NLS-1$
		JLabel lblAnnounceWinner = new JLabel(Messages.getString("ParametersPanel.AnnounceWinner")); //$NON-NLS-1$
		add(lblAnnounceWinner, "cell 0 13,alignx right"); //$NON-NLS-1$
		chckbxAnnounceWinner = new JCheckBox(""); //$NON-NLS-1$
		chckbxAnnounceWinner.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_ANNOUNCE_WINNER).equals(ON));
		add(chckbxAnnounceWinner, "cell 1 13,alignx left"); //$NON-NLS-1$
		txtWinnerPrefix = new JTextField();
		txtWinnerPrefix.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWinnerPrefix.setText(Settings.getControlParameter(SettingsKeys.CTRL_WINNER_PREFIX));
		txtWinnerPrefix.setColumns(10);
		add(txtWinnerPrefix, "cell 2 12,alignx center"); //$NON-NLS-1$
		txtWinnerSuffix = new JTextField();
		txtWinnerSuffix.setText(Settings.getControlParameter(SettingsKeys.CTRL_WINNER_SUFFIX));
		txtWinnerSuffix.setColumns(10);
		add(txtWinnerSuffix, "cell 3 12,growx,aligny top"); //$NON-NLS-1$
		JLabel lblAnnounceMeatball = new JLabel(Messages.getString("ParametersPanel.AnnounceMeatball")); //$NON-NLS-1$
		add(lblAnnounceMeatball, "cell 0 14,alignx right"); //$NON-NLS-1$
		chckbxAnnounceMeatball = new JCheckBox(""); //$NON-NLS-1$
		chckbxAnnounceMeatball.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_ANNOUNCE_MEATBALL).equals(ON));
		add(chckbxAnnounceMeatball, "cell 1 14,alignx left"); //$NON-NLS-1$
		txtMeatball = new JTextField();
		txtMeatball.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeatball.setText(Settings.getControlParameter(SettingsKeys.CTRL_MEATBALL));
		txtMeatball.setColumns(10);
		add(txtMeatball, "cell 2 13,alignx center"); //$NON-NLS-1$
		JLabel lblShowTimeOutsUsed = new JLabel(Messages.getString("ParametersPanel.ShowTimeOutsUsed")); //$NON-NLS-1$
		add(lblShowTimeOutsUsed, "cell 0 15,alignx right"); //$NON-NLS-1$
		chckbxShowTimeOutsUsed = new JCheckBox(""); //$NON-NLS-1$
		chckbxShowTimeOutsUsed.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED).equals(ON));
		add(chckbxShowTimeOutsUsed, "cell 1 15"); //$NON-NLS-1$
		JLabel lblAutoCapitalizeTeam = new JLabel(Messages.getString("ParametersPanel.AutoCapitalizeNames")); //$NON-NLS-1$
		add(lblAutoCapitalizeTeam, "cell 0 16,alignx right"); //$NON-NLS-1$
		chckbxAutoCapNames = new JCheckBox(""); //$NON-NLS-1$
		chckbxAutoCapNames.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_AUTO_CAP_NAMES).equals(ON));
		add(chckbxAutoCapNames, "cell 1 16"); //$NON-NLS-1$
		JLabel lblWinByFinalOnly = new JLabel(Messages.getString("ParametersPanel.WinByFinalGameOnly")); //$NON-NLS-1$
		add(lblWinByFinalOnly, "cell 0 17,alignx right"); //$NON-NLS-1$
		chckbxWinByFinalOnly = new JCheckBox(""); //$NON-NLS-1$
		chckbxWinByFinalOnly.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_WIN_BY_FINAL_ONLY).equals(ON));
		add(chckbxWinByFinalOnly, "cell 1 17"); //$NON-NLS-1$
		JLabel lblEnableShowSkunk = new JLabel(Messages.getString("ParametersPanel.EnableShowSkunk")); //$NON-NLS-1$
		add(lblEnableShowSkunk, "cell 0 18,alignx right"); //$NON-NLS-1$
		chckbxEnableShowSkunk = new JCheckBox(""); //$NON-NLS-1$
		chckbxEnableShowSkunk.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_SHOW_SKUNK).equals(ON));
		add(chckbxEnableShowSkunk, "cell 1 18"); //$NON-NLS-1$
		JLabel lblCutThroatMode = new JLabel(Messages.getString("ParametersPanel.CutThroatMode")); //$NON-NLS-1$
		add(lblCutThroatMode, "cell 0 19, alignx right"); //$NON-NLS-1$
		chckbxCutThroatMode = new JCheckBox(""); //$NON-NLS-1$
		chckbxCutThroatMode.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON));
		add(chckbxCutThroatMode, "cell 1 19"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 0 21,alignx center"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 1 21,alignx center"); //$NON-NLS-1$
		btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    confirmClose(win);
                });
		add(btnCancel, "cell 2 21,alignx center"); //$NON-NLS-1$
		btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent arg0) -> {
                    restoreDefaults();
                });
		btnRestoreDefaults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnRestoreDefaults, "cell 3 21,alignx center"); //$NON-NLS-1$
		takeSnapshot();
	}
	private void restoreDefaults() {
		txtPointsToWin.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_POINTS_TO_WIN));
		txtShotTime.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_SHOT_TIME));
		txtMaxWin.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_MAX_WIN));
		txtPassTime.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_PASS_TIME));
		txtWinBy.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_WIN_BY));
		txtTimeOutTime.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_TIME_OUT_TIME));
		int checkValue = Integer.parseInt(Settings.getDefaultParameter(SettingsKeys.CTRL_GAMES_TO_WIN));
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_GAME_TIME));
		txtMaxTimeOuts.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_MAX_TIME_OUTS));
		txtRecallTime.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_RECALL_TIME));
		chckbxRackMode.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_RACK_MODE).equals(ON));
		chckbxAutoIncrementGame.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_AUTO_INCREMENT_GAME).equals(ON));
		chckbxAnnounceWinner.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_ANNOUNCE_WINNER).equals(ON));
		chckbxAnnounceMeatball.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_ANNOUNCE_MEATBALL).equals(ON));
		chckbxShowTimeOutsUsed.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED).equals(ON));
		chckbxAutoCapNames.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_AUTO_CAP_NAMES).equals(ON));
		chckbxWinByFinalOnly.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_WIN_BY_FINAL_ONLY).equals(ON));
		chckbxEnableShowSkunk.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_SHOW_SKUNK).equals(ON));
		chckbxCutThroatMode.setSelected(Settings.getDefaultParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON));
		txtWinnerPrefix.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_WINNER_PREFIX));
		txtWinnerSuffix.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_WINNER_SUFFIX));
		txtMeatball.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_MEATBALL));
		txtTeam1LastScored.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_TEAM1_LAST_SCORED));
		txtTeam2LastScored.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_TEAM2_LAST_SCORED));
		txtClearLastScored.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_CLEAR_LAST_SCORED));
		txtSide1Color.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_SIDE1_COLOR));
		txtSide2Color.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_SIDE2_COLOR));
		txtBallsInRack.setText(Settings.getDefaultParameter(SettingsKeys.CTRL_BALLS_IN_RACK));
	}
	private void revertChanges() {
		txtPointsToWin.setText(Settings.getControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN));
		txtShotTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_SHOT_TIME));
		txtMaxWin.setText(Settings.getControlParameter(SettingsKeys.CTRL_MAX_WIN));
		txtPassTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_PASS_TIME));
		txtWinBy.setText(Settings.getControlParameter(SettingsKeys.CTRL_WIN_BY));
		txtTimeOutTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_TIME_OUT_TIME));
		int checkValue = Integer.parseInt(Settings.getControlParameter(SettingsKeys.CTRL_GAMES_TO_WIN));
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_GAME_TIME));
		txtMaxTimeOuts.setText(Settings.getControlParameter(SettingsKeys.CTRL_MAX_TIME_OUTS));
		txtRecallTime.setText(Settings.getControlParameter(SettingsKeys.CTRL_RECALL_TIME));
		chckbxRackMode.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_RACK_MODE).equals(ON));
		chckbxAutoIncrementGame.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_AUTO_INCREMENT_GAME).equals(ON));
		chckbxAnnounceMeatball.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_ANNOUNCE_MEATBALL).equals(ON));
		chckbxAnnounceWinner.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_ANNOUNCE_WINNER).equals(ON));
		chckbxShowTimeOutsUsed.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED).equals(ON));
		chckbxAutoCapNames.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_AUTO_CAP_NAMES).equals(ON));
		chckbxWinByFinalOnly.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_WIN_BY_FINAL_ONLY).equals(ON));
		chckbxEnableShowSkunk.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_SHOW_SKUNK).equals(ON));
		chckbxCutThroatMode.setSelected(Settings.getControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON));
		txtWinnerPrefix.setText(Settings.getControlParameter(SettingsKeys.CTRL_WINNER_PREFIX));
		txtWinnerSuffix.setText(Settings.getControlParameter(SettingsKeys.CTRL_WINNER_SUFFIX));
		txtMeatball.setText(Settings.getControlParameter(SettingsKeys.CTRL_MEATBALL));
		txtTeam1LastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_TEAM1_LAST_SCORED));
		txtTeam2LastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_TEAM2_LAST_SCORED));
		txtClearLastScored.setText(Settings.getControlParameter(SettingsKeys.CTRL_CLEAR_LAST_SCORED));
		txtSide1Color.setText(Settings.getControlParameter(SettingsKeys.CTRL_SIDE1_COLOR));
		txtSide2Color.setText(Settings.getControlParameter(SettingsKeys.CTRL_SIDE2_COLOR));
		txtBallsInRack.setText(Settings.getControlParameter(SettingsKeys.CTRL_BALLS_IN_RACK));
		setEnableShowSkunk(Settings.getControlParameter(SettingsKeys.CTRL_SHOW_SKUNK).equals(ON));
		takeSnapshot();
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
		saveIntegerSetting(SettingsKeys.CTRL_POINTS_TO_WIN, txtPointsToWin.getText());
		saveMaxWin(SettingsKeys.CTRL_MAX_WIN, txtMaxWin.getText(), Settings.getControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN));
		saveIntegerSetting(SettingsKeys.CTRL_WIN_BY, txtWinBy.getText());
    	if (isValidInteger(txtGamesToWin.getText())) {
    		Integer checkValue = Integer.valueOf(txtGamesToWin.getText());
    		if (checkValue > maxGamesToWin) {
    			checkValue = maxGamesToWin;
    			JOptionPane.showMessageDialog(null,Messages.getString("ParametersPanel.GamesToWinExceedError") + maxGamesToWin + Messages.getString("ParametersPanel.SettingTo") + maxGamesToWin + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		}
    		txtGamesToWin.setText(Integer.toString(checkValue));
    		Settings.setControlParameter(SettingsKeys.CTRL_GAMES_TO_WIN, txtGamesToWin.getText());
    	}
    	saveIntegerSetting(SettingsKeys.CTRL_MAX_TIME_OUTS, txtMaxTimeOuts.getText());
   		Settings.setControlParameter(SettingsKeys.CTRL_RACK_MODE, chckbxRackMode.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_AUTO_INCREMENT_GAME, chckbxAutoIncrementGame.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_ANNOUNCE_WINNER, chckbxAnnounceWinner.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_ANNOUNCE_MEATBALL, chckbxAnnounceMeatball.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_MEATBALL, txtMeatball.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_WINNER_PREFIX, txtWinnerPrefix.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_WINNER_SUFFIX, txtWinnerSuffix.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_TEAM1_LAST_SCORED, txtTeam1LastScored.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_TEAM2_LAST_SCORED, txtTeam2LastScored.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_CLEAR_LAST_SCORED, txtClearLastScored.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_SIDE1_COLOR, txtSide1Color.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_SIDE2_COLOR, txtSide2Color.getText());
		Settings.setControlParameter(SettingsKeys.CTRL_BALLS_IN_RACK, txtBallsInRack.getText());
    	saveIntegerSetting(SettingsKeys.CTRL_SHOT_TIME, txtShotTime.getText());
    	saveIntegerSetting(SettingsKeys.CTRL_PASS_TIME, txtPassTime.getText());
    	saveIntegerSetting(SettingsKeys.CTRL_TIME_OUT_TIME, txtTimeOutTime.getText());
    	saveIntegerSetting(SettingsKeys.CTRL_GAME_TIME, txtGameTime.getText());
    	saveIntegerSetting(SettingsKeys.CTRL_RECALL_TIME, txtRecallTime.getText());
    	Settings.setControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED, chckbxShowTimeOutsUsed.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_AUTO_CAP_NAMES, chckbxAutoCapNames.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY_FINAL_ONLY, chckbxWinByFinalOnly.isSelected() ? ON : OFF);
    	Settings.setControlParameter(SettingsKeys.CTRL_SHOW_SKUNK, chckbxEnableShowSkunk.isSelected() ? ON : OFF);
		Settings.setControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE, chckbxCutThroatMode.isSelected() ? ON : OFF);
		try {
			Settings.saveControlConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
			JOptionPane.showMessageDialog(null, ex.getMessage(), Messages.getString("Errors.ErrorSavingPropertiesFile"), 1);//$NON-NLS-1$
		}
		takeSnapshot();
	}
	private boolean isValidInteger(String checkString) {
    	try {
    		Integer.valueOf(checkString);
    		return true;
    	} catch (NumberFormatException e) {
    		logger.error(e.toString());
    		return false;
    	}
	}
	public void setSaveCallback(BooleanSupplier callback) { this.saveCallback = callback; }
	private void takeSnapshot() { snapshot.clear(); snapshotOf(this); }
	private void snapshotOf(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JCheckBox cb) {
				snapshot.put(cb, cb.isSelected());
			} else if (c instanceof JTextField tf) {
				snapshot.put(tf, tf.getText());
			} else if (c instanceof Container sub) {
				snapshotOf(sub);
			}
		}
	}
	public boolean hasChanges() { return checkChangesIn(this); }
	private boolean checkChangesIn(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JCheckBox cb) {
				Object saved = snapshot.get(cb);
				if (saved != null && !saved.equals(cb.isSelected())) return true;
			} else if (c instanceof JTextField tf) {
				Object saved = snapshot.get(tf);
				if (saved != null && !tf.getText().equals(saved)) return true;
			} else if (c instanceof Container sub) {
				if (checkChangesIn(sub)) return true;
			}
		}
		return false;
	}
	void confirmClose(Window win) {
		if (!hasChanges()) {
			revertChanges();
			win.dispose();
			return;
		}
		int result = JOptionPane.showConfirmDialog(
			win,
			Messages.getString("Global.UnsavedChangesMessage"), //$NON-NLS-1$
			Messages.getString("Global.UnsavedChangesTitle"), //$NON-NLS-1$
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			if (saveCallback.getAsBoolean()) {
				win.dispose();
			}
		} else if (result == JOptionPane.NO_OPTION) {
			revertChanges();
			win.dispose();
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
