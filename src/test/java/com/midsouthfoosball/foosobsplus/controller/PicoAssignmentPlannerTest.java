package com.midsouthfoosball.foosobsplus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.controller.PicoAssignmentPlanner.Disposition;
import com.midsouthfoosball.foosobsplus.main.PicoDiscovery.PicoInfo;
import com.midsouthfoosball.foosobsplus.model.TableConnection;

class PicoAssignmentPlannerTest {
	@Test void macMatchWinsOverReportedTableNumber() {
		PicoInfo pico = pico(1, "10.0.0.2", "AA-BB-CC-DD-EE-02", "FREE");
		var assignment = PicoAssignmentPlanner.plan(List.of(pico), List.of("", "AA-BB-CC-DD-EE-02"), true).get(0);
		assertEquals(1, assignment.targetIndex());
		assertEquals(Disposition.ASSIGN, assignment.disposition());
	}

	@Test void classifiesBusyDuplicatesConflictsAndRangeErrors() {
		List<PicoInfo> picos = List.of(
			pico(1, "10.0.0.1", "AA-BB-CC-DD-EE-01", "BUSY:10.0.0.9"),
			pico(1, "10.0.0.2", "AA-BB-CC-DD-EE-02", "FREE"),
			pico(1, "10.0.0.3", "AA-BB-CC-DD-EE-03", "FREE"),
			pico(3, "10.0.0.4", "AA-BB-CC-DD-EE-04", "FREE"));
		var plan = PicoAssignmentPlanner.plan(picos, List.of("AA-BB-CC-DD-EE-99", ""), false);
		assertEquals(List.of(Disposition.BUSY, Disposition.MAC_CONFLICT, Disposition.DUPLICATE_TARGET, Disposition.OUT_OF_RANGE),
			plan.stream().map(PicoAssignmentPlanner.Assignment::disposition).toList());
	}

	@Test void repeatedMacIsRejected() {
		var plan = PicoAssignmentPlanner.plan(List.of(
			pico(1, "10.0.0.1", "AA-BB-CC-DD-EE-01", "FREE"),
			pico(2, "10.0.0.2", "AA:BB:CC:DD:EE:01", "FREE")), List.of("", ""), true);
		assertEquals(Disposition.DUPLICATE_MAC, plan.get(1).disposition());
	}

	@Test void busyDeviceCanBeExplicitlyIncluded() {
		var assignment = PicoAssignmentPlanner.plan(List.of(
			pico(1, "10.0.0.1", "AA-BB-CC-DD-EE-01", "BUSY:10.0.0.9")), List.of(""), true).get(0);
		assertEquals(Disposition.ASSIGN, assignment.disposition());
	}

	@Test void addingMissingRowsMakesPreviouslyOutOfRangeDeviceAssignable() {
		PicoInfo third = pico(3, "10.0.0.3", "AA-BB-CC-DD-EE-03", "FREE");
		assertEquals(Disposition.OUT_OF_RANGE, PicoAssignmentPlanner.plan(List.of(third), List.of(""), true).get(0).disposition());
		assertEquals(Disposition.ASSIGN, PicoAssignmentPlanner.plan(List.of(third), List.of("", "", ""), true).get(0).disposition());
	}

	@Test void legacyAssignmentPreservesKnownMac() {
		FakeTarget target = new FakeTarget(true);
		target.rows.get(0).setMacAddress("AA-BB-CC-DD-EE-01");
		PicoInfo legacy = new PicoInfo("Table 1", "10.0.0.8", "5051", "", "", "raw");
		PicoAssignmentPlanner.apply(PicoAssignmentPlanner.plan(List.of(legacy), target.macs(), true), target);
		assertEquals("AA-BB-CC-DD-EE-01", target.rows.get(0).getMacAddress());
	}

	@Test void failedSaveRollsBackAndDoesNotLogSuccess() {
		FakeTarget target = new FakeTarget(false);
		var plan = PicoAssignmentPlanner.plan(List.of(pico(1, "10.0.0.9", "AA-BB-CC-DD-EE-09", "FREE")), target.macs(), true);
		var result = PicoAssignmentPlanner.apply(plan, target);
		assertFalse(result.saved());
		assertEquals("10.0.0.1", target.rows.get(0).getServerAddress());
		assertTrue(target.messages.stream().noneMatch(m -> m.startsWith("Assigned Table")));
	}

	@Test void successfulSaveLogsOnlyAfterPersistence() {
		FakeTarget target = new FakeTarget(true);
		var plan = PicoAssignmentPlanner.plan(List.of(pico(1, "10.0.0.9", "AA-BB-CC-DD-EE-09", "FREE")), target.macs(), true);
		assertTrue(PicoAssignmentPlanner.apply(plan, target).saved());
		assertTrue(target.savedBeforeSuccessMessage);
	}

	private static PicoInfo pico(int table, String ip, String mac, String status) {
		return new PicoInfo("Table " + table, ip, "5051", mac, status, "raw");
	}

	private static final class FakeTarget implements PicoSearchHelper.AssignTarget {
		private final boolean saveResult;
		private List<TableConnection> rows = new ArrayList<>(List.of(new TableConnection("One", "10.0.0.1", "5051", false, false)));
		private final List<String> messages = new ArrayList<>();
		private boolean saved;
		private boolean savedBeforeSuccessMessage;
		FakeTarget(boolean saveResult) { this.saveResult = saveResult; }
		List<String> macs() { return rows.stream().map(TableConnection::getMacAddress).toList(); }
		public int getTableCount() { return rows.size(); }
		public void setTableAddress(int i, String host, String port, String mac) { rows.get(i).setServerAddress(host); rows.get(i).setServerPort(port); if (!mac.isBlank()) rows.get(i).setMacAddress(mac); }
		public void ensureTableCount(int count) { while (rows.size() < count) rows.add(new TableConnection("New", "10.0.0.2", "5051", false, false)); }
		public int findTableByMac(String mac) { return -1; }
		public String getTableMac(int i) { return rows.get(i).getMacAddress(); }
		public Object createAssignmentSnapshot() { return rows.stream().map(TableConnection::copy).toList(); }
		@SuppressWarnings("unchecked") public void restoreAssignmentSnapshot(Object snapshot) { rows = new ArrayList<>((List<TableConnection>) snapshot); }
		public void addMessage(String message) { if (message.startsWith("Assigned ")) savedBeforeSuccessMessage = saved; messages.add(message); }
		public boolean saveAssignments() { saved = true; return saveResult; }
	}
}
