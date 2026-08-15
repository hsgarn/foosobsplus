/**
Copyright © 2026 Hugh Garner
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.miginfocom.swing.MigLayout;

import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableSession;
import com.midsouthfoosball.foosobsplus.model.Team;

/**
 * A single grid showing every configured table's live game state at once -
 * one row per team (2, or 3 in cutthroat) grouped under its table - unlike
 * {@link TableViewFrame}, which shows one table per window. Purely a
 * monitor/edit surface: it does not own any game state, it polls the live
 * {@link TableSession} list on a Swing {@link Timer} and routes edits back
 * through the same callbacks {@code Main} already uses for the per-table
 * monitor windows ({@link FieldAdjustListener}, {@link TableNamesListener},
 * Send-to-OBS), so background-table edits and the displayed table's OBS
 * output stay authoritative there.
 */
@SuppressWarnings("serial")
public class TableDataPanel extends JPanel {

	/** Notified when the user clicks a +/- stepper on a numeric field. */
	public interface FieldAdjustListener {
		void adjust(int tableIndex, int teamNumber, TableViewFrame.Field field, int delta);
	}

	/** Notified when the user commits an edited Name/Forward/Goalie cell. */
	public interface TableNamesListener {
		void apply(int tableIndex, int teamNumber, String teamName, String forwardName, String goalieName);
	}

	/** One grid row: which table and which of its teams. */
	private record RowRef(int tableIndex, int teamIndex) {}

	private static final String ON = "1"; //$NON-NLS-1$
	private static final int REFRESH_MS = 500;
	private static final Color LIVE_GREEN = new Color(0, 170, 0);
	private static final Color LIVE_YELLOW = new Color(220, 180, 0);
	private static final Color LIVE_RED = new Color(200, 0, 0);
	private static final Color GROUP_BORDER = new Color(120, 120, 120);
	private static final Color ZEBRA_EVEN = Color.WHITE;
	private static final Color ZEBRA_ODD = new Color(240, 244, 248);

	private static final int COL_TABLE = 0;
	private static final int COL_TEAM = 1;
	private static final int COL_NAME = 2;
	private static final int COL_FORWARD = 3;
	private static final int COL_GOALIE = 4;
	private static final int COL_SCORE = 5;
	private static final int COL_GAMES = 6;
	private static final int COL_MATCHES = 7;
	private static final int COL_TIMEOUTS = 8;
	private static final int COL_OBS_STATUS = 9;
	private static final int COL_AUTOSCORE_STATUS = 10;
	private static final int COL_CLEAR = 11;
	private static final int COL_ACTION = 12;
	private static final String[] COLUMN_NAMES = {
		"Table", "Team", "Name", "Forward", "Goalie", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		"Score", "Games", "Matches", "Timeouts", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		"OBS", "AutoScore", "", "" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	};

	private final Supplier<List<TableSession>> sessionsSupplier;
	private final Supplier<TableSession> activeSessionSupplier;
	private final BooleanSupplier obsConnectedSupplier;
	private final IntPredicate autoScoreConnectedProvider;
	private final IntConsumer sendToObsListener;
	private final FieldAdjustListener adjustListener;
	private final TableNamesListener namesListener;
	private final IntConsumer clearTableListener;
	private final Runnable clearAllTablesListener;
	private final DataTableModel tableModel;
	private final JTable table;
	private final Timer refreshTimer;
	private List<RowRef> rowRefs = new ArrayList<>();

