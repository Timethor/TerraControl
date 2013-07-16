package com.timethor.terracontrol.core.util.coordinate;

import com.timethor.terracontrol.core.util.MathHelper;

/**
 * Represents a third dimension coordinate (extending Coordinate2D by
 * adding the y coordinate)
 * <p/>
 * @author Timethor
 */
public class Coordinate3D extends Coordinate2D {

    /**
     * The third dimensional field
     */
    protected int y;

    /**
     * Protected Empty constructor for use only in this class and
     * inheritors
     */
    protected Coordinate3D() {
    }

    /**
     * Creation of a coordinate in 2D space represented in 3D
     * <p/>
     * @param x The x part of a new 3D point
     * @param z The z part of a new 3D point
     */
    protected Coordinate3D(int x, int z) {
        super(x, z);
        this.y = 0;
    }

    /**
     * Creation of a coordinate in 3D space
     * <p/>
     * @param x The x part of a new 3D point
     * @param y The y part of a new 3D point
     * @param z The z part of a new 3D point
     */
    protected Coordinate3D(int x, int y, int z) {
        super(x, z);
        this.y = y;
    }

    /**
     * Creation of a coordinate in 3D space by using an existing point in
     * 2D space
     * <p/>
     * @param other The 2D point you wish to use as a base for a new 3D
     *              point
     * @param y     The y part of a new 3D point
     */
    protected Coordinate3D(Coordinate2D other, int y) {
        super(other);
        this.y = y;
    }

    /**
     * Creation of a coordinate in 3D space based on another 3D point
     * <p/>
     * @param other
     */
    protected Coordinate3D(Coordinate3D other) {
        super(other);
        this.y = other.y;
    }

    /**
     * Returns a new 3D coordinate based on x and z components with a y=0
     * <p/>
     * @param x The x component of this coordinate
     * @param z The z component of this coordinate
     * <p/>
     * @return A new 3D coordinate
     */
    public static Coordinate3D make3D(int x, int z) {
        return new Coordinate3D(x, z);
    }

    /**
     * Returns a new 3D coordinate based on x, y, and z components
     * <p/>
     * @param x The x component of this coordinate
     * @param y The y component of this coordinate
     * @param z The z component of this coordinate
     * <p/>
     * @return A new 3D coordinate
     */
    public static Coordinate3D make3D(int x, int y, int z) {
        return new Coordinate3D(x, y, z);
    }

    /**
     * Returns a new 3D coordinate based on a 2D point and a y component
     * <p/>
     * @param other A 2D point containing the x and z components
     * @param y     The y component of this coordinate
     * <p/>
     * @return A new 3D coordinate
     */
    public static Coordinate3D make3D(Coordinate2D other, int y) {
        return new Coordinate3D(other, y);
    }

    /**
     * Returns a new 3D coordinate based on another 3D point
     * <p/>
     * @param other A 3D point containing the x, y, and z components
     * <p/>
     * @return A new 3D coordinate
     */
    public static Coordinate3D make3D(Coordinate3D other) {
        return new Coordinate3D(other);
    }

