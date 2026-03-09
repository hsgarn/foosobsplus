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
 * Verifies that calling setX() / setXParameter() never mutates the hardcoded
 * default values returned by getDefaultX().
 *
 * The root-cause bug this guards against: if a .properties file is missing at
 * startup, loadFrom*Config() used to do configXProps = defaultXProps (identity
 * assignment), making them the same Java object.  Any subsequent setX() call
 * would then silently corrupt the defaults, causing Restore Defaults to appear
 * to do nothing.  The fix replaced the identity assignment with putAll().
 *
 * These tests are deliberately stateless — they rely only on the hardcoded
 * default values baked into the Settings static initialiser block, which are
 * constant for the lifetime of the JVM.  They do NOT write property files.
 */
@DisplayName("Settings defaults are immutable after setX() calls")
class SettingsDefaultsTest {

    // ------------------------------------------------------------------ Filter

    @Test
    @DisplayName("setFilter does not mutate getDefaultFilter")
    void setFilterDoesNotMutateDefault() {
        String original = Settings.getDefaultFilter(SettingsKeys.FILTER_TEAM1_SCORE);
        assertNotNull(original, "hardcoded default for Team1Score filter must not be null");

        Settings.setFilter(SettingsKeys.FILTER_TEAM1_SCORE, "MUTATED_VALUE");

        assertEquals(original, Settings.getDefaultFilter(SettingsKeys.FILTER_TEAM1_SCORE),
                "getDefaultFilter must return the original hardcoded value after setFilter");
    }

    @Test
    @DisplayName("setFilter for Team2Score does not mutate its default")
    void setFilterTeam2ScoreDoesNotMutateDefault() {
        String original = Settings.getDefaultFilter(SettingsKeys.FILTER_TEAM2_SCORE);
        assertNotNull(original);
        Settings.setFilter(SettingsKeys.FILTER_TEAM2_SCORE, "CHANGED");
        assertEquals(original, Settings.getDefaultFilter(SettingsKeys.FILTER_TEAM2_SCORE));
    }

    @Test
    @DisplayName("setFilter for StartMatch does not mutate its default")
    void setFilterStartMatchDoesNotMutateDefault() {
        String original = Settings.getDefaultFilter(SettingsKeys.FILTER_START_MATCH);
        assertNotNull(original);
        Settings.setFilter(SettingsKeys.FILTER_START_MATCH, "CHANGED");
        assertEquals(original, Settings.getDefaultFilter(SettingsKeys.FILTER_START_MATCH));
    }

    // ------------------------------------------------------------------ OBS

    @Test
    @DisplayName("setOBS does not mutate getDefaultOBSParameter")
    void setOBSDoesNotMutateDefault() {
        String original = Settings.getDefaultOBSParameter(SettingsKeys.OBS_HOST);
        assertNotNull(original, "hardcoded default OBSHost must not be null");
        assertEquals("localhost", original, "default OBSHost should be 'localhost'");

        Settings.setOBS(SettingsKeys.OBS_HOST, "192.168.1.99");

        assertEquals(original, Settings.getDefaultOBSParameter(SettingsKeys.OBS_HOST),
                "getDefaultOBSParameter must return the original hardcoded value after setOBS");
    }

    @Test
    @DisplayName("setOBS for OBSPort does not mutate its default")
    void setOBSPortDoesNotMutateDefault() {
        String original = Settings.getDefaultOBSParameter(SettingsKeys.OBS_PORT);
        assertNotNull(original);
        assertEquals("4455", original, "default OBSPort should be '4455'");
        Settings.setOBS(SettingsKeys.OBS_PORT, "9999");
        assertEquals(original, Settings.getDefaultOBSParameter(SettingsKeys.OBS_PORT));
    }

