package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.time.Timeframe;

import java.util.*;

public class BaseMultiTimeframeOHLCSeries<S extends OHLCSeries<?>> implements MultiTimeframeOHLCSeries<S> {
    protected final Map<Timeframe, S> seriesMap;

    public BaseMultiTimeframeOHLCSeries(Set<S> series) {
        this.seriesMap = new HashMap<>();
        for (S s : series) {
            addSeries(s);
        }
    }

    @Override
    public boolean contains(Timeframe timeframe) {
        return seriesMap.containsKey(timeframe);
    }

    @Override
    public S get(Timeframe timeframe) {
        if (!contains(timeframe)) throw new IllegalStateException("No series found for timeframe " + timeframe);
        return seriesMap.get(timeframe);
    }

    @Override
    public Set<S> getAllSeries() {
        return new HashSet<>(seriesMap.values());
    }

    protected void addSeries(S series) {
        seriesMap.put(series.timeframe(), series);
    }
}
