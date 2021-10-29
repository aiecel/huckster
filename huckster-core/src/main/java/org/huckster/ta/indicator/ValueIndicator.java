package org.huckster.ta.indicator;

import java.util.function.Function;

public class ValueIndicator<I, O> extends AbstractIndicator<I, O> {
    private final Function<I, O> valueFunction;

    public ValueIndicator(Indicator<? extends I> base, Function<I, O> valueFunction) {
        super(base);
        this.valueFunction = valueFunction;
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public O get(int index) {
        return valueFunction.apply(base.get(index));
    }
}
