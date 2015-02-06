package com.timethor.terracontrol.core.layer;

import com.timethor.terracontrol.core.TerraBiome;

/**
 * @author   Timethor
 */
public class LayerBiome extends Layer {

    /**
	 */
    public TerraBiome[] biomes;
    /**
	 */
    public TerraBiome[] ice_biomes;

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerBiome(long paramLong, Layer paramGenLayer) {
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
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, x, z, x_size, z_size);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int i = 0; i < z_size; i++) {
            for (int j = 0; j < x_size; j++) {
                SetSeed(j + x, i + z);
                int currentPiece = arrayOfInt1[(j + i * x_size)];


                if ((currentPiece & BiomeBits) == 0) // without biome
                {
                    if (this.biomes.length > 0 && (currentPiece & IceBit) == 0) // Normal Biome
                    {
                        TerraBiome biome = this.biomes[nextInt(this.biomes.length)];
                        if (biome != null) {
                            currentPiece = currentPiece | biome.getId();
                        }
                    } else if (this.ice_biomes.length > 0 && (currentPiece & IceBit) != 0) //Ice biome
                    {
                        TerraBiome biome = this.ice_biomes[nextInt(this.ice_biomes.length)];
                        if (biome != null) {
                            currentPiece = currentPiece | biome.getId();
                        }
                    }
                }

                arrayOfInt2[(j + i * x_size)] = currentPiece;


            }
        }

        return arrayOfInt2;
    }
}