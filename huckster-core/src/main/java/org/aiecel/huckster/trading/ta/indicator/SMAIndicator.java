package org.aiecel.huckster.trading.ta.indicator;

import lombok.Getter;

public class SMAIndicator extends AbstractIndicator<Number, Number> {
    @Getter
    private final int period;

    public SMAIndicator(Indicator<? extends Number> base, int period) {
        super(base);
        this.period = period;
    }

    @Override
    public Double get(int index) {
        if (size() == 0) return 0d;

        var actualLength = Math.min(period, size());
        var sequenceStartIndex = index + 1 - actualLength;

        var sum = 0d;
        for (int i = sequenceStartIndex; i <= index; i++) {
            sum += base.get(i).doubleValue();
        }

        return sum / period;
    }
}
