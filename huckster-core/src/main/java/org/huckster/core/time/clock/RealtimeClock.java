package org.huckster.core.time.clock;

import java.time.Instant;

/**
 * Clock that returns current time.
 */
public final class RealtimeClock implements Clock {
    private static RealtimeClock instance;

    private RealtimeClock() {
    }

    public static RealtimeClock getInstance() {
        if (instance == null) instance = new RealtimeClock();
        return instance;
    }

    /**
     * Returns {@link Instant} of current time.
     *
     * @return {@link Instant}.
     */
    @Override
    public Instant getTime() {
        return Instant.now();
    }
}
