package com.timethor.terracontrol.core.util.coordinate;

import com.timethor.terracontrol.core.util.MathHelper;

public class Coordinate2D {

    protected int x;
    protected int z;

    public Coordinate2D() {
    }

    public Coordinate2D(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Coordinate2D(Coordinate2D other) {
        this.x = other.x;
        this.z = other.z;
    }

    //>>	END CONSTRUCTORS
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    public int magnitudeSqared() {
        return x * x + z * z;
    }

    public static <T extends Coordinate2D> T getGenericVector(T a) {
        if (a instanceof Coordinate2D) {
            return (T) new Coordinate2D();
        } else {
            return null;
        }
    }

    public static Coordinate2D add(Coordinate2D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 += b0.x;
            y0 += b0.z;
        }
        return new Coordinate2D(x0, y0);
    }

    public static Coordinate2D sub(Coordinate2D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.z;
        }
        return new Coordinate2D(x0, y0);
    }

    public static Coordinate2D shift(Coordinate2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x + b1), MathHelper.floor_int(a.z + b1));
    }

    public static Coordinate2D shift(Coordinate2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x + b1), MathHelper.floor_int(a.z + b1));
    }

    public static Coordinate2D shift(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 += b0;
        }
        return new Coordinate2D(a.x + b1, a.z + b1);
    }

    public static Coordinate2D mult(Coordinate2D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.z;
        }
        return new Coordinate2D(x0, y0);
    }

    public static Coordinate2D mult(Coordinate2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x * b1), MathHelper.floor_int(a.z * b1));
    }

    public static Coordinate2D mult(Coordinate2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x * b1), MathHelper.floor_int(a.z * b1));
    }

    public static Coordinate2D mult(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 *= b0;
        }
        return new Coordinate2D(a.x * b1, a.z * b1);
    }

    public static int dot(Coordinate2D a, Coordinate2D b) {
        return a.x * b.x + a.z * b.z;
    }

    public static int cross(Coordinate2D a, Coordinate2D b) {
        return (a.x * b.z) - (a.z * b.x);
    }

    public static Coordinate2D div(Coordinate2D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.z;
        }
        return new Coordinate2D(x0, y0);
    }

    public static Coordinate2D div(Coordinate2D a, double... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1));
    }

    public static Coordinate2D div(Coordinate2D a, float... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1));
    }

    public static Coordinate2D div(Coordinate2D a, int... b) {
        int b1 = 0;
        for (int b0 : b) {
            b1 /= b0;
        }
        return new Coordinate2D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1));
    }

    public static Coordinate2D componentMax(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x = a.x > b0.x ? a.x : b0.x;
            a.z = a.z > b0.z ? a.z : b0.z;
        }
        return a;
    }

    public static Coordinate2D componentMin(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a.x = a.x < b0.x ? a.x : b0.x;
            a.z = a.z < b0.z ? a.z : b0.z;
        }
        return a;
    }

    public static Coordinate2D min(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Coordinate2D max(Coordinate2D a, Coordinate2D... b) {
        for (Coordinate2D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Coordinate2D clamp(Coordinate2D a, Coordinate2D min, Coordinate2D max) {
        a.x = a.x < min.x ? min.x : (a.x > max.x ? max.x : a.x);
        a.z = a.z < min.z ? min.z : (a.z > max.z ? max.z : a.z);
        return a;
    }

    public static Coordinate2D clamp(Coordinate2D a, int min, int max) {
        a.x = MathHelper.floor_int(a.x < min ? min : (a.x > max ? max : a.x));
        a.z = MathHelper.floor_int(a.z < min ? min : (a.z > max ? max : a.z));
        return a;
    }

    public static Coordinate2D lerp(Coordinate2D a, Coordinate2D b, int blend) {
        a.x = blend * (b.x - a.x) + a.x;
        a.z = blend * (b.z - a.z) + a.z;
        return a;
    }

    public static Coordinate2D baryCentric(Coordinate2D a, Coordinate2D b, Coordinate2D c, int x, int y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Coordinate2D.add(Coordinate2D.mult(Coordinate2D.shift(a, x), Coordinate2D.sub(b, a)), Coordinate2D.mult(Coordinate2D.sub(c, a), y));
    }

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

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
