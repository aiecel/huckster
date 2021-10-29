package org.huckster.core.update.handler;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.data.provider.OHLCProvider;
import org.huckster.core.price.Candle;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.time.clock.RealtimeClock;
import org.huckster.core.time.clock.Clock;

import java.time.Duration;

/**
 * Update handler for {@link CandleSeries}.
 */
@Slf4j
public final class CandleSeriesUpdateHandler extends AbstractCallableUpdateHandler<CandleSeries> {
    private final OHLCProvider<Candle> provider;
    private final Clock clock;

    public CandleSeriesUpdateHandler(CandleSeries updatingSeries,
                                     OHLCProvider<Candle> candleProvider) {
        super(updatingSeries);
        this.provider = candleProvider;
        this.clock = RealtimeClock.getInstance();
    }

    public CandleSeriesUpdateHandler(CandleSeries updatingSeries,
                                     OHLCProvider<Candle> candleProvider,
                                     Clock clock) {
        super(updatingSeries);
        this.provider = candleProvider;
        this.clock = clock;
    }

    /**
     * Updates an object.
     */
    @Override
    protected void updateObject() {
        if (updatingObject.getSize() == 0) {
            //if there's no candles then fill the series
            var preloadedCandles = provider.getLast(updatingObject.getTimeframe(), updatingObject.getCapacity());
            updatingObject.addAll(preloadedCandles);
            log.debug("{} has been filled with {} candlesticks", updatingObject, preloadedCandles.size());
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

            if (newCandles.size() > 1)
                log.debug("{} new candles created for {}", newCandles.size() - 1, updatingObject);
        }
    }

    private long calculateCandleQuantityToGet() {
        var lastCandleStartTime = updatingObject.getLast().getTimestamp();
        var quantity = 1 + Duration.between(lastCandleStartTime, clock.getTime())
                .dividedBy(updatingObject.getTimeframe().getDuration()
                );
        return Math.min(updatingObject.getCapacity(), quantity);
    }
}
