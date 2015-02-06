package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraBiome;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.resource.propagator.TreeType;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public class TreeObject implements CustomObject {

    /**
     */
    private TreeType type;
    /**
     */
    private int minHeight = TerraControl.worldDepth;
    /**
     */
    private int maxHeight = TerraControl.worldHeight;

    /**
     *
     * @param type
     */
    public TreeObject(TreeType type) {
        this.type = type;
    }

    @Override
    public void onEnable(Map<String, CustomObject> otherObjectsInDirectory) {
        // Stub method
    }

    /**
     *
     * @param type
     * @param settings
     */
    public TreeObject(TreeType type, Map<String, String> settings) {
        this.type = type;
        for (Entry<String, String> entry : settings.entrySet()) {
            try {
                if (entry.getKey().equalsIgnoreCase("MinHeight")) {
                    this.minHeight = Math.max(TerraControl.worldDepth, Math.min(Integer.parseInt(entry.getValue()), TerraControl.worldHeight));
                }
                if (entry.getKey().equalsIgnoreCase("MaxHeight")) {
                    this.maxHeight = Math.max(minHeight, Math.min(Integer.parseInt(entry.getValue()), TerraControl.worldHeight));
                }
            } catch (NumberFormatException e) {
                TerraControl.log(Level.WARNING, "Cannot parse " + entry.getKey() + " of a " + type + " tree: invalid number!");
            }

        }
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public boolean canSpawnAsTree() {
        return true;
    }

    @Override
    public boolean canSpawnAsObject() {
        return false;
    }

    @Override
    public boolean spawnForced(TerraWorld world, Random random, Rotation rotation, int x, int y, int z) {
        return world.PlaceTree(type, random, x, y, z);
    }

    @Override
    public boolean spawnAsTree(TerraWorld world, Random random, int x, int z) {
        int y = world.getHighestBlockYAt(x, z);
        if (y < minHeight || y > maxHeight) {
            return false;
        }
        return world.PlaceTree(type, random, x, y, z);
    }

    @Override
    public boolean process(TerraWorld world, Random random, int chunkX, int chunkZ) {
        // A tree has no rarity, so spawn it once in the chunk
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        return spawnAsTree(world, random, x, z);
    }

    @Override
    public CustomObject applySettings(Map<String, String> settings) {
        return new TreeObject(type, settings);
    }

    @Override
    public boolean hasPreferenceToSpawnIn(TerraBiome biome) {
        return true;
    }

    @Override
    public boolean canSpawnAt(TerraWorld world, Rotation rotation, int x, int y, int z) {
        return true; // We can only guess...
    }

    @Override
    public boolean canRotateRandomly() {
        // Trees cannot be rotated
        return false;
    }
}
