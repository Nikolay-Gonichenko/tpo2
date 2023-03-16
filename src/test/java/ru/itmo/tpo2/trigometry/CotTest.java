package ru.itmo.tpo2.trigometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class CotTest {
    private static final double DELTA = 0.0005;

    @ParameterizedTest
    @DisplayName("Testing ctg(x) on values % Pi == 0")
    @ValueSource(doubles = {0, Math.PI, 3 * Math.PI})
    void cot_ShouldThrowException(double dot) {
        assertThrows(IllegalArgumentException.class, () -> Cot.calcCot(dot));
    }

    @DisplayName("Testing ctg(x) on random values")
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI / 2, 5 * Math.PI / 2, 4.22 * Math.PI})
    void cot_RandomValues(double dot) {
        assertEquals(1 / Math.tan(dot), Cot.calcCot(dot), DELTA);
    }

    @DisplayName("Testing ctg(x) on random values with stub on Cos(x)")
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI / 2, 5 * Math.PI / 2, 4.22 * Math.PI})
    void cot_RandomValuesWithCosStub(double dot) {
        try (MockedStatic<Cos> mocked = mockStatic(Cos.class)) {
            mocked.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            assertEquals(1 / Math.tan(dot), Cot.calcCot(dot), DELTA);
        }
    }

    @DisplayName("Testing ctg(x) on random values with stub on Sin(x)")
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI / 2, 5 * Math.PI / 2, 4.22 * Math.PI})
    void cot_RandomValuesWithSinStub(double dot) {
        try (MockedStatic<Sin> mocked = mockStatic(Sin.class)) {
            mocked.when(() -> Sin.calcSin(TrigUtil.makeInRange(dot))).thenReturn(Math.sin(TrigUtil.makeInRange(dot)));
            assertEquals(1 / Math.tan(dot), Cot.calcCot(dot), DELTA);
        }
    }

    @DisplayName("Testing ctg(x) on random values with stub on Cos(x) and Sin(x)")
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.0, 2.0, 3.0, 4.0, Math.PI / 2, 5 * Math.PI / 2, 4.22 * Math.PI})
    void cot_RandomValuesWithCosAndSinStub(double dot) {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class);
             MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            mockedCos.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            mockedSin.when(() -> Sin.calcSin(TrigUtil.makeInRange(dot))).thenReturn(Math.sin(TrigUtil.makeInRange(dot)));
            assertEquals(1 / Math.tan(dot), Cot.calcCot(dot), DELTA);
        }
    }
}
