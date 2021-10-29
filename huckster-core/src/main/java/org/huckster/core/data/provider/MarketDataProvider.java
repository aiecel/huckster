package org.huckster.core.data.provider;

import org.huckster.core.asset.symbol.Symbol;
import org.huckster.core.price.Candle;
import org.huckster.core.time.Timeframe;

import java.util.List;

public interface MarketDataProvider {
    /**
     * Returns a list of last candles of specified timeframe for an asset.
     *
     * @param symbol    symbol of an asset.
     * @param timeframe timeframe of candles.
     * @param quantity  quantity of the candles.
     * @return list of last candles of specified timeframe for an asset.
     */
    List<Candle> getLastCandles(Symbol symbol, Timeframe timeframe, int quantity);
}
