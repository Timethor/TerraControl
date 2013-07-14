/*
 * Copyright (C) 2013 Timethor
 *
 * This program is NOT free software; you can NOT redistribute it and/or
 * modify it without explicit permission from the owner of the codebase.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package com.timethor.terracontrol.core.util.dataGroups;

/**
 * @author Timethor
 */
public class Entry3<F, S, T> extends Entry2<F, S> implements Triple<F, S, T> {

    T third;

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
