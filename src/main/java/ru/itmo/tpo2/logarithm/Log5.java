package ru.itmo.tpo2.logarithm;

public class Log5 {
    /**
     * Calc log5(x)
     *
     * @param x
     * @return
     */
    public static double calcLog5(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();

        return Ln.calcLn(x) / Ln.calcLn(5);
    }
}
