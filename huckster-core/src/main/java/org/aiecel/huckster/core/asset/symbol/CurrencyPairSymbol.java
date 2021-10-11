package org.aiecel.huckster.core.asset.symbol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aiecel.huckster.core.price.Currency;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public final class CurrencyPairSymbol implements Symbol {
    private final Currency baseCurrency;
    private final Currency homeCurrency;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CurrencyPairSymbol) obj;
        return Objects.equals(this.baseCurrency, that.baseCurrency) &&
                Objects.equals(this.homeCurrency, that.homeCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCurrency, homeCurrency);
    }
}
