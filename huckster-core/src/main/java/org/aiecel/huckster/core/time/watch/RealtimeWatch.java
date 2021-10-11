package org.aiecel.huckster.core.time.watch;

import java.time.Instant;

public final class RealtimeWatch implements Watch {
    @Override
    public Instant getCurrentTimestamp() {
        return Instant.now();
    }
}
