package com.timethor.terraincontrol.forge;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.event.EventHandler;
import com.timethor.terracontrol.core.resource.propagator.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Translates TerraControl events into MinecraftForge terrain events
 */
public class EventManager extends EventHandler {
    // Two maps, key is world name, value is boolean

    private Map<String, Boolean> hasOreGenBegun = new HashMap<>();
    private Map<String, Boolean> hasDecorationBegun = new HashMap<>();

    @Override
    public boolean onResourceProcess(Resource resource, TerraWorld localWorld, Random random, boolean villageInChunk, int chunkX, int chunkZ, boolean isCancelled) {
        final ForgeWorld world = (ForgeWorld) localWorld;

        // Convert to Forge event and fire
        if (resource instanceof DungeonGen
            || resource instanceof SmallLakeGen
            || resource instanceof UndergroundLakeGen
            || resource instanceof LiquidGen
            || resource instanceof CustomObjectGen) {
            // Fire population event
            Populate.EventType forgeEvent = getPopulateEventType(resource.getBlockId());
            return TerrainGen.populate(world.getChunkGenerator(), world.getWorld(), random, chunkX, chunkZ, villageInChunk, forgeEvent);
        } else if (resource instanceof OreGen) {
            if (!hasOreGenerationBegun(world)) {
                // Fire ore generation start event
                MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world.getWorld(), random, chunkX, chunkZ));
                setOreGenerationBegun(world, true);
            }
            // Fire ore generation event
            GenerateMinable.EventType forgeEvent = getOreEventType(resource.getBlockId());
            return TerrainGen.generateOre(world.getWorld(), random, null, chunkX, chunkZ, forgeEvent);
        } else {
            if (!hasDecorationBegun(world)) {
                // Fire decoration start event
                MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world.getWorld(), random, chunkX, chunkZ));
                setDecorationBegun(world, true);
            }
            // Fire decoration event
            Decorate.EventType forgeEvent = getDecorateEventType(resource.getBlockId());
            return TerrainGen.decorate(world.getWorld(), random, chunkX, chunkZ, forgeEvent);
        }
    }

    @Override
    public void onPopulateStart(TerraWorld localWorld, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        final ForgeWorld world = (ForgeWorld) localWorld;

        // Reset states
        setDecorationBegun(world, false);
        setOreGenerationBegun(world, false);

        // Fire event
        final PopulateChunkEvent forgeEvent = new PopulateChunkEvent.Pre(world.getChunkGenerator(), world.getWorld(), random, chunkX, chunkZ, villageInChunk);
        MinecraftForge.EVENT_BUS.post(forgeEvent);
    }

    @Override
    public void onPopulateEnd(TerraWorld localWorld, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        final ForgeWorld world = (ForgeWorld) localWorld;

        // Fire all events

        // Decoration close
        if (hasDecorationBegun(world)) {
            MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world.getWorld(), random, chunkX, chunkZ));
            setDecorationBegun(world, false);
        }

        // Ore generation close
        if (hasOreGenerationBegun(world)) {
            MinecraftForge.EVENT_BUS.post(new OreGenEvent.Post(world.getWorld(), random, chunkX, chunkZ));
            setOreGenerationBegun(world, false);
        }

        // Population close
        final PopulateChunkEvent forgeEvent = new PopulateChunkEvent.Post(world.getChunkGenerator(), world.getWorld(), random, chunkX, chunkZ, villageInChunk);
        MinecraftForge.EVENT_BUS.post(forgeEvent);

        // Population close (FML and ModLoader style)
        GameRegistry.generateWorld(chunkX, chunkZ, world.getWorld(), world.getChunkGenerator(), world.getChunkGenerator());
    }

    private Decorate.EventType getDecorateEventType(int blockId) {
        if (blockId == MaterialCatalog.WATER_LILY.id) {
            return Decorate.EventType.LILYPAD;
        }
        if (blockId == MaterialCatalog.CACTUS.id) {
            return Decorate.EventType.CACTUS;
        }
        if (blockId == MaterialCatalog.LONG_GRASS.id) {
            return Decorate.EventType.GRASS;
        }
        if (blockId == MaterialCatalog.DEAD_BUSH.id) {
            return Decorate.EventType.DEAD_BUSH;
        }
        if (blockId == MaterialCatalog.RED_ROSE.id || blockId == MaterialCatalog.YELLOW_FLOWER.id) {
            return Decorate.EventType.FLOWERS;
        }
        if (blockId == MaterialCatalog.PUMPKIN.id) {
            return Decorate.EventType.PUMPKIN;
        }
        if (blockId == MaterialCatalog.BROWN_MUSHROOM.id || blockId == MaterialCatalog.RED_MUSHROOM.id) {
            return Decorate.EventType.SHROOM;
        }
        if (blockId == MaterialCatalog.SUGAR_CANE_BLOCK.id) {
            return Decorate.EventType.REED;
        }
        if (blockId == MaterialCatalog.SAND.id) {
            return Decorate.EventType.SAND;
        }
        if (blockId == MaterialCatalog.CLAY.id) {
            return Decorate.EventType.CLAY;
        }
        return Decorate.EventType.CUSTOM;
    }

    private Populate.EventType getPopulateEventType(int blockId) {
        if (blockId == MaterialCatalog.WATER.id) {
            return Populate.EventType.LAKE;
        }
        if (blockId == MaterialCatalog.LAVA.id) {
            return Populate.EventType.LAVA;
        }
        return Populate.EventType.CUSTOM;
    }

    private GenerateMinable.EventType getOreEventType(int blockId) {
        if (blockId == MaterialCatalog.COAL_ORE.id) {
            return GenerateMinable.EventType.COAL;
        }
        if (blockId == MaterialCatalog.DIAMOND_ORE.id) {
            return GenerateMinable.EventType.DIAMOND;
        }
        if (blockId == MaterialCatalog.DIRT.id) {
            return GenerateMinable.EventType.DIRT;
        }
        if (blockId == MaterialCatalog.GOLD_ORE.id) {
            return GenerateMinable.EventType.GOLD;
        }
        if (blockId == MaterialCatalog.GRAVEL.id) {
            return GenerateMinable.EventType.GRAVEL;
        }
        if (blockId == MaterialCatalog.IRON_ORE.id) {
            return GenerateMinable.EventType.IRON;
        }
        if (blockId == MaterialCatalog.LAPIS_ORE.id) {
            return GenerateMinable.EventType.LAPIS;
        }
        if (blockId == MaterialCatalog.REDSTONE_ORE.id) {
            return GenerateMinable.EventType.REDSTONE;
        }
        return GenerateMinable.EventType.CUSTOM;
    }

    private boolean hasOreGenerationBegun(TerraWorld world) {
        return hasOreGenBegun.get(world.getName());
    }

    private boolean hasDecorationBegun(TerraWorld world) {
        return hasDecorationBegun.get(world.getName());
    }

    private void setOreGenerationBegun(TerraWorld world, boolean begun) {
        hasOreGenBegun.put(world.getName(), begun);
    }

    private void setDecorationBegun(TerraWorld world, boolean begun) {
        hasDecorationBegun.put(world.getName(), begun);
    }
}
