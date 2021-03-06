package org.huckster.core.update.scheduler;

import org.huckster.core.update.Updatable;

/**
 * Scheduler for triggering updates of {@link Updatable} objects.
 */
public interface UpdateScheduler {
    /**
     * Starts the scheduler.
     */
    void start();

    /**
     * Stops the scheduler.
     */
    void stop();

    /**
     * Adds new {@link Updatable} objects to the scheduler.
     *
     * @param updatable {@link Updatable} object.
     */
    void addUpdatable(Updatable updatable);
}
