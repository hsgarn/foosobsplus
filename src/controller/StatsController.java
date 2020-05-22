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

import javax.swing.JTextField;

import model.Stats;
import view.StatsEntryPanel;

public class StatsController {
	private Stats stats;
	private StatsEntryPanel statsEntryPanel;
	private TeamController teamController;
	
	public StatsController(Stats stats, StatsEntryPanel statsEntryPanel, TeamController teamController) {
		this.stats = stats;
		this.statsEntryPanel = statsEntryPanel;
		this.teamController = teamController;

		////// Stats Entry Panel Listeners Methods //////

		this.statsEntryPanel.addCodeListener(new CodeListener());
		this.statsEntryPanel.addClearListener(new ClearListener());
		
	}
	
	////// Stats Entry Panel Listener Objects //////

	private class CodeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField txt = (JTextField) e.getSource();
			String code = txt.getText().toUpperCase();
			stats.setCode(code);
			stats.addCodeToHistory(code);
			statsEntryPanel.updateCode("");
			statsEntryPanel.updateCodeHistory(code);
			parseCode(code);
			if (stats.getTeam1Scored()) teamController.incrementScore("Team 1");
			if (stats.getTeam2Scored()) teamController.incrementScore("Team 2");
		}
	}
	private class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stats.clearAll();
			statsEntryPanel.clearAll();
		}
	}

	////// Utility Methods //////
	
	private void parseCode(String code) {
		switch (code) {
			case "Y3Y":
				teamController.incrementScore("Team 1");
				break;
			case "Y5S":
				teamController.incrementScore("Team 1");
				break;
			case "Y2S":
				teamController.incrementScore("Team 1");
				break;
			case "YST":
				teamController.incrementScore("Team 1");
				break;
			case "YSL":
				teamController.incrementScore("Team 1");
				break;
			case "B3Y":
				teamController.incrementScore("Team 2");
				break;
			case "B5S":
				teamController.incrementScore("Team 2");
				break;
			case "B2S":
				teamController.incrementScore("Team 2");
				break;
			case "BST":
				teamController.incrementScore("Team 2");
				break;
			case "BSL":
				teamController.incrementScore("Team 2");
				break;
			case "Y5T":
				teamController.callTimeOut("Team 1");
				break;
			case "Y3T":
				teamController.callTimeOut("Team 1");
				break;
			case "Y2T":
				teamController.callTimeOut("Team 1");
				break;
			case "TOY":
				teamController.callTimeOut("Team 1");
				break;
			case "B5T":
				teamController.callTimeOut("Team 2");
				break;
			case "B3T":
				teamController.callTimeOut("Team 2");
				break;
			case "B2T":
				teamController.callTimeOut("Team 2");
				break;
			case "TOB":
				teamController.callTimeOut("Team 2");
				break;
			default:
				break;
		}
		
	}
}
