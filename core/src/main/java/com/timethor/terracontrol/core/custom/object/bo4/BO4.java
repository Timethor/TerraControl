package com.timethor.terracontrol.core.custom.object.bo4;

import com.timethor.terracontrol.core.custom.object.bo4.checks.BO4Check;
import com.timethor.terracontrol.core.custom.object.bo4.config.BO4Config;
import com.timethor.terracontrol.core.TerraBiome;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.custom.object.Branch;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate.SpawnHeight;
import com.timethor.terracontrol.core.custom.object.Rotation;
import com.timethor.terracontrol.core.custom.object.StructuredCustomObject;
import com.timethor.terracontrol.core.custom.object.bo4.BO4Settings.OutsideSourceBlock;
import com.timethor.terracontrol.core.custom.object.bo4.BO4Settings.SpawnHeightSetting;
import com.timethor.terracontrol.core.util.RandomHelper;

import java.io.File;
import java.util.Map;
import java.util.Random;

/**
 * @author Timethor
 */
public class BO4 implements StructuredCustomObject {

    /**
     */
    private BO4Config settings;
    /**
     */
    private String name;
    /**
     */
    private File file;

    /**
     * Creates a BO3 from a file.
     *
     * @param name
     * @param file
     */
    public BO4(String name, File file) {
        this.name = name;
        this.file = file;
    }

    @Override
    public void onEnable(Map<String, CustomObject> otherObjectsInDirectory) {
        this.settings = new BO4Config(name, file, otherObjectsInDirectory);
    }

    /**
     * Creates a BO3 with the specified settings. Ignores the settings in
     * the
     * settings file.
     *
     * @param oldObject     The object where this object is based on
     * @param extraSettings The settings to override
     */
    public BO4(BO4 oldObject, Map<String, String> extraSettings) {
        this.settings = new BO4Config(oldObject, extraSettings);
        this.name = settings.name;
        this.file = settings.file;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public BO4Config getSettings() {
        return settings;
    }

    @Override
    public boolean canSpawnAsTree() {
        return settings.tree;
    }

    @Override
    public boolean canSpawnAsObject() {
        return true;
    }

    @Override
    public boolean canSpawnAt(TerraWorld world, Rotation rotation, int x, int y, int z) {
        BlockFunction[] blocks = settings.blocks[rotation.getRotationId()];
        BO4Check[] checks = settings.bo3Checks[rotation.getRotationId()];

        // Check for spawning
        for (BO4Check check : checks) {
            if (check.preventsSpawn(world, x + check.x, y + check.y, z + check.z)) {
                // A check failed
                return false;
            }
        }
        // Check for source blocks
        int blocksOutsideSourceBlock = 0;
        for (BlockFunction block : blocks) {
            if (!world.isLoaded(x + block.x, y + block.y, z + block.z)) {
                // Cannot spawn BO3, part of world is not loaded
                return false;
            }
            if (world.getTypeId(x + block.x, y + block.y, z + block.z) != settings.sourceBlock) {
                blocksOutsideSourceBlock++;
            }
        }
        if ((((double) blocksOutsideSourceBlock / (double) blocks.length) * 100.0) > settings.maxPercentageOutsideSourceBlock) {
            // Too many blocks outside source block
            return false;
        }

        // Call event
        if (!TerraControl.fireCanCustomObjectSpawnEvent(this, world, x, y, z)) {
            // Cancelled
            return false;
        }

        // Can most likely spawn here
        return true;
    }

    @Override
    public boolean canRotateRandomly() {
        return settings.rotateRandomly;
    }

    @Override
    public boolean spawnForced(TerraWorld world, Random random, Rotation rotation, int x, int y, int z) {
        BlockFunction[] blocks = settings.blocks[rotation.getRotationId()];

        // Spawn
        for (BlockFunction block : blocks) {
            int previousBlock = world.getTypeId(x + block.x, y + block.y, z + block.z);
            if (previousBlock == settings.sourceBlock || settings.outsideSourceBlock == OutsideSourceBlock.placeAnyway) {
                block.spawn(world, random, x + block.x, y + block.y, z + block.z);
            }
        }
        return true;
    }

    /**
     *
     * @param world
     * @param random
     * @param x
     * @param z
     * @return
     */
    protected boolean spawn(TerraWorld world, Random random, int x, int z) {
        Rotation rotation = settings.rotateRandomly ? Rotation.getRandomRotation(random) : Rotation.NORTH;
        int y = 0;
        if (settings.spawnHeight == SpawnHeightSetting.randomY) {
            y = RandomHelper.getRandomNumberInRange(random, settings.minHeight, settings.maxHeight);
        }
        if (settings.spawnHeight == SpawnHeightSetting.highestBlock) {
            y = world.getHighestBlockYAt(x, z);
            if (y < settings.minHeight || y > settings.maxHeight) {
                return false;
            }
        }
        if (settings.spawnHeight == SpawnHeightSetting.highestSolidBlock) {
            y = world.getSolidHeight(x, z);
            if (y < settings.minHeight || y > settings.maxHeight) {
                return false;
            }
        }
        if (!canSpawnAt(world, rotation, x, y, z)) {
            return false;
        }
        return spawnForced(world, random, rotation, x, y, z);
    }

    @Override
    public boolean spawnAsTree(TerraWorld world, Random random, int x, int z) {
        if (settings.tree) {
            return spawn(world, random, x, z);
        }
        return false;
    }

    @Override
    public boolean process(TerraWorld world, Random random, int chunkX, int chunkZ) {
        boolean atLeastOneObjectHasSpawned = false;

        int chunkMiddleX = chunkX * 16 + 8;
        int chunkMiddleZ = chunkZ * 16 + 8;
        for (int i = 0; i < settings.frequency; i++) {
            if (settings.chance > random.nextDouble() * 100.0) {
                if (spawn(world, random, chunkMiddleX + random.nextInt(16), chunkMiddleZ + random.nextInt(16))) {
                    atLeastOneObjectHasSpawned = true;
                }
            }
        }

        return atLeastOneObjectHasSpawned;
    }

    @Override
    public CustomObject applySettings(Map<String, String> extraSettings) {
        return new BO4(this, extraSettings);
    }

    @Override
    public boolean hasPreferenceToSpawnIn(TerraBiome biome) {
        if (settings.excludedBiomes.contains("All") || settings.excludedBiomes.contains("all") || settings.excludedBiomes.contains(biome.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasBranches() {
        return settings.branches[0].length != 0;
    }

    @Override
    public Branch[] getBranches(Rotation rotation) {
        return settings.branches[rotation.getRotationId()];
    }

    @Override
    public CustomObjectCoordinate makeCustomObjectCoordinate(Random random, int chunkX, int chunkZ) {
        if (settings.chance > random.nextDouble() * 100.0) {
            Rotation rotation = settings.rotateRandomly ? Rotation.getRandomRotation(random) : Rotation.NORTH;
            int height = RandomHelper.getRandomNumberInRange(random, settings.minHeight, settings.maxHeight);
            return new CustomObjectCoordinate(this, rotation, chunkX * 16 + 8 + random.nextInt(16), height, chunkZ * 16 + 8 + random.nextInt(16));
        }
        return null;
    }

    @Override
    public int getMaxBranchDepth() {
        return settings.maxBranchDepth;
    }

    @Override
    public SpawnHeight getSpawnHeight() {
        return this.settings.spawnHeight.toSpawnHeight();
    }
}
