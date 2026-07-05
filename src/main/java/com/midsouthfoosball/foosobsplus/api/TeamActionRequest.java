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

import java.util.List;

public record TeamActionRequest(Integer tableNumber, int teamNumber, String action) {

	public static final List<String> VALID_ACTIONS = List.of(
			"scorePlus", "scoreMinus", "gameCountPlus", "gameCountMinus",
			"matchCountPlus", "matchCountMinus", "timeOutPlus", "timeOutMinus",
			"reset", "warn", "kingSeat", "switchPositions");

	/**
	 * Validate team action request. tableNumber is optional: when omitted the
	 * action applies to the active table.
	 * @return the canonical action name (case-insensitive match)
	 * @throws ValidationException if validation fails
	 */
	public String validate() throws ValidationException {
		if (tableNumber != null && tableNumber <= 0) {
			throw new ValidationException("Table number must be a positive integer");
		}
		if (teamNumber < 1 || teamNumber > 3) {
			throw new ValidationException("Team number must be 1, 2 or 3");
		}
		if (action == null || action.isBlank()) {
			throw new ValidationException("Action is required");
		}
		for (String valid : VALID_ACTIONS) {
			if (valid.equalsIgnoreCase(action)) {
				return valid;
			}
		}
		throw new ValidationException("Invalid action. Must be: " + String.join(", ", VALID_ACTIONS));
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
