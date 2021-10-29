package org.huckster.core.update.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.time.clock.SimulatedClock;

@Slf4j
public class SimulatedFixedRateUpdateScheduler extends AbstractUpdateScheduler {
    private final long periodMillis;
    private final long delayMillis;
    private final SimulatedClock clock;
    private Thread tickThread;
    private boolean isRunning;

    public SimulatedFixedRateUpdateScheduler(long periodMillis, SimulatedClock clock) {
        this(periodMillis, 0, clock);
    }

    public SimulatedFixedRateUpdateScheduler(long periodMillis, long delayMillis, SimulatedClock clock) {
        this(SimulatedFixedRateUpdateScheduler.class.getSimpleName(), periodMillis, delayMillis, clock);
    }

    public SimulatedFixedRateUpdateScheduler(String name, long periodMillis, long delayMillis, SimulatedClock clock) {
        super(name);
        this.periodMillis = periodMillis;
        this.delayMillis = delayMillis;
        this.clock = clock;
    }

    /**
     * Starts the scheduler.
     */
    @Override
    public void start() {
        if (delayMillis != 0) clock.moveForward(periodMillis);
        tickThread = new Thread(() -> {
            while (true) {
                if (!isRunning) return;
                update();
                clock.moveForward(periodMillis);
            }
        }, getName());
        isRunning = true;
        tickThread.start();
    }

    /**
     * Stops the scheduler.
     */
    @Override
    public void stop() {
        isRunning = false;
    }
}
