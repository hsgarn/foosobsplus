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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntSupplier;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.midsouthfoosball.foosobsplus.main.PicoDiscovery;

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
		/** Appends a line to the target's message/status log. */
		void addMessage(String message);
		/** Persists the assignment(s) just made and refreshes any dependent runtime state. */
		void saveAssignments();
	}

	/**
	 * The user's choice in the search results dialog: a single device to
	 * assign to a specific table, or assign every device to its own table.
	 */
	private record SearchAction(PicoDiscovery.PicoInfo pico, boolean assignAll) {}

	/**
	 * Full post-discovery flow: logs found devices, shows the picker dialog,
	 * and applies the result to {@code target}. {@code selectedIndexSupplier}
	 * gives the table index Assign Selected should target (e.g. the row/table
	 * currently selected in the caller's UI); a negative value means no table
	 * is selected.
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
		SearchAction action = choosePico(parent, picos);
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
		int index = selectedIndexSupplier.getAsInt();
		if (index < 0 || index >= target.getTableCount()) {
			target.addMessage("No table selected - could not assign " + chosen.display() + "."); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
		target.setTableAddress(index, chosen.ipAddress(), chosen.port(), chosen.macAddress());
		target.addMessage("Assigned " + chosen.display() + " to table " + (index + 1) + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		target.saveAssignments();
	}

	/**
	 * Shows the discovered devices and returns the action the user picked:
	 * Assign Selected (one device for a specific table connection), Assign
	 * All (every device to the table matching its reported table number), or
	 * null if cancelled. Devices reporting a non-Available status are grayed
	 * out (still selectable).
	 */
	private static SearchAction choosePico(Component parent, List<PicoDiscovery.PicoInfo> picos) {
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
		Object[] message = {
			picos.size() + " device(s) found. Select the one to use for the selected table's IP Address and Port," //$NON-NLS-1$
				+ " or Assign All to assign every device to the table matching its table number:", //$NON-NLS-1$
			new JScrollPane(list)
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
			return new SearchAction(list.getSelectedValue(), false);
		}
		if (result == 1) {
			return new SearchAction(null, true);
		}
		return null;
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
		int maxTableNumber = 0;
		for (PicoDiscovery.PicoInfo pico : picos) {
			maxTableNumber = Math.max(maxTableNumber, pico.tableNumber());
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

		boolean assignedAny = false;
		Set<Integer> assignedTableNumbers = new HashSet<>();
		for (PicoDiscovery.PicoInfo pico : picos) {
			int tableNumber = pico.tableNumber();
			if (tableNumber < 1) {
				target.addMessage("Skipped " + pico.display() + " - could not determine its table number."); //$NON-NLS-1$ //$NON-NLS-2$
				continue;
			}
			if (tableNumber > target.getTableCount()) {
				target.addMessage("Skipped " + pico.label() + " - only " + target.getTableCount() + " table(s) configured."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				continue;
			}
			target.setTableAddress(tableNumber - 1, pico.ipAddress(), pico.port(), pico.macAddress());
			target.addMessage("Assigned " + pico.display() + " to table " + tableNumber + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			assignedAny = true;
			assignedTableNumbers.add(tableNumber);
		}
		for (int t = 1; t <= target.getTableCount(); t++) {
			if (!assignedTableNumbers.contains(t)) {
				target.addMessage("Table " + t + " - no matching device found; left unchanged."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		target.addMessage("Assigned " + assignedTableNumbers.size() + " of " + target.getTableCount() + " table(s)."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (assignedAny) {
			target.saveAssignments();
		}
	}
}
