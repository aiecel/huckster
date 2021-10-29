package org.huckster.core.value.series;

import org.huckster.core.price.Tick;
import org.huckster.core.price.series.TickSeries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

class TickSeriesTest {
    private final ArrayList<Tick> testTicks = new ArrayList<>();
    private TickSeries testSeries;

    {
        testTicks.add(new Tick(BigDecimal.valueOf(1), Instant.ofEpochSecond(10)));
        testTicks.add(new Tick(BigDecimal.valueOf(2), Instant.ofEpochSecond(20)));
        testTicks.add(new Tick(BigDecimal.valueOf(3), Instant.ofEpochSecond(30)));
    }

    @Test
    void initializationWithCollection() {
        testSeries = new TickSeries(5, testTicks);
        Assertions.assertIterableEquals(testTicks, testSeries.getAll());
    }

    @Test
    void addTickToEmpty() {
        testSeries = new TickSeries(5);

        var testTick = new Tick(BigDecimal.valueOf(10), Instant.ofEpochSecond(10));
        var expected = new ArrayList<>();
        expected.add(testTick);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addTickWhenNotFull() {
        testSeries = new TickSeries(5, testTicks);

        var testTick = new Tick(BigDecimal.valueOf(10), Instant.ofEpochSecond(40));
        var expected = new ArrayList<>(testTicks);
        expected.add(testTick);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addTickWhenFull() {
        testSeries = new TickSeries(3, testTicks);

        var testTick = new Tick(BigDecimal.valueOf(10), Instant.ofEpochSecond(40));
        var expected = new ArrayList<>(testTicks);
        expected.add(testTick);
        expected.remove(0);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addIllegalTickThrowsException() {
        testSeries = new TickSeries(5, testTicks);
        var testTick = new Tick(BigDecimal.valueOf(10), Instant.ofEpochSecond(20));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testSeries.add(testTick));
    }
}