package org.aiecel.huckster.core.data.provider;

import javassist.compiler.ast.Symbol;
import org.aiecel.huckster.core.price.Candle;
import org.aiecel.huckster.core.time.Timeframe;

import java.math.BigDecimal;
import java.util.List;

public interface MarketDataProvider {
    BigDecimal getPrice(Symbol symbol);

    List<Candle> getLast(Symbol symbol, Timeframe timeframe, int quantity);
}
