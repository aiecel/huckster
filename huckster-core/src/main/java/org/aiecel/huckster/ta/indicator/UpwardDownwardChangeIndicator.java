package org.aiecel.huckster.ta.indicator;

public class UpwardDownwardChangeIndicator extends AbstractIndicator<Number, UpwardDownwardChange> {
    public UpwardDownwardChangeIndicator(Indicator<? extends Number> base) {
        super(base);
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public UpwardDownwardChange get(int index) {
        if (size() == 0 || index == 0) return new UpwardDownwardChange(0, 0);
        var delta = base.get(index).doubleValue() - base.get(index - 1).doubleValue();
        if (delta < 0) return new UpwardDownwardChange(0, -delta);
        return new UpwardDownwardChange(delta, 0);
    }
}
