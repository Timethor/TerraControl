package com.timethor.terracontrol.bukkit;

import com.timethor.terracontrol.core.builtin.MobCatalog;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.WeightedMobSpawnGroup;
import net.minecraft.server.v1_6_R2.BiomeBase;
import net.minecraft.server.v1_6_R2.BiomeMeta;
import net.minecraft.server.v1_6_R2.Entity;
import net.minecraft.server.v1_6_R2.EntityTypes;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_6_R2.block.CraftBlock;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class CustomBiome extends BiomeBase {

    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    public CustomBiome(int id, String name) {
        super(id);
        this.a(name);

        // Insert the biome in CraftBukkit's biome mapping
        try {
            Field biomeMapping = CraftBlock.class.getDeclaredField("BIOME_MAPPING");
            biomeMapping.setAccessible(true);
            Biome[] mappingArray = (Biome[]) biomeMapping.get(null);

            mappingArray[id] = Biome.OCEAN;

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            TerraControl.log(Level.SEVERE, "Couldn't update Bukkit's biome mappings!");
            TerraControl.log(Level.SEVERE, e.getStackTrace().toString());
        }
    }

    @SuppressWarnings("unchecked")
    public void setEffects(BiomeConfig config) {
        this.D = config.BiomeHeight;
        this.E = config.BiomeVolatility;
        this.A = config.SurfaceBlock;
        this.B = config.GroundBlock;
        this.temperature = config.BiomeTemperature;
        this.humidity = config.BiomeWetness;
        if (this.humidity == 0) {
            this.b(); // this.disableRain()
        }

        // Mob spawning
        addMobs(this.J, config.spawnMonstersAddDefaults, config.spawnMonsters);
        addMobs(this.K, config.spawnCreaturesAddDefaults, config.spawnCreatures);
        addMobs(this.L, config.spawnWaterCreaturesAddDefaults, config.spawnWaterCreatures);
        addMobs(this.M, config.spawnAmbientCreaturesAddDefaults, config.spawnAmbientCreatures);
    }

    // Adds the mobs to the internal list. Displays a warning for each mob type it doesn't understand
    protected void addMobs(List<BiomeMeta> internalList, boolean addDefaults, List<WeightedMobSpawnGroup> configList) {
        if (!addDefaults) {
            internalList.clear();
        }
        for (WeightedMobSpawnGroup mobGroup : configList) {
            Class<? extends Entity> entityClass = getEntityClass(mobGroup);
            if (entityClass != null) {
                internalList.add(new BiomeMeta(entityClass, mobGroup.getWeight(), mobGroup.getMin(), mobGroup.getMax()));
            } else {
                // The .toLowerCase() is just a safeguard so that we get notified if this.y is no longer the biome name
                TerraControl.log(Level.WARNING, "Mob type " + mobGroup.getMobName() + " not found in " + this.y.toLowerCase());
            }
        }
    }

    // Gets the class of the entity.
    @SuppressWarnings("unchecked")
    protected Class<? extends Entity> getEntityClass(WeightedMobSpawnGroup mobGroup) {
        String mobName = MobCatalog.getInternalMinecraftName(mobGroup.getMobName());
        try {
            Field entitiesField = EntityTypes.class.getDeclaredField("b");
            entitiesField.setAccessible(true);
            Map<String, Class<? extends Entity>> entitiesList = (Map<String, Class<? extends Entity>>) entitiesField.get(null);
            return entitiesList.get(mobName);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            TerraControl.log(Level.SEVERE, "Someone forgot to update the mob spawning code! Please report!");
            TerraControl.log(Level.SEVERE, e.getStackTrace().toString());
            return null;
        }
    }
}
