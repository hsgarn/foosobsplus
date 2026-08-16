package com.midsouthfoosball.foosobsplus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.model.TableConnection;

@DisplayName("AutoScore connection manager")
class AutoScoreManagerTest {
	private TestManager manager;
	private ServerSocket server;
	private Thread serverThread;

	@AfterEach
	void cleanUp() throws Exception {
		if (manager != null) {
			manager.setBlockReconnect(true);
			manager.disconnect();
		}
		if (server != null && !server.isClosed()) server.close();
		if (serverThread != null) serverThread.join(2_000);
		SwingUtilities.invokeAndWait(() -> { });
	}

	@Test
	@DisplayName("connects, translates a team sensor event, and sends bye on disconnect")
	void connectionScoreAndDisconnectProtocol() throws Exception {
		server = new ServerSocket(0);
		CountDownLatch accepted = new CountDownLatch(1);
		AtomicReference<String> hello = new AtomicReference<>();
		AtomicReference<String> command = new AtomicReference<>();
		startServer(socket -> {
			accepted.countDown();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			hello.set(in.readLine()); // "hello:<sessionId>", sent right after connecting
			out.println("Team:1");
			command.set(in.readLine());
		});
		manager = manager(false);
		CountDownLatch connected = new CountDownLatch(1);
		CountDownLatch scored = new CountDownLatch(1);
		manager.setConnectionStateListener(state -> { if (state) connected.countDown(); });
		manager.setScoreEventListener(code -> {
			manager.events.add(code);
			scored.countDown();
		});

		manager.connect();
		assertTrue(accepted.await(2, TimeUnit.SECONDS));
		assertTrue(connected.await(2, TimeUnit.SECONDS));
		assertTrue(scored.await(2, TimeUnit.SECONDS));
		assertTrue(manager.isConnected());
		assertEquals(List.of("XIST1"), manager.events);
		assertTrue(hello.get() != null && hello.get().startsWith("hello:"));

		manager.setBlockReconnect(true);
		manager.disconnect();
		await(() -> "bye:".equals(command.get()));
		assertEquals("bye:", command.get());
		assertFalse(manager.isConnected());
	}

	@Test
	@DisplayName("ignored sensors suppress scores but do not suppress timeouts")
	void ignoredSensorsStillDeliverTimeouts() throws Exception {
		server = new ServerSocket(0);
		CountDownLatch sendSecondEvent = new CountDownLatch(1);
		startServer(socket -> {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Team:2");
			sendSecondEvent.await(2, TimeUnit.SECONDS);
			out.println("TO:2");
			new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
		});
		manager = manager(true);
		CountDownLatch timeout = new CountDownLatch(1);
		manager.setScoreEventListener(code -> {
			manager.events.add(code);
			if ("XUTT2".equals(code)) timeout.countDown();
		});

		manager.connect();
		await(manager::isConnected);
		// Give SwingWorker.process a chance to handle the ignored score separately.
		SwingUtilities.invokeAndWait(() -> { });
		sendSecondEvent.countDown();
		assertTrue(timeout.await(2, TimeUnit.SECONDS));
		assertEquals(List.of("XUTT2"), manager.events);
	}

	@Test
	@DisplayName("read protocol clears and appends configuration lines")
	void readProtocolUpdatesConfiguration() throws Exception {
		server = new ServerSocket(0);
		startServer(socket -> {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Read:");
			out.println("Line:PORT = 5050");
			out.println("Line:SENSOR1 = 2");
			new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
		});
		manager = manager(false);
		manager.config.append("stale");

		manager.connect();
		await(() -> manager.config.toString().contains("SENSOR1"));

		assertEquals("PORT = 5050\nSENSOR1 = 2\n", manager.config.toString());
	}

	private TestManager manager(boolean ignored) {
		TableConnection connection = new TableConnection("Test", "127.0.0.1",
			Integer.toString(server.getLocalPort()), false, false);
		return new TestManager(connection, ignored);
	}

	private void startServer(ServerAction action) {
		serverThread = new Thread(() -> {
			try (Socket socket = server.accept()) {
				action.run(socket);
			} catch (Exception e) {
				if (!server.isClosed()) throw new RuntimeException(e);
			}
		}, "autoscore-test-server");
		serverThread.start();
	}

	private static void await(Check check) throws Exception {
		long deadline = System.nanoTime() + Duration.ofSeconds(2).toNanos();
		while (!check.isTrue() && System.nanoTime() < deadline) {
			Thread.sleep(10);
		}
		assertTrue(check.isTrue());
	}

	@FunctionalInterface private interface Check { boolean isTrue(); }
	@FunctionalInterface private interface ServerAction { void run(Socket socket) throws Exception; }

	private static final class TestManager extends AutoScoreManager {
		private final boolean ignored;
		private final List<String> events = new ArrayList<>();
		private final StringBuilder config = new StringBuilder();

		private TestManager(TableConnection connection, boolean ignored) {
			super(connection, null, null, null);
			this.ignored = ignored;
		}

		@Override void displayMessage(String message) { }
		@Override String getConfigText() { return config.toString(); }
		@Override void clearConfigText() { config.setLength(0); }
		@Override void appendConfigText(String line) { config.append(line); }
		@Override boolean areSensorsIgnored() { return ignored; }
	}
}
