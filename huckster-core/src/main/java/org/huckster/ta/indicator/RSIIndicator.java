package org.huckster.ta.indicator;

import lombok.Getter;

public class RSIIndicator extends AbstractIndicator<Number, Double> {
    private final EMAIndicator upwardEMAIndicator;
    private final EMAIndicator downwardEMAIndicator;

    @Getter
    private final int period;

    public RSIIndicator(Indicator<? extends Number> base, int period) {
        super(base);
        this.period = period;
        var upwardDownwardChangeIndicator = new UpwardDownwardChangeIndicator(base);
        upwardEMAIndicator = new EMAIndicator(
                new ValueIndicator<>(upwardDownwardChangeIndicator, UpwardDownwardChange::upwardChange),
                1d / period
        );
        downwardEMAIndicator = new EMAIndicator(
                new ValueIndicator<>(upwardDownwardChangeIndicator, UpwardDownwardChange::downwardChange),
                1d / period
        );
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public Double get(int index) {
        var upwardEMA = upwardEMAIndicator.get(index);
        var downwardEMA = downwardEMAIndicator.get(index);
        if (downwardEMA == 0) return 100d;
        return 100 * upwardEMA / (upwardEMA + downwardEMA);
    }

    @Override
    public String toString() {
        return String.format("RSI {period: %s}", period);
    }
}
