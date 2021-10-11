package org.aiecel.huckster.core.value;

import java.time.Instant;

public interface TimedValue {
    /**
     * Returns the timestamp of the price
     *
     * @return timestamp of the price
     */
    Instant timestamp();
}
