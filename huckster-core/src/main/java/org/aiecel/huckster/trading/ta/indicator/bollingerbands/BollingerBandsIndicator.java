package org.aiecel.huckster.trading.ta.indicator.bollingerbands;

import lombok.Getter;
import org.aiecel.huckster.trading.ta.indicator.AbstractIndicator;
import org.aiecel.huckster.trading.ta.indicator.Indicator;
import org.aiecel.huckster.trading.ta.indicator.SMAIndicator;
import org.aiecel.huckster.trading.ta.indicator.StandardDeviationIndicator;

public class BollingerBandsIndicator extends AbstractIndicator<Number, BollingerBands> {
    private final Indicator<Number> middleLineIndicator;
    private final StandardDeviationIndicator standardDeviationIndicator;

    @Getter
    private final int period;

    @Getter
    private final double multiplier;

    public BollingerBandsIndicator(Indicator<? extends Number> base, int period, double multiplier) {
        this(base, new SMAIndicator(base, period), period, multiplier);
    }

    public BollingerBandsIndicator(Indicator<? extends Number> base,
                                   Indicator<Number> middleLineIndicator,
                                   int standardDeviationPeriod,
                                   double multiplier) {
        super(base);
        this.middleLineIndicator = middleLineIndicator;
        this.standardDeviationIndicator = new StandardDeviationIndicator(base, middleLineIndicator, standardDeviationPeriod);
        this.period = standardDeviationPeriod;
        this.multiplier = multiplier;
    }

    /**
     * Returns value of an indicator by its index.
     *
     * @param index index of the value.
     * @return value of the indicator.
     */
    @Override
    public BollingerBands get(int index) {
        var middleLine = middleLineIndicator.get(index).doubleValue();
        var standardDeviation = standardDeviationIndicator.get(index);

        return new BollingerBands(
                middleLine,
                middleLine + standardDeviation * multiplier,
                middleLine - standardDeviation * multiplier
        );
    }
}
