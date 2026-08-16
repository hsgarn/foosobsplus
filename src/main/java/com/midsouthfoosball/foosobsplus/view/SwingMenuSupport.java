package com.midsouthfoosball.foosobsplus.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.function.IntConsumer;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/** Headless-testable construction for the dynamic portions of the main menu. */
final class SwingMenuSupport {
	private SwingMenuSupport() { }

	static JMenuBar menuBar(JMenu... menus) {
		JMenuBar menuBar = new JMenuBar();
		for (JMenu menu : menus) menuBar.add(menu);
		return menuBar;
	}

	static void rebuildActiveTables(JMenu menu, ButtonGroup group, List<String> labels,
			int activeIndex, boolean[] connected, IntConsumer selectListener) {
		group.getElements().asIterator().forEachRemaining(group::remove);
		menu.removeAll();
		JRadioButtonMenuItem activeItem = null;
		for (int i = 0; i < labels.size(); i++) {
			final int index = i;
			boolean isConnected = i < connected.length && connected[i];
			String dotColor = isConnected ? "#00AA00" : "#C80000";
			String text = "<html><font color='" + dotColor + "'>●</font>&nbsp;"
				+ escapeHtml(labels.get(i)) + "</html>";
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(text);
			item.addActionListener(e -> { if (selectListener != null) selectListener.accept(index); });
			group.add(item);
			menu.add(item);
			if (i == activeIndex) activeItem = item;
		}
		if (activeItem != null) activeItem.setSelected(true);
	}

	static void rebuildTableViews(JMenu menu, List<String> labels, IntConsumer viewListener,
			Runnable viewAllListener, String viewAllLabel) {
		menu.removeAll();
		for (int i = 0; i < labels.size(); i++) {
			final int index = i;
			JMenuItem item = new JMenuItem(labels.get(i));
			item.addActionListener(e -> { if (viewListener != null) viewListener.accept(index); });
			menu.add(item);
		}
		menu.addSeparator();
		JMenuItem viewAll = new JMenuItem(viewAllLabel);
		viewAll.addActionListener(e -> { if (viewAllListener != null) viewAllListener.run(); });
		menu.add(viewAll);
	}

	static void rebuildAutoScoreConnections(JMenu menu, List<String> labels, boolean[] connected,
			IntConsumer connectListener, IntConsumer disconnectListener, Runnable connectAllListener,
			Runnable disconnectAllListener, String connectAllLabel, String disconnectAllLabel) {
		menu.removeAll();
		for (int i = 0; i < labels.size(); i++) {
			final int index = i;
			final boolean isConnected = i < connected.length && connected[i];
			JMenuItem item = new JMenuItem(labels.get(i)
				+ (isConnected ? "  — connected" : "  — disconnected"));
			item.setIcon(makeDotIcon(isConnected ? new Color(0, 170, 0) : new Color(200, 0, 0)));
			item.addActionListener(e -> {
				if (isConnected) {
					if (disconnectListener != null) disconnectListener.accept(index);
				} else if (connectListener != null) {
					connectListener.accept(index);
				}
			});
			menu.add(item);
		}
		menu.addSeparator();
		JMenuItem connectAll = new JMenuItem(connectAllLabel);
		connectAll.addActionListener(e -> { if (connectAllListener != null) connectAllListener.run(); });
		menu.add(connectAll);
		JMenuItem disconnectAll = new JMenuItem(disconnectAllLabel);
		disconnectAll.addActionListener(e -> { if (disconnectAllListener != null) disconnectAllListener.run(); });
		menu.add(disconnectAll);
	}

	private static String escapeHtml(String value) {
		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private static Icon makeDotIcon(Color color) {
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
}
