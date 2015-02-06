package com.timethor.terraincontrol.forge;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.TerraControlEngine;
import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.TerraCatalog;
import com.timethor.terracontrol.core.custom.object.BODefaultValues;
import com.timethor.terracontrol.core.event.EventPriority;
import com.timethor.terracontrol.core.util.StringHelper;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.Instance;
//import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mod(modid = "TerraControl", name = "TerraControl", version = "0.0.1")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, versionBounds = "*")
public class TerraPlugin implements TerraControlEngine {

    @Instance("TerraControl")
    public static TerraPlugin instance;
    public File terrainControlDirectory;
    private TerraWorldType worldType;
    /**
     *
     */
    private static final Logger Logs = FMLCommonHandler.instance().getFMLLogger();

    //@EventHandler
    public void load(FMLInitializationEvent event) {
        // This is the place where the mod starts loading

        // Set the directory
        try {
            Field minecraftDir = Loader.class.getDeclaredField("minecraftDir");
            minecraftDir.setAccessible(true);
            terrainControlDirectory = new File((File) minecraftDir.get(null), "mods" + File.separator + "TerraControl");
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            terrainControlDirectory = new File("mods" + File.separator + "TerraControl");
            this.log(Level.SEVERE, "Could not reflect the Minecraft directory, save location may be unpredicatble.");
            this.log(Level.SEVERE, e.getStackTrace().toString());
        }

        // Start TerraControl engine
        TerraControl.supportedBlockIds = 4095;
        TerraControl.startEngine(this);

        // Register localization
        LanguageRegistry.instance().addStringLocalization("generator.TerraControl", "TerraControl");

        // Register world type
        worldType = new TerraWorldType(this, "TerraControl");

        // Register listening channel for listening to received configs.
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            NetworkRegistry.instance().registerChannel(new PacketHandler(this), TerraCatalog.ChannelName.stringValue());
        }

        // Register player tracker, for sending configs.
        GameRegistry.registerPlayerTracker(new PlayerTracker(this));

        // Register sapling tracker, for custom tree growth.
        SaplingListener saplingListener = new SaplingListener();
        MinecraftForge.TERRAIN_GEN_BUS.register(saplingListener);
        MinecraftForge.EVENT_BUS.register(saplingListener);

        // Register to our own events, so that they can be fired again as Forge
        // events.
        // TODO: make this optional for people who haven't installed other
        // terrain mods, and don't want to lose performance.
        TerraControl.registerEventHandler(new EventManager(), EventPriority.CANCELABLE);
    }

    @Override
    public TerraWorld getWorld(String name) {
        TerraWorld world = worldType.worldTC;
        if (world == null) {
            return null;
        }
        if (world.getName().equals(name)) {
            return world;
        }
        return null;
    }

    /**
     * Gets the world loaded by Terrain Control.
     * <p />
     * Note: this method may be removed in the future, when multiworld
     * support is introduced.
     * <p/>
     * @return The world loaded by Terrain Control, or null if no world is
     *         loaded.
     */
    public TerraWorld getWorld() {
        return worldType.worldTC;
    }

    @Override
    public void logIfLevel(Level level, String... messages) {
        if (Logs.getLevel() == level) {
            this.log(level, messages);
        }
    }

    @Override
    public void log(Level level, String... messages) {
        Logs.log(level, "TerraControl: {0}", StringHelper.join(messages, ","));
    }

    @Override
    public void log(String... message) {
        this.log(Level.INFO, message);
    }

    @Override
    public File getGlobalObjectsDirectory() {
        return new File(terrainControlDirectory, BODefaultValues.BO_GlobalDirectoryName.stringValue());
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
        if (Block.blocksList[id] == null) {
            return false;
        }
        return true;
    }
}
