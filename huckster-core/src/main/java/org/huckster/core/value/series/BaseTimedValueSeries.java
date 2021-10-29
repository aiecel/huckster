package org.huckster.core.value.series;

import org.huckster.core.time.Timed;

import java.util.*;

public class BaseTimedValueSeries<V extends Timed> extends BaseSeries<V> {
    public BaseTimedValueSeries() {
        super();
    }

    public BaseTimedValueSeries(int capacity) {
        super(capacity);
    }

    public BaseTimedValueSeries(int capacity, Collection<V> values) {
        super(capacity);
        addAll(sortAndRemoveWithSameTimestamp(values));
    }

    /**
     * Adds new value to a series.
     *
     * @param value value to add.
     */
    @Override
    public void add(V value) {
        if (!isEmpty() && !value.getTimestamp().isAfter(getLast().getTimestamp()))
            throw new IllegalArgumentException("Invalid price timestamp: " + value.getTimestamp());

        if (isFull()) values.remove(0);
        values.add(value);
    }

    /**
     * Adds new values to a series.
     *
     * @param values new values to add.
     */
    @Override
    public void addAll(Iterable<V> values) {
        for (V value : values) {
            add(value);
        }
    }

    protected Collection<V> sortAndRemoveWithSameTimestamp(Collection<V> values) {
        var sortedValues = values.stream().sorted(Comparator.comparing(Timed::getTimestamp)).toList();

        //remove values with the same timestamp
        Iterator<V> iterator = sortedValues.iterator();
        V previous = iterator.next();
        V current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current.getTimestamp().equals(previous.getTimestamp())) iterator.remove();
        }
        return sortedValues;
    }
}
