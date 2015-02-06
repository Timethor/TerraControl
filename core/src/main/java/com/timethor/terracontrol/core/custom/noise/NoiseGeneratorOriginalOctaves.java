package com.timethor.terracontrol.core.custom.noise;

import java.util.Random;

/**
 * @author   Timethor
 */
public class NoiseGeneratorOriginalOctaves {

    /**
	 */
    private NoiseGeneratorOriginal[] a;
    /**
	 */
    private int b;

    /**
     *
     * @param paramRandom
     * @param paramInt
     */
    public NoiseGeneratorOriginalOctaves(Random paramRandom, int paramInt) {
        this.b = paramInt;
        this.a = new NoiseGeneratorOriginal[paramInt];
        for (int i = 0; i < paramInt; i++) {
            this.a[i] = new NoiseGeneratorOriginal(paramRandom);
        }
    }

    /**
     *
     * @param doubleArray
     * @param dx
     * @param dy
     * @param i
     * @param j
     * @param dxd
     * @param dyd
     * @param dscale1
     * @return
     */
    public double[] a(double[] doubleArray, double dx, double dy, int i, int j, double dxd, double dyd, double dscale1) {
        return a(doubleArray, dx, dy, i, j, dxd, dyd, dscale1, 0.5D);
    }

    /**
     *
     * @param doubleArray
     * @param dx
     * @param dy
     * @param i
     * @param j
     * @param dxd
     * @param dyd
     * @param dscale1
     * @param dscale2
     * @return
     */
    public double[] a(double[] doubleArray, double dx, double dy, int i, int j, double dxd, double dyd, double dscale1, double dscale2) {
        dxd /= 1.5D;
        dyd /= 1.5D;

        if ((doubleArray == null) || (doubleArray.length < i * j)) {
            doubleArray = new double[i * j];
        } else {
            for (int m = 0; m < doubleArray.length; m++) {
                doubleArray[m] = 0.0D;
            }
        }
        double d1p = 1.0D;
        double d2p = 1.0D;
        for (int n = 0; n < this.b; n++) {
            this.a[n].a(doubleArray, dx, dy, i, n, dxd * d2p, dyd * d2p, 0.55D / d1p);
            d2p *= dscale1;
            d1p *= dscale2;
        }

        return doubleArray;
    }
}