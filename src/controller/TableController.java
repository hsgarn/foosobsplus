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
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import model.Match;
import model.Table;
import view.SwitchPanel;
import view.TablePanel;

public class TableController {
	private Table table;
	private Match match;
	private TablePanel tablePanel;
	private SwitchPanel switchPanel;
	private String[] lastScoredStrings = {"     Last Scored     ", "<--- Last Scored     ", "     Last Scored --->"};

	public TableController(Table table, Match match, TablePanel tablePanel, SwitchPanel switchPanel) {
		this.table = table;
		this.match = match;
		this.tablePanel = tablePanel;
		this.switchPanel = switchPanel;
		
		////// Table Panel Listener Methods //////

		this.tablePanel.addTournamentNameListener(new TournamentNameListener());
		this.tablePanel.addTournamentNameFocusListener(new TournamentNameFocusListener());
		this.tablePanel.addTournamentNameMouseListener(new TournamentNameMouseListener());
		this.tablePanel.addEventNameListener(new EventNameListener());
		this.tablePanel.addEventNameFocusListener(new EventNameFocusListener());
		this.tablePanel.addEventNameMouseListener(new EventNameMouseListener());
		this.tablePanel.addTableNameListener(new TableNameListener());
		this.tablePanel.addTableNameFocusListener(new TableNameFocusListener());
		this.tablePanel.addTableNameMouseListener(new TableNameMouseListener());
		this.tablePanel.addClearListener(new ClearListener());
	}
	
	////// Table Panel Listener Objects //////

	private class TournamentNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tournamentName = txt.getText();
			table.setTournamentName(tournamentName);
			tablePanel.updateTournamentName(tournamentName);
		}
	}
	private class TournamentNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tournamentName = txt.getText();
			table.setTournamentName(tournamentName);
			tablePanel.updateTournamentName(tournamentName);
		}
	}
	private class TournamentNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tablePanel.selectTournamentName();
		}
	}
	private class EventNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String eventName = txt.getText();
			table.setEventName(eventName);
			tablePanel.updateEventName(eventName);
		}
	}
	private class EventNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String eventName = txt.getText();
			table.setEventName(eventName);
			tablePanel.updateEventName(eventName);
		}
	}
	private class EventNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tablePanel.selectEventName();
		}
	}
	private class TableNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tableName = txt.getText();
			table.setTableName(tableName);
			tablePanel.updateTableName(tableName);
		}
	}
	private class TableNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tableName = txt.getText();
			table.setTableName(tableName);
			tablePanel.updateTableName(tableName);
		}
	}
	private class TableNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tablePanel.selectTableName();
		}
	}
	private class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			table.clearAll();
			tablePanel.clearAllFields();
			match.setLastScored(0);
			switchPanel.setLastScored(lastScoredStrings[match.getLastScored()]);
		}
	}
	
	////// Utility Methods \\\\\\
	
	public void clearLastScored(int lastScored) {
		match.setLastScored(lastScored);
	}
	
}
