package org.huckster.ta.condition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.huckster.ta.indicator.Indicator;

@Getter
@RequiredArgsConstructor
public abstract class AbstractCrossoverCondition implements Condition {
    protected final Indicator<? extends Number> signalIndicator;
    protected final CrossoverDirection direction;

    protected boolean isMet(double signalBefore, double signalAfter,
                            double barBefore, double barAfter,
                            CrossoverDirection direction) {
        if (direction != CrossoverDirection.DOWN && signalBefore < barBefore && signalAfter > barAfter) return true;
        return direction != CrossoverDirection.UP && signalBefore > barBefore && signalAfter < barAfter;
    }
}
