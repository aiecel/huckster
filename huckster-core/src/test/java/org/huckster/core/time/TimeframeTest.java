package org.huckster.core.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeframeTest {
    @Test
    public void timeframeM1CompareToTimeframeM5isSmallerThanZero() {
        final Timeframe timeframeM1 = Timeframe.M1;
        final Timeframe timeframeM5 = Timeframe.M5;
        Assertions.assertTrue(timeframeM1.compareTo(timeframeM5) < 0);
    }

    @Test
    public void timeframeH1CompareToTimeframeM5isLargerThanZero() {
        final Timeframe timeframeH1 = Timeframe.H1;
        final Timeframe timeframeM5 = Timeframe.M5;
        Assertions.assertTrue(timeframeH1.compareTo(timeframeM5) > 0);
    }
}