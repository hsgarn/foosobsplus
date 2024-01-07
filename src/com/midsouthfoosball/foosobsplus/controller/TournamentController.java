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
import com.midsouthfoosball.foosobsplus.model.Tournament;
import com.midsouthfoosball.foosobsplus.view.TournamentPanel;

public class TournamentController {
	private OBSInterface obsInterface;
	private Tournament tournament;
	private TournamentPanel tournamentPanel;
	public TournamentController(OBSInterface obsInterface, Tournament tournament, Match match, TournamentPanel tournamentPanel) {
		this.obsInterface = obsInterface;
		this.tournament = tournament;
		this.tournamentPanel = tournamentPanel;
		
		////// Tournament Panel Listener Methods //////

		this.tournamentPanel.addTournamentNameListener(new TournamentNameListener());
		this.tournamentPanel.addTournamentNameFocusListener(new TournamentNameFocusListener());
		this.tournamentPanel.addTournamentNameMouseListener(new TournamentNameMouseListener());
		this.tournamentPanel.addEventNameListener(new EventNameListener());
		this.tournamentPanel.addEventNameFocusListener(new EventNameFocusListener());
		this.tournamentPanel.addEventNameMouseListener(new EventNameMouseListener());
		this.tournamentPanel.addTableNameListener(new TableNameListener());
		this.tournamentPanel.addTableNameFocusListener(new TableNameFocusListener());
		this.tournamentPanel.addTableNameMouseListener(new TableNameMouseListener());
		this.tournamentPanel.addClearListener(new ClearListener());
	}
	
	////// Tournament Panel Listener Objects //////

	private class TournamentNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tournamentName = txt.getText();
			tournament.setTournamentName(tournamentName);
			tournamentPanel.updateTournamentName(tournamentName);
		}
	}
	private class TournamentNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tournamentName = txt.getText();
			tournament.setTournamentName(tournamentName);
			tournamentPanel.updateTournamentName(tournamentName);
		}
	}
	private class TournamentNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tournamentPanel.selectTournamentName();
		}
	}
	private class EventNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String eventName = txt.getText();
			tournament.setEventName(eventName);
			tournamentPanel.updateEventName(eventName);
		}
	}
	private class EventNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String eventName = txt.getText();
			tournament.setEventName(eventName);
			tournamentPanel.updateEventName(eventName);
		}
	}
	private class EventNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tournamentPanel.selectEventName();
		}
	}
	private class TableNameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tableName = txt.getText();
			tournament.setTableName(tableName);
			tournamentPanel.updateTableName(tableName);
		}
	}
	private class TableNameFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String tableName = txt.getText();
			tournament.setTableName(tableName);
			tournamentPanel.updateTableName(tableName);
		}
	}
	private class TableNameMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			tournamentPanel.selectTableName();
		}
	}
	private class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			clearAll();
//			tournament.clearAll();
//			tournamentPanel.clearAllFields();
		}
	}
	public void fetchAll() {
		tournament.setTournamentName(obsInterface.getContents(Settings.getTournamentSource()));
		tournamentPanel.updateTournamentName(tournament.getTournamentName());
		tournament.setEventName(obsInterface.getContents(Settings.getEventSource()));
		tournamentPanel.updateEventName(tournament.getEventName());
		tournament.setTableName(obsInterface.getContents(Settings.getTableNameSource()));
		tournamentPanel.updateTableName(tournament.getTableName());
	}
	public void writeAll() {
		tournament.writeAll();
	}
	public void clearAll() {
		tournament.clearAll();
		tournamentPanel.clearAllFields();
	}
}
