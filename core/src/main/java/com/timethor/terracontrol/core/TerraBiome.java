package com.timethor.terracontrol.core;

import com.timethor.terracontrol.core.configuration.BiomeConfig;

/**
 * @author    Timethor
 */
public interface TerraBiome {

    /**
     *
     * @return
     */
    public abstract boolean isCustom();

    /**
     * Sets the post generator effects. For the client it is things like
     * colors. For the server it are things like mob spawning.
     *
     * @param config The BiomeConfig of the biome.
     */
    public abstract void setEffects(BiomeConfig config);

    /**
     * 
     * @return 
     */
    public abstract String getName();

    /**
     *
     * @return
     */
    public abstract int getId();

    /**
     *
     * @return
     */
    public abstract int getCustomId();

    /**
     *
     * @return
     */
    public abstract float getTemperature();

    /**
     *
     * @return
     */
    public abstract float getWetness();

    /**
     *
     * @return
     */
    public abstract float getSurfaceHeight();

    /**
     *
     * @return
     */
    public abstract float getSurfaceVolatility();

    /**
     *
     * @return
     */
    public abstract byte getSurfaceBlock();

    /**
     *
     * @return
     */
    public abstract byte getGroundBlock();
}