package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate.SpawnHeight;

import java.util.Random;

/**
 * Represents CustomObjects that can have other objects attached to it making a structure.
 */
public interface StructuredCustomObject extends CustomObject {

    /**
     * Returns whether this object has branches attached to it.
     * <p/>
     * @return Whether this object has branches attached to it.
     */
    public boolean hasBranches();

    /**
     * Returns a list of all branches in this object. Null is not a valid
     * return value, return an empty list instead.
     * <p/>
     * @param rotation 
     * @return A list of all branches in this object.
     */
    public Branch[] getBranches(Rotation rotation);

    /**
     * Create a coordinate for this at a random position in the chunk.
     * Should respect it's own rarity setting. Can return null.
     * <p/>
     * @param random 
     * @param chunkX The chunk x.
     * @param chunkZ The chunk z.
     * <p/>
     * @return The CustomObjectCoordinate
     */
    public CustomObjectCoordinate makeCustomObjectCoordinate(Random random, int chunkX, int chunkZ);

    /**
     * Branches can have branches which can have branches, etc. This
     * method returns the limit of searching for branches.
     * <p/>
     * @return The maximum branch depth.
     */
    public int getMaxBranchDepth();

    /**
	 * Returns the height at which the whole structure should spawn. <p/>
	 * @return    The height.
	 */
    public SpawnHeight getSpawnHeight();
}
