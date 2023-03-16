package ru.itmo.tpo2.logarithm;

public class Log3 {
    /**
     * Calc log3(x)
     *
     * @param x
     * @return
     */
    public static double calcLog3(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();

        return Ln.calcLn(x) / Ln.calcLn(3);
    }
}
