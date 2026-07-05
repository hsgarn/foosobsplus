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

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.main.Main;

/**
 * Shared helper for API controllers that must execute game actions on the
 * Swing Event Dispatch Thread and translate the outcome to an HTTP status.
 */
public final class CommandExecutor {
	private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

	public record Result(boolean success, int httpStatus, String message) {}

	private CommandExecutor() {}

	/**
	 * Runs Main.processCode(code) on the EDT and waits for completion.
	 * postCodeAction (may be null) runs on the EDT after the code succeeds,
	 * mirroring the button listeners that call activateFilter after processCode.
	 */
	public static Result processCode(String code, Runnable postCodeAction) {
		try {
			SwingUtilities.invokeAndWait(() -> {
				Main.processCode(code, false);
				if (!Main.getLastCodeError() && postCodeAction != null) {
					postCodeAction.run();
				}
			});
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("API: Command processing interrupted for code {}", code, e);
			return new Result(false, 500, "Command processing interrupted");
		} catch (InvocationTargetException e) {
			logger.error("API: Error processing code {} on EDT", code, e);
			return new Result(false, 500, "Error processing command");
		}
		if (Main.getLastCodeError()) {
			return new Result(false, 400, "Invalid code: " + Main.getLastCodeErrorMsg());
		}
		return new Result(true, 200, "Command processed successfully");
	}

	/**
	 * Runs an arbitrary action on the EDT and waits for completion.
	 */
	public static Result runOnEDT(Runnable action) {
		try {
			SwingUtilities.invokeAndWait(action);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("API: Command processing interrupted", e);
			return new Result(false, 500, "Command processing interrupted");
		} catch (InvocationTargetException e) {
			logger.error("API: Error processing command on EDT", e);
			return new Result(false, 500, "Error processing command");
		}
		return new Result(true, 200, "Command processed successfully");
	}

	/**
	 * Runs a supplier on the EDT, waits for completion and returns its value.
	 */
	public static <T> T callOnEDT(Supplier<T> supplier) throws InterruptedException, InvocationTargetException {
		final Object[] holder = new Object[1];
		SwingUtilities.invokeAndWait(() -> holder[0] = supplier.get());
		@SuppressWarnings("unchecked")
		T result = (T) holder[0];
		return result;
	}
}
