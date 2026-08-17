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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.midsouthfoosball.foosobsplus.main.PicoDiscovery;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

/**
 * Shared UI flow for handling AutoScore (Pico) discovery results: showing the
 * found-devices picker (Assign Selected / Assign All / Cancel) and applying
 * the choice to whichever UI is driving the search. Used by both the
 * one-table-at-a-time {@code AutoScoreSettingsPanel} (via {@code AutoScoreManager})
 * and the multi-table {@code AutoScoreTablesPanel} grid, so the Assign All
 * edge-case handling (growing the table count, reporting unmatched tables)
 * lives in exactly one place.
 */
public final class PicoSearchHelper {
	private PicoSearchHelper() {}

	/**
	 * A UI that owns a list of table connections and can have discovered
	 * devices assigned into it.
	 */
	public interface AssignTarget {
		/** Number of table connections currently configured. */
		int getTableCount();
		/** Applies a device's address/port/MAC to the connection at the given index. */
		void setTableAddress(int index, String host, String port, String mac);
		/** Grows the table list to at least minCount (appending default connections). */
		void ensureTableCount(int minCount);
		/** Existing row for this physical device, or -1 when unassigned. */
		int findTableByMac(String mac);
		/** MAC currently assigned to a row. */
		String getTableMac(int index);
		Object createAssignmentSnapshot();
		void restoreAssignmentSnapshot(Object snapshot);
		/** Appends a line to the target's message/status log. */
		void addMessage(String message);
		/** Persists the assignment(s) just made and refreshes any dependent runtime state. */
		boolean saveAssignments();
	}

	/**
	 * The user's choice in the search results dialog: a single device to
	 * assign to a specific table (tableIndex, chosen in the dialog itself so
	 * it's never left unset), or assign every device to its own table.
	 */
	private record SearchAction(PicoDiscovery.PicoInfo pico, boolean assignAll, int tableIndex) {}

