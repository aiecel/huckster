package org.aiecel.huckster.core.price;

import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.OHLCValue;

import java.math.BigDecimal;
import java.time.Instant;

public record Candle(Instant timestamp,
                     Timeframe timeframe,
                     BigDecimal open,
                     BigDecimal high,
                     BigDecimal low,
                     BigDecimal close) implements OHLCValue<BigDecimal>, Priced {

    public Candle(Instant openTimestamp, Timeframe timeframe, BigDecimal initialPrice) {
        this(openTimestamp, timeframe, initialPrice, initialPrice, initialPrice, initialPrice);
    }

    public Candle(Instant timestamp,
                  Timeframe timeframe,
                  BigDecimal open,
                  BigDecimal high,
                  BigDecimal low,
                  BigDecimal close) {
        this.timestamp = timestamp;
        this.timeframe = timeframe;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public static Candle fromOHLCValue(OHLCValue<BigDecimal> ohlcValue) {
        return new Candle(ohlcValue.timestamp(),
                ohlcValue.timeframe(),
                ohlcValue.open(),
                ohlcValue.high(),
                ohlcValue.low(),
                ohlcValue.close()
        );
    }

    @Override
    public BigDecimal price() {
        return close;
    }

    @Override
    public String toString() {
        return String.format("Candlestick {%s, %s, OHLC: %s %s %s %s}",
                timeframe, timestamp, open, high, low, close
        );
    }
}
