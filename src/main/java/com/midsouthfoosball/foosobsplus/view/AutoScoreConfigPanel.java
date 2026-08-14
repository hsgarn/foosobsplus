/**
Copyright © 2022-2026 Hugh Garner
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.IntPredicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

public class AutoScoreConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JLabel lblConfig;
	private final JTextArea txtConfig;
	private final JButton btnValidateConfig;
	private final JButton btnWriteConfig;
	private final JButton btnReadConfig;
	private final JButton btnResetConfig;
	private final JButton btnClearConfig;
	private final JComboBox<String> cmbTables;
	private final DefaultComboBoxModel<String> mdlTables;
	// Supplies the live connection state of the table at a given index (set by
	// Main), so the Table dropdown can show a green/red dot per table.
	private IntPredicate tableConnected = i -> false;
	private static final Icon DOT_CONNECTED = makeDot(new Color(0, 170, 0));
	private static final Icon DOT_DISCONNECTED = makeDot(new Color(200, 0, 0));
	public AutoScoreConfigPanel() {
		// Row 2 (the config text area) is the only row that grows, so extra panel
		// height goes into the text area and pushes the button rows below it down.
		setLayout(new MigLayout("", "[][grow]", "[][][grow][][][]")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JLabel lblTable = new JLabel("Table:"); //$NON-NLS-1$
		add(lblTable, "cell 0 0,alignx trailing"); //$NON-NLS-1$
		mdlTables = new DefaultComboBoxModel<>();
		cmbTables = new JComboBox<>(mdlTables);
		cmbTables.setRenderer(new ConnectionStatusRenderer());
		add(cmbTables, "cell 1 0,growx"); //$NON-NLS-1$
		lblConfig = new JLabel(Messages.getString("AutoScoreConfigPanel.Config")); //$NON-NLS-1$
		add(lblConfig, "cell 0 1,alignx leading"); //$NON-NLS-1$
		txtConfig = new JTextArea(20,50);
		add(txtConfig, "cell 0 2 2 1, growx, growy"); //$NON-NLS-1$
		btnReadConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Read")); //$NON-NLS-1$
		add(btnReadConfig, "cell 0 3 1 1"); //$NON-NLS-1$
		btnClearConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Clear")); //$NON-NLS-1$
		add(btnClearConfig, "cell 1 3 1 1"); //$NON-NLS-1$
		btnValidateConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Validate")); //$NON-NLS-1$
		add(btnValidateConfig, "cell 0 4 2 1, growx"); //$NON-NLS-1$
		btnWriteConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Write")); //$NON-NLS-1$
		add(btnWriteConfig, "cell 0 5 1 1"); //$NON-NLS-1$
		btnResetConfig = new JButton(Messages.getString("AutoScoreConfigPanel.Reset")); //$NON-NLS-1$
		add(btnResetConfig, "cell 1 5 1 1, growx"); //$NON-NLS-1$
	}
	// Rebuilds the Table dropdown from the current table labels, preserving the
	// current selection (clamped to the new size) rather than resetting it, so
	// unrelated refreshes (e.g. a connection state change) don't yank the
	// selection away from the table the user is working with.
	public void setTableLabels(List<String> labels) {
		int keep = Math.max(0, Math.min(cmbTables.getSelectedIndex(), labels.size() - 1));
		mdlTables.removeAllElements();
		labels.forEach(mdlTables::addElement);
		if (labels.size() > 0) cmbTables.setSelectedIndex(keep);
	}
	// Selects the table at the given index (e.g. to default to the displayed
	// table when the Config window is opened).
	public void selectTable(int index) {
		if (index >= 0 && index < mdlTables.getSize()) cmbTables.setSelectedIndex(index);
	}
	// Index of the table currently selected in the dropdown. Aligned with the
	// order of Settings.getTableConnections() and the live AutoScoreManager list.
	public int getSelectedTableIndex() {
		return cmbTables.getSelectedIndex();
	}
	// Registers the provider that reports each table's connection state by index,
	// and triggers an initial repaint of the dropdown dots.
	public void setTableConnectedProvider(IntPredicate provider) {
		this.tableConnected = provider;
		refreshTableStatus();
	}
	// Repaints the Table dropdown so its connection dots reflect current state.
	public void refreshTableStatus() {
		cmbTables.repaint();
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
	// Renders each table in the dropdown with a green (connected) or red
	// (disconnected) dot. For the collapsed display (index -1) it uses the
	// currently selected table's state.
	private class ConnectionStatusRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			int statusIdx = (index >= 0) ? index : cmbTables.getSelectedIndex();
			boolean connected = statusIdx >= 0 && tableConnected.test(statusIdx);
			if (comp instanceof JLabel) {
				((JLabel) comp).setIcon(connected ? DOT_CONNECTED : DOT_DISCONNECTED);
			}
			return comp;
		}
	}
	public void clearConfigTextArea() {
		txtConfig.setText(""); //$NON-NLS-1$
	}
	public void appendConfigTextArea(String line) {
		txtConfig.append(line);
	}
	public String getConfigTextArea() {
		String text = txtConfig.getText();
		return text;
	}
	////// Listeners \\\\\\
	public void addReadConfigListener(ActionListener listenForBtnReadConfig) {
		btnReadConfig.addActionListener(listenForBtnReadConfig);
	}
	public void addWriteConfigListener(ActionListener listenForBtnWriteConfig) {
		btnWriteConfig.addActionListener(listenForBtnWriteConfig);
	}
	public void addValidateConfigListener(ActionListener listenForBtnValidateConfig) {
		btnValidateConfig.addActionListener(listenForBtnValidateConfig);
	}
	public void addResetConfigListener(ActionListener listenForBtnResetConfig) {
		btnResetConfig.addActionListener(listenForBtnResetConfig);
	}
	public void addClearConfigListener(ActionListener listenForBtnClearConfig) {
		btnClearConfig.addActionListener(listenForBtnClearConfig);
	}
}