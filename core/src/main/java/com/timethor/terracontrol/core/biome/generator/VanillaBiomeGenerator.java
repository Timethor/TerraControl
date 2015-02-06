package com.timethor.terracontrol.core.biome.generator;

import com.timethor.terracontrol.core.TerraWorld;

/**
 * Dummy class. Handled by special if-statements in the code.
 *
 */
public class VanillaBiomeGenerator extends BiomeGenerator {

    /**
     *
     * @param world
     * @param cache
     */
    public VanillaBiomeGenerator(TerraWorld world, BiomeCache cache) {
        super(world, cache);
    }

    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int x_size, int z_size) {
        return null;
    }

    /**
     *
     * @param paramArrayOfFloat
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public float[] getTemperatures(float[] paramArrayOfFloat, int x, int z, int x_size, int z_size) {
        return null;
    }

    /**
     *
     * @param paramArrayOfFloat
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public float[] getRainfall(float[] paramArrayOfFloat, int x, int z, int x_size, int z_size) {
        return null;
    }

    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public int[] getBiomes(int[] biomeArray, int x, int z, int x_size, int z_size) {
        return null;
    }

    /**
     *
     * @param x
     * @param z
     * @return
     */
    @Override
    public int getBiome(int x, int z) {
        return 0;
    }

    /**
     *
     */
    @Override
    public void cleanupCache() {
    }
}