    @Test
    @DisplayName("setOBS for OBSUpdateOnConnect does not mutate its default")
    void setOBSUpdateOnConnectDoesNotMutateDefault() {
        String original = Settings.getDefaultOBSParameter(SettingsKeys.OBS_UPDATE_ON_CONNECT);
        assertNotNull(original);
        Settings.setOBS(SettingsKeys.OBS_UPDATE_ON_CONNECT, "0");
        assertEquals(original, Settings.getDefaultOBSParameter(SettingsKeys.OBS_UPDATE_ON_CONNECT));
    }

    // ------------------------------------------------------------------ Control / Parameters

    @Test
    @DisplayName("setControlParameter does not mutate the hardcoded default")
    void setControlParameterDoesNotMutateDefault() {
        String original = Settings.getDefaultParameter(SettingsKeys.CTRL_POINTS_TO_WIN);
        assertNotNull(original, "hardcoded default PointsToWin must not be null");
        assertEquals("5", original, "default PointsToWin should be '5'");

        Settings.setControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN, "99");

        assertEquals(original, Settings.getDefaultParameter(SettingsKeys.CTRL_POINTS_TO_WIN),
                "getDefaultParameter must return the original value after setControlParameter");
    }

    @Test
    @DisplayName("setControlParameter for GamesToWin does not mutate its default")
    void setControlParameterGamesToWinDoesNotMutateDefault() {
        String original = Settings.getDefaultParameter(SettingsKeys.CTRL_GAMES_TO_WIN);
        assertNotNull(original);
        Settings.setControlParameter(SettingsKeys.CTRL_GAMES_TO_WIN, "99");
        assertEquals(original, Settings.getDefaultParameter(SettingsKeys.CTRL_GAMES_TO_WIN));
    }

    // ------------------------------------------------------------------ Source

    @Test
    @DisplayName("setSource does not mutate getDefaultSource")
    void setSourceDoesNotMutateDefault() {
        String original = Settings.getDefaultSource(SettingsKeys.SRC_TEAM1_NAME);
        assertNotNull(original, "hardcoded default Team1Name source must not be null");

        Settings.setSource(SettingsKeys.SRC_TEAM1_NAME, "MUTATED_SOURCE");

        assertEquals(original, Settings.getDefaultSource(SettingsKeys.SRC_TEAM1_NAME),
                "getDefaultSource must return the original hardcoded value after setSource");
    }

    @Test
    @DisplayName("setSource for Tournament does not mutate its default")
    void setSourceTournamentDoesNotMutateDefault() {
        String original = Settings.getDefaultSource(SettingsKeys.SRC_TOURNAMENT);
        assertNotNull(original);
        Settings.setSource(SettingsKeys.SRC_TOURNAMENT, "CHANGED");
        assertEquals(original, Settings.getDefaultSource(SettingsKeys.SRC_TOURNAMENT));
    }

    // ------------------------------------------------------------------ Defaults are distinct from config

    @Test
    @DisplayName("config and default Filter values are distinct objects/states")
    void filterConfigAndDefaultAreDistinct() {
        // Mutate config
        Settings.setFilter(SettingsKeys.FILTER_MEATBALL, "config-meatball-filter");
        // Default must not reflect the config mutation
        assertNotEquals("config-meatball-filter",
                Settings.getDefaultFilter(SettingsKeys.FILTER_MEATBALL),
                "defaultFilterProps must not be the same object as configFilterProps");
    }

    @Test
    @DisplayName("config and default OBS values are distinct objects/states")
    void obsConfigAndDefaultAreDistinct() {
        Settings.setOBS(SettingsKeys.OBS_MAIN_SCENE, "config-scene-name");
        assertNotEquals("config-scene-name",
                Settings.getDefaultOBSParameter(SettingsKeys.OBS_MAIN_SCENE),
                "defaultOBSProps must not be the same object as configOBSProps");
    }

    @Test
    @DisplayName("config and default Control values are distinct objects/states")
    void controlConfigAndDefaultAreDistinct() {
        Settings.setControlParameter(SettingsKeys.CTRL_WIN_BY, "99");
        assertNotEquals("99",
                Settings.getDefaultParameter(SettingsKeys.CTRL_WIN_BY),
                "defaultControlProps must not be the same object as configControlProps");
    }
}
