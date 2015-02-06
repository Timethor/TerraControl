package com.timethor.terracontrol.core;

import com.timethor.terracontrol.core.biome.generator.BiomeModeManager;
import com.timethor.terracontrol.core.configuration.ConfigFunctionsManager;
import com.timethor.terracontrol.core.event.EventHandler;
import com.timethor.terracontrol.core.event.EventPriority;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.custom.object.CustomObjectLoader;
import com.timethor.terracontrol.core.custom.object.CustomObjectManager;
import com.timethor.terracontrol.core.resource.propagator.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.logging.Level;

/**
 *
 * @author Timethor
 */
public class TerraControl {

    /**
     * The world height that the engine supports. Not the actual height the
     * world is capped at. 256 in Minecraft.
     */
    public static int worldHeight = 256;
    /**
     * The world depth that the engine supports. Not the actual depth the
     * world is capped at. 0 in Minecraft.
     */
    public static int worldDepth = 0;
    /**
     * The maximum block id that is supported. 255 on CraftBukkit.
     */
    public static int supportedBlockIds = 255;
    /**
     * The maximum block id that is supported. 255 on CraftBukkit.
     */
    private static TerraControlEngine engine;
    private static ConfigFunctionsManager configFunctionsManager;
    private static CustomObjectManager customObjectManager;
    private static BiomeModeManager biomeManagers;
    private static List<EventHandler> cancelableEventHandlers = new ArrayList<>();
    private static List<EventHandler> monitoringEventHandlers = new ArrayList<>();

    private TerraControl() {
        // Forbidden to instantiate.
    }

    /**
     * Starts the engine, making all API methods available.
     *
     * @param engine The implementation of the engine.
     */
    public static void startEngine(TerraControlEngine engine) {
        if (TerraControl.engine != null) {
            throw new UnsupportedOperationException("Engine is already set!");
        }

        // Start the engine
        TerraControl.engine = engine;
        configFunctionsManager = new ConfigFunctionsManager();
        customObjectManager = new CustomObjectManager();
        biomeManagers = new BiomeModeManager();

        // Fire start event
        for (EventHandler handler : cancelableEventHandlers) {
            handler.onStart();
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onStart();
        }
        // Load global objects after the event has been fired, so that custom
        // object types are also taken into account
        customObjectManager.loadGlobalObjects();
    }

    /**
     * Null out static references to free up memory. Should be called on
     * shutdown.
     */
    public static void stopEngine() {
        // Shutdown all loaders
        for (CustomObjectLoader loader : customObjectManager.loaders.values()) {
            loader.onShutdown();
        }

        engine = null;
        customObjectManager = null;
        configFunctionsManager = null;
        biomeManagers = null;
        cancelableEventHandlers.clear();
        monitoringEventHandlers.clear();
    }

    /**
     * Returns the engine, containing the API methods.
     *
     * @return The engine
     */
    public static TerraControlEngine getEngine() {
        return engine;
    }

    /**
     * Returns the world object with the given name.
     *
     * @param name The name of the world.
     * <p/>
     * @return The world object.
     */
    public static TerraWorld getWorld(String name) {
        return engine.getWorld(name);
    }

    /**
     * Convienence method to quickly get the biome name at the given
     * coordinates. Will return null if the world isn't loaded by Terrain
     * Control.
     *
     * @param worldName The world name.
     * @param x         The block x in the world.
     * @param z         The block z in the world.
     * <p/>
     * @return The biome name, or null if the world isn't managed by
     *         Terrain
     *         Control.
     */
    public static String getBiomeName(String worldName, int x, int z) {
        TerraWorld world = getWorld(worldName);
        if (world == null) {
            // World isn't loaded by Terrain Control
            return null;
        }
        return world.getBiome(x, z).getName();
    }
    
    /**
     * Logs the message(s) with the given importance. Message will be
     * prefixed with TerraControl, so don't do that yourself.
     *
     * @param level
     * @param messages The messages to log.
     */
    public static void log(Level level, String... messages) {
        engine.log(level, messages);
    }

