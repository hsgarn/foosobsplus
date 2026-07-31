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
package com.midsouthfoosball.foosobsplus.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Max Win accepts an "unlimited" setting, typed as U on the Parameters settings
 * page or written as U to the partner program's MaxWin file.  It is stored as
 * Integer.MAX_VALUE so that the numeric readers in Match keep working unchanged.
 *
 * These tests pin the spellings that must be accepted and the round trip between
 * the stored sentinel and the displayed text — the display text is fed straight
 * back into the parser the next time the settings page is applied.
 */
@DisplayName("Max Win unlimited handling")
class SettingsMaxWinTest {

    @Test
    @DisplayName("U is recognised as unlimited regardless of case or whitespace")
    void abbreviationIsUnlimited() {
        assertTrue(Settings.isUnlimitedMaxWin("U"));
        assertTrue(Settings.isUnlimitedMaxWin("u"));
        assertTrue(Settings.isUnlimitedMaxWin("  U  "));
    }

    @Test
    @DisplayName("the Unlimited display text is recognised as unlimited")
    void displayTextIsUnlimited() {
        assertTrue(Settings.isUnlimitedMaxWin("Unlimited"));
        assertTrue(Settings.isUnlimitedMaxWin("unlimited"));
    }

    @Test
    @DisplayName("the stored sentinel is recognised as unlimited")
    void sentinelIsUnlimited() {
        assertTrue(Settings.isUnlimitedMaxWin(Integer.toString(Settings.MAX_WIN_UNLIMITED)));
    }

    @Test
    @DisplayName("ordinary values and junk are not unlimited")
    void otherValuesAreNotUnlimited() {
        assertFalse(Settings.isUnlimitedMaxWin("8"));
        assertFalse(Settings.isUnlimitedMaxWin("0"));
        assertFalse(Settings.isUnlimitedMaxWin(""));
        assertFalse(Settings.isUnlimitedMaxWin("UU"));
        assertFalse(Settings.isUnlimitedMaxWin(null));
    }

    @Test
    @DisplayName("the sentinel formats as Unlimited and ordinary values pass through")
    void formatMaxWinRendersSentinel() {
        assertEquals("Unlimited", Settings.formatMaxWin(Integer.toString(Settings.MAX_WIN_UNLIMITED)));
        assertEquals("8", Settings.formatMaxWin("8"));
    }

    @Test
    @DisplayName("displayed text round trips back to unlimited")
    void displayedTextRoundTrips() {
        String displayed = Settings.formatMaxWin(Integer.toString(Settings.MAX_WIN_UNLIMITED));
        assertTrue(Settings.isUnlimitedMaxWin(displayed),
            "the text shown in the Max Win field must parse back as unlimited when re-applied");
    }

    @Test
    @DisplayName("no score can reach the unlimited cap")
    void noScoreReachesTheCap() {
        // Match tests "score >= maxWin", so the sentinel must sit above any reachable score.
        assertEquals(Integer.MAX_VALUE, Settings.MAX_WIN_UNLIMITED);
    }
}
