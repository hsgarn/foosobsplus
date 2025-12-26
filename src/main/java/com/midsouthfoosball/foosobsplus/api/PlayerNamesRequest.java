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

public class PlayerNamesRequest {
	private static final int MAX_NAME_LENGTH = 100;

	private int tableNumber;
	private TeamPlayers team1;
	private TeamPlayers team2;

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public TeamPlayers getTeam1() {
		return team1;
	}

	public void setTeam1(TeamPlayers team1) {
		this.team1 = team1;
	}

	public TeamPlayers getTeam2() {
		return team2;
	}

	public void setTeam2(TeamPlayers team2) {
		this.team2 = team2;
	}

	/**
	 * Validate and sanitize player names
	 * @throws ValidationException if validation fails
	 */
	public void validate() throws ValidationException {
		if (team1 != null) {
			validateAndSanitizeName("Team 1 Forward", team1.forward);
			validateAndSanitizeName("Team 1 Goalie", team1.goalie);
		}
		if (team2 != null) {
			validateAndSanitizeName("Team 2 Forward", team2.forward);
			validateAndSanitizeName("Team 2 Goalie", team2.goalie);
		}
	}

	private void validateAndSanitizeName(String fieldName, String name) throws ValidationException {
		if (name != null) {
			if (name.length() > MAX_NAME_LENGTH) {
				throw new ValidationException(fieldName + " exceeds maximum length of " + MAX_NAME_LENGTH + " characters");
			}
			// Check for control characters or other potentially problematic characters
			if (name.chars().anyMatch(ch -> ch < 32 && ch != 9 && ch != 10 && ch != 13)) {
				throw new ValidationException(fieldName + " contains invalid control characters");
			}
		}
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}

	public static class TeamPlayers {
		private String forward;
		private String goalie;

		public String getForward() {
			return forward;
		}

		public void setForward(String forward) {
			this.forward = forward;
		}

		public String getGoalie() {
			return goalie;
		}

		public void setGoalie(String goalie) {
			this.goalie = goalie;
		}
	}
}
