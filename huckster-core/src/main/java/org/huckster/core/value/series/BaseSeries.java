package org.huckster.core.value.series;

import java.util.*;

public class BaseSeries<V> implements Series<V> {
    public static final int DEFAULT_CAPACITY = 100;

    protected final List<V> values;
    protected int capacity;
    protected int firstIndex;

    public BaseSeries() {
        this(DEFAULT_CAPACITY);
    }

    public BaseSeries(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Series capacity must be greater than 1");
        this.capacity = capacity;
        this.values = new ArrayList<>(capacity);
        this.firstIndex = 0;
    }

    public BaseSeries(int capacity, Collection<V> values) {
        if (capacity < 1) throw new IllegalArgumentException("Series capacity must be greater than 1");
        this.capacity = capacity;
        this.values = new ArrayList<>(capacity);
        this.firstIndex = 0;
        addAll(values);
    }

    /**
     * Returns the max size of a series.
     *
     * @return max size of a series.
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    @Override
    public int getSize() {
        return values.size();
    }

    /**
     * Returns an index of the first element of a series.
     *
     * @return index of the first element of a series.
     */
    @Override
    public int getFirstIndex() {
        return firstIndex;
    }

    /**
     * Returns an index of the last element of a series.
     *
     * @return index of the last element of a series.
     */
    @Override
    public int getLastIndex() {
        return values.size() == 0 ? 0 : firstIndex + values.size() - 1;
    }

    /**
     * Returns a value by its index.
     *
     * @param index index of a value.
     * @return a value.
     */
    @Override
    public V get(int index) {
        if (index < 0 && index >= this.getSize()) throw new IndexOutOfBoundsException(index);
        return values.get(index);
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
        if (isFull()) {
            values.remove(0);
            firstIndex++;
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
}
