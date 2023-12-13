/**
Copyright 2020-2024 Hugh Garner
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
	private static Logger logger;
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public ParametersPanel(Settings settings) throws IOException {

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
		
		JLabel lblPointsToWin = new JLabel(Messages.getString("ParametersPanel.PointsToWin", settings.getGameType())); //$NON-NLS-1$
		add(lblPointsToWin, "flowx,cell 0 1,alignx right"); //$NON-NLS-1$
		
		txtPointsToWin = new JTextField();
		txtPointsToWin.setText(Integer.toString(settings.getPointsToWin()));
		add(txtPointsToWin, "cell 1 1,alignx left"); //$NON-NLS-1$
		txtPointsToWin.setColumns(10);
		
		JLabel lblShotTime = new JLabel(Messages.getString("ParametersPanel.ShotTime", settings.getGameType())); //$NON-NLS-1$
		add(lblShotTime, "cell 2 1,alignx trailing"); //$NON-NLS-1$
		
		txtShotTime = new JTextField();
		txtShotTime.setText(Integer.toString(settings.getShotTime()));
		add(txtShotTime, "cell 3 1,growx"); //$NON-NLS-1$
		txtShotTime.setColumns(10);
		
		JLabel lblMaxWin = new JLabel(Messages.getString("ParametersPanel.MaxWin", settings.getGameType())); //$NON-NLS-1$
		add(lblMaxWin, "flowx,cell 0 2,alignx right"); //$NON-NLS-1$
		
		txtMaxWin = new JTextField();
		txtMaxWin.setText(Integer.toString(settings.getMaxWin()));
		add(txtMaxWin, "cell 1 2,alignx left"); //$NON-NLS-1$
		txtMaxWin.setColumns(10);
		
		JLabel lblPassTime = new JLabel(Messages.getString("ParametersPanel.PassTime", settings.getGameType())); //$NON-NLS-1$
		add(lblPassTime, "cell 2 2,alignx trailing"); //$NON-NLS-1$
		
		txtPassTime = new JTextField();
		txtPassTime.setText(Integer.toString(settings.getPassTime()));
		add(txtPassTime, "cell 3 2,growx"); //$NON-NLS-1$
		txtPassTime.setColumns(10);
		
		JLabel lblWinBy = new JLabel(Messages.getString("ParametersPanel.WinBy", settings.getGameType())); //$NON-NLS-1$
		add(lblWinBy, "cell 0 3,alignx trailing"); //$NON-NLS-1$
		
		txtWinBy = new JTextField();
		txtWinBy.setText(Integer.toString(settings.getWinBy()));
		add(txtWinBy, "cell 1 3,alignx left"); //$NON-NLS-1$
		txtWinBy.setColumns(10);
		
		JLabel lblTimeOutTime = new JLabel(Messages.getString("ParametersPanel.TimeOutTime", settings.getGameType())); //$NON-NLS-1$
		add(lblTimeOutTime, "cell 2 3,alignx trailing"); //$NON-NLS-1$
		
		txtTimeOutTime = new JTextField();
		txtTimeOutTime.setText(Integer.toString(settings.getTimeOutTime()));
		add(txtTimeOutTime, "cell 3 3,growx"); //$NON-NLS-1$
		txtTimeOutTime.setColumns(10);
		
		JLabel lblGamesToWin = new JLabel(Messages.getString("ParametersPanel.GamesToWin", settings.getGameType())); //$NON-NLS-1$
		add(lblGamesToWin, "cell 0 4,alignx trailing"); //$NON-NLS-1$
		
		txtGamesToWin = new JTextField();
		txtGamesToWin.setText(Integer.toString(settings.getGamesToWin()));
		add(txtGamesToWin, "cell 1 4,alignx left"); //$NON-NLS-1$
		txtGamesToWin.setColumns(10);
		
		JLabel lblGameTime = new JLabel(Messages.getString("ParametersPanel.GameTime", settings.getGameType())); //$NON-NLS-1$
		add(lblGameTime, "cell 2 4,alignx trailing"); //$NON-NLS-1$
		
		txtGameTime = new JTextField();
		txtGameTime.setText(Integer.toString(settings.getGameTime()));
		add(txtGameTime, "cell 3 4,growx"); //$NON-NLS-1$
		txtGameTime.setColumns(10);
		
		JLabel lblMaxTimeOuts = new JLabel(Messages.getString("ParametersPanel.MaxTimeOuts", settings.getGameType())); //$NON-NLS-1$
		add(lblMaxTimeOuts, "flowx,cell 0 5,alignx right"); //$NON-NLS-1$
		
		txtMaxTimeOuts = new JTextField();
		txtMaxTimeOuts.setText(Integer.toString(settings.getMaxTimeOuts()));
		add(txtMaxTimeOuts, "cell 1 5"); //$NON-NLS-1$
		txtMaxTimeOuts.setColumns(10);
		
		JLabel lblRecallTime = new JLabel(Messages.getString("ParametersPanel.RecallTime", settings.getGameType())); //$NON-NLS-1$
		add(lblRecallTime, "cell 2 5,alignx trailing"); //$NON-NLS-1$
		
		txtRecallTime = new JTextField();
		txtRecallTime.setText(Integer.toString(settings.getRecallTime()));
		add(txtRecallTime, "cell 3 5,growx,aligny top"); //$NON-NLS-1$
		txtRecallTime.setColumns(10);
		
		JLabel lblTeam1LastScored = new JLabel(Messages.getString("ParametersPanel.Team1LastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1LastScored, "cell 2 6,alignx trailing"); //$NON-NLS-1$
		
		txtTeam1LastScored = new JTextField();
		txtTeam1LastScored.setText(settings.getTeam1LastScored());
		add(txtTeam1LastScored, "cell 3 6,growx"); //$NON-NLS-1$
		txtTeam1LastScored.setColumns(10);

		JLabel lblTeam2LastScored = new JLabel(Messages.getString("ParametersPanel.Team2LastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2LastScored, "cell 2 7,alignx trailing"); //$NON-NLS-1$
		
		txtTeam2LastScored = new JTextField();
		txtTeam2LastScored.setText(settings.getTeam2LastScored());
		add(txtTeam2LastScored, "cell 3 7,growx"); //$NON-NLS-1$
		txtTeam2LastScored.setColumns(10);
		
		JLabel lblClearLastScored = new JLabel(Messages.getString("ParametersPanel.ClearLastScored", settings.getGameType())); //$NON-NLS-1$
		add(lblClearLastScored, "cell 2 8,alignx trailing"); //$NON-NLS-1$
		
		txtClearLastScored = new JTextField();
		txtClearLastScored.setText(settings.getClearLastScored());
		add(txtClearLastScored, "cell 3 8,growx"); //$NON-NLS-1$
		txtClearLastScored.setColumns(10);
		
		JLabel lblSide1Color = new JLabel(Messages.getString("ParametersPanel.Team1Color", settings.getGameType())); //$NON-NLS-1$
		add(lblSide1Color, "cell 0 6,alignx trailing"); //$NON-NLS-1$
		
		txtSide1Color = new JTextField();
		txtSide1Color.setText(settings.getSide1Color());
		add(txtSide1Color, "cell 1 6,growx,aligny top"); //$NON-NLS-1$
		txtSide1Color.setColumns(10);
		
		JLabel lblSide2Color = new JLabel(Messages.getString("ParametersPanel.Team2Color", settings.getGameType())); //$NON-NLS-1$
		add(lblSide2Color, "cell 0 7,alignx trailing"); //$NON-NLS-1$
		
		txtSide2Color = new JTextField();
		txtSide2Color.setText(settings.getSide2Color());
		add(txtSide2Color, "cell 1 7,growx"); //$NON-NLS-1$
		txtSide2Color.setColumns(10);
		
		JLabel lblBallsInRack = new JLabel(Messages.getString("ParametersPanel.BallsInRack", settings.getGameType())); //$NON-NLS-1$
		add(lblBallsInRack, "cell 0 8,alignx trailing"); //$NON-NLS-1$
		
		txtBallsInRack = new JTextField();
		txtBallsInRack.setText(Integer.toString(settings.getBallsInRack()));
		add(txtBallsInRack, "cell 1 8,growx"); //$NON-NLS-1$
		txtBallsInRack.setColumns(10);
		
		JLabel lblRackMode = new JLabel(Messages.getString("ParametersPanel.RackMode", settings.getGameType())); //$NON-NLS-1$
		add(lblRackMode, "cell 0 9,alignx right"); //$NON-NLS-1$
		
		chckbxRackMode = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getRackMode()).equals("1")) { //$NON-NLS-1$
			chckbxRackMode.setSelected(true);
		} else {
			chckbxRackMode.setSelected(false);
		}
		add(chckbxRackMode, "cell 1 9"); //$NON-NLS-1$
		
		JLabel lblAutoIncrementGame = new JLabel(Messages.getString("ParametersPanel.AutoIncrementGame", settings.getGameType())); //$NON-NLS-1$
		add(lblAutoIncrementGame, "cell 0 12,alignx right"); //$NON-NLS-1$
		
		chckbxAutoIncrementGame = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getAutoIncrementGame()).equals("1")) { //$NON-NLS-1$
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		add(chckbxAutoIncrementGame, "cell 1 12"); //$NON-NLS-1$
		
		JLabel lblWinnerPrefix = new JLabel(Messages.getString("ParametersPanel.WinnerPrefix", settings.getGameType())); //$NON-NLS-1$
		add(lblWinnerPrefix, "cell 2 11,alignx center"); //$NON-NLS-1$
		
		JLabel lblWinnerSuffix = new JLabel(Messages.getString("ParametersPanel.WinnerSuffix", settings.getGameType())); //$NON-NLS-1$
		add(lblWinnerSuffix, "cell 3 11,alignx center"); //$NON-NLS-1$
		
		JLabel lblAnnounceWinner = new JLabel(Messages.getString("ParametersPanel.AnnounceWinner", settings.getGameType())); //$NON-NLS-1$
		add(lblAnnounceWinner, "cell 0 13,alignx right"); //$NON-NLS-1$
		
		chckbxAnnounceWinner = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getAnnounceWinner()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		add(chckbxAnnounceWinner, "cell 1 13,alignx left"); //$NON-NLS-1$
		
		txtWinnerPrefix = new JTextField();
		txtWinnerPrefix.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWinnerPrefix.setText(settings.getWinnerPrefix());
		add(txtWinnerPrefix, "cell 2 12,alignx center"); //$NON-NLS-1$
		txtWinnerPrefix.setColumns(10);
		
		txtWinnerSuffix = new JTextField();
		txtWinnerSuffix.setText(settings.getWinnerSuffix());
		add(txtWinnerSuffix, "cell 3 12,growx,aligny top"); //$NON-NLS-1$
		txtWinnerSuffix.setColumns(10);
		
		JLabel lblAnnounceMeatball = new JLabel(Messages.getString("ParametersPanel.AnnounceMeatball", settings.getGameType())); //$NON-NLS-1$
		add(lblAnnounceMeatball, "cell 0 14,alignx right"); //$NON-NLS-1$
		
		chckbxAnnounceMeatball = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getAnnounceMeatball()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		add(chckbxAnnounceMeatball, "cell 1 14,alignx left"); //$NON-NLS-1$
		
		txtMeatball = new JTextField();
		txtMeatball.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeatball.setText(settings.getMeatball());
		add(txtMeatball, "cell 2 13,alignx center"); //$NON-NLS-1$
		txtMeatball.setColumns(10);
		
		JLabel lblShowTimeOutsUsed = new JLabel(Messages.getString("ParametersPanel.ShowTimeOutsUsed", settings.getGameType())); //$NON-NLS-1$
		add(lblShowTimeOutsUsed, "cell 0 15,alignx right"); //$NON-NLS-1$
		
		chckbxShowTimeOutsUsed = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getShowTimeOutsUsed()).equals("1")) { //$NON-NLS-1$
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		add(chckbxShowTimeOutsUsed, "cell 1 15"); //$NON-NLS-1$
		
		JLabel lblAutoCapitalizeTeam = new JLabel(Messages.getString("ParametersPanel.AutoCapitalizeNames", settings.getGameType())); //$NON-NLS-1$
		add(lblAutoCapitalizeTeam, "cell 0 16,alignx right"); //$NON-NLS-1$
		
		chckbxAutoCapNames = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getAutoCapNames()).equals("1")) { //$NON-NLS-1$
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		add(chckbxAutoCapNames, "cell 1 16"); //$NON-NLS-1$
		
		JLabel lblWinByFinalOnly = new JLabel(Messages.getString("ParametersPanel.WinByFinalGameOnly", settings.getGameType())); //$NON-NLS-1$
		add(lblWinByFinalOnly, "cell 0 17,alignx right"); //$NON-NLS-1$
		
		chckbxWinByFinalOnly = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getWinByFinalOnly()).equals("1")) { //$NON-NLS-1$
			chckbxWinByFinalOnly.setSelected(true);
		} else {
			chckbxWinByFinalOnly.setSelected(false);
		}
		add(chckbxWinByFinalOnly, "cell 1 17"); //$NON-NLS-1$
		
		JLabel lblEnableShowSkunk = new JLabel(Messages.getString("ParametersPanel.EnableShowSkunk", settings.getGameType())); //$NON-NLS-1$
		add(lblEnableShowSkunk, "cell 0 18,alignx right"); //$NON-NLS-1$
		
		chckbxEnableShowSkunk = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getShowSkunk()).equals("1")) { //$NON-NLS-1$
			chckbxEnableShowSkunk.setSelected(true);
		} else {
			chckbxEnableShowSkunk.setSelected(false);
		}
		add(chckbxEnableShowSkunk, "cell 1 18"); //$NON-NLS-1$
		
		JLabel lblCutThroatMode = new JLabel(Messages.getString("ParametersPanel.CutThroatMode", settings.getGameType())); //$NON-NLS-1$
		add(lblCutThroatMode, "cell 0 19, alignx right"); //$NON-NLS-1$
		
		chckbxCutThroatMode = new JCheckBox(""); //$NON-NLS-1$
		if (Integer.toString(settings.getCutThroatMode()).equals("1")) { //$NON-NLS-1$
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
				restoreDefaults(settings);
			}
		});
		btnRestoreDefaults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnRestoreDefaults, "cell 3 21,alignx center"); //$NON-NLS-1$
	}
	
	private void restoreDefaults(Settings settings) {
		txtPointsToWin.setText(Integer.toString(settings.getDefaultPointsToWin()));
		txtShotTime.setText(Integer.toString(settings.getDefaultShotTime()));
		txtMaxWin.setText(Integer.toString(settings.getDefaultMaxWin()));
		txtPassTime.setText(Integer.toString(settings.getDefaultPassTime()));
		txtWinBy.setText(Integer.toString(settings.getDefaultWinBy()));
		txtTimeOutTime.setText(Integer.toString(settings.getDefaultTimeOutTime()));
		Integer checkValue = settings.getDefaultGamesToWin();
		if (checkValue > maxGamesToWin) checkValue = maxGamesToWin;
		txtGamesToWin.setText(Integer.toString(checkValue));
		txtGameTime.setText(Integer.toString(settings.getDefaultGameTime()));
		txtMaxTimeOuts.setText(Integer.toString(settings.getDefaultMaxTimeOuts()));
		txtRecallTime.setText(Integer.toString(settings.getDefaultRecallTime()));
		if (Integer.toString(settings.getDefaultRackMode()).equals("1")) { //$NON-NLS-1$
			chckbxRackMode.setSelected(true);
		} else {
			chckbxRackMode.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultAutoIncrementGame()).equals("1")) { //$NON-NLS-1$
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultAnnounceWinner()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		txtWinnerPrefix.setText(settings.getDefaultWinnerPrefix());
		txtWinnerSuffix.setText(settings.getDefaultWinnerSuffix());
		if (Integer.toString(settings.getDefaultAnnounceMeatball()).equals("1")) { //$NON-NLS-1$
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		txtMeatball.setText(settings.getDefaultMeatball());
		if (Integer.toString(settings.getDefaultShowTimeOutsUsed()).equals("1")) { //$NON-NLS-1$
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultAutoCapNames()).equals("1")) { //$NON-NLS-1$
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultWinByFinalOnly()).equals("1")) { //$NON-NLS-1$
			chckbxWinByFinalOnly.setSelected(true);
		} else {
			chckbxWinByFinalOnly.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultShowSkunk()).equals("1")) { //$NON-NLS-1$
			chckbxEnableShowSkunk.setSelected(true);
		} else {
			chckbxEnableShowSkunk.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultCutThroatMode()).equals("1")) { //$NON-NLS-1$
			chckbxCutThroatMode.setSelected(true);
		} else {
			chckbxCutThroatMode.setSelected(false);
		}
		txtTeam1LastScored.setText(settings.getDefaultTeam1LastScored());
		txtTeam2LastScored.setText(settings.getDefaultTeam2LastScored());
		txtClearLastScored.setText(settings.getDefaultClearLastScored());
		txtSide1Color.setText(settings.getDefaultSide1Color());
		txtSide2Color.setText(settings.getDefaultSide2Color());
		txtBallsInRack.setText(settings.getDefaultBallsInRack());
	}
	
	public void saveSettings(Settings settings) {
    	if (isValidInteger(txtPointsToWin.getText())) {
			settings.setPointsToWin(Integer.parseInt(txtPointsToWin.getText()));
    	}
    	if (isValidInteger(txtMaxWin.getText())) {
    		int maxWin = Integer.parseInt(txtMaxWin.getText());
    		if (settings.getPointsToWin() > maxWin) {
    			settings.setMaxWin(settings.getPointsToWin());
    			txtMaxWin.setText(Integer.toString(settings.getPointsToWin()));
    		} else {
    			settings.setMaxWin(Integer.parseInt(txtMaxWin.getText()));
    		}
    	} else {
    		settings.setMaxWin(settings.getPointsToWin());
    	}
    	if (isValidInteger(txtWinBy.getText())) {
		settings.setWinBy(Integer.parseInt(txtWinBy.getText()));
    	}
    	if (isValidInteger(txtGamesToWin.getText())) {
    		Integer checkValue = Integer.parseInt(txtGamesToWin.getText());
    		if (checkValue > maxGamesToWin) {
    			checkValue = maxGamesToWin;
    			JOptionPane.showMessageDialog(null,Messages.getString("ParametersPanel.GamesToWinExceedError", settings.getGameType()) + maxGamesToWin + Messages.getString("ParametersPanel.SettingTo", settings.getGameType()) + maxGamesToWin + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		}
    		txtGamesToWin.setText(Integer.toString(checkValue));
    		settings.setGamesToWin(Integer.parseInt(txtGamesToWin.getText()));
    	}
    	if (isValidInteger(txtMaxTimeOuts.getText())) {
    		settings.setMaxTimeOuts(Integer.parseInt(txtMaxTimeOuts.getText()));
    	}
    	if (chckbxRackMode.isSelected()) {
    		settings.setRackMode(1);
    	} else {
    		settings.setRackMode(0);
    	}
		if (chckbxAutoIncrementGame.isSelected()) {
			settings.setAutoIncrementGame(1);
		} else {
			settings.setAutoIncrementGame(0);
		}
		if (chckbxAnnounceWinner.isSelected()) {
			settings.setAnnounceWinner(1);
		} else {
			settings.setAnnounceWinner(0);
		}
		if (chckbxAnnounceMeatball.isSelected()) {
			settings.setAnnounceMeatball(1);
		} else {
			settings.setAnnounceMeatball(0);
		}
		settings.setMeatball(txtMeatball.getText());
		settings.setWinnerPrefix(txtWinnerPrefix.getText());
		settings.setWinnerSuffix(txtWinnerSuffix.getText());
		settings.setTeam1LastScored(txtTeam1LastScored.getText());
		settings.setTeam2LastScored(txtTeam2LastScored.getText());
		settings.setClearLastScored(txtClearLastScored.getText());
		settings.setSide1Color(txtSide1Color.getText());
		settings.setSide2Color(txtSide2Color.getText());
		settings.setBallsInRack(txtBallsInRack.getText());
		if (isValidInteger(txtShotTime.getText())) {
    		settings.setShotTime(Integer.parseInt(txtShotTime.getText()));
    	}
    	if (isValidInteger(txtPassTime.getText())) {
    		settings.setPassTime(Integer.parseInt(txtPassTime.getText()));
    	}
    	if (isValidInteger(txtTimeOutTime.getText())) {
    		settings.setTimeOutTime(Integer.parseInt(txtTimeOutTime.getText()));
    	}
    	if (isValidInteger(txtGameTime.getText())) {
    		settings.setGameTime(Integer.parseInt(txtGameTime.getText()));
    	}
    	if (isValidInteger(txtRecallTime.getText())) {
    		settings.setRecallTime(Integer.parseInt(txtRecallTime.getText()));
    	}
		if (chckbxShowTimeOutsUsed.isSelected()) {
			settings.setShowTimeOutsUsed(1);
		} else {
			settings.setShowTimeOutsUsed(0);
		}
		if (chckbxAutoCapNames.isSelected()) {
			settings.setAutoCapNames(1);
		} else {
			settings.setAutoCapNames(0);
		}
		if (chckbxWinByFinalOnly.isSelected()) {
			settings.setWinByFinalOnly(1);
		} else {
			settings.setWinByFinalOnly(0);
		}
		if (chckbxEnableShowSkunk.isSelected()) {
			settings.setShowSkunk(1);
		} else {
			settings.setShowSkunk(0);
		}
		if (chckbxCutThroatMode.isSelected()) {
			settings.setCutThroatMode(1);
		} else {
			settings.setCutThroatMode(0);
		}
		try {
			settings.saveControlConfig();
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
