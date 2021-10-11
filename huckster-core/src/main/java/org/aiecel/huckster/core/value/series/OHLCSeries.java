package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.OHLCValue;

public interface OHLCSeries<V extends OHLCValue<?>> extends Series<V> {
    /**
     * Returns the timeframe of a series.
     *
     * @return timeframe of a series.
     */
    Timeframe timeframe();
}
