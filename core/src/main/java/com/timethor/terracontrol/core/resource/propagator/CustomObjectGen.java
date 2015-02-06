package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;
import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.util.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Timethor
 */
public class CustomObjectGen extends Resource {

    /**
     */
    private List<CustomObject> objects;
    /**
     */
    private List<String> objectNames;

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        if (args.isEmpty() || (args.size() == 1 && args.get(0).trim().equals(""))) {
            // Backwards compability
            args = new ArrayList<>();
            args.add("UseWorld");
        }
        objects = new ArrayList<>();
        objectNames = new ArrayList<>();
        for (String arg : args) {
            CustomObject object = TerraControl.getCustomObjectManager().getObjectFromString(arg, getHolder().worldConfig);
            if (object == null || !object.canSpawnAsObject()) {
                throw new InvalidConfigException("No custom object found with the name " + arg);
            }
            objects.add(object);
            objectNames.add(arg);
        }
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z) {
        // Left blank, as process(..) already handles this.
    }

    @Override
    protected void spawnInChunk(TerraWorld world, Random random, boolean villageInChunk, int chunkX, int chunkZ) {
        for (CustomObject object : objects) {
            object.process(world, random, chunkX, chunkZ);
        }
    }

    @Override
    public String makeString() {
        return "CustomObject(" + StringHelper.join(objectNames, ",") + ")";
    }
}
