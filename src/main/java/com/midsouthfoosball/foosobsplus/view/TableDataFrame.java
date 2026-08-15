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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import javax.swing.JFrame;

import com.midsouthfoosball.foosobsplus.model.AppConfig;
import com.midsouthfoosball.foosobsplus.model.TableSession;

/**
 * Window wrapper for {@link TableDataPanel}. Created on demand (mirroring
 * {@code Main}'s per-table {@code TableViewFrame} windows) and disposed on
 * close, so a fresh instance re-reads the current table list next time it is
 * opened rather than needing a live add/remove-row mechanism of its own.
 */
@SuppressWarnings("serial")
public class TableDataFrame extends JFrame {
	private final TableDataPanel tableDataPanel;
	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;

	public TableDataFrame(Supplier<List<TableSession>> sessionsSupplier, Supplier<TableSession> activeSessionSupplier,
			BooleanSupplier obsConnectedSupplier, IntPredicate autoScoreConnectedProvider,
			IntConsumer sendToObsListener, TableDataPanel.FieldAdjustListener adjustListener,
			TableDataPanel.TableNamesListener namesListener, IntConsumer clearTableListener,
			Runnable clearAllTablesListener) {
		super(PROGRAMNAME + " " + Messages.getString("TableDataFrame.Title")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		tableDataPanel = new TableDataPanel(sessionsSupplier, activeSessionSupplier, obsConnectedSupplier,
				autoScoreConnectedProvider, sendToObsListener, adjustListener, namesListener,
				clearTableListener, clearAllTablesListener);
		getContentPane().add(tableDataPanel);
		pack();
		tableDataPanel.startPolling();
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosed(WindowEvent e) { tableDataPanel.stopPolling(); }
		});
	}
	public TableDataPanel getTableDataPanel() {
		return tableDataPanel;
	}
}
