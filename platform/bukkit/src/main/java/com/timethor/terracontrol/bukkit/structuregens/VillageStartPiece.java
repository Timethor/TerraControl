package com.timethor.terracontrol.bukkit.structuregens;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.BiomeConfig.VillageType;
import com.timethor.terracontrol.bukkit.util.WorldHelper;
import net.minecraft.server.v1_6_R2.*;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public class VillageStartPiece extends WorldGenVillageStartPiece {

    /**
     */
    public final WorldChunkManager worldChunkManager;

    @SuppressWarnings("rawtypes")
    public VillageStartPiece(World world, int i, Random random, int blockX, int blockZ, List arraylist, int size) {
        super(world.getWorldChunkManager(), i, random, blockX, blockZ, arraylist, size);
        this.worldChunkManager = world.getWorldChunkManager();

        // Whether the village is a sandstone village
        BiomeBase currentBiomeGenBase = worldChunkManager.getBiome(blockX, blockZ);
        TerraWorld worldTC = WorldHelper.toLocalWorld(world);
        BiomeConfig config = worldTC.getSettings().biomeConfigs[currentBiomeGenBase.id];
        setSandstoneVillage(config.villageType == VillageType.sandstone);

        this.k = this;
    }

    /**
     * Just sets the first boolean it can find in the
     * WorldGenVillageStartPiece.class to sandstoneVillage.
     *
     * @param sandstoneVillage Whether the village should be a sandstone
     *                         village.
     */
    private void setSandstoneVillage(boolean sandstoneVillage) {
        for (Field field : WorldGenVillageStartPiece.class.getFields()) {
            if (field.getType().toString().equals("boolean")) {
                try {
                    field.setAccessible(true);
                    field.setBoolean(this, sandstoneVillage);
                    break;
                } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    TerraControl.log(Level.INFO,
                                     "Cannot make village a sandstone village! ",
                                     ex.getStackTrace().toString());
                }
            }
        }
    }

    @Override
    public WorldChunkManager d() {
        return this.worldChunkManager;
    }

    // Three methods to help MCPC+ dynamically rename things.
    // It has problems with classes that extend native Minecraft classes
    public void buildComponent(VillageStartPiece startPiece, LinkedList<?> list, Random random) {
        a(startPiece, list, random);
    }

    @SuppressWarnings("unchecked")
    public List<StructurePiece> getPiecesListJ() {
        return j;
    }

    @SuppressWarnings("unchecked")
    public List<StructurePiece> getPiecesListI() {
        return i;
    }
}
