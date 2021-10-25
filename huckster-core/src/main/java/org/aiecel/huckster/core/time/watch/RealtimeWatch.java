package org.aiecel.huckster.core.time.watch;

import java.time.Instant;

/**
 * Watch that returns current time.
 */
public final class RealtimeWatch implements Watch {
    private static RealtimeWatch instance;

    private RealtimeWatch() {
    }

    public static RealtimeWatch getInstance() {
        if (instance == null) instance = new RealtimeWatch();
        return instance;
    }

    /**
     * Returns {@link Instant} of current time.
     *
     * @return {@link Instant}.
     */
    @Override
    public Instant getCurrentTimestamp() {
        return Instant.now();
    }
}
