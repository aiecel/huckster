package org.huckster.core.asset.symbol;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.huckster.core.price.Currency;

import java.util.Objects;

/**
 * Symbol of a currency pair asset.
 */
@Getter
@RequiredArgsConstructor
public final class CurrencyPairSymbol implements Symbol {
    private final Currency baseCurrency;
    private final Currency homeCurrency;

    /**
     * Returns the symbol of the asset.
     *
     * @return a {@link String}.
     */
    @Override
    public String getString() {
        return getString("_");
    }

    /**
     * Returns the symbol of the asset with the specified delimiter.
     *
     * @return a {@link String}.
     */
    public String getString(String delimiter) {
        return baseCurrency + delimiter + homeCurrency;
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

    @Override
    public String toString() {
        return getString();
    }
}
