package org.huckster.ta.indicator;

import lombok.Getter;

public class EMAIndicator extends AbstractIndicator<Number, Double> {

    @Getter
    private final double alpha;

    public EMAIndicator(Indicator<? extends Number> base, int period) {
        this(base, period, 2);
    }

    public EMAIndicator(Indicator<? extends Number> base, int period, double smoothFactor) {
        this(base, smoothFactor / period + 1);
    }

    public EMAIndicator(Indicator<? extends Number> base, double alpha) {
        super(base);
        this.alpha = alpha;
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public Double get(int index) {
        if (getSize() == 0) return 0d;
        if (index == getFirstIndex()) return base.get(getFirstIndex()).doubleValue();
        return alpha * base.get(index).doubleValue() + (1 - alpha) * get(index - 1);
    }

    @Override
    public String toString() {
        return String.format("EMA {alpha: %s}", alpha);
    }
}
