package org.aiecel.huckster.ta.condition;

import lombok.Getter;
import org.aiecel.huckster.ta.indicator.Indicator;

@Getter
public class BarCrossoverCondition extends AbstractCrossoverCondition {
    private final double bar;

    public BarCrossoverCondition(Indicator<Number> signalIndicator, Number bar, CrossoverDirection direction) {
        super(signalIndicator, direction);
        this.bar = bar.doubleValue();
    }

    /**
     * Returns true if the condition at specified index is met.
     *
     * @param offset index.
     * @return true if the condition at specified index is met.
     */
    @Override
    public boolean isMetAtOffset(int offset) {
        if (offset >= signalIndicator.size() - 1) return false;
        return isMet(
                signalIndicator.getByOffset(offset + 1).doubleValue(),
                signalIndicator.getByOffset(offset).doubleValue(),
                bar, bar, direction
        );
    }
}
