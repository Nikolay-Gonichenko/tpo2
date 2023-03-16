package ru.itmo.tpo2.logarithm;

public class LogarithmFunc {
    /**
     * Calculating second part of system where x > 0
     *
     * @param x
     * @return
     */
    public static double calc(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();

        double ln = Ln.calcLn(x);
        double log3 = Log3.calcLog3(x);
        double log5 = Log5.calcLog5(x);
        double log10 = Log10.calcLog10(x);
        double log5Cube = Log5.calcLog5(Math.pow(x, 3));

        return ((((log5Cube / log3) - ln) / log5) / (log3 * log5)) / (log10 * log5);
    }
}
