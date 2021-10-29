package org.huckster.trading.broker;

import org.huckster.trading.contract.Contract;

public interface Broker<C extends Contract> {
    void placeOrder(C contract);
}
