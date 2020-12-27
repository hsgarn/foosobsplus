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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.midsouthfoosball.foosobsplus.model.GameTableModel;
import com.midsouthfoosball.foosobsplus.model.Settings;

@SuppressWarnings("serial")
public class MatchPanel extends JPanel {
	
	private JButton btnStartMatch;
	private JButton btnPauseMatch;
	private JButton btnStartGame;
	private JLabel lblStartTimeLabel;
	private JLabel lblElapsedTimeLabel;
	private JLabel lblStartTime;
	private JLabel lblElapsedTime;
	private JLabel lblGameTimeLabel;
	private JLabel lblGameTime;
	private JTable gameTable;
	private Settings settings;
	private int matchWinner = 0;
	private int currentGameNumber = 1;
	private int gameWinners[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int maxGameCount;
	
	public MatchPanel(Settings settings) {

		this.settings = settings;
		this.maxGameCount = settings.getGamesToWin()*2-1; 
		
		Dimension dim = getPreferredSize();
		dim.width = 550;
		dim.height = 100;
		setPreferredSize(dim);
		
		btnStartMatch = new JButton("Start Match");
		btnPauseMatch = new JButton("Pause Match");
		btnStartGame = new JButton("Start Game");
		lblStartTimeLabel = new JLabel("Start Time:");
		lblElapsedTimeLabel = new JLabel("Elapsed Time:");
		lblGameTimeLabel = new JLabel("Game Time:");
		lblStartTime = new JLabel("00:00:00");
		lblElapsedTime = new JLabel("00:00:00");
		lblGameTime = new JLabel("00:00:00");
		gameTable = new JTable(new GameTableModel(maxGameCount));
		gameTable.setDefaultRenderer(Object.class, new GameTableCellRenderer());
		
		setMnemonics();
		
		Border innerBorder = BorderFactory.createTitledBorder("Match Information");
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	public void resizeGameTable() {
		GameTableModel tableModel = new GameTableModel(settings.getGamesToWin()*2-1);
		gameTable.setModel(tableModel);
		maxGameCount = settings.getGamesToWin()*2-1;
		return;
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;

		////////  Start ////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(btnStartMatch, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblStartTimeLabel, gc);

		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblStartTime, gc);

		////////  Elapsed Time ////////
		gc.gridy++;
		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(btnPauseMatch, gc);
		gc.gridheight = 1;
		
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(5, 5, 5, 5);
		add(lblElapsedTimeLabel, gc);
		
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblElapsedTime, gc);
		
		////////  Start Game ////////
		gc.gridy++;
		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(btnStartGame, gc);
		gc.gridheight = 1;
		
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(5, 5, 5, 5);
		add(lblGameTimeLabel, gc);
		
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblGameTime, gc);
		
		////// Game Table \\\\\\
		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 0.1;
		gc.gridwidth = 6;
		gc.gridheight = 4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(5, 5, 5, 5);
		add(gameTable,gc);
	}		

	////// Listeners  //////
	public void addStartMatchListener(ActionListener listenForBtnStartMatch) {
		btnStartMatch.addActionListener(listenForBtnStartMatch);
	}
	public void addPauseMatchListener(ActionListener listenForBtnPauseMatch) {
		btnPauseMatch.addActionListener(listenForBtnPauseMatch);
	}
	public void addStartGameListener(ActionListener listenForBtnStartGame) {
		btnStartGame.addActionListener(listenForBtnStartGame);
	}
	
	public void updateElapsedTime(String elapsedTime) {
		lblElapsedTime.setText(elapsedTime);
	}
	public void updateStartTime(String startTime) {
		lblStartTime.setText(startTime);
	}
	public void updateGameTime(String gameTime) {
		lblGameTime.setText(gameTime);
	}
	public void setTime(String time) {
		int gameNumber = currentGameNumber;
		int maxGameNumber = settings.getGamesToWin()*2-1;
		if (gameNumber > maxGameNumber) gameNumber = maxGameNumber;
		gameTable.setValueAt(time, 3, gameNumber);
		gameTable.repaint();
	}
	
	////// Utility Methods \\\\\\
	public void setPauseLabel(String labelText) {
		btnPauseMatch.setText(labelText);
	}
	public void setStartMatchLabel(String labelText) {
		btnStartMatch.setText(labelText);
	}
	private void setMnemonics() {
		if(settings.getStartMatchHotKey().isEmpty()) {
			btnStartMatch.setMnemonic(-1);
		} else {
			btnStartMatch.setMnemonic(settings.getStartMatchHotKey().charAt(0));
		};
		if(settings.getPauseMatchHotKey().isEmpty()) {
			btnPauseMatch.setMnemonic(-1);
		} else {
			btnPauseMatch.setMnemonic(settings.getPauseMatchHotKey().charAt(0));
		};
		if(settings.getStartGameHotKey().isEmpty()) {
			btnStartGame.setMnemonic(-1);
		} else {
			btnStartGame.setMnemonic(settings.getStartGameHotKey().charAt(0));
		};
	}
	public void updateMnemonics() {
		setMnemonics();
	}

	public void setGameWinners(int[] gameWinners) {
		this.gameWinners = gameWinners;
	}
	public void setMatchWinner(int matchWinner) {
		this.matchWinner = matchWinner;
	}
	public void updateGameTable(String[] scoresTeam1, String[] scoresTeam2, String[] times, int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
		for (int i = 1; i <= maxGameCount; ++i) {
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
		  ////// Set score cells to center alignment and highlight winning scores \\\\\\
		  if ((row==1 || row==2) && column>= 1 ) {
        	  if ( gameWinners[column-1] == row) {
        		  setBackground(Color.CYAN);
        	  }
        	  setHorizontalAlignment(SwingConstants.CENTER);
          }
          ////// Center time cells \\\\\\
          if (row==3 && column>=1) setHorizontalAlignment(SwingConstants.CENTER);
          setText(tmp);
          return this;
	  }
    }
}
