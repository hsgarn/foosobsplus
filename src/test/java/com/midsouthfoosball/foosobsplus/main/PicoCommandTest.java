package com.midsouthfoosball.foosobsplus.main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PicoCommandTest {
	private static String[] parts(String reply) { return reply.split(":"); }

	@Test void assignedReplyForRequestedTableIsAnswer() {
		assertTrue(PicoCommand.isAssignAnswer(parts("ASSIGNED:aa-bb-cc-dd-ee-01:2"), 2));
	}

	@Test void assignedReplyForOtherTableIsIgnored() {
		assertFalse(PicoCommand.isAssignAnswer(parts("ASSIGNED:aa-bb-cc-dd-ee-01:1"), 2));
		assertFalse(PicoCommand.isAssignAnswer(parts("ASSIGNED:aa-bb-cc-dd-ee-01:x"), 2));
		assertFalse(PicoCommand.isAssignAnswer(parts("ASSIGNED:aa-bb-cc-dd-ee-01"), 2));
	}

	@Test void busyIsAnAnswerButOtherRepliesAreNot() {
		assertTrue(PicoCommand.isAssignAnswer(parts("BUSY:aa-bb-cc-dd-ee-01"), 2));
		assertFalse(PicoCommand.isAssignAnswer(parts("FLASHING:aa-bb-cc-dd-ee-01"), 2));
		assertFalse(PicoCommand.isAssignAnswer(parts("REPORTING:aa-bb-cc-dd-ee-01:2"), 2));
	}
}
