package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.price.PriceTick;
import org.aiecel.huckster.core.price.series.TickSeries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

class PriceTickSeriesTest {
    private final ArrayList<PriceTick> testTicks = new ArrayList<>();
    private TickSeries testSeries;

    {
        testTicks.add(new PriceTick(BigDecimal.valueOf(1), Instant.ofEpochSecond(10)));
        testTicks.add(new PriceTick(BigDecimal.valueOf(2), Instant.ofEpochSecond(20)));
        testTicks.add(new PriceTick(BigDecimal.valueOf(3), Instant.ofEpochSecond(30)));
    }

    @Test
    void initializationWithCollection() {
        testSeries = new TickSeries(5, testTicks);
        Assertions.assertIterableEquals(testTicks, testSeries.getAll());
    }

    @Test
    void addTickToEmpty() {
        testSeries = new TickSeries(5);

        var testTick = new PriceTick(BigDecimal.valueOf(10), Instant.ofEpochSecond(10));
        var expected = new ArrayList<>();
        expected.add(testTick);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addTickWhenNotFull() {
        testSeries = new TickSeries(5, testTicks);

        var testTick = new PriceTick(BigDecimal.valueOf(10), Instant.ofEpochSecond(40));
        var expected = new ArrayList<>(testTicks);
        expected.add(testTick);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addTickWhenFull() {
        testSeries = new TickSeries(3, testTicks);

        var testTick = new PriceTick(BigDecimal.valueOf(10), Instant.ofEpochSecond(40));
        var expected = new ArrayList<>(testTicks);
        expected.add(testTick);
        expected.remove(0);

        testSeries.add(testTick);

        Assertions.assertIterableEquals(expected, testSeries.getAll());
    }

    @Test
    void addIllegalTickThrowsException() {
        testSeries = new TickSeries(5, testTicks);
        var testTick = new PriceTick(BigDecimal.valueOf(10), Instant.ofEpochSecond(20));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testSeries.add(testTick));
    }
}