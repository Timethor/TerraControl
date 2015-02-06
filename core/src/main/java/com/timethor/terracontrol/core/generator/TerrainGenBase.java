package com.timethor.terracontrol.core.generator;

import com.timethor.terracontrol.core.TerraWorld;

import java.util.Random;

/**
 * @author   Timethor
 */
public class TerrainGenBase {

    /**
	 */
    protected int b = 8;
    /**
	 */
    protected Random c = new Random();
    /**
	 */
    protected TerraWorld d;

    /**
     *
     * @param world
     */
    public TerrainGenBase(TerraWorld world) {
        this.d = world;
    }

    /**
     *
     * @param chunk_x
     * @param chunk_z
     * @param paramArrayOfByte
     */
    public void a(int chunk_x, int chunk_z, byte[] paramArrayOfByte) {
        int i = this.b;

        this.c.setSeed(this.d.getSeed());
        long l1 = this.c.nextLong();
        long l2 = this.c.nextLong();

        for (int j = chunk_x - i; j <= chunk_x + i; j++) {
            for (int k = chunk_z - i; k <= chunk_z + i; k++) {
                long l3 = j * l1;
                long l4 = k * l2;
                this.c.setSeed(l3 ^ l4 ^ this.d.getSeed());
                a(j, k, chunk_x, chunk_z, paramArrayOfByte);
            }
        }
    }

    /**
     *
     * @param paramInt1
     * @param paramInt2
     * @param chunk_x
     * @param chunk_z
     * @param paramArrayOfByte
     */
    protected void a(int paramInt1, int paramInt2, int chunk_x, int chunk_z, byte[] paramArrayOfByte) {
    }
}
