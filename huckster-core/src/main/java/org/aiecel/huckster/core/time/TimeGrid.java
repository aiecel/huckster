package org.aiecel.huckster.core.time;

import java.time.Instant;

public interface TimeGrid {
    /**
     * Returns true if the provided timestamp is aligned with the grid.
     *
     * @param timestamp timestamp to check.
     * @return true if the provided timestamp is aligned with the grid.
     */
    boolean isAligned(Instant timestamp);

    /**
     * Returns a timestamp that is aligned to the nearest grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to the nearest grid timestamp.
     */
    Instant alignToNearest(Instant timestamp);

    /**
     * Returns a timestamp that is aligned to previous grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to previous grid timestamp.
     */
    Instant alignToPrevious(Instant timestamp);

    /**
     * Returns a timestamp that is aligned to next grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to next grid timestamp.
     */
    Instant alignToNext(Instant timestamp);
}
