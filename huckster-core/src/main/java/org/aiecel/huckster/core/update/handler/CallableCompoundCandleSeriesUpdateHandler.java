package org.aiecel.huckster.core.update.handler;

import org.aiecel.huckster.core.data.provider.OHLCProvider;
import org.aiecel.huckster.core.price.Candle;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.core.price.series.CompoundCandleSeries;
import org.aiecel.huckster.core.time.watch.Watch;

import java.util.HashSet;
import java.util.Set;

/**
 * Update handler for {@link CompoundCandleSeries}.
 */
public final class CallableCompoundCandleSeriesUpdateHandler
        extends AbstractCallableUpdateHandler<CompoundCandleSeries> {

    private final Set<CallableUpdateHandler<CandleSeries>> updateHandlers;

    public CallableCompoundCandleSeriesUpdateHandler(CompoundCandleSeries compoundCandleSeries,
                                                     OHLCProvider<Candle> candleProvider,
                                                     Watch watch) {
        super(compoundCandleSeries);
        this.updateHandlers = new HashSet<>();
        for (CandleSeries series : compoundCandleSeries.getAllSeries()) {
            updateHandlers.add(new CallableCandleSeriesUpdateHandler(series, candleProvider, watch));
        }
    }

    @Override
    protected void updateObject() {
        updateHandlers.forEach(CallableUpdateHandler::update);
    }
}
