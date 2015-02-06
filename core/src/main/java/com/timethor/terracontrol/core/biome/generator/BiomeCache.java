package com.timethor.terracontrol.core.biome.generator;

/**
 *
 * @author Timethor
 */
public interface BiomeCache {

    /**
     *
     * @param x
     * @param z
     * @return
     */
    public int getBiome(int x, int z);

    /**
     *
     */
    public void cleanupCache();

    /**
     *
     * @param x
     * @param z
     * @return
     */
    public int[] getCachedBiomes(int x, int z);
}
