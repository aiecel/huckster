package org.aiecel.huckster.core.data.provider;

import org.aiecel.huckster.core.value.TimedValue;

import java.util.List;

public interface TimedValueProvider<V extends TimedValue> {
    List<V> getLast(int quantity);
}
