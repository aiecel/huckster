package org.aiecel.huckster.core.update.handler;

import org.aiecel.huckster.core.update.Updatable;

public interface CallableUpdateHandler<T> extends UpdateHandler<T>, Updatable {
    /**
     * Triggers an update.
     */
    @Override
    void update();
}
