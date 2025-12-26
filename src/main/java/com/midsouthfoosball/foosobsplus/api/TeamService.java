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

import com.midsouthfoosball.foosobsplus.controller.TeamController;
import com.midsouthfoosball.foosobsplus.model.Tournament;

public class TeamService {
	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	private final TeamController teamController;
	private final Tournament tournament;

	public TeamService(TeamController teamController, Tournament tournament) {
		this.teamController = teamController;
		this.tournament = tournament;
	}

	public TeamController getTeamController() {
		return teamController;
	}

	public void updatePlayerNames(PlayerNamesRequest request) throws ValidationException {
		validateTableNumber(request.getTableNumber());

		// Use SwingUtilities.invokeLater to ensure updates happen on EDT
		SwingUtilities.invokeLater(() -> {
			try {
				// Update Team 1
				if (request.getTeam1() != null) {
					if (request.getTeam1().getForward() != null) {
						teamController.setTeam1ForwardName(request.getTeam1().getForward());
						logger.info("API: Updated Team 1 Forward: {}", request.getTeam1().getForward());
					}
					if (request.getTeam1().getGoalie() != null) {
						teamController.setTeam1GoalieName(request.getTeam1().getGoalie());
						logger.info("API: Updated Team 1 Goalie: {}", request.getTeam1().getGoalie());
					}
				}

				// Update Team 2
				if (request.getTeam2() != null) {
					if (request.getTeam2().getForward() != null) {
						teamController.setTeam2ForwardName(request.getTeam2().getForward());
						logger.info("API: Updated Team 2 Forward: {}", request.getTeam2().getForward());
					}
					if (request.getTeam2().getGoalie() != null) {
						teamController.setTeam2GoalieName(request.getTeam2().getGoalie());
						logger.info("API: Updated Team 2 Goalie: {}", request.getTeam2().getGoalie());
					}
				}
			} catch (Exception e) {
				logger.error("Error updating player names on EDT", e);
			}
		});
	}

	private void validateTableNumber(int requestedTable) throws ValidationException {
		String tableName = tournament.getTableName();
		int currentTable = 1; // Default to table 1

		// Parse table number from tableName if present
		if (tableName != null && !tableName.trim().isEmpty()) {
			try {
				// Try to extract number from table name (handles "Table 1", "1", "T1", etc.)
				String numericPart = tableName.replaceAll("[^0-9]", "");
				if (!numericPart.isEmpty()) {
					currentTable = Integer.parseInt(numericPart);
				}
			} catch (NumberFormatException e) {
				// If parsing fails, default to table 1
				logger.warn("Could not parse table number from: '{}', defaulting to 1", tableName);
			}
		}

		if (requestedTable != currentTable) {
			throw new ValidationException(
					String.format("Table number mismatch. Expected table %d, got %d", currentTable, requestedTable));
		}
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
