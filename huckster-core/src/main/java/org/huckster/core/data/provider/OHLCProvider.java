package org.huckster.core.data.provider;

import org.huckster.core.time.Timeframe;
import org.huckster.core.value.OHLC;

import java.util.List;

public interface OHLCProvider<V extends OHLC<?>> {
    List<V> getLast(Timeframe timeframe, int quantity);
}
