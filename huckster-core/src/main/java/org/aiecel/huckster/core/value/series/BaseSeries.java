package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.core.value.TimedValue;

import java.util.*;

public class BaseSeries<V extends TimedValue> implements Series<V> {
    public static final int DEFAULT_CAPACITY = 10;

    protected final List<V> values;
    protected int capacity;

    public BaseSeries() {
        this(DEFAULT_CAPACITY);
    }

    public BaseSeries(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Series capacity must be greater than 1");
        this.capacity = capacity;
        this.values = new ArrayList<>(capacity);
    }

    public BaseSeries(int capacity, Collection<V> values) {
        if (capacity < 1) throw new IllegalArgumentException("Series capacity must be greater than 1");
        this.capacity = capacity;
        this.values = new ArrayList<>(capacity);
        addAll(sortAndRemoveWithSameTimestamp(values));
    }

    /**
     * Returns the max size of a series.
     *
     * @return max size of a series.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    @Override
    public int size() {
        return values.size();
    }

    /**
     * Returns a value by its index.
     *
     * @param index index of a value.
     * @return a value.
     */
    @Override
    public V get(int index) {
        if (index < 0 && index >= size()) throw new IndexOutOfBoundsException(index);
        return values.get(index);
    }

    /**
     * Returns last value.
     *
     * @return last value.
     */
    @Override
    public V getLast() {
        if (size() == 0) throw new IllegalStateException("The series is empty");
        return values.get(size() - 1);
    }

    /**
     * Returns list of all values
     *
     * @return list of all values
     */
    @Override
    public List<V> getAll() {
        return new ArrayList<>(values);
    }

    /**
     * Adds new value to a series.
     *
     * @param value value to add.
     */
    @Override
    public void add(V value) {
        if (!isEmpty() && !value.timestamp().isAfter(getLast().timestamp()))
            throw new IllegalArgumentException("Invalid price timestamp: " + value.timestamp());

        if (isFull()) {
            values.remove(0);
        }
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

    /**
     * Clears the series.
     */
    @Override
    public void clear() {
        values.clear();
    }

    /**
     * Returns an iterator over values.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return values.listIterator();
    }

    protected Collection<V> sortAndRemoveWithSameTimestamp(Collection<V> values) {
        var sortedValues = values.stream().sorted(Comparator.comparing(TimedValue::timestamp)).toList();

        //remove values with the same timestamp
        Iterator<V> iterator = sortedValues.iterator();
        V previous = iterator.next();
        V current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current.timestamp().equals(previous.timestamp())) iterator.remove();
        }
        return sortedValues;
    }
}
