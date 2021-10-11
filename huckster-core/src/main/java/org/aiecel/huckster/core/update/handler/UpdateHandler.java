package org.aiecel.huckster.core.update.handler;

import org.aiecel.huckster.core.update.UpdateListener;

public interface UpdateHandler<T> {
    /**
     * Initializes an update handler.
     */
    void init();

    /**
     * Adds an update listener to the update handler.
     *
     * @param updateListener an update listener.
     */
    void addUpdateListener(UpdateListener<T> updateListener);
}
