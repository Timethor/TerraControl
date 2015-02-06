package com.timethor.terracontrol.forge.structuregens;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.forge.util.WorldHelper;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureMineshaftStart;
import net.minecraft.world.gen.structure.StructureStart;

public class MineshaftGen extends MapGenStructure {

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        if (rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ))) {
            TerraWorld world = WorldHelper.toLocalWorld(this.worldObj);
            int biomeId = world.getCalculatedBiomeId(chunkX * 16 + 8, chunkZ * 16 + 8);
            if (rand.nextDouble() * 100.0 < world.getSettings().biomeConfigs[biomeId].mineshaftsRarity) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2) {
        return new StructureMineshaftStart(this.worldObj, this.rand, par1, par2);
    }
}
