package com.timethor.terraincontrol.forge;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.biome.generator.BiomeGenerator;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.forge.util.ForgeMetricsHelper;
import com.timethor.terracontrol.forge.util.WorldHelper;

import java.io.File;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class TerraWorldType extends WorldType {

    public ForgeWorld worldTC;
    private TerraPlugin plugin;
    private String worldType;

    public TerraWorldType(TerraPlugin plugin, String paramString) {
        super(WorldHelper.getNextWorldTypeID(), paramString);
        this.plugin = plugin;
        this.worldType = paramString;
    }

    @Override
    public String getTranslateName() {
        return worldType;
    }

    // Actually: getBiomeManager
    @Override
    public WorldChunkManager getChunkManager(World world) {
        boolean standAloneServer = false;
        try {
            if (world instanceof WorldClient) {
                return super.getChunkManager(world);
            }
        } catch (NoClassDefFoundError e) {
            // There isn't a WorldClient class, so we are on a stand-alone
            // server. Continue normally.
            standAloneServer = true;
        }

        // Restore old biomes
        ForgeWorld.restoreBiomes();

        // Load everything
        File worldDirectory = new File(plugin.terrainControlDirectory, "worlds" + File.separator + world.getSaveHandler().getWorldDirectoryName());

        if (!worldDirectory.exists()) {
            System.out.println("TerraControl: settings does not exist, creating defaults");

            if (!worldDirectory.mkdirs()) {
                System.out.println("TerraControl: cant create folder " + worldDirectory.getAbsolutePath());
            }
        }

        this.worldTC = new ForgeWorld(world.getSaveHandler().getWorldDirectoryName());
        WorldConfig config = new WorldConfig(worldDirectory, worldTC, false);
        this.worldTC.Init(world, config);

        WorldChunkManager chunkManager = null;

        Class<? extends BiomeGenerator> biomeManagerClass = this.worldTC.getSettings().biomeMode;

        if (biomeManagerClass == TerraControl.getBiomeModeManager().VANILLA) {
            chunkManager = super.getChunkManager(world);
        } else {
            chunkManager = new TerraWorldChunkManager(this.worldTC);
            BiomeGenerator biomeManager = TerraControl.getBiomeModeManager().create(biomeManagerClass, worldTC, new BiomeCacheWrapper(chunkManager));
            ((TerraWorldChunkManager) chunkManager).setBiomeManager(biomeManager);
            this.worldTC.setBiomeManager(biomeManager);
        }

        // Start metrics
        if (standAloneServer) {
            new ForgeMetricsHelper(plugin);
        }

        return chunkManager;
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        if (this.worldTC.getSettings().ModeTerrain != WorldConfig.TerrainMode.Default) {
            return this.worldTC.getChunkGenerator();
        } else {
            return super.getChunkGenerator(world, generatorOptions);
        }
    }

    @Override
    public int getMinimumSpawnHeight(World world) {
        return WorldHelper.toLocalWorld(world).getSettings().waterLevelMax;
    }
}
