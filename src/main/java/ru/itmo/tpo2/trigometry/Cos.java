package ru.itmo.tpo2.trigometry;

public class Cos {
    /**
     * Calc cos(x)
     *
     * @param x arg in rad
     * @return cos(x)
     */
    public static double calcCos(double x) {
        x = TrigUtil.makeInRange(x);

        if (x < 0)
            x = -x;
        double result = 1;
        int countOfCalculating = 13;
        int sign = -1;
        for (int i = 1; i < countOfCalculating; i++) {
            result += sign * (Math.pow(x, 2 * i) / factorial(2 * i));
            sign *= -1;
        }
        return result;
    }

    /**
     * Calc a factorial
     *
     * @param i arg
     * @return factorial(i)
     */
    private static double factorial(int i) {
        long fact = 1;
        for (int j = 2; j <= i; j++) {
            fact = fact * j;
        }
        return fact;
    }
}
