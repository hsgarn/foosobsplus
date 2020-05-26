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
import java.io.IOException;

import javax.swing.JTextField;
import commands.*;
import model.Stats;
import view.StatsDisplayPanel;
import view.StatsEntryPanel;

public class StatsController {
	private Stats stats;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private TeamController teamController;
	private CommandSwitch mySwitch;
	
	public StatsController(Stats stats, StatsEntryPanel statsEntryPanel, StatsDisplayPanel statsDisplayPanel, TeamController teamController) {
		this.stats = stats;
		this.statsEntryPanel = statsEntryPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.teamController = teamController;

		////// Stats Entry Panel Listeners Methods //////

		this.statsEntryPanel.addCodeListener(new CodeListener());
		this.statsEntryPanel.addClearListener(new ClearListener());
		
		loadCommands();
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
			if (stats.getIsCommand()) processCommand(stats.getCommand());
			if (stats.getTeam1Scored()) teamController.incrementScore("Team 1");
			if (stats.getTeam2Scored()) teamController.incrementScore("Team 2");
			if (stats.getTeam1TimeOut()) teamController.callTimeOut("Team 1");
			if (stats.getTeam2TimeOut()) teamController.callTimeOut("Team 2");
			displayAllStats();
		}
	}
	private class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stats.clearAll();
			statsEntryPanel.clearAll();
		}
	}

	////// Utility Methods \\\\\\
	
	private void processCommand(String command) {
		mySwitch.execute(command);
		return;
	}
	private void loadCommands() {
		Command sst = new SSTCommand(teamController);
		Command spt = new SPTCommand(teamController);
		Command sgt = new SGTCommand(teamController);
		Command stt = new STTCommand(teamController);
		Command srt = new SRTCommand(teamController);
		Command prt = new PRTCommand(teamController);
		Command ist1 = new IST1Command(teamController);
		Command ist2 = new IST2Command(teamController);
		Command dst1 = new DST1Command(teamController);
		Command dst2 = new DST2Command(teamController);
		Command igt1 = new IGT1Command(teamController);
		Command igt2 = new IGT2Command(teamController);
		Command dgt1 = new DGT1Command(teamController);
		Command dgt2 = new DGT2Command(teamController);
		Command utt1 = new UTT1Command(teamController);
		Command utt2 = new UTT2Command(teamController);
		Command rtt1 = new RTT1Command(teamController);
		Command rtt2 = new RTT2Command(teamController);
		Command prt1 = new PRT1Command(teamController);
		Command prt2 = new PRT2Command(teamController);
		Command pwt1 = new PWT1Command(teamController);
		Command pwt2 = new PWT2Command(teamController);
		Command pss = new PSSCommand(teamController);
		Command xpt1 = new XPT1Command(teamController);
		Command xpt2 = new XPT2Command(teamController);
		mySwitch = new CommandSwitch();
		mySwitch.register("SST", sst);
		mySwitch.register("SPT", spt);
		mySwitch.register("SGT", sgt);
		mySwitch.register("STT", stt);
		mySwitch.register("SRT", srt);
		mySwitch.register("PRT", prt);
		mySwitch.register("IST1", ist1);
		mySwitch.register("IST2", ist2);
		mySwitch.register("DST1", dst1);
		mySwitch.register("DST2", dst2);
		mySwitch.register("IGT1", igt1);
		mySwitch.register("IGT2", igt2);
		mySwitch.register("DGT1", dgt1);
		mySwitch.register("DGT2", dgt2);
		mySwitch.register("UTT1", utt1);
		mySwitch.register("UTT2", utt2);
		mySwitch.register("RTT1", rtt1);
		mySwitch.register("RTT2", rtt2);
		mySwitch.register("PRT1", prt1);
		mySwitch.register("PRT2", prt2);
		mySwitch.register("PWT1", pwt1);
		mySwitch.register("PWT2", pwt2);
		mySwitch.register("PSS", pss);
		mySwitch.register("XPT1", xpt1);
		mySwitch.register("XPT2", xpt2);
	}
	public void displayAllStats() {
		statsDisplayPanel.updatePassStats(1, teamController.getPassCompletes(1),teamController.getPassAttempts(1),teamController.getPassPercent(1));
		statsDisplayPanel.updatePassStats(2, teamController.getPassCompletes(2),teamController.getPassAttempts(2),teamController.getPassPercent(2));
		statsDisplayPanel.updateShotStats(1, teamController.getShotCompletes(1),teamController.getShotAttempts(1),teamController.getShotPercent(1));
		statsDisplayPanel.updateShotStats(2, teamController.getShotCompletes(2),teamController.getShotAttempts(2),teamController.getShotPercent(2));
		statsDisplayPanel.updateClearStats(1, teamController.getClearCompletes(1),teamController.getClearAttempts(1),teamController.getClearPercent(1));
		statsDisplayPanel.updateClearStats(2, teamController.getClearCompletes(2),teamController.getClearAttempts(2),teamController.getClearPercent(2));
	}
}