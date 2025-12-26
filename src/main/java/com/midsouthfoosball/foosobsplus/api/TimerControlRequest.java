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

public class TimerControlRequest {
	private String timerType;
	private int tableNumber;

	public String getTimerType() {
		return timerType;
	}

	public void setTimerType(String timerType) {
		this.timerType = timerType;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Validate timer control request
	 * @throws ValidationException if validation fails
	 */
	public void validate() throws ValidationException {
		if (timerType == null || timerType.trim().isEmpty()) {
			throw new ValidationException("Timer type is required");
		}

		String timerTypeLower = timerType.toLowerCase();
		if (!timerTypeLower.equals("shot") && !timerTypeLower.equals("pass") &&
			!timerTypeLower.equals("timeout") && !timerTypeLower.equals("game") &&
			!timerTypeLower.equals("recall") && !timerTypeLower.equals("reset")) {
			throw new ValidationException("Invalid timer type. Must be: shot, pass, timeout, game, recall, or reset");
		}

		if (tableNumber <= 0) {
			throw new ValidationException("Valid table number is required");
		}
	}

	public static class ValidationException extends Exception {
		private static final long serialVersionUID = 1L;

		public ValidationException(String message) {
			super(message);
		}
	}
}
