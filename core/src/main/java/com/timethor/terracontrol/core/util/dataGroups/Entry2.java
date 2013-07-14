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
public class Entry2<F, S> implements Pair<F, S> {

    F first;
    S second;

    public Entry2(F first, S second) {
        this.first = first;
        this.second = second;
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
