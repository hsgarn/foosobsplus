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

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

import com.midsouthfoosball.foosobsplus.model.AppConfig;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableSession;
import com.midsouthfoosball.foosobsplus.model.Team;

/**
 * A small always-live window showing one (possibly non-displayed) table's
 * score, game count, match count, timeouts and team/forward/goalie names for
 * each team, plus a "Send to OBS" button that makes this table the
 * active/displayed table.
 *
 * <p>Layout: each team is a column (name over Forward/Goalie and an Edit Names
 * button); the four counters (Score/Games/Matches/Timeouts) are rows, each with
 * a {@code + value -} control per team.
 *
 * <p>The window reads its {@link TableSession} directly and polls it on a Swing
 * {@link Timer} so background tables update as their AutoScore feeds them. Its
 * {@code +/-} buttons and name modal delegate the actual edits to the
 * caller-supplied listeners (which route to {@code Main}) so the change is
 * applied to this window's own table whether or not it is displayed.
 */
@SuppressWarnings("serial")
public class TableViewFrame extends JFrame {

	/** The four adjustable per-team counters. */
	public enum Field { SCORE, GAMES, MATCHES, TIMEOUTS }

	/** Notified when the user nudges a counter with a {@code +/-} button. */
	public interface FieldAdjustListener {
		void adjust(int teamNumber, Field field, int delta);
	}

	/** Notified when the user commits new names from the Edit Names modal. */
	public interface NamesListener {
		void apply(int teamNumber, String teamName, String forwardName, String goalieName);
	}

	/** Notified when the user commits new win-condition values from the Edit Parameters modal. */
	public interface ParametersListener {
		void apply(boolean handicapped, int pointsToWin, int maxWin, int winBy, int gamesToWin,
				int team1PointsToWin, int team2PointsToWin, int team3PointsToWin);
	}

	/** The bulk clear/reset actions offered by the buttons on the right side of the window. */
	public enum ClearAction { NAMES, SCORES, GAMES, MATCHES, TIMEOUTS, RESTART_MATCH, ALL }

	/** Notified when the user presses one of the clear/reset buttons. */
	public interface ClearListener {
		void clear(ClearAction action);
	}

	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final int REFRESH_MS = 500;
	/** MigLayout column of each team (column 2 holds the swap-teams button). */
	private static final int SWAP_BUTTON_HEIGHT = 20;
	private static final int[] TEAM_COLUMNS = { 1, 3, 4 };
	private static final Color LIVE_GREEN = new Color(0, 170, 0);
	private static final Color LIVE_YELLOW = new Color(220, 180, 0);
	private static final Color LIVE_RED = new Color(200, 0, 0);

	private final int tableIndex;
	private final TableSession session;
	private final IntSupplier activeIndexSupplier;
	private final BooleanSupplier obsConnectedSupplier;
	private final BooleanSupplier autoScoreConnectedSupplier;
	private final FieldAdjustListener adjustListener;
	private final NamesListener namesListener;
	private final ParametersListener parametersListener;
	private final ClearListener clearListener;
	private final Timer refreshTimer;
	private final JLabel[] teamNameLabels = new JLabel[3];
	private final JLabel[] forwardLabels = new JLabel[3];
	private final JLabel[] goalieLabels = new JLabel[3];
	private final JButton[] editNamesButtons = new JButton[3];
	private final JPanel[] nameCells = new JPanel[3];
	private final MigLayout contentLayout;
	private boolean team3ColumnShown = true;
	private final JButton swapTeamsButton;
	private final JButton swapForwardsButton;
	private final JButton swapGoaliesButton;
	private final JPanel[] scoreCells = new JPanel[3];
	private final JPanel[] gamesCells = new JPanel[3];
	private final JPanel[] matchesCells = new JPanel[3];
	private final JPanel[] timeoutCells = new JPanel[3];
	private final JLabel[] scoreValues = new JLabel[3];
	private final JLabel[] gamesValues = new JLabel[3];
	private final JLabel[] matchesValues = new JLabel[3];
	private final JLabel[] timeoutValues = new JLabel[3];
	private final JLabel autoScoreLabel;
	private final JLabel obsLiveLabel;
	private final JButton sendToObsButton;

