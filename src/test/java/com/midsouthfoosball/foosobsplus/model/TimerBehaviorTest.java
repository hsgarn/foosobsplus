package com.midsouthfoosball.foosobsplus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.LongSupplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.midsouthfoosball.foosobsplus.main.OBSInterface;

@DisplayName("Timer behavior")
class TimerBehaviorTest {
	private GameClock gameClock;
	private TimeClock timeClock;

	@AfterEach
	void stopSwingTimers() {
		if (gameClock != null) gameClock.getTimer().stop();
		if (timeClock != null) timeClock.getTimer().stop();
	}

	@Test
	@DisplayName("countdown uses elapsed tenths rather than callback count")
	void countdownUsesElapsedTime() {
		MutableClock clock = new MutableClock(10_000);
		timeClock = new TimeClock(new OBSInterface(), clock);

		timeClock.setTimer(25);
		clock.advance(700);
		timeClock.updateTime();

		assertEquals(18, timeClock.getTimeRemaining());
		assertTrue(timeClock.getTimer().isRunning());
	}

	@Test
	@DisplayName("countdown clamps at zero and stops at exact expiration")
	void countdownStopsAtZero() {
		MutableClock clock = new MutableClock(5_000);
		timeClock = new TimeClock(new OBSInterface(), clock);
		timeClock.setTimer(10);

		clock.advance(1_000);
		timeClock.updateTime();

		assertEquals(0, timeClock.getTimeRemaining());
		assertFalse(timeClock.getTimer().isRunning());
	}

	@Test
	@DisplayName("starting a countdown again replaces its duration and baseline")
	void restartingCountdownResetsIt() {
		MutableClock clock = new MutableClock(1_000);
		timeClock = new TimeClock(new OBSInterface(), clock);
		timeClock.setTimer(50);
		clock.advance(2_000);
		timeClock.setTimer(30);

		assertEquals(30, timeClock.getNbrOfSeconds());
		assertEquals(30, timeClock.getTimeRemaining());
		clock.advance(500);
		timeClock.updateTime();
		assertEquals(25, timeClock.getTimeRemaining());
	}

	@Test
	@DisplayName("game, match, and stream times are formatted independently")
	void clocksFormatIndependently() {
		gameClock = new GameClock(new OBSInterface());
		gameClock.setGameHours(1);
		gameClock.setGameMinutes(2);
		gameClock.setGameSeconds(3);
		gameClock.setMatchMinutes(12);
		gameClock.setMatchSeconds(34);
		gameClock.setStreamHours(9);
		gameClock.setStreamSeconds(8);

		assertEquals("01:02:03", gameClock.getGameTime());
		assertEquals("00:12:34", gameClock.getMatchTime());
		assertEquals("09:00:08", gameClock.getStreamTime());
	}

	@Test
	@DisplayName("timer lifecycle methods reset and change only their own running state")
	void lifecycleMethodsKeepTimersIndependent() {
		gameClock = new GameClock(new OBSInterface());
		gameClock.setGameHours(4);
		gameClock.setMatchMinutes(5);

		gameClock.startGameTimer();
		assertTrue(gameClock.isGameTimerRunning());
		assertFalse(gameClock.isMatchTimerRunning());
		assertEquals("00:00:00", gameClock.getGameTime());

		gameClock.startMatchTimer();
		gameClock.startStreamTimer();
		gameClock.stopGameTimer();
		assertFalse(gameClock.isGameTimerRunning());
		assertTrue(gameClock.isMatchTimerRunning());
		assertTrue(gameClock.isStreamTimerRunning());

		gameClock.pauseMatchTimer(true);
		gameClock.stopStreamTimer();
		assertFalse(gameClock.isMatchTimerRunning());
		assertFalse(gameClock.isStreamTimerRunning());
	}

	private static final class MutableClock implements LongSupplier {
		private long millis;
		private MutableClock(long millis) { this.millis = millis; }
		private void advance(long elapsedMillis) { millis += elapsedMillis; }
		@Override public long getAsLong() { return millis; }
	}
}
