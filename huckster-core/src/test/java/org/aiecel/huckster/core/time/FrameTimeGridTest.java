package org.aiecel.huckster.core.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

class FrameTimeGridTest {
    private final FrameTimeGrid testGrid = new FrameTimeGrid(
            LocalDateTime.of(2021, 10, 4, 21, 0, 0).toInstant(ZoneOffset.UTC),
            Timeframe.D.getDuration()
    );

    @Test
    void alignedTimestampAlignedAfterAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2021, 10, 5, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);
        Assertions.assertTrue(testGrid.isAligned(testTimestamp));
    }

    @Test
    void notAlignedTimestampNotAlignedAfterAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2021, 10, 6, 21, 0, 1)
                .toInstant(ZoneOffset.UTC);
        Assertions.assertFalse(testGrid.isAligned(testTimestamp));
    }

    @Test
    void alignedTimestampAlignedBeforeAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2020, 10, 5, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);
        Assertions.assertTrue(testGrid.isAligned(testTimestamp));
    }

    @Test
    void notAlignedTimestampNotAlignedBeforeAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2020, 10, 6, 21, 0, 1)
                .toInstant(ZoneOffset.UTC);
        Assertions.assertFalse(testGrid.isAligned(testTimestamp));
    }

    @Test
    void alignToNearestAfterAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2021, 10, 4, 20, 0, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2021, 10, 4, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToNearest(testTimestamp));
    }

    @Test
    void alignToPreviousAfterAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2021, 10, 5, 21, 55, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2021, 10, 5, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToPrevious(testTimestamp));
    }

    @Test
    void alignToNextAfterAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2021, 10, 6, 21, 10, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2021, 10, 7, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToNext(testTimestamp));
    }

    @Test
    void alignToNearestBeforeAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2020, 10, 5, 22, 0, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2020, 10, 5, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToNearest(testTimestamp));
    }

    @Test
    void alignToPreviousBeforeAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2020, 10, 5, 21, 55, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2020, 10, 5, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToPrevious(testTimestamp));
    }

    @Test
    void alignToNextBeforeAlignmentTimestamp() {
        var testTimestamp = LocalDateTime.of(2020, 10, 6, 21, 10, 0)
                .toInstant(ZoneOffset.UTC);

        var expected = LocalDateTime.of(2020, 10, 7, 21, 0, 0)
                .toInstant(ZoneOffset.UTC);

        Assertions.assertEquals(expected, testGrid.alignToNext(testTimestamp));
    }
}