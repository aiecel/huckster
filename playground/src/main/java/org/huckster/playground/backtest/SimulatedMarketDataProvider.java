package org.huckster.playground.backtest;

import org.huckster.core.asset.symbol.Symbol;
import org.huckster.core.data.provider.AbstractSimulatedMarketDataProvider;
import org.huckster.core.price.Candle;
import org.huckster.core.price.Tick;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.time.FrameTimeGrid;
import org.huckster.core.time.TimeGrid;
import org.huckster.core.time.Timeframe;
import org.huckster.core.time.clock.SimulatedClock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class SimulatedMarketDataProvider extends AbstractSimulatedMarketDataProvider {
    private TrueFXPricesReader pricesReader;
    private CandleSeries candleSeries;
    private TimeGrid timeGrid;
    private Tick next;
    private boolean begin = true;

    public SimulatedMarketDataProvider(SimulatedClock watch) {
        this(watch, 0, 0);
    }

    public SimulatedMarketDataProvider(SimulatedClock watch, long delayMillis) {
        this(watch, delayMillis, delayMillis);
    }

    public SimulatedMarketDataProvider(SimulatedClock watch, long minDelayMillis, long maxDelayMillis) {
        super(watch, minDelayMillis, maxDelayMillis);
        try {
            candleSeries = new CandleSeries(Timeframe.M5, 100);
            pricesReader = new TrueFXPricesReader(new File("2.csv"));
            timeGrid = new FrameTimeGrid(Instant.EPOCH, candleSeries.getTimeframe().getDuration());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Candle> getCandles(Symbol symbol, Timeframe timeframe, int quantity) {
        fill();
        return candleSeries.getAll().subList(candleSeries.getSize() - quantity, candleSeries.getSize());
    }

    private void fill() {
        try {
            if (begin) {
                next = pricesReader.readNext();
                begin = false;
            }

            while (next != null && next.getTimestamp().isBefore(clock.getTime())) {
                stick(next);
                next = pricesReader.readNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stick(Tick tick) {
        if (candleSeries.isEmpty() || tick.getTimestamp().isAfter(candleSeries.getLast().getCloseTimestamp())) {
            candleSeries.add(
                    new Candle(
                            timeGrid.alignToPrevious(tick.getTimestamp()),
                            candleSeries.getTimeframe(),
                            tick.getPrice()
                    )
            );
        } else {
            Candle lastCandle = candleSeries.getLast();
            Candle newCandle = new Candle(
                    lastCandle.getTimestamp(),
                    lastCandle.getTimeframe(),
                    lastCandle.getOpen(),
                    lastCandle.getHigh().compareTo(tick.getPrice()) < 0 ? tick.getPrice() : lastCandle.getHigh(),
                    lastCandle.getLow().compareTo(tick.getPrice()) < 0 ? lastCandle.getLow() : tick.getPrice(),
                    tick.getPrice()
            );
            candleSeries.add(newCandle);
        }
    }
}
