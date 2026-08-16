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
package com.midsouthfoosball.foosobsplus.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.midsouthfoosball.foosobsplus.api.PlayerNamesRequest.TeamPlayers;

@DisplayName("REST API request validation")
class ApiRequestValidationTest {
	@ParameterizedTest
	@ValueSource(strings = {"connect", "CONNECT", "Disconnect"})
	void autoScoreAcceptsSupportedActionsCaseInsensitively(String action) throws Exception {
		assertEquals(action.equalsIgnoreCase("connect") ? "connect" : "disconnect",
			new AutoScoreActionRequest(action).validate());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {" ", "restart"})
	void autoScoreRejectsMissingOrUnsupportedActions(String action) {
		assertThrows(AutoScoreActionRequest.ValidationException.class,
			() -> new AutoScoreActionRequest(action).validate());
	}

	@Test
	void gameActionReturnsCanonicalActionAndAllowsMissingTable() throws Exception {
		GameActionRequest request = new GameActionRequest(null, "STARTGAME");
		assertEquals("startGame", request.validate(List.of("start", "startGame", "end")));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0})
	void gameActionRejectsNonpositiveTables(int tableNumber) {
		assertThrows(GameActionRequest.ValidationException.class,
			() -> new GameActionRequest(tableNumber, "start").validate(List.of("start")));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {" ", "invalid"})
	void gameActionRejectsMissingOrUnsupportedActions(String action) {
		assertThrows(GameActionRequest.ValidationException.class,
			() -> new GameActionRequest(1, action).validate(List.of("start", "end")));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	void teamActionAcceptsAllTeamNumbers(int teamNumber) throws Exception {
		assertEquals("scorePlus", new TeamActionRequest(null, teamNumber, "SCOREPLUS").validate());
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 4})
	void teamActionRejectsOutOfRangeTeamNumbers(int teamNumber) {
		assertThrows(TeamActionRequest.ValidationException.class,
			() -> new TeamActionRequest(1, teamNumber, "scorePlus").validate());
	}

	@Test
	void teamActionRejectsInvalidTableAndAction() {
		assertThrows(TeamActionRequest.ValidationException.class,
			() -> new TeamActionRequest(0, 1, "scorePlus").validate());
		assertThrows(TeamActionRequest.ValidationException.class,
			() -> new TeamActionRequest(1, 1, "dance").validate());
	}

	@ParameterizedTest
	@ValueSource(strings = {"shot", "PASS", "Timeout", "game", "recall", "reset"})
	void timerAcceptsEverySupportedType(String timerType) {
		assertDoesNotThrow(() -> new TimerControlRequest(timerType, null).validate());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {" ", "match", "stop"})
	void timerRejectsMissingOrUnsupportedTypes(String timerType) {
		assertThrows(TimerControlRequest.ValidationException.class,
			() -> new TimerControlRequest(timerType, 1).validate());
	}

	@Test
	void timerRejectsNonpositiveTableNumber() {
		assertThrows(TimerControlRequest.ValidationException.class,
			() -> new TimerControlRequest("shot", 0).validate());
	}

	@Test
	void foosballCodeAcceptsOptionalOrPositiveTableNumber() {
		assertDoesNotThrow(() -> new FoosballCodeRequest("YG3", null).validate());
		assertDoesNotThrow(() -> new FoosballCodeRequest("YG3", 2).validate());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {" "})
	void foosballCodeRejectsMissingCode(String code) {
		assertThrows(FoosballCodeRequest.ValidationException.class,
			() -> new FoosballCodeRequest(code, 1).validate());
	}

	@Test
	void foosballCodeRejectsOversizedControlCharacterAndInvalidTableInputs() {
		assertThrows(FoosballCodeRequest.ValidationException.class,
			() -> new FoosballCodeRequest("123456789012345678901", 1).validate());
		assertThrows(FoosballCodeRequest.ValidationException.class,
			() -> new FoosballCodeRequest("YG\u0001", 1).validate());
		assertThrows(FoosballCodeRequest.ValidationException.class,
			() -> new FoosballCodeRequest("YG3", 0).validate());
	}

	@ParameterizedTest
	@ValueSource(strings = {"connect", "DISCONNECT", "push", "Pull"})
	void obsAcceptsCommandsWithoutState(String action) throws Exception {
		OBSActionRequest request = new OBSActionRequest(action, null);
		String canonical = request.validate();
		assertFalse(request.isToggleAction(canonical));
	}

	@ParameterizedTest
	@ValueSource(strings = {"showScores", "SHOWTIMER", "showCutthroat", "showSkunk", "startStream"})
	void obsAcceptsToggleActionsWithExplicitState(String action) throws Exception {
		OBSActionRequest request = new OBSActionRequest(action, true);
		String canonical = request.validate();
		assertTrue(request.isToggleAction(canonical));
	}

	@Test
	void obsRejectsStateForCommandsAndUnknownActions() {
		assertThrows(OBSActionRequest.ValidationException.class,
			() -> new OBSActionRequest("connect", true).validate());
		assertThrows(OBSActionRequest.ValidationException.class,
			() -> new OBSActionRequest("unknown", null).validate());
	}

	@Test
	void tableSelectionDefaultsToSelectAndRequiresTableNumber() throws Exception {
		TableSelectRequest valid = new TableSelectRequest(null, 2);
		assertEquals("select", valid.actionOrDefault());
		valid.validate();

		assertThrows(TableSelectRequest.ValidationException.class,
			() -> new TableSelectRequest(null, null).validate());
		assertThrows(TableSelectRequest.ValidationException.class,
			() -> new TableSelectRequest("select", 0).validate());
	}

	@Test
	void nextTableDoesNotRequireTableNumberAndActionsNormalizeCase() {
		TableSelectRequest next = new TableSelectRequest("NEXT", null);
		assertEquals("next", next.actionOrDefault());
		assertDoesNotThrow(next::validate);
		assertThrows(TableSelectRequest.ValidationException.class,
			() -> new TableSelectRequest("previous", 1).validate());
	}

	@Test
	void playerNamesAllowNullAndNormalNames() {
		assertDoesNotThrow(() -> new PlayerNamesRequest(null, null, null).validate());
		assertDoesNotThrow(() -> new PlayerNamesRequest(1,
			new TeamPlayers("Alice", "Bob"), new TeamPlayers(null, "Dana")).validate());
	}

	@Test
	void playerNamesRejectOversizedAndControlCharacterNames() {
		String oversized = "x".repeat(101);
		assertThrows(PlayerNamesRequest.ValidationException.class,
			() -> new PlayerNamesRequest(1, new TeamPlayers(oversized, "Bob"), null).validate());
		assertThrows(PlayerNamesRequest.ValidationException.class,
			() -> new PlayerNamesRequest(1, null, new TeamPlayers("Dana", "Bad\u0001Name")).validate());
	}
}
