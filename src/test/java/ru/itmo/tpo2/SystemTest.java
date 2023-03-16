package ru.itmo.tpo2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo2.logarithm.LogarithmFunc;
import ru.itmo.tpo2.trigometry.TrigonometryFunc;
import ru.itmo.tpo2.util.CalcUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class SystemTest {

    private static final double DELTA = 0.001;

    @Test
    @DisplayName("Testing system when value is 0")
    void system_whenZero_shouldReturnNan() {
        assertEquals(Double.NaN, SystemFunc.calc(0.0), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Test all system on random values")
    @ValueSource(doubles = {-3, -2, -1, 1, 2.33})
    void system_withoutStubs(double dot) {
        assertEquals(CalcUtil.calcExpected(dot), SystemFunc.calc(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Test system when trig part is stub")
    @ValueSource(doubles = {-3, -2, -1, 1, 2.33})
    void system_WithTrigStub(double dot) {
        double expected = CalcUtil.calcExpected(dot);
        try (MockedStatic<TrigonometryFunc> mocked = mockStatic(TrigonometryFunc.class)) {
            mocked.when(() -> TrigonometryFunc.calc(dot)).thenReturn(expected);
            assertEquals(expected, SystemFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("Test system when log part is stub")
    @ValueSource(doubles = {-3, -2, -1, 1, 2.33})
    void system_WithLogStub(double dot) {
        double expected = CalcUtil.calcExpected(dot);
        try (MockedStatic<LogarithmFunc> mocked = mockStatic(LogarithmFunc.class)) {
            mocked.when(() -> LogarithmFunc.calc(dot)).thenReturn(expected);
            assertEquals(expected, SystemFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("Test system when all stubs")
    @ValueSource(doubles = {-3, -2, -1, 1, 2.33})
    void system_WithAllStubs(double dot) {
        double expected = CalcUtil.calcExpected(dot);
        try (MockedStatic<TrigonometryFunc> mockedTrig = mockStatic(TrigonometryFunc.class);
             MockedStatic<LogarithmFunc> mockedLog = mockStatic(LogarithmFunc.class)) {
            mockedTrig.when(() -> TrigonometryFunc.calc(dot)).thenReturn(expected);
            mockedLog.when(() -> LogarithmFunc.calc(dot)).thenReturn(expected);
            assertEquals(expected, SystemFunc.calc(dot), DELTA);
        }
    }
}
