package com.timethor.terracontrol.core.util.vector;

import com.timethor.terracontrol.core.util.MathHelper;

/**
 *
 * @author Timethor
 */
public class Vector4D extends Vector3D {

    /**
     *
     */
    protected double w;

    /**
     *
     */
    public Vector4D() {
    }

    /**
     * Creation of a coordinate in 2D space represented in 4D
     * <p/>
     * @param x The x part of a new 4D point
     * @param y The y part of a new 4D point
     */
    public Vector4D(double x, double y) {
        super(x, y);
        this.w = 0;
    }

    /**
     * Creation of a coordinate in 3D space represented in 4D
     * <p/>
     * @param x The x part of a new 4D point
     * @param y The y part of a new 4D point
     * @param z The z part of a new 4D point
     */
    public Vector4D(double x, double y, double z) {
        super(x, y, z);
        this.w = 0;
    }

    /**
     * Creation of a coordinate in 4D space
     * <p/>
     * @param x The x part of a new 4D point
     * @param y The y part of a new 4D point
     * @param z The z part of a new 4D point
     * @param w The w part of a new 4D point
     */
    public Vector4D(double x, double y, double z, double w) {
        super(x, y, z);
        this.w = w;
    }

    /**
     * Creation of a coordinate in 2D space by using an existing point in
     * 4D space
     * <p/>
     * @param other The 2D point you wish to use as a base for a new 4D
     *              point
     * @param z     The z part of a new 4D point
     * @param w     The w part of a new 4D point
     */
    public Vector4D(Vector2D other, double z, double w) {
        super(other, z);
        this.w = w;
    }

    /**
     * Creation of a coordinate in 4D space by using an existing point in
     * 3D space
     * <p/>
     * @param other The 3D point you wish to use as a base for a new 4D
     *              point
     * @param z     The z part of a new 4D point
     */
    public Vector4D(Vector3D other, double w) {
        super(other);
        this.w = w;
    }

    /**
     * Creation of a coordinate in 4D space based on another 4D point
     * <p/>
     * @param other
     */
    public Vector4D(Vector4D other) {
        super(other);
        this.w = other.w;
    }

