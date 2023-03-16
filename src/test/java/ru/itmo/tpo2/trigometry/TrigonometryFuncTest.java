package ru.itmo.tpo2.trigometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import ru.itmo.tpo2.util.CalcUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class TrigonometryFuncTest {
    private static final double DELTA = 0.0005;

    @ParameterizedTest
    @DisplayName("Testing trig func when dot >= 0")
    @ValueSource(doubles = {0, 1, 2})
    void trigFunc_shouldThrowException(double dot) {
        assertThrows(IllegalArgumentException.class, () -> TrigonometryFunc.calc(dot));
    }

    @ParameterizedTest
    @DisplayName("Testing trig func without stub")
    @ValueSource(doubles = {-0.1, -0.2, -1, -4})
    void trigFunc_testWithoutStub(double dot) {
        assertEquals(CalcUtil.calcExpected(dot), TrigonometryFunc.calc(dot), DELTA);
    }

    @ParameterizedTest
    @DisplayName("Testing trig func with stub on cos(x)")
    @ValueSource(doubles = {-0.1, -0.2, -1, -4})
    void trigFunc_withCosStub(double dot) {
        try (MockedStatic<Cos> mocked = mockStatic(Cos.class)) {
            mocked.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            assertEquals(CalcUtil.calcExpected(dot), TrigonometryFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("Testing trig func with stub on cos(x) and sin(x)")
    @ValueSource(doubles = {-0.1, -0.2, -1, -4})
    void trigFunc_withCosAndSinStub(double dot) {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class);
             MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            mockedCos.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            mockedSin.when(() -> Sin.calcSin(TrigUtil.makeInRange(dot))).thenReturn(Math.sin(TrigUtil.makeInRange(dot)));
            assertEquals(CalcUtil.calcExpected(dot), TrigonometryFunc.calc(dot), DELTA);
        }
    }


    @ParameterizedTest
    @DisplayName("Testing trig func with stub on cos(x), sin(x) and tg(x)")
    @ValueSource(doubles = {-0.1, -0.2, -1, -4})
    void trigFunc_withCosAndSinAndTanStub(double dot) {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class);
             MockedStatic<Sin> mockedSin = mockStatic(Sin.class);
             MockedStatic<Tan> mockedTan = mockStatic(Tan.class)) {
            mockedCos.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            mockedSin.when(() -> Sin.calcSin(TrigUtil.makeInRange(dot))).thenReturn(Math.sin(TrigUtil.makeInRange(dot)));
            mockedTan.when(() -> Tan.calcTan(TrigUtil.makeInRange(dot))).thenReturn(Math.tan(TrigUtil.makeInRange(dot)));
            assertEquals(CalcUtil.calcExpected(dot), TrigonometryFunc.calc(dot), DELTA);
        }
    }

    @ParameterizedTest
    @DisplayName("Testing trig func with all stubs")
    @ValueSource(doubles = {-0.1, -0.2, -1, -4})
    void trigFunc_withAllStubs(double dot) {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class);
             MockedStatic<Sin> mockedSin = mockStatic(Sin.class);
             MockedStatic<Tan> mockedTan = mockStatic(Tan.class);
             MockedStatic<Cot> mockedCot = mockStatic(Cot.class)) {
            mockedCos.when(() -> Cos.calcCos(TrigUtil.makeInRange(dot))).thenReturn(Math.cos(TrigUtil.makeInRange(dot)));
            mockedSin.when(() -> Sin.calcSin(TrigUtil.makeInRange(dot))).thenReturn(Math.sin(TrigUtil.makeInRange(dot)));
            mockedTan.when(() -> Tan.calcTan(TrigUtil.makeInRange(dot))).thenReturn(Math.tan(TrigUtil.makeInRange(dot)));
            mockedCot.when(() -> Cot.calcCot(TrigUtil.makeInRange(dot))).thenReturn(1 / Math.tan(TrigUtil.makeInRange(dot)));
            assertEquals(CalcUtil.calcExpected(dot), TrigonometryFunc.calc(dot), DELTA);
        }
    }
}
