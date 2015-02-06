package com.timethor.terracontrol.bukkit;

import com.timethor.terracontrol.core.generator.ObjectSpawner;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_6_R2.CraftChunk;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 * @author  Timethor
 */
public class TerraBlockPopulator extends BlockPopulator {

    /**
	 */
    private BukkitWorld world;
    /**
	 */
    private ObjectSpawner spawner;

    public TerraBlockPopulator(BukkitWorld _world) {
        this.world = _world;
        this.spawner = new ObjectSpawner(_world.getSettings(), _world);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        this.world.LoadChunk(((CraftChunk) chunk).getHandle());
        this.spawner.populate(chunk.getX(), chunk.getZ());
    }
}
