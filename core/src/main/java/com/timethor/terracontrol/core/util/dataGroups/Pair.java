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

    /**
     * Compares the specified object with this Group for equality.
     * Returns <tt>true</tt> if the given object is also a Group and
     * the elements represent the same mapping. More formally, given two
     * elements <tt>e1</tt> and <tt>e2</tt> represent the same mapping if
     * <pre>
     *     (e1.getFirstValue()==null ?
     *      e2.getFirstValue()==null : e1.getFirstValue().equals(e2.getFirstValue()))  &amp;&amp;
     *     (e1.getSecondValue()==null ?
     *      e2.getSecondValue()==null : e1.getSecondValue().equals(e2.getSecondValue()))
     * </pre>
     * This ensures that the <tt>equals</tt> method works properly across
     * different implementations of the <tt>Group</tt> interfaces.
     *
     * @param o object to be compared for equality with this Group
     * <p/>
     * @return <tt>true</tt> if the specified object is equal to this Group
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns the hash code value for this Group. The hash code
     * of a Group <tt>e</tt> given two elements is defined to be:
     * <pre>
     *     (e.getFirstValue()==null   ? 0 : e.getFirstValue().hashCode()) ^
     *     (e.getSecondValue()==null ? 0 : e.getSecondValue().hashCode())
     * </pre>
     * This ensures that <tt>e1.equals(e2)</tt> implies that
     * <tt>e1.hashCode()==e2.hashCode()</tt> for any two Groups
     * <tt>e1</tt> and <tt>e2</tt>, as required by the general
     * contract of <tt>Object.hashCode</tt>.
     *
     * @return the hash code value for this Group
     * <p/>
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    @Override
    int hashCode();
}