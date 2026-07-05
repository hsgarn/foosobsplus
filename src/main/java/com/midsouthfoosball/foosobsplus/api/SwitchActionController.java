/**
Copyright © 2026 Hugh Garner
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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

import io.javalin.http.Context;

/**
 * Handles POST /api/switch - switch hotkey actions (sides, teams, scores,
 * game counts, match counts, time outs, reset warns, forwards, goalies).
 */
public class SwitchActionController {
	private static final Logger logger = LoggerFactory.getLogger(SwitchActionController.class);
	private static final List<String> VALID_ACTIONS = List.of(
			"sides", "teams", "scores", "gameCounts", "matchCounts",
			"timeOuts", "resetWarns", "forwards", "goalies");
	private final TeamService teamService;

	public SwitchActionController(TeamService teamService) {
		this.teamService = teamService;
	}

	public void handle(Context ctx) {
		try {
			GameActionRequest request = ctx.bodyAsClass(GameActionRequest.class);

			// Validate request
			String action = request.validate(VALID_ACTIONS);

			// Validate table number matches current table
			try {
				teamService.validateTableNumber(request.tableNumber());
			} catch (TeamService.ValidationException e) {
				ctx.status(400).json(new APIResponse(false, e.getMessage()));
				logger.warn("API: Table number validation failed: {}", e.getMessage());
				return;
			}

			String code;
			Runnable postCodeAction = null;
			switch (action) {
				case "sides" -> {
					code = "XPSS"; //$NON-NLS-1$
					postCodeAction = () -> Main.activateFilter("SwitchSides"); //$NON-NLS-1$
				}
				case "teams"       -> code = "XPST"; //$NON-NLS-1$
				case "scores"      -> code = "XPSSC"; //$NON-NLS-1$
				case "gameCounts"  -> code = "XPSGC"; //$NON-NLS-1$
				case "matchCounts" -> code = "XPSMC"; //$NON-NLS-1$
				case "timeOuts"    -> code = "XPSTO"; //$NON-NLS-1$
				case "resetWarns"  -> code = "XPSR"; //$NON-NLS-1$
				case "forwards"    -> code = "XXP1"; //$NON-NLS-1$
				case "goalies"     -> code = "XXP2"; //$NON-NLS-1$
				default -> throw new GameActionRequest.ValidationException("Invalid action: " + action);
			}

			CommandExecutor.Result result = CommandExecutor.processCode(code, postCodeAction);
			if (result.success()) {
				ctx.status(200).json(new APIResponse(true, "Switch " + action + " processed", code));
				logger.info("API: Processed switch action '{}' (code {})", action, code);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message(), code));
				logger.warn("API: Switch action '{}' failed: {}", action, result.message());
			}

		} catch (GameActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Switch action validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing switch action", e);
		}
	}
}
