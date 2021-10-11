package org.aiecel.huckster.trading.ta.indicator;

import org.aiecel.huckster.core.value.OHLC;

import java.util.function.Function;

public class OHLCValueIndicator extends AbstractIndicator<OHLC<?>, Number> {
    private final Function<OHLC<?>, ? extends Number> priceFunction;

    public OHLCValueIndicator(Indicator<? extends OHLC<?>> baseSeries) {
        this(baseSeries, OHLC::close);
    }

    public OHLCValueIndicator(Indicator<? extends OHLC<?>> base, Function<OHLC<?>, ? extends Number> priceFunction) {
        super(base);
        this.priceFunction = priceFunction;
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public Number get(int index) {
        return priceFunction.apply(base.get(index));
    }
}
