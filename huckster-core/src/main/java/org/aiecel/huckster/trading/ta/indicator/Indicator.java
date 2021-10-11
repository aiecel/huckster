package org.aiecel.huckster.trading.ta.indicator;

public interface Indicator<V> extends Iterable<V> {
    /**
     * Returns size of the base series.
     *
     * @return size of the base series.
     */
    int size();

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
    default V getLast() {
        return getByOffset(0);
    }

    /**
     * Returns a value of an indicator by its offset (index = size - offset - 1).
     *
     * @param offset offset of a value.
     * @return a value of an indicator.
     */
    default V getByOffset(int offset) {
        return get(size() - offset - 1);
    }
}
