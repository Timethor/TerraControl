package com.timethor.terracontrol.core.util;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.biome.generator.*;
import com.timethor.terracontrol.core.configuration.WorldConfig;

/**
 * Counts how much each biome mode is used.
 *
 */
public abstract class MetricsHelper {

    /**
     *
     */
    protected int normalMode = 0;
    /**
     *
     */
    protected int fromImageMode = 0;
    /**
     *
     */
    protected int vanillaMode = 0;
    /**
     *
     */
    protected int oldBiomeMode = 0;
    /**
     *
     */
    protected int customMode = 0;

    /**
     * Calculates how much each biome mode is used.
     * <p/>
     * @param worlds The loaded worlds.
     */
    protected void calculateBiomeModes(Iterable<? extends TerraWorld> worlds) {
        for (TerraWorld world : worlds) {
            WorldConfig config = world.getSettings();
            if (config != null) {
                Class<? extends BiomeGenerator> clazz = config.biomeMode;
                if (clazz.equals(NormalBiomeGenerator.class)) {
                    normalMode++;
                } else if (clazz.equals(FromImageBiomeGenerator.class)) {
                    fromImageMode++;
                } else if (clazz.equals(VanillaBiomeGenerator.class)) {
                    vanillaMode++;
                } else if (clazz.equals(OldBiomeGenerator.class)) {
                    oldBiomeMode++;
                } else {
                    customMode++;
                }
            }
        }
    }
}
