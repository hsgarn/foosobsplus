/**
Copyright 2020 Hugh Garner
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

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.midsouthfoosball.foosobsplus.model.GameTableModel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GameTableWindowPanel extends JPanel {
	private JTable gameTable;
	private int matchWinner = 0;
	private int currentGameNumber = 1;
	private int gameWinners[] = {0,0,0,0,0};

	/**
	 * Create the panel.
	 */
	public GameTableWindowPanel() {
		setLayout(new MigLayout("", "[430.00]", "[80.00]"));

		gameTable = new JTable(new GameTableModel());
		gameTable.setDefaultRenderer(Object.class, new GameTableCellRenderer());
		TableColumnModel gameTableColumnModel = gameTable.getColumnModel();
		
		gameTableColumnModel.getColumn(0).setPreferredWidth(130);
		gameTableColumnModel.getColumn(1).setPreferredWidth(60);
		gameTableColumnModel.getColumn(2).setPreferredWidth(60);
		gameTableColumnModel.getColumn(3).setPreferredWidth(60);
		gameTableColumnModel.getColumn(4).setPreferredWidth(60);
		gameTableColumnModel.getColumn(5).setPreferredWidth(60);
		add(gameTable);

	}
	public void setTeams(String name1, String name2) {
		gameTable.setValueAt(name1, 1, 0);
		gameTable.setValueAt(name2, 2, 0);
	}
	public void setGameWinners(int[] gameWinners) {
		this.gameWinners = gameWinners;
	}
	public void setMatchWinner(int matchWinner) {
		this.matchWinner = matchWinner;
	}
	public void setTime(String time) {
		gameTable.setValueAt(time, 3, currentGameNumber);
		gameTable.repaint();
	}
	public void updateGameTable(String[] scoresTeam1, String[] scoresTeam2, String[] times, int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
		for (int i = 1; i < 6; ++i) {
			gameTable.setValueAt(scoresTeam1[i-1], 1, i);
			gameTable.setValueAt(scoresTeam2[i-1], 2, i);
			gameTable.setValueAt(times[i-1], 3, i);
		}
		gameTable.repaint();
	}

	
	
	public class GameTableCellRenderer extends DefaultTableCellRenderer 
    {
	  public GameTableCellRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	  {
		  String tmp = "";
		  tmp = (String) value;
		  setHorizontalAlignment(SwingConstants.RIGHT);
		  setBackground(UIManager.getColor("Table.background"));
		  ////// Center game number columns and make current game number column CYAN \\\\\\
		  if (row == 0 && column >=1 ) {
			  setHorizontalAlignment(SwingConstants.CENTER);
			  if(column == currentGameNumber) {
				  setBackground(Color.CYAN);
			  }
		  }
		  ////// Set team1 name cell to Orange if they won the match \\\\\\
		  if (row == 1 && column == 0) {
			  if (matchWinner == 1) {
				  setBackground(Color.ORANGE);
			  }
		  }
		  ////// Set team2 name cell to Orange if they won the match \\\\\\
		  if (row == 2 && column == 0) {
			  if (matchWinner == 2) {
				  setBackground(Color.ORANGE);
			  }
		  }
		  ////// Set score cells to center alignment and winning scores to Green \\\\\\
          if ((row==1 || row==2) && column>= 1 ) {
        	  if ( gameWinners[column-1] == row) {
        		  setBackground(Color.GREEN);
        	  }
        	  setHorizontalAlignment(SwingConstants.CENTER);
          }
          if (row==3 && column>=1) setHorizontalAlignment(SwingConstants.CENTER);
          setText(tmp);
          return this;
	  }
    }

}
