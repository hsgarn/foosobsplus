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
package com.midsouthfoosball.foosobsplus.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple rate limiter using sliding window approach
 * Limits requests per IP address
 */
public class RateLimiter {
	private static final Logger logger = LoggerFactory.getLogger(RateLimiter.class);

	private final int maxRequestsPerMinute;
	private final Map<String, AtomicInteger> requestCounts;
	private final ScheduledExecutorService scheduler;

	public RateLimiter(int maxRequestsPerMinute) {
		this.maxRequestsPerMinute = maxRequestsPerMinute;
		this.requestCounts = new ConcurrentHashMap<>();
		this.scheduler = Executors.newSingleThreadScheduledExecutor();

		// Reset counters every minute
		scheduler.scheduleAtFixedRate(() -> {
			requestCounts.clear();
		}, 1, 1, TimeUnit.MINUTES);

		logger.info("Rate limiter initialized: {} requests per minute per IP", maxRequestsPerMinute);
	}

	/**
	 * Check if the request from this IP should be allowed
	 * @param ipAddress The client IP address
	 * @return true if request is allowed, false if rate limit exceeded
	 */
	public boolean allowRequest(String ipAddress) {
		AtomicInteger count = requestCounts.computeIfAbsent(ipAddress, k -> new AtomicInteger(0));
		int currentCount = count.incrementAndGet();

		if (currentCount > maxRequestsPerMinute) {
			logger.warn("Rate limit exceeded for IP: {} ({} requests)", ipAddress, currentCount);
			return false;
		}

		return true;
	}

	/**
	 * Shutdown the rate limiter scheduler
	 */
	public void shutdown() {
		scheduler.shutdown();
		try {
			if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
				scheduler.shutdownNow();
			}
		} catch (InterruptedException e) {
			scheduler.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