	/**
	 * @param tableIndex                 index of this table in the sessions list
	 * @param tableName                  current display name of the table (title bar)
	 * @param session                    the table's live game state
	 * @param activeIndexSupplier        supplies the index of the currently displayed
	 *                                   (OBS-live) table, so this window can show
	 *                                   whether it is the active one
	 * @param obsConnectedSupplier       supplies whether OBS is actually connected,
	 *                                   so the active table shows green (connected)
	 *                                   vs yellow (not connected)
	 * @param autoScoreConnectedSupplier supplies whether this table's AutoScore is
	 *                                   connected (green) or not (red)
	 * @param sendToObsListener          invoked with {@code tableIndex} when the user
	 *                                   presses Send to OBS
	 * @param adjustListener             invoked when the user nudges a counter
	 * @param namesListener              invoked when the user commits new names
	 * @param parametersListener         invoked when the user commits new win-condition
	 *                                   values from the Edit Parameters modal
	 * @param clearListener              invoked when the user presses a clear/reset button
	 */
	public TableViewFrame(int tableIndex, String tableName, TableSession session, IntSupplier activeIndexSupplier,
			BooleanSupplier obsConnectedSupplier, BooleanSupplier autoScoreConnectedSupplier,
			IntConsumer sendToObsListener, FieldAdjustListener adjustListener, NamesListener namesListener,
			ParametersListener parametersListener, ClearListener clearListener) {
		super(buildTitle(tableName));
		this.tableIndex = tableIndex;
		this.session = session;
		this.activeIndexSupplier = activeIndexSupplier;
		this.obsConnectedSupplier = obsConnectedSupplier;
		this.autoScoreConnectedSupplier = autoScoreConnectedSupplier;
		this.adjustListener = adjustListener;
		this.namesListener = namesListener;
		this.parametersListener = parametersListener;
		this.clearListener = clearListener;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);

		// One column per team; the field labels sit in column 0.
		contentLayout = new MigLayout("insets 4, gap 2 2, hidemode 3", columnConstraints(true)); //$NON-NLS-1$
		JPanel panel = new JPanel(contentLayout);
		Font headerFont = new Font("Tahoma", Font.BOLD, 12); //$NON-NLS-1$
		Font cellFont = new Font("Tahoma", Font.PLAIN, 14); //$NON-NLS-1$
		Font nameFont = new Font("Tahoma", Font.PLAIN, 10); //$NON-NLS-1$
		Font labelFont = new Font("Tahoma", Font.BOLD, 11); //$NON-NLS-1$

