package org.aiecel.huckster.core.asset;

import lombok.AllArgsConstructor;
import org.aiecel.huckster.core.asset.symbol.AssetSymbol;
import org.aiecel.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.aiecel.huckster.core.price.series.PriceSeries;

import java.math.BigDecimal;

@AllArgsConstructor
public final class CurrencyPair implements Asset {
    private final CurrencyPairSymbol symbol;
    private final PriceSeries priceSeries;

    /**
     * Returns the symbol of the asset.
     *
     * @return the symbol of the asset.
     */
    @Override
    public AssetSymbol symbol() {
        return symbol;
    }

    @Override
    public PriceSeries getPriceSeries() {
        return priceSeries;
    }

    @Override
    public BigDecimal price() {
        return priceSeries.price();
    }
}
