/**
Copyright © 2025-2026 Hugh Garner
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
package com.midsouthfoosball.foosobsplus.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.midsouthfoosball.foosobsplus.model.MatchObserver;

import io.javalin.http.Context;

public class EventBroadcaster implements MatchObserver {
	private static final Logger logger = LoggerFactory.getLogger(EventBroadcaster.class);
	private final List<AsyncContext> clients = new CopyOnWriteArrayList<>();
	private final ScheduledExecutorService keepAliveExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
		Thread t = new Thread(r, "SSE-KeepAlive");
		t.setDaemon(true);
		return t;
	});

	public EventBroadcaster() {
		keepAliveExecutor.scheduleAtFixedRate(this::sendKeepAlive, 30, 30, TimeUnit.SECONDS);
	}

	public void addClient(Context ctx) {
		ctx.res.setContentType("text/event-stream");
		ctx.res.setCharacterEncoding("UTF-8");
		ctx.res.setHeader("Cache-Control", "no-cache");
		ctx.res.setHeader("Connection", "keep-alive");
		ctx.status(200);

		AsyncContext asyncCtx = ctx.req.startAsync();
		asyncCtx.setTimeout(0); // No timeout — keep connection open indefinitely
		clients.add(asyncCtx);

		asyncCtx.addListener(new javax.servlet.AsyncListener() {
			@Override
			public void onComplete(javax.servlet.AsyncEvent event) {
				clients.remove(asyncCtx);
				logger.info("SSE client disconnected. Active clients: {}", clients.size());
			}
			@Override
			public void onTimeout(javax.servlet.AsyncEvent event) {
				clients.remove(asyncCtx);
				asyncCtx.complete();
			}
			@Override
			public void onError(javax.servlet.AsyncEvent event) {
				clients.remove(asyncCtx);
			}
			@Override
			public void onStartAsync(javax.servlet.AsyncEvent event) {
			}
		});

		// Send initial connected event
		sendToClient(asyncCtx, "connected", "{\"message\":\"Connected to FoosOBSPlus event stream\"}");
		logger.info("SSE client connected. Active clients: {}", clients.size());
	}

	@Override
	public void onMeatball() {
		broadcast("meatball", "{\"event\":\"meatball\"}");
	}

	@Override
	public void onScoreChange(int teamNumber, int newScore) {
		String data = String.format("{\"event\":\"score\",\"team\":%d,\"score\":%d}", teamNumber, newScore);
		broadcast("score", data);
	}

	@Override
	public void onTimeOut(int teamNumber, int timeOutsRemaining) {
		String data = String.format("{\"event\":\"timeout\",\"team\":%d,\"timeoutsRemaining\":%d}", teamNumber, timeOutsRemaining);
		broadcast("timeout", data);
	}

	private void broadcast(String eventName, String data) {
		if (clients.isEmpty()) return;
		logger.debug("Broadcasting SSE event '{}' to {} clients: {}", eventName, clients.size(), data);
		for (AsyncContext client : clients) {
			sendToClient(client, eventName, data);
		}
	}

	private void sendToClient(AsyncContext asyncCtx, String eventName, String data) {
		try {
			PrintWriter writer = asyncCtx.getResponse().getWriter();
			writer.write("event: " + eventName + "\n");
			writer.write("data: " + data + "\n\n");
			writer.flush();
			if (writer.checkError()) {
				clients.remove(asyncCtx);
			}
		} catch (IOException e) {
			logger.warn("Failed to send SSE event to client, removing: {}", e.getMessage());
			clients.remove(asyncCtx);
		}
	}

	private void sendKeepAlive() {
		for (AsyncContext client : clients) {
			try {
				PrintWriter writer = client.getResponse().getWriter();
				writer.write(": keepalive\n\n");
				writer.flush();
				if (writer.checkError()) {
					clients.remove(client);
				}
			} catch (Exception e) {
				clients.remove(client);
			}
		}
	}

	public void shutdown() {
		keepAliveExecutor.shutdown();
		for (AsyncContext client : clients) {
			try {
				client.complete();
			} catch (Exception e) {
				// ignore
			}
		}
		clients.clear();
	}
}
