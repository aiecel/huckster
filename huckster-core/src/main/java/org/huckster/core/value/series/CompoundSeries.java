package org.huckster.core.value.series;

import org.huckster.core.time.Timeframe;

import java.util.Set;

public interface CompoundSeries<S extends TimeframedSeries<?>> {
    boolean contains(Timeframe timeframe);

    S get(Timeframe timeframe);

    Set<S> getAllSeries();
}
