package com.timethor.terracontrol.core;

import java.io.File;
import java.util.logging.Level;

/**
 * @author Timethor
 */
public interface TerraControlEngine {

    /**
     * Returns the world object with the given name.
     *
     * @param name The name of the world.
     * <p/>
     * @return The world object.
     */
    public TerraWorld getWorld(String name);

    /**
     * Logs the messages.
     *
     * @param message The messages to log.
     * @param level   The severity of the message
     */
    public void log(Level level, String... message);

    /**
     * Logs the messages.
     *
     * @param message The messages to log.
     */
    public void log(String... message);

    /**
     * Logs the message(s) with the given importance <b>ONLY IF</b> logger
     * level matches the level provided. Message will be prefixed with
     * TerraControl, so don't do that yourself.
     *
     * @param level
     * @param messages The messages to log.
     */
    public void logIfLevel(Level level, String... messages);

    /**
     * Returns the folder where the global objects are stored in.
     * <p/>
     * @return
     */
    public File getGlobalObjectsDirectory();

    /**
     * Returns whether the given id is a valid block id. If the block
     * doesn't exist, the id is considered invalid.
     *
     * @param id The id of the block.
     * <p/>
     * @return Whether the given id is a valid block id.
     */
    public boolean isValidBlockId(int id);
}
