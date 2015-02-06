package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerEmpty extends Layer {

    /**
     *
     * @param paramLong
     */
    public LayerEmpty(long paramLong) {
        super(paramLong);
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
    @Override
    public int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        int[] arrayOfInt = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int i = 0; i < arrayOfInt.length; i++) {
            arrayOfInt[i] = 0;
        }
        return arrayOfInt;
    }
}
