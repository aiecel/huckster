package org.aiecel.huckster.core.update;

public interface UpdateListener<T> {
    void onUpdate(T object);
}
