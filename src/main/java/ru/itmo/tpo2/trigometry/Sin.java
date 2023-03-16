package ru.itmo.tpo2.trigometry;

public class Sin {

    /**
     * calc sin
     *
     * @param x
     * @return
     */
    public static double calcSin(double x) {
        x = TrigUtil.makeInRange(x);
        double value = Math.sqrt(Math.abs(1 - Math.pow(Cos.calcCos(x), 2)));
        if (x > 0)
            return x < Math.PI ? value : -value;
        return x > -Math.PI ? -value : value;
    }
}
