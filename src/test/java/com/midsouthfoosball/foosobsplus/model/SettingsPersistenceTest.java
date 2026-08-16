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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Settings persistence and AutoScore migration")
class SettingsPersistenceTest {
	@TempDir
	Path configDirectory;

	private String originalConfigDirectory;
	private String originalPointsToWin;

	@BeforeEach
	void redirectConfigurationFiles() {
		originalConfigDirectory = System.getProperty(Settings.CONFIG_DIRECTORY_PROPERTY);
		originalPointsToWin = Settings.getControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN);
		System.setProperty(Settings.CONFIG_DIRECTORY_PROPERTY, configDirectory.toString());
	}

	@AfterEach
	void restoreConfigurationLocationAndState() throws Exception {
		Settings.setControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN, originalPointsToWin);
		if (originalConfigDirectory == null) {
			System.clearProperty(Settings.CONFIG_DIRECTORY_PROPERTY);
		} else {
			System.setProperty(Settings.CONFIG_DIRECTORY_PROPERTY, originalConfigDirectory);
		}
		// Restore the in-memory AutoScore/API state from the application's actual files.
		Settings.loadFromAutoScoreSettingsConfig();
		Settings.loadFromAPIConfig();
	}

	@Test
	void controlSettingSurvivesSaveAndReload() throws Exception {
		Settings.setControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN, "11");
		Settings.saveControlConfig();
		Settings.setControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN, "3");

		Settings.loadFromControlConfig();

		assertEquals("11", Settings.getControlParameter(SettingsKeys.CTRL_POINTS_TO_WIN));
		assertTrue(Files.exists(configDirectory.resolve("control.properties")));
	}

	@Test
	void legacySingleConnectionLoadsAsTableOne() throws Exception {
		Properties legacy = new Properties();
		legacy.setProperty(SettingsKeys.AS_SERVER_ADDRESS, "192.168.1.50");
		legacy.setProperty(SettingsKeys.AS_SERVER_PORT, "5051");
		legacy.setProperty(SettingsKeys.AS_AUTO_CONNECT, "1");
		legacy.setProperty(SettingsKeys.AS_DETAIL_LOG, "0");
		store(configDirectory.resolve("autoscoresettings.properties"), legacy);

		Settings.loadFromAutoScoreSettingsConfig();
		List<TableConnection> connections = Settings.getTableConnections();

		assertEquals(1, connections.size());
		assertConnection(connections.get(0), "Table 1", "192.168.1.50", "5051", true, false, "");
	}

	@Test
	void multipleConnectionsRoundTripAllFields() throws Exception {
		List<TableConnection> expected = List.of(
			new TableConnection("Table 1", "10.0.0.11", "5051", true, false, "Camera 1"),
			new TableConnection("Finals", "10.0.0.12", "6060", false, true, "Scene,Camera 2"));

		Settings.saveTableConnections(expected);
		Settings.loadFromAutoScoreSettingsConfig();
		List<TableConnection> actual = Settings.getTableConnections();

		assertEquals(2, actual.size());
		assertConnection(actual.get(0), "Table 1", "10.0.0.11", "5051", true, false, "Camera 1");
		assertConnection(actual.get(1), "Finals", "10.0.0.12", "6060", false, true, "Scene,Camera 2");
		assertEquals(expected.get(0).getId(), actual.get(0).getId());
		assertEquals(expected.get(1).getId(), actual.get(1).getId());
		assertEquals("10.0.0.11", Settings.getAutoScoreParameter(SettingsKeys.AS_SERVER_ADDRESS),
			"the first connection remains mirrored to legacy keys");
	}

	@Test
	void savingFewerConnectionsRemovesStaleIndexedProperties() throws Exception {
		Settings.saveTableConnections(List.of(
			new TableConnection("One", "10.0.0.1", "5001", false, false),
			new TableConnection("Two", "10.0.0.2", "5002", false, false),
			new TableConnection("Three", "10.0.0.3", "5003", false, false)));
		Settings.saveTableConnections(List.of(
			new TableConnection("Only", "10.0.0.9", "5099", true, true)));

		Properties persisted = load(configDirectory.resolve("autoscoresettings.properties"));
		assertEquals("1", persisted.getProperty(SettingsKeys.AS_TABLE_COUNT));
		assertFalse(persisted.stringPropertyNames().stream()
			.anyMatch(key -> key.startsWith(SettingsKeys.AS_TABLE_PREFIX + "2")));
		assertFalse(persisted.stringPropertyNames().stream()
			.anyMatch(key -> key.startsWith(SettingsKeys.AS_TABLE_PREFIX + "3")));
	}

	@Test
	void indexedSettingsWithoutIdMigrateToStablePersistedId() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(SettingsKeys.AS_TABLE_COUNT, "1");
		properties.setProperty(SettingsKeys.AS_TABLE_PREFIX + "1" + SettingsKeys.AS_SUFFIX_LABEL, "Legacy Indexed");
		properties.setProperty(SettingsKeys.AS_TABLE_PREFIX + "1" + SettingsKeys.AS_SUFFIX_ADDRESS, "10.0.0.5");
		properties.setProperty(SettingsKeys.AS_TABLE_PREFIX + "1" + SettingsKeys.AS_SUFFIX_PORT, "5051");
		store(configDirectory.resolve("autoscoresettings.properties"), properties);
		Settings.loadFromAutoScoreSettingsConfig();
		TableConnection migrated = Settings.getTableConnections().get(0);
		assertFalse(migrated.getId().isBlank());
		Settings.saveTableConnections(List.of(migrated));
		Settings.loadFromAutoScoreSettingsConfig();
		assertEquals(migrated.getId(), Settings.getTableConnections().get(0).getId());
	}

	@Test
	void identitySurvivesRenameAndMacIsNormalizedAcrossPersistence() throws Exception {
		TableConnection connection = new TableConnection("Before", "10.0.0.6", "5051", false, false, "", "aa:bb:cc:dd:ee:06");
		String id = connection.getId();
		connection.setLabel("After");
		Settings.saveTableConnections(List.of(connection));
		Settings.loadFromAutoScoreSettingsConfig();
		TableConnection loaded = Settings.getTableConnections().get(0);
		assertEquals(id, loaded.getId());
		assertEquals("After", loaded.getLabel());
		assertEquals("AA-BB-CC-DD-EE-06", loaded.getMacAddress());
	}

	@Test
	void missingApiFileGeneratesAndPersistsUniqueKey() throws Exception {
		Path apiFile = configDirectory.resolve("api.properties");
		Settings.loadFromAPIConfig();

		String generated = Settings.getAPIParameter(SettingsKeys.API_KEY);
		assertNotNull(generated);
		assertFalse(generated.isBlank());
		assertEquals(generated, load(apiFile).getProperty(SettingsKeys.API_KEY));
		assertNotEquals(generated, Settings.generateRandomAPIKey());
	}

	private static void assertConnection(TableConnection actual, String label, String address, String port,
			boolean autoConnect, boolean detailLog, String camera) {
		assertEquals(label, actual.getLabel());
		assertEquals(address, actual.getServerAddress());
		assertEquals(port, actual.getServerPort());
		assertEquals(autoConnect, actual.isAutoConnect());
		assertEquals(detailLog, actual.isDetailLog());
		assertEquals(camera, actual.getCameraSource());
	}

	private static void store(Path path, Properties properties) throws Exception {
		try (OutputStream output = Files.newOutputStream(path)) {
			properties.store(output, "test");
		}
	}

	private static Properties load(Path path) throws Exception {
		Properties properties = new Properties();
		try (InputStream input = Files.newInputStream(path)) {
			properties.load(input);
		}
		return properties;
	}
}
