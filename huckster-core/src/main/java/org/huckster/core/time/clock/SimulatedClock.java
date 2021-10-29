package org.huckster.core.time.clock;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

@Slf4j
public class SimulatedClock implements Clock {
    private Instant timestamp;

    public SimulatedClock(Instant startTimestamp) {
        this.timestamp = startTimestamp;
        log.info("Clock is initialized at timestamp: {}", timestamp);
    }

    /**
     * Returns the timestamp of a clock.
     *
     * @return {@link Instant}.
     */
    @Override
    public Instant getTime() {
        return timestamp;
    }

    /**
     * Moves the time of a clock.
     *
     * @param duration duration.
     */
    public void moveForward(Duration duration) {
        timestamp = timestamp.plus(duration);
        log.debug("Clock is moved {} ahead, current timestamp: {}", duration, timestamp);
    }

    /**
     * Moves the time of a clock.
     *
     * @param millis milliseconds.
     */
    public void moveForward(long millis) {
        timestamp = timestamp.plusMillis(millis);
        log.debug("Clock is moved {}ms ahead, current timestamp: {}", millis, timestamp);
    }
}
