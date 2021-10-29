package org.huckster.core.update.scheduler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.huckster.core.update.Updatable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Schedules update ticks at a fixed rate.
 */
@Slf4j
@Getter
public class FixedRateUpdateScheduler extends AbstractUpdateScheduler {
    private final long periodMillis;
    private final long delayMillis;
    private final Timer timer;

    public FixedRateUpdateScheduler(long periodMillis) {
        this(periodMillis, 0);
    }

    public FixedRateUpdateScheduler(long periodMillis, long delayMillis) {
        this(FixedRateUpdateScheduler.class.getSimpleName(), periodMillis, delayMillis);
    }

    public FixedRateUpdateScheduler(String name, long periodMillis, long delayMillis) {
        super(name);
        this.periodMillis = periodMillis;
        this.delayMillis = delayMillis;
        this.timer = new Timer(getName());
    }

    /**
     * Starts the scheduler.
     */
    @Override
    public void start() {
        log.info("Scheduling ticks - period: {}ms, delay: {}ms", periodMillis, delayMillis);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, delayMillis, periodMillis);
    }

    /**
     * Stops the scheduler.
     */
    @Override
    public void stop() {
        timer.cancel();
    }


    @Override
    protected void update() {
        var tickStartMillis = System.currentTimeMillis();
        log.debug("Tick started");
        updatable.forEach(Updatable::update);
        log.debug("Tick ended in {}ms", System.currentTimeMillis() - tickStartMillis);
    }
}
