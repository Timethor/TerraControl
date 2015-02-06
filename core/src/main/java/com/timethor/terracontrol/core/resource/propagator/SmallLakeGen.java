package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.List;
import java.util.Random;

/**
 * @author   Timethor
 */
public class SmallLakeGen extends Resource {

    /**
	 */
    private final boolean[] BooleanBuffer = new boolean[2048];
    /**
	 */
    public int minAltitude;
    /**
	 */
    public int maxAltitude;

    @Override
    public void spawn(TerraWorld world, Random rand, boolean villageInChunk, int x, int z) {
        if (villageInChunk) {
            // Lakes and villages don't like each other.
            return;
        }

        x -= 8;
        z -= 8;

        int y = rand.nextInt(maxAltitude - minAltitude) + minAltitude;

        // Search any free space
        while ((y > 5) && (world.isEmpty(x, y, z))) {
            y--;
        }

        if (y <= 4) {
            return;
        }

        // y = floor
        y -= 4;

        synchronized (BooleanBuffer) {
            boolean[] BooleanBufferT = new boolean[2048];
            int i = rand.nextInt(4) + 4;
            for (int j = 0; j < i; j++) {
                double d1 = rand.nextDouble() * 6.0D + 3.0D;
                double d2 = rand.nextDouble() * 4.0D + 2.0D;
                double d3 = rand.nextDouble() * 6.0D + 3.0D;

                double d4 = rand.nextDouble() * (16.0D - d1 - 2.0D) + 1.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (8.0D - d2 - 4.0D) + 2.0D + d2 / 2.0D;
                double d6 = rand.nextDouble() * (16.0D - d3 - 2.0D) + 1.0D + d3 / 2.0D;

                for (int k = 1; k < 15; k++) {
                    for (int m = 1; m < 15; m++) {
                        for (int n = 1; n < 7; n++) {
                            double xd = (k - d4) / (d1 / 2.0D);
                            double yd = (n - d5) / (d2 / 2.0D);
                            double zd = (m - d6) / (d3 / 2.0D);
                            double d10 = xd * xd + yd * yd + zd * zd;
                            if (d10 >= 1.0D) {
                                continue;
                            }
                            BooleanBufferT[((k * 16 + m) * 8 + n)] = true;
                        }
                    }
                }
            }
            int z1;
            int y1;
            for (int x0 = 0; x0 < 16; x0++) {
                for (z1 = 0; z1 < 16; z1++) {
                    for (y1 = 0; y1 < 8; y1++) {
                        boolean flag = (!BooleanBufferT[((x0 * 16 + z1) * 8 + y1)]) && (((x0 < 15) && (BooleanBufferT[(((x0 + 1) * 16 + z1) * 8 + y1)])) || ((x0 > 0) && (BooleanBufferT[(((x0 - 1) * 16 + z1) * 8 + y1)])) || ((z1 < 15) && (BooleanBufferT[((x0 * 16 + (z1 + 1)) * 8 + y1)])) || ((z1 > 0) && (BooleanBufferT[((x0 * 16 + (z1 - 1)) * 8 + y1)])) || ((y1 < 7) && (BooleanBufferT[((x0 * 16 + z1) * 8 + (y1 + 1))])) || ((y1 > 0) && (BooleanBufferT[((x0 * 16 + z1) * 8 + (y1 - 1))])));

                        if (flag) {
                            MaterialCatalog localMaterial = world.getMaterial(x + x0, y + y1, z + z1);
                            if ((y1 >= 4) && (localMaterial.isLiquid())) {
                                return;
                            }
                            if ((y1 < 4) && (!localMaterial.isSolid()) && (world.getTypeId(x + x0, y + y1, z + z1) != blockId)) {
                                return;
                            }
                        }
                    }
                }

            }

            for (int x1 = 0; x1 < 16; x1++) {
                for (z1 = 0; z1 < 16; z1++) {
                    for (y1 = 0; y1 < 4; y1++) {
                        if (BooleanBufferT[((x1 * 16 + z1) * 8 + y1)]) {
                            world.setBlock(x + x1, y + y1, z + z1, blockId, blockData);
                            BooleanBufferT[((x1 * 16 + z1) * 8 + y1)] = false;
                        }
                    }
                    for (y1 = 4; y1 < 8; y1++) {
                        if (BooleanBufferT[((x1 * 16 + z1) * 8 + y1)]) {
                            world.setBlock(x + x1, y + y1, z + z1, 0, 0);
                            BooleanBufferT[((x1 * 16 + z1) * 8 + y1)] = false;
                        }
                    }
                }
            }

        }
    }

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(5, args);
        blockId = readBlockId(args.get(0));
        blockData = readBlockData(args.get(0));
        frequency = readInt(args.get(1), 1, 100);
        chance = readRarity(args.get(2));
        minAltitude = readInt(args.get(3), TerraControl.worldDepth, TerraControl.worldHeight);
        maxAltitude = readInt(args.get(4), minAltitude + 1, TerraControl.worldHeight);
    }

    @Override
    public String makeString() {
        return "SmallLake(" + makeMaterial(blockId, blockData) + "," + frequency + "," + chance + "," + minAltitude + "," + maxAltitude + ")";
    }
}
