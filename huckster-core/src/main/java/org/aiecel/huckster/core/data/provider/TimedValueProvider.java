package org.aiecel.huckster.core.data.provider;

import org.aiecel.huckster.core.time.Timed;

import java.util.List;

public interface TimedValueProvider<V extends Timed> {
    List<V> getLast(int quantity);
}
