package org.aiecel.huckster.ta.condition;

public interface Condition {
    /**
     * Returns true if the condition at last index is met.
     *
     * @return true if the condition at last index is met.
     */
    default boolean isMet() {
        return isMetAtOffset(0);
    }

    /**
     * Returns true if the condition at specified offset is met.
     *
     * @param offset index.
     * @return true if the condition at specified offset is met.
     */
    boolean isMetAtOffset(int offset);
}
