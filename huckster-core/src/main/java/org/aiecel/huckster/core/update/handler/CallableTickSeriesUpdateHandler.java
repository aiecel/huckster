package org.aiecel.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.data.provider.TimedValueProvider;
import org.aiecel.huckster.core.price.Tick;
import org.aiecel.huckster.core.price.series.TickSeries;

/**
 * Update handler for {@link TickSeries}.
 */
@Slf4j
public final class CallableTickSeriesUpdateHandler extends AbstractCallableUpdateHandler<TickSeries> {
    private final TimedValueProvider<Tick> timedValueProvider;

    public CallableTickSeriesUpdateHandler(TickSeries tickSeries, TimedValueProvider<Tick> timedValueProvider) {
        super(tickSeries);
        this.timedValueProvider = timedValueProvider;
    }

    @Override
    protected void updateObject() {
        updatingObject.clear();
        updatingObject.addAll(timedValueProvider.getLast(updatingObject.capacity()));
        log.debug("{} has been updated", updatingObject);
    }
}
