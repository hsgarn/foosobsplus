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
package com.midsouthfoosball.foosobsplus.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.midsouthfoosball.foosobsplus.main.PicoDiscovery.PicoInfo;

@DisplayName("AutoScore Pico discovery response parsing")
class PicoInfoTest {
	@Test
	void rejectsMalformedEndpointAndMac() {
		assertNull(PicoInfo.parse("Table 1:not-an-ip:5051:28-CD-C1-0F-12-34:FREE"));
		assertNull(PicoInfo.parse("Table 1:192.168.1.2:70000:28-CD-C1-0F-12-34:FREE"));
		assertNull(PicoInfo.parse("Table 1:192.168.1.2:5051:not-a-mac:FREE"));
	}
	@Test
	@DisplayName("legacy response parses without status or MAC")
	void parsesLegacyResponse() {
		PicoInfo info = PicoInfo.parse("Table 1:192.168.68.74:5051");

		assertEquals("Table 1", info.label());
		assertEquals("192.168.68.74", info.ipAddress());
		assertEquals("5051", info.port());
		assertEquals("", info.macAddress());
		assertEquals("", info.status());
		assertEquals(1, info.tableNumber());
		assertFalse(info.isBusy());
		assertEquals("Table 1  192.168.68.74:5051", info.display());
	}

	@Test
	@DisplayName("FREE response parses MAC and reports available")
	void parsesFreeResponse() {
		PicoInfo info = PicoInfo.parse("  Table 2:192.168.68.75:5051:28-CD-C1-0F-12-34:FREE  ");

		assertEquals(2, info.tableNumber());
		assertEquals("28-cd-c1-0f-12-34", info.macAddress());
		assertEquals("FREE", info.status());
		assertFalse(info.isBusy());
		assertEquals("", info.busyClientIp());
		assertEquals("Table 2  192.168.68.75:5051  MAC 28-cd-c1-0f-12-34  [FREE]", info.display());
	}

	@Test
	@DisplayName("BUSY response preserves client IP in the status field")
	void parsesBusyResponse() {
		PicoInfo info = PicoInfo.parse("Table 3:10.0.0.30:5051:AABBCCDDEEFF:BUSY:10.0.0.8");

		assertEquals(3, info.tableNumber());
		assertEquals("BUSY:10.0.0.8", info.status());
		assertTrue(info.isBusy());
		assertEquals("10.0.0.8", info.busyClientIp());
		assertEquals("Table 3  10.0.0.30:5051  MAC aa-bb-cc-dd-ee-ff  [IN GAME - 10.0.0.8]", info.display());
	}

	@Test
	@DisplayName("busy and free status checks are case insensitive")
	void statusChecksIgnoreCase() {
		PicoInfo free = PicoInfo.parse("Table 1:10.0.0.1:5051:AA-BB-CC-DD-EE-01:free");
		PicoInfo busy = PicoInfo.parse("Table 2:10.0.0.2:5051:AA-BB-CC-DD-EE-02:busy:10.0.0.9");

		assertFalse(free.isBusy());
		assertTrue(busy.isBusy());
		assertEquals("10.0.0.9", busy.busyClientIp());
	}

	@ParameterizedTest(name = "invalid response: {0}")
	@NullAndEmptySource
	@ValueSource(strings = {
		"not a discovery response",
		"TABLE 1:192.168.1.2:5051",
		"Table 1:192.168.1.2",
		"Table 1:192.168.1.2:5051:MAC"
	})
	@DisplayName("invalid response formats are rejected")
	void rejectsInvalidResponses(String response) {
		assertNull(PicoInfo.parse(response));
	}

	@ParameterizedTest
	@ValueSource(strings = {"Table", "Table A", "Court One"})
	@DisplayName("labels without a trailing number return minus one")
	void invalidTableLabelsReturnMinusOne(String label) {
		PicoInfo info = new PicoInfo(label, "10.0.0.1", "5051", "", "", "raw");
		assertEquals(-1, info.tableNumber());
	}

	@Test
	@DisplayName("unknown nonempty status is treated as busy")
	void unknownStatusIsConservativelyBusy() {
		PicoInfo info = PicoInfo.parse("Table 4:10.0.0.4:5051:AA-BB-CC-DD-EE-04:UNKNOWN");
		assertTrue(info.isBusy());
		assertEquals("", info.busyClientIp());
		assertTrue(info.display().endsWith("[UNKNOWN]"));
	}

	@Test
	void deduplicatesByNormalizedMacEvenWhenStatusChanges() {
		List<PicoInfo> infos = PicoDiscovery.parseUniqueResponses(List.of(
			"Table 1:10.0.0.1:5051:AA-BB-CC-DD-EE-01:FREE",
			"Table 1:10.0.0.1:5051:aabbccddee01:BUSY:10.0.0.9"), null);
		assertEquals(1, infos.size());
	}

	@Test
	void legacyResponsesDeduplicateByEndpointAndMalformedResponsesAreIgnored() {
		List<PicoInfo> infos = PicoDiscovery.parseUniqueResponses(List.of(
			"Table 1:10.0.0.1:5051", "Table 9:10.0.0.1:5051", "Table 2:not-an-ip:5051"), null);
		assertEquals(1, infos.size());
	}
}
