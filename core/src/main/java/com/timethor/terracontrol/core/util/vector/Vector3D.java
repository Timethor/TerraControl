package com.timethor.terracontrol.core.util.vector;

/**
 *
 * @author Timethor
 */
public class Vector3D extends Vector2D {

    /**
     * The third dimensional field
     */
    protected double z;

    /**
     * Protected Empty constructor for use only in this class and
     * inheritors
     */
    protected Vector3D() {
    }

    /**
     * Creation of a coordinate in 2D space represented in 3D
     * <p/>
     * @param x The x part of a new 3D point
     * @param y The y part of a new 3D point
     */
    public Vector3D(double x, double y) {
        super(x, y);
        this.z = 0;
    }

    /**
     * Creation of a coordinate in 3D space
     * <p/>
     * @param x The x part of a new 3D point
     * @param y The y part of a new 3D point
     * @param z The z part of a new 3D point
     */
    public Vector3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    /**
     * Creation of a coordinate in 3D space by using an existing point in
     * 2D space
     * <p/>
     * @param other The 2D point you wish to use as a base for a new 3D
     *              point
     * @param z     The z part of a new 3D point
     */
    public Vector3D(Vector2D other, double z) {
        super(other);
        this.z = z;
    }

    /**
     * Creation of a coordinate in 3D space based on another 3D point
     * <p/>
     * @param other
     */
    public Vector3D(Vector3D other) {
        super(other);
        this.z = other.z;
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
     * Returns the vector magnitude of the 3D point
     * <p/>
     * @return The Vector magnitude of the 3D point squared
     */
    @Override
    public double magnitudeSqared() {
        return super.magnitudeSqared() + z * z;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise addition of
     * given x,y,z components
     * <p/>
     * @param a The base coordinate for addition
     * @param x The value to add to the x component of a
     * @param y The value to add to the y component of a
     * @param z The value to add to the z component of a
     * <p/>
     * @return The component-wise addend of a, and x,y,z ...
     */
    public static Vector3D add(Vector3D a, double x, double y, double z) {
        a.x += x;
        a.y += y;
        a.z += z;
        return a;
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
    public static Vector3D add(Vector3D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
        }
        return new Vector3D(x0, y0, a.z);
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
    public static Vector3D add(Vector3D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
            z0 += b0.z;
        }
        return new Vector3D(x0, y0, z0);
    }

    /**
     * Returns a new 3D Coordinate from the component-wise addition of
     * given x,y,z components
     * <p/>
     * @param a The base coordinate for addition
     * @param x The value to add to the x component of a
     * @param y The value to add to the y component of a
     * @param z The value to add to the z component of a
     * <p/>
     * @return The component-wise addend of a, and x,y,z ...
     */
    public static Vector3D sub(Vector3D a, double x, double y, double z) {
        a.x -= x;
        a.y -= y;
        a.z -= z;
        return a;
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
    public static Vector3D sub(Vector3D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
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
    public static Vector3D sub(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
            a.z -= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the linear shifting (addition) of a
     * single 3D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A double value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D shift(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear shifting (addition) of a
     * single 3D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A float value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D shift(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear shifting (addition) of a
     * single 3D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A long value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D shift(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear shifting (addition) of a
     * single 3D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b An integer value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D shift(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
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
    public static Vector3D mult(Vector3D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
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
    public static Vector3D mult(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
            a.z *= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the linear multiplication of a
     * single 3D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A double value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D mult(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear multiplication of a
     * single 3D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A float value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D mult(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear multiplication of a
     * single 3D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A long value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D mult(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear multiplication of a
     * single 3D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b An integer value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D mult(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
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
    public static double dot(Vector3D a, Vector3D b) {
        return Vector2D.dot(a, b) + a.z * b.z;
    }

    /**
     * Returns a new 3D Coordinate from the cross product of two 3D
     * coordinates
     * <p/>
     * @param a The first element in the cross product
     * @param b The second element in the cross product
     * <p/>
     * @return The cross product of a and b, that is; a cross b
     */
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(
            (a.y * b.z) - (a.z * b.y),
            (a.z * b.x) - (a.x * b.z),
            Vector2D.cross(a, b));
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
    public static Vector3D div(Vector3D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
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
    public static Vector3D div(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
            a.z /= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the linear division of a single 3D
     * coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for division
     * @param b A double value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D div(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear division of a single 3D
     * coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for division
     * @param b A float value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D div(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear division of a single 3D
     * coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for division
     * @param b A long value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D div(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     * Returns a new 3D Coordinate from the linear division of a single 3D
     * coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for division
     * @param b An integer value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector3D div(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
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
    public static Vector3D componentMax(Vector3D a, Vector3D... b) {
        a = (Vector3D) Vector2D.componentMax(a, b);
        for (Vector3D b0 : b) {
            a.z = a.z > b0.z ? a.z : b0.z;
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
    public static Vector3D componentMin(Vector3D a, Vector3D... b) {
        a = (Vector3D) Vector2D.componentMax(a, b);
        for (Vector3D b0 : b) {
            a.z = a.z < b0.z ? a.z : b0.z;
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
    public static Vector3D min(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
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
    public static Vector3D max(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
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
    public static Vector3D clamp(Vector3D a, Vector3D min, Vector3D max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min.z ? min.z : (a.z > max.z ? max.z : a.z);
        return a;
    }

    /**
     * Returns a 3D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an double min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 3D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector3D clamp(Vector3D a, double min, double max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     * Returns a 3D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an float min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 3D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector3D clamp(Vector3D a, float min, float max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     * Returns a 3D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an long min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 3D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector3D clamp(Vector3D a, long min, long max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
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
    public static Vector3D clamp(Vector3D a, int min, int max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     * Returns a 3D Coordinate with normalized component values
     * <p/>
     * @param a The 3D Coordinate to normalize
     * <p/>
     * @return A 3D Coordinate with normalized component values
     */
    public static Vector3D normalize(Vector3D a) {
        double scale = 1.0f / a.magnitude();
        return new Vector3D(a.x * scale, a.y * scale, a.z * scale);
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
    public static Vector3D lerp(Vector3D a, Vector3D b, double blend) {
        a = (Vector3D) Vector2D.lerp(a, b, blend);
        a.z = blend * (b.z - a.z) + a.z;
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
    public static Vector3D baryCentric(Vector3D a, Vector3D b, Vector3D c, double x, double y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Vector3D.add(Vector3D.mult(Vector3D.shift(a, x), Vector3D.sub(b, a)), Vector3D.mult(Vector3D.sub(c, a), y));
    }

    /**
     * Returns the double angle between two points
     * <p/>
     * @param a The first coordinate
     * @param b The second coordinate
     * <p/>
     * @return The angle between a and b represented in a double
     */
    public static double angle(Vector3D a, Vector3D b) {
        return Math.acos(Vector3D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Vector3D) {
            final Vector3D other = (Vector3D) obj;
            if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the z component of this coordinate
     * <p/>
     * @return The z component of this coordinate
     */
    public double getZ() {
        return z;
    }
}
