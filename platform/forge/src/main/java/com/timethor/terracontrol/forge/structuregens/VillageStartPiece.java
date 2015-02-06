package com.timethor.terracontrol.forge.structuregens;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.BiomeConfig.VillageType;
import com.timethor.terracontrol.forge.util.WorldHelper;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 *
 * @author Timethor
 */
public class VillageStartPiece extends ComponentVillageStartPiece {

    /**
     *
     */
    public final WorldChunkManager worldChunkManager;

    /**
     *
     * @param world
     * @param par2
     * @param par3Random
     * @param par4
     * @param par5
     * @param par6ArrayList
     * @param size
     */
    @SuppressWarnings("rawtypes")
    public VillageStartPiece(World world, int par2, Random par3Random, int par4, int par5, List par6ArrayList, int size) {
        super(world.getWorldChunkManager(), par2, par3Random, par4, par5, par6ArrayList, size);
        this.worldChunkManager = world.getWorldChunkManager();

        // Whether the village is a sandstone village
        BiomeGenBase currentBiomeGenBase = worldChunkManager.getBiomeGenAt(par4, par5);
        TerraWorld worldTC = WorldHelper.toLocalWorld(world);
        BiomeConfig config = worldTC.getSettings().biomeConfigs[currentBiomeGenBase.biomeID];
        setSandstoneVillage(config.villageType == VillageType.sandstone);

        this.startPiece = this;
    }

    /**
     * Just sets the first boolean it can find in the
     * WorldGenVillageStartPiece.class to sandstoneVillage.
     *
     * @param sandstoneVillage Whether the village should be a sandstone
     *                         village.
     */
    private void setSandstoneVillage(boolean sandstoneVillage) {
        for (Field field : ComponentVillageStartPiece.class.getFields()) {
            if (field.getType().toString().equals("boolean")) {
                try {
                    field.setAccessible(true);
                    field.setBoolean(this, sandstoneVillage);
                    break;
                } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
                    TerraControl.log(Level.WARNING, "Cannot make village a sandstone village!", e.getStackTrace().toString());
                }
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public WorldChunkManager getWorldChunkManager() {
        return this.worldChunkManager;
    }
}
