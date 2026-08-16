package com.midsouthfoosball.foosobsplus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class AutoScoreConnectionValidatorTest {
	@Test void acceptsBoundaryPortsAndTrimsFields() {
		TableConnection one = connection(" One ", " 10.0.0.1 ", " 1 ", "AA:BB:CC:DD:EE:01");
		TableConnection two = connection("Two", "10.0.0.2", "65535", "AA-BB-CC-DD-EE-02");
		assertTrue(AutoScoreConnectionValidator.validate(List.of(one, two)).isEmpty());
		assertEquals("One", one.getLabel());
		assertEquals("10.0.0.1", one.getServerAddress());
		assertEquals("AA-BB-CC-DD-EE-01", one.getMacAddress());
	}

	@Test void rejectsAllIdentityAndEndpointConflicts() {
		TableConnection one = connection("Court", "10.0.0.1", "5051", "AA:BB:CC:DD:EE:01");
		TableConnection two = connection("court", "10.0.0.1", "5051", "aabbccddee01");
		String errors = String.join("\n", AutoScoreConnectionValidator.validate(List.of(one, two)));
		assertTrue(errors.contains("duplicate label"));
		assertTrue(errors.contains("MAC address is already assigned"));
		assertTrue(errors.contains("address and port are already used"));
	}

	@Test void rejectsBlankAndMalformedValues() {
		for (String port : List.of("", "0", "65536", "abc")) {
			TableConnection c = connection("", "not-an-ip", port, "not-a-mac");
			String errors = String.join("\n", AutoScoreConnectionValidator.validate(List.of(c)));
			assertTrue(errors.contains("label is required"));
			assertTrue(errors.contains("invalid IP address"));
			assertTrue(errors.contains("invalid port"));
			assertTrue(errors.contains("invalid MAC address"));
		}
	}

	private static TableConnection connection(String label, String address, String port, String mac) {
		return new TableConnection(label, address, port, false, false, "", mac);
	}
}
