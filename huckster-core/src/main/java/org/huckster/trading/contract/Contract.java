package org.huckster.trading.contract;

import org.huckster.core.asset.symbol.Symbol;

import java.time.Instant;

public interface Contract {
    String getId();

    Symbol getSymbol();

    Instant getBuyTimestamp();
}
