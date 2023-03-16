package ru.itmo.tpo2.logarithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {

    private final double DELTA = 0.00001;

    @ParameterizedTest
    @DisplayName("Testing ln(x) on values <= 0")
    @ValueSource(doubles = {0, -1, -10})
    void testLn_shouldThrowException(double dot) {
        assertThrows(IllegalArgumentException.class, () -> Ln.calcLn(dot));
    }

    @ParameterizedTest
    @DisplayName("Testing ln(x) on random values")
    @ValueSource(doubles = {1, 1.5, Math.E, 3, 10.33})
    void testLn_randomValues(double dot) {
        assertEquals(Math.log(dot), Ln.calcLn(dot), DELTA);
    }
}
