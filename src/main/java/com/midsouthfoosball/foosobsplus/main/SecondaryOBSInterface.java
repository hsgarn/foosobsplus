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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.midsouthfoosball.foosobsplus.model.Settings;
import com.midsouthfoosball.foosobsplus.model.SettingsKeys;

/**
 * OBS sink for the "secondary" ("mini") table area. The most recently displayed
 * background table is pointed at one of these so a small subset of its info
 * (per-team Name, Forward, Goalie, Score, GameCount, MatchCount, TimeOut, plus
 * the table name) is written to OBS under prefixed source names — e.g. prefix
 * {@code "s-"} turns source {@code team1score} into {@code s-team1score}. Every
 * other write (timers, stats, game-show, balls, filters, reset/warn) is dropped,
 * so the background table never disturbs the full sources owned by the displayed
 * table.
 *
 * <p>An empty prefix disables the feature entirely (all writes dropped), which
 * keeps single-table users at their current behavior.
 *
 * <p>The prefix and the set of allowed (subset) source names are resolved from
 * {@link Settings} and cached; {@link #refreshFromSettings()} must be called
 * whenever the source mappings or the prefix change (startup and Sources save).
 */
public class SecondaryOBSInterface extends OBSInterface {
	// Suffixes of the per-team sources that make up the mini area, resolved via
	// Settings.getTeamSourceParameter(teamNumber, suffix).
	private static final String[] TEAM_SUFFIXES = {
		"Name", "Forward", "Goalie", "Score", "GameCount", "MatchCount", "TimeOut" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	};
	private volatile String prefix = ""; //$NON-NLS-1$
	private volatile Set<String> allowedSources = Collections.emptySet();

	public SecondaryOBSInterface() {
		super();
	}

	/**
	 * Recomputes the prefix and the allowed subset source-name set from the
	 * current Settings source mappings. Safe to call from any thread; the fields
	 * are volatile and swapped atomically.
	 */
	public void refreshFromSettings() {
		String p = Settings.getSourceParameter(SettingsKeys.SRC_SECONDARY_PREFIX);
		prefix = p == null ? "" : p.trim(); //$NON-NLS-1$
		Set<String> allowed = new HashSet<>();
		for (int team = 1; team <= 3; team++) {
			for (String suffix : TEAM_SUFFIXES) {
				String src = Settings.getTeamSourceParameter(Integer.toString(team), suffix);
				if (src != null && !src.isEmpty()) allowed.add(src);
			}
		}
		String tableName = Settings.getSourceParameter(SettingsKeys.SRC_TABLE_NAME);
		if (tableName != null && !tableName.isEmpty()) allowed.add(tableName);
		allowedSources = Collections.unmodifiableSet(allowed);
	}

	/** True when a non-empty prefix is configured (the mini area is enabled). */
	public boolean isEnabled() {
		return !prefix.isEmpty();
	}

	@Override
	public void writeData(String source, String data, String className, Boolean showParsed) {
		if (prefix.isEmpty()) return;
		if (source == null || source.isEmpty()) return;
		if (!allowedSources.contains(source)) return;
		super.writeData(prefix + source, data, className, showParsed);
	}

	@Override
	public String getContents(String whichSource) {
		return ""; //$NON-NLS-1$
	}
}
