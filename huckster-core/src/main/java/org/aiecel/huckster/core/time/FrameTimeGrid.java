package org.aiecel.huckster.core.time;

import java.time.Duration;
import java.time.Instant;

/**
 * Time grid with equally distributed time frames.
 */
public class FrameTimeGrid implements TimeGrid {
    private final long alignmentEpochMilli;
    private final long timeframeMillis;

    public FrameTimeGrid(Instant alignmentTimestamp, Duration frameDuration) {
        this.alignmentEpochMilli = alignmentTimestamp.toEpochMilli();
        this.timeframeMillis = frameDuration.toMillis();
    }

    /**
     * Returns true if the provided timestamp is aligned with the grid.
     *
     * @param timestamp timestamp to check.
     * @return true if the provided timestamp is aligned with the grid.
     */
    @Override
    public boolean isAligned(Instant timestamp) {
        return nearestAlignedTimestampOffsetMillis(timestamp) == 0;
    }

    /**
     * Returns a timestamp that is aligned to the nearest grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to the nearest grid timestamp.
     */
    @Override
    public Instant alignToNearest(Instant timestamp) {
        return timestamp.minus(Duration.ofMillis(nearestAlignedTimestampOffsetMillis(timestamp)));
    }

    /**
     * Returns a timestamp that is aligned to previous grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to previous grid timestamp.
     */
    @Override
    public Instant alignToPrevious(Instant timestamp) {
        var nearestAlignedTimestamp = alignToNearest(timestamp);
        return nearestAlignedTimestamp.isBefore(timestamp) ?
                nearestAlignedTimestamp : nearestAlignedTimestamp.minusMillis(timeframeMillis);
    }

    /**
     * Returns a timestamp that is aligned to next grid timestamp.
     *
     * @param timestamp timestamp to align.
     * @return a timestamp that is aligned to next grid timestamp.
     */
    @Override
    public Instant alignToNext(Instant timestamp) {
        var nearestAlignedTimestamp = alignToNearest(timestamp);
        return nearestAlignedTimestamp.isAfter(timestamp) ?
                nearestAlignedTimestamp : nearestAlignedTimestamp.plusMillis(timeframeMillis);
    }

    @Override
    public String toString() {
        return String.format(
                "Timeframe Anchor {%s, %s}",
                Instant.ofEpochMilli(alignmentEpochMilli),
                Duration.ofMillis(timeframeMillis)
        );
    }

    private long nearestAlignedTimestampOffsetMillis(Instant timestamp) {
        var timestampMillis = timestamp.toEpochMilli();
        var offsetMillis = timestampMillis - alignmentEpochMilli;
        offsetMillis %= timeframeMillis;
        if (offsetMillis == 0) return 0;
        if (Math.abs(offsetMillis) > timeframeMillis / 2) {
            if (timestampMillis > alignmentEpochMilli) {
                offsetMillis -= timeframeMillis;
            } else {
                offsetMillis += timeframeMillis;
            }
        }
        return offsetMillis;
    }
}

