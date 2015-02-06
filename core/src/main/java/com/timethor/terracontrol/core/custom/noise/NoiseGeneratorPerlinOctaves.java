package com.timethor.terracontrol.core.custom.noise;

import com.timethor.terracontrol.core.util.MathHelper;
import java.util.Random;

/**
 * @author   Timethor
 */
public class NoiseGeneratorPerlinOctaves {

    /**
	 */
    private NoiseGeneratorPerlin[] gens;
    /**
	 */
    private int numGens;

    /**
     *
     * @param random
     * @param numGens
     */
    public NoiseGeneratorPerlinOctaves(Random random, int numGens) {
        this.numGens = numGens;
        this.gens = new NoiseGeneratorPerlin[numGens];

        for (int i = 0; i < numGens; ++i) {
            this.gens[i] = new NoiseGeneratorPerlin(random);
        }
    }

    /**
     *
     * @param ad
     * @param i
     * @param j
     * @param k
     * @param x
     * @param y
     * @param z
     * @param d0
     * @param d1
     * @param d2
     * @return
     */
    public double[] Noise3D(double[] ad, int i, int j, int k, int x, int y, int z, double d0, double d1, double d2) {        
        if (ad == null) {//>>	Create an approprietely sized array if none given
            ad = new double[x * y * z];
        } else {//>>	Initialize
            for (int dAi = 0; dAi < ad.length; ++dAi) {
                ad[dAi] = 0.0D;
            }
        }
        
        double persistence = 1.0D;

        
        for (int l1 = 0; l1 < this.numGens; ++l1) {
            double di = (double) i * persistence * d0;
            double dj = (double) j * persistence * d1;
            double dk = (double) k * persistence * d2;
            long fi = MathHelper.floor_long(di);
            long fk = MathHelper.floor_long(dk);

            di -= (double) fi;
            dk -= (double) fk;
            fi &= 16777215L;
            fk &= 16777215L;
            di += (double) fi;
            dk += (double) fk;
            this.gens[l1].noise(ad, new double[]{
                x, y, z
            }, new double[]{
                di, dj, dk
            }, new double[]{
                d0 * persistence, d1 * persistence, d2 * persistence
            }, persistence);
            persistence /= 2.0D;
        }

        return ad;
    }

    /**
     *
     * @param doubleArray
     * @param i
     * @param j
     * @param k
     * @param l
     * @param d0
     * @param d1
     * @return
     */
    public double[] Noise2D(double[] doubleArray, int i, int j, int k, int l, double d0, double d1) {
        return this.Noise3D(doubleArray, i, 10, j, k, 1, l, d0, 1.0D, d1);
    }
}