	public TableDataPanel(Supplier<List<TableSession>> sessionsSupplier, Supplier<TableSession> activeSessionSupplier,
			BooleanSupplier obsConnectedSupplier, IntPredicate autoScoreConnectedProvider,
			IntConsumer sendToObsListener, FieldAdjustListener adjustListener, TableNamesListener namesListener,
			IntConsumer clearTableListener, Runnable clearAllTablesListener) {
		this.sessionsSupplier = sessionsSupplier;
		this.activeSessionSupplier = activeSessionSupplier;
		this.obsConnectedSupplier = obsConnectedSupplier;
		this.autoScoreConnectedProvider = autoScoreConnectedProvider;
		this.sendToObsListener = sendToObsListener;
		this.adjustListener = adjustListener;
		this.namesListener = namesListener;
		this.clearTableListener = clearTableListener;
		this.clearAllTablesListener = clearAllTablesListener;
		rebuildRowRefs();

		tableModel = new DataTableModel();
		table = new JTable(tableModel);
		table.setRowHeight(30);
		ZebraTextRenderer textRenderer = new ZebraTextRenderer();
		table.getColumnModel().getColumn(COL_TABLE).setCellRenderer(textRenderer);
		table.getColumnModel().getColumn(COL_TEAM).setCellRenderer(textRenderer);
		table.getColumnModel().getColumn(COL_NAME).setCellRenderer(textRenderer);
		table.getColumnModel().getColumn(COL_FORWARD).setCellRenderer(textRenderer);
		table.getColumnModel().getColumn(COL_GOALIE).setCellRenderer(textRenderer);
		StepperRenderer stepperRenderer = new StepperRenderer();
		StepperEditor stepperEditor = new StepperEditor();
		for (int col = COL_SCORE; col <= COL_TIMEOUTS; col++) {
			table.getColumnModel().getColumn(col).setCellRenderer(stepperRenderer);
			table.getColumnModel().getColumn(col).setCellEditor(stepperEditor);
		}
		table.getColumnModel().getColumn(COL_OBS_STATUS).setCellRenderer(new ObsStatusRenderer());
		table.getColumnModel().getColumn(COL_AUTOSCORE_STATUS).setCellRenderer(new AutoScoreStatusRenderer());
		table.getColumnModel().getColumn(COL_CLEAR).setCellRenderer(new ClearTableRenderer());
		table.getColumnModel().getColumn(COL_CLEAR).setCellEditor(new ClearTableEditor());
		table.getColumnModel().getColumn(COL_ACTION).setCellRenderer(new SendToObsRenderer());
		table.getColumnModel().getColumn(COL_ACTION).setCellEditor(new SendToObsEditor());
		// Column widths sized to fit each cell's real controls (name text, +/-
		// buttons, status dot + label, buttons) without crowding; wider than a
		// literal 30% bump on the old layout because this version also adds the
		// Clear column and inline name editing, both of which need real room.
		setColumnWidth(COL_TABLE, 50);
		setColumnWidth(COL_TEAM, 50);
		setColumnWidth(COL_NAME, 140);
		setColumnWidth(COL_FORWARD, 110);
		setColumnWidth(COL_GOALIE, 110);
		// Score/Games/Matches/Timeouts get the width freed up by shrinking Table/
		// Clear All/Send to OBS below - those are the columns whose +/- buttons
		// were most cramped.
		for (int col = COL_SCORE; col <= COL_TIMEOUTS; col++) {
			setColumnWidth(col, 124);
		}
		setColumnWidth(COL_OBS_STATUS, 90);
		setColumnWidth(COL_AUTOSCORE_STATUS, 100);
		setColumnWidth(COL_CLEAR, 108);
		setColumnWidth(COL_ACTION, 117);

		setLayout(new BorderLayout());
		JLabel hint = new JLabel("Live data for every table, one row per team. Edit Name/Forward/Goalie directly; use +/- to nudge Score/Games/Matches/Timeouts; Clear All resets one table; Send to OBS switches the displayed table."); //$NON-NLS-1$
		hint.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		add(hint, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(table);
		// Default window ~30%+ wider than the original layout so nothing is
		// squeezed (see column-width comment above for why it's not exactly 30%).
		scrollPane.setPreferredSize(new Dimension(1400, 360));
		add(scrollPane, BorderLayout.CENTER);

		JButton clearAllTablesButton = new JButton("CLEAR ALL TABLES"); //$NON-NLS-1$
		clearAllTablesButton.setForeground(new Color(160, 0, 0));
		clearAllTablesButton.addActionListener((ActionEvent e) -> confirmClearAllTables());
		JPanel bottomPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 8));
		bottomPanel.add(clearAllTablesButton);
		add(bottomPanel, BorderLayout.SOUTH);

