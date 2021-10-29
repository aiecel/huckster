package org.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.update.UpdateListener;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class used for handling update process of an object.
 *
 * @param <T> type of updating object.
 */
@Slf4j
public abstract class AbstractUpdateHandler<T> implements UpdateHandler<T> {
    private final Collection<UpdateListener<T>> updateListeners;
    protected final T updatingObject;

    public AbstractUpdateHandler(T updatingObject) {
        this.updatingObject = updatingObject;
        this.updateListeners = new ArrayList<>();
    }

    /**
     * Adds an update listener to the update handler.
     *
     * @param updateListener an update listener.
     */
    @Override
    public void addUpdateListener(UpdateListener<T> updateListener) {
        updateListeners.add(updateListener);
    }

    /**
     * Initializes an update handler.
     */
    @Override
    public void init() {
        log.debug("Update handler for {} is initialized", updatingObject);
    }

    protected void notifyListeners() {
        log.debug("Notifying update listeners");
        for (UpdateListener<T> updateListener : updateListeners) {
            updateListener.onUpdate(updatingObject);
        }
    }
}
