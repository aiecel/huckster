package org.huckster.playground.backtest;

import lombok.extern.slf4j.Slf4j;
import org.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.huckster.core.data.provider.OHLCProvider;
import org.huckster.core.price.Candle;
import org.huckster.core.price.Currency;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.time.Timeframe;
import org.huckster.core.time.clock.SimulatedClock;
import org.huckster.core.update.handler.CandleSeriesUpdateHandler;
import org.huckster.core.update.scheduler.SimulatedFixedRateUpdateScheduler;
import org.huckster.ta.indicator.BollingerBandsIndicator;
import org.huckster.ta.indicator.CandleValueIndicator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
public class Backtest {
    public static void main(String[] args) {
        var symbol = new CurrencyPairSymbol(Currency.EUR, Currency.USD);

        var clock = new SimulatedClock(
                LocalDateTime.of(2019, 12, 10, 12, 0, 30).toInstant(ZoneOffset.UTC)
        );

        var marketDataProvider = new SimulatedMarketDataProvider(clock, 100, 200);

        var candles = new CandleSeries(Timeframe.M5, 50);
        var indicator = new BollingerBandsIndicator(new CandleValueIndicator(candles), 20, 2);

        var candleProvider = new OHLCProvider<Candle>() {
            @Override
            public List<Candle> getLast(Timeframe timeframe, int quantity) {
                return marketDataProvider.getLastCandles(symbol, timeframe, quantity);
            }
        };
        var updateHandler = new CandleSeriesUpdateHandler(candles, candleProvider, clock);
        updateHandler.addUpdateListener(o -> {
            log.info("{}, Price: {}, {}: {}", clock.getTime(), o.getPrice(), indicator, indicator.getLast());
        });

        var scheduler = new SimulatedFixedRateUpdateScheduler(1000, clock);
        scheduler.addUpdatable(updateHandler);
        scheduler.addUpdatable(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        scheduler.start();
    }
}
