package org.huckster.core.asset;

import lombok.Getter;
import org.huckster.core.asset.symbol.CurrencyPairSymbol;
import org.huckster.core.price.Currency;
import org.huckster.core.price.series.CompoundCandleSeries;
import org.huckster.core.time.schedule.TradingSchedule;

@Getter
public class CurrencyPair extends AbstractAsset {
    private final CurrencyPairSymbol symbol;

    public CurrencyPair(CurrencyPairSymbol symbol, TradingSchedule tradingSchedule) {
        super(tradingSchedule);
        this.symbol = symbol;
    }

    public CurrencyPair(CurrencyPairSymbol symbol,
                        CompoundCandleSeries compoundCandleSeries,
                        TradingSchedule tradingSchedule) {
        super(compoundCandleSeries, tradingSchedule);
        this.symbol = symbol;
    }

    public Currency getBaseCurrency() {
        return symbol.getBaseCurrency();
    }

    public Currency getHomeCurrency() {
        return symbol.getHomeCurrency();
    }
}