    //>>	END CONSTRUCTORS
    /**
     * Returns the vector magnitude of the 3D point
     * <p/>
     * @return The vector magnitude of the 3D point
     */
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    /**
     * Returns the vector magnitude of the 3D point squared
     * <p/>
     * @return The Vector magnitude of the 3D point squared
     */
    @Override
    public int magnitudeSqared() {
        return super.magnitudeSqared() + y * y;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise addition of one
     * or more 2D coordinates to a 3D coordinate
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D add(Coordinate3D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x += b0.x;
            a.z += b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise addition of two
     * or more 3D coordinates
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D add(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 += b0.x;
            y0 += b0.z;
            z0 += b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    /**
     * Returns a new 3D Coordinate from the component-wise subtraction of
     * one or more 2D coordinates from a 3D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D sub(Coordinate3D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x -= b0.x;
            a.z -= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise subtraction of
     * one or more 3D coordinates from a 3D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D sub(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.z;
            z0 -= b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    /**
     * Returns a new 3D Coordinate from the linear shifting (addition) of a
     * single 3D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate3D shift(Coordinate3D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 += b0;
        }
        return new Coordinate3D(a.x + b1, a.z + b1, a.y + b1);
    }

    /**
     * Returns a new 3D Coordinate from the component-wise multiplication
     * of one or more 2D coordinates from a 3D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D mult(Coordinate3D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x *= b0.x;
            a.z *= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise multiplication
     * of one or more 3D coordinates from a 3D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D mult(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.z;
            a.z *= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the linear multiplication of a
     * single 3D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate3D mult(Coordinate3D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 *= b0;
        }
        return new Coordinate3D(a.x * b1, a.z * b1, a.y * b1);
    }

    /**
     * Returns a new 3D Coordinate from the dot product or two 3D
     * coordinates
     * <p/>
     * @param a The first element in the dot product
     * @param b The second element in the dot product
     * <p/>
     * @return The dot product of a and b, that is; a dot b
     */
    public static double dot(Coordinate3D a, Coordinate3D b) {
        return Coordinate2D.dot(a, b) + a.y * b.y;
    }

    /**
     * Returns a new 3D Coordinate from the cross product or two 3D
     * coordinates
     * <p/>
     * @param a The first element in the cross product
     * @param b The second element in the cross product
     * <p/>
     * @return The cross product of a and b, that is; a cross b
     */
    public static Coordinate3D cross(Coordinate3D a, Coordinate3D b) {
        return new Coordinate3D(
            MathHelper.floor_int((a.z * b.y) - (a.y * b.z)),
            MathHelper.floor_int((a.y * b.x) - (a.x * b.y)),
            Coordinate2D.cross(a, b));
    }

    /**
     * Returns a new 3D Coordinate from the component-wise division of a 3D
     * coordinate by one or more 2D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D div(Coordinate3D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x /= b0.x;
            a.z /= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise division of a 3D
     * coordinate by one or more other 3D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Coordinate3D div(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.z;
            a.z /= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the linear division of a single 3D
     * coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for division
     * @param b A value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Coordinate3D div(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1), MathHelper.floor_int(a.y / b1));
    }

    /**
     * Returns the component-wise maximum 3D Coordinate from a group of
     * two or more. That is, given g group of 3D coordinates, the maximum x
     * will be found from g and be set to return.x, likewise with y and z;
     * giving a 3D coordinate comprising the highest value components found
     * from g
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to pull maximums from
     * <p/>
     * @return The component-wise maximum of two or more 3D coordinates
     */
    public static Coordinate3D componentMax(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.y = a.y > b0.y ? a.y : b0.y;
        }
        return a;
    }

    /**
     * Returns the component-wise minimum 3D Coordinate from a group of
     * two or more. That is, given g group of 3D coordinates, the minimum x
     * will be found from g and be set to return.x, likewise with y and z;
     * giving a 3D coordinate comprising the lowest value components found
     * from g
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to pull minimums from
     * <p/>
     * @return The component-wise minimum of two or more 3D coordinates
     */
    public static Coordinate3D componentMin(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.y = a.y < b0.y ? a.y : b0.y;
        }
        return a;
    }

    /**
     * Returns the magnitude-based minimum 3D Coordinate from a group of
     * two or more. That is, given g group of 3D coordinates, the magnitude
     * will be calculated for all g and the coordinate with the highest
     * magnitude will be returned
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to test against the base
     * <p/>
     * @return The magnitude-based minimum of two or more 3D coordinates
     */
    public static Coordinate3D min(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     * Returns the magnitude-based maximum 3D Coordinate from a group of
     * two or more. That is, given g group of 3D coordinates, the magnitude
     * will be calculated for all g and the coordinate with the highest
     * magnitude will be returned
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to test against the base
     * <p/>
     * @return The magnitude-based maximum of two or more 3D coordinates
     */
    public static Coordinate3D max(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     * Returns a 3D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses coordinates to achieve
     * <b>component-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate components
     * @param max The maximum coordinate components
     * <p/>
     * @return a value-clamped 3D coordinate whose component values do not
     *         fall outside of the <b>component-wise</b> bounds given
     */
    public static Coordinate3D clamp(Coordinate3D a, Coordinate3D min, Coordinate3D max) {
        a = (Coordinate3D) Coordinate2D.clamp(a, min, max);
        a.y = a.y < min.y ? min.y : (a.y > max.y ? max.y : a.y);
        return a;
    }

    /**
     * Returns a 3D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an integer min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 3D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Coordinate3D clamp(Coordinate3D a, int min, int max) {
        a = (Coordinate3D) Coordinate2D.clamp(a, min, max);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    /**
     * Returns a linearly interpolated 3D coordinate from two given points
     * and a blend factor
     * <p/>
     * @param a     The first point for interpolation
     * @param b     The second point for interpolation
     * @param blend A blending factor
     * <p/>
     * @return A new 3D coordinate from the linear interpolation of two
     *         points
     *         and a blend factor
     */
    public static Coordinate3D lerp(Coordinate3D a, Coordinate3D b, int blend) {
        a = (Coordinate3D) Coordinate2D.lerp(a, b, blend);
        a.y = MathHelper.floor_int(blend * (b.y - a.y) + a.y);
        return a;
    }

    /**
     * Returns a Barycentric interpolated 3D coordinate from three given
     * points and two blend factors
     * <p/>
     * @param a      The first point for interpolation
     * @param c
     * @param b      The second point for interpolation
     * @param blendx A blending factor
     * @param blendy A blending factor
     * <p/>
     * @return A new 3D coordinate from the Barycentric interpolated
     *         coordinate from three given points and two blend factors
     */
    public static Coordinate3D baryCentric(Coordinate3D a, Coordinate3D b, Coordinate3D c, int blendx, int blendy) {
        //>>	a+x * (b-a) + v * (c-a)
        return Coordinate3D.add(Coordinate3D.mult(Coordinate3D.shift(a, blendx), Coordinate3D.sub(b, a)), Coordinate3D.mult(Coordinate3D.sub(c, a), blendy));
    }

    /**
     * Returns the double angle between two points
     * <p/>
     * @param a The first coordinate
     * @param b The second coordinate
     * <p/>
     * @return The angle between a and b represented in a double
     */
    public static double angle(Coordinate3D a, Coordinate3D b) {
        return Math.acos(Coordinate3D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Coordinate3D) {
            final Coordinate3D other = (Coordinate3D) obj;
            if (this.y != other.y) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the Y component of this coordinate
     * <p/>
     * @return The y component of this coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y component of this coordinate
     * <p/>
     * @param y The new y component of this coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
