package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.util.dataGroups.Entry2;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Timethor
 */
public class TreeGen extends Resource {

    /**
     */
    private TreeMap<String, Entry2<Integer, CustomObject>> trees;

    @Override
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (int i = 0; i < frequency; i++) {
            for (Entry<String, Entry2<Integer, CustomObject>> tree : trees.entrySet()) {
                String treeName = tree.getKey();
                Integer treeChance = tree.getValue().getFirstValue();
                if (random.nextInt(100) < treeChance) {
                    int x = chunkX * 16 + random.nextInt(16) + 8;
                    int z = chunkZ * 16 + random.nextInt(16) + 8;
                    if (trees.get(treeName).getSecondValue().spawnAsTree(world, random, x, z)) {
                        // Success, on to the next tree!
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(3, args);

        frequency = readInt(args.get(0), 1, 100);

        trees = new TreeMap<>();

        for (int i = 1; i < args.size() - 1; i += 2) {
            CustomObject object = TerraControl.getCustomObjectManager().getObjectFromString(args.get(i), getHolder().worldConfig);
            if (object == null) {
                throw new InvalidConfigException("Custom object " + args.get(i) + " not found!");
            }
            if (!object.canSpawnAsTree()) {
                throw new InvalidConfigException("Custom object " + args.get(i) + " is not a tree!");
            }
            trees.put(args.get(i), new Entry2<>(readInt(args.get(i + 1), 1, 100), object));
        }
    }

    @Override
    public String makeString() {
        String output = "Tree(" + frequency;
        for (Entry<String, Entry2<Integer, CustomObject>> tree : trees.entrySet()) {
            output += "," + tree.getKey() + "," + tree.getValue().getFirstValue();
        }
        return output + ")";
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Left blank, as process() already handles this
    }
}
