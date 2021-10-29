package org.huckster.core.value.series;

import org.huckster.ta.indicator.Indicator;

import java.util.List;

public interface Series<V> extends Indicator<V> {
    /**
     * Returns the max size of a series.
     *
     * @return max size of a series.
     */
    int getCapacity();

    /**
     * Returns the current size of a series.
     *
     * @return current size of a series.
     */
    int getSize();

    /**
     * Returns an index of the first element of a series.
     *
     * @return index of the first element of a series.
     */
    int getFirstIndex();

    /**
     * Returns an index of the last element of a series.
     *
     * @return index of the last element of a series.
     */
    int getLastIndex();

    /**
     * Returns true if size is zero.
     *
     * @return true if size is zero.
     */
    default boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Returns true if size is equal to capacity.
     *
     * @return true if size is equal to capacity.
     */
    default boolean isFull() {
        return getSize() == getCapacity();
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
    default V getFirst() {
        return get(getFirstIndex());
    }

    /**
     * Returns last value.
     *
     * @return last value.
     */
    default V getLast() {
        return get(getLastIndex());
    }

    /**
     * Returns list of all values
     *
     * @return list of all values
     */
    List<V> getAll();

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
