package com.timethor.terracontrol.core.custom.object.bo4;

import com.timethor.terracontrol.core.custom.object.bo4.config.BO4Config;
import com.timethor.terracontrol.core.configuration.ConfigFunction;

/**
 * Represents a BO3 function - a ConfigFunction with a BO3 as holder. It
 * can be
 * rotated.
 */
public abstract class BO4Function extends ConfigFunction<BO4Config> {

    /**
     *
     * @return
     */
    @Override
    public Class<BO4Config> getHolderType() {
        return BO4Config.class;
    }

    /**
     * Returns a new BO3Function that is rotated 90 degrees.
     * <p/>
     * Note: the BO3Functons can have a magical link: if you change
     * something on
     * the rotated one, it may also change on the original and vice versa.
     *
     * @return A new BlockFunction that is rotated 90 degrees.
     */
    public abstract BO4Function rotate();
}
