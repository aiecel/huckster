package org.aiecel.huckster.trading.strategy;

import org.aiecel.huckster.core.update.Updatable;

public interface Strategy extends Updatable {
    /**
     * Updates the strategy
     */
    @Override
    void update();
}
