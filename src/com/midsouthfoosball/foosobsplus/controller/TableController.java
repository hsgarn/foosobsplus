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
package com.midsouthfoosball.foosobsplus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.Table;
import com.midsouthfoosball.foosobsplus.view.TablePanel;

public class TableController {
	private Settings settings;
	private OBSInterface obsInterface;
	private Table table;
	private TablePanel tablePanel;
	private TeamController teamController;
	public TableController(OBSInterface obsInterface, Settings settings, Table table, Match match, TablePanel tablePanel, TeamController teamController) {
		this.settings = settings;
		this.obsInterface = obsInterface;
		this.table = table;
		this.tablePanel = tablePanel;
		this.teamController = teamController;
		
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
		this.tablePanel.addLoadListener(new LoadListener());
		this.tablePanel.addSetListener(new SetListener());
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
			obsInterface.setTableName(tableName);
			table.setTableName(tableName);
			tablePanel.updateTableName(tableName);
		}
	}
	private class TableNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tableName = txt.getText();
			obsInterface.setTableName(tableName);
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
		}
	}
	private class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String tableName = table.getTableName();
			obsInterface.setTableName(tableName);
			fetchAll(table.getTableName());
			teamController.fetchAll();
		}
	}
	private class SetListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			obsInterface.setTableName(table.getTableName());
			table.writeAll();
			teamController.writeAll();
		}
	}
	public void fetchAll() {
		table.setTournamentName(obsInterface.getContents(settings.getTournamentSource()));
		tablePanel.updateTournamentName(table.getTournamentName());
		table.setEventName(obsInterface.getContents(settings.getEventSource()));
		tablePanel.updateEventName(table.getEventName());
	}
	public void fetchAll(String tableName) {
		table.setTableName(tableName);
		tablePanel.updateTableName(table.getTableName());
		fetchAll();
	}
	public void writeAll() {
		table.writeAll();
	}
}
