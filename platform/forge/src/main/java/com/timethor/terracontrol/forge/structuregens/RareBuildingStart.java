package com.timethor.terracontrol.forge.structuregens;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.forge.util.WorldHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureDesertPyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureJunglePyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeatureSwampHut;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Random;

/**
 *
 * @author Timethor
 */
public class RareBuildingStart extends StructureStart {

    /**
     *
     * @param world
     * @param random
     * @param chunkX
     * @param chunkZ
     */
    @SuppressWarnings("unchecked")
    public RareBuildingStart(World world, Random random, int chunkX, int chunkZ) {
        TerraWorld localWorld = WorldHelper.toLocalWorld(world);
        BiomeConfig biomeConfig = localWorld.getSettings().biomeConfigs[localWorld.getCalculatedBiomeId(chunkX * 16 + 8, chunkZ * 16 + 8)];
        StructureComponent building;
        switch (biomeConfig.rareBuildingType) {
            case desertPyramid:
                building = new ComponentScatteredFeatureDesertPyramid(random, chunkX * 16, chunkZ * 16);
                break;
            case jungleTemple:
                building = new ComponentScatteredFeatureJunglePyramid(random, chunkX * 16, chunkZ * 16);
                break;
            case swampHut:
                building = new ComponentScatteredFeatureSwampHut(random, chunkX * 16, chunkZ * 16);
                break;
            case disabled:
            default:
                // Should never happen, but on biome borders there is chance
                // that a structure gets started in a biome where it shouldn't.
                building = null;
                break;
        }

        if (building != null) {
            this.components.add(building);
        }

        this.updateBoundingBox();
    }
}
