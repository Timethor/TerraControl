package com.timethor.terracontrol.core.util.dataGroups;

/**
 * @param <F>
 * @param <S>
 * @param <T>
 * <p/>
 * @author Timethor
 */
public class Entry3<F, S, T> extends Entry2<F, S> implements Triple<F, S, T> {

    T third;

    /**
     *
     * @param third
     * @param first
     * @param second
     */
    public Entry3(T third, F first, S second) {
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
