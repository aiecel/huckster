package org.huckster.core.price;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.huckster.core.time.Timeframe;
import org.huckster.core.value.OHLC;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
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
                ohlc.getTimeframe(),
                ohlc.getOpen(),
                ohlc.getHigh(),
                ohlc.getLow(),
                ohlc.getClose()
        );
    }

    @Override
    public BigDecimal getPrice() {
        return close;
    }

    public Instant getCloseTimestamp() {
        return timestamp.plus(timeframe.getDuration());
    }

    @Override
    public String toString() {
        return String.format("Candle {%s, %s, OHLC: %s %s %s %s, color: %s}",
                timeframe, timestamp, open, high, low, close, close.compareTo(open) > 0 ? "GREEN" : "RED"
        );
    }
}
