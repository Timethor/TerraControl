package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.List;
import java.util.Random;

/**
 * @author Timethor
 */
public class DungeonGen extends Resource {

    /**
     */
    private int minAltitude;
    /**
     */
    private int maxAltitude;

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(4, args);

        frequency = readInt(args.get(0), 1, 100);
        chance = readRarity(args.get(1));
        minAltitude = readInt(args.get(2), TerraControl.worldDepth, TerraControl.worldHeight);
        maxAltitude = readInt(args.get(3), minAltitude + 1, TerraControl.worldHeight);
    }

    @Override
    public void spawn(TerraWorld world, Random random, boolean villageInChunk, int x, int z) {
        int y = random.nextInt(maxAltitude - minAltitude) + minAltitude;
        world.PlaceDungeons(random, x, y, z);
    }

    @Override
    public String makeString() {
        return "Dungeon(" + frequency + "," + chance + "," + minAltitude + "," + maxAltitude + ")";
    }
}