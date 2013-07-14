package com.timethor.terracontrol.core.util.vector;

import com.timethor.terracontrol.core.util.MathHelper;

public class Vector4D extends Vector3D {

    protected double w;

    public Vector4D() {
    }

    public Vector4D(double x, double y) {
        super(x, y);
        this.w = 0;
    }

    public Vector4D(double x, double y, double z) {
        super(x, y, z);
        this.w = 0;
    }

    public Vector4D(double x, double y, double z, double w) {
        super(x, y, z);
        this.w = w;
    }

    public Vector4D(Vector2D other, double z, double w) {
        super(other, z);
        this.w = w;
    }

    public Vector4D(Vector3D other, double w) {
        super(other);
        this.w = w;
    }

    public Vector4D(Vector4D other) {
        super(other);
        this.w = other.w;
    }

    //>>	END CONSTRUCTORS
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    @Override
    public double magnitudeSqared() {
        return super.magnitudeSqared() + w * w;
    }

    public static <T extends Vector2D> T getGenericVector(T a) {
        if (a instanceof Vector4D) {
            return (T) new Vector4D();
        } else {
            return Vector3D.getGenericVector(a);
        }
    }

    public static Vector4D add(Vector4D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
        }
        return new Vector4D(x0, y0, a.z, a.w);
    }

    public static Vector4D add(Vector4D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
            z0 += b0.z;
        }
        return new Vector4D(x0, y0, z0, a.w);
    }

    public static Vector4D add(Vector4D a, Vector4D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        double w0 = a.w;
        for (Vector4D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
            z0 += b0.z;
            w0 += b0.w;
        }
        return new Vector4D(x0, y0, z0, w0);
    }

    public static Vector4D sub(Vector4D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
        }
        return new Vector4D(x0, y0, a.z, a.w);
    }

    public static Vector4D sub(Vector4D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
            z0 -= b0.z;
        }
        return new Vector4D(x0, y0, z0, a.w);
    }

    public static Vector4D sub(Vector4D a, Vector4D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        double w0 = a.w;
        for (Vector4D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
            z0 -= b0.z;
            w0 -= b0.w;
        }
        return new Vector4D(x0, y0, z0, w0);
    }

    public static Vector4D shift(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    public static Vector4D shfit(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    public static Vector4D shift(Vector4D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    public static Vector4D shift(Vector4D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector4D(a.x + b1, a.y + b1, a.z + b1, a.w + b1);
    }

    public static Vector4D mult(Vector4D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
        }
        return new Vector4D(x0, y0, a.z, a.w);
    }

    public static Vector4D mult(Vector4D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
            z0 *= b0.z;
        }
        return new Vector4D(x0, y0, z0, a.w);
    }

    public static Vector4D mult(Vector4D a, Vector4D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        double w0 = a.w;
        for (Vector4D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
            z0 *= b0.z;
            w0 *= b0.w;
        }
        return new Vector4D(x0, y0, z0, w0);
    }

    public static Vector4D mult(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    public static Vector4D mult(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    public static Vector4D mult(Vector4D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    public static Vector4D mult(Vector4D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector4D(a.x * b1, a.y * b1, a.z * b1, a.w * b1);
    }

    public static double dot(Vector4D a, Vector4D b) {
        return Vector3D.dot(a, b) + a.w * b.w;
    }

    public static Vector4D cross(Vector4D a, Vector4D b, Vector4D c) {
        return new Vector4D(
            MathHelper.abs(a.x * (b.z * c.w - c.z * b.w) - a.z * (b.y * c.w - c.y * b.w) + a.w * (b.y * c.z - c.y * b.z)),
            MathHelper.abs(-a.x * (b.z * c.w - c.z * b.w) + a.z * (b.x * c.w - c.x * b.w) - a.w * (b.x * c.z - c.x * b.z)),
            MathHelper.abs(a.x * (b.y * c.w - c.y * b.w) - a.y * (b.x * c.w - c.x * b.w) + a.w * (b.x * c.y - c.x * b.y)),
            MathHelper.abs(-a.x * (b.y * c.z - c.y * b.z) + a.y * (b.x * c.z - c.x * b.z) - a.z * (b.x * c.y - c.x * b.y)));
    }

    public static Vector4D div(Vector4D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
        }
        return new Vector4D(x0, y0, a.z, a.w);
    }

    public static Vector4D div(Vector4D a, Vector3D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        for (Vector3D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
            z0 /= b0.z;
        }
        return new Vector4D(x0, y0, z0, a.w);
    }

    public static Vector4D div(Vector4D a, Vector4D... b) {
        double x0 = a.x;
        double y0 = a.y;
        double z0 = a.z;
        double w0 = a.w;
        for (Vector4D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
            z0 /= b0.z;
            w0 /= b0.w;
        }
        return new Vector4D(x0, y0, z0, w0);
    }

    public static Vector4D div(Vector4D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
    }

    public static Vector4D div(Vector4D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector4D(a.x / b1, a.y / b1, a.z / b1, a.w / b1);
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

    public double getW() {
        return w;
    }
}
