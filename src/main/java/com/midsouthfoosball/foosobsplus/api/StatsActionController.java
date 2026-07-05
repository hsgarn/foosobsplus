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
 * Handles POST /api/stats - undo, redo, clearAll and clearTournament.
 * Note: undo/redo silently no-op at the undo/redo history boundaries and
 * still return success, matching the hotkey behavior.
 */
public class StatsActionController {
	private static final Logger logger = LoggerFactory.getLogger(StatsActionController.class);
	private static final List<String> VALID_ACTIONS = List.of("undo", "redo", "clearAll", "clearTournament");
	private final TeamService teamService;

	public StatsActionController(TeamService teamService) {
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

			CommandExecutor.Result result;
			String code = null;
			switch (action) {
				case "undo" -> result = CommandExecutor.runOnEDT(Main::undo);
				case "redo" -> result = CommandExecutor.runOnEDT(Main::redo);
				case "clearAll" -> {
					code = "XPCA"; //$NON-NLS-1$
					result = CommandExecutor.processCode(code, null);
				}
				case "clearTournament" -> {
					code = "XPTCA"; //$NON-NLS-1$
					result = CommandExecutor.processCode(code, null);
				}
				default -> throw new GameActionRequest.ValidationException("Invalid action: " + action);
			}

			if (result.success()) {
				ctx.status(200).json(new APIResponse(true, "Stats " + action + " processed", code));
				logger.info("API: Processed stats action '{}'", action);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message(), code));
				logger.warn("API: Stats action '{}' failed: {}", action, result.message());
			}

		} catch (GameActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Stats action validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing stats action", e);
		}
	}
}
