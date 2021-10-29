package org.huckster.ta.indicator;

public record UpwardDownwardChange(double upwardChange, double downwardChange) {
    @Override
    public String toString() {
        return String.format("UDC {U: %s, D: %s}", upwardChange, downwardChange);
    }
}
