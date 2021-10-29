package org.huckster.core.time.schedule;

import java.time.Instant;

public interface TradingSchedule {
    boolean isTrading(Instant timestamp);
}
