package org.aiecel.huckster.core.time;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
@Getter
public enum Timeframe {
    S5 (Duration.ofSeconds(5)),
    S10 (Duration.ofSeconds(10)),
    S15 (Duration.ofSeconds(15)),
    S30 (Duration.ofSeconds(30)),
    M1 (Duration.ofMinutes(1)),
    M2 (Duration.ofMinutes(2)),
    M4 (Duration.ofMinutes(4)),
    M5 (Duration.ofMinutes(5)),
    M10 (Duration.ofMinutes(10)),
    M15 (Duration.ofMinutes(15)),
    M30 (Duration.ofMinutes(30)),
    H1 (Duration.ofHours(1)),
    H2 (Duration.ofHours(2)),
    H3 (Duration.ofHours(3)),
    H4 (Duration.ofHours(4)),
    H6 (Duration.ofHours(6)),
    H8 (Duration.ofHours(8)),
    H12 (Duration.ofHours(12)),
    D (Duration.ofDays(1));

    private final Duration duration;
}