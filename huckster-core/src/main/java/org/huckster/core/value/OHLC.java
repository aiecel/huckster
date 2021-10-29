package org.huckster.core.value;

import org.huckster.core.time.Timed;
import org.huckster.core.time.Timeframe;

import java.time.Instant;

public interface OHLC<N extends Number & Comparable<N>> extends Timed {
    Timeframe getTimeframe();

    default Instant getCloseTimestamp() {
        return getTimestamp().plus(getTimeframe().getDuration());
    }

    N getOpen();

    N getHigh();

    N getLow();

    N getClose();
}
