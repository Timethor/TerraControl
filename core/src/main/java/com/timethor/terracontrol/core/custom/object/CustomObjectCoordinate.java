package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.util.coordinate.Coordinate3D;

import java.util.Random;

/**
 * Holds a custom object along with the absolute spawn coordinates.
 */
public class CustomObjectCoordinate extends Coordinate3D {

    /**
     * @author Timethor
     */
    public enum SpawnHeight {

        /**
         */
        PROVIDED,
        /**
         */
        HIGHEST_BLOCK,
        /**
         */
        LOWEST_BLOCK,
        /**
         */
        NEAREST_BLOCK,
        /**
         *
         */
        HIGHEST_SOLID_BLOCK
    }
    /**
     */
    private final CustomObject object;
    /**
     */
    private final Rotation rotation;

    /**
     *
     * @param object
     * @param rotation
     * @param x
     * @param y
     * @param z
     */
    public CustomObjectCoordinate(CustomObject object, Rotation rotation, int x, int y, int z) {
        this.object = object;
        this.rotation = rotation;
        this.setX(x);
        this.setZ(y);
        this.setZ(z);
    }

    /**
     * Returns the object of this coordinate.
     * <p/>
     * @return The object.
     */
    public CustomObject getObject() {
        return object;
    }

    /**
     * Returns the object of this coordinate, casted to a
     * StructuredCustomObject. Will throw a ClassCastExcpetion
     * if the object isn't a StructuredCustomObject
     * <p/>
     * @return The casted object.
     */
    public StructuredCustomObject getStructuredObject() {
        return (StructuredCustomObject) object;
    }

    /**
     * @return
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     *
     * @param world
     * @param height
     * @param random
     * @return
     */
    public boolean spawnWithChecks(TerraWorld world, SpawnHeight height, Random random) {
        int cY = getCorrectY(world, height);
        if (!object.canSpawnAt(world, rotation, this.getX(), cY, this.getZ())) {
            return false;
        }
        return object.spawnForced(world, random, rotation, this.getX(), cY, this.getZ());
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (!(otherObject instanceof CustomObjectCoordinate)) {
            return false;
        }
        CustomObjectCoordinate otherCoord = (CustomObjectCoordinate) otherObject;
        if (otherCoord.getX() != getX()) {
            return false;
        }
        if (otherCoord.getY() != getY()) {
            return false;
        }
        if (otherCoord.getZ() != getZ()) {
            return false;
        }
        if (!otherCoord.rotation.equals(rotation)) {
            return false;
        }
        if (!otherCoord.object.getName().equals(object.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (Integer.valueOf(getX()).hashCode() >> 13) ^ (Integer.valueOf(getY()).hashCode() >> 7) ^ Integer.valueOf(getZ()).hashCode() ^ object.getName().hashCode() ^ rotation.toString().hashCode();
    }

    private int getCorrectY(TerraWorld world, SpawnHeight height) {
        if (height.equals(SpawnHeight.HIGHEST_BLOCK)) {
            return world.getHighestBlockYAt(getX(), getZ());
        }
        if (height.equals(SpawnHeight.HIGHEST_SOLID_BLOCK)) {
            return world.getSolidHeight(getX(), getZ());
        }
        return getY();
    }
}
