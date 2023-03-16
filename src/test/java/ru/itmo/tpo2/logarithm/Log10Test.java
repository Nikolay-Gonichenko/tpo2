package ru.itmo.tpo2.logarithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class Log10Test {
    private final double DELTA = 0.00001;
    private final int BASE = 10;

    @ParameterizedTest
    @DisplayName("Testing log10(x) on values <= 0")
    @ValueSource(doubles = {0, -1, -10})
    void testLog10_shouldThrowException(double dot) {
        assertThrows(IllegalArgumentException.class, () -> Log10.calcLog10(dot));
    }

    @ParameterizedTest
    @DisplayName("Testing log10(x) on random values")
    @ValueSource(doubles = {1, 1.5, Math.E, 3, 10.33})
    void testLog5_randomValuesWithoutStub(double dot) {
        assertEquals(Math.log10(dot), Log10.calcLog10(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Testing log10(x) on random values with stub on ln(x)")
    @ValueSource(doubles = {1, 1.5, Math.E, 3, 10.33})
    void testLog10_randomValuesWithStub(double dot) {
        try (MockedStatic<Ln> mocked = mockStatic(Ln.class)) {
            mocked.when(() -> Ln.calcLn(dot)).thenReturn(Math.log(dot));
            mocked.when(() -> Ln.calcLn(BASE)).thenReturn(Math.log(BASE));
            assertEquals(Math.log10(dot), Log10.calcLog10(dot), DELTA);
        }
    }
}
