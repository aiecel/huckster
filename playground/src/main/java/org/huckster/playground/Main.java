package org.huckster.playground;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.asset.CurrencyPair;
import org.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.huckster.core.price.Currency;
import org.huckster.core.price.series.CompoundCandleSeries;
import org.huckster.core.time.Timeframe;
import org.huckster.core.time.clock.RealtimeClock;
import org.huckster.core.update.handler.CompoundCandleSeriesUpdateHandler;
import org.huckster.core.update.scheduler.FixedRateUpdateScheduler;
import org.huckster.ta.indicator.CandleValueIndicator;
import org.huckster.ta.indicator.SMAIndicator;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var symbol = new CurrencyPairSymbol(Currency.EUR, Currency.USD);
        var compoundSeries = CompoundCandleSeries.withTimeframes(Timeframe.M5);

        var asset = new CurrencyPair(symbol, compoundSeries, timestamp -> false);

        var updateHandler = new CompoundCandleSeriesUpdateHandler(
                asset, new TestCandleProvider(symbol), RealtimeClock.getInstance()
        );

        var indicator = new SMAIndicator(new CandleValueIndicator(asset.get(Timeframe.M5)), 20);

        updateHandler.addUpdateListener(object -> {
            log.info("{}: {}", indicator, indicator.getLast());
        });

        var updateScheduler = new FixedRateUpdateScheduler(10000);
        updateScheduler.addUpdatable(updateHandler);
        updateScheduler.start();
    }
}
