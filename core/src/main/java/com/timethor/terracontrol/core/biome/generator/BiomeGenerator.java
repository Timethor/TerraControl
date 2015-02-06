package com.timethor.terracontrol.core.biome.generator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.configuration.WorldConfig;

/**
 * @author   Timethor
 */
public abstract class BiomeGenerator {

    /**
	 */
    protected WorldConfig worldConfig;
    /**
	 */
    protected BiomeCache cache;
    /**
	 */
    public final Object lockObject = new Object();

    /**
     *
     * @param world
     * @param cache
     */
    public BiomeGenerator(TerraWorld world, BiomeCache cache) {
        this.worldConfig = world.getSettings();
        this.cache = cache;
    }

    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param xSize
     * @param zSize
     * @return
     */
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int xSize, int zSize) {
        // Fall back on getBiomes
        return getBiomes(biomeArray, x, z, xSize, zSize);
    }

    /**
     *
     * @param paramArrayOfFloat
     * @param x
     * @param z
     * @param xSize
     * @param zSize
     * @return
     */
    public abstract float[] getTemperatures(float[] paramArrayOfFloat, int x, int z, int xSize, int zSize);

    /**
     *
     * @param paramArrayOfFloat
     * @param x
     * @param z
     * @param xSize
     * @param zSize
     * @return
     */
    public abstract float[] getRainfall(float[] paramArrayOfFloat, int x, int z, int xSize, int zSize);

    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param xSize
     * @param zSize
     * @return
     */
    public abstract int[] getBiomes(int[] biomeArray, int x, int z, int xSize, int zSize);

    /**
     *
     * @param x
     * @param z
     * @return
     */
    public abstract int getBiome(int x, int z);

    /**
     *
     */
    public abstract void cleanupCache();
}