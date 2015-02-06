package com.timethor.terracontrol.core.biome.generator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.BiomeCatalog;
import com.timethor.terracontrol.core.custom.noise.NoiseGeneratorOriginalOctaves;

import java.util.Random;

/**
 * @author   Timethor
 */
public class OldBiomeGenerator extends BiomeGenerator {

    /**
	 */
    private NoiseGeneratorOriginalOctaves temperatureGenerator1;
    /**
	 */
    private NoiseGeneratorOriginalOctaves wetnessGenerator;
    /**
	 */
    private NoiseGeneratorOriginalOctaves temperatureGenerator2;
    /**
	 */
    public double[] oldTemperature1;
    /**
	 */
    public double[] oldWetness;
    /**
	 */
    private double[] oldTemperature2;
    private static int[] biomeDiagram = new int[4096];
    private static boolean hasGeneratedBiomeDiagram;

    /**
     *
     * @param world
     * @param cache
     */
    public OldBiomeGenerator(TerraWorld world, BiomeCache cache) {
        super(world, cache);
        this.temperatureGenerator1 = new NoiseGeneratorOriginalOctaves(new Random(world.getSeed() * 9871L), 4);
        this.wetnessGenerator = new NoiseGeneratorOriginalOctaves(new Random(world.getSeed() * 39811L), 4);
        this.temperatureGenerator2 = new NoiseGeneratorOriginalOctaves(new Random(world.getSeed() * 543321L), 2);

        if (!hasGeneratedBiomeDiagram) {
            hasGeneratedBiomeDiagram = true;
            OldBiomeGenerator.generateBiomeDiagram();
        }
    }

