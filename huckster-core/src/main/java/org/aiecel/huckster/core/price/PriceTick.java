package org.aiecel.huckster.core.price;

import lombok.RequiredArgsConstructor;
import org.aiecel.huckster.core.time.Timed;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@RequiredArgsConstructor
public class PriceTick implements Timed, Priced {
    private final BigDecimal price;
    private final Instant timestamp;

    /**
     * Returns the timestamp of the price
     *
     * @return timestamp of the price
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PriceTick) obj;
        return Objects.equals(this.price, that.price) &&
                Objects.equals(this.timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, timestamp);
    }

    @Override
    public String toString() {
        return String.format("Tick {%s, %s}", price, timestamp);
    }
}
