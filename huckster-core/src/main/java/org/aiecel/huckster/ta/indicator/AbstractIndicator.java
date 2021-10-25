package org.aiecel.huckster.ta.indicator;

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
    public int size() {
        return base.size();
    }

    /**
     * Returns an iterator over elements.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<O> iterator() {
        return new Iterator<>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index < size() - 1;
            }

            @Override
            public O next() {
                return get(++index);
            }
        };
    }
}
