package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.time.Timeframe;

import java.util.Set;

public interface CompoundSeries<S extends OHLCSeries<?>> {
    boolean contains(Timeframe timeframe);

    S get(Timeframe timeframe);

    Set<S> getAllSeries();
}
