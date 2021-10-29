package org.huckster.core.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.huckster.core.price.series.CandleSeries;
import org.huckster.core.price.series.CompoundCandleSeries;
import org.huckster.core.time.Timeframe;
import org.huckster.core.time.schedule.TradingSchedule;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractAsset implements Asset {
    private final CompoundCandleSeries compoundCandleSeries;
    private TradingSchedule tradingSchedule;

    public AbstractAsset(TradingSchedule tradingSchedule) {
        this(new CompoundCandleSeries(), tradingSchedule);
    }

    @Override
    public boolean contains(Timeframe timeframe) {
        return compoundCandleSeries.contains(timeframe);
    }

    @Override
    public CandleSeries get(Timeframe timeframe) {
        return compoundCandleSeries.get(timeframe);
    }

    @Override
    public Set<CandleSeries> getAllSeries() {
        return compoundCandleSeries.getAllSeries();
    }

    @Override
    public BigDecimal getPrice() {
        return compoundCandleSeries.getPrice();
    }
}
