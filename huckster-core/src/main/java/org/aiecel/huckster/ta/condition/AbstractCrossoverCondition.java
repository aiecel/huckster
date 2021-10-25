package org.aiecel.huckster.ta.condition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aiecel.huckster.ta.indicator.Indicator;

@Getter
@RequiredArgsConstructor
public abstract class AbstractCrossoverCondition implements Condition {
    protected final Indicator<Number> signalIndicator;
    protected final CrossoverDirection direction;

    /**
     * Returns true if the condition at specified offset is met.
     *
     * @param offset index.
     * @return true if the condition at specified offset is met.
     */
    @Override
    public boolean isMetAtOffset(int offset) {
        return false;
    }

    protected boolean isMet(double signalBefore, double signalAfter,
                            double barBefore, double barAfter,
                            CrossoverDirection direction) {
        if (direction == CrossoverDirection.UP && signalBefore < barBefore && signalAfter > barAfter) return true;
        return direction == CrossoverDirection.DOWN && signalBefore > barBefore && signalAfter < barAfter;
    }
}
