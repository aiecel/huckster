package org.aiecel.huckster.core.data.provider;

import org.aiecel.huckster.core.asset.symbol.Symbol;

import java.math.BigDecimal;

public interface AssetPriceProvider {
    BigDecimal getPrice(Symbol symbol);
}