    /**
     * Logs the message(s) with the given importance <b>ONLY IF</b> logger
     * level matches the level provided. Message will be prefixed with
     * TerraControl, so don't do that yourself.
     *
     * @param level
     * @param messages The messages to log.
     */
    public static void logIfLevel(Level level, String... messages) {
        engine.logIfLevel(level, messages);
    }

    /**
     * Returns the CustomObject manager, with hooks to spawn CustomObjects.
     *
     * @return The CustomObject manager.
     */
    public static CustomObjectManager getCustomObjectManager() {
        return customObjectManager;
    }

    /**
     * Returns the Resource manager.
     *
     * @return The Resource manager.
     */
    public static ConfigFunctionsManager getConfigFunctionsManager() {
        return configFunctionsManager;
    }

    /**
     * Returns the biome managers. Register your own biome manager here.
     * <p/>
     * @return The biome managers.
     */
    public static BiomeModeManager getBiomeModeManager() {
        return biomeManagers;
    }

    // Events
    /**
     * Register your event handler here with normal priority. You can do
     * this
     * before TerraControl is started.
     *
     * @param handler The handler that will receive the events.
     */
    public static void registerEventHandler(EventHandler handler) {
        cancelableEventHandlers.add(handler);
    }

    /**
     * Register you event handler here with the given priority. You can do
     * this
     * before TerraControl is started.
     *
     * @param handler  The handler that will receive the events.
     * @param priority The priority of the event.
     */
    public static void registerEventHandler(EventHandler handler, EventPriority priority) {
        if (priority == EventPriority.CANCELABLE) {
            cancelableEventHandlers.add(handler);
        } else {
            monitoringEventHandlers.add(handler);
        }
    }

    // Firing events
    // All methods first call the cancelableEventHandlers, and then the monitoringEventHandlers.
    // Only cancelableEventHandlers can cancel events.
    // Cancelled events are still fired.
    /**
     *
     * @param object
     * @param world
     * @param x
     * @param y
     * @param z
     *               <p/>
     * @return
     */
    public static boolean fireCanCustomObjectSpawnEvent(CustomObject object, TerraWorld world, int x, int y, int z) {
        boolean success = true;
        for (EventHandler handler : cancelableEventHandlers) {
            if (!handler.canCustomObjectSpawn(object, world, x, y, z, !success)) {
                success = false;
            }
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.canCustomObjectSpawn(object, world, x, y, z, !success);
        }
        return success;
    }

    /**
     *
     * @param resource
     * @param world
     * @param random
     * @param villageInChunk
     * @param chunkX
     * @param chunkZ
     *                       <p/>
     * @return
     */
    public static boolean fireResourceProcessEvent(Resource resource, TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        boolean success = true;
        for (EventHandler handler : cancelableEventHandlers) {
            if (!handler.onResourceProcess(resource, world, random, villageInChunk, chunkX, chunkZ, !success)) {
                success = false;
            }
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onResourceProcess(resource, world, random, villageInChunk, chunkX, chunkZ, !success);
        }
        return success;
    }

    /**
     *
     * @param world
     * @param random
     * @param villageInChunk
     * @param chunkX
     * @param chunkZ
     */
    public static void firePopulationStartEvent(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (EventHandler handler : cancelableEventHandlers) {
            handler.onPopulateStart(world, random, villageInChunk, chunkX, chunkZ);
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onPopulateStart(world, random, villageInChunk, chunkX, chunkZ);
        }
    }

    /**
     *
     * @param world
     * @param random
     * @param villageInChunk
     * @param chunkX
     * @param chunkZ
     */
    public static void firePopulationEndEvent(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (EventHandler handler : cancelableEventHandlers) {
            handler.onPopulateEnd(world, random, villageInChunk, chunkX, chunkZ);
        }
        for (EventHandler handler : monitoringEventHandlers) {
            handler.onPopulateEnd(world, random, villageInChunk, chunkX, chunkZ);
        }
    }
}
