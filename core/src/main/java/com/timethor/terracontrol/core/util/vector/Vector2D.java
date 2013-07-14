package com.timethor.terracontrol.core.util.vector;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D() {
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    //>>	END CONSTRUCTORS
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    public double magnitudeSqared() {
        return x * x + y * y;
    }

    public static <T extends Vector2D> T getGenericVector(T a) {
        if (a instanceof Vector2D) {
            return (T) new Vector2D();
        } else {
            return null;
        }
    }

    public static Vector2D add(Vector2D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 += b0.x;
            y0 += b0.y;
        }
        return new Vector2D(x0, y0);
    }

    public static Vector2D sub(Vector2D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.y;
        }
        return new Vector2D(x0, y0);
    }

    public static Vector2D shift(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    public static Vector2D shift(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    public static Vector2D shift(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    public static Vector2D shift(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Vector2D(a.x + b1, a.y + b1);
    }

    public static Vector2D mult(Vector2D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.y;
        }
        return new Vector2D(x0, y0);
    }

    public static Vector2D mult(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    public static Vector2D mult(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    public static Vector2D mult(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    public static Vector2D mult(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Vector2D(a.x * b1, a.y * b1);
    }

    public static double dot(Vector2D a, Vector2D b) {
        return a.x * b.x + a.y * b.y;
    }

    public static double cross(Vector2D a, Vector2D b) {
        return (a.x * b.y) - (a.y * b.x);
    }

    public static Vector2D div(Vector2D a, Vector2D... b) {
        double x0 = a.x;
        double y0 = a.y;
        for (Vector2D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.y;
        }
        return new Vector2D(x0, y0);
    }

    public static Vector2D div(Vector2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    public static Vector2D div(Vector2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    public static Vector2D div(Vector2D a, long... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    public static Vector2D div(Vector2D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Vector2D(a.x / b1, a.y / b1);
    }

    public static Vector2D componentMax(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x = a.x > b0.x ? a.x : b0.x;
            a.y = a.y > b0.y ? a.y : b0.y;
        }
        return a;
    }

    public static Vector2D componentMin(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a.x = a.x < b0.x ? a.x : b0.x;
            a.y = a.y < b0.y ? a.y : b0.y;
        }
        return a;
    }

    public static Vector2D min(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Vector2D max(Vector2D a, Vector2D... b) {
        for (Vector2D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Vector2D clamp(Vector2D a, Vector2D min, Vector2D max) {
        a.x = a.x < min.x ? min.x : (a.x > max.x ? max.x : a.x);
        a.y = a.y < min.y ? min.y : (a.y > max.y ? max.y : a.y);
        return a;
    }

    public static Vector2D clamp(Vector2D a, double min, double max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    public static Vector2D clamp(Vector2D a, float min, float max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    public static Vector2D clamp(Vector2D a, long min, long max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    public static Vector2D clamp(Vector2D a, int min, int max) {
        a.x = a.x < min ? min : (a.x > max ? max : a.x);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    public static Vector2D normalize(Vector2D a) {
        double scale = 1.0f / a.magnitude();
        a.x *= scale;
        a.y *= scale;
        return a;
    }

    public static Vector2D lerp(Vector2D a, Vector2D b, double blend) {
        a.x = blend * (b.x - a.x) + a.x;
        a.y = blend * (b.y - a.y) + a.y;
        return a;
    }

    public static Vector2D baryCentric(Vector2D a, Vector2D b, Vector2D c, double x, double y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Vector2D.add(Vector2D.mult(Vector2D.shift(a, x), Vector2D.sub(b, a)), Vector2D.mult(Vector2D.sub(c, a), y));
    }

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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
