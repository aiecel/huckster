package org.aiecel.huckster.core.update.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.update.Updatable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class FixedRateUpdateScheduler implements UpdateScheduler {
    private final long periodMillis;
    private final long delay;
    private final String name;
    private final Collection<Updatable> updateHandlers;

    public FixedRateUpdateScheduler(long periodMillis) {
        this(periodMillis, 0);
    }

    public FixedRateUpdateScheduler(long periodMillis, long delay) {
        this.periodMillis = periodMillis;
        this.delay = delay;
        this.name = this.getClass().getSimpleName();
        this.updateHandlers = new ArrayList<>();
    }

    public FixedRateUpdateScheduler(long periodMillis, long delay, String name) {
        this.periodMillis = periodMillis;
        this.delay = delay;
        this.name = name;
        this.updateHandlers = new ArrayList<>();
    }

    @Override
    public void start() {
        log.info("Scheduling ticks - period: {}ms, delay: {}ms", periodMillis, delay);
        Timer timer = new Timer(name);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                var tickStartMillis = System.currentTimeMillis();
                log.debug("Tick started");
                updateHandlers.forEach(Updatable::update);
                log.debug("Tick ended in {}ms", System.currentTimeMillis() - tickStartMillis);
            }
        }, delay, periodMillis);
    }

    @Override
    public void addUpdatable(Updatable updatable) {
        updateHandlers.add(updatable);
    }
}
