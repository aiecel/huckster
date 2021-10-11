package org.aiecel.huckster.core.update.handler;

public interface CallableUpdateHandler<T> extends UpdateHandler<T> {
    /**
     * Triggers an update.
     */
    void call();
}
