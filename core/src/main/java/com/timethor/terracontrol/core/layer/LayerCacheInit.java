package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerCacheInit extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerCacheInit(long paramLong, Layer paramGenLayer) {
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
    @Override
    public int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        return new int[0];
    }

    /**
     *
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public int[] Calculate(int x, int z, int x_size, int z_size) {
        int cache = LayerCache.GetCacheId();
        int[] out = this.child.GetBiomes(cache, x, z, x_size, z_size);
        LayerCache.ReleaseCacheId(cache);
        return out;
    }
}