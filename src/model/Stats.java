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
package model;

import javax.swing.DefaultListModel;

public class Stats {
	private String code = "";
	private DefaultListModel<String> codeHistory;
	private String previousCode = "";
	private String command = "";
	private char previousTeam;
	private char previousPosition;
	private char previousAction;
	private char previousModifier;
	private char currentTeam;
	private char currentPosition;
	private char currentAction;
	private char currentModifier;
	private boolean isSameTeam;
	private boolean isTeamScored;
	private boolean isSameRod;
	private boolean isLuckyBreak;
	private boolean isPenalty;
	private boolean isShot;
	private boolean isPass;
	private boolean isClear;
	private boolean isDrop;
	private boolean isFiveRod;
	private boolean isTwoRod;
	private boolean isThreeRod;
	private boolean wasFiveRod;
	private boolean wasTwoRod;
	private boolean wasThreeRod;
	private boolean isForwardDirection;
	private boolean isShowParsed = true;
	private boolean isTeam1;
	private boolean isTeam2;
	private boolean team1Scored;
	private boolean team2Scored;
	private boolean isCommand;
	private boolean isPassComplete;
	private boolean isClearComplete;
	
	private char breakChar = new Character('B');
	private char goalChar = new Character('G');
	private char penaltyChar = new Character('X');
	private char passChar = new Character('P');
	private char shotChar = new Character('S');
	private char clearChar = new Character('C');
	private char fiveRodChar = new Character('5');
	private char twoRodChar = new Character('2');
	private char threeRodChar = new Character('3');
	private char team1Char = new Character('Y');
	private char team2Char = new Character('B');
	private char commandChar = new Character('X');
	private char dropChar = new Character('D');
	
	private int team1PassAttempts = 0;
	private int team1PassCompletes = 0;
	private int team1PassBreaks = 0;
	private int team1ShotAttempts = 0;
	private int team1ShotCompletes = 0;
	private int team1ShotBreaks = 0;
	private int team1ClearAttempts = 0;
	private int team1ClearCompletes = 0;
	private int team2PassAttempts = 0;
	private int team2PassCompletes = 0;
	private int team2PassBreaks = 0;
	private int team2ShotAttempts = 0;
	private int team2ShotCompletes = 0;
	private int team2ShotBreaks = 0;
	private int team2ClearAttempts = 0;
	private int team2ClearCompletes = 0;
	
	public int getTeam1PassAttempts() {
		return team1PassAttempts;
	}

	public int getTeam1PassCompletes() {
		return team1PassCompletes;
	}

	public int getTeam1PassBreaks() {
		return team1PassBreaks;
	}

	public int getTeam1ShotAttempts() {
		return team1ShotAttempts;
	}

	public int getTeam1ShotCompletes() {
		return team1ShotCompletes;
	}

	public int getTeam1ShotBreaks() {
		return team1ShotBreaks;
	}

	public int getTeam1ClearAttempts() {
		return team1ClearAttempts;
	}

	public int getTeam1ClearCompletes() {
		return team1ClearCompletes;
	}

	public int getTeam2PassAttempts() {
		return team2PassAttempts;
	}

	public int getTeam2PassCompletes() {
		return team2PassCompletes;
	}

	public int getTeam2PassBreaks() {
		return team2PassBreaks;
	}

	public int getTeam2ShotAttempts() {
		return team2ShotAttempts;
	}

	public int getTeam2ShotCompletes() {
		return team2ShotCompletes;
	}

	public int getTeam2ShotBreaks() {
		return team2ShotBreaks;
	}

	public int getTeam2ClearAttempts() {
		return team2ClearAttempts;
	}

	public int getTeam2ClearCompletes() {
		return team2ClearCompletes;
	}

