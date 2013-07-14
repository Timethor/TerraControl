package com.timethor.terracontrol.core.util;

public class MathHelper {

    private static float[] a = new float[65536];

    public static float sqrt(float key) {
        return (float) Math.sqrt(key);
    }

    public static double sqrt(double key) {
        return Math.sqrt(key);
    }

    public static float sin(float f) {
        return a[((int) (f * 10430.378F) & 0xFFFF)];
    }

    public static float cos(float f) {
        return a[((int) (f * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    public static int floor_int(double d) {
        int i = (int) d;

        return d < (double) i ? i - 1 : i;
    }

    public static long floor_long(double d) {
        long l = (long) d;
        return d >= (double) l ? l : l - 1L;
    }

    public static double floor_double(double d) {

        return (double) floor_long(d);
    }

    public static int abs(int a) {
        return (a <= 0) ? -a : a;
    }

    public static double abs(double a) {
        return (a <= 0.0F) ? 0.0F - a : a;
    }

    static {
        for (int i = 0; i < 65536; i++) {
            a[i] = (float) Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
        }
    }
}
