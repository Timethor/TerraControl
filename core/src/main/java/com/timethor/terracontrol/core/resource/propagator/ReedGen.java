package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Timethor
 */
public class ReedGen extends Resource {

    /**
     */
    private int minAltitude;
    /**
     */
    private int maxAltitude;
    /**
     */
    private List<Integer> sourceBlocks;

    @Override
    public void spawn(TerraWorld world, Random rand, boolean villageInChunk, int x, int z) {
        int y = world.getHighestBlockYAt(x, z);
        if (y > maxAltitude || y < minAltitude || (!world.getMaterial(x - 1, y - 1, z).isLiquid() && !world.getMaterial(x + 1, y - 1, z).isLiquid() && !world.getMaterial(x, y - 1, z - 1).isLiquid() && !world.getMaterial(x, y - 1, z + 1).isLiquid())) {
            return;
        }
        if (!sourceBlocks.contains(world.getTypeId(x, y - 1, z))) {
            return;
        }

        int n = 1 + rand.nextInt(2);
        for (int i1 = 0; i1 < n; i1++) {
            world.setBlock(x, y + i1, z, blockId, blockData, false, false, false);
        }
    }

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(6, args);

        blockId = readBlockId(args.get(0));
        blockData = readBlockData(args.get(0));
        frequency = readInt(args.get(1), 1, 100);
        chance = readRarity(args.get(2));
        minAltitude = readInt(args.get(3), TerraControl.worldDepth, TerraControl.worldHeight);
        maxAltitude = readInt(args.get(4), minAltitude + 1, TerraControl.worldHeight);
        sourceBlocks = new ArrayList<>();
        for (int i = 5; i < args.size(); i++) {
            sourceBlocks.add(readBlockId(args.get(i)));
        }
    }

    @Override
    public String makeString() {
        return "Reed(" + makeMaterial(blockId, blockData) + "," + frequency + "," + chance + "," + minAltitude + "," + maxAltitude + makeMaterial(sourceBlocks) + ")";
    }


}