package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerSmooth extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerSmooth(long paramLong, Layer paramGenLayer) {
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
                int i2 = arrayOfInt1[(i1 + 0 + (n + 1) * k)];
                int i3 = arrayOfInt1[(i1 + 2 + (n + 1) * k)];
                int i4 = arrayOfInt1[(i1 + 1 + (n + 0) * k)];
                int i5 = arrayOfInt1[(i1 + 1 + (n + 2) * k)];
                int i6 = arrayOfInt1[(i1 + 1 + (n + 1) * k)];
                if ((i2 == i3) && (i4 == i5)) {
                    SetSeed(i1 + x, n + z);
                    if (nextInt(2) == 0) {
                        i6 = i2;
                    } else {
                        i6 = i4;
                    }
                } else {
                    if (i2 == i3) {
                        i6 = i2;
                    }
                    if (i4 == i5) {
                        i6 = i4;
                    }
                }
                arrayOfInt2[(i1 + n * x_size)] = i6;
            }

        }

        return arrayOfInt2;
    }
}