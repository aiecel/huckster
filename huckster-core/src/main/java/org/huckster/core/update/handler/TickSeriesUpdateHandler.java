package org.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.data.provider.Provider;
import org.huckster.core.price.Tick;
import org.huckster.core.price.series.TickSeries;

/**
 * Update handler for {@link TickSeries}.
 */
@Slf4j
public final class TickSeriesUpdateHandler extends AbstractCallableUpdateHandler<TickSeries> {
    private final Provider<Tick> timedValueProvider;

    public TickSeriesUpdateHandler(TickSeries tickSeries, Provider<Tick> timedValueProvider) {
        super(tickSeries);
        this.timedValueProvider = timedValueProvider;
    }

    @Override
    protected void updateObject() {
        updatingObject.clear();
        updatingObject.addAll(timedValueProvider.getLast(updatingObject.getCapacity()));
        log.debug("{} has been updated", updatingObject);
    }
}
