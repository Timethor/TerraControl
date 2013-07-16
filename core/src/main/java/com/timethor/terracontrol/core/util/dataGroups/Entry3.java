package com.timethor.terracontrol.core.util.dataGroups;

/**
 * Holder Class for use as Second Type in Maps
 * <p/>
 * @param <F> The class of the first object this entry should hold
 * @param <S> The class of the second object this entry should hold
 * @param <T> The class of the third object this entry should hold
 * <p/>
 * @author Timethor
 */
public class Entry3<F, S, T> extends Entry2<F, S> implements Triple<F, S, T> {

    T third;

    /**
     * Creation of a new Entry3 object
     * <p/>
     * @param first  The first component of the entry
     * @param second The second component of the entry
     * @param third  The third component of the entry
     */
    public Entry3(F first, S second, T third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public T getThirdValue() {
        return third;
    }

    @Override
    public T setThirdValue(T newThird) {
        T oldThird = this.third;
        this.third = newThird;
        return oldThird;
    }
}
