package org.huckster.core.value.series;

import org.huckster.core.time.Timed;
import org.huckster.core.time.Timeframe;

public interface TimeframedSeries<V extends Timed> extends Series<V> {
    /**
     * Returns the timeframe of a series.
     *
     * @return timeframe of a series.
     */
    Timeframe getTimeframe();
}
