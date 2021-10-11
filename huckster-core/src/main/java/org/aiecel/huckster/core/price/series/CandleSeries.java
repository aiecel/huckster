package org.aiecel.huckster.core.price.series;

import org.aiecel.huckster.core.price.PriceCandle;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.series.BaseOHLCSeries;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

public class CandleSeries extends BaseOHLCSeries<PriceCandle> implements PriceSeries {
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

    public CandleSeries(Timeframe timeframe, int capacity, Collection<PriceCandle> candles) {
        super(timeframe, capacity, candles);
    }

    public CandleSeries(Timeframe timeframe, Instant alignmentTimestamp, int capacity, Collection<PriceCandle> candles) {
        super(timeframe, alignmentTimestamp, capacity, candles);
    }

    @Override
    public BigDecimal getPrice() {
        return getLast().close();
    }
}
