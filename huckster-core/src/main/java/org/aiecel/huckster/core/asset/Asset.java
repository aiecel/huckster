package org.aiecel.huckster.core.asset;

import org.aiecel.huckster.core.asset.symbol.AssetSymbol;
import org.aiecel.huckster.core.price.Priced;
import org.aiecel.huckster.core.price.series.PriceSeries;

public interface Asset extends Priced {
    /**
     * Returns the symbol of the asset.
     *
     * @return the symbol of the asset.
     */
    AssetSymbol symbol();

    PriceSeries getPriceSeries();
}