package org.aiecel.huckster.core.value.series;

import org.aiecel.huckster.ta.indicator.Indicator;

import java.util.List;

public interface Series<V> extends Indicator<V> {
    /**
     * Returns the max size of a series.
     *
     * @return max size of a series.
     */
    int capacity();

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    int size();

    /**
     * Returns true if size is zero.
     *
     * @return true if size is zero.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if size is equal to capacity.
     *
     * @return true if size is equal to capacity.
     */
    default boolean isFull() {
        return size() == capacity();
    }

    /**
     * Returns a value by its index.
     *
     * @param index index of a value.
     * @return a value.
     */
    V get(int index);

    /**
     * Returns last value.
     *
     * @return last value.
     */
    default V getLast() {
        return getByOffset(0);
    }

    /**
     * Returns list of all values
     *
     * @return list of all values
     */
    List<V> getAll();

    /**
     * Returns a value by its offset (index = size - offset - 1).
     *
     * @param offset offset of a value.
     * @return a value.
     */
    default V getByOffset(int offset) {
        return get(size() - offset - 1);
    }

    /**
     * Adds new value to a series.
     *
     * @param value value to add.
     */
    void add(V value);

    /**
     * Adds new values to a series.
     *
     * @param values new values to add.
     */
    void addAll(Iterable<V> values);

    /**
     * Clears the series.
     */
    void clear();
}
