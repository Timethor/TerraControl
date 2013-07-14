package com.timethor.terracontrol.core.util.coordinate;

import com.timethor.terracontrol.core.util.MathHelper;

/**
 * Represents a second dimension coordinate
 * <p/>
 * @author Timethor
 */
public class Coordinate2D {

    /**
     * The first dimensional field
     */
    protected int x;
    /**
     * The second dimensional field
     */
    protected int z;

    /**
     * Protected Empty constructor for use only in this class and
     * inheritors
     */
    protected Coordinate2D() {
    }

    /**
     * Creation of a coordinate in 2D space
     * <p/>
     * @param x The x part of a new 2D point
     * @param z The z part of a new 2D point
     */
    protected Coordinate2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

    /**
     * Creation of a coordinate in 2D space based on another 2D point
     * <p/>
     * @param other
     */
    protected Coordinate2D(Coordinate2D other) {
        this.x = other.x;
        this.z = other.z;
    }

    /**
     * Returns a new 2D coordinate based on a given x and z component
     * <p/>
     * @param x The x component of this coordinate
     * @param z The z component of this coordinate
     * <p/>
     * @return A new 2D coordinate
     */
    public static Coordinate2D make2d(int x, int z) {
        return new Coordinate2D(x, z);
    }

    /**
     * Returns a new 2D coordinate based on another 2d Coordinate
     * <p/>
     * @param other The base of the new coordinate
     * <p/>
     * @return A new 2D coordinate
     */
    public static Coordinate2D make2d(Coordinate2D other) {
        return new Coordinate2D(other);
    }

    //>>	END CONSTRUCTORS
    /**
     * Returns the vector magnitude of the 3D point
     * <p/>
     * @return The vector magnitude of the 3D point
     */
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    /**
     * Returns the vector magnitude of the 3D point
     * <p/>
     * @return The Vector magnitude of the 3D point squared
     */
    public int magnitudeSqared() {
        return x * x + z * z;
    }

    /**
     * Returns a new 2D Coordinate from the component-wise addition of two
     * or more 2D coordinates
     * <p/>
     * @param a          The base coordinate for addition
     * @param b          The second coordinate for addition
     * @param ... Additional coordinates for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate2D add(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x += b0.x;
            a.z += b0.z;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the component-wise subtraction of
     * one or more 2D coordinates from a 2D coordinate
     * <p/>
     * @param a          The base coordinate for subtraction
     * @param b          The second coordinate for subtraction
     * @param ... Additional coordinates for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate2D sub(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x -= b0.x;
            a.z -= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear shifting (addition) of a
     * single 2D coordinate by one or more integer values
     * <p/>
     * @param a          The base coordinate for shifting
     * @param b          A value to shift by
     * @param ... Additional values to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate2D shift(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 += b0;
        }
        return new Coordinate2D(a.x + b1, a.z + b1);
    }

    /**
     * Returns a new 2D Coordinate from the component-wise multiplication
     * of one or more 2D coordinates from a 3D coordinate
     * <p/>
     * @param a          The base coordinate for multiplication
     * @param b          The second coordinate for multiplication
     * @param ... Additional coordinates for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate2D mult(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x *= b0.x;
            a.z *= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear multiplication of a
     * single 2D coordinate by one or more integer values
     * <p/>
     * @param a          The base coordinate for multiplication
     * @param b          A value to multiply by
     * @param ... Additional values to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate2D mult(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 *= b0;
        }
        return new Coordinate2D(a.x * b1, a.z * b1);
    }

    /**
     * Returns a new 2D Coordinate from the dot product or two 2D
     * coordinates
     * <p/>
     * @param a The first element in the dot product
     * @param b The second element in the dot product
     * <p/>
     * @return The dot product of a and b, that is; a dot b
     */
    public static int dot(Coordinate2D a, Coordinate2D b) {
        return a.x * b.x + a.z * b.z;
    }

    /**
     * Returns a new 2D Coordinate from the cross product or two 2D
     * coordinates
     * <p/>
     * @param a The first element in the cross product
     * @param b The second element in the cross product
     * <p/>
     * @return The cross product of a and b, that is; a cross b
     */
    public static int cross(Coordinate2D a, Coordinate2D b) {
        return (a.x * b.z) - (a.z * b.x);
    }

