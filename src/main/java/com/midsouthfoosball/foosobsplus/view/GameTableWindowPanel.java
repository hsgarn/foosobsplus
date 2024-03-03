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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.midsouthfoosball.foosobsplus.model.GameTableModel;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class GameTableWindowPanel extends JPanel {
	private final JTable gameTable;
	private int matchWinner = 0;
	private int currentGameNumber = 1;
	private int gameWinners[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int maxGameCount = 5;
	private static final String ON = "1"; //$NON-NLS-1$

	/**
	 * Create the panel.
	 */
	public GameTableWindowPanel() {
		this.maxGameCount = Settings.getMaxGameNumber();
		gameWinners = new int[maxGameCount];
		gameTable = new JTable(new GameTableModel(maxGameCount, Settings.getControlParameter("CutThroatMode"))); //$NON-NLS-1$
		gameTable.setDefaultRenderer(Object.class, new GameTableCellRenderer());
		updateGameTableColumnWidths();
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
			
		add(gameTable);
	}
	public void setTeams(String name1, String name2, String name3) {
		gameTable.setValueAt(name1, 1, 0);
		gameTable.setValueAt(name2, 2, 0);
		if (Settings.getControlParameter("CutThroatMode").equals(ON)) { //$NON-NLS-1$
			gameTable.setValueAt(name3, 3, 0);
		}
	}
	public void setGameWinners(int[] gameWinners) {
		this.gameWinners = gameWinners;
	}
	public void setMatchWinner(int matchWinner) {
		this.matchWinner = matchWinner;
	}
	public void setTime(String time) {
		int gameNumber = currentGameNumber;
		int maxGameNumber = Settings.getMaxGameNumber();
		if (gameNumber > maxGameNumber) gameNumber = maxGameNumber;
		int row = Integer.parseInt(Settings.getControlParameter("CutThroatMode")) + 3; //$NON-NLS-1$
		gameTable.setValueAt(time, row, gameNumber);
		gameTable.repaint();
	}
	public void updateGameTable(String[] scoresTeam1, String[] scoresTeam2, String[] scoresTeam3, String[] times, int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
		if (Settings.getControlParameter("CutThroatMode").equals(ON)) { //$NON-NLS-1$
			for (int i = 1; i <= maxGameCount; ++i) {
				gameTable.setValueAt(scoresTeam1[i-1], 1, i);
				gameTable.setValueAt(scoresTeam2[i-1], 2, i);
				gameTable.setValueAt(scoresTeam3[i-1], 3, i);
				gameTable.setValueAt(times[i-1], 4, i);
			}
		} else {
			for (int i = 1; i <= maxGameCount; ++i) {
				gameTable.setValueAt(scoresTeam1[i-1], 1, i);
				gameTable.setValueAt(scoresTeam2[i-1], 2, i);
				gameTable.setValueAt(times[i-1], 3, i);
			}
		}
		gameTable.repaint();
	}
	public void resizeGameTable() {
		this.maxGameCount = Settings.getMaxGameNumber();
		GameTableModel tableModel = new GameTableModel(this.maxGameCount ,Settings.getControlParameter("CutThroatMode")); //$NON-NLS-1$
		gameTable.setModel(tableModel);
		updateGameTableColumnWidths();
	}
	private void updateGameTableColumnWidths() {
        TableColumnModel gameTableColumnModel = gameTable.getColumnModel();
        gameTableColumnModel.getColumn(0).setPreferredWidth(130);
        for (int i = 1; i <= maxGameCount; i++) {
            gameTableColumnModel.getColumn(i).setPreferredWidth((int) (300 / maxGameCount));
        }
	}
	public class GameTableCellRenderer extends DefaultTableCellRenderer 
    {
	  public GameTableCellRenderer() {
	    setOpaque(true);
	  }
          @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	  {
                  Boolean isCutthroatMode = Settings.getControlParameter("CutThroatMode").equals(ON); //$NON-NLS-1$
		  String tmp;
                  tmp = (String) value;
		  setHorizontalAlignment(SwingConstants.RIGHT);
		  setBackground(UIManager.getColor("Tournament.background")); //$NON-NLS-1$
		  ////// Center game number columns and highlight current game number column \\\\\\
		  if (row == 0 && column >=1 ) {
			  setHorizontalAlignment(SwingConstants.CENTER);
			  if(column == currentGameNumber) {
				  setBackground(Color.LIGHT_GRAY);
			  }
		  }
		  ////// Highlight team1 name cell if they won the match \\\\\\
		  if (row == 1 && column == 0) {
			  if (matchWinner == 1) {
				  setBackground(Color.GREEN);
			  }
		  }
		  ////// Highlight team2 name cell if they won the match \\\\\\
		  if (row == 2 && column == 0) {
			  if (matchWinner == 2) {
				  setBackground(Color.GREEN);
			  }
		  }
		  ////// Highlight team3 name cell if they won the match \\\\\\
		  if (row == 3 && column == 0 && isCutthroatMode) {
			  if (matchWinner == 3) {
				  setBackground(Color.GREEN);
			  }
		  }
		  ////// Set score cells to center alignment and highlight winning scores \\\\\\
          if ((row==1 || row==2 || (row==3 && isCutthroatMode)) && column>= 1 ) {
        	  if ( gameWinners[column-1] == row) {
        		  setBackground(Color.CYAN);
        	  }
        	  setHorizontalAlignment(SwingConstants.CENTER);
          }
          ////// Center time cells \\\\\\
          if (((row==3 && !isCutthroatMode) || (row==4 && isCutthroatMode)) && column>=1) setHorizontalAlignment(SwingConstants.CENTER);
          setText(tmp);
          return this;
	  }
    }
}
