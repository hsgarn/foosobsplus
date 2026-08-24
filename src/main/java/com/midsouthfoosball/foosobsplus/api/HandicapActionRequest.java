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

/**
 * Request for the /api/handicap endpoint (Handicapped Points to Win control).
 * state is only used by "toggle" (optional: omitted toggles the current state).
 * teamNumber is required by "increase", "decrease" and "swap" (1-3); teamNumber2
 * is required by "swap" (1-3, must differ from teamNumber).
 */
public record HandicapActionRequest(String action, Boolean state, Integer teamNumber, Integer teamNumber2) {

	public static final List<String> VALID_ACTIONS = List.of("toggle", "increase", "decrease", "swap");

	/**
	 * Validate handicap action request.
	 * @return the canonical action name (case-insensitive match)
	 * @throws ValidationException if validation fails
	 */
	public String validate() throws ValidationException {
		if (action == null || action.isBlank()) {
			throw new ValidationException("Action is required");
		}
		String canonical = null;
		for (String valid : VALID_ACTIONS) {
			if (valid.equalsIgnoreCase(action)) {
				canonical = valid;
				break;
			}
		}
		if (canonical == null) {
			throw new ValidationException("Invalid action. Must be: " + String.join(", ", VALID_ACTIONS));
		}
		if (canonical.equals("toggle")) {
			return canonical;
		}
		if (teamNumber == null || teamNumber < 1 || teamNumber > 3) {
			throw new ValidationException("Team number must be 1, 2 or 3");
		}
		if (canonical.equals("swap")) {
			if (teamNumber2 == null || teamNumber2 < 1 || teamNumber2 > 3) {
				throw new ValidationException("Team number 2 must be 1, 2 or 3");
			}
			if (teamNumber2.equals(teamNumber)) {
				throw new ValidationException("Team number and team number 2 must differ");
			}
		}
		return canonical;
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
