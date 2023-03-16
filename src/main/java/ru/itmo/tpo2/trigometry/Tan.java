package ru.itmo.tpo2.trigometry;

public class Tan {

    /**
     * calculating tg
     *
     * @param x
     * @return
     */
    public static double calcTan(double x) {
        x = TrigUtil.makeInRange(x);
        if (x % (Math.PI / 2) == 0 && x % Math.PI != 0)
            throw new IllegalArgumentException();
        double cos = Cos.calcCos(x);
        double sin = Sin.calcSin(x);


        return sin / cos;
    }
}
