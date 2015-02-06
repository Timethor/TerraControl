package com.timethor.terracontrol.core.layer;

import com.timethor.terracontrol.core.TerraBiome;

/**
 * @author   Timethor
 */
public class LayerBiomeInBiome extends Layer {

    /**
	 */
    public TerraBiome biome;
    /**
	 */
    public int chance = 10;
    /**
	 */
    public boolean inOcean = false;
    /**
	 */
    public boolean[] BiomeIsles = new boolean[256];

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerBiomeInBiome(long paramLong, Layer paramGenLayer) {
        super(paramLong);
        this.child = paramGenLayer;
        for (int i = 0; i < BiomeIsles.length; i++) {
            BiomeIsles[i] = false;
        }

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
        int i = x - 1;
        int j = z - 1;
        int k = x_size + 2;
        int m = z_size + 2;
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, i, j, k, m);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, x_size * z_size);

        for (int n = 0; n < z_size; n++) {
            for (int i1 = 0; i1 < x_size; i1++) {
                SetSeed(i1 + x, n + z);
                int currentPiece = arrayOfInt1[(i1 + 1 + (n + 1) * k)];

                boolean spawn = false;
                if (inOcean) {
                    int i2 = arrayOfInt1[(i1 + 0 + (n + 0) * k)] & LandBit;
                    int i3 = arrayOfInt1[(i1 + 2 + (n + 0) * k)] & LandBit;
                    int i4 = arrayOfInt1[(i1 + 0 + (n + 2) * k)] & LandBit;
                    int i5 = arrayOfInt1[(i1 + 2 + (n + 2) * k)] & LandBit;


                    if (((currentPiece & LandBit) == 0) && (i2 == 0) && (i3 == 0) && (i4 == 0) && (i5 == 0) && nextInt(chance) == 0) {
                        currentPiece = (currentPiece & IceBit) | (currentPiece & RiverBits) | LandBit | biome.getId() | IslandBit;
                        spawn = true;
                    }
                }
                if (!spawn) {
                    int i2 = arrayOfInt1[(i1 + 0 + (n + 0) * k)] & BiomeBits;
                    int i3 = arrayOfInt1[(i1 + 2 + (n + 0) * k)] & BiomeBits;
                    int i4 = arrayOfInt1[(i1 + 0 + (n + 2) * k)] & BiomeBits;
                    int i5 = arrayOfInt1[(i1 + 2 + (n + 2) * k)] & BiomeBits;


                    if (BiomeIsles[(currentPiece & BiomeBits)] && BiomeIsles[i2] && BiomeIsles[i3] && BiomeIsles[i4] && BiomeIsles[i5] && nextInt(chance) == 0) {
                        currentPiece = (currentPiece & LandBit) | (currentPiece & IceBit) | (currentPiece & RiverBits) | biome.getId() | IslandBit;
                    }

                }

                arrayOfInt2[(i1 + n * x_size)] = currentPiece;
            }
        }
        return arrayOfInt2;
    }
}
