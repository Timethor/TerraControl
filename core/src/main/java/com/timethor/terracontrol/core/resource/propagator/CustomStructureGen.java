package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.custom.object.CustomObjectCoordinate;
import com.timethor.terracontrol.core.custom.object.CustomObjectStructure;
import com.timethor.terracontrol.core.custom.object.Rotation;
import com.timethor.terracontrol.core.custom.object.StructuredCustomObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author   Timethor
 */
public class CustomStructureGen extends Resource {

    /**
	 */
    private List<StructuredCustomObject> objects;
    /**
	 */
    private List<Double> objectChances;
    /**
	 */
    private List<String> objectNames;

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        objects = new ArrayList<>();
        objectNames = new ArrayList<>();
        objectChances = new ArrayList<>();
        for (int i = 0; i < args.size() - 1; i += 2) {
            CustomObject object = TerraControl.getCustomObjectManager().getObjectFromString(args.get(i), getHolder().worldConfig);
            if (object == null || !object.canSpawnAsObject()) {
                throw new InvalidConfigException("No custom object found with the name " + args.get(i));
            }
            if (!(object instanceof StructuredCustomObject) || ((StructuredCustomObject) object).getBranches(Rotation.NORTH).length == 0) {
                throw new InvalidConfigException("The object " + args.get(i) + " isn't a structure");
            }
            objects.add((StructuredCustomObject) object);
            objectNames.add(args.get(i));
            objectChances.add(readRarity(args.get(i + 1)));
        }

        // Inject ourselves in the BiomeConfig
        if (getHolder().structureGen != null) {
            throw new InvalidConfigException("There can only be one CustomStructure resource in each BiomeConfig");
        }
        getHolder().structureGen = this;
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Left blank, as spawnInChunk(..) already handles this.
    }

    @Override
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, int currentChunkX, int currentChunkZ) {
        // Find all structures that reach this chunk, and spawn them
        int searchRadius = 5; // @TODO: Maybe add a setting for this?

        for (int searchChunkX = currentChunkX - searchRadius; searchChunkX < currentChunkX + searchRadius; searchChunkX++) {
            for (int searchChunkZ = currentChunkZ - searchRadius; searchChunkZ < currentChunkZ + searchRadius; searchChunkZ++) {
                CustomObjectStructure structureStart = world.getStructureCache().getStructureStart(searchChunkX, searchChunkZ);
                if (structureStart != null) {
                    structureStart.spawnForChunk(currentChunkX, currentChunkZ);
                }
            }
        }
    }

    @Override
    public String makeString() {
        if (objects.isEmpty()) {
            return "CustomStructure()";
        }
        String output = "CustomStructure(" + objectNames.get(0) + "," + objectChances.get(0);
        for (int i = 1; i < objectNames.size(); i++) {
            output += "," + objectNames.get(i) + "," + objectChances.get(i);
        }
        return output + ")";
    }

    /**
     *
     * @param random
     * @param chunkX
     * @param chunkZ
     * @return
     */
    public CustomObjectCoordinate getRandomObjectCoordinate(Random random, int chunkX, int chunkZ) {
        if (objects.isEmpty()) {
            return null;
        }
        for (int objectNumber = 0; objectNumber < objects.size(); objectNumber++) {
            if (random.nextDouble() * 100.0 < objectChances.get(objectNumber)) {
                return objects.get(objectNumber).makeCustomObjectCoordinate(random, chunkX, chunkZ);
            }
        }
        return null;
    }
}
