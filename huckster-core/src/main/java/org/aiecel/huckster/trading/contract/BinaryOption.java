package org.aiecel.huckster.trading.contract;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public interface BinaryOption extends Contract {
    BigDecimal price();

    Duration duration();

    Instant expirationTimestamp();

    enum Type {
        CALL, PUT
    }
}
