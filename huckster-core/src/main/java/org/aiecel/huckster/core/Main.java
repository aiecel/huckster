package org.aiecel.huckster.core;

import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.price.series.MultiTimeframeCandleSeries;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.time.watch.RealtimeWatch;
import org.aiecel.huckster.core.update.handler.CallableMultiTimeframeCandleSeriesUpdateHandler;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var series = MultiTimeframeCandleSeries.withTimeframes(Timeframe.M1, Timeframe.H1);

        var updateHandler = new CallableMultiTimeframeCandleSeriesUpdateHandler(
                series, new TestCandleProvider("AUD_JPY"), new RealtimeWatch()
        );

        updateHandler.addUpdateListener((s) -> {
            s.getAllSeries().forEach(candleSeries -> {
                log.info("Timeframe {}:", candleSeries.timeframe());
                candleSeries.getAll().forEach(candle -> {
                    log.info("{} ({})", candle, candle.close().compareTo(candle.open()) > 0 ? "GREEN" : "RED");
                });
            });
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("Tick started");
                updateHandler.call();
            }
        }, 0, 5000);
    }
}
