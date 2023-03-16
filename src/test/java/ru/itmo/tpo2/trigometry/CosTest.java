package ru.itmo.tpo2.trigometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {

    private static final double DELTA = 0.0005;

    @DisplayName("Testing cos(x) on random values in {0; 2*Pi}")
    @ParameterizedTest
    @ValueSource(doubles = {0, 0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI, Math.PI / 2})
    void cos_RandomValues(double dot) {
        assertEquals(Math.cos(dot), Cos.calcCos(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Testing cos(x) on random values which is more than 2*Pi")
    @ValueSource(doubles = {3*Math.PI, 5*Math.PI, 4.22 * Math.PI})
    void cos_RandomValuesNotInRange(double dot) {
        assertEquals(Math.cos(dot), Cos.calcCos(dot), DELTA);
    }
}
