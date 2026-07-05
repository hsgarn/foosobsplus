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
 * Request for the /api/obs endpoint. For toggle actions, state is optional:
 * omitted means toggle the current state, provided means set it explicitly.
 */
public record OBSActionRequest(String action, Boolean state) {

	public static final List<String> COMMAND_ACTIONS = List.of("connect", "disconnect", "push", "pull");
	public static final List<String> TOGGLE_ACTIONS = List.of(
			"showScores", "showTimer", "showCutthroat", "showSkunk", "startStream");

	/**
	 * Validate OBS action request
	 * @return the canonical action name (case-insensitive match)
	 * @throws ValidationException if validation fails
	 */
	public String validate() throws ValidationException {
		if (action == null || action.isBlank()) {
			throw new ValidationException("Action is required");
		}
		for (String valid : COMMAND_ACTIONS) {
			if (valid.equalsIgnoreCase(action)) {
				if (state != null) {
					throw new ValidationException("State is only valid for toggle actions: " + String.join(", ", TOGGLE_ACTIONS));
				}
				return valid;
			}
		}
		for (String valid : TOGGLE_ACTIONS) {
			if (valid.equalsIgnoreCase(action)) {
				return valid;
			}
		}
		throw new ValidationException("Invalid action. Must be: " + String.join(", ", COMMAND_ACTIONS)
				+ ", " + String.join(", ", TOGGLE_ACTIONS));
	}

	public boolean isToggleAction(String canonicalAction) {
		return TOGGLE_ACTIONS.contains(canonicalAction);
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
