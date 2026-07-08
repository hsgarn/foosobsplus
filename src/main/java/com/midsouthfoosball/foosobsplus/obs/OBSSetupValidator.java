/**
Copyright © 2021-2026 Hugh Garner
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Compares configured OBS source/filter names against the names OBS actually
 * reports, so UI panels can highlight and report typos, duplicates, and
 * source/filter name mix-ups without a round trip through OBS on their own.
 */
public final class OBSSetupValidator {
	private OBSSetupValidator() {}

	public enum Status { OK, MISSING, WRONG_TYPE, DUPLICATE }

	public record Result(String key, Status status, String value) {}

	/**
	 * @param configured key to current (already-trimmed, non-blank) field text
	 * @param ownList OBS names of the type being validated (e.g. inputs for a sources check)
	 * @param otherTypeList OBS names of the other type, used only for WRONG_TYPE detection; may be null
	 */
	public static Map<String, Result> validate(Map<String, String> configured, List<String> ownList, List<String> otherTypeList) {
		Map<String, Result> results = new HashMap<>();
		Set<String> own = ownList == null ? Set.of() : new HashSet<>(ownList);
		Set<String> otherType = otherTypeList == null ? Set.of() : new HashSet<>(otherTypeList);

		Map<String, Integer> nameCounts = new HashMap<>();
		configured.values().forEach(value -> nameCounts.merge(value, 1, Integer::sum));

		configured.forEach((key, value) -> {
			Status status;
			if (nameCounts.get(value) > 1) {
				status = Status.DUPLICATE;
			} else if (own.contains(value)) {
				status = Status.OK;
			} else if (otherType.contains(value)) {
				status = Status.WRONG_TYPE;
			} else {
				status = Status.MISSING;
			}
			results.put(key, new Result(key, status, value));
		});
		return results;
	}
}
