package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerRiverInit extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerRiverInit(long paramLong, Layer paramGenLayer) {
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
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, x, z, x_size, z_size);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int i = 0; i < z_size; i++) {
            for (int j = 0; j < x_size; j++) {
                SetSeed(i + z, j + x);           // reversed
                int currentPiece = arrayOfInt1[(j + i * x_size)];
                if (nextInt(2) == 0) {
                    currentPiece |= 1024;
                } else {
                    currentPiece |= 2048;
                }

                arrayOfInt2[(j + i * x_size)] = currentPiece;
            }
        }

        return arrayOfInt2;
    }
}