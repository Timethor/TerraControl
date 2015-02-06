package com.timethor.terracontrol.core.custom.noise;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.util.MathHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;

/**
 * @author   Timethor
 */
public class NoiseGeneratorPerlin implements NoiseGenerator {

    /**
	 */
    private int perms[];
    /**
	 */
    public double xCoord;
    /**
	 */
    public double yCoord;
    /**
	 */
    public double zCoord;

    /**
     *
     * @param random
     */
    public NoiseGeneratorPerlin(Random random) {
        perms = new int[512];
        xCoord = random.nextDouble() * 256D;
        yCoord = random.nextDouble() * 256D;
        zCoord = random.nextDouble() * 256D;
        // get 0 thru 255
        for (int i = 0; i < 256; i++) {
            perms[i] = i;
        }
        // shuffle and copy, seed based
        for (int j = 0; j < 256; j++) {
            int k = random.nextInt(256 - j) + j;
            // swap k,j: copy k to max+j
            int l = perms[j];
            perms[j] = perms[j + 256] = perms[k];
            perms[k] = l;
        }
    }

    /**
     *
     * @param t
     * @param a
     * @param b
     * @return
     */
    public final double lerp(double t, double a, double b) {

        return a + t * (b - a);
    }

    /**
     *
     * @param hash
     * @param x
     * @param y
     * @return
     */
    public final double grad2(int hash, double x, double y) {
        int h = hash & 0xf;
        double u = (double) (1 - ((h & 8) >> 3)) * x;
        double v = h >= 4 ? h != 12 && h != 14 ? y : x : 0.0D;
        return ((h & 1) != 0 ? -u : u) + ((h & 2) != 0 ? -v : v);
    }

    /**
     *
     * @param hash
     * @param x
     * @param y
     * @param z
     * @return
     */
    public final double grad3(int hash, double x, double y, double z) {
        int h = hash & 0xf;
        double u = h >= 8 ? y : x;
        double v = (h >= 4) ? ((h != 12 && h != 14) ? z : x) : y;
        return ((h & 1) != 0 ? -u : u) + ((h & 2) != 0 ? -v : v);
    }

    static double fade(double t) {
        return t * t * t * (t * (t * 6D - 15D) + 10D);
    }

    /**
     *
     * @param hash
     * @param d1
     * @param d2
     * @param t
     * @return
     */
    public static double shape(int hash, double d1, double d2, double t) {
        return d1 + (double) hash * d2 + t;
    }

    /**
     *
     * @param ad
     * @param xz
     * @param ik
     * @param dxz
     * @param dscale
     */
    public void noise2(double ad[], double[] xz, int[] ik, double[] dxz, double dscale) {
        double dx = xz[0], dz = xz[1];
        double dxd = dxz[0], dzd = dxz[1];
        int i = ik[0], k = ik[1];
        int adi = 0;
        double amplitude = 1.0D / dscale;
        for (int xi = 0; xi < i; xi++) {
            double x = shape(xi, dx, dxd, xCoord);
            int X = MathHelper.floor_int(x);
            x -= X;
            X %= 0x100;
            double u = fade(x);
            for (int zi = 0; zi < k; zi++) {
                double z = shape(zi, dz, dzd, zCoord);
                int Z = MathHelper.floor_int(z);
                z -= Z;
                Z = Z & 0xff;
                double v = fade(z);

                int A = perms[X] + 0;
                int AA = perms[A] + Z;
                int B = perms[X + 1] + 0;
                int BA = perms[B] + Z;

                ad[adi++] += lerp(
                    v,
                    lerp(
                    u,
                    grad2(perms[AA], x, z),
                    grad3(perms[BA], x - 1.0D, 0.0D, z)),
                    lerp(
                    u,
                    grad3(perms[AA + 1], x, 0.0D, z - 1.0D),
                    grad3(perms[BA + 1], x - 1.0D, 0.0D, z - 1.0D))) * amplitude;
            }
        }
    }

    /**
     *
     * @param ad
     * @param xyz
     * @param ijk
     * @param dxyz
     * @param dscale
     */
    public void noise3(double ad[], double[] xyz, int[] ijk, double[] dxyz, double dscale) {
        double dx = xyz[0], dy = xyz[1], dz = xyz[2];
        double dxd = dxyz[0], dyd = dxyz[1], dzd = dxyz[2];
        int i = ijk[0], j = ijk[1], k = ijk[2];
        int adi = 0;
        double amplitude = 1.0D / dscale;
        int i2 = -1;
        double v11 = 0.0D, v12 = 0.0D, v21 = 0.0D, v22 = 0.0D;
        for (int xi = 0; xi < i; xi++) {
            double x = shape(xi, dx, dxd, xCoord);
            int X = MathHelper.floor_int(x);
            x -= X;
            X = X & 0xff;
            double u = fade(x);
            for (int zi = 0; zi < k; zi++) {
                double z = shape(zi, dz, dzd, zCoord);
                int Z = MathHelper.floor_int(z);
                z -= Z;
                Z = Z & 0xff;
                double w = fade(z);
                for (int yi = 0; yi < j; yi++) {
                    double y = shape(yi, dy, dyd, yCoord);
                    int Y = MathHelper.floor_int(y);
                    y -= Y;
                    Y = Y & 0xff;
                    double v = fade(y);
                    if (yi == 0 || Y != i2) {
                        i2 = Y;
                        int A = perms[X] + Y,
                            AA = perms[A] + Z,
                            AB = perms[A + 1] + Z,
                            B = perms[X + 1] + Y,
                            BA = perms[B] + Z,
                            BB = perms[B + 1] + Z;

                        v11 = lerp(u, grad3(perms[AA], x, y, z), grad3(perms[BA], x - 1.0D, y, z));
                        v12 = lerp(u, grad3(perms[AB], x, y - 1.0D, z), grad3(perms[BB], x - 1.0D, y - 1.0D, z));
                        v21 = lerp(u, grad3(perms[AA + 1], x, y, z - 1.0D), grad3(perms[BA + 1], x - 1.0D, y, z - 1.0D));
                        v22 = lerp(u, grad3(perms[AB + 1], x, y - 1.0D, z - 1.0D), grad3(perms[BB + 1], x - 1.0D, y - 1.0D, z - 1.0D));
                    }
                    ad[adi++] += lerp(w, lerp(v, v11, v12), lerp(v, v21, v22)) * amplitude;
                }
            }
        }
    }

    /**
     *
     * @param ad
     * @param dx
     * @param dy
     * @param dz
     * @param i
     * @param j
     * @param k
     * @param dxd
     * @param dyd
     * @param dzd
     * @param dscale
     */
    public void noise(double ad[], double dx, double dy, double dz, int i, int j, int k, double dxd, double dyd, double dzd, double dscale) {
    }

    /**
     *
     * @param ad
     * @param ijk
     * @param xyz
     * @param dxyz
     * @param dscale
     */
    @Override
    public void noise(double[] ad, double[] ijk, double[] xyz, double[] dxyz, double dscale) {
        try {
            NoiseGeneratorPerlin.class.getMethod("noise" + ijk.length).invoke(ad, ijk, xyz, dxyz, dscale);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            TerraControl.log(Level.WARNING, "Class: noise" + ijk.length + " is not implemented.");
        }


    }
}
