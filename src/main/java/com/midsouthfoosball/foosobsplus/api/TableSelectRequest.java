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

public record TableSelectRequest(String action, Integer tableNumber) {

	/**
	 * Returns the requested action, defaulting to "select" when absent.
	 */
	public String actionOrDefault() {
		return (action == null || action.isBlank()) ? "select" : action.toLowerCase();
	}

	/**
	 * Validate table select request
	 * @throws ValidationException if validation fails
	 */
	public void validate() throws ValidationException {
		String act = actionOrDefault();
		if (!act.equals("select") && !act.equals("next")) {
			throw new ValidationException("Invalid action. Must be: select or next");
		}
		if (act.equals("select") && (tableNumber == null || tableNumber <= 0)) {
			throw new ValidationException("Valid table number is required for select action");
		}
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
