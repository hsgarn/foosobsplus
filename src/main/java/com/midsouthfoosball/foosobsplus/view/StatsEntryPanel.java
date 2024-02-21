/**
Copyright © 2020-2024 Hugh Garner
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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class StatsEntryPanel extends JPanel {
	private JLabel lblCode;
	private JTextField txtCode;
	private JLabel lblCodeHistory;
	private JList<String> lstCodeHistory;
	private DefaultListModel<String> mdlCodeHistory;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnClear;
	private JScrollPane scrCodeHistory;
	private Border innerBorder;
	public StatsEntryPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 550;
		setPreferredSize(dim);
		setName(buildTitle());
		lblCode = new JLabel(buildTitle());
		lblCodeHistory = new JLabel(Messages.getString("StatsEntryPanel.History")); //$NON-NLS-1$
		txtCode = new JTextField();
		mdlCodeHistory = new DefaultListModel<String>();
		lstCodeHistory = new JList<String>(mdlCodeHistory);
		btnUndo = new JButton(Messages.getString("StatsEntryPanel.Undo")); //$NON-NLS-1$
		btnRedo = new JButton(Messages.getString("StatsEntryPanel.Redo")); //$NON-NLS-1$
		btnClear = new JButton(Messages.getString("StatsEntryPanel.Clear")); //$NON-NLS-1$
		scrCodeHistory = new JScrollPane();
		scrCodeHistory.setViewportView(lstCodeHistory);
		lstCodeHistory.setLayoutOrientation(JList.VERTICAL);
		lstCodeHistory.setCellRenderer(new AttributiveCellRenderer());
		lstCodeHistory.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		lstCodeHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					copySelectedItemsToClipboard(lstCodeHistory);
				}
			}
		});
		lstCodeHistory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					copySelectedItemsToClipboard(lstCodeHistory);
				}
			}
		});
		lstCodeHistory.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int[] selectedIndices = lstCodeHistory.getSelectedIndices();
					StringBuilder selectedItems = new StringBuilder();
					for (int index : selectedIndices) {
						selectedItems.append(lstCodeHistory.getModel().getElementAt(index)).append("\n"); //$NON-NLS-1$
					}
				}
			}
		});
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(Messages.getString("StatsEntryPanel.StatisticsEntryPanel")); //$NON-NLS-1$
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
    private static void copySelectedItemsToClipboard(JList<String> jList) {
        int[] selectedIndices = jList.getSelectedIndices();
        if (selectedIndices.length > 0) {
            StringBuilder selectedItems = new StringBuilder();
            for (int index : selectedIndices) {
                selectedItems.append(jList.getModel().getElementAt(index)).append("\n"); //$NON-NLS-1$
            }
            StringSelection selection = new StringSelection(selectedItems.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
    }
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = -1;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(lblCode, gc);
		gc.weightx = 1;
		gc.weighty = .1;
		gc.gridx = 1;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(lblCodeHistory, gc);
		//////// Statistics Window ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .9;
		gc.gridx = 0;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(txtCode, gc);
		gc.weightx = 1;
		gc.weighty = .9;
		gc.gridx = 1;
		gc.gridheight=4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 3);
		add(scrCodeHistory, gc);
		gc.weightx = 1;
		gc.weighty = .9;
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(btnUndo, gc);
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .9;
		gc.gridx = 0;
		gc.gridheight=2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(btnClear, gc);
		gc.weightx = 1;
		gc.weighty = .9;
		gc.gridx = 2;
		gc.gridheight=1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 3);
		add(btnRedo, gc);
	}
	private void setMnemonics() {
		if(Settings.getHotKeyParameter("Undo").isEmpty()) { //$NON-NLS-1$
			btnUndo.setMnemonic(-1);
		} else {
			btnUndo.setMnemonic(Settings.getHotKeyParameter("Undo").charAt(0)); //$NON-NLS-1$
		};
		if(Settings.getHotKeyParameter("Redo").isEmpty()) { //$NON-NLS-1$
			btnRedo.setMnemonic(-1);
		} else {
			btnRedo.setMnemonic(Settings.getHotKeyParameter("Redo").charAt(0)); //$NON-NLS-1$
		};
	}
	////// Listeners  //////
	public void addCodeListener(ActionListener listenForTxtCode) {
		txtCode.addActionListener(listenForTxtCode);
	}
	public void addStatsClearListener(ActionListener listenForBtnClear) {
		btnClear.addActionListener(listenForBtnClear);
	}
	public void addUndoListener(ActionListener listenForBtnUndo) {
		btnUndo.addActionListener(listenForBtnUndo);
	}
	public void addRedoListener(ActionListener listenForBtnRedo) {
		btnRedo.addActionListener(listenForBtnRedo);
	}
	////// Utility Methods //////
	public void updateMnemonics() {
		setMnemonics();
	}
	public void updateCode(String code) {
		txtCode.setText(code);
	}
	public void updateCodeHistory(String code) {
		mdlCodeHistory.insertElementAt(code, 0);
	}
	public void errorCodeHistory() {
		mdlCodeHistory.set(0, mdlCodeHistory.firstElement() + Messages.getString("StatsEntryPanel.Invalid")); //$NON-NLS-1$
	}
	public void removeCodeHistory() {
		if (mdlCodeHistory.getSize() > 0) {
			mdlCodeHistory.removeElement(mdlCodeHistory.firstElement());
		}
	}
	public void selectCode() {
		txtCode.selectAll();
	}
	public void clearAll() {
		updateCode(""); //$NON-NLS-1$
		mdlCodeHistory.clear();
	}
	public String getCode() {
		return txtCode.getText();
	}
    public class AttributiveCellRenderer extends DefaultListCellRenderer 
    {
	  public AttributiveCellRenderer() {
	    setOpaque(true);
	  }
	  public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	  {
		  String tmp = ""; //$NON-NLS-1$
		  tmp = (String) value;
		  setBackground(UIManager.getColor("List.background")); //$NON-NLS-1$
		  setForeground(UIManager.getColor("List.foreground")); //$NON-NLS-1$
		  if (tmp.indexOf("<") != -1) { //$NON-NLS-1$
			  setForeground(Color.RED);
		  }
          setText(tmp);
          return this;
	  }
    }
    public void setFocusOnCode() {
    	txtCode.requestFocusInWindow();
    	txtCode.requestFocus();
    }
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("StatsEntryPanel.Code"); //$NON-NLS-1$
	}
}
