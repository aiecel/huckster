import lombok.extern.slf4j.Slf4j;
import org.aiecel.huckster.core.price.series.CandleSeries;
import org.aiecel.huckster.trading.strategy.Strategy;
import org.aiecel.huckster.trading.ta.indicator.OHLCValueIndicator;
import org.aiecel.huckster.trading.ta.indicator.SMAIndicator;

@Slf4j
public class TestSMAStrategy implements Strategy {
    private final CandleSeries candleSeries;
    private final SMAIndicator sma;

    public TestSMAStrategy(CandleSeries candleSeries, int smaPeriod) {
        this.candleSeries = candleSeries;
        sma = new SMAIndicator(new OHLCValueIndicator(candleSeries), smaPeriod);
    }

    /**
     * Updates the strategy
     */
    @Override
    public void update() {
        log.info("Price: {}, SMA: {}", candleSeries.getPrice(), sma.getLast());
    }
}
