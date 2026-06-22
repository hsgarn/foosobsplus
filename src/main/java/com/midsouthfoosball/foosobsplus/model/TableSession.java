/**
Copyright © 2020-2026 Hugh Garner
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

import java.util.ArrayList;
import java.util.List;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.commands.Memento;
import com.midsouthfoosball.foosobsplus.main.OBSInterface;

/**
 * Holds all per-table game state for a single foosball table.
 *
 * <p>Each physical table (with its own AutoScore system) gets its own
 * {@code TableSession}: the two/three teams, the match, the stats, and the
 * clocks. Exactly one session is "active" (bound to the view panels and the
 * live OBS output) at a time; the remaining sessions run headless, continuing
 * to track scores, games, timeouts, and elapsed time in the background.
 *
 * <p>This class is intentionally a plain container of the existing model
 * objects so that the rest of the application can be migrated to it
 * incrementally without changing behavior. Wiring to the view and to OBS still
 * lives outside this class for now.
 */
public class TableSession {

	private final Team team1;
	private final Team team2;
	private final Team team3;
	private final Match match;
	private final Stats stats;
	private final TimeClock timeClock;
	private final GameClock gameClock;
	private final LastScoredClock lastScored1Clock;
	private final LastScoredClock lastScored2Clock;
	private final LastScoredClock lastScored3Clock;

	// Per-table display name (shown in the Tournament Information panel and written
	// to OBS for the active table). Tournament and event names stay global on the
	// shared Tournament object; only the table name is per-session.
	private String tableName = "";

	// Per-table command/undo working state. While this session is the active
	// (displayed) table, these mirror the live stacks in Main; on switch-away
	// they are saved here and the incoming session's are loaded into Main. This
	// gives each table its own command history and undo/redo, independent of the
	// other tables.
	private final List<Command> commandStack = new ArrayList<>();
	private final List<String> codeStack = new ArrayList<>();
	private final List<Memento> mementoStackTeam1 = new ArrayList<>();
	private final List<Memento> mementoStackTeam2 = new ArrayList<>();
	private final List<Memento> mementoStackTeam3 = new ArrayList<>();
	private final List<Memento> mementoStackStats = new ArrayList<>();
	private final List<Memento> mementoStackMatch = new ArrayList<>();
	private final List<Memento> mementoStackGameClock = new ArrayList<>();
	private int undoRedoPointer = -1;

	/**
	 * Creates a session with its own set of model objects.
	 *
	 * @param obsInterface the OBS interface the models write through. For the
	 *                     active session this points at the live datapath; for
	 *                     background sessions it will be a silent sink.
	 * @param side1Color   color label for team 1
	 * @param side2Color   color label for team 2
	 * @param side3Color   color label for team 3 (cutthroat)
	 */
	public TableSession(OBSInterface obsInterface, String side1Color, String side2Color, String side3Color) {
		this.team1 = new Team(obsInterface, 1, side1Color);
		this.team2 = new Team(obsInterface, 2, side2Color);
		this.team3 = new Team(obsInterface, 3, side3Color);
		this.match = new Match(obsInterface, team1, team2, team3);
		this.stats = new Stats(team1, team2);
		this.timeClock = new TimeClock(obsInterface);
		this.gameClock = new GameClock(obsInterface);
		this.lastScored1Clock = new LastScoredClock();
		this.lastScored2Clock = new LastScoredClock();
		this.lastScored3Clock = new LastScoredClock();
	}

	/**
	 * Repoints every model in this session at the given OBS interface. Used when
	 * switching the active table: the incoming session is pointed at the live
	 * (active) interface and the outgoing session at a silent one, so only the
	 * displayed table writes to OBS. {@link LastScoredClock} has no OBS output
	 * and is unaffected.
	 */
	public void setObsInterface(OBSInterface obsInterface) {
		team1.setObsInterface(obsInterface);
		team2.setObsInterface(obsInterface);
		team3.setObsInterface(obsInterface);
		match.setObsInterface(obsInterface);
		timeClock.setObsInterface(obsInterface);
		gameClock.setObsInterface(obsInterface);
	}

	/**
	 * Saves the given live command/undo working state into this session. Called
	 * when switching away from this table so its history/undo state is preserved.
	 */
	public void saveWorkingState(List<Command> commandStack, List<String> codeStack,
			List<Memento> mementoStackTeam1, List<Memento> mementoStackTeam2, List<Memento> mementoStackTeam3,
			List<Memento> mementoStackStats, List<Memento> mementoStackMatch, List<Memento> mementoStackGameClock,
			int undoRedoPointer) {
		copy(commandStack, this.commandStack);
		copy(codeStack, this.codeStack);
		copy(mementoStackTeam1, this.mementoStackTeam1);
		copy(mementoStackTeam2, this.mementoStackTeam2);
		copy(mementoStackTeam3, this.mementoStackTeam3);
		copy(mementoStackStats, this.mementoStackStats);
		copy(mementoStackMatch, this.mementoStackMatch);
		copy(mementoStackGameClock, this.mementoStackGameClock);
		this.undoRedoPointer = undoRedoPointer;
	}

	/**
	 * Loads this session's command/undo working state into the given live lists.
	 * Called when switching to this table. Returns the undo/redo pointer to
	 * restore.
	 */
	public int loadWorkingStateInto(List<Command> commandStack, List<String> codeStack,
			List<Memento> mementoStackTeam1, List<Memento> mementoStackTeam2, List<Memento> mementoStackTeam3,
			List<Memento> mementoStackStats, List<Memento> mementoStackMatch, List<Memento> mementoStackGameClock) {
		copy(this.commandStack, commandStack);
		copy(this.codeStack, codeStack);
		copy(this.mementoStackTeam1, mementoStackTeam1);
		copy(this.mementoStackTeam2, mementoStackTeam2);
		copy(this.mementoStackTeam3, mementoStackTeam3);
		copy(this.mementoStackStats, mementoStackStats);
		copy(this.mementoStackMatch, mementoStackMatch);
		copy(this.mementoStackGameClock, mementoStackGameClock);
		return this.undoRedoPointer;
	}

	private static <T> void copy(List<T> from, List<T> to) {
		to.clear();
		to.addAll(from);
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public Team getTeam3() {
		return team3;
	}

	public Match getMatch() {
		return match;
	}

	public Stats getStats() {
		return stats;
	}

	public TimeClock getTimeClock() {
		return timeClock;
	}

	public GameClock getGameClock() {
		return gameClock;
	}

	public LastScoredClock getLastScored1Clock() {
		return lastScored1Clock;
	}

	public LastScoredClock getLastScored2Clock() {
		return lastScored2Clock;
	}

	public LastScoredClock getLastScored3Clock() {
		return lastScored3Clock;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
