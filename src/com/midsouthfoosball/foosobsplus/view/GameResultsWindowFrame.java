/**
Copyright © 2024-2024 Hugh Garner
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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.midsouthfoosball.foosobsplus.model.AppConfig;

@SuppressWarnings("serial")
public class GameResultsWindowFrame extends JFrame {
	private static final String programName = AppConfig.PROGRAM_NAME;
	private DefaultListModel<JPanel> listModel;
    private JList<JPanel> gameResultsList;
    private JButton clearButton;
    private JButton updateButton;
    private JScrollPane gameResultsScrollPane;
	public GameResultsWindowFrame() {
		super(programName + " " + Messages.getString("GameResultsWindowFrame.GameResultsWindowTitle")); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(gameResultsScrollPane, BorderLayout.CENTER);
        container.add(clearButton, BorderLayout.NORTH);
        container.add(updateButton, BorderLayout.SOUTH);
        container.setPreferredSize(new Dimension(450,300));
		setLocation(0,100);
		pack();
	}
    private void initComponents() {
        listModel = new DefaultListModel<>();
        gameResultsList = new JList<>(listModel);
        gameResultsList.setCellRenderer(new CheckListRenderer());
        gameResultsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gameResultsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = gameResultsList.locationToIndex(e.getPoint());
                if (index != -1) {
                    JPanel gameResultsPanel = listModel.getElementAt(index);
                    JCheckBox gameResultsCheckBox = (JCheckBox) gameResultsPanel.getComponent(1);
                    gameResultsCheckBox.setSelected(!gameResultsCheckBox.isSelected());
                }
            }
        });
        gameResultsScrollPane = new JScrollPane(gameResultsList);
        gameResultsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        updateButton = new JButton(Messages.getString("GameResultsWindowFrame.Update"));
        clearButton = new JButton(Messages.getString("GameResultsWindowFrame.Clear"));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGameResultsLines();
            }
        });
    }
    public void addLine(String line) {
        if (line != null && !line.trim().isEmpty()) {
            JPanel panel = new JPanel(new BorderLayout());
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(true);
            checkBox.addChangeListener(new ListenForCkbxUpdate());
            panel.add(new JLabel(line), BorderLayout.CENTER);
            panel.add(checkBox, BorderLayout.WEST);
            listModel.addElement(panel);
        }
    }
    private void clearGameResultsLines() {
    	for (int x=0; x < listModel.getSize(); x++) {
    		JPanel panel = (JPanel) listModel.getElementAt(x);
    		JCheckBox checkBox = (JCheckBox) panel.getComponent(1);
    		checkBox.setSelected(false);
    	}
//    	listModel.clear();
    	updateButton.doClick();
    }
    public StringBuilder buildGameResults() {
    	StringBuilder results = new StringBuilder();
    	for (int x=0; x < listModel.getSize(); x++) {
    		JPanel panel = (JPanel) listModel.getElementAt(x);
    		JCheckBox checkBox = (JCheckBox) panel.getComponent(1);
    		JLabel label = (JLabel) panel.getComponent(0);
    		if (checkBox.isSelected()) {
    			results.append(label.getText()).append(System.lineSeparator());
    		}
    	}
    	return results;
    }
    private class CheckListRenderer extends JPanel implements ListCellRenderer<JPanel> {

        public CheckListRenderer() {
            setLayout(new BorderLayout());
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            removeAll();
            add(value);
            return this;
        }
    }
	////// Listeners \\\\\\
	public void addGameResultsWindowClosedListener(WindowListener listenForGameResultsWindowClose) {
		this.addWindowListener(listenForGameResultsWindowClose);
	}
	public void addUpdateBtnListener(ActionListener listenForBtnUpdate) {
		buildGameResults();
		updateButton.addActionListener(listenForBtnUpdate);
	}
	private class ListenForCkbxUpdate implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			buildGameResults();
			updateButton.doClick();
			gameResultsScrollPane.repaint();
		}
	}
}
