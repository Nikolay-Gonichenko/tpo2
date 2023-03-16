package ru.itmo.tpo2.logarithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo2.util.CalcUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class LogarithmFuncTest {
    private final double DELTA = 0.005;

    @ParameterizedTest
    @DisplayName("testing log func when values <= 0")
    @ValueSource(doubles = {0, -1, -5.33})
    void logFunc_shouldThrowException(double dot) {
        assertThrows(IllegalArgumentException.class, () -> LogarithmFunc.calc(dot));
    }

    @ParameterizedTest
    @DisplayName("testing log func without stubs")
    @ValueSource(doubles = {1, 1.5, Math.E, 3})
    void logFunc_withoutStubs(double dot) {
        assertEquals(CalcUtil.calcExpected(dot), LogarithmFunc.calc(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("testing log func with ln stub")
    @ValueSource(doubles = {1, 1.5, Math.E, 3})
    void logFunc_withLnStub(double dot) {
        try (MockedStatic<Ln> mocked = mockStatic(Ln.class)) {
            mocked.when(() -> Ln.calcLn(dot)).thenReturn(Math.log(dot));
            mocked.when(() -> Ln.calcLn(Math.pow(dot, 3))).thenReturn(Math.log(Math.pow(dot, 3)));
            mocked.when(() -> Ln.calcLn(3)).thenReturn(Math.log(3));
            mocked.when(() -> Ln.calcLn(5)).thenReturn(Math.log(5));
            mocked.when(() -> Ln.calcLn(10)).thenReturn(Math.log(10));
            assertEquals(CalcUtil.calcExpected(dot), LogarithmFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("testing log func with ln and log3 stubs")
    @ValueSource(doubles = {1, 1.5, Math.E, 3})
    void logFunc_withLnAndLog3Stub(double dot) {
        double log3 = Math.log(dot) / Math.log(3);

        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class);
             MockedStatic<Log3> mockedLog3 = mockStatic(Log3.class)) {
            mockedLn.when(() -> Ln.calcLn(dot)).thenReturn(Math.log(dot));
            mockedLn.when(() -> Ln.calcLn(Math.pow(dot, 3))).thenReturn(Math.log(Math.pow(dot, 3)));
            mockedLog3.when(() -> Log3.calcLog3(dot)).thenReturn(log3);
            mockedLn.when(() -> Ln.calcLn(5)).thenReturn(Math.log(5));
            mockedLn.when(() -> Ln.calcLn(10)).thenReturn(Math.log(10));
            assertEquals(CalcUtil.calcExpected(dot), LogarithmFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("testing log func with all stubs")
    @ValueSource(doubles = {1, 1.5, Math.E, 3})
    void testFunc_allStubs(double dot) {
        double log3 = Math.log(dot) / Math.log(3);
        double log5 = Math.log(dot) / Math.log(5);
        double log5Cube = Math.log(Math.pow(dot, 3)) / Math.log(5);

        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class);
             MockedStatic<Log3> mockedLog3 = mockStatic(Log3.class);
             MockedStatic<Log5> mockedLog5 = mockStatic(Log5.class);
             MockedStatic<Log10> mockedLog10 = mockStatic(Log10.class)) {
            mockedLn.when(() -> Ln.calcLn(dot)).thenReturn(Math.log(dot));
            mockedLog3.when(() -> Log3.calcLog3(dot)).thenReturn(log3);
            mockedLog5.when(() -> Log5.calcLog5(dot)).thenReturn(log5);
            mockedLog5.when(() -> Log5.calcLog5(Math.pow(dot, 3))).thenReturn(log5Cube);
            mockedLog10.when(() -> Log10.calcLog10(dot)).thenReturn(Math.log10(dot));
            assertEquals(CalcUtil.calcExpected(dot), LogarithmFunc.calc(dot), DELTA);
        }
    }
}
