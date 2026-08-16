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

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

@DisplayName("Match scoring and lifecycle")
class MatchTest {
	private static final String[] TEST_SETTING_KEYS = {
		SettingsKeys.CTRL_POINTS_TO_WIN,
		SettingsKeys.CTRL_MAX_WIN,
		SettingsKeys.CTRL_WIN_BY,
		SettingsKeys.CTRL_WIN_BY_FINAL_ONLY,
		SettingsKeys.CTRL_GAMES_TO_WIN,
		SettingsKeys.CTRL_AUTO_INCREMENT_GAME,
		SettingsKeys.CTRL_RACK_MODE,
		SettingsKeys.CTRL_BALLS_IN_RACK,
		SettingsKeys.CTRL_CUT_THROAT_MODE,
		SettingsKeys.CTRL_ANNOUNCE_MEATBALL,
		SettingsKeys.CTRL_ANNOUNCE_WINNER
	};

	private final Map<String, String> originalSettings = new HashMap<>();
	private OBSInterface obs;
	private Team team1;
	private Team team2;
	private Team team3;
	private Match match;

	@BeforeEach
	void setUp() {
		for (String key : TEST_SETTING_KEYS) {
			originalSettings.put(key, Settings.getControlParameter(key));
		}
		Settings.setControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN, "3");
		Settings.setControlParameter(SettingsKeys.CTRL_MAX_WIN, "5");
		Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY, "1");
		Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY_FINAL_ONLY, "0");
		Settings.setControlParameter(SettingsKeys.CTRL_GAMES_TO_WIN, "2");
		Settings.setControlParameter(SettingsKeys.CTRL_AUTO_INCREMENT_GAME, "1");
		Settings.setControlParameter(SettingsKeys.CTRL_RACK_MODE, "0");
		Settings.setControlParameter(SettingsKeys.CTRL_BALLS_IN_RACK, "9");
		Settings.setControlParameter(SettingsKeys.CTRL_CUT_THROAT_MODE, "0");
		Settings.setControlParameter(SettingsKeys.CTRL_ANNOUNCE_MEATBALL, "0");
		Settings.setControlParameter(SettingsKeys.CTRL_ANNOUNCE_WINNER, "0");

		obs = new OBSInterface();
		obs.setActive(false);
		team1 = new Team(obs, 1, "Yellow");
		team2 = new Team(obs, 2, "Black");
		team3 = new Team(obs, 3, "Red");
		match = new Match(obs, team1, team2, team3);
	}

	@AfterEach
	void tearDown() {
		originalSettings.forEach(Settings::setControlParameter);
	}

	@Test
	@DisplayName("a score below Points to Win does not end the game")
	void scoreBelowThresholdDoesNotWinGame() {
		assertEquals(0, match.incrementScore(1, "00:00:01"));
		assertEquals(0, match.incrementScore(1, "00:00:02"));

		assertEquals(2, team1.getScore());
		assertEquals(0, team1.getGameCount());
		assertFalse(match.getMatchWon());
	}

	@Test
	@DisplayName("reaching Points to Win with the required margin wins a game")
	void reachingThresholdWinsGame() {
		score(match, 1, 3);

		assertEquals(1, match.getWinState());
		assertEquals(1, team1.getGameCount());
		assertEquals(1, match.getGameWinners()[0]);
		assertEquals("00:00:03", match.getTimes()[0]);
		assertFalse(match.getMatchWon());
	}

	@Test
	@DisplayName("Win By prevents a one-point win and permits the required margin")
	void winByRequiresConfiguredMargin() {
		Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY, "2");
		score(match, 1, 2);
		score(match, 2, 2);

		assertEquals(0, match.incrementScore(1, "00:00:05"));
		assertEquals(0, team1.getGameCount(), "3-2 is not a two-point margin");
		assertEquals(1, match.incrementScore(1, "00:00:06"));
		assertEquals(1, team1.getGameCount(), "4-2 satisfies Win By 2");
	}

	@Test
	@DisplayName("Max Win ends a game even when Win By is not satisfied")
	void maxWinCapsExtendedGame() {
		Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY, "2");
		for (int i = 0; i < 4; i++) {
			match.incrementScore(1, "00:00:01");
			match.incrementScore(2, "00:00:02");
		}

		assertEquals(1, match.incrementScore(1, "00:00:09"));
		assertEquals(5, team1.getScore());
		assertEquals(1, team1.getGameCount());
	}

	@Test
	@DisplayName("winning the configured number of games wins the match")
	void winningEnoughGamesWinsMatch() {
		score(match, 1, 3);
		assertEquals(1, match.getWinState());
		match.increaseCurrentGameNumber();

		// The first point after a completed game resets the teams' per-game state.
		score(match, 1, 3);

		assertEquals(2, match.getWinState());
		assertTrue(match.getMatchWon());
		assertEquals(1, match.getMatchWinner());
		assertEquals(2, team1.getGameCount());
		assertEquals(2, match.getCurrentGameNumber());
	}

	@Test
	@DisplayName("startMatch clears stale match state and starts game one")
	void startMatchClearsPriorState() {
		team1.setScore(2);
		team1.setGameCount(1);
		match.setLastScored(1);
		match.setMatchPaused(true);
		match.setGamePaused(true);
		match.setCurrentGameNumber(3);

		match.startMatch("match-123");

		assertEquals("match-123", match.getMatchId());
		assertEquals(1, match.getCurrentGameNumber());
		assertEquals(0, match.getLastScored());
		assertEquals("0", match.getScoresTeam1()[0]);
		assertEquals(2, team1.getScore(), "team counters are reset by the controller, not Match.startMatch");
		assertFalse(match.isMatchPaused());
		assertFalse(match.isGamePaused());
		assertFalse(match.getMatchWon());
		assertEquals(0, match.getMatchWinner());
	}

	private static void score(Match match, int teamNumber, int times) {
		for (int i = 1; i <= times; i++) {
			match.incrementScore(teamNumber, String.format("00:00:%02d", i));
		}
	}
}
