package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraWorld;

import java.util.Random;

/**
 * Represents a branch of a CustomObject.
 *
 */
public interface Branch {

    /**
     * Makes a CustomObjectCoordinate out of this branch. Is allowed
     * to return null if based on the random number generator no
     * branch should spawn here.
     * <p/>
     * @param world  The world.
     * @param random The random number generator.
     * @param x      X coordinate of the origin of the object.
     * @param y      Y coordinate of the origin of the object.
     * @param z      Z coordinate of the origin of the object.
     * <p/>
     * @return The CustomObjectCoordinate of this branch.
     */
    public CustomObjectCoordinate toCustomObjectCoordinate(TerraWorld world, Random random, int x, int y, int z);
}
