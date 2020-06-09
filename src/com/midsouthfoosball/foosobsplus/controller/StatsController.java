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
import java.util.Stack;

import javax.swing.JTextField;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.commands.CommandSwitch;
import com.midsouthfoosball.foosobsplus.commands.DGT1Command;
import com.midsouthfoosball.foosobsplus.commands.IGT1Command;
import com.midsouthfoosball.foosobsplus.model.Match;
import com.midsouthfoosball.foosobsplus.model.Stats;
import com.midsouthfoosball.foosobsplus.model.Team;
import com.midsouthfoosball.foosobsplus.view.StatsDisplayPanel;
import com.midsouthfoosball.foosobsplus.view.StatsEntryPanel;

public class StatsController {

	////// undo/redo setup \\\\\\
	
	private int undoRedoPointer = -1;
	private Stack<Command> commandStack = new Stack<>();
	
	private Stats stats;
	private Match match;
	private Team team1;
	private Team team2;
	private StatsEntryPanel statsEntryPanel;
	private StatsDisplayPanel statsDisplayPanel;
	private TeamController teamController;
	private MatchController matchController;
	private CommandSwitch mySwitch;
	
	public StatsController(Stats stats, Match match, Team team1, Team team2, StatsEntryPanel statsEntryPanel, StatsDisplayPanel statsDisplayPanel, TeamController teamController, MatchController matchController) {
		this.stats = stats;
		this.match = match;
		this.team1 = team1;
		this.team2 = team2;
		this.statsEntryPanel = statsEntryPanel;
		this.statsDisplayPanel = statsDisplayPanel;
		this.teamController = teamController;
		this.matchController = matchController;

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
			if (stats.getIsCommand()) {
				processCommand(stats.getCommand());
			} else {
				if (stats.getTeam1Scored()) teamController.incrementScore("Team 1");
				if (stats.getTeam2Scored()) teamController.incrementScore("Team 2");
				if (stats.getTeam1TimeOut()) {
					teamController.callTimeOut("Team 1");
				} else if (stats.getTeam2TimeOut()) {
					teamController.callTimeOut("Team 2");
				} else {
					if (stats.isThreeRod()||stats.isTwoRod()) teamController.startShotTimer();
					if (stats.isFiveRod()) teamController.startPassTimer();
				}
			}
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

	private void loadCommands() {
/*		Command psm = new PSMCommand(matchController);
		Command sst = new SSTCommand(teamController, match);
		Command spt = new SPTCommand(teamController, match);
		Command sgt = new SGTCommand(teamController, match);
		Command stt = new STTCommand(teamController, match);
		Command srt = new SRTCommand(teamController, match);
		Command prt = new PRTCommand(teamController, match);
		Command ist1 = new IST1Command(teamController, match);
		Command ist2 = new IST2Command(teamController, match);
		Command dst1 = new DST1Command(teamController, match);
		Command dst2 = new DST2Command(teamController, match);
		Command igt1 = new IGT1Command(teamController, match);
		Command igt2 = new IGT2Command(teamController, match);
		Command dgt1 = new DGT1Command(teamController, match);
		Command dgt2 = new DGT2Command(teamController, match);
		Command utt1 = new UTT1Command(teamController, match);
		Command utt2 = new UTT2Command(teamController, match);
		Command rtt1 = new RTT1Command(teamController, match);
		Command rtt2 = new RTT2Command(teamController, match);
		Command prt1 = new PRT1Command(teamController, match);
		Command prt2 = new PRT2Command(teamController, match);
		Command pwt1 = new PWT1Command(teamController, match);
		Command pwt2 = new PWT2Command(teamController, match);
		Command pss = new PSSCommand(teamController, match);
		Command xpt1 = new XPT1Command(teamController, match);
		Command xpt2 = new XPT2Command(teamController, match);
*/
		mySwitch = new CommandSwitch();
/*		mySwitch.register("PSM", psm);
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
*/
	}
	public void displayAllStats() {
		statsDisplayPanel.updatePassStats(1, teamController.getPassCompletes(1),teamController.getPassAttempts(1),teamController.getPassPercent(1));
		statsDisplayPanel.updatePassStats(2, teamController.getPassCompletes(2),teamController.getPassAttempts(2),teamController.getPassPercent(2));
		statsDisplayPanel.updateShotStats(1, teamController.getShotCompletes(1),teamController.getShotAttempts(1),teamController.getShotPercent(1));
		statsDisplayPanel.updateShotStats(2, teamController.getShotCompletes(2),teamController.getShotAttempts(2),teamController.getShotPercent(2));
		statsDisplayPanel.updateClearStats(1, teamController.getClearCompletes(1),teamController.getClearAttempts(1),teamController.getClearPercent(1));
		statsDisplayPanel.updateClearStats(2, teamController.getClearCompletes(2),teamController.getClearAttempts(2),teamController.getClearPercent(2));
		statsDisplayPanel.updateTwoBarPassStats(1, teamController.getTwoBarPassCompletes(1),teamController.getTwoBarPassAttempts(1),teamController.getTwoBarPassPercent(1));
		statsDisplayPanel.updateTwoBarPassStats(2, teamController.getTwoBarPassCompletes(2),teamController.getTwoBarPassAttempts(2),teamController.getTwoBarPassPercent(2));
		statsDisplayPanel.updateScoring(1, teamController.getScoring(1));
		statsDisplayPanel.updateScoring(2, teamController.getScoring(2));
		statsDisplayPanel.updateThreeBarScoring(1, teamController.getThreeBarScoring(1));
		statsDisplayPanel.updateThreeBarScoring(2, teamController.getThreeBarScoring(2));
		statsDisplayPanel.updateFiveBarScoring(1, teamController.getFiveBarScoring(1));
		statsDisplayPanel.updateFiveBarScoring(2, teamController.getFiveBarScoring(2));
		statsDisplayPanel.updateTwoBarScoring(1, teamController.getTwoBarScoring(1));
		statsDisplayPanel.updateTwoBarScoring(2, teamController.getTwoBarScoring(2));
		statsDisplayPanel.updateStuffs(1,  teamController.getStuffs(1));
		statsDisplayPanel.updateStuffs(2,  teamController.getStuffs(2));
		statsDisplayPanel.updateBreaks(1,  teamController.getBreaks(1));
		statsDisplayPanel.updateBreaks(2,  teamController.getBreaks(2));
	}
	private void processCommand(String command) {
//		mySwitch.execute(command);
		System.out.println("command: " + command);
		if (command.equals("IGT1")) insertIGT1Command();
		if (command.equals("DGT1")) insertDGT1Command();
		return;
	}
	private void insertIGT1Command() {
		System.out.println("In insertIGT1Command");
		System.out.println("  UndoRedoPointer: " + undoRedoPointer);
		deleteElementsAfterPointer(undoRedoPointer);
		Command command = new IGT1Command(teamController, match, team1, team2);
		System.out.println("  IGT1Command: " + command);
		command.execute();
		commandStack.push(command);
		undoRedoPointer++;
		System.out.println("  UndoRedoPointer: " + undoRedoPointer);
	}
	private void insertDGT1Command() {
		System.out.println("In insertDGT1Command");
		System.out.println("  UndoRedoPointer: " + undoRedoPointer);
		deleteElementsAfterPointer(undoRedoPointer);
		Command command = new DGT1Command(teamController, match, team1, team2);
		System.out.println("  DGT1Command: " + command);
		command.execute();
		commandStack.push(command);
		undoRedoPointer++;
		System.out.println("  UndoRedoPointer: " + undoRedoPointer);
	}
	public void undo()
	{
		if(undoRedoPointer < 0) return;
		System.out.println("Undo: pulling from position: " + undoRedoPointer);
	    Command command = commandStack.get(undoRedoPointer);
		System.out.println("in Undo: Command: " + command);
	    command.unExecute();
	    undoRedoPointer--;
		System.out.println("in Undo: UndoRedoPointer: " + undoRedoPointer);
	}

	public void redo()
	{
	    if(undoRedoPointer == commandStack.size() - 1)
	        return;
	    undoRedoPointer++;
		System.out.println("in Redo: UndoRedoPointer: " + undoRedoPointer);
	    Command command = commandStack.get(undoRedoPointer);
		System.out.println("in Redo: Command: " + command);
	    command.execute();
	}
	private void deleteElementsAfterPointer(int undoRedoPointer)
	{
	    if (commandStack.size()<1) return;
	    for(int i = commandStack.size()-1; i > undoRedoPointer; i--)
	    {
	        commandStack.remove(i);
	    }
	}
}