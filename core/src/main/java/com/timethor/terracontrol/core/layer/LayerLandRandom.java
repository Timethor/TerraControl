package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerLandRandom extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerLandRandom(long paramLong, Layer paramGenLayer) {
        super(paramLong);
        this.child = paramGenLayer;
    }

    /**
     *
     * @param cacheId
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * <p/>
     * @return
     */
    public int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        int i = x - 1;
        int j = z - 1;
        int k = x_size + 2;
        int m = z_size + 2;
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, i, j, k, m);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int n = 0; n < z_size; n++) {
            for (int i1 = 0; i1 < x_size; i1++) {
                int i2 = arrayOfInt1[(i1 + 0 + (n + 0) * k)] & LandBit;
                int i3 = arrayOfInt1[(i1 + 2 + (n + 0) * k)] & LandBit;
                int i4 = arrayOfInt1[(i1 + 0 + (n + 2) * k)] & LandBit;
                int i5 = arrayOfInt1[(i1 + 2 + (n + 2) * k)] & LandBit;
                int i6 = arrayOfInt1[(i1 + 1 + (n + 1) * k)] & LandBit;
                SetSeed(i1 + x, n + z);
                arrayOfInt2[(i1 + n * x_size)] = arrayOfInt1[(i1 + 1 + (n + 1) * k)] | LandBit;
                if ((i6 == 0) && ((i2 != 0) || (i3 != 0) || (i4 != 0) || (i5 != 0))) {
                    if (nextInt(3) != 0) {
                        arrayOfInt2[(i1 + n * x_size)] ^= LandBit;
                    }

                } else if ((i6 > 0) && ((i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0))) {
                    if (nextInt(5) == 0) {
                        arrayOfInt2[(i1 + n * x_size)] ^= LandBit;
                    }

                } else if (i6 == 0) {
                    arrayOfInt2[(i1 + n * x_size)] ^= LandBit;
                }
            }
        }
        return arrayOfInt2;
    }
}