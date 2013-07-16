package com.timethor.terracontrol.core.util.dataGroups;

/**
 * Holder Class for use as Second Type in Maps
 * <p/>
 * @param <F> The class of the first object this entry should hold
 * @param <S> The class of the second object this entry should hold
 * <p/>
 * @author Timethor
 */
public class Entry2<F, S> implements Pair<F, S> {

    F first;
    S second;

    /**
     * Creation of a new Entry2 object
     * <p/>
     * @param first  The first component of the entry
     * @param second The second component of the entry
     */
    public Entry2(F first, S second) {
        this.first = first;
        this.second = second;
        super.hashCode();
    }

    @Override
    public F getFirstValue() {
        return first;
    }

    @Override
    public S getSecondValue() {
        return second;
    }

    @Override
    public F setFirstValue(F newFirst) {
        F oldFirst = this.first;
        this.first = newFirst;
        return oldFirst;
    }

    @Override
    public S setSecondValue(S newSecond) {
        S oldSecond = this.second;
        this.second = newSecond;
        return oldSecond;
    }
}
