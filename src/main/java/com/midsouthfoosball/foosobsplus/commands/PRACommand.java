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

package com.midsouthfoosball.foosobsplus.commands;

import com.midsouthfoosball.foosobsplus.controller.MatchController;
import com.midsouthfoosball.foosobsplus.controller.StatsController;
import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.main.Main;
/**
 * Press Reset All Button Command
 * @author Hugh Garner
 *
 */
public class PRACommand implements Command {
	private final StatsController statsController;
	private final TeamController teamController;
	private final MatchController matchController;
	public PRACommand(StatsController statsController, TeamController teamController, MatchController matchController) {
		this.statsController = statsController;
		this.teamController = teamController;
		this.matchController = matchController;
	}
	public void execute() {
		teamController.resetAll();
		matchController.resetMatch();
		statsController.displayAllStats();
		Main.setTeamGameCountVisible("1","0");
		Main.setTeamGameCountVisible("2", "0");
		Main.setTeamGameCountVisible("3", "0");
	}
	public String getCode() {
		return statsController.getLastCode();
	}
}
