package ru.itmo.tpo2;

import ru.itmo.tpo2.logarithm.LogarithmFunc;
import ru.itmo.tpo2.trigometry.TrigonometryFunc;

public class SystemFunc {

    public static double calc(double x) {
        try {
            if (x <= 0)
                return TrigonometryFunc.calc(x);
            return LogarithmFunc.calc(x);
        } catch (IllegalArgumentException e) {
            return Double.NaN;
        }
    }
}
