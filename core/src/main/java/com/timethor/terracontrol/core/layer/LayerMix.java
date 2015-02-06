package com.timethor.terracontrol.core.layer;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.BiomeCatalog;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.WorldConfig;

/**
 * @author   Timethor
 */
public class LayerMix extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     * @param config
     * @param world
     */
    public LayerMix(long paramLong, Layer paramGenLayer, WorldConfig config, TerraWorld world) {
        super(paramLong);
        this.child = paramGenLayer;
        this.worldConfig = config;
        this.RiverBiomes = new int[world.getMaxBiomesCount()];

        for (int id = 0; id < this.RiverBiomes.length; id++) {
            BiomeConfig biomeConfig = config.biomeConfigs[id];

            if (biomeConfig == null || biomeConfig.RiverBiome.isEmpty()) {
                this.RiverBiomes[id] = -1;
            } else {
                this.RiverBiomes[id] = world.getBiomeIdByName(biomeConfig.RiverBiome);
            }

        }
    }
    /**
	 */
    private WorldConfig worldConfig;
    /**
	 */
    private int[] RiverBiomes;

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

        int currentPiece;
        int cachedId;
        for (int i = 0; i < z_size; i++) {
            for (int j = 0; j < x_size; j++) {
                currentPiece = arrayOfInt1[(j + i * x_size)];

                if ((currentPiece & LandBit) != 0) {
                    cachedId = currentPiece & BiomeBits;
                } else if (this.worldConfig.FrozenOcean && (currentPiece & IceBit) != 0) {
                    cachedId = BiomeCatalog.FROZEN_OCEAN.Id;
                } else {
                    cachedId = BiomeCatalog.OCEAN.Id;
                }

                if (this.worldConfig.RiversEnabled && (currentPiece & RiverBits) != 0 && !this.worldConfig.biomeConfigs[cachedId].RiverBiome.isEmpty()) {
                    currentPiece = this.RiverBiomes[cachedId];
                } else {
                    currentPiece = cachedId;
                }

                arrayOfInt2[(j + i * x_size)] = currentPiece;
            }
        }

        return arrayOfInt2;
    }
}
