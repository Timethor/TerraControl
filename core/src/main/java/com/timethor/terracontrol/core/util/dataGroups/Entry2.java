package com.timethor.terracontrol.core.util.dataGroups;

/**
 * @param <F> 
 * @param <S> 
 * @author Timethor
 */
public class Entry2<F, S> implements Pair<F, S> {

    F first;
    S second;

    /**
     *
     * @param first
     * @param second
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
