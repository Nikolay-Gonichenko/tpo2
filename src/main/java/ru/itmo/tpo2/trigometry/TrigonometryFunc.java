package ru.itmo.tpo2.trigometry;

public class TrigonometryFunc {
    /**
     * Calculating first part of system where x <= 0
     * @param x
     * @return
     */
    public static double calc(double x) {
        if (x > 0)
            throw new IllegalArgumentException();

        double cos = Cos.calcCos(x);
        double sin = Sin.calcSin(x);
        double tan = Tan.calcTan(x);
        double cot = Cot.calcCot(x);

        return (((((cos * tan) * sin) - cos) - cot) * (tan - cos));
    }
}
