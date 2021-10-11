package org.aiecel.huckster.core.price.series;

import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.series.BaseMultiTimeframeOHLCSeries;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public final class MultiTimeframeCandleSeries extends BaseMultiTimeframeOHLCSeries<CandleSeries> implements PriceSeries {
    public MultiTimeframeCandleSeries(Set<CandleSeries> series) {
        super(series);
    }

    public static MultiTimeframeCandleSeries withTimeframes(Timeframe... timeframes) {
        var series = new HashSet<CandleSeries>();
        for (Timeframe timeframe : timeframes) {
            series.add(new CandleSeries(timeframe));
        }
        return new MultiTimeframeCandleSeries(series);
    }

    @Override
    public BigDecimal price() {
        if (seriesMap.size() == 0) return BigDecimal.ZERO;
        var priceSeries = seriesMap.values().stream().findFirst().get();
        if (priceSeries.isEmpty()) return BigDecimal.ZERO;
        return priceSeries.getLast().price();
    }
}
