package com.timethor.terracontrol.core.layer;

/**
 * @author   Timethor
 */
public class LayerLand extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     * @param _chance
     */
    public LayerLand(long paramLong, Layer paramGenLayer, int _chance) {
        super(paramLong);
        this.child = paramGenLayer;
        this.chance = 101 - _chance;
    }
    /**
	 */
    public int chance = 5;

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

        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, x, z, x_size, z_size);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int i = 0; i < z_size; i++) {
            for (int j = 0; j < x_size; j++) {
                SetSeed(x + j, z + i);
                if (nextInt(chance) == 0) {
                    arrayOfInt2[(j + i * x_size)] = arrayOfInt1[(j + i * x_size)] | LandBit;
                } else {
                    arrayOfInt2[(j + i * x_size)] = arrayOfInt1[(j + i * x_size)];
                }
            }
        }

        return arrayOfInt2;
    }
}
