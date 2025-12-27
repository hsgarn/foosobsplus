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

public class FoosballCodeRequest {
	private static final int MAX_CODE_LENGTH = 20;

	private String code;
	private int tableNumber;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Validate foosball code request
	 * @throws ValidationException if validation fails
	 */
	public void validate() throws ValidationException {
		if (code == null || code.trim().isEmpty()) {
			throw new ValidationException("Code is required");
		}

		if (code.length() > MAX_CODE_LENGTH) {
			throw new ValidationException("Code exceeds maximum length of " + MAX_CODE_LENGTH + " characters");
		}

		// Check for control characters
		if (code.chars().anyMatch(ch -> ch < 32 && ch != 9 && ch != 10 && ch != 13)) {
			throw new ValidationException("Code contains invalid control characters");
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
