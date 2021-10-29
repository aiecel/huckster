package org.huckster.core.update;

import org.huckster.core.update.handler.UpdateHandler;

/**
 * Interface for listeners of {@link UpdateHandler} updates.
 *
 * @param <T> type of the object updated by the {@link UpdateHandler}.
 */
public interface UpdateListener<T> {
    /**
     * Called when the object has been updated.
     *
     * @param object updated object.
     */
    void onUpdate(T object);
}
