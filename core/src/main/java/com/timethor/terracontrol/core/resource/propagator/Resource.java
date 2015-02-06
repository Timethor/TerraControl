package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.ConfigFunction;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.util.ChunkCoordinate;
import com.timethor.terracontrol.core.util.coordinate.Coordinate2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * Represents a Resource: something that can generate in the world.
 */
public abstract class Resource extends ConfigFunction<BiomeConfig> {

    /**
     * The block id of the block to be represented
     */
    protected int blockId = -1;
    /**
     * The data value of the block to be represented
     */
    protected int blockData = -1;
    /**
     * How many times this resource should be attempted for spawn
     */
    protected int frequency;
    /**
     * The chance this resource has per spawn
     */
    protected double chance;
    /**
     * The weight this resource has for spawning
     */
    protected double weight;

    /**
     *
     * @return
     */
    @Override
    public Class<BiomeConfig> getHolderType() {
        return BiomeConfig.class;
    }

    /**
     * Spawns the resource at this position, ignoring chance and frequency.
     * <p/>
     * If you want chunk-control over the resource, override spawnInChunk
     * instead, and leave this method blank.
     *
     * @param world          The world.
     * @param random 
     * @param villageInChunk Whether there is a village in the chunk.
     * @param x              The block x.
     * @param z              The block z.
     */
    public abstract void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z);

    /**
     * Spawns the resource at this position, ignoring chance and frequency.
     * <p/>
     * If you want chunk-control over the resource, override spawnInChunk
     * instead, and leave this method blank.
     *
     * @param world          The world.
     * @param random 
     * @param villageInChunk Whether there is a village in the chunk.
     * @param block          The block coordinates
     */
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, Coordinate2D block) {
        spawn(world, random, villageInChunk, block.getX(), block.getZ());
    }

    /**
     * Spawns the resource normally. Can be canceled by an event.
     * <p/>
     * If you want to override this, override spawnInChunk instead.
     *
     * @param world          The world.
     * @param random         The random number generator.
     * @param villageInChunk Whether there is a village in the chunk.
     * @param chunkX         The chunk x.
     * @param chunkZ         The chunk z.
     */
    public final void process(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        if (!isValid()) {
            // Don't process invalid resources
            return;
        }

        // Fire event
        if (!TerraControl.fireResourceProcessEvent(this, world, random, villageInChunk, chunkX, chunkZ)) {
            return;
        }

        // Spawn
        spawnInChunk(world, random, villageInChunk, chunkX, chunkZ);
    }

    /**
     * Spawns the resource normally. Can be canceled by an event.
     * <p/>
     * If you want to override this, override spawnInChunk instead.
     *
     * @param world          The world.
     * @param random         The random number generator.
     * @param villageInChunk Whether there is a village in the chunk.
     * @param chunk 
     */
    public final void process(TerraWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunk) {
        process(world, random, villageInChunk, chunk.getX(), chunk.getZ());
    }

    /**
     * Called once per chunk, instead of once per attempt.
     * <p/>
     * @param world          The world.
     * @param random         The random number generator.
     * @param villageInChunk Whether there is a village in the chunk.
     * @param chunkX         The chunk x.
     * @param chunkZ         The chunk z.
     */
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (int t = 0; t < frequency; t++) {
            if (random.nextDouble() * 100.0 > chance) {
                continue;
            }
            int x = chunkX * 16 + random.nextInt(16) + 8;
            int z = chunkZ * 16 + random.nextInt(16) + 8;
            spawn(world, random, false, x, z);
        }
    }

    /**
     * Called once per chunk, instead of once per attempt.
     * <p/>
     * @param world          The world.
     * @param random         The random number generator.
     * @param villageInChunk Whether there is a village in the chunk.
     * @param chunk  
     */
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, ChunkCoordinate chunk) {
        spawnInChunk(world, random, villageInChunk, chunk.getX(), chunk.getZ());
    }

    /**
     * Convenience method for creating a resource. Used to create the
     * default resources.
     *
     * @param config biome configuration data for resource creation
     * @param clazz  the class of the resource
     * @param args   arguments to pass to the resource once instantiated
     * <p/>
     * @return
     */
    public static Resource createResource(BiomeConfig config, Class<? extends Resource> clazz, Object... args) {
        List<String> stringArgs = new ArrayList<>(args.length);
        for (Object arg : args) {
            stringArgs.add("" + arg);
        }

        Resource resource;
        try {
            resource = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        resource.setHolder(config);
        try {
            resource.load(stringArgs);
            resource.setValid(true);
        } catch (InvalidConfigException e) {
            TerraControl.log(Level.SEVERE, "Invalid default resource! Please report! " + clazz.getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }

        return resource;
    }

    /**
     * Returns the block id. Resources that don't have a block id should
     * return -1.
     * <p/>
     * @return
     */
    public int getBlockId() {
        return blockId;
    }
}
