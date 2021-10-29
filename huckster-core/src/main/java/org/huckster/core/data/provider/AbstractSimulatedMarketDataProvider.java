package org.huckster.core.data.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.huckster.core.asset.symbol.Symbol;
import org.huckster.core.price.Candle;
import org.huckster.core.time.Timeframe;
import org.huckster.core.time.clock.SimulatedClock;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractSimulatedMarketDataProvider implements MarketDataProvider {
    protected final SimulatedClock clock;
    protected long minDelayMillis;
    protected long maxDelayMillis;

    public AbstractSimulatedMarketDataProvider(SimulatedClock clock) {
        this(clock, 0, 0);
    }

    public AbstractSimulatedMarketDataProvider(SimulatedClock clock, long delayMillis) {
        this(clock, delayMillis, delayMillis);
    }

    /**
     * Returns a list of last candles of specified timeframe for an asset.
     *
     * @param symbol    symbol of an asset.
     * @param timeframe timeframe of candles.
     * @param quantity  quantity of the candles.
     * @return list of last candles of specified timeframe for an asset.
     */
    @Override
    public List<Candle> getLastCandles(Symbol symbol, Timeframe timeframe, int quantity) {
        var delay = Duration.ofMillis(getDelayMillis());
        log.debug("Delay is set to {}ms.", delay.toMillis());
        clock.moveForward(delay.dividedBy(2));
        var candles = getCandles(symbol, timeframe, quantity);
        clock.moveForward(delay.dividedBy(2));
        return candles;
    }

    /**
     * Returns a list of last candles of specified timeframe for an asset.
     *
     * @param symbol    symbol of an asset.
     * @param timeframe timeframe of candles.
     * @param quantity  quantity of the candles.
     * @return list of last candles of specified timeframe for an asset.
     */
    protected abstract List<Candle> getCandles(Symbol symbol, Timeframe timeframe, int quantity);

    private long getDelayMillis() {
        if (minDelayMillis == maxDelayMillis) return maxDelayMillis;
        return minDelayMillis + new Random().nextLong(maxDelayMillis - minDelayMillis);
    }
}
