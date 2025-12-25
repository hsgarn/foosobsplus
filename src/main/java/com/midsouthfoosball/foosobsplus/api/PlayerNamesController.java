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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.http.Context;

public class PlayerNamesController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerNamesController.class);
	private final TeamService teamService;

	public PlayerNamesController(TeamService teamService) {
		this.teamService = teamService;
	}

	public void updatePlayerNames(Context ctx) {
		try {
			PlayerNamesRequest request = ctx.bodyAsClass(PlayerNamesRequest.class);

			// Validate table number
			if (request.getTableNumber() <= 0) {
				ctx.status(400).json(new APIResponse(false, "Invalid table number"));
				return;
			}

			// Validate and sanitize player names
			request.validate();

			// Update names
			teamService.updatePlayerNames(request);

			// Success response
			ctx.status(200).json(new APIResponse(true, "Player names updated successfully"));

			logger.info("API: Successfully updated player names for table {}", request.getTableNumber());

		} catch (PlayerNamesRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Input validation failed: {}", e.getMessage());

		} catch (TeamService.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Table validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error updating player names", e);
		}
	}
}
