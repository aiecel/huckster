package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.time.FrameTimeGrid;
import org.aiecel.huckster.core.time.TimeGrid;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.OHLCValue;

import java.time.Instant;
import java.util.Collection;

public class BaseOHLCSeries<V extends OHLCValue<?>> extends BaseSeries<V> implements OHLCSeries<V> {
    private final Timeframe timeframe;
    private final TimeGrid timeGrid;

    public BaseOHLCSeries(Timeframe timeframe) {
        this(timeframe, Instant.EPOCH);
    }

    public BaseOHLCSeries(Timeframe timeframe, Instant alignmentTimestamp) {
        super();
        this.timeframe = timeframe;
        this.timeGrid = new FrameTimeGrid(alignmentTimestamp, timeframe.getDuration());
    }

    public BaseOHLCSeries(Timeframe timeframe, int capacity) {
        this(timeframe, Instant.EPOCH, capacity);
    }

    public BaseOHLCSeries(Timeframe timeframe, Instant alignmentTimestamp, int capacity) {
        super(capacity);
        this.timeframe = timeframe;
        this.timeGrid = new FrameTimeGrid(alignmentTimestamp, timeframe.getDuration());
    }

    public BaseOHLCSeries(Timeframe timeframe, int capacity, Collection<V> candles) {
        this(timeframe, Instant.EPOCH, capacity, candles);
    }

    public BaseOHLCSeries(Timeframe timeframe,
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
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    @Override
    public int size() {
        return values.size();
    }

    /**
     * Returns the timeframe of a series.
     *
     * @return timeframe of a series.
     */
    @Override
    public Timeframe timeframe() {
        return timeframe;
    }

    /**
     * Adds new value to a series.
     *
     * @param value value to add.
     */
    @Override
    public void add(V value) {
        if (value.timeframe() != timeframe())
            throw new IllegalArgumentException("Illegal value timeframe: " + value.timestamp());

        if (isEmpty() ||
                (value.timestamp().isAfter(getLast().timestamp()) && timeGrid.isAligned(value.timestamp()))) {
            super.add(value);
            return;
        }

        //allow the last value to be changed
        if (!isEmpty() && value.timestamp().equals(getLast().timestamp())) {
            values.set(size() - 1,  value);
            return;
        }

        throw new IllegalArgumentException("Illegal value timestamp: " + value.timestamp());
    }
}
