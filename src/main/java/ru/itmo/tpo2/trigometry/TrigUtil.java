package ru.itmo.tpo2.trigometry;

public class TrigUtil {

    public static double makeInRange(double value) {
        double PI2 = Math.PI * 2;

        if (value >= 0) {
            while (value > PI2) {
                value -= PI2;
            }
        } else {
            while (value < -PI2) {
                value += PI2;
            }
        }

        return value;
    }
}
