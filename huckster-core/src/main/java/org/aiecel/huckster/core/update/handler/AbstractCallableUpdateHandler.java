package org.aiecel.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;

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

    protected abstract void updateObject();
}
