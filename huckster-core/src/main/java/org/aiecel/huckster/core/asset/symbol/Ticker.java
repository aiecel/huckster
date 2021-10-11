package org.aiecel.huckster.core.asset.symbol;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class Ticker implements Symbol {
    private final String ticker;
    private final String description;

    public Ticker(String ticker) {
        this(ticker, "");
    }

    public Ticker(String ticker, String description) {
        this.ticker = ticker.toUpperCase();
        this.description = description;
    }

    /**
     * Returns the symbol of the asset.
     *
     * @return the symbol of the asset.
     */
    @Override
    public String getString() {
        return ticker;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Ticker) obj;
        return Objects.equals(this.ticker, that.ticker) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, description);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", ticker, description);
    }

}
