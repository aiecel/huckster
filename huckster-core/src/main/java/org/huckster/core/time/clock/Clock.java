package org.huckster.core.time.clock;

import java.time.Instant;

/**
 * Interface for a clock.
 */
public interface Clock {
    /**
     * Returns the timestamp of a clock.
     *
     * @return {@link Instant}.
     */
    Instant getTime();
}
