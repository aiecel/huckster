package org.aiecel.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * Abstract class used for handling update process of an object.
 * Update is triggered by calling the update() method.
 *
 * @param <T> type of updating object.
 */
@Slf4j
public abstract class AbstractCallableUpdateHandler<T> extends AbstractUpdateHandler<T> implements CallableUpdateHandler<T> {
    public AbstractCallableUpdateHandler(T updatingObject) {
        super(updatingObject);
    }

    /**
     * Triggers an update.
     */
    @Override
    public void update() {
        log.debug("Updating object: {}", updatingObject);
        updateObject();
        log.debug("Object {} has been updated", updatingObject);
        notifyListeners();
    }

    /**
     * Updates an object.
     */
    protected abstract void updateObject();
}
