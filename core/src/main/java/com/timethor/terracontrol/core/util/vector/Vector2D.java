package com.timethor.terracontrol.core.util.vector;

/**
 *
 * @author Timethor
 */
public class Vector2D {

    /**
     * The first dimensional field
     */
    protected double x;
    /**
     * The second dimensional field
     */
    protected double y;

    /**
     * Protected Empty constructor for use only in this class and
     * inheritors
     */
    protected Vector2D() {
    }

    /**
     * Creation of a coordinate in 2D space
     * <p/>
     * @param x The x part of a new 2D point
     * @param y The y part of a new 2D point
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creation of a coordinate in 2D space based on another 2D point
     * <p/>
     * @param other
     */
    public Vector2D(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
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
     * Returns the vector magnitude of the 3D point squared
     * <p/>
     * @return The Vector magnitude of the 3D point squared
     */
    public double magnitudeSqared() {
        return x * x + y * y;
    }

    /**
     * Returns a new 2D Coordinate from the component-wise addition of two
     * or more 2D coordinates
     * <p/>
     * @param a The base coordinate for addition
     * @param b The second coordinate for addition
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector2D add(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x += b0.x;
            a.y += b0.y;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the component-wise subtraction of
     * one or more 2D coordinates from a 2D coordinate
     * <p/>
     * @param a The base coordinate for subtraction
     * @param b The second coordinate for subtraction
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector2D sub(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x -= b0.x;
            a.y -= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear shifting (addition) of a
     * single 2D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A double value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D shift(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear shifting (addition) of a
     * single 2D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A float value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D shift(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear shifting (addition) of a
     * single 2D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b A long value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D shift(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear shifting (addition) of a
     * single 2D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for shifting
     * @param b An integer value to shift by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D shift(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    /**
     * Returns a new 2D Coordinate from the component-wise multiplication
     * of one or more 2D coordinates from a 3D coordinate
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b The second coordinate for multiplication
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector2D mult(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x *= b0.x;
            a.y *= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear multiplication of a
     * single 2D coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A double value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D mult(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear multiplication of a
     * single 2D coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A float value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D mult(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear multiplication of a
     * single 2D coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b A long value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D mult(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear multiplication of a
     * single 2D coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for multiplication
     * @param b An integer value to multiply by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D mult(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
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
    public static double dot(Vector2D a, Vector2D b) {
        return a.x * b.x + a.y * b.y;
    }

    /**
     * Returns a new 2D Coordinate from the cross product of two 2D
     * coordinates
     * <p/>
     * @param a The first element in the cross product
     * @param b The second element in the cross product
     * <p/>
     * @return The cross product of a and b, that is; a cross b
     */
    public static double cross(Vector2D a, Vector2D b) {
        return (a.x * b.y) - (a.y * b.x);
    }

    /**
     * Returns a new 2D Coordinate from the component-wise division of a 2D
     * coordinate by one or more other 2D coordinates
     * <p/>
     * @param a The base coordinate for division
     * @param b The second coordinate for division
     * <p/>
     * @return The component-wise addend of a, b, ...
     */
    public static Vector2D div(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x /= b0.x;
            a.y /= b0.y;
        }
        return a;
    }

