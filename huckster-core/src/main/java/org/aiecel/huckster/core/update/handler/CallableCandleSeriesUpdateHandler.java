package org.aiecel.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.data.provider.OHLCProvider;
import org.aiecel.huckster.core.price.Candle;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.core.time.watch.RealtimeWatch;
import org.aiecel.huckster.core.time.watch.Watch;

import java.time.Duration;

/**
 * Update handler for {@link CandleSeries}.
 */
@Slf4j
public final class CallableCandleSeriesUpdateHandler extends AbstractCallableUpdateHandler<CandleSeries> {
    private final OHLCProvider<Candle> provider;
    private final Watch watch;

    public CallableCandleSeriesUpdateHandler(CandleSeries updatingSeries,
                                             OHLCProvider<Candle> candleProvider) {
        super(updatingSeries);
        this.provider = candleProvider;
        this.watch = RealtimeWatch.getInstance();
    }

    public CallableCandleSeriesUpdateHandler(CandleSeries updatingSeries,
                                             OHLCProvider<Candle> candleProvider,
                                             Watch watch) {
        super(updatingSeries);
        this.provider = candleProvider;
        this.watch = watch;
    }

    /**
     * Updates an object.
     */
    @Override
    protected void updateObject() {
        if (updatingObject.size() == 0) {
            //if there's no candles then fill the series
            var preloadedCandles = provider.getLast(updatingObject.getTimeframe(), updatingObject.capacity());
            updatingObject.addAll(preloadedCandles);
            log.debug("{} has been filled with {} candlesticks", preloadedCandles.size(), updatingObject);
        } else {
            //request new candles
            var newCandles = provider.getLast(updatingObject.getTimeframe(), (int) calculateCandleQuantityToGet());

            //slow provider fix
            if (newCandles.size() > 0 && newCandles.get(0).getTimestamp().isBefore(updatingObject.getLast().getTimestamp())) {
                log.warn("Provider doesn't have latest candle(s) (latest available candle timestamp: {}, {} candles behind)",
                        newCandles.get(newCandles.size() - 1).getTimestamp(),
                        "PLACEHOLDER" //todo
                );
                newCandles = newCandles.subList(1, newCandles.size());
            }

            //todo exception handling

            //update the series
            updatingObject.addAll(newCandles);

            //todo don't trust provider
        }
    }

    private long calculateCandleQuantityToGet() {
        var lastCandleStartTime = updatingObject.getLast().getTimestamp();
        return 1 + Duration.between(lastCandleStartTime, watch.getCurrentTimestamp())
                .dividedBy(updatingObject.getTimeframe().getDuration()
                );
    }
}
