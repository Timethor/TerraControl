package com.timethor.terracontrol.core.biome.generator;

import com.timethor.terracontrol.core.TerraWorld;

/**
 * Extends the NormalBiomeMode. The code that makes it generate differently
 * can be found inside the layer code.
 *
 */
public class FromImageBiomeGenerator extends NormalBiomeGenerator {

    /**
     *
     * @param world
     * @param cache
     */
    public FromImageBiomeGenerator(TerraWorld world, BiomeCache cache) {
        super(world, cache);
    }
}
