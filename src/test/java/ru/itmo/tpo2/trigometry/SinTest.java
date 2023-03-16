package ru.itmo.tpo2.trigometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class SinTest {
    private static final double DELTA = 0.0005;

    @DisplayName("Testing sin(x) on random values in {0; 2*Pi}")
    @ParameterizedTest
    @ValueSource(doubles = {0, 0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI, Math.PI / 2})
    void sin_RandomValues(double dot) {
        assertEquals(Math.sin(dot), Sin.calcSin(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Testing sin(x) on random values which is more than 2*Pi")
    @ValueSource(doubles = {3 * Math.PI, 5 * Math.PI, 4.22 * Math.PI})
    void sin_RandomValuesNotInRange(double dot) {
        assertEquals(Math.sin(dot), Sin.calcSin(dot), DELTA);
    }

    @DisplayName("Testing sin(x) on random values in {0; 2*Pi} with stub")
    @ParameterizedTest
    @ValueSource(doubles = {0, 0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI, Math.PI / 2})
    void sin_RandomValuesWithStub(double dot) {
        try (MockedStatic<Cos> mocked = mockStatic(Cos.class)) {
            mocked.when(() -> Cos.calcCos(dot)).thenReturn(Math.cos(dot));
            assertEquals(Math.sin(dot), Sin.calcSin(dot), DELTA);
        }
    }
}
