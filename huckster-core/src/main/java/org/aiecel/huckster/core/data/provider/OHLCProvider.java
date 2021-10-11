package org.aiecel.huckster.core.data.provider;

import org.aiecel.huckster.core.time.Timeframe;
import org.aiecel.huckster.core.value.OHLC;

import java.util.List;

public interface OHLCProvider<V extends OHLC<?>> {
    List<V> getLast(Timeframe timeframe, int quantity);
}
