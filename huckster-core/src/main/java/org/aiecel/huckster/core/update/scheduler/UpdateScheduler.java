package org.aiecel.huckster.core.update.scheduler;

import org.aiecel.huckster.core.update.Updatable;

/**
 * Scheduler for triggering updates of {@link Updatable} objects.
 */
public interface UpdateScheduler {
    /**
     * Starts the scheduler.
     */
    void start();

    /**
     * Adds new {@link Updatable} objects to the scheduler.
     *
     * @param updatable {@link Updatable} object.
     */
    void addUpdatable(Updatable updatable);
}
