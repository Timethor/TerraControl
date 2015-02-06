package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Timethor
 */
public class GrassGen extends Resource {

    /**
     */
    private List<Integer> sourceBlocks;

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(5, args);

        blockId = readBlockId(args.get(0));
        blockData = readInt(args.get(1), 0, 16);
        frequency = readInt(args.get(2), 1, 500);
        chance = readRarity(args.get(3));
        sourceBlocks = new ArrayList<>();
        for (int i = 4; i < args.size(); i++) {
            sourceBlocks.add(readBlockId(args.get(i)));
        }
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Handled by process().
    }

    @Override
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (int t = 0; t < frequency; t++) {
            if (random.nextInt(100) >= chance) {
                continue;
            }
            int x = chunkX * 16 + random.nextInt(16) + 8;
            int z = chunkZ * 16 + random.nextInt(16) + 8;
            int y = world.getHighestBlockYAt(x, z);

            int i;
            while ((((i = world.getTypeId(x, y, z)) == 0) || (i == MaterialCatalog.LEAVES.id)) && (y > 0)) {
                y--;
            }

            if ((!world.isEmpty(x, y + 1, z)) || (!sourceBlocks.contains(world.getTypeId(x, y, z)))) {
                continue;
            }
            world.setBlock(x, y + 1, z, blockId, blockData, false, false, false);
        }
    }

    @Override
    public String makeString() {
        return "Grass(" + makeMaterial(blockId) + "," + blockData + "," + frequency + "," + chance + makeMaterial(sourceBlocks) + ")";
    }
}