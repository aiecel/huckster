package org.aiecel.huckster.core.asset.symbol;

import org.aiecel.huckster.core.price.Currency;

public record CurrencyPairSymbol(Currency baseCurrency, Currency homeCurrency) implements AssetSymbol {
    /**
     * Returns the symbol of the asset.
     *
     * @return the symbol of the asset.
     */
    @Override
    public String getString() {
        return baseCurrency + "_" + homeCurrency;
    }

    /**
     * Returns the symbol of the asset.
     *
     * @return the symbol of the asset.
     */
    public String getString(String delimiter) {
        return baseCurrency + delimiter + homeCurrency;
    }

    @Override
    public String toString() {
        return getString();
    }
}