    //>>	END CONSTRUCTORS
    /**
     * Returns the vector magnitude of the 4D point
     * <p/>
     * @return The vector magnitude of the 4D point
     */
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    /**
     * Returns the vector magnitude of the 4D point squared
     * <p/>
     * @return The Vector magnitude of the 4D point squared
     */
    @Override
    public double magnitudeSqared() {
        return super.magnitudeSqared() + w * w;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise addition of
     * given x,y,z,w components
     * <p/>
     * @param a The base coordinate for addition
     * @param x The value to add to the x component of a
     * @param y The value to add to the y component of a
     * @param z The value to add to the z component of a
     * @param w The value to add to the w component of a
     * <p/>
     * @return The component-wise addend of a, and x,y,z,w ...
     */
    public static Vector4D add(Vector4D a, double x, double y, double z, double w) {
        a.x += x;
        a.y += y;
        a.z += z;
        a.w += w;
        return a;
    }

    /**
     * Returns a new 3D Coordinate from the component-wise addition of one
     * or more 2D coordinates to a 4D coordinate
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D add(Vector4D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x += b0.x;
            a.y += b0.y;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise addition of one
     * or more 3D coordinates to a 4D coordinate
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D add(Vector4D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x += b0.x;
            a.y += b0.y;
            a.z += b0.z;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise addition of two
     * or more 4D coordinates
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D add(Vector4D a, Vector4D... b) {
        for (Vector4D b0 : b) {
            a.x += b0.x;
            a.y += b0.y;
            a.z += b0.z;
            a.w += b0.w;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise addition of
     * given x,y,z,w components
     * <p/>
     * @param a The base coordinate for addition
     * @param x The value to add to the x component of a
     * @param y The value to add to the y component of a
     * @param z The value to add to the z component of a
     * @param w The value to add to the w component of a
     * <p/>
     * @return The component-wise addend of a, and x,y,z,w ...
     */
    public static Vector4D sub(Vector4D a, double x, double y, double z, double w) {
        a.x -= x;
        a.y -= y;
        a.z -= z;
        a.w -= w;
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise subtraction of
     * one or more 2D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D sub(Vector4D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise subtraction of
     * one or more 3D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D sub(Vector4D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
            a.z -= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise subtraction of
     * one or more 4D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D sub(Vector4D a, Vector4D... b) {
        for (Vector4D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
            a.z -= b0.z;
            a.w -= b0.w;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the linear shifting (addition) of a
     * single 4D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A double value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D shift(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear shifting (addition) of a
     * single 4D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A float value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D shfit(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear shifting (addition) of a
     * single 4D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A long value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D shift(Vector4D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear shifting (addition) of a
     * single 4D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b An integer value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D shift(Vector4D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    /**
     * Returns a new 4D Coordinate from the component-wise multiplication
     * of one or more 2D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise multiplication
     * of one or more 3D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
            a.z *= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise multiplication
     * of one or more 4D coordinates from a 4D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, Vector4D... b) {
        for (Vector4D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
            a.z *= b0.z;
            a.w *= b0.w;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the linear multiplication of a
     * single 4D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A double value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear multiplication of a
     * single 4D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A float value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear multiplication of a
     * single 4D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A long value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear multiplication of a
     * single 4D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b An integer value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D mult(Vector4D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    /**
     * Returns a new 4D Coordinate from the dot product or two 4D
     * coordinates
     * <p/>
     * @param a The first element in the dot product
     * @param b The second element in the dot product
     * <p/>
     * @return The dot product of a and b, that is; a dot b
     */
    public static double dot(Vector4D a, Vector4D b) {
        return Vector3D.dot(a, b) + a.w * b.w;
    }

    /**
     * Returns a new 4D Coordinate from the cross product of three 4D
     * coordinates
     * <p/>
     * @param a The first element in the cross product
     * @param b The second element in the cross product
     * @param c The third element in the cross product
     * <p/>
     * @return The cross product of a, b, and c, that is; a cross b cross c
     */
    public static Vector4D cross(Vector4D a, Vector4D b, Vector4D c) {
        return new Vector4D(
            MathHelper.abs(a.x * (b.z * c.w - c.z * b.w) - a.z * (b.y * c.w - c.y * b.w) + a.w * (b.y * c.z - c.y * b.z)),
            MathHelper.abs(-a.x * (b.z * c.w - c.z * b.w) + a.z * (b.x * c.w - c.x * b.w) - a.w * (b.x * c.z - c.x * b.z)),
            MathHelper.abs(a.x * (b.y * c.w - c.y * b.w) - a.y * (b.x * c.w - c.x * b.w) + a.w * (b.x * c.y - c.x * b.y)),
            MathHelper.abs(-a.x * (b.y * c.z - c.y * b.z) + a.y * (b.x * c.z - c.x * b.z) - a.z * (b.x * c.y - c.x * b.y)));
    }

    /**
     * Returns a new 4D Coordinate from the component-wise division of a 4D
     * coordinate by one or more 2D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise division of a 34
     * coordinate by one or more 3D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
            a.z /= b0.z;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the component-wise division of a 4D
     * coordinate by one or more other 4D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, Vector4D... b) {
        for (Vector4D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
            a.z /= b0.z;
            a.w /= b0.w;
        }
        return a;
    }

    /**
     * Returns a new 4D Coordinate from the linear division of a single 4D
     * coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for division
     * @param b A double value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear division of a single 4D
     * coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for division
     * @param b A float value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear division of a single 4D
     * coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for division
     * @param b A long value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
    }

    /**
     * Returns a new 4D Coordinate from the linear division of a single 4D
     * coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for division
     * @param b An integer value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector4D div(Vector4D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
    }

    /**
     * Returns a 4D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses coordinates to achieve
     * <b>component-wise</b> clamping
     * <p/>
     * @param a   The base coordinate1
     * @param min The minimum coordinate components
     * @param max The maximum coordinate components
     * <p/>
     * @return a value-clamped 4D coordinate whose component values do not
     *         fall outside of the <b>component-wise</b> bounds given
     */
    public static Vector4D clamp(Vector4D a, Vector4D min, Vector4D max) {
        a = (Vector4D) Vector4D.clamp(a, min, max);
        a.w = a.w < min.w ? min.w : (a.w > max.w ? max.w : a.w);
        return a;
    }

    /**
     * Returns a 4D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an double min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 4D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector4D clamp(Vector4D a, double min, double max) {
        a = (Vector4D) Vector4D.clamp(a, min, max);
        a.w = a.w < min ? min : (a.w > max ? max : a.w);
        return a;
    }

    /**
     * Returns a 4D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an float min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 4D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector4D clamp(Vector4D a, float min, float max) {
        a = (Vector4D) Vector4D.clamp(a, min, max);
        a.w = a.w < min ? min : (a.w > max ? max : a.w);
        return a;
    }

    /**
     * Returns a 4D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an long min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 4D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector4D clamp(Vector4D a, long min, long max) {
        a = (Vector4D) Vector4D.clamp(a, min, max);
        a.w = a.w < min ? min : (a.w > max ? max : a.w);
        return a;
    }

    /**
     * Returns a 4D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an integer min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 4D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector4D clamp(Vector4D a, int min, int max) {
        a = (Vector4D) Vector4D.clamp(a, min, max);
        a.w = a.w < min ? min : (a.w > max ? max : a.w);
        return a;
    }

    /**
     * Returns a 4D Coordinate with normalized component values
     * <p/>
     * @param a The 4D Coordinate to normalize
     * <p/>
     * @return A 4D Coordinate with normalized component values
     */
    public static Vector4D normalize(Vector4D a) {
        double scale = 1.0f / a.magnitude();
        return new Vector4D(a.x * scale, a.y * scale, a.z * scale, a.w * scale);
    }

    /**
     * Returns a linearly interpolated 4D coordinate from two given points
     * and a blend factor
     * <p/>
     * @param a     The first point for interpolation
     * @param b     The second point for interpolation
     * @param blend A blending factor
     * <p/>
     * @return A new 4D coordinate from the linear interpolation of two
     *         points
     *         and a blend factor
     */
    public static Vector4D lerp(Vector4D a, Vector4D b, double blend) {
        a = (Vector4D) Vector3D.lerp(a, b, blend);
        a.w = blend * (b.w - a.w) + a.w;
        return a;
    }

    /**
     * Returns a Barycentric interpolated 4D coordinate from three given
     * points and two blend factors
     * <p/>
     * @param a      The first point for interpolation
     * @param c
     * @param b      The second point for interpolation
     * @param blendx A blending factor
     * @param blendy A blending factor
     * <p/>
     * @return A new 4D coordinate from the Barycentric interpolated
     *         coordinate from three given points and two blend factors
     */
    public static Vector4D baryCentric(Vector4D a, Vector4D b, Vector4D c, double x, double y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Vector4D.add(Vector4D.mult(Vector4D.shift(a, x), Vector4D.sub(b, a)), Vector4D.mult(Vector4D.sub(c, a), y));
    }

    /**
     * Returns the double angle between two points
     * <p/>
     * @param a The first coordinate
     * @param b The second coordinate
     * <p/>
     * @return The angle between a and b represented in a double
     */
    public static double angle(Vector4D a, Vector4D b) {
        return Math.acos(Vector4D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Vector4D) {
            final Vector4D other = (Vector4D) obj;
            if (Double.doubleToLongBits(this.w) != Double.doubleToLongBits(other.w)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the W component of this coordinate
     * <p/>
     * @return The W component of this coordinate
     */
    public double getW() {
        return w;
    }
}
