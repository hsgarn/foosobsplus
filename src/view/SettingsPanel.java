/**
Copyright 2020 Hugh Garner
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
package view;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class SettingsPanel extends JPanel {

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
	private JTextField txtTeam1LastScored;
	private JTextField txtTeam2LastScored;
	private JTextField txtClearLastScored;
	private JTextField txtSide1Color;
	private JTextField txtSide2Color;
	private JButton btnSave;

	public SettingsPanel(Settings settings) throws IOException {

		setLayout(new MigLayout("", "[119.00][50.00:87.00,grow,left][78.00,grow][grow][]", "[][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblParameter = new JLabel("Parameter");
		lblParameter.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblParameter, "cell 0 0,alignx right");
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblValue, "cell 1 0,alignx left");
		
		JLabel label = new JLabel("Parameter");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(label, "cell 2 0,alignx right");
		
		JLabel label_1 = new JLabel("Value");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(label_1, "cell 3 0");
		
		JLabel lblPointsToWin = new JLabel("Points to Win");
		add(lblPointsToWin, "flowx,cell 0 1,alignx right");
		
		txtPointsToWin = new JTextField();
		txtPointsToWin.setText(Integer.toString(settings.getPointsToWin()));
		add(txtPointsToWin, "cell 1 1,alignx left");
		txtPointsToWin.setColumns(10);
		
		JLabel lblShotTime = new JLabel("Shot Time");
		add(lblShotTime, "cell 2 1,alignx trailing");
		
		txtShotTime = new JTextField();
		txtShotTime.setText(Integer.toString(settings.getShotTime()));
		add(txtShotTime, "cell 3 1,growx");
		txtShotTime.setColumns(10);
		
		JLabel lblMaxWin = new JLabel("Max Win");
		add(lblMaxWin, "flowx,cell 0 2,alignx right");
		
		txtMaxWin = new JTextField();
		txtMaxWin.setText(Integer.toString(settings.getMaxWin()));
		add(txtMaxWin, "cell 1 2,alignx left");
		txtMaxWin.setColumns(10);
		
		JLabel lblPassTime = new JLabel("Pass Time");
		add(lblPassTime, "cell 2 2,alignx trailing");
		
		txtPassTime = new JTextField();
		txtPassTime.setText(Integer.toString(settings.getPassTime()));
		add(txtPassTime, "cell 3 2,growx");
		txtPassTime.setColumns(10);
		
		JLabel lblWinBy = new JLabel("Win by");
		add(lblWinBy, "cell 0 3,alignx trailing");
		
		txtWinBy = new JTextField();
		txtWinBy.setText(Integer.toString(settings.getWinBy()));
		add(txtWinBy, "cell 1 3,alignx left");
		txtWinBy.setColumns(10);
		
		JLabel lblTimeOutTime = new JLabel("Time Out Time");
		add(lblTimeOutTime, "cell 2 3,alignx trailing");
		
		txtTimeOutTime = new JTextField();
		txtTimeOutTime.setText(Integer.toString(settings.getTimeOutTime()));
		add(txtTimeOutTime, "cell 3 3,growx");
		txtTimeOutTime.setColumns(10);
		
		JLabel lblGamesToWin = new JLabel("Games to Win");
		add(lblGamesToWin, "cell 0 4,alignx trailing");
		
		txtGamesToWin = new JTextField();
		txtGamesToWin.setText(Integer.toString(settings.getGamesToWin()));
		add(txtGamesToWin, "cell 1 4,alignx left");
		txtGamesToWin.setColumns(10);
		
		JLabel lblGameTime = new JLabel("Game Time");
		add(lblGameTime, "cell 2 4,alignx trailing");
		
		txtGameTime = new JTextField();
		txtGameTime.setText(Integer.toString(settings.getGameTime()));
		add(txtGameTime, "cell 3 4,growx");
		txtGameTime.setColumns(10);
		
		JLabel lblMaxTimeOuts = new JLabel("Max Time Outs");
		add(lblMaxTimeOuts, "flowx,cell 0 5,alignx right");
		
		txtMaxTimeOuts = new JTextField();
		txtMaxTimeOuts.setText(Integer.toString(settings.getMaxTimeOuts()));
		add(txtMaxTimeOuts, "cell 1 5");
		txtMaxTimeOuts.setColumns(10);
		
		JLabel lblRecallTime = new JLabel("Recall Time (min)");
		add(lblRecallTime, "cell 2 5,alignx trailing");
		
		txtRecallTime = new JTextField();
		txtRecallTime.setText(Integer.toString(settings.getRecallTime()));
		add(txtRecallTime, "cell 3 5,growx,aligny top");
		txtRecallTime.setColumns(10);
		
		JLabel lblTeam1LastScored = new JLabel("Team 1 Last Scored");
		add(lblTeam1LastScored, "cell 0 6,alignx trailing");
		
		txtTeam1LastScored = new JTextField();
		txtTeam1LastScored.setText(settings.getTeam1LastScored());
		add(txtTeam1LastScored, "cell 1 6,growx");
		txtTeam1LastScored.setColumns(10);

		JLabel lblTeam2LastScored = new JLabel("Team 2 Last Scored");
		add(lblTeam2LastScored, "cell 2 6,alignx trailing");
		
		txtTeam2LastScored = new JTextField();
		txtTeam2LastScored.setText(settings.getTeam2LastScored());
		add(txtTeam2LastScored, "cell 3 6,growx");
		txtTeam2LastScored.setColumns(10);
		
		JLabel lblClearLastScored = new JLabel("Clear Last Scored");
		add(lblClearLastScored, "cell 0 7,alignx trailing");
		
		txtClearLastScored = new JTextField();
		txtClearLastScored.setText(settings.getClearLastScored());
		add(txtClearLastScored, "cell 1 7,growx");
		txtClearLastScored.setColumns(10);
		
		JLabel lblSide1Color = new JLabel("Team 1 Color");
		add(lblSide1Color, "cell 0 8,alignx trailing");
		
		txtSide1Color = new JTextField();
		txtSide1Color.setText(settings.getSide1Color());
		add(txtSide1Color, "cell 1 8,growx,aligny top");
		txtSide1Color.setColumns(10);
		
		JLabel lblSide2Color = new JLabel("Team 2 Color");
		add(lblSide2Color, "cell 2 8,alignx trailing");
		
		txtSide2Color = new JTextField();
		txtSide2Color.setText(settings.getSide2Color());
		add(txtSide2Color, "cell 3 8,growx");
		txtSide2Color.setColumns(10);
		
		JLabel lblAutoIncrementGame = new JLabel("Auto Increment Game");
		add(lblAutoIncrementGame, "cell 0 11,alignx right");
		
		chckbxAutoIncrementGame = new JCheckBox("");
		if (Integer.toString(settings.getAutoIncrementGame()).equals("1")) {
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		add(chckbxAutoIncrementGame, "cell 1 11");
		
		JLabel lblWinnerPrefix = new JLabel("Winner Prefix");
		add(lblWinnerPrefix, "cell 2 11,alignx center");
		
		JLabel lblWinnerSuffix = new JLabel("Winner Suffix");
		add(lblWinnerSuffix, "cell 3 11,alignx center");
		
		JLabel lblAnnounceWinner = new JLabel("Announce Winner");
		add(lblAnnounceWinner, "cell 0 12,alignx right");
		
		chckbxAnnounceWinner = new JCheckBox("");
		if (Integer.toString(settings.getAnnounceWinner()).equals("1")) {
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		add(chckbxAnnounceWinner, "cell 1 12,alignx left");
		
		txtWinnerPrefix = new JTextField();
		txtWinnerPrefix.setHorizontalAlignment(SwingConstants.RIGHT);
		txtWinnerPrefix.setText(settings.getWinnerPrefix());
		add(txtWinnerPrefix, "cell 2 12,alignx center");
		txtWinnerPrefix.setColumns(10);
		
		txtWinnerSuffix = new JTextField();
		txtWinnerSuffix.setText(settings.getWinnerSuffix());
		add(txtWinnerSuffix, "cell 3 12,growx,aligny top");
		txtWinnerSuffix.setColumns(10);
		
		JLabel lblAnnounceMeatball = new JLabel("Announce Meatball");
		add(lblAnnounceMeatball, "cell 0 13,alignx right");
		
		chckbxAnnounceMeatball = new JCheckBox("");
		if (Integer.toString(settings.getAnnounceMeatball()).equals("1")) {
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		add(chckbxAnnounceMeatball, "cell 1 13,alignx left");
		
		txtMeatball = new JTextField();
		txtMeatball.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeatball.setText(settings.getMeatball());
		add(txtMeatball, "cell 2 13,alignx center");
		txtMeatball.setColumns(10);
		
		JLabel lblShowTimeOutsUsed = new JLabel("Show Time Outs Used");
		add(lblShowTimeOutsUsed, "cell 0 14,alignx right");
		
		chckbxShowTimeOutsUsed = new JCheckBox("");
		if (Integer.toString(settings.getShowTimeOutsUsed()).equals("1")) {
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		add(chckbxShowTimeOutsUsed, "cell 1 14");
		
		JLabel lblAutoCapitalizeTeam = new JLabel("Auto Capitalize Names");
		add(lblAutoCapitalizeTeam, "cell 0 15,alignx right");
		
		chckbxAutoCapNames = new JCheckBox("");
		if (Integer.toString(settings.getAutoCapNames()).equals("1")) {
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		add(chckbxAutoCapNames, "cell 1 15");

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSave, "cell 1 18,alignx center");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancel, "cell 2 18,alignx center");
		
		JButton btnRestoreDefaults = new JButton("Restore Defaults");
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restoreDefaults(settings);
			}
		});
		btnRestoreDefaults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(btnRestoreDefaults, "cell 3 18,alignx center");
	}

	private void restoreDefaults(Settings settings) {
		txtPointsToWin.setText(Integer.toString(settings.getDefaultPointsToWin()));
		txtShotTime.setText(Integer.toString(settings.getDefaultShotTime()));
		txtMaxWin.setText(Integer.toString(settings.getDefaultMaxWin()));
		txtPassTime.setText(Integer.toString(settings.getDefaultPassTime()));
		txtWinBy.setText(Integer.toString(settings.getDefaultWinBy()));
		txtTimeOutTime.setText(Integer.toString(settings.getDefaultTimeOutTime()));
		txtGamesToWin.setText(Integer.toString(settings.getDefaultGamesToWin()));
		txtGameTime.setText(Integer.toString(settings.getDefaultGameTime()));
		txtMaxTimeOuts.setText(Integer.toString(settings.getDefaultMaxTimeOuts()));
		txtRecallTime.setText(Integer.toString(settings.getDefaultRecallTime()));
		if (Integer.toString(settings.getDefaultAutoIncrementGame()).equals("1")) {
			chckbxAutoIncrementGame.setSelected(true);
		} else {
			chckbxAutoIncrementGame.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultAnnounceWinner()).equals("1")) {
			chckbxAnnounceWinner.setSelected(true);
		} else {
			chckbxAnnounceWinner.setSelected(false);
		}
		txtWinnerPrefix.setText(settings.getDefaultWinnerPrefix());
		txtWinnerSuffix.setText(settings.getDefaultWinnerSuffix());
		if (Integer.toString(settings.getDefaultAnnounceMeatball()).equals("1")) {
			chckbxAnnounceMeatball.setSelected(true);
		} else {
			chckbxAnnounceMeatball.setSelected(false);
		}
		txtMeatball.setText(settings.getDefaultMeatball());
		if (Integer.toString(settings.getDefaultShowTimeOutsUsed()).equals("1")) {
			chckbxShowTimeOutsUsed.setSelected(true);
		} else {
			chckbxShowTimeOutsUsed.setSelected(false);
		}
		if (Integer.toString(settings.getDefaultAutoCapNames()).equals("1")) {
			chckbxAutoCapNames.setSelected(true);
		} else {
			chckbxAutoCapNames.setSelected(false);
		}
		txtTeam1LastScored.setText(settings.getDefaultTeam1LastScored());
		txtTeam2LastScored.setText(settings.getDefaultTeam2LastScored());
		txtClearLastScored.setText(settings.getDefaultClearLastScored());
		txtSide1Color.setText(settings.getDefaultSide1Color());
		txtSide2Color.setText(settings.getDefaultSide2Color());
	}
	
	private void saveSettings(Settings settings) {
    	if (isValidInteger(txtPointsToWin.getText())) {
			settings.setPointsToWin(Integer.parseInt(txtPointsToWin.getText()));
    	}
    	if (isValidInteger(txtMaxWin.getText())) {
			settings.setMaxWin(Integer.parseInt(txtMaxWin.getText()));
    	}
    	if (isValidInteger(txtWinBy.getText())) {
		settings.setWinBy(Integer.parseInt(txtWinBy.getText()));
    	}
    	if (isValidInteger(txtGamesToWin.getText())) {
		settings.setGamesToWin(Integer.parseInt(txtGamesToWin.getText()));
    	}
    	if (isValidInteger(txtMaxTimeOuts.getText())) {
    		settings.setMaxTimeOuts(Integer.parseInt(txtMaxTimeOuts.getText()));
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
		try {
			settings.saveToConfig();
		} catch (IOException ex) {
			System.out.println("Error saving properties file: " + ex.getMessage());		
		}
	}

	private boolean isValidInteger(String checkString) {
    	try {
    		Integer.parseInt(checkString);
    		return true;
    	} catch (NumberFormatException e) {
    		return false;
    	}
	}
}
