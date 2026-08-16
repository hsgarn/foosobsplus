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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.commands.Memento;
import com.midsouthfoosball.foosobsplus.main.OBSInterface;

@DisplayName("Team model behavior")
class TeamTest {
	private String originalShowTimeoutsUsed;
	private String originalMaxTimeouts;
	private Team team;

	@BeforeEach
	void setUp() {
		originalShowTimeoutsUsed = Settings.getControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED);
		originalMaxTimeouts = Settings.getControlParameter(SettingsKeys.CTRL_MAX_TIME_OUTS);
		Settings.setControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED, "0");
		Settings.setControlParameter(SettingsKeys.CTRL_MAX_TIME_OUTS, "2");
		OBSInterface obs = new OBSInterface();
		obs.setActive(false);
		team = new Team(obs, 1, "Yellow");
	}

	@AfterEach
	void tearDown() {
		Settings.setControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED, originalShowTimeoutsUsed);
		Settings.setControlParameter(SettingsKeys.CTRL_MAX_TIME_OUTS, originalMaxTimeouts);
	}

	@Test
	@DisplayName("score, game, and match decrements stop at zero")
	void countersDoNotDecrementBelowZero() {
		assertEquals(0, team.decrementScore());
		assertEquals(0, team.decrementGameCount());
		assertEquals(0, team.decrementMatchCount());

		team.incrementScore();
		team.incrementGameCount();
		team.incrementMatchCount();
		assertEquals(0, team.decrementScore());
		assertEquals(0, team.decrementGameCount());
		assertEquals(0, team.decrementMatchCount());
	}

	@Test
	@DisplayName("remaining-timeout mode resets and caps restored timeouts")
	void remainingTimeoutModeHonorsConfiguredMaximum() {
		team.resetTimeOuts();
		assertEquals(2, team.getTimeOutCount());
		assertEquals(1, team.callTimeOut());
		assertEquals(0, team.callTimeOut());
		assertEquals(1, team.restoreTimeOut());
		assertEquals(2, team.restoreTimeOut());
		assertEquals(2, team.restoreTimeOut(), "restoring cannot exceed the configured maximum");
	}

	@Test
	@DisplayName("used-timeout mode starts at zero and cannot restore below zero")
	void usedTimeoutModeHonorsZeroBoundary() {
		Settings.setControlParameter(SettingsKeys.CTRL_SHOW_TIME_OUTS_USED, "1");
		team.resetTimeOuts();
		assertEquals(0, team.getTimeOutCount());
		assertEquals(1, team.callTimeOut());
		assertEquals(0, team.restoreTimeOut());
		assertEquals(0, team.restoreTimeOut());
	}

	@Test
	@DisplayName("switchPositions swaps forward and goalie")
	void switchPositionsSwapsNames() {
		team.setForwardName("Alice");
		team.setGoalieName("Bob");

		String[] switched = team.switchPositions();

		assertEquals("Bob", team.getForwardName());
		assertEquals("Alice", team.getGoalieName());
		assertEquals("Bob", switched[0]);
		assertEquals("Alice", switched[1]);
	}

	@Test
	@DisplayName("resetStats clears statistical fields without clearing identity or score")
	void resetStatsOnlyClearsStatistics() {
		team.setTeamName("Champions");
		team.setScore(4);
		team.setPassAttempts(8);
		team.setPassCompletes(5);
		team.setPassPercent(62.5f);
		team.setShotAttempts(3);
		team.setScoring(2);
		team.setAces(1);

		team.resetStats();

		assertEquals("Champions", team.getTeamName());
		assertEquals(4, team.getScore());
		assertEquals(0, team.getPassAttempts());
		assertEquals(0, team.getPassCompletes());
		assertEquals(0f, team.getPassPercent());
		assertEquals(0, team.getShotAttempts());
		assertEquals(0, team.getScoring());
		assertEquals(0, team.getAces());
	}

	@Test
	@DisplayName("clearAll clears identity, counters, flags, and statistics")
	void clearAllResetsCompleteTeamState() {
		team.setTeamName("Team A");
		team.setForwardName("Alice");
		team.setGoalieName("Bob");
		team.setScore(4);
		team.setGameCount(2);
		team.setMatchCount(1);
		team.setReset(true);
		team.setWarn(true);
		team.setKingSeat(true);
		team.setShotsOnGoal(7);

		team.clearAll();

		assertEquals("", team.getTeamName());
		assertEquals("", team.getForwardName());
		assertEquals("", team.getGoalieName());
		assertEquals(0, team.getScore());
		assertEquals(0, team.getGameCount());
		assertEquals(0, team.getMatchCount());
		assertEquals(2, team.getTimeOutCount());
		assertFalse(team.getReset());
		assertFalse(team.getWarn());
		assertFalse(team.getKingSeat());
		assertEquals(0, team.getShotsOnGoal());
	}

	@Test
	@DisplayName("string setters accept blanks and formatted percentages")
	void stringSettersParsePersistedValues() {
		team.setScore("");
		team.setGameCount("3");
		team.setMatchCount("2");
		team.setPassAttempts("8");
		team.setPassPercent("62.5%");

		assertEquals(0, team.getScore());
		assertEquals(3, team.getGameCount());
		assertEquals(2, team.getMatchCount());
		assertEquals(8, team.getPassAttempts());
		assertEquals(62.5f, team.getPassPercent());
	}

	@Test
	@DisplayName("Memento restoration round-trips all representative team state")
	void mementoRestoresTeamState() {
		team.setTeamName("Original Team");
		team.setForwardName("Alice");
		team.setGoalieName("Bob");
		team.setScore(4);
		team.setGameCount(2);
		team.setMatchCount(1);
		team.setTimeOutCount(1);
		team.setReset(true);
		team.setWarn(true);
		team.setKingSeat(true);
		team.setPassAttempts(9);
		team.setScoring(3);
		Memento saved = new Memento(team);

		team.clearAll();
		team.restoreState(saved.getState());

		assertEquals("Original Team", team.getTeamName());
		assertEquals("Alice", team.getForwardName());
		assertEquals("Bob", team.getGoalieName());
		assertEquals(4, team.getScore());
		assertEquals(2, team.getGameCount());
		assertEquals(1, team.getMatchCount());
		assertEquals(1, team.getTimeOutCount());
		assertTrue(team.getReset());
		assertTrue(team.getWarn());
		assertTrue(team.getKingSeat());
		assertEquals(9, team.getPassAttempts());
		assertEquals(3, team.getScoring());
	}
}
