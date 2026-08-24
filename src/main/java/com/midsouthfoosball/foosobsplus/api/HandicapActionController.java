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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

import io.javalin.http.Context;

/**
 * Handles POST /api/handicap - handicapped Points to Win control (toggle
 * Handicapped, increase/decrease a team's handicapped Points to Win, swap it
 * between two teams). These are control parameters shared across every table,
 * the same as Max Win/Win By, so there is no per-table scoping here.
 */
public class HandicapActionController {
	private static final Logger logger = LoggerFactory.getLogger(HandicapActionController.class);

	public void handle(Context ctx) {
		try {
			HandicapActionRequest request = ctx.bodyAsClass(HandicapActionRequest.class);
			String action = request.validate();

			switch (action) {
				case "toggle" -> {
					Boolean state = request.state();
					boolean applied = CommandExecutor.callOnEDT(() -> Main.toggleHandicappedFromAPI(state));
					ctx.status(200).json(new APIResponse(true, "Handicapped set to " + applied,
							Map.of("action", action, "handicapped", applied)));
					logger.info("API: Processed handicap toggle -> {}", applied);
				}
				case "increase", "decrease" -> {
					int teamNumber = request.teamNumber();
					int delta = action.equals("increase") ? 1 : -1;
					int newValue = CommandExecutor.callOnEDT(() -> Main.adjustHandicappedPointsToWinFromAPI(teamNumber, delta));
					ctx.status(200).json(new APIResponse(true, "Team " + teamNumber + " handicapped Points to Win set to " + newValue,
							Map.of("action", action, "teamNumber", teamNumber, "pointsToWin", newValue)));
					logger.info("API: Processed handicap {} for team {} -> {}", action, teamNumber, newValue);
				}
				case "swap" -> {
					int teamNumber = request.teamNumber();
					int teamNumber2 = request.teamNumber2();
					CommandExecutor.Result result = CommandExecutor.runOnEDT(
							() -> Main.swapHandicappedPointsToWinFromAPI(teamNumber, teamNumber2));
					if (result.success()) {
						ctx.status(200).json(new APIResponse(true,
								"Swapped handicapped Points to Win between team " + teamNumber + " and team " + teamNumber2,
								Map.of("action", action, "teamNumber", teamNumber, "teamNumber2", teamNumber2)));
						logger.info("API: Processed handicap swap between team {} and team {}", teamNumber, teamNumber2);
					} else {
						ctx.status(result.httpStatus()).json(new APIResponse(false, result.message()));
						logger.warn("API: Handicap swap failed: {}", result.message());
					}
				}
				default -> throw new HandicapActionRequest.ValidationException("Invalid action: " + action);
			}

		} catch (HandicapActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Handicap action validation failed: {}", e.getMessage());

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			ctx.status(500).json(new APIResponse(false, "Handicap action interrupted"));
			logger.error("API: Handicap action interrupted", e);

		} catch (InvocationTargetException e) {
			ctx.status(500).json(new APIResponse(false, "Error processing handicap action"));
			logger.error("API: Error processing handicap action on EDT", e);

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing handicap action", e);
		}
	}
}
