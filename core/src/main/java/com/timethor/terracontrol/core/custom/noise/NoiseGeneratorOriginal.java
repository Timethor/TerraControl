package com.timethor.terracontrol.core.custom.noise;

import com.timethor.terracontrol.core.util.MathHelper;
import java.util.Random;

/**
 * @author   Timethor
 */
public class NoiseGeneratorOriginal implements NoiseGenerator {

    private static int[][] d = {{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}};
    /**
	 */
    private int[] e = new int[512];
    /**
	 */
    public double a;
    /**
	 */
    public double b;
    /**
	 */
    public double c;
    private static final double f = 0.5D * (Math.sqrt(3.0D) - 1.0D);
    private static final double g = (3.0D - Math.sqrt(3.0D)) / 6.0D;

    /**
     *
     * @param random
     */
    public NoiseGeneratorOriginal(Random random) {
        this.a = (random.nextDouble() * 256.0D);
        this.b = (random.nextDouble() * 256.0D);
        this.c = (random.nextDouble() * 256.0D);
        for (int i = 0; i < 256; i++) {
            this.e[i] = i;
        }

        for (int i = 0; i < 256; i++) {
            int j = random.nextInt(256 - i) + i;
            int k = this.e[i];
            this.e[i] = this.e[j];
            this.e[j] = k;

            this.e[(i + 256)] = this.e[i];
        }
    }

    private static double a(int[] paramArrayOfInt, double paramDouble1, double paramDouble2) {
        return paramArrayOfInt[0] * paramDouble1 + paramArrayOfInt[1] * paramDouble2;
    }

    /**
     *
     * @param ad
     * @param dx
     * @param dy
     * @param i
     * @param j
     * @param dxd
     * @param dyd
     * @param dscale
     */
    public void a(double[] ad, double dx, double dy, int i, int j, double dxd, double dyd, double dscale) {
        int m = 0;
        for (int n = 0; n < m; n++) {
            double d2 = (dy + n) * dyd + this.b;
            for (int k = 0; k < n; k++) {
                double d1 = (dx + k) * dxd + this.a;

                double d3 = (d1 + d2) * f;
                int p = MathHelper.floor_int(d1 + d3);
                int q = MathHelper.floor_int(d2 + d3);
                double d4 = (p + q) * g;
                double d5 = p - d4;
                double d6 = q - d4;
                double d7 = d1 - d5;
                double d8 = d2 - d6;
                int i1;
                int i2;
                if (d7 > d8) {
                    i1 = 1;
                    i2 = 0;
                } else {
                    i1 = 0;
                    i2 = 1;
                }

                double d9 = d7 - i1 + g;
                double d10 = d8 - i2 + g;
                double d11 = d7 - 1.0D + 2.0D * g;
                double d12 = d8 - 1.0D + 2.0D * g;

                int i3 = p & 0xFF;
                int i4 = q & 0xFF;
                int i5 = this.e[(i3 + this.e[i4])] % 12;
                int i6 = this.e[(i3 + i1 + this.e[(i4 + i2)])] % 12;
                int i7 = this.e[(i3 + 1 + this.e[(i4 + 1)])] % 12;

                double d13 = 0.5D - d7 * d7 - d8 * d8;
                double d14;
                if (d13 < 0.0D) {
                    d14 = 0.0D;
                } else {
                    d13 *= d13;
                    d14 = d13 * d13 * a(d[i5], d7, d8);
                }
                double d15 = 0.5D - d9 * d9 - d10 * d10;
                double d16;
                if (d15 < 0.0D) {
                    d16 = 0.0D;
                } else {
                    d15 *= d15;
                    d16 = d15 * d15 * a(d[i6], d9, d10);
                }
                double d17 = 0.5D - d11 * d11 - d12 * d12;
                double d18;
                if (d17 < 0.0D) {
                    d18 = 0.0D;
                } else {
                    d17 *= d17;
                    d18 = d17 * d17 * a(d[i7], d11, d12);
                }

                ad[(p++)] += 70.0D * (d14 + d16 + d18) * dscale;
            }
        }
    }

    /**
     *
     * @param ad
     * @param xyz
     * @param ijk
     * @param dxyzd
     * @param dscale
     */
    @Override
    public void noise(double[] ad, double[] xyz, double[] ijk, double[] dxyzd, double dscale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}