package org.aiecel.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.data.provider.OHLCProvider;
import org.aiecel.huckster.core.price.Candle;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.core.price.series.MultiTimeframeCandleSeries;
import org.aiecel.huckster.core.time.watch.Watch;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public final class CallableMultiTimeframeCandleSeriesUpdateHandler
        extends AbstractCallableUpdateHandler<MultiTimeframeCandleSeries> {

    private final Set<CallableUpdateHandler<CandleSeries>> updateHandlers;

    public CallableMultiTimeframeCandleSeriesUpdateHandler(MultiTimeframeCandleSeries multiTimeframeCandleSeries,
                                                           OHLCProvider<Candle> candleProvider,
                                                           Watch watch) {
        super(multiTimeframeCandleSeries);
        this.updateHandlers = new HashSet<>();
        for (CandleSeries series : multiTimeframeCandleSeries.getAllSeries()) {
            updateHandlers.add(new CallableCandleSeriesUpdateHandler(series, candleProvider, watch));
        }
    }

    @Override
    protected void updateObject() {
        updateHandlers.forEach(CallableUpdateHandler::call);
    }
}
