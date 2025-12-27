/**
Copyright © 2025-2026 Hugh Garner
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

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

import io.javalin.http.Context;

public class FoosballCodeController {
	private static final Logger logger = LoggerFactory.getLogger(FoosballCodeController.class);
	private final TeamService teamService;

	public FoosballCodeController(TeamService teamService) {
		this.teamService = teamService;
	}

	public void submitCode(Context ctx) {
		try {
			FoosballCodeRequest request = ctx.bodyAsClass(FoosballCodeRequest.class);

			// Validate request
			request.validate();

			// Validate table number matches current table
			try {
				teamService.updatePlayerNames(createEmptyPlayerRequest(request.getTableNumber()));
			} catch (TeamService.ValidationException e) {
				ctx.status(400).json(new APIResponse(false, e.getMessage()));
				logger.warn("API: Table number validation failed: {}", e.getMessage());
				return;
			}

			// Uppercase the code before processing
			String code = request.getCode().toUpperCase();

			// Process code on EDT (Swing Event Dispatch Thread) and wait for result
			try {
				SwingUtilities.invokeAndWait(() -> {
					Main.processCode(code, false);
				});
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				ctx.status(500).json(new APIResponse(false, "Code processing interrupted"));
				logger.error("API: Code processing interrupted", e);
				return;
			} catch (java.lang.reflect.InvocationTargetException e) {
				ctx.status(500).json(new APIResponse(false, "Error processing code"));
				logger.error("API: Error processing code on EDT", e);
				return;
			}

			// Check if the code was valid
			if (Main.getLastCodeError()) {
				String errorMsg = Main.getLastCodeErrorMsg();
				ctx.status(400).json(new APIResponse(false, "Invalid code: " + errorMsg, code));
				logger.warn("API: Invalid code '{}': {}", code, errorMsg);
			} else {
				ctx.status(200).json(new APIResponse(true, "Code submitted successfully", code));
				logger.info("API: Successfully processed code '{}' for table {}", code, request.getTableNumber());
			}

		} catch (FoosballCodeRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Code validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error submitting code", e);
		}
	}

	/**
	 * Creates an empty PlayerNamesRequest just to validate table number
	 */
	private PlayerNamesRequest createEmptyPlayerRequest(int tableNumber) {
		PlayerNamesRequest request = new PlayerNamesRequest();
		request.setTableNumber(tableNumber);
		return request;
	}
}
