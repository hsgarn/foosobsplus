package com.midsouthfoosball.foosobsplus.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Focused Swing main-menu behavior")
class SwingMenuSupportTest {
	@Test
	@DisplayName("main menu keeps AutoScore immediately before Tables")
	void mainMenuOrderKeepsAutoScoreBeforeTables() {
		JMenuBar bar = SwingMenuSupport.menuBar(menu("File"), menu("Edit"), menu("OBS"),
			menu("AutoScore"), menu("Tables"), menu("View"), menu("Help"));

		assertEquals(List.of("File", "Edit", "OBS", "AutoScore", "Tables", "View", "Help"),
			menuNames(bar));
	}

	@Test
	@DisplayName("active-table menu shows status, escapes labels, selects one table, and dispatches its index")
	void activeTableMenuBuildsStatusAndSelection() {
		JMenu menu = menu("Active Table");
		ButtonGroup group = new ButtonGroup();
		AtomicInteger selected = new AtomicInteger(-1);

		SwingMenuSupport.rebuildActiveTables(menu, group,
			List.of("Center & Court", "Table <2>"), 1, new boolean[] {true, false}, selected::set);

		assertEquals(2, menu.getItemCount());
		JRadioButtonMenuItem first = assertInstanceOf(JRadioButtonMenuItem.class, menu.getItem(0));
		JRadioButtonMenuItem second = assertInstanceOf(JRadioButtonMenuItem.class, menu.getItem(1));
		assertTrue(first.getText().contains("#00AA00"));
		assertTrue(first.getText().contains("Center &amp; Court"));
		assertTrue(second.getText().contains("#C80000"));
		assertTrue(second.getText().contains("Table &lt;2&gt;"));
		assertFalse(first.isSelected());
		assertTrue(second.isSelected());

		first.doClick();
		assertEquals(0, selected.get());
		assertTrue(first.isSelected());
		assertFalse(second.isSelected());
	}

	@Test
	@DisplayName("table-monitor menu preserves table order and wires table and open-all actions")
	void tableViewsMenuWiresActions() {
		JMenu menu = menu("Table Monitors");
		AtomicInteger viewed = new AtomicInteger(-1);
		AtomicInteger viewedAll = new AtomicInteger();

		SwingMenuSupport.rebuildTableViews(menu, List.of("Table 1", "Table 2"),
			viewed::set, viewedAll::incrementAndGet, "Open All Table Monitors");

		assertEquals(4, menu.getItemCount());
		assertEquals("Table 1", menu.getItem(0).getText());
		assertEquals("Table 2", menu.getItem(1).getText());
		assertNull(menu.getItem(2));
		assertEquals("Open All Table Monitors", menu.getItem(3).getText());

		menu.getItem(1).doClick();
		menu.getItem(3).doClick();
		assertEquals(1, viewed.get());
		assertEquals(1, viewedAll.get());
	}

	@Test
	@DisplayName("AutoScore connections toggle the appropriate table and expose connect-all actions")
	void autoScoreConnectionsWireToggleAndBulkActions() {
		JMenu menu = menu("Connections");
		List<Integer> connected = new ArrayList<>();
		List<Integer> disconnected = new ArrayList<>();
		AtomicInteger connectAll = new AtomicInteger();
		AtomicInteger disconnectAll = new AtomicInteger();

		SwingMenuSupport.rebuildAutoScoreConnections(menu, List.of("Table 1", "Table 2"),
			new boolean[] {true, false}, connected::add, disconnected::add,
			connectAll::incrementAndGet, disconnectAll::incrementAndGet, "Connect All", "Disconnect All");

		assertEquals(5, menu.getItemCount());
		assertEquals("Table 1  — connected", menu.getItem(0).getText());
		assertEquals("Table 2  — disconnected", menu.getItem(1).getText());
		assertNotNull(menu.getItem(0).getIcon());
		assertNotNull(menu.getItem(1).getIcon());
		assertNull(menu.getItem(2));

		menu.getItem(0).doClick();
		menu.getItem(1).doClick();
		menu.getItem(3).doClick();
		menu.getItem(4).doClick();
		assertEquals(List.of(1), connected);
		assertEquals(List.of(0), disconnected);
		assertEquals(1, connectAll.get());
		assertEquals(1, disconnectAll.get());
	}

	private static JMenu menu(String text) {
		return new JMenu(text);
	}

	private static List<String> menuNames(JMenuBar bar) {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < bar.getMenuCount(); i++) names.add(bar.getMenu(i).getText());
		return names;
	}
}
