package ru.itmo.tpo2.logarithm;

public class Ln {

    /**
     * Calc ln
     *
     * @param x
     * @return
     */
    public static double calcLn(double x) {
        if (x <= 0)
            throw new IllegalArgumentException();


        double result = 0;
        int n = 1000000;
        if (Math.abs(x - 1) <= 1) {
            for (int i = 1; i < n; i++) {
                result += ((Math.pow(-1, i - 1) * Math.pow(x - 1, i)) / i);
            }
        } else {
            for (int i = 1; i < n; i++) {
                result += ((Math.pow(-1, i - 1) * Math.pow(x - 1, -i)) / i);
            }
            result += calcLn(x - 1);
        }
        return result;
    }
}
