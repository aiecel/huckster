package org.huckster.core.data.provider;

import java.util.List;

public interface Provider<V> {
    List<V> getLast(int quantity);
}