		refreshTimer = new Timer(REFRESH_MS, e -> refresh());
	}

	/** Confirms, then clears every table's score/games/matches/timeouts/stats. */
	private void confirmClearAllTables() {
		int result = javax.swing.JOptionPane.showConfirmDialog(this,
				"Clear ALL tables? This resets every table's scores, games, matches, timeouts and stats. This cannot be undone.", //$NON-NLS-1$
				"Clear All Tables", //$NON-NLS-1$
				javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
		if (result == javax.swing.JOptionPane.YES_OPTION) {
			if (clearAllTablesListener != null) clearAllTablesListener.run();
			refreshRows();
		}
	}

	private void setColumnWidth(int col, int width) {
		TableColumn column = table.getColumnModel().getColumn(col);
		column.setPreferredWidth(width);
	}

	private static boolean isCutThroat() {
		return Settings.getControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON); //$NON-NLS-1$
	}

	private static Team teamFor(TableSession session, int teamIndex) {
		switch (teamIndex) {
			case 0: return session.getTeam1();
			case 1: return session.getTeam2();
			case 2: return session.getTeam3();
			default: return null;
		}
	}

	private static int fieldValue(Team team, TableViewFrame.Field field) {
		switch (field) {
			case SCORE: return team.getScore();
			case GAMES: return team.getGameCount();
			case MATCHES: return team.getMatchCount();
			case TIMEOUTS: return team.getTimeOutCount();
			default: return 0;
		}
	}

	private static TableViewFrame.Field fieldForColumn(int col) {
		switch (col) {
			case COL_SCORE: return TableViewFrame.Field.SCORE;
			case COL_GAMES: return TableViewFrame.Field.GAMES;
			case COL_MATCHES: return TableViewFrame.Field.MATCHES;
			case COL_TIMEOUTS: return TableViewFrame.Field.TIMEOUTS;
			default: return null;
		}
	}

	private static String displayName(TableSession session, int tableIndex) {
		String name = session.getTableName();
		return name.isEmpty() ? String.valueOf(tableIndex + 1) : name;
	}

	/** Rebuilds the row -> (table, team) mapping from the live session list + cutthroat flag. */
	private void rebuildRowRefs() {
		List<RowRef> refs = new ArrayList<>();
		List<TableSession> sessions = sessionsSupplier.get();
		int teamCount = isCutThroat() ? 3 : 2;
		for (int t = 0; t < sessions.size(); t++) {
			for (int team = 0; team < teamCount; team++) {
				refs.add(new RowRef(t, team));
			}
		}
		rowRefs = refs;
	}

	/** Re-reads all sessions unless the user is mid-edit (avoids clobbering a typed value). */
	private void refresh() {
		if (table.isEditing()) return;
		refreshRows();
	}

	/** Rebuilds the row list and repaints immediately (used right after an edit). */
	private void refreshRows() {
		rebuildRowRefs();
		tableModel.fireTableDataChanged();
	}

	/** Starts polling; call once the window is shown. */
	public void startPolling() {
		refreshRows();
		refreshTimer.start();
	}

	/** Stops polling; call when the window is closed. */
	public void stopPolling() {
		refreshTimer.stop();
	}

	private Color zebraBackground(int row) {
		if (row < 0 || row >= rowRefs.size()) return ZEBRA_EVEN;
		return rowRefs.get(row).tableIndex() % 2 == 0 ? ZEBRA_EVEN : ZEBRA_ODD;
	}

	/** A thicker top border on the first row of each table's group (row > 0), so table blocks read as visually distinct. */
	private Border groupBorder(int row) {
		boolean groupStart = row > 0 && row < rowRefs.size() && rowRefs.get(row).teamIndex() == 0;
		return groupStart ? BorderFactory.createMatteBorder(2, 0, 0, 0, GROUP_BORDER) : null;
	}

	private static Icon makeDot(Color color) {
		return new Icon() {
			@Override public int getIconWidth() { return 12; }
			@Override public int getIconHeight() { return 12; }
			@Override public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(color);
				g2.fillOval(x + 1, y + 1, 10, 10);
				g2.setColor(color.darker());
				g2.drawOval(x + 1, y + 1, 10, 10);
				g2.dispose();
			}
		};
	}

	private static JButton makeStepButton(String text) {
		JButton button = new JButton(text);
		button.setMargin(new Insets(0, 5, 0, 5));
		button.setFocusable(false);
		return button;
	}

	/** Plain text cells (Table/Team/Name/Forward/Goalie) with zebra-by-table shading and a group-start border. */
	private class ZebraTextRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, col);
			label.setBackground(zebraBackground(row));
			label.setBorder(groupBorder(row));
			return label;
		}
	}

	/** A {@code + value -} stepper cell for Score/Games/Matches/Timeouts (renderer half - not interactive). */
	private class StepperRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private final JButton plus = makeStepButton("+"); //$NON-NLS-1$
		private final JButton minus = makeStepButton("-"); //$NON-NLS-1$
		private final JLabel valueLabel = new JLabel("0", SwingConstants.CENTER); //$NON-NLS-1$
		StepperRenderer() {
			super(new MigLayout("insets 0, gap 2", "[]4[24!]4[]", "[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			setOpaque(true);
			add(plus);
			add(valueLabel, "growx, alignx center"); //$NON-NLS-1$
			add(minus);
		}
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			valueLabel.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
			setBackground(zebraBackground(row));
			setBorder(groupBorder(row));
			return this;
		}
	}

	/** A {@code + value -} stepper cell for Score/Games/Matches/Timeouts (editor half - the real, clickable buttons). */
	private class StepperEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private final JPanel panel = new JPanel(new MigLayout("insets 0, gap 2", "[]4[24!]4[]", "[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		private final JButton plus = makeStepButton("+"); //$NON-NLS-1$
		private final JButton minus = makeStepButton("-"); //$NON-NLS-1$
		private final JLabel valueLabel = new JLabel("0", SwingConstants.CENTER); //$NON-NLS-1$
		private int editingRow = -1;
		private int editingCol = -1;
		StepperEditor() {
			panel.setOpaque(true);
			panel.add(plus);
			panel.add(valueLabel, "growx, alignx center"); //$NON-NLS-1$
			panel.add(minus);
			plus.addActionListener((ActionEvent e) -> applyDelta(1));
			minus.addActionListener((ActionEvent e) -> applyDelta(-1));
		}
		private void applyDelta(int delta) {
			fireEditingStopped();
			if (editingRow < 0 || editingRow >= rowRefs.size()) return;
			RowRef ref = rowRefs.get(editingRow);
			TableViewFrame.Field field = fieldForColumn(editingCol);
			if (adjustListener != null && field != null) {
				adjustListener.adjust(ref.tableIndex(), ref.teamIndex() + 1, field, delta);
			}
			refreshRows();
		}
		@Override
		public Component getTableCellEditorComponent(JTable jTable, Object value, boolean isSelected, int row, int col) {
			editingRow = row;
			editingCol = col;
			valueLabel.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
			panel.setBackground(zebraBackground(row));
			panel.setBorder(groupBorder(row));
			return panel;
		}
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}

	/** Green when active+OBS connected, yellow when active but OBS not connected, red when a background table. Blank on non-group-start rows. */
	private class ObsStatusRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, col);
			label.setBackground(zebraBackground(row));
			label.setBorder(groupBorder(row));
			label.setHorizontalAlignment(SwingConstants.LEFT);
			if (row < 0 || row >= rowRefs.size() || rowRefs.get(row).teamIndex() != 0) {
				label.setIcon(null);
				label.setText(""); //$NON-NLS-1$
				return label;
			}
			List<TableSession> sessions = sessionsSupplier.get();
			int tableIndex = rowRefs.get(row).tableIndex();
			boolean active = tableIndex < sessions.size() && sessions.get(tableIndex) == activeSessionSupplier.get();
			boolean obsConnected = obsConnectedSupplier.getAsBoolean();
			Color color = !active ? LIVE_RED : (obsConnected ? LIVE_GREEN : LIVE_YELLOW);
			label.setIcon(makeDot(color));
			label.setText(!active ? "Background" : (obsConnected ? "Live" : "Active")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return label;
		}
	}

	/** Green when this table's AutoScore connection is up, red otherwise. Blank on non-group-start rows. */
	private class AutoScoreStatusRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, col);
			label.setBackground(zebraBackground(row));
			label.setBorder(groupBorder(row));
			label.setHorizontalAlignment(SwingConstants.LEFT);
			if (row < 0 || row >= rowRefs.size() || rowRefs.get(row).teamIndex() != 0) {
				label.setIcon(null);
				label.setText(""); //$NON-NLS-1$
				return label;
			}
			int tableIndex = rowRefs.get(row).tableIndex();
			boolean connected = autoScoreConnectedProvider.test(tableIndex);
			label.setIcon(makeDot(connected ? LIVE_GREEN : LIVE_RED));
			label.setText(connected ? "Connected" : "Disconnected"); //$NON-NLS-1$ //$NON-NLS-2$
			return label;
		}
	}

	/** A single button reading "Send to OBS" on a background table's group-start row, "Active" (disabled) on the displayed table's, blank otherwise. */
	private class SendToObsRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private final JButton button = new JButton();
		SendToObsRenderer() {
			super(new BorderLayout());
			setOpaque(true);
			add(button, BorderLayout.CENTER);
		}
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			setBackground(zebraBackground(row));
			setBorder(groupBorder(row));
			boolean groupStart = row >= 0 && row < rowRefs.size() && rowRefs.get(row).teamIndex() == 0;
			button.setVisible(groupStart);
			if (!groupStart) return this;
			List<TableSession> sessions = sessionsSupplier.get();
			int tableIndex = rowRefs.get(row).tableIndex();
			boolean active = tableIndex < sessions.size() && sessions.get(tableIndex) == activeSessionSupplier.get();
			button.setText(active ? "Active" : "Send to OBS"); //$NON-NLS-1$ //$NON-NLS-2$
			button.setEnabled(!active);
			return this;
		}
	}
	/** A "Clear All" button on each table's group-start row (blank otherwise). Same action as the main screen's Switch Panel Clear All button. */
	private class ClearTableRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private final JButton button = new JButton("Clear All"); //$NON-NLS-1$
		ClearTableRenderer() {
			super(new BorderLayout());
			setOpaque(true);
			add(button, BorderLayout.CENTER);
		}
		@Override
		public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
			setBackground(zebraBackground(row));
			setBorder(groupBorder(row));
			boolean groupStart = row >= 0 && row < rowRefs.size() && rowRefs.get(row).teamIndex() == 0;
			button.setVisible(groupStart);
			return this;
		}
	}
	private class ClearTableEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private final JButton button = new JButton("Clear All"); //$NON-NLS-1$
		private int editingRow = -1;
		ClearTableEditor() {
			button.addActionListener((ActionEvent e) -> {
				fireEditingStopped();
				if (editingRow < 0 || editingRow >= rowRefs.size()) return;
				int tableIndex = rowRefs.get(editingRow).tableIndex();
				if (clearTableListener != null) clearTableListener.accept(tableIndex);
				refreshRows();
			});
		}
		@Override
		public Component getTableCellEditorComponent(JTable jTable, Object value, boolean isSelected, int row, int col) {
			editingRow = row;
			return button;
		}
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}
	private class SendToObsEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private final JButton button = new JButton("Send to OBS"); //$NON-NLS-1$
		private int editingRow = -1;
		SendToObsEditor() {
			button.addActionListener((ActionEvent e) -> {
				fireEditingStopped();
				if (editingRow < 0 || editingRow >= rowRefs.size()) return;
				int tableIndex = rowRefs.get(editingRow).tableIndex();
				if (sendToObsListener != null) sendToObsListener.accept(tableIndex);
				refreshRows();
			});
		}
		@Override
		public Component getTableCellEditorComponent(JTable jTable, Object value, boolean isSelected, int row, int col) {
			editingRow = row;
			return button;
		}
		@Override
		public Object getCellEditorValue() {
			return null;
		}
	}

	private class DataTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		@Override public int getRowCount() { return rowRefs.size(); }
		@Override public int getColumnCount() { return COLUMN_NAMES.length; }
		@Override public String getColumnName(int col) { return COLUMN_NAMES[col]; }
		@Override
		public boolean isCellEditable(int row, int col) {
			if (row < 0 || row >= rowRefs.size()) return false;
			RowRef ref = rowRefs.get(row);
			switch (col) {
				case COL_NAME: case COL_FORWARD: case COL_GOALIE:
				case COL_SCORE: case COL_GAMES: case COL_MATCHES: case COL_TIMEOUTS:
					return true;
				case COL_CLEAR:
					return ref.teamIndex() == 0;
				case COL_ACTION: {
					if (ref.teamIndex() != 0) return false;
					List<TableSession> sessions = sessionsSupplier.get();
					return ref.tableIndex() < sessions.size() && sessions.get(ref.tableIndex()) != activeSessionSupplier.get();
				}
				default: return false;
			}
		}
		@Override
		public Object getValueAt(int row, int col) {
			if (row < 0 || row >= rowRefs.size()) return null;
			RowRef ref = rowRefs.get(row);
			List<TableSession> sessions = sessionsSupplier.get();
			if (ref.tableIndex() >= sessions.size()) return null;
			TableSession session = sessions.get(ref.tableIndex());
			switch (col) {
				case COL_TABLE: return ref.teamIndex() == 0 ? displayName(session, ref.tableIndex()) : ""; //$NON-NLS-1$
				case COL_TEAM: return String.valueOf(ref.teamIndex() + 1);
				case COL_NAME: return teamFor(session, ref.teamIndex()).getTeamName();
				case COL_FORWARD: return teamFor(session, ref.teamIndex()).getForwardName();
				case COL_GOALIE: return teamFor(session, ref.teamIndex()).getGoalieName();
				case COL_SCORE: case COL_GAMES: case COL_MATCHES: case COL_TIMEOUTS:
					return fieldValue(teamFor(session, ref.teamIndex()), fieldForColumn(col));
				default: return ""; //$NON-NLS-1$
			}
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
			if (row < 0 || row >= rowRefs.size() || !(value instanceof String)) return;
			RowRef ref = rowRefs.get(row);
			List<TableSession> sessions = sessionsSupplier.get();
			if (ref.tableIndex() >= sessions.size()) return;
			Team team = teamFor(sessions.get(ref.tableIndex()), ref.teamIndex());
			String text = ((String) value).trim();
			String teamName = team.getTeamName();
			String forwardName = team.getForwardName();
			String goalieName = team.getGoalieName();
			switch (col) {
				case COL_NAME: teamName = text; break;
				case COL_FORWARD: forwardName = text; break;
				case COL_GOALIE: goalieName = text; break;
				default: return;
			}
			if (namesListener != null) {
				namesListener.apply(ref.tableIndex(), ref.teamIndex() + 1, teamName, forwardName, goalieName);
			}
			refreshRows();
		}
	}
}
