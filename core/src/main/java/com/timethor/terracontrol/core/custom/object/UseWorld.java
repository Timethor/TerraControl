package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraBiome;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.configuration.WorldConfig;

import java.util.Map;
import java.util.Random;

/**
 * UseWorld is a keyword that spawns the objects in the WorldObjects
 * folder.
 */
public class UseWorld implements CustomObject {

    @Override
    public void onEnable(Map<String, CustomObject> otherObjectsInDirectory) {
        // Stub method
    }

    @Override
    public String getName() {
        return "UseWorld";
    }

    @Override
    public boolean canSpawnAsTree() {
        return true;
    }

    @Override
    public boolean canSpawnAsObject() {
        return true;
    }

    @Override
    public boolean spawnForced(TerraWorld world, Random random, Rotation rotation, int x, int y, int z) {
        for (CustomObject object : world.getSettings().customObjects) {
            if (object.hasPreferenceToSpawnIn(world.getBiome(x, z))) {
                if (object.spawnForced(world, random, rotation, x, y, z)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean spawnAsTree(TerraWorld world, Random random, int x, int z) {
        // Pick one object, try to spawn that, if that fails, try with another
        // object, as long as the objectSpawnRatio cap isn't reached.

        WorldConfig worldSettings = world.getSettings();

        if (worldSettings.customObjects.isEmpty()) {
            return false;
        }

        boolean objectSpawned = false;
        int spawnattemps = 0;
        while (!objectSpawned) {
            if (spawnattemps > worldSettings.objectSpawnRatio) {
                return false;
            }

            spawnattemps++;

            CustomObject selectedObject = worldSettings.customObjects.get(random.nextInt(worldSettings.customObjects.size()));

            if (!selectedObject.hasPreferenceToSpawnIn(world.getBiome(x, z))) {
                continue;
            }

            // Process the object
            objectSpawned = selectedObject.spawnAsTree(world, random, x, z);

        }
        return objectSpawned;
    }

    @Override
    public boolean process(TerraWorld world, Random rand, int chunk_x, int chunk_z) {
        // Pick one object, try to spawn that, if that fails, try with another
        // object, as long as the objectSpawnRatio cap isn't reached.

        WorldConfig worldSettings = world.getSettings();

        if (worldSettings.customObjects.isEmpty()) {
            return false;
        }

        boolean objectSpawned = false;
        int spawnattemps = 0;
        while (!objectSpawned) {
            if (spawnattemps > worldSettings.objectSpawnRatio) {
                return false;
            }

            spawnattemps++;

            CustomObject selectedObject = worldSettings.customObjects.get(rand.nextInt(worldSettings.customObjects.size()));

            if (!selectedObject.hasPreferenceToSpawnIn(world.getBiome(chunk_x * 16 + 8, chunk_z * 16 + 8))) {
                continue;
            }

            // Process the object
            objectSpawned = selectedObject.process(world, rand, chunk_x, chunk_z);

        }
        return objectSpawned;
    }

    @Override
    public CustomObject applySettings(Map<String, String> settings) {
        // Not supported
        return this;
    }

    @Override
    public boolean hasPreferenceToSpawnIn(TerraBiome biome) {
        // Never, ever spawn this in UseWorld. It would cause an infinite loop.
        return false;
    }

    @Override
    public boolean canSpawnAt(TerraWorld world, Rotation rotation, int x, int y, int z) {
        return true; // We can only guess
    }

    @Override
    public boolean canRotateRandomly() {
        return true;
    }
}
