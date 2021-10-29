package org.huckster.core.price.series;

import org.huckster.core.time.Timeframe;
import org.huckster.core.value.series.BaseCompoundSeries;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CompoundCandleSeries extends BaseCompoundSeries<CandleSeries> implements PriceSeries {
    public CompoundCandleSeries() {
        super();
    }

    public CompoundCandleSeries(Set<CandleSeries> series) {
        super(series);
    }

    public static CompoundCandleSeries withTimeframes(Timeframe... timeframes) {
        var series = new HashSet<CandleSeries>();
        for (Timeframe timeframe : timeframes) {
            series.add(new CandleSeries(timeframe));
        }
        return new CompoundCandleSeries(series);
    }

    @Override
    public BigDecimal getPrice() {
        if (seriesMap.size() == 0) return BigDecimal.ZERO;
        var priceSeries = seriesMap.values().stream().findFirst().get();
        if (priceSeries.isEmpty()) return BigDecimal.ZERO;
        return priceSeries.getLast().getPrice();
    }
}
