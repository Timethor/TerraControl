package com.timethor.terracontrol.core.layer;

import com.timethor.terracontrol.core.TerraBiome;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.configuration.BiomeConfig;

/**
 * @author Timethor
 */
public class LayerBiomeBorder extends Layer {

    /**
     *
     * @param paramLong
     * @param world
     */
    public LayerBiomeBorder(long paramLong, TerraWorld world) {
        super(paramLong);
        this.BordersFrom = new boolean[world.getMaxBiomesCount()][];
        this.BordersTo = new int[world.getMaxBiomesCount()];
    }
    /**
     */
    private boolean[][] BordersFrom;
    /**
     */
    private int[] BordersTo;

    /**
     *
     * @param ReplaceTo
     * @param ReplaceFrom
     * @param world
     */
    public void AddBiome(BiomeConfig ReplaceTo, int ReplaceFrom, TerraWorld world) {
        this.BordersFrom[ReplaceFrom] = new boolean[world.getMaxBiomesCount()];

        for (int i = 0; i < this.BordersFrom[ReplaceFrom].length; i++) {
            TerraBiome biome = world.getBiomeById(i);
            this.BordersFrom[ReplaceFrom][i] = biome == null || !ReplaceTo.NotBorderNear.contains(biome.getName());
        }
        this.BordersTo[ReplaceFrom] = ReplaceTo.Biome.getId();
    }

    /**
     *
     * @param cacheId
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * <p/>
     * @return
     */
    @Override
    public int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, x - 1, z - 1, x_size + 2, z_size + 2);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (int i = 0; i < z_size; i++) {
            for (int j = 0; j < x_size; j++) {
                SetSeed(j + x, i + z);
                int currentPiece = arrayOfInt1[(j + 1 + (i + 1) * (x_size + 2))];

                int biomeId = GetBiomeFromLayer(currentPiece);
                if (BordersFrom[biomeId] != null) {
                    int i1 = GetBiomeFromLayer(arrayOfInt1[(j + 1 + (i + 1 - 1) * (x_size + 2))]);
                    int i2 = GetBiomeFromLayer(arrayOfInt1[(j + 1 + 1 + (i + 1) * (x_size + 2))]);
                    int i3 = GetBiomeFromLayer(arrayOfInt1[(j + 1 - 1 + (i + 1) * (x_size + 2))]);
                    int i4 = GetBiomeFromLayer(arrayOfInt1[(j + 1 + (i + 1 + 1) * (x_size + 2))]);
                    boolean[] biomeFrom = BordersFrom[biomeId];
                    if (biomeFrom[i1] && biomeFrom[i2] && biomeFrom[i3] && biomeFrom[i4]) {
                        if ((i1 != biomeId) || (i2 != biomeId) || (i3 != biomeId) || (i4 != biomeId)) {
                            currentPiece = (currentPiece & (IslandBit | RiverBits | IceBit)) | LandBit | BordersTo[biomeId];
                        }
                    }
                }

                arrayOfInt2[(j + i * x_size)] = currentPiece;

            }
        }

        return arrayOfInt2;
    }
}