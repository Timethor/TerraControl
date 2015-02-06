package com.timethor.terracontrol.core.resource.propagator;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.exception.InvalidConfigException;

import java.util.List;
import java.util.Random;

/**
 * @author Timethor
 */
public class VinesGen extends Resource {

    /**
     */
    private int minAltitude;
    /**
     */
    private int maxAltitude;

    @Override
    public void spawn(TerraWorld world, Random rand, boolean villageInChunk, int x, int z) {
        int _x = x;
        int _z = z;
        int y = minAltitude;

        while (y < maxAltitude) {
            if (world.isEmpty(_x, y, _z)) {
                for (int direction = 2; direction <= 5; direction++) {
                    if (canPlace(world, _x, y, _z, direction)) {
                        world.setBlock(_x, y, _z, MaterialCatalog.VINE.id, 1 << d[OPPOSITE_FACING[direction]]);
                        break;
                    }
                }
            } else {
                _x = x + rand.nextInt(4) - rand.nextInt(4);
                _z = z + rand.nextInt(4) - rand.nextInt(4);
            }
            y++;
        }

    }

    /**
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @param paramInt4
     *                  <p/>
     * @return
     */
    public boolean canPlace(TerraWorld world, int x, int y, int z, int paramInt4) {
        int id;
        switch (paramInt4) {
            default:
                return false;
            case 1:
                id = (world.getTypeId(x, y + 1, z));
                break;
            case 2:
                id = (world.getTypeId(x, y, z + 1));
                break;
            case 3:
                id = (world.getTypeId(x, y, z - 1));
                break;
            case 5:
                id = (world.getTypeId(x - 1, y, z));
                break;
            case 4:
                id = (world.getTypeId(x + 1, y, z));
                break;
        }
        return MaterialCatalog.getMaterial(id).isSolid();
    }
    /**
     *
     */
    public static final int[] d = {-1, -1, 2, 0, 1, 3};
    /**
     *
     */
    public static final int[] OPPOSITE_FACING = {1, 0, 3, 2, 5, 4};

    @Override
    public void load(List<String> args) throws InvalidConfigException {
        blockId = MaterialCatalog.VINE.id; // Hardcoded for now

        assureSize(4, args);
        frequency = readInt(args.get(0), 1, 100);
        chance = readRarity(args.get(1));
        minAltitude = readInt(args.get(2), TerraControl.worldDepth, TerraControl.worldHeight);
        maxAltitude = readInt(args.get(3), minAltitude + 1, TerraControl.worldHeight);
    }

    @Override
    public String makeString() {
        return "Vines(" + frequency + "," + chance + "," + minAltitude + "," + maxAltitude + ")";
    }
}
