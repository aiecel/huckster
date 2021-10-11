package org.aiecel.huckster.core.time.watch;

import java.time.Instant;

public final class RealtimeWatch implements Watch {
    private static RealtimeWatch instance;

    private RealtimeWatch() {
    }

    public static RealtimeWatch getInstance() {
        if (instance == null) instance = new RealtimeWatch();
        return instance;
    }

    @Override
    public Instant getCurrentTimestamp() {
        return Instant.now();
    }
}
