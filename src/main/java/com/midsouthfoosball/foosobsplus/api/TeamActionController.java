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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

import io.javalin.http.Context;

/**
 * Handles POST /api/team - team hotkey actions (score, game count, match
 * count, time out, reset, warn, king seat, switch positions).
 */
public class TeamActionController {
	private static final Logger logger = LoggerFactory.getLogger(TeamActionController.class);
	private final TeamService teamService;

	public TeamActionController(TeamService teamService) {
		this.teamService = teamService;
	}

	public void handle(Context ctx) {
		try {
			TeamActionRequest request = ctx.bodyAsClass(TeamActionRequest.class);

			// Validate request
			String action = request.validate();

			// Validate table number matches current table
			try {
				teamService.validateTableNumber(request.tableNumber());
			} catch (TeamService.ValidationException e) {
				ctx.status(400).json(new APIResponse(false, e.getMessage()));
				logger.warn("API: Table number validation failed: {}", e.getMessage());
				return;
			}

			int teamNumber = request.teamNumber();
			String code;
			Runnable postCodeAction = null;
			switch (action) {
				case "scorePlus"       -> code = "XIST" + teamNumber; //$NON-NLS-1$
				case "scoreMinus"      -> code = "XDST" + teamNumber; //$NON-NLS-1$
				case "gameCountPlus"   -> code = "XIGT" + teamNumber; //$NON-NLS-1$
				case "gameCountMinus"  -> code = "XDGT" + teamNumber; //$NON-NLS-1$
				case "matchCountPlus"  -> code = "XIMT" + teamNumber; //$NON-NLS-1$
				case "matchCountMinus" -> code = "XDMT" + teamNumber; //$NON-NLS-1$
				case "timeOutPlus"     -> code = "XUTT" + teamNumber; //$NON-NLS-1$
				case "timeOutMinus"    -> code = "XRTT" + teamNumber; //$NON-NLS-1$
				case "reset" -> {
					code = "XPRT" + teamNumber; //$NON-NLS-1$
					postCodeAction = () -> Main.activateTeamFilterForAPI("Reset", teamNumber); //$NON-NLS-1$
				}
				case "warn" -> {
					code = "XPWT" + teamNumber; //$NON-NLS-1$
					postCodeAction = () -> Main.activateTeamFilterForAPI("Warn", teamNumber); //$NON-NLS-1$
				}
				case "kingSeat" -> {
					code = "XPKT" + teamNumber; //$NON-NLS-1$
					postCodeAction = () -> Main.activateTeamFilterForAPI("KingSeat", teamNumber); //$NON-NLS-1$
				}
				case "switchPositions" -> {
					code = "XXPT" + teamNumber; //$NON-NLS-1$
					postCodeAction = () -> Main.activateTeamFilterForAPI("SwitchPositions", teamNumber); //$NON-NLS-1$
				}
				default -> throw new TeamActionRequest.ValidationException("Invalid action: " + action);
			}

			CommandExecutor.Result result = CommandExecutor.processCode(code, postCodeAction);
			if (result.success()) {
				ctx.status(200).json(new APIResponse(true, "Team " + teamNumber + " " + action + " processed", code));
				logger.info("API: Processed team action '{}' for team {} (code {})", action, teamNumber, code);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message(), code));
				logger.warn("API: Team action '{}' for team {} failed: {}", action, teamNumber, result.message());
			}

		} catch (TeamActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Team action validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing team action", e);
		}
	}
}
