package org.huckster.core.data.reader;

import java.io.IOException;
import java.util.List;

public interface Reader<V> extends AutoCloseable {
    /**
     * Reads the next value.
     *
     * @return next value.
     */
    V readNext() throws IOException;

    /**
     * Reads next values.
     *
     * @param quantity quantity of values to read.
     * @return next values.
     */
    List<V> readNext(int quantity) throws IOException;

    /**
     * Reads all the values.
     *
     * @return all the values.
     */
    List<V> readAll() throws IOException;
}
