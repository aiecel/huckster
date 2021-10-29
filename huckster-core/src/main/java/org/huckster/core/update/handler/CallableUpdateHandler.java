package org.huckster.core.update.handler;

import org.huckster.core.update.Updatable;

/**
 * Interface for classes used for handling update process of an object.
 * Update is triggered by calling the update() method.
 *
 * @param <T> type of updating object.
 */
public interface CallableUpdateHandler<T> extends UpdateHandler<T>, Updatable {
    /**
     * Triggers an update.
     */
    @Override
    void update();
}
