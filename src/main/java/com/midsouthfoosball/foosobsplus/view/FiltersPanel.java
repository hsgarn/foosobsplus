/**
Copyright © 2021-2025 Hugh Garner
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class FiltersPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTeam1ScoreFilter;
	private JTextField txtTeam2ScoreFilter;
	private JTextField txtTeam1WinGameFilter;
	private JTextField txtTeam2WinGameFilter;
	private JTextField txtTeam1WinMatchFilter;
	private JTextField txtTeam2WinMatchFilter;
	private JTextField txtTeam1TimeOutFilter;
	private JTextField txtTeam2TimeOutFilter;
	private JTextField txtTeam1ResetFilter;
	private JTextField txtTeam2ResetFilter;
	private JTextField txtTeam1WarnFilter;
	private JTextField txtTeam2WarnFilter;
	private JTextField txtTeam1SwitchPositionsFilter;
	private JTextField txtTeam2SwitchPositionsFilter;
	private JTextField txtTeam1SkunkFilter;
	private JTextField txtTeam2SkunkFilter;
	private JTextField txtStartMatchFilter;
	private JTextField txtStartGameFilter;
	private JTextField txtSwitchSidesFilter;
	private JTextField txtMeatballFilter;
	private JButton btnTeam1ScoreFilter;
	private JButton btnTeam2ScoreFilter;
	private JButton btnTeam1WinGameFilter;
	private JButton btnTeam2WinGameFilter;
	private JButton btnTeam1WinMatchFilter;
	private JButton btnTeam2WinMatchFilter;
	private JButton btnTeam1TimeOutFilter;
	private JButton btnTeam2TimeOutFilter;
	private JButton btnTeam1ResetFilter;
	private JButton btnTeam2ResetFilter;
	private JButton btnTeam1WarnFilter;
	private JButton btnTeam2WarnFilter;
	private JButton btnTeam1SwitchPositionsFilter;
	private JButton btnTeam2SwitchPositionsFilter;
	private JButton btnTeam1SkunkFilter;
	private JButton btnTeam2SkunkFilter;
	private JButton btnStartMatchFilter;
	private JButton btnStartGameFilter;
	private JButton btnSwitchSidesFilter;
	private JButton btnMeatballFilter;
	private static final Logger logger = LoggerFactory.getLogger(FiltersPanel.class);
	// Create the Panel
	public FiltersPanel() throws IOException {
		setLayout();
	}
	private void restoreDefaults() {
		txtTeam1ScoreFilter.setText(Settings.getDefaultFilter("Team1Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setText(Settings.getDefaultFilter("Team2Score")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setText(Settings.getDefaultFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setText(Settings.getDefaultFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setText(Settings.getDefaultFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setText(Settings.getDefaultFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setText(Settings.getDefaultFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setText(Settings.getDefaultFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam1ResetFilter.setText(Settings.getDefaultFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setText(Settings.getDefaultFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam1WarnFilter.setText(Settings.getDefaultFilter("Team1Warn"));
		txtTeam2WarnFilter.setText(Settings.getDefaultFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setText(Settings.getDefaultFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setText(Settings.getDefaultFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setText(Settings.getDefaultFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setText(Settings.getDefaultFilter("Team2Skunk")); //$NON-NLS-1$
		txtStartMatchFilter.setText(Settings.getDefaultFilter("StartMatch")); //$NON-NLS-1$
		txtStartGameFilter.setText(Settings.getDefaultFilter("StartGame")); //$NON-NLS-1$
		txtSwitchSidesFilter.setText(Settings.getDefaultFilter("SwitchSides")); //$NON-NLS-1$
		txtMeatballFilter.setText(Settings.getDefaultFilter("Meatball")); //$NON-NLS-1$
	}
	private void revertChanges() {
		txtTeam1ScoreFilter.setText(Settings.getFiltersFilter("Team1Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setText(Settings.getFiltersFilter("Team2Score")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setText(Settings.getFiltersFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setText(Settings.getFiltersFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setText(Settings.getFiltersFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setText(Settings.getFiltersFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setText(Settings.getFiltersFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setText(Settings.getFiltersFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam1ResetFilter.setText(Settings.getFiltersFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setText(Settings.getFiltersFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam1WarnFilter.setText(Settings.getFiltersFilter("Team1Warn")); //$NON-NLS-1$
		txtTeam2WarnFilter.setText(Settings.getFiltersFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setText(Settings.getFiltersFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setText(Settings.getFiltersFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setText(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setText(Settings.getFiltersFilter("Team2Skunk")); //$NON-NLS-1$
		txtStartMatchFilter.setText(Settings.getFiltersFilter("StartMatch")); //$NON-NLS-1$
		txtStartGameFilter.setText(Settings.getFiltersFilter("StartGame")); //$NON-NLS-1$
		txtSwitchSidesFilter.setText(Settings.getFiltersFilter("SwitchSides")); //$NON-NLS-1$
		txtMeatballFilter.setText(Settings.getFiltersFilter("Meatball")); //$NON-NLS-1$
	}
	private void saveSettings() {
		Settings.setFilter("Team1Score",txtTeam1ScoreFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2Score",txtTeam2ScoreFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1WinGame",txtTeam1WinGameFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2WinGame",txtTeam2WinGameFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1WinMatch",txtTeam1WinMatchFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2WinMatch",txtTeam2WinMatchFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1TimeOut",txtTeam1TimeOutFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2TimeOut",txtTeam2TimeOutFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1Reset",txtTeam1ResetFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2Reset",txtTeam2ResetFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1Warn",txtTeam1WarnFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2Warn",txtTeam2WarnFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1SwitchPositions",txtTeam1SwitchPositionsFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2SwitchPositions",txtTeam2SwitchPositionsFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team1Skunk",txtTeam1SkunkFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Team2Skunk",txtTeam2SkunkFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("StartMatch",txtStartMatchFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("StartGame",txtStartGameFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("SwitchSides",txtSwitchSidesFilter.getText()); //$NON-NLS-1$
		Settings.setFilter("Meatball", txtMeatballFilter.getText()); //$NON-NLS-1$
		try {
			Settings.saveFilterConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
			logger.error(ex.toString());
		}
	}
	public final void setLayout() {
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblFilter = new JLabel(Messages.getString("FiltersPanel.Filter")); //$NON-NLS-1$
		lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblFilter, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblFilterCol2 = new JLabel(Messages.getString("FiltersPanel.Filter")); //$NON-NLS-1$
		lblFilterCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblFilterCol2, "cell 5 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam1ScoreFilter = new JLabel(Messages.getString("FiltersPanel.Team1Score")); //$NON-NLS-1$
		add(lblTeam1ScoreFilter, "cell 1 2,alignx right"); //$NON-NLS-1$
		txtTeam1ScoreFilter = new JTextField();
		txtTeam1ScoreFilter.setText(Settings.getFiltersFilter("Team1Score")); //$NON-NLS-1$
		txtTeam1ScoreFilter.setColumns(20);
		add(txtTeam1ScoreFilter, "cell 2 2,alignx left"); //$NON-NLS-1$
		btnTeam1ScoreFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1ScoreFilter, "cell 3 2, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2ScoreFilter = new JLabel(Messages.getString("FiltersPanel.Team2Score")); //$NON-NLS-1$
		add(lblTeam2ScoreFilter, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam2ScoreFilter = new JTextField();
		txtTeam2ScoreFilter.setText(Settings.getFiltersFilter("Team2Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setColumns(20);
		add(txtTeam2ScoreFilter, "cell 2 3,alignx left"); //$NON-NLS-1$
		btnTeam2ScoreFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2ScoreFilter, "cell 3 3, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinGame")); //$NON-NLS-1$
		add(lblTeam1WinGameFilter, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1WinGameFilter = new JTextField();
		txtTeam1WinGameFilter.setText(Settings.getFiltersFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setColumns(20);
		add(txtTeam1WinGameFilter, "cell 2 4,alignx left"); //$NON-NLS-1$
		btnTeam1WinGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WinGameFilter, "cell 3 4, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinGame")); //$NON-NLS-1$
		add(lblTeam2WinGameFilter, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam2WinGameFilter = new JTextField();
		txtTeam2WinGameFilter.setText(Settings.getFiltersFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setColumns(20);
		add(txtTeam2WinGameFilter, "cell 2 5,alignx left"); //$NON-NLS-1$
		btnTeam2WinGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WinGameFilter, "cell 3 5, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinMatch")); //$NON-NLS-1$
		add(lblTeam1WinMatchFilter, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1WinMatchFilter = new JTextField();
		txtTeam1WinMatchFilter.setText(Settings.getFiltersFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setColumns(20);
		add(txtTeam1WinMatchFilter, "cell 2 6,alignx left"); //$NON-NLS-1$
		btnTeam1WinMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WinMatchFilter, "cell 3 6, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinMatch")); //$NON-NLS-1$
		add(lblTeam2WinMatchFilter, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam2WinMatchFilter = new JTextField();
		txtTeam2WinMatchFilter.setText(Settings.getFiltersFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setColumns(20);
		add(txtTeam2WinMatchFilter, "cell 2 7,alignx left"); //$NON-NLS-1$
		btnTeam2WinMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WinMatchFilter, "cell 3 7, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team1TimeOut")); //$NON-NLS-1$
		add(lblTeam1TimeOutFilter, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1TimeOutFilter = new JTextField();
		txtTeam1TimeOutFilter.setText(Settings.getFiltersFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setColumns(20);
		add(txtTeam1TimeOutFilter, "cell 2 8,alignx left"); //$NON-NLS-1$
		btnTeam1TimeOutFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1TimeOutFilter, "cell 3 8, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team2TimeOut")); //$NON-NLS-1$
		add(lblTeam2TimeOutFilter, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam2TimeOutFilter = new JTextField();
		txtTeam2TimeOutFilter.setText(Settings.getFiltersFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setColumns(20);
		add(txtTeam2TimeOutFilter, "cell 2 9,alignx left"); //$NON-NLS-1$
		btnTeam2TimeOutFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2TimeOutFilter, "cell 3 9, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team1Reset")); //$NON-NLS-1$
		add(lblTeam1ResetFilter, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1ResetFilter = new JTextField();
		txtTeam1ResetFilter.setText(Settings.getFiltersFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam1ResetFilter.setColumns(20);
		add(txtTeam1ResetFilter, "cell 2 10,alignx left"); //$NON-NLS-1$
		btnTeam1ResetFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1ResetFilter, "cell 3 10, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team2Reset")); //$NON-NLS-1$
		add(lblTeam2ResetFilter, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam2ResetFilter = new JTextField();
		txtTeam2ResetFilter.setText(Settings.getFiltersFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setColumns(20);
		add(txtTeam2ResetFilter, "cell 2 11,alignx left"); //$NON-NLS-1$
		btnTeam2ResetFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2ResetFilter, "cell 3 11, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team1Warn")); //$NON-NLS-1$
		add(lblTeam1WarnFilter, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1WarnFilter = new JTextField();
		txtTeam1WarnFilter.setText(Settings.getFiltersFilter("Team1Warn")); //$NON-NLS-1$
		txtTeam1WarnFilter.setColumns(20);
		add(txtTeam1WarnFilter, "cell 2 12,alignx left"); //$NON-NLS-1$
		btnTeam1WarnFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WarnFilter, "cell 3 12, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team2Warn")); //$NON-NLS-1$
		add(lblTeam2WarnFilter, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam2WarnFilter = new JTextField();
		txtTeam2WarnFilter.setText(Settings.getFiltersFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam2WarnFilter.setColumns(20);
		add(txtTeam2WarnFilter, "cell 2 13,alignx left"); //$NON-NLS-1$
		btnTeam2WarnFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WarnFilter, "cell 3 13, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team1SwitchPositions")); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsFilter, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter = new JTextField();
		txtTeam1SwitchPositionsFilter.setText(Settings.getFiltersFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setColumns(20);
		add(txtTeam1SwitchPositionsFilter, "cell 2 14,alignx left"); //$NON-NLS-1$
		btnTeam1SwitchPositionsFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1SwitchPositionsFilter, "cell 3 14, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team2SwitchPositions")); //$NON-NLS-1$
		add(lblTeam2SwitchPositionsFilter, "cell 1 15,alignx right"); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter = new JTextField();
		txtTeam2SwitchPositionsFilter.setText(Settings.getFiltersFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setColumns(20);
		add(txtTeam2SwitchPositionsFilter, "cell 2 15,alignx left"); //$NON-NLS-1$
		btnTeam2SwitchPositionsFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2SwitchPositionsFilter, "cell 3 15, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team1Skunk")); //$NON-NLS-1$
		add(lblTeam1SkunkFilter, "cell 1 16,alignx right"); //$NON-NLS-1$
		txtTeam1SkunkFilter = new JTextField();
		txtTeam1SkunkFilter.setText(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setColumns(20);
		add(txtTeam1SkunkFilter, "cell 2 16,alignx left"); //$NON-NLS-1$
		btnTeam1SkunkFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1SkunkFilter, "cell 3 16, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team2Skunk")); //$NON-NLS-1$
		add(lblTeam2SkunkFilter, "cell 1 17,alignx right"); //$NON-NLS-1$
		txtTeam2SkunkFilter = new JTextField();
		txtTeam2SkunkFilter.setText(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setColumns(20);
		add(txtTeam2SkunkFilter, "cell 2 17,alignx left"); //$NON-NLS-1$
		btnTeam2SkunkFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2SkunkFilter, "cell 3 17, alignx left"); //$NON-NLS-1$
		JLabel lblStartMatchFilter = new JLabel(Messages.getString("FiltersPanel.StartMatch")); //$NON-NLS-1$
		add(lblStartMatchFilter, "cell 4 2,alignx right"); //$NON-NLS-1$
		txtStartMatchFilter = new JTextField();
		txtStartMatchFilter.setText(Settings.getFiltersFilter("StartMatch")); //$NON-NLS-1$
		txtStartMatchFilter.setColumns(20);
		add(txtStartMatchFilter, "cell 5 2,alignx left"); //$NON-NLS-1$
		btnStartMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnStartMatchFilter, "cell 6 2, alignx left"); //$NON-NLS-1$
		JLabel lblStartGameFilter = new JLabel(Messages.getString("FiltersPanel.StartGame")); //$NON-NLS-1$
		add(lblStartGameFilter, "cell 4 3,alignx right"); //$NON-NLS-1$
		txtStartGameFilter = new JTextField();
		txtStartGameFilter.setText(Settings.getFiltersFilter("StartGame")); //$NON-NLS-1$
		txtStartGameFilter.setColumns(20);
		add(txtStartGameFilter, "cell 5 3,alignx left"); //$NON-NLS-1$
		btnStartGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnStartGameFilter, "cell 6 3, alignx left"); //$NON-NLS-1$
		JLabel lblSwitchSidesFilter = new JLabel(Messages.getString("FiltersPanel.SwitchSides")); //$NON-NLS-1$
		add(lblSwitchSidesFilter, "cell 4 4,alignx right"); //$NON-NLS-1$
		txtSwitchSidesFilter = new JTextField();
		txtSwitchSidesFilter.setText(Settings.getFiltersFilter("SwitchSides")); //$NON-NLS-1$
		txtSwitchSidesFilter.setColumns(20);
		add(txtSwitchSidesFilter, "cell 5 4,alignx left"); //$NON-NLS-1$
		btnSwitchSidesFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnSwitchSidesFilter, "cell 6 4, alignx left"); //$NON-NLS-1$
		JLabel lblMeatballFilter = new JLabel(Messages.getString("FiltersPanel.Meatball")); //$NON-NLS-1$
		add(lblMeatballFilter, "cell 4 5,alignx right"); //$NON-NLS-1$
		txtMeatballFilter = new JTextField();
		txtMeatballFilter.setText(Settings.getFiltersFilter("Meatball")); //$NON-NLS-1$
		txtMeatballFilter.setColumns(20);
		add(txtMeatballFilter, "cell 5 5,alignx left"); //$NON-NLS-1$
		btnMeatballFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnMeatballFilter, "cell 6 5, alignx left"); //$NON-NLS-1$
		JButton btnApplyFilters = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		btnApplyFilters.addActionListener((ActionEvent e) -> {
                    saveSettings();
                });
		add(btnApplyFilters, "cell 1 19,alignx center"); //$NON-NLS-1$
		JButton btnSaveFilters = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveFilters.addActionListener((ActionEvent e) -> {
                    saveSettings();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnSaveFilters, "cell 2 19,alignx center"); //$NON-NLS-1$
		JButton btnCancelFilters = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelFilters.addActionListener((ActionEvent e) -> {
                    revertChanges();
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    win.dispose();
                });
		add(btnCancelFilters, "cell 4 19,alignx center"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}
	////// Listeners  //////
	public void addTeam1ScoreFilterListener(ActionListener listenForBtnTeam1ScoreFilter) {
		btnTeam1ScoreFilter.addActionListener(listenForBtnTeam1ScoreFilter);
	}
	public void addTeam2ScoreFilterListener(ActionListener listenForBtnTeam2ScoreFilter) {
		btnTeam2ScoreFilter.addActionListener(listenForBtnTeam2ScoreFilter);
	}
	public void addTeam1WinGameFilterListener(ActionListener listenForBtnTeam1WinGameFilter) {
		btnTeam1WinGameFilter.addActionListener(listenForBtnTeam1WinGameFilter);
	}
	public void addTeam2WinGameFilterListener(ActionListener listenForBtnTeam2WinGameFilter) {
		btnTeam2WinGameFilter.addActionListener(listenForBtnTeam2WinGameFilter);
	}
	public void addTeam1WinMatchFilterListener(ActionListener listenForBtnTeam1WinMatchFilter) {
		btnTeam1WinMatchFilter.addActionListener(listenForBtnTeam1WinMatchFilter);
	}
	public void addTeam2WinMatchFilterListener(ActionListener listenForBtnTeam2WinMatchFilter) {
		btnTeam2WinMatchFilter.addActionListener(listenForBtnTeam2WinMatchFilter);
	}
	public void addTeam1TimeOutFilterListener(ActionListener listenForBtnTeam1TimeOutFilter) {
		btnTeam1TimeOutFilter.addActionListener(listenForBtnTeam1TimeOutFilter);
	}
	public void addTeam2TimeOutFilterListener(ActionListener listenForBtnTeam2TimeOutFilter) {
		btnTeam2TimeOutFilter.addActionListener(listenForBtnTeam2TimeOutFilter);
	}
	public void addTeam1ResetFilterListener(ActionListener listenForBtnTeam1ResetFilter) {
		btnTeam1ResetFilter.addActionListener(listenForBtnTeam1ResetFilter);
	}
	public void addTeam2ResetFilterListener(ActionListener listenForBtnTeam2ResetFilter) {
		btnTeam2ResetFilter.addActionListener(listenForBtnTeam2ResetFilter);
	}
	public void addTeam1WarnFilterListener(ActionListener listenForBtnTeam1WarnFilter) {
		btnTeam1WarnFilter.addActionListener(listenForBtnTeam1WarnFilter);
	}
	public void addTeam2WarnFilterListener(ActionListener listenForBtnTeam2WarnFilter) {
		btnTeam2WarnFilter.addActionListener(listenForBtnTeam2WarnFilter);
	}
	public void addTeam1SwitchPositionsFilterListener(ActionListener listenForBtnTeam1SwitchPositionsFilter) {
		btnTeam1SwitchPositionsFilter.addActionListener(listenForBtnTeam1SwitchPositionsFilter);
	}
	public void addTeam2SwitchPositionsFilterListener(ActionListener listenForBtnTeam2SwitchPositionsFilter) {
		btnTeam2SwitchPositionsFilter.addActionListener(listenForBtnTeam2SwitchPositionsFilter);
	}
	public void addTeam1SkunkFilterListener(ActionListener listenForBtnTeam1SkunkFilter) {
		btnTeam1SkunkFilter.addActionListener(listenForBtnTeam1SkunkFilter);
	}
	public void addTeam2SkunkFilterListener(ActionListener listenForBtnTeam2SkunkFilter) {
		btnTeam2SkunkFilter.addActionListener(listenForBtnTeam2SkunkFilter);
	}
	public void addStartMatchFilterListener(ActionListener listenForBtnStartMatchFilter) {
		btnStartMatchFilter.addActionListener(listenForBtnStartMatchFilter);
	}
	public void addStartGameFilterListener(ActionListener listenForBtnStartGameFilter) {
		btnStartGameFilter.addActionListener(listenForBtnStartGameFilter);
	}
	public void addSwitchSidesFilterListener(ActionListener listenForBtnSwitchSidesFilter) {
		btnSwitchSidesFilter.addActionListener(listenForBtnSwitchSidesFilter);
	}
	public void addMeatballFilterListener(ActionListener listenForBtnMeatballFilter) {
		btnMeatballFilter.addActionListener(listenForBtnMeatballFilter);
	}
}