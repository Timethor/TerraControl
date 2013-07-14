package com.timethor.terracontrol.core.util.dataGroups;

/**
 * A data Grouping for use in a Collection Map
 */
interface Triple<F, S, T> extends Pair<F, S> {

    /**
     * Returns the third value corresponding to this Group.
     *
     * @return the third value corresponding to this Group
     * <p/>
     */
    T getThirdValue();

    /**
     * Replaces the third value corresponding to this Group with the
     * specified value (optional operation).
     *
     * @param value new value to be stored in the third part of this Group
     * <p/>
     * @return old third value corresponding to the Group
     * <p/>
     */
    T setThirdValue(T value);
}