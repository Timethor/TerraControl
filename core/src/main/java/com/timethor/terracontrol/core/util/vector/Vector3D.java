package com.timethor.terracontrol.core.util.vector;

/**
 *
 * @author Timethor
 */
public class Vector3D extends Vector2D {

    /**
     *
     */
    protected double z;

    /**
     *
     */
    public Vector3D() {
    }

    /**
     *
     * @param x
     * @param y
     */
    public Vector3D(double x, double y) {
        super(x, y);
        this.z = 0;
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    /**
     *
     * @param other
     * @param z
     */
    public Vector3D(Vector2D other, double z) {
        super(other);
        this.z = z;
    }

    /**
     *
     * @param other
     */
    public Vector3D(Vector3D other) {
        super(other);
        this.z = other.z;
    }

    //>>	END CONSTRUCTORS
    /**
     *
     * @return
     */
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    /**
     *
     * @return
     */
    @Override
    public double magnitudeSqared() {
        return super.magnitudeSqared() + z * z;
    }

    /**
     *
     * @param <T>
     * @param a
     * @return
     */
    public static <T extends Vector2D> T getGenericVector(T a) {
        if (a instanceof Vector3D) {
            return (T) new Vector3D();
        } else {
            return Vector2D.getGenericVector(a);
        }
    }

    /**
     *
     * @param a
     * @param b
     * @return
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
     *
     * @param a
     * @param b
     * @return
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
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D sub(Vector3D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
        }
        return new Vector3D(x0, y0, a.z);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D sub(Vector3D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
            z0 -= b0.z;
        }
        return new Vector3D(x0, y0, z0);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D shift(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D shift(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D shift(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D shift(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector3D(a.x + b1, a.y + b1, a.z + b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
        }
        return new Vector3D(x0, y0, a.z);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
            z0 *= b0.z;
        }
        return new Vector3D(x0, y0, z0);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D mult(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector3D(a.x * b1, a.y * b1, a.z * b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double dot(Vector3D a, Vector3D b) {
        return Vector2D.dot(a, b) + a.z * b.z;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(
            (a.y * b.z) - (a.z * b.y),
            (a.z * b.x) - (a.x * b.z),
            Vector2D.cross(a, b));
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
        }
        return new Vector3D(x0, y0, a.z);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
            z0 /= b0.z;
        }
        return new Vector3D(x0, y0, z0);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D div(Vector3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector3D(a.x / b1, a.y / b1, a.z / b1);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D componentMax(Vector3D a, Vector3D... b) {
        a = (Vector3D) Vector2D.componentMax(a, b);
        for (Vector3D b0 : b) {
            a.z = a.z > b0.z ? a.z : b0.z;
        }
        return a;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D componentMin(Vector3D a, Vector3D... b) {
        a = (Vector3D) Vector2D.componentMax(a, b);
        for (Vector3D b0 : b) {
            a.z = a.z < b0.z ? a.z : b0.z;
        }
        return a;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D min(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static Vector3D max(Vector3D a, Vector3D... b) {
        for (Vector3D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    /**
     *
     * @param a
     * @param min
     * @param max
     * @return
     */
    public static Vector3D clamp(Vector3D a, Vector3D min, Vector3D max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min.z ? min.z : (a.z > max.z ? max.z : a.z);
        return a;
    }

    /**
     *
     * @param a
     * @param min
     * @param max
     * @return
     */
    public static Vector3D clamp(Vector3D a, double min, double max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     *
     * @param a
     * @param min
     * @param max
     * @return
     */
    public static Vector3D clamp(Vector3D a, float min, float max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     *
     * @param a
     * @param min
     * @param max
     * @return
     */
    public static Vector3D clamp(Vector3D a, long min, long max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     *
     * @param a
     * @param min
     * @param max
     * @return
     */
    public static Vector3D clamp(Vector3D a, int min, int max) {
        a = (Vector3D) Vector2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    /**
     *
     * @param a
     * @return
     */
    public static Vector3D normalize(Vector3D a) {
        double scale = 1.0f / a.magnitude();
        return new Vector3D(a.x * scale, a.y * scale, a.z * scale);
    }

    /**
     *
     * @param a
     * @param b
     * @param blend
     * @return
     */
    public static Vector3D lerp(Vector3D a, Vector3D b, double blend) {
        a = (Vector3D) Vector2D.lerp(a, b, blend);
        a.z = blend * (b.z - a.z) + a.z;
        return a;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param x
     * @param y
     * @return
     */
    public static Vector3D baryCentric(Vector3D a, Vector3D b, Vector3D c, double x, double y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Vector3D.add(Vector3D.mult(Vector3D.shift(a, x), Vector3D.sub(b, a)), Vector3D.mult(Vector3D.sub(c, a), y));
    }

    /**
     *
     * @param a
     * @param b
     * @return
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
     *
     * @return
     */
    public double getZ() {
        return z;
    }
}
