package org.huckster.ta.indicator;

public record BollingerBands(double mean, double upperBand, double lowerBand) {
    @Override
    public String toString() {
        return String.format("BB {M: %s, U: %s, L: %s}", mean, upperBand, lowerBand);
    }
}
