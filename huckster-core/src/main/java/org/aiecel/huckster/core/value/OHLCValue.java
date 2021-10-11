package org.aiecel.huckster.core.value;

import org.aiecel.huckster.core.time.Timeframe;

import java.time.Instant;

public interface OHLCValue<N extends Number & Comparable<N>> extends TimedValue {
    Timeframe timeframe();

    default Instant closeTimestamp() {
        return timestamp().plus(timeframe().getDuration());
    }

    N open();

    N high();

    N low();

    N close();
}
