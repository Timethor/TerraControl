package com.timethor.terracontrol.core.custom.object;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.resource.propagator.CustomStructureGen;
import com.timethor.terracontrol.core.util.ChunkCoordinate;
import com.timethor.terracontrol.core.util.RandomHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Each world has a cache of unfinished structures. This class is the
 * cache.
 */
public class CustomObjectStructureCache {

    /**
     */
    private Map<ChunkCoordinate, CustomObjectStructure> structureCache;
    /**
     */
    private TerraWorld world;

    /**
     *
     * @param world
     */
    public CustomObjectStructureCache(TerraWorld world) {
        this.world = world;
        this.structureCache = new HashMap<>();
    }

    /**
     *
     * @param world
     */
    public void reload(TerraWorld world) {
        this.world = world;
        structureCache.clear();
    }

    /**
     *
     * @param chunkX
     * @param chunkZ
     *               <p/>
     * @return
     */
    public CustomObjectStructure getStructureStart(int chunkX, int chunkZ) {
        ChunkCoordinate coord = ChunkCoordinate.fromChunkCoords(chunkX, chunkZ);
        CustomObjectStructure structureStart = structureCache.get(coord);

        // Clear cache if needed
        // @TODO: Why 400? Make this an advanced setting? proportional to max available memory?
        if (structureCache.size() > 400) {
            structureCache.clear();
        }

        if (structureStart != null) {
            return structureStart;
        }
        // No structure found, create one
        Random random = RandomHelper.getRandomForCoords(chunkX ^ 2, (chunkZ + 1) * 2, world.getSeed());
        CustomStructureGen structureGen = world.getSettings().biomeConfigs[world.getBiomeId(chunkX * 16 + 15, chunkZ * 16 + 15)].structureGen;
        if (structureGen != null) {
            CustomObjectCoordinate customObject = structureGen.getRandomObjectCoordinate(random, chunkX, chunkZ);
            if (customObject != null) {
                structureStart = new CustomObjectStructure(world, customObject);
                structureCache.put(coord, structureStart);
                return structureStart;
            } // @TODO: Maybe also store that no structure was here?
        }

        return null;
    }
}
