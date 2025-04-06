/**
Copyright © 2020-2025 Hugh Garner
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stats implements Serializable {
	private static final long serialVersionUID = -3545984674770731270L;
	private String code = "";
	private DefaultListModel<String> codeHistory;
	private String previousCode = "";
	private String command = "";
	private transient char previousTeam;
	private transient char previousPosition;
	private transient char previousAction;
	private transient char previousModifier;
	private transient char currentTeam;
	private transient char currentPosition;
	private transient char currentAction;
	private transient char currentModifier;
	private transient boolean isSameTeam;
	private transient boolean isTeamScored;
	private transient boolean isSameRod;
	private transient boolean isBreak;
	private transient boolean isPenalty;
	private transient boolean isShot;
	private transient boolean isAce;
	private transient boolean isPass;
	private transient boolean isClear;
	private transient boolean isDrop;
	private transient boolean isTimeOut;
	private transient boolean isFiveRod;
	private transient boolean isTwoRod;
	private transient boolean isThreeRod;
	private transient boolean isOffTable;
	private transient boolean isDeadBall;
	private transient boolean wasFiveRod;
	private transient boolean wasTwoRod;
	private transient boolean wasThreeRod;
	private transient boolean isForwardDirection;
	private transient boolean isTeam1;
	private transient boolean isTeam2;
	private final transient boolean[] teamScored = {false, false, false};
	private final transient boolean[] teamTimeOut = {false, false, false};
	private transient boolean isCommand;
	private transient boolean isPassComplete;
	private transient boolean isClearComplete;
	private transient boolean isStuff;
	private transient boolean isShotOnGoal;
	private transient boolean isError = false;
	private transient String errorMsg = "";
	private static final transient char BREAKCHAR 			= 'B';
	private static final transient char STUFFCHAR 			= 'F';
	private static final transient char GOALCHAR 			= 'G';
	private static final transient char PENALTYCHAR 		= 'X';
	private static final transient char PASSCHAR			= 'P';
	private static final transient char SHOTCHAR 			= 'S';
	private static final transient char CLEARCHAR 			= 'C';
	private static final transient char TIMEOUTCHAR 		= 'T';
	private static final transient char FIVERODCHAR 		= '5';
	private static final transient char TWORODCHAR 			= '2';
	private static final transient char THREERODCHAR 		= '3';
	private static final transient char OFFTABLECHAR 		= 'O';
	private static final transient char DEADBALLCHAR 		= 'D';
	private static final transient char TEAM1CHAR 			= 'Y';
	private static final transient char TEAM2CHAR 			= 'B';
	private static final transient char COMMANDCHAR 		= 'X';
	private static final transient char DROPCHAR 			= 'D';
	private static final transient char RESETCHAR 			= 'R';
	private static final transient char WARNCHAR 			= 'W';
	private static final transient char BALLCHAR 			= 'Y';
	private static final transient char ERRORCHAR 			= 'E';
	private static final transient char ACECHAR 			= 'A';
	private static final transient char WALLSCHAR 			= 'W';
	private static final transient char SPINCHAR 			= 'S';
	private static final transient char JARCHAR 			= 'J';
	private static final transient char DISTRACTIONCHAR		= 'D';
	private static final transient char TIMEOUTSOUTCHAR             = 'T';
	private static final transient char ILLEGALPASSCHAR             = 'P';
	private static final transient char TECHNICALCHAR 		= 'X';
	private static final transient char PROTOCOLCHAR 		= 'R';
	private static final transient char OTHERCHAR 			= 'O';
	private final transient Team team1;
	private final transient Team team2;
	private final transient Logger logger = LoggerFactory.getLogger(Stats.class);
	public Stats(Team team1, Team team2) {
		codeHistory = new DefaultListModel<>();
		this.team1 = team1;
		this.team2 = team2;
	}
	public String getCode() {
		return code;
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
	public String getLastCode() {
		if (codeHistory.getSize() > 0) {
			return codeHistory.lastElement();
		}
		return "";
	}
	public List<String> getCodeHistoryAsList() {
		List<String> codes = new ArrayList<>();
		for (int i = 0; i < codeHistory.size(); i++) {
			codes.add(codeHistory.getElementAt(i));
		}
		return codes;
	}
	public boolean getTeamScored(int teamNumber) {
		return teamScored[teamNumber-1];
	}
	public boolean getTeamTimeOut(int teamNumber) {
		return teamTimeOut[teamNumber-1];
	}
	public boolean getIsCommand() {
		return isCommand;
	}
	public String getCommand() {
		return command;
	}
	public boolean getIsFiveRod() {
		return isFiveRod;
	}
	public boolean getIsTwoRod() {
		return isTwoRod;
	}
	public boolean getIsThreeRod() {
		return isThreeRod;
	}
	public boolean getIsOffTable() {
		return isOffTable;
	}
	public boolean getIsDeadBall() {
		return isDeadBall;
	}
	public DefaultListModel<String> getCodeHistory() {
		return codeHistory;
	}
	public String getPreviousCode() {
		return previousCode;
	}
	public boolean getIsError() {
		return isError;
	}
	public void setCodeHistory(DefaultListModel<String> codeHistory) {
		this.codeHistory = codeHistory;
	}
	public void setPreviousCode(String previousCode) {
		this.previousCode = previousCode;
	}
	public void setIsCommand(boolean isCommand) {
		this.isCommand = isCommand;
	}
	public void setCode(String code) {
		this.code = code;
		processCode(code, previousCode);
	}
	private void processCode(String code, String previousCode) {
		parseCode(previousCode, code);
		if(isCommand) {
			command=code.substring(1,code.length());
			return;
		}
		teamScored[0]=false;
		teamScored[1]=false;
		teamScored[2]=false;
		if(isTeamScored) {
			scoringLogic();
		}
		if(isShot||isAce) {
			shotLogic();
			if(isShotOnGoal) shotsOnGoalLogic();
		}
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
		isError = false;
		errorMsg = "";
		resetFlags();
		if (code.length() < 3) {
			isError = true;
			errorMsg = "Invalid code.";
			return;
		}
		currentTeam = code.charAt(0);
		isCommand = currentTeam==COMMANDCHAR;
		if(isCommand) return;
		if (previousCode.length()>0) {
			previousTeam = previousCode.charAt(0);
			previousPosition = previousCode.charAt(1);
			previousAction = previousCode.charAt(2);
			if (previousCode.length() > 3) {
				previousModifier = previousCode.charAt(3);
			}
		}
		isTeam1 = currentTeam==TEAM1CHAR;
		isTeam2 = currentTeam==TEAM2CHAR;
		currentPosition = code.charAt(1);
		currentAction = code.charAt(2);
		if (code.length() > 3) {
			currentModifier = code.charAt(3);
		} else {
			currentModifier = Character.MIN_VALUE;
		}
		validateCode();
		if (isError) {
			return;
		}
		isSameTeam = previousTeam==currentTeam;
		isTeamScored = currentPosition==GOALCHAR;
		isSameRod = isSameTeam && currentPosition==previousPosition;
		isBreak = currentModifier==BREAKCHAR;
		isStuff = currentModifier==STUFFCHAR && isSameTeam;
		isPenalty = currentAction==PENALTYCHAR;
		isPass = currentAction==PASSCHAR;
		isShot = currentAction==SHOTCHAR;
		isAce = currentAction==ACECHAR;
		isClear = currentAction==CLEARCHAR;
		isDrop = currentAction==DROPCHAR;
		isTimeOut = currentAction==TIMEOUTCHAR;
		isFiveRod = currentPosition==FIVERODCHAR;
		isTwoRod = currentPosition==TWORODCHAR;
		isThreeRod = currentPosition==THREERODCHAR;
		isOffTable = currentPosition==OFFTABLECHAR;
		isDeadBall = currentPosition==DEADBALLCHAR;
		wasFiveRod = previousPosition==FIVERODCHAR;
		wasTwoRod = previousPosition==TWORODCHAR;
		wasThreeRod = previousPosition==THREERODCHAR;
		isForwardDirection = isSameTeam && !isSameRod && ((isFiveRod && wasTwoRod) || (isThreeRod && (wasFiveRod || wasTwoRod)));
		isPassComplete = isPass && isSameTeam && !isSameRod && isForwardDirection;
		isClearComplete = isClear && ((isSameTeam && isThreeRod) || (!isSameTeam && (isFiveRod || isTwoRod)));
		isShotOnGoal = isShot && previousPosition==TWORODCHAR;
	}
	private void validateCode() {
		isError=false;
		if(!(isTeam1 || isTeam2)) {
			isError=true;
			errorMsg = "Invalid team code: " + currentTeam + ". ";
		}
		if (!(	currentPosition==GOALCHAR 		||
				currentPosition==FIVERODCHAR 	||
				currentPosition==TWORODCHAR 	||
				currentPosition==THREERODCHAR 	||
				currentPosition==OFFTABLECHAR   ||
				currentPosition==DEADBALLCHAR)
				) 
		{
			isError=true;
			errorMsg = errorMsg + "Invalid position: " + currentPosition + ". ";
		}
		if (!(	currentAction==PASSCHAR 	||
				currentAction==SHOTCHAR 	||
				currentAction==CLEARCHAR 	||
				currentAction==DROPCHAR 	||
				currentAction==TIMEOUTCHAR 	||
				currentAction==RESETCHAR 	||
				currentAction==WARNCHAR 	||
				currentAction==PENALTYCHAR 	||
				currentAction==BALLCHAR 	||
				currentAction==ERRORCHAR    ||
				currentAction==ACECHAR)
				) 
		{
			isError=true;
			errorMsg = errorMsg + "Invalid action: " + currentAction + ". ";
		}
		if (code.length() > 3) {
			if (!(	currentModifier==BREAKCHAR	 		||
					currentModifier==STUFFCHAR 			||
					currentModifier==WALLSCHAR 			||
					currentModifier==SPINCHAR 			||
					currentModifier==JARCHAR 			||
					currentModifier==DISTRACTIONCHAR 	||
					currentModifier==TIMEOUTSOUTCHAR 	||
					currentModifier==ILLEGALPASSCHAR 	||
					currentModifier==TECHNICALCHAR 		||
					currentModifier==PROTOCOLCHAR		||
					currentModifier==OTHERCHAR)
					) 
			{
				isError=true;
				errorMsg = errorMsg + "Invalid modifier: " + currentModifier + ". ";
			}
		}
		if (Settings.getShowParsed()) {
			logger.warn("ErrorMsg: " + errorMsg);
		}
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
		isAce = false;
		isClear = false;
		isDrop = false;
		isTimeOut = false;
		isFiveRod = false;
		isTwoRod = false;
		isThreeRod = false;
		isOffTable = false;
		isDeadBall = false;
		wasFiveRod = false;
		wasTwoRod = false;
		wasThreeRod = false;
		isForwardDirection = false;
		isPassComplete = false;
		isClearComplete = false;
		teamScored[0] = false;
		teamScored[1] = false;
		teamTimeOut[0] = false;
		teamTimeOut[1] = false;
		isShotOnGoal = false;
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
			teamScored[1]=true;
			team2.setScoring(team2.getScoring()+1);
		} else if (isTeam2) {
			teamScored[0]=true;
			team1.setScoring(team1.getScoring()+1);
		}
		if(!isSameTeam) {
			if(wasThreeRod) {
				if(teamScored[0]) {
					team1.setThreeBarScoring(team1.getThreeBarScoring()+1);
				} else {
					team2.setThreeBarScoring(team2.getThreeBarScoring()+1);
				}
			} else if(wasFiveRod) {
				if(teamScored[0]) {
					team1.setFiveBarScoring(team1.getFiveBarScoring()+1);
				} else {
					team2.setFiveBarScoring(team2.getFiveBarScoring()+1);
				}
			} else if(wasTwoRod) {
				if(teamScored[0]) {
					team1.setTwoBarScoring(team1.getTwoBarScoring()+1);
				} else {
					team2.setTwoBarScoring(team2.getTwoBarScoring()+1);
				}
			}
		}
	}
	private void clearLogic() {
		int attempts;
		int completes;
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
			if(teamScored[0]) {
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
		teamTimeOut[0]=false;
		teamTimeOut[1]=false;
		if(isTeam1) {
			teamTimeOut[0]=true;
		} else {
			teamTimeOut[1]=true;
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
		int aces;
		if(isTeamScored) {
			if(teamScored[0]) {
				if(isSameTeam) {
					attempts=team2.getShotAttempts() + 1;
					team2.setShotAttempts(attempts);
				} else {
					attempts=team1.getShotAttempts() + 1;
					completes=team1.getShotCompletes() + 1;
					team1.setShotAttempts(attempts);
					team1.setShotCompletes(completes);
					team1.setShotPercent(caclPercent(attempts, completes));
				}
				if(isAce) {
					aces=team1.getAces() + 1;
					team1.setAces(aces);
				}
			} else {
				if(isSameTeam) {
					attempts=team1.getShotAttempts() + 1;
					team1.setShotAttempts(attempts);
				} else {
					attempts=team2.getShotAttempts() + 1;
					completes=team2.getShotCompletes() + 1;
					team2.setShotAttempts(attempts);
					team2.setShotCompletes(completes);
					team2.setShotPercent(caclPercent(attempts, completes));
				}
				if(isAce) {
					aces=team2.getAces() + 1;
					team2.setAces(aces);
				}
			}
			if(isStuff) {
				if(teamScored[0]) {
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
	private void shotsOnGoalLogic() {
		int shotsOnGoal;
		if(isTeamScored) {
			if(teamScored[0]) {
				shotsOnGoal = team1.getShotsOnGoal() + 1;
				team1.setShotsOnGoal(shotsOnGoal);
			} else {
				shotsOnGoal = team2.getShotsOnGoal() + 1;
				team2.setShotsOnGoal(shotsOnGoal);
			}
		} else {
			if(isSameTeam) {
				if(isTeam1) {
					shotsOnGoal = team1.getShotsOnGoal() + 1;
					team1.setShotsOnGoal(shotsOnGoal);
				} else {
					shotsOnGoal = team2.getShotsOnGoal() + 1;
					team2.setShotsOnGoal(shotsOnGoal);
					
				}
			} else {
				if(isTeam1) {
					shotsOnGoal = team2.getShotsOnGoal() + 1;
					team2.setShotsOnGoal(shotsOnGoal);
				} else {
					shotsOnGoal = team1.getShotsOnGoal() + 1;
					team1.setShotsOnGoal(shotsOnGoal);
				}
			}
		}
	}
	public void showParsed() {
		if (!Settings.getShowParsed()) {
			return;
		}
		logger.info("----------------------------------------");
		logger.info("Previous Code: " + previousCode + "       Current Code: " + code);
		logger.info("Previous Team: " + previousTeam + "         Current Team: " + currentTeam);
		logger.info("Previous Pos.: " + previousPosition + "         Current Pos.: " + currentPosition);
		logger.info("Previous Act.: " + previousAction + "         Current Act.: " + currentAction);
		logger.info("Previous Mod.: " + previousModifier + "         Current Mod.: " + currentModifier);
		logger.info("teamScored[0]: " + teamScored[0] + "       teamScored[1]: " + teamScored[1]);
		logger.info("teamTimeOut[0]: " + teamTimeOut[0] + "      teamTimeOut[1]: " + teamTimeOut[1]);
		logger.info(" IsTeam1: " + isTeam1 + "         IsTeam2: " + isTeam2);
		logger.info(" IsSameTeam: " + isSameTeam + "     IsSameRod: " + isSameRod);
		logger.info(" IsTeamScored: " + isTeamScored + "   IsBreak: " + isBreak);
		logger.info(" IsPenalty: " + isPenalty + "      IsStuff: " + isStuff);
		logger.info(" IsPass: " + isPass + "          IsShot: " + isShot);
		logger.info(" IsClear: " + isClear + "         IsTimeOut: " + isTimeOut);
		logger.info(" IsForwardDirection: " + isForwardDirection);
		logger.info(" IsTwoRod: " + isTwoRod + "    wasTwoRod: " + wasTwoRod);
		logger.info(" IsFiveRod: " + isFiveRod + "    wasFiveRod: " + wasFiveRod);
		logger.info(" IsThreeRod: " + isThreeRod + "    wasThreeRod: " + wasThreeRod);
		logger.info(" IsShotOnGoal: "  + isShotOnGoal + "    isOffTable: " + isOffTable);
		logger.info(" IsDeadBall: " + isDeadBall + "    isAce: " + isAce);
		logger.info("Team1PassAttempts: " + team1.getPassAttempts() + "  Completes: " + team1.getPassCompletes());
		logger.info("Team2PassAttempts: " + team2.getPassAttempts() + "  Completes: " + team2.getPassCompletes());
		logger.info("Team1ShotAttempts: " + team1.getShotAttempts() + "  Completes: " + team1.getShotCompletes());
		logger.info("Team2ShotAttempts: " + team2.getShotAttempts() + "  Completes: " + team2.getShotCompletes());
		logger.info("Team1ClearAttempts: " + team1.getClearAttempts() + "  Completes: " + team1.getClearCompletes());
		logger.info("Team2ClearAttempts: " + team2.getClearAttempts() + "  Completes: " + team2.getClearCompletes());
		logger.info("Team1TwoBarPassAttempts: " + team1.getTwoBarPassAttempts() + "  Completes: " + team1.getTwoBarPassCompletes());
		logger.info("Team2TwoBarPassAttempts: " + team2.getTwoBarPassAttempts() + "  Completes: " + team2.getTwoBarPassCompletes());
		logger.info("Team1ShotsOnGoal: " + team1.getShotsOnGoal());
		logger.info("Team2ShotsOnGoal: " + team2.getShotsOnGoal());
		logger.info("Team1Stuffs: " + team1.getStuffs() + "       Team2Stuffs: " + team2.getStuffs());
		logger.info("Team1Breaks: " + team1.getBreaks() + "       Team2Breaks: " + team2.getBreaks());
		logger.info("");
	}
	public void restoreState(byte[] serializedObject) {
		Stats tempStats = null;
		try {
			byte b[] = serializedObject;
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			tempStats = (Stats) si.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.toString());
		}
		this.setCodeHistory(tempStats.getCodeHistory());
		this.setPreviousCode(tempStats.getPreviousCode());
	}
}
