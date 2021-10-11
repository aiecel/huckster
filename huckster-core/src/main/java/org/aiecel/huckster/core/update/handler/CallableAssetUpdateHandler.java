package org.aiecel.huckster.core.update.handler;

import org.aiecel.huckster.core.asset.Asset;
import org.aiecel.huckster.core.data.provider.MarketDataProvider;

public final class CallableAssetUpdateHandler extends AbstractCallableUpdateHandler<Asset> {
    private final MarketDataProvider marketDataProvider;

    public CallableAssetUpdateHandler(Asset updatingObject, MarketDataProvider marketDataProvider) {
        super(updatingObject);
        this.marketDataProvider = marketDataProvider;
    }

    @Override
    protected void updateObject() {

    }
}
