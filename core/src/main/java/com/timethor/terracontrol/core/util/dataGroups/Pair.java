package com.timethor.terracontrol.core.util.dataGroups;

/**
 * A data Grouping for use in a Collection Map
 */
interface Pair<F, S> {

    /**
     * Returns the first value corresponding to this Group.
     *
     * @return the first value corresponding to this Group
     * <p/>
     */
    F getFirstValue();

    /**
     * Returns the second value corresponding to this Group.
     *
     * @return the second value corresponding to this Group
     * <p/>
     */
    S getSecondValue();

    /**
     * Replaces the first value corresponding to this Group with the
     * specified value (optional operation).
     *
     * @param value new value to be stored in the first part of this Group
     * <p/>
     * @return old first value corresponding to the Group
     * <p/>
     */
    F setFirstValue(F value);

    /**
     * Replaces the second value corresponding to this Group with the
     * specified value (optional operation).
     *
     * @param value new value to be stored in the second part of this Group
     * <p/>
     * @return old second value corresponding to the Group
     * <p/>
     */
    S setSecondValue(S value);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}