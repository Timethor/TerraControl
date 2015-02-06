package com.timethor.terracontrol.core.configuration;

import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.resource.propagator.AboveWaterGen;
import com.timethor.terracontrol.core.resource.propagator.CactusGen;
import com.timethor.terracontrol.core.resource.propagator.CustomObjectGen;
import com.timethor.terracontrol.core.resource.propagator.CustomStructureGen;
import com.timethor.terracontrol.core.resource.propagator.DungeonGen;
import com.timethor.terracontrol.core.resource.propagator.GrassGen;
import com.timethor.terracontrol.core.resource.propagator.LiquidGen;
import com.timethor.terracontrol.core.resource.propagator.OreGen;
import com.timethor.terracontrol.core.resource.propagator.PlantGen;
import com.timethor.terracontrol.core.resource.propagator.ReedGen;
import com.timethor.terracontrol.core.resource.propagator.SaplingGen;
import com.timethor.terracontrol.core.resource.propagator.SmallLakeGen;
import com.timethor.terracontrol.core.resource.propagator.TreeGen;
import com.timethor.terracontrol.core.resource.propagator.UnderWaterOreGen;
import com.timethor.terracontrol.core.resource.propagator.UndergroundLakeGen;
import com.timethor.terracontrol.core.resource.propagator.VeinGen;
import com.timethor.terracontrol.core.resource.propagator.VinesGen;
import com.timethor.terracontrol.core.resource.propagator.WellGen;
import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public final class ConfigFunctionsManager {

    /**
     */
    private Map<String, Class<? extends ConfigFunction<?>>> configFunctions;

    /**
     *
     */
    public ConfigFunctionsManager() {
        // Also store in this class
        this.configFunctions = new HashMap<>();

        // Add vanilla resources
        registerConfigFunction("AboveWaterRes", AboveWaterGen.class);
        registerConfigFunction("Cactus", CactusGen.class);
        registerConfigFunction("CustomObject", CustomObjectGen.class);
        registerConfigFunction("CustomStructure", CustomStructureGen.class);
        registerConfigFunction("Dungeon", DungeonGen.class);
        registerConfigFunction("Grass", GrassGen.class);
        registerConfigFunction("Liquid", LiquidGen.class);
        registerConfigFunction("Ore", OreGen.class);
        registerConfigFunction("Plant", PlantGen.class);
        registerConfigFunction("Reed", ReedGen.class);
        registerConfigFunction("Sapling", SaplingGen.class);
        registerConfigFunction("SmallLake", SmallLakeGen.class);
        registerConfigFunction("Tree", TreeGen.class);
        registerConfigFunction("UndergroundLake", UndergroundLakeGen.class);
        registerConfigFunction("UnderWaterOre", UnderWaterOreGen.class);
        registerConfigFunction("Vein", VeinGen.class);
        registerConfigFunction("Vines", VinesGen.class);
        registerConfigFunction("Well", WellGen.class);
    }

    /**
     *
     * @param name
     * @param value
     */
    public void registerConfigFunction(String name, Class<? extends ConfigFunction<?>> value) {
        configFunctions.put(name.toLowerCase(), value);
    }

    /**
     * Returns a config function with the given name.
     *
     * @param <T>
     * @param name               The name of the config function.
     * @param holder             The holder of the config function.
     *                           WorldConfig or BO3.
     * @param locationOfResource The location of the config function, for
     *                           example
     *                           TaigaBiomeConfig.ini.
     * @param args               The args of the function.
     * <p/>
     * @return A config function with the given name, or null of it wasn't
     *         found.
     */
    @SuppressWarnings("unchecked")
    // It's checked with if (!clazz.isAssignableFrom(holder.getClass()))
    public <T> ConfigFunction<T> getConfigFunction(String name, T holder, String locationOfResource, List<String> args) {
        // Check if config function exists
        if (!configFunctions.containsKey(name.toLowerCase())) {
            TerraControl.log(Level.WARNING, "Invalid resource " + name + " in " + locationOfResource + ": resource type not found!");
            return null;
        }

        ConfigFunction<?> configFunction;
        Class<? extends ConfigFunction<?>> clazz = configFunctions.get(name.toLowerCase());

        // Get a config function
        try {
            configFunction = clazz.newInstance();
        } catch (InstantiationException e) {
            TerraControl.log(Level.WARNING, "Reflection error (Instantiation) while loading the resources: " + e.getMessage());
            TerraControl.log(Level.WARNING, e.getStackTrace().toString());
            return null;
        } catch (IllegalAccessException e) {
            TerraControl.log(Level.WARNING, "Reflection error (IllegalAccess) while loading the resources: " + e.getMessage());
            TerraControl.log(Level.WARNING, e.getStackTrace().toString());
            return null;
        }

        // Check if config function is of the right type
        boolean matchingTypes;
        try {
            matchingTypes = holder.getClass().isAssignableFrom((Class<?>) clazz.getMethod("getHolderType").invoke(configFunction));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            TerraControl.log(Level.WARNING, "Reflection error (" + e.getClass().getSimpleName() + ") while loading the resources: " + e.getMessage());
            TerraControl.log(Level.WARNING, e.getStackTrace().toString());
            return null;
        }
        if (!matchingTypes) {
            TerraControl.log(Level.WARNING, "Invalid resource " + name + " in " + locationOfResource + ": cannot be placed in this config file!");
            return null;
        }

        // Set the holder
        configFunction.setHolder(holder);

        // Load it
        try {
            configFunction.read(name, args);
        } catch (InvalidConfigException e) {
            TerraControl.log(Level.WARNING, "Invalid resource " + name + " in " + locationOfResource + ": " + e.getMessage());
        }

        // Return it
        return (ConfigFunction<T>) configFunction;
    }
}