		String teamPrefix = Messages.getString("TableViewFrame.Team"); //$NON-NLS-1$
		for (int i = 0; i < 3; i++) {
			int teamNumber = i + 1;
			int col = TEAM_COLUMNS[i];
			teamNameLabels[i] = new JLabel(teamPrefix + " " + teamNumber); //$NON-NLS-1$
			teamNameLabels[i].setFont(headerFont);
			teamNameLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(teamNameLabels[i], "cell " + col + " 0,alignx center"); //$NON-NLS-1$ //$NON-NLS-2$
			forwardLabels[i] = new JLabel(""); //$NON-NLS-1$
			forwardLabels[i].setFont(nameFont);
			goalieLabels[i] = new JLabel(""); //$NON-NLS-1$
			goalieLabels[i].setFont(nameFont);
			// Names stacked with a vertical double-headed arrow on the outside edge
			// (left of team 1, right of the others) that swaps Forward and Goalie.
			JButton swapFg = makeSwapButton("↕", Messages.getString("TableViewFrame.SwapForwardGoalie")); //$NON-NLS-1$ //$NON-NLS-2$
			swapFg.addActionListener(e -> swapForwardGoalie(teamNumber));
			JPanel names = new JPanel(new MigLayout("insets 0, gap 2, gapy 2, hidemode 3", "[][]", "[" + SWAP_BUTTON_HEIGHT + "!][" + SWAP_BUTTON_HEIGHT + "!]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			if (i == 0) {
				names.add(swapFg, "cell 0 0 1 2,aligny center"); //$NON-NLS-1$
				names.add(forwardLabels[i], "cell 1 0,alignx center"); //$NON-NLS-1$
				names.add(goalieLabels[i], "cell 1 1,alignx center"); //$NON-NLS-1$
			} else {
				names.add(forwardLabels[i], "cell 0 0,alignx center"); //$NON-NLS-1$
				names.add(goalieLabels[i], "cell 0 1,alignx center"); //$NON-NLS-1$
				names.add(swapFg, "cell 1 0 1 2,aligny center"); //$NON-NLS-1$
			}
			nameCells[i] = names;
			panel.add(names, "cell " + col + " 1 1 2,alignx center"); //$NON-NLS-1$ //$NON-NLS-2$
			editNamesButtons[i] = new JButton(Messages.getString("TableViewFrame.EditNames")); //$NON-NLS-1$
			editNamesButtons[i].setMargin(new Insets(0, 4, 0, 4));
			editNamesButtons[i].setFont(nameFont);
			editNamesButtons[i].setFocusable(false);
			editNamesButtons[i].addActionListener(e -> openNamesDialog(teamNumber));
			panel.add(editNamesButtons[i], "cell " + col + " 3,alignx center"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Horizontal double-headed arrows between team 1 and team 2: forwards, goalies, then whole teams.
		swapForwardsButton = makeSwapButton("↔", Messages.getString("TableViewFrame.SwapForwards")); //$NON-NLS-1$ //$NON-NLS-2$
		swapForwardsButton.addActionListener(e -> swapPosition(true));
		panel.add(swapForwardsButton, "cell 2 1,aligny center"); //$NON-NLS-1$
		swapGoaliesButton = makeSwapButton("↔", Messages.getString("TableViewFrame.SwapGoalies")); //$NON-NLS-1$ //$NON-NLS-2$
		swapGoaliesButton.addActionListener(e -> swapPosition(false));
		panel.add(swapGoaliesButton, "cell 2 2,aligny center"); //$NON-NLS-1$
		swapTeamsButton = makeSwapButton("<html><center>↔<br>↔</center></html>", Messages.getString("TableViewFrame.SwapTeams")); //$NON-NLS-1$ //$NON-NLS-2$
		swapTeamsButton.setPreferredSize(new java.awt.Dimension(swapTeamsButton.getPreferredSize().width, 2 * SWAP_BUTTON_HEIGHT - 6));
		swapTeamsButton.addActionListener(e -> swapTeams());
		panel.add(swapTeamsButton, "cell 2 3,aligny center"); //$NON-NLS-1$

		addFieldRow(panel, Messages.getString("TableViewFrame.Score"), 4, Field.SCORE, scoreValues, scoreCells, cellFont, labelFont); //$NON-NLS-1$
		addFieldRow(panel, Messages.getString("TableViewFrame.Games"), 5, Field.GAMES, gamesValues, gamesCells, cellFont, labelFont); //$NON-NLS-1$
		addFieldRow(panel, Messages.getString("TableViewFrame.Matches"), 6, Field.MATCHES, matchesValues, matchesCells, cellFont, labelFont); //$NON-NLS-1$
		addFieldRow(panel, Messages.getString("TableViewFrame.Timeouts"), 7, Field.TIMEOUTS, timeoutValues, timeoutCells, cellFont, labelFont); //$NON-NLS-1$

		// Status indicators, each a label with a filled status circle beneath it:
		// "AutoScore" (green connected / red not) and "OBS Live" (green active+OBS
		// connected / yellow active+OBS disconnected / red background table).
		autoScoreLabel = makeIndicatorLabel(Messages.getString("TableViewFrame.AutoScore"), labelFont); //$NON-NLS-1$
		obsLiveLabel = makeIndicatorLabel(Messages.getString("TableViewFrame.OBSLive"), labelFont); //$NON-NLS-1$
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
		statusPanel.add(autoScoreLabel);
		statusPanel.add(obsLiveLabel);
		panel.add(statusPanel, "cell 0 8 5 1,alignx center,gaptop 4"); //$NON-NLS-1$

		sendToObsButton = new JButton(Messages.getString("TableViewFrame.SendToOBS")); //$NON-NLS-1$
		sendToObsButton.addActionListener(e -> {
			if (sendToObsListener != null) sendToObsListener.accept(this.tableIndex);
		});
		panel.add(sendToObsButton, "cell 0 9 5 1,alignx center,gaptop 2"); //$NON-NLS-1$

		JButton editParametersButton = new JButton(Messages.getString("TableViewFrame.EditParameters")); //$NON-NLS-1$
		editParametersButton.addActionListener(e -> openParametersDialog());
		panel.add(editParametersButton, "cell 0 10 5 1,alignx center,gaptop 2"); //$NON-NLS-1$

		// Clear/reset buttons down the right-hand side.
		addClearButton(panel, 3, "TableViewFrame.ClearNames", ClearAction.NAMES); //$NON-NLS-1$
		addClearButton(panel, 4, "TableViewFrame.ClearScores", ClearAction.SCORES); //$NON-NLS-1$
		addClearButton(panel, 5, "TableViewFrame.ClearGames", ClearAction.GAMES); //$NON-NLS-1$
		addClearButton(panel, 6, "TableViewFrame.ClearMatches", ClearAction.MATCHES); //$NON-NLS-1$
		addClearButton(panel, 7, "TableViewFrame.ClearTimeouts", ClearAction.TIMEOUTS); //$NON-NLS-1$
		addClearButton(panel, 9, "TableViewFrame.RestartMatch", ClearAction.RESTART_MATCH); //$NON-NLS-1$
		addClearButton(panel, 10, "TableViewFrame.ClearAll", ClearAction.ALL); //$NON-NLS-1$

		getContentPane().add(panel);
		refresh();
		pack();

		refreshTimer = new Timer(REFRESH_MS, e -> refresh());
		refreshTimer.start();
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosed(WindowEvent e) { refreshTimer.stop(); }
		});
	}

