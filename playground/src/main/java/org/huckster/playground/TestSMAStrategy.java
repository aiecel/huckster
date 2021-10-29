package org.huckster.playground;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.trading.strategy.Strategy;
import org.huckster.ta.indicator.CandleValueIndicator;
import org.huckster.ta.indicator.SMAIndicator;

@Slf4j
public class TestSMAStrategy implements Strategy {
    private final CandleSeries candleSeries;
    private final SMAIndicator sma;

    public TestSMAStrategy(CandleSeries candleSeries, int smaPeriod) {
        this.candleSeries = candleSeries;
        sma = new SMAIndicator(new CandleValueIndicator(candleSeries), smaPeriod);
    }

    /**
     * Updates the strategy
     */
    @Override
    public void update() {
        log.info("Price: {}, SMA: {}", candleSeries.getPrice(), sma.getLast());
    }
}
