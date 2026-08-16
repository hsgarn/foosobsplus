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
package com.midsouthfoosball.foosobsplus.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Command registration and dispatch")
class CommandSwitchTest {
	@Test
	void registeredCommandExecutesAndIsReturned() {
		CommandSwitch commandSwitch = new CommandSwitch();
		CountingCommand command = new CountingCommand("XPS1");
		commandSwitch.register("score-team-1", command);

		Command returned = commandSwitch.execute("score-team-1");

		assertSame(command, returned);
		assertEquals(1, command.executions);
		assertEquals("XPS1", returned.getCode());
	}

	@Test
	void unknownCommandDoesNotExecuteAndReturnsNull() {
		CommandSwitch commandSwitch = new CommandSwitch();
		CountingCommand registered = new CountingCommand("KNOWN");
		commandSwitch.register("known", registered);

		assertNull(commandSwitch.execute("missing"));
		assertEquals(0, registered.executions);
	}

	@Test
	void registeringSameNameReplacesPreviousCommand() {
		CommandSwitch commandSwitch = new CommandSwitch();
		CountingCommand first = new CountingCommand("FIRST");
		CountingCommand replacement = new CountingCommand("SECOND");
		commandSwitch.register("action", first);
		commandSwitch.register("action", replacement);

		assertSame(replacement, commandSwitch.execute("action"));
		assertEquals(0, first.executions);
		assertEquals(1, replacement.executions);
	}

	@Test
	void nullNamesAndCommandsAreRejected() {
		CommandSwitch commandSwitch = new CommandSwitch();
		CountingCommand command = new CountingCommand("CODE");

		assertThrows(NullPointerException.class, () -> commandSwitch.register(null, command));
		assertThrows(NullPointerException.class, () -> commandSwitch.register("action", null));
	}

	private static class CountingCommand implements Command {
		private final String code;
		private int executions;

		private CountingCommand(String code) {
			this.code = code;
		}

		@Override public void execute() { executions++; }
		@Override public String getCode() { return code; }
	}
}
