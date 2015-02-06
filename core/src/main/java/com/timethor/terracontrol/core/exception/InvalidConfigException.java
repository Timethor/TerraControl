package com.timethor.terracontrol.core.exception;

/**
 *
 * @author Timethor
 */
@SuppressWarnings("serial") // No need to serialize this
public class InvalidConfigException extends Exception {

    /**
     *
     * @param string
     */
    public InvalidConfigException(String string) {
        super(string);
    }
}
