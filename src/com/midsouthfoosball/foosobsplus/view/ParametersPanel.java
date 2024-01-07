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
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnRestoreDefaults;
	private final Integer maxGamesToWin = 6;
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
		
		JLabel lblPointsToWin = new JLabel(Messages.getString("ParametersPanel.PointsToWin", Settings.getGameType())); //$NON-NLS-1$
		add(lblPointsToWin, "flowx,cell 0 1,alignx right"); //$NON-NLS-1$
		
		txtPointsToWin = new JTextField();
		txtPointsToWin.setText(Integer.toString(Settings.getPointsToWin()));
		txtPointsToWin.setColumns(10);
		add(txtPointsToWin, "cell 1 1,alignx left"); //$NON-NLS-1$
		
		JLabel lblShotTime = new JLabel(Messages.getString("ParametersPanel.ShotTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblShotTime, "cell 2 1,alignx trailing"); //$NON-NLS-1$
		
		txtShotTime = new JTextField();
		txtShotTime.setText(Integer.toString(Settings.getShotTime()));
		txtShotTime.setColumns(10);
		add(txtShotTime, "cell 3 1,growx"); //$NON-NLS-1$
		
		JLabel lblMaxWin = new JLabel(Messages.getString("ParametersPanel.MaxWin", Settings.getGameType())); //$NON-NLS-1$
		add(lblMaxWin, "flowx,cell 0 2,alignx right"); //$NON-NLS-1$
		
		txtMaxWin = new JTextField();
		txtMaxWin.setText(Integer.toString(Settings.getMaxWin()));
		txtMaxWin.setColumns(10);
		add(txtMaxWin, "cell 1 2,alignx left"); //$NON-NLS-1$
		
		JLabel lblPassTime = new JLabel(Messages.getString("ParametersPanel.PassTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblPassTime, "cell 2 2,alignx trailing"); //$NON-NLS-1$
		
		txtPassTime = new JTextField();
		txtPassTime.setText(Integer.toString(Settings.getPassTime()));
		txtPassTime.setColumns(10);
		add(txtPassTime, "cell 3 2,growx"); //$NON-NLS-1$
		
		JLabel lblWinBy = new JLabel(Messages.getString("ParametersPanel.WinBy", Settings.getGameType())); //$NON-NLS-1$
		add(lblWinBy, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		
		txtWinBy = new JTextField();
		txtWinBy.setText(Integer.toString(Settings.getWinBy()));
		txtWinBy.setColumns(10);
		add(txtWinBy, "cell 1 3,alignx left"); //$NON-NLS-1$
		
		JLabel lblTimeOutTime = new JLabel(Messages.getString("ParametersPanel.TimeOutTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOutTime, "cell 2 3,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOutTime = new JTextField();
		txtTimeOutTime.setText(Integer.toString(Settings.getTimeOutTime()));
		txtTimeOutTime.setColumns(10);
		add(txtTimeOutTime, "cell 3 3,growx"); //$NON-NLS-1$
		
		JLabel lblGamesToWin = new JLabel(Messages.getString("ParametersPanel.GamesToWin", Settings.getGameType())); //$NON-NLS-1$
		add(lblGamesToWin, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		
		txtGamesToWin = new JTextField();
		txtGamesToWin.setText(Integer.toString(Settings.getGamesToWin()));
		txtGamesToWin.setColumns(10);
		add(txtGamesToWin, "cell 1 4,alignx left"); //$NON-NLS-1$
		
		JLabel lblGameTime = new JLabel(Messages.getString("ParametersPanel.GameTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblGameTime, "cell 2 4,alignx trailing"); //$NON-NLS-1$
		
		txtGameTime = new JTextField();
		txtGameTime.setText(Integer.toString(Settings.getGameTime()));
		txtGameTime.setColumns(10);
		add(txtGameTime, "cell 3 4,growx"); //$NON-NLS-1$
		
		JLabel lblMaxTimeOuts = new JLabel(Messages.getString("ParametersPanel.MaxTimeOuts", Settings.getGameType())); //$NON-NLS-1$
		add(lblMaxTimeOuts, "flowx,cell 0 5,alignx right"); //$NON-NLS-1$
		
		txtMaxTimeOuts = new JTextField();
		txtMaxTimeOuts.setText(Integer.toString(Settings.getMaxTimeOuts()));
		txtMaxTimeOuts.setColumns(10);
		add(txtMaxTimeOuts, "cell 1 5"); //$NON-NLS-1$
		
		JLabel lblRecallTime = new JLabel(Messages.getString("ParametersPanel.RecallTime", Settings.getGameType())); //$NON-NLS-1$
		add(lblRecallTime, "cell 2 5,alignx trailing"); //$NON-NLS-1$
		
		txtRecallTime = new JTextField();
		txtRecallTime.setText(Integer.toString(Settings.getRecallTime()));
		txtRecallTime.setColumns(10);
		add(txtRecallTime, "cell 3 5,growx,aligny top"); //$NON-NLS-1$
		
		JLabel lblTeam1LastScored = new JLabel(Messages.getString("ParametersPanel.Team1LastScored", Settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1LastScored, "cell 2 6,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1LastScored = new JTextField();
		txtTeam1LastScored.setText(Settings.getTeam1LastScored());
		txtTeam1LastScored.setColumns(10);
		add(txtTeam1LastScored, "cell 3 6,growx"); //$NON-NLS-1$

		JLabel lblTeam2LastScored = new JLabel(Messages.getString("ParametersPanel.Team2LastScored", Settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2LastScored, "cell 2 7,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2LastScored = new JTextField();
		txtTeam2LastScored.setText(Settings.getTeam2LastScored());
		txtTeam2LastScored.setColumns(10);
		add(txtTeam2LastScored, "cell 3 7,growx"); //$NON-NLS-1$
		
		JLabel lblClearLastScored = new JLabel(Messages.getString("ParametersPanel.ClearLastScored", Settings.getGameType())); //$NON-NLS-1$
		add(lblClearLastScored, "cell 2 8,alignx trailing"); //$NON-NLS-1$
		
		txtClearLastScored = new JTextField();
		txtClearLastScored.setText(Settings.getClearLastScored());
		txtClearLastScored.setColumns(10);
		add(txtClearLastScored, "cell 3 8,growx"); //$NON-NLS-1$
		
		JLabel lblSide1Color = new JLabel(Messages.getString("ParametersPanel.Team1Color", Settings.getGameType())); //$NON-NLS-1$
		add(lblSide1Color, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		
		txtSide1Color = new JTextField();
		txtSide1Color.setText(Settings.getSide1Color());
		txtSide1Color.setColumns(10);
		add(txtSide1Color, "cell 1 6,growx,aligny top"); //$NON-NLS-1$
		
		JLabel lblSide2Color = new JLabel(Messages.getString("ParametersPanel.Team2Color", Settings.getGameType())); //$NON-NLS-1$
		add(lblSide2Color, "cell 0 7,alignx trailing"); //$NON-NLS-1$
		
		txtSide2Color = new JTextField();
		txtSide2Color.setText(Settings.getSide2Color());
		txtSide2Color.setColumns(10);
		add(txtSide2Color, "cell 1 7,growx"); //$NON-NLS-1$
		
		JLabel lblBallsInRack = new JLabel(Messages.getString("ParametersPanel.BallsInRack", Settings.getGameType())); //$NON-NLS-1$
		add(lblBallsInRack, "cell 0 8,alignx trailing"); //$NON-NLS-1$
		
		txtBallsInRack = new JTextField();
		txtBallsInRack.setText(Integer.toString(Settings.getBallsInRack()));
		txtBallsInRack.setColumns(10);
		add(txtBallsInRack, "cell 1 8,growx"); //$NON-NLS-1$
		
		JLabel lblRackMode = new JLabel(Messages.getString("ParametersPanel.RackMode", Settings.getGameType())); //$NON-NLS-1$
		add(lblRackMode, "cell 0 9,alignx right"); //$NON-NLS-1$
		
		chckbxRackMode = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getRackMode()).equals("1")) { //$NON-NLS-1$
			chckbxRackMode.setSelected(true);
		} else {
			chckbxRackMode.setSelected(false);
		}
		add(chckbxRackMode, "cell 1 9"); //$NON-NLS-1$
		
		JLabel lblAutoIncrementGame = new JLabel(Messages.getString("ParametersPanel.AutoIncrementGame", Settings.getGameType())); //$NON-NLS-1$
		add(lblAutoIncrementGame, "cell 0 12,alignx right"); //$NON-NLS-1$
		
		chckbxAutoIncrementGame = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getAutoIncrementGame()).equals("1")) { //$NON-NLS-1$
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		add(chckbxAutoIncrementGame, "cell 1 12"); //$NON-NLS-1$
		
		JLabel lblWinnerPrefix = new JLabel(Messages.getString("ParametersPanel.WinnerPrefix", Settings.getGameType())); //$NON-NLS-1$
		add(lblWinnerPrefix, "cell 2 11,alignx center"); //$NON-NLS-1$
		
		JLabel lblWinnerSuffix = new JLabel(Messages.getString("ParametersPanel.WinnerSuffix", Settings.getGameType())); //$NON-NLS-1$
		add(lblWinnerSuffix, "cell 3 11,alignx center"); //$NON-NLS-1$
		
		JLabel lblAnnounceWinner = new JLabel(Messages.getString("ParametersPanel.AnnounceWinner", Settings.getGameType())); //$NON-NLS-1$
		add(lblAnnounceWinner, "cell 0 13,alignx right"); //$NON-NLS-1$
		
		chckbxAnnounceWinner = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getAnnounceWinner()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		add(chckbxAnnounceWinner, "cell 1 13,alignx left"); //$NON-NLS-1$
		
		txtWinnerPrefix = new JTextField();
		txtWinnerPrefix.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWinnerPrefix.setText(Settings.getWinnerPrefix());
		txtWinnerPrefix.setColumns(10);
		add(txtWinnerPrefix, "cell 2 12,alignx center"); //$NON-NLS-1$
		
		txtWinnerSuffix = new JTextField();
		txtWinnerSuffix.setText(Settings.getWinnerSuffix());
		txtWinnerSuffix.setColumns(10);
		add(txtWinnerSuffix, "cell 3 12,growx,aligny top"); //$NON-NLS-1$
		
		JLabel lblAnnounceMeatball = new JLabel(Messages.getString("ParametersPanel.AnnounceMeatball", Settings.getGameType())); //$NON-NLS-1$
		add(lblAnnounceMeatball, "cell 0 14,alignx right"); //$NON-NLS-1$
		
		chckbxAnnounceMeatball = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getAnnounceMeatball()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		add(chckbxAnnounceMeatball, "cell 1 14,alignx left"); //$NON-NLS-1$
		
		txtMeatball = new JTextField();
		txtMeatball.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeatball.setText(Settings.getMeatball());
		txtMeatball.setColumns(10);
		add(txtMeatball, "cell 2 13,alignx center"); //$NON-NLS-1$
		
		JLabel lblShowTimeOutsUsed = new JLabel(Messages.getString("ParametersPanel.ShowTimeOutsUsed", Settings.getGameType())); //$NON-NLS-1$
		add(lblShowTimeOutsUsed, "cell 0 15,alignx right"); //$NON-NLS-1$
		
		chckbxShowTimeOutsUsed = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getShowTimeOutsUsed()).equals("1")) { //$NON-NLS-1$
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		add(chckbxShowTimeOutsUsed, "cell 1 15"); //$NON-NLS-1$
		
		JLabel lblAutoCapitalizeTeam = new JLabel(Messages.getString("ParametersPanel.AutoCapitalizeNames", Settings.getGameType())); //$NON-NLS-1$
		add(lblAutoCapitalizeTeam, "cell 0 16,alignx right"); //$NON-NLS-1$
		
		chckbxAutoCapNames = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getAutoCapNames()).equals("1")) { //$NON-NLS-1$
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		add(chckbxAutoCapNames, "cell 1 16"); //$NON-NLS-1$
		
		JLabel lblWinByFinalOnly = new JLabel(Messages.getString("ParametersPanel.WinByFinalGameOnly", Settings.getGameType())); //$NON-NLS-1$
		add(lblWinByFinalOnly, "cell 0 17,alignx right"); //$NON-NLS-1$
		
		chckbxWinByFinalOnly = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getWinByFinalOnly()).equals("1")) { //$NON-NLS-1$
			chckbxWinByFinalOnly.setSelected(true);
		} else {
			chckbxWinByFinalOnly.setSelected(false);
		}
		add(chckbxWinByFinalOnly, "cell 1 17"); //$NON-NLS-1$
		
		JLabel lblEnableShowSkunk = new JLabel(Messages.getString("ParametersPanel.EnableShowSkunk", Settings.getGameType())); //$NON-NLS-1$
		add(lblEnableShowSkunk, "cell 0 18,alignx right"); //$NON-NLS-1$
		
		chckbxEnableShowSkunk = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getShowSkunk()).equals("1")) { //$NON-NLS-1$
			chckbxEnableShowSkunk.setSelected(true);
		} else {
			chckbxEnableShowSkunk.setSelected(false);
		}
		add(chckbxEnableShowSkunk, "cell 1 18"); //$NON-NLS-1$
		
		JLabel lblCutThroatMode = new JLabel(Messages.getString("ParametersPanel.CutThroatMode", Settings.getGameType())); //$NON-NLS-1$
		add(lblCutThroatMode, "cell 0 19, alignx right"); //$NON-NLS-1$
		
		chckbxCutThroatMode = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(Settings.getCutThroatMode()).equals("1")) { //$NON-NLS-1$
			chckbxCutThroatMode.setSelected(true);
		} else {
			chckbxCutThroatMode.setSelected(false);
		}
		add(chckbxCutThroatMode, "cell 1 19"); //$NON-NLS-1$
		
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 1 21,alignx center"); //$NON-NLS-1$

		btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		txtPointsToWin.setText(Integer.toString(Settings.getDefaultPointsToWin()));
		txtShotTime.setText(Integer.toString(Settings.getDefaultShotTime()));
		txtMaxWin.setText(Integer.toString(Settings.getDefaultMaxWin()));
		txtPassTime.setText(Integer.toString(Settings.getDefaultPassTime()));
		txtWinBy.setText(Integer.toString(Settings.getDefaultWinBy()));
		txtTimeOutTime.setText(Integer.toString(Settings.getDefaultTimeOutTime()));
		Integer checkValue = Settings.getDefaultGamesToWin();
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Integer.toString(Settings.getDefaultGameTime()));
		txtMaxTimeOuts.setText(Integer.toString(Settings.getDefaultMaxTimeOuts()));
		txtRecallTime.setText(Integer.toString(Settings.getDefaultRecallTime()));
		if (Integer.toString(Settings.getDefaultRackMode()).equals("1")) { //$NON-NLS-1$
			chckbxRackMode.setSelected(true);
		} else {
			chckbxRackMode.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultAutoIncrementGame()).equals("1")) { //$NON-NLS-1$
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultAnnounceWinner()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		txtWinnerPrefix.setText(Settings.getDefaultWinnerPrefix());
		txtWinnerSuffix.setText(Settings.getDefaultWinnerSuffix());
		if (Integer.toString(Settings.getDefaultAnnounceMeatball()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		txtMeatball.setText(Settings.getDefaultMeatball());
		if (Integer.toString(Settings.getDefaultShowTimeOutsUsed()).equals("1")) { //$NON-NLS-1$
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultAutoCapNames()).equals("1")) { //$NON-NLS-1$
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultWinByFinalOnly()).equals("1")) { //$NON-NLS-1$
			chckbxWinByFinalOnly.setSelected(true);
		} else {
			chckbxWinByFinalOnly.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultShowSkunk()).equals("1")) { //$NON-NLS-1$
			chckbxEnableShowSkunk.setSelected(true);
		} else {
			chckbxEnableShowSkunk.setSelected(false);
		}
		if (Integer.toString(Settings.getDefaultCutThroatMode()).equals("1")) { //$NON-NLS-1$
			chckbxCutThroatMode.setSelected(true);
		} else {
			chckbxCutThroatMode.setSelected(false);
		}
		txtTeam1LastScored.setText(Settings.getDefaultTeam1LastScored());
		txtTeam2LastScored.setText(Settings.getDefaultTeam2LastScored());
		txtClearLastScored.setText(Settings.getDefaultClearLastScored());
		txtSide1Color.setText(Settings.getDefaultSide1Color());
		txtSide2Color.setText(Settings.getDefaultSide2Color());
		txtBallsInRack.setText(Settings.getDefaultBallsInRack());
	}
	public void saveSettings(Settings settings) {
    	if (isValidInteger(txtPointsToWin.getText())) {
			Settings.setPointsToWin(Integer.parseInt(txtPointsToWin.getText()));
    	}
    	if (isValidInteger(txtMaxWin.getText())) {
    		int maxWin = Integer.parseInt(txtMaxWin.getText());
    		if (Settings.getPointsToWin() > maxWin) {
    			Settings.setMaxWin(Settings.getPointsToWin());
    			txtMaxWin.setText(Integer.toString(Settings.getPointsToWin()));
    		} else {
    			Settings.setMaxWin(Integer.parseInt(txtMaxWin.getText()));
    		}
    	} else {
    		Settings.setMaxWin(Settings.getPointsToWin());
    	}
    	if (isValidInteger(txtWinBy.getText())) {
    		Settings.setWinBy(Integer.parseInt(txtWinBy.getText()));
    	}
    	if (isValidInteger(txtGamesToWin.getText())) {
    		Integer checkValue = Integer.parseInt(txtGamesToWin.getText());
    		if (checkValue > maxGamesToWin) {
    			checkValue = maxGamesToWin;
    			JOptionPane.showMessageDialog(null,Messages.getString("ParametersPanel.GamesToWinExceedError", Settings.getGameType()) + maxGamesToWin + Messages.getString("ParametersPanel.SettingTo", Settings.getGameType()) + maxGamesToWin + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		}
    		txtGamesToWin.setText(Integer.toString(checkValue));
    		Settings.setGamesToWin(Integer.parseInt(txtGamesToWin.getText()));
    	}
    	if (isValidInteger(txtMaxTimeOuts.getText())) {
    		Settings.setMaxTimeOuts(Integer.parseInt(txtMaxTimeOuts.getText()));
    	}
    	if (chckbxRackMode.isSelected()) {
    		Settings.setRackMode(1);
    	} else {
    		Settings.setRackMode(0);
    	}
		if (chckbxAutoIncrementGame.isSelected()) {
			Settings.setAutoIncrementGame(1);
		} else {
			Settings.setAutoIncrementGame(0);
		}
		if (chckbxAnnounceWinner.isSelected()) {
			Settings.setAnnounceWinner(1);
		} else {
			Settings.setAnnounceWinner(0);
		}
		if (chckbxAnnounceMeatball.isSelected()) {
			Settings.setAnnounceMeatball(1);
		} else {
			Settings.setAnnounceMeatball(0);
		}
		Settings.setMeatball(txtMeatball.getText());
		Settings.setWinnerPrefix(txtWinnerPrefix.getText());
		Settings.setWinnerSuffix(txtWinnerSuffix.getText());
		Settings.setTeam1LastScored(txtTeam1LastScored.getText());
		Settings.setTeam2LastScored(txtTeam2LastScored.getText());
		Settings.setClearLastScored(txtClearLastScored.getText());
		Settings.setSide1Color(txtSide1Color.getText());
		Settings.setSide2Color(txtSide2Color.getText());
		Settings.setBallsInRack(txtBallsInRack.getText());
		if (isValidInteger(txtShotTime.getText())) {
    		Settings.setShotTime(Integer.parseInt(txtShotTime.getText()));
    	}
    	if (isValidInteger(txtPassTime.getText())) {
    		Settings.setPassTime(Integer.parseInt(txtPassTime.getText()));
    	}
    	if (isValidInteger(txtTimeOutTime.getText())) {
    		Settings.setTimeOutTime(Integer.parseInt(txtTimeOutTime.getText()));
    	}
    	if (isValidInteger(txtGameTime.getText())) {
    		Settings.setGameTime(Integer.parseInt(txtGameTime.getText()));
    	}
    	if (isValidInteger(txtRecallTime.getText())) {
    		Settings.setRecallTime(Integer.parseInt(txtRecallTime.getText()));
    	}
		if (chckbxShowTimeOutsUsed.isSelected()) {
			Settings.setShowTimeOutsUsed(1);
		} else {
			Settings.setShowTimeOutsUsed(0);
		}
		if (chckbxAutoCapNames.isSelected()) {
			Settings.setAutoCapNames(1);
		} else {
			Settings.setAutoCapNames(0);
		}
		if (chckbxWinByFinalOnly.isSelected()) {
			Settings.setWinByFinalOnly(1);
		} else {
			Settings.setWinByFinalOnly(0);
		}
		if (chckbxEnableShowSkunk.isSelected()) {
			Settings.setShowSkunk(1);
		} else {
			Settings.setShowSkunk(0);
		}
		if (chckbxCutThroatMode.isSelected()) {
			Settings.setCutThroatMode(1);
		} else {
			Settings.setCutThroatMode(0);
		}
		try {
			Settings.saveControlConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile"));
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
	public void addSaveListener(ActionListener listenForBtnSave) {
		btnSave.addActionListener(listenForBtnSave);
	}
	public void addEnableShowSkunkListener(ActionListener listenForChckbxEnableShowSkunk) {
		chckbxEnableShowSkunk.addActionListener(listenForChckbxEnableShowSkunk);
	}
}
