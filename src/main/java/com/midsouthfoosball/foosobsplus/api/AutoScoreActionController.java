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
 * Handles POST /api/autoscore - AutoScore connect/disconnect for the
 * currently displayed table, matching the AutoScore panel buttons.
 */
public class AutoScoreActionController {
	private static final Logger logger = LoggerFactory.getLogger(AutoScoreActionController.class);

	public void handle(Context ctx) {
		try {
			AutoScoreActionRequest request = ctx.bodyAsClass(AutoScoreActionRequest.class);

			// Validate request
			String action = request.validate();

			CommandExecutor.Result result;
			if (action.equals("connect")) { //$NON-NLS-1$
				result = CommandExecutor.runOnEDT(Main::autoScoreConnectFromAPI);
			} else {
				result = CommandExecutor.runOnEDT(Main::autoScoreDisconnectFromAPI);
			}

			if (result.success()) {
				// connect only initiates the connection; it completes asynchronously
				String message = action.equals("connect") ? "AutoScore connection initiated" : "AutoScore disconnected";
				ctx.status(200).json(new APIResponse(true, message));
				logger.info("API: Processed AutoScore action '{}'", action);
			} else {
				ctx.status(result.httpStatus()).json(new APIResponse(false, result.message()));
				logger.warn("API: AutoScore action '{}' failed: {}", action, result.message());
			}

		} catch (AutoScoreActionRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: AutoScore action validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error processing AutoScore action", e);
		}
	}
}
