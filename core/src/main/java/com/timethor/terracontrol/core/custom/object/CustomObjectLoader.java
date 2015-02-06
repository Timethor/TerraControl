package com.timethor.terracontrol.core.custom.object;

import java.io.File;

/**
 *
 * @author Timethor
 */
public interface CustomObjectLoader {

    /**
     * Returns a CustomObject with the given name and file. The object
     * shouldn't yet be initialisized.
     *
     * @param objectName
     * @param file
     * <p/>
     * @return
     */
    public CustomObject loadFromFile(String objectName, File file);

    /**
     * Called whenever Terrain Control is being shut down / reloaded.
     */
    public void onShutdown();
}
