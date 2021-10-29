package org.huckster.core.update.scheduler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.huckster.core.update.Updatable;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public abstract class AbstractUpdateScheduler implements UpdateScheduler {
    @Getter
    protected final String name;
    protected final Collection<Updatable> updatable;

    public AbstractUpdateScheduler() {
        this(AbstractUpdateScheduler.class.getSimpleName());
    }

    public AbstractUpdateScheduler(String name) {
        this.name = name;
        this.updatable = new ArrayList<>();
    }

    /**
     * Adds new {@link Updatable} objects to the scheduler.
     *
     * @param updatable {@link Updatable} object.
     */
    @Override
    public void addUpdatable(Updatable updatable) {
        this.updatable.add(updatable);
    }

    protected void update() {
        log.debug("Tick started");
        updatable.forEach(Updatable::update);
        log.debug("Tick ended");
    }
}
