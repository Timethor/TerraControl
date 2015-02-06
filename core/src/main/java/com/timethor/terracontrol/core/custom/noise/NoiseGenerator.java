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
package com.timethor.terracontrol.core.custom.noise;

/**
 *
 * @author Timethor
 */
interface NoiseGenerator {

    public void noise(double[] ad, double[] xyz, double[] ijk, double[] dxyzd, double dscale);
}
