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
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

import com.midsouthfoosball.foosobsplus.model.AppConfig;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;
import com.midsouthfoosball.foosobsplus.model.TableSession;
import com.midsouthfoosball.foosobsplus.model.Team;

/**
 * A small always-live window showing one (possibly non-displayed) table's
 * score, game count, match count, and timeouts for each team, plus a
 * "Send to OBS" button that makes this table the active/displayed table.
 *
 * <p>The window reads its {@link TableSession} directly and polls it on a Swing
 * {@link Timer} so background tables update as their AutoScore feeds them. It
 * holds no OBS or controller state of its own; switching is delegated to the
 * caller-supplied listener (which routes to {@code Main.selectTable}).
 */
@SuppressWarnings("serial")
public class TableViewFrame extends JFrame {

	private static final String PROGRAMNAME = AppConfig.PROGRAM_NAME;
	private static final String ON = "1"; //$NON-NLS-1$
	private static final int REFRESH_MS = 500;
	private static final Color LIVE_GREEN = new Color(0, 170, 0);
	private static final Color LIVE_YELLOW = new Color(220, 180, 0);
	private static final Color LIVE_RED = new Color(200, 0, 0);

	private final int tableIndex;
	private final TableSession session;
	private final IntSupplier activeIndexSupplier;
	private final BooleanSupplier obsConnectedSupplier;
	private final BooleanSupplier autoScoreConnectedSupplier;
	private final Timer refreshTimer;
	private final JLabel[] rowLabels = new JLabel[3];
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
	 */
	public TableViewFrame(int tableIndex, String tableName, TableSession session, IntSupplier activeIndexSupplier,
			BooleanSupplier obsConnectedSupplier, BooleanSupplier autoScoreConnectedSupplier,
			IntConsumer sendToObsListener) {
		super(buildTitle(tableName));
		this.tableIndex = tableIndex;
		this.session = session;
		this.activeIndexSupplier = activeIndexSupplier;
		this.obsConnectedSupplier = obsConnectedSupplier;
		this.autoScoreConnectedSupplier = autoScoreConnectedSupplier;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);

		JPanel panel = new JPanel(new MigLayout("insets 12, hidemode 3", "[70]18[55]14[55]14[65]14[70]", "[]10[]6[]6[]16[]14[]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Font headerFont = new Font("Tahoma", Font.BOLD, 13); //$NON-NLS-1$
		Font cellFont = new Font("Tahoma", Font.PLAIN, 18); //$NON-NLS-1$

		panel.add(new JLabel(""), "cell 0 0"); //$NON-NLS-1$ //$NON-NLS-2$
		addHeader(panel, Messages.getString("TableViewFrame.Score"), headerFont, 1, 0); //$NON-NLS-1$
		addHeader(panel, Messages.getString("TableViewFrame.Games"), headerFont, 2, 0); //$NON-NLS-1$
		addHeader(panel, Messages.getString("TableViewFrame.Matches"), headerFont, 3, 0); //$NON-NLS-1$
		addHeader(panel, Messages.getString("TableViewFrame.Timeouts"), headerFont, 4, 0); //$NON-NLS-1$

		String teamPrefix = Messages.getString("TableViewFrame.Team"); //$NON-NLS-1$
		for (int i = 0; i < 3; i++) {
			int row = i + 1;
			rowLabels[i] = new JLabel(teamPrefix + " " + (i + 1)); //$NON-NLS-1$
			rowLabels[i].setFont(headerFont);
			panel.add(rowLabels[i], "cell 0 " + row); //$NON-NLS-1$
			scoreValues[i] = addCell(panel, cellFont, 1, row);
			gamesValues[i] = addCell(panel, cellFont, 2, row);
			matchesValues[i] = addCell(panel, cellFont, 3, row);
			timeoutValues[i] = addCell(panel, cellFont, 4, row);
		}

		// Status indicators, each a label with a filled status circle beneath it:
		// "AutoScore" (green connected / red not) and "OBS Live" (green active+OBS
		// connected / yellow active+OBS disconnected / red background table).
		autoScoreLabel = makeIndicatorLabel(Messages.getString("TableViewFrame.AutoScore"), headerFont); //$NON-NLS-1$
		obsLiveLabel = makeIndicatorLabel(Messages.getString("TableViewFrame.OBSLive"), headerFont); //$NON-NLS-1$
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
		statusPanel.add(autoScoreLabel);
		statusPanel.add(obsLiveLabel);
		panel.add(statusPanel, "cell 0 4 5 1,alignx center"); //$NON-NLS-1$

		sendToObsButton = new JButton(Messages.getString("TableViewFrame.SendToOBS")); //$NON-NLS-1$
		sendToObsButton.addActionListener(e -> {
			if (sendToObsListener != null) sendToObsListener.accept(this.tableIndex);
		});
		panel.add(sendToObsButton, "cell 0 5 5 1,alignx center"); //$NON-NLS-1$

		getContentPane().add(panel);
		refresh();
		pack();

		refreshTimer = new Timer(REFRESH_MS, e -> refresh());
		refreshTimer.start();
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosed(WindowEvent e) { refreshTimer.stop(); }
		});
	}

	private static String buildTitle(String tableName) {
		return PROGRAMNAME + " " + Messages.getString("TableViewFrame.Title") //$NON-NLS-1$ //$NON-NLS-2$
				+ ": " + Messages.getString("TableViewFrame.Table") + " " + tableName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	private static void addHeader(JPanel panel, String text, Font font, int col, int row) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, "cell " + col + " " + row + ",alignx center"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private static JLabel addCell(JPanel panel, Font font, int col, int row) {
		JLabel label = new JLabel("0"); //$NON-NLS-1$
		label.setFont(font);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label, "cell " + col + " " + row + ",alignx center"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return label;
	}

	/** Reads the session's current values into the display. Runs on the EDT. */
	private void refresh() {
		boolean cutThroat = Settings.getControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE).equals(ON); //$NON-NLS-1$
		Team[] teams = { session.getTeam1(), session.getTeam2(), session.getTeam3() };
		for (int i = 0; i < 3; i++) {
			boolean visible = i < 2 || cutThroat;
			rowLabels[i].setVisible(visible);
			scoreValues[i].setVisible(visible);
			gamesValues[i].setVisible(visible);
			matchesValues[i].setVisible(visible);
			timeoutValues[i].setVisible(visible);
			if (!visible) continue;
			Team team = teams[i];
			scoreValues[i].setText(Integer.toString(team.getScore()));
			gamesValues[i].setText(Integer.toString(team.getGameCount()));
			matchesValues[i].setText(Integer.toString(team.getMatchCount()));
			timeoutValues[i].setText(Integer.toString(team.getTimeOutCount()));
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
