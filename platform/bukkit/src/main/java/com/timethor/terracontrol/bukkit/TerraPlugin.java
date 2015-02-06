package com.timethor.terracontrol.bukkit;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.TerraControlEngine;
import com.timethor.terracontrol.bukkit.commands.TerraCommandExecutor;
import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.builtin.TerrainControlCatalog;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.core.util.StringHelper;
import net.minecraft.server.v1_6_R2.BiomeBase;
import net.minecraft.server.v1_6_R2.Block;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_6_R2.block.CraftBlock;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Timethor
 */
public class TerraPlugin extends JavaPlugin implements TerraControlEngine {

    /**
     */
    private final HashMap<String, BukkitWorld> notInitedWorlds = new HashMap<>();
    /**
     */
    public TerraListener listener;
    /**
     *
     */
    private static final Logger logs = Logger.getLogger("Minecraft");
    /**
     */
    public TerraCommandExecutor commandExecutor;
    /**
     * Debug setting. Set it to true to make TerraControl try to disable
     * itself. However, terrain generators aren't cleaned up properly by
     * Bukkit, so this won't really work until that bug is fixed.
     */
    public boolean cleanupOnDisable = false;
    /**
     */
    public final HashMap<UUID, BukkitWorld> worlds = new HashMap<>();

    @Override
    public void onDisable() {
        if (cleanupOnDisable) {
            //>>	Cleanup worlds
            for (BukkitWorld world : worlds.values()) {
                world.disable();
            }
            worlds.clear();

            TerraControl.stopEngine();
        }
    }

    @Override
    public void onEnable() {

        if (!Bukkit.getWorlds().isEmpty() && !cleanupOnDisable) {
            logs.setLevel(Level.ALL);
            //>>	Reload "handling"
            //>>	(worlds are already loaded and TC didn't clean up itself)
            log(Level.SEVERE, new String[]{
                "The server was just /reloaded! ", TerraCatalog.ChannelName.stringValue(), " has problems handling this,",
                "as old parts from before the reload have not been cleaned up.",
                "Unexpected things may happen! Please restart the server!",
                "In the future, instead of /reloading, please restart the server,",
                "or reload a plugin using it's built-in command (like /tc reload),",
                "or use a plugin managing plugin that can reload one plugin at a time."
            });
            setEnabled(false);
        } else {
            //t>>	Get Configs Level for setting level here
            logs.setLevel(Level.ALL);
            if (Bukkit.getVersion().contains("MCPC-Plus")) {
                //>>	We're on MCPC+, so enable the extra block ids.
                TerraControl.supportedBlockIds = 4095;
                log(Level.INFO, "MCPC+ detected, enabling extended block id support.");
            }

            //>>	Start the engine
            TerraControl.startEngine(this);
            this.commandExecutor = new TerraCommandExecutor(this);
            this.listener = new TerraListener(this);
            Bukkit.getMessenger().registerOutgoingPluginChannel(this, TerraCatalog.ChannelName.stringValue());

            TerraControl.log(Level.INFO, "Global objects loaded, waiting for worlds to load");
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return this.commandExecutor.onCommand(sender, command, label, args);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if (worldName.equals("")) {
            TerraControl.log(Level.INFO, "Ignoring empty world name. Is some generator plugin checking if \"", TerraCatalog.ChannelName.stringValue(), "\" is a valid world name?");
            return new TerraChunkGenerator(this);
        }
        if (worldName.equals("test")) {
            TerraControl.log(Level.INFO,
                             "Ignoring world with the name \"test\". This is not a valid world name,",
                             "as it's used by Multiverse to check if \"", TerraCatalog.ChannelName.stringValue(), "\" a valid generator name.",
                             "So if you were just using /mv create, don't worry about this message.");
            return new TerraChunkGenerator(this);
        }

        // Check if not already enabled
        for (BukkitWorld world : worlds.values()) {
            if (world.getName().equals(worldName)) {
                TerraControl.log(Level.INFO, "Already enabled for '" + worldName + "'");
                return world.getChunkGenerator();
            }
        }

        TerraControl.log(Level.INFO, "Starting to enable world '" + worldName + "'...");

        // Create BukkitWorld instance
        BukkitWorld localWorld = new BukkitWorld(worldName);

        // Hack to initialize CraftBukkit's biome mappings
        // This is really needed. Try for yourself if you don't believe it,
        // you will get a java.lang.IllegalArgumentException when adding biomes
        CraftBlock.biomeBaseToBiome(BiomeBase.OCEAN);

        // Load settings
        File baseFolder = getWorldSettingsFolder(worldName);
        WorldConfig worldConfig = new WorldConfig(baseFolder, localWorld, false);
        localWorld.setSettings(worldConfig);

        // Add the world to the to-do list
        this.notInitedWorlds.put(worldName, localWorld);

        // Get the right chunk generator
        TerraChunkGenerator generator = null;
        switch (worldConfig.ModeTerrain) {
            case Normal:
            case TerrainTest:
            case OldGenerator:
            case NotGenerate:
                generator = new TerraChunkGenerator(this);
                break;
            case Default:
                break;
        }

        // Set and return the generator
        localWorld.setChunkGenerator(generator);
        return generator;
    }

    public File getWorldSettingsFolder(String worldName) {
        File baseFolder = new File(this.getDataFolder(), "worlds" + File.separator + worldName);
        if (!baseFolder.exists()) {
            TerraControl.log(Level.INFO, "settings does not exist, creating defaults");

            if (!baseFolder.mkdirs()) {
                TerraControl.log(Level.SEVERE, "cant create folder " + baseFolder.getName());
            }
        }
        return baseFolder;
    }

    public void onWorldInit(World world) {
        if (this.notInitedWorlds.containsKey(world.getName())) {
            // Remove the world from the to-do list
            BukkitWorld bukkitWorld = this.notInitedWorlds.remove(world.getName());

            // Enable and register the world
            bukkitWorld.enable(world);
            this.worlds.put(world.getUID(), bukkitWorld);

            // Show message
            TerraControl.log(Level.INFO, "World ", bukkitWorld.getName(), " is now enabled!");
        }
    }

    @Override
    public void log(Level level, String... msg) {
        logs.log(level, "[{0}] {1}", new Object[]{TerraCatalog.ChannelName.stringValue(), StringHelper.join(msg, " ")});
    }

    @Override
    public void log(String... msg) {
        this.log(Level.INFO, msg);
    }

    @Override
    public void logIfLevel(Level level, String... messages) {
        if (logs.getLevel() == level) {
            this.log(level, messages);
        }
    }

    @Override
    public TerraWorld getWorld(String name) {
        World world = Bukkit.getWorld(name);
        if (world == null) {
            // World not loaded
            return null;
        }
        return this.worlds.get(world.getUID());
    }

    @Override
    public File getGlobalObjectsDirectory() {
        return new File(this.getDataFolder(), TerrainControlCatalog.BO_WorldDirectoryName.stringValue());
    }

    @Override
    public boolean isValidBlockId(int id) {
        if (id == 0) {
            // Air is a special case
            return true;
        }
        if (id < 0 || id > TerraControl.supportedBlockIds) {
            return false;
        }
        if (Block.byId[id] == null) {
            return false;
        }
        return true;
    }
}