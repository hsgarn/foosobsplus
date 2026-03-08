/**
Copyright © 2023-2026 Hugh Garner
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class StatSourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String>txtTeam1PassAttempts;
	private JComboBox<String>txtTeam2PassAttempts;
	private JComboBox<String>txtTeam3PassAttempts;
	private JComboBox<String>txtTeam1PassCompletes;
	private JComboBox<String>txtTeam2PassCompletes;
	private JComboBox<String>txtTeam3PassCompletes;
	private JComboBox<String>txtTeam1PassPercent;
	private JComboBox<String>txtTeam2PassPercent;
	private JComboBox<String>txtTeam3PassPercent;
	private JComboBox<String>txtTeam1ShotAttempts;
	private JComboBox<String>txtTeam2ShotAttempts;
	private JComboBox<String>txtTeam3ShotAttempts;
	private JComboBox<String>txtTeam1ShotCompletes;
	private JComboBox<String>txtTeam2ShotCompletes;
	private JComboBox<String>txtTeam3ShotCompletes;
	private JComboBox<String>txtTeam1ShotPercent;
	private JComboBox<String>txtTeam2ShotPercent;
	private JComboBox<String>txtTeam3ShotPercent;
	private JComboBox<String>txtTeam1ClearAttempts;
	private JComboBox<String>txtTeam2ClearAttempts;
	private JComboBox<String>txtTeam3ClearAttempts;
	private JComboBox<String>txtTeam1ClearCompletes;
	private JComboBox<String>txtTeam2ClearCompletes;
	private JComboBox<String>txtTeam3ClearCompletes;
	private JComboBox<String>txtTeam1ClearPercent;
	private JComboBox<String>txtTeam2ClearPercent;
	private JComboBox<String>txtTeam3ClearPercent;
	private JComboBox<String>txtTeam1TwoBarPassAttempts;
	private JComboBox<String>txtTeam2TwoBarPassAttempts;
	private JComboBox<String>txtTeam3TwoBarPassAttempts;
	private JComboBox<String>txtTeam1TwoBarPassCompletes;
	private JComboBox<String>txtTeam2TwoBarPassCompletes;
	private JComboBox<String>txtTeam3TwoBarPassCompletes;
	private JComboBox<String>txtTeam1TwoBarPassPercent;
	private JComboBox<String>txtTeam2TwoBarPassPercent;
	private JComboBox<String>txtTeam3TwoBarPassPercent;
	private JComboBox<String>txtTeam1Aces;
	private JComboBox<String>txtTeam2Aces;
	private JComboBox<String>txtTeam3Aces;
	private JComboBox<String>txtTeam1Stuffs;
	private JComboBox<String>txtTeam2Stuffs;
	private JComboBox<String>txtTeam3Stuffs;
	private JComboBox<String>txtTeam1Breaks;
	private JComboBox<String>txtTeam2Breaks;
	private JComboBox<String>txtTeam3Breaks;
	private JComboBox<String>txtTeam1Scoring;
	private JComboBox<String>txtTeam2Scoring;
	private JComboBox<String>txtTeam3Scoring;
	private JComboBox<String>txtTeam1ThreeBarScoring;
	private JComboBox<String>txtTeam2ThreeBarScoring;
	private JComboBox<String>txtTeam3ThreeBarScoring;
	private JComboBox<String>txtTeam1FiveBarScoring;
	private JComboBox<String>txtTeam2FiveBarScoring;
	private JComboBox<String>txtTeam3FiveBarScoring;
	private JComboBox<String>txtTeam1TwoBarScoring;
	private JComboBox<String>txtTeam2TwoBarScoring;
	private JComboBox<String>txtTeam3TwoBarScoring;
	private JComboBox<String>txtTeam1ShotsOnGoal;
	private JComboBox<String>txtTeam2ShotsOnGoal;
	private JComboBox<String>txtTeam3ShotsOnGoal;
	private JButton btnApply;
	private JButton btnSave;
	private JButton btnFetchSources;
	private static final String TEAM1 = "1"; //$NON-NLS-1$
	private static final String TEAM2 = "2"; //$NON-NLS-1$
	private static final String TEAM3 = "3"; //$NON-NLS-1$
        private final Map<String, JComboBox<String>> sourcesMap = new HashMap<>();
	private List<String> obsSourcesList = new ArrayList<>();
	private boolean filterUpdating = false;
	private final Map<Component, Object> snapshot = new HashMap<>();
	private BooleanSupplier saveCallback = () -> saveSettings();
	private static final transient Logger logger = LoggerFactory.getLogger(StatSourcesPanel.class);
	// Create the Panel.
	public StatSourcesPanel() {
		setLayout();
		loadSourceMap();
		sourcesMap.values().forEach(combo -> {
			combo.setEditable(true);
			setupComboFiltering(combo);
		});
		revertChanges();
	}
	private void setupComboFiltering(JComboBox<String> combo) {
		JTextComponent editor = (JTextComponent) combo.getEditor().getEditorComponent();
		editor.getDocument().addDocumentListener(new DocumentListener() {
			private void filter() {
				if (filterUpdating) return;
				SwingUtilities.invokeLater(() -> {
					if (filterUpdating) return;
					filterUpdating = true;
					try {
						String text = editor.getText();
						String lower = text.toLowerCase();
						DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
						obsSourcesList.stream()
							.filter(s -> s.toLowerCase().contains(lower))
							.forEach(model::addElement);
						combo.setModel(model);
						editor.setText(text);
						if (model.getSize() > 0 && !text.isEmpty()) {
							combo.showPopup();
						} else {
							combo.hidePopup();
						}
					} finally {
						filterUpdating = false;
					}
				});
			}
			@Override public void insertUpdate(DocumentEvent e) { filter(); }
			@Override public void removeUpdate(DocumentEvent e) { filter(); }
			@Override public void changedUpdate(DocumentEvent e) { filter(); }
		});
	}
	private String getComboText(JComboBox<String> combo) {
		Object item = combo.getEditor().getItem();
		return item != null ? item.toString() : "";
	}
	private void loadSourceMap() {
		sourcesMap.put("Team1PassAttempts",txtTeam1PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2PassAttempts",txtTeam2PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3PassAttempts",txtTeam3PassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1PassCompletes",txtTeam1PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2PassCompletes",txtTeam2PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3PassCompletes",txtTeam3PassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1PassPercent",txtTeam1PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team2PassPercent",txtTeam2PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team3PassPercent",txtTeam3PassPercent); //$NON-NLS-1$
		sourcesMap.put("Team1ShotAttempts",txtTeam1ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2ShotAttempts",txtTeam2ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3ShotAttempts",txtTeam3ShotAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1ShotCompletes",txtTeam1ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2ShotCompletes",txtTeam2ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3ShotCompletes",txtTeam3ShotCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1ShotPercent",txtTeam1ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team2ShotPercent",txtTeam2ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team3ShotPercent",txtTeam3ShotPercent); //$NON-NLS-1$
		sourcesMap.put("Team1ClearAttempts",txtTeam1ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2ClearAttempts",txtTeam2ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3ClearAttempts",txtTeam3ClearAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1ClearCompletes",txtTeam1ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2ClearCompletes",txtTeam2ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3ClearCompletes",txtTeam3ClearCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1ClearPercent",txtTeam1ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team2ClearPercent",txtTeam2ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team3ClearPercent",txtTeam3ClearPercent); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassAttempts",txtTeam1TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassAttempts",txtTeam2TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassAttempts",txtTeam3TwoBarPassAttempts); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassCompletes",txtTeam1TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassCompletes",txtTeam2TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassCompletes",txtTeam3TwoBarPassCompletes); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarPassPercent",txtTeam1TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarPassPercent",txtTeam2TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarPassPercent",txtTeam3TwoBarPassPercent); //$NON-NLS-1$
		sourcesMap.put("Team1Aces",txtTeam1Aces); //$NON-NLS-1$
		sourcesMap.put("Team2Aces",txtTeam2Aces); //$NON-NLS-1$
		sourcesMap.put("Team3Aces",txtTeam3Aces); //$NON-NLS-1$
		sourcesMap.put("Team1Stuffs",txtTeam1Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team2Stuffs",txtTeam2Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team3Stuffs",txtTeam3Stuffs); //$NON-NLS-1$
		sourcesMap.put("Team1Breaks",txtTeam1Breaks); //$NON-NLS-1$
		sourcesMap.put("Team2Breaks",txtTeam2Breaks); //$NON-NLS-1$
		sourcesMap.put("Team3Breaks",txtTeam3Breaks); //$NON-NLS-1$
		sourcesMap.put("Team1Scoring",txtTeam1Scoring); //$NON-NLS-1$
		sourcesMap.put("Team2Scoring",txtTeam2Scoring); //$NON-NLS-1$
		sourcesMap.put("Team3Scoring",txtTeam3Scoring); //$NON-NLS-1$
		sourcesMap.put("Team1ThreeBarScoring",txtTeam1ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2ThreeBarScoring",txtTeam2ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3ThreeBarScoring",txtTeam3ThreeBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1FiveBarScoring",txtTeam1FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2FiveBarScoring",txtTeam2FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3FiveBarScoring",txtTeam3FiveBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1TwoBarScoring",txtTeam1TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team2TwoBarScoring",txtTeam2TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team3TwoBarScoring",txtTeam3TwoBarScoring); //$NON-NLS-1$
		sourcesMap.put("Team1ShotsOnGoal",txtTeam1ShotsOnGoal); //$NON-NLS-1$
		sourcesMap.put("Team2ShotsOnGoal",txtTeam2ShotsOnGoal); //$NON-NLS-1$
		sourcesMap.put("Team3ShotsOnGoal",txtTeam3ShotsOnGoal); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		filterUpdating = true;
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setSelectedItem(Settings.getDefaultStatsSource(sourceName));
		});
		filterUpdating = false;
	}
	private void revertChanges() {
		filterUpdating = true;
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setSelectedItem(Settings.getStatsSourceParameter(sourceName));
		});
		filterUpdating = false;
		takeSnapshot();
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		sourcesMap.forEach((sourceName, combo) -> {
			Settings.setStatsSource(sourceName, getComboText(combo));
		});
		try {
			Settings.saveStatsSourceConfig();
			okToCloseWindow = true;
			takeSnapshot();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile")); //$NON-NLS-1$
			logger.error(ex.toString());
		}
		return okToCloseWindow;
	}
	public void setSaveCallback(BooleanSupplier callback) { this.saveCallback = callback; }
	private void takeSnapshot() { snapshot.clear(); snapshotOf(this); }
	private void snapshotOf(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JComboBox<?> combo) {
				snapshot.put(combo, combo.isEditable() ? combo.getEditor().getItem() : combo.getSelectedItem());
			} else if (c instanceof Container sub) {
				snapshotOf(sub);
			}
		}
	}
	public boolean hasChanges() { return checkChangesIn(this); }
	private boolean checkChangesIn(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JComboBox<?> combo) {
				Object cur = combo.isEditable() ? combo.getEditor().getItem() : combo.getSelectedItem();
				Object saved = snapshot.get(combo);
				if (saved != null && !String.valueOf(cur).equals(String.valueOf(saved))) return true;
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
	private void setLayout() {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblTeam1Column = new JLabel(Messages.getString("StatSourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column = new JLabel(Messages.getString("StatSourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam3Column = new JLabel(Messages.getString("StatSourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol1 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol1, "cell 2 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol2 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol2, "cell 3 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol3 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamPassAttempts")); //$NON-NLS-1$
		add(lblTeamPassAttempts, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam1PassAttempts = new JComboBox<>();
		txtTeam1PassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"PassAttempts")); //$NON-NLS-1$
		txtTeam1PassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam1PassAttempts, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2PassAttempts = new JComboBox<>();
		txtTeam2PassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"PassAttempts")); //$NON-NLS-1$
		txtTeam2PassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam2PassAttempts, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam3PassAttempts = new JComboBox<>();
		txtTeam3PassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"PassAttempts")); //$NON-NLS-1$
		txtTeam3PassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam3PassAttempts, "cell 4 3,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamPassCompletes")); //$NON-NLS-1$
		add(lblTeamPassCompletes, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1PassCompletes = new JComboBox<>();
		txtTeam1PassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"PassCompletes")); //$NON-NLS-1$
		txtTeam1PassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam1PassCompletes, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam2PassCompletes = new JComboBox<>();
		txtTeam2PassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"PassCompletes")); //$NON-NLS-1$
		txtTeam2PassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam2PassCompletes, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam3PassCompletes = new JComboBox<>();
		txtTeam3PassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"PassCompletes")); //$NON-NLS-1$
		txtTeam3PassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam3PassCompletes, "cell 4 4,alignx left"); //$NON-NLS-1$
		JLabel lblTeamPassPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamPassPercent")); //$NON-NLS-1$
		add(lblTeamPassPercent, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam1PassPercent = new JComboBox<>();
		txtTeam1PassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"PassPercent")); //$NON-NLS-1$
		txtTeam1PassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam1PassPercent, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam2PassPercent = new JComboBox<>();
		txtTeam2PassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"PassPercent")); //$NON-NLS-1$
		txtTeam2PassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam2PassPercent, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtTeam3PassPercent = new JComboBox<>();
		txtTeam3PassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"PassPercent")); //$NON-NLS-1$
		txtTeam3PassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam3PassPercent, "cell 4 5,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamShotAttempts")); //$NON-NLS-1$
		add(lblTeamShotAttempts, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1ShotAttempts = new JComboBox<>();
		txtTeam1ShotAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ShotAttempts")); //$NON-NLS-1$
		txtTeam1ShotAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam1ShotAttempts, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTeam2ShotAttempts = new JComboBox<>();
		txtTeam2ShotAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ShotAttempts")); //$NON-NLS-1$
		txtTeam2ShotAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam2ShotAttempts, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtTeam3ShotAttempts = new JComboBox<>();
		txtTeam3ShotAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ShotAttempts")); //$NON-NLS-1$
		txtTeam3ShotAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam3ShotAttempts, "cell 4 6,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamShotCompletes")); //$NON-NLS-1$
		add(lblTeamShotCompletes, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam1ShotCompletes = new JComboBox<>();
		txtTeam1ShotCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ShotCompletes")); //$NON-NLS-1$
		txtTeam1ShotCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam1ShotCompletes, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtTeam2ShotCompletes = new JComboBox<>();
		txtTeam2ShotCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ShotCompletes")); //$NON-NLS-1$
		txtTeam2ShotCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam2ShotCompletes, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtTeam3ShotCompletes = new JComboBox<>();
		txtTeam3ShotCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ShotCompletes")); //$NON-NLS-1$
		txtTeam3ShotCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam3ShotCompletes, "cell 4 7,alignx left"); //$NON-NLS-1$
		JLabel lblTeamShotPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamShotPercent")); //$NON-NLS-1$
		add(lblTeamShotPercent, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1ShotPercent = new JComboBox<>();
		txtTeam1ShotPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ShotPercent")); //$NON-NLS-1$
		txtTeam1ShotPercent.setPrototypeDisplayValue("               ");
		add(txtTeam1ShotPercent, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTeam2ShotPercent = new JComboBox<>();
		txtTeam2ShotPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ShotPercent")); //$NON-NLS-1$
		txtTeam2ShotPercent.setPrototypeDisplayValue("               ");
		add(txtTeam2ShotPercent, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtTeam3ShotPercent = new JComboBox<>();
		txtTeam3ShotPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ShotPercent")); //$NON-NLS-1$
		txtTeam3ShotPercent.setPrototypeDisplayValue("               ");
		add(txtTeam3ShotPercent, "cell 4 8,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearAttempts = new JLabel(Messages.getString("StatSourcesPanel.TeamClearAttempts")); //$NON-NLS-1$
		add(lblTeamClearAttempts, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam1ClearAttempts = new JComboBox<>();
		txtTeam1ClearAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ClearAttempts")); //$NON-NLS-1$
		txtTeam1ClearAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam1ClearAttempts, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTeam2ClearAttempts = new JComboBox<>();
		txtTeam2ClearAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ClearAttempts")); //$NON-NLS-1$
		txtTeam2ClearAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam2ClearAttempts, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtTeam3ClearAttempts = new JComboBox<>();
		txtTeam3ClearAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ClearAttempts")); //$NON-NLS-1$
		txtTeam3ClearAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam3ClearAttempts, "cell 4 9,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearCompletes = new JLabel(Messages.getString("StatSourcesPanel.TeamClearCompletes")); //$NON-NLS-1$
		add(lblTeamClearCompletes, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1ClearCompletes = new JComboBox<>();
		txtTeam1ClearCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ClearCompletes")); //$NON-NLS-1$
		txtTeam1ClearCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam1ClearCompletes, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTeam2ClearCompletes = new JComboBox<>();
		txtTeam2ClearCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ClearCompletes")); //$NON-NLS-1$
		txtTeam2ClearCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam2ClearCompletes, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtTeam3ClearCompletes = new JComboBox<>();
		txtTeam3ClearCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ClearCompletes")); //$NON-NLS-1$
		txtTeam3ClearCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam3ClearCompletes, "cell 4 10,alignx left"); //$NON-NLS-1$
		JLabel lblTeamClearPercent = new JLabel(Messages.getString("StatSourcesPanel.TeamClearPercent")); //$NON-NLS-1$
		add(lblTeamClearPercent, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam1ClearPercent = new JComboBox<>();
		txtTeam1ClearPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ClearPercent")); //$NON-NLS-1$
		txtTeam1ClearPercent.setPrototypeDisplayValue("               ");
		add(txtTeam1ClearPercent, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTeam2ClearPercent = new JComboBox<>();
		txtTeam2ClearPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ClearPercent")); //$NON-NLS-1$
		txtTeam2ClearPercent.setPrototypeDisplayValue("               ");
		add(txtTeam2ClearPercent, "cell 3 11,alignx left"); //$NON-NLS-1$
		txtTeam3ClearPercent = new JComboBox<>();
		txtTeam3ClearPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ClearPercent")); //$NON-NLS-1$
		txtTeam3ClearPercent.setPrototypeDisplayValue("               ");
		add(txtTeam3ClearPercent, "cell 4 11,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassAttempts = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassAttempts")); //$NON-NLS-1$
		add(lblTeamTwoBarPassAttempts, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassAttempts = new JComboBox<>();
		txtTeam1TwoBarPassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam1TwoBarPassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam1TwoBarPassAttempts, "cell 2 12,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassAttempts = new JComboBox<>();
		txtTeam2TwoBarPassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam2TwoBarPassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam2TwoBarPassAttempts, "cell 3 12,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassAttempts = new JComboBox<>();
		txtTeam3TwoBarPassAttempts.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassAttempts")); //$NON-NLS-1$
		txtTeam3TwoBarPassAttempts.setPrototypeDisplayValue("               ");
		add(txtTeam3TwoBarPassAttempts, "cell 4 12,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassCompletes = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassCompletes")); //$NON-NLS-1$
		add(lblTeamTwoBarPassCompletes, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassCompletes = new JComboBox<>();
		txtTeam1TwoBarPassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam1TwoBarPassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam1TwoBarPassCompletes, "cell 2 13,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassCompletes = new JComboBox<>();
		txtTeam2TwoBarPassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam2TwoBarPassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam2TwoBarPassCompletes, "cell 3 13,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassCompletes = new JComboBox<>();
		txtTeam3TwoBarPassCompletes.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassCompletes")); //$NON-NLS-1$
		txtTeam3TwoBarPassCompletes.setPrototypeDisplayValue("               ");
		add(txtTeam3TwoBarPassCompletes, "cell 4 13,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarPassPercent = new JLabel(Messages.getString("StatSourcesPanel.Team2BarPassPercent")); //$NON-NLS-1$
		add(lblTeamTwoBarPassPercent, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarPassPercent = new JComboBox<>();
		txtTeam1TwoBarPassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam1TwoBarPassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam1TwoBarPassPercent, "cell 2 14,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarPassPercent = new JComboBox<>();
		txtTeam2TwoBarPassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam2TwoBarPassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam2TwoBarPassPercent, "cell 3 14,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarPassPercent = new JComboBox<>();
		txtTeam3TwoBarPassPercent.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarPassPercent")); //$NON-NLS-1$
		txtTeam3TwoBarPassPercent.setPrototypeDisplayValue("               ");
		add(txtTeam3TwoBarPassPercent, "cell 4 14,alignx left"); //$NON-NLS-1$
		JLabel lblTeam1Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column2, "cell 6 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column2, "cell 7 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam3Column2 = new JLabel(Messages.getString("StatSourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column2, "cell 8 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol4 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol4.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol5 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol5.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol5, "cell 7 2,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblSourceCol6 = new JLabel(Messages.getString("StatSourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol6.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol6, "cell 8 2,alignx left"); //$NON-NLS-1$
		JLabel lblAces = new JLabel(Messages.getString("StatSourcesPanel.Aces")); //$NON-NLS-1$
		add(lblAces, "cell 5 3,alignx right"); //$NON-NLS-1$
		txtTeam1Aces = new JComboBox<>();
		txtTeam1Aces.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"Aces")); //$NON-NLS-1$
		txtTeam1Aces.setPrototypeDisplayValue("               ");
		add(txtTeam1Aces, "cell 6 3,alignx left"); //$NON-NLS-1$
		txtTeam2Aces = new JComboBox<>();
		txtTeam2Aces.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"Aces")); //$NON-NLS-1$
		txtTeam2Aces.setPrototypeDisplayValue("               ");
		add(txtTeam2Aces, "cell 7 3,alignx left"); //$NON-NLS-1$
		txtTeam3Aces = new JComboBox<>();
		txtTeam3Aces.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"Aces")); //$NON-NLS-1$
		txtTeam3Aces.setPrototypeDisplayValue("               ");
		add(txtTeam3Aces, "cell 8 3,alignx left"); //$NON-NLS-1$
		JLabel lblStuffs = new JLabel(Messages.getString("StatSourcesPanel.Stuffs")); //$NON-NLS-1$
		add(lblStuffs, "cell 5 4,alignx right"); //$NON-NLS-1$
		txtTeam1Stuffs = new JComboBox<>();
		txtTeam1Stuffs.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"Stuffs")); //$NON-NLS-1$
		txtTeam1Stuffs.setPrototypeDisplayValue("               ");
		add(txtTeam1Stuffs, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtTeam2Stuffs = new JComboBox<>();
		txtTeam2Stuffs.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"Stuffs")); //$NON-NLS-1$
		txtTeam2Stuffs.setPrototypeDisplayValue("               ");
		add(txtTeam2Stuffs, "cell 7 4,alignx left"); //$NON-NLS-1$
		txtTeam3Stuffs = new JComboBox<>();
		txtTeam3Stuffs.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"Stuffs")); //$NON-NLS-1$
		txtTeam3Stuffs.setPrototypeDisplayValue("               ");
		add(txtTeam3Stuffs, "cell 8 4,alignx left"); //$NON-NLS-1$
		JLabel lblBreaks = new JLabel(Messages.getString("StatSourcesPanel.Breaks")); //$NON-NLS-1$
		add(lblBreaks, "cell 5 5,alignx right"); //$NON-NLS-1$
		txtTeam1Breaks = new JComboBox<>();
		txtTeam1Breaks.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"Breaks")); //$NON-NLS-1$
		txtTeam1Breaks.setPrototypeDisplayValue("               ");
		add(txtTeam1Breaks, "cell 6 5,alignx left"); //$NON-NLS-1$
		txtTeam2Breaks = new JComboBox<>();
		txtTeam2Breaks.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"Breaks")); //$NON-NLS-1$
		txtTeam2Breaks.setPrototypeDisplayValue("               ");
		add(txtTeam2Breaks, "cell 7 5,alignx left"); //$NON-NLS-1$
		txtTeam3Breaks = new JComboBox<>();
		txtTeam3Breaks.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"Breaks")); //$NON-NLS-1$
		txtTeam3Breaks.setPrototypeDisplayValue("               ");
		add(txtTeam3Breaks, "cell 8 5,alignx left"); //$NON-NLS-1$
		JLabel lblTeamScoring = new JLabel(Messages.getString("StatSourcesPanel.TeamScoring")); //$NON-NLS-1$
		add(lblTeamScoring, "cell 5 6,alignx right"); //$NON-NLS-1$
		txtTeam1Scoring = new JComboBox<>();
		txtTeam1Scoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"Scoring")); //$NON-NLS-1$
		txtTeam1Scoring.setPrototypeDisplayValue("               ");
		add(txtTeam1Scoring, "cell 6 6,alignx left"); //$NON-NLS-1$
		txtTeam2Scoring = new JComboBox<>();
		txtTeam2Scoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"Scoring")); //$NON-NLS-1$
		txtTeam2Scoring.setPrototypeDisplayValue("               ");
		add(txtTeam2Scoring, "cell 7 6,alignx left"); //$NON-NLS-1$
		txtTeam3Scoring = new JComboBox<>();
		txtTeam3Scoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"Scoring")); //$NON-NLS-1$
		txtTeam3Scoring.setPrototypeDisplayValue("               ");
		add(txtTeam3Scoring, "cell 8 6,alignx left"); //$NON-NLS-1$
		JLabel lblTeamThreeBarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team3BarScoring")); //$NON-NLS-1$
		add(lblTeamThreeBarScoring, "cell 5 7,alignx right"); //$NON-NLS-1$
		txtTeam1ThreeBarScoring = new JComboBox<>();
		txtTeam1ThreeBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam1ThreeBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam1ThreeBarScoring, "cell 6 7,alignx left"); //$NON-NLS-1$
		txtTeam2ThreeBarScoring = new JComboBox<>();
		txtTeam2ThreeBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam2ThreeBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam2ThreeBarScoring, "cell 7 7,alignx left"); //$NON-NLS-1$
		txtTeam3ThreeBarScoring = new JComboBox<>();
		txtTeam3ThreeBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ThreeBarScoring")); //$NON-NLS-1$
		txtTeam3ThreeBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam3ThreeBarScoring, "cell 8 7,alignx left"); //$NON-NLS-1$
		JLabel lblTeam5BarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team5BarScoring")); //$NON-NLS-1$
		add(lblTeam5BarScoring, "cell 5 8,alignx right"); //$NON-NLS-1$
		txtTeam1FiveBarScoring = new JComboBox<>();
		txtTeam1FiveBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam1FiveBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam1FiveBarScoring, "cell 6 8,alignx left"); //$NON-NLS-1$
		txtTeam2FiveBarScoring = new JComboBox<>();
		txtTeam2FiveBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam2FiveBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam2FiveBarScoring, "cell 7 8,alignx left"); //$NON-NLS-1$
		txtTeam3FiveBarScoring = new JComboBox<>();
		txtTeam3FiveBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"FiveBarScoring")); //$NON-NLS-1$
		txtTeam3FiveBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam3FiveBarScoring, "cell 8 8,alignx left"); //$NON-NLS-1$
		JLabel lblTeamTwoBarScoring = new JLabel(Messages.getString("StatSourcesPanel.Team2BarScoring")); //$NON-NLS-1$
		add(lblTeamTwoBarScoring, "cell 5 9,alignx right"); //$NON-NLS-1$
		txtTeam1TwoBarScoring = new JComboBox<>();
		txtTeam1TwoBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam1TwoBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam1TwoBarScoring, "cell 6 9,alignx left"); //$NON-NLS-1$
		txtTeam2TwoBarScoring = new JComboBox<>();
		txtTeam2TwoBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam2TwoBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam2TwoBarScoring, "cell 7 9,alignx left"); //$NON-NLS-1$
		txtTeam3TwoBarScoring = new JComboBox<>();
		txtTeam3TwoBarScoring.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"TwoBarScoring")); //$NON-NLS-1$
		txtTeam3TwoBarScoring.setPrototypeDisplayValue("               ");
		add(txtTeam3TwoBarScoring, "cell 8 9,alignx left"); //$NON-NLS-1$
		JLabel lblTeamSOG = new JLabel(Messages.getString("StatSourcesPanel.TeamShotsOnGoal")); //$NON-NLS-1$
		add(lblTeamSOG, "cell 5 10,alignx trailing"); //$NON-NLS-1$
		txtTeam1ShotsOnGoal = new JComboBox<>();
		txtTeam1ShotsOnGoal.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM1,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam1ShotsOnGoal.setPrototypeDisplayValue("               ");
		add(txtTeam1ShotsOnGoal, "cell 6 10,alignx left"); //$NON-NLS-1$
		txtTeam2ShotsOnGoal = new JComboBox<>();
		txtTeam2ShotsOnGoal.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM2,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam2ShotsOnGoal.setPrototypeDisplayValue("               ");
		add(txtTeam2ShotsOnGoal, "cell 7 10,alignx left"); //$NON-NLS-1$
		txtTeam3ShotsOnGoal = new JComboBox<>();
		txtTeam3ShotsOnGoal.setSelectedItem(Settings.getTeamStatsSourceParameter(TEAM3,"ShotsOnGoal")); //$NON-NLS-1$
		txtTeam3ShotsOnGoal.setPrototypeDisplayValue("               ");
		add(txtTeam3ShotsOnGoal, "cell 8 10,alignx left"); //$NON-NLS-1$
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 2 15,alignx right"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 3 15,alignx center"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    confirmClose(win);
                });
		add(btnCancel, "cell 4 15,alignx left"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 15,alignx left"); //$NON-NLS-1$
		btnFetchSources = new JButton(Messages.getString("SourcesPanel.FetchSources")); //$NON-NLS-1$
		add(btnFetchSources, "cell 6 15,alignx left"); //$NON-NLS-1$
	}
	/////// Listeners \\\\\\\
	public void addSaveListener(ActionListener listenForBtnSaveSources) {
		btnSave.addActionListener(listenForBtnSaveSources);
	}
	public void addApplyListener(ActionListener listenForBtnApplySources) {
		btnApply.addActionListener(listenForBtnApplySources);
	}
	public void addFetchSourcesListener(ActionListener listenForBtnFetchSources) {
		btnFetchSources.addActionListener(listenForBtnFetchSources);
	}
	public void populateObsSources(List<String> inputNames) {
		SwingUtilities.invokeLater(() -> {
			obsSourcesList = new ArrayList<>(inputNames);
			filterUpdating = true;
			try {
				sourcesMap.values().forEach(combo -> {
					String current = getComboText(combo);
					combo.removeAllItems();
					inputNames.forEach(combo::addItem);
					combo.setSelectedItem(current);
				});
			} finally {
				filterUpdating = false;
			}
		});
	}
}
