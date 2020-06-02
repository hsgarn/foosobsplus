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

import com.midsouthfoosball.foosobsplus.view.SwitchPanel;

public class SwitchController {
	
	private SwitchPanel switchPanel;
	private TeamController teamController;
	private StatsController statsController;
	
	public SwitchController(SwitchPanel switchPanel, TeamController teamController, StatsController statsController) {
		this.switchPanel = switchPanel;
		this.teamController = teamController;
		this.statsController = statsController;
		
		////// Switch Panel Listener Methods //////
		
		this.switchPanel.addSwitchSidesListener(new SwitchSidesListener());
		this.switchPanel.addSwitchTeamsListener(new SwitchTeamsListener());
		this.switchPanel.addSwitchScoresListener(new SwitchScoresListener());
		this.switchPanel.addSwitchGameCountsListener(new SwitchGameCountsListener());
		this.switchPanel.addSwitchTimeOutsListener(new SwitchTimeOutsListener());
		this.switchPanel.addSwitchResetWarnsListener(new SwitchResetWarnsListener());
		this.switchPanel.addClearAllListener(new ClearAllListener());
		
	}
	
	////// Switch Panel Lisetner Objects //////
	
	private class SwitchSidesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchSides();
			statsController.displayAllStats();
		}
	}
	private class SwitchTeamsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchTeams();
		}
	}
	private class SwitchScoresListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchScores();
		}
	}
	private class SwitchGameCountsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchGameCounts();
		}
	}
	private class SwitchTimeOutsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchTimeOuts();
		}
	}
	private class SwitchResetWarnsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.switchResetWarns();
		}
	}
	private class ClearAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			teamController.clearAll();
		}
	}
}
