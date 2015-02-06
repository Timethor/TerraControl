package com.timethor.terracontrol.core.custom.noise;

import java.util.Random;

/**
 * Creates perlin noise through unbiased octaves
 */
public class B_PerlinOctaveGenerator extends B_OctaveGenerator {

    /**
     * Creates a perlin octave generator for the given world
     *
     * @param seed    Seed to construct this generator for
     * @param octaves Amount of octaves to create
     */
    public B_PerlinOctaveGenerator(long seed, int octaves) {
        this(new Random(seed), octaves);
    }

    /**
     * Creates a perlin octave generator for the given {@link Random}
     *
     * @param rand    Random object to construct this generator for
     * @param octaves Amount of octaves to create
     */
    public B_PerlinOctaveGenerator(Random rand, int octaves) {
        super(createOctaves(rand, octaves));
    }

    private static B_NoiseGenerator[] createOctaves(Random rand, int octaves) {
        B_NoiseGenerator[] result = new B_NoiseGenerator[octaves];

        for (int i = 0; i < octaves; i++) {
            result[i] = new B_PerlinNoiseGenerator(rand);
        }

        return result;
    }
}