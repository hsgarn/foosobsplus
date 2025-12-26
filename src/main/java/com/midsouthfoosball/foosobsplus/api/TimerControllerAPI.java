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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.controller.TeamController;
import io.javalin.http.Context;

public class TimerControllerAPI {
	private static final Logger logger = LoggerFactory.getLogger(TimerControllerAPI.class);
	private final TeamController teamController;

	public TimerControllerAPI(TeamController teamController) {
		this.teamController = teamController;
	}

	public void controlTimer(Context ctx) {
		try {
			TimerControlRequest request = ctx.bodyAsClass(TimerControlRequest.class);

			// Validate request
			request.validate();

			// Execute timer action based on type
			String timerType = request.getTimerType().toLowerCase();
			switch (timerType) {
				case "shot":
					teamController.startShotTimer();
					logger.info("API: Started shot timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Shot timer started"));
					break;
				case "pass":
					teamController.startPassTimer();
					logger.info("API: Started pass timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Pass timer started"));
					break;
				case "timeout":
					teamController.startTimeOutTimer();
					logger.info("API: Started timeout timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Timeout timer started"));
					break;
				case "game":
					teamController.startGameTimer();
					logger.info("API: Started game timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Game timer started"));
					break;
				case "recall":
					teamController.startRecallTimer();
					logger.info("API: Started recall timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Recall timer started"));
					break;
				case "reset":
					teamController.resetTimer();
					logger.info("API: Reset timer for table {}", request.getTableNumber());
					ctx.status(200).json(new APIResponse(true, "Timer reset"));
					break;
				default:
					ctx.status(400).json(new APIResponse(false, "Unknown timer type"));
					break;
			}

		} catch (TimerControlRequest.ValidationException e) {
			ctx.status(400).json(new APIResponse(false, e.getMessage()));
			logger.warn("API: Timer control validation failed: {}", e.getMessage());

		} catch (Exception e) {
			ctx.status(500).json(new APIResponse(false, "Internal server error"));
			logger.error("API: Error controlling timer", e);
		}
	}
}
