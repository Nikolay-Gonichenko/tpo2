package ru.itmo.tpo2.util;

import static java.lang.Math.*;

public class CalcUtil {
    public static double calcExpected(double dot) {
        if (dot == 0)
            throw new IllegalArgumentException();

        if (dot < 0) {
            return (((((cos(dot) * tan(dot)) * sin(dot)) - cos(dot)) - (1 / tan(dot))) * (tan(dot) - cos(dot)));
        }

        double ln = Math.log(dot);
        double log3 = Math.log(dot) / Math.log(3);
        double log5 = Math.log(dot) / Math.log(5);
        double log10 = Math.log10(dot);
        double log5Cube = Math.log(Math.pow(dot, 3)) / Math.log(5);
        return ((((log5Cube / log3) - ln) / log5) / (log3 * log5)) / (log10 * log5);

    }
}
