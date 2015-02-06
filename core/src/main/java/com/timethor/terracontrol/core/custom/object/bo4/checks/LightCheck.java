package com.timethor.terracontrol.core.custom.object.bo4.checks;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.List;

/**
 * @author Timethor
 */
public class LightCheck extends BO4Check {
    // The minimum and maximum light levels, inclusive

    /**
     */
    public int minLightLevel;
    /**
     */
    public int maxLightLevel;

    @Override
    public boolean preventsSpawn(TerraWorld world, int x, int y, int z) {
        int lightLevel = world.getLightLevel(x, y, z);
        if (lightLevel < minLightLevel || lightLevel > maxLightLevel) {
            // Out of bounds
            return true;
        }

        return false;
    }

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        assureSize(5, args);
        x = readInt(args.get(0), -100, 100);
        y = readInt(args.get(1), -100, 100);
        z = readInt(args.get(2), -100, 100);
        minLightLevel = readInt(args.get(3), 0, 16);
        maxLightLevel = readInt(args.get(4), minLightLevel, 16);
    }

    @Override
    public String makeString() {
        return "LightCheck(" + x + "," + y + "," + z + "," + minLightLevel + "," + maxLightLevel + ")";
    }

    @Override
    public BO4Check rotate() {
        LightCheck rotatedCheck = new LightCheck();
        rotatedCheck.x = z;
        rotatedCheck.y = y;
        rotatedCheck.z = -x;
        rotatedCheck.minLightLevel = minLightLevel;
        rotatedCheck.maxLightLevel = maxLightLevel;

        return rotatedCheck;
    }
}
