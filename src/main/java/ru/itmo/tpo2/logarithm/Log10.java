package ru.itmo.tpo2.logarithm;

public class Log10 {
    /**
     * Calc log10(x)
     *
     * @param x
     * @return
     */
    public static double calcLog10(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();

        return Ln.calcLn(x) / Ln.calcLn(10);
    }
}
