package org.aiecel.huckster.core.update;

/**
 * Interface for listeners of {@link org.aiecel.huckster.core.update.handler.UpdateHandler} updates.
 *
 * @param <T> type of the object updated by the {@link org.aiecel.huckster.core.update.handler.UpdateHandler}.
 */
public interface UpdateListener<T> {
    /**
     * Called when the object has been updated.
     *
     * @param object updated object.
     */
    void onUpdate(T object);
}
