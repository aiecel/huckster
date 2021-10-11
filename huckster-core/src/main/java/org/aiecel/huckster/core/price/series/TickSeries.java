package org.aiecel.huckster.core.price.series;

import org.aiecel.huckster.core.price.Tick;
import org.aiecel.huckster.core.value.series.BaseSeries;

import java.math.BigDecimal;
import java.util.Collection;

public final class TickSeries extends BaseSeries<Tick> implements PriceSeries {
    public TickSeries(int capacity) {
        super(capacity);
    }

    public TickSeries(int capacity, Collection<Tick> values) {
        super(capacity, values);
    }

    @Override
    public BigDecimal price() {
        return getLast().price();
    }
}
