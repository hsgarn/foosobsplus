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
	
	private JButton btnStartEvent;
	private JButton btnStartMatch;
	private JButton btnPauseMatch;
	private JButton btnEndMatch;
	private JButton btnStartGame;
	private JLabel lblStartTimeLabel;
	private JLabel lblElapsedTimeLabel;
	private JLabel lblStartTime;
	private JLabel lblElapsedTime;
	private JLabel lblGameTimeLabel;
	private JLabel lblGameTime;
	private JTable gameTable;
	private int matchWinner = 0;
	private int currentGameNumber = 1;
	private int gameWinners[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private int maxGameCount;
	private Border innerBorder;
	
	public MatchPanel() {
		this.maxGameCount = Settings.getMaxGameNumber(); 
		Dimension dim = getPreferredSize();
		dim.width = 340;
		dim.height = 325;
		setPreferredSize(dim);
		setName(buildTitle());
		btnStartEvent = new JButton(Messages.getString("MatchPanel.StartEvent", Settings.getGameType())); //$NON-NLS-1$
		btnStartMatch = new JButton(Messages.getString("MatchPanel.StartMatch", Settings.getGameType())); //$NON-NLS-1$
		btnPauseMatch = new JButton(Messages.getString("MatchPanel.PauseMatch", Settings.getGameType())); //$NON-NLS-1$
		btnEndMatch = new JButton(Messages.getString("MatchPanel.EndMatch", Settings.getGameType())); //$NON-NLS-1$
		btnStartGame = new JButton(Messages.getString("MatchPanel.StartGame", Settings.getGameType())); //$NON-NLS-1$
		lblStartTimeLabel = new JLabel(Messages.getString("MatchPanel.StartTime", Settings.getGameType())); //$NON-NLS-1$
		lblElapsedTimeLabel = new JLabel(Messages.getString("MatchPanel.ElapsedTime", Settings.getGameType())); //$NON-NLS-1$
		lblGameTimeLabel = new JLabel(Messages.getString("MatchPanel.GameTime", Settings.getGameType())); //$NON-NLS-1$
		lblStartTime = new JLabel("00:00:00"); //$NON-NLS-1$
		lblElapsedTime = new JLabel("00:00:00"); //$NON-NLS-1$
		lblGameTime = new JLabel("00:00:00"); //$NON-NLS-1$
		gameTable = new JTable(new GameTableModel(maxGameCount,Settings.getCutThroatMode()));
		gameTable.setDefaultRenderer(Object.class, new GameTableCellRenderer());
		setMnemonics();
		innerBorder = BorderFactory.createTitledBorder(buildTitle());
		((TitledBorder) innerBorder).setTitleJustification(TitledBorder.CENTER);
		Border outerBorder = BorderFactory.createEmptyBorder(Settings.getBorderTop(),Settings.getBorderLeft(),Settings.getBorderBottom(),Settings.getBorderRight());
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}
	public void changeGameType() {
		btnStartMatch.setText(Messages.getString("MatchPanel.StartMatch", Settings.getGameType())); //$NON-NLS-1$
		btnPauseMatch.setText(Messages.getString("MatchPanel.PauseMatch", Settings.getGameType())); //$NON-NLS-1$
		btnEndMatch.setText(Messages.getString("MatchPanel.EndMatch", Settings.getGameType())); //$NON-NLS-1$
		btnStartGame.setText(Messages.getString("MatchPanel.StartGame", Settings.getGameType())); //$NON-NLS-1$
		lblStartTimeLabel.setText(Messages.getString("MatchPanel.StartTime", Settings.getGameType())); //$NON-NLS-1$
		lblElapsedTimeLabel.setText(Messages.getString("MatchPanel.ElapsedTime", Settings.getGameType())); //$NON-NLS-1$
		lblGameTimeLabel.setText(Messages.getString("MatchPanel.GameTime", Settings.getGameType())); //$NON-NLS-1$
		setTitle();
	}
	public void resizeGameTable() {
		maxGameCount = Settings.getMaxGameNumber();
		GameTableModel tableModel = new GameTableModel(maxGameCount,Settings.getCutThroatMode());
		gameTable.setModel(tableModel);
		return;
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		
		//////// Start Event ///////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .5;

		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(1, 5, 1, 5);
		add(btnStartEvent, gc);

		////////  Start Match ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .5;

		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(1, 5, 1, 5);
		add(btnStartMatch, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(1, 5, 1, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblStartTimeLabel, gc);

		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(1, 5, 1, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblStartTime, gc);

		////////  Pause Match ////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = .5;

		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(1, 5, 1, 5);
		add(btnPauseMatch, gc);
		gc.gridheight = 1;

		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(1, 5, 1, 5);
		add(lblElapsedTimeLabel, gc);
		
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(1, 5, 1, 5);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblElapsedTime, gc);

		//////// End Match ///////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = .5;

		gc.gridx = 0;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(1, 5, 1, 5);
		add(btnEndMatch, gc);
		gc.gridheight = 1;
		
		////////  Start Game ////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .5;

		gc.gridx = 0;
		gc.gridheight = 1;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(1, 5, 1, 5);
		add(btnStartGame, gc);
		gc.gridheight = 1;
		
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(1, 5, 1, 5);
		add(lblGameTimeLabel, gc);
		
		gc.gridx = 2;
		gc.gridwidth = 1;
		gc.insets = new Insets(1, 5, 1, 5);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblGameTime, gc);
		
		////// Game Table \\\\\\
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridwidth = 6;
		gc.gridheight = 4;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(gameTable,gc);
	}		

	////// Listeners  //////
	public void addStartEventListener(ActionListener listenForBtnStartEvent) {
		btnStartEvent.addActionListener(listenForBtnStartEvent);
	}
	public void addStartMatchListener(ActionListener listenForBtnStartMatch) {
		btnStartMatch.addActionListener(listenForBtnStartMatch);
	}
	public void addPauseMatchListener(ActionListener listenForBtnPauseMatch) {
		btnPauseMatch.addActionListener(listenForBtnPauseMatch);
	}
	public void addEndMatchListener(ActionListener listenForBtnEndMatch) {
		btnEndMatch.addActionListener(listenForBtnEndMatch);
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
		int maxGameNumber = Settings.getMaxGameNumber();
		int row = Settings.getCutThroatMode() + 3;
		if (gameNumber > maxGameNumber) gameNumber = maxGameNumber;
		gameTable.setValueAt(time, row, gameNumber);
		gameTable.repaint();
	}
	
	////// Utility Methods \\\\\\
	public void setPauseLabel(String labelText) {
		btnPauseMatch.setText(labelText);
	}
	private void setMnemonics() {
		if(Settings.getStartMatchHotKey().isEmpty()) {
			btnStartMatch.setMnemonic(-1);
		} else {
			btnStartMatch.setMnemonic(Settings.getStartMatchHotKey().charAt(0));
		};
		if(Settings.getPauseMatchHotKey().isEmpty()) {
			btnPauseMatch.setMnemonic(-1);
		} else {
			btnPauseMatch.setMnemonic(Settings.getPauseMatchHotKey().charAt(0));
		};
		if(Settings.getEndMatchHotKey().isEmpty()) {
			btnEndMatch.setMnemonic(-1);
		} else {
			btnEndMatch.setMnemonic(Settings.getEndMatchHotKey().charAt(0));
		};
		if(Settings.getStartGameHotKey().isEmpty()) {
			btnStartGame.setMnemonic(-1);
		} else {
			btnStartGame.setMnemonic(Settings.getStartGameHotKey().charAt(0));
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
	public void updateGameTable(String[] scoresTeam1, String[] scoresTeam2, String[] scoresTeam3, String[] times, int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
		if (Settings.getCutThroatMode()==1) {
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
	public void clearKingSeat() {
		gameTable.setValueAt("Team 1",1,0);
		gameTable.setValueAt("Team 2",2,0);
		if (Settings.getCutThroatMode()==1) {
			gameTable.setValueAt("Team 3",3,0);
		}
		gameTable.repaint();
	}
	public void setKingSeat(int teamNumber) {
		if (teamNumber > 0 && (teamNumber < 3 || (teamNumber == 3 && Settings.getCutThroatMode()==1))) {
			String kingSeat = Messages.getString("Global.KingSeat"); //$NON-NLS-1$
			gameTable.setValueAt(kingSeat + "Team " + Integer.toString(teamNumber), teamNumber, 0);
			gameTable.repaint();
		}
	}
	public class GameTableCellRenderer extends DefaultTableCellRenderer 
    {
	  public GameTableCellRenderer() {
	    setOpaque(true);
	  }
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	  {
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
		  if (row == 3 && column == 0 && Settings.getCutThroatMode()==1) {
			  if (matchWinner == 3) {
				  setBackground(Color.GREEN);
			  }
		  }
		  ////// Set score cells to center alignment and highlight winning scores \\\\\\
          if ((row==1 || row==2 || (row==3 && Settings.getCutThroatMode()==1)) && column>= 1 ) {
        	  if ( gameWinners[column-1] == row) {
        		  setBackground(Color.CYAN);
        	  }
        	  setHorizontalAlignment(SwingConstants.CENTER);
          }
          ////// Center time cells \\\\\\
          if (((row==3 && Settings.getCutThroatMode()==0) || (row==4 && Settings.getCutThroatMode()==1)) && column>=1) setHorizontalAlignment(SwingConstants.CENTER);
          setText(tmp);
          return this;
	  }
    }
	public void setTitle() {
		String title=buildTitle();
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
	}
	private String buildTitle() {
		return Messages.getString("MatchPanel.MatchInformation", Settings.getGameType()); //$NON-NLS-1$
	}
}
