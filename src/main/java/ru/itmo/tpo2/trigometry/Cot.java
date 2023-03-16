package ru.itmo.tpo2.trigometry;

public class Cot {
    /**
     * calculating ctg
     *
     * @param x
     * @return
     */
    public static double calcCot(double x) {
        x = TrigUtil.makeInRange(x);
        if (x % Math.PI == 0)
            throw new IllegalArgumentException();

        double cos = Cos.calcCos(x);
        double sin = Sin.calcSin(x);

        return cos / sin;
    }
}
