/**
Copyright © 2021-2026 Hugh Garner
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.Settings;

import net.miginfocom.swing.MigLayout;

public class FiltersPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> txtTeam1ScoreFilter;
	private JComboBox<String> txtTeam2ScoreFilter;
	private JComboBox<String> txtTeam1WinGameFilter;
	private JComboBox<String> txtTeam2WinGameFilter;
	private JComboBox<String> txtTeam1WinMatchFilter;
	private JComboBox<String> txtTeam2WinMatchFilter;
	private JComboBox<String> txtTeam1TimeOutFilter;
	private JComboBox<String> txtTeam2TimeOutFilter;
	private JComboBox<String> txtTeam1ResetFilter;
	private JComboBox<String> txtTeam2ResetFilter;
	private JComboBox<String> txtTeam1WarnFilter;
	private JComboBox<String> txtTeam2WarnFilter;
	private JComboBox<String> txtTeam1SwitchPositionsFilter;
	private JComboBox<String> txtTeam2SwitchPositionsFilter;
	private JComboBox<String> txtTeam1SkunkFilter;
	private JComboBox<String> txtTeam2SkunkFilter;
	private JComboBox<String> txtStartMatchFilter;
	private JComboBox<String> txtStartGameFilter;
	private JComboBox<String> txtSwitchSidesFilter;
	private JComboBox<String> txtMeatballFilter;
	private JButton btnFetchFilters;
	private List<JComboBox<String>> allFilterCombos;
	private List<String> obsFiltersList = new ArrayList<>();
	private boolean filterUpdating = false;
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
	private final Map<Component, Object> snapshot = new HashMap<>();
	private BooleanSupplier saveCallback = () -> { saveSettings(); return true; };
	private static final Logger logger = LoggerFactory.getLogger(FiltersPanel.class);
	// Create the Panel
	public FiltersPanel() throws IOException {
		setLayout();
		allFilterCombos = new ArrayList<>(List.of(
			txtTeam1ScoreFilter, txtTeam2ScoreFilter,
			txtTeam1WinGameFilter, txtTeam2WinGameFilter,
			txtTeam1WinMatchFilter, txtTeam2WinMatchFilter,
			txtTeam1TimeOutFilter, txtTeam2TimeOutFilter,
			txtTeam1ResetFilter, txtTeam2ResetFilter,
			txtTeam1WarnFilter, txtTeam2WarnFilter,
			txtTeam1SwitchPositionsFilter, txtTeam2SwitchPositionsFilter,
			txtTeam1SkunkFilter, txtTeam2SkunkFilter,
			txtStartMatchFilter, txtStartGameFilter,
			txtSwitchSidesFilter, txtMeatballFilter
		));
		for (JComboBox<String> combo : allFilterCombos) {
			combo.setEditable(true);
			setupComboFiltering(combo);
		}
		revertChanges();
	}
	private void setupComboFiltering(JComboBox<String> combo) {
		JTextComponent editor = (JTextComponent) combo.getEditor().getEditorComponent();
		editor.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void insertUpdate(DocumentEvent e) { filter(); }
			@Override public void removeUpdate(DocumentEvent e) { filter(); }
			@Override public void changedUpdate(DocumentEvent e) { filter(); }
			private void filter() {
				if (filterUpdating) return;
				filterUpdating = true;
				String text = editor.getText();
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
				for (String item : obsFiltersList) {
					if (item.toLowerCase().contains(text.toLowerCase())) {
						model.addElement(item);
					}
				}
				combo.setModel(model);
				combo.setSelectedItem(text);
				combo.showPopup();
				filterUpdating = false;
			}
		});
	}
	private String getComboText(JComboBox<String> combo) {
		Object item = combo.getEditor().getItem();
		return item != null ? item.toString() : "";
	}
	public void setSaveCallback(BooleanSupplier callback) { this.saveCallback = callback; }
	private void takeSnapshot() { snapshot.clear(); snapshotOf(this); }
	private void snapshotOf(Container container) {
		for (Component c : container.getComponents()) {
			if (c instanceof JComboBox<?> combo) {
				snapshot.put(combo, combo.isEditable() ? combo.getEditor().getItem() : combo.getSelectedItem());
			} else if (c instanceof JCheckBox cb) {
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
			if (c instanceof JComboBox<?> combo) {
				Object cur = combo.isEditable() ? combo.getEditor().getItem() : combo.getSelectedItem();
				Object saved = snapshot.get(combo);
				if (saved != null && !String.valueOf(cur).equals(String.valueOf(saved))) return true;
			} else if (c instanceof JCheckBox cb) {
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
	public void populateObsFilters(List<String> filterNames) {
		SwingUtilities.invokeLater(() -> {
			obsFiltersList = new ArrayList<>(filterNames);
			filterUpdating = true;
			String[] items = filterNames.toArray(new String[0]);
			for (JComboBox<String> combo : allFilterCombos) {
				String current = getComboText(combo);
				combo.setModel(new DefaultComboBoxModel<>(items));
				combo.setSelectedItem(current);
			}
			filterUpdating = false;
		});
	}
	private void restoreDefaults() {
		filterUpdating = true;
		txtTeam1ScoreFilter.setSelectedItem(Settings.getDefaultFilter("Team1Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setSelectedItem(Settings.getDefaultFilter("Team2Score")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setSelectedItem(Settings.getDefaultFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setSelectedItem(Settings.getDefaultFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setSelectedItem(Settings.getDefaultFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setSelectedItem(Settings.getDefaultFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setSelectedItem(Settings.getDefaultFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setSelectedItem(Settings.getDefaultFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam1ResetFilter.setSelectedItem(Settings.getDefaultFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setSelectedItem(Settings.getDefaultFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam1WarnFilter.setSelectedItem(Settings.getDefaultFilter("Team1Warn")); //$NON-NLS-1$
		txtTeam2WarnFilter.setSelectedItem(Settings.getDefaultFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setSelectedItem(Settings.getDefaultFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setSelectedItem(Settings.getDefaultFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setSelectedItem(Settings.getDefaultFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setSelectedItem(Settings.getDefaultFilter("Team2Skunk")); //$NON-NLS-1$
		txtStartMatchFilter.setSelectedItem(Settings.getDefaultFilter("StartMatch")); //$NON-NLS-1$
		txtStartGameFilter.setSelectedItem(Settings.getDefaultFilter("StartGame")); //$NON-NLS-1$
		txtSwitchSidesFilter.setSelectedItem(Settings.getDefaultFilter("SwitchSides")); //$NON-NLS-1$
		txtMeatballFilter.setSelectedItem(Settings.getDefaultFilter("Meatball")); //$NON-NLS-1$
		filterUpdating = false;
		// Do NOT call takeSnapshot() here — defaults are not yet saved;
		// hasChanges() must remain true so closing prompts the user to save.
	}
	private void revertChanges() {
		filterUpdating = true;
		txtTeam1ScoreFilter.setSelectedItem(Settings.getFiltersFilter("Team1Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setSelectedItem(Settings.getFiltersFilter("Team2Score")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setSelectedItem(Settings.getFiltersFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setSelectedItem(Settings.getFiltersFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setSelectedItem(Settings.getFiltersFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setSelectedItem(Settings.getFiltersFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setSelectedItem(Settings.getFiltersFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setSelectedItem(Settings.getFiltersFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam1ResetFilter.setSelectedItem(Settings.getFiltersFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setSelectedItem(Settings.getFiltersFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam1WarnFilter.setSelectedItem(Settings.getFiltersFilter("Team1Warn")); //$NON-NLS-1$
		txtTeam2WarnFilter.setSelectedItem(Settings.getFiltersFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setSelectedItem(Settings.getFiltersFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setSelectedItem(Settings.getFiltersFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setSelectedItem(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setSelectedItem(Settings.getFiltersFilter("Team2Skunk")); //$NON-NLS-1$
		txtStartMatchFilter.setSelectedItem(Settings.getFiltersFilter("StartMatch")); //$NON-NLS-1$
		txtStartGameFilter.setSelectedItem(Settings.getFiltersFilter("StartGame")); //$NON-NLS-1$
		txtSwitchSidesFilter.setSelectedItem(Settings.getFiltersFilter("SwitchSides")); //$NON-NLS-1$
		txtMeatballFilter.setSelectedItem(Settings.getFiltersFilter("Meatball")); //$NON-NLS-1$
		filterUpdating = false;
		takeSnapshot();
	}
	private void saveSettings() {
		Settings.setFilter("Team1Score",getComboText(txtTeam1ScoreFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2Score",getComboText(txtTeam2ScoreFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1WinGame",getComboText(txtTeam1WinGameFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2WinGame",getComboText(txtTeam2WinGameFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1WinMatch",getComboText(txtTeam1WinMatchFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2WinMatch",getComboText(txtTeam2WinMatchFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1TimeOut",getComboText(txtTeam1TimeOutFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2TimeOut",getComboText(txtTeam2TimeOutFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1Reset",getComboText(txtTeam1ResetFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2Reset",getComboText(txtTeam2ResetFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1Warn",getComboText(txtTeam1WarnFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2Warn",getComboText(txtTeam2WarnFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1SwitchPositions",getComboText(txtTeam1SwitchPositionsFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2SwitchPositions",getComboText(txtTeam2SwitchPositionsFilter)); //$NON-NLS-1$
		Settings.setFilter("Team1Skunk",getComboText(txtTeam1SkunkFilter)); //$NON-NLS-1$
		Settings.setFilter("Team2Skunk",getComboText(txtTeam2SkunkFilter)); //$NON-NLS-1$
		Settings.setFilter("StartMatch",getComboText(txtStartMatchFilter)); //$NON-NLS-1$
		Settings.setFilter("StartGame",getComboText(txtStartGameFilter)); //$NON-NLS-1$
		Settings.setFilter("SwitchSides",getComboText(txtSwitchSidesFilter)); //$NON-NLS-1$
		Settings.setFilter("Meatball", getComboText(txtMeatballFilter)); //$NON-NLS-1$
		try {
			Settings.saveFilterConfig();
		} catch (IOException ex) {
			logger.error(Messages.getString("Errors.ErrorSavingPropertiesFile") + ex.getMessage()); //$NON-NLS-1$
			logger.error(ex.toString());
		}
		takeSnapshot();
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
		txtTeam1ScoreFilter = new JComboBox<>();
		txtTeam1ScoreFilter.setSelectedItem(Settings.getFiltersFilter("Team1Score")); //$NON-NLS-1$
		txtTeam1ScoreFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1ScoreFilter, "cell 2 2,alignx left"); //$NON-NLS-1$
		btnTeam1ScoreFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1ScoreFilter, "cell 3 2, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2ScoreFilter = new JLabel(Messages.getString("FiltersPanel.Team2Score")); //$NON-NLS-1$
		add(lblTeam2ScoreFilter, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam2ScoreFilter = new JComboBox<>();
		txtTeam2ScoreFilter.setSelectedItem(Settings.getFiltersFilter("Team2Score")); //$NON-NLS-1$
		txtTeam2ScoreFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2ScoreFilter, "cell 2 3,alignx left"); //$NON-NLS-1$
		btnTeam2ScoreFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2ScoreFilter, "cell 3 3, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinGame")); //$NON-NLS-1$
		add(lblTeam1WinGameFilter, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1WinGameFilter = new JComboBox<>();
		txtTeam1WinGameFilter.setSelectedItem(Settings.getFiltersFilter("Team1WinGame")); //$NON-NLS-1$
		txtTeam1WinGameFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1WinGameFilter, "cell 2 4,alignx left"); //$NON-NLS-1$
		btnTeam1WinGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WinGameFilter, "cell 3 4, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WinGameFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinGame")); //$NON-NLS-1$
		add(lblTeam2WinGameFilter, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam2WinGameFilter = new JComboBox<>();
		txtTeam2WinGameFilter.setSelectedItem(Settings.getFiltersFilter("Team2WinGame")); //$NON-NLS-1$
		txtTeam2WinGameFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2WinGameFilter, "cell 2 5,alignx left"); //$NON-NLS-1$
		btnTeam2WinGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WinGameFilter, "cell 3 5, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team1WinMatch")); //$NON-NLS-1$
		add(lblTeam1WinMatchFilter, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1WinMatchFilter = new JComboBox<>();
		txtTeam1WinMatchFilter.setSelectedItem(Settings.getFiltersFilter("Team1WinMatch")); //$NON-NLS-1$
		txtTeam1WinMatchFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1WinMatchFilter, "cell 2 6,alignx left"); //$NON-NLS-1$
		btnTeam1WinMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WinMatchFilter, "cell 3 6, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WinMatchFilter = new JLabel(Messages.getString("FiltersPanel.Team2WinMatch")); //$NON-NLS-1$
		add(lblTeam2WinMatchFilter, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam2WinMatchFilter = new JComboBox<>();
		txtTeam2WinMatchFilter.setSelectedItem(Settings.getFiltersFilter("Team2WinMatch")); //$NON-NLS-1$
		txtTeam2WinMatchFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2WinMatchFilter, "cell 2 7,alignx left"); //$NON-NLS-1$
		btnTeam2WinMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WinMatchFilter, "cell 3 7, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team1TimeOut")); //$NON-NLS-1$
		add(lblTeam1TimeOutFilter, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1TimeOutFilter = new JComboBox<>();
		txtTeam1TimeOutFilter.setSelectedItem(Settings.getFiltersFilter("Team1TimeOut")); //$NON-NLS-1$
		txtTeam1TimeOutFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1TimeOutFilter, "cell 2 8,alignx left"); //$NON-NLS-1$
		btnTeam1TimeOutFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1TimeOutFilter, "cell 3 8, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2TimeOutFilter = new JLabel(Messages.getString("FiltersPanel.Team2TimeOut")); //$NON-NLS-1$
		add(lblTeam2TimeOutFilter, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam2TimeOutFilter = new JComboBox<>();
		txtTeam2TimeOutFilter.setSelectedItem(Settings.getFiltersFilter("Team2TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOutFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2TimeOutFilter, "cell 2 9,alignx left"); //$NON-NLS-1$
		btnTeam2TimeOutFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2TimeOutFilter, "cell 3 9, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team1Reset")); //$NON-NLS-1$
		add(lblTeam1ResetFilter, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1ResetFilter = new JComboBox<>();
		txtTeam1ResetFilter.setSelectedItem(Settings.getFiltersFilter("Team1Reset")); //$NON-NLS-1$
		txtTeam1ResetFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1ResetFilter, "cell 2 10,alignx left"); //$NON-NLS-1$
		btnTeam1ResetFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1ResetFilter, "cell 3 10, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2ResetFilter = new JLabel(Messages.getString("FiltersPanel.Team2Reset")); //$NON-NLS-1$
		add(lblTeam2ResetFilter, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam2ResetFilter = new JComboBox<>();
		txtTeam2ResetFilter.setSelectedItem(Settings.getFiltersFilter("Team2Reset")); //$NON-NLS-1$
		txtTeam2ResetFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2ResetFilter, "cell 2 11,alignx left"); //$NON-NLS-1$
		btnTeam2ResetFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2ResetFilter, "cell 3 11, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team1Warn")); //$NON-NLS-1$
		add(lblTeam1WarnFilter, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1WarnFilter = new JComboBox<>();
		txtTeam1WarnFilter.setSelectedItem(Settings.getFiltersFilter("Team1Warn")); //$NON-NLS-1$
		txtTeam1WarnFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1WarnFilter, "cell 2 12,alignx left"); //$NON-NLS-1$
		btnTeam1WarnFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1WarnFilter, "cell 3 12, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2WarnFilter = new JLabel(Messages.getString("FiltersPanel.Team2Warn")); //$NON-NLS-1$
		add(lblTeam2WarnFilter, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam2WarnFilter = new JComboBox<>();
		txtTeam2WarnFilter.setSelectedItem(Settings.getFiltersFilter("Team2Warn")); //$NON-NLS-1$
		txtTeam2WarnFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2WarnFilter, "cell 2 13,alignx left"); //$NON-NLS-1$
		btnTeam2WarnFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2WarnFilter, "cell 3 13, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team1SwitchPositions")); //$NON-NLS-1$
		add(lblTeam1SwitchPositionsFilter, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter = new JComboBox<>();
		txtTeam1SwitchPositionsFilter.setSelectedItem(Settings.getFiltersFilter("Team1SwitchPositions")); //$NON-NLS-1$
		txtTeam1SwitchPositionsFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1SwitchPositionsFilter, "cell 2 14,alignx left"); //$NON-NLS-1$
		btnTeam1SwitchPositionsFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1SwitchPositionsFilter, "cell 3 14, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2SwitchPositionsFilter = new JLabel(Messages.getString("FiltersPanel.Team2SwitchPositions")); //$NON-NLS-1$
		add(lblTeam2SwitchPositionsFilter, "cell 1 15,alignx right"); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter = new JComboBox<>();
		txtTeam2SwitchPositionsFilter.setSelectedItem(Settings.getFiltersFilter("Team2SwitchPositions")); //$NON-NLS-1$
		txtTeam2SwitchPositionsFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2SwitchPositionsFilter, "cell 2 15,alignx left"); //$NON-NLS-1$
		btnTeam2SwitchPositionsFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2SwitchPositionsFilter, "cell 3 15, alignx left"); //$NON-NLS-1$
		JLabel lblTeam1SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team1Skunk")); //$NON-NLS-1$
		add(lblTeam1SkunkFilter, "cell 1 16,alignx right"); //$NON-NLS-1$
		txtTeam1SkunkFilter = new JComboBox<>();
		txtTeam1SkunkFilter.setSelectedItem(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam1SkunkFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam1SkunkFilter, "cell 2 16,alignx left"); //$NON-NLS-1$
		btnTeam1SkunkFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam1SkunkFilter, "cell 3 16, alignx left"); //$NON-NLS-1$
		JLabel lblTeam2SkunkFilter = new JLabel(Messages.getString("FiltersPanel.Team2Skunk")); //$NON-NLS-1$
		add(lblTeam2SkunkFilter, "cell 1 17,alignx right"); //$NON-NLS-1$
		txtTeam2SkunkFilter = new JComboBox<>();
		txtTeam2SkunkFilter.setSelectedItem(Settings.getFiltersFilter("Team1Skunk")); //$NON-NLS-1$
		txtTeam2SkunkFilter.setPrototypeDisplayValue("                    ");
		add(txtTeam2SkunkFilter, "cell 2 17,alignx left"); //$NON-NLS-1$
		btnTeam2SkunkFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnTeam2SkunkFilter, "cell 3 17, alignx left"); //$NON-NLS-1$
		JLabel lblStartMatchFilter = new JLabel(Messages.getString("FiltersPanel.StartMatch")); //$NON-NLS-1$
		add(lblStartMatchFilter, "cell 4 2,alignx right"); //$NON-NLS-1$
		txtStartMatchFilter = new JComboBox<>();
		txtStartMatchFilter.setSelectedItem(Settings.getFiltersFilter("StartMatch")); //$NON-NLS-1$
		txtStartMatchFilter.setPrototypeDisplayValue("                    ");
		add(txtStartMatchFilter, "cell 5 2,alignx left"); //$NON-NLS-1$
		btnStartMatchFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnStartMatchFilter, "cell 6 2, alignx left"); //$NON-NLS-1$
		JLabel lblStartGameFilter = new JLabel(Messages.getString("FiltersPanel.StartGame")); //$NON-NLS-1$
		add(lblStartGameFilter, "cell 4 3,alignx right"); //$NON-NLS-1$
		txtStartGameFilter = new JComboBox<>();
		txtStartGameFilter.setSelectedItem(Settings.getFiltersFilter("StartGame")); //$NON-NLS-1$
		txtStartGameFilter.setPrototypeDisplayValue("                    ");
		add(txtStartGameFilter, "cell 5 3,alignx left"); //$NON-NLS-1$
		btnStartGameFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnStartGameFilter, "cell 6 3, alignx left"); //$NON-NLS-1$
		JLabel lblSwitchSidesFilter = new JLabel(Messages.getString("FiltersPanel.SwitchSides")); //$NON-NLS-1$
		add(lblSwitchSidesFilter, "cell 4 4,alignx right"); //$NON-NLS-1$
		txtSwitchSidesFilter = new JComboBox<>();
		txtSwitchSidesFilter.setSelectedItem(Settings.getFiltersFilter("SwitchSides")); //$NON-NLS-1$
		txtSwitchSidesFilter.setPrototypeDisplayValue("                    ");
		add(txtSwitchSidesFilter, "cell 5 4,alignx left"); //$NON-NLS-1$
		btnSwitchSidesFilter = new JButton(Messages.getString("FiltersPanel.Test")); //$NON-NLS-1$
		add(btnSwitchSidesFilter, "cell 6 4, alignx left"); //$NON-NLS-1$
		JLabel lblMeatballFilter = new JLabel(Messages.getString("FiltersPanel.Meatball")); //$NON-NLS-1$
		add(lblMeatballFilter, "cell 4 5,alignx right"); //$NON-NLS-1$
		txtMeatballFilter = new JComboBox<>();
		txtMeatballFilter.setSelectedItem(Settings.getFiltersFilter("Meatball")); //$NON-NLS-1$
		txtMeatballFilter.setPrototypeDisplayValue("                    ");
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
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    confirmClose(win);
                });
		add(btnCancelFilters, "cell 4 19,alignx center"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 19,alignx center"); //$NON-NLS-1$
		btnFetchFilters = new JButton(Messages.getString("FiltersPanel.FetchFilters")); //$NON-NLS-1$
		add(btnFetchFilters, "cell 6 19,alignx left"); //$NON-NLS-1$
	}
	////// Listeners  //////
	public void addFetchFiltersListener(ActionListener listenForBtnFetchFilters) {
		btnFetchFilters.addActionListener(listenForBtnFetchFilters);
	}
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