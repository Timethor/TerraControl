package com.timethor.terracontrol.core.util;

import com.timethor.terracontrol.core.util.coordinate.Coordinate2D;
import com.timethor.terracontrol.core.util.coordinate.Coordinate3D;
import java.util.Random;

/**
 * Class to get a random generator which is constant for the given input.
 *
 */
public class RandomHelper {

    /**
     * Gets a Random generator with a random seed. However, the same input
     * will always produce the same output.
     * <p/>
     * @param x    X-coord to start with.
     * @param z    Z-coord to start with.
     * @param seed Seed to start with.
     * <p/>
     * @return A random generator with a random seed.
     */
    public static Random getRandomForCoords(int x, int z, long seed) {
        Random random = new Random();
        random.setSeed(seed);
        long l1 = random.nextLong() + 1L;
        long l2 = random.nextLong() + 1L;
        random.setSeed(x * l1 + z * l2 ^ seed);
        return random;
    }

    /**
     * Gets a Random generator with a random seed. However, the same input
     * will always produce the same output.
     * <p/>
     * @param i2d 
     * @param seed Seed to start with.
     * <p/>
     * @return A random generator with a random seed.
     */
    public static Random getRandomForCoords(Coordinate2D i2d, long seed) {
        Random random = new Random();
        random.setSeed(seed);
        long l1 = random.nextLong() + 1L;
        long l2 = random.nextLong() + 1L;
        random.setSeed(i2d.getX() * l1 + i2d.getZ() * l2 ^ seed);
        return random;
    }

    /**
     * Gets a Random generator with a random seed. However, the same input
     * will always produce the same output.
     * <p/>
     * @param x    X-coord to start with.
     * @param y    Y-coord to start with.
     * @param z    Z-coord to start with.
     * @param seed Seed to start with.
     * <p/>
     * @return A random generator with a random seed.
     */
    public static Random getRandomForCoords(int x, int y, int z, long seed) {
        Random random = getRandomForCoords(x, z, seed);
        random.setSeed(random.nextInt() * y);
        return random;
    }

    /**
     * Gets a Random generator with a random seed. However, the same input
     * will always produce the same output.
     * <p/>
     * @param i3d 
     * @param seed Seed to start with.
     * <p/>
     * @return A random generator with a random seed.
     */
    public static Random getRandomForCoords(Coordinate3D i3d, long seed) {
        Random random = getRandomForCoords(i3d.getX(), i3d.getZ(), seed);
        random.setSeed(random.nextInt() * i3d.getY());
        return random;
    }

    /**
     * Returns a random number between min and max, inclusive.
     * <p/>
     * @param random The random number generator.
     * @param min    The minimum value.
     * @param max    The maximum value.
     * <p/>
     * @return A random number between min and max, inclusive.
     */
    public static int getRandomNumberInRange(Random random, int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
