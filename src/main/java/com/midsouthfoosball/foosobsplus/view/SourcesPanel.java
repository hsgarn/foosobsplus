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

public class SourcesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> txtTeam1Name;
	private JComboBox<String> txtTeam2Name;
	private JComboBox<String> txtTeam3Name;
	private JComboBox<String> txtTeam1Forward;
	private JComboBox<String> txtTeam2Forward;
	private JComboBox<String> txtTeam3Forward;
	private JComboBox<String> txtTeam1Goalie;
	private JComboBox<String> txtTeam2Goalie;
	private JComboBox<String> txtTeam3Goalie;
	private JComboBox<String> txtTeam1Score;
	private JComboBox<String> txtTeam2Score;
	private JComboBox<String> txtTeam3Score;
	private JComboBox<String> txtTeam1GameCount;
	private JComboBox<String> txtTeam2GameCount;
	private JComboBox<String> txtTeam3GameCount;
	private JComboBox<String> txtTeam1MatchCount;
	private JComboBox<String> txtTeam2MatchCount;
	private JComboBox<String> txtTeam3MatchCount;
	private JComboBox<String> txtTeam1TimeOut;
	private JComboBox<String> txtTeam2TimeOut;
	private JComboBox<String> txtTeam3TimeOut;
	private JComboBox<String> txtTeam1Reset;
	private JComboBox<String> txtTeam2Reset;
	private JComboBox<String> txtTeam3Reset;
	private JComboBox<String> txtTeam1Warn;
	private JComboBox<String> txtTeam2Warn;
	private JComboBox<String> txtTeam3Warn;
	private JComboBox<String> txtTeam1KingSeat;
	private JComboBox<String> txtTeam2KingSeat;
	private JComboBox<String> txtTeam3KingSeat;
	private JComboBox<String> txtTeam1Game1Show;
	private JComboBox<String> txtTeam2Game1Show;
	private JComboBox<String> txtTeam3Game1Show;
	private JComboBox<String> txtTeam1Game2Show;
	private JComboBox<String> txtTeam2Game2Show;
	private JComboBox<String> txtTeam3Game2Show;
	private JComboBox<String> txtTeam1Game3Show;
	private JComboBox<String> txtTeam2Game3Show;
	private JComboBox<String> txtTeam3Game3Show;
	private JComboBox<String> txtTournament;
	private JComboBox<String> txtEvent;
	private JComboBox<String> txtTableName;
	private JComboBox<String> txtTimerInUse;
	private JComboBox<String> txtTimeRemaining;
	private JComboBox<String> txtGameTime;
	private JComboBox<String> txtMatchTime;
	private JComboBox<String> txtStreamTime;
	private JComboBox<String> txtLastScored;
	private JComboBox<String> txtMatchWinner;
	private JComboBox<String> txtMeatball;
	private JComboBox<String> txtGameResults;
	private JComboBox<String> txtShowScores;
	private JComboBox<String> txtShowTimer;
	private JComboBox<String> txtShowCutthroat;
    private JComboBox<String> txtCueBall;
    private JComboBox<String> txtOneBall;
    private JComboBox<String> txtTwoBall;
    private JComboBox<String> txtThreeBall;
    private JComboBox<String> txtFourBall;
    private JComboBox<String> txtFiveBall;
    private JComboBox<String> txtSixBall;
    private JComboBox<String> txtSevenBall;
    private JComboBox<String> txtEightBall;
    private JComboBox<String> txtNineBall;
    private JComboBox<String> txtTenBall;
    private JComboBox<String> txtElevenBall;
    private JComboBox<String> txtTwelveBall;
    private JComboBox<String> txtThirteenBall;
    private JComboBox<String> txtFourteenBall;
    private JComboBox<String> txtFifteenBall;
	private JButton btnFetchSources;
	private JButton btnApply;
	private JButton btnSave;
    private final Map<String, JComboBox<String>> sourcesMap = new HashMap<>();
	private List<String> obsSourcesList = new ArrayList<>();
	private boolean filterUpdating = false;
	private static final String TEAM1 = "1"; //$NON-NLS-1$
	private static final String TEAM2 = "2"; //$NON-NLS-1$
	private static final String TEAM3 = "3"; //$NON-NLS-1$
	private final Map<Component, Object> snapshot = new HashMap<>();
	private BooleanSupplier saveCallback = () -> saveSettings();
	private static final transient Logger logger = LoggerFactory.getLogger(SourcesPanel.class);
	// Create the Panel.
	public SourcesPanel() {
		setupLayout();
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
		sourcesMap.put("Tournament",txtTournament); //$NON-NLS-1$
		sourcesMap.put("Event",txtEvent); //$NON-NLS-1$
		sourcesMap.put("Team1Name",txtTeam1Name); //$NON-NLS-1$
		sourcesMap.put("Team1Forward",txtTeam1Forward); //$NON-NLS-1$
		sourcesMap.put("Team1Goalie",txtTeam1Goalie); //$NON-NLS-1$
		sourcesMap.put("Team2Name",txtTeam2Name); //$NON-NLS-1$
		sourcesMap.put("Team2Forward",txtTeam2Forward); //$NON-NLS-1$
		sourcesMap.put("Team2Goalie",txtTeam2Goalie); //$NON-NLS-1$
		sourcesMap.put("Team3Name",txtTeam3Name); //$NON-NLS-1$
		sourcesMap.put("Team3Forward",txtTeam3Forward); //$NON-NLS-1$
		sourcesMap.put("Team3Goalie",txtTeam3Goalie); //$NON-NLS-1$
		sourcesMap.put("TableName",txtTableName); //$NON-NLS-1$
		sourcesMap.put("Team1GameCount",txtTeam1GameCount); //$NON-NLS-1$
		sourcesMap.put("Team2GameCount",txtTeam2GameCount); //$NON-NLS-1$
		sourcesMap.put("Team3GameCount",txtTeam3GameCount); //$NON-NLS-1$
		sourcesMap.put("Team1MatchCount",txtTeam1MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team2MatchCount",txtTeam2MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team3MatchCount",txtTeam3MatchCount); //$NON-NLS-1$
		sourcesMap.put("Team1Score",txtTeam1Score); //$NON-NLS-1$
		sourcesMap.put("Team2Score",txtTeam2Score); //$NON-NLS-1$
		sourcesMap.put("Team3Score",txtTeam3Score); //$NON-NLS-1$
		sourcesMap.put("Team1TimeOut",txtTeam1TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team2TimeOut",txtTeam2TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team3TimeOut",txtTeam3TimeOut); //$NON-NLS-1$
		sourcesMap.put("Team1Reset",txtTeam1Reset); //$NON-NLS-1$
		sourcesMap.put("Team2Reset",txtTeam2Reset); //$NON-NLS-1$
		sourcesMap.put("Team3Reset",txtTeam3Reset); //$NON-NLS-1$
		sourcesMap.put("Team1Warn",txtTeam1Warn); //$NON-NLS-1$
		sourcesMap.put("Team2Warn",txtTeam2Warn); //$NON-NLS-1$
		sourcesMap.put("Team3Warn",txtTeam3Warn); //$NON-NLS-1$
		sourcesMap.put("Team1KingSeat",txtTeam1KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team2KingSeat",txtTeam2KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team3KingSeat",txtTeam3KingSeat); //$NON-NLS-1$
		sourcesMap.put("Team1Game1Show",txtTeam1Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game1Show",txtTeam2Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game1Show",txtTeam3Game1Show); //$NON-NLS-1$
		sourcesMap.put("Team1Game2Show",txtTeam1Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game2Show",txtTeam2Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game2Show",txtTeam3Game2Show); //$NON-NLS-1$
		sourcesMap.put("Team1Game3Show",txtTeam1Game3Show); //$NON-NLS-1$
		sourcesMap.put("Team2Game3Show",txtTeam2Game3Show); //$NON-NLS-1$
		sourcesMap.put("Team3Game3Show",txtTeam3Game3Show); //$NON-NLS-1$
		sourcesMap.put("TimeRemaining",txtTimeRemaining); //$NON-NLS-1$
		sourcesMap.put("TimerInUse",txtTimerInUse); //$NON-NLS-1$
		sourcesMap.put("MatchWinner",txtMatchWinner); //$NON-NLS-1$
		sourcesMap.put("Meatball",txtMeatball); //$NON-NLS-1$
		sourcesMap.put("GameResults",txtGameResults); //$NON-NLS-1$
		sourcesMap.put("LastScored",txtLastScored); //$NON-NLS-1$
		sourcesMap.put("GameTime",txtGameTime); //$NON-NLS-1$
		sourcesMap.put("MatchTime",txtMatchTime); //$NON-NLS-1$
		sourcesMap.put("StreamTime",txtStreamTime); //$NON-NLS-1$
		sourcesMap.put("ShowScores",txtShowScores); //$NON-NLS-1$
		sourcesMap.put("ShowTimer",txtShowTimer); //$NON-NLS-1$
		sourcesMap.put("ShowCutthroat",txtShowCutthroat); //$NON-NLS-1$
        sourcesMap.put("CueBall",txtCueBall); //$NON-NLS-1$
        sourcesMap.put("OneBall",txtOneBall); //$NON-NLS-1$
        sourcesMap.put("TwoBall",txtTwoBall); //$NON-NLS-1$
        sourcesMap.put("ThreeBall",txtThreeBall); //$NON-NLS-1$
        sourcesMap.put("FourBall",txtFourBall); //$NON-NLS-1$
        sourcesMap.put("FiveBall",txtFiveBall); //$NON-NLS-1$
        sourcesMap.put("SixBall",txtSixBall); //$NON-NLS-1$
        sourcesMap.put("SevenBall",txtSevenBall); //$NON-NLS-1$
        sourcesMap.put("EightBall",txtEightBall); //$NON-NLS-1$
        sourcesMap.put("NineBall",txtNineBall); //$NON-NLS-1$
        sourcesMap.put("TenBall",txtTenBall); //$NON-NLS-1$
        sourcesMap.put("ElevenBall",txtElevenBall); //$NON-NLS-1$
        sourcesMap.put("TwelveBall",txtTwelveBall); //$NON-NLS-1$
        sourcesMap.put("ThirteenBall",txtThirteenBall); //$NON-NLS-1$
        sourcesMap.put("FourteenBall",txtFourteenBall); //$NON-NLS-1$
        sourcesMap.put("FifteenBall",txtFifteenBall); //$NON-NLS-1$
	}
	private void restoreDefaults() {
		filterUpdating = true;
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setSelectedItem(Settings.getDefaultSource(sourceName));
		});
		filterUpdating = false;
	}
	private void revertChanges() {
		filterUpdating = true;
		sourcesMap.forEach((sourceName, textField) -> {
			textField.setSelectedItem(Settings.getSourceParameter(sourceName));
		});
		filterUpdating = false;
		takeSnapshot();
	}
	public boolean saveSettings() {
		boolean okToCloseWindow = false;
		sourcesMap.forEach((sourceName, combo) -> {
			Settings.setSource(sourceName, getComboText(combo));
		});
		try {
			Settings.saveSourceConfig();
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
	private void setupLayout() {	
		setBounds(100, 100, 900, 489);
		setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Column Headers
		JLabel lblTeam1Column = new JLabel(Messages.getString("SourcesPanel.Team1Column")); //$NON-NLS-1$
		lblTeam1Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam1Column, "cell 2 1,alignx left"); //$NON-NLS-1$
		JLabel lblTeam2Column = new JLabel(Messages.getString("SourcesPanel.Team2Column")); //$NON-NLS-1$
		lblTeam2Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam2Column, "cell 3 1,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblTeam3Column = new JLabel(Messages.getString("SourcesPanel.Team3Column")); //$NON-NLS-1$
		lblTeam3Column.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblTeam3Column, "cell 4 1,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol1 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol1.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol1, "cell 2 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol2 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol2.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol2, "cell 3 2,alignx left,aligny center"); //$NON-NLS-1$
		JLabel lblSourceCol3 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol3.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol3, "cell 4 2,alignx left"); //$NON-NLS-1$
		JLabel lblSourceCol4 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
		lblSourceCol4.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
		add(lblSourceCol4, "cell 6 2,alignx left"); //$NON-NLS-1$
        JLabel lblSourceCol5 = new JLabel(Messages.getString("SourcesPanel.Source")); //$NON-NLS-1$
        lblSourceCol5.setFont(new Font("Tahoma", Font.BOLD, 14)); //$NON-NLS-1$
        add(lblSourceCol5, "cell 8 2,alignx left"); //$NON-NLS-1$
		//Team Name
		JLabel lblName = new JLabel(Messages.getString("SourcesPanel.Name")); //$NON-NLS-1$
		add(lblName, "cell 1 3,alignx right"); //$NON-NLS-1$
		txtTeam1Name = new JComboBox<>();
		txtTeam1Name.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Name")); //$NON-NLS-1$
		txtTeam1Name.setPrototypeDisplayValue("          ");
		add(txtTeam1Name, "cell 2 3,alignx left"); //$NON-NLS-1$
		txtTeam2Name = new JComboBox<>();
		txtTeam2Name.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Name")); //$NON-NLS-1$
		txtTeam2Name.setPrototypeDisplayValue("          ");
		add(txtTeam2Name, "cell 3 3,alignx left"); //$NON-NLS-1$
		txtTeam3Name = new JComboBox<>();
		txtTeam3Name.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Name")); //$NON-NLS-1$
		txtTeam3Name.setPrototypeDisplayValue("          ");
		add(txtTeam3Name, "cell 4 3,alignx left"); //$NON-NLS-1$
		//Forward Name
		JLabel lblTeam1Forward = new JLabel(Messages.getString("SourcesPanel.ForwardName")); //$NON-NLS-1$
		add(lblTeam1Forward, "cell 1 4,alignx right"); //$NON-NLS-1$
		txtTeam1Forward = new JComboBox<>();
		txtTeam1Forward.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Forward")); //$NON-NLS-1$
		txtTeam1Forward.setPrototypeDisplayValue("          ");
		add(txtTeam1Forward, "cell 2 4,alignx left"); //$NON-NLS-1$
		txtTeam2Forward = new JComboBox<>();
		txtTeam2Forward.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Forward")); //$NON-NLS-1$
		txtTeam2Forward.setPrototypeDisplayValue("          ");
		add(txtTeam2Forward, "cell 3 4,alignx left"); //$NON-NLS-1$
		txtTeam3Forward = new JComboBox<>();
		txtTeam3Forward.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Forward")); //$NON-NLS-1$
		txtTeam3Forward.setPrototypeDisplayValue("          ");
		add(txtTeam3Forward, "cell 4 4,alignx left"); //$NON-NLS-1$
		//Goalie Name
		JLabel lblGoalieName = new JLabel(Messages.getString("SourcesPanel.GoalieName")); //$NON-NLS-1$
		add(lblGoalieName, "cell 1 5,alignx right"); //$NON-NLS-1$
		txtTeam1Goalie = new JComboBox<>();
		txtTeam1Goalie.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Goalie")); //$NON-NLS-1$
		txtTeam1Goalie.setPrototypeDisplayValue("          ");
		add(txtTeam1Goalie, "cell 2 5,alignx left"); //$NON-NLS-1$
		txtTeam2Goalie = new JComboBox<>();
		txtTeam2Goalie.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Goalie")); //$NON-NLS-1$
		txtTeam2Goalie.setPrototypeDisplayValue("          ");
		add(txtTeam2Goalie, "cell 3 5,alignx left"); //$NON-NLS-1$
		txtTeam3Goalie = new JComboBox<>();
		txtTeam3Goalie.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Goalie")); //$NON-NLS-1$
		txtTeam3Goalie.setPrototypeDisplayValue("          ");
		add(txtTeam3Goalie, "cell 4 5,alignx left"); //$NON-NLS-1$
		//Score
		JLabel lblScore = new JLabel(Messages.getString("SourcesPanel.Score")); //$NON-NLS-1$
		add(lblScore, "cell 1 6,alignx right"); //$NON-NLS-1$
		txtTeam1Score = new JComboBox<>();
		txtTeam1Score.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Score")); //$NON-NLS-1$
		txtTeam1Score.setPrototypeDisplayValue("          ");
		add(txtTeam1Score, "cell 2 6,alignx left"); //$NON-NLS-1$
		txtTeam2Score = new JComboBox<>();
		txtTeam2Score.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Score")); //$NON-NLS-1$
		txtTeam2Score.setPrototypeDisplayValue("          ");
		add(txtTeam2Score, "cell 3 6,alignx left"); //$NON-NLS-1$
		txtTeam3Score = new JComboBox<>();
		txtTeam3Score.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Score")); //$NON-NLS-1$
		txtTeam3Score.setPrototypeDisplayValue("          ");
		add(txtTeam3Score, "cell 4 6,alignx left"); //$NON-NLS-1$
		//Game Count
		JLabel lblGameCount = new JLabel(Messages.getString("SourcesPanel.GameCount")); //$NON-NLS-1$
		add(lblGameCount, "cell 1 7,alignx right"); //$NON-NLS-1$
		txtTeam1GameCount = new JComboBox<>();
		txtTeam1GameCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"GameCount")); //$NON-NLS-1$
		txtTeam1GameCount.setPrototypeDisplayValue("          ");
		add(txtTeam1GameCount, "cell 2 7,alignx left"); //$NON-NLS-1$
		txtTeam2GameCount = new JComboBox<>();
		txtTeam2GameCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"GameCount")); //$NON-NLS-1$
		txtTeam2GameCount.setPrototypeDisplayValue("          ");
		add(txtTeam2GameCount, "cell 3 7,alignx left"); //$NON-NLS-1$
		txtTeam3GameCount = new JComboBox<>();
		txtTeam3GameCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"GameCount")); //$NON-NLS-1$
		txtTeam3GameCount.setPrototypeDisplayValue("          ");
		add(txtTeam3GameCount, "cell 4 7,alignx left"); //$NON-NLS-1$
		//Match Count
		JLabel lblMatchCount = new JLabel(Messages.getString("SourcesPanel.MatchCount")); //$NON-NLS-1$
		add(lblMatchCount, "cell 1 8,alignx right"); //$NON-NLS-1$
		txtTeam1MatchCount = new JComboBox<>();
		txtTeam1MatchCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"MatchCount")); //$NON-NLS-1$
		txtTeam1MatchCount.setPrototypeDisplayValue("          ");
		add(txtTeam1MatchCount, "cell 2 8,alignx left"); //$NON-NLS-1$
		txtTeam2MatchCount = new JComboBox<>();
		txtTeam2MatchCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"MatchCount")); //$NON-NLS-1$
		txtTeam2MatchCount.setPrototypeDisplayValue("          ");
		add(txtTeam2MatchCount, "cell 3 8,alignx left"); //$NON-NLS-1$
		txtTeam3MatchCount = new JComboBox<>();
		txtTeam3MatchCount.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"MatchCount")); //$NON-NLS-1$
		txtTeam3MatchCount.setPrototypeDisplayValue("          ");
		add(txtTeam3MatchCount, "cell 4 8,alignx left"); //$NON-NLS-1$
		//Time Out
		JLabel lblTimeOut = new JLabel(Messages.getString("SourcesPanel.TimeOut")); //$NON-NLS-1$
		add(lblTimeOut, "cell 1 9,alignx right"); //$NON-NLS-1$
		txtTeam1TimeOut = new JComboBox<>();
		txtTeam1TimeOut.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"TimeOut")); //$NON-NLS-1$
		txtTeam1TimeOut.setPrototypeDisplayValue("          ");
		add(txtTeam1TimeOut, "cell 2 9,alignx left"); //$NON-NLS-1$
		txtTeam2TimeOut = new JComboBox<>();
		txtTeam2TimeOut.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"TimeOut")); //$NON-NLS-1$
		txtTeam2TimeOut.setPrototypeDisplayValue("          ");
		add(txtTeam2TimeOut, "cell 3 9,alignx left"); //$NON-NLS-1$
		txtTeam3TimeOut = new JComboBox<>();
		txtTeam3TimeOut.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"TimeOut")); //$NON-NLS-1$
		txtTeam3TimeOut.setPrototypeDisplayValue("          ");
		add(txtTeam3TimeOut, "cell 4 9,alignx left"); //$NON-NLS-1$
		//Reset
		JLabel lblReset = new JLabel(Messages.getString("SourcesPanel.Reset")); //$NON-NLS-1$
		add(lblReset, "cell 1 10,alignx right"); //$NON-NLS-1$
		txtTeam1Reset = new JComboBox<>();
		txtTeam1Reset.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Reset")); //$NON-NLS-1$
		txtTeam1Reset.setPrototypeDisplayValue("          ");
		add(txtTeam1Reset, "cell 2 10,alignx left"); //$NON-NLS-1$
		txtTeam2Reset = new JComboBox<>();
		txtTeam2Reset.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Reset")); //$NON-NLS-1$
		txtTeam2Reset.setPrototypeDisplayValue("          ");
		add(txtTeam2Reset, "cell 3 10,alignx left"); //$NON-NLS-1$
		txtTeam3Reset = new JComboBox<>();
		txtTeam3Reset.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Reset")); //$NON-NLS-1$
		txtTeam3Reset.setPrototypeDisplayValue("          ");
		add(txtTeam3Reset, "cell 4 10,alignx left"); //$NON-NLS-1$
		//Warn
		JLabel lblWarn = new JLabel(Messages.getString("SourcesPanel.Warn")); //$NON-NLS-1$
		add(lblWarn, "cell 1 11,alignx right"); //$NON-NLS-1$
		txtTeam1Warn = new JComboBox<>();
		txtTeam1Warn.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"Warn")); //$NON-NLS-1$
		txtTeam1Warn.setPrototypeDisplayValue("          ");
		add(txtTeam1Warn, "cell 2 11,alignx left"); //$NON-NLS-1$
		txtTeam2Warn = new JComboBox<>();
		txtTeam2Warn.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"Warn")); //$NON-NLS-1$
		txtTeam2Warn.setPrototypeDisplayValue("          ");
		add(txtTeam2Warn, "cell 3 11,alignx left"); //$NON-NLS-1$
		txtTeam3Warn = new JComboBox<>();
		txtTeam3Warn.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"Warn")); //$NON-NLS-1$
		txtTeam3Warn.setPrototypeDisplayValue("          ");
		add(txtTeam3Warn, "cell 4 11,alignx left"); //$NON-NLS-1$
		//KingSeat
		JLabel lblKingSeat = new JLabel(Messages.getString("SourcesPanel.KingSeat")); //$NON-NLS-1$
		add(lblKingSeat, "cell 1 12,alignx right"); //$NON-NLS-1$
		txtTeam1KingSeat = new JComboBox<>();
		txtTeam1KingSeat.setSelectedItem(Settings.getTeamSourceParameter(TEAM1,"KingSeat")); //$NON-NLS-1$
		txtTeam1KingSeat.setPrototypeDisplayValue("          ");
		add(txtTeam1KingSeat, "cell 2 12, alignx left"); //$NON-NLS-1$
		txtTeam2KingSeat = new JComboBox<>();
		txtTeam2KingSeat.setSelectedItem(Settings.getTeamSourceParameter(TEAM2,"KingSeat")); //$NON-NLS-1$
		txtTeam2KingSeat.setPrototypeDisplayValue("          ");
		add(txtTeam2KingSeat, "cell 3 12, alignx left"); //$NON-NLS-1$
		txtTeam3KingSeat = new JComboBox<>();
		txtTeam3KingSeat.setSelectedItem(Settings.getTeamSourceParameter(TEAM3,"KingSeat")); //$NON-NLS-1$
		txtTeam3KingSeat.setPrototypeDisplayValue("          ");
		add(txtTeam3KingSeat, "cell 4 12, alignx left"); //$NON-NLS-1$
		//Game 1 Show Source
		JLabel lblGame1Show = new JLabel(Messages.getString("SourcesPanel.Game1ShowSource")); //$NON-NLS-1$
		add(lblGame1Show, "cell 1 13,alignx right"); //$NON-NLS-1$
		txtTeam1Game1Show = new JComboBox<>();
		txtTeam1Game1Show.setSelectedItem(Settings.getTeamGameShowSource(1,1));
		txtTeam1Game1Show.setPrototypeDisplayValue("          ");
		add(txtTeam1Game1Show, "cell 2 13, alignx left"); //$NON-NLS-1$
		txtTeam2Game1Show = new JComboBox<>();
		txtTeam2Game1Show.setSelectedItem(Settings.getTeamGameShowSource(2,1));
		txtTeam2Game1Show.setPrototypeDisplayValue("          ");
		add(txtTeam2Game1Show, "cell 3 13, alignx left"); //$NON-NLS-1$
		txtTeam3Game1Show = new JComboBox<>();
		txtTeam3Game1Show.setSelectedItem(Settings.getTeamGameShowSource(3,1));
		txtTeam3Game1Show.setPrototypeDisplayValue("          ");
		add(txtTeam3Game1Show, "cell 4 13, alignx left"); //$NON-NLS-1$
		//Game 2 Show Source
		JLabel lblGame2Show = new JLabel(Messages.getString("SourcesPanel.Game2ShowSource")); //$NON-NLS-1$
		add(lblGame2Show, "cell 1 14,alignx right"); //$NON-NLS-1$
		txtTeam1Game2Show = new JComboBox<>();
		txtTeam1Game2Show.setSelectedItem(Settings.getTeamGameShowSource(1,2));
		txtTeam1Game2Show.setPrototypeDisplayValue("          ");
		add(txtTeam1Game2Show, "cell 2 14, alignx left"); //$NON-NLS-1$
		txtTeam2Game2Show = new JComboBox<>();
		txtTeam2Game2Show.setSelectedItem(Settings.getTeamGameShowSource(2,2));
		txtTeam2Game2Show.setPrototypeDisplayValue("          ");
		add(txtTeam2Game2Show, "cell 3 14, alignx left"); //$NON-NLS-1$
		txtTeam3Game2Show = new JComboBox<>();
		txtTeam3Game2Show.setSelectedItem(Settings.getTeamGameShowSource(3,2));
		txtTeam3Game2Show.setPrototypeDisplayValue("          ");
		add(txtTeam3Game2Show, "cell 4 14, alignx left"); //$NON-NLS-1$
		//Game 3 Show Source
		JLabel lblGame3Show = new JLabel(Messages.getString("SourcesPanel.Game3ShowSource")); //$NON-NLS-1$
		add(lblGame3Show, "cell 1 15,alignx right"); //$NON-NLS-1$
		txtTeam1Game3Show = new JComboBox<>();
		txtTeam1Game3Show.setSelectedItem(Settings.getTeamGameShowSource(1,3));
		txtTeam1Game3Show.setPrototypeDisplayValue("          ");
		add(txtTeam1Game3Show, "cell 2 15, alignx left"); //$NON-NLS-1$
		txtTeam2Game3Show = new JComboBox<>();
		txtTeam2Game3Show.setSelectedItem(Settings.getTeamGameShowSource(2,3));
		txtTeam2Game3Show.setPrototypeDisplayValue("          ");
		add(txtTeam2Game3Show, "cell 3 15, alignx left"); //$NON-NLS-1$
		txtTeam3Game3Show = new JComboBox<>();
		txtTeam3Game3Show.setSelectedItem(Settings.getTeamGameShowSource(3,3));
		txtTeam3Game3Show.setPrototypeDisplayValue("          ");
		add(txtTeam3Game3Show, "cell 4 15, alignx left"); //$NON-NLS-1$
		//Tournament
		JLabel lblTournament = new JLabel(Messages.getString("SourcesPanel.Tournament")); //$NON-NLS-1$
		add(lblTournament, "cell 5 3,alignx right"); //$NON-NLS-1$
		txtTournament = new JComboBox<>();
		txtTournament.setSelectedItem(Settings.getSourceParameter("Tournament")); //$NON-NLS-1$
		txtTournament.setPrototypeDisplayValue("          ");
		add(txtTournament, "cell 6 3,alignx left"); //$NON-NLS-1$
		//Event
		JLabel lblEvent = new JLabel(Messages.getString("SourcesPanel.Event")); //$NON-NLS-1$
		add(lblEvent, "cell 5 4,alignx right"); //$NON-NLS-1$
		txtEvent = new JComboBox<>();
		txtEvent.setSelectedItem(Settings.getSourceParameter("Event")); //$NON-NLS-1$
		add(txtEvent, "cell 6 4,alignx left"); //$NON-NLS-1$
		txtEvent.setPrototypeDisplayValue("          ");
		//Table Name
		JLabel lblTableName = new JLabel(Messages.getString("SourcesPanel.TableName")); //$NON-NLS-1$
		add(lblTableName, "cell 5 5,alignx right"); //$NON-NLS-1$
		txtTableName = new JComboBox<>();
		txtTableName.setSelectedItem(Settings.getSourceParameter("TableName")); //$NON-NLS-1$
		txtTableName.setPrototypeDisplayValue("          ");
		add(txtTableName, "cell 6 5,alignx left"); //$NON-NLS-1$
		//Timer in Use
		JLabel lblTimer = new JLabel(Messages.getString("SourcesPanel.Timer")); //$NON-NLS-1$
		add(lblTimer, "cell 5 6,alignx right"); //$NON-NLS-1$
		txtTimerInUse = new JComboBox<>();
		txtTimerInUse.setSelectedItem(Settings.getSourceParameter("TimerInUse")); //$NON-NLS-1$
		txtTimerInUse.setPrototypeDisplayValue("          ");
		add(txtTimerInUse, "cell 6 6,alignx left"); //$NON-NLS-1$
		//Time Remaining
		JLabel lblTimeRemaining = new JLabel(Messages.getString("SourcesPanel.TimeRemaining")); //$NON-NLS-1$
		add(lblTimeRemaining, "cell 5 7,alignx right"); //$NON-NLS-1$
		txtTimeRemaining = new JComboBox<>();
		txtTimeRemaining.setSelectedItem(Settings.getSourceParameter("TimeRemaining")); //$NON-NLS-1$
		txtTimeRemaining.setPrototypeDisplayValue("          ");
		add(txtTimeRemaining, "cell 6 7,alignx left"); //$NON-NLS-1$
		//Game Time
		JLabel lblGameTime = new JLabel(Messages.getString("SourcesPanel.GameTime")); //$NON-NLS-1$
		add(lblGameTime, "cell 5 8,alignx right"); //$NON-NLS-1$
		txtGameTime = new JComboBox<>();
		txtGameTime.setSelectedItem(Settings.getSourceParameter("GameTime")); //$NON-NLS-1$
		txtGameTime.setPrototypeDisplayValue("          ");
		add(txtGameTime, "cell 6 8,alignx left"); //$NON-NLS-1$
		//Match Time
		JLabel lblMatchTime = new JLabel(Messages.getString("SourcesPanel.MatchTime")); //$NON-NLS-1$
		add(lblMatchTime, "cell 5 9,alignx right"); //$NON-NLS-1$
		txtMatchTime = new JComboBox<>();
		txtMatchTime.setSelectedItem(Settings.getSourceParameter("MatchTime")); //$NON-NLS-1$
		txtMatchTime.setPrototypeDisplayValue("          ");
		add(txtMatchTime, "cell 6 9,alignx left"); //$NON-NLS-1$
		//Stream Time
		JLabel lblStreamTime = new JLabel(Messages.getString("SourcesPanel.StreamTime")); //$NON-NLS-1$
		add(lblStreamTime, "cell 5 10,alignx right"); //$NON-NLS-1$
		txtStreamTime = new JComboBox<>();
		txtStreamTime.setSelectedItem(Settings.getSourceParameter("StreamTime")); //$NON-NLS-1$
		txtStreamTime.setPrototypeDisplayValue("          ");
		add(txtStreamTime, "cell 6 10,alignx left"); //$NON-NLS-1$
		//Last Scored
		JLabel lblLastScored = new JLabel(Messages.getString("SourcesPanel.LastScored")); //$NON-NLS-1$
		add(lblLastScored, "cell 5 11,alignx right"); //$NON-NLS-1$
		txtLastScored = new JComboBox<>();
		txtLastScored.setSelectedItem(Settings.getSourceParameter("LastScored")); //$NON-NLS-1$
		txtLastScored.setPrototypeDisplayValue("          ");
		add(txtLastScored, "cell 6 11,alignx left"); //$NON-NLS-1$
		//Match Winner
		JLabel lblMatchWinner = new JLabel(Messages.getString("SourcesPanel.MatchWinner")); //$NON-NLS-1$
		add(lblMatchWinner, "cell 5 12,alignx right"); //$NON-NLS-1$
		txtMatchWinner = new JComboBox<>();
		txtMatchWinner.setSelectedItem(Settings.getSourceParameter("MatchWinner")); //$NON-NLS-1$
		txtMatchWinner.setPrototypeDisplayValue("          ");
		add(txtMatchWinner, "cell 6 12,alignx left"); //$NON-NLS-1$
		//Meatball
		JLabel lblMeatball = new JLabel(Messages.getString("SourcesPanel.Meatball")); //$NON-NLS-1$
		add(lblMeatball, "cell 5 13,alignx right"); //$NON-NLS-1$
		txtMeatball = new JComboBox<>();
		txtMeatball.setSelectedItem(Settings.getSourceParameter("Meatball")); //$NON-NLS-1$
		txtMeatball.setPrototypeDisplayValue("          ");
		add(txtMeatball, "cell 6 13,alignx left"); //$NON-NLS-1$
		//Game Results
		JLabel lblGameResults = new JLabel(Messages.getString("SourcesPanel.GameResults")); //$NON-NLS-1$
		add(lblGameResults, "cell 5 14,alignx right"); //$NON-NLS-1$
		txtGameResults = new JComboBox<>();
		txtGameResults.setSelectedItem(Settings.getSourceParameter("GameResults")); //$NON-NLS-1$
		txtGameResults.setPrototypeDisplayValue("          ");
		add(txtGameResults, "cell 6 14,alignx left"); //$NON-NLS-1$
		//Show Scores
		JLabel lblShowScores = new JLabel(Messages.getString("SourcesPanel.ShowScores")); //$NON-NLS-1$
		add(lblShowScores, "cell 5 15,alignx trailing"); //$NON-NLS-1$
		txtShowScores = new JComboBox<>();
		txtShowScores.setSelectedItem(Settings.getSourceParameter("ShowScores")); //$NON-NLS-1$
		txtShowScores.setPrototypeDisplayValue("          ");
		add(txtShowScores, "cell 6 15,alignx left"); //$NON-NLS-1$
		//Show Timer
		JLabel lblShowTimer = new JLabel(Messages.getString("SourcesPanel.ShowTimer")); //$NON-NLS-1$
		add(lblShowTimer, "cell 5 16,alignx trailing"); //$NON-NLS-1$
		txtShowTimer = new JComboBox<>();
		txtShowTimer.setSelectedItem(Settings.getSourceParameter("ShowTimer")); //$NON-NLS-1$
		txtShowTimer.setPrototypeDisplayValue("          ");
		add(txtShowTimer, "cell 6 16,alignx left"); //$NON-NLS-1$
		//Show Cutthroat
		JLabel lblShowCutthroat = new JLabel(Messages.getString("SourcesPanel.ShowCutthroat")); //$NON-NLS-1$
		add(lblShowCutthroat, "cell 5 17,alignx trailing"); //$NON-NLS-1$
		txtShowCutthroat = new JComboBox<>();
		txtShowCutthroat.setSelectedItem(Settings.getSourceParameter("ShowCutthroatSource")); //$NON-NLS-1$
		txtShowCutthroat.setPrototypeDisplayValue("          ");
		add(txtShowCutthroat, "cell 6 17,alignx left"); //$NON-NLS-1$
		//CueBall
		JLabel lblCueBall = new JLabel(Messages.getString("SourcesPanel.CueBall")); //$NON-NLS-1$
		add(lblCueBall, "cell 7 3,alignx right"); //$NON-NLS-1$
		txtCueBall = new JComboBox<>();
		txtCueBall.setSelectedItem(Settings.getSourceParameter("CueBall")); //$NON-NLS-1$
		txtCueBall.setPrototypeDisplayValue("          ");
		add(txtCueBall, "cell 8 3,alignx left"); //$NON-NLS-1$
		//OneBall
		JLabel lblOneBall = new JLabel(Messages.getString("SourcesPanel.OneBall")); //$NON-NLS-1$
		add(lblOneBall, "cell 7 4,alignx right"); //$NON-NLS-1$
		txtOneBall = new JComboBox<>();
		txtOneBall.setSelectedItem(Settings.getSourceParameter("OneBall")); //$NON-NLS-1$
		txtOneBall.setPrototypeDisplayValue("          ");
		add(txtOneBall, "cell 8 4,alignx left"); //$NON-NLS-1$
		//TwoBall
		JLabel lblTwoBall = new JLabel(Messages.getString("SourcesPanel.TwoBall")); //$NON-NLS-1$
		add(lblTwoBall, "cell 7 5,alignx right"); //$NON-NLS-1$
		txtTwoBall = new JComboBox<>();
		txtTwoBall.setSelectedItem(Settings.getSourceParameter("TwoBall")); //$NON-NLS-1$
		txtTwoBall.setPrototypeDisplayValue("          ");
		add(txtTwoBall, "cell 8 5,alignx left"); //$NON-NLS-1$
		//ThreeBall
		JLabel lblThreeBall = new JLabel(Messages.getString("SourcesPanel.ThreeBall")); //$NON-NLS-1$
		add(lblThreeBall, "cell 7 6,alignx right"); //$NON-NLS-1$
		txtThreeBall = new JComboBox<>();
		txtThreeBall.setSelectedItem(Settings.getSourceParameter("ThreeBall")); //$NON-NLS-1$
		txtThreeBall.setPrototypeDisplayValue("          ");
		add(txtThreeBall, "cell 8 6,alignx left"); //$NON-NLS-1$
		//FourBall
		JLabel lblFourBall = new JLabel(Messages.getString("SourcesPanel.FourBall")); //$NON-NLS-1$
		add(lblFourBall, "cell 7 7,alignx right"); //$NON-NLS-1$
		txtFourBall = new JComboBox<>();
		txtFourBall.setSelectedItem(Settings.getSourceParameter("FourBall")); //$NON-NLS-1$
		txtFourBall.setPrototypeDisplayValue("          ");
		add(txtFourBall, "cell 8 7,alignx left"); //$NON-NLS-1$
		//FiveBall
		JLabel lblFiveBall = new JLabel(Messages.getString("SourcesPanel.FiveBall")); //$NON-NLS-1$
		add(lblFiveBall, "cell 7 8,alignx right"); //$NON-NLS-1$
		txtFiveBall = new JComboBox<>();
		txtFiveBall.setSelectedItem(Settings.getSourceParameter("FiveBall")); //$NON-NLS-1$
		txtFiveBall.setPrototypeDisplayValue("          ");
		add(txtFiveBall, "cell 8 8,alignx left"); //$NON-NLS-1$
		//SixBall
		JLabel lblSixBall = new JLabel(Messages.getString("SourcesPanel.SixBall")); //$NON-NLS-1$
		add(lblSixBall, "cell 7 9,alignx right"); //$NON-NLS-1$
		txtSixBall = new JComboBox<>();
		txtSixBall.setSelectedItem(Settings.getSourceParameter("SixBall")); //$NON-NLS-1$
		txtSixBall.setPrototypeDisplayValue("          ");
		add(txtSixBall, "cell 8 9,alignx left"); //$NON-NLS-1$
		//SevenBall
		JLabel lblSevenBall = new JLabel(Messages.getString("SourcesPanel.SevenBall")); //$NON-NLS-1$
		add(lblSevenBall, "cell 7 10,alignx right"); //$NON-NLS-1$
		txtSevenBall = new JComboBox<>();
		txtSevenBall.setSelectedItem(Settings.getSourceParameter("SevenBall")); //$NON-NLS-1$
		txtSevenBall.setPrototypeDisplayValue("          ");
		add(txtSevenBall, "cell 8 10,alignx left"); //$NON-NLS-1$
		//EightBall
		JLabel lblEightBall = new JLabel(Messages.getString("SourcesPanel.EightBall")); //$NON-NLS-1$
		add(lblEightBall, "cell 7 11,alignx right"); //$NON-NLS-1$
		txtEightBall = new JComboBox<>();
		txtEightBall.setSelectedItem(Settings.getSourceParameter("EightBall")); //$NON-NLS-1$
		txtEightBall.setPrototypeDisplayValue("          ");
		add(txtEightBall, "cell 8 11,alignx left"); //$NON-NLS-1$
		//NineBall
		JLabel lblNineBall = new JLabel(Messages.getString("SourcesPanel.NineBall")); //$NON-NLS-1$
		add(lblNineBall, "cell 7 12,alignx right"); //$NON-NLS-1$
		txtNineBall = new JComboBox<>();
		txtNineBall.setSelectedItem(Settings.getSourceParameter("NineBall")); //$NON-NLS-1$
		txtNineBall.setPrototypeDisplayValue("          ");
		add(txtNineBall, "cell 8 12,alignx left"); //$NON-NLS-1$
		//TenBall
		JLabel lblTenBall = new JLabel(Messages.getString("SourcesPanel.TenBall")); //$NON-NLS-1$
		add(lblTenBall, "cell 7 13,alignx right"); //$NON-NLS-1$
		txtTenBall = new JComboBox<>();
		txtTenBall.setSelectedItem(Settings.getSourceParameter("TenBall")); //$NON-NLS-1$
		txtTenBall.setPrototypeDisplayValue("          ");
		add(txtTenBall, "cell 8 13,alignx left"); //$NON-NLS-1$
		//ElevenBall
		JLabel lblElevenBall = new JLabel(Messages.getString("SourcesPanel.ElevenBall")); //$NON-NLS-1$
		add(lblElevenBall, "cell 7 14,alignx right"); //$NON-NLS-1$
		txtElevenBall = new JComboBox<>();
		txtElevenBall.setSelectedItem(Settings.getSourceParameter("ElevenBall")); //$NON-NLS-1$
		txtElevenBall.setPrototypeDisplayValue("          ");
		add(txtElevenBall, "cell 8 14,alignx left"); //$NON-NLS-1$
		//TwelveBall
		JLabel lblTwelveBall = new JLabel(Messages.getString("SourcesPanel.TwelveBall")); //$NON-NLS-1$
		add(lblTwelveBall, "cell 7 15,alignx right"); //$NON-NLS-1$
		txtTwelveBall = new JComboBox<>();
		txtTwelveBall.setSelectedItem(Settings.getSourceParameter("TwelveBall")); //$NON-NLS-1$
		txtTwelveBall.setPrototypeDisplayValue("          ");
		add(txtTwelveBall, "cell 8 15,alignx left"); //$NON-NLS-1$
		//ThirteenBall
		JLabel lblThirteenBall = new JLabel(Messages.getString("SourcesPanel.ThirteenBall")); //$NON-NLS-1$
		add(lblThirteenBall, "cell 7 16,alignx right"); //$NON-NLS-1$
		txtThirteenBall = new JComboBox<>();
		txtThirteenBall.setSelectedItem(Settings.getSourceParameter("ThirteenBall")); //$NON-NLS-1$
		txtThirteenBall.setPrototypeDisplayValue("          ");
		add(txtThirteenBall, "cell 8 16,alignx left"); //$NON-NLS-1$
		//FourteenBall
		JLabel lblFourteenBall = new JLabel(Messages.getString("SourcesPanel.FourteenBall")); //$NON-NLS-1$
		add(lblFourteenBall, "cell 7 17,alignx right"); //$NON-NLS-1$
		txtFourteenBall = new JComboBox<>();
		txtFourteenBall.setSelectedItem(Settings.getSourceParameter("FourteenBall")); //$NON-NLS-1$
		txtFourteenBall.setPrototypeDisplayValue("          ");
		add(txtFourteenBall, "cell 8 17,alignx left"); //$NON-NLS-1$
		//FifteenBall
		JLabel lblFifteenBall = new JLabel(Messages.getString("SourcesPanel.FifteenBall")); //$NON-NLS-1$
		add(lblFifteenBall, "cell 7 18,alignx right"); //$NON-NLS-1$
		txtFifteenBall = new JComboBox<>();
		txtFifteenBall.setSelectedItem(Settings.getSourceParameter("FifteenBall")); //$NON-NLS-1$
		txtFifteenBall.setPrototypeDisplayValue("          ");
		add(txtFifteenBall, "cell 8 18,alignx left"); //$NON-NLS-1$
        //Apply
		btnApply = new JButton(Messages.getString("Global.Apply")); //$NON-NLS-1$
		add(btnApply, "cell 2 20,alignx left"); //$NON-NLS-1$
		btnSave = new JButton(Messages.getString("Global.Save")); //$NON-NLS-1$
		add(btnSave, "cell 3 20,alignx left"); //$NON-NLS-1$
		btnFetchSources = new JButton(Messages.getString("SourcesPanel.FetchSources")); //$NON-NLS-1$
		add(btnFetchSources, "cell 6 20,alignx left"); //$NON-NLS-1$
		JButton btnCancel = new JButton(Messages.getString("Global.Cancel")); //$NON-NLS-1$
		btnCancel.addActionListener((ActionEvent e) -> {
                    JComponent comp = (JComponent) e.getSource();
                    Window win = SwingUtilities.getWindowAncestor(comp);
                    confirmClose(win);
                });
		add(btnCancel, "cell 4 20,alignx left"); //$NON-NLS-1$
		JButton btnRestoreDefaults = new JButton(Messages.getString("Global.RestoreDefaults")); //$NON-NLS-1$
		btnRestoreDefaults.addActionListener((ActionEvent e) -> {
                    restoreDefaults();
                });
		add(btnRestoreDefaults, "cell 5 20,alignx left"); //$NON-NLS-1$
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
