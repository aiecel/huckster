package org.aiecel.huckster.core.asset.symbol;

public record Ticker(String ticker, String description) implements AssetSymbol {
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
    public String toString() {
        return String.format("%s (%s)", ticker, description);
    }
}