	/**
	 * Full post-discovery flow: logs found devices, shows the picker dialog,
	 * and applies the result to {@code target}. {@code selectedIndexSupplier}
	 * gives the row/table currently selected in the caller's UI, used only to
	 * seed the picker dialog's own "Assign Selected to:" default (a negative
	 * value means no table is selected there); the dialog's dropdown is the
	 * actual source of truth for which table gets the assignment.
	 */
	public static void handleDiscoveryResult(
			Component parent,
			List<PicoDiscovery.PicoInfo> picos,
			AssignTarget target,
			IntSupplier selectedIndexSupplier) {
		if (picos.isEmpty()) {
			target.addMessage("No Pico found."); //$NON-NLS-1$
			return;
		}
		for (PicoDiscovery.PicoInfo pico : picos) {
			target.addMessage("Found: " + pico.display()); //$NON-NLS-1$
		}
		SearchAction action = choosePico(parent, picos, target, selectedIndexSupplier);
		if (action == null) {
			return;
		}
		if (action.assignAll()) {
			assignAll(parent, target, picos);
			return;
		}
		PicoDiscovery.PicoInfo chosen = action.pico();
		if (chosen.isBusy()) {
			String clientIp = chosen.busyClientIp();
			String busyDesc = clientIp.isEmpty()
				? " reports status \"" + chosen.status() + "\"" //$NON-NLS-1$ //$NON-NLS-2$
				: " is in a game with client " + clientIp; //$NON-NLS-1$
			int confirm = JOptionPane.showConfirmDialog(
				parent,
				chosen.label() + busyDesc + " - it may already be in use by another table. Use it anyway?", //$NON-NLS-1$
				"Device May Be In Use", //$NON-NLS-1$
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE
			);
			if (confirm != JOptionPane.YES_OPTION) {
				return;
			}
		}
		int index = action.tableIndex();
		String mac = chosen.macAddress();
		int existing = target.findTableByMac(mac);
		if (existing >= 0 && existing != index) {
			target.addMessage("Device " + mac + " is already assigned to table " + (existing + 1) + "; assignment cancelled."); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
		Object snapshot = target.createAssignmentSnapshot();
		target.setTableAddress(index, chosen.ipAddress(), chosen.port(), mac);
		if (target.saveAssignments()) {
			target.addMessage("Assigned " + chosen.display() + " to table " + (index + 1) + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			target.restoreAssignmentSnapshot(snapshot);
			target.addMessage("Assignment was not saved; correct the configuration errors and try again."); //$NON-NLS-1$
		}
	}

	/**
	 * Shows the discovered devices and returns the action the user picked:
	 * Assign Selected (one device for a table connection chosen right here via
	 * a dropdown, so there's no separate selection step to forget), Assign
	 * All (every device to the table matching its reported table number), or
	 * null if cancelled. Devices reporting a non-Available status are grayed
	 * out (still selectable).
	 */
	private static SearchAction choosePico(
			Component parent,
			List<PicoDiscovery.PicoInfo> picos,
			AssignTarget target,
			IntSupplier selectedIndexSupplier) {
		JList<PicoDiscovery.PicoInfo> list = new JList<>(picos.toArray(new PicoDiscovery.PicoInfo[0]));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(Math.min(picos.size(), 10));
		list.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> jList, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(jList, value, index, isSelected, cellHasFocus);
				PicoDiscovery.PicoInfo pico = (PicoDiscovery.PicoInfo) value;
				setText(pico.display());
				if (pico.isBusy() && !isSelected) {
					setForeground(Color.GRAY);
				}
				return this;
			}
		});
		int tableCount = target.getTableCount();
		JComboBox<String> tableCombo = new JComboBox<>();
		for (int i = 0; i < tableCount; i++) tableCombo.addItem("Table " + (i + 1)); //$NON-NLS-1$
		tableCombo.setSelectedIndex(defaultTableIndex(target, selectedIndexSupplier, tableCount));
		Object[] message = {
			picos.size() + " device(s) found. Select the one to use for Assign Selected," //$NON-NLS-1$
				+ " or Assign All to assign every device to the table matching its table number:", //$NON-NLS-1$
			new JScrollPane(list),
			"Assign Selected to:", //$NON-NLS-1$
			tableCombo
		};
		String[] options = {"Assign Selected", "Assign All", "Cancel"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		int result = JOptionPane.showOptionDialog(
			parent,
			message,
			"Update Auto Score Settings", //$NON-NLS-1$
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
		if (result == 0) {
			return new SearchAction(list.getSelectedValue(), false, tableCombo.getSelectedIndex());
		}
		if (result == 1) {
			return new SearchAction(null, true, -1);
		}
		return null;
	}

	/**
	 * The table the "Assign Selected to:" combo should default to: the row
	 * already selected in the caller's grid/dropdown, or - since that's often
	 * unset - the first table with no MAC on file yet, or table 1 if every
	 * table already has one.
	 */
	private static int defaultTableIndex(AssignTarget target, IntSupplier selectedIndexSupplier, int tableCount) {
		int selected = selectedIndexSupplier.getAsInt();
		if (selected >= 0 && selected < tableCount) return selected;
		for (int i = 0; i < tableCount; i++) {
			if (target.getTableMac(i).isEmpty()) return i;
		}
		return 0;
	}

	/**
	 * Assigns every discovered device to the table connection matching its
	 * reported table number ("Table N" goes to the Nth table), then saves all
	 * connections in one shot. Devices without a parsable table number are
	 * skipped with a message. If discovery found devices numbered beyond the
	 * configured tables, the user is asked whether to add the missing table(s)
	 * before assigning; declining leaves those devices skipped. Configured
	 * tables with no matching device are reported, not modified.
	 */
	private static void assignAll(Component parent, AssignTarget target, List<PicoDiscovery.PicoInfo> picos) {
		Object snapshot = target.createAssignmentSnapshot();
		List<PicoDiscovery.PicoInfo> busy = picos.stream().filter(PicoDiscovery.PicoInfo::isBusy).toList();
		boolean includeBusy = true;
		if (!busy.isEmpty()) {
			String details = busy.stream().map(PicoDiscovery.PicoInfo::display).reduce((a, b) -> a + "\n" + b).orElse(""); //$NON-NLS-1$ //$NON-NLS-2$
			int confirm = JOptionPane.showConfirmDialog(parent,
				"These devices are already in use and will be skipped unless you explicitly continue:\n" + details + "\n\nAssign them anyway?", //$NON-NLS-1$
				"Busy AutoScore Devices", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
			if (confirm != JOptionPane.YES_OPTION) {
				includeBusy = false;
			}
		}
		int maxTableNumber = 0;
		for (PicoDiscovery.PicoInfo pico : picos) {
			if (includeBusy || !pico.isBusy()) maxTableNumber = Math.max(maxTableNumber, pico.tableNumber());
		}
		int configuredCount = target.getTableCount();
		if (maxTableNumber > configuredCount) {
			int toAdd = maxTableNumber - configuredCount;
			int confirm = JOptionPane.showConfirmDialog(
				parent,
				"Found devices for " + maxTableNumber + " table(s) but only " + configuredCount + " configured. Add " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ toAdd + " table(s) and assign all?", //$NON-NLS-1$
				"Add Tables?", //$NON-NLS-1$
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			if (confirm == JOptionPane.YES_OPTION) {
				target.ensureTableCount(maxTableNumber);
			}
		}
		List<String> tableMacs = new ArrayList<>();
		for (int i = 0; i < target.getTableCount(); i++) tableMacs.add(target.getTableMac(i));
		List<PicoAssignmentPlanner.Assignment> plan = PicoAssignmentPlanner.plan(picos, tableMacs, includeBusy);
		StringBuilder preview = new StringBuilder("Proposed AutoScore assignments:\n"); //$NON-NLS-1$
		for (PicoAssignmentPlanner.Assignment assignment : plan) {
			preview.append(assignment.pico().display()).append("  -> "); //$NON-NLS-1$
			if (assignment.assignable()) preview.append("table ").append(assignment.targetIndex() + 1); //$NON-NLS-1$
			else preview.append("SKIP: ").append(assignment.reason()); //$NON-NLS-1$
			preview.append('\n');
		}
		int apply = JOptionPane.showConfirmDialog(parent, preview + "\nApply these assignments?", //$NON-NLS-1$
			"Confirm AutoScore Assignments", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE); //$NON-NLS-1$
		if (apply != JOptionPane.OK_OPTION) {
			target.restoreAssignmentSnapshot(snapshot);
			return;
		}

		for (PicoAssignmentPlanner.Assignment assignment : plan) {
			if (!assignment.assignable()) {
				target.addMessage("Skipped " + assignment.pico().display() + " - " + assignment.reason() + "."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		PicoAssignmentPlanner.ApplyResult result = PicoAssignmentPlanner.apply(plan, target);
		if (!result.saved()) target.restoreAssignmentSnapshot(snapshot);
		for (int row = 0; row < target.getTableCount(); row++) {
			if (!result.assignedRows().contains(row)) {
				target.addMessage("Table " + (row + 1) + " - no matching device found; left unchanged."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
}
