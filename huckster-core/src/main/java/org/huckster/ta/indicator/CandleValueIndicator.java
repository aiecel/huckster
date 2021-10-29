package org.huckster.ta.indicator;

import org.huckster.core.price.Candle;

import java.util.function.Function;

public class CandleValueIndicator extends ValueIndicator<Candle, Number> {
    public CandleValueIndicator(Indicator<? extends Candle> base) {
        super(base, Candle::getClose);
    }

    public CandleValueIndicator(Indicator<? extends Candle> base,
                                Function<Candle, Number> valueFunction) {
        super(base, valueFunction);
    }
}
