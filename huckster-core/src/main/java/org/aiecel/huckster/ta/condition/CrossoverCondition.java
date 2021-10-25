package org.aiecel.huckster.ta.condition;

import lombok.Getter;
import org.aiecel.huckster.ta.indicator.Indicator;

@Getter
public class CrossoverCondition extends AbstractCrossoverCondition {
    private final Indicator<Number> barIndicator;

    public CrossoverCondition(Indicator<Number> signalIndicator, Indicator<Number> barIndicator, CrossoverDirection direction) {
        super(signalIndicator, direction);
        this.barIndicator = barIndicator;
    }

    /**
     * Returns true if the condition at specified index is met.
     *
     * @param offset index.
     * @return true if the condition at specified index is met.
     */
    @Override
    public boolean isMetAtOffset(int offset) {
        if (offset >= signalIndicator.size() - 1 || offset >= barIndicator.size() - 1) return false;
        return isMet(
                signalIndicator.getByOffset(offset + 1).doubleValue(),
                signalIndicator.getByOffset(offset).doubleValue(),
                barIndicator.getByOffset(offset + 1).doubleValue(),
                barIndicator.getByOffset(offset).doubleValue(),
                direction
        );
    }
}