	public Stats() {
		codeHistory = new DefaultListModel<String>();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
		processCode(code, previousCode);
	}
	public void clearAll() {
		code = "";
		codeHistory.clear();
	}
	public void addCodeToHistory(String code) {
		codeHistory.addElement(code);
		if (!isCommand) {
			previousCode = code;
		}
	}
	public boolean getTeam1Scored() {
		return team1Scored;
	}
	public boolean getTeam2Scored() {
		return team2Scored;
	}
	public boolean getIsCommand() {
		return isCommand;
	}
	public String getCommand() {
		return command;
	}
	private void processCode(String code, String previousCode) {
		parseCode(previousCode, code);
		if(isCommand) {
			command=code.substring(1,code.length());
			return;
		}
		team1Scored=false;
		team2Scored=false;
		if(isTeamScored) {
			if(isTeam1) {
				team2Scored=true;
			} else {
				if(isTeam2) {
					team1Scored=true;
				}
			}
		}
		if(isShot) shotLogic();
		if(isPass) passLogic();
		if(isClear) clearLogic();
		if(isDrop) dropLogic();
		
		showParsed();
	}
	private void parseCode(String previousCode, String code) {
		if (code.length() < 3) return;
		currentTeam = code.charAt(0);
		isCommand = currentTeam==commandChar;
		if(isCommand) return;
		if (previousCode.length()>0) {
			previousTeam = previousCode.charAt(0);
			previousPosition = previousCode.charAt(1);
			previousAction = previousCode.charAt(2);
			if (previousCode.length() > 3) {
				previousModifier = previousCode.charAt(3);
			}
		}
		isTeam1 = currentTeam==team1Char;
		isTeam2 = currentTeam==team2Char;
		currentPosition = code.charAt(1);
		currentAction = code.charAt(2);
		if (code.length() > 3) {
			currentModifier = code.charAt(3);
		}
		isSameTeam = previousTeam==currentTeam;
		isTeamScored = currentPosition==goalChar;
		isSameRod = isSameTeam && currentPosition==previousPosition;
		isLuckyBreak = currentModifier==breakChar;
		isPenalty = currentAction==penaltyChar;
		isPass = currentAction==passChar;
		isShot = currentAction==shotChar;
		isClear = currentAction==clearChar;
		isDrop = currentAction==dropChar;
		isFiveRod = currentPosition==fiveRodChar;
		isTwoRod = currentPosition==twoRodChar;
		isThreeRod = currentPosition==threeRodChar;
		wasFiveRod = previousPosition==fiveRodChar;
		wasTwoRod = previousPosition==twoRodChar;
		wasThreeRod = previousPosition==threeRodChar;
		
		isForwardDirection = isSameTeam && !isSameRod && ((isFiveRod && wasTwoRod) || (isThreeRod && (wasFiveRod || wasTwoRod)));

		isPassComplete = isPass && isSameTeam && !isSameRod && isForwardDirection;
		isClearComplete = isClear && !isSameRod && (isSameTeam || (!isSameTeam && (isFiveRod || isTwoRod)));

	}
	private void clearLogic() {
		if(isClearComplete) {
			if(isSameTeam) {
				if(isTeam1) {
					team1ClearAttempts++;
					team1ClearCompletes++;
				} else {
					team2ClearAttempts++;
					team2ClearCompletes++;
				}
			} else {
				if(isTeam1) {
					team2ClearAttempts++;
					team2ClearCompletes++;
				} else {
					team1ClearAttempts++;
					team1ClearCompletes++;
				}
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					team1ClearAttempts++;
				} else {
					team2ClearAttempts++;
				}
			} else {
				if(isTeam1) {
					team2ClearAttempts++;
				} else {
					team1ClearAttempts++;
				}
			}
		}
	}
	private void dropLogic() {
		if(isTwoRod && wasTwoRod && !isSameTeam) {
			if(isTeam1) {
				team2ClearAttempts++;
				team2ClearCompletes++;
			} else {
				team1ClearAttempts++;
				team1ClearCompletes++;
			}
		}
	}
	private void passLogic() {
		if(isPassComplete) {
			if(isTeam1) {
				team1PassAttempts++;
				team1PassCompletes++;
			} else {
				team2PassAttempts++;
				team2PassCompletes++;
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					team1PassAttempts++;
				} else {
					team2PassAttempts++;
				}
			} else {
				if(isTeam1) {
					team2PassAttempts++;
				} else {
					team1PassAttempts++;
				}
			}
		}
	}
	private void shotLogic() {
		if(isTeamScored) {
			if(team1Scored) {
				team1ShotAttempts++;
				team1ShotCompletes++;
			} else {
				team2ShotAttempts++;
				team2ShotCompletes++;
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					team1ShotAttempts++;
				} else {
					team2ShotAttempts++;
				}
			} else {
				if(isTeam1) {
					team2ShotAttempts++;
				} else {
					team1ShotAttempts++;
				}
			}
		}
	}
	private void showParsed() {
		if (!isShowParsed) return;
		System.out.println("----------------------------------------");
		System.out.println("Previous Code: " + previousCode + "   Current Code: " + code);
		System.out.println("Previous Team: " + previousTeam + "     Current Team: " + currentTeam);
		System.out.println("Previous Pos.: " + previousPosition + "     Current Pos.: " + currentPosition);
		System.out.println("Previous Act.: " + previousAction + "     Current Act.: " + currentAction);
		System.out.println("Previous Mod.: " + previousModifier + "     Current Mod.: " + currentModifier);
		System.out.println("team1Scored: " + team1Scored + "   team2Scored: " + team2Scored);
		System.out.println("Is Team 1: " + isTeam1 + "      Is Team 2: " + isTeam2);
		System.out.println(" Is Same Team: " + isSameTeam + "   Is Same Rod: " + isSameRod);
		System.out.println(" Is Team Scored: " + isTeamScored);
		System.out.println(" Is Lucky Break: " + isLuckyBreak);
		System.out.println(" Is Penalty: " + isPenalty);
		System.out.println(" Is Pass: " + isPass + "      Is Shot: " + isShot);
		System.out.println(" Is Clear: " + isClear);
		System.out.println(" Is forward direction: " + isForwardDirection);
		System.out.println("Team1PassAttempts: " + team1PassAttempts + "  Completes: " + team1PassCompletes);
		System.out.println("Team2PassAttempts: " + team2PassAttempts + "  Completes: " + team2PassCompletes);
		System.out.println("Team1ShotAttempts: " + team1ShotAttempts + "  Completes: " + team1ShotCompletes);
		System.out.println("Team2ShotAttempts: " + team2ShotAttempts + "  Completes: " + team2ShotCompletes);
		System.out.println("Team1ClearAttempts: " + team1ClearAttempts + "  Completes: " + team1ClearCompletes);
		System.out.println("Team2ClearAttempts: " + team2ClearAttempts + "  Completes: " + team2ClearCompletes);
		
	}
}
