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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.commands.Command;
import com.midsouthfoosball.foosobsplus.commands.Memento;
import com.midsouthfoosball.foosobsplus.main.OBSInterface;

@DisplayName("Per-table session isolation")
class TableSessionTest {
	private final List<TableSession> sessionsToStop = new ArrayList<>();

	@AfterEach
	void stopSessionTimers() {
		for (TableSession session : sessionsToStop) {
			session.getGameClock().getTimer().stop();
		}
	}

	@Test
	@DisplayName("sessions own independent model objects and table data")
	void sessionsHaveIndependentState() {
		TableSession first = session(new OBSInterface());
		TableSession second = session(new OBSInterface());

		first.setTableName("Table 1");
		second.setTableName("Table 2");
		first.getTeam1().setScore(4);
		first.getTeam2().setGameCount(2);
		first.getMatch().setLastScored(1);

		assertEquals("Table 1", first.getTableName());
		assertEquals("Table 2", second.getTableName());
		assertEquals(4, first.getTeam1().getScore());
		assertEquals(0, second.getTeam1().getScore());
		assertEquals(2, first.getTeam2().getGameCount());
		assertEquals(0, second.getTeam2().getGameCount());
		assertEquals(1, first.getMatch().getLastScored());
		assertEquals(0, second.getMatch().getLastScored());

		assertNotSame(first.getTeam1(), second.getTeam1());
		assertNotSame(first.getMatch(), second.getMatch());
		assertNotSame(first.getStats(), second.getStats());
		assertNotSame(first.getGameClock(), second.getGameClock());
	}

	@Test
	@DisplayName("working state round-trips and replaces destination lists")
	void workingStateRoundTrips() {
		TableSession session = session(new OBSInterface());
		Command savedCommand = new TestCommand("saved");
		Memento savedMemento = new Memento("saved state");

		session.saveWorkingState(
			new ArrayList<>(List.of(savedCommand)),
			new ArrayList<>(List.of("XPS1")),
			new ArrayList<>(List.of(savedMemento)),
			new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
			4);

		List<Command> commands = new ArrayList<>(List.of(new TestCommand("stale")));
		List<String> codes = new ArrayList<>(List.of("STALE"));
		List<Memento> team1Mementos = new ArrayList<>();
		List<Memento> team2Mementos = new ArrayList<>();
		List<Memento> team3Mementos = new ArrayList<>();
		List<Memento> statsMementos = new ArrayList<>();
		List<Memento> matchMementos = new ArrayList<>();
		List<Memento> clockMementos = new ArrayList<>();

		int pointer = session.loadWorkingStateInto(commands, codes, team1Mementos,
			team2Mementos, team3Mementos, statsMementos, matchMementos, clockMementos);

		assertEquals(4, pointer);
		assertEquals(1, commands.size());
		assertSame(savedCommand, commands.get(0));
		assertEquals(List.of("XPS1"), codes);
		assertEquals(1, team1Mementos.size());
		assertSame(savedMemento, team1Mementos.get(0));
		assertEquals(0, team2Mementos.size());
	}

	@Test
	@DisplayName("separate sessions retain separate undo histories")
	void sessionsRetainIndependentWorkingState() {
		TableSession first = session(new OBSInterface());
		TableSession second = session(new OBSInterface());
		first.saveWorkingState(new ArrayList<>(), new ArrayList<>(List.of("FIRST")),
			new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
			new ArrayList<>(), new ArrayList<>(), 1);
		second.saveWorkingState(new ArrayList<>(), new ArrayList<>(List.of("SECOND")),
			new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
			new ArrayList<>(), new ArrayList<>(), 7);

		List<String> loadedCodes = new ArrayList<>();
		assertEquals(1, loadCodes(first, loadedCodes));
		assertEquals(List.of("FIRST"), loadedCodes);
		assertEquals(7, loadCodes(second, loadedCodes));
		assertEquals(List.of("SECOND"), loadedCodes);
	}

	@Test
	@DisplayName("setObsInterface redirects subsequent model writes")
	void setObsInterfaceRedirectsWrites() {
		RecordingOBSInterface original = new RecordingOBSInterface();
		RecordingOBSInterface replacement = new RecordingOBSInterface();
		TableSession session = session(original);

		session.getTeam1().setScore(1);
		int originalWrites = original.writeCount;
		session.setObsInterface(replacement);
		session.getTeam1().setScore(2);

		assertEquals(originalWrites, original.writeCount);
		assertEquals(1, replacement.writeCount);
	}

	private TableSession session(OBSInterface obs) {
		TableSession session = new TableSession(obs, "Yellow", "Black", "Red");
		sessionsToStop.add(session);
		return session;
	}

	private static int loadCodes(TableSession session, List<String> codes) {
		return session.loadWorkingStateInto(new ArrayList<>(), codes, new ArrayList<>(),
			new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	private record TestCommand(String code) implements Command {
		@Override public void execute() {}
		@Override public String getCode() { return code; }
	}

	private static class RecordingOBSInterface extends OBSInterface {
		private int writeCount;

		@Override
		public void writeData(String source, String data, String className, Boolean showParsed) {
			writeCount++;
		}
	}
}