    /**
     *
     * @param temp_out
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public float[] getTemperatures(float[] temp_out, int x, int z, int x_size, int z_size) {
        if ((temp_out == null) || (temp_out.length < x_size * z_size)) {
            temp_out = new float[x_size * z_size];
        }

        this.oldTemperature1 = this.temperatureGenerator1.a(this.oldTemperature1, x, z, x_size, z_size, 0.025000000372529D / worldConfig.oldBiomeSize, 0.025000000372529D / worldConfig.oldBiomeSize, 0.25D);
        this.oldTemperature2 = this.temperatureGenerator2.a(this.oldTemperature2, x, z, x_size, z_size, 0.25D / worldConfig.oldBiomeSize, 0.25D / worldConfig.oldBiomeSize, 0.5882352941176471D);

        int i = 0;
        for (int j = 0; j < x_size; j++) {
            for (int k = 0; k < z_size; k++) {
                double d1 = this.oldTemperature2[i] * 1.1D + 0.5D;

                double d2 = 0.01D;
                double d3 = 1.0D - d2;
                double d4 = (temp_out[i] * 0.15D + 0.7D) * d3 + d1 * d2;
                d4 = 1.0D - (1.0D - d4) * (1.0D - d4);

                if (d4 < worldConfig.minTemperature) {
                    d4 = worldConfig.minTemperature;
                }
                if (d4 > worldConfig.maxTemperature) {
                    d4 = worldConfig.maxTemperature;
                }
                temp_out[i] = (float) d4;
                i++;
            }

        }
        if (worldConfig.isDeprecated) {
            worldConfig = worldConfig.newSettings;
        }

        return temp_out;
    }

    /**
     *
     * @param temp_out
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    public float[] getRainfall(float[] temp_out, int x, int z, int x_size, int z_size) {
        if ((temp_out == null) || (temp_out.length < x_size * z_size)) {
            temp_out = new float[x_size * z_size];
        }
        // int[] temp_biomeBases = new int[x_size * z_size];
        // temp_biomeBases = this.getBiomes(temp_biomeBases, x, z, x_size, z_size, false);

        for (int i = 0; i < temp_out.length; i++) {
            temp_out[i] = (float) this.oldWetness[i];
        }

        return temp_out;
    }

    /**
     *
     * @param paramArrayOfBiomeBase
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @param useCache
     * @return
     */
    public int[] getBiomes(int[] paramArrayOfBiomeBase, int x, int z, int x_size, int z_size, boolean useCache) {
        if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < x_size * z_size)) {
            paramArrayOfBiomeBase = new int[x_size * z_size];
        }
        if ((useCache) && (x_size == 16) && (z_size == 16) && ((x & 0xF) == 0) && ((z & 0xF) == 0)) {
            int[] localObject = this.cache.getCachedBiomes(x, z);
            System.arraycopy(localObject, 0, paramArrayOfBiomeBase, 0, x_size * z_size);
            return paramArrayOfBiomeBase;
        }

        this.oldTemperature1 = this.temperatureGenerator1.a(this.oldTemperature1, x, z, x_size, x_size, 0.025000000372529D / worldConfig.oldBiomeSize, 0.025000000372529D / worldConfig.oldBiomeSize, 0.25D);
        this.oldWetness = this.wetnessGenerator.a(this.oldWetness, x, z, x_size, x_size, 0.0500000007450581D / worldConfig.oldBiomeSize, 0.0500000007450581D / worldConfig.oldBiomeSize, 0.3333333333333333D);
        this.oldTemperature2 = this.temperatureGenerator2.a(this.oldTemperature2, x, z, x_size, x_size, 0.25D / worldConfig.oldBiomeSize, 0.25D / worldConfig.oldBiomeSize, 0.5882352941176471D);

        int i = 0;
        for (int j = 0; j < x_size; j++) {
            for (int k = 0; k < z_size; k++) {
                double d1 = this.oldTemperature2[i] * 1.1D + 0.5D;

                double d2 = 0.01D;
                double d3 = 1.0D - d2;
                double d4 = (this.oldTemperature1[i] * 0.15D + 0.7D) * d3 + d1 * d2;
                d2 = 0.002D;
                d3 = 1.0D - d2;
                double d5 = (this.oldWetness[i] * 0.15D + 0.5D) * d3 + d1 * d2;
                d4 = 1.0D - (1.0D - d4) * (1.0D - d4);

                if (d4 < worldConfig.minTemperature) {
                    d4 = worldConfig.minTemperature;
                }
                if (d5 < worldConfig.minMoisture) {
                    d5 = worldConfig.minMoisture;
                }
                if (d4 > worldConfig.maxTemperature) {
                    d4 = worldConfig.maxTemperature;
                }
                if (d5 > worldConfig.maxMoisture) {
                    d5 = worldConfig.maxMoisture;
                }
                this.oldTemperature1[i] = d4;
                this.oldWetness[i] = d5;

                paramArrayOfBiomeBase[(i++)] = OldBiomeGenerator.getBiomeFromDiagram(d4, d5);
            }

        }

        if (this.worldConfig.isDeprecated) {
            this.worldConfig = this.worldConfig.newSettings;
        }

        return paramArrayOfBiomeBase;
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
        return getBiomes(biomeArray, x, z, x_size, z_size, false);
    }

    /**
     *
     * @param x
     * @param z
     * @return
     */
    @Override
    public int getBiome(int x, int z) {
        return this.cache.getBiome(x, z);
    }

    /**
     *
     */
    @Override
    public void cleanupCache() {
        this.cache.cleanupCache();
    }

    private static int getBiomeFromDiagram(double temp, double rain) {
        int i = (int) (temp * 63.0D);
        int j = (int) (rain * 63.0D);
        return biomeDiagram[(i + j * 64)];
    }

    private static void generateBiomeDiagram() {
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                biomeDiagram[(i + j * 64)] = generatePositionOnBiomeDiagram(i / 63.0F, j / 63.0F);
            }
        }
    }

    private static int generatePositionOnBiomeDiagram(double paramFloat1, double paramFloat2) {
        paramFloat2 *= paramFloat1;
        if (paramFloat1 < 0.1F) {
            return BiomeCatalog.PLAINS.Id;
        }
        if (paramFloat2 < 0.2F) {
            if (paramFloat1 < 0.5F) {
                return BiomeCatalog.PLAINS.Id;
            }
            if (paramFloat1 < 0.95F) {
                return BiomeCatalog.PLAINS.Id;
            }
            return BiomeCatalog.DESERT.Id;
        }
        if ((paramFloat2 > 0.5F) && (paramFloat1 < 0.7F)) {
            return BiomeCatalog.SWAMPLAND.Id;
        }
        if (paramFloat1 < 0.5F) {
            return BiomeCatalog.TAIGA.Id;
        }
        if (paramFloat1 < 0.97F) {
            if (paramFloat2 < 0.35F) {
                return BiomeCatalog.TAIGA.Id;
            }
            return BiomeCatalog.FOREST.Id;
        }

        if (paramFloat2 < 0.45F) {
            return BiomeCatalog.PLAINS.Id;
        }

        if (paramFloat2 < 0.9F) {
            return BiomeCatalog.FOREST.Id;
        }

        return BiomeCatalog.FOREST.Id;
    }
}
