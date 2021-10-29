package org.huckster.core.value.series;

import org.huckster.core.time.FrameTimeGrid;
import org.huckster.core.time.TimeGrid;
import org.huckster.core.time.Timed;
import org.huckster.core.time.Timeframe;

import java.time.Instant;
import java.util.Collection;

public class BaseTimeframedSeries<V extends Timed> extends BaseTimedValueSeries<V> implements TimeframedSeries<V> {
    private final Timeframe timeframe;
    private TimeGrid timeGrid;

    public BaseTimeframedSeries(Timeframe timeframe) {
        this(timeframe, Instant.EPOCH);
    }

    public BaseTimeframedSeries(Timeframe timeframe, Instant alignmentTimestamp) {
        super();
        this.timeframe = timeframe;
        this.timeGrid = new FrameTimeGrid(alignmentTimestamp, timeframe.getDuration());
    }

    public BaseTimeframedSeries(Timeframe timeframe, int capacity) {
        this(timeframe, Instant.EPOCH, capacity);
    }

    public BaseTimeframedSeries(Timeframe timeframe, Instant alignmentTimestamp, int capacity) {
        super(capacity);
        this.timeframe = timeframe;
        this.timeGrid = new FrameTimeGrid(alignmentTimestamp, timeframe.getDuration());
    }

    public BaseTimeframedSeries(Timeframe timeframe, int capacity, Collection<V> candles) {
        this(timeframe, Instant.EPOCH, capacity, candles);
    }

    public BaseTimeframedSeries(Timeframe timeframe,
                                Instant alignmentTimestamp,
                                int capacity,
                                Collection<V> candles) {
        super(capacity);
        this.timeframe = timeframe;
        this.timeGrid = new FrameTimeGrid(alignmentTimestamp, timeframe.getDuration());
        addAll(sortAndRemoveWithSameTimestamp(candles)); //fixme sort and check
    }

    /**
     * Returns the max size of a series.
     *
     * @return max size of a series.
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    @Override
    public int getSize() {
        return values.size();
    }

    /**
     * Returns the timeframe of a series.
     *
     * @return timeframe of a series.
     */
    @Override
    public Timeframe getTimeframe() {
        return timeframe;
    }

    /**
     * Adds new value to a series.
     *
     * @param value value to add.
     */
    @Override
    public void add(V value) {
        if (isEmpty() ||
                (value.getTimestamp().isAfter(getLast().getTimestamp()) && timeGrid.isAligned(value.getTimestamp()))) {
            super.add(value);
            this.timeGrid = new FrameTimeGrid(value.getTimestamp(), timeframe.getDuration());
            return;
        }

        //allow the last value to be changed
        if (!isEmpty() && value.getTimestamp().equals(getLast().getTimestamp())) {
            values.set(this.getSize() - 1, value);
            return;
        }

        throw new IllegalArgumentException("Illegal value timestamp: " + value.getTimestamp());
    }
}
