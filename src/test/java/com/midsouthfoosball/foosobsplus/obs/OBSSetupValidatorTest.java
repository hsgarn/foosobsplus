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
package com.midsouthfoosball.foosobsplus.obs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.obs.OBSSetupValidator.Result;
import com.midsouthfoosball.foosobsplus.obs.OBSSetupValidator.Status;

@DisplayName("OBS source and filter validation")
class OBSSetupValidatorTest {
	@Test
	void configuredNamePresentInExpectedListIsOk() {
		Map<String, Result> results = OBSSetupValidator.validate(
			Map.of("team1Score", "score-source"), List.of("score-source"), List.of("blur-filter"));

		assertEquals(new Result("team1Score", Status.OK, "score-source"), results.get("team1Score"));
	}

	@Test
	void unknownNameIsMissing() {
		Map<String, Result> results = OBSSetupValidator.validate(
			Map.of("team1Score", "misspelled"), List.of("score-source"), List.of("blur-filter"));

		assertEquals(Status.MISSING, results.get("team1Score").status());
	}

	@Test
	void namePresentOnlyInOtherTypeIsWrongType() {
		Map<String, Result> results = OBSSetupValidator.validate(
			Map.of("team1Score", "blur-filter"), List.of("score-source"), List.of("blur-filter"));

		assertEquals(Status.WRONG_TYPE, results.get("team1Score").status());
	}

	@Test
	void duplicateConfiguredNamesMarkEveryUseDuplicate() {
		Map<String, String> configured = new LinkedHashMap<>();
		configured.put("team1Score", "shared-source");
		configured.put("team2Score", "shared-source");

		Map<String, Result> results = OBSSetupValidator.validate(
			configured, List.of("shared-source"), List.of());

		assertEquals(Status.DUPLICATE, results.get("team1Score").status());
		assertEquals(Status.DUPLICATE, results.get("team2Score").status());
	}

	@Test
	void duplicateTakesPrecedenceOverWrongTypeAndMissing() {
		Map<String, String> configured = Map.of("first", "wrong-kind", "second", "wrong-kind");
		Map<String, Result> results = OBSSetupValidator.validate(
			configured, List.of(), List.of("wrong-kind"));

		assertEquals(Status.DUPLICATE, results.get("first").status());
		assertEquals(Status.DUPLICATE, results.get("second").status());
	}

	@Test
	void nullObservedListsAreHandledAsEmpty() {
		Map<String, Result> results = OBSSetupValidator.validate(
			Map.of("team1Score", "score-source"), null, null);

		assertEquals(Status.MISSING, results.get("team1Score").status());
	}

	@Test
	void emptyConfigurationReturnsEmptyResults() {
		assertTrue(OBSSetupValidator.validate(Map.of(), List.of("source"), List.of("filter")).isEmpty());
	}

	@Test
	void valuesAreMatchedCaseSensitivelyLikeObsNames() {
		Map<String, Result> results = OBSSetupValidator.validate(
			Map.of("team1Score", "Score Source"), List.of("score source"), List.of());

		assertEquals(Status.MISSING, results.get("team1Score").status());
	}
}
