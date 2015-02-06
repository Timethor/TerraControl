package com.timethor.terracontrol.core;

import com.timethor.terracontrol.core.builtin.MaterialCatalog;
import com.timethor.terracontrol.core.configuration.BiomeConfig;
import com.timethor.terracontrol.core.configuration.Tag;
import com.timethor.terracontrol.core.configuration.WorldConfig;
import com.timethor.terracontrol.core.custom.object.CustomObjectStructureCache;
import com.timethor.terracontrol.core.resource.propagator.TreeType;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Timethor
 */
public interface TerraWorld {
    // Biome init

    /**
     *
     * @param name
     * @param id
     *             <p/>
     * @return
     */
    public TerraBiome AddBiome(String name, int id);

    /**
     *
     * @param name
     *             <p/>
     * @return
     */
    public TerraBiome getNullBiome(String name);

    // With static id allocation this is not a required feature.
    /**
     *
     * @return
     */
    public int getMaxBiomesCount();

    /**
     *
     * @return
     */
    public int getFreeBiomeId();

    /**
     *
     * @param id
     *           <p/>
     * @return
     */
    public TerraBiome getBiomeById(int id);

    /**
     *
     * @param name
     *             <p/>
     * @return
     */
    public int getBiomeIdByName(String name);

    /**
     *
     * @return
     */
    public ArrayList<TerraBiome> getDefaultBiomes();

    // Biome manager
    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     *                   <p/>
     * @return
     */
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int x_size, int z_size);

    /**
     *
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     *               <p/>
     * @return
     */
    public float[] getTemperatures(int x, int z, int x_size, int z_size);

    /**
     *
     * @param biomeArray
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     *                   <p/>
     * @return
     */
    public int[] getBiomes(int[] biomeArray, int x, int z, int x_size, int z_size);

    /**
     *
     * @param x
     * @param z
     *          <p/>
     * @return
     */
    public int getCalculatedBiomeId(int x, int z);

    /**
     * Calculates the biome that should generate at the given coordinates.
     *
     * @param x The block x.
     * @param z The block z.
     * <p/>
     * @return The biome at the given coordinates.
     */
    public TerraBiome getCalculatedBiome(int x, int z);

    /**
     *
     * @param x
     * @param z
     *          <p/>
     * @return
     */
    public int getBiomeId(int x, int z);

    /**
     * Gets the (stored) biome at the given coordinates.
     *
     * @param x The block x.
     * @param z The block z.
     * <p/>
     * @return The biome at the given coordinates.
     */
    public TerraBiome getBiome(int x, int z);

    // temperature*rain
    /**
     *
     * @param index
     *              <p/>
     * @return
     */
    public double getBiomeFactorForOldBM(int index);

    // Default generators
    /**
     *
     * @param x
     * @param z
     * @param chunkArray
     * @param dry
     */
    public void PrepareTerrainObjects(int x, int z, byte[] chunkArray, boolean dry);

    /**
     *
     * @param rand
     * @param x
     * @param y
     * @param z
     */
    public void PlaceDungeons(Random rand, int x, int y, int z);

    /**
     *
     * @param type
     * @param rand
     * @param x
     * @param y
     * @param z
     *             <p/>
     * @return
     */
    public boolean PlaceTree(TreeType type, Random rand, int x, int y, int z);

    /**
     *
     * @param rand
     * @param chunk_x
     * @param chunk_z
     *                <p/>
     * @return
     */
    public boolean PlaceTerrainObjects(Random rand, int chunk_x, int chunk_z);

    /**
     *
     */
    public void replaceBlocks();

    /**
     *
     */
    public void replaceBiomes();

    /**
     * Since Minecraft Beta 1.8, friendly mobs are mainly spawned during
     * the terrain generation.
     * <p/>
     * @param config
     * @param random
     * @param chunkX
     * @param chunkZ
     */
    public void placePopulationMobs(BiomeConfig config, Random random, int chunkX, int chunkZ);

    // Blocks
    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public int getTypeId(int x, int y, int z);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public byte getTypeData(int x, int y, int z);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public boolean isEmpty(int x, int y, int z);

    /**
     *
     * @param x
     * @param y
     * @param z
     * @param typeId
     * @param data
     * @param updateLight
     * @param applyPhysics
     * @param notifyPlayers
     */
    public void setBlock(final int x, final int y, final int z, final int typeId, final int data, final boolean updateLight, final boolean applyPhysics, final boolean notifyPlayers);

    /**
     *
     * @param x
     * @param y
     * @param z
     * @param typeId
     * @param data
     */
    public void setBlock(final int x, final int y, final int z, final int typeId, final int data);

    /**
     *
     * @param x
     * @param y
     * @param z
     * @param tag
     */
    public void attachMetadata(int x, int y, int z, Tag tag);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public Tag getMetadata(int x, int y, int z);

    /**
     *
     * @param x
     * @param z
     *          <p/>
     * @return
     */
    public int getLiquidHeight(int x, int z);

    /**
     * Returns the block above the highest solid block.
     * <p/>
     * @param x
     * @param z
     *          <p/>
     * @return
     */
    public int getSolidHeight(int x, int z);

    /**
     * Returns the block above the highest block.
     * <p/>
     * @param x
     * @param z
     *          <p/>
     * @return
     */
    public int getHighestBlockYAt(int x, int z);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public MaterialCatalog getMaterial(int x, int y, int z);

    /**
     *
     * @param createNew
     */
    public void setChunksCreations(boolean createNew);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public int getLightLevel(int x, int y, int z);

    /**
     *
     * @param x
     * @param y
     * @param z
     *          <p/>
     * @return
     */
    public boolean isLoaded(int x, int y, int z);

    /**
     *
     * @return
     */
    public WorldConfig getSettings();

    /**
     *
     * @return
     */
    public CustomObjectStructureCache getStructureCache();

    /**
     *
     * @return
     */
    public String getName();

    // Terrain init
    /**
     *
     * @return
     */
    public long getSeed();

    /**
     *
     * @return
     */
    public int getHeight();

    /**
     *
     * @return
     */
    public int getHeightBits();

    /**
     *
     * @param heightBits
     */
    public void setHeightBits(int heightBits);
}