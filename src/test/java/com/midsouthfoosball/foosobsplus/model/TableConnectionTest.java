package com.midsouthfoosball.foosobsplus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TableConnectionTest {
	@Test
	void normalizesCommonMacFormats() {
		assertEquals("28-cd-c1-0f-12-34", TableConnection.normalizeMac("28:cd:c1:0f:12:34"));
		assertEquals("28-cd-c1-0f-12-34", TableConnection.normalizeMac("28cdc10f1234"));
		assertTrue(TableConnection.isValidMac("28:cd:c1:0f:12:34"));
		assertFalse(TableConnection.isValidMac("not-a-mac"));
	}

	@Test
	void copyRetainsStableIdentity() {
		TableConnection original = new TableConnection("Court", "10.0.0.2", "5051", true, false, "Camera", "28-CD-C1-0F-12-34");
		assertEquals(original.getId(), original.copy().getId());
	}
}
