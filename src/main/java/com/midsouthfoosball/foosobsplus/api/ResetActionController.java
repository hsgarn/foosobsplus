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

import io.javalin.http.Context;

/**
 * Handles POST /api/reset - reset hotkey actions (names, scores, game counts,
 * match counts, time outs, reset warns, all).
 */
public class ResetActionController {
	private static final Logger logger = LoggerFactory.getLogger(ResetActionController.class);
	private static final List<String> VALID_ACTIONS = List.of(
			"names", "scores", "gameCounts", "matchCounts", "timeOuts", "resetWarns", "all");
	private final TeamService teamService;

	public ResetActionController(TeamService teamService) {
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

			String code = switch (action) {
				case "names"       -> "XPRN"; //$NON-NLS-1$
				case "scores"      -> "XPRS"; //$NON-NLS-1$
				case "gameCounts"  -> "XPRG"; //$NON-NLS-1$
				case "matchCounts" -> "XPRM"; //$NON-NLS-1$
				case "timeOuts"    -> "XPRTO"; //$NON-NLS-1$
				case "resetWarns"  -> "XPRR"; //$NON-NLS-1$
				case "all"         -> "XPRA"; //$NON-NLS-1$
				default -> throw new GameActionRequest.ValidationException("Invalid action: " + action);
			};

			CommandExecutor.Result result = CommandExecutor.processCode(code, null);
			if (result.success()) {
				ctx.status(200).json(new APIResponse(true, "Reset " + action + " processed", code));
				logger.info("API: Processed reset action '{}' (code {})", action, code);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message(), code));
				logger.warn("API: Reset action '{}' failed: {}", action, result.message());
			}

		} catch (GameActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Reset action validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing reset action", e);
		}
	}
}
