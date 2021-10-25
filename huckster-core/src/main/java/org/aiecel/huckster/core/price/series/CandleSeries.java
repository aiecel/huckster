package org.aiecel.huckster.core.price.series;

import org.aiecel.huckster.core.price.Candle;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.series.BaseOHLCSeries;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

public class CandleSeries extends BaseOHLCSeries<Candle> implements PriceSeries {
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

    @Override
    public BigDecimal getPrice() {
        return getLast().close();
    }
}
