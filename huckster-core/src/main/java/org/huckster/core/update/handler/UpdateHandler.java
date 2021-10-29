package org.huckster.core.update.handler;

import org.huckster.core.update.UpdateListener;

/**
 * Interface for classes used for handling update process of an object.
 *
 * @param <T> type of updating object.
 */
public interface UpdateHandler<T> {
    /**
     * Initializes an update handler.
     */
    void init();

    /**
     * Adds an update listener to the update handler.
     *
     * @param updateListener an {@link UpdateListener}.
     */
    void addUpdateListener(UpdateListener<T> updateListener);
}
