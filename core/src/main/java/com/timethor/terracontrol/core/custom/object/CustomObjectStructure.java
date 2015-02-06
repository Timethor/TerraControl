package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate.SpawnHeight;
import com.timethor.terracontrol.core.util.ChunkCoordinate;
import com.timethor.terracontrol.core.util.RandomHelper;

import java.util.*;

/**
 * This class creates the branch structure based on one parent object, and spawns all objects that should spawn in a chunk. <p/> Although it shouldn't be too slow to recalculate, a structure cache should be kept.
 */
public final class CustomObjectStructure {

    /**
	 */
    protected final Random random;
    /**
	 */
    protected TerraWorld world;
    /**
	 */
    protected CustomObjectCoordinate start;
    /**
	 */
    protected SpawnHeight height;
    /**
	 */
    protected Map<ChunkCoordinate, Set<CustomObjectCoordinate>> objectsToSpawn;
    /**
	 */
    protected int maxBranchDepth;

    /**
     *
     * @param world
     * @param start
     */
    public CustomObjectStructure(TerraWorld world, CustomObjectCoordinate start) {
        if (!(start.getObject() instanceof StructuredCustomObject)) {
            throw new IllegalArgumentException("Start object has to be a structure!");
        }

        this.world = world;
        this.start = start;
        this.height = start.getStructuredObject().getSpawnHeight();
        this.maxBranchDepth = start.getStructuredObject().getMaxBranchDepth();
        random = RandomHelper.getRandomForCoords(start.getX(), start.getY(), start.getZ(), world.getSeed());

        // Calculate all branches and add them to a list
        objectsToSpawn = new HashMap<>();
        addToChunk(start); // Add the object itself
        addBranches(start, 1);
    }

    /**
     *
     * @param coordObject
     * @param depth
     */
    protected void addBranches(CustomObjectCoordinate coordObject, int depth) {
        for (Branch branch : coordObject.getStructuredObject().getBranches(coordObject.getRotation())) {
            CustomObjectCoordinate childCoordObject = branch.toCustomObjectCoordinate(world, random, coordObject.getX(), coordObject.getY(), coordObject.getZ());

            // Don't add null objects
            if (childCoordObject == null) {
                continue;
            }

            // Add this object to the chunk
            addToChunk(childCoordObject);

            // Also add the branches of this object
            if (depth < maxBranchDepth) {
                addBranches(childCoordObject, depth + 1);
            }
        }
    }

    /**
     *
     * @param coordObject
     */
    public void addToChunk(CustomObjectCoordinate coordObject) {
        ChunkCoordinate chunkCoordinate = ChunkCoordinate.fromBlockCoords(coordObject.getX(), coordObject.getZ());
        Set<CustomObjectCoordinate> objectsInChunk = objectsToSpawn.get(chunkCoordinate);
        if (objectsInChunk == null) {
            objectsInChunk = new LinkedHashSet<>();
        }
        objectsInChunk.add(coordObject);
        objectsToSpawn.put(chunkCoordinate, objectsInChunk);
    }

    /**
     *
     * @param chunkX
     * @param chunkZ
     */
    public void spawnForChunk(int chunkX, int chunkZ) {
        ChunkCoordinate chunkCoordinate = ChunkCoordinate.fromChunkCoords(chunkX, chunkZ);
        Set<CustomObjectCoordinate> objectsInChunk = objectsToSpawn.get(chunkCoordinate);
        if (objectsInChunk != null) {
            for (CustomObjectCoordinate coordObject : objectsInChunk) {
                coordObject.spawnWithChecks(world, height, random);
            }
        }
    }
}
