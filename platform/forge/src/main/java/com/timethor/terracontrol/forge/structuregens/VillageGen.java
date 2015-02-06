package com.timethor.terracontrol.forge.structuregens;

import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.BiomeConfig.VillageType;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terraincontrol.forge.Biome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Timethor
 */
public class VillageGen extends MapGenStructure {

    /**
     * A list of all the biomes villages can spawn in.
     */
    public List<BiomeGenBase> villageSpawnBiomes;
    /**
     * Village size, 0 for normal, 1 for flat map
     */
    private int size;
    private int distance;
    private int minimumDistance;

    /**
     *
     * @param worldConfig
     */
    public VillageGen(WorldConfig worldConfig) {
        size = worldConfig.villageSize;
        distance = worldConfig.villageDistance;
        minimumDistance = 8;

        // Add all village biomes to the list
        villageSpawnBiomes = new ArrayList<>();
        for (BiomeConfig config : worldConfig.biomeConfigs) {
            if (config == null) {
                continue;
            }
            if (config.villageType != VillageType.disabled) {
                villageSpawnBiomes.add(((Biome) config.Biome).getHandle());
            }
        }
    }

    /**
     *
     * @param chunkX
     * @param chunkZ
     *               <p/>
     * @return
     */
    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int var3 = chunkX;
        int var4 = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.distance - 1;
        }

        int var5 = chunkX / this.distance;
        int var6 = chunkZ / this.distance;
        Random var7 = this.worldObj.setRandomSeed(var5, var6, 10387312);
        var5 *= this.distance;
        var6 *= this.distance;
        var5 += var7.nextInt(this.distance - this.minimumDistance);
        var6 += var7.nextInt(this.distance - this.minimumDistance);

        if (var3 == var5 && var4 == var6) {
            boolean canSpawn = this.worldObj.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 0, villageSpawnBiomes);

            if (canSpawn) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param chunkX
     * @param chunkZ
     *               <p/>
     * @return
     */
    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureVillageStart(this.worldObj, this.rand, chunkX, chunkZ, this.size);
    }
}