	/** Adds a clear button in the right-hand column, level with the row of the field it clears. */
	private void addClearButton(JPanel target, int row, String messageKey, ClearAction action) {
		JButton button = new JButton(Messages.getString(messageKey));
		button.setFocusable(false);
		button.setFont(new Font("Tahoma", Font.PLAIN, 11)); //$NON-NLS-1$
		button.setMargin(new Insets(0, 4, 0, 4));
		button.addActionListener(e -> {
			if (clearListener != null) clearListener.clear(action);
			refresh();
		});
		target.add(button, "cell 5 " + row + ",growx,gapleft 4"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** Column constraints; the team 3 column (and its gap) collapses unless cut-throat is on. */
	private static String columnConstraints(boolean showTeam3) {
		return showTeam3 ? "[46]6[84]2[]2[84]6[84]0[]" : "[46]6[84]2[]2[84]0[0!]0[]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static String buildTitle(String tableName) {
		return PROGRAMNAME + " " + Messages.getString("TableViewFrame.Title") //$NON-NLS-1$ //$NON-NLS-2$
				+ ": " + Messages.getString("TableViewFrame.Table") + " " + tableName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/** Adds a counter row: a bold field label in column 0 and a per-team editor in each team column. */
	private void addFieldRow(JPanel panel, String label, int row, Field field, JLabel[] valueLabels, JPanel[] cells,
			Font cellFont, Font labelFont) {
		JLabel lbl = new JLabel(label);
		lbl.setFont(labelFont);
		panel.add(lbl, "cell 0 " + row + ",aligny center"); //$NON-NLS-1$ //$NON-NLS-2$
		for (int i = 0; i < 3; i++) {
			int teamNumber = i + 1;
			int col = TEAM_COLUMNS[i];
			valueLabels[i] = new JLabel("0"); //$NON-NLS-1$
			cells[i] = buildValueEditor(valueLabels[i], cellFont, teamNumber, field);
			panel.add(cells[i], "cell " + col + " " + row + ",alignx center"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	/** A {@code [-] value [+]} editor cell whose value label is polled and whose buttons fire {@link #adjustListener}. */
	private JPanel buildValueEditor(JLabel valueLabel, Font cellFont, int teamNumber, Field field) {
		valueLabel.setFont(cellFont);
		valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel cell = new JPanel(new MigLayout("insets 0, gap 2", "[]3[26!]3[]", "[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JButton minus = makeStepButton("−", Messages.getString("TableViewFrame.Decrement")); //$NON-NLS-1$ //$NON-NLS-2$
		JButton plus = makeStepButton("+", Messages.getString("TableViewFrame.Increment")); //$NON-NLS-1$ //$NON-NLS-2$
		minus.addActionListener(e -> fireAdjust(teamNumber, field, -1));
		plus.addActionListener(e -> fireAdjust(teamNumber, field, 1));
		cell.add(minus);
		cell.add(valueLabel, "alignx center"); //$NON-NLS-1$
		cell.add(plus);
		return cell;
	}

	private static JButton makeStepButton(String text, String tooltip) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 11)); //$NON-NLS-1$
		button.setMargin(new Insets(0, 4, 0, 4));
		button.setFocusable(false);
		button.setToolTipText(tooltip);
		return button;
	}

	private static JButton makeSwapButton(String text, String tooltip) {
		JButton button = new JButton(text);
		button.setFont(new Font("Dialog", Font.BOLD, 11)); //$NON-NLS-1$
		button.setMargin(new Insets(0, 3, 0, 3));
		button.setFocusable(false);
		button.setToolTipText(tooltip);
		button.setPreferredSize(new java.awt.Dimension(button.getPreferredSize().width, SWAP_BUTTON_HEIGHT));
		return button;
	}

	/** Swaps team 1's and team 2's Forward (or Goalie) names. */
	private void swapPosition(boolean forwards) {
		if (namesListener == null) return;
		Team t1 = session.getTeam1();
		Team t2 = session.getTeam2();
		String n1 = t1.getTeamName(), f1 = t1.getForwardName(), g1 = t1.getGoalieName();
		String n2 = t2.getTeamName(), f2 = t2.getForwardName(), g2 = t2.getGoalieName();
		if (forwards) {
			namesListener.apply(1, n1, f2, g1);
			namesListener.apply(2, n2, f1, g2);
		} else {
			namesListener.apply(1, n1, f1, g2);
			namesListener.apply(2, n2, f2, g1);
		}
		refresh();
	}

	/** Swaps a team's Forward and Goalie names. */
	private void swapForwardGoalie(int teamNumber) {
		Team team = teamForNumber(teamNumber);
		if (team == null || namesListener == null) return;
		namesListener.apply(teamNumber, team.getTeamName(), team.getGoalieName(), team.getForwardName());
		refresh();
	}

	/** Swaps team 1 and team 2 (team name, Forward and Goalie). */
	private void swapTeams() {
		if (namesListener == null) return;
		Team t1 = session.getTeam1();
		Team t2 = session.getTeam2();
		String n1 = t1.getTeamName(), f1 = t1.getForwardName(), g1 = t1.getGoalieName();
		String n2 = t2.getTeamName(), f2 = t2.getForwardName(), g2 = t2.getGoalieName();
		namesListener.apply(1, n2, f2, g2);
		namesListener.apply(2, n1, f1, g1);
		refresh();
	}

	private void fireAdjust(int teamNumber, Field field, int delta) {
		if (adjustListener != null) adjustListener.adjust(teamNumber, field, delta);
		refresh();
	}

	/** Opens a modal pre-filled with the team's current names; commits via {@link #namesListener}. */
	private void openNamesDialog(int teamNumber) {
		Team team = teamForNumber(teamNumber);
		if (team == null) return;
		JTextField teamField = new JTextField(team.getTeamName(), 18);
		JTextField forwardField = new JTextField(team.getForwardName(), 18);
		JTextField goalieField = new JTextField(team.getGoalieName(), 18);
		JPanel form = new JPanel(new MigLayout("wrap 2", "[right][grow,fill]")); //$NON-NLS-1$ //$NON-NLS-2$
		form.add(new JLabel(Messages.getString("TableViewFrame.TeamName"))); //$NON-NLS-1$
		form.add(teamField);
		form.add(new JLabel(Messages.getString("TableViewFrame.Forward"))); //$NON-NLS-1$
		form.add(forwardField);
		form.add(new JLabel(Messages.getString("TableViewFrame.Goalie"))); //$NON-NLS-1$
		form.add(goalieField);
		String title = Messages.getString("TableViewFrame.EditNamesTitle") + " " + teamNumber; //$NON-NLS-1$ //$NON-NLS-2$
		int result = JOptionPane.showConfirmDialog(this, form, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION && namesListener != null) {
			namesListener.apply(teamNumber, teamField.getText().trim(), forwardField.getText().trim(), goalieField.getText().trim());
			refresh();
		}
	}

	/**
	 * Opens a modal pre-filled with this table's current Points to Win/Max Win/
	 * Win By/Games to Win/Handicapped/per-team handicapped Points to Win; commits
	 * via {@link #parametersListener}. Lets a background table's win-condition
	 * format be configured without switching to it first.
	 */
	private void openParametersDialog() {
		Match match = session.getMatch();
		JCheckBox handicappedBox = new JCheckBox("", match.isHandicapped()); //$NON-NLS-1$
		JTextField pointsToWinField = new JTextField(Integer.toString(match.getPointsToWin()), 6);
		JTextField maxWinField = new JTextField(Settings.formatMaxWin(Integer.toString(match.getMaxWin())), 6);
		JTextField winByField = new JTextField(Integer.toString(match.getWinBy()), 6);
		JTextField gamesToWinField = new JTextField(Integer.toString(match.getGamesToWin()), 6);
		JTextField team1Field = new JTextField(Integer.toString(match.getTeamPointsToWin(1)), 6);
		JTextField team2Field = new JTextField(Integer.toString(match.getTeamPointsToWin(2)), 6);
		JTextField team3Field = new JTextField(Integer.toString(match.getTeamPointsToWin(3)), 6);
		JPanel form = new JPanel(new MigLayout("wrap 2", "[right][grow,fill]")); //$NON-NLS-1$ //$NON-NLS-2$
		form.add(new JLabel(Messages.getString("TableViewFrame.PointsToWin"))); form.add(pointsToWinField); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.MaxWin"))); form.add(maxWinField); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.WinBy"))); form.add(winByField); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.GamesToWin"))); form.add(gamesToWinField); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.Handicapped"))); form.add(handicappedBox); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.Team1PointsToWin"))); form.add(team1Field); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.Team2PointsToWin"))); form.add(team2Field); //$NON-NLS-1$
		form.add(new JLabel(Messages.getString("TableViewFrame.Team3PointsToWin"))); form.add(team3Field); //$NON-NLS-1$
		String title = Messages.getString("TableViewFrame.EditParametersTitle"); //$NON-NLS-1$
		int result = JOptionPane.showConfirmDialog(this, form, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result != JOptionPane.OK_OPTION || parametersListener == null) return;
		try {
			int pointsToWin = Integer.parseInt(pointsToWinField.getText().trim());
			String maxWinText = maxWinField.getText().trim();
			int maxWin = Settings.isUnlimitedMaxWin(maxWinText) ? Settings.MAX_WIN_UNLIMITED : Integer.parseInt(maxWinText);
			int winBy = Integer.parseInt(winByField.getText().trim());
			int gamesToWin = Integer.parseInt(gamesToWinField.getText().trim());
			int team1PointsToWin = Integer.parseInt(team1Field.getText().trim());
			int team2PointsToWin = Integer.parseInt(team2Field.getText().trim());
			int team3PointsToWin = Integer.parseInt(team3Field.getText().trim());
			parametersListener.apply(handicappedBox.isSelected(), pointsToWin, maxWin, winBy, gamesToWin,
					team1PointsToWin, team2PointsToWin, team3PointsToWin);
			refresh();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, Messages.getString("TableViewFrame.InvalidNumber")); //$NON-NLS-1$
		}
	}

	private Team teamForNumber(int teamNumber) {
		switch (teamNumber) {
			case 1: return session.getTeam1();
			case 2: return session.getTeam2();
			case 3: return session.getTeam3();
			default: return null;
		}
	}

	/** A status label with its text centered above a filled status circle. */
	private static JLabel makeIndicatorLabel(String text, Font font) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.TOP);
		return label;
	}

	/** Reads the session's current values into the display. Runs on the EDT. */
	private void refresh() {
		boolean cutThroat = Settings.getControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON); //$NON-NLS-1$
		String forwardPrefix = Messages.getString("TableViewFrame.ForwardShort"); //$NON-NLS-1$
		String goaliePrefix = Messages.getString("TableViewFrame.GoalieShort"); //$NON-NLS-1$
		boolean cutThroatChanged = cutThroat != team3ColumnShown;
		if (cutThroatChanged) {
			team3ColumnShown = cutThroat;
			contentLayout.setColumnConstraints(columnConstraints(cutThroat));
		}
		Team[] teams = { session.getTeam1(), session.getTeam2(), session.getTeam3() };
		for (int i = 0; i < 3; i++) {
			boolean visible = i < 2 || cutThroat;
			teamNameLabels[i].setVisible(visible);
			nameCells[i].setVisible(visible);
			editNamesButtons[i].setVisible(visible);
			scoreCells[i].setVisible(visible);
			gamesCells[i].setVisible(visible);
			matchesCells[i].setVisible(visible);
			timeoutCells[i].setVisible(visible);
			if (!visible) continue;
			Team team = teams[i];
			teamNameLabels[i].setText(team.getTeamName());
			forwardLabels[i].setText(forwardPrefix + " " + team.getForwardName()); //$NON-NLS-1$
			goalieLabels[i].setText(goaliePrefix + " " + team.getGoalieName()); //$NON-NLS-1$
			scoreValues[i].setText(Integer.toString(team.getScore()));
			gamesValues[i].setText(Integer.toString(team.getGameCount()));
			matchesValues[i].setText(Integer.toString(team.getMatchCount()));
			timeoutValues[i].setText(Integer.toString(team.getTimeOutCount()));
		}
		// Resize only after the team 3 components' visibility has been updated above,
		// so pack() sees the new content size.
		if (cutThroatChanged) {
			revalidate();
			pack();
		}
		// This table is "OBS live" when it is the currently displayed table. Green if
		// OBS is actually connected, yellow if it is the active table but OBS is not
		// connected, red if this is a background table. When active, the Send to OBS
		// button is redundant (it would switch to itself), so disable it.
		boolean active = activeIndexSupplier != null && activeIndexSupplier.getAsInt() == tableIndex;
		boolean obsConnected = obsConnectedSupplier != null && obsConnectedSupplier.getAsBoolean();
		Color dotColor = active ? (obsConnected ? LIVE_GREEN : LIVE_YELLOW) : LIVE_RED;
		obsLiveLabel.setIcon(makeDotIcon(dotColor));
		sendToObsButton.setEnabled(!active);
		// AutoScore indicator: green when this table's AutoScore is connected, red otherwise.
		boolean autoScoreConnected = autoScoreConnectedSupplier != null && autoScoreConnectedSupplier.getAsBoolean();
		autoScoreLabel.setIcon(makeDotIcon(autoScoreConnected ? LIVE_GREEN : LIVE_RED));
	}

	/** Re-reads this window's session immediately (e.g. right after an edit). */
	public void refreshNow() {
		if (SwingUtilities.isEventDispatchThread()) {
			refresh();
		} else {
			SwingUtilities.invokeLater(this::refresh);
		}
	}

	/** Updates the title bar after the table has been renamed. */
	public void setTableName(String tableName) {
		setTitle(buildTitle(tableName));
	}

	/** A filled circle icon in the given color, drawn under the "OBS Live" label. */
	private static Icon makeDotIcon(Color color) {
		return new Icon() {
			@Override public int getIconWidth() { return 16; }
			@Override public int getIconHeight() { return 16; }
			@Override public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(color);
				g2.fillOval(x + 2, y + 2, 12, 12);
				g2.setColor(color.darker());
				g2.drawOval(x + 2, y + 2, 12, 12);
				g2.dispose();
			}
		};
	}
}
