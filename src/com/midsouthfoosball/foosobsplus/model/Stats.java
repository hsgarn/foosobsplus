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
package com.midsouthfoosball.foosobsplus.model;

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
	private boolean isBreak;
	private boolean isPenalty;
	private boolean isShot;
	private boolean isPass;
	private boolean isClear;
	private boolean isDrop;
	private boolean isTimeOut;
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
	private boolean team1TimeOut;
	private boolean team2TimeOut;
	private boolean isCommand;
	private boolean isPassComplete;
	private boolean isClearComplete;
	private boolean isStuff;
	
	private char breakChar = new Character('B');
	private char stuffChar = new Character('F');
	private char goalChar = new Character('G');
	private char penaltyChar = new Character('X');
	private char passChar = new Character('P');
	private char shotChar = new Character('S');
	private char clearChar = new Character('C');
	private char timeOutChar = new Character('T');
	private char fiveRodChar = new Character('5');
	private char twoRodChar = new Character('2');
	private char threeRodChar = new Character('3');
	private char team1Char = new Character('Y');
	private char team2Char = new Character('B');
	private char commandChar = new Character('X');
	private char dropChar = new Character('D');
	
	private Team team1;
	private Team team2;
	private Game[] games;
	private Match match;

	public Stats(Team team1, Team team2, Game[] games, Match match) {
		codeHistory = new DefaultListModel<String>();
		this.team1 = team1;
		this.team2 = team2;
		this.games = games;
		this.match = match;
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
	public boolean getTeam1TimeOut() {
		return team1TimeOut;
	}
	public boolean getTeam2TimeOut() {
		return team2TimeOut;
	}
	public boolean getIsCommand() {
		return isCommand;
	}
	public String getCommand() {
		return command;
	}
	public boolean isFiveRod() {
		return isFiveRod;
	}

	public boolean isTwoRod() {
		return isTwoRod;
	}

	public boolean isThreeRod() {
		return isThreeRod;
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
			scoringLogic();
		}
		if(isShot) shotLogic();
		if(isPass) {
			passLogic();
			if(wasTwoRod) twoBarPassLogic();
		}
		if(isClear) clearLogic();
		if(isDrop) dropLogic();
		if(isBreak) breakLogic();
		if(isTimeOut) timeOutLogic();
		
		showParsed();
	}
	private void parseCode(String previousCode, String code) {
		resetFlags();
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
		isBreak = currentModifier==breakChar;
		isStuff = currentModifier==stuffChar && isSameTeam;
		isPenalty = currentAction==penaltyChar;
		isPass = currentAction==passChar;
		isShot = currentAction==shotChar;
		isClear = currentAction==clearChar;
		isDrop = currentAction==dropChar;
		isTimeOut = currentAction==timeOutChar;
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
private void resetFlags() {
	isTeam1 = false;
	isTeam2 = false;
	isSameTeam = false;
	isTeamScored = false;
	isSameRod = false;
	isBreak = false;
	isStuff = false;
	isPenalty = false;
	isPass = false;
	isShot = false;
	isClear = false;
	isDrop = false;
	isTimeOut = false;
	isFiveRod = false;
	isTwoRod = false;
	isThreeRod = false;
	wasFiveRod = false;
	wasTwoRod = false;
	wasThreeRod = false;
	isForwardDirection = false;
	isPassComplete = false;
	isClearComplete = false;
	team1Scored = false;
	team2Scored = false;
	team1TimeOut = false;
	team2TimeOut = false;
}
	public Float caclPercent(int attempts, int completes) {
		float percent = 0;
		if(attempts > 0) {
			percent = (float) completes/ (float) attempts;
			percent = percent * 100f;
		}
		return percent;
	}
	private void scoringLogic() {
		if(isTeam1) {
			team2Scored=true;
			team2.setScoring(team2.getScoring()+1);
		} else {
			if(isTeam2) {
				team1Scored=true;
				team1.setScoring(team1.getScoring()+1);
			}
		}
		if(!isSameTeam) {
			if(wasThreeRod) {
				if(team1Scored) {
					team1.setThreeBarScoring(team1.getThreeBarScoring()+1);
				} else {
					team2.setThreeBarScoring(team2.getThreeBarScoring()+1);
				}
			} else if(wasFiveRod) {
				if(team1Scored) {
					team1.setFiveBarScoring(team1.getFiveBarScoring()+1);
				} else {
					team2.setFiveBarScoring(team2.getFiveBarScoring()+1);
				}
			} else if(wasTwoRod) {
				if(team1Scored) {
					team1.setTwoBarScoring(team1.getTwoBarScoring()+1);
				} else {
					team2.setTwoBarScoring(team2.getTwoBarScoring()+1);
				}
			}
		}
	}
	private void clearLogic() {
		int attempts = 0;
		int completes = 0;
		if(isClearComplete) {
			if(isSameTeam) {
				if(isTeam1) {
					attempts=team1.getClearAttempts()+1;
					completes=team1.getClearCompletes()+1;
					team1.setClearAttempts(attempts);
					team1.setClearCompletes(completes);
					team1.setClearPercent(caclPercent(attempts, completes));
				} else {
					attempts=team2.getClearAttempts()+1;
					completes=team2.getClearCompletes()+1;
					team2.setClearAttempts(attempts);
					team2.setClearCompletes(completes);
					team2.setClearPercent(caclPercent(attempts, completes));
				}
			} else {
				if(isTeam1) {
					attempts=team2.getClearAttempts()+1;
					completes=team2.getClearCompletes()+1;
					team2.setClearAttempts(attempts);
					team2.setClearCompletes(completes);
					team2.setClearPercent(caclPercent(attempts, completes));
				} else {
					attempts=team1.getClearAttempts()+1;
					completes=team1.getClearCompletes()+1;
					team1.setClearAttempts(attempts);
					team1.setClearCompletes(completes);
					team1.setClearPercent(caclPercent(attempts, completes));
				}
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					attempts=team1.getClearAttempts()+1;
					completes=team1.getClearCompletes();
					team1.setClearAttempts(attempts);
					team1.setClearPercent(caclPercent(attempts, completes));
			} else {
					attempts=team2.getClearAttempts()+1;
					completes=team2.getClearCompletes();
					team2.setClearAttempts(attempts);
					team2.setClearPercent(caclPercent(attempts, completes));
				}
			} else {
				if(isTeam1) {
					attempts=team2.getClearAttempts()+1;
					completes=team2.getClearCompletes();
					team2.setClearAttempts(attempts);
					team2.setClearPercent(caclPercent(attempts, completes));
				} else {
					attempts=team1.getClearAttempts()+1;
					completes=team1.getClearCompletes();
					team1.setClearAttempts(attempts);
					team1.setClearPercent(caclPercent(attempts, completes));
				}
			}
		}
	}
	private void dropLogic() {
		if(isTwoRod && wasTwoRod && !isSameTeam) {
			if(isTeam1) {
				team2.setClearAttempts(team2.getClearAttempts() + 1);
				team2.setClearCompletes(team2.getClearCompletes() + 1);
			} else {
				team1.setClearAttempts(team1.getClearAttempts() + 1);
				team1.setClearCompletes(team1.getClearCompletes() + 1);
			}
		}
	}
	private void breakLogic() {
		if(isTeamScored) {
			if(team1Scored) {
				team1.setBreaks(team1.getBreaks() + 1);
			} else {
				team2.setBreaks(team2.getBreaks() + 1);
			}
		} else {
			if(isTeam1) {
				team1.setBreaks(team1.getBreaks() + 1);
			} else {
				team2.setBreaks(team2.getBreaks() + 1);
			}
		}
	}
	private void timeOutLogic() {
		team1TimeOut=false;
		team2TimeOut=false;
		if(isTeam1) {
			team1TimeOut=true;
		} else {
			team2TimeOut=true;
		}
	}
	private void passLogic() {
		int attempts;
		int completes;
		if(isPassComplete) {
			if(isTeam1) {
				attempts=team1.getPassAttempts() + 1;
				completes=team1.getPassCompletes() + 1;
				team1.setPassAttempts(attempts);
				team1.setPassCompletes(completes);
				team1.setPassPercent(caclPercent(attempts, completes));
			} else {
				attempts=team2.getPassAttempts() + 1;
				completes=team2.getPassCompletes() + 1;
				team2.setPassAttempts(attempts);
				team2.setPassCompletes(completes);
				team2.setPassPercent(caclPercent(attempts, completes));
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					attempts=team1.getPassAttempts() + 1;
					completes=team1.getPassCompletes();
					team1.setPassAttempts(attempts);
					team1.setPassPercent(caclPercent(attempts, completes));
				} else {
					attempts=team2.getPassAttempts() + 1;
					completes=team2.getPassCompletes();
					team2.setPassAttempts(attempts);
					team2.setPassPercent(caclPercent(attempts, completes));
				}
				if(isStuff) {
					if(isTeam1) {
						team2.setStuffs(team2.getStuffs() + 1);
					} else {
						team1.setStuffs(team1.getStuffs() + 1);
					}
				}
			} else {
				if(isTeam1) {
					attempts=team2.getPassAttempts() + 1;
					completes=team2.getPassCompletes();
					team2.setPassAttempts(attempts);
					team2.setPassPercent(caclPercent(attempts, completes));
				} else {
					attempts=team1.getPassAttempts() + 1;
					completes=team1.getPassCompletes();
					team1.setPassAttempts(attempts);
					team1.setPassPercent(caclPercent(attempts, completes));
				}
			}
		}
	}
	private void twoBarPassLogic() {
		int attempts;
		int completes;
		if(isPassComplete) {
			if(isTeam1) {
				attempts=team1.getTwoBarPassAttempts() + 1;
				completes=team1.getTwoBarPassCompletes() + 1;
				team1.setTwoBarPassAttempts(attempts);
				team1.setTwoBarPassCompletes(completes);
				team1.setTwoBarPassPercent(caclPercent(attempts, completes));
			} else {
				attempts=team2.getTwoBarPassAttempts() + 1;
				completes=team2.getTwoBarPassCompletes() + 1;
				team2.setTwoBarPassAttempts(attempts);
				team2.setTwoBarPassCompletes(completes);
				team2.setTwoBarPassPercent(caclPercent(attempts, completes));
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					attempts=team1.getTwoBarPassAttempts() + 1;
					completes=team1.getTwoBarPassCompletes();
					team1.setTwoBarPassAttempts(attempts);
					team1.setTwoBarPassPercent(caclPercent(attempts, completes));
				} else {
					attempts=team2.getTwoBarPassAttempts() + 1;
					completes=team2.getTwoBarPassCompletes();
					team2.setTwoBarPassAttempts(attempts);
					team2.setTwoBarPassPercent(caclPercent(attempts, completes));
				}
			} else {
				if(isTeam1) {
					attempts=team2.getTwoBarPassAttempts() + 1;
					completes=team2.getTwoBarPassCompletes();
					team2.setTwoBarPassAttempts(attempts);
					team2.setTwoBarPassPercent(caclPercent(attempts, completes));
				} else {
					attempts=team1.getTwoBarPassAttempts() + 1;
					completes=team1.getTwoBarPassCompletes();
					team1.setTwoBarPassAttempts(attempts);
					team1.setTwoBarPassPercent(caclPercent(attempts, completes));
				}
			}
		}
	}
	private void shotLogic() {
		int attempts;
		int completes;
		if(isTeamScored) {
			if(team1Scored) {
				attempts=team1.getShotAttempts() + 1;
				completes=team1.getShotCompletes() + 1;
				team1.setShotAttempts(attempts);
				team1.setShotCompletes(completes);
				team1.setShotPercent(caclPercent(attempts, completes));
			} else {
				attempts=team2.getShotAttempts() + 1;
				completes=team2.getShotCompletes() + 1;
				team2.setShotAttempts(attempts);
				team2.setShotCompletes(completes);
				team2.setShotPercent(caclPercent(attempts, completes));
			}
			if(isStuff) {
				if(team1Scored) {
					team1.setStuffs(team1.getStuffs() + 1);
				} else {
					team2.setStuffs(team2.getStuffs() + 1);
				}
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					attempts=team1.getShotAttempts() + 1;
					completes=team1.getShotCompletes();
					team1.setShotAttempts(attempts);
					team1.setShotPercent(caclPercent(attempts, completes));
				} else {
					attempts=team2.getShotAttempts() + 1;
					completes=team2.getShotCompletes();
					team2.setShotAttempts(attempts);
					team2.setShotPercent(caclPercent(attempts, completes));
				}
			} else {
				if(isTeam1) {
					attempts=team2.getShotAttempts() + 1;
					completes=team2.getShotCompletes();
					team2.setShotAttempts(attempts);
					team2.setShotPercent(caclPercent(attempts, completes));
				} else {
					attempts=team1.getShotAttempts() + 1;
					completes=team1.getShotCompletes();
					team1.setShotAttempts(attempts);
					team1.setShotPercent(caclPercent(attempts, completes));
				}
			}
		}
	}
	private void showParsed() {
		if (!isShowParsed) return;
		System.out.println("----------------------------------------");
		System.out.println("Previous Code: " + previousCode + "       Current Code: " + code);
		System.out.println("Previous Team: " + previousTeam + "         Current Team: " + currentTeam);
		System.out.println("Previous Pos.: " + previousPosition + "         Current Pos.: " + currentPosition);
		System.out.println("Previous Act.: " + previousAction + "         Current Act.: " + currentAction);
		System.out.println("Previous Mod.: " + previousModifier + "         Current Mod.: " + currentModifier);
		System.out.println("team1Scored: " + team1Scored + "       team2Scored: " + team2Scored);
		System.out.println("team1TimeOut: " + team1TimeOut + "      team1TimeOut: " + team2TimeOut);
		System.out.println(" IsTeam1: " + isTeam1 + "         IsTeam2: " + isTeam2);
		System.out.println(" IsSameTeam: " + isSameTeam + "     IsSameRod: " + isSameRod);
		System.out.println(" IsTeamScored: " + isTeamScored + "   IsBreak: " + isBreak);
		System.out.println(" IsPenalty: " + isPenalty + "      IsStuff: " + isStuff);
		System.out.println(" IsPass: " + isPass + "          IsShot: " + isShot);
		System.out.println(" IsClear: " + isClear + "         IsTimeOut: " + isTimeOut);
		System.out.println(" IsForwardDirection: " + isForwardDirection);
		System.out.println(" IsTwoRod: " + isTwoRod + "    wasTwoRod: " + wasTwoRod);
		System.out.println(" IsFiveRod: " + isFiveRod + "    wasFiveRod: " + wasFiveRod);
		System.out.println(" IsThreeRod: " + isThreeRod + "    wasThreeRod: " + wasThreeRod);
		System.out.println("Team1PassAttempts: " + team1.getPassAttempts() + "  Completes: " + team1.getPassCompletes());
		System.out.println("Team2PassAttempts: " + team2.getPassAttempts() + "  Completes: " + team2.getPassCompletes());
		System.out.println("Team1ShotAttempts: " + team1.getShotAttempts() + "  Completes: " + team1.getShotCompletes());
		System.out.println("Team2ShotAttempts: " + team2.getShotAttempts() + "  Completes: " + team2.getShotCompletes());
		System.out.println("Team1ClearAttempts: " + team1.getClearAttempts() + "  Completes: " + team1.getClearCompletes());
		System.out.println("Team2ClearAttempts: " + team2.getClearAttempts() + "  Completes: " + team2.getClearCompletes());
		System.out.println("Team1TwoBarPassAttempts: " + team1.getTwoBarPassAttempts() + "  Completes: " + team1.getTwoBarPassCompletes());
		System.out.println("Team2TwoBarPassAttempts: " + team2.getTwoBarPassAttempts() + "  Completes: " + team2.getTwoBarPassCompletes());
		System.out.println("Team1Stuffs: " + team1.getStuffs() + "       Team2Stuffs: " + team2.getStuffs());
		System.out.println("Team1Breaks: " + team1.getBreaks() + "       Team2Breaks: " + team2.getBreaks());
//		System.out.println("Team1Errors: " + team1.getErrors() + "       Team2Errors: " + team2.getErrors());
		System.out.println("");
	}
}