    /**
     * Returns a new 2D Coordinate from the component-wise division of a 2D
     * coordinate by one or more other 2D coordinates
     * <p/>
     * @param a          The base coordinate for division
     * @param b          The second coordinate for division
     * @param ... Additional coordinates for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate2D div(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x /= b0.x;
            a.z /= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear division of a single 2D
     * coordinate by one or more integer values
     * <p/>
     * @param a          The base coordinate for division
     * @param b          A value to divide by
     * @param ... Additional values to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate2D div(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 /= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1));
    }

    /**
     * Returns the component-wise maximum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the maximum x
     * will be found from g and be set to return.x, likewise with z;
     * giving a 2D coordinate comprising the highest value components found
     * from g
     * <p/>
     * @param a          The base coordinate
     * @param b          A coordinate to pull maximums from
     * @param ... Additional coordinates to pull maximums from
     * <p/>
     * @return The component-wise maximum of two or more 2D coordinates
     */
    public static Coordinate2D componentMax(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x = a.x > b0.x ? a.x : b0.x;
            a.z = a.z > b0.z ? a.z : b0.z;
        }
        return a;
    }

    /**
     * Returns the component-wise minimum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the minimum x
     * will be found from g and be set to return.x, likewise with z;
     * giving a 2D coordinate comprising the lowest value components found
     * from g
     * <p/>
     * @param a          The base coordinate
     * @param b          A coordinate to pull minimums from
     * @param ... Additional coordinates to pull minimums from
     * <p/>
     * @return The component-wise minimum of two or more 2D coordinates
     */
    public static Coordinate2D componentMin(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x = a.x < b0.x ? a.x : b0.x;
            a.z = a.z < b0.z ? a.z : b0.z;
        }
        return a;
    }

    /**
     * Returns the magnitude-based minimum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the magnitude
     * will be calculated for all g and the coordinate with the highest
     * magnitude will be returned
     * <p/>
     * @param a          The base coordinate
     * @param b          A coordinate to test against the base
     * @param ... Additional coordinates to test against the base
     * <p/>
     * @return The magnitude-based minimum of two or more 2D coordinates
     */
    public static Coordinate2D min(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     * Returns the magnitude-based maximum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the magnitude
     * will be calculated for all g and the coordinate with the highest
     * magnitude will be returned
     * <p/>
     * @param a          The base coordinate
     * @param b          A coordinate to test against the base
     * @param ... Additional coordinates to test against the base
     * <p/>
     * @return The magnitude-based maximum of two or more 2D coordinates
     */
    public static Coordinate2D max(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     * Returns a 2D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses coordinates to achieve
     * <b>component-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate components
     * @param max The maximum coordinate components
     * <p/>
     * @return a value-clamped 2D coordinate whose component values do not
     *         fall outside of the <b>component-wise</b> bounds given
     */
    public static Coordinate2D clamp(Coordinate2D a, Coordinate2D min, Coordinate2D max) {
        a.x = a.x < min.x ? min.x : (a.x > max.x ? max.x : a.x);
        a.z = a.z < min.z ? min.z : (a.z > max.z ? max.z : a.z);
        return a;
    }

    /**
     * Returns a 2D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an integer min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 2D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Coordinate2D clamp(Coordinate2D a, int min, int max) {
        a.x = MathHelper.floor_int(a.x < min ? min : (a.x > max ? max : a.x));
        a.z = MathHelper.floor_int(a.z < min ? min : (a.z > max ? max : a.z));
        return a;
    }

    /**
     * Returns a linearly interpolated 2D coordinate from two given points
     * and a blend factor
     * <p/>
     * @param a     The first point for interpolation
     * @param b     The second point for interpolation
     * @param blend A blending factor
     * <p/>
     * @return A new 2D coordinate from the linear interpolation of two
     *         points
     *         and a blend factor
     */
    public static Coordinate2D lerp(Coordinate2D a, Coordinate2D b, int blend) {
        a.x = blend * (b.x - a.x) + a.x;
        a.z = blend * (b.z - a.z) + a.z;
        return a;
    }

    /**
     * Returns a Barycentric interpolated 2D coordinate from three given
     * points and two blend factors
     * <p/>
     * @param a      The first point for interpolation
     * @param b      The second point for interpolation
     * @param b      The second point for interpolation
     * @param blendx A blending factor
     * @param blendy A blending factor
     * <p/>
     * @return A new 2D coordinate from the Barycentric interpolated
     *         coordinate from three given points and two blend factors
     */
    public static Coordinate2D baryCentric(Coordinate2D a, Coordinate2D b, Coordinate2D c, int blendx, int blendy) {
        //>>	a+x * (b-a) + v * (c-a)
        return Coordinate2D.add(Coordinate2D.mult(Coordinate2D.shift(a, blendx), Coordinate2D.sub(b, a)), Coordinate2D.mult(Coordinate2D.sub(c, a), blendy));
    }

    /**
     * Returns the double angle between two points
     * <p/>
     * @param a The first coordinate
     * @param b The second coordinate
     * <p/>
     * @return The angle between a and b represented in a double
     */
    public static double angle(Coordinate2D a, Coordinate2D b) {
        return Math.acos(Coordinate2D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.z;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Coordinate2D) {
            final Coordinate2D other = (Coordinate2D) obj;
            if (this.x != other.x || this.z != other.z) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the X component of this coordinate
     * <p/>
     * @return The X component of this coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Z component of this coordinate
     * <p/>
     * @return The Z component of this coordinate
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets the X component of this coordinate
     * <p/>
     * @param y The new X component of this coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Z component of this coordinate
     * <p/>
     * @param y The new Z component of this coordinate
     */
    public void setZ(int z) {
        this.z = z;
    }
}
