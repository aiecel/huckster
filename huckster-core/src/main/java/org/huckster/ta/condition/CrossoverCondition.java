package org.huckster.ta.condition;

import lombok.Getter;
import org.huckster.ta.indicator.Indicator;

@Getter
public class CrossoverCondition extends AbstractCrossoverCondition {
    private final Indicator<? extends Number> barIndicator;

    public CrossoverCondition(Indicator<? extends Number> signalIndicator, Indicator<Number> barIndicator, CrossoverDirection direction) {
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
        if (offset >= signalIndicator.getSize() - 1 || offset >= barIndicator.getSize() - 1) return false;
        return isMet(
                signalIndicator.getByOffset(offset + 1).doubleValue(),
                signalIndicator.getByOffset(offset).doubleValue(),
                barIndicator.getByOffset(offset + 1).doubleValue(),
                barIndicator.getByOffset(offset).doubleValue(),
                direction
        );
    }
}

