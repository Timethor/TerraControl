package com.timethor.terracontrol.core.util;

/**
 * Some methods for faster trig and other math as well as some ease of use
 * methods
 */
public class MathHelper {

    private static float[] a = new float[65536];

    /**
     * Returns the square root of a float
     * <p/>
     * @param f A float value to have its square root taken
     * <p/>
     * @return The square root of a float
     */
    public static float sqrt(float f) {
        return (float) Math.sqrt(f);
    }

    /**
     * Returns the square root of a double
     * <p/>
     * @param d A double value to have its square root taken
     * <p/>
     * @return The square root of a double
     */
    public static double sqrt(double d) {
        return Math.sqrt(d);
    }

    /**
     * Returns the approximated sine of a float
     * <p/>
     * @param f A float value to get a sine of
     * <p/>
     * @return The approximated sine of a float
     */
    public static float sin(float f) {
        return a[((int) (f * 10430.378F) & 0xFFFF)];
    }

    /**
     * Returns the approximated cosine of a float
     * <p/>
     * @param f A float value to get a cosine of
     * <p/>
     * @return The approximated cosine of a float
     */
    public static float cos(float f) {
        return a[((int) (f * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    /**
     * Returns the mathematical integer floor value of a double
     * <p/>
     * @param f A double value to get the floor of
     * <p/>
     * @return The integer floor of a double
     */
    public static int floor_int(double d) {
        int i = (int) d;

        return d < (double) i ? i - 1 : i;
    }

    /**
     * Returns the mathematical long floor value of a double
     * <p/>
     * @param f A double value to get the floor of
     * <p/>
     * @return The long floor of a double
     */
    public static long floor_long(double d) {
        long l = (long) d;
        return d >= (double) l ? l : l - 1L;
    }

    /**
     * Returns the mathematical double floor value of a double
     * <p/>
     * @param f A double value to get the floor of
     * <p/>
     * @return The double floor of a double
     */
    public static double floor_double(double d) {

        return (double) floor_long(d);
    }

    /**
     * Returns the absolute value of an integer
     * <p/>
     * @param a An integer value to get the absolute value of
     * <p/>
     * @return The absolute value of an integer
     */
    public static int abs(int a) {
        return (a <= 0) ? -a : a;
    }

    /**
     * Returns the absolute value of a double
     * <p/>
     * @param a A double value to get the absolute value of
     * <p/>
     * @return The absolute value of an double
     */
    public static double abs(double a) {
        return (a <= 0.0F) ? 0.0F - a : a;
    }

    /*
     * Initializes a lookup table for the sin and cos functions above
     */
    static {
        for (int i = 0; i < 65536; i++) {
            a[i] = (float) Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
        }
    }
}
