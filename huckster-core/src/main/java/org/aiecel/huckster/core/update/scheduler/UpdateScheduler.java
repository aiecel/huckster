package org.aiecel.huckster.core.update.scheduler;

import org.aiecel.huckster.core.update.Updatable;

public interface UpdateScheduler {
    void start();

    void addUpdatable(Updatable updatable);
}
