package org.huckster.core.price.series;

import org.huckster.core.price.Candle;
import org.huckster.core.time.Timeframe;
import org.huckster.core.value.series.BaseTimeframedSeries;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

public class CandleSeries extends BaseTimeframedSeries<Candle> implements PriceSeries {
    public CandleSeries(Timeframe timeframe) {
        super(timeframe);
    }

    public CandleSeries(Timeframe timeframe, Instant alignmentTimestamp) {
        super(timeframe, alignmentTimestamp);
    }

    public CandleSeries(Timeframe timeframe, int capacity) {
        super(timeframe, capacity);
    }

    public CandleSeries(Timeframe timeframe, Instant alignmentTimestamp, int capacity) {
        super(timeframe, alignmentTimestamp, capacity);
    }

    public CandleSeries(Timeframe timeframe, int capacity, Collection<Candle> candles) {
        super(timeframe, capacity, candles);
    }

    public CandleSeries(Timeframe timeframe, Instant alignmentTimestamp, int capacity, Collection<Candle> candles) {
        super(timeframe, alignmentTimestamp, capacity, candles);
    }

    /**
     * Adds new candle to a series.
     *
     * @param candle candle to add.
     */
    @Override
    public void add(Candle candle) {
        if (candle.getTimeframe() != getTimeframe())
            throw new IllegalArgumentException("Illegal value timeframe: " + candle.getTimeframe());

        super.add(candle);
    }

    @Override
    public BigDecimal getPrice() {
        return getLast().getClose();
    }
}
