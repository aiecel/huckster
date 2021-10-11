package org.aiecel.huckster.core.price;

import org.aiecel.huckster.core.value.TimedValue;

import java.math.BigDecimal;
import java.time.Instant;

public record Tick(BigDecimal price, Instant timestamp) implements TimedValue, Priced {
    /**
     * Returns the timestamp of the price
     *
     * @return timestamp of the price
     */
    @Override
    public Instant timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Tick {%s, %s}", price, timestamp);
    }
}
