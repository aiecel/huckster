package org.huckster.playground.backtest;

import org.huckster.core.data.reader.FileLineReader;
import org.huckster.core.price.Tick;

import java.io.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TrueFXPricesReader extends FileLineReader<Tick> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSS");

    public TrueFXPricesReader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    protected Tick parseLine(String line) {
        if (line == null) return null;

        String[] columns = line.split(",");

        Instant instant = LocalDateTime.parse(columns[1], dateTimeFormatter).toInstant(ZoneOffset.UTC);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(columns[2]) + Double.parseDouble(columns[3]) / 2);

        return new Tick(price, instant);
    }
}
