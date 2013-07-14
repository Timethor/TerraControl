package com.timethor.terracontrol.core.custom.object.bo3;

import com.timethor.terracontrol.core.TerraWorld;

/**
 * Represents a check - something that can prevent the BO3 from spawning if this condition is not met.
 */
public abstract class BO3Check extends BO3Function {

    /**
	 */
    public int x;
    /**
	 */
    public int y;
    /**
	 */
    public int z;

    /**
     * Returns whether this check would prevent spawning at the given
     * position.
     * The internal x, y and z values of this check are ignored.
     *
     * @param world The world to check in
     * @param x     The x position
     * @param y     The y position
     * @param z     The z position
     * <p/>
     * @return Whether this check prevents the BO3 from spawning.
     */
    public abstract boolean preventsSpawn(TerraWorld world, int x, int y, int z);

    @Override
    public abstract BO3Check rotate();
}
