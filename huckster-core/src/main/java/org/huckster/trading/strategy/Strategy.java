package org.huckster.trading.strategy;

import org.huckster.core.update.Updatable;

public interface Strategy extends Updatable {
    /**
     * Updates the strategy
     */
    @Override
    void update();
}
