package com.midsouthfoosball.foosobsplus.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.midsouthfoosball.foosobsplus.main.PicoDiscovery.PicoInfo;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

/** Pure, UI-free planning for mapping discovery responses to table rows. */
public final class PicoAssignmentPlanner {
	private PicoAssignmentPlanner() {}

	public enum Disposition { ASSIGN, BUSY, INVALID_TABLE, OUT_OF_RANGE, DUPLICATE_MAC, DUPLICATE_TARGET, MAC_CONFLICT }
	public record Assignment(PicoInfo pico, int targetIndex, Disposition disposition, String reason) {
		public boolean assignable() { return disposition == Disposition.ASSIGN; }
	}
	public record ApplyResult(boolean saved, Set<Integer> assignedRows) {}

	/** Applies a plan atomically; failed persistence restores the target snapshot. */
	public static ApplyResult apply(List<Assignment> plan, PicoSearchHelper.AssignTarget target) {
		Object snapshot = target.createAssignmentSnapshot();
		Set<Integer> rows = new HashSet<>();
		List<String> success = new ArrayList<>();
		for (Assignment assignment : plan) {
			if (!assignment.assignable()) continue;
			int row = assignment.targetIndex();
			target.setTableAddress(row, assignment.pico().ipAddress(), assignment.pico().port(), assignment.pico().macAddress());
			rows.add(row);
			success.add("Assigned " + assignment.pico().display() + " to table " + (row + 1) + "."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (rows.isEmpty()) return new ApplyResult(false, Set.of());
		if (!target.saveAssignments()) {
			target.restoreAssignmentSnapshot(snapshot);
			target.addMessage("Assignments were not saved; correct the configuration errors and try again."); //$NON-NLS-1$
			return new ApplyResult(false, Set.of());
		}
		success.forEach(target::addMessage);
		target.addMessage("Assigned " + rows.size() + " of " + target.getTableCount() + " table(s)."); //$NON-NLS-1$ //$NON-NLS-2$
		return new ApplyResult(true, Set.copyOf(rows));
	}

	public static List<Assignment> plan(List<PicoInfo> picos, List<String> tableMacs, boolean includeBusy) {
		List<Assignment> result = new ArrayList<>();
		Set<String> seenMacs = new HashSet<>();
		Set<Integer> seenTargets = new HashSet<>();
		for (PicoInfo pico : picos) {
			String mac = TableConnection.normalizeMac(pico.macAddress());
			if (pico.isBusy() && !includeBusy) {
				result.add(skip(pico, Disposition.BUSY, "device is already in use")); //$NON-NLS-1$
				continue;
			}
			if (!mac.isEmpty() && !seenMacs.add(mac)) {
				result.add(skip(pico, Disposition.DUPLICATE_MAC, "duplicate discovery response for MAC " + mac)); //$NON-NLS-1$
				continue;
			}
			int target = findMac(tableMacs, mac);
			if (target < 0) target = pico.tableNumber() - 1;
			if (target < 0) {
				result.add(skip(pico, Disposition.INVALID_TABLE, "could not determine table number")); //$NON-NLS-1$
				continue;
			}
			if (target >= tableMacs.size()) {
				result.add(new Assignment(pico, target, Disposition.OUT_OF_RANGE, "table " + (target + 1) + " is not configured")); //$NON-NLS-1$ //$NON-NLS-2$
				continue;
			}
			if (!seenTargets.add(target)) {
				result.add(new Assignment(pico, target, Disposition.DUPLICATE_TARGET, "another device already targets table " + (target + 1))); //$NON-NLS-1$
				continue;
			}
			String existing = TableConnection.normalizeMac(tableMacs.get(target));
			if (!existing.isEmpty() && !mac.isEmpty() && !existing.equals(mac)) {
				result.add(new Assignment(pico, target, Disposition.MAC_CONFLICT, "table " + (target + 1) + " is assigned to MAC " + existing)); //$NON-NLS-1$ //$NON-NLS-2$
				continue;
			}
			result.add(new Assignment(pico, target, Disposition.ASSIGN, "")); //$NON-NLS-1$
		}
		return result;
	}

	private static int findMac(List<String> tableMacs, String mac) {
		if (mac.isEmpty()) return -1;
		for (int i = 0; i < tableMacs.size(); i++) if (mac.equals(TableConnection.normalizeMac(tableMacs.get(i)))) return i;
		return -1;
	}
	private static Assignment skip(PicoInfo pico, Disposition disposition, String reason) {
		return new Assignment(pico, -1, disposition, reason);
	}
}
