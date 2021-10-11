package org.aiecel.huckster.trading.contract;

import org.aiecel.huckster.core.asset.symbol.Symbol;

import java.time.Instant;

public interface Contract {
    Instant buyTimestamp();

    Symbol assetSymbol();
}
