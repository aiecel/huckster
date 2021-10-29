package org.huckster.core.price;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Currency {
    AUD("Australia Dollar"),
    CAD("Canada Dollar"),
    CHF("Switzerland Franc"),
    CNY("China Yuan"),
    EUR("Euro"),
    GBP("United Kingdom Pound"),
    JPY("Japan Yen"),
    NZD("New Zealand Dollar"),
    RUB("Russian Ruble"),
    USD("United States Dollar");

    private final String description;
}
