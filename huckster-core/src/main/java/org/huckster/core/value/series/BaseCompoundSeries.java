package org.huckster.core.value.series;

import org.huckster.core.time.Timeframe;

import java.util.*;

public class BaseCompoundSeries<S extends TimeframedSeries<?>> implements CompoundSeries<S> {
    protected final Map<Timeframe, S> seriesMap;

    public BaseCompoundSeries() {
        this.seriesMap = new HashMap<>();
    }

    public BaseCompoundSeries(Set<S> series) {
        this();
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
        seriesMap.put(series.getTimeframe(), series);
    }
}
