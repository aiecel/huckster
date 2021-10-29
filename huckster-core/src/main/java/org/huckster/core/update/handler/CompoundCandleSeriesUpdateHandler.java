package org.huckster.core.update.handler;

import org.huckster.core.data.provider.OHLCProvider;
import org.huckster.core.price.Candle;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.time.clock.Clock;
import org.huckster.core.value.series.CompoundSeries;

import java.util.HashSet;
import java.util.Set;

/**
 * Update handler for {@link CompoundSeries<CandleSeries>}.
 */
public final class CompoundCandleSeriesUpdateHandler
        extends AbstractCallableUpdateHandler<CompoundSeries<CandleSeries>> {

    private final Set<CallableUpdateHandler<CandleSeries>> updateHandlers;

    public CompoundCandleSeriesUpdateHandler(CompoundSeries<CandleSeries> compoundCandleSeries,
                                             OHLCProvider<Candle> candleProvider,
                                             Clock clock) {
        super(compoundCandleSeries);
        this.updateHandlers = new HashSet<>();
        for (CandleSeries series : compoundCandleSeries.getAllSeries()) {
            updateHandlers.add(new CandleSeriesUpdateHandler(series, candleProvider, clock));
        }
    }

    @Override
    protected void updateObject() {
        updateHandlers.forEach(CallableUpdateHandler::update);
    }
}
