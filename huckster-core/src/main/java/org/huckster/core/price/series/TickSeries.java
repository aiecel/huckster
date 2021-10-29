package org.huckster.core.price.series;

import org.huckster.core.price.Tick;
import org.huckster.core.value.series.BaseTimedValueSeries;

import java.math.BigDecimal;
import java.util.Collection;

public class TickSeries extends BaseTimedValueSeries<Tick> implements PriceSeries {
    public TickSeries() {
        super();
    }

    public TickSeries(int capacity) {
        super(capacity);
    }

    public TickSeries(int capacity, Collection<Tick> values) {
        super(capacity, values);
    }

    @Override
    public BigDecimal getPrice() {
        return getLast().getPrice();
    }
}
