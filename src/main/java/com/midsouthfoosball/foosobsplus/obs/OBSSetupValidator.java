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

	public enum Status { OK, MISSING, MISSING_SCENE, WRONG_TYPE, DUPLICATE }

	public record Result(String key, Status status, String value) {}

	/**
	 * @param configured key to current (already-trimmed, non-blank) field text
	 * @param ownList OBS names of the type being validated (e.g. inputs for a sources check)
	 * @param otherTypeList OBS names of the other type, used only for WRONG_TYPE detection; may be null
	 */
	public static Map<String, Result> validate(Map<String, String> configured, List<String> ownList, List<String> otherTypeList) {
		return validate(configured, ownList, otherTypeList, null);
	}

	/**
	 * @param configured key to current (already-trimmed, non-blank) field text
	 * @param ownList OBS names of the type being validated (e.g. inputs for a sources check)
	 * @param otherTypeList OBS names of the other type, used only for WRONG_TYPE detection; may be null
	 * @param sceneAndGroupList OBS scene + group names, used to validate the scene half of a
	 *        "SceneName,SourceName" value (see showSource()). Null means the scene half isn't
	 *        checked at all (e.g. callers that never use that format, or the list hasn't been
	 *        fetched from OBS yet) - this differs from ownList/otherTypeList, where null/empty
	 *        means "checked against nothing" and so always fails; here it means "not checked".
	 */
	public static Map<String, Result> validate(Map<String, String> configured, List<String> ownList, List<String> otherTypeList, List<String> sceneAndGroupList) {
		Map<String, Result> results = new HashMap<>();
		Set<String> own = ownList == null ? Set.of() : new HashSet<>(ownList);
		Set<String> otherType = otherTypeList == null ? Set.of() : new HashSet<>(otherTypeList);
		Set<String> containers = sceneAndGroupList == null ? null : new HashSet<>(sceneAndGroupList);

		Map<String, Integer> nameCounts = new HashMap<>();
		configured.values().forEach(value -> nameCounts.merge(value, 1, Integer::sum));

		configured.forEach((key, value) -> {
			Status status;
			// A "Scene,Source" value (see showSource()) names a source nested in a specific
			// scene or group; split it the same way showSource() does (plain split(","),
			// which drops a trailing empty piece) so validation checks exactly what OBS
			// would actually be asked to look up.
			String[] parts = value.split(",");
			String scenePart = parts.length >= 2 && !parts[0].isEmpty() ? parts[0] : null;
			String lookupValue = parts.length >= 2 ? parts[1] : value;
			if (nameCounts.get(value) > 1) {
				status = Status.DUPLICATE;
			} else if (scenePart != null && containers != null && !containers.contains(scenePart)) {
				status = Status.MISSING_SCENE;
			} else if (own.contains(lookupValue)) {
				status = Status.OK;
			} else if (otherType.contains(lookupValue)) {
				status = Status.WRONG_TYPE;
			} else {
				status = Status.MISSING;
			}
			results.put(key, new Result(key, status, value));
		});
		return results;
	}
}
