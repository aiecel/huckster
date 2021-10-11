import org.aiecel.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.aiecel.huckster.core.price.Currency;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.update.handler.CallableCandleSeriesUpdateHandler;
import org.aiecel.huckster.core.update.scheduler.FixedRateUpdateScheduler;

public class StrategyTest {
    public static void main(String[] args) {
        var candleSeries = new CandleSeries(Timeframe.M1);

        var strategy = new TestSMAStrategy(candleSeries, 10);

        var updateHandler = new CallableCandleSeriesUpdateHandler(
                candleSeries, new TestCandleProvider(new CurrencyPairSymbol(Currency.AUD, Currency.USD))
        );
        updateHandler.addUpdateListener(object -> strategy.update());

        var scheduler = new FixedRateUpdateScheduler(10000);
        scheduler.addUpdatable(updateHandler);

        scheduler.start();
    }
}
