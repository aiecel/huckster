package org.huckster.core.data.reader;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileLineReader<V> implements Reader<V> {
    @Getter
    private final File file;

    protected BufferedReader reader;

    public FileLineReader(File file) throws FileNotFoundException {
        this.file = file;
        this.reader = new BufferedReader(new FileReader(file));
    }

    /**
     * Reads the next value.
     *
     * @return next value.
     */
    @Override
    public V readNext() throws IOException {
        return parseLine(reader.readLine());
    }

    /**
     * Reads next values.
     *
     * @param quantity quantity of values to read.
     * @return next values.
     */
    @Override
    public List<V> readNext(int quantity) throws IOException {
        List<V> values = new ArrayList<>(quantity);
        V value;
        for (int i = 0; i < quantity; i++) {
            value = readNext();
            if (value == null) return values;
            values.add(value);
        }
        return values;
    }

    /**
     * Reads all the values.
     *
     * @return all the values.
     */
    @Override
    public List<V> readAll() throws IOException {
        List<V> values = new ArrayList<>();
        V value;
        while ((value = readNext()) != null) {
            values.add(value);
        }
        return values;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    protected abstract V parseLine(String line);
}
