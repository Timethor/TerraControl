package com.timethor.terracontrol.bukkit;

import com.timethor.terracontrol.core.configuration.BiomeConfig;
import net.minecraft.server.v1_6_R2.BiomeBase;

/**
 * @author  Timethor
 */
public class NullBiome extends BukkitBiome {

    /**
	 */
    private String name;

    public NullBiome(String _name) {
        super(BiomeBase.OCEAN);
        this.name = _name;
    }

    @Override
    public boolean isCustom() {
        return true;
    }

    @Override
    public int getId() {
        return 255;
    }

    /**
	 * @return
	 */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setEffects(BiomeConfig config) {
    }
}
