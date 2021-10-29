package org.huckster.ta.indicator;

import lombok.Getter;

public class StandardDeviationIndicator extends AbstractIndicator<Number, Double> {
    private final Indicator<Number> meanIndicator;

    @Getter
    private final int period;

    public StandardDeviationIndicator(Indicator<? extends Number> base, Indicator<Number> meanIndicator, int period) {
        super(base);
        this.meanIndicator = meanIndicator;
        this.period = period;
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

        var actualLength = Math.min(period, index - getFirstIndex());
        var sequenceStartIndex = index + 1 - actualLength;

        var sum = 0d;
        for (int i = sequenceStartIndex; i <= index; i++) {
            sum += Math.pow(base.get(i).doubleValue() - meanIndicator.get(i).doubleValue(), 2);
        }

        return Math.sqrt(sum / actualLength);
    }

    @Override
    public String toString() {
        return String.format("STD {mean: %s, period: %s}", meanIndicator, period);
    }
}
