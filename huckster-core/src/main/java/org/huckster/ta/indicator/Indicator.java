package org.huckster.ta.indicator;

public interface Indicator<V> extends Iterable<V> {
    /**
     * Returns size of the base series.
     *
     * @return size of the base series.
     */
    int getSize();

    /**
     * Returns an index of the first element of the base series.
     *
     * @return index of the first element of the base series.
     */
    int getFirstIndex();

    /**
     * Returns an index of the last element of the base series.
     *
     * @return index of the last element of the base series.
     */
    int getLastIndex();

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    V get(int index);

    /**
     * Returns last value of an indicator.
     *
     * @return last value of the indicator.
     */
    default V getFirst() {
        return get(getFirstIndex());
    }

    /**
     * Returns last value of an indicator.
     *
     * @return last value of the indicator.
     */
    default V getLast() {
        return get(getLastIndex());
    }

    /**
     * Returns a value of an indicator by its offset (index = size - offset - 1).
     *
     * @param offset offset of a value.
     * @return a value of an indicator.
     */
    default V getByOffset(int offset) {
        return get(getSize() - offset - 1);
    }
}
