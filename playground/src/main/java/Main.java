import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.aiecel.huckster.core.price.Currency;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.update.handler.CallableCandleSeriesUpdateHandler;
import org.aiecel.huckster.core.update.scheduler.FixedRateUpdateScheduler;
import org.aiecel.huckster.trading.ta.indicator.OHLCValueIndicator;
import org.aiecel.huckster.trading.ta.indicator.bollingerbands.BollingerBandsIndicator;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var series = new CandleSeries(Timeframe.M1, 20);

        var bollingerBands = new BollingerBandsIndicator(new OHLCValueIndicator(series), 10, 2);

        var updateHandler = new CallableCandleSeriesUpdateHandler(
                series, new TestCandleProvider(new CurrencyPairSymbol(Currency.EUR, Currency.CAD))
        );

        updateHandler.addUpdateListener((s) -> {
            log.info("Timeframe {}:", series.getTimeframe());
            series.getAll().forEach(candle ->
                    log.info("{} ({})", candle, candle.close().compareTo(candle.open()) >= 0 ? "GREEN" : "RED")
            );
            log.info("BB = {}", bollingerBands.getLast());
        });

        var updateScheduler = new FixedRateUpdateScheduler(5000);
        updateScheduler.addUpdatable(updateHandler);
        updateScheduler.start();
    }
}
