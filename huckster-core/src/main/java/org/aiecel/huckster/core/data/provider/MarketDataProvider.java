package org.aiecel.huckster.core.data.provider;

import org.aiecel.huckster.core.asset.symbol.Symbol;
import org.aiecel.huckster.core.price.PriceCandle;
import org.aiecel.huckster.core.time.Timeframe;

import java.util.List;

public interface MarketDataProvider extends AssetPriceProvider {
    List<PriceCandle> getLast(Symbol symbol, Timeframe timeframe, int quantity);
}
