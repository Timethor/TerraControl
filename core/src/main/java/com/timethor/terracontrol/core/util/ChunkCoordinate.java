package com.timethor.terracontrol.core.util;

import com.timethor.terracontrol.core.util.coordinate.Coordinate2D;
import com.timethor.terracontrol.core.util.coordinate.Coordinate3D;

/**
 * Position of a chunk, used as a key in HashMaps/HashSets.
 */
public class ChunkCoordinate extends Coordinate2D {

    /**
     * Creation of a ChunkCoordinate
     * <p/>
     * @param chunkX The X value of a chunk
     * @param chunkZ The Z value of a chunk
     */
    protected ChunkCoordinate(int chunkX, int chunkZ) {
        this.x = chunkX;
        this.z = chunkZ;
    }

    /**
     * Creation of a ChunkCoordinate
     * <p/>
     * @param chunk
     */
    protected ChunkCoordinate(Coordinate2D chunk) {
        this.x = chunk.getX();
        this.z = chunk.getZ();
    }

    /**
     * Converts block coordinates to the coordinates of the chunk that the
     * block is located in
     * <p/>
     * @param blockX The X value of the block
     * @param blockZ The Z value of the block
     * <p/>
     * @return A new ChunkCoordinate for which the given block is located
     */
    public static ChunkCoordinate fromBlockCoords(int blockX, int blockZ) {
        // Because of the way Minecraft population works, objects should never
        // be placed in the bottom left corner of a chunk. That's why this
        // formula looks a bit overly complicated.
        return new ChunkCoordinate((int) Math.floor((blockX - 8) / 16.0), (int) Math.floor((blockZ - 8) / 16.0));
    }

    /**
     * Converts block coordinates to the coordinates of the chunk that the
     * block is located in
     * <p/>
     * @param block A 2D block coordinate
     * <p/>
     * @return A new ChunkCoordinate for which the given block is located
     */
    public static ChunkCoordinate fromBlockCoords(Coordinate2D block) {
        // Because of the way Minecraft population works, objects should never
        // be placed in the bottom left corner of a chunk. That's why this
        // formula looks a bit overly complicated.
        return new ChunkCoordinate((int) Math.floor((block.getX() - 8) / 16.0), (int) Math.floor((block.getZ() - 8) / 16.0));
    }

    /**
     * Converts block coordinates to the coordinates of the chunk that the
     * block is located in
     * <p/>
     * @param block A 3D block coordinate
     * <p/>
     * @return A new ChunkCoordinate for which the given block is located
     */
    public static ChunkCoordinate fromBlockCoords(Coordinate3D block) {
        // Because of the way Minecraft population works, objects should never
        // be placed in the bottom left corner of a chunk. That's why this
        // formula looks a bit overly complicated.
        return new ChunkCoordinate((int) Math.floor((block.getX() - 8) / 16.0), (int) Math.floor((block.getZ() - 8) / 16.0));
    }

    /**
     * Returns a new ChunkCoordinate matching the components chunkX and
     * chunkZ given
     * <p/>
     * @param chunkX The chunk X component
     * @param chunkZ The chunk Z component
     * <p/>
     * @return A new ChunkCoordinate matching the components given
     */
    public static ChunkCoordinate fromChunkCoords(int chunkX, int chunkZ) {
        return new ChunkCoordinate(chunkX, chunkZ);
    }

    /**
     * Returns a new ChunkCoordinate matching the 2D coordinate given
     * <p/>
     * @param chunk The 2D coordinate from which a ChunkCoordinate should
     *              be derived
     * <p/>
     * @return A new ChunkCoordinate matching the 2D coordinate given
     */
    public static ChunkCoordinate fromChunkCoords(Coordinate2D chunk) {
        return new ChunkCoordinate(chunk);
    }
}
