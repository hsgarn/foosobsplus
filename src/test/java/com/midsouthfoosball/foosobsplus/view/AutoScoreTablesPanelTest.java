package com.midsouthfoosball.foosobsplus.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AutoScoreTablesPanelTest {
	@BeforeAll static void headless() { System.setProperty("java.awt.headless", "true"); }

	@Test void macIsReadOnlyAndAddressEditClearsKnownIdentity() throws Exception {
		onEdt(() -> {
			AutoScoreTablesPanel panel = new AutoScoreTablesPanel();
			JTable table = panel.tableForTesting();
			panel.setTableAddress(0, "10.0.0.1", "5051", "AA-BB-CC-DD-EE-01");
			assertFalse(table.getModel().isCellEditable(0, 3));
			table.getModel().setValueAt("10.0.0.2", 0, 1);
			assertEquals("", table.getModel().getValueAt(0, 3));
		});
	}

	@Test void restoringDefaultsClearsMacAndConnectedRowsCannotBeDeleted() throws Exception {
		onEdt(() -> {
			AutoScoreTablesPanel panel = new AutoScoreTablesPanel();
			JTable table = panel.tableForTesting();
			panel.setTableAddress(0, "10.0.0.1", "5051", "AA-BB-CC-DD-EE-01");
			table.setRowSelectionInterval(0, 0);
			panel.restoreSelectedRowDefaults();
			assertEquals("", table.getModel().getValueAt(0, 3));
			panel.setTableConnectedProvider(row -> row == 0);
			assertFalse(panel.canDeleteRow(0));
		});
	}

	private static void onEdt(Runnable action) throws Exception {
		AtomicReference<Throwable> failure = new AtomicReference<>();
		SwingUtilities.invokeAndWait(() -> { try { action.run(); } catch (Throwable t) { failure.set(t); } });
		if (failure.get() != null) throw new AssertionError(failure.get());
	}
}
