package org.huckster.core.time;

import java.time.Instant;

public interface Timed {
    /**
     * Returns the timestamp
     *
     * @return timestamp
     */
    Instant getTimestamp();
}
