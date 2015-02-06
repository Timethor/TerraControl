package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.util.coordinate.Coordinate2D;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Timethor
 */
public class AboveWaterGen extends Resource {

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(3, args);

        blockId = readBlockId(args.get(0));
        blockData = readBlockData(args.get(0));
        frequency = readInt(args.get(1), 1, 100);
        chance = readRarity(args.get(2));
    }

    @Override
    public void spawn(TerraWorld world, Random rand, boolean villageInChunk, int x, int z) {
        int y = world.getLiquidHeight(x, z);
        if (y == -1) {
            return;
        }
        y++;

        for (int i = 0; i < 10; i++) {
            int j = x + rand.nextInt(8) - rand.nextInt(8);
            int m = z + rand.nextInt(8) - rand.nextInt(8);
            if (!world.isEmpty(j, y, m) || !world.getMaterial(j, y - 1, m).isLiquid()) {
                continue;
            }
            world.setBlock(j, y, m, blockId, blockData, false, false, false);
        }
    }

    @Override
    public String makeString() {
        return "AboveWaterRes(" + makeMaterial(blockId) + "," + frequency + "," + chance + ")";
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, Coordinate2D block) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
