package org.aiecel.huckster.core.price;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.OHLC;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@EqualsAndHashCode
public class Candle implements OHLC<BigDecimal>, Priced {
    private final Instant timestamp;
    private final Timeframe timeframe;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;

    public Candle(Instant openTimestamp, Timeframe timeframe, BigDecimal initialPrice) {
        this(openTimestamp, timeframe, initialPrice, initialPrice, initialPrice, initialPrice);
    }

    public static Candle fromOHLC(OHLC<BigDecimal> ohlc) {
        return new Candle(ohlc.getTimestamp(),
                ohlc.timeframe(),
                ohlc.open(),
                ohlc.high(),
                ohlc.low(),
                ohlc.close()
        );
    }

    @Override
    public BigDecimal getPrice() {
        return close;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    public Instant closeTimestamp() {
        return timestamp.plus(timeframe.getDuration());
    }

    @Override
    public Timeframe timeframe() {
        return timeframe;
    }

    @Override
    public BigDecimal open() {
        return open;
    }

    @Override
    public BigDecimal high() {
        return high;
    }

    @Override
    public BigDecimal low() {
        return low;
    }

    @Override
    public BigDecimal close() {
        return close;
    }

    @Override
    public String toString() {
        return String.format("Candlestick {%s, %s, OHLC: %s %s %s %s}",
                timeframe, timestamp, open, high, low, close
        );
    }
}
