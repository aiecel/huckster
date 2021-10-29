package org.huckster.core.asset;

import org.huckster.core.asset.symbol.Symbol;
import org.huckster.core.price.Priced;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.time.schedule.TradingSchedule;
import org.huckster.core.value.series.CompoundSeries;

public interface Asset extends CompoundSeries<CandleSeries>, Priced {
    /**
     * Returns the symbol of an asset.
     *
     * @return a {@link Symbol}.
     */
    Symbol getSymbol();

    /**
     * Returns trading schedule of an asset.
     *
     * @return a {@link TradingSchedule}.
     */
    TradingSchedule getTradingSchedule();
}
