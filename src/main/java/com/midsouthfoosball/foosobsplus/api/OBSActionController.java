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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;
import com.midsouthfoosball.foosobsplus.model.OBS;

import io.javalin.http.Context;

/**
 * Handles POST /api/obs - OBS hotkey actions (connect, disconnect, push,
 * pull, showScores, showTimer, showCutthroat, showSkunk, startStream).
 * Toggle actions accept an optional boolean state: omitted toggles the
 * current state, provided sets it explicitly; the GUI checkboxes are synced.
 */
public class OBSActionController {
	private static final Logger logger = LoggerFactory.getLogger(OBSActionController.class);

	public void handle(Context ctx) {
		try {
			OBSActionRequest request = ctx.bodyAsClass(OBSActionRequest.class);

			// Validate request
			String action = request.validate();

			// Actions that require an active OBS connection (buttons silently
			// no-op when disconnected; the API is explicit about it)
			boolean requiresConnection = switch (action) {
				case "push", "pull", "showScores", "showTimer", "showCutthroat" -> true;
				default -> false;
			};
			// OBS.getConnected() is null until the first connection attempt
			if (requiresConnection && !Boolean.TRUE.equals(OBS.getConnected())) {
				ctx.status(409).json(new APIResponse(false, "OBS is not connected"));
				logger.warn("API: OBS action '{}' rejected: OBS is not connected", action);
				return;
			}

			if (request.isToggleAction(action)) {
				Boolean state = request.state();
				boolean applied = switch (action) {
					case "showScores"    -> CommandExecutor.callOnEDT(() -> Main.obsShowScoresFromAPI(state));
					case "showTimer"     -> CommandExecutor.callOnEDT(() -> Main.obsShowTimerFromAPI(state));
					case "showCutthroat" -> CommandExecutor.callOnEDT(() -> Main.obsShowCutthroatFromAPI(state));
					case "showSkunk"     -> CommandExecutor.callOnEDT(() -> Main.obsShowSkunkFromAPI(state));
					case "startStream"   -> CommandExecutor.callOnEDT(() -> Main.obsStartStreamFromAPI(state));
					default -> throw new OBSActionRequest.ValidationException("Invalid action: " + action);
				};
				Map<String, Object> data = Map.of("action", action, "state", applied);
				ctx.status(200).json(new APIResponse(true, "OBS " + action + " set to " + applied, data));
				logger.info("API: Processed OBS toggle '{}' -> {}", action, applied);
				return;
			}

			CommandExecutor.Result result = switch (action) {
				case "connect"    -> CommandExecutor.runOnEDT(Main::obsConnectFromAPI);
				case "disconnect" -> CommandExecutor.runOnEDT(Main::obsDisconnectFromAPI);
				case "push"       -> CommandExecutor.runOnEDT(Main::obsPushFromAPI);
				case "pull"       -> CommandExecutor.runOnEDT(Main::obsPullFromAPI);
				default -> throw new OBSActionRequest.ValidationException("Invalid action: " + action);
			};

			if (result.success()) {
				// connect only initiates the connection; it completes asynchronously
				String message = action.equals("connect") ? "OBS connection initiated" : "OBS " + action + " processed";
				ctx.status(200).json(new APIResponse(true, message));
				logger.info("API: Processed OBS action '{}'", action);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message()));
				logger.warn("API: OBS action '{}' failed: {}", action, result.message());
			}

		} catch (OBSActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: OBS action validation failed: {}", e.getMessage());

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			ctx.status(500).json(new APIResponse(false, "OBS action interrupted"));
			logger.error("API: OBS action interrupted", e);

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing OBS action", e);
		}
	}
}
