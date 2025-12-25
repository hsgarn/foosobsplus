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

import com.midsouthfoosball.foosobsplus.model.Settings;

import io.javalin.Javalin;

public class APIServer {
	private static final Logger logger = LoggerFactory.getLogger(APIServer.class);
	private static final int MAX_REQUEST_SIZE_BYTES = 10 * 1024; // 10KB max request size
	private static final int MAX_REQUESTS_PER_MINUTE = 30; // Rate limit

	private Javalin app;
	private final int port;
	private final String apiKey;
	private final PlayerNamesController playerNamesController;
	private final RateLimiter rateLimiter;

	public APIServer(TeamService teamService) {
		this.port = Integer.parseInt(Settings.getAPIParameter("APIPort"));
		this.apiKey = Settings.getAPIParameter("APIKey");
		this.playerNamesController = new PlayerNamesController(teamService);
		this.rateLimiter = new RateLimiter(MAX_REQUESTS_PER_MINUTE);
	}

	public void start() {
		if (app != null && app.jettyServer() != null && !app.jettyServer().server().isStopped()) {
			logger.warn("API Server already running");
			return;
		}

		final String finalApiKey = this.apiKey; // Make effectively final for lambda

		app = Javalin.create(config -> {
			// Set max request size
			config.maxRequestSize = (long) MAX_REQUEST_SIZE_BYTES;

			// Configure access manager for API key authentication
			config.accessManager((handler, ctx, permittedRoles) -> {
				String path = ctx.path();

				// Rate limiting check (for all API endpoints)
				if (path.startsWith("/api/")) {
					String clientIp = ctx.ip();
					if (!rateLimiter.allowRequest(clientIp)) {
						ctx.status(429).json(new APIResponse(false, "Rate limit exceeded. Maximum " + MAX_REQUESTS_PER_MINUTE + " requests per minute."));
						return;
					}
				}

				if (path.equals("/api/health")) {
					// Health check bypasses auth
					handler.handle(ctx);
				} else if (path.startsWith("/api/")) {
					// Check API key for all other /api/* endpoints
					String providedKey = ctx.header("X-API-Key");
					if (providedKey != null && providedKey.equals(finalApiKey)) {
						handler.handle(ctx);
					} else {
						logger.warn("API: Authentication failed for {} from IP {}", path, ctx.ip());
						ctx.status(401).json(new APIResponse(false, "Invalid or missing API key"));
					}
				} else {
					handler.handle(ctx);
				}
			});
		});

		// Health check endpoint (no auth required)
		app.get("/api/health", ctx -> ctx.json(new APIResponse(true, "API server is running")));

		// Auth test endpoint (requires API key)
		app.get("/api/test", ctx -> ctx.json(new APIResponse(true, "Authentication successful")));

		// Routes
		app.post("/api/players", playerNamesController::updatePlayerNames);

		// Start server
		try {
			app.start(port);
			logger.info("REST API Server started on port {}", port);
			logger.info("API Key authentication: enabled");
		} catch (Exception e) {
			logger.error("Failed to start REST API Server on port {}", port, e);
			throw e;
		}
	}

	public void stop() {
		if (app != null) {
			try {
				app.stop();
				rateLimiter.shutdown();
				logger.info("REST API Server stopped");
			} catch (Exception e) {
				logger.error("Error stopping REST API Server", e);
			}
		}
	}

	public boolean isRunning() {
		return app != null && app.jettyServer() != null && !app.jettyServer().server().isStopped();
	}
}
