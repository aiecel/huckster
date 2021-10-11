package org.aiecel.huckster.core.value;

import org.aiecel.huckster.core.time.Timed;
import org.aiecel.huckster.core.time.Timeframe;

import java.time.Instant;

public interface OHLC<N extends Number & Comparable<N>> extends Timed {
    Timeframe timeframe();

    default Instant closeTimestamp() {
        return getTimestamp().plus(timeframe().getDuration());
    }

    N open();

    N high();

    N low();

    N close();
}
