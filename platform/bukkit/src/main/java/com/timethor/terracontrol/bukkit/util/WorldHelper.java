package com.timethor.terracontrol.bukkit.util;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.bukkit.TerraPlugin;
import net.minecraft.server.v1_6_R2.World;

public abstract class WorldHelper {

    /**
     * Returns the LocalWorld of the Minecraft world. Returns null if there
     * is
     * no world.
     *
     * @param world
     *              <p/>
     * @return The LocalWorld, or null if there is none.
     */
    public static TerraWorld toLocalWorld(World world) {
        return ((TerraPlugin) TerraControl.getEngine()).worlds.get(world.getWorld().getUID());
    }
}