    /**
     * Returns a new 2D Coordinate from the linear division of a single 2D
     * coordinate by one or more double values
     * <p/>
     * @param a The base coordinate for division
     * @param b A double value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D div(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear division of a single 2D
     * coordinate by one or more float values
     * <p/>
     * @param a The base coordinate for division
     * @param b A float value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D div(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear division of a single 2D
     * coordinate by one or more long values
     * <p/>
     * @param a The base coordinate for division
     * @param b A long value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D div(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    /**
     * Returns a new 2D Coordinate from the linear division of a single 2D
     * coordinate by one or more integer values
     * <p/>
     * @param a The base coordinate for division
     * @param b An integer value to divide by
     * <p/>
     * @return The addend of a, b, ...
     */
    public static Vector2D div(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    /**
     * Returns the component-wise maximum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the maximum x
     * will be found from g and be set to return.x, likewise with z;
     * giving a 2D coordinate comprising the highest value components found
     * from g
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to pull maximums from
     * <p/>
     * @return The component-wise maximum of two or more 2D coordinates
     */
    public static Vector2D componentMax(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x = a.x > b0.x ? a.x : b0.x;
            a.y = a.y > b0.y ? a.y : b0.y;
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
     * @param a The base coordinate
     * @param b A coordinate to pull minimums from
     * <p/>
     * @return The component-wise minimum of two or more 2D coordinates
     */
    public static Vector2D componentMin(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x = a.x < b0.x ? a.x : b0.x;
            a.y = a.y < b0.y ? a.y : b0.y;
        }
        return a;
    }

    /**
     * Returns the magnitude-based minimum 2D Coordinate from a group of
     * two or more. That is, given g group of 2D coordinates, the magnitude
     * will be calculated for all g and the coordinate with the highest
     * magnitude will be returned
     * <p/>
     * @param a The base coordinate
     * @param b A coordinate to test against the base
     * <p/>
     * @return The magnitude-based minimum of two or more 2D coordinates
     */
    public static Vector2D min(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
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
     * @param a The base coordinate
     * @param b A coordinate to test against the base
     * <p/>
     * @return The magnitude-based maximum of two or more 2D coordinates
     */
    public static Vector2D max(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
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
    public static Vector2D clamp(Vector2D a, Vector2D min, Vector2D max) {
        a.x = a.x < min.x ? min.x : (a.x > max.x ? max.x : a.x);
        a.y = a.y < min.y ? min.y : (a.y > max.y ? max.y : a.y);
        return a;
    }

    /**
     * Returns a 2D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an double min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 2D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector2D clamp(Vector2D a, double min, double max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    /**
     * Returns a 2D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an float min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 2D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector2D clamp(Vector2D a, float min, float max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    /**
     * Returns a 2D Coordinate that has had each of its components snapped
     * to a minimum and maximum if they fall outside the given values.
     * This method uses an long min and max to achieve
     * <b>coordinate-wise</b> clamping
     * <p/>
     * @param a   The base coordinate
     * @param min The minimum coordinate component
     * @param max The maximum coordinate component
     * <p/>
     * @return a value-clamped 2D coordinate whose component values do not
     *         fall outside of the <b>coordinate-wise</b> bounds given
     */
    public static Vector2D clamp(Vector2D a, long min, long max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
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
    public static Vector2D clamp(Vector2D a, int min, int max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    /**
     * Returns a 2D Coordinate with normalized component values
     * <p/>
     * @param a The 2D Coordinate to normalize
     * <p/>
     * @return A 2D Coordinate with normalized component values
     */
    public static Vector2D normalize(Vector2D a) {
        double scale = 1.0f / a.magnitude();
        a.x *= scale;
        a.y *= scale;
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
    public static Vector2D lerp(Vector2D a, Vector2D b, double blend) {
        a.x = blend * (b.x - a.x) + a.x;
        a.y = blend * (b.y - a.y) + a.y;
        return a;
    }

    /**
     * Returns a Barycentric interpolated 2D coordinate from three given
     * points and two blend factors
     * <p/>
     * @param a      The first point for interpolation
     * @param b      The second point for interpolation
     * @param c
     * @param blendx A blending factor
     * @param blendy A blending factor
     * <p/>
     * @return A new 2D coordinate from the Barycentric interpolated
     *         coordinate from three given points and two blend factors
     */
    public static Vector2D baryCentric(Vector2D a, Vector2D b, Vector2D c, double x, double y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Vector2D.add(Vector2D.mult(Vector2D.shift(a, x), Vector2D.sub(b, a)), Vector2D.mult(Vector2D.sub(c, a), y));
    }

    /**
     * Returns the double angle between two points
     * <p/>
     * @param a The first coordinate
     * @param b The second coordinate
     * <p/>
     * @return The angle between a and b represented in a double
     */
    public static double angle(Vector2D a, Vector2D b) {
        return Math.acos(Vector2D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Vector2D) {
            final Vector2D other = (Vector2D) obj;
            if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
                return false;
            }
            if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the x component of this coordinate
     * <p/>
     * @return The x component of this coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y component of this coordinate
     * <p/>
     * @return The y component of this coordinate
     */
    public double getY() {
        return y;
    }
}
