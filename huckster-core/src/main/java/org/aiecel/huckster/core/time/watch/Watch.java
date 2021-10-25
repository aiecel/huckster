package org.aiecel.huckster.core.time.watch;

import java.time.Instant;

/**
 * Interface for a watch.
 */
public interface Watch {
    /**
     * Returns the timestamp of a watch.
     *
     * @return {@link Instant}.
     */
    Instant getCurrentTimestamp();
}
