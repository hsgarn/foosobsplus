package com.midsouthfoosball.foosobsplus.view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
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
	private JFormattedTextField formattedTxtPath;
	OBSInterface obsInterface;
	private String defaultFilePath = "c:\\temp"; //$NON-NLS-1$
	
	// Create the Panel

	public FiltersPanel(Settings settings, OBSInterface obsInterface) throws IOException {
		this.obsInterface = obsInterface;

		setLayout(settings);

	}

	private void restoreDefaults(Settings settings) {
		txtTeam1ScoreFilter.setText(settings.getDefaultTeam1ScoreFilter());
		txtTeam2ScoreFilter.setText(settings.getDefaultTeam2ScoreFilter());
		txtTeam1WinGameFilter.setText(settings.getDefaultTeam1WinGameFilter());
		txtTeam2WinGameFilter.setText(settings.getDefaultTeam2WinGameFilter());
		txtTeam1WinMatchFilter.setText(settings.getDefaultTeam1WinMatchFilter());
		txtTeam2WinMatchFilter.setText(settings.getDefaultTeam2WinMatchFilter());
		txtTeam1TimeOutFilter.setText(settings.getDefaultTeam1TimeOutFilter());
		txtTeam2TimeOutFilter.setText(settings.getDefaultTeam2TimeOutFilter());
		txtTeam1ResetFilter.setText(settings.getDefaultTeam1ResetFilter());
		txtTeam2ResetFilter.setText(settings.getDefaultTeam2ResetFilter());
		txtTeam1WarnFilter.setText(settings.getDefaultTeam1WarnFilter());
		txtTeam2WarnFilter.setText(settings.getDefaultTeam2WarnFilter());
		txtTeam1SwitchPositionsFilter.setText(settings.getDefaultTeam1SwitchPositionsFilter());
		txtTeam2SwitchPositionsFilter.setText(settings.getDefaultTeam2SwitchPositionsFilter());
		txtTeam1SkunkFilter.setText(settings.getDefaultTeam1SkunkFilter());
		txtTeam1SkunkFilter.setText(settings.getDefaultTeam2SkunkFilter());
		txtStartMatchFilter.setText(settings.getDefaultStartMatchFilter());
		txtStartGameFilter.setText(settings.getDefaultStartGameFilter());
		txtSwitchSidesFilter.setText(settings.getDefaultSwitchSidesFilter());
	}
	
	private void saveSettings(Settings settings) {
		settings.setTeam1ScoreFilter(txtTeam1ScoreFilter.getText());
		settings.setTeam2ScoreFilter(txtTeam2ScoreFilter.getText());
		settings.setTeam1WinGameFilter(txtTeam1WinGameFilter.getText());
		settings.setTeam2WinGameFilter(txtTeam2WinGameFilter.getText());
		settings.setTeam1WinMatchFilter(txtTeam1WinMatchFilter.getText());
		settings.setTeam2WinMatchFilter(txtTeam2WinMatchFilter.getText());
		settings.setTeam1TimeOutFilter(txtTeam1TimeOutFilter.getText());
		settings.setTeam2TimeOutFilter(txtTeam2TimeOutFilter.getText());
		settings.setTeam1ResetFilter(txtTeam1ResetFilter.getText());
		settings.setTeam2ResetFilter(txtTeam2ResetFilter.getText());
		settings.setTeam1WarnFilter(txtTeam1WarnFilter.getText());
		settings.setTeam2WarnFilter(txtTeam2WarnFilter.getText());
		settings.setTeam1SwitchPositionsFilter(txtTeam1SwitchPositionsFilter.getText());
		settings.setTeam2SwitchPositionsFilter(txtTeam2SwitchPositionsFilter.getText());
		settings.setTeam1SkunkFilter(txtTeam1SkunkFilter.getText());
		settings.setTeam2SkunkFilter(txtTeam2SkunkFilter.getText());
		settings.setStartMatchFilter(txtStartMatchFilter.getText());
		settings.setStartGameFilter(txtStartGameFilter.getText());
		settings.setSwitchSidesFilter(txtSwitchSidesFilter.getText());
		try {
			settings.saveFilterConfig();
		} catch (IOException ex) {
			System.out.print(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
		}
	}
	public void setLayout(Settings settings) {
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][174.00,grow][27.00][91.00][grow][][][grow][14.00][][grow]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		JButton btnSelectPath = new JButton(Messages.getString("FiltersPanel.SelectPath")); //$NON-NLS-1$
		btnSelectPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File(formattedTxtPath.getText()));
				chooser.setDialogTitle(Messages.getString("FiltersPanel.SelectDirectoryForPath")); //$NON-NLS-1$
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				int returnVal = chooser.showOpenDialog(FiltersPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (chooser.getSelectedFile().exists()) {
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					} else {
						String directoryName = chooser.getSelectedFile().getAbsolutePath();
						if(!Files.exists(Paths.get(directoryName))) {
							try {
								Files.createDirectory(Paths.get(directoryName));
							} catch (IOException e1) {
								System.out.println(Messages.getString("Errors.ErrorCreatingDirectory") + e1.getMessage()); //$NON-NLS-1$
							}
						}
						formattedTxtPath.setText(chooser.getSelectedFile().getAbsolutePath());
					}
					obsInterface.setFilePath(formattedTxtPath.getText());
					try {
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveFilterConfig();
					} catch (IOException ex) {
						System.out.print(Messages.getString("FiltersPanel.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
					}
				}
			}
		});
		add(btnSelectPath, "cell 1 0"); //$NON-NLS-1$

		formattedTxtPath = new JFormattedTextField(defaultFilePath);
		formattedTxtPath.setText(settings.getDatapath());
		formattedTxtPath.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
		    	try {
					obsInterface.setFilePath(formattedTxtPath.getText());
					settings.setDatapath(formattedTxtPath.getText());
					settings.saveFilterConfig();
		    	} catch (IOException ex) {
		    		System.out.print(Messages.getString("FiltersPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
		    	}
			}
		});
		formattedTxtPath.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			    if (key == KeyEvent.VK_ENTER) {
			    	try {
						obsInterface.setFilePath(formattedTxtPath.getText());
						settings.setDatapath(formattedTxtPath.getText());
						settings.saveFilterConfig();
			    	} catch (IOException ex) {
			    		System.out.print(Messages.getString("FiltersPanel.ErrorSavingPropertiesFile") + ex.getMessage());		 //$NON-NLS-1$
			    	}
			    }
			}
		});
		add(formattedTxtPath, "cell 2 0 4 1,alignx left"); //$NON-NLS-1$
		formattedTxtPath.setColumns(50);

		JLabel lblFilter = new JLabel(Messages.getString("FiltersPanel.Filter")); //$NON-NLS-1$
		add(lblFilter, "cell 2 1,alignx left"); //$NON-NLS-1$

		JLabel lblFilterCol2 = new JLabel(Messages.getString("FiltersPanel.Filter")); //$NON-NLS-1$
		add(lblFilterCol2, "cell 5 1,alignx left,aligny center"); //$NON-NLS-1$
		
		JLabel lblTeam1ScoreFilter = new JLabel(Messages.getString("FiltersPanel.Team1Score", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ScoreFilter, "cell 1 2,alignx right"); //$NON-NLS-1$

		txtTeam1ScoreFilter = new JTextField();
		txtTeam1ScoreFilter.setText(settings.getTeam1ScoreFilter());
		add(txtTeam1ScoreFilter, "cell 2 2,alignx left"); //$NON-NLS-1$
		txtTeam1ScoreFilter.setColumns(20);

		JLabel lblTeam2ScoreFilter = new JLabel(Messages.getString("FiltersPanel.Team2Score", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ScoreFilter, "cell 1 3,alignx right"); //$NON-NLS-1$

		txtTeam2ScoreFilter = new JTextField();
		txtTeam2ScoreFilter.setText(settings.getTeam2ScoreFilter());
		add(txtTeam2ScoreFilter, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2ScoreFilter.setColumns(20);

		JLabel lblTeam1WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinGame", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1WinGameFilter, "cell 1 4,alignx right"); //$NON-NLS-1$

		txtTeam1WinGameFilter = new JTextField();
		txtTeam1WinGameFilter.setText(settings.getTeam1WinGameFilter());
		add(txtTeam1WinGameFilter, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam1WinGameFilter.setColumns(20);

		JLabel lblTeam2WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinGame", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2WinGameFilter, "cell 1 5,alignx right"); //$NON-NLS-1$

		txtTeam2WinGameFilter = new JTextField();
		txtTeam2WinGameFilter.setText(settings.getTeam2WinGameFilter());
		add(txtTeam2WinGameFilter, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam2WinGameFilter.setColumns(20);

		JLabel lblTeam1WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1WinMatchFilter, "cell 1 6,alignx right"); //$NON-NLS-1$

		txtTeam1WinMatchFilter = new JTextField();
		txtTeam1WinMatchFilter.setText(settings.getTeam1WinMatchFilter());
		add(txtTeam1WinMatchFilter, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setColumns(20);

		JLabel lblTeam2WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2WinMatchFilter, "cell 1 7,alignx right"); //$NON-NLS-1$

		txtTeam2WinMatchFilter = new JTextField();
		txtTeam2WinMatchFilter.setText(settings.getTeam2WinMatchFilter());
		add(txtTeam2WinMatchFilter, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setColumns(20);

		JLabel lblTeam1TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team1TimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1TimeOutFilter, "cell 1 8,alignx right"); //$NON-NLS-1$

		txtTeam1TimeOutFilter = new JTextField();
		txtTeam1TimeOutFilter.setText(settings.getTeam1TimeOutFilter());
		add(txtTeam1TimeOutFilter, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setColumns(20);

		JLabel lblTeam2TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team2TimeOut", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2TimeOutFilter, "cell 1 9,alignx right"); //$NON-NLS-1$

		txtTeam2TimeOutFilter = new JTextField();
		txtTeam2TimeOutFilter.setText(settings.getTeam2TimeOutFilter());
		add(txtTeam2TimeOutFilter, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setColumns(20);

		JLabel lblTeam1ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team1Reset", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1ResetFilter, "cell 1 10,alignx right"); //$NON-NLS-1$

		txtTeam1ResetFilter = new JTextField();
		txtTeam1ResetFilter.setText(settings.getTeam1ResetFilter());
		add(txtTeam1ResetFilter, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTeam1ResetFilter.setColumns(20);

		JLabel lblTeam2ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team2Reset", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2ResetFilter, "cell 1 11,alignx right"); //$NON-NLS-1$

		txtTeam2ResetFilter = new JTextField();
		txtTeam2ResetFilter.setText(settings.getTeam2ResetFilter());
		add(txtTeam2ResetFilter, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTeam2ResetFilter.setColumns(20);

		JLabel lblTeam1WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team1Warn", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1WarnFilter, "cell 1 12,alignx right"); //$NON-NLS-1$

		txtTeam1WarnFilter = new JTextField();
		txtTeam1WarnFilter.setText(settings.getTeam1WarnFilter());
		add(txtTeam1WarnFilter, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtTeam1WarnFilter.setColumns(20);

		JLabel lblTeam2WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team2Warn", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2WarnFilter, "cell 1 13,alignx right"); //$NON-NLS-1$

		txtTeam2WarnFilter = new JTextField();
		txtTeam2WarnFilter.setText(settings.getTeam2WarnFilter());
		add(txtTeam2WarnFilter, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtTeam2WarnFilter.setColumns(20);

		JLabel lblTeam1SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team1SwitchPositions", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsFilter, "cell 1 14,alignx right"); //$NON-NLS-1$

		txtTeam1SwitchPositionsFilter = new JTextField();
		txtTeam1SwitchPositionsFilter.setText(settings.getTeam1SwitchPositionsFilter());
		add(txtTeam1SwitchPositionsFilter, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setColumns(20);

		JLabel lblTeam2SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team2SwitchPositions", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SwitchPositionsFilter, "cell 1 15,alignx right"); //$NON-NLS-1$

		txtTeam2SwitchPositionsFilter = new JTextField();
		txtTeam2SwitchPositionsFilter.setText(settings.getTeam2SwitchPositionsFilter());
		add(txtTeam2SwitchPositionsFilter, "cell 2 15,alignx left"); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setColumns(20);

		JLabel lblTeam1SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team1Skunk", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam1SkunkFilter, "cell 1 16,alignx right"); //$NON-NLS-1$

		txtTeam1SkunkFilter = new JTextField();
		txtTeam1SkunkFilter.setText(settings.getTeam1SkunkFilter());
		add(txtTeam1SkunkFilter, "cell 2 16,alignx left"); //$NON-NLS-1$
		txtTeam1SkunkFilter.setColumns(20);

		JLabel lblTeam2SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team2Skunk", settings.getGameType())); //$NON-NLS-1$
		add(lblTeam2SkunkFilter, "cell 1 17,alignx right"); //$NON-NLS-1$

		txtTeam2SkunkFilter = new JTextField();
		txtTeam2SkunkFilter.setText(settings.getTeam2SkunkFilter());
		add(txtTeam2SkunkFilter, "cell 2 17,alignx left"); //$NON-NLS-1$
		txtTeam2SkunkFilter.setColumns(20);

		JLabel lblStartMatchFilter = new JLabel(Messages.getString("FiltersPanel.StartMatch", settings.getGameType())); //$NON-NLS-1$
		add(lblStartMatchFilter, "cell 4 2,alignx right"); //$NON-NLS-1$

		txtStartMatchFilter = new JTextField();
		txtStartMatchFilter.setText(settings.getStartMatchFilter());
		add(txtStartMatchFilter, "cell 5 2,alignx left"); //$NON-NLS-1$
		txtStartMatchFilter.setColumns(20);

		JLabel lblStartGameFilter = new JLabel(Messages.getString("FiltersPanel.StartGame", settings.getGameType())); //$NON-NLS-1$
		add(lblStartGameFilter, "cell 4 3,alignx right"); //$NON-NLS-1$

		txtStartGameFilter = new JTextField();
		txtStartGameFilter.setText(settings.getStartGameFilter());
		add(txtStartGameFilter, "cell 5 3,alignx left"); //$NON-NLS-1$
		txtStartGameFilter.setColumns(20);

		JLabel lblSwitchSidesFilter = new JLabel(Messages.getString("FiltersPanel.SwitchSides", settings.getGameType())); //$NON-NLS-1$
		add(lblSwitchSidesFilter, "cell 4 4,alignx right"); //$NON-NLS-1$

		txtSwitchSidesFilter = new JTextField();
		txtSwitchSidesFilter.setText(settings.getSwitchSidesFilter());
		add(txtSwitchSidesFilter, "cell 5 4,alignx left"); //$NON-NLS-1$
		txtSwitchSidesFilter.setColumns(20);

		JButton btnSaveFilters = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		btnSaveFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSettings(settings);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnSaveFilters, "cell 2 19,alignx center"); //$NON-NLS-1$

		JButton btnCancelFilters = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancelFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(btnCancelFilters, "cell 4 19,alignx center"); //$NON-NLS-1$

		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restoreDefaults(settings);
			}
		});
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
	}

}
