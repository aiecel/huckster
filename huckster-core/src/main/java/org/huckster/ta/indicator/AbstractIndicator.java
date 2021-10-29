package org.huckster.ta.indicator;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@RequiredArgsConstructor
public abstract class AbstractIndicator<I, O> implements Indicator<O> {
    protected final Indicator<? extends I> base;

    /**
     * Returns size of the base series.
     *
     * @return size of the base series.
     */
    @Override
    public int getSize() {
        return base.getSize();
    }

    /**
     * Returns an index of the first element of the base series.
     *
     * @return index of the first element of the base series.
     */
    @Override
    public int getFirstIndex() {
        return base.getFirstIndex();
    }

    /**
     * Returns an index of the last element of the base series.
     *
     * @return index of the last element of the base series.
     */
    @Override
    public int getLastIndex() {
        return base.getLastIndex();
    }

    /**
     * Returns an iterator over elements.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<O> iterator() {
        return new Iterator<>() {
            private int index = getFirstIndex() - 1;

            @Override
            public boolean hasNext() {
                return index < getLastIndex();
            }

            @Override
            public O next() {
                return get(++index);
            }
        };
    }
}
